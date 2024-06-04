package org.extendj.actions;

import org.extendj.Bfpa;
import org.extendj.Options;
import org.extendj.ast.Program;

public class ParseBenchmarkAction implements Action {
  private Options options;

  public ParseBenchmarkAction(Options options) {
    this.options = options;
  }

  @Override
  public int execute() {
    long startTime = 0;
    long totalTime = 0;
    long iterationTime = 0;
    int iterations = options.getIterations();
    if (options.getCsv() && options.getCsvHeader()) {
      System.out.println(
          "# runid: The string provided by the command line arg -runid, it will be the same value for this invocation of the analysis\n" +
          "# iteration: The iteration for the run (determined by -niter)\n" + 
          "# time: Time in nanoseconds to parse the program"
          );
      System.out.println("runid,iteration,time");
    }
    for(int i = 0; i < iterations; i++){
      startTime = System.nanoTime();
      int exitCode = new Bfpa(options).run();
      iterationTime = System.nanoTime() - startTime;
      System.out.println(options.getRunid() + "," + i + "," + iterationTime);
      totalTime += iterationTime;
      if (exitCode != 0) {
        return exitCode;
      }
    }
    totalTime = totalTime / iterations;
    System.err.println(String.format("Avg compile time (s): %.6f", totalTime / 1_000_000_000.0));
    System.err.println(String.format("Last iteration (s): %.6f", iterationTime / 1_000_000_000.0));
    return 0;
  }

}
