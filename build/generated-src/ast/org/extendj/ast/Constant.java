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
 * @aspect Constant
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:33
 */
public class Constant extends java.lang.Object {
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:34
   */
  
    static class ConstantInt extends Constant {
      private int value;
      public ConstantInt(int i) { this.value = i; }
      @Override public int intValue() { return value; }
      @Override public long longValue() { return value; }
      @Override public float floatValue() { return value; }
      @Override public double doubleValue() { return value; }
      @Override public String stringValue() { return new Integer(value).toString(); }
    }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:43
   */
  
    static class ConstantLong extends Constant {
      private long value;
      public ConstantLong(long l) { this.value = l; }
      @Override public int intValue() { return (int)value; }
      @Override public long longValue() { return value; }
      @Override public float floatValue() { return value; }
      @Override public double doubleValue() { return value; }
      @Override public String stringValue() { return new Long(value).toString(); }
    }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:52
   */
  
    static class ConstantFloat extends Constant {
      private float value;
      public ConstantFloat(float f) { this.value = f; }
      @Override public int intValue() { return (int)value; }
      @Override public long longValue() { return (long)value; }
      @Override public float floatValue() { return value; }
      @Override public double doubleValue() { return value; }
      @Override public String stringValue() { return new Float(value).toString(); }
    }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:61
   */
  
    static class ConstantDouble extends Constant {
      private double value;
      public ConstantDouble(double d) { this.value = d; }
      @Override public int intValue() { return (int)value; }
      @Override public long longValue() { return (long)value; }
      @Override public float floatValue() { return (float)value; }
      @Override public double doubleValue() { return value; }
      @Override public String stringValue() { return new Double(value).toString(); }
    }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:70
   */
  
    static class ConstantChar extends Constant {
      private char value;
      public ConstantChar(char c) { this.value = c; }
      @Override public int intValue() { return value; }
      @Override public long longValue() { return value; }
      @Override public float floatValue() { return value; }
      @Override public double doubleValue() { return value; }
      @Override public String stringValue() { return new Character(value).toString(); }
    }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:79
   */
  
    static class ConstantBoolean extends Constant {
      private boolean value;
      public ConstantBoolean(boolean b) { this.value = b; }
      @Override public boolean booleanValue() { return value; }
      @Override public String stringValue() { return new Boolean(value).toString(); }
    }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:85
   */
  
    static class ConstantString extends Constant {
      private String value;
      public ConstantString(String s) { this.value = s; }
      @Override public String stringValue() { return value; }
    }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:91
   */
  

    public int intValue() { throw new UnsupportedOperationException(); }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:92
   */
  
    public long longValue() { throw new UnsupportedOperationException(); }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:93
   */
  
    public float floatValue() { throw new UnsupportedOperationException(); }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:94
   */
  
    public double doubleValue() { throw new UnsupportedOperationException(); }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:95
   */
  
    public boolean booleanValue() { throw new UnsupportedOperationException(getClass().getName()); }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:96
   */
  
    public String stringValue() { throw new UnsupportedOperationException(); }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:98
   */
  

    protected Constant() { }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:100
   */
  

    public boolean error = false;
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:102
   */
  

    static Constant create(int i) { return new ConstantInt(i); }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:103
   */
  
    static Constant create(long l) { return new ConstantLong(l); }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:104
   */
  
    static Constant create(float f) { return new ConstantFloat(f); }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:105
   */
  
    static Constant create(double d) { return new ConstantDouble(d); }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:106
   */
  
    static Constant create(boolean b) { return new ConstantBoolean(b); }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:107
   */
  
    static Constant create(char c) { return new ConstantChar(c); }
  /**
   * @aspect Constant
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Constant.jadd:108
   */
  
    static Constant create(String s) { return new ConstantString(s); }

}
