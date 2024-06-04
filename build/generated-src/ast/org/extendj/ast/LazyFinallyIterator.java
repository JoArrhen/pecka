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
 * @aspect BranchTarget
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:48
 */
 class LazyFinallyIterator extends java.lang.Object implements Iterator<FinallyHost> {
  /**
   * @aspect BranchTarget
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:49
   */
  
    private final Stmt branch;
  /**
   * @aspect BranchTarget
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:50
   */
  
    private FinallyHost enclosing;
  /**
   * @aspect BranchTarget
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:52
   */
  

    LazyFinallyIterator(Stmt root) {
      branch = root;
      enclosing = branch.enclosingFinally(branch);
    }
  /**
   * @aspect BranchTarget
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:57
   */
  

    @Override
    public boolean hasNext() {
      return enclosing != null;
    }
  /**
   * @aspect BranchTarget
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:62
   */
  

    @Override
    public FinallyHost next() {
      FinallyHost b = enclosing;
      enclosing = enclosing.enclosingFinally(branch);
      return b;
    }
  /**
   * @aspect BranchTarget
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:69
   */
  

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

}
