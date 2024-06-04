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
 * @aspect ErrorCheck
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:102
 */
public class Problem extends java.lang.Object implements Comparable {
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:103
   */
  
    public static class Severity {
      public static final Severity ERROR = new Severity("error");
      public static final Severity WARNING = new Severity("warning");

      private final String name;

      private Severity(String name) {
        this.name = name;
      }

      @Override
      public String toString() {
        return name;
      }
    }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:119
   */
  

    public static class Kind {
      public static final Kind OTHER = new Kind();
      public static final Kind LEXICAL = new Kind();
      public static final Kind SYNTACTIC = new Kind();
      public static final Kind SEMANTIC = new Kind();
      private Kind() { }
    }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:127
   */
  

    protected int line = -1;
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:129
   */
  

    protected int column = -1;
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:131
   */
  

    protected int endLine = -1;
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:133
   */
  

    protected int endColumn = -1;
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:135
   */
  

    protected String fileName;
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:137
   */
  

    protected final String message;
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:139
   */
  

    protected Severity severity = Severity.ERROR;
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:141
   */
  

    protected Kind kind = Kind.OTHER;
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:143
   */
  

    public Problem(String fileName, String message, int line, Severity severity, Kind kind) {
      this.fileName = fileName;
      this.message = message;
      this.line = line;
      this.kind = kind;
      this.severity = severity;
    }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:151
   */
  

    public Problem(String fileName, String message, int line, int column,
        Severity severity, Kind kind) {
      this.fileName = fileName;
      this.message = message;
      this.line = line;
      this.column = column;
      this.kind = kind;
      this.severity = severity;
    }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:161
   */
  

    public Problem(String fileName, String message, int line, int column, int endLine,
        int endColumn, Severity severity, Kind kind) {
      this.fileName = fileName;
      this.message = message;
      this.line = line;
      this.column = column;
      this.endLine = endLine;
      this.endColumn = endColumn;
      this.kind = kind;
      this.severity = severity;
    }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:173
   */
  

    public int line() {
      return line;
    }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:177
   */
  

    public int column() {
      return column;
    }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:181
   */
  

    public int endLine() {
      return endLine;
    }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:185
   */
  

    public int endColumn() {
      return endColumn;
    }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:189
   */
  

    public String fileName() {
      return fileName;
    }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:193
   */
  

    public void setFileName(String fileName) {
      this.fileName = fileName;
    }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:197
   */
  

    public String message() {
      return message;
    }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:201
   */
  

    public Severity severity() {
      return severity;
    }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:205
   */
  

    public Kind kind() {
      return kind;
    }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:209
   */
  

    @Override
    public String toString() {
      String location = "";
      if (line != -1 && column != -1) {
        location = line + "," + column + ":";
      } else if (line != -1) {
        location = line + ":";
      }
      return String.format("%s:%s %s: %s", fileName, location, severity, message);
    }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:220
   */
  

    @Override
    public int compareTo(Object o) {
      if (o instanceof Problem) {
        Problem other = (Problem) o;
        if (!fileName.equals(other.fileName)) {
          return fileName.compareTo(other.fileName);
        } else if (line != other.line) {
          // Using Integer.compare(int, int) breaks Java 6 builds.
          return Integer.valueOf(line).compareTo(other.line);
        } else {
          return message.compareTo(other.message);
        }
      }
      return 0;
    }

}
