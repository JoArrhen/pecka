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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/grammar/GenericMethods.ast:1
 * @astdecl GenericMethodDecl : MethodDecl ::= Modifiers TypeAccess:Access <ID:String> Parameter:ParameterDeclaration* Exception:Access* [Block] TypeParameter:TypeVariable*;
 * @production GenericMethodDecl : {@link MethodDecl} ::= <span class="component">TypeParameter:{@link TypeVariable}*</span>;

 */
public class GenericMethodDecl extends MethodDecl implements Cloneable {
  /**
   * @aspect Java5PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/PrettyPrint.jadd:289
   */
  public void prettyPrint(PrettyPrinter out) {
    if (hasDocComment()) {
      out.print(docComment());
    }
    if (!out.isNewLine()) {
      out.println();
    }
    out.print(getModifiers());
    out.print("<");
    out.join(getTypeParameterList(), new PrettyPrinter.Joiner() {
      @Override
      public void printSeparator(PrettyPrinter out) {
        out.print(", ");
      }
    });
    out.print("> ");
    out.print(getTypeAccess());
    out.print(" ");
    out.print(getID());
    out.print("(");
    out.join(getParameterList(), new PrettyPrinter.Joiner() {
      @Override
      public void printSeparator(PrettyPrinter out) {
        out.print(", ");
      }
    });
    out.print(")");
    if (hasExceptions()) {
      out.print(" throws ");
      out.join(getExceptionList(), new PrettyPrinter.Joiner() {
        @Override
        public void printSeparator(PrettyPrinter out) {
          out.print(", ");
        }
      });
    }
    if (hasBlock()) {
      out.print(" ");
      out.print(getBlock());
    } else {
      out.print(";");
    }
  }
  /**
   * @aspect GenericMethods
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:70
   */
  public ParMethodDecl newParMethodDecl(Collection<TypeDecl> typeArguments) {
    Parameterization parameterization = new Parameterization(getTypeParameterList(),
        typeArguments);
    ParMethodDecl methodDecl = typeArguments.isEmpty() ? new RawMethodDecl() : new ParMethodDecl();

    // Adding a link to GenericMethodDecl to be used during substitution
    // instead of the not yet existing parent link.
    methodDecl.setGenericMethodDecl(genericDecl());

    List<Access> list = new List<Access>();
    if (typeArguments.isEmpty()) {
      GenericMethodDecl original = genericDecl();
      for (int i = 0; i < original.getNumTypeParameter(); i++) {
        list.add(original.getTypeParameter(i).erasure().createBoundAccess());
      }
    } else {
      for (TypeDecl arg : typeArguments) {
        list.add(arg.createBoundAccess());
      }
    }
    methodDecl.setTypeArgumentList(list);
    methodDecl.setModifiers(getModifiers().treeCopy());
    methodDecl.setTypeAccess(getTypeAccess().treeCopy());
    methodDecl.setID(getID());
    methodDecl.setParameterList(getParameterList().treeCopy());
    methodDecl.setExceptionList(getExceptionList().treeCopy());
    methodDecl.setTypeParameterList(getTypeParameterList().treeCopy());
    methodDecl.setParameterization(parameterization);
    return methodDecl;
  }
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1453
   */
  public BodyDecl signatureCopy() {
    return new GenericMethodDeclSubstituted(
        getModifiers().treeCopyNoTransform(),
        getTypeAccessNoTransform().treeCopyNoTransform(),
        getID(),
        getParameterList().treeCopyNoTransform(),
        getExceptionList().treeCopyNoTransform(),
        new Opt<Block>(),
        getTypeParameterList().treeCopyNoTransform(),
        this);
  }
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1554
   */
  public BodyDecl erasedCopy() {
    return new GenericMethodDeclErased(
        getModifiers().treeCopyNoTransform(),
        getTypeAccess().erasedCopy(),
        getID(),
        erasedParameterList(getParameterList()),
        erasedAccessList(getExceptionList()),
        new Opt<Block>(),
        getTypeParameterList().treeCopyNoTransform(),
        this);
  }
  /**
   * @aspect PrettyPrintUtil5
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/PrettyPrintUtil.jrag:35
   */
  @Override public String toString() {
    StringBuilder params = new StringBuilder();
    for (TypeVariable var : getTypeParameterListNoTransform()) {
      if (params.length() > 0) {
        params.append(", ");
      }
      params.append(var.toString());
    }
    return String.format("<%s> %s", params.toString(), super.toString());
  }
  /**
   * @declaredat ASTNode:1
   */
  public GenericMethodDecl() {
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
    children = new ASTNode[6];
    setChild(new List(), 2);
    setChild(new List(), 3);
    setChild(new Opt(), 4);
    setChild(new List(), 5);
  }
  /**
   * @declaredat ASTNode:17
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Modifiers", "TypeAccess", "ID", "Parameter", "Exception", "Block", "TypeParameter"},
    type = {"Modifiers", "Access", "String", "List<ParameterDeclaration>", "List<Access>", "Opt<Block>", "List<TypeVariable>"},
    kind = {"Child", "Child", "Token", "List", "List", "Opt", "List"}
  )
  public GenericMethodDecl(Modifiers p0, Access p1, String p2, List<ParameterDeclaration> p3, List<Access> p4, Opt<Block> p5, List<TypeVariable> p6) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
    setChild(p3, 2);
    setChild(p4, 3);
    setChild(p5, 4);
    setChild(p6, 5);
  }
  /**
   * @declaredat ASTNode:31
   */
  public GenericMethodDecl(Modifiers p0, Access p1, beaver.Symbol p2, List<ParameterDeclaration> p3, List<Access> p4, Opt<Block> p5, List<TypeVariable> p6) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
    setChild(p3, 2);
    setChild(p4, 3);
    setChild(p5, 4);
    setChild(p6, 5);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:41
   */
  protected int numChildren() {
    return 6;
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
    rawMethodDecl_reset();
    lookupParMethodDecl_Collection_TypeDecl__reset();
    typeVariableInReturn_reset();
    subsignatureTo_MethodDecl_reset();
    sameTypeParameters_GenericMethodDecl_reset();
    sameFormalParameters_GenericMethodDecl_reset();
    usesTypeVariable_reset();
    genericMethodLevel_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:63
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:68
   */
  public GenericMethodDecl clone() throws CloneNotSupportedException {
    GenericMethodDecl node = (GenericMethodDecl) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:73
   */
  public GenericMethodDecl copy() {
    try {
      GenericMethodDecl node = (GenericMethodDecl) clone();
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
   * @declaredat ASTNode:92
   */
  @Deprecated
  public GenericMethodDecl fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:102
   */
  public GenericMethodDecl treeCopyNoTransform() {
    GenericMethodDecl tree = (GenericMethodDecl) copy();
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
   * @declaredat ASTNode:122
   */
  public GenericMethodDecl treeCopy() {
    GenericMethodDecl tree = (GenericMethodDecl) copy();
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
  public GenericMethodDecl setModifiers(Modifiers node) {
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
   * Replaces the TypeAccess child.
   * @param node The new node to replace the TypeAccess child.
   * @apilevel high-level
   */
  public GenericMethodDecl setTypeAccess(Access node) {
    setChild(node, 1);
    return this;
  }
  /**
   * Retrieves the TypeAccess child.
   * @return The current node used as the TypeAccess child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="TypeAccess")
  public Access getTypeAccess() {
    return (Access) getChild(1);
  }
  /**
   * Retrieves the TypeAccess child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the TypeAccess child.
   * @apilevel low-level
   */
  public Access getTypeAccessNoTransform() {
    return (Access) getChildNoTransform(1);
  }
  /**
   * Replaces the lexeme ID.
   * @param value The new value for the lexeme ID.
   * @apilevel high-level
   */
  public GenericMethodDecl setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public GenericMethodDecl setID(beaver.Symbol symbol) {
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
  public GenericMethodDecl setParameterList(List<ParameterDeclaration> list) {
    setChild(list, 2);
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
  public GenericMethodDecl addParameter(ParameterDeclaration node) {
    List<ParameterDeclaration> list = (parent == null) ? getParameterListNoTransform() : getParameterList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public GenericMethodDecl addParameterNoTransform(ParameterDeclaration node) {
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
  public GenericMethodDecl setParameter(ParameterDeclaration node, int i) {
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
    List<ParameterDeclaration> list = (List<ParameterDeclaration>) getChild(2);
    return list;
  }
  /**
   * Retrieves the Parameter list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Parameter list.
   * @apilevel low-level
   */
  public List<ParameterDeclaration> getParameterListNoTransform() {
    return (List<ParameterDeclaration>) getChildNoTransform(2);
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
  public GenericMethodDecl setExceptionList(List<Access> list) {
    setChild(list, 3);
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
  public GenericMethodDecl addException(Access node) {
    List<Access> list = (parent == null) ? getExceptionListNoTransform() : getExceptionList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public GenericMethodDecl addExceptionNoTransform(Access node) {
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
  public GenericMethodDecl setException(Access node, int i) {
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
    List<Access> list = (List<Access>) getChild(3);
    return list;
  }
  /**
   * Retrieves the Exception list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Exception list.
   * @apilevel low-level
   */
  public List<Access> getExceptionListNoTransform() {
    return (List<Access>) getChildNoTransform(3);
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
   * Replaces the optional node for the Block child. This is the <code>Opt</code>
   * node containing the child Block, not the actual child!
   * @param opt The new node to be used as the optional node for the Block child.
   * @apilevel low-level
   */
  public GenericMethodDecl setBlockOpt(Opt<Block> opt) {
    setChild(opt, 4);
    return this;
  }
  /**
   * Replaces the (optional) Block child.
   * @param node The new node to be used as the Block child.
   * @apilevel high-level
   */
  public GenericMethodDecl setBlock(Block node) {
    getBlockOpt().setChild(node, 0);
    return this;
  }
  /**
   * Check whether the optional Block child exists.
   * @return {@code true} if the optional Block child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasBlock() {
    return getBlockOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) Block child.
   * @return The Block child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public Block getBlock() {
    return (Block) getBlockOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for the Block child. This is the <code>Opt</code> node containing the child Block, not the actual child!
   * @return The optional node for child the Block child.
   * @apilevel low-level
   */
  @ASTNodeAnnotation.OptChild(name="Block")
  public Opt<Block> getBlockOpt() {
    return (Opt<Block>) getChild(4);
  }
  /**
   * Retrieves the optional node for child Block. This is the <code>Opt</code> node containing the child Block, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child Block.
   * @apilevel low-level
   */
  public Opt<Block> getBlockOptNoTransform() {
    return (Opt<Block>) getChildNoTransform(4);
  }
  /**
   * Replaces the TypeParameter list.
   * @param list The new list node to be used as the TypeParameter list.
   * @apilevel high-level
   */
  public GenericMethodDecl setTypeParameterList(List<TypeVariable> list) {
    setChild(list, 5);
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
  public GenericMethodDecl addTypeParameter(TypeVariable node) {
    List<TypeVariable> list = (parent == null) ? getTypeParameterListNoTransform() : getTypeParameterList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public GenericMethodDecl addTypeParameterNoTransform(TypeVariable node) {
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
  public GenericMethodDecl setTypeParameter(TypeVariable node, int i) {
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
    List<TypeVariable> list = (List<TypeVariable>) getChild(5);
    return list;
  }
  /**
   * Retrieves the TypeParameter list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeParameter list.
   * @apilevel low-level
   */
  public List<TypeVariable> getTypeParameterListNoTransform() {
    return (List<TypeVariable>) getChildNoTransform(5);
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
  /** @apilevel internal */
  private void rawMethodDecl_reset() {
    rawMethodDecl_computed = null;
    rawMethodDecl_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle rawMethodDecl_computed = null;

  /** @apilevel internal */
  protected ParMethodDecl rawMethodDecl_value;

  /**
   * @attribute syn
   * @aspect GenericMethods
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:49
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericMethods", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:49")
  public ParMethodDecl rawMethodDecl() {
    ASTState state = state();
    if (rawMethodDecl_computed == ASTState.NON_CYCLE || rawMethodDecl_computed == state().cycle()) {
      return rawMethodDecl_value;
    }
    rawMethodDecl_value = lookupParMethodDecl(Collections.<TypeDecl>emptyList());
    if (state().inCircle()) {
      rawMethodDecl_computed = state().cycle();
    
    } else {
      rawMethodDecl_computed = ASTState.NON_CYCLE;
    
    }
    return rawMethodDecl_value;
  }
  /** @apilevel internal */
  private void lookupParMethodDecl_Collection_TypeDecl__reset() {
    lookupParMethodDecl_Collection_TypeDecl__values = null;
    lookupParMethodDecl_Collection_TypeDecl__proxy = null;
  }
  /** @apilevel internal */
  protected ASTNode lookupParMethodDecl_Collection_TypeDecl__proxy;
  /** @apilevel internal */
  protected java.util.Map lookupParMethodDecl_Collection_TypeDecl__values;

  /**
   * @return a specific parameterization of this generic method.
   * @attribute syn
   * @aspect GenericMethods
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:60
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="GenericMethods", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:60")
  public ParMethodDecl lookupParMethodDecl(Collection<TypeDecl> typeArguments) {
    Object _parameters = typeArguments;
    if (lookupParMethodDecl_Collection_TypeDecl__values == null) lookupParMethodDecl_Collection_TypeDecl__values = new java.util.HashMap(4);
    ASTState state = state();
    if (lookupParMethodDecl_Collection_TypeDecl__values.containsKey(_parameters)) {
      return (ParMethodDecl) lookupParMethodDecl_Collection_TypeDecl__values.get(_parameters);
    }
    state().enterLazyAttribute();
    ParMethodDecl lookupParMethodDecl_Collection_TypeDecl__value = newParMethodDecl(typeArguments);
    if (lookupParMethodDecl_Collection_TypeDecl__proxy == null) {
      lookupParMethodDecl_Collection_TypeDecl__proxy = new ASTNode();
      lookupParMethodDecl_Collection_TypeDecl__proxy.setParent(this);
    }
    if (lookupParMethodDecl_Collection_TypeDecl__value != null) {
      lookupParMethodDecl_Collection_TypeDecl__value.setParent(lookupParMethodDecl_Collection_TypeDecl__proxy);
      if (lookupParMethodDecl_Collection_TypeDecl__value.mayHaveRewrite()) {
        lookupParMethodDecl_Collection_TypeDecl__value = (ParMethodDecl) lookupParMethodDecl_Collection_TypeDecl__value.rewrittenNode();
        lookupParMethodDecl_Collection_TypeDecl__value.setParent(lookupParMethodDecl_Collection_TypeDecl__proxy);
      }
    }
    lookupParMethodDecl_Collection_TypeDecl__values.put(_parameters, lookupParMethodDecl_Collection_TypeDecl__value);
    state().leaveLazyAttribute();
    return lookupParMethodDecl_Collection_TypeDecl__value;
  }
  /**
   * @attribute syn
   * @aspect GenericMethodsNameAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:211
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericMethodsNameAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:211")
  public SimpleSet<TypeDecl> localLookupType(String name) {
    {
        for (int i = 0; i < getNumTypeParameter(); i++) {
          if (original().getTypeParameter(i).name().equals(name)) {
            return original().getTypeParameter(i);
          }
        }
        return emptySet();
      }
  }
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1465
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1465")
  public GenericMethodDecl original() {
    GenericMethodDecl original_value = this;
    return original_value;
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:417
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:415")
  public boolean isGeneric() {
    boolean isGeneric_value = true;
    return isGeneric_value;
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:430
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:426")
  public GenericMethodDecl genericDecl() {
    GenericMethodDecl genericDecl_value = this;
    return genericDecl_value;
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:454
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:450")
  public List<TypeVariable> typeParameters() {
    List<TypeVariable> typeParameters_value = getTypeParameterList();
    return typeParameters_value;
  }
  /** @apilevel internal */
  private void typeVariableInReturn_reset() {
    typeVariableInReturn_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeVariableInReturn_computed = null;

  /** @apilevel internal */
  protected boolean typeVariableInReturn_value;

  /**
   * @attribute syn
   * @aspect PolyExpressions
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:117
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PolyExpressions", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:117")
  public boolean typeVariableInReturn() {
    ASTState state = state();
    if (typeVariableInReturn_computed == ASTState.NON_CYCLE || typeVariableInReturn_computed == state().cycle()) {
      return typeVariableInReturn_value;
    }
    typeVariableInReturn_value = typeVariableInReturn_compute();
    if (state().inCircle()) {
      typeVariableInReturn_computed = state().cycle();
    
    } else {
      typeVariableInReturn_computed = ASTState.NON_CYCLE;
    
    }
    return typeVariableInReturn_value;
  }
  /** @apilevel internal */
  private boolean typeVariableInReturn_compute() {
      if (!getTypeAccess().usesTypeVariable()) {
        return false;
      }
      ASTNode current = getTypeAccess();
      LinkedList<ASTNode> list = new LinkedList<ASTNode>();
      list.add(current);
      boolean foundUse = false;
      while (!list.isEmpty()) {
        current = list.poll();
        for (int i = 0; i < current.getNumChild(); i++) {
          list.add(current.getChild(i));
        }
        if (current instanceof TypeAccess) {
          TypeAccess typeAccess = (TypeAccess) current;
          if (typeAccess.type().isTypeVariable()) {
            for (int i = 0; i < getNumTypeParameter(); i++) {
              if (typeAccess.type() == getTypeParameter(i)) {
                foundUse = true;
                break;
              }
            }
            if (foundUse) {
              break;
            }
          }
        }
      }
      return foundUse;
    }
  /**
   * @attribute syn
   * @aspect FunctionalInterface
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:36
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FunctionalInterface", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:36")
  public boolean sameSignature(MethodDecl m) {
    {
        if (!m.isGeneric()) {
          return false;
        }
        GenericMethodDecl gm = m.genericDecl();
        return !(!name().equals(gm.name()) || !sameTypeParameters(gm) || !sameFormalParameters(gm));
      }
  }
  /** @apilevel internal */
  private void subsignatureTo_MethodDecl_reset() {
    subsignatureTo_MethodDecl_computed = null;
    subsignatureTo_MethodDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map subsignatureTo_MethodDecl_values;
  /** @apilevel internal */
  protected java.util.Map subsignatureTo_MethodDecl_computed;
  /**
   * @attribute syn
   * @aspect FunctionalInterface
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:54
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FunctionalInterface", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:54")
  public boolean subsignatureTo(MethodDecl m) {
    Object _parameters = m;
    if (subsignatureTo_MethodDecl_computed == null) subsignatureTo_MethodDecl_computed = new java.util.HashMap(4);
    if (subsignatureTo_MethodDecl_values == null) subsignatureTo_MethodDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (subsignatureTo_MethodDecl_values.containsKey(_parameters)
        && subsignatureTo_MethodDecl_computed.containsKey(_parameters)
        && (subsignatureTo_MethodDecl_computed.get(_parameters) == ASTState.NON_CYCLE || subsignatureTo_MethodDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) subsignatureTo_MethodDecl_values.get(_parameters);
    }
    boolean subsignatureTo_MethodDecl_value = subsignatureTo_compute(m);
    if (state().inCircle()) {
      subsignatureTo_MethodDecl_values.put(_parameters, subsignatureTo_MethodDecl_value);
      subsignatureTo_MethodDecl_computed.put(_parameters, state().cycle());
    
    } else {
      subsignatureTo_MethodDecl_values.put(_parameters, subsignatureTo_MethodDecl_value);
      subsignatureTo_MethodDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return subsignatureTo_MethodDecl_value;
  }
  /** @apilevel internal */
  private boolean subsignatureTo_compute(MethodDecl m) {
      if (m.isGeneric()) {
        GenericMethodDecl gm = m.genericDecl();
        if (this == gm) {
          return true;
        }
        return sameSignature(gm);
      } else {
        // A generic method can never be subsignature to a non-generic method.
        return false;
      }
    }
  /** @apilevel internal */
  private void sameTypeParameters_GenericMethodDecl_reset() {
    sameTypeParameters_GenericMethodDecl_computed = null;
    sameTypeParameters_GenericMethodDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map sameTypeParameters_GenericMethodDecl_values;
  /** @apilevel internal */
  protected java.util.Map sameTypeParameters_GenericMethodDecl_computed;
  /**
   * @attribute syn
   * @aspect FunctionalInterface
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:90
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FunctionalInterface", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:90")
  public boolean sameTypeParameters(GenericMethodDecl gm) {
    Object _parameters = gm;
    if (sameTypeParameters_GenericMethodDecl_computed == null) sameTypeParameters_GenericMethodDecl_computed = new java.util.HashMap(4);
    if (sameTypeParameters_GenericMethodDecl_values == null) sameTypeParameters_GenericMethodDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (sameTypeParameters_GenericMethodDecl_values.containsKey(_parameters)
        && sameTypeParameters_GenericMethodDecl_computed.containsKey(_parameters)
        && (sameTypeParameters_GenericMethodDecl_computed.get(_parameters) == ASTState.NON_CYCLE || sameTypeParameters_GenericMethodDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) sameTypeParameters_GenericMethodDecl_values.get(_parameters);
    }
    boolean sameTypeParameters_GenericMethodDecl_value = sameTypeParameters_compute(gm);
    if (state().inCircle()) {
      sameTypeParameters_GenericMethodDecl_values.put(_parameters, sameTypeParameters_GenericMethodDecl_value);
      sameTypeParameters_GenericMethodDecl_computed.put(_parameters, state().cycle());
    
    } else {
      sameTypeParameters_GenericMethodDecl_values.put(_parameters, sameTypeParameters_GenericMethodDecl_value);
      sameTypeParameters_GenericMethodDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return sameTypeParameters_GenericMethodDecl_value;
  }
  /** @apilevel internal */
  private boolean sameTypeParameters_compute(GenericMethodDecl gm) {
      if (getNumTypeParameter() != gm.getNumTypeParameter()) {
        return false;
      }
  
      for (int i = 0; i < getNumTypeParameter(); i++) {
        TypeVariable tv1 = getTypeParameter(i);
        TypeVariable tv2 = gm.getTypeParameter(i);
        if (!tv1.sameType(tv2)) {
          return false;
        }
      }
      return true;
    }
  /** @apilevel internal */
  private void sameFormalParameters_GenericMethodDecl_reset() {
    sameFormalParameters_GenericMethodDecl_computed = null;
    sameFormalParameters_GenericMethodDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map sameFormalParameters_GenericMethodDecl_values;
  /** @apilevel internal */
  protected java.util.Map sameFormalParameters_GenericMethodDecl_computed;
  /**
   * @attribute syn
   * @aspect FunctionalInterface
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:106
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FunctionalInterface", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:106")
  public boolean sameFormalParameters(GenericMethodDecl gm) {
    Object _parameters = gm;
    if (sameFormalParameters_GenericMethodDecl_computed == null) sameFormalParameters_GenericMethodDecl_computed = new java.util.HashMap(4);
    if (sameFormalParameters_GenericMethodDecl_values == null) sameFormalParameters_GenericMethodDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (sameFormalParameters_GenericMethodDecl_values.containsKey(_parameters)
        && sameFormalParameters_GenericMethodDecl_computed.containsKey(_parameters)
        && (sameFormalParameters_GenericMethodDecl_computed.get(_parameters) == ASTState.NON_CYCLE || sameFormalParameters_GenericMethodDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) sameFormalParameters_GenericMethodDecl_values.get(_parameters);
    }
    boolean sameFormalParameters_GenericMethodDecl_value = sameFormalParameters_compute(gm);
    if (state().inCircle()) {
      sameFormalParameters_GenericMethodDecl_values.put(_parameters, sameFormalParameters_GenericMethodDecl_value);
      sameFormalParameters_GenericMethodDecl_computed.put(_parameters, state().cycle());
    
    } else {
      sameFormalParameters_GenericMethodDecl_values.put(_parameters, sameFormalParameters_GenericMethodDecl_value);
      sameFormalParameters_GenericMethodDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return sameFormalParameters_GenericMethodDecl_value;
  }
  /** @apilevel internal */
  private boolean sameFormalParameters_compute(GenericMethodDecl gm) {
      if (getNumParameter() != gm.getNumParameter()) {
        return false;
      }
      if (getNumParameter() == 0) {
        return true;
      }
  
      for (int i = 0; i < getNumParameter(); i++) {
        ParameterDeclaration p1 = getParameter(i);
        ParameterDeclaration p2 = gm.getParameter(i);
        Access a1 = p1.getTypeAccess();
        Access a2 = p2.getTypeAccess();
        if (!a1.sameType(a2)) {
          return false;
        }
      }
      return true;
    }
  /** @apilevel internal */
  private void usesTypeVariable_reset() {
    usesTypeVariable_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle usesTypeVariable_computed = null;

  /** @apilevel internal */
  protected boolean usesTypeVariable_value;

  /**
   * @attribute syn
   * @aspect FunctionalInterface
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:357
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1332")
  public boolean usesTypeVariable() {
    ASTState state = state();
    if (usesTypeVariable_computed == ASTState.NON_CYCLE || usesTypeVariable_computed == state().cycle()) {
      return usesTypeVariable_value;
    }
    usesTypeVariable_value = super.usesTypeVariable() || getTypeParameterList().usesTypeVariable();
    if (state().inCircle()) {
      usesTypeVariable_computed = state().cycle();
    
    } else {
      usesTypeVariable_computed = ASTState.NON_CYCLE;
    
    }
    return usesTypeVariable_value;
  }
  /**
   * @attribute inh
   * @aspect GenericMethodsNameAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:209
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="GenericMethodsNameAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:209")
  public SimpleSet<TypeDecl> lookupType(String name) {
    SimpleSet<TypeDecl> lookupType_String_value = getParent().Define_lookupType(this, null, name);
    return lookupType_String_value;
  }
  /**
   * @attribute inh
   * @aspect TypeVariablePositions
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:31
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TypeVariablePositions", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:31")
  public int genericMethodLevel() {
    ASTState state = state();
    if (genericMethodLevel_computed == ASTState.NON_CYCLE || genericMethodLevel_computed == state().cycle()) {
      return genericMethodLevel_value;
    }
    genericMethodLevel_value = getParent().Define_genericMethodLevel(this, null);
    if (state().inCircle()) {
      genericMethodLevel_computed = state().cycle();
    
    } else {
      genericMethodLevel_computed = ASTState.NON_CYCLE;
    
    }
    return genericMethodLevel_value;
  }
  /** @apilevel internal */
  private void genericMethodLevel_reset() {
    genericMethodLevel_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle genericMethodLevel_computed = null;

  /** @apilevel internal */
  protected int genericMethodLevel_value;

  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getTypeParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:207
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return NameType.TYPE_NAME;
    }
    else {
      return super.Define_nameType(_callerNode, _childNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute nameType
   */
  protected boolean canDefine_nameType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:231
   * @apilevel internal
   */
  public SimpleSet<TypeDecl> Define_lookupType(ASTNode _callerNode, ASTNode _childNode, String name) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return localLookupType(name).isEmpty() ? lookupType(name) : localLookupType(name);
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:231
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupType
   */
  protected boolean canDefine_lookupType(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:29
   * @apilevel internal
   */
  public int Define_typeVarPosition(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getTypeParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:37
      int i = _callerNode.getIndexOfChild(_childNode);
      return i;
    }
    else {
      return getParent().Define_typeVarPosition(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:29
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeVarPosition
   */
  protected boolean canDefine_typeVarPosition(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:32
   * @apilevel internal
   */
  public boolean Define_typeVarInMethod(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getTypeParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:38
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return true;
    }
    else {
      return getParent().Define_typeVarInMethod(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:32
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeVarInMethod
   */
  protected boolean canDefine_typeVarInMethod(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:31
   * @apilevel internal
   */
  public int Define_genericMethodLevel(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:50
      return genericMethodLevel() + 1;
    }
    else if (_callerNode == getTypeParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:49
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return genericMethodLevel() + 1;
    }
    else {
      return getParent().Define_genericMethodLevel(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:31
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute genericMethodLevel
   */
  protected boolean canDefine_genericMethodLevel(ASTNode _callerNode, ASTNode _childNode) {
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
