package org.extendj.actions;

import java.util.ArrayList;
import java.util.List;

import org.extendj.Bfpa;
import org.extendj.Options;
import org.extendj.ast.BodyDecl;
import org.extendj.ast.ClassDecl;
import org.extendj.ast.CompilationUnit;
import org.extendj.ast.InvocationTarget;
import org.extendj.ast.MethodDecl;
import org.extendj.ast.Program;
import org.extendj.ast.TypeDecl;
import org.extendj.utils.Utils;

public class PrintSignaturesAction implements Action {
  private Options options;

  public PrintSignaturesAction(Options options) {
    this.options = options;
  }

  @Override
  public int execute() {
    Bfpa compiler = new Bfpa(options);
    int exitCode = compiler.run();

    if (exitCode != 0) {
      System.err.println("Could not compile program");
      return exitCode;
    }

    Program program = compiler.getEntryPoint(); 

    System.out.println("Method signatures for program:");
    for (InvocationTarget target : Utils.getMethods(program)) {
      System.out.println("    " + target.targetSignature());
    }
    return 0;
  }
}
