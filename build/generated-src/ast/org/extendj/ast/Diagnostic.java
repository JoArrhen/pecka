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
 * @aspect jastaddbridge
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:9
 */
public class Diagnostic extends java.lang.Object {
  /**
   * @aspect jastaddbridge
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:10
   */
  
        private ASTNode node;
  /**
   * @aspect jastaddbridge
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:11
   */
  
        private String message;
  /**
   * @aspect jastaddbridge
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:12
   */
  
        private int severity = 3;
  /**
   * @aspect jastaddbridge
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:13
   */
  
        private int startLine;
  /**
   * @aspect jastaddbridge
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:14
   */
  
        private int startColumn;
  /**
   * @aspect jastaddbridge
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:15
   */
  
        private int endLine;
  /**
   * @aspect jastaddbridge
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:16
   */
  
        private int endColumn;
  /**
   * @aspect jastaddbridge
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:17
   */
  
        private Severity severity_level;
  /**
   * @aspect jastaddbridge
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:19
   */
  

        public Diagnostic(ASTNode node, String message, Severity severity_level) {
            this.node = node;
            this.message = message;
            this.startLine = Symbol.getLine(node.getStart()) - 1;
            this.startColumn = Symbol.getColumn(node.getStart());
            this.endLine = Symbol.getLine(node.getEnd()) - 1;
            this.endColumn = Symbol.getColumn(node.getEnd());
            this.severity_level = severity_level;
        }
  /**
   * @aspect jastaddbridge
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:29
   */
  

        public String message() {
            return message;
        }
  /**
   * @aspect jastaddbridge
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:33
   */
  

        public int severity() { // Diagnostic type. 1 = error, 2 = warning, 3 = info, 4 = hint
            return severity_level.getValue();
        }
  /**
   * @aspect jastaddbridge
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:37
   */
  

        public int startLine() {
            return startLine;
        }
  /**
   * @aspect jastaddbridge
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:41
   */
  

        public int startColumn() {
            return startColumn;
        }
  /**
   * @aspect jastaddbridge
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:45
   */
  

        public int endLine() {
            return endLine;
        }
  /**
   * @aspect jastaddbridge
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:49
   */
  

        public int endColumn() {
            return endColumn;
        }
  /**
   * @aspect jastaddbridge
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Jastaddbridge.jrag:52
   */
  
        @Override
        public String toString() {
            return "'" +
                message + "' " +
                "at location " + startLine + ":" + startColumn +
                " - " + endLine + ":" + endColumn;
        }

}
