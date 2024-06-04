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
 * @ast node
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/grammar/GenericMethods.ast:10
 * @astdecl ParConstructorDecl : ConstructorDecl ::= Modifiers <ID:String> Parameter:ParameterDeclaration* Exception:Access* [ParsedConstructorInvocation:Stmt] Block TypeArgument:Access* <GenericConstructorDecl:GenericConstructorDecl> TypeParameter:TypeVariable* <Parameterization:Parameterization> ImplicitConstructorInvocation:Stmt;
 * @production ParConstructorDecl : {@link ConstructorDecl} ::= <span class="component">TypeArgument:{@link Access}*</span> <span class="component">&lt;GenericConstructorDecl:{@link GenericConstructorDecl}&gt;</span> <span class="component">TypeParameter:{@link TypeVariable}*</span> <span class="component">&lt;Parameterization:{@link Parameterization}&gt;</span>;

 */
public class ParConstructorDecl extends ConstructorDecl implements Cloneable {
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1274
   */
  public boolean isRawType() {
    return false;
  }
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1290
   */
  public int numTypeParameter() {
    return genericConstructorDecl().original().getNumTypeParameter();
  }
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1298
   */
  public TypeVariable typeParameter(int index) {
    return genericConstructorDecl().original().getTypeParameter(index);
  }
  /**
   * @declaredat ASTNode:1
   */
  public ParConstructorDecl() {
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
    children = new ASTNode[8];
    setChild(new List(), 1);
    setChild(new List(), 2);
    setChild(new Opt(), 3);
    setChild(new List(), 5);
    setChild(new List(), 6);
  }
  /**
   * @declaredat ASTNode:18
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Modifiers", "ID", "Parameter", "Exception", "ParsedConstructorInvocation", "Block", "TypeArgument", "GenericConstructorDecl", "TypeParameter", "Parameterization"},
    type = {"Modifiers", "String", "List<ParameterDeclaration>", "List<Access>", "Opt<Stmt>", "Block", "List<Access>", "GenericConstructorDecl", "List<TypeVariable>", "Parameterization"},
    kind = {"Child", "Token", "List", "List", "Opt", "Child", "List", "Token", "List", "Token"}
  )
  public ParConstructorDecl(Modifiers p0, String p1, List<ParameterDeclaration> p2, List<Access> p3, Opt<Stmt> p4, Block p5, List<Access> p6, GenericConstructorDecl p7, List<TypeVariable> p8, Parameterization p9) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
    setChild(p4, 3);
    setChild(p5, 4);
    setChild(p6, 5);
    setGenericConstructorDecl(p7);
    setChild(p8, 6);
    setParameterization(p9);
  }
  /**
   * @declaredat ASTNode:35
   */
  public ParConstructorDecl(Modifiers p0, beaver.Symbol p1, List<ParameterDeclaration> p2, List<Access> p3, Opt<Stmt> p4, Block p5, List<Access> p6, GenericConstructorDecl p7, List<TypeVariable> p8, Parameterization p9) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
    setChild(p4, 3);
    setChild(p5, 4);
    setChild(p6, 5);
    setGenericConstructorDecl(p7);
    setChild(p8, 6);
    setParameterization(p9);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:48
   */
  protected int numChildren() {
    return 7;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:54
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:58
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    genericConstructorDecl_reset();
    sourceConstructorDecl_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:64
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:69
   */
  public ParConstructorDecl clone() throws CloneNotSupportedException {
    ParConstructorDecl node = (ParConstructorDecl) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:74
   */
  public ParConstructorDecl copy() {
    try {
      ParConstructorDecl node = (ParConstructorDecl) clone();
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
   * @declaredat ASTNode:93
   */
  @Deprecated
  public ParConstructorDecl fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:103
   */
  public ParConstructorDecl treeCopyNoTransform() {
    ParConstructorDecl tree = (ParConstructorDecl) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 7:
          tree.children[i] = null;
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
   * @declaredat ASTNode:128
   */
  public ParConstructorDecl treeCopy() {
    ParConstructorDecl tree = (ParConstructorDecl) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 7:
          tree.children[i] = null;
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
  public ParConstructorDecl setModifiers(Modifiers node) {
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
  public ParConstructorDecl setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public ParConstructorDecl setID(beaver.Symbol symbol) {
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
   * Replaces the Parameter list.
   * @param list The new list node to be used as the Parameter list.
   * @apilevel high-level
   */
  public ParConstructorDecl setParameterList(List<ParameterDeclaration> list) {
    setChild(list, 1);
    return this;
  }
  /**
   * Retrieves the number of children in the Parameter list.
   * @return Number of children in the Parameter list.
   * @apilevel high-level
   */
  public int getNumParameter() {
    return getParameterList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Parameter list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the Parameter list.
   * @apilevel low-level
   */
  public int getNumParameterNoTransform() {
    return getParameterListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Parameter list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Parameter list.
   * @apilevel high-level
   */
  public ParameterDeclaration getParameter(int i) {
    return (ParameterDeclaration) getParameterList().getChild(i);
  }
  /**
   * Check whether the Parameter list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasParameter() {
    return getParameterList().getNumChild() != 0;
  }
  /**
   * Append an element to the Parameter list.
   * @param node The element to append to the Parameter list.
   * @apilevel high-level
   */
  public ParConstructorDecl addParameter(ParameterDeclaration node) {
    List<ParameterDeclaration> list = (parent == null) ? getParameterListNoTransform() : getParameterList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public ParConstructorDecl addParameterNoTransform(ParameterDeclaration node) {
    List<ParameterDeclaration> list = getParameterListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the Parameter list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public ParConstructorDecl setParameter(ParameterDeclaration node, int i) {
    List<ParameterDeclaration> list = getParameterList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the Parameter list.
   * @return The node representing the Parameter list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Parameter")
  public List<ParameterDeclaration> getParameterList() {
    List<ParameterDeclaration> list = (List<ParameterDeclaration>) getChild(1);
    return list;
  }
  /**
   * Retrieves the Parameter list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Parameter list.
   * @apilevel low-level
   */
  public List<ParameterDeclaration> getParameterListNoTransform() {
    return (List<ParameterDeclaration>) getChildNoTransform(1);
  }
  /**
   * @return the element at index {@code i} in the Parameter list without
   * triggering rewrites.
   */
  public ParameterDeclaration getParameterNoTransform(int i) {
    return (ParameterDeclaration) getParameterListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Parameter list.
   * @return The node representing the Parameter list.
   * @apilevel high-level
   */
  public List<ParameterDeclaration> getParameters() {
    return getParameterList();
  }
  /**
   * Retrieves the Parameter list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Parameter list.
   * @apilevel low-level
   */
  public List<ParameterDeclaration> getParametersNoTransform() {
    return getParameterListNoTransform();
  }
  /**
   * Replaces the Exception list.
   * @param list The new list node to be used as the Exception list.
   * @apilevel high-level
   */
  public ParConstructorDecl setExceptionList(List<Access> list) {
    setChild(list, 2);
    return this;
  }
  /**
   * Retrieves the number of children in the Exception list.
   * @return Number of children in the Exception list.
   * @apilevel high-level
   */
  public int getNumException() {
    return getExceptionList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Exception list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the Exception list.
   * @apilevel low-level
   */
  public int getNumExceptionNoTransform() {
    return getExceptionListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Exception list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Exception list.
   * @apilevel high-level
   */
  public Access getException(int i) {
    return (Access) getExceptionList().getChild(i);
  }
  /**
   * Check whether the Exception list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasException() {
    return getExceptionList().getNumChild() != 0;
  }
  /**
   * Append an element to the Exception list.
   * @param node The element to append to the Exception list.
   * @apilevel high-level
   */
  public ParConstructorDecl addException(Access node) {
    List<Access> list = (parent == null) ? getExceptionListNoTransform() : getExceptionList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public ParConstructorDecl addExceptionNoTransform(Access node) {
    List<Access> list = getExceptionListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the Exception list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public ParConstructorDecl setException(Access node, int i) {
    List<Access> list = getExceptionList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the Exception list.
   * @return The node representing the Exception list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Exception")
  public List<Access> getExceptionList() {
    List<Access> list = (List<Access>) getChild(2);
    return list;
  }
  /**
   * Retrieves the Exception list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Exception list.
   * @apilevel low-level
   */
  public List<Access> getExceptionListNoTransform() {
    return (List<Access>) getChildNoTransform(2);
  }
  /**
   * @return the element at index {@code i} in the Exception list without
   * triggering rewrites.
   */
  public Access getExceptionNoTransform(int i) {
    return (Access) getExceptionListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Exception list.
   * @return The node representing the Exception list.
   * @apilevel high-level
   */
  public List<Access> getExceptions() {
    return getExceptionList();
  }
  /**
   * Retrieves the Exception list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Exception list.
   * @apilevel low-level
   */
  public List<Access> getExceptionsNoTransform() {
    return getExceptionListNoTransform();
  }
  /**
   * Replaces the optional node for the ParsedConstructorInvocation child. This is the <code>Opt</code>
   * node containing the child ParsedConstructorInvocation, not the actual child!
   * @param opt The new node to be used as the optional node for the ParsedConstructorInvocation child.
   * @apilevel low-level
   */
  public ParConstructorDecl setParsedConstructorInvocationOpt(Opt<Stmt> opt) {
    setChild(opt, 3);
    return this;
  }
  /**
   * Replaces the (optional) ParsedConstructorInvocation child.
   * @param node The new node to be used as the ParsedConstructorInvocation child.
   * @apilevel high-level
   */
  public ParConstructorDecl setParsedConstructorInvocation(Stmt node) {
    getParsedConstructorInvocationOpt().setChild(node, 0);
    return this;
  }
  /**
   * Check whether the optional ParsedConstructorInvocation child exists.
   * @return {@code true} if the optional ParsedConstructorInvocation child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasParsedConstructorInvocation() {
    return getParsedConstructorInvocationOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) ParsedConstructorInvocation child.
   * @return The ParsedConstructorInvocation child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public Stmt getParsedConstructorInvocation() {
    return (Stmt) getParsedConstructorInvocationOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for the ParsedConstructorInvocation child. This is the <code>Opt</code> node containing the child ParsedConstructorInvocation, not the actual child!
   * @return The optional node for child the ParsedConstructorInvocation child.
   * @apilevel low-level
   */
  @ASTNodeAnnotation.OptChild(name="ParsedConstructorInvocation")
  public Opt<Stmt> getParsedConstructorInvocationOpt() {
    return (Opt<Stmt>) getChild(3);
  }
  /**
   * Retrieves the optional node for child ParsedConstructorInvocation. This is the <code>Opt</code> node containing the child ParsedConstructorInvocation, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child ParsedConstructorInvocation.
   * @apilevel low-level
   */
  public Opt<Stmt> getParsedConstructorInvocationOptNoTransform() {
    return (Opt<Stmt>) getChildNoTransform(3);
  }
  /**
   * Replaces the Block child.
   * @param node The new node to replace the Block child.
   * @apilevel high-level
   */
  public ParConstructorDecl setBlock(Block node) {
    setChild(node, 4);
    return this;
  }
  /**
   * Retrieves the Block child.
   * @return The current node used as the Block child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Block")
  public Block getBlock() {
    return (Block) getChild(4);
  }
  /**
   * Retrieves the Block child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Block child.
   * @apilevel low-level
   */
  public Block getBlockNoTransform() {
    return (Block) getChildNoTransform(4);
  }
  /**
   * Replaces the TypeArgument list.
   * @param list The new list node to be used as the TypeArgument list.
   * @apilevel high-level
   */
  public ParConstructorDecl setTypeArgumentList(List<Access> list) {
    setChild(list, 5);
    return this;
  }
  /**
   * Retrieves the number of children in the TypeArgument list.
   * @return Number of children in the TypeArgument list.
   * @apilevel high-level
   */
  public int getNumTypeArgument() {
    return getTypeArgumentList().getNumChild();
  }
  /**
   * Retrieves the number of children in the TypeArgument list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the TypeArgument list.
   * @apilevel low-level
   */
  public int getNumTypeArgumentNoTransform() {
    return getTypeArgumentListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the TypeArgument list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the TypeArgument list.
   * @apilevel high-level
   */
  public Access getTypeArgument(int i) {
    return (Access) getTypeArgumentList().getChild(i);
  }
  /**
   * Check whether the TypeArgument list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasTypeArgument() {
    return getTypeArgumentList().getNumChild() != 0;
  }
  /**
   * Append an element to the TypeArgument list.
   * @param node The element to append to the TypeArgument list.
   * @apilevel high-level
   */
  public ParConstructorDecl addTypeArgument(Access node) {
    List<Access> list = (parent == null) ? getTypeArgumentListNoTransform() : getTypeArgumentList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public ParConstructorDecl addTypeArgumentNoTransform(Access node) {
    List<Access> list = getTypeArgumentListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the TypeArgument list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public ParConstructorDecl setTypeArgument(Access node, int i) {
    List<Access> list = getTypeArgumentList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the TypeArgument list.
   * @return The node representing the TypeArgument list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="TypeArgument")
  public List<Access> getTypeArgumentList() {
    List<Access> list = (List<Access>) getChild(5);
    return list;
  }
  /**
   * Retrieves the TypeArgument list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeArgument list.
   * @apilevel low-level
   */
  public List<Access> getTypeArgumentListNoTransform() {
    return (List<Access>) getChildNoTransform(5);
  }
  /**
   * @return the element at index {@code i} in the TypeArgument list without
   * triggering rewrites.
   */
  public Access getTypeArgumentNoTransform(int i) {
    return (Access) getTypeArgumentListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the TypeArgument list.
   * @return The node representing the TypeArgument list.
   * @apilevel high-level
   */
  public List<Access> getTypeArguments() {
    return getTypeArgumentList();
  }
  /**
   * Retrieves the TypeArgument list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeArgument list.
   * @apilevel low-level
   */
  public List<Access> getTypeArgumentsNoTransform() {
    return getTypeArgumentListNoTransform();
  }
  /**
   * Replaces the lexeme GenericConstructorDecl.
   * @param value The new value for the lexeme GenericConstructorDecl.
   * @apilevel high-level
   */
  public ParConstructorDecl setGenericConstructorDecl(GenericConstructorDecl value) {
    tokenGenericConstructorDecl_GenericConstructorDecl = value;
    return this;
  }
  /** @apilevel internal 
   */
  protected GenericConstructorDecl tokenGenericConstructorDecl_GenericConstructorDecl;
  /**
   * Retrieves the value for the lexeme GenericConstructorDecl.
   * @return The value for the lexeme GenericConstructorDecl.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Token(name="GenericConstructorDecl")
  public GenericConstructorDecl getGenericConstructorDecl() {
    return tokenGenericConstructorDecl_GenericConstructorDecl;
  }
  /**
   * Replaces the TypeParameter list.
   * @param list The new list node to be used as the TypeParameter list.
   * @apilevel high-level
   */
  public ParConstructorDecl setTypeParameterList(List<TypeVariable> list) {
    setChild(list, 6);
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
  public ParConstructorDecl addTypeParameter(TypeVariable node) {
    List<TypeVariable> list = (parent == null) ? getTypeParameterListNoTransform() : getTypeParameterList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public ParConstructorDecl addTypeParameterNoTransform(TypeVariable node) {
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
  public ParConstructorDecl setTypeParameter(TypeVariable node, int i) {
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
    List<TypeVariable> list = (List<TypeVariable>) getChild(6);
    return list;
  }
  /**
   * Retrieves the TypeParameter list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeParameter list.
   * @apilevel low-level
   */
  public List<TypeVariable> getTypeParameterListNoTransform() {
    return (List<TypeVariable>) getChildNoTransform(6);
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
  public ParConstructorDecl setParameterization(Parameterization value) {
    tokenParameterization_Parameterization = value;
    return this;
  }
  /** @apilevel internal 
   */
  protected Parameterization tokenParameterization_Parameterization;
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
   * Retrieves the ImplicitConstructorInvocation child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the ImplicitConstructorInvocation child.
   * @apilevel low-level
   */
  public Stmt getImplicitConstructorInvocationNoTransform() {
    return (Stmt) getChildNoTransform(7);
  }
  /**
   * Retrieves the child position of the optional child ImplicitConstructorInvocation.
   * @return The the child position of the optional child ImplicitConstructorInvocation.
   * @apilevel low-level
   */
  protected int getImplicitConstructorInvocationChildPosition() {
    return 7;
  }
  /** @apilevel internal */
  private void genericConstructorDecl_reset() {
    genericConstructorDecl_computed = null;
    genericConstructorDecl_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle genericConstructorDecl_computed = null;

  /** @apilevel internal */
  protected GenericConstructorDecl genericConstructorDecl_value;

  /**
   * @attribute syn
   * @aspect GenericMethods
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:54
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericMethods", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:54")
  public GenericConstructorDecl genericConstructorDecl() {
    ASTState state = state();
    if (genericConstructorDecl_computed == ASTState.NON_CYCLE || genericConstructorDecl_computed == state().cycle()) {
      return genericConstructorDecl_value;
    }
    genericConstructorDecl_value = getGenericConstructorDecl();
    if (state().inCircle()) {
      genericConstructorDecl_computed = state().cycle();
    
    } else {
      genericConstructorDecl_computed = ASTState.NON_CYCLE;
    
    }
    return genericConstructorDecl_value;
  }
  /** @apilevel internal */
  private void sourceConstructorDecl_reset() {
    sourceConstructorDecl_computed = null;
    sourceConstructorDecl_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle sourceConstructorDecl_computed = null;

  /** @apilevel internal */
  protected ConstructorDecl sourceConstructorDecl_value;

  /**
   * @attribute syn
   * @aspect SourceDeclarations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1920
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SourceDeclarations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1918")
  public ConstructorDecl sourceConstructorDecl() {
    ASTState state = state();
    if (sourceConstructorDecl_computed == ASTState.NON_CYCLE || sourceConstructorDecl_computed == state().cycle()) {
      return sourceConstructorDecl_value;
    }
    sourceConstructorDecl_value = genericConstructorDecl().original().sourceConstructorDecl();
    if (state().inCircle()) {
      sourceConstructorDecl_computed = state().cycle();
    
    } else {
      sourceConstructorDecl_computed = ASTState.NON_CYCLE;
    
    }
    return sourceConstructorDecl_value;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:231
   * @apilevel internal
   */
  public SimpleSet<TypeDecl> Define_lookupType(ASTNode _callerNode, ASTNode _childNode, String name) {
    int childIndex = this.getIndexOfChild(_callerNode);
    {
        TypeDecl paramType = getParameterization().substitute(name);
        if (paramType != null) {
          return paramType;
        }
        return lookupType(name);
      }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:231
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupType
   */
  protected boolean canDefine_lookupType(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
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
