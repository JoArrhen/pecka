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
 * @ast class
 * @aspect Solver
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:303
 */
 class FieldAccess extends java.lang.Object implements PFGNode {
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:304
   */
  
        public final PFGNode variable;
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:305
   */
  
        public final String field;
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:307
   */
  

        public FieldAccess(PFGNode variable, String field) {
            this.variable = variable;
            this.field = field;
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:312
   */
  

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (getClass() != o.getClass()) return false;
            FieldAccess fa = (FieldAccess) o;
            return variable == fa.variable && field.equals(fa.field);
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:320
   */
  

        @Override
        public int hashCode() {
            return variable.toString().hashCode() ^ field.hashCode();
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:325
   */
  

        @Override
        public String toString() {
            return variable + "." + field;
        }

}
