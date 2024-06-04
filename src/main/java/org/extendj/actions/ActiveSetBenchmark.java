package org.extendj.actions;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.extendj.Bfpa;
import org.extendj.Options;
import org.extendj.ast.InvocationTarget;
import org.extendj.ast.Program;
import org.extendj.utils.Utils;

/**
 * Collect statistics about the active set of a method.
 */
public class ActiveSetBenchmark implements Action {
  private Options options;

  public ActiveSetBenchmark(Options options) {
    this.options = options;
  }

  @Override
  public int execute() {
    Bfpa compiler = new Bfpa(options);
    compiler.run();
    long seed = options.getSeed() == null ? System.currentTimeMillis() : options.getSeed();
    Random random = new Random(seed);
    Program program = compiler.getEntryPoint();
    List<InvocationTarget> methods = Utils.getMethods(program);
    int n = options.getBenchmarkN();
    int samples = n > methods.size() ? methods.size() : n;
    Collections.shuffle(methods, random);
    List<InvocationTarget> toTest = methods.subList(0, samples);
    System.err.println("Running with the seed " + seed);
    if (options.getCsv() && options.getCsvHeader()) {
      System.out.println(
          "# runid: The string provided by the command line arg -runid, it will be the same value for this invocation of the analysis\n" +
          "# seed: The seed used for the run (determined by -seed, or random if not provided)\n" +
          "# sample_count: The n provided by -activeSetBenchmark <n>\n" +
          "# distance: The distance provided by -distance\n" +
          "# method_name: The name of the method the active set is computed from\n" +
          "# methods: The amount of methods included in the active set\n" +
          "# points_to_edges: Amount of points to edges included in the active set before solving\n" +
          "# inclusion_edges: Amount of inclusion edges included in the active set before solving\n" +
          "# time: Time in nanoseconds to compute the active set and collect the constraints for the method\n"
          );
      System.out.println("runid,seed,sample_count,distance,method_name,methods,points_to_edges,inclusion_edges,time");
    }

    program.flushTreeCache();
    String method_name = "";
    int total_method_count = 0;
    int method_count = 0;
    int pointsToConstraints = 0;
    int inclusionConstraints = 0;
    int totalInclusionConstraints = 0;
    int totalPointsToConstrainst = 0;

    long startTime = 0;
    long totalTime = 0;
    long iterationTime = 0;

    for (InvocationTarget target : toTest) {
      program.flushTreeCache();
      method_name = target.targetSignature().replace(",", " "); // Do not use commas to avoid issues with csv

      startTime = System.nanoTime();
      method_count = target.selectedMethods().size();
      total_method_count += method_count;

      pointsToConstraints = target.activePointsToEdges().size();
      inclusionConstraints = target.activeInclusionEdges().size();

      iterationTime = System.nanoTime() - startTime;

      totalPointsToConstrainst += pointsToConstraints;
      totalInclusionConstraints += inclusionConstraints;
      totalTime += iterationTime;
              
      if (options.getCsv()) {
        System.out
          .println(Stream
              .of(options.getRunid(), seed, samples, options.getDistance(), method_name,
                method_count, pointsToConstraints, inclusionConstraints, iterationTime)
              .map(Object::toString).collect(Collectors.joining(",")));
      }
    }

    System.err.println("Distance: " + options.getDistance());
    System.err.println("runid: " + options.getRunid());
    System.err.println(
        "Avg methods in active set: " + (total_method_count / samples));
    System.err.println("Avg initial constraints in active set: "
        + ((totalInclusionConstraints + totalPointsToConstrainst) / samples));
    System.err.println(String.format("Execution time (s): %.6f", totalTime / 1_000_000_000.0));

    return 0;
  }

}
