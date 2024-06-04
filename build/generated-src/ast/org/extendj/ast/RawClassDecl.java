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
 * The superclass and implemented interfaces in a raw type are also raw types.
 * @ast node
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/grammar/Generics.ast:28
 * @astdecl RawClassDecl : ParClassDecl ::= Modifiers <ID:String> TypeParameter:TypeVariable* <Parameterization:Parameterization> [SuperClass:Access] Implements:Access* [ImplicitConstructor:ConstructorDecl] SubstTypeParam:TypeVariable* BodyDecl*;
 * @production RawClassDecl : {@link ParClassDecl};

 */
public class RawClassDecl extends ParClassDecl implements Cloneable {
  /**
   * @declaredat ASTNode:1
   */
  public RawClassDecl() {
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
    children = new ASTNode[7];
    setChild(new List(), 1);
    setChild(new Opt(), 2);
    setChild(new List(), 3);
    setChild(new Opt(), 4);
    setChild(new List(), 5);
    setChild(new List(), 6);
  }
  /**
   * @declaredat ASTNode:19
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Modifiers", "ID", "TypeParameter", "Parameterization", "SuperClass", "Implements"},
    type = {"Modifiers", "String", "List<TypeVariable>", "Parameterization", "Opt<Access>", "List<Access>"},
    kind = {"Child", "Token", "List", "Token", "Opt", "List"}
  )
  public RawClassDecl(Modifiers p0, String p1, List<TypeVariable> p2, Parameterization p3, Opt<Access> p4, List<Access> p5) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setParameterization(p3);
    setChild(p4, 2);
    setChild(p5, 3);
  }
  /**
   * @declaredat ASTNode:32
   */
  public RawClassDecl(Modifiers p0, beaver.Symbol p1, List<TypeVariable> p2, Parameterization p3, Opt<Access> p4, List<Access> p5) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setParameterization(p3);
    setChild(p4, 2);
    setChild(p5, 3);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:41
   */
  protected int numChildren() {
    return 4;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:47
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:51
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    isRawDecl_reset();
    getGenericHostDecl_reset();
    getBodyDeclList_reset();
    subtype_TypeDecl_reset();
    firstTypeArgument_reset();
    strictSubtype_TypeDecl_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:61
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:66
   */
  public RawClassDecl clone() throws CloneNotSupportedException {
    RawClassDecl node = (RawClassDecl) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:71
   */
  public RawClassDecl copy() {
    try {
      RawClassDecl node = (RawClassDecl) clone();
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
   * @declaredat ASTNode:90
   */
  @Deprecated
  public RawClassDecl fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:100
   */
  public RawClassDecl treeCopyNoTransform() {
    RawClassDecl tree = (RawClassDecl) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 4:
          tree.children[i] = new Opt();
          continue;
        case 5:
        case 6:
          tree.children[i] = new List();
          continue;
        }
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
   * @declaredat ASTNode:129
   */
  public RawClassDecl treeCopy() {
    RawClassDecl tree = (RawClassDecl) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 4:
          tree.children[i] = new Opt();
          continue;
        case 5:
        case 6:
          tree.children[i] = new List();
          continue;
        }
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
  public RawClassDecl setModifiers(Modifiers node) {
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
  public RawClassDecl setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public RawClassDecl setID(beaver.Symbol symbol) {
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
   * Replaces the TypeParameter list.
   * @param list The new list node to be used as the TypeParameter list.
   * @apilevel high-level
   */
  public RawClassDecl setTypeParameterList(List<TypeVariable> list) {
    setChild(list, 1);
    return this;
  }
  /**
   * Retrieves the number of children in the TypeParameter list.
   * @return Number of children in the TypeParameter list.
   * @apilevel high-level
   */
  public int getNumTypeParameter() {
    return getTypeParameterList().getNumChild();
  }
  /**
   * Retrieves the number of children in the TypeParameter list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the TypeParameter list.
   * @apilevel low-level
   */
  public int getNumTypeParameterNoTransform() {
    return getTypeParameterListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the TypeParameter list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the TypeParameter list.
   * @apilevel high-level
   */
  public TypeVariable getTypeParameter(int i) {
    return (TypeVariable) getTypeParameterList().getChild(i);
  }
  /**
   * Check whether the TypeParameter list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasTypeParameter() {
    return getTypeParameterList().getNumChild() != 0;
  }
  /**
   * Append an element to the TypeParameter list.
   * @param node The element to append to the TypeParameter list.
   * @apilevel high-level
   */
  public RawClassDecl addTypeParameter(TypeVariable node) {
    List<TypeVariable> list = (parent == null) ? getTypeParameterListNoTransform() : getTypeParameterList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public RawClassDecl addTypeParameterNoTransform(TypeVariable node) {
    List<TypeVariable> list = getTypeParameterListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the TypeParameter list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public RawClassDecl setTypeParameter(TypeVariable node, int i) {
    List<TypeVariable> list = getTypeParameterList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the TypeParameter list.
   * @return The node representing the TypeParameter list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="TypeParameter")
  public List<TypeVariable> getTypeParameterList() {
    List<TypeVariable> list = (List<TypeVariable>) getChild(1);
    return list;
  }
  /**
   * Retrieves the TypeParameter list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeParameter list.
   * @apilevel low-level
   */
  public List<TypeVariable> getTypeParameterListNoTransform() {
    return (List<TypeVariable>) getChildNoTransform(1);
  }
  /**
   * @return the element at index {@code i} in the TypeParameter list without
   * triggering rewrites.
   */
  public TypeVariable getTypeParameterNoTransform(int i) {
    return (TypeVariable) getTypeParameterListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the TypeParameter list.
   * @return The node representing the TypeParameter list.
   * @apilevel high-level
   */
  public List<TypeVariable> getTypeParameters() {
    return getTypeParameterList();
  }
  /**
   * Retrieves the TypeParameter list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeParameter list.
   * @apilevel low-level
   */
  public List<TypeVariable> getTypeParametersNoTransform() {
    return getTypeParameterListNoTransform();
  }
  /**
   * Replaces the lexeme Parameterization.
   * @param value The new value for the lexeme Parameterization.
   * @apilevel high-level
   */
  public RawClassDecl setParameterization(Parameterization value) {
    tokenParameterization_Parameterization = value;
    return this;
  }
  /**
   * Retrieves the value for the lexeme Parameterization.
   * @return The value for the lexeme Parameterization.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Token(name="Parameterization")
  public Parameterization getParameterization() {
    return tokenParameterization_Parameterization;
  }
  /**
   * Replaces the optional node for the SuperClass child. This is the <code>Opt</code>
   * node containing the child SuperClass, not the actual child!
   * @param opt The new node to be used as the optional node for the SuperClass child.
   * @apilevel low-level
   */
  public RawClassDecl setSuperClassOpt(Opt<Access> opt) {
    setChild(opt, 2);
    return this;
  }
  /**
   * Replaces the (optional) SuperClass child.
   * @param node The new node to be used as the SuperClass child.
   * @apilevel high-level
   */
  public RawClassDecl setSuperClass(Access node) {
    getSuperClassOpt().setChild(node, 0);
    return this;
  }
  /**
   * Check whether the optional SuperClass child exists.
   * @return {@code true} if the optional SuperClass child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasSuperClass() {
    return getSuperClassOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) SuperClass child.
   * @return The SuperClass child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public Access getSuperClass() {
    return (Access) getSuperClassOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for the SuperClass child. This is the <code>Opt</code> node containing the child SuperClass, not the actual child!
   * @return The optional node for child the SuperClass child.
   * @apilevel low-level
   */
  @ASTNodeAnnotation.OptChild(name="SuperClass")
  public Opt<Access> getSuperClassOpt() {
    return (Opt<Access>) getChild(2);
  }
  /**
   * Retrieves the optional node for child SuperClass. This is the <code>Opt</code> node containing the child SuperClass, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child SuperClass.
   * @apilevel low-level
   */
  public Opt<Access> getSuperClassOptNoTransform() {
    return (Opt<Access>) getChildNoTransform(2);
  }
  /**
   * Replaces the Implements list.
   * @param list The new list node to be used as the Implements list.
   * @apilevel high-level
   */
  public RawClassDecl setImplementsList(List<Access> list) {
    setChild(list, 3);
    return this;
  }
  /**
   * Retrieves the number of children in the Implements list.
   * @return Number of children in the Implements list.
   * @apilevel high-level
   */
  public int getNumImplements() {
    return getImplementsList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Implements list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the Implements list.
   * @apilevel low-level
   */
  public int getNumImplementsNoTransform() {
    return getImplementsListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Implements list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Implements list.
   * @apilevel high-level
   */
  public Access getImplements(int i) {
    return (Access) getImplementsList().getChild(i);
  }
  /**
   * Check whether the Implements list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasImplements() {
    return getImplementsList().getNumChild() != 0;
  }
  /**
   * Append an element to the Implements list.
   * @param node The element to append to the Implements list.
   * @apilevel high-level
   */
  public RawClassDecl addImplements(Access node) {
    List<Access> list = (parent == null) ? getImplementsListNoTransform() : getImplementsList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public RawClassDecl addImplementsNoTransform(Access node) {
    List<Access> list = getImplementsListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the Implements list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public RawClassDecl setImplements(Access node, int i) {
    List<Access> list = getImplementsList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the Implements list.
   * @return The node representing the Implements list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Implements")
  public List<Access> getImplementsList() {
    List<Access> list = (List<Access>) getChild(3);
    return list;
  }
  /**
   * Retrieves the Implements list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Implements list.
   * @apilevel low-level
   */
  public List<Access> getImplementsListNoTransform() {
    return (List<Access>) getChildNoTransform(3);
  }
  /**
   * @return the element at index {@code i} in the Implements list without
   * triggering rewrites.
   */
  public Access getImplementsNoTransform(int i) {
    return (Access) getImplementsListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Implements list.
   * @return The node representing the Implements list.
   * @apilevel high-level
   */
  public List<Access> getImplementss() {
    return getImplementsList();
  }
  /**
   * Retrieves the Implements list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Implements list.
   * @apilevel low-level
   */
  public List<Access> getImplementssNoTransform() {
    return getImplementsListNoTransform();
  }
  /**
   * Replaces the (optional) ImplicitConstructor child.
   * @param node The new node to be used as the ImplicitConstructor child.
   * @apilevel high-level
   */
  public RawClassDecl setImplicitConstructor(ConstructorDecl node) {
    getImplicitConstructorOpt().setChild(node, 0);
    return this;
  }
  /**
   * Check whether the optional ImplicitConstructor child exists.
   * @return {@code true} if the optional ImplicitConstructor child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasImplicitConstructor() {
    return getImplicitConstructorOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) ImplicitConstructor child.
   * @return The ImplicitConstructor child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public ConstructorDecl getImplicitConstructor() {
    return (ConstructorDecl) getImplicitConstructorOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for child ImplicitConstructor. This is the <code>Opt</code> node containing the child ImplicitConstructor, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child ImplicitConstructor.
   * @apilevel low-level
   */
  public Opt<ConstructorDecl> getImplicitConstructorOptNoTransform() {
    return (Opt<ConstructorDecl>) getChildNoTransform(4);
  }
  /**
   * Retrieves the child position of the optional child ImplicitConstructor.
   * @return The the child position of the optional child ImplicitConstructor.
   * @apilevel low-level
   */
  protected int getImplicitConstructorOptChildPosition() {
    return 4;
  }
  /**
   * Retrieves the number of children in the SubstTypeParam list.
   * @return Number of children in the SubstTypeParam list.
   * @apilevel high-level
   */
  public int getNumSubstTypeParam() {
    return getSubstTypeParamList().getNumChild();
  }
  /**
   * Retrieves the number of children in the SubstTypeParam list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the SubstTypeParam list.
   * @apilevel low-level
   */
  public int getNumSubstTypeParamNoTransform() {
    return getSubstTypeParamListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the SubstTypeParam list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the SubstTypeParam list.
   * @apilevel high-level
   */
  public TypeVariable getSubstTypeParam(int i) {
    return (TypeVariable) getSubstTypeParamList().getChild(i);
  }
  /**
   * Check whether the SubstTypeParam list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasSubstTypeParam() {
    return getSubstTypeParamList().getNumChild() != 0;
  }
  /**
   * Append an element to the SubstTypeParam list.
   * @param node The element to append to the SubstTypeParam list.
   * @apilevel high-level
   */
  public RawClassDecl addSubstTypeParam(TypeVariable node) {
    List<TypeVariable> list = (parent == null) ? getSubstTypeParamListNoTransform() : getSubstTypeParamList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public RawClassDecl addSubstTypeParamNoTransform(TypeVariable node) {
    List<TypeVariable> list = getSubstTypeParamListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the SubstTypeParam list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public RawClassDecl setSubstTypeParam(TypeVariable node, int i) {
    List<TypeVariable> list = getSubstTypeParamList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the child position of the SubstTypeParam list.
   * @return The the child position of the SubstTypeParam list.
   * @apilevel low-level
   */
  protected int getSubstTypeParamListChildPosition() {
    return 5;
  }
  /**
   * Retrieves the SubstTypeParam list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the SubstTypeParam list.
   * @apilevel low-level
   */
  public List<TypeVariable> getSubstTypeParamListNoTransform() {
    return (List<TypeVariable>) getChildNoTransform(5);
  }
  /**
   * @return the element at index {@code i} in the SubstTypeParam list without
   * triggering rewrites.
   */
  public TypeVariable getSubstTypeParamNoTransform(int i) {
    return (TypeVariable) getSubstTypeParamListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the SubstTypeParam list.
   * @return The node representing the SubstTypeParam list.
   * @apilevel high-level
   */
  public List<TypeVariable> getSubstTypeParams() {
    return getSubstTypeParamList();
  }
  /**
   * Retrieves the SubstTypeParam list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the SubstTypeParam list.
   * @apilevel low-level
   */
  public List<TypeVariable> getSubstTypeParamsNoTransform() {
    return getSubstTypeParamListNoTransform();
  }
  /**
   * This method should not be called. This method throws an exception due to
   * the corresponding child being an NTA shadowing a non-NTA child.
   * @param node
   * @apilevel internal
   */
  public RawClassDecl setBodyDeclList(List<BodyDecl> node) {
    throw new Error("Can not replace NTA child BodyDeclList in RawClassDecl!");
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
  public RawClassDecl addBodyDecl(BodyDecl node) {
    List<BodyDecl> list = (parent == null) ? getBodyDeclListNoTransform() : getBodyDeclList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public RawClassDecl addBodyDeclNoTransform(BodyDecl node) {
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
  public RawClassDecl setBodyDecl(BodyDecl node, int i) {
    List<BodyDecl> list = getBodyDeclList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the child position of the BodyDecl list.
   * @return The the child position of the BodyDecl list.
   * @apilevel low-level
   */
  protected int getBodyDeclListChildPosition() {
    return 6;
  }
  /**
   * Retrieves the BodyDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the BodyDecl list.
   * @apilevel low-level
   */
  public List<BodyDecl> getBodyDeclListNoTransform() {
    return (List<BodyDecl>) getChildNoTransform(6);
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
  /** @apilevel internal */
  private void isRawDecl_reset() {
    isRawDecl_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isRawDecl_computed = null;

  /** @apilevel internal */
  protected boolean isRawDecl_value;

  /**
   * @attribute syn
   * @aspect CHA
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:61
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CHA", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:60")
  public boolean isRawDecl() {
    ASTState state = state();
    if (isRawDecl_computed == ASTState.NON_CYCLE || isRawDecl_computed == state().cycle()) {
      return isRawDecl_value;
    }
    isRawDecl_value = true;
    if (state().inCircle()) {
      isRawDecl_computed = state().cycle();
    
    } else {
      isRawDecl_computed = ASTState.NON_CYCLE;
    
    }
    return isRawDecl_value;
  }
  /** @apilevel internal */
  private void getGenericHostDecl_reset() {
    getGenericHostDecl_computed = null;
    getGenericHostDecl_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle getGenericHostDecl_computed = null;

  /** @apilevel internal */
  protected TypeDecl getGenericHostDecl_value;

  /**
   * @attribute syn
   * @aspect CHA
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:70
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CHA", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:67")
  public TypeDecl getGenericHostDecl() {
    ASTState state = state();
    if (getGenericHostDecl_computed == ASTState.NON_CYCLE || getGenericHostDecl_computed == state().cycle()) {
      return getGenericHostDecl_value;
    }
    getGenericHostDecl_value = genericDecl();
    if (state().inCircle()) {
      getGenericHostDecl_computed = state().cycle();
    
    } else {
      getGenericHostDecl_computed = ASTState.NON_CYCLE;
    
    }
    return getGenericHostDecl_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsParTypeDecl.jrag:70
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsParTypeDecl.jrag:55")
  public String nameWithArgs() {
    String nameWithArgs_value = name();
    return nameWithArgs_value;
  }
  /**
   * @attribute syn
   * @aspect ReifiableTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/ReifiableTypes.jrag:76
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ReifiableTypes", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/ReifiableTypes.jrag:39")
  public boolean isReifiable() {
    boolean isReifiable_value = true;
    return isReifiable_value;
  }
  /**
   * @attribute syn
   * @aspect Generics
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:85
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NestedTypes", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:686")
  public TypeDecl hostType() {
    TypeDecl hostType_value = original();
    return hostType_value;
  }
  /**
   * @attribute syn
   * @aspect Generics
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:358
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Generics", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:342")
  public boolean isParameterizedType() {
    boolean isParameterizedType_value = false;
    return isParameterizedType_value;
  }
  /**
   * @attribute syn
   * @aspect Generics
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:368
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Generics", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:343")
  public boolean isRawType() {
    boolean isRawType_value = true;
    return isRawType_value;
  }
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:903
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:881")
  public boolean sameSignature(Access a) {
    boolean sameSignature_Access_value = a instanceof TypeAccess && a.type() == this;
    return sameSignature_Access_value;
  }
  /** @apilevel internal */
  private void getBodyDeclList_reset() {
    getBodyDeclList_computed = false;
    
    getBodyDeclList_value = null;
  }
  /** @apilevel internal */
  protected boolean getBodyDeclList_computed = false;

  /** @apilevel internal */
  protected List<BodyDecl> getBodyDeclList_value;

  /**
   * @attribute syn nta
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1693
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1693")
  public List<BodyDecl> getBodyDeclList() {
    ASTState state = state();
    if (getBodyDeclList_computed) {
      return (List<BodyDecl>) getChild(getBodyDeclListChildPosition());
    }
    state().enterLazyAttribute();
    getBodyDeclList_value = original().erasedBodyDecls();
    setChild(getBodyDeclList_value, getBodyDeclListChildPosition());
    getBodyDeclList_computed = true;
    state().leaveLazyAttribute();
    List<BodyDecl> node = (List<BodyDecl>) this.getChild(getBodyDeclListChildPosition());
    return node;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:43
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:41")
  public boolean supertypeGenericClassDecl(GenericClassDecl type) {
    boolean supertypeGenericClassDecl_GenericClassDecl_value = type.subtype(genericDecl().original());
    return supertypeGenericClassDecl_GenericClassDecl_value;
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
        new_subtype_TypeDecl_value = type.supertypeRawClassDecl(this);
        if (((Boolean)_value.value) != new_subtype_TypeDecl_value) {
          state.setChangeInCycle();
          _value.value = new_subtype_TypeDecl_value;
        }
      } while (state.testAndClearChangeInCycle());
      subtype_TypeDecl_values.put(_parameters, new_subtype_TypeDecl_value);
      state.startLastCycle();
      boolean $tmp = type.supertypeRawClassDecl(this);

      state.leaveCircle();
      return new_subtype_TypeDecl_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_subtype_TypeDecl_value = type.supertypeRawClassDecl(this);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:138
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Subtyping", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:471")
  public boolean supertypeClassDecl(ClassDecl type) {
    boolean supertypeClassDecl_ClassDecl_value = type.subtype(genericDecl().original());
    return supertypeClassDecl_ClassDecl_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:139
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Subtyping", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:486")
  public boolean supertypeInterfaceDecl(InterfaceDecl type) {
    boolean supertypeInterfaceDecl_InterfaceDecl_value = type.subtype(genericDecl().original());
    return supertypeInterfaceDecl_InterfaceDecl_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:141
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:152")
  public boolean supertypeParClassDecl(ParClassDecl type) {
    boolean supertypeParClassDecl_ParClassDecl_value = type.genericDecl().original().subtype(genericDecl().original());
    return supertypeParClassDecl_ParClassDecl_value;
  }
  /** @apilevel internal */
  private void firstTypeArgument_reset() {
    firstTypeArgument_computed = null;
    firstTypeArgument_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle firstTypeArgument_computed = null;

  /** @apilevel internal */
  protected TypeDecl firstTypeArgument_value;

  /**
   * @attribute syn
   * @aspect EnhancedFor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/EnhancedFor.jrag:133
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="EnhancedFor", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/EnhancedFor.jrag:129")
  public TypeDecl firstTypeArgument() {
    ASTState state = state();
    if (firstTypeArgument_computed == ASTState.NON_CYCLE || firstTypeArgument_computed == state().cycle()) {
      return firstTypeArgument_value;
    }
    firstTypeArgument_value = typeObject();
    if (state().inCircle()) {
      firstTypeArgument_computed = state().cycle();
    
    } else {
      firstTypeArgument_computed = ASTState.NON_CYCLE;
    
    }
    return firstTypeArgument_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:41
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:39")
  public boolean strictSupertypeGenericClassDecl(GenericClassDecl type) {
    boolean strictSupertypeGenericClassDecl_GenericClassDecl_value = type.strictSubtype(genericDecl().original());
    return strictSupertypeGenericClassDecl_GenericClassDecl_value;
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
        new_strictSubtype_TypeDecl_value = type.strictSupertypeRawClassDecl(this);
        if (((Boolean)_value.value) != new_strictSubtype_TypeDecl_value) {
          state.setChangeInCycle();
          _value.value = new_strictSubtype_TypeDecl_value;
        }
      } while (state.testAndClearChangeInCycle());
      strictSubtype_TypeDecl_values.put(_parameters, new_strictSubtype_TypeDecl_value);
      state.startLastCycle();
      boolean $tmp = type.strictSupertypeRawClassDecl(this);

      state.leaveCircle();
      return new_strictSubtype_TypeDecl_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_strictSubtype_TypeDecl_value = type.strictSupertypeRawClassDecl(this);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:134
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:376")
  public boolean strictSupertypeClassDecl(ClassDecl type) {
    boolean strictSupertypeClassDecl_ClassDecl_value = type.strictSubtype(genericDecl().original());
    return strictSupertypeClassDecl_ClassDecl_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:136
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:395")
  public boolean strictSupertypeInterfaceDecl(InterfaceDecl type) {
    boolean strictSupertypeInterfaceDecl_InterfaceDecl_value = type.strictSubtype(genericDecl().original());
    return strictSupertypeInterfaceDecl_InterfaceDecl_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:138
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:149")
  public boolean strictSupertypeParClassDecl(ParClassDecl type) {
    boolean strictSupertypeParClassDecl_ParClassDecl_value = type.genericDecl().original().strictSubtype(genericDecl().original());
    return strictSupertypeParClassDecl_ParClassDecl_value;
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
