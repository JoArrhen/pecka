package org.extendj.testutils;

import static org.assertj.core.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.extendj.Bfpa;
import org.extendj.Options;
import org.extendj.ast.DeclarationSite;
import org.extendj.ast.Pointer;
import org.extendj.ast.Program;

public class PBUtils {

  public static Stream<String> getPointerBenchTestFiles(int maxJavaVersion) throws IOException {
    String pbDir = "PointerBench/src";


    return Files.walk(Paths.get(pbDir))
      .filter(Files::isRegularFile)
      .filter(p -> !p.toString().contains("src/benchmark"))
      .filter(p -> !p.toString().contains("src/new/StaticFieldTypeAccess.java")) // disable until or if we support it
      .filter(p -> {
        if (maxJavaVersion < 5) {
          return !p.toString().contains("src/java5");
        }
        return true;
      })
      .map(Object::toString);
  }

  public static List<String> getPointerBenchLib() throws IOException {
    String libDir = "PointerBench/src/benchmark";
    return Files.walk(Paths.get(libDir))
      .filter(Files::isRegularFile)
      .map(p -> p.toString())
      .collect(Collectors.toList());

  }

  public static Program compileProgram(String entryfile, List<String> libFiles) throws FileNotFoundException {
    List<String> JCheckerArgs = new ArrayList<>();
    JCheckerArgs.addAll(libFiles);
    JCheckerArgs.add(entryfile);

    Options options = new Options(JCheckerArgs.toArray(new String[0]));

    Bfpa compiler = new Bfpa(options);

    int exitCode = compiler.run();
    assertThat(exitCode).as("Program should compile with exit code 0").isEqualTo(0);

    Program program = compiler.getEntryPoint();
    return program;
  }

  public static List<PointerBenchTruth> parseJson(String json) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(json, new TypeReference<List<PointerBenchTruth>>(){});
    } catch (Exception e) {
      fail("Error while parsing JSON: " + json + "\nerror: " + e.getMessage());
      return null;
    } 
  }

  public static Set<Integer> getAllocIDExpected(Program program) {
    String json = program.benchmarkTests().iterator().next().getValue();
    List<PointerBenchTruth> truths = parseJson(json);
    return truths.stream().map(e -> e.getAllocId()).collect(Collectors.toSet());
  }

  public static Set<Integer> getAllocIDActuals(Program program) {
    Pointer target = program.getTargetDeclaration().iterator().next();
    Set<Integer> res = target.pointsToSet().stream().map(e -> e.allocID()).collect(Collectors.toSet());

    // Add the non-allocation site to the result if we had no points to
    if (res.size() == 0) {
      res.add(0);
    }
    return res;
  }
}
