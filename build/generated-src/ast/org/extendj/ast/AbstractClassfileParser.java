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
 * @aspect ClassfileParser
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:34
 */
public abstract class AbstractClassfileParser extends java.lang.Object {
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:36
   */
  

    public static class ClassfileFormatError extends Error {
      public ClassfileFormatError(String message) {
        super(message);
      }
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:42
   */
  

    public static final boolean VERBOSE = false;
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:44
   */
  

    protected static final int CONSTANT_Class = 7;
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:45
   */
  
    protected static final int CONSTANT_FieldRef = 9;
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:46
   */
  
    protected static final int CONSTANT_MethodRef = 10;
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:47
   */
  
    protected static final int CONSTANT_InterfaceMethodRef = 11;
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:48
   */
  
    protected static final int CONSTANT_String = 8;
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:49
   */
  
    protected static final int CONSTANT_Integer = 3;
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:50
   */
  
    protected static final int CONSTANT_Float = 4;
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:51
   */
  
    protected static final int CONSTANT_Long = 5;
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:52
   */
  
    protected static final int CONSTANT_Double = 6;
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:53
   */
  
    protected static final int CONSTANT_NameAndType = 12;
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:54
   */
  
    protected static final int CONSTANT_Utf8 = 1;
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:56
   */
  

    private DataInputStream is;
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:58
   */
  

    public final String name;
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:59
   */
  
    public CONSTANT_Class_Info classInfo;
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:60
   */
  
    public CONSTANT_Info[] constantPool = null;
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:63
   */
  

    // For Java 5 and later.
    public boolean isInnerClass = false;
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:65
   */
  

    public AbstractClassfileParser(InputStream in, String name) {
      this.is = new DataInputStream(new BufferedInputStream(in));
      this.name = name;
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:70
   */
  

    public abstract boolean outerClassNameEquals(String name);
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:72
   */
  

    public final int next() throws IOException {
      return is.read();
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:76
   */
  

    public final int u1() throws IOException {
      return is.readUnsignedByte();
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:80
   */
  

    public final int u2() throws IOException {
      return is.readUnsignedShort();
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:84
   */
  

    public final int u4() throws IOException {
      return is.readInt();
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:88
   */
  

    public final int readInt() throws IOException {
      return is.readInt();
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:92
   */
  

    public final float readFloat() throws IOException {
      return is.readFloat();
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:96
   */
  

    public final long readLong() throws IOException {
      return is.readLong();
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:100
   */
  

    public final double readDouble() throws IOException {
      return is.readDouble();
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:104
   */
  

    public final String readUTF() throws IOException {
      return is.readUTF();
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:108
   */
  

    public final void skip(int length) throws IOException {
      while (length > 0) {
        length -= is.skip(length);
      }
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:114
   */
  

    public void error(String msg) throws ClassfileFormatError {
      throw new ClassfileFormatError(msg);
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:118
   */
  

    static Set<String> warned = Collections.newSetFromMap(
        new java.util.concurrent.ConcurrentHashMap<String,Boolean>());
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:121
   */
  

    public void warning(String msg, String version) {
      if (!warned.contains(version)) {
        System.err.println(msg);
        warned.add(version);
      }
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:128
   */
  

    public final void print(String s) {
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:131
   */
  

    public final void println(String s) {
      print(s + "\n");
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:135
   */
  

    public final void println() {
      print("\n");
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:139
   */
  

    public final void format(String fmt, Object... args) {
      print(String.format(fmt, args));
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:143
   */
  

    public final void parseMagic() throws IOException {
      if (next() != 0xca || next() != 0xfe || next() != 0xba || next() != 0xbe) {
        error("magic error");
      }
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:149
   */
  

    public final int parseMinor() throws IOException {
      return u2();
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:153
   */
  

    public final int parseMajor() throws IOException {
      return u2();
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:157
   */
  

    public final String parseThisClass() throws IOException {
      int index = u2();
      CONSTANT_Class_Info info = (CONSTANT_Class_Info) constantPool[index];
      classInfo = info;
      return info.simpleName();
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:164
   */
  

    public final Access parseSuperClass() throws IOException {
      int index = u2();
      if (index == 0) {
        return null;
      }
      CONSTANT_Class_Info info = (CONSTANT_Class_Info) constantPool[index];
      return info.access();
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:173
   */
  

    public final List parseInterfaces(List list) throws IOException {
      int count = u2();
      for (int i = 0; i < count; i++) {
        CONSTANT_Class_Info info = (CONSTANT_Class_Info) constantPool[u2()];
        list.add(info.access());
      }
      return list;
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:182
   */
  

    public void parseFields(TypeDecl typeDecl) throws IOException {
      int count = u2();
      if (VERBOSE) {
        println("Fields (" + count + "):");
      }
      for (int i = 0; i < count; i++) {
        if (VERBOSE) {
          print(" Field nbr " + i + " ");
        }
        FieldInfo fieldInfo = new FieldInfo(this);
        if (!fieldInfo.isSynthetic()) {
          typeDecl.addBodyDecl(fieldInfo.bodyDecl());
        }
      }
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:198
   */
  

    public static Modifiers modifiers(int flags) {
      Modifiers m = new Modifiers();
      if ((flags & 0x0001) != 0) {
        m.addModifier(new Modifier("public"));
      }
      if ((flags & 0x0002) != 0) {
        m.addModifier(new Modifier("private"));
      }
      if ((flags & 0x0004) != 0) {
        m.addModifier(new Modifier("protected"));
      }
      if ((flags & 0x0008) != 0) {
        m.addModifier(new Modifier("static"));
      }
      if ((flags & 0x0010) != 0) {
        m.addModifier(new Modifier("final"));
      }
      if ((flags & 0x0020) != 0) {
        m.addModifier(new Modifier("synchronized"));
      }
      if ((flags & 0x0040) != 0) {
        m.addModifier(new Modifier("volatile"));
      }
      if ((flags & 0x0080) != 0) {
        m.addModifier(new Modifier("transient"));
      }
      if ((flags & 0x0100) != 0) {
        m.addModifier(new Modifier("native"));
      }
      if ((flags & 0x0400) != 0) {
        m.addModifier(new Modifier("abstract"));
      }
      if ((flags & 0x0800) != 0) {
        m.addModifier(new Modifier("strictfp"));
      }
      return m;
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:236
   */
  

    private void checkLengthAndNull(int index) {
      if (index >= constantPool.length) {
        throw new Error("Trying to access element " + index  + " in constant pool of length "
            + constantPool.length);
      }
      if (constantPool[index] == null) {
        throw new Error("Unexpected null element in constant pool at index " + index);
      }
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:246
   */
  

    public boolean validConstantPoolIndex(int index) {
      return index < constantPool.length && constantPool[index] != null;
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:250
   */
  

    public CONSTANT_Info getCONSTANT_Info(int index) {
      checkLengthAndNull(index);
      return constantPool[index];
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:255
   */
  

    public CONSTANT_Utf8_Info getCONSTANT_Utf8_Info(int index) {
      checkLengthAndNull(index);
      CONSTANT_Info info = constantPool[index];
      if (!(info instanceof CONSTANT_Utf8_Info)) {
        throw new Error("Expected CONSTANT_Utf8_info at " + index + " in constant pool but found "
            + info.getClass().getName());
      }
      return (CONSTANT_Utf8_Info) info;
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:265
   */
  

    public CONSTANT_Class_Info getCONSTANT_Class_Info(int index) {
      checkLengthAndNull(index);
      CONSTANT_Info info = constantPool[index];
      if (!(info instanceof CONSTANT_Class_Info)) {
        throw new Error("Expected CONSTANT_Class_info at " + index + " in constant pool but found "
            + info.getClass().getName());
      }
      return (CONSTANT_Class_Info) info;
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:275
   */
  

    public void parseConstantPool() throws IOException {
      int count = u2();
      if (VERBOSE) {
        println("constant_pool_count: " + count);
      }
      constantPool = new CONSTANT_Info[count + 1];
      for (int i = 1; i < count; i++) {
        parseConstantPoolEntry(i);
        if (constantPool[i] instanceof CONSTANT_Long_Info
            || constantPool[i] instanceof CONSTANT_Double_Info) {
          i++;
        }
      }
    }
  /**
   * @aspect ClassfileParser
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassfileParser.jrag:290
   */
  

    protected abstract void parseConstantPoolEntry(int i) throws IOException;

}
