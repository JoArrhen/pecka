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
 * @aspect GenericMethodsInference
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:66
 */
 class Constraints extends java.lang.Object {
  /**
   * Set of type bounds for inferred type variables.
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:70
   */
  
    /**
     * Set of type bounds for inferred type variables.
     */
    static class ConstraintSet {
      /** Lower type bounds. */
      public Collection<TypeDecl> lower = new HashSet<TypeDecl>(4);

      /** Upper type bounds. */
      public Collection<TypeDecl> upper = new HashSet<TypeDecl>(4);

      /** Equal type bounds. */
      public Collection<TypeDecl> equal = new HashSet<TypeDecl>(4);

      /**
       * Computed type argument.
       *
       * <p>Set to {@code null} before inference starts and if no type matches
       * the bounds.
       */
      public TypeDecl typeArgument;
    }
  /**
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:89
   */
  

    private Collection<TypeVariable> typeVariables;
  /**
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:91
   */
  

    protected Map<TypeVariable, ConstraintSet> constraintsMap;
  /**
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:93
   */
  

    public boolean rawAccess = false;
  /**
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:95
   */
  

    public Constraints() {
      typeVariables = new ArrayList<TypeVariable>(4);
      constraintsMap = new HashMap<TypeVariable, ConstraintSet>();
    }
  /**
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:100
   */
  

    public void addTypeVariable(TypeVariable T) {
      if (!typeVariables.contains(T)) {
        typeVariables.add(T);
        constraintsMap.put(T, new ConstraintSet());
      }
    }
  /**
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:107
   */
  

    public boolean unresolvedTypeArguments() {
      for (TypeVariable T : typeVariables) {
        ConstraintSet set = constraintsMap.get(T);
        if (set.typeArgument == null) {
          return true;
        }
      }
      return false;
    }
  /**
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:117
   */
  

    public String toString() {
      StringBuilder str = new StringBuilder();
      for (TypeVariable T : typeVariables) {
        ConstraintSet set = constraintsMap.get(T);
        for (TypeDecl U : set.lower) {
          if (str.length() > 0) {
            str.append("\n");
          }
          str.append(T.fullName() + " :> " + U.fullName());
        }
        for (TypeDecl U : set.upper) {
          if (str.length() > 0) {
            str.append("\n");
          }
          str.append(T.fullName() + " <: " + U.fullName());
        }
        for (TypeDecl U : set.equal) {
          if (str.length() > 0) {
            str.append("\n");
          }
          str.append(T.fullName() + " = " + U.fullName());
        }
      }
      return str.toString();
    }
  /**
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:144
   */
  


    public void resolveBounds() {
      for (TypeVariable T : typeVariables) {
        ConstraintSet set = constraintsMap.get(T);
        if (set.typeArgument == null) {
          set.typeArgument = T.firstBound().type();
        }
      }
    }
  /**
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:153
   */
  

    public void resolveEqualityConstraints() {
      for (TypeVariable T : typeVariables) {
        ConstraintSet set = constraintsMap.get(T);
        for (TypeDecl U : set.equal) {
          if (!typeVariables.contains(U)) {
            // Replace equality constraints for other type variables.
            replaceEqualityConstraints(T, U);
            set.equal.clear();
            // Make U is the only equality constraint for T.
            set.equal.add(U);
            set.typeArgument = U;
            break; // Continue on next type variable.
          } else if (T == U) {
            // Discard constraint.
          } else {
            replaceAllConstraints(T, U); // Rewrite all constraints involving T to use U instead.
            break; // Continue on next type variable.
          }
        }
        if (set.typeArgument == null
            && set.equal.size() == 1
            && set.equal.contains(T)) {
          set.typeArgument = T;
        }
      }
    }
  /**
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:180
   */
  

    public void replaceEqualityConstraints(TypeDecl before, TypeDecl after) {
      for (TypeVariable T : typeVariables) {
        ConstraintSet set = constraintsMap.get(T);
        replaceConstraints(set.equal, before, after);
      }
    }
  /**
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:187
   */
  

    public void replaceAllConstraints(TypeDecl before, TypeDecl after) {
      for (TypeVariable T : typeVariables) {
        ConstraintSet set = constraintsMap.get(T);
        replaceConstraints(set.lower, before, after);
        replaceConstraints(set.upper, before, after);
        replaceConstraints(set.equal, before, after);
      }
    }
  /**
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:196
   */
  

    private void replaceConstraints(Collection<TypeDecl> constraints,
        TypeDecl before, TypeDecl after) {
      Collection<TypeDecl> newConstraints = new ArrayList<TypeDecl>();
      for (Iterator<TypeDecl> iter = constraints.iterator(); iter.hasNext(); ) {
        TypeDecl U = iter.next();
        if (U == before) { // TODO: fix parameterized type
          iter.remove();
          newConstraints.add(after);
        }
      }
      constraints.addAll(newConstraints);
    }
  /**
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:209
   */
  

    public void resolveSubtypeConstraints() {
      for (TypeVariable T : typeVariables) {
        ConstraintSet set = constraintsMap.get(T);
        if (set.typeArgument == null && !set.upper.isEmpty()) {
          if (set.upper.size() == 1) {
            set.typeArgument = set.upper.iterator().next();
          } else {
            ArrayList<TypeDecl> bounds = new ArrayList<TypeDecl>();
            for (TypeDecl type : set.upper) {
              bounds.add(type);
            }
            set.typeArgument = GLBTypeFactory.glb(bounds);
          }
        }
      }
    }
  /**
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:226
   */
  

    public void resolveSupertypeConstraints() {
      for (TypeVariable T : typeVariables) {
        ConstraintSet set = constraintsMap.get(T);
        if (set.typeArgument == null && !set.lower.isEmpty()) {
          if (set.lower.size() == 1) {
            set.typeArgument = set.lower.iterator().next();
          } else {
            set.typeArgument = T.lookupLUBType(set.lower).lub();
          }
        }
      }
    }
  /**
   * Computes the direct supertypes of a type.
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:242
   */
  

    /**
     * Computes the direct supertypes of a type.
     */
    protected static Collection<TypeDecl> directSupertypes(TypeDecl t) {
      // TODO(joqvist): this should be an attribute of TypeDecl instead.
      if (t instanceof ClassDecl) {
        ClassDecl type = (ClassDecl) t;
        Collection<TypeDecl> set = new HashSet<TypeDecl>();
        if (type.hasSuperclass()) {
          set.add(type.superclass());
        }
        for (int i = 0; i < type.getNumImplements(); i++) {
          set.add(type.getImplements(i).type());
        }
        return set;
      } else if (t instanceof InterfaceDecl) {
        InterfaceDecl type = (InterfaceDecl) t;
        Collection<TypeDecl> set = new HashSet<TypeDecl>();
        for (int i = 0; i < type.getNumSuperInterface(); i++) {
          set.add(type.getSuperInterface(i).type());
        }
        return set;
      } else if (t instanceof TypeVariable) {
        TypeVariable type = (TypeVariable) t;
        Collection<TypeDecl> set = new HashSet<TypeDecl>();
        for (int i = 0; i < type.getNumTypeBound(); i++) {
          set.add(type.getTypeBound(i).type());
        }
        return set;
      } else {
        throw new Error(String.format(
              "Operation not supported for %s, %s",
              t.fullName(), t.getClass().getName()));
      }
    }
  /**
   * Computes the parameterized supertypes of some type.
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:278
   */
  

    /**
     * Computes the parameterized supertypes of some type.
     */
    protected static Collection<ParTypeDecl> parameterizedSupertypes(TypeDecl type) {
      // TODO(joqvist): this should be an attribute of TypeDecl instead.
      Collection<ParTypeDecl> result = new HashSet<ParTypeDecl>();
      addParameterizedSupertypes(type, new HashSet<TypeDecl>(), result);
      return result;
    }
  /**
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:285
   */
  

    protected static void addParameterizedSupertypes(TypeDecl type,
        Collection<TypeDecl> processed,
        Collection<ParTypeDecl> result) {
      // TODO(joqvist): this should be an attribute of TypeDecl instead.
      if (!processed.contains(type)) {
        processed.add(type);
        if (type.isParameterizedType()) {
          result.add((ParTypeDecl) type);
        }
        for (TypeDecl typeDecl : directSupertypes(type)) {
          addParameterizedSupertypes(typeDecl, processed, result);
        }
      }
    }
  /**
   * Gives the inferred type arguments.
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:303
   */
  

    /**
     * Gives the inferred type arguments.
     */
    public Collection<TypeDecl> typeArguments() {
      Collection<TypeDecl> list = new ArrayList<TypeDecl>(typeVariables.size());
      for (TypeVariable T : typeVariables) {
        ConstraintSet set = constraintsMap.get(T);
        list.add(set.typeArgument);
      }
      return list;
    }
  /**
   * Adds A as a lower bound for type variable T.
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:315
   */
  

    /**
     * Adds A as a lower bound for type variable T.
     */
    public void addSupertypeConstraint(TypeDecl T, TypeDecl A) {
      ConstraintSet set = constraintsMap.get(T);
      set.lower.add(A);
    }
  /**
   * Adds A as an upper bound for type variable T.
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:323
   */
  

    /**
     * Adds A as an upper bound for type variable T.
     */
    public void addSubtypeConstraint(TypeDecl T, TypeDecl A) {
      ConstraintSet set = constraintsMap.get(T);
      set.upper.add(A);
    }
  /**
   * T = A : T and A are the same type.
   * 
   * <p>This assigns type A to the type variable T.
   * 
   * <p>It could happen that T and A refer to the same object, if the generic
   * method call is recursive.  It is important to still add the
   * constraint T = T even though it may seen redundant: one type variable
   * represents the type variable in the generic method call, and one type
   * variable represents a type in the context of the method call. Removing
   * a constraint T = T removes information from the type inference and
   * breaks existing tests.  See the regression test generics/method_23p.
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:341
   */
  

    /**
     * T = A : T and A are the same type.
     *
     * <p>This assigns type A to the type variable T.
     *
     * <p>It could happen that T and A refer to the same object, if the generic
     * method call is recursive.  It is important to still add the
     * constraint T = T even though it may seen redundant: one type variable
     * represents the type variable in the generic method call, and one type
     * variable represents a type in the context of the method call. Removing
     * a constraint T = T removes information from the type inference and
     * breaks existing tests.  See the regression test generics/method_23p.
     */
    public void addEqualConstraint(TypeDecl T, TypeDecl A) {
      ConstraintSet set = constraintsMap.get(T);
      set.equal.add(A);
    }
  /**
   * A &lt;&lt; F : A is convertible to F by method invocation conversion.
   * 
   * <p>Note: convertibleTo and convertibleFrom are not symmetrical.
   * They differ in which side of the relation contains the type variables to
   * be inferred.
   * 
   * @param A actual argument type.
   * @param F formal argument type (target type), containing type variables to be inferred.
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:356
   */
  

    /**
     * A &lt;&lt; F : A is convertible to F by method invocation conversion.
     *
     * <p>Note: convertibleTo and convertibleFrom are not symmetrical.
     * They differ in which side of the relation contains the type variables to
     * be inferred.
     *
     * @param A actual argument type.
     * @param F formal argument type (target type), containing type variables to be inferred.
     */
    public void convertibleTo(TypeDecl A, TypeDecl F) {
      if (!F.involvesTypeParameters()) {
        // F does not involve a type parameter Tj. No constraint is implied on Tj.
        return;
      }
      if (A.isNull()) {
        // A is the type of null. No constraint is implied on Tj.
        return;
      }
      if (A.isUnboxedPrimitive()) {
        // A is a primitive type. A is converted to a reference type U
        // via boxing conversion and this algorithm is applied recursively to
        // the constraint U << F.
        TypeDecl U = A.boxed();
        convertibleTo(U, F);
      } else if (F instanceof TypeVariable) {
        // F = Tj implies the constraint Tj :> A.
        if (typeVariables.contains(F)) {
          addSupertypeConstraint(F, A);
        }
      } else if (F.isArrayDecl()) {
        // If F = U[], where the type U involves Tj, then if A is an array type
        // V[] or a type variable with an upper bound that is an array type
        // V[], where V is a reference type, this algorithm is applied
        // recursively to the constraint V << U.
        TypeDecl U = F.componentType();
        if (!U.involvesTypeParameters()) {
          return;
        }
        if (A.isArrayDecl()) {
          TypeDecl V = A.componentType();
          if (V.isReferenceType()) {
            convertibleTo(V, U);
          }
        } else if (A.isTypeVariable()) {
          TypeVariable t = (TypeVariable) A;
          for (int i = 0; i < t.getNumTypeBound(); i++) {
            TypeDecl typeBound = t.getTypeBound(i).type();
            if (typeBound.isArrayDecl()
                && typeBound.componentType().isReferenceType()) {
              TypeDecl V = typeBound.componentType();
              convertibleTo(V, U);
            }
          }
        }
      } else if (F instanceof ParTypeDecl && !F.isRawType()) {
        ParTypeDecl PF = (ParTypeDecl) F;
        for (ParTypeDecl PA : parameterizedSupertypes(A)) {
          if (PF.genericDecl() == PA.genericDecl()) {
            if (A.isRawType()) {
              rawAccess = true;
            } else {
              java.util.List<TypeDecl> pfArgs = PF.getParameterization().args;
              java.util.List<TypeDecl> paArgs = PA.getParameterization().args;
              for (int i = 0; i < pfArgs.size(); i++) {
                TypeDecl T = pfArgs.get(i);
                if (T.involvesTypeParameters()) {
                  if (!T.isWildcard()) {
                    TypeDecl U = T;
                    TypeDecl V = paArgs.get(i);
                    constraintEqual(V, U);
                  } else if (T instanceof WildcardExtendsType) {
                    TypeDecl U = ((WildcardExtendsType) T).extendsType();
                    TypeDecl S = paArgs.get(i);
                    if (!S.isWildcard()) {
                      TypeDecl V = S;
                      convertibleTo(V, U);
                    } else if (S instanceof WildcardExtendsType) {
                      TypeDecl V = ((WildcardExtendsType) S).extendsType();
                      convertibleTo(V, U);
                    }
                  } else if (T instanceof WildcardSuperType) {
                    TypeDecl U = ((WildcardSuperType) T).superType();
                    TypeDecl S = paArgs.get(i);
                    if (!S.isWildcard()) {
                      TypeDecl V = S;
                      convertibleFrom(V, U);
                    } else if (S instanceof WildcardSuperType) {
                      TypeDecl V = ((WildcardSuperType) S).superType();
                      convertibleFrom(V, U);
                    }
                  }
                }
              }
            }
            break;
          }
        }
      }
    }
  /**
   * A &gt;&gt; F : F is convertible to A by method invocation conversion.
   * 
   * <p>Note: convertibleTo and convertibleFrom are not symmetrical.
   * They differ in which side of the relation contains the type variables to
   * be inferred.
   * 
   * @param A actual argument type.
   * @param F formal argument type (target type), containing type variables to be inferred.
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:457
   */
  

    /**
     * A &gt;&gt; F : F is convertible to A by method invocation conversion.
     *
     * <p>Note: convertibleTo and convertibleFrom are not symmetrical.
     * They differ in which side of the relation contains the type variables to
     * be inferred.
     *
     * @param A actual argument type.
     * @param F formal argument type (target type), containing type variables to be inferred.
     */
    public void convertibleFrom(TypeDecl A, TypeDecl F) {
      if (!F.involvesTypeParameters()) {
        // F does not involve a type parameter Tj. No constraint is implied on Tj.
        return;
      } else if (A.isNull()) {
        // A is the type of null. No constraint is implied on Tj.
        return;
      } else if (F instanceof TypeVariable) {
        if (typeVariables.contains(F)) {
          addSubtypeConstraint(F, A);
        }
      } else if (F.isArrayDecl()) {
        TypeDecl U = F.componentType();
        if (A.isArrayDecl()) {
          TypeDecl V = A.componentType();
          convertibleFrom(V, U);
        } else if (A.isTypeVariable()) {
          TypeVariable t = (TypeVariable) A;
          for (int i = 0; i < t.getNumTypeBound(); i++) {
            TypeDecl typeBound = t.getTypeBound(i).type();
            if (typeBound.isArrayDecl()
                && typeBound.componentType().isReferenceType()) {
              TypeDecl V = typeBound.componentType();
              convertibleFrom(V, U);
            }
          }
        }
      } else if (F instanceof ParTypeDecl && !F.isRawType()
          && A instanceof ParTypeDecl && !A.isRawType()) {
        ParTypeDecl PF = (ParTypeDecl) F;
        ParTypeDecl PA = (ParTypeDecl) A;
        java.util.List<TypeDecl> pfArgs = PF.getParameterization().args;
        java.util.List<TypeDecl> paArgs = PA.getParameterization().args;
        TypeDecl G = PF.genericDecl();
        TypeDecl H = PA.genericDecl();
        if (G.subtype(H)) {
          for (int i = 0; i < pfArgs.size(); i++) {
            TypeDecl T = pfArgs.get(i);
            if (T.involvesTypeParameters()) {
              // F has the form G<...,U,...> where U is a type expression that involves Tj.
              if (!T.isWildcard()) {
                TypeDecl U = T;
                if (H != G) {
                  for (ParTypeDecl V : parameterizedSupertypes(F)) {
                    if (V.genericDecl() == H) {
                      if (!V.isRawType()) {
                        // TODO(joqvist): must substitute type parameter i of G for T in H to get V!
                        // See JLS7 \u00a715.12.2.7: https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.12.2.7
                        if (F.subtype((TypeDecl) V)) {
                          convertibleFrom(A, (TypeDecl) V);
                        }
                      }
                      break;
                    }
                  }
                } else if (pfArgs.size() == paArgs.size()) {
                  TypeDecl X = paArgs.get(i);
                  if (!X.isWildcard()) {
                    TypeDecl W = X;
                    constraintEqual(W, U);
                  } else if (X instanceof WildcardExtendsType) {
                    TypeDecl W = ((WildcardExtendsType) X).extendsType();
                    convertibleFrom(W, U);
                  } else if (X instanceof WildcardSuperType) {
                    TypeDecl W = ((WildcardSuperType) X).superType();
                    convertibleTo(W, U);
                  }
                }
              } else if (T instanceof WildcardExtendsType) {
                // F has the form G<..., ? extends U, ...> where U is a type expression
                // that involves Tj.
                TypeDecl U = ((WildcardExtendsType) T).extendsType();
                if (H != G) {
                  for (ParTypeDecl V : parameterizedSupertypes(F)) {
                    if (V.genericDecl() == H) {
                      if (!V.isRawType()) {
                        // Replace type argument Un with ? extends Un in V.
                        ArrayList<TypeDecl> list = new ArrayList<TypeDecl>();
                        for (TypeDecl vArg : V.getParameterization().args) {
                          list.add(vArg.asWildcardExtends());
                        }
                        TypeDecl typeV = ((GenericTypeDecl) H).lookupParTypeDecl(list);
                        convertibleFrom(A, typeV);
                      }
                      break;
                    }
                  }
                } else if (pfArgs.size() == paArgs.size()) {
                  TypeDecl X = paArgs.get(i);
                  if (X instanceof WildcardExtendsType) {
                    TypeDecl W = ((WildcardExtendsType) X).extendsType();
                    convertibleFrom(W, U);
                  }
                }
              } else if (T instanceof WildcardSuperType) {
                // F has the form G<..., ? super U, ...> where U is a type expression
                // that involves Tj.
                TypeDecl U = ((WildcardSuperType) T).superType();
                if (H != G) {
                  for (ParTypeDecl V : parameterizedSupertypes(F)) {
                    if (V.genericDecl() == H) {
                      if (!V.isRawType()) {
                        // Replace type argument Un with ? super Un in V.
                        ArrayList<TypeDecl> list = new ArrayList<TypeDecl>();
                        for (TypeDecl vArg : V.getParameterization().args) {
                          list.add(vArg.asWildcardExtends());
                        }
                        TypeDecl typeV = ((GenericTypeDecl) H).lookupParTypeDecl(list);
                        convertibleFrom(A, typeV);
                      }
                      break;
                    }
                  }
                } else if (pfArgs.size() == paArgs.size()) {
                  TypeDecl X = paArgs.get(i);
                  if (X instanceof WildcardSuperType) {
                    TypeDecl W = ((WildcardSuperType) X).superType();
                    convertibleTo(W, U);
                  }
                }
              }
            }
          }
        }
      } else if (F.isRawType()) {
        rawAccess = true;
      }
    }
  /**
   * T = A : T and A are the same type.
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:589
   */
  

    /**
     * T = A : T and A are the same type.
     */
    public void constraintEqual(TypeDecl A, TypeDecl F) {
      if (!F.involvesTypeParameters()) {
        // F does not involve a type parameter Tj. No constraint is implied on Tj.
        return;
      } else if (A.isNull()) {
        // A is the type of null. No constraint is implied on Tj.
        return;
      } else if (F instanceof TypeVariable) {
        if (typeVariables.contains(F)) {
          addEqualConstraint(F, A);
        }
      } else if (F.isArrayDecl()) {
        TypeDecl U = F.componentType();
        if (A.isArrayDecl()) {
          TypeDecl V = A.componentType();
          constraintEqual(V, U);
        } else if (A.isTypeVariable()) {
          TypeVariable t = (TypeVariable) A;
          for (int i = 0; i < t.getNumTypeBound(); i++) {
            TypeDecl typeBound = t.getTypeBound(i).type();
            if (typeBound.isArrayDecl()
                && typeBound.componentType().isReferenceType()) {
              TypeDecl V = typeBound.componentType();
              constraintEqual(V, U);
            }
          }
        }
      } else if (F instanceof ParTypeDecl && !F.isRawType()
          && A instanceof ParTypeDecl) {
        ParTypeDecl PF = (ParTypeDecl) F;
        ParTypeDecl PA = (ParTypeDecl) A;
        java.util.List<TypeDecl> pfArgs = PF.getParameterization().args;
        java.util.List<TypeDecl> paArgs = PA.getParameterization().args;
        if (PF.genericDecl() == PA.genericDecl()) {
          if (A.isRawType()) {
            rawAccess = true;
          } else {
            for (int i = 0; i < pfArgs.size(); i++) {
              TypeDecl T = pfArgs.get(i);
              if (T.involvesTypeParameters()) {
                if (!T.isWildcard()) {
                  TypeDecl U = T;
                  TypeDecl V = paArgs.get(i);
                  constraintEqual(V, U);
                } else if (T instanceof WildcardExtendsType) {
                  TypeDecl U = ((WildcardExtendsType) T).extendsType();
                  TypeDecl S = paArgs.get(i);
                  if (S instanceof WildcardExtendsType) {
                    TypeDecl V = ((WildcardExtendsType) S).extendsType();
                    constraintEqual(V, U);
                  }
                } else if (T instanceof WildcardSuperType) {
                  TypeDecl U = ((WildcardSuperType) T).superType();
                  TypeDecl S = paArgs.get(i);
                  if (S instanceof WildcardSuperType) {
                    TypeDecl V = ((WildcardSuperType) S).superType();
                    constraintEqual(V, U);
                  }
                }
              }
            }
          }
        }
      }
    }

}
