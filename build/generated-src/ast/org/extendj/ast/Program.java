/* This file was generated with JastAdd2 (http://jastadd.org) version 2.3.4-50-gf00c118 */
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
 * The root of a Java AST.
 * 
 * <p>A Java program consists of multiple compilation units that represent the
 * source files of the program.
 * @ast node
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:37
 * @astdecl Program : ASTNode ::= CompilationUnit*;
 * @production Program : {@link ASTNode} ::= <span class="component">{@link CompilationUnit}*</span>;

 */
public class Program extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @aspect CG2JSON
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cg2json.jrag:32
   */
  public
  void callGraph2JSON(PrintStream out, boolean forward) {
    InvocationTarget entryPoint =
        getTarget(entryPointPackage, entryPointMethod);
    out.println("{");
    out.println("  \"nodes\": [");
    ArrayList<InvocationTarget> all = new ArrayList<InvocationTarget>();
    if (forward)
      completeCallGraph(entryPoint, new HashSet<InvocationTarget>(), all);
    else
      completeCallGraphBackward(entryPoint, new HashSet<InvocationTarget>(),
                                all);
    for (int i = 0; i < all.size(); i++) {
      out.println("    {");
      InvocationTarget target = all.get(i);
      target.nodes2JSON(out);
      out.println("    }" + (i < all.size() - 1 ? "," : ""));
    }

    out.println("  ],");
    out.println("  \"edges\": [");
    boolean first = true;
    for (InvocationTarget target : all) {
      target.edges2JSON(out, first, forward);
      first = false;
    }
    out.println("  ]");
    out.println("}");
  }
  /**
   * @aspect CG2JSON
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cg2json.jrag:62
   */
  public
  void completeCallGraph(InvocationTarget target,
                                 Set<InvocationTarget> visited,
                                 ArrayList<InvocationTarget> cg) {
    if (visited.contains(target))
      return;
    visited.add(target);
    if (target.shouldBeConsiderAsMethod()) {
      cg.add(target);
    }
    for (InvocationTarget neighbor : target.cg()) {
      completeCallGraph(neighbor, visited, cg);
    }
    return;
  }
  /**
   * @aspect CG2JSON
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cg2json.jrag:78
   */
  public
  void completeCallGraphBackward(InvocationTarget target,
                                         Set<InvocationTarget> visited,
                                         ArrayList<InvocationTarget> cg) {
    if (visited.contains(target))
      return;
    visited.add(target);
    if (target.shouldBeConsiderAsMethod()) {
      cg.add(target);
    }
    for (InvocationTarget neighbor : target.reversedCG()) {
      completeCallGraphBackward(neighbor, visited, cg);
    }
    return;
  }
  /**
   * @aspect AttributeTracer
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/Tracer.jrag:34
   */
  public
  void cpr_setTraceReceiver(
      final java.util.function.Consumer<Object[]> recv) {
    trace().setReceiver(new ASTState.Trace.Receiver() {
      @Override public void accept(ASTState.Trace.Event event, ASTNode node,
                                   String attribute, Object params,
                                   Object value) {
        recv.accept(new Object[]{event, node, attribute, params, value});
      }
    });
  }
  /**
   * @aspect AttributeTracer
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/Tracer.jrag:46
   */
  public
  AttributeTracer trace = new AttributeTracer();
  /**
   * @aspect AllMethods
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:44
   */
  public
  String allMethodsToJson() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  \"methods\": [\n");
    int methodCount = 0;
    for (InvocationTarget m : allMethods()) {
      sb.append("    {\n");
      sb.append("      \"name\": \"" + m.methodName() + "\",\n");
      sb.append("      \"file\": \"" + m.enclosingCompilationUnit().pathName() +
                "\",\n");
      sb.append("      \"lineStart\": " + m.lineStart() + ",\n");
      sb.append("      \"packageName\": \"" + m.packageName() + "\"\n");
      sb.append("    }");
      if (++methodCount < allMethods().size()) {
        sb.append(",\n");
      }
    }
    sb.append("\n  ]\n");
    sb.append("}\n");
    return sb.toString();
  }
  /**
   * @aspect CHA
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:144
   */
  public
  boolean rta = false;
  /**
   * @aspect CallGraphWithRAGS
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:87
   */
  public
  int currentSccId = 0;
  /**
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:231
   */
  public
  boolean attributesOnly = false;
  /**
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:234
   */
  public
  String entryPointPackage = "";
  /**
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:236
   */
  public
  String entryPointMethod = "";
  /**
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:239
   */
  public
  boolean mergeNames = false;
  /**
   * @aspect AddOptionsToProgram
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Options.jadd:35
   */
  public Options options = new Options();
  /**
   * @aspect AddOptionsToProgram
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Options.jadd:37
   */
  public Options options() {
    return options;
  }
  /**
   * Returns a robust iterator that can be iterated while the colleciton is updated.
   * @aspect LibraryCompilationUnits
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LibCompilationUnits.jadd:35
   */
  public Iterator<CompilationUnit> libraryCompilationUnitIterator() {
    return libraryCompilationUnitSet.iterator();
  }
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:61
   */
  protected BytecodeReader bytecodeReader = defaultBytecodeReader();
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:63
   */
  public void initBytecodeReader(BytecodeReader r) {
    bytecodeReader = r;
  }
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:67
   */
  public static BytecodeReader defaultBytecodeReader() {
    return new BytecodeReader() {
      @Override
      public CompilationUnit read(InputStream is, String fullName, Program p)
          throws FileNotFoundException, IOException {
        return new BytecodeParser(is, fullName).parse(null, null, p);
      }
    };
  }
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:77
   */
  protected JavaParser javaParser = defaultJavaParser();
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:79
   */
  public void initJavaParser(JavaParser p) {
    javaParser = p;
  }
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:83
   */
  public static JavaParser defaultJavaParser() {
    return new JavaParser() {
      @Override
      public CompilationUnit parse(InputStream is, String fileName)
          throws IOException, beaver.Parser.Exception {
        return new org.extendj.parser.JavaParser().parse(is, fileName);
      }
    };
  }
  /**
   * Parse the source file and add the compilation unit to the list of
   * compilation units in the program.
   * 
   * <p>This method modifies the AST. It may not be called after any attribute
   * evaluation starts.
   * 
   * @param fileName file name of the source file
   * @return The CompilationUnit representing the source file,
   * or <code>null</code> if the source file could not be parsed
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:127
   */
  public CompilationUnit addSourceFile(String fileName) throws IOException {
    SourceFilePath pathPart = new SourceFilePath(fileName);
    CompilationUnit cu = pathPart.getCompilationUnit(this, fileName);
    if (cu != emptyCompilationUnit()) {
      classPath.addPackage(cu.packageName());
      // In parallel execution, multiple source files could be added concurrently.
      // However, the Program class is not thread-safe for modification, so we use
      // a synchronized block to ensure sequential updating of the Program node.
      synchronized (this) {
        addCompilationUnit(cu);
      }
    }
    return cu;
  }
  /**
   * Creates an iterator to iterate over compilation units parsed from source files.
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:145
   */
  public Iterator<CompilationUnit> compilationUnitIterator() {
    return new Iterator<CompilationUnit>() {
      int index = 0;

      @Override
      public boolean hasNext() {
        return index < getNumCompilationUnit();
      }

      @Override
      public CompilationUnit next() {
        if (getNumCompilationUnit() == index) {
          throw new java.util.NoSuchElementException();
        }
        return getCompilationUnit(index++);
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
  /**
   * Get the input stream for a compilation unit specified using a canonical
   * name. This is used by the bytecode reader to load nested types.
   * @param name The canonical name of the compilation unit.
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:174
   */
  public InputStream getInputStream(String name) {
    return classPath.getInputStream(name);
  }
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:178
   */
  private final ClassPath classPath = new ClassPath(this);
  /**
   * Cache for parsed compilation units.
   * 
   * <p>Should only be accessed via Program.getCompilationUnit(String).
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:488
   */
  private ConcurrentMap<String, ParseSynchronizer> parseLocks =
      new ConcurrentSkipListMap<String, ParseSynchronizer>();
  /**
   * @return <code>true</code> if there is a package with the given name on
   * the classpath
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:529
   */
  public boolean isPackage(String packageName) {
    return classPath.isPackage(packageName);
  }
  /**
   * Add a path part to the library class path.
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:552
   */
  public void addClassPath(PathPart pathPart) {
    classPath.addClassPath(pathPart);
  }
  /**
   * Add a path part to the user class path.
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:559
   */
  public void addSourcePath(PathPart pathPart) {
    classPath.addSourcePath(pathPart);
  }
  /**
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:133
   */
  public int classFileReadTime;
  /**
   * Cache for source type lookups.
   * 
   * <p>Should only be accessed via Program.lookupSourceType(String,String)!
   * 
   * <p>This cache is important in order to make all source types shadow
   * library types with matching names, even when the source type lives in a
   * compilation unit with a different name.
   * 
   * <p>When loading a compilation unit, all additional types in the compilation unit
   * must become visible after the type lookup for the type with the same name as the
   * compilation unit. This map ensures that additional types become visible.
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:170
   */
  private final Map<String, TypeDecl> sourceTypeMap = new HashMap<String, TypeDecl>();
  /**
   * Flag indictating if the source type map has already been initialized.
   * 
   * <p>Should only be accessed via Program.lookupSourceType(String,String)!
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:177
   */
  private boolean sourceTypeMapInitialized = false;
  /**
   * Lookup a type among source classes.
   * 
   * <p>Invoking this method may cause more than just the specified type to be
   * loaded, for example if there exists other types in the same source file,
   * the additional types are also loaded and memoized for the next lookup.
   * 
   * <p>This is a method rather than an attribute because it uses side-effects
   * to memoize additional types. The side effects are only observable via the
   * fields Program.sourceTypeMapInitialized and Program.sourceTypeMap.
   * These fields should only be used by this method to ensure that it is
   * observationally pure.
   * 
   * <p>This method is synchronized to ensure that concurrent type lookups
   * run sequentially.
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:195
   */
  protected synchronized TypeDecl lookupSourceType(String packageName, String typeName) {
    String fullName = packageName.equals("") ? typeName : packageName + "." + typeName;

    if (!sourceTypeMapInitialized) {
      initializeSourceTypeMap();
      sourceTypeMapInitialized = true;
    }

    if (sourceTypeMap.containsKey(fullName)) {
      return sourceTypeMap.get(fullName);
    } else {
      sourceTypeMap.put(fullName, unknownType());
    }

    // Source type not found: lookup library type instead.
    return unknownType();
  }
  /**
   * Initialize source types in the source type map.  This puts all the types provided by
   * Program.addSourceFile() in a map for lookup by Program.lookupSourceType.
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:217
   */
  private void initializeSourceTypeMap() {
    // Initialize source type map with the compilation units supplied by Program.addSourceFile.
    for (int i = 0; i < getNumCompilationUnit(); i++) {
      CompilationUnit unit = getCompilationUnit(i);
      for (int j = 0; j < unit.getNumTypeDecl(); j++) {
        TypeDecl type = unit.getTypeDecl(j);
        sourceTypeMap.put(type.fullName(), type);
      }
    }
  }
  /**
   * Extra cache for library type lookups.
   * 
   * <p>Should only be accessed via Program.lookupLibraryType(String,String)!
   * 
   * <p>This cache is needed to be able to track library types that are
   * declared in compilation units with a different name than the type itself.
   * Note that this only affects library types loaded from source (unfortunately easy
   * to confuse with sourceTypeMap).
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:238
   */
  private final Map<String, TypeDecl> libraryTypeMap = new HashMap<String, TypeDecl>();
  /**
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:240
   */
  private final Set<CompilationUnit> libraryCompilationUnitSet =
      new RobustSet<CompilationUnit>(new HashSet<CompilationUnit>());
  /**
   * Flag indictating if the library type map has already been initialized.
   * 
   * <p>Should only be accessed via Program.lookupLibraryType(String,String)!
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:248
   */
  private boolean libraryTypeMapInitialized = false;
  /**
   * Lookup a type among library classes. The lookup includes Jar and source files.
   * 
   * <p>Invoking this method may cause more than just the specified type to be loaded, for
   * example if there exists other types in the same source file, the additional
   * types are also loaded and memoized for the next lookup.
   * 
   * <p>This is a method rather than an attribute because it uses side-effects
   * to memoize additional types. The side effects are only observable via the
   * fields Program.libraryTypeMapInitialized and Program.libraryTypeMap.
   * These fields should only be used by this method to ensure that it is
   * observationally pure.
   * 
   * <p>This method is synchronized to ensure that concurrent type lookups
   * run sequentially.
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:266
   */
  protected synchronized TypeDecl lookupLibraryType(String packageName, String typeName) {
    String fullName = packageName.isEmpty() ? typeName : packageName + "." + typeName;

    if (!libraryTypeMapInitialized) {
      initializeLibraryTypeMap();
      libraryTypeMapInitialized = true;
    }

    if (libraryTypeMap.containsKey(fullName)) {
      return libraryTypeMap.get(fullName);
    }

    // Lookup the type in the library class path.
    CompilationUnit libraryUnit = getLibCompilationUnit(fullName);

    // Store the compilation unit in a set for later introspection of loaded compilation units.
    libraryCompilationUnitSet.add(libraryUnit);

    // Add all types from the compilation unit in the library type map so that we can find them on
    // the next type lookup. If we don't do this lookup might incorrectly miss a type that is not
    // declared in a Java source file with a matching name.
    for (int j = 0; j < libraryUnit.getNumTypeDecl(); j++) {
      TypeDecl type = libraryUnit.getTypeDecl(j);
      if (!libraryTypeMap.containsKey(type.fullName())) {
        libraryTypeMap.put(type.fullName(), type);
      }
    }

    if (libraryTypeMap.containsKey(fullName)) {
      return libraryTypeMap.get(fullName);
    } else {
      libraryTypeMap.put(fullName, unknownType());
      return unknownType();
    }
  }
  /** Initialize primitive types in the library type map.  
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:303
   */
  private void initializeLibraryTypeMap() {
    PrimitiveCompilationUnit unit = getPrimitiveCompilationUnit();
    libraryTypeMap.put(PRIMITIVE_PACKAGE_NAME + ".boolean", unit.typeBoolean());
    libraryTypeMap.put(PRIMITIVE_PACKAGE_NAME + ".byte", unit.typeByte());
    libraryTypeMap.put(PRIMITIVE_PACKAGE_NAME + ".short", unit.typeShort());
    libraryTypeMap.put(PRIMITIVE_PACKAGE_NAME + ".char", unit.typeChar());
    libraryTypeMap.put(PRIMITIVE_PACKAGE_NAME + ".int", unit.typeInt());
    libraryTypeMap.put(PRIMITIVE_PACKAGE_NAME + ".long", unit.typeLong());
    libraryTypeMap.put(PRIMITIVE_PACKAGE_NAME + ".float", unit.typeFloat());
    libraryTypeMap.put(PRIMITIVE_PACKAGE_NAME + ".double", unit.typeDouble());
    libraryTypeMap.put(PRIMITIVE_PACKAGE_NAME + ".null", unit.typeNull());
    libraryTypeMap.put(PRIMITIVE_PACKAGE_NAME + ".void", unit.typeVoid());
    libraryTypeMap.put(PRIMITIVE_PACKAGE_NAME + ".Unknown", unit.unknownType());
  }
  /**
   * {@link #flushTreeCache} should remove all cached values from the tree.
   * Since we cache some nodes outside of attributes, we need to manually clear them here.
   * {@inheritDoc}
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:332
   */
  @Override
  public void flushTreeCache() {
    super.flushTreeCache();
    libraryTypeMap.clear();
    libraryTypeMapInitialized = false;
    libraryCompilationUnitSet.clear();
    sourceTypeMap.clear();
    sourceTypeMapInitialized = false;
    parseLocks.clear();
  }
  /**
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:37
   */
  public long javaParseTime;
  /**
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:38
   */
  public long bytecodeParseTime;
  /**
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:39
   */
  public long codeGenTime;
  /**
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:40
   */
  public long errorCheckTime;
  /**
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:41
   */
  public int numJavaFiles;
  /**
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:42
   */
  public int numClassFiles;
  /**
   * Reset the profile statistics.
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:47
   */
  public void resetStatistics() {
    javaParseTime = 0;
    bytecodeParseTime = 0;
    codeGenTime = 0;
    errorCheckTime = 0;
    numJavaFiles = 0;
    numClassFiles = 0;
  }
  /**
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:56
   */
  public void printStatistics(PrintStream out) {
    out.println("javaParseTime: " + javaParseTime);
    out.println("numJavaFiles: " + numJavaFiles);
    out.println("bytecodeParseTime: " + bytecodeParseTime);
    out.println("numClassFiles: " + numClassFiles);
    out.println("errorCheckTime: " + errorCheckTime);
    out.println("codeGenTime: " + codeGenTime);
  }
  /**
   * @aspect PrettyPrintUtil
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:192
   */
  public void prettyPrint(PrettyPrinter out) {
    for (Iterator iter = compilationUnitIterator(); iter.hasNext(); ) {
      CompilationUnit cu = (CompilationUnit) iter.next();
      if (cu.fromSource()) {
        out.print(cu);
      }
    }
  }
  /**
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:248
   */
  public int analysisDistance = 0;
  /**
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:249
   */
  public Strategy strategy = Strategy.DISTANCE;
  /**
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:255
   */
  public void useReachableStrategy() {
        strategy = Strategy.REACHABLE;
    }
  /**
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:262
   */
  public void useDistanceStrategy(int distance) {
        analysisDistance = distance;
        strategy = Strategy.DISTANCE;
    }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:46
   */
  public PrintStream printStream = null;
  /**
   * @aspect pointerbench
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:51
   */
  public int NULL_BENCHMARK_ID = 0;
  /**
   * @declaredat ASTNode:1
   */
  public Program() {
    super();
  }
  /**
   * Initializes the child array to the correct size.
   * Initializes List and Opt nta children.
   * @apilevel internal
   * @ast method
   * @declaredat ASTNode:10
   */
  public void init$Children() {
    children = new ASTNode[1];
    setChild(new List(), 0);
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"CompilationUnit"},
    type = {"List<CompilationUnit>"},
    kind = {"List"}
  )
  public Program(List<CompilationUnit> p0) {
    setChild(p0, 0);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:23
   */
  protected int numChildren() {
    return 1;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:29
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:33
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    getCompilationUnit_String_reset();
    typeObject_reset();
    typeCloneable_reset();
    typeSerializable_reset();
    typeBoolean_reset();
    typeByte_reset();
    typeShort_reset();
    typeChar_reset();
    typeInt_reset();
    typeLong_reset();
    typeFloat_reset();
    typeDouble_reset();
    typeString_reset();
    typeVoid_reset();
    typeNull_reset();
    unknownType_reset();
    hasPackage_String_reset();
    lookupType_String_String_reset();
    getLibCompilationUnit_String_reset();
    emptyCompilationUnit_reset();
    getPrimitiveCompilationUnit_reset();
    unknownConstructor_reset();
    wildcards_reset();
    wholeProgramSolution_reset();
    paramMap_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:62
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
    Program_allMethods_computed = null;
    Program_allMethods_value = null;
    Program_uniqueTypes_computed = null;
    Program_uniqueTypes_value = null;
    Program_instanciatedTypes_computed = null;
    Program_instanciatedTypes_value = null;
    Program_allPointsToResults_computed = null;
    Program_allPointsToResults_value = null;
    Program_lsp_diagnostics_computed = null;
    Program_lsp_diagnostics_value = null;
    Program_visualiseAllPointsToHover_computed = null;
    Program_visualiseAllPointsToHover_value = null;
    Program_methodsOfInterest_computed = null;
    Program_methodsOfInterest_value = null;
    Program_benchmarkTests_computed = null;
    Program_benchmarkTests_value = null;
    Program_getTargetDeclaration_computed = null;
    Program_getTargetDeclaration_value = null;
    contributorMap_Program_allMethods = null;
    contributorMap_Program_uniqueTypes = null;
    contributorMap_Program_instanciatedTypes = null;
    contributorMap_InvocationTarget_backwardCG = null;
    contributorMap_InvocationTarget_backwardCG = null;
    contributorMap_InvocationTarget_backwardCG = null;
    contributorMap_InvocationTarget_backwardCG = null;
    contributorMap_BlockLambdaBody_lambdaReturns = null;
    contributorMap_InvocationTarget_backwardCG = null;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:93
   */
  public Program clone() throws CloneNotSupportedException {
    Program node = (Program) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:98
   */
  public Program copy() {
    try {
      Program node = (Program) clone();
      node.parent = null;
      if (children != null) {
        node.children = (ASTNode[]) children.clone();
      }
      return node;
    } catch (CloneNotSupportedException e) {
      throw new Error("Error: clone not supported for " + getClass().getName());
    }
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:117
   */
  @Deprecated
  public Program fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:127
   */
  public Program treeCopyNoTransform() {
    Program tree = (Program) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) children[i];
        if (child != null) {
          child = child.treeCopyNoTransform();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:147
   */
  public Program treeCopy() {
    Program tree = (Program) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) getChild(i);
        if (child != null) {
          child = child.treeCopy();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /**
   * Replaces the CompilationUnit list.
   * @param list The new list node to be used as the CompilationUnit list.
   * @apilevel high-level
   */
  public Program setCompilationUnitList(List<CompilationUnit> list) {
    setChild(list, 0);
    return this;
  }
  /**
   * Retrieves the number of children in the CompilationUnit list.
   * @return Number of children in the CompilationUnit list.
   * @apilevel high-level
   */
  public int getNumCompilationUnit() {
    return getCompilationUnitList().getNumChild();
  }
  /**
   * Retrieves the number of children in the CompilationUnit list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the CompilationUnit list.
   * @apilevel low-level
   */
  public int getNumCompilationUnitNoTransform() {
    return getCompilationUnitListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the CompilationUnit list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the CompilationUnit list.
   * @apilevel high-level
   */
  public CompilationUnit getCompilationUnit(int i) {
    return (CompilationUnit) getCompilationUnitList().getChild(i);
  }
  /**
   * Check whether the CompilationUnit list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasCompilationUnit() {
    return getCompilationUnitList().getNumChild() != 0;
  }
  /**
   * Append an element to the CompilationUnit list.
   * @param node The element to append to the CompilationUnit list.
   * @apilevel high-level
   */
  public Program addCompilationUnit(CompilationUnit node) {
    List<CompilationUnit> list = (parent == null) ? getCompilationUnitListNoTransform() : getCompilationUnitList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public Program addCompilationUnitNoTransform(CompilationUnit node) {
    List<CompilationUnit> list = getCompilationUnitListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the CompilationUnit list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public Program setCompilationUnit(CompilationUnit node, int i) {
    List<CompilationUnit> list = getCompilationUnitList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the CompilationUnit list.
   * @return The node representing the CompilationUnit list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="CompilationUnit")
  public List<CompilationUnit> getCompilationUnitList() {
    List<CompilationUnit> list = (List<CompilationUnit>) getChild(0);
    return list;
  }
  /**
   * Retrieves the CompilationUnit list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the CompilationUnit list.
   * @apilevel low-level
   */
  public List<CompilationUnit> getCompilationUnitListNoTransform() {
    return (List<CompilationUnit>) getChildNoTransform(0);
  }
  /**
   * @return the element at index {@code i} in the CompilationUnit list without
   * triggering rewrites.
   */
  public CompilationUnit getCompilationUnitNoTransform(int i) {
    return (CompilationUnit) getCompilationUnitListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the CompilationUnit list.
   * @return The node representing the CompilationUnit list.
   * @apilevel high-level
   */
  public List<CompilationUnit> getCompilationUnits() {
    return getCompilationUnitList();
  }
  /**
   * Retrieves the CompilationUnit list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the CompilationUnit list.
   * @apilevel low-level
   */
  public List<CompilationUnit> getCompilationUnitsNoTransform() {
    return getCompilationUnitListNoTransform();
  }
  /**
   * @aspect <NoAspect>
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:32
   */
  /** @apilevel internal */
protected java.util.Map<ASTNode, java.util.Set<ASTNode>> contributorMap_Program_allMethods = null;

  /** @apilevel internal */
  protected void survey_Program_allMethods() {
    if (contributorMap_Program_allMethods == null) {
      contributorMap_Program_allMethods = new java.util.IdentityHashMap<ASTNode, java.util.Set<ASTNode>>();
      collect_contributors_Program_allMethods(this, contributorMap_Program_allMethods);
    }
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:153
   */
  /** @apilevel internal */
protected java.util.Map<ASTNode, java.util.Set<ASTNode>> contributorMap_Program_uniqueTypes = null;

  /** @apilevel internal */
  protected void survey_Program_uniqueTypes() {
    if (contributorMap_Program_uniqueTypes == null) {
      contributorMap_Program_uniqueTypes = new java.util.IdentityHashMap<ASTNode, java.util.Set<ASTNode>>();
      collect_contributors_Program_uniqueTypes(this, contributorMap_Program_uniqueTypes);
    }
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:158
   */
  /** @apilevel internal */
protected java.util.Map<ASTNode, java.util.Set<ASTNode>> contributorMap_Program_instanciatedTypes = null;

  /** @apilevel internal */
  protected void survey_Program_instanciatedTypes() {
    if (contributorMap_Program_instanciatedTypes == null) {
      contributorMap_Program_instanciatedTypes = new java.util.IdentityHashMap<ASTNode, java.util.Set<ASTNode>>();
      collect_contributors_Program_instanciatedTypes(this, contributorMap_Program_instanciatedTypes);
    }
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:37
   */
  /** @apilevel internal */
protected java.util.Map<ASTNode, java.util.Set<ASTNode>> contributorMap_InvocationTarget_backwardCG = null;

  /** @apilevel internal */
  protected void survey_InvocationTarget_backwardCG() {
    if (contributorMap_InvocationTarget_backwardCG == null) {
      contributorMap_InvocationTarget_backwardCG = new java.util.IdentityHashMap<ASTNode, java.util.Set<ASTNode>>();
      collect_contributors_InvocationTarget_backwardCG(this, contributorMap_InvocationTarget_backwardCG);
    }
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaBody.jrag:47
   */
  /** @apilevel internal */
protected java.util.Map<ASTNode, java.util.Set<ASTNode>> contributorMap_BlockLambdaBody_lambdaReturns = null;

  /** @apilevel internal */
  protected void survey_BlockLambdaBody_lambdaReturns() {
    if (contributorMap_BlockLambdaBody_lambdaReturns == null) {
      contributorMap_BlockLambdaBody_lambdaReturns = new java.util.IdentityHashMap<ASTNode, java.util.Set<ASTNode>>();
      collect_contributors_BlockLambdaBody_lambdaReturns(this, contributorMap_BlockLambdaBody_lambdaReturns);
    }
  }

  /**
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:189
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:189")
  public InvocationTarget getTarget(String pkg, String name) {
    {
        String packageName =
            pkg.contains(".") ? pkg.substring(0, pkg.lastIndexOf(".")) : "";
    
        String className = pkg.substring(pkg.lastIndexOf(".") + 1);
        TypeDecl td = lookupType(packageName, className);
        Iterator iter = td.methods().iterator();
        while (iter.hasNext()) {
          MethodDecl md = (MethodDecl)iter.next();
          if (md.name().equals(name)) {
            return md;
          }
        }
        throw new RuntimeException("CAT: Invocation Target not found. Check the " +
                                   "package and the name of the target.");
      }
  }
  /** @apilevel internal */
  private void getCompilationUnit_String_reset() {
    getCompilationUnit_String_computed = null;
    getCompilationUnit_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map getCompilationUnit_String_values;
  /** @apilevel internal */
  protected java.util.Map getCompilationUnit_String_computed;
  /**
   * Load a compilation unit from disk, selecting a class file
   * if one exists that is not older than a corresponding source
   * file, otherwise the source file is selected.
   * 
   * <p>This attribute is used by the LibCompilationUnit NTA.  Internally this
   * attribute memoizes its results via the parseLocks map.
   * 
   * @return the loaded compilation unit, or the empty compilation unit if no
   * compilation unit was found.
   * @attribute syn
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:502
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ClassPath", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:502")
  public CompilationUnit getCompilationUnit(String typeName) {
    Object _parameters = typeName;
    if (getCompilationUnit_String_computed == null) getCompilationUnit_String_computed = new java.util.HashMap(4);
    if (getCompilationUnit_String_values == null) getCompilationUnit_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (getCompilationUnit_String_values.containsKey(_parameters)
        && getCompilationUnit_String_computed.containsKey(_parameters)
        && (getCompilationUnit_String_computed.get(_parameters) == ASTState.NON_CYCLE || getCompilationUnit_String_computed.get(_parameters) == state().cycle())) {
      return (CompilationUnit) getCompilationUnit_String_values.get(_parameters);
    }
    CompilationUnit getCompilationUnit_String_value = getCompilationUnit_compute(typeName);
    if (state().inCircle()) {
      getCompilationUnit_String_values.put(_parameters, getCompilationUnit_String_value);
      getCompilationUnit_String_computed.put(_parameters, state().cycle());
    
    } else {
      getCompilationUnit_String_values.put(_parameters, getCompilationUnit_String_value);
      getCompilationUnit_String_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return getCompilationUnit_String_value;
  }
  /** @apilevel internal */
  private CompilationUnit getCompilationUnit_compute(String typeName) {
      final ParseSynchronizer synchronizer;
      ParseSynchronizer old = parseLocks.get(typeName);
      if (old != null) {
        synchronizer = old;
      } else {
        ParseSynchronizer fresh = new ParseSynchronizer();
        old = parseLocks.putIfAbsent(typeName, fresh);
        if (old == null) {
          synchronizer = fresh;
        } else {
          synchronizer = old;
        }
      }
      if (synchronizer.first()) {
        CompilationUnit result = classPath.getCompilationUnit(typeName, emptyCompilationUnit());
        synchronizer.set(result);
        return result;
      } else {
        return synchronizer.get();
      }
    }
  /** @apilevel internal */
  private void typeObject_reset() {
    typeObject_computed = null;
    typeObject_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeObject_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeObject_value;

  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:41
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:41")
  public TypeDecl typeObject() {
    ASTState state = state();
    if (typeObject_computed == ASTState.NON_CYCLE || typeObject_computed == state().cycle()) {
      return typeObject_value;
    }
    typeObject_value = lookupType("java.lang", "Object");
    if (state().inCircle()) {
      typeObject_computed = state().cycle();
    
    } else {
      typeObject_computed = ASTState.NON_CYCLE;
    
    }
    return typeObject_value;
  }
  /** @apilevel internal */
  private void typeCloneable_reset() {
    typeCloneable_computed = null;
    typeCloneable_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeCloneable_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeCloneable_value;

  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:42
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:42")
  public TypeDecl typeCloneable() {
    ASTState state = state();
    if (typeCloneable_computed == ASTState.NON_CYCLE || typeCloneable_computed == state().cycle()) {
      return typeCloneable_value;
    }
    typeCloneable_value = lookupType("java.lang", "Cloneable");
    if (state().inCircle()) {
      typeCloneable_computed = state().cycle();
    
    } else {
      typeCloneable_computed = ASTState.NON_CYCLE;
    
    }
    return typeCloneable_value;
  }
  /** @apilevel internal */
  private void typeSerializable_reset() {
    typeSerializable_computed = null;
    typeSerializable_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeSerializable_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeSerializable_value;

  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:43
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:43")
  public TypeDecl typeSerializable() {
    ASTState state = state();
    if (typeSerializable_computed == ASTState.NON_CYCLE || typeSerializable_computed == state().cycle()) {
      return typeSerializable_value;
    }
    typeSerializable_value = lookupType("java.io", "Serializable");
    if (state().inCircle()) {
      typeSerializable_computed = state().cycle();
    
    } else {
      typeSerializable_computed = ASTState.NON_CYCLE;
    
    }
    return typeSerializable_value;
  }
  /** @apilevel internal */
  private void typeBoolean_reset() {
    typeBoolean_computed = null;
    typeBoolean_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeBoolean_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeBoolean_value;

  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:49
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:49")
  public TypeDecl typeBoolean() {
    ASTState state = state();
    if (typeBoolean_computed == ASTState.NON_CYCLE || typeBoolean_computed == state().cycle()) {
      return typeBoolean_value;
    }
    typeBoolean_value = getPrimitiveCompilationUnit().typeBoolean();
    if (state().inCircle()) {
      typeBoolean_computed = state().cycle();
    
    } else {
      typeBoolean_computed = ASTState.NON_CYCLE;
    
    }
    return typeBoolean_value;
  }
  /** @apilevel internal */
  private void typeByte_reset() {
    typeByte_computed = null;
    typeByte_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeByte_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeByte_value;

  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:50
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:50")
  public TypeDecl typeByte() {
    ASTState state = state();
    if (typeByte_computed == ASTState.NON_CYCLE || typeByte_computed == state().cycle()) {
      return typeByte_value;
    }
    typeByte_value = getPrimitiveCompilationUnit().typeByte();
    if (state().inCircle()) {
      typeByte_computed = state().cycle();
    
    } else {
      typeByte_computed = ASTState.NON_CYCLE;
    
    }
    return typeByte_value;
  }
  /** @apilevel internal */
  private void typeShort_reset() {
    typeShort_computed = null;
    typeShort_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeShort_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeShort_value;

  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:51
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:51")
  public TypeDecl typeShort() {
    ASTState state = state();
    if (typeShort_computed == ASTState.NON_CYCLE || typeShort_computed == state().cycle()) {
      return typeShort_value;
    }
    typeShort_value = getPrimitiveCompilationUnit().typeShort();
    if (state().inCircle()) {
      typeShort_computed = state().cycle();
    
    } else {
      typeShort_computed = ASTState.NON_CYCLE;
    
    }
    return typeShort_value;
  }
  /** @apilevel internal */
  private void typeChar_reset() {
    typeChar_computed = null;
    typeChar_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeChar_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeChar_value;

  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:52
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:52")
  public TypeDecl typeChar() {
    ASTState state = state();
    if (typeChar_computed == ASTState.NON_CYCLE || typeChar_computed == state().cycle()) {
      return typeChar_value;
    }
    typeChar_value = getPrimitiveCompilationUnit().typeChar();
    if (state().inCircle()) {
      typeChar_computed = state().cycle();
    
    } else {
      typeChar_computed = ASTState.NON_CYCLE;
    
    }
    return typeChar_value;
  }
  /** @apilevel internal */
  private void typeInt_reset() {
    typeInt_computed = null;
    typeInt_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeInt_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeInt_value;

  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:53
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:53")
  public TypeDecl typeInt() {
    ASTState state = state();
    if (typeInt_computed == ASTState.NON_CYCLE || typeInt_computed == state().cycle()) {
      return typeInt_value;
    }
    typeInt_value = getPrimitiveCompilationUnit().typeInt();
    if (state().inCircle()) {
      typeInt_computed = state().cycle();
    
    } else {
      typeInt_computed = ASTState.NON_CYCLE;
    
    }
    return typeInt_value;
  }
  /** @apilevel internal */
  private void typeLong_reset() {
    typeLong_computed = null;
    typeLong_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeLong_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeLong_value;

  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:54
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:54")
  public TypeDecl typeLong() {
    ASTState state = state();
    if (typeLong_computed == ASTState.NON_CYCLE || typeLong_computed == state().cycle()) {
      return typeLong_value;
    }
    typeLong_value = getPrimitiveCompilationUnit().typeLong();
    if (state().inCircle()) {
      typeLong_computed = state().cycle();
    
    } else {
      typeLong_computed = ASTState.NON_CYCLE;
    
    }
    return typeLong_value;
  }
  /** @apilevel internal */
  private void typeFloat_reset() {
    typeFloat_computed = null;
    typeFloat_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeFloat_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeFloat_value;

  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:55
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:55")
  public TypeDecl typeFloat() {
    ASTState state = state();
    if (typeFloat_computed == ASTState.NON_CYCLE || typeFloat_computed == state().cycle()) {
      return typeFloat_value;
    }
    typeFloat_value = getPrimitiveCompilationUnit().typeFloat();
    if (state().inCircle()) {
      typeFloat_computed = state().cycle();
    
    } else {
      typeFloat_computed = ASTState.NON_CYCLE;
    
    }
    return typeFloat_value;
  }
  /** @apilevel internal */
  private void typeDouble_reset() {
    typeDouble_computed = null;
    typeDouble_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeDouble_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeDouble_value;

  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:56
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:56")
  public TypeDecl typeDouble() {
    ASTState state = state();
    if (typeDouble_computed == ASTState.NON_CYCLE || typeDouble_computed == state().cycle()) {
      return typeDouble_value;
    }
    typeDouble_value = getPrimitiveCompilationUnit().typeDouble();
    if (state().inCircle()) {
      typeDouble_computed = state().cycle();
    
    } else {
      typeDouble_computed = ASTState.NON_CYCLE;
    
    }
    return typeDouble_value;
  }
  /** @apilevel internal */
  private void typeString_reset() {
    typeString_computed = null;
    typeString_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeString_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeString_value;

  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:57
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:57")
  public TypeDecl typeString() {
    ASTState state = state();
    if (typeString_computed == ASTState.NON_CYCLE || typeString_computed == state().cycle()) {
      return typeString_value;
    }
    typeString_value = lookupType("java.lang", "String");
    if (state().inCircle()) {
      typeString_computed = state().cycle();
    
    } else {
      typeString_computed = ASTState.NON_CYCLE;
    
    }
    return typeString_value;
  }
  /** @apilevel internal */
  private void typeVoid_reset() {
    typeVoid_computed = null;
    typeVoid_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeVoid_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeVoid_value;

  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:69
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:69")
  public TypeDecl typeVoid() {
    ASTState state = state();
    if (typeVoid_computed == ASTState.NON_CYCLE || typeVoid_computed == state().cycle()) {
      return typeVoid_value;
    }
    typeVoid_value = getPrimitiveCompilationUnit().typeVoid();
    if (state().inCircle()) {
      typeVoid_computed = state().cycle();
    
    } else {
      typeVoid_computed = ASTState.NON_CYCLE;
    
    }
    return typeVoid_value;
  }
  /** @apilevel internal */
  private void typeNull_reset() {
    typeNull_computed = null;
    typeNull_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeNull_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeNull_value;

  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:72
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:72")
  public TypeDecl typeNull() {
    ASTState state = state();
    if (typeNull_computed == ASTState.NON_CYCLE || typeNull_computed == state().cycle()) {
      return typeNull_value;
    }
    typeNull_value = getPrimitiveCompilationUnit().typeNull();
    if (state().inCircle()) {
      typeNull_computed = state().cycle();
    
    } else {
      typeNull_computed = ASTState.NON_CYCLE;
    
    }
    return typeNull_value;
  }
  /** @apilevel internal */
  private void unknownType_reset() {
    unknownType_computed = null;
    unknownType_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle unknownType_computed = null;

  /** @apilevel internal */
  protected TypeDecl unknownType_value;

  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:75
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:75")
  public TypeDecl unknownType() {
    ASTState state = state();
    if (unknownType_computed == ASTState.NON_CYCLE || unknownType_computed == state().cycle()) {
      return unknownType_value;
    }
    unknownType_value = getPrimitiveCompilationUnit().unknownType();
    if (state().inCircle()) {
      unknownType_computed = state().cycle();
    
    } else {
      unknownType_computed = ASTState.NON_CYCLE;
    
    }
    return unknownType_value;
  }
  /** @apilevel internal */
  private void hasPackage_String_reset() {
    hasPackage_String_computed = null;
    hasPackage_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map hasPackage_String_values;
  /** @apilevel internal */
  protected java.util.Map hasPackage_String_computed;
  /**
   * @attribute syn
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:105
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupFullyQualifiedTypes", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:105")
  public boolean hasPackage(String packageName) {
    Object _parameters = packageName;
    if (hasPackage_String_computed == null) hasPackage_String_computed = new java.util.HashMap(4);
    if (hasPackage_String_values == null) hasPackage_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (hasPackage_String_values.containsKey(_parameters)
        && hasPackage_String_computed.containsKey(_parameters)
        && (hasPackage_String_computed.get(_parameters) == ASTState.NON_CYCLE || hasPackage_String_computed.get(_parameters) == state().cycle())) {
      return (Boolean) hasPackage_String_values.get(_parameters);
    }
    boolean hasPackage_String_value = isPackage(packageName);
    if (state().inCircle()) {
      hasPackage_String_values.put(_parameters, hasPackage_String_value);
      hasPackage_String_computed.put(_parameters, state().cycle());
    
    } else {
      hasPackage_String_values.put(_parameters, hasPackage_String_value);
      hasPackage_String_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return hasPackage_String_value;
  }
  /** @apilevel internal */
  private void lookupType_String_String_reset() {
    lookupType_String_String_computed = null;
    lookupType_String_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map lookupType_String_String_values;
  /** @apilevel internal */
  protected java.util.Map lookupType_String_String_computed;
  /**
   * Checks from-source compilation units for the given type.
   * If no matching compilation unit is found the library compliation units
   * will be searched.
   * @attribute syn
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:146
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupFullyQualifiedTypes", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:146")
  public TypeDecl lookupType(String packageName, String typeName) {
    java.util.List _parameters = new java.util.ArrayList(2);
    _parameters.add(packageName);
    _parameters.add(typeName);
    if (lookupType_String_String_computed == null) lookupType_String_String_computed = new java.util.HashMap(4);
    if (lookupType_String_String_values == null) lookupType_String_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (lookupType_String_String_values.containsKey(_parameters)
        && lookupType_String_String_computed.containsKey(_parameters)
        && (lookupType_String_String_computed.get(_parameters) == ASTState.NON_CYCLE || lookupType_String_String_computed.get(_parameters) == state().cycle())) {
      return (TypeDecl) lookupType_String_String_values.get(_parameters);
    }
    TypeDecl lookupType_String_String_value = lookupType_compute(packageName, typeName);
    if (state().inCircle()) {
      lookupType_String_String_values.put(_parameters, lookupType_String_String_value);
      lookupType_String_String_computed.put(_parameters, state().cycle());
    
    } else {
      lookupType_String_String_values.put(_parameters, lookupType_String_String_value);
      lookupType_String_String_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return lookupType_String_String_value;
  }
  /** @apilevel internal */
  private TypeDecl lookupType_compute(String packageName, String typeName) {
      // Look for a matching source type.
      TypeDecl sourceType = lookupSourceType(packageName, typeName);
      if (!sourceType.isUnknown()) {
        return sourceType;
      }
  
      // Look for a matching library type.
      return lookupLibraryType(packageName, typeName);
    }
  /** @apilevel internal */
  private void getLibCompilationUnit_String_reset() {
    getLibCompilationUnit_String_values = null;
    getLibCompilationUnit_String_proxy = null;
  }
  /** @apilevel internal */
  protected ASTNode getLibCompilationUnit_String_proxy;
  /** @apilevel internal */
  protected java.util.Map getLibCompilationUnit_String_values;

  /**
   * This attribute is used to cache library compilation units, by storing the compilation units in
   * a parameterized NTA.
   * @attribute syn
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:322
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="LookupFullyQualifiedTypes", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:322")
  public CompilationUnit getLibCompilationUnit(String typeName) {
    Object _parameters = typeName;
    if (getLibCompilationUnit_String_values == null) getLibCompilationUnit_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (getLibCompilationUnit_String_values.containsKey(_parameters)) {
      return (CompilationUnit) getLibCompilationUnit_String_values.get(_parameters);
    }
    state().enterLazyAttribute();
    CompilationUnit getLibCompilationUnit_String_value = getCompilationUnit(typeName);
    if (getLibCompilationUnit_String_proxy == null) {
      getLibCompilationUnit_String_proxy = new ASTNode();
      getLibCompilationUnit_String_proxy.setParent(this);
    }
    if (getLibCompilationUnit_String_value != null) {
      getLibCompilationUnit_String_value.setParent(getLibCompilationUnit_String_proxy);
      if (getLibCompilationUnit_String_value.mayHaveRewrite()) {
        getLibCompilationUnit_String_value = (CompilationUnit) getLibCompilationUnit_String_value.rewrittenNode();
        getLibCompilationUnit_String_value.setParent(getLibCompilationUnit_String_proxy);
      }
    }
    getLibCompilationUnit_String_values.put(_parameters, getLibCompilationUnit_String_value);
    state().leaveLazyAttribute();
    return getLibCompilationUnit_String_value;
  }
  /** @apilevel internal */
  private void emptyCompilationUnit_reset() {
    emptyCompilationUnit_computed = false;
    
    emptyCompilationUnit_value = null;
  }
  /** @apilevel internal */
  protected boolean emptyCompilationUnit_computed = false;

  /** @apilevel internal */
  protected CompilationUnit emptyCompilationUnit_value;

  /**
   * @attribute syn
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:325
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="LookupFullyQualifiedTypes", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:325")
  public CompilationUnit emptyCompilationUnit() {
    ASTState state = state();
    if (emptyCompilationUnit_computed) {
      return emptyCompilationUnit_value;
    }
    state().enterLazyAttribute();
    emptyCompilationUnit_value = new CompilationUnit();
    emptyCompilationUnit_value.setParent(this);
    emptyCompilationUnit_computed = true;
    state().leaveLazyAttribute();
    return emptyCompilationUnit_value;
  }
  /** @apilevel internal */
  private void getPrimitiveCompilationUnit_reset() {
    getPrimitiveCompilationUnit_computed = false;
    
    getPrimitiveCompilationUnit_value = null;
  }
  /** @apilevel internal */
  protected boolean getPrimitiveCompilationUnit_computed = false;

  /** @apilevel internal */
  protected PrimitiveCompilationUnit getPrimitiveCompilationUnit_value;

  /** Creates a compilation unit with primitive types. 
   * @attribute syn
   * @aspect PrimitiveTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrimitiveTypes.jrag:145
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="PrimitiveTypes", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrimitiveTypes.jrag:145")
  public PrimitiveCompilationUnit getPrimitiveCompilationUnit() {
    ASTState state = state();
    if (getPrimitiveCompilationUnit_computed) {
      return getPrimitiveCompilationUnit_value;
    }
    state().enterLazyAttribute();
    getPrimitiveCompilationUnit_value = getPrimitiveCompilationUnit_compute();
    getPrimitiveCompilationUnit_value.setParent(this);
    getPrimitiveCompilationUnit_computed = true;
    state().leaveLazyAttribute();
    return getPrimitiveCompilationUnit_value;
  }
  /** @apilevel internal */
  private PrimitiveCompilationUnit getPrimitiveCompilationUnit_compute() {
      PrimitiveCompilationUnit u = new PrimitiveCompilationUnit();
      u.setPackageDecl(PRIMITIVE_PACKAGE_NAME);
      return u;
    }
  /** @apilevel internal */
  private void unknownConstructor_reset() {
    unknownConstructor_computed = null;
    unknownConstructor_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle unknownConstructor_computed = null;

  /** @apilevel internal */
  protected ConstructorDecl unknownConstructor_value;

  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:262
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:262")
  public ConstructorDecl unknownConstructor() {
    ASTState state = state();
    if (unknownConstructor_computed == ASTState.NON_CYCLE || unknownConstructor_computed == state().cycle()) {
      return unknownConstructor_value;
    }
    unknownConstructor_value = unknownType().constructors().iterator().next();
    if (state().inCircle()) {
      unknownConstructor_computed = state().cycle();
    
    } else {
      unknownConstructor_computed = ASTState.NON_CYCLE;
    
    }
    return unknownConstructor_value;
  }
  /** @apilevel internal */
  private void wildcards_reset() {
    wildcards_computed = false;
    
    wildcards_value = null;
  }
  /** @apilevel internal */
  protected boolean wildcards_computed = false;

  /** @apilevel internal */
  protected WildcardsCompilationUnit wildcards_value;

  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1741
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1741")
  public WildcardsCompilationUnit wildcards() {
    ASTState state = state();
    if (wildcards_computed) {
      return wildcards_value;
    }
    state().enterLazyAttribute();
    wildcards_value = new WildcardsCompilationUnit(
              "wildcards",
              new List(),
              new List());
    wildcards_value.setParent(this);
    wildcards_computed = true;
    state().leaveLazyAttribute();
    return wildcards_value;
  }
  /** @apilevel internal */
  private void wholeProgramSolution_reset() {
    wholeProgramSolution_computed = null;
    wholeProgramSolution_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle wholeProgramSolution_computed = null;

  /** @apilevel internal */
  protected Set<PointsToEdge> wholeProgramSolution_value;

  /**
   * @attribute syn
   * @aspect WholeProgram
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/WholeProgram.jrag:8
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="WholeProgram", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/WholeProgram.jrag:8")
  public Set<PointsToEdge> wholeProgramSolution() {
    ASTState state = state();
    if (wholeProgramSolution_computed == ASTState.NON_CYCLE || wholeProgramSolution_computed == state().cycle()) {
      return wholeProgramSolution_value;
    }
    wholeProgramSolution_value = wholeProgramSolution_compute();
    if (state().inCircle()) {
      wholeProgramSolution_computed = state().cycle();
    
    } else {
      wholeProgramSolution_computed = ASTState.NON_CYCLE;
    
    }
    return wholeProgramSolution_value;
  }
  /** @apilevel internal */
  private Set<PointsToEdge> wholeProgramSolution_compute() {
          Set<InclusionEdge> inclusionEdges = allMethods().stream().flatMap(m -> m.inclusionEdges().stream()).collect(Collectors.toSet());
          Set<PointsToEdge>  pointsToEdges =  allMethods().stream().flatMap(m -> m.pointsToEdges().stream()).collect(Collectors.toSet());
  
          System.err.println("Starting whole program solving...");
          System.err.println("Total edges: " + (inclusionEdges.size() + pointsToEdges.size())); 
          Solver solver = new Solver(inclusionEdges, pointsToEdges, null);
          Set<PointsToEdge> solution = solver.solution();
          System.err.println("Done solving");
          return solution;
      }
  /** @apilevel internal */
  private void paramMap_reset() {
    paramMap_computed = null;
    paramMap_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle paramMap_computed = null;

  /** @apilevel internal */
  protected Map<DeclarationSite, Set<AllocationSite>> paramMap_value;

  /**
   * @attribute syn
   * @aspect WholeProgram
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/WholeProgram.jrag:20
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="WholeProgram", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/WholeProgram.jrag:20")
  public Map<DeclarationSite, Set<AllocationSite>> paramMap() {
    ASTState state = state();
    if (paramMap_computed == ASTState.NON_CYCLE || paramMap_computed == state().cycle()) {
      return paramMap_value;
    }
    paramMap_value = paramMap_compute();
    if (state().inCircle()) {
      paramMap_computed = state().cycle();
    
    } else {
      paramMap_computed = ASTState.NON_CYCLE;
    
    }
    return paramMap_value;
  }
  /** @apilevel internal */
  private Map<DeclarationSite, Set<AllocationSite>> paramMap_compute() {
          Map<DeclarationSite, Set<AllocationSite>> res = new HashMap<>();
          for (PointsToEdge pte : wholeProgramSolution()) {
              res.putIfAbsent(pte.from, new HashSet<>());
              res.get(pte.from).add(pte.to);
              if (pte.to.typeName().equals("")) System.err.println("empty typename: " + pte.to.getClass());
          }
          return res;
      }
  /**
   * @attribute syn
   * @aspect codeprober
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Codeprober.jrag:3
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="codeprober", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Codeprober.jrag:3")
  public ASTNode pastaVisibleNextAfterRoot() {
    {
            final int numCUs = getNumCompilationUnit();
            return numCUs == 0 ? null : getCompilationUnit(numCUs - 1);
        }
  }
  /**
   * @attribute syn
   * @aspect pointerbench
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:90
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="pointerbench", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:90")
  public Set<String> benchmarkTestsTargets() {
    Set<String> benchmarkTestsTargets_value = benchmarkTests()
            .stream()
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());
    return benchmarkTestsTargets_value;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:40
   * @apilevel internal
   */
  public CompilationUnit Define_enclosingCompilationUnit(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:40
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingCompilationUnit
   */
  protected boolean canDefine_enclosingCompilationUnit(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:212
   * @apilevel internal
   */
  public InvocationTarget Define_enclosingTarget(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:212
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingTarget
   */
  protected boolean canDefine_enclosingTarget(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:408
   * @apilevel internal
   */
  public TypeDecl Define_declaredIn(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:408
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute declaredIn
   */
  protected boolean canDefine_declaredIn(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Options.jadd:41
   * @apilevel internal
   */
  public Program Define_program(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return this;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Options.jadd:41
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute program
   */
  protected boolean canDefine_program(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:44
   * @apilevel internal
   */
  public boolean Define_isMethodParameter(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:44
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isMethodParameter
   */
  protected boolean canDefine_isMethodParameter(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:45
   * @apilevel internal
   */
  public boolean Define_isConstructorParameter(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:45
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isConstructorParameter
   */
  protected boolean canDefine_isConstructorParameter(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:46
   * @apilevel internal
   */
  public boolean Define_isExceptionHandlerParameter(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:46
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isExceptionHandlerParameter
   */
  protected boolean canDefine_isExceptionHandlerParameter(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:35
   * @apilevel internal
   */
  public Collection<ConstructorDecl> Define_lookupConstructor(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return Collections.emptyList();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:35
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupConstructor
   */
  protected boolean canDefine_lookupConstructor(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:43
   * @apilevel internal
   */
  public Collection<ConstructorDecl> Define_lookupSuperConstructor(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return Collections.emptyList();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:43
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupSuperConstructor
   */
  protected boolean canDefine_lookupSuperConstructor(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:47
   * @apilevel internal
   */
  public TypeDecl Define_typeException(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return lookupType("java.lang", "Exception");
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:47
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeException
   */
  protected boolean canDefine_typeException(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:124
   * @apilevel internal
   */
  public TypeDecl Define_typeRuntimeException(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return lookupType("java.lang", "RuntimeException");
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:124
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeRuntimeException
   */
  protected boolean canDefine_typeRuntimeException(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:122
   * @apilevel internal
   */
  public TypeDecl Define_typeError(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return lookupType("java.lang", "Error");
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:122
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeError
   */
  protected boolean canDefine_typeError(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:56
   * @apilevel internal
   */
  public TypeDecl Define_typeNullPointerException(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return lookupType("java.lang", "NullPointerException");
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:56
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeNullPointerException
   */
  protected boolean canDefine_typeNullPointerException(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:97
   * @apilevel internal
   */
  public TypeDecl Define_typeThrowable(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return lookupType("java.lang", "Throwable");
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:97
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeThrowable
   */
  protected boolean canDefine_typeThrowable(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:114
   * @apilevel internal
   */
  public boolean Define_handlesException(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    int childIndex = this.getIndexOfChild(_callerNode);
    {
        throw new Error("Operation handlesException not supported");
      }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:114
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute handlesException
   */
  protected boolean canDefine_handlesException(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:284
   * @apilevel internal
   */
  public boolean Define_reportUnreachable(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:284
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute reportUnreachable
   */
  protected boolean canDefine_reportUnreachable(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:110
   * @apilevel internal
   */
  public CompilationUnit Define_compilationUnit(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:110
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute compilationUnit
   */
  protected boolean canDefine_compilationUnit(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:34
   * @apilevel internal
   */
  public boolean Define_isDest(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:34
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isDest
   */
  protected boolean canDefine_isDest(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:44
   * @apilevel internal
   */
  public boolean Define_isSource(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:44
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isSource
   */
  protected boolean canDefine_isSource(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:66
   * @apilevel internal
   */
  public boolean Define_isIncOrDec(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:66
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isIncOrDec
   */
  protected boolean canDefine_isIncOrDec(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute assignedBefore
   */
  protected boolean canDefine_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:887
   * @apilevel internal
   */
  public boolean Define_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:887
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unassignedBefore
   */
  protected boolean canDefine_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:33
   * @apilevel internal
   */
  public String Define_methodHost(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    {
        throw new Error("Needs extra equation for methodHost()");
      }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:33
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute methodHost
   */
  protected boolean canDefine_methodHost(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:204
   * @apilevel internal
   */
  public boolean Define_inExplicitConstructorInvocation(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:204
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inExplicitConstructorInvocation
   */
  protected boolean canDefine_inExplicitConstructorInvocation(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:212
   * @apilevel internal
   */
  public TypeDecl Define_enclosingExplicitConstructorHostType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:212
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingExplicitConstructorHostType
   */
  protected boolean canDefine_enclosingExplicitConstructorHostType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:223
   * @apilevel internal
   */
  public boolean Define_inStaticContext(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:223
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inStaticContext
   */
  protected boolean canDefine_inStaticContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1302
   * @apilevel internal
   */
  public TypeDecl Define_typeObject(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return typeObject();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1302
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeObject
   */
  protected boolean canDefine_typeObject(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:152
   * @apilevel internal
   */
  public TypeDecl Define_typeCloneable(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return typeCloneable();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:152
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeCloneable
   */
  protected boolean canDefine_typeCloneable(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:151
   * @apilevel internal
   */
  public TypeDecl Define_typeSerializable(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return typeSerializable();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:151
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeSerializable
   */
  protected boolean canDefine_typeSerializable(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:78
   * @apilevel internal
   */
  public TypeDecl Define_typeBoolean(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return typeBoolean();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:78
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeBoolean
   */
  protected boolean canDefine_typeBoolean(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:79
   * @apilevel internal
   */
  public TypeDecl Define_typeByte(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return typeByte();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:79
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeByte
   */
  protected boolean canDefine_typeByte(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:80
   * @apilevel internal
   */
  public TypeDecl Define_typeShort(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return typeShort();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:80
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeShort
   */
  protected boolean canDefine_typeShort(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:81
   * @apilevel internal
   */
  public TypeDecl Define_typeChar(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return typeChar();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:81
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeChar
   */
  protected boolean canDefine_typeChar(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:90
   * @apilevel internal
   */
  public TypeDecl Define_typeInt(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return typeInt();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:90
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeInt
   */
  protected boolean canDefine_typeInt(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:92
   * @apilevel internal
   */
  public TypeDecl Define_typeLong(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return typeLong();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:92
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeLong
   */
  protected boolean canDefine_typeLong(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:84
   * @apilevel internal
   */
  public TypeDecl Define_typeFloat(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return typeFloat();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:84
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeFloat
   */
  protected boolean canDefine_typeFloat(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:85
   * @apilevel internal
   */
  public TypeDecl Define_typeDouble(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return typeDouble();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:85
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeDouble
   */
  protected boolean canDefine_typeDouble(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:525
   * @apilevel internal
   */
  public TypeDecl Define_typeString(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return typeString();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:525
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeString
   */
  protected boolean canDefine_typeString(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:87
   * @apilevel internal
   */
  public TypeDecl Define_typeVoid(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return typeVoid();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:87
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeVoid
   */
  protected boolean canDefine_typeVoid(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1321
   * @apilevel internal
   */
  public TypeDecl Define_typeNull(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return typeNull();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1321
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeNull
   */
  protected boolean canDefine_typeNull(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:32
   * @apilevel internal
   */
  public TypeDecl Define_unknownType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return unknownType();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:32
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unknownType
   */
  protected boolean canDefine_unknownType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:113
   * @apilevel internal
   */
  public boolean Define_hasPackage(ASTNode _callerNode, ASTNode _childNode, String packageName) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return hasPackage(packageName);
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:113
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute hasPackage
   */
  protected boolean canDefine_hasPackage(ASTNode _callerNode, ASTNode _childNode, String packageName) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:42
   * @apilevel internal
   */
  public TypeDecl Define_lookupType(ASTNode _callerNode, ASTNode _childNode, String packageName, String typeName) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return lookupType(packageName, typeName);
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:42
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupType
   */
  protected boolean canDefine_lookupType(ASTNode _callerNode, ASTNode _childNode, String packageName, String typeName) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:231
   * @apilevel internal
   */
  public SimpleSet<TypeDecl> Define_lookupType(ASTNode _callerNode, ASTNode _childNode, String name) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return emptySet();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:231
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupType
   */
  protected boolean canDefine_lookupType(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:485
   * @apilevel internal
   */
  public TypeDecl Define_switchType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return unknownType();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:485
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute switchType
   */
  protected boolean canDefine_switchType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:537
   * @apilevel internal
   */
  public TypeDecl Define_returnType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return typeVoid();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:537
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute returnType
   */
  protected boolean canDefine_returnType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:672
   * @apilevel internal
   */
  public TypeDecl Define_enclosingInstance(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:672
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingInstance
   */
  protected boolean canDefine_enclosingInstance(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:433
   * @apilevel internal
   */
  public boolean Define_mayBePublic(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:433
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBePublic
   */
  protected boolean canDefine_mayBePublic(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:435
   * @apilevel internal
   */
  public boolean Define_mayBeProtected(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:435
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeProtected
   */
  protected boolean canDefine_mayBeProtected(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:434
   * @apilevel internal
   */
  public boolean Define_mayBePrivate(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:434
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBePrivate
   */
  protected boolean canDefine_mayBePrivate(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:436
   * @apilevel internal
   */
  public boolean Define_mayBeStatic(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:436
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeStatic
   */
  protected boolean canDefine_mayBeStatic(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:437
   * @apilevel internal
   */
  public boolean Define_mayBeFinal(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:437
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeFinal
   */
  protected boolean canDefine_mayBeFinal(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:438
   * @apilevel internal
   */
  public boolean Define_mayBeAbstract(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:438
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeAbstract
   */
  protected boolean canDefine_mayBeAbstract(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:439
   * @apilevel internal
   */
  public boolean Define_mayBeVolatile(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:439
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeVolatile
   */
  protected boolean canDefine_mayBeVolatile(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:440
   * @apilevel internal
   */
  public boolean Define_mayBeTransient(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:440
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeTransient
   */
  protected boolean canDefine_mayBeTransient(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:441
   * @apilevel internal
   */
  public boolean Define_mayBeStrictfp(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:441
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeStrictfp
   */
  protected boolean canDefine_mayBeStrictfp(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:442
   * @apilevel internal
   */
  public boolean Define_mayBeSynchronized(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:442
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeSynchronized
   */
  protected boolean canDefine_mayBeSynchronized(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:443
   * @apilevel internal
   */
  public boolean Define_mayBeNative(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:443
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeNative
   */
  protected boolean canDefine_mayBeNative(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:30
   * @apilevel internal
   */
  public SimpleSet<Variable> Define_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return emptySet();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:30
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupVariable
   */
  protected boolean canDefine_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:381
   * @apilevel internal
   */
  public BodyDecl Define_enclosingMemberDecl(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:381
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingMemberDecl
   */
  protected boolean canDefine_enclosingMemberDecl(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:31
   * @apilevel internal
   */
  public VariableScope Define_outerScope(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    {
        throw new UnsupportedOperationException("outerScope() not defined");
      }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:31
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute outerScope
   */
  protected boolean canDefine_outerScope(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:546
   * @apilevel internal
   */
  public boolean Define_insideLoop(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:546
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute insideLoop
   */
  protected boolean canDefine_insideLoop(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:554
   * @apilevel internal
   */
  public boolean Define_insideSwitch(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:554
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute insideSwitch
   */
  protected boolean canDefine_insideSwitch(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:615
   * @apilevel internal
   */
  public Case Define_previousCase(ASTNode _callerNode, ASTNode _childNode, Case c) {
    int childIndex = this.getIndexOfChild(_callerNode);
    {
        throw new Error("Missing enclosing switch for case label.");
      }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:615
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute previousCase
   */
  protected boolean canDefine_previousCase(ASTNode _callerNode, ASTNode _childNode, Case c) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Arrays.jrag:56
   * @apilevel internal
   */
  public TypeDecl Define_componentType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return unknownType();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Arrays.jrag:56
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute componentType
   */
  protected boolean canDefine_componentType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DeclareBeforeUse.jrag:35
   * @apilevel internal
   */
  public int Define_blockIndex(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return -1;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DeclareBeforeUse.jrag:35
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute blockIndex
   */
  protected boolean canDefine_blockIndex(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DeclareBeforeUse.jrag:58
   * @apilevel internal
   */
  public boolean Define_declaredBefore(ASTNode _callerNode, ASTNode _childNode, Variable decl) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DeclareBeforeUse.jrag:58
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute declaredBefore
   */
  protected boolean canDefine_declaredBefore(ASTNode _callerNode, ASTNode _childNode, Variable decl) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return NameType.NOT_CLASSIFIED;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute nameType
   */
  protected boolean canDefine_nameType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:98
   * @apilevel internal
   */
  public Expr Define_nestedScope(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    {
        throw new UnsupportedOperationException(
            "Cannot evaluate nestedScope() on unqualified access.");
      }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:98
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute nestedScope
   */
  protected boolean canDefine_nestedScope(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:116
   * @apilevel internal
   */
  public Collection<MethodDecl> Define_lookupMethod(ASTNode _callerNode, ASTNode _childNode, String name) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return Collections.EMPTY_LIST;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:116
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupMethod
   */
  protected boolean canDefine_lookupMethod(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:255
   * @apilevel internal
   */
  public LabeledStmt Define_lookupLabel(ASTNode _callerNode, ASTNode _childNode, String name) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:255
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupLabel
   */
  protected boolean canDefine_lookupLabel(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:33
   * @apilevel internal
   */
  public TypeDecl Define_superType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:33
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute superType
   */
  protected boolean canDefine_superType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:39
   * @apilevel internal
   */
  public ConstructorDecl Define_constructorDecl(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return unknownConstructor();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:39
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute constructorDecl
   */
  protected boolean canDefine_constructorDecl(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:386
   * @apilevel internal
   */
  public Variable Define_unknownField(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return unknownType().findSingleVariable("unknown");
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:386
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unknownField
   */
  protected boolean canDefine_unknownField(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:31
   * @apilevel internal
   */
  public MethodDecl Define_unknownMethod(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    {
        for (MethodDecl m : unknownType().memberMethods("unknown")) {
          return m;
        }
        throw new Error("Could not find method unknown in type Unknown");
      }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:31
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unknownMethod
   */
  protected boolean canDefine_unknownMethod(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/ConstructorReference.jrag:30
   * @apilevel internal
   */
  public ConstructorDecl Define_unknownConstructor(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return unknownConstructor();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/ConstructorReference.jrag:30
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unknownConstructor
   */
  protected boolean canDefine_unknownConstructor(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:723
   * @apilevel internal
   */
  public TypeDecl Define_declType(ASTNode _callerNode, ASTNode _childNode) {
    int i = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:723
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute declType
   */
  protected boolean canDefine_declType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:30
   * @apilevel internal
   */
  public BodyDecl Define_enclosingBodyDecl(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:30
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingBodyDecl
   */
  protected boolean canDefine_enclosingBodyDecl(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:631
   * @apilevel internal
   */
  public boolean Define_isMemberType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:631
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isMemberType
   */
  protected boolean canDefine_isMemberType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:76
   * @apilevel internal
   */
  public TypeDecl Define_hostType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:76
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute hostType
   */
  protected boolean canDefine_hostType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsParTypeDecl.jrag:74
   * @apilevel internal
   */
  public TypeDecl Define_genericDecl(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsParTypeDecl.jrag:74
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute genericDecl
   */
  protected boolean canDefine_genericDecl(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:46
   * @apilevel internal
   */
  public boolean Define_variableArityValid(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:46
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute variableArityValid
   */
  protected boolean canDefine_variableArityValid(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:131
   * @apilevel internal
   */
  public boolean Define_mayUseAnnotationTarget(ASTNode _callerNode, ASTNode _childNode, String name) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:131
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayUseAnnotationTarget
   */
  protected boolean canDefine_mayUseAnnotationTarget(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:278
   * @apilevel internal
   */
  public ElementValue Define_lookupElementTypeValue(ASTNode _callerNode, ASTNode _childNode, String name) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:278
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupElementTypeValue
   */
  protected boolean canDefine_lookupElementTypeValue(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SuppressWarnings.jrag:37
   * @apilevel internal
   */
  public boolean Define_withinSuppressWarnings(ASTNode _callerNode, ASTNode _childNode, String annot) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SuppressWarnings.jrag:37
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute withinSuppressWarnings
   */
  protected boolean canDefine_withinSuppressWarnings(ASTNode _callerNode, ASTNode _childNode, String annot) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:533
   * @apilevel internal
   */
  public boolean Define_withinDeprecatedAnnotation(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:533
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute withinDeprecatedAnnotation
   */
  protected boolean canDefine_withinDeprecatedAnnotation(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:604
   * @apilevel internal
   */
  public Annotation Define_lookupAnnotation(ASTNode _callerNode, ASTNode _childNode, TypeDecl typeDecl) {
    int i = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:604
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupAnnotation
   */
  protected boolean canDefine_lookupAnnotation(ASTNode _callerNode, ASTNode _childNode, TypeDecl typeDecl) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:648
   * @apilevel internal
   */
  public TypeDecl Define_enclosingAnnotationDecl(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return unknownType();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:648
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingAnnotationDecl
   */
  protected boolean canDefine_enclosingAnnotationDecl(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:385
   * @apilevel internal
   */
  public boolean Define_inExtendsOrImplements(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:385
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inExtendsOrImplements
   */
  protected boolean canDefine_inExtendsOrImplements(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1405
   * @apilevel internal
   */
  public FieldDecl Define_fieldDecl(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1405
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute fieldDecl
   */
  protected boolean canDefine_fieldDecl(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1762
   * @apilevel internal
   */
  public TypeDecl Define_typeWildcard(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return wildcards().typeWildcard();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1762
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeWildcard
   */
  protected boolean canDefine_typeWildcard(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1761
   * @apilevel internal
   */
  public TypeDecl Define_lookupWildcardExtends(ASTNode _callerNode, ASTNode _childNode, TypeDecl typeDecl) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return wildcards().lookupWildcardExtends(typeDecl);
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1761
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupWildcardExtends
   */
  protected boolean canDefine_lookupWildcardExtends(ASTNode _callerNode, ASTNode _childNode, TypeDecl typeDecl) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1760
   * @apilevel internal
   */
  public TypeDecl Define_lookupWildcardSuper(ASTNode _callerNode, ASTNode _childNode, TypeDecl typeDecl) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return wildcards().lookupWildcardSuper(typeDecl);
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1760
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupWildcardSuper
   */
  protected boolean canDefine_lookupWildcardSuper(ASTNode _callerNode, ASTNode _childNode, TypeDecl typeDecl) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:210
   * @apilevel internal
   */
  public LUBType Define_lookupLUBType(ASTNode _callerNode, ASTNode _childNode, Collection<TypeDecl> bounds) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return wildcards().lookupLUBType(bounds);
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:210
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupLUBType
   */
  protected boolean canDefine_lookupLUBType(ASTNode _callerNode, ASTNode _childNode, Collection<TypeDecl> bounds) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1862
   * @apilevel internal
   */
  public GLBType Define_lookupGLBType(ASTNode _callerNode, ASTNode _childNode, Collection<TypeDecl> bounds) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return wildcards().lookupGLBType(bounds);
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1862
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupGLBType
   */
  protected boolean canDefine_lookupGLBType(ASTNode _callerNode, ASTNode _childNode, Collection<TypeDecl> bounds) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/AssignConvertedType.jrag:39
   * @apilevel internal
   */
  public TypeDecl Define_assignConvertedType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return typeNull();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/AssignConvertedType.jrag:39
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute assignConvertedType
   */
  protected boolean canDefine_assignConvertedType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Diamond.jrag:99
   * @apilevel internal
   */
  public ClassInstanceExpr Define_getClassInstanceExpr(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Diamond.jrag:99
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute getClassInstanceExpr
   */
  protected boolean canDefine_getClassInstanceExpr(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Diamond.jrag:284
   * @apilevel internal
   */
  public boolean Define_isAnonymousDecl(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Diamond.jrag:284
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isAnonymousDecl
   */
  protected boolean canDefine_isAnonymousDecl(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Diamond.jrag:300
   * @apilevel internal
   */
  public boolean Define_isExplicitGenericConstructorAccess(ASTNode _callerNode, ASTNode _childNode) {
    int i = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Diamond.jrag:300
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isExplicitGenericConstructorAccess
   */
  protected boolean canDefine_isExplicitGenericConstructorAccess(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:206
   * @apilevel internal
   */
  public boolean Define_isCatchParam(ASTNode _callerNode, ASTNode _childNode) {
    int i = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:206
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isCatchParam
   */
  protected boolean canDefine_isCatchParam(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:213
   * @apilevel internal
   */
  public CatchClause Define_catchClause(ASTNode _callerNode, ASTNode _childNode) {
    int i = this.getIndexOfChild(_callerNode);
    {
        throw new IllegalStateException("Could not find parent " + "catch clause");
      }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:213
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute catchClause
   */
  protected boolean canDefine_catchClause(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:178
   * @apilevel internal
   */
  public boolean Define_resourcePreviouslyDeclared(ASTNode _callerNode, ASTNode _childNode, String name) {
    int i = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:178
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute resourcePreviouslyDeclared
   */
  protected boolean canDefine_resourcePreviouslyDeclared(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:44
   * @apilevel internal
   */
  public TypeDecl Define_targetType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return typeNull();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:44
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute targetType
   */
  protected boolean canDefine_targetType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:72
   * @apilevel internal
   */
  public Stmt Define_prevStmt(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:72
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute prevStmt
   */
  protected boolean canDefine_prevStmt(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /** @apilevel internal */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
  /** @apilevel internal */
  public boolean canRewrite() {
    return false;
  }
  /**
   * @attribute coll
   * @aspect AllMethods
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:32
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="AllMethods", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:32")
  public Set<InvocationTarget> allMethods() {
    ASTState state = state();
    if (Program_allMethods_computed == ASTState.NON_CYCLE || Program_allMethods_computed == state().cycle()) {
      return Program_allMethods_value;
    }
    Program_allMethods_value = allMethods_compute();
    if (state().inCircle()) {
      Program_allMethods_computed = state().cycle();
    
    } else {
      Program_allMethods_computed = ASTState.NON_CYCLE;
    
    }
    return Program_allMethods_value;
  }
  /** @apilevel internal */
  private Set<InvocationTarget> allMethods_compute() {
    ASTNode node = this;
    while (node != null && !(node instanceof Program)) {
      node = node.getParent();
    }
    Program root = (Program) node;
    root.survey_Program_allMethods();
    Set<InvocationTarget> _computedValue = new HashSet<>();
    if (root.contributorMap_Program_allMethods.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_Program_allMethods.get(this)) {
        contributor.contributeTo_Program_allMethods(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle Program_allMethods_computed = null;

  /** @apilevel internal */
  protected Set<InvocationTarget> Program_allMethods_value;

  /**
   * @attribute coll
   * @aspect CHA
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:153
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="CHA", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:153")
  public Set<TypeDecl> uniqueTypes() {
    ASTState state = state();
    if (Program_uniqueTypes_computed == ASTState.NON_CYCLE || Program_uniqueTypes_computed == state().cycle()) {
      return Program_uniqueTypes_value;
    }
    Program_uniqueTypes_value = uniqueTypes_compute();
    if (state().inCircle()) {
      Program_uniqueTypes_computed = state().cycle();
    
    } else {
      Program_uniqueTypes_computed = ASTState.NON_CYCLE;
    
    }
    return Program_uniqueTypes_value;
  }
  /** @apilevel internal */
  private Set<TypeDecl> uniqueTypes_compute() {
    ASTNode node = this;
    while (node != null && !(node instanceof Program)) {
      node = node.getParent();
    }
    Program root = (Program) node;
    root.survey_Program_uniqueTypes();
    Set<TypeDecl> _computedValue = new LinkedHashSet<>();
    if (root.contributorMap_Program_uniqueTypes.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_Program_uniqueTypes.get(this)) {
        contributor.contributeTo_Program_uniqueTypes(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle Program_uniqueTypes_computed = null;

  /** @apilevel internal */
  protected Set<TypeDecl> Program_uniqueTypes_value;

  /**
   * @attribute coll
   * @aspect CHA
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:158
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="CHA", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:158")
  public Set<TypeDecl> instanciatedTypes() {
    ASTState state = state();
    if (Program_instanciatedTypes_computed == ASTState.NON_CYCLE || Program_instanciatedTypes_computed == state().cycle()) {
      return Program_instanciatedTypes_value;
    }
    Program_instanciatedTypes_value = instanciatedTypes_compute();
    if (state().inCircle()) {
      Program_instanciatedTypes_computed = state().cycle();
    
    } else {
      Program_instanciatedTypes_computed = ASTState.NON_CYCLE;
    
    }
    return Program_instanciatedTypes_value;
  }
  /** @apilevel internal */
  private Set<TypeDecl> instanciatedTypes_compute() {
    ASTNode node = this;
    while (node != null && !(node instanceof Program)) {
      node = node.getParent();
    }
    Program root = (Program) node;
    root.survey_Program_instanciatedTypes();
    Set<TypeDecl> _computedValue = new LinkedHashSet<>();
    if (root.contributorMap_Program_instanciatedTypes.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_Program_instanciatedTypes.get(this)) {
        contributor.contributeTo_Program_instanciatedTypes(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle Program_instanciatedTypes_computed = null;

  /** @apilevel internal */
  protected Set<TypeDecl> Program_instanciatedTypes_value;

  /**
   * @attribute coll
   * @aspect requests
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Requests.jrag:104
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="requests", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Requests.jrag:104")
  public Set<String> allPointsToResults() {
    ASTState state = state();
    if (Program_allPointsToResults_computed == ASTState.NON_CYCLE || Program_allPointsToResults_computed == state().cycle()) {
      return Program_allPointsToResults_value;
    }
    Program_allPointsToResults_value = allPointsToResults_compute();
    if (state().inCircle()) {
      Program_allPointsToResults_computed = state().cycle();
    
    } else {
      Program_allPointsToResults_computed = ASTState.NON_CYCLE;
    
    }
    return Program_allPointsToResults_value;
  }
  /** @apilevel internal */
  private Set<String> allPointsToResults_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    ASTNode root = (ASTNode) node;
    root.survey_Program_allPointsToResults();
    Set<String> _computedValue = new TreeSet<>();
    if (root.contributorMap_Program_allPointsToResults.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_Program_allPointsToResults.get(this)) {
        contributor.contributeTo_Program_allPointsToResults(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle Program_allPointsToResults_computed = null;

  /** @apilevel internal */
  protected Set<String> Program_allPointsToResults_value;

  /**
   * @attribute coll
   * @aspect jastaddbridge
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:4
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="jastaddbridge", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:4")
  public Set<Diagnostic> lsp_diagnostics() {
    ASTState state = state();
    if (Program_lsp_diagnostics_computed == ASTState.NON_CYCLE || Program_lsp_diagnostics_computed == state().cycle()) {
      return Program_lsp_diagnostics_value;
    }
    Program_lsp_diagnostics_value = lsp_diagnostics_compute();
    if (state().inCircle()) {
      Program_lsp_diagnostics_computed = state().cycle();
    
    } else {
      Program_lsp_diagnostics_computed = ASTState.NON_CYCLE;
    
    }
    return Program_lsp_diagnostics_value;
  }
  /** @apilevel internal */
  private Set<Diagnostic> lsp_diagnostics_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    ASTNode root = (ASTNode) node;
    root.survey_Program_lsp_diagnostics();
    Set<Diagnostic> _computedValue = new HashSet<>();
    if (root.contributorMap_Program_lsp_diagnostics.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_Program_lsp_diagnostics.get(this)) {
        contributor.contributeTo_Program_lsp_diagnostics(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle Program_lsp_diagnostics_computed = null;

  /** @apilevel internal */
  protected Set<Diagnostic> Program_lsp_diagnostics_value;

  /**
   * @attribute coll
   * @aspect codeprober
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Codeprober.jrag:25
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="codeprober", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Codeprober.jrag:25")
  public Set<Object> visualiseAllPointsToHover() {
    ASTState state = state();
    if (Program_visualiseAllPointsToHover_computed == ASTState.NON_CYCLE || Program_visualiseAllPointsToHover_computed == state().cycle()) {
      return Program_visualiseAllPointsToHover_value;
    }
    Program_visualiseAllPointsToHover_value = visualiseAllPointsToHover_compute();
    if (state().inCircle()) {
      Program_visualiseAllPointsToHover_computed = state().cycle();
    
    } else {
      Program_visualiseAllPointsToHover_computed = ASTState.NON_CYCLE;
    
    }
    return Program_visualiseAllPointsToHover_value;
  }
  /** @apilevel internal */
  private Set<Object> visualiseAllPointsToHover_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    ASTNode root = (ASTNode) node;
    root.survey_Program_visualiseAllPointsToHover();
    Set<Object> _computedValue = new HashSet<>();
    if (root.contributorMap_Program_visualiseAllPointsToHover.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_Program_visualiseAllPointsToHover.get(this)) {
        contributor.contributeTo_Program_visualiseAllPointsToHover(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle Program_visualiseAllPointsToHover_computed = null;

  /** @apilevel internal */
  protected Set<Object> Program_visualiseAllPointsToHover_value;

  /**
   * @attribute coll
   * @aspect runtimebenchmark
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/RuntimeBenchmark.jrag:27
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="runtimebenchmark", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/RuntimeBenchmark.jrag:27")
  public Set<MethodDecl> methodsOfInterest() {
    ASTState state = state();
    if (Program_methodsOfInterest_computed == ASTState.NON_CYCLE || Program_methodsOfInterest_computed == state().cycle()) {
      return Program_methodsOfInterest_value;
    }
    Program_methodsOfInterest_value = methodsOfInterest_compute();
    if (state().inCircle()) {
      Program_methodsOfInterest_computed = state().cycle();
    
    } else {
      Program_methodsOfInterest_computed = ASTState.NON_CYCLE;
    
    }
    return Program_methodsOfInterest_value;
  }
  /** @apilevel internal */
  private Set<MethodDecl> methodsOfInterest_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    ASTNode root = (ASTNode) node;
    root.survey_Program_methodsOfInterest();
    Set<MethodDecl> _computedValue = new HashSet<>();
    if (root.contributorMap_Program_methodsOfInterest.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_Program_methodsOfInterest.get(this)) {
        contributor.contributeTo_Program_methodsOfInterest(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle Program_methodsOfInterest_computed = null;

  /** @apilevel internal */
  protected Set<MethodDecl> Program_methodsOfInterest_value;

  /**
   * @attribute coll
   * @aspect pointerbench
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:80
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="pointerbench", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:80")
  public Set<Map.Entry<String, String>> benchmarkTests() {
    ASTState state = state();
    if (Program_benchmarkTests_computed == ASTState.NON_CYCLE || Program_benchmarkTests_computed == state().cycle()) {
      return Program_benchmarkTests_value;
    }
    Program_benchmarkTests_value = benchmarkTests_compute();
    if (state().inCircle()) {
      Program_benchmarkTests_computed = state().cycle();
    
    } else {
      Program_benchmarkTests_computed = ASTState.NON_CYCLE;
    
    }
    return Program_benchmarkTests_value;
  }
  /** @apilevel internal */
  private Set<Map.Entry<String, String>> benchmarkTests_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    ASTNode root = (ASTNode) node;
    root.survey_Program_benchmarkTests();
    Set<Map.Entry<String, String>> _computedValue = new HashSet<>();
    if (root.contributorMap_Program_benchmarkTests.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_Program_benchmarkTests.get(this)) {
        contributor.contributeTo_Program_benchmarkTests(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle Program_benchmarkTests_computed = null;

  /** @apilevel internal */
  protected Set<Map.Entry<String, String>> Program_benchmarkTests_value;

  /**
   * @attribute coll
   * @aspect pointerbench
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:96
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="pointerbench", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:96")
  public Set<Pointer> getTargetDeclaration() {
    ASTState state = state();
    if (Program_getTargetDeclaration_computed == ASTState.NON_CYCLE || Program_getTargetDeclaration_computed == state().cycle()) {
      return Program_getTargetDeclaration_value;
    }
    Program_getTargetDeclaration_value = getTargetDeclaration_compute();
    if (state().inCircle()) {
      Program_getTargetDeclaration_computed = state().cycle();
    
    } else {
      Program_getTargetDeclaration_computed = ASTState.NON_CYCLE;
    
    }
    return Program_getTargetDeclaration_value;
  }
  /** @apilevel internal */
  private Set<Pointer> getTargetDeclaration_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    ASTNode root = (ASTNode) node;
    root.survey_Program_getTargetDeclaration();
    Set<Pointer> _computedValue = new HashSet<>();
    if (root.contributorMap_Program_getTargetDeclaration.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_Program_getTargetDeclaration.get(this)) {
        contributor.contributeTo_Program_getTargetDeclaration(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle Program_getTargetDeclaration_computed = null;

  /** @apilevel internal */
  protected Set<Pointer> Program_getTargetDeclaration_value;


}
