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
 * A Jar file path listed in the classpath. Can contain many .class files.
 * This PathPart lazily initializes its package set and entry set.
 * @ast class
 * @aspect PathPart
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:535
 */
public class JarFilePath extends PathPart {
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:536
   */
  
    private Collection<String> packageIndex = null;
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:537
   */
  
    private final ZipFile jar;
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:538
   */
  
    private final String jarPath;
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:540
   */
  

    public JarFilePath(String jarPath) throws IOException {
      super(false);
      this.jar = new ZipFile(jarPath);
      this.jarPath = jarPath;
    }
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:546
   */
  

    public JarFilePath(File jarFile) throws IOException {
      super(false);
      this.jar = new ZipFile(jarFile);
      this.jarPath = jarFile.getPath();
    }
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:552
   */
  

    @Override
    public String getPath() {
      return jarPath;
    }
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:557
   */
  

    private static void scanJar(ZipFile jar, Collection<String> packages,
        String fileSuffix) {
      // Add all zip entries to a set so that we can quickly check if the Jar
      // contains a given class.
      for (Enumeration entries = jar.entries(); entries.hasMoreElements(); ) {
        ZipEntry entry = (ZipEntry) entries.nextElement();
        String path = entry.getName();
        if (path.endsWith(fileSuffix)) {
          addPackages(packages, path);
        }
      }
    }
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:570
   */
  

    private static void addPackages(Collection<String> packages, String path) {
      String name = path.replace('/', '.');
      int index = path.length();
      do {
        index = path.lastIndexOf('/', index-1);
      } while (index >= 0 && packages.add(name.substring(0, index)));
    }
  /**
   * Caches the package index from the Jar file so that subsequent calls to
   * this method are quicker.
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:582
   */
  

    /**
     * Caches the package index from the Jar file so that subsequent calls to
     * this method are quicker.
     */
    @Override
    public boolean hasPackage(String name) {
      synchronized (this) {
        if (packageIndex == null) {
          packageIndex = new HashSet<String>();
          scanJar(jar, packageIndex, fileSuffix);
        }
      }
      return packageIndex.contains(name);
    }
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:593
   */
  

    @Override
    public ClassSource findSource(String name) {
      // ZipFiles always use '/' as separator
      String jarName = name.replace('.', '/') + fileSuffix;
      ZipEntry entry = jar.getEntry(jarName);
      if (entry != null) {
        return new JarClassSource(this, jar, entry, jarPath);
      } else {
        return ClassSource.NONE;
      }
    }
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:605
   */
  

    @Override
    public String toString() {
      return "jar:" + jarPath;
    }

}
