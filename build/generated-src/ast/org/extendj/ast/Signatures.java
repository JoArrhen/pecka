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
 * @aspect BytecodeSignatures
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:33
 */
public class Signatures extends java.lang.Object {
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:35
   */
  
    // Simple parser framework.
    String data;
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:36
   */
  
    int pos;
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:38
   */
  

    public Signatures(String s) {
      data = s;
      pos = 0;
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:43
   */
  

    public boolean next(String s) {
      for (int i = 0; i < s.length(); i++) {
        if (data.charAt(pos + i) != s.charAt(i)) {
          return false;
        }
      }
      return true;
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:52
   */
  

    public void eat(String s) {
      for (int i = 0; i < s.length(); i++) {
        if (data.charAt(pos + i) != s.charAt(i)) {
          error(s);
        }
      }
      pos += s.length();
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:61
   */
  

    public void error(String s) {
      throw new Error("Expected " + s + " but found " + data.substring(pos));
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:65
   */
  

    public String identifier() {
      int i = pos;
      while (Character.isJavaIdentifierPart(data.charAt(i))) {
        i++;
      }
      String result = data.substring(pos, i);
      pos = i;
      return result;
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:75
   */
  

    public boolean eof() {
      return pos == data.length();
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:81
   */
  

    // 4.4.4 Signatures

    public static class ClassSignature extends Signatures {
      protected Access superclassSignature;

      protected List superinterfaceSignature = new List();

      public ClassSignature(String s) {
        super(s);
        classSignature();
      }

      void classSignature() {
        if (next("<")) {
          formalTypeParameters();
        }
        superclassSignature = parseSuperclassSignature();
        while (!eof()) {
          superinterfaceSignature.add(parseSuperinterfaceSignature());
        }
      }

      public boolean hasFormalTypeParameters() {
        return typeParameters != null;
      }

      public List typeParameters() {
        return typeParameters;
      }

      public boolean hasSuperclassSignature() {
        return superclassSignature != null;
      }

      public Access superclassSignature() {
        return superclassSignature;
      }

      public boolean hasSuperinterfaceSignature() {
        return superinterfaceSignature.getNumChildNoTransform() != 0;
      }

      public List superinterfaceSignature() {
        return superinterfaceSignature;
      }

      Access parseSuperclassSignature() {
        return classTypeSignature();
      }

      Access parseSuperinterfaceSignature() {
        return classTypeSignature();
      }
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:134
   */
  

    public static class FieldSignature extends Signatures {
      private Access fieldTypeAccess;

      public FieldSignature(String s) {
        super(s);
        fieldTypeAccess = fieldTypeSignature();
      }

      Access fieldTypeAccess() {
        return fieldTypeAccess;
      }
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:147
   */
  

    public static class MethodSignature extends Signatures {
      protected Collection<Access> parameterTypes = new ArrayList<Access>();

      protected List exceptionList = new List();

      protected Access returnType = null;

      public MethodSignature(String s) {
        super(s);
        methodTypeSignature();
      }

      void methodTypeSignature() {
        if (next("<")) {
          formalTypeParameters();
        }
        eat("(");
        while (!next(")")) {
          parameterTypes.add(typeSignature());
        }
        eat(")");
        returnType = parseReturnType();
        while (!eof()) {
          exceptionList.add(throwsSignature());
        }
      }

      Access parseReturnType() {
        if (next("V")) {
          eat("V");
          return new PrimitiveTypeAccess("void");
        } else {
          return typeSignature();
        }
      }

      Access throwsSignature() {
        eat("^");
        if (next("L")) {
          return classTypeSignature();
        } else {
          return typeVariableSignature();
        }
      }

      public boolean hasFormalTypeParameters() {
        return typeParameters != null;
      }

      public List typeParameters() {
        return typeParameters;
      }

      public Collection<Access> parameterTypes() {
        return parameterTypes;
      }

      public List exceptionList() {
        return exceptionList;
      }

      public boolean hasExceptionList() {
        return exceptionList.getNumChildNoTransform() != 0;
      }

      public boolean hasReturnType() {
        return returnType != null;
      }

      public Access returnType() {
        return returnType;
      }

    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:222
   */
  

    protected List typeParameters;
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:224
   */
  

    void formalTypeParameters() {
      eat("<");
      typeParameters = new List();
      do {
        typeParameters.add(formalTypeParameter());
      } while (!next(">"));
      eat(">");
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:233
   */
  

    TypeVariable formalTypeParameter() {
      String id = identifier();
      List<Access> bounds = new List<Access>();
      Access classBound = classBound();
      if (classBound != null) {
        bounds.add(classBound);
      }
      while (next(":")) {
        bounds.add(interfaceBound());
      }
      if (bounds.getNumChildNoTransform() == 0) {
        bounds.add(new TypeAccess("java.lang", "Object"));
      }
      return new TypeVariable(new Modifiers(new List()), id, new List(), bounds);
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:249
   */
  

    Access classBound() {
      eat(":");
      if (nextIsFieldTypeSignature()) {
        return fieldTypeSignature();
      } else {
        return null;
      }
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:258
   */
  

    Access interfaceBound() {
      eat(":");
      return fieldTypeSignature();
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:263
   */
  

    Access fieldTypeSignature() {
      if (next("L")) {
        return classTypeSignature();
      } else if (next("[")) {
        return arrayTypeSignature();
      } else if (next("T")) {
        return typeVariableSignature();
      } else {
        error("L or [ or T");
      }
      return null; // Error never returns.
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:276
   */
  

    boolean nextIsFieldTypeSignature() {
      return next("L") || next("[") || next("T");
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:280
   */
  

    Access classTypeSignature() {
      eat("L");
      // Package and Type Name.
      StringBuilder packageName = new StringBuilder();
      String typeName = identifier();
      while (next("/")) {
        eat("/");
        if (packageName.length() != 0) {
          packageName.append(".");
        }
        packageName.append(typeName);
        typeName = identifier();
      }
      Access a = typeName.indexOf('$') == -1 ?
        new TypeAccess(packageName.toString(), typeName) :
        new BytecodeTypeAccess(packageName.toString(), typeName);
      if (next("<")) { // Type arguments of top level type.
        a = new ParTypeAccess(a, typeArguments());
      }
      while (next(".")) { // Inner classes.
        a = a.qualifiesAccess(classTypeSignatureSuffix());
      }
      eat(";");
      return a;
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:306
   */
  

    Access classTypeSignatureSuffix() {
      eat(".");
      String id = identifier();
      Access a = id.indexOf('$') == -1 ?
        new TypeAccess(id) : new BytecodeTypeAccess("", id);
      if (next("<")) {
        a = new ParTypeAccess(a, typeArguments());
      }
      return a;
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:317
   */
  

    Access typeVariableSignature() {
      eat("T");
      String id = identifier();
      eat(";");
      return new TypeAccess(id);
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:324
   */
  

    List typeArguments() {
      eat("<");
      List list = new List();
      do {
        list.add(typeArgument());
      } while (!next(">"));
      eat(">");
      return list;
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:334
   */
  

    Access typeArgument() {
      if (next("*")) {
        eat("*");
        return new Wildcard();
      } else if (next("+")) {
        eat("+");
        return new WildcardExtends(fieldTypeSignature());
      } else if (next("-")) {
        eat("-");
        return new WildcardSuper(fieldTypeSignature());
      } else {
        return fieldTypeSignature();
      }
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:349
   */
  

    Access arrayTypeSignature() {
      eat("[");
      return new ArrayTypeAccess(typeSignature());
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:354
   */
  

    Access typeSignature() {
      if (nextIsFieldTypeSignature()) {
        return fieldTypeSignature();
      } else {
        return baseType();
      }
    }
  /**
   * @aspect BytecodeSignatures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:362
   */
  

    Access baseType() {
      if (next("B")) {
        eat("B");
        return new PrimitiveTypeAccess("byte");
      } else if (next("C")) {
        eat("C");
        return new PrimitiveTypeAccess("char");
      } else if (next("D")) {
        eat("D");
        return new PrimitiveTypeAccess("double");
      } else if (next("F")) {
        eat("F");
        return new PrimitiveTypeAccess("float");
      } else if (next("I")) {
        eat("I");
        return new PrimitiveTypeAccess("int");
      } else if (next("J")) {
        eat("J");
        return new PrimitiveTypeAccess("long");
      } else if (next("S")) {
        eat("S");
        return new PrimitiveTypeAccess("short");
      } else if (next("Z")) {
        eat("Z");
        return new PrimitiveTypeAccess("boolean");
      }
      error("baseType");
      return null; // Error never returns.
    }

}
