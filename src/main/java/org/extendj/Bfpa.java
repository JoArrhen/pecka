/* Copyright (c) 2022, Idriss Riouak <idriss.riouak@cs.lth.se>
 *                     Johan Arrhen  <jo2065ar-s@student.lu.se>
 *                     Ruben Wiklund <ru6707wi-s@student.lu.se>
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

import java.io.FileNotFoundException;
import java.io.IOException;

import org.extendj.ast.CompilationUnit;
import org.extendj.ast.Frontend;
import org.extendj.ast.Program;
import org.extendj.actions.Action;

/**
 * Perform static semantic checks on a Java program.
 */
public class Bfpa extends Frontend {
  // Options for Cat
  private boolean considerOnlyAttributes = false;
  private boolean mergeNames = false;
  private boolean rta = false;

  private Options options;

  public static Object DrAST_root_node;
  public static Object CodeProber_root_node;

  /**
   * Entry point for the Java checker.
   * 
   * @param args command-line arguments
   */
  public static void main(String args[])
      throws FileNotFoundException, InterruptedException, IOException {
    Options options = new Options(args);

    Action action = options.getAction();
    int exitCode = action.execute();

    if (exitCode != 0) {
      System.exit(exitCode);
    }
  }

  /**
   * Initialize the Java checker.
   */
  public Bfpa(Options options) {
    super("Bfpa", ExtendJVersion.getVersion());
    this.options = options;
    setupASTOptions();
  }

  private void setupASTOptions() {
    DrAST_root_node = getEntryPoint();
    CodeProber_root_node = getEntryPoint();

    getEntryPoint().attributesOnly = considerOnlyAttributes;
    getEntryPoint().mergeNames = mergeNames;
    getEntryPoint().rta = rta;

    getEntryPoint().entryPointPackage = options.getEntryPointPackage();
    getEntryPoint().entryPointMethod = options.getEntryPointMethod();

    if (options.getDistance() == -1) {
      getEntryPoint().useDistanceStrategy(Integer.MAX_VALUE);
    } else if (options.getDistance() >= 0) {
      getEntryPoint().useDistanceStrategy(options.getDistance());
    } else {
      throw new Error("Invalid distance: " + options.getDistance());
    }
    getEntryPoint().printStream = System.err;
  }

  /**
   * Run the Java checker.
   * 
   * @return 0 on success, 1 on error, 2 on configuration error, 3 on system
   */
  public int run() {
    return run(options.getJCheckerArgs(), Program.defaultBytecodeReader(),
        Program.defaultJavaParser());
  }

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
   * Called for each from-source compilation unit with no errors.
   */
  protected void processNoErrors(CompilationUnit unit) {
  }

  @Override
  protected String name() {
    return "Bfpa";
  }

  @Override
  protected String version() {
    return "BFPA2023";
  }

  public Program getEntryPoint() {
    return program;
  }

  private static void log(String message) {
    System.out.println("\u001B[33m[INFO]\u001B[0m: " + message);
  }
}
