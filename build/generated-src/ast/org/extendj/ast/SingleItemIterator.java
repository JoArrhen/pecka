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
 * An iterator that iterates over one single item.
 * 
 * <p>This is used to iterate singleton sets.
 * @ast class
 * @aspect DataStructures
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:253
 */
public class SingleItemIterator<T> extends java.lang.Object implements Iterator<T> {
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:254
   */
  
    private boolean done = false;
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:255
   */
  
    private final T item;
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:257
   */
  

    public SingleItemIterator(T item) {
      this.item = item;
    }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:261
   */
  

    @Override
    public boolean hasNext() {
      return !done;
    }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:266
   */
  

    @Override
    public T next() {
      done = true;
      return item;
    }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:272
   */
  

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

}
