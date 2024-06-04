package org.extendj.ast;

import java.util.*;
import java.util.zip.*;
import java.io.*;
import org.jastadd.util.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import beaver.Symbol;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.stream.StreamSupport;
import java.net.URL;
import java.util.function.Predicate;
import java.lang.reflect.Field;
import org.jastadd.util.PrettyPrinter;
import java.io.BufferedInputStream;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.io.PrintStream;
import java.util.regex.Pattern;
import org.extendj.callgraph.AttributeTracer;
import java.util.concurrent.ConcurrentSkipListMap;
import java.io.InputStream;
import java.util.concurrent.ConcurrentMap;
import org.jastadd.util.PrettyPrintable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;
import org.extendj.Cat;
import java.util.Collection;
import java.io.File;
/**
 * @ast class
 * @aspect BytecodeReader
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/BytecodeReader.jrag:35
 */
public class BytecodeParser extends AbstractClassfileParser implements Flags {
  /**
   * @aspect BytecodeReader
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/BytecodeReader.jrag:37
   */
  

    static final int supportedMajorVersion;
  /**
   * @aspect BytecodeReader
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/BytecodeReader.jrag:38
   */
  
    static final String supportedJavaVersion;
  /**
   * @aspect BytecodeReader
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/BytecodeReader.jrag:39
   */
  
    static {
      supportedMajorVersion = new ClassDecl().majorVersion();
      String javaVersion = org.extendj.ExtendJVersion.getSupportedJavaVersion();
      if (!javaVersion.isEmpty()) {
        supportedJavaVersion = String.format(" (%s)", javaVersion);
      } else {
        supportedJavaVersion = "";
      }
    }
  /**
   * @aspect BytecodeReader
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/BytecodeReader.jrag:49
   */
  

    public String outerClassName;
  /**
   * @aspect BytecodeReader
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/BytecodeReader.jrag:51
   */
  

    public BytecodeParser(InputStream in, String name) {
      super(in, name);
    }
  /**
   * @aspect BytecodeReader
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/BytecodeReader.jrag:55
   */
  

    @Override
    public boolean outerClassNameEquals(String name) {
      return outerClassName != null && outerClassName.equals(name);
    }
  /**
   * @aspect BytecodeReader
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/BytecodeReader.jrag:60
   */
  

    public CompilationUnit parse(TypeDecl outerTypeDecl, String outerClassName,
        Program classPath, boolean isInner)
        throws FileNotFoundException, IOException {
      isInnerClass = isInner;
      return parse(outerTypeDecl, outerClassName, classPath);
    }
  /**
   * @aspect BytecodeReader
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/BytecodeReader.jrag:67
   */
  

    public CompilationUnit parse(TypeDecl outerTypeDecl, String outerClassName, Program program)
        throws FileNotFoundException, IOException {

      if (VERBOSE) {
        println("Parsing byte codes in " + name);
      }

      try {
        this.outerClassName = outerClassName;
        parseMagic();
        int minor = parseMinor();
        int major = parseMajor();
        if (AbstractClassfileParser.VERBOSE) {
          println(String.format("Classfile version: %d.%d", major, minor));
        }
        if (major > supportedMajorVersion) {
          String ver = String.format("%d.%d", major, minor);
          warning(String.format("Warning: attempting to parse classfile version %s (%s)."
                + " Classfile versions up to %d.x%s are supported by"
                + " this version of the compiler.",
                ver, name, supportedMajorVersion, supportedJavaVersion), ver);
        }
        parseConstantPool();
        CompilationUnit cu = new CompilationUnit();
        TypeDecl typeDecl = parseTypeDecl();
        cu.setPackageDecl(classInfo.packageDecl());
        cu.addTypeDecl(typeDecl);
        parseFields(typeDecl);
        parseMethods(typeDecl);
        new Attributes.TypeAttributes(this, typeDecl, outerTypeDecl, program);
        return cu;
      } catch (Error e) {
        throw new IOException("Failed to parse classfile: " + name, e);
      }
    }
  /**
   * @aspect BytecodeReader
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/BytecodeReader.jrag:103
   */
  

    public TypeDecl parseTypeDecl() throws IOException {
      int flags = u2();
      Modifiers modifiers = modifiers(flags & 0xfddf);
      if ((flags & (ACC_INTERFACE | ACC_ENUM)) == ACC_ENUM) {
        // Modifiers <ID:String> /[SuperClass:Access]/ Implements:Access* BodyDecl*;
        EnumDecl decl = new EnumDecl();
        decl.setModifiers(modifiers);
        decl.setID(parseThisClass());
        Access superClass = parseSuperClass();
        // TODO trying to overwrite NTA?
        decl.setImplementsList(parseInterfaces(new List()));
        return decl;
      } else if ((flags & ACC_INTERFACE) == 0) {
        ClassDecl decl = new ClassDecl();
        decl.setModifiers(modifiers);
        decl.setID(parseThisClass());
        Access superClass = parseSuperClass();
        decl.setSuperClassOpt(
            superClass == null
            ? new Opt()
            : new Opt(superClass));
        decl.setImplementsList(parseInterfaces(new List()));
        return decl;
      } else if ((flags & ACC_ANNOTATION) == 0) {
        InterfaceDecl decl = new InterfaceDecl();
        decl.setModifiers(modifiers);
        decl.setID(parseThisClass());
        Access superClass = parseSuperClass();
        decl.setSuperInterfaceList(parseInterfaces(
            superClass == null
            ? new List()
            : new List().add(superClass)));
        return decl;
      } else {
        AnnotationDecl decl = new AnnotationDecl();
        decl.setModifiers(modifiers);
        decl.setID(parseThisClass());
        Access superClass = parseSuperClass();
        parseInterfaces(
            superClass == null
            ? new List()
            : new List().add(superClass));
        return decl;
      }
    }
  /**
   * @aspect BytecodeReader
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/BytecodeReader.jrag:149
   */
  

    public static Access fromClassName(String s) {
      // Sample ClassName: a/b/c$d$e
      // the package name ends at the last '/'
      // after that follows a list of type names separated by '$'
      // all except the first are nested types

      String packageName = "";
      int index = s.lastIndexOf('/');
      if (index != -1) {
        packageName = s.substring(0, index).replace('/', '.');
      }
      String typeName = s.substring(index + 1, s.length());
      if (typeName.indexOf('$') != -1) {
        return new BytecodeTypeAccess(packageName, typeName);
      } else {
        return new TypeAccess(packageName, typeName);
      }
    }
  /**
   * @aspect BytecodeReader
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/BytecodeReader.jrag:168
   */
  

    public void parseMethods(TypeDecl typeDecl) throws IOException {
      int count = u2();
      if (VERBOSE) {
        println("Methods (" + count + "):");
      }
      for (int i = 0; i < count; i++) {
        if (VERBOSE) {
          print("  Method nbr " + i + " ");
        }
        MethodInfo info = new MethodInfo(this);
        if (!info.isSynthetic() && !info.name.equals("<clinit>")) {
          BodyDecl bodyDecl = info.bodyDecl();
          if (bodyDecl instanceof MethodDecl && typeDecl instanceof InterfaceDecl) {
            MethodDecl methodDecl = (MethodDecl) bodyDecl;
            boolean foundAbstract = false;
            boolean foundStatic = false;
            for (int j = 0; j < methodDecl.getModifiers().getNumModifier(); j++) {
              Modifier modifier = methodDecl.getModifiers().getModifier(j);
              if (modifier.getID().equals("abstract")) {
                foundAbstract = true;
              } else if (modifier.getID().equals("static")) {
                foundStatic = true;
              }
            }
            if (!foundAbstract && !foundStatic) {
              methodDecl.getModifiers().addModifier(new Modifier("default"));
            }
          }
          typeDecl.addBodyDecl(bodyDecl);
        }
      }
    }
  /**
   * @aspect BytecodeReader
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/BytecodeReader.jrag:201
   */
  

    @Override
    public void parseConstantPoolEntry(int i) throws IOException {
      int tag = u1();
      switch (tag) {
        case CONSTANT_Class:
          constantPool[i] = new CONSTANT_Class_Info(this);
          break;
        case CONSTANT_FieldRef:
          constantPool[i] = new CONSTANT_Fieldref_Info(this);
          break;
        case CONSTANT_MethodRef:
          constantPool[i] = new CONSTANT_Methodref_Info(this);
          break;
        case CONSTANT_InterfaceMethodRef:
          constantPool[i] = new CONSTANT_InterfaceMethodref_Info(this);
          break;
        case CONSTANT_String:
          constantPool[i] = new CONSTANT_String_Info(this);
          break;
        case CONSTANT_Integer:
          constantPool[i] = new CONSTANT_Integer_Info(this);
          break;
        case CONSTANT_Float:
          constantPool[i] = new CONSTANT_Float_Info(this);
          break;
        case CONSTANT_Long:
          constantPool[i] = new CONSTANT_Long_Info(this);
          break;
        case CONSTANT_Double:
          constantPool[i] = new CONSTANT_Double_Info(this);
          break;
        case CONSTANT_NameAndType:
          constantPool[i] = new CONSTANT_NameAndType_Info(this);
          break;
        case CONSTANT_Utf8:
          constantPool[i] = new CONSTANT_Utf8_Info(this);
          break;
        case 15:
          u1();
          u2();
          break;
        case 16:
          u2();
          break;
        case 18:
          u2();
          u2();
        default:
          println("Unknown entry: " + tag);
      }
    }

}
