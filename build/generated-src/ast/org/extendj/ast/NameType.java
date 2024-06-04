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
 * The classified name type of a parsed name or expression.
 * 
 * <p>Method Name is not in this enum because it never occurs in the ASTs
 * produced by our parser: the parser builds the correct node since it has a
 * different structure.
 * @ast enum
 * @aspect SyntacticClassification
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:47
 */
// Circular.

  /**
   * The classified name type of a parsed name or expression.
   *
   * <p>Method Name is not in this enum because it never occurs in the ASTs
   * produced by our parser: the parser builds the correct node since it has a
   * different structure.
   */
  enum NameType {
    NOT_CLASSIFIED,
    PACKAGE_NAME,
    TYPE_NAME,
    PACKAGE_OR_TYPE_NAME,
    AMBIGUOUS_NAME,
    EXPRESSION_NAME
  }

