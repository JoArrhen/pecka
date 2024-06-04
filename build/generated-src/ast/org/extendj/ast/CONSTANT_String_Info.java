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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:262
 */
 class CONSTANT_String_Info extends CONSTANT_Info {
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:263
   */
  
    public int string_index;
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:265
   */
  

    public CONSTANT_String_Info(AbstractClassfileParser parser) throws IOException {
      super(parser);
      string_index = p.u2();
    }
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:270
   */
  

    @Override
    public Expr expr() {
      CONSTANT_Utf8_Info i = (CONSTANT_Utf8_Info) p.constantPool[string_index];
      return Literal.buildStringLiteral(i.string);
    }
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:276
   */
  

    @Override
    public String toString() {
      return "StringInfo: " + p.constantPool[string_index];
    }

}
