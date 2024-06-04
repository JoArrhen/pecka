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
 * @aspect PathPart
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:160
 */
public class FileClassSource extends ClassSource {
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:161
   */
  
    private final String filePath;
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:163
   */
  

    public FileClassSource(PathPart sourcePath, String path) {
      super(sourcePath);
      this.filePath = path;
    }
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:168
   */
  

    @Override
    public long lastModified() {
      // last modification time computed only if needed
      File file = new File(filePath);
      return file.lastModified();
    }
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:175
   */
  

    @Override
    public InputStream openInputStream() throws IOException {
      File file = new File(filePath);
      return new FileInputStream(file);
    }
  /**
   * @aspect PathPart
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PathPart.jadd:181
   */
  

    @Override
    public String pathName() {
      return filePath;
    }

}
