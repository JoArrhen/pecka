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
 * An option type, for representing a nullable object with a non-null
 * reference.
 * 
 * <p>The main benefit in using this type is that the Java type checker
 * disallows direct dereference, and you instead have to decide to use
 * either the get() or getOrElse() methods.
 * @ast class
 * @aspect OptionType
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/Option.jadd:41
 */
public abstract class Option<T> extends java.lang.Object {
  /**
   * @aspect OptionType
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/Option.jadd:42
   */
  
    protected static final Option NONE = new Option() {
      @Override public Object get() {
        throw new NullPointerException();
      }

      @Override public Object getOrElse(Object v) {
        return v;
      }

      @Override public boolean hasValue() {
        return false;
      }
    };
  /**
   * @aspect OptionType
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/Option.jadd:56
   */
  

    protected static class OptionSome<V> extends Option<V> {
      private final V value;

      public OptionSome(V value) {
        assert (value != null);
        this.value = value;
      }

      @Override public V get() {
        return value;
      }

      @Override public V getOrElse(V v) {
        return value;
      }

      @Override public boolean hasValue() {
        return true;
      }
    }
  /**
   * @aspect OptionType
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/Option.jadd:77
   */
  

    protected Option() { }
  /**
   * Get the contained value.
   * Throws an error if there is no object.
   * @aspect OptionType
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/Option.jadd:83
   */
  

    /**
     * Get the contained value.
     * Throws an error if there is no object.
     */
    public abstract T get();
  /**
   * Get the contained value.
   * If there is no value, the argument is returned.
   * @aspect OptionType
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/Option.jadd:89
   */
  

    /**
     * Get the contained value.
     * If there is no value, the argument is returned.
     */
    public abstract T getOrElse(T v);
  /** Test if there is a value in this option object. 
   * @aspect OptionType
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/Option.jadd:92
   */
  

    /** Test if there is a value in this option object. */
    public abstract boolean hasValue();
  /**
   * Convert a nullable object to an option.
   * @aspect OptionType
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/Option.jadd:97
   */
  

    /**
     * Convert a nullable object to an option.
     */
    public static <U> Option<U> maybe(U u) {
      if (u != null) {
        return new OptionSome(u);
      } else {
        return (Option<U>) NONE;
      }
    }
  /**
   * Convert a nullable object to an option.
   * @aspect OptionType
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/Option.jadd:108
   */
  

    /**
     * Convert a nullable object to an option.
     */
    public static <U> Option<U> some(U u) {
      if (u == null) {
        throw new Error("Can't create an option containing null using Option.some(). "
            + "Use Option.none() or Option.maybe() instead.");
      }
      return new OptionSome(u);
    }
  /**
   * Returns the empty option object.
   * @aspect OptionType
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/Option.jadd:119
   */
  

    /**
     * Returns the empty option object.
     */
    public static <U> Option<U> none() {
      return (Option<U>) NONE;
    }

}
