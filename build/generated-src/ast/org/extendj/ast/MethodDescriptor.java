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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:127
 */
 class MethodDescriptor extends java.lang.Object {
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:128
   */
  
    private AbstractClassfileParser p;
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:129
   */
  
    private String parameterDescriptors;
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:130
   */
  
    private String typeDescriptor;
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:132
   */
  

    public MethodDescriptor(AbstractClassfileParser parser, String name) throws IOException {
      p = parser;
      int descriptor_index = p.u2();
      String descriptor = ((CONSTANT_Utf8_Info) p.constantPool[descriptor_index]).string();
      if (AbstractClassfileParser.VERBOSE) {
        p.println("  Method: " + name + ", " + descriptor);
      }
      int pos = descriptor.indexOf(')');
      parameterDescriptors = descriptor.substring(1, pos);
      typeDescriptor = descriptor.substring(pos + 1, descriptor.length());
    }
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:144
   */
  

    public List parameterList() {
      TypeDescriptor d = new TypeDescriptor(p, parameterDescriptors);
      return d.parameterList();
    }
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:149
   */
  

    public List parameterListSkipFirst() {
      TypeDescriptor d = new TypeDescriptor(p, parameterDescriptors);
      return d.parameterListSkipFirst();
    }
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:154
   */
  

    public Access type() {
      TypeDescriptor d = new TypeDescriptor(p, typeDescriptor);
      return d.type();
    }

}
