package org.extendj.ast;

import java.util.*;
import java.util.zip.*;
import java.io.*;
import org.jastadd.util.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import beaver.Symbol;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.stream.StreamSupport;
import java.net.URL;
import java.util.function.Predicate;
import java.lang.reflect.Field;
import org.jastadd.util.PrettyPrinter;
import java.io.BufferedInputStream;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.io.PrintStream;
import java.util.regex.Pattern;
import org.extendj.callgraph.AttributeTracer;
import java.util.concurrent.ConcurrentSkipListMap;
import java.io.InputStream;
import java.util.concurrent.ConcurrentMap;
import org.jastadd.util.PrettyPrintable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;
import org.extendj.Cat;
import java.util.Collection;
import java.io.File;
/**
 * Represents a bytecode class source (.class file).
 * @ast class
 * @aspect PathPart
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:190
 */
public abstract class BytecodeClassSource extends ClassSource {
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:191
   */
  
    public BytecodeClassSource(PathPart sourcePath) {
      super(sourcePath);
    }
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:195
   */
  

    @Override
    public CompilationUnit parseCompilationUnit(Program program) throws IOException {
      InputStream is = openInputStream();
      try {
        if (program.options().verbose()) {
          System.out.print("Loading " + sourceName());
        }

        long start = System.nanoTime();
        CompilationUnit u = program.bytecodeReader.read(is, sourceName(), program);
        long elapsed = System.nanoTime() - start;
        program.bytecodeParseTime += elapsed;
        program.numClassFiles += 1;

        u.setFromSource(false);
        u.setClassSource(this);

        if (program.options().verbose()) {
          System.out.println(" in " + (elapsed / 1000000) + " ms");
        }
        return u;
      } catch (AbstractClassfileParser.ClassfileFormatError e) {
        throw new AbstractClassfileParser.ClassfileFormatError(
            String.format("Error loading classfile %s: %s\n", sourceName(), e.getMessage()));
      } catch (Exception e) {
        throw new Error("Error loading classfile " + sourceName(), e);
      } finally {
        if (is != null) {
          try {
            is.close();
          } catch (IOException e) {
          }
        }
      }
    }

}
