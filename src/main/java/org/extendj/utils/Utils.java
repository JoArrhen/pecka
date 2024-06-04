package org.extendj.utils;

import java.util.ArrayList;
import java.util.List;

import org.extendj.ast.BodyDecl;
import org.extendj.ast.ClassDecl;
import org.extendj.ast.CompilationUnit;
import org.extendj.ast.InvocationTarget;
import org.extendj.ast.MethodDecl;
import org.extendj.ast.Program;
import org.extendj.ast.TypeDecl;

public class Utils {

  public static List<InvocationTarget> getMethods(Program p) {
    List<InvocationTarget> methods = new ArrayList<>();
    for (CompilationUnit cu: p.getCompilationUnits()) {
      for (TypeDecl td: cu.getTypeDecls()) {
        if (td instanceof ClassDecl) {
          ClassDecl cd = (ClassDecl) td;
          for (BodyDecl bd: cd.getBodyDecls()) {
            if (bd instanceof MethodDecl && bd instanceof InvocationTarget) {
              methods.add((InvocationTarget) bd);
            }
          }
        }
      }
    }
    return methods;
  }

}
