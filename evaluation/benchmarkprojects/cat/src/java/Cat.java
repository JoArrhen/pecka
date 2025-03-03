/* Copyright (c) 2021, Idriss Riouak <idriss.riouak@cs.lth.se>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.extendj;

import static spark.Spark.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import org.extendj.ast.CompilationUnit;
import org.extendj.ast.Frontend;
import org.extendj.ast.Program;
import org.extendj.ast.InvocationTarget;

/**
 * Perform static semantic checks on a Java program.
 */
public class Cat extends Frontend {
  public boolean considerOnlyAttributes = false;
  public boolean visualiseCallGraph = false;
  public boolean saveCallGraph = false;
  public boolean mergeNames = false;
  public String callGraphPath = "";
  public String entryPointPackage = "";
  public String entryPointMethod = "";
  public static boolean allMethods = false;
  public static Object DrAST_root_node;
  public static boolean vscode = false;
  public static boolean forward = true;
  public static int n_iter = 0;
  private static boolean takeTime = false;
  private static long totalTime = 0;
  

  private String[] setEnv(String[] args) throws FileNotFoundException {
    if (args.length < 1) {
      System.err.println("You must specify a source file on the command line!");
      printOptionsUsage();
    }

    ArrayList<String> FEOptions = new ArrayList<>();

    for (int i = 0; i < args.length; ++i) {
      String opt = args[i];
      if (opt.contains(".java")) {
        FEOptions.add(args[i]);
        continue;
      }
      switch (opt) {
      case "-attributesOnly":
        considerOnlyAttributes = true;
        break;
      case "-entryPoint":
        entryPointPackage = args[++i];
        entryPointMethod = args[++i];
        break;
      case "-niter":
        takeTime = true;
        n_iter = Integer.parseInt(args[++i]);
        break;
      case "-classpath":
        FEOptions.add("-classpath");
        FEOptions.add(args[++i]);
        break;
      case "-allMethods":
        allMethods = true;
        break;
      case "-visualise":
        visualiseCallGraph = true;
        break;
      case "-o":
        saveCallGraph = true;
        callGraphPath = args[++i];
        break;
      case "-mergeNames":
        mergeNames = true;
        break;

      case "-vscode":
        vscode = true;
        break;
      case "-backward":
        forward = false;
        break;
      default:
        System.err.println("Unknown option: " + opt);
        printOptionsUsage();
        break;
      }
    }
    FEOptions.add("-nowarn");
    return FEOptions.toArray(new String[FEOptions.size()]);
  }

  public boolean getConsiderOnlyAttributes() { return considerOnlyAttributes; }

  public boolean getVisualiseCallGraph() { return visualiseCallGraph; }

  public boolean getSaveCallGraph() { return saveCallGraph; }

  public String getCallGraphPath() { return callGraphPath; }

  public boolean getMergeNames() { return mergeNames; }

  public String getEntryPointPackage() { return entryPointPackage; }

  public String getEntryPointMethod() { return entryPointMethod; }

  /**
   * Entry point for the Java checker.
   * @param args command-line arguments
   */
  public static void main(String args[])
      throws FileNotFoundException, InterruptedException, IOException {

    for (int i = 0; i <= n_iter; ++i) {
      totalTime = 0;
      Cat cat = new Cat();
      String[] jCheckerArgs = cat.setEnv(args);
      Program root = cat.getEntryPoint();
      root.attributesOnly = cat.getConsiderOnlyAttributes();
      root.mergeNames = cat.getMergeNames();
      root.entryPointPackage = cat.getEntryPointPackage();
      root.entryPointMethod = cat.getEntryPointMethod();
      int exitCode = cat.run(jCheckerArgs);
      if (exitCode != 0) {
        System.exit(exitCode);
      }
      InvocationTarget entry = root.getTarget(cat.getEntryPointPackage(),
                                             cat.getEntryPointMethod());
      processMethodDeclCAT(entry);
    }
    if (takeTime) {
      System.out.println(
          String.format("Analysis: %.6f", totalTime / 1_000_000_000.0));
      System.out.println(String.format("Error: %.6f", 0 /
                                                          1_000_000_000.0));
    }
  }

  public static void processMethodDeclCAT(InvocationTarget t){
    long startTime = System.nanoTime();
    Set<InvocationTarget> visited = new LinkedHashSet<>();
    traverseCallgraph(t, visited);
    totalTime += (System.nanoTime() - startTime);
  }

  public static void traverseCallgraph(InvocationTarget target, Set<InvocationTarget> visited) {
    if (visited.contains(target)) {
      return;
    }
    visited.add(target);
    for (InvocationTarget t : target.cg()) {
      traverseCallgraph(t, visited);
    }
  }

  /**
   * Initialize the Java checker.
   */
  public Cat() { super("Cat", ExtendJVersion.getVersion()); }

  /**
   * @param args command-line arguments
   * @return {@code true} on success, {@code false} on error
   * @deprecated Use run instead!
   */
  @Deprecated
  public static boolean compile(String args[]) {
    return 0 == new JavaChecker().run(args);
  }

  /**
   * Run the Java checker.
   * @param args command-line arguments
   * @return 0 on success, 1 on error, 2 on configuration error, 3 on system
   */
  public int run(String args[]) {
    return run(args, Program.defaultBytecodeReader(),
               Program.defaultJavaParser());
  }

  /**
   * Called for each from-source compilation unit with no errors.
   */
  protected void processNoErrors(CompilationUnit unit) {}

  @Override
  protected String name() {
    return "Cat";
  }

  @Override
  protected String version() {
    return "CAT2023";
  }

  public Program getEntryPoint() { return program; }

  private static void log(String message) {
    if (!vscode) {
      System.out.println("\u001B[33m[INFO]\u001B[0m: " + message);
    }
  }

  private void printOptionsUsage() {
    if (vscode)
      System.exit(0);
    System.out.println("Usage: java -jar cat.jar [options] <source_files>");
    System.out.println("Options:");
    System.out.println(
        "[MANDATORY]  -entryPoint <package> <method> Specify the entry point package and method");
    System.out.println(
        "  -attributesOnly   Enable consideration of attributes only. Methods will be discarded.");
    System.out.println("  -classpath <path> Specify the classpath");
    System.out.println("  -visualise        Visualize the call graph");
    System.out.println(
        "  -o <path>         Save call graph to a JSON file at the specified path");
    System.out.println(
        "  -mergeNames       Enable name merging for attributes. This option will discards type information");
    System.exit(1);
  }
}
