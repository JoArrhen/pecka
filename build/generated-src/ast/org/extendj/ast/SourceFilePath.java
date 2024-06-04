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
 * A file specified explicitly on the commandline as a source file.
 * Must point to a .java file.
 * @ast class
 * @aspect PathPart
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:615
 */
public class SourceFilePath extends PathPart {
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:616
   */
  
    private final String filePath;
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:618
   */
  

    public SourceFilePath(String path) {
      super(true);
      this.filePath = path;
    }
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:623
   */
  

    @Override
    public String getPath() {
      return filePath;
    }
  /**
   * <b>Use the parsed CompilationUnit to find the package name of the file!</b>
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:631
   */
  

    /**
     * <b>Use the parsed CompilationUnit to find the package name of the file!</b>
     */
    @Override
    public boolean hasPackage(String name) {
      return false;
    }
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:636
   */
  

    @Override
    public ClassSource findSource(String name) {
      if (filePath.equals(name)) {
        File file = new File(filePath);
        if (file.isFile() && file.canRead()) {
          return new FileClassSource(this, filePath);
        }
      }
      return ClassSource.NONE;
    }
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:647
   */
  

    @Override
    public String toString() {
      return filePath;
    }

}
