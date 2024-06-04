/* This file was generated with JastAdd2 (http://jastadd.org) version 2.3.4-50-gf00c118 */
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
 * Intersection type.
 * Occurs in, for example, conditional expression type analysis.
 * @ast node
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/grammar/Generics.ast:120
 * @astdecl LUBType : ReferenceType ::= Modifiers <ID:String> BodyDecl* TypeBound:Access*;
 * @production LUBType : {@link ReferenceType} ::= <span class="component">{@link Modifiers}</span> <span class="component">&lt;ID:{@link String}&gt;</span> <span class="component">{@link BodyDecl}*</span> <span class="component">TypeBound:{@link Access}*</span>;

 */
public class LUBType extends ReferenceType implements Cloneable {
  /**
   * Erased candidate set.
   * 
   * <p>The erased candidate set for type parameter Tj, EC,
   * is the intersection of all the sets EST(U) for each
   * U in U1...Uk.
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:680
   */
  public static Collection<TypeDecl> EC(ArrayList<TypeDecl> list) {
    Collection<TypeDecl> result = new HashSet<TypeDecl>();
    boolean first = true;
    for (TypeDecl U : list) {
      // Erased supertype set of U.
      Collection<TypeDecl> EST = LUBType.EST(U);
      if (first) {
        result.addAll(EST);
        first = false;
      } else {
        result.retainAll(EST);
      }
    }
    return result;
  }
  /**
   * The minimal erased candidate set for Tj
   * is MEC = {V | V in EC, forall  W != V in EC, not W <: V}
   * @return minimal erased candidate set for Tj
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:701
   */
  public static Collection<TypeDecl> MEC(ArrayList<TypeDecl> list) {
    Collection<TypeDecl> EC = LUBType.EC(list);
    if (EC.size() == 1) {
      return EC;
    }
    Collection<TypeDecl> MEC = new HashSet<TypeDecl>();
    for (TypeDecl V : EC) {
      boolean keep = true;
      for (TypeDecl W : EC) {
        if (!(V instanceof TypeVariable) && V != W && W.subtype(V)) {
          keep = false;
        }
      }
      if (keep) {
        MEC.add(V);
      }
    }
    return MEC;
  }
  /**
   * Relevant invocations of G, Inv(G)
   * Inv(G) = {V | 1 <= i <= k, V in ST(Ui), V = G<...>}
   * @return set of relevant invocations of G, Inv(G)
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:726
   */
  public static Collection<ParTypeDecl> Inv(TypeDecl G, Collection<TypeDecl> Us) {
    Collection<ParTypeDecl> result = new HashSet<ParTypeDecl>();
    for (TypeDecl U : Us) {
      for (TypeDecl V : LUBType.ST(U)) {
        if (V instanceof ParTypeDecl && !V.isRawType() && ((ParTypeDecl) V).genericDecl() == G) {
          result.add((ParTypeDecl) V);
        }
      }
    }
    return result;
  }
  /**
   * @return least containing invocation (lci)
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:741
   */
  public TypeDecl lci(Collection<ParTypeDecl> set, TypeDecl G) {
    ArrayList<TypeDecl> list = new ArrayList<TypeDecl>();
    boolean first = true;
    for (ParTypeDecl decl : set) {
      java.util.List<TypeDecl> declArgs = decl.getParameterization().args;
      if (first) {
        first = false;
        for (TypeDecl arg : declArgs) {
          list.add(arg);
        }
      } else {
        for (int i = 0; i < declArgs.size(); i++) {
          list.set(i, lcta(list.get(i), declArgs.get(i)));
        }
      }
    }
    return ((GenericTypeDecl) G).lookupParTypeDecl(list);
  }
  /**
   * Least containing type arguments.
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:763
   */
  public TypeDecl lcta(TypeDecl X, TypeDecl Y) {
    if (!X.isWildcard() && !Y.isWildcard()) {
      TypeDecl U = X;
      TypeDecl V = Y;
      return U == V ? U : lub(U, V).asWildcardExtends();
    } else if (!X.isWildcard() && Y instanceof WildcardExtendsType) {
      TypeDecl U = X;
      TypeDecl V = ((WildcardExtendsType) Y).extendsType();
      return lub(U, V).asWildcardExtends();
    } else if (!Y.isWildcard() && X instanceof WildcardExtendsType) {
      TypeDecl U = Y;
      TypeDecl V = ((WildcardExtendsType) X).extendsType();
      return lub(U, V).asWildcardExtends();
    } else if (!X.isWildcard() && Y instanceof WildcardSuperType) {
      TypeDecl U = X;
      TypeDecl V = ((WildcardSuperType) Y).superType();
      ArrayList<TypeDecl> bounds = new ArrayList<TypeDecl>();
      bounds.add(U);
      bounds.add(V);
      return GLBTypeFactory.glb(bounds).asWildcardSuper();
    } else if (!Y.isWildcard() && X instanceof WildcardSuperType) {
      TypeDecl U = Y;
      TypeDecl V = ((WildcardSuperType) X).superType();
      ArrayList<TypeDecl> bounds = new ArrayList<TypeDecl>();
      bounds.add(U);
      bounds.add(V);
      return GLBTypeFactory.glb(bounds).asWildcardSuper();
    } else if (X instanceof WildcardExtendsType && Y instanceof WildcardExtendsType) {
      TypeDecl U = ((WildcardExtendsType) X).extendsType();
      TypeDecl V = ((WildcardExtendsType) Y).extendsType();
      return lub(U, V).asWildcardExtends();
    } else if (X instanceof WildcardExtendsType && Y instanceof WildcardSuperType) {
      TypeDecl U = ((WildcardExtendsType) X).extendsType();
      TypeDecl V = ((WildcardSuperType) Y).superType();
      return U == V ? U : U.typeWildcard();
    } else if (Y instanceof WildcardExtendsType && X instanceof WildcardSuperType) {
      TypeDecl U = ((WildcardExtendsType) Y).extendsType();
      TypeDecl V = ((WildcardSuperType) X).superType();
      return U == V ? U : U.typeWildcard();
    } else if (X instanceof WildcardSuperType && Y instanceof WildcardSuperType) {
      TypeDecl U = ((WildcardSuperType) X).superType();
      TypeDecl V = ((WildcardSuperType) Y).superType();
      ArrayList<TypeDecl> bounds = new ArrayList<TypeDecl>();
      bounds.add(U);
      bounds.add(V);
      return GLBTypeFactory.glb(bounds).asWildcardSuper();
    } else {
      throw new Error("lcta not defined for (" + X.getClass().getName()
          + ", " + Y.getClass().getName() + ")");
    }
  }
  /**
   * Computes the least upper bound of two types.
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:818
   */
  public TypeDecl lub(TypeDecl X, TypeDecl Y) {
    ArrayList<TypeDecl> list = new ArrayList<TypeDecl>(2);
    list.add(X);
    list.add(Y);
    return lub(list);
  }
  /**
   * Computes the least upper bound of a list of types.
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:828
   */
  public TypeDecl lub(ArrayList<TypeDecl> list) {
    return lookupLUBType(list);
  }
  /** Computes the erased supertype set of type. 
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:833
   */
  public static Collection<TypeDecl> EST(TypeDecl type) {
    Collection<TypeDecl> result = new HashSet<TypeDecl>();
    for (TypeDecl typeDecl : LUBType.ST(type)) {
      if (typeDecl instanceof TypeVariable) {
        result.add(typeDecl);
      } else {
        result.add(typeDecl.erasure());
      }
    }
    return result;
  }
  /** Computes the supertype set of a type. 
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:846
   */
  public static Collection<TypeDecl> ST(TypeDecl type) {
    Collection<TypeDecl> result = new HashSet<TypeDecl>();
    LUBType.addSupertypes(result, type);
    return result;
  }
  /**
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:852
   */
  public static void addSupertypes(Collection<TypeDecl> set, TypeDecl t) {
    set.add(t);
    if (t instanceof ClassDecl) {
      ClassDecl type = (ClassDecl) t;
      if (type.hasSuperclass()) {
        addSupertypes(set, type.superclass());
      }
      for (int i = 0; i < type.getNumImplements(); i++) {
        addSupertypes(set, type.getImplements(i).type());
      }
    } else if (t instanceof InterfaceDecl) {
      InterfaceDecl type = (InterfaceDecl) t;
      for (int i = 0; i < type.getNumSuperInterface(); i++) {
        addSupertypes(set, type.getSuperInterface(i).type());
      }
      if (type.getNumSuperInterface() == 0) {
        set.add(type.typeObject());
      }
    } else if (t instanceof TypeVariable) {
      TypeVariable type = (TypeVariable) t;
      for (int i = 0; i < type.getNumTypeBound(); i++) {
        addSupertypes(set, type.getTypeBound(i).type());
      }
      if (type.getNumTypeBound() == 0) {
        set.add(type.typeObject());
      }
    } else if (t instanceof LUBType) {
      LUBType type = (LUBType) t;
      for (int i = 0; i < type.getNumTypeBound(); i++) {
        addSupertypes(set, type.getTypeBound(i).type());
      }
      if (type.getNumTypeBound() == 0) {
        set.add(type.typeObject());
      }
    } else if (! (t instanceof NullType)) {
      throw new Error(String.format(
            "Operation not supported for %s, %s",
            t.fullName(), t.getClass().getName()));
    }
  }
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1838
   */
  public Collection<InterfaceDecl> implementedInterfaces() {
    Collection<InterfaceDecl> ret = new HashSet<InterfaceDecl>();
    for (int i = 0; i < getNumTypeBound(); i++) {
      ret.addAll(getTypeBound(i).type().implementedInterfaces());
    }
    return ret;
  }
  /**
   * @declaredat ASTNode:1
   */
  public LUBType() {
    super();
  }
  /**
   * Initializes the child array to the correct size.
   * Initializes List and Opt nta children.
   * @apilevel internal
   * @ast method
   * @declaredat ASTNode:10
   */
  public void init$Children() {
    children = new ASTNode[3];
    setChild(new List(), 1);
    setChild(new List(), 2);
  }
  /**
   * @declaredat ASTNode:15
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Modifiers", "ID", "BodyDecl", "TypeBound"},
    type = {"Modifiers", "String", "List<BodyDecl>", "List<Access>"},
    kind = {"Child", "Token", "List", "List"}
  )
  public LUBType(Modifiers p0, String p1, List<BodyDecl> p2, List<Access> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /**
   * @declaredat ASTNode:26
   */
  public LUBType(Modifiers p0, beaver.Symbol p1, List<BodyDecl> p2, List<Access> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:33
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:39
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:43
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    lub_reset();
    subtype_TypeDecl_reset();
    strictSubtype_TypeDecl_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:50
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:55
   */
  public LUBType clone() throws CloneNotSupportedException {
    LUBType node = (LUBType) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:60
   */
  public LUBType copy() {
    try {
      LUBType node = (LUBType) clone();
      node.parent = null;
      if (children != null) {
        node.children = (ASTNode[]) children.clone();
      }
      return node;
    } catch (CloneNotSupportedException e) {
      throw new Error("Error: clone not supported for " + getClass().getName());
    }
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:79
   */
  @Deprecated
  public LUBType fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:89
   */
  public LUBType treeCopyNoTransform() {
    LUBType tree = (LUBType) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) children[i];
        if (child != null) {
          child = child.treeCopyNoTransform();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:109
   */
  public LUBType treeCopy() {
    LUBType tree = (LUBType) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) getChild(i);
        if (child != null) {
          child = child.treeCopy();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /**
   * Replaces the Modifiers child.
   * @param node The new node to replace the Modifiers child.
   * @apilevel high-level
   */
  public LUBType setModifiers(Modifiers node) {
    setChild(node, 0);
    return this;
  }
  /**
   * Retrieves the Modifiers child.
   * @return The current node used as the Modifiers child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Modifiers")
  public Modifiers getModifiers() {
    return (Modifiers) getChild(0);
  }
  /**
   * Retrieves the Modifiers child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Modifiers child.
   * @apilevel low-level
   */
  public Modifiers getModifiersNoTransform() {
    return (Modifiers) getChildNoTransform(0);
  }
  /**
   * Replaces the lexeme ID.
   * @param value The new value for the lexeme ID.
   * @apilevel high-level
   */
  public LUBType setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public LUBType setID(beaver.Symbol symbol) {
    if (symbol.value != null && !(symbol.value instanceof String))
    throw new UnsupportedOperationException("setID is only valid for String lexemes");
    tokenString_ID = (String)symbol.value;
    IDstart = symbol.getStart();
    IDend = symbol.getEnd();
    return this;
  }
  /**
   * Retrieves the value for the lexeme ID.
   * @return The value for the lexeme ID.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Token(name="ID")
  public String getID() {
    return tokenString_ID != null ? tokenString_ID : "";
  }
  /**
   * Replaces the BodyDecl list.
   * @param list The new list node to be used as the BodyDecl list.
   * @apilevel high-level
   */
  public LUBType setBodyDeclList(List<BodyDecl> list) {
    setChild(list, 1);
    return this;
  }
  /**
   * Retrieves the number of children in the BodyDecl list.
   * @return Number of children in the BodyDecl list.
   * @apilevel high-level
   */
  public int getNumBodyDecl() {
    return getBodyDeclList().getNumChild();
  }
  /**
   * Retrieves the number of children in the BodyDecl list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the BodyDecl list.
   * @apilevel low-level
   */
  public int getNumBodyDeclNoTransform() {
    return getBodyDeclListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the BodyDecl list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the BodyDecl list.
   * @apilevel high-level
   */
  public BodyDecl getBodyDecl(int i) {
    return (BodyDecl) getBodyDeclList().getChild(i);
  }
  /**
   * Check whether the BodyDecl list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasBodyDecl() {
    return getBodyDeclList().getNumChild() != 0;
  }
  /**
   * Append an element to the BodyDecl list.
   * @param node The element to append to the BodyDecl list.
   * @apilevel high-level
   */
  public LUBType addBodyDecl(BodyDecl node) {
    List<BodyDecl> list = (parent == null) ? getBodyDeclListNoTransform() : getBodyDeclList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public LUBType addBodyDeclNoTransform(BodyDecl node) {
    List<BodyDecl> list = getBodyDeclListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the BodyDecl list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public LUBType setBodyDecl(BodyDecl node, int i) {
    List<BodyDecl> list = getBodyDeclList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the BodyDecl list.
   * @return The node representing the BodyDecl list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="BodyDecl")
  public List<BodyDecl> getBodyDeclList() {
    List<BodyDecl> list = (List<BodyDecl>) getChild(1);
    return list;
  }
  /**
   * Retrieves the BodyDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the BodyDecl list.
   * @apilevel low-level
   */
  public List<BodyDecl> getBodyDeclListNoTransform() {
    return (List<BodyDecl>) getChildNoTransform(1);
  }
  /**
   * @return the element at index {@code i} in the BodyDecl list without
   * triggering rewrites.
   */
  public BodyDecl getBodyDeclNoTransform(int i) {
    return (BodyDecl) getBodyDeclListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the BodyDecl list.
   * @return The node representing the BodyDecl list.
   * @apilevel high-level
   */
  public List<BodyDecl> getBodyDecls() {
    return getBodyDeclList();
  }
  /**
   * Retrieves the BodyDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the BodyDecl list.
   * @apilevel low-level
   */
  public List<BodyDecl> getBodyDeclsNoTransform() {
    return getBodyDeclListNoTransform();
  }
  /**
   * Replaces the TypeBound list.
   * @param list The new list node to be used as the TypeBound list.
   * @apilevel high-level
   */
  public LUBType setTypeBoundList(List<Access> list) {
    setChild(list, 2);
    return this;
  }
  /**
   * Retrieves the number of children in the TypeBound list.
   * @return Number of children in the TypeBound list.
   * @apilevel high-level
   */
  public int getNumTypeBound() {
    return getTypeBoundList().getNumChild();
  }
  /**
   * Retrieves the number of children in the TypeBound list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the TypeBound list.
   * @apilevel low-level
   */
  public int getNumTypeBoundNoTransform() {
    return getTypeBoundListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the TypeBound list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the TypeBound list.
   * @apilevel high-level
   */
  public Access getTypeBound(int i) {
    return (Access) getTypeBoundList().getChild(i);
  }
  /**
   * Check whether the TypeBound list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasTypeBound() {
    return getTypeBoundList().getNumChild() != 0;
  }
  /**
   * Append an element to the TypeBound list.
   * @param node The element to append to the TypeBound list.
   * @apilevel high-level
   */
  public LUBType addTypeBound(Access node) {
    List<Access> list = (parent == null) ? getTypeBoundListNoTransform() : getTypeBoundList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public LUBType addTypeBoundNoTransform(Access node) {
    List<Access> list = getTypeBoundListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the TypeBound list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public LUBType setTypeBound(Access node, int i) {
    List<Access> list = getTypeBoundList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the TypeBound list.
   * @return The node representing the TypeBound list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="TypeBound")
  public List<Access> getTypeBoundList() {
    List<Access> list = (List<Access>) getChild(2);
    return list;
  }
  /**
   * Retrieves the TypeBound list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeBound list.
   * @apilevel low-level
   */
  public List<Access> getTypeBoundListNoTransform() {
    return (List<Access>) getChildNoTransform(2);
  }
  /**
   * @return the element at index {@code i} in the TypeBound list without
   * triggering rewrites.
   */
  public Access getTypeBoundNoTransform(int i) {
    return (Access) getTypeBoundListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the TypeBound list.
   * @return The node representing the TypeBound list.
   * @apilevel high-level
   */
  public List<Access> getTypeBounds() {
    return getTypeBoundList();
  }
  /**
   * Retrieves the TypeBound list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeBound list.
   * @apilevel low-level
   */
  public List<Access> getTypeBoundsNoTransform() {
    return getTypeBoundListNoTransform();
  }
  /** @apilevel internal */
  private void lub_reset() {
    lub_computed = null;
    lub_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle lub_computed = null;

  /** @apilevel internal */
  protected TypeDecl lub_value;

  /** Computes the least upper bound type. 
   * @attribute syn
   * @aspect GenericMethodsInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:657
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericMethodsInference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:657")
  public TypeDecl lub() {
    ASTState state = state();
    if (lub_computed == ASTState.NON_CYCLE || lub_computed == state().cycle()) {
      return lub_value;
    }
    lub_value = lub_compute();
    if (state().inCircle()) {
      lub_computed = state().cycle();
    
    } else {
      lub_computed = ASTState.NON_CYCLE;
    
    }
    return lub_value;
  }
  /** @apilevel internal */
  private TypeDecl lub_compute() {
      ArrayList<TypeDecl> list = new ArrayList<TypeDecl>();
      for (int i = 0; i < getNumTypeBound(); i++) {
        list.add(getTypeBound(i).type());
      }
      ArrayList<TypeDecl> bounds = new ArrayList<TypeDecl>();
      for (TypeDecl W : LUBType.MEC(list)) {
        TypeDecl C = W instanceof GenericTypeDecl ? lci(Inv(W, list), W) : W;
        bounds.add(C);
      }
      if (bounds.size() == 1) {
        return bounds.iterator().next();
      }
      return lookupLUBType(bounds);
    }
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1826
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1826")
  public String typeName() {
    {
        if (getNumTypeBound() == 0) {
          return "<NOTYPE>";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getTypeBound(0).type().typeName());
        for (int i = 1; i < getNumTypeBound(); i++) {
          sb.append(" & " + getTypeBound(i).type().typeName());
        }
        return sb.toString();
      }
  }
  /**
   * @attribute syn
   * @aspect TypeConversion
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:39
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeConversion", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:39")
  public boolean wideningConversionTo(TypeDecl type) {
    {
        for (Access bound : getTypeBoundList()) {
          if (bound.type().subtype(type)) {
            return true;
          }
        }
        return false;
      }
  }

  /** @apilevel internal */
  private void subtype_TypeDecl_reset() {
    subtype_TypeDecl_values = null;
  }
  protected java.util.Map subtype_TypeDecl_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="Subtyping", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:455")
  public boolean subtype(TypeDecl type) {
    Object _parameters = type;


    if (subtype_TypeDecl_values == null) subtype_TypeDecl_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (subtype_TypeDecl_values.containsKey(_parameters)) {
      Object _cache = subtype_TypeDecl_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      subtype_TypeDecl_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_subtype_TypeDecl_value;
      do {
        _value.cycle = state.nextCycle();
        new_subtype_TypeDecl_value = type.supertypeLUBType(this);
        if (((Boolean)_value.value) != new_subtype_TypeDecl_value) {
          state.setChangeInCycle();
          _value.value = new_subtype_TypeDecl_value;
        }
      } while (state.testAndClearChangeInCycle());
      subtype_TypeDecl_values.put(_parameters, new_subtype_TypeDecl_value);
      state.startLastCycle();
      boolean $tmp = type.supertypeLUBType(this);

      state.leaveCircle();
      return new_subtype_TypeDecl_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_subtype_TypeDecl_value = type.supertypeLUBType(this);
      if (state.lastCycle()) {
        subtype_TypeDecl_values.put(_parameters, new_subtype_TypeDecl_value);
      }
      if (((Boolean)_value.value) != new_subtype_TypeDecl_value) {
        state.setChangeInCycle();
        _value.value = new_subtype_TypeDecl_value;
      }
      return new_subtype_TypeDecl_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:432
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Subtyping", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:471")
  public boolean supertypeClassDecl(ClassDecl type) {
    boolean supertypeClassDecl_ClassDecl_value = type.subtype(lub());
    return supertypeClassDecl_ClassDecl_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:434
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Subtyping", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:486")
  public boolean supertypeInterfaceDecl(InterfaceDecl type) {
    boolean supertypeInterfaceDecl_InterfaceDecl_value = type.subtype(lub());
    return supertypeInterfaceDecl_InterfaceDecl_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:438
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:438")
  public boolean supertypeGLBType(GLBType type) {
    {
        // TODO(joqvist): changed from Access to TypeDecl, is this correct?
        ArrayList<TypeDecl> bounds = new ArrayList<TypeDecl>(getNumTypeBound());
        for (int i = 0; i < getNumTypeBound(); i++) {
          bounds.add(getTypeBound(i).type());
        }
        return type == lookupGLBType(bounds);
      }
  }

  /** @apilevel internal */
  private void strictSubtype_TypeDecl_reset() {
    strictSubtype_TypeDecl_values = null;
  }
  protected java.util.Map strictSubtype_TypeDecl_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:363")
  public boolean strictSubtype(TypeDecl type) {
    Object _parameters = type;


    if (strictSubtype_TypeDecl_values == null) strictSubtype_TypeDecl_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (strictSubtype_TypeDecl_values.containsKey(_parameters)) {
      Object _cache = strictSubtype_TypeDecl_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      strictSubtype_TypeDecl_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_strictSubtype_TypeDecl_value;
      do {
        _value.cycle = state.nextCycle();
        new_strictSubtype_TypeDecl_value = type.strictSupertypeLUBType(this);
        if (((Boolean)_value.value) != new_strictSubtype_TypeDecl_value) {
          state.setChangeInCycle();
          _value.value = new_strictSubtype_TypeDecl_value;
        }
      } while (state.testAndClearChangeInCycle());
      strictSubtype_TypeDecl_values.put(_parameters, new_strictSubtype_TypeDecl_value);
      state.startLastCycle();
      boolean $tmp = type.strictSupertypeLUBType(this);

      state.leaveCircle();
      return new_strictSubtype_TypeDecl_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_strictSubtype_TypeDecl_value = type.strictSupertypeLUBType(this);
      if (state.lastCycle()) {
        strictSubtype_TypeDecl_values.put(_parameters, new_strictSubtype_TypeDecl_value);
      }
      if (((Boolean)_value.value) != new_strictSubtype_TypeDecl_value) {
        state.setChangeInCycle();
        _value.value = new_strictSubtype_TypeDecl_value;
      }
      return new_strictSubtype_TypeDecl_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:338
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:376")
  public boolean strictSupertypeClassDecl(ClassDecl type) {
    boolean strictSupertypeClassDecl_ClassDecl_value = type.strictSubtype(lub());
    return strictSupertypeClassDecl_ClassDecl_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:339
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:395")
  public boolean strictSupertypeInterfaceDecl(InterfaceDecl type) {
    boolean strictSupertypeInterfaceDecl_InterfaceDecl_value = type.strictSubtype(lub());
    return strictSupertypeInterfaceDecl_InterfaceDecl_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:342
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:342")
  public boolean strictSupertypeGLBType(GLBType type) {
    {
        // TODO(joqvist): changed from Access to TypeDecl, is this correct?
        ArrayList<TypeDecl> bounds = new ArrayList<TypeDecl>(getNumTypeBound());
        for (int i = 0; i < getNumTypeBound(); i++) {
          bounds.add(getTypeBound(i).type());
        }
        return type == lookupGLBType(bounds);
      }
  }
  /** @apilevel internal */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
  /** @apilevel internal */
  public boolean canRewrite() {
    return false;
  }

}
