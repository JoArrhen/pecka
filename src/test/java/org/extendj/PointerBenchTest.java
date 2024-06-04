package org.extendj;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.extendj.ast.DeclarationSite;
import org.extendj.ast.Program;
import org.extendj.testutils.PBUtils;
import org.extendj.testutils.PointerBenchTruth;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class PointerBenchTest {

  @TestFactory
  Stream<DynamicTest> runPointerBenchTests() throws IOException {
    return PBUtils.getPointerBenchTestFiles(4).map(p -> DynamicTest.dynamicTest(
          "PointerBenchtest for " + p,
          () -> runPointerBenchTest(p, PBUtils.getPointerBenchLib())));
  }

  @Disabled
  @TestFactory
  Stream<DynamicTest> runAttributeExtractionTests() throws IOException {
    return PBUtils.getPointerBenchTestFiles(4).map(p -> DynamicTest.dynamicTest(
          "Check attributes extraction for " + p.toString(),
          () -> runAttributeExtractionTest(p.toString(), PBUtils.getPointerBenchLib())));
  }

  @TestFactory
  Stream<DynamicTest> runProgramTests() throws IOException {
    List<String> files = Files.walk(Paths.get("src/test/resources/exampleprograms"))
                          .filter(Files::isRegularFile)
                          .map(p -> p.toString())
                          .collect(Collectors.toList());

    return files.stream().map(p -> DynamicTest.dynamicTest(
          "Check that " + p.toString() + " gets a points-to result",
          () -> runProgramTest(p.toString())));
  }

  void runAttributeExtractionTest(String filename, List<String> libFiles) throws FileNotFoundException {
    Program program = PBUtils.compileProgram(filename, libFiles);
    assertThat(program.benchmarkTests().size()).as("The set should contain the one test").isEqualTo(1);
    String json = program.benchmarkTests().iterator().next().getValue();
    List<PointerBenchTruth> tests = PBUtils.parseJson(json);
    assertThat(tests.size()).isGreaterThan(0);
    assertThat(program.getTargetDeclaration().size()).as("There should be one declaration for the target").isEqualTo(1);
  }

  void runPointerBenchTest(String filename, List<String> libFiles) throws IOException {
    Program program = PBUtils.compileProgram(filename, libFiles);

    Set<Integer> idExpected = PBUtils.getAllocIDExpected(program);
    Set<Integer> idActuals = PBUtils.getAllocIDActuals(program);

    // Check that we found each allocationSite that is in the truth set
    idExpected.forEach(truth -> assertThat(idActuals.contains(truth))
        .as("The allocationSite with id %d should be reported as an allocationsite, but it reported %s",
          truth, idActuals)
        .isTrue());

  }

  void runProgramTest(String filename) throws FileNotFoundException {
    String[] JCheckerArgs = {filename};

    Options options = new Options(JCheckerArgs);

    Bfpa compiler = new Bfpa(options);

    int exitCode = compiler.run();
    assertThat(exitCode).as("Program should compile with exit code 0").isEqualTo(0);

    Program program = compiler.getEntryPoint();

    //assertThat(() -> program.allPointsToResults()).
    try {
      program.allPointsToResults();
    } catch (Exception e) {
      assertThat(false).as("The program should not throw an exception").isTrue();
    }

  }
}
