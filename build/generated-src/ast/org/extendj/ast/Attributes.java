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
 * @aspect BytecodeAttributes
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeAttributes.jrag:35
 */
 class Attributes extends java.lang.Object {
  /**
   * @aspect BytecodeAttributes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeAttributes.jrag:36
   */
  
    protected AbstractClassfileParser p;
  /**
   * @aspect BytecodeAttributes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeAttributes.jrag:37
   */
  
    protected boolean isSynthetic;
  /**
   * @aspect BytecodeAttributes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeAttributes.jrag:39
   */
  

    protected Attributes(AbstractClassfileParser parser) {
      p = parser;
      isSynthetic = false;
    }
  /**
   * @aspect BytecodeAttributes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeAttributes.jrag:44
   */
  

    protected void processAttribute(String attribute_name, int attribute_length)
        throws IOException {
      if (attribute_name.equals("Synthetic")) {
        isSynthetic = true;
      } else {
        p.skip(attribute_length);
      }
    }
  /**
   * @aspect BytecodeAttributes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeAttributes.jrag:53
   */
  

    protected void attributes() throws IOException {
      int attributes_count = p.u2();
      if (AbstractClassfileParser.VERBOSE) {
        p.println("    " + attributes_count + " attributes:");
      }
      for (int j = 0; j < attributes_count; j++) {
        int attribute_name_index = p.u2();
        int attribute_length = p.u4();
        String attribute_name = p.getCONSTANT_Utf8_Info(attribute_name_index).string();
        if (AbstractClassfileParser.VERBOSE) {
          p.println("    Attribute: " + attribute_name + ", length: " + attribute_length);
        }
        processAttribute(attribute_name, attribute_length);
      }
    }
  /**
   * @aspect BytecodeAttributes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeAttributes.jrag:69
   */
  

    public boolean isSynthetic() {
      return isSynthetic;
    }
  /**
   * @aspect BytecodeAttributes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeAttributes.jrag:74
   */
  

    // 4.8.15.1
    protected ElementValue readElementValue() throws IOException {
      char c = (char) p.u1();
      switch (c) {
        case 'e':
          int type_name_index = p.u2();
          String type_name = p.getCONSTANT_Utf8_Info(type_name_index).string();
          // Remove inital L and trailing semicolon.
          Access typeAccess = BytecodeParser.fromClassName(
              type_name.substring(1, type_name.length() - 1));
          int const_name_index = p.u2();
          String const_name = p.getCONSTANT_Utf8_Info(const_name_index).string();
          return new ElementConstantValue(typeAccess.qualifiesAccess(new VarAccess(const_name)));
        case 'B':
        case 'C':
        case 'D':
        case 'F':
        case 'I':
        case 'J':
        case 'S':
        case 'Z':
        case 's':
          int const_value_index = p.u2();
          Expr e = p.getCONSTANT_Info(const_value_index).expr();
          return new ElementConstantValue(e);
        case 'c':
          int class_info_index = p.u2();
          String descriptor = p.getCONSTANT_Utf8_Info(class_info_index).string();
          e = new TypeDescriptor(p, descriptor).type();
          return new ElementConstantValue(e);
        case '@':
          return new ElementAnnotationValue(readAnnotation());
        case '[':
          int index = p.u2();
          List list = new List();
          for (int i = 0; i < index; i++) {
            list.add(readElementValue());
          }
          return new ElementArrayValue(list);
        default:
          throw new Error("AnnotationDefault tag " + c + " not supported");
      }
    }
  /**
   * @aspect BytecodeAttributes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeAttributes.jrag:118
   */
  

    // 4.8.15
    protected Annotation readAnnotation() throws IOException {
      Access typeAccess = new FieldDescriptor(p, "").type();
      int num_element_value_pairs = p.u2();
      List list = new List();
      for (int i = 0; i < num_element_value_pairs; i++) {
        int element_name_index = p.u2();
        String element_name = p.getCONSTANT_Utf8_Info(element_name_index).string();
        ElementValue element_value = readElementValue();
        list.add(new ElementValuePair(element_name, element_value));
      }
      return new Annotation("Annotation", typeAccess, list);
    }
  /**
   * @aspect BytecodeAttributes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeAttributes.jrag:131
   */
  

    public static class FieldAttributes extends Attributes {
      protected CONSTANT_Info constantValue;
      public ArrayList<Annotation> annotations;
      public Signatures.FieldSignature fieldSignature;

      public FieldAttributes(AbstractClassfileParser p) throws IOException {
        super(p);
        attributes();
      }

      protected void processAttribute(String attribute_name, int attribute_length)
          throws IOException {
        if (attribute_name.equals("ConstantValue") && attribute_length == 2) {
          int constantvalue_index = p.u2();
          constantValue = p.getCONSTANT_Info(constantvalue_index);
        } else if (attribute_name.equals("RuntimeVisibleAnnotations")) {
          int num_annotations = p.u2();
          if (annotations == null) {
            annotations = new ArrayList<Annotation>();
          }
          for (int j = 0; j < num_annotations; j++) {
            annotations.add(readAnnotation());
          }
        } else if (attribute_name.equals("RuntimeInvisibleAnnotations")) {
          int num_annotations = p.u2();
          if (annotations == null) {
            annotations = new ArrayList<Annotation>();
          }
          for (int j = 0; j < num_annotations; j++) {
            annotations.add(readAnnotation());
          }
        } else if (attribute_name.equals("Signature")) {
          int signature_index = p.u2();
          String s = p.getCONSTANT_Utf8_Info(signature_index).string();
          fieldSignature = new Signatures.FieldSignature(s);
        } else {
          super.processAttribute(attribute_name, attribute_length);
        }
      }

      public CONSTANT_Info constantValue() {
        return constantValue;
      }
    }
  /**
   * @aspect BytecodeAttributes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeAttributes.jrag:176
   */
  

    public static class MethodAttributes extends Attributes {
      protected List exceptionList;
      protected ElementValue elementValue;
      public Signatures.MethodSignature methodSignature;
      public ArrayList<Annotation> annotations;
      public ArrayList<Annotation>[] parameterAnnotations;

      public MethodAttributes(AbstractClassfileParser p) throws IOException {
        super(p);
        attributes();
      }

      protected void processAttribute(String attribute_name, int attribute_length)
          throws IOException {
        if (attribute_name.equals("Exceptions")) {
          parseExceptions();
        } else if (attribute_name.equals("AnnotationDefault")) {
          annotationDefault();
        } else if (attribute_name.equals("Signature")) {
          int signature_index = p.u2();
          String s = p.getCONSTANT_Utf8_Info(signature_index).string();
          methodSignature = new Signatures.MethodSignature(s);
        } else if (attribute_name.equals("RuntimeVisibleAnnotations")) {
          int num_annotations = p.u2();
          if (annotations == null) {
            annotations = new ArrayList<Annotation>();
          }
          for (int j = 0; j < num_annotations; j++) {
            annotations.add(readAnnotation());
          }
        } else if (attribute_name.equals("RuntimeInvisibleAnnotations")) {
          int num_annotations = p.u2();
          if (annotations == null) {
            annotations = new ArrayList<Annotation>();
          }
          for (int j = 0; j < num_annotations; j++) {
            annotations.add(readAnnotation());
          }
        } else if (attribute_name.equals("RuntimeVisibleParameterAnnotations")) {
          int num_parameters = p.u1();
          if (parameterAnnotations == null) {
            parameterAnnotations = new ArrayList[num_parameters];
          }
          for (int i = 0; i < num_parameters; i++) {
            if (parameterAnnotations[i] == null) {
              parameterAnnotations[i] = new ArrayList<Annotation>();
            }
            int num_annotations = p.u2();
            for (int j = 0; j < num_annotations; j++) {
              parameterAnnotations[i].add(readAnnotation());
            }
          }
        } else if (attribute_name.equals("RuntimeInvisibleParameterAnnotations")) {
          int num_parameters = p.u1();
          if (parameterAnnotations == null) {
            parameterAnnotations = new ArrayList[num_parameters];
          }
          for (int i = 0; i < num_parameters; i++) {
            if (parameterAnnotations[i] == null) {
              parameterAnnotations[i] = new ArrayList<Annotation>();
            }
            int num_annotations = p.u2();
            for (int j = 0; j < num_annotations; j++) {
              parameterAnnotations[i].add(readAnnotation());
            }
          }
        } else {
          super.processAttribute(attribute_name, attribute_length);
        }
      }

      private void parseExceptions() throws IOException {
        int number_of_exceptions = p.u2();
        exceptionList = new List();
        if (AbstractClassfileParser.VERBOSE) {
          p.println("      " + number_of_exceptions + " exceptions:");
        }
        for (int i = 0; i < number_of_exceptions; i++) {
          CONSTANT_Class_Info exception = p.getCONSTANT_Class_Info(p.u2());
          if (AbstractClassfileParser.VERBOSE) {
            p.println("        exception " + exception.name());
          }
          exceptionList.add(exception.access());
        }
      }

      public List exceptionList() {
        return exceptionList != null ? exceptionList : new List();
      }

      public ElementValue elementValue() {
        return elementValue;
      }

      // 4.8.19
      private void annotationDefault() throws IOException {
        elementValue = readElementValue();
      }

    }
  /**
   * @aspect BytecodeAttributes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeAttributes.jrag:277
   */
  

    public static class TypeAttributes extends Attributes {
      TypeDecl typeDecl;
      TypeDecl outerTypeDecl;
      Program classPath;

      private boolean isInnerClass;

      public TypeAttributes(AbstractClassfileParser p, TypeDecl typeDecl, TypeDecl outerTypeDecl,
          Program classPath) throws IOException {
        super(p);
        this.typeDecl = typeDecl;
        this.outerTypeDecl = outerTypeDecl;
        this.classPath = classPath;
        attributes();
      }

      public boolean isInnerClass() {
        return isInnerClass;
      }

      protected void processAttribute(String attribute_name, int attribute_length)
          throws IOException {
        if (attribute_name.equals("InnerClasses")) {
          innerClasses();
        } else if (attribute_name.equals("Signature")) {
          int signature_index = p.u2();
          String s = p.getCONSTANT_Utf8_Info(signature_index).string();
          Signatures.ClassSignature classSignature = new Signatures.ClassSignature(s);
          typeDecl = typeDecl.makeGeneric(classSignature);
        } else if (attribute_name.equals("RuntimeVisibleAnnotations")) {
          int num_annotations = p.u2();
          for (int j = 0; j < num_annotations; j++) {
            Annotation a = readAnnotation();
            typeDecl.getModifiers().addModifier(a);
          }
        } else if (attribute_name.equals("RuntimeInvisibleAnnotations")) {
          int num_annotations = p.u2();
          for (int j = 0; j < num_annotations; j++) {
            Annotation a = readAnnotation();
            typeDecl.getModifiers().addModifier(a);
          }
        } else {
          super.processAttribute(attribute_name, attribute_length);
        }
      }

      protected void innerClasses() throws IOException {
        int number_of_classes = p.u2();
        if (AbstractClassfileParser.VERBOSE) {
          p.println("    Number of classes: " + number_of_classes);
        }
        for (int i = 0; i < number_of_classes; i++) {
          if (AbstractClassfileParser.VERBOSE) {
            p.print("      " + i + "(" + number_of_classes + ")" +  ":");
          }
          int inner_class_info_index = p.u2();
          int outer_class_info_index = p.u2();
          int inner_name_index = p.u2();
          int inner_class_access_flags = p.u2();
          if (inner_class_info_index > 0) {
            CONSTANT_Class_Info inner_class_info = p.getCONSTANT_Class_Info(
                inner_class_info_index);
            String inner_class_name = inner_class_info.name();
            String inner_name = inner_class_name.substring(inner_class_name.lastIndexOf("$") + 1);
            String outer_class_name;
            if (outer_class_info_index > 0) {
              CONSTANT_Class_Info outer_class_info = p.getCONSTANT_Class_Info(
                  outer_class_info_index);
              if (inner_class_info == null || outer_class_info == null) {
                System.out.println("Null");
              }
              outer_class_name = outer_class_info.name();
              if (AbstractClassfileParser.VERBOSE) {
                p.println("      inner: " + inner_class_name + ", outer: " + outer_class_name);
              }
            } else {
              // Anonymous inner class; need to infer outer_class_name from inner_class_name.
              outer_class_name = inner_class_name.substring(0, inner_class_name.lastIndexOf("$"));
            }
            if (inner_class_info.name().equals(p.classInfo.name())) {
              if (AbstractClassfileParser.VERBOSE) {
                p.println("      Class " + inner_class_name + " is inner (" + inner_name + ")");
              }
              typeDecl.setID(inner_name);

              // Replace non-annotation modifiers:
              List<Modifier> prevModifiers = typeDecl.getModifiers().getModifierList();
              typeDecl.setModifiers(
                  AbstractClassfileParser.modifiers(inner_class_access_flags & 0x041f));
              for (Modifier mod : prevModifiers) {
                if (mod instanceof Annotation) {
                  typeDecl.getModifiers().addModifier(mod);
                }
              }

              if (p.outerClassNameEquals(outer_class_name)) {
                MemberTypeDecl m = null;
                if (typeDecl instanceof ClassDecl) {
                  m = new MemberClassDecl((ClassDecl) typeDecl);
                  outerTypeDecl.addBodyDecl(m);
                } else if (typeDecl instanceof InterfaceDecl) {
                  m = new MemberInterfaceDecl((InterfaceDecl) typeDecl);
                  outerTypeDecl.addBodyDecl(m);
                }
              } else {
                isInnerClass = true;
              }
            }
            if (outer_class_name.equals(p.classInfo.name())) {
              if (AbstractClassfileParser.VERBOSE) {
                p.println("      Class " + p.classInfo.name()
                    + " has inner class: " + inner_class_name);
              }
              if (AbstractClassfileParser.VERBOSE) {
                p.println("Begin processing: " + inner_class_name);
              }
              try {
                InputStream is = null;
                try {
                  is = classPath.getInputStream(inner_class_name);
                } catch(Error e) {
                  if (e.getMessage().startsWith("Could not find nested type")) {
                    // Ignore.
                  } else {
                    throw e;
                  }
                }
                if (is != null) {
                  BytecodeParser p2 = new BytecodeParser(is, p.name);
                  p2.parse(typeDecl, outer_class_name, classPath,
                      (inner_class_access_flags & Flags.ACC_STATIC) == 0);
                  is.close();
                } else {
                  p.println("Error: ClassFile " + inner_class_name
                      + " not found");
                }
              } catch (FileNotFoundException e) {
                System.out.println("Error: " + inner_class_name
                    + " not found");
              } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
              }
              if (AbstractClassfileParser.VERBOSE) {
                p.println("End processing: " + inner_class_name);
              }
            }
          }
        }
        if (AbstractClassfileParser.VERBOSE) {
          p.println("    end");
        }
      }
    }

}
