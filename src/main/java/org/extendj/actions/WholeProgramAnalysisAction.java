package org.extendj.actions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

import org.extendj.Bfpa;
import org.extendj.Options;
import org.extendj.ast.Program;
import org.extendj.utils.Utils;
import org.extendj.ast.InvocationTarget;
import org.extendj.ast.MethodDecl;
import org.extendj.ast.Pointer;

public class WholeProgramAnalysisAction implements Action {
  private Options options;
  private static final String singleIndent = "  ";
  private static final String doubleIndent = singleIndent + singleIndent;
  private static final String tripleIndent = doubleIndent + singleIndent;

  public WholeProgramAnalysisAction(Options options) {
    this.options = options;
  }

  @Override
  public int execute() {
    System.out.println("Writing the results to the file " + options.getOutputFile());


    Bfpa compiler = new Bfpa(options);
    compiler.run();
    Program program = compiler.getEntryPoint();
    //Set<MethodDecl> methods = program.methodsOfInterest();
    Set<InvocationTarget> methods = program.allMethods().stream().filter(m -> m.hasPointerParameter()).collect(Collectors.toSet());

    try {
      FileWriter writer = new FileWriter(options.getOutputFile(), false);
      writer.write("[\n");
      Boolean firstEntry = true;
      int count = 0;

      for (InvocationTarget method : methods) {
        //program.flushTreeCache();
        System.out.println("methods completed: " + count + "/" + methods.size());
        count++;
        StringBuilder sb = new StringBuilder();
        if (firstEntry) {
          firstEntry = false;
        } else {
          sb.append(",\n");
        }
        sb.append(singleIndent).append("{\n");
        sb.append(doubleIndent).append("\"method_signature\": ")
                               .append('"')
                               .append(method.targetSignature())
                               .append('"')
                               .append('\n');

        sb.append(doubleIndent).append("\"vars\": {\n");

        for (Map.Entry<String, List<String>> entry : method.wholeProgramParameterPointers().entrySet()) {
          String name = entry.getKey();
          String types = entry.getValue().stream().map(e -> '"' + e + '"').collect(Collectors.joining(", "));
          sb.append(tripleIndent).append('"').append(name).append("\": ").append('[').append(types).append("]\n");
        }

        // add the vars
        sb.append(doubleIndent).append("}\n");
           
        sb.append(singleIndent).append('}');

        writer.write(sb.toString());
      }
      writer.write("\n]");
      writer.close();
    } catch (IOException e) {
      System.err.println("An error occurred while writing to the file.");
      e.printStackTrace();
    }

    return 1;
  }
}
