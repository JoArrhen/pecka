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
 * @aspect ClassPath
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:180
 */
public class ClassPath extends java.lang.Object {
  /**
   * Tracks all currently available packages in the program classpath.
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:185
   */
  

    /**
     * Tracks all currently available packages in the program classpath.
     */
    private Set<String> packages = new HashSet<String>();
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:187
   */
  

    private boolean pathsInitialized = false;
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:189
   */
  

    private ArrayList<PathPart> classPath = new ArrayList<PathPart>();
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:191
   */
  

    private ArrayList<PathPart> sourcePath = new ArrayList<PathPart>();
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:193
   */
  

    private final Program program;
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:195
   */
  

    public ClassPath(Program program) {
      this.program = program;
    }
  /**
   * Used to make the classpath empty, in case you want more control
   * over the classpath initialization. Usually you would use
   * addClassPath to manually setup the classpath after this.
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:204
   */
  

    /**
     * Used to make the classpath empty, in case you want more control
     * over the classpath initialization. Usually you would use
     * addClassPath to manually setup the classpath after this.
     */
    public synchronized void initEmptyPaths() {
      pathsInitialized = true;
    }
  /**
   * Set up the classpaths (standard + boot classpath).
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:211
   */
  

    /**
     * Set up the classpaths (standard + boot classpath).
     */
    private synchronized void initPaths() {
      if (pathsInitialized) {
        return;
      }
      pathsInitialized = true;

      ArrayList<String> classPaths = new ArrayList<String>();
      ArrayList<String> sourcePaths = new ArrayList<String>();

      String bootclasspath;
      if (program.options().hasValueForOption("-bootclasspath")) {
        bootclasspath = program.options().getValueForOption("-bootclasspath");
      } else {
        bootclasspath = System.getProperty("sun.boot.class.path", "");
      }
      for (String path : bootclasspath.split(File.pathSeparator)) {
        if (!path.isEmpty()) {
          classPaths.add(path);
        }
      }
      boolean hasBootClassPath = !classPaths.isEmpty();

      String extdirs;
      if (program.options().hasValueForOption("-extdirs")) {
        extdirs = program.options().getValueForOption("-extdirs");
      } else {
        extdirs = System.getProperty("java.ext.dirs", "");
      }
      for (String path : extdirs.split(File.pathSeparator)) {
        File dir = new File(path);
        if (dir.isDirectory()) {
          for (File jar : dir.listFiles()) {
            if (jar.getName().toLowerCase().endsWith(".jar")) {
              classPaths.add(jar.getPath());
            }
          }
        }
      }

      String[] userClasses;
      if (program.options().hasValueForOption("-classpath")) {
        userClasses = program.options().getValueForOption("-classpath").split(File.pathSeparator);
      } else if (program.options().hasValueForOption("-cp")) {
        userClasses = program.options().getValueForOption("-cp").split(File.pathSeparator);
      } else {
        userClasses = new String[] { "." };
      }
      if (!program.options().hasValueForOption("-sourcepath")) {
        for (String path : userClasses) {
          classPaths.add(path);
          sourcePaths.add(path);
        }
      } else {
        for (String path : userClasses) {
          classPaths.add(path);
        }
        userClasses = program.options().getValueForOption("-sourcepath").split(File.pathSeparator);
        for (String path : userClasses) {
          sourcePaths.add(path);
        }
      }

      if (!hasBootClassPath) {
        // We have no boot classpath: add default system resource loader.
        addClassPath(new SystemResourceClassLoader());
      }
      for (String path : classPaths) {
        PathPart part = PathPart.createClassPath(path);
        if (part != null) {
          addClassPath(part);
        } else if (program.options().verbose()) {
          System.out.println("Warning: Could not use " + path + " as class path");
        }
      }
      for (String path : sourcePaths) {
        PathPart part = PathPart.createSourcePath(path);
        if (part != null) {
          addSourcePath(part);
        } else if(program.options().verbose()) {
          System.out.println("Warning: Could not use " + path + " as source path");
        }
      }
    }
  /**
   * Get the input stream for a compilation unit specified using a canonical
   * name. This is used by the bytecode reader to load nested types.
   * @param name The canonical name of the compilation unit.
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:300
   */
  

    /**
     * Get the input stream for a compilation unit specified using a canonical
     * name. This is used by the bytecode reader to load nested types.
     * @param name The canonical name of the compilation unit.
     */
    public synchronized InputStream getInputStream(String name) {
      try {
        for (PathPart part : classPath) {
          ClassSource source = part.findSource(name);
          if (source != ClassSource.NONE) {
            return source.openInputStream();
          }
        }
      } catch(IOException e) {
      }
      throw new Error("Could not find nested type " + name);
    }
  /**
   * Load a compilation unit from disk based on a classname. A class file is parsed if one exists
   * matching the classname that is not older than a corresponding source file, otherwise the
   * source file is selected.
   * 
   * <p>This method is called by the LibCompilationUnit NTA.  We rely on the result of this method
   * being cached because it will return a newly parsed compilation unit each time it is called.
   * 
   * @return the loaded compilation unit, or the provided default compilation unit if no matching
   * compilation unit was found.
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:324
   */
  

    /**
     * Load a compilation unit from disk based on a classname. A class file is parsed if one exists
     * matching the classname that is not older than a corresponding source file, otherwise the
     * source file is selected.
     *
     * <p>This method is called by the LibCompilationUnit NTA.  We rely on the result of this method
     * being cached because it will return a newly parsed compilation unit each time it is called.
     *
     * @return the loaded compilation unit, or the provided default compilation unit if no matching
     * compilation unit was found.
     */
    public CompilationUnit getCompilationUnit(String typeName,
        CompilationUnit defaultCompilationUnit) {
      try {
        initPaths();
        ClassSource sourcePart = ClassSource.NONE;
        ClassSource classPart = ClassSource.NONE;
        for (PathPart part : sourcePath) {
          sourcePart = part.findSource(typeName);
          if (sourcePart != ClassSource.NONE) {
            break;
          }
        }
        for (PathPart part : classPath) {
          classPart = part.findSource(typeName);
          if (classPart != ClassSource.NONE) {
            break;
          }
        }

        if (sourcePart != ClassSource.NONE && (classPart == ClassSource.NONE
              || classPart.lastModified() < sourcePart.lastModified())) {
          CompilationUnit unit = sourcePart.parseCompilationUnit(program);
          int index = typeName.lastIndexOf('.');
          if (index == -1) {
            return unit;
          }
          String pkgName = typeName.substring(0, index);
          if (pkgName.equals(unit.getPackageDecl())) {
            return unit;
          }
        }
        if (classPart != ClassSource.NONE) {
          CompilationUnit unit = classPart.parseCompilationUnit(program);
          int index = typeName.lastIndexOf('.');
          if (index == -1) {
            return unit;
          }
          String pkgName = typeName.substring(0, index);
          if (pkgName.equals(unit.getPackageDecl())) {
            return unit;
          }
        }
        return defaultCompilationUnit;
      } catch (IOException e) {
        // Attributes can't throw checked exceptions, so convert this to an Error.
        throw new Error(e);
      }
    }
  /**
   * Add a package name to available package set.
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:376
   */
  

    /**
     * Add a package name to available package set.
     */
    public synchronized void addPackage(String packageName) {
      int end = packageName.length();
      while (end > 0 && packages.add(packageName.substring(0, end))) {
        end = packageName.lastIndexOf('.', end - 1);
      }
    }
  /**
   * Add a path part to the library class path.
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:386
   */
  

    /**
     * Add a path part to the library class path.
     */
    public synchronized void addClassPath(PathPart pathPart) {
      classPath.add(pathPart);
    }
  /**
   * Add a path part to the user class path.
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:393
   */
  

    /**
     * Add a path part to the user class path.
     */
    public synchronized void addSourcePath(PathPart pathPart) {
      sourcePath.add(pathPart);
    }
  /**
   * Quick pass, slow fail. Cache existing package names in a concurrent set.
   * @return <code>true</code> if there is a package with the given name on
   * the classpath
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:402
   */
  

    /**
     * Quick pass, slow fail. Cache existing package names in a concurrent set.
     * @return <code>true</code> if there is a package with the given name on
     * the classpath
     */
    public synchronized boolean isPackage(String packageName) {
      initPaths();
      if (packages.contains(packageName)) {
        return true;
      }
      for (PathPart part : classPath) {
        if (part.hasPackage(packageName)) {
          addPackage(packageName);
          return true;
        }
      }
      for (PathPart part : sourcePath) {
        if (part.hasPackage(packageName)) {
          addPackage(packageName);
          return true;
        }
      }
      return false;
    }
  /**
   * @return a copy of the source path parts
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:425
   */
  

    /**
     * @return a copy of the source path parts
     */
    public synchronized Collection<PathPart> getSourcePath() {
      return new ArrayList<PathPart>(sourcePath);
    }
  /**
   * @return a copy of the class path parts
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:432
   */
  

    /**
     * @return a copy of the class path parts
     */
    public synchronized Collection<PathPart> getClassPath() {
      return new ArrayList<PathPart>(classPath);
    }

}
