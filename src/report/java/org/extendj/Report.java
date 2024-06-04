package org.extendj;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.extendj.ast.Program;
import org.extendj.testutils.PBUtils;

public class Report {
  public static void main(String[] args) throws IOException {
    new Report().run(args);
  }

  public void run(String[] args) throws IOException {
    BenchmarkResult res = new BenchmarkResult();
    for (String file : PBUtils.getPointerBenchTestFiles(4).collect(Collectors.toList())) {
      BenchmarkResult fileRes = processFile(file);
      //System.out.println(file.substring(17) + " -> " + fileRes.toString());
      res.add(fileRes);
    }
    System.out.println("Result: " + res.toString());
  }

  public BenchmarkResult processFile(String entryfile) throws IOException {
    List<String> lib = PBUtils.getPointerBenchLib();

    Program program = PBUtils.compileProgram(entryfile, lib);

    Set<Integer> idExpected = PBUtils.getAllocIDExpected(program);
    Set<Integer> idActuals = PBUtils.getAllocIDActuals(program);

    return new BenchmarkResult(idExpected, idActuals);
  }

  class BenchmarkResult {
    int truePositives = 0;
    int falsePositives = 0;
    int falseNegatives = 0;

    public BenchmarkResult() {}
    public BenchmarkResult(Set<Integer> idExpected, Set<Integer> idActuals) {
      for (Integer actual : idActuals) {
        if (idExpected.contains(actual)) {
          // True positive
          this.truePositives += 1;
        } else {
          // False positive
          this.falsePositives += 1;
        }
      }
      
      for (Integer expected : idExpected) {
        if (!idActuals.contains(expected)) {
          // False negative
          this.falseNegatives += 1;
        } 
      }

    }

    public double precision() {
        if (truePositives + falsePositives == 0) return 0;
        return (double) truePositives / (truePositives + falsePositives);
    }

    public double recall() {
        if (truePositives + falseNegatives == 0) return 0;
        return (double) truePositives / (truePositives + falseNegatives);
    }
    public void add(BenchmarkResult other) {
      this.truePositives += other.truePositives;
      this.falseNegatives += other.falseNegatives;
      this.falsePositives += other.falsePositives;
    }

    @Override
    public String toString() {
      return String.format("TP: %d, FP: %d, FN: %d, Precision: %.2f, Recall: %.2f",
                truePositives, falsePositives, falseNegatives, precision(), recall());
    }
  }
}
