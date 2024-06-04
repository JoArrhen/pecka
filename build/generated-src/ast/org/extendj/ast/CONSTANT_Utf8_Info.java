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
 * @aspect BytecodeCONSTANT
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:282
 */
 class CONSTANT_Utf8_Info extends CONSTANT_Info {
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:283
   */
  
    public String string;
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:285
   */
  

    public CONSTANT_Utf8_Info(AbstractClassfileParser parser) throws IOException {
      super(parser);
      string = p.readUTF();
    }
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:290
   */
  

    @Override
    public String toString() {
      return "Utf8Info: " + string;
    }
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:295
   */
  

    @Override
    public Expr expr() {
      return Literal.buildStringLiteral(string);
    }
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:300
   */
  

    public String string() {
      return string;
    }

}
