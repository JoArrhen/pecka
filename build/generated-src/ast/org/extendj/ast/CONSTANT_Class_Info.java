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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:32
 */
 class CONSTANT_Class_Info extends CONSTANT_Info {
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:33
   */
  
    public int name_index;
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:35
   */
  

    public CONSTANT_Class_Info(AbstractClassfileParser parser) throws IOException {
      super(parser);
      name_index = p.u2();
    }
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:40
   */
  

    @Override
    public String toString() {
      return "ClassInfo: " + name();
    }
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:45
   */
  

    public String name() {
      String name = ((CONSTANT_Utf8_Info) p.constantPool[name_index]).string();
      name = name.replace('/', '.');
      return name;
    }
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:51
   */
  

    public String simpleName() {
      String name = name();
      int pos = name.lastIndexOf('.');
      return name.substring(pos + 1, name.length());
    }
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:57
   */
  

    public String packageDecl() {
      String name = name();
      int pos = name.lastIndexOf('.');
      if (pos == -1) {
        return "";
      }
      return name.substring(0, pos);
    }
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BytecodeCONSTANT.jrag:66
   */
  

    public Access access() {
      String name = name();
      int pos = name.lastIndexOf('.');
      String typeName = name.substring(pos + 1, name.length());
      String packageName = pos == -1 ? "" : name.substring(0, pos);
      if (typeName.indexOf('$') != -1) {
        return new BytecodeTypeAccess(packageName, typeName);
      } else {
        return new TypeAccess(packageName, typeName);
      }
    }

}
