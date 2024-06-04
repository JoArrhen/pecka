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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:289
 */
 class TypeDescriptor extends java.lang.Object {
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:290
   */
  
    private AbstractClassfileParser p;
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:291
   */
  
    private String descriptor;
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:293
   */
  

    public TypeDescriptor(AbstractClassfileParser parser, String descriptor) {
      p = parser;
      this.descriptor = descriptor;
    }
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:298
   */
  

    public boolean isBoolean() {
      return descriptor.charAt(0) == 'Z';
    }
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:302
   */
  

    public Access type() {
      return type(descriptor);
    }
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:306
   */
  

    public Access type(String s) {
      char c = s.charAt(0);
      switch (c) {
        case 'B':
          return new PrimitiveTypeAccess("byte");
        case 'C':
          return new PrimitiveTypeAccess("char");
        case 'D':
          return new PrimitiveTypeAccess("double");
        case 'F':
          return new PrimitiveTypeAccess("float");
        case 'I':
          return new PrimitiveTypeAccess("int");
        case 'J':
          return new PrimitiveTypeAccess("long");
        case 'S':
          return new PrimitiveTypeAccess("short");
        case 'Z':
          return new PrimitiveTypeAccess("boolean");
        case 'L':
          return BytecodeParser.fromClassName(s.substring(1, s.length() - 1));
        case '[':
          return new ArrayTypeAccess(type(s.substring(1)));
        case 'V':
          return new PrimitiveTypeAccess("void");
        default:
          throw new Error("Error: unknown Type in TypeDescriptor: " + s);
      }
    }
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:336
   */
  

    public List parameterList() {
      List list = new List();
      String s = descriptor;
      while (!s.equals("")) {
        s = typeList(s, list);
      }
      return list;
    }
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:344
   */
  
    public List parameterListSkipFirst() {
      List list = new List();
      String s = descriptor;
      if (!s.equals("")) {
        s = typeList(s, new List()); // skip first
      }
      while (!s.equals("")) {
        s = typeList(s, list);
      }
      return list;
    }
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:356
   */
  

    public String typeList(String s, List l) {
      char c = s.charAt(0);
      switch (c) {
        case 'B':
          l.add(new ParameterDeclaration(new Modifiers(),
                new PrimitiveTypeAccess("byte"), "p" + l.getNumChildNoTransform()));
          return s.substring(1);
        case 'C':
          l.add(new ParameterDeclaration(new Modifiers(),
                new PrimitiveTypeAccess("char"), "p" + l.getNumChildNoTransform()));
          return s.substring(1);
        case 'D':
          l.add(new ParameterDeclaration(new Modifiers(),
                new PrimitiveTypeAccess("double"), "p" + l.getNumChildNoTransform()));
          return s.substring(1);
        case 'F':
          l.add(new ParameterDeclaration(new Modifiers(),
                new PrimitiveTypeAccess("float"), "p" + l.getNumChildNoTransform()));
          return s.substring(1);
        case 'I':
          l.add(new ParameterDeclaration(new Modifiers(),
                new PrimitiveTypeAccess("int"), "p" + l.getNumChildNoTransform()));
          return s.substring(1);
        case 'J':
          l.add(new ParameterDeclaration(new Modifiers(),
                new PrimitiveTypeAccess("long"), "p" + l.getNumChildNoTransform()));
          return s.substring(1);
        case 'S':
          l.add(new ParameterDeclaration(new Modifiers(),
                new PrimitiveTypeAccess("short"), "p" + l.getNumChildNoTransform()));
          return s.substring(1);
        case 'Z':
          l.add(new ParameterDeclaration(new Modifiers(),
                new PrimitiveTypeAccess("boolean"), "p" + l.getNumChildNoTransform()));
          return s.substring(1);
        case 'L':
          int pos = s.indexOf(';');
          String s1 = s.substring(1, pos);
          String s2 = s.substring(pos + 1, s.length());
          l.add(new ParameterDeclaration(new Modifiers(),
                BytecodeParser.fromClassName(s1),
                "p" + l.getNumChildNoTransform()));
          return s2;
        case '[':
          int i = 1;
          while (s.charAt(i) == '[') {
            i++;
          }
          // Dummy name is replaced later.
          ArrayTypeAccess bottom = new ArrayTypeAccess(new ParseName());
          ArrayTypeAccess top = bottom;
          for (int j = 0; j < i - 1; j++) {
            top = new ArrayTypeAccess(top);
          }
          l.add(new ParameterDeclaration(new Modifiers(), top, "p" + l.getNumChild()));
          return arrayTypeList(s.substring(i), bottom);
        default:
          throw new Error("Error: unknown Type in TypeDescriptor: " + s);
      }

    }
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:418
   */
  

    public String arrayTypeList(String s, ArrayTypeAccess typeAccess) {
      char c = s.charAt(0);
      switch (c) {
        case 'B':
          typeAccess.setAccess(new PrimitiveTypeAccess("byte"));
          return s.substring(1);
        case 'C':
          typeAccess.setAccess(new PrimitiveTypeAccess("char"));
          return s.substring(1);
        case 'D':
          typeAccess.setAccess(new PrimitiveTypeAccess("double"));
          return s.substring(1);
        case 'F':
          typeAccess.setAccess(new PrimitiveTypeAccess("float"));
          return s.substring(1);
        case 'I':
          typeAccess.setAccess(new PrimitiveTypeAccess("int"));
          return s.substring(1);
        case 'J':
          typeAccess.setAccess(new PrimitiveTypeAccess("long"));
          return s.substring(1);
        case 'S':
          typeAccess.setAccess(new PrimitiveTypeAccess("short"));
          return s.substring(1);
        case 'Z':
          typeAccess.setAccess(new PrimitiveTypeAccess("boolean"));
          return s.substring(1);
        case 'L':
          int pos = s.indexOf(';');
          String s1 = s.substring(1, pos);
          String s2 = s.substring(pos + 1, s.length());
          typeAccess.setAccess(BytecodeParser.fromClassName(s1));
          return s2;
        default:
          throw new Error("Error: unknown Type in TypeDescriptor: " + s);
      }
    }

}
