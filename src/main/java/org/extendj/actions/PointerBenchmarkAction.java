package org.extendj.actions;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.extendj.Bfpa;
import org.extendj.Options;
import org.extendj.ast.InvocationTarget;
import org.extendj.ast.PointsToEdge;
import org.extendj.ast.InclusionEdge;
import org.extendj.ast.MethodDecl;
import org.extendj.ast.Program;
import org.extendj.utils.Utils;

/**
 * Measure the time to preform n random points-to results for the program.
 */
public class PointerBenchmarkAction implements Action {
  private Options options;

  public PointerBenchmarkAction(Options options) {
    this.options = options;
  }

  @Override
  public int execute() {
    long startTimestamp = 0;
    long endTimestamp = 0;
    long constraintsCollectedTimestamp = 0;
    long latestIter = 0;
    int iterations = options.getIterations();

    Bfpa compiler = new Bfpa(options);
    compiler.run();
    long seed = options.getSeed() == null ? System.currentTimeMillis() : options.getSeed();
    Random random = new Random(seed);
    Program program = compiler.getEntryPoint();
    List<InvocationTarget> methods = Utils.getMethods(program);
    int totalMethods = methods.size();

    int n = options.getBenchmarkN();
    int samples = n > methods.size() ? methods.size() : n;
    Collections.shuffle(methods, random);
    List<InvocationTarget> toTest = methods.subList(0, samples);
    System.err.println("Running with the seed " + seed);
    if (options.getCsv() && options.getCsvHeader()) {
      System.out.println(
          "# runid: The string provided by the command line arg -runid, it will be the same value for this invocation of the analysis\n" +
          "# seed: The seed used for the run (determined by -seed, or random if not provided)\n" +
          "# iteration: The iteration for the run (determined by -niter)\n" + 
          "# sample_count: The n provided by -pointerBenchmark <n>\n" +
          "# distance: The distance provided by -distance\n" +
          "# time: Time in nanoseconds to compute the points-to results for the n random methods" +
          "# alloc_count: The amount of allocations the analysis found for the n random methods\n" +
          "# type_count: The amount of unique types the analysis found for the n random methods\n" +
          "# seed: The seed used to generate the samples\n" + 
          "# pointer_count: total pointers found in the n random methods"
          );
      System.out.println("runid,iteration,sample_count,distance,time,alloc_count,type_count,memory_usage,seed,pointer_count");
    }

    long totalTime = 0;

    for(int i = 0; i < iterations; i++){
      long alloc_count = 0;
      long type_count = 0;
      long pointer_count = 0;
      totalTime = 0;
      long collectTime = 0;
      long solveTime = 0;

      int targetIndex = 1;
      startTimestamp = System.nanoTime();

      for (InvocationTarget target : toTest) {
        program.flushTreeCache();
        System.err.print("\u001b[2K"); // Clear current line
        System.err.print("\u001b[0G"); // Move cursor to beginning of line
        System.err.flush();
        System.err.print("Iteration: " + (i+1) + "/" + iterations + ", Method: " + targetIndex + "/" + toTest.size() + ", Distance: " + options.getDistance() + ", " + target.targetSignature());
        try {

          java.util.Set<PointsToEdge> pte = target.activePointsToEdges();
          java.util.Set<InclusionEdge> ie = target.activeInclusionEdges();

          // These methods will trigger InvocationTarget.pointsToSet(), which is lazy.
          alloc_count += target.pointsToResultCount();
          type_count += target.uniqueTypes();
          pointer_count += target.pointers().size();
        } catch (Throwable e) {
          System.err.println("Error when looking up points-to for: " + target.targetSignature());
          System.err.println("Distance: " + options.getDistance());
          e.printStackTrace();
          System.exit(1);
        }

        targetIndex++;
      }


      endTimestamp = System.nanoTime();

      latestIter = endTimestamp - startTimestamp;
      collectTime += constraintsCollectedTimestamp - startTimestamp;
      solveTime += endTimestamp - constraintsCollectedTimestamp;
      totalTime += latestIter;
      if (options.getCsv()) {
        System.out
            .println(Stream
                .of(options.getRunid(), i, toTest.size(), options.getDistance(), latestIter, 
                    alloc_count, type_count, seed, pointer_count)
                .map(Object::toString).collect(Collectors.joining(",")));
      }

    }

    System.err.print("\u001b[2K"); // Clear current line
    System.err.print("\u001b[0G"); // Move cursor to beginning of line

    System.err.println("Pointer Analysis Benchmark (" + samples + " random samples, distance " + options.getDistance() + "):");
    System.err.println(String.format("Avg time to complete one method (s): %.6f", totalTime / toTest.size() / 1_000_000_000.0));
    System.err.println(String.format("Last iteration completed the samples (s): %.6f", latestIter / 1_000_000_000.0));

    return 0;
  }

}
