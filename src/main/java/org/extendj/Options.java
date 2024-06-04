package org.extendj;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.extendj.actions.*;

public class Options {
  private String entryPointPackage = "";
  private String entryPointMethod = "";

  private String[] jCheckerArgs;
  private int benchmarkN = 0;
  private int iterations = 1;
  private int distance = -1;
  private Long seed = null;
  private String id = "";
  private String requestSignature = "";
  private boolean csv = false;
  private boolean csvHeader = false;
  private String outputFile = "";
  private Action action;
  
  /*
   * Constructor used for the main program
   * @param args command-line arguments
   */
  public Options(String[] args) throws FileNotFoundException {
    parseArgs(args);
  }

  /*
   * Parse the command-line arguments
   * @param args command-line arguments
   */
  private void parseArgs(String[] args) throws FileNotFoundException {
    if (args.length < 1) {
      System.err.println("You must specify a source file on the command line!");
      printOptionsUsage();
    }

    ArrayList<String> FEOptions = new ArrayList<>();
    Set<String> files = new HashSet<>();

    for (int i = 0; i < args.length; ++i) {
      String opt = args[i];
      if (opt.contains(".java")) {
        files.add(args[i]);
        continue;
      }
      switch (opt) {
        case "-printSignatures":
          setAction(new PrintSignaturesAction(this));
          break;
        case "-pointerRequest":
          setAction(new PointstoRequestAction(this));
          requestSignature = args[++i];
          break;
        case "-help":
          printOptionsUsage(0);
          break;
        case "-entryPoint":
          setEntryPointPackage(args[++i]);
          setEntryPointMethod(args[++i]);
          break;
        case "-classpath":
          FEOptions.add("-classpath");
          FEOptions.add(args[++i]);
          break;
        case "-parseBenchmark":
          setAction(new ParseBenchmarkAction(this));
          break;
        case "-pointerBenchmark":
          setAction(new PointerBenchmarkAction(this));
          setBenchmarkN(args[++i]);
          break;
        case "-filterTest":
          setAction(new FilterTestAction(this));
          setBenchmarkN(args[++i]);
          break;
        case "-activeSetBenchmark":
          setAction(new ActiveSetBenchmark(this));
          setBenchmarkN(args[++i]);
          break;
        case "-allMethods":
          setAction(new AllMethodsAction(this));
          setOutputFile(args[++i]);
          break;
        case "-wholeProgramAnalysis":
          setAction(new WholeProgramAnalysisAction(this));
          setOutputFile(args[++i]);
          break;
        case "-niter":
          setIterations(args[++i]);
          break;
        case "-runid":
          this.id = args[++i];
          break;
        case "-distance":
          setDistance(args[++i]);
          break;
        case "-seed":
          setSeed(args[++i]);
          break;
        case "-csv":
          csv = true;
          break;
        case "-csvHeader":
          csvHeader = true;
          break;
        case "-src":
          String srcDir = args[++i];
          try {
            Files.walk(Paths.get(srcDir))
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".java"))
                .forEach(p -> {
                  if(!FEOptions.contains(p.toString())) {
                    files.add(p.toString());
                  }
                });
          } catch (IOException e) {
            System.err.println("Error reading source directory: " + e.getMessage());
          }
          break;
        default:
          System.err.println("Unrecognized option: " + opt);
          printOptionsUsage();
          break;
      }
    }
    if (files.isEmpty()) {
      System.err.println("You must provide at least one java source file!");
      printOptionsUsage();
    }


    FEOptions.addAll(files);
    FEOptions.add("-nowarn");
    this.jCheckerArgs = FEOptions.toArray(new String[0]);
  }

  private void printOptionsUsage(int exitcode) {
    System.out.println(
        "\nUsage: java -jar bfpa.jar [options] <source_files>\n" + 
        "Options:\n" + 
        "  -entryPoint <package> <method>     Specify the entry point package and method\n" + 
        "  -classpath <path>                  Specify the classpath\n" +
        "  -help                              Print the options for the program\n" +
        "  -printSignatures                   Print all method signatures for the program\n" +
        "  -pointerRequest <signature>        Print out points to information for the provided method signature\n" + 
        "  -compileBenchmark                  Run benchmark for compiling the program\n" + 
        "  -pointerBenchmark <n>              Run benchmark for getting all points-to results for n random methods\n" + 
        "  -wholeProgramAnalysis <outputfile> Run a whole program analysis and output types of parameter pointers.\n" + 
        "  -allMethods <ouputfile>            Run analysis on methods with parameter that has subtypes outputing the result to outputfile in json format.\n" + 
        "  -filterTest <n>                    Run the analysis on n methods and check that the default filtered results is the same as without filtering.\n" + 
        "  -activeSetBenchmark <n>            Generate statistics for the active set used to calculate the result. Measure on n random methods\n" + 
        "  -niter <number>                    Specify how many iterations to run the benchmark\n" +
        "  -distance <number>                 Specify what distance to use for the analysis (default -1 is all reachable methods)\n" +
        "  -runid <name>                      Specify a name of the run used in the csv output\n" +
        "  -seed <long>                       Specify the seed to use when taking n random samples\n" +
        "  -src <path>                        Specify source directory with .java files\n" +
        "  -csv                               Write output of analysis in csv format to stdout\n" +
        "  -csvHeader                         Write the header for the csv output of the analysis (default=false)"
    );

    System.exit(exitcode);
  }

  private void printOptionsUsage() {
    printOptionsUsage(1);
  }

  public void setEntryPointPackage(String entryPointPackage) {
    this.entryPointPackage = entryPointPackage;
  }

  public void setEntryPointMethod(String entryPointMethod) {
    this.entryPointMethod = entryPointMethod;
  }

  public void setJCheckerArgs(String[] jCheckerArgs) {
    this.jCheckerArgs = jCheckerArgs;
  }

  public String getEntryPointPackage() {
    return entryPointPackage;
  }

  public String getEntryPointMethod() {
    return entryPointMethod;
  }

  public String[] getJCheckerArgs() {
    return jCheckerArgs;
  }

  private void setIterations(String str) {
    try {
      this.iterations = Integer.parseInt(str);
    } catch (NumberFormatException e) {
      System.err.println("Expected the number of iterations but got " + str);
      printOptionsUsage();
    }
  }

  private void setBenchmarkN(String str) {
    try {
      this.benchmarkN = Integer.parseInt(str);
    } catch (NumberFormatException e) {
      System.err.println("Expected a number but got " + str);
      printOptionsUsage();
    }
  }

  private void setDistance(String str) {
    try {
      this.distance = Integer.parseInt(str);
    } catch (NumberFormatException e) {
      System.err.println("Expected a number but got " + str);
      printOptionsUsage();
    }
  }

  public int getDistance() {
    return distance;
  }

  public int getBenchmarkN() {
    return benchmarkN;
  }

  public int getIterations() {
    return iterations;
  }

  public String getRequestSignature() {
    return requestSignature;
  }

  public boolean hasRequestSignature() {
    return !requestSignature.isEmpty();
  }

  public String getRunid() {
    return id;
  }

  private void setSeed(String str) {
    try {
      this.seed = Long.parseLong(str);
    } catch (NumberFormatException e) {
      System.err.println("Expected a number but got " + str);
      printOptionsUsage();
    }
  }

  public Long getSeed() {
    return seed;
  }

  public boolean getCsv() {
    return csv;
  }

  public boolean getCsvHeader() {
    return csvHeader;
  }

  public void setOutputFile(String outputfile) {
    this.outputFile = outputfile;
  }

  public String getOutputFile() {
    return outputFile;
  }

  private void setAction(Action action) {
    if (this.action != null) {
      System.err.println("Multiple actions detected, please specify only one.");
      printOptionsUsage();
    }

    this.action = action;
  }

  public Action getAction() {
    if (this.action == null) {
      this.action = new RunAction(this);
    }

    return action;
  }
  
}
