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
 * @aspect BytecodeDescriptor
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:160
 */
 class MethodInfo extends java.lang.Object {
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:161
   */
  
    private AbstractClassfileParser p;
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:162
   */
  
    String name;
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:163
   */
  
    int flags;
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:164
   */
  
    private MethodDescriptor methodDescriptor;
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:165
   */
  
    private Attributes.MethodAttributes attributes;
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:167
   */
  

    public MethodInfo(AbstractClassfileParser parser) throws IOException {
      p = parser;
      flags = p.u2();
      if (AbstractClassfileParser.VERBOSE) {
        p.print("  Flags: " + Integer.toBinaryString(flags));
      }
      int name_index = p.u2();
      CONSTANT_Info info = p.constantPool[name_index];
      if (info == null || !(info instanceof CONSTANT_Utf8_Info)) {
        System.err.println("Expected CONSTANT_Utf8_Info but found: " + info.getClass().getName());
      }
      name = ((CONSTANT_Utf8_Info) info).string();
      methodDescriptor = new MethodDescriptor(p, name);
      attributes = new Attributes.MethodAttributes(p);
    }
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:183
   */
  

    public BodyDecl bodyDecl() {
      Signatures.MethodSignature s = attributes.methodSignature;
      Access returnType = (s != null && s.hasReturnType())
          ? s.returnType()
          : methodDescriptor.type();
      List parameterList;
      if (isConstructor() && p.isInnerClass) {
        parameterList = methodDescriptor.parameterListSkipFirst();
        if (s != null) {
          Iterator<Access> iter = s.parameterTypes().iterator();
          if (iter.hasNext()) {
            iter.next();
          }
          for (int i = 0; iter.hasNext(); i++) {
            Access a = iter.next();
            ((ParameterDeclaration) parameterList.getChildNoTransform(i)).setTypeAccess(a);
          }
        }
      } else {
        parameterList = methodDescriptor.parameterList();
        if (s != null) {
          int i = 0;
          for (Access a : s.parameterTypes()) {
            ((ParameterDeclaration) parameterList.getChildNoTransform(i++)).setTypeAccess(a);
          }
        }
      }
      if ((flags & Flags.ACC_VARARGS) != 0) {
        int lastIndex = parameterList.getNumChildNoTransform() - 1;
        // The last index is -1 if this is the constructor of a static anonymous
        // inner class with a variable arity parameter list.
        // Since this only affects anonymous inner classes we don't have to
        // generate a precise AST for the bytecode. We could even skip loading
        // the bytecode entirely.
        if (lastIndex >= 0) {
          ParameterDeclaration p = (ParameterDeclaration)
              parameterList.getChildNoTransform(lastIndex);
          parameterList.setChild(
              new VariableArityParameterDeclaration(
                  p.getModifiersNoTransform(),
                  ((ArrayTypeAccess) p.getTypeAccessNoTransform()).getAccessNoTransform(),
                  p.getID()),
              lastIndex);
        }
      }
      List exceptionList = (s != null && s.hasExceptionList())
          ? s.exceptionList()
          : attributes.exceptionList();

      if (attributes.parameterAnnotations != null) {
        for (int i = 0; i < attributes.parameterAnnotations.length; i++) {
          ParameterDeclaration p = (ParameterDeclaration) parameterList.getChildNoTransform(i);
          for (Annotation annotation : attributes.parameterAnnotations[i]) {
            p.getModifiersNoTransform().addModifier(annotation);
          }
        }
      }

      BodyDecl b;
      if (isConstructor()) {
        if (s == null || !s.hasFormalTypeParameters()) {
          b = new ConstructorDecl(AbstractClassfileParser.modifiers(flags), name, parameterList,
              exceptionList, new Opt(), new Block());
        } else {
          b = new GenericConstructorDecl(
              AbstractClassfileParser.modifiers(flags),
              name,
              parameterList,
              exceptionList,
              new Opt(),
              new Block(),
              s.typeParameters());
        }
      } else if (attributes.elementValue() != null) {
        b = new AnnotationMethodDecl(AbstractClassfileParser.modifiers(flags), returnType, name,
            parameterList, exceptionList,
            new Opt(new Block()), new Opt(attributes.elementValue()));
      } else if (s != null && s.hasFormalTypeParameters()) {
        b = new GenericMethodDecl(AbstractClassfileParser.modifiers(flags), returnType, name,
            parameterList, exceptionList, new Opt(new Block()), s.typeParameters());
      } else {
        b = new MethodDecl(AbstractClassfileParser.modifiers(flags), returnType,
            name, parameterList, exceptionList, new Opt(new Block()));
      }
      if (attributes.annotations != null) {
        for (Annotation annotation : attributes.annotations) {
          if (b instanceof MethodDecl) {
            ((MethodDecl) b).getModifiers().addModifier(annotation);
          } else if (b instanceof ConstructorDecl) {
            ((ConstructorDecl) b).getModifiers().addModifier(annotation);
          }
        }
      }
      return b;
    }
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:279
   */
  

    private boolean isConstructor() {
      return name.equals("<init>");
    }
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:283
   */
  

    public boolean isSynthetic() {
      return attributes.isSynthetic() || (flags & 0x1000) != 0;
    }

}
