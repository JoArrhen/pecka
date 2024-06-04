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
 * This is a helper class used to ensure that only one thread
 * parses a single demand loaded compilation unit.
 * 
 * <p>A thread calls the first() method to check if it should parse the
 * compilation unit. If first() returns {@code true} then the thread proceeds
 * to parse the compilation unit and stores the result by calling set(). If
 * first() instead returns {@code false} the thread will call get() which
 * waits until the result is available.
 * @ast class
 * @aspect ClassPath
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:447
 */
 class ParseSynchronizer extends java.lang.Object {
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:448
   */
  
    private AtomicBoolean first = new AtomicBoolean(true);
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:449
   */
  
    private CompilationUnit result = null;
  /**
   * @return {@code true} in only one thread calling this method.
   * Returns {@code false} in all other threads.
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:455
   */
  

    /**
     * @return {@code true} in only one thread calling this method.
     * Returns {@code false} in all other threads.
     */
    public boolean first() {
      return first.getAndSet(false);
    }
  /**
   * Share a parsed compilation unit with other threads.
   * The compilation unit must not be null!
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:463
   */
  

    /**
     * Share a parsed compilation unit with other threads.
     * The compilation unit must not be null!
     */
    public synchronized void set(CompilationUnit result) {
      this.result = result;
      notifyAll();
    }
  /**
   * Read the stored compilation unit. This blocks until the result has been
   * stored by another thread.
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:472
   */
  

    /**
     * Read the stored compilation unit. This blocks until the result has been
     * stored by another thread.
     */
    public synchronized CompilationUnit get() {
      try {
        while (result == null) {
          wait();
        }
      } catch (InterruptedException e) {
      }
      return result;
    }

}
