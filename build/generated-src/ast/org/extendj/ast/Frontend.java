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
 * Base class for ExtendJ compilers.
 * 
 * <p>This class can be used to build a new ExtendJ-based compiler.
 * In some cases it is better to roll your own compiler class, but this
 * is often a good starting point.
 * 
 * <p>Compilation should be handled by overriding the processCompilationUnit method.
 * If you want to use the default error checking implementation, just override
 * processNoErrors instead.
 * @ast class
 * @aspect FrontendMain
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:76
 */
abstract public class Frontend extends java.lang.Object {
  /**
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:77
   */
  
    protected Program program;
  /** Compile success. 
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:80
   */
  

    /** Compile success. */
    public static final int EXIT_SUCCESS = 0;
  /** Lexical/semantic error. 
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:83
   */
  

    /** Lexical/semantic error. */
    public static final int EXIT_ERROR = 1;
  /** Command-line configuration error. 
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:86
   */
  

    /** Command-line configuration error. */
    public static final int EXIT_CONFIG_ERROR = 2;
  /** The compiler terminated by system error. 
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:89
   */
  

    /** The compiler terminated by system error. */
    public static final int EXIT_SYSTEM_ERROR = 3;
  /** The compiler terminated abnormally. 
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:92
   */
  

    /** The compiler terminated abnormally. */
    public static final int EXIT_UNHANDLED_ERROR = 4;
  /**
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:94
   */
  

    private final String name;
  /**
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:96
   */
  

    private final String version;
  /** Root node for AST debugging. 
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:99
   */
  

    /** Root node for AST debugging. */
    public static Program DrAST_root_node;
  /**
   * Initializes the AST root (Program).
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:104
   */
  

    /**
     * Initializes the AST root (Program).
     */
    protected Frontend() {
      this("Unknown", "0");
    }
  /**
   * Initializes the AST and sets compiler name and version.
   * 
   * @param name compiler name.
   * @param version compiler version.
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:114
   */
  

    /**
     * Initializes the AST and sets compiler name and version.
     *
     * @param name compiler name.
     * @param version compiler version.
     */
    protected Frontend(String name, String version) {
      this.name = name;
      this.version = version;
      program = new Program();
      DrAST_root_node = program;
      program.state().reset();
    }
  /**
   * Run the compiler with the supplied arguments.
   * 
   * <p>Argument processing is done using the processArgs method. Override that
   * if you want to change the argument handling. Compilation options are stored
   * in {@code program.options()}.
   * 
   * <p>In normal operation this method is used to compile a set of source files.
   * If the -help or -version options are provided then comilation is skipped.
   * 
   * <p>The return value indicates whether there were any errors during compilation.
   * 
   * @return 0 on success, 1 on error, 2 on configuration error, 3 on system
   * error, 4 on unhandled error
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:137
   */
  

    /**
     * Run the compiler with the supplied arguments.
     *
     * <p>Argument processing is done using the processArgs method. Override that
     * if you want to change the argument handling. Compilation options are stored
     * in {@code program.options()}.
     *
     * <p>In normal operation this method is used to compile a set of source files.
     * If the -help or -version options are provided then comilation is skipped.
     *
     * <p>The return value indicates whether there were any errors during compilation.
     *
     * @return 0 on success, 1 on error, 2 on configuration error, 3 on system
     * error, 4 on unhandled error
     */
    public int run(String[] args, BytecodeReader reader, JavaParser parser) {

      program.resetStatistics();
      program.initBytecodeReader(reader);
      program.initJavaParser(parser);

      initOptions();
      int argResult = processArgs(args);
      if (argResult != 0) {
        return argResult;
      }

      Collection<String> files = program.options().files();

      if (program.options().hasOption("-version")) {
        printVersion();
        return EXIT_SUCCESS;
      }

      if (program.options().hasOption("-help") || files.isEmpty()) {
        printUsage();
        return EXIT_SUCCESS;
      }

      Collection<CompilationUnit> work = new LinkedList<CompilationUnit>();

      try {
        for (String file : files) {
          program.addSourceFile(file);
        }

        TypeDecl object = program.lookupType("java.lang", "Object");
        if (object.isUnknown()) {
          // If we try to continue without java.lang.Object, we'll just get a stack overflow
          // in member lookups because the unknown (Object) type would be treated as circular.
          System.err.println("Error: java.lang.Object is missing."
              + " The Java standard library was not found.");
          return EXIT_UNHANDLED_ERROR;
        }

        int compileResult = EXIT_SUCCESS;

        // Process source compilation units.
        Iterator<CompilationUnit> iter = program.compilationUnitIterator();
        while (iter.hasNext()) {
          CompilationUnit unit = iter.next();
          work.add(unit);
          int result = processCompilationUnit(unit);
          switch (result) {
            case EXIT_SUCCESS:
              break;
            case EXIT_UNHANDLED_ERROR:
              return result;
            default:
              compileResult = result;
          }
        }

        // Process library compilation units.
        Iterator<CompilationUnit> libraryIterator = program.libraryCompilationUnitIterator();
        while (libraryIterator.hasNext()) {
          CompilationUnit unit = libraryIterator.next();
          work.add(unit);
          int result = processCompilationUnit(unit);
          switch (result) {
            case EXIT_SUCCESS:
              break;
            case EXIT_UNHANDLED_ERROR:
              return result;
            default:
              compileResult = result;
          }
        }

        if (compileResult != EXIT_SUCCESS) {
          return compileResult;
        }

        for (CompilationUnit unit : work) {
          if (unit != null && unit.fromSource()) {
            long start = System.nanoTime();
            processNoErrors(unit);
            program.codeGenTime += System.nanoTime() - start;
          }
        }

      } catch (AbstractClassfileParser.ClassfileFormatError e) {
        System.err.println(e.getMessage());
        return EXIT_UNHANDLED_ERROR;
      } catch (Throwable t) {
        System.err.println("Fatal exception:");
        t.printStackTrace(System.err);
        return EXIT_UNHANDLED_ERROR;
      } finally {
        if (program.options().hasOption("-profile")) {
          program.printStatistics(System.out);
        }
      }
      return EXIT_SUCCESS;
    }
  /**
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:240
   */
  

    // This is a commonly used singleton value, so we store it here to avoid a method call.
    // NOTE(joqvist): this probably doesn't make a noticeable difference.
    private Collection<Problem> EMPTY_PROBLEM_LIST = Collections.emptyList();
  /**
   * Processes from-source compilation units by error-checking them.
   * This method only report semantic errors and warnings.
   * 
   * @return zero on success, non-zero on error
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:248
   */
  

    /**
     * Processes from-source compilation units by error-checking them.
     * This method only report semantic errors and warnings.
     *
     * @return zero on success, non-zero on error
     */
    protected int processCompilationUnit(CompilationUnit unit) throws Error {
      if (unit != null && unit.fromSource()) {
        try {
          Collection<Problem> errors = unit.parseErrors();
          Collection<Problem> warnings = EMPTY_PROBLEM_LIST;
          // Compute static semantic errors when there are no parse errors
          // or the recover from parse errors option is specified.
          if (errors.isEmpty() || program.options().hasOption("-recover")) {
            long start = System.nanoTime();
            errors = unit.errors();
            warnings = unit.warnings();
            program.errorCheckTime += System.nanoTime() - start;
          }
          if (!errors.isEmpty()) {
            processErrors(errors, unit);
            return EXIT_ERROR;
          } else {
            if (!warnings.isEmpty() && !program.options().hasOption("-nowarn")) {
              processWarnings(warnings, unit);
            }
          }
        } catch (Error e) {
          System.err.println("Encountered error while processing " + unit.pathName());
          throw e;
        }
      }
      return EXIT_SUCCESS;
    }
  /**
   * Initialize the command-line options.
   * Override this method to add your own command-line options.
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:281
   */
  

    /**
     * Initialize the command-line options.
     * Override this method to add your own command-line options.
     */
    protected void initOptions() {
      Options options = program.options();
      options.initOptions();
      options.addKeyOption("-version");
      options.addKeyOption("-print");
      options.addKeyOption("-g");
      options.addKeyOption("-g:none");
      options.addKeyOption("-g:lines,vars,source");
      options.addKeyOption("-nowarn");
      options.addKeyOption("-verbose");
      options.addKeyOption("-deprecation");
      options.addKeyValueOption("-classpath");
      options.addKeyValueOption("-cp");
      options.addKeyValueOption("-sourcepath");
      options.addKeyValueOption("-bootclasspath");
      options.addKeyValueOption("-extdirs");
      options.addKeyValueOption("-d");
      options.addKeyValueOption("-encoding");
      options.addKeyValueOption("-source");
      options.addKeyValueOption("-target");
      options.addKeyOption("-help");
      options.addKeyOption("-O");
      options.addKeyOption("-J-Xmx128M");
      options.addKeyOption("-recover");
      options.addKeyOption("-XprettyPrint");
      options.addKeyOption("-XdumpTree");

      // Non-javac options.
      options.addKeyOption("-profile"); // Output profiling information.
      options.addKeyOption("-debug"); // Extra debug checks and information.

      // These unused nonstandard options with arguments are here added so that
      // their arguments are discarded when parsing command-line options.
      options.addKeyValueOption("-Xmaxerrs");
      options.addKeyValueOption("-Xmaxwarns");
      options.addKeyValueOption("-Xstdout");
    }
  /**
   * Configure the compiler with command-line arguments.
   * 
   * @return 0 if there were no configuration errors
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:324
   */
  

    /**
     * Configure the compiler with command-line arguments.
     *
     * @return 0 if there were no configuration errors
     */
    protected int processArgs(String[] args) {
      boolean error = false;
      try {
        program.options().addOptions(args);
        Collection<String> files = program.options().files();
        for (String file : files) {
          if (!new File(file).isFile()) {
            System.err.println("Error: neither a valid option nor a filename: " + file);
            error = true;
          }
        }
      } catch (Options.CommandLineError e) {
        System.err.println(e.getMessage());
        error = true;
      }
      return error ? EXIT_CONFIG_ERROR : EXIT_SUCCESS;
    }
  /**
   * Print errors for a compilation unit.
   * 
   * @param errors collection of compile problems
   * @param unit affected compilation unit
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:348
   */
  

    /**
     * Print errors for a compilation unit.
     *
     * @param errors collection of compile problems
     * @param unit affected compilation unit
     */
    protected void processErrors(Collection<Problem> errors, CompilationUnit unit) {
      for (Problem error : errors) {
        System.err.println(error);
      }
    }
  /**
   * Print the warnings for a compilation unit.
   * 
   * @param warnings collection of warnings
   * @param unit affected compilation unit
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:360
   */
  

    /**
     * Print the warnings for a compilation unit.
     *
     * @param warnings collection of warnings
     * @param unit affected compilation unit
     */
    protected void processWarnings(Collection<Problem> warnings, CompilationUnit unit) {
      for (Problem warning : warnings) {
        System.err.println(warning);
      }
    }
  /**
   * Called for each from-source compilation unit with no errors.
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:369
   */
  

    /**
     * Called for each from-source compilation unit with no errors.
     */
    protected void processNoErrors(CompilationUnit unit) {
    }
  /**
   * Print the command-line usage help to stdout.
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:375
   */
  

    /**
     * Print the command-line usage help to stdout.
     */
    protected void printUsage() {
      System.out.println(name() + " " + version());
      System.out.println("\n"
          + "Usage: java " + name() + " <options> <source files>\n"
          + "  -verbose                  Output messages about what the compiler is doing\n"
          + "  -classpath <path>         Specify where to find user class files\n"
          + "  -sourcepath <path>        Specify where to find input source files\n"
          + "  -bootclasspath <path>     Override location of bootstrap class files\n"
          + "  -extdirs <dirs>           Override location of installed extensions\n"
          + "  -d <directory>            Specify where to place generated class files\n"
          + "  -nowarn                   Disable warning messages\n"
          + "  -help                     Print a synopsis of standard options\n"
          + "  -version                  Print version information");
    }
  /**
   * Echo the version to sysout.
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:393
   */
  

    /**
     * Echo the version to sysout.
     */
    protected void printVersion() {
      System.out.println(name() + " " + version());
    }
  /**
   * @return the name of the compiler.
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:400
   */
  

    /**
     * @return the name of the compiler.
     */
    protected String name() {
      return name;
    }
  /**
   * @return the version of the compiler.
   * @aspect FrontendMain
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/FrontendMain.jrag:407
   */
  

    /**
     * @return the version of the compiler.
     */
    protected String version() {
      return version;
    }

}
