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
 * @aspect BytecodeDescriptor
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:55
 */
 class FieldInfo extends java.lang.Object {
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:56
   */
  
    private AbstractClassfileParser p;
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:57
   */
  
    String name;
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:58
   */
  
    int flags;
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:59
   */
  
    private FieldDescriptor fieldDescriptor;
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:60
   */
  
    private Attributes.FieldAttributes attributes;
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:62
   */
  

    public FieldInfo(AbstractClassfileParser parser) throws IOException {
      p = parser;
      flags = p.u2();
      if (AbstractClassfileParser.VERBOSE) {
        p.print("Flags: " + flags);
      }
      int name_index = p.u2();
      name = ((CONSTANT_Utf8_Info) p.constantPool[name_index]).string();

      fieldDescriptor = new FieldDescriptor(p, name);
      attributes = new Attributes.FieldAttributes(p);
      if (attributes.isSynthetic()) {
        flags |= Flags.ACC_SYNTHETIC;
      }
    }
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:78
   */
  

    public BodyDecl bodyDecl() {
      if ((flags & Flags.ACC_ENUM) != 0) {
        EnumConstant constant = new EnumConstant(
            AbstractClassfileParser.modifiers(flags),
            name,
            new List(),
            new List());
        if (attributes.constantValue() != null) {
          if (fieldDescriptor.isBoolean()) {
            constant.setInit(attributes.constantValue().exprAsBoolean());
          } else {
            constant.setInit(attributes.constantValue().expr());
          }
        }
        if (attributes.annotations != null) {
          for (Annotation annotation : attributes.annotations) {
            constant.getModifiersNoTransform().addModifier(annotation);
          }
        }
        return constant;
      } else {
        Signatures.FieldSignature s = attributes.fieldSignature;
        Access type = s != null ? s.fieldTypeAccess() : fieldDescriptor.type();
        FieldDeclarator decl = new FieldDeclarator(name, new List<Dims>(), new Opt<Expr>());
        FieldDecl f = new FieldDecl(
            AbstractClassfileParser.modifiers(flags),
            type,
            new List<FieldDeclarator>(decl));
        if (attributes.constantValue() != null) {
          if (fieldDescriptor.isBoolean()) {
            decl.setInit(attributes.constantValue().exprAsBoolean());
          } else {
            decl.setInit(attributes.constantValue().expr());
          }
        }
        if (attributes.annotations != null) {
          for (Annotation annotation : attributes.annotations) {
            f.getModifiersNoTransform().addModifier(annotation);
          }
        }
        return f;
      }
    }
  /**
   * @aspect BytecodeDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeDescriptor.jrag:122
   */
  

    public boolean isSynthetic() {
      return (flags & Flags.ACC_SYNTHETIC) != 0;
    }

}
