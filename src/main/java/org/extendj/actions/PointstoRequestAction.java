package org.extendj.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.extendj.Bfpa;
import org.extendj.Options;
import org.extendj.ast.BodyDecl;
import org.extendj.ast.ClassDecl;
import org.extendj.ast.CompilationUnit;
import org.extendj.ast.DeclarationSite;
import org.extendj.ast.InvocationTarget;
import org.extendj.ast.MethodDecl;
import org.extendj.ast.Program;
import org.extendj.ast.TypeDecl;
import org.extendj.utils.Utils;

public class PointstoRequestAction implements Action {
  private Options options;

  public PointstoRequestAction(Options options) {
    this.options = options;
  }

  @Override
  public int execute() {
    Bfpa compiler = new Bfpa(options);
    int exitCode = compiler.run();
    if (exitCode != 0) {
      System.err.println("Could not compile the program");
      return exitCode;
    }


    System.out.println("Searching for " + options.getRequestSignature() + "...");

    Program program = compiler.getEntryPoint();
    List<InvocationTarget> methods = Utils.getMethods(program);
    System.out.println("There are a total of " + methods.size() + " methods in the program");

    InvocationTarget match = null;
    for (InvocationTarget target : Utils.getMethods(program)) {
      if (target.targetSignature().equals(options.getRequestSignature())) {
        match = target;
        break;
      }
    }

    if (match == null) {
      System.err.println(
          "Could not find the reqested method. Make sure it is written in the correct format: " +
          "package.class::method(paramtertype(s))"
      );
      return 1;
    } else {
      System.out.println("Found the method, will perform lookup...");
    }

    System.out.println(match.pointsToResults());
    return 0;
  }

}
