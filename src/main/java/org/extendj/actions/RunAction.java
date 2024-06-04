package org.extendj.actions;

import java.io.FileNotFoundException;

import org.extendj.Bfpa;
import org.extendj.Options;
import org.extendj.ast.BytecodeReader;
import org.extendj.ast.JavaParser;
import org.extendj.ast.Program;

public class RunAction implements Action {
  private Options options;

  public RunAction(Options options) {
    this.options = options;
  }

  @Override
  public int execute() {
    Bfpa compiler = new Bfpa(options);
    compiler.getEntryPoint().entryPointPackage = options.getEntryPointPackage();
    compiler.getEntryPoint().entryPointMethod = options.getEntryPointMethod();
    return compiler.run();
  }
}
