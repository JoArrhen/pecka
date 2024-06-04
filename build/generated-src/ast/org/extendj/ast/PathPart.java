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
 * Abstract representation of a collection of class sources.
 * @ast class
 * @aspect PathPart
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:329
 */
public abstract class PathPart extends java.lang.Object {
  /**
   * This is {@code true} if this path part represents a source path, {@code
   * true} if this path part represents a bytecode class path.
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:334
   */
  
    /**
     * This is {@code true} if this path part represents a source path, {@code
     * true} if this path part represents a bytecode class path.
     */
    protected final boolean isSource;
  /**
   * The file suffix of source files in this path part.
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:339
   */
  

    /**
     * The file suffix of source files in this path part.
     */
    protected final String fileSuffix;
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:341
   */
  

    protected PathPart(boolean isSource) {
      this.isSource = isSource;
      this.fileSuffix = isSource ? ".java" : ".class";
    }
  /**
   * @return the path which this path part represents
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:349
   */
  

    /**
     * @return the path which this path part represents
     */
    abstract public String getPath();
  /**
   * Test if a package is available in this path part.
   * 
   * <p>The implementations of this method may use caching to improve the
   * efficiency of subsequent calls to the method.
   * 
   * @return {@code true} if the given package name exists in this path part
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:359
   */
  

    /**
     * Test if a package is available in this path part.
     *
     * <p>The implementations of this method may use caching to improve the
     * efficiency of subsequent calls to the method.
     *
     * @return {@code true} if the given package name exists in this path part
     */
    abstract public boolean hasPackage(String name);
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:361
   */
  

    public static PathPart createSourcePath(String fileName) {
      return createPathPart(fileName, true);
    }
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:365
   */
  

    public static PathPart createClassPath(String fileName) {
      return createPathPart(fileName, false);
    }
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:369
   */
  

    private static PathPart createPathPart(String path, boolean fromSource) {
      try {
        File file = new File(path);
        if (file.isDirectory()) {
          if (fromSource) {
            return new SourceFolderPath(path);
          } else {
            return new ClassFolderPath(path);
          }
        } else if (file.isFile()) {
          return new JarFilePath(path);
        }
      } catch (IOException e) {
        System.err.println("Warning: can not open class path " + path);
      }
      return null;
    }
  /**
   * Retrieves a compilation unit based on the canonical name.
   * @param name the canonical name of the class to lookup
   * @return {@code null} if a compilation unit with the given name is not
   * available in this PathPart.
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:393
   */
  

    /**
     * Retrieves a compilation unit based on the canonical name.
     * @param name the canonical name of the class to lookup
     * @return {@code null} if a compilation unit with the given name is not
     * available in this PathPart.
     */
    public ClassSource findSource(String name) {
      return ClassSource.NONE;
    }
  /**
   * Load the compilation unit of a class.
   * @param program
   * @param name The canonical name of the class.
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:402
   */
  

    /**
     * Load the compilation unit of a class.
     * @param program
     * @param name The canonical name of the class.
     */
    public CompilationUnit getCompilationUnit(Program program, String name) throws IOException {
      ClassSource source = findSource(name);
      if (source == ClassSource.NONE) {
        throw new IOException(String.format("%s file not found: %s",
              isSource ? "Source" : "Class", name));
      }
      return source.parseCompilationUnit(program);
    }

}
