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
 * Specifies a particular parameterization for a parameterized type or generic method.
 * 
 * <p>Parameterizations are used during type lookup to substitute type variable names
 * for type arguments (TypeDecl).
 * @ast class
 * @aspect LookupParTypeDecl
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1062
 */
public class Parameterization extends java.lang.Object {
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1063
   */
  
    public interface TypeProperty {
      boolean holds(TypeDecl t1, TypeDecl t2);
    }
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1067
   */
  

    static class Substitution {
      final public TypeVariable param;
      final public TypeDecl arg;

      public Substitution(TypeVariable param, TypeDecl arg) {
        this.param = param;
        this.arg = arg;
      }

      /** Creates a raw type substitution. */
      public Substitution(TypeVariable param) {
        this.param = param;
        this.arg = null;
      }

      public TypeDecl substitute() {
        return arg == null
            ? param.erasure()
            : arg.expandWildcard(param);
      }

      /** Substitute types, preserving wildcards. */
      public TypeDecl substituteBound() {
        return arg == null
            ? param.erasure()
            : arg;
      }
    }
  /** Type variable names mapped to wildcard expanded argument types. 
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1097
   */
  

    /** Type variable names mapped to wildcard expanded argument types. */
    public final Map<String, Substitution> typeMap = new HashMap<String, Substitution>();
  /**
   * The original type parameters of the generic type or method.
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1102
   */
  

    /**
     * The original type parameters of the generic type or method.
     */
    public final java.util.List<TypeVariable> params = new ArrayList<TypeVariable>();
  /**
   * The type arguments provided for the this parameterization (not wildcard expanded).
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1107
   */
  

    /**
     * The type arguments provided for the this parameterization (not wildcard expanded).
     */
    public final java.util.List<TypeDecl> args = new ArrayList<TypeDecl>();
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1109
   */
  

    private final boolean isRaw;
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1111
   */
  

    public Parameterization(Iterable<TypeVariable> typeParams, Iterable<TypeDecl> typeArgs) {
      Iterator<TypeVariable> param = typeParams.iterator();
      Iterator<TypeDecl> arg = typeArgs.iterator();
      isRaw = param.hasNext() && !arg.hasNext();
      while (param.hasNext()) {
        TypeVariable variable = param.next();
        Substitution substitution;
        if (arg.hasNext()) {
          TypeDecl argument = arg.next();
          substitution = new Substitution(variable, argument);
          this.args.add(argument);
        } else {
          substitution = new Substitution(variable);
        }
        typeMap.put(variable.name(), substitution);
        params.add(variable);
      }
    }
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1130
   */
  

    @Override public String toString() {
      StringBuilder str = new StringBuilder();
      str.append("[");
      if (isRaw) {
        str.append("raw");
      } else {
        for (TypeVariable var : params) {
          if (str.length() > 1) {
            str.append(", ");
          }
          TypeDecl arg = typeMap.get(var.name()).arg;
          str.append(var.name()).append("=").append(arg.typeName());
        }
      }
      str.append("]");
      return str.toString();
    }
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1148
   */
  

    public boolean isRawType() {
      return isRaw;
    }
  /**
   * @return the substituted type, if the name matches a type variable name.
   * Returns {@code null} if the name does not match a type variable in this
   * parameterization.
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1157
   */
  

    /**
     * @return the substituted type, if the name matches a type variable name.
     * Returns {@code null} if the name does not match a type variable in this
     * parameterization.
     */
    public TypeDecl substitute(String name) {
      if (typeMap.containsKey(name)) {
        return typeMap.get(name).substitute();
      }
      return null;
    }
  /**
   * Substitute a type bound of a type variable.
   * This should preserve wildcards.
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1168
   */
  

    /**
     * Substitute a type bound of a type variable.
     * This should preserve wildcards.
     */
    public TypeDecl substituteBound(String name) {
      if (typeMap.containsKey(name)) {
        return typeMap.get(name).substituteBound();
      }
      return null;
    }
  /**
   * @return the original type argument, or the Object type, if this is a raw
   * parameterization.
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1179
   */
  

    /**
     * @return the original type argument, or the Object type, if this is a raw
     * parameterization.
     */
    public TypeDecl getArg(int i) {
      if (args.isEmpty()) {
        // This is a raw parameterization.
        return params.get(0).typeObject();
      } else {
        return args.get(i);
      }
    }
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1188
   */
  

    public boolean sameArguments(Parameterization that) {
      Iterator<TypeDecl> it1 = this.args.iterator();
      Iterator<TypeDecl> it2 = that.args.iterator();
      while (it1.hasNext() && it2.hasNext()) {
        TypeDecl t1 = it1.next();
        TypeDecl t2 = it2.next();
        if (t1 instanceof ParTypeDecl && t2 instanceof ParTypeDecl) {
          if (!((ParTypeDecl) t1).sameArguments((ParTypeDecl) t2)) {
            return false;
          }
        } else {
          if (t1 != t2) {
            return false;
          }
        }
      }
      return !it1.hasNext() && !it2.hasNext();
    }
  /**
   * Check a type property on each pair of type arguments in this and that
   * parameterization.
   * @return {@code true} if this parameterization and the argument
   * parameterization have the same number of type arguments, and if the
   * given property holds for each type argument pair.
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1214
   */
  

    /**
     * Check a type property on each pair of type arguments in this and that
     * parameterization.
     * @return {@code true} if this parameterization and the argument
     * parameterization have the same number of type arguments, and if the
     * given property holds for each type argument pair.
     */
    public boolean compare(Parameterization that, TypeProperty property) {
      Iterator<TypeDecl> it1 = this.args.iterator();
      Iterator<TypeDecl> it2 = that.args.iterator();
      while (it1.hasNext() && it2.hasNext()) {
        TypeDecl t1 = it1.next();
        TypeDecl t2 = it2.next();
        if (!property.holds(t1, t2)) {
          return false;
        }
      }
      return !it1.hasNext() && !it2.hasNext();
    }
  /**
   * Check a type property on each pair of type arguments in this and that
   * parameterization, with substituted arguments in this parameterization.
   * @return {@code true} if this parameterization and the argument
   * parameterization have the same number of type arguments, and if the
   * given property holds for each type argument pair, with the argument
   * from this parameterization substituted.
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1235
   */
  

    /**
     * Check a type property on each pair of type arguments in this and that
     * parameterization, with substituted arguments in this parameterization.
     * @return {@code true} if this parameterization and the argument
     * parameterization have the same number of type arguments, and if the
     * given property holds for each type argument pair, with the argument
     * from this parameterization substituted.
     */
    public boolean compareSubstituted(Parameterization that, TypeProperty property) {
      Iterator<TypeVariable> it1 = this.params.iterator();
      Iterator<TypeDecl> it2 = that.args.iterator();
      while (it1.hasNext() && it2.hasNext()) {
        TypeDecl t1 = substitute(it1.next().name());
        TypeDecl t2 = it2.next();
        if (!property.holds(t1, t2)) {
          return false;
        }
      }
      return !it1.hasNext() && !it2.hasNext();
    }
  /**
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:215
   */
  public static final TypeProperty SAME_STRUCTURE = new TypeProperty() {
    @Override
    public boolean holds(TypeDecl t1, TypeDecl t2) {
      return t1.sameStructure(t2);
    }
  };
  /**
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:297
   */
  public static final TypeProperty CONTAINED_IN = new TypeProperty() {
    @Override
    public boolean holds(TypeDecl t1, TypeDecl t2) {
      return t1.containedIn(t2);
    }
  };
  /**
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:208
   */
  public static final TypeProperty STRICT_CONTAINED_IN = new TypeProperty() {
    @Override
    public boolean holds(TypeDecl t1, TypeDecl t2) {
      return t1.strictContainedIn(t2);
    }
  };

}
