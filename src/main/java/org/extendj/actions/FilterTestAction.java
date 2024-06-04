package org.extendj.actions;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.extendj.Bfpa;
import org.extendj.Options;
import org.extendj.ast.InvocationTarget;
import org.extendj.ast.AllocationSite;
import org.extendj.ast.MethodDecl;
import org.extendj.ast.Pointer;
import org.extendj.ast.Program;
import org.extendj.ast.VarAccess;
import org.extendj.utils.Utils;

/**
 * Tests the filter by checking that the filtered and unfiltered constraints result in the same
 * points-to set for every pointer
 */
public class FilterTestAction implements Action {
  private Options options;

  public FilterTestAction(Options options) {
    this.options = options;
  }

  @Override
  public int execute() {
    long startTime = 0;
    long totalTime = 0;
    long latestIter = 0;
    int iterations = options.getIterations();

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
          "# iteration: The iteration for the run (determined by -niter)\n" + 
          "# sample_count: The n provided by -pointerBenchmark <n>\n" +
          "# distance: The distance provided by -distance\n" +
          "# time: Time in nanoseconds to compute the points-to results for n random methods" +
          "# alloc_count: The amount of allocations the analysis found for the n random methods\n" +
          "# type_count: The amount of unique types the analysis found for the n random methods\n" +
          "# seed: The seed used to generate the samples"
          );
      System.out.println("runid,iteration,sample_count,distance,time,alloc_count,type_count,seed");
    }

    for(int i = 0; i < iterations; i++){
      program.flushTreeCache();
      String res = "";
      long alloc_count = 0;
      long type_count = 0;
      startTime = System.nanoTime();
      int misses = 0;
      int total = 0;
      for (InvocationTarget target : toTest) {
          for (Pointer pointer : target.pointers()) {
              if (!pointer.isPointer() || pointer instanceof VarAccess) continue;
              total++;
              if (total % 100 == 0) System.err.println(total);
              Set<AllocationSite> filtered = pointer.pointsToSet().stream().filter(a -> !a.isNull()).collect(Collectors.toSet());
              Set<AllocationSite> unfiltered = pointer.unfilteredPointsToSet().stream().filter(a -> !a.isNull()).collect(Collectors.toSet());
              if (!filtered.equals(unfiltered)) {
                  misses++;
                  System.err.println("Miss in " + pointer + ", " + pointer.fileName());
                  String errorMessage = String.format("Points-to sets do not match for pointer %s in method %s in %s. " +  
                    "Filtered: %s Unfiltered: %s", pointer, target, pointer.fileName(), filtered, unfiltered);
                  errorMessage = errorMessage + "\nMissed:\n" + 
                      unfiltered
                      .stream()
                      .filter(uf -> !filtered.contains(uf))
                      .map(uf -> uf.info())
                      .collect(Collectors.joining("\n"));
                  System.err.println(errorMessage);
                  //throw new Error(errorMessage);
              }
          }
      }
      System.err.println("Total misses: " + misses + "/" + total);
      latestIter = System.nanoTime() - startTime;
      if (options.getCsv()) {
        System.out
            .println(Stream
                .of(options.getRunid(), i, samples, options.getDistance(), latestIter,
                    alloc_count, type_count, seed)
                .map(Object::toString).collect(Collectors.joining(",")));
      }

      totalTime += latestIter;
      System.err.println("Found " + samples + " samples in " + latestIter / 1_000_000_000.0 + "s with the distance " + options.getDistance());
    }
    return 0;
  }

}
