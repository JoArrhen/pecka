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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:33
 */
 class FieldDescriptor extends java.lang.Object {
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:34
   */
  
    private AbstractClassfileParser p;
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:35
   */
  
    String typeDescriptor;
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:37
   */
  

    public FieldDescriptor(AbstractClassfileParser parser, String name) throws IOException {
      p = parser;
      int descriptor_index = p.u2();
      typeDescriptor = ((CONSTANT_Utf8_Info) p.constantPool[descriptor_index]).string();
      if (AbstractClassfileParser.VERBOSE) {
        p.println("  Field: " + name + ", " + typeDescriptor);
      }
    }
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:46
   */
  

    public Access type() {
      return new TypeDescriptor(p, typeDescriptor).type();
    }
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:50
   */
  

    public boolean isBoolean() {
      return new TypeDescriptor(p, typeDescriptor).isBoolean();
    }

}
