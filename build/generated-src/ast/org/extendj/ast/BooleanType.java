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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:168
 * @astdecl BooleanType : PrimitiveType ::= Modifiers <ID:String> BodyDecl*;
 * @production BooleanType : {@link PrimitiveType};

 */
public class BooleanType extends PrimitiveType implements Cloneable {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrint.jadd:116
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print("boolean");
  }
  /**
   * @declaredat ASTNode:1
   */
  public BooleanType() {
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
    children = new ASTNode[2];
    setChild(new List(), 1);
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Modifiers", "ID", "BodyDecl"},
    type = {"Modifiers", "String", "List<BodyDecl>"},
    kind = {"Child", "Token", "List"}
  )
  public BooleanType(Modifiers p0, String p1, List<BodyDecl> p2) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
  }
  /**
   * @declaredat ASTNode:24
   */
  public BooleanType(Modifiers p0, beaver.Symbol p1, List<BodyDecl> p2) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:30
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:36
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:40
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    boxed_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:45
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:50
   */
  public BooleanType clone() throws CloneNotSupportedException {
    BooleanType node = (BooleanType) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:55
   */
  public BooleanType copy() {
    try {
      BooleanType node = (BooleanType) clone();
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
   * @declaredat ASTNode:74
   */
  @Deprecated
  public BooleanType fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:84
   */
  public BooleanType treeCopyNoTransform() {
    BooleanType tree = (BooleanType) copy();
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
   * @declaredat ASTNode:104
   */
  public BooleanType treeCopy() {
    BooleanType tree = (BooleanType) copy();
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
  public BooleanType setModifiers(Modifiers node) {
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
  public BooleanType setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public BooleanType setID(beaver.Symbol symbol) {
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
  public BooleanType setBodyDeclList(List<BodyDecl> list) {
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
  public BooleanType addBodyDecl(BodyDecl node) {
    List<BodyDecl> list = (parent == null) ? getBodyDeclListNoTransform() : getBodyDeclList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public BooleanType addBodyDeclNoTransform(BodyDecl node) {
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
  public BooleanType setBodyDecl(BodyDecl node, int i) {
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
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:114
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:95")
  public Constant cast(Constant c) {
    Constant cast_Constant_value = Constant.create(c.booleanValue());
    return cast_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:268
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:257")
  public Constant andBitwise(Constant c1, Constant c2) {
    Constant andBitwise_Constant_Constant_value = Constant.create(c1.booleanValue() & c2.booleanValue());
    return andBitwise_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:282
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:271")
  public Constant xorBitwise(Constant c1, Constant c2) {
    Constant xorBitwise_Constant_Constant_value = Constant.create(c1.booleanValue() ^ c2.booleanValue());
    return xorBitwise_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:296
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:285")
  public Constant orBitwise(Constant c1, Constant c2) {
    Constant orBitwise_Constant_Constant_value = Constant.create(c1.booleanValue() | c2.booleanValue());
    return orBitwise_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:316
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:299")
  public Constant questionColon(Constant cond, Constant c1, Constant c2) {
    Constant questionColon_Constant_Constant_Constant_value = Constant.create(cond.booleanValue() ? c1.booleanValue() : c2.booleanValue());
    return questionColon_Constant_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:516
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:499")
  public boolean eqIsTrue(Expr left, Expr right) {
    boolean eqIsTrue_Expr_Expr_value = left.isTrue() && right.isTrue() || left.isFalse() && right.isFalse();
    return eqIsTrue_Expr_Expr_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:196
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:195")
  public boolean isBoolean() {
    boolean isBoolean_value = true;
    return isBoolean_value;
  }
  /** @apilevel internal */
  private void boxed_reset() {
    boxed_computed = null;
    boxed_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle boxed_computed = null;

  /** @apilevel internal */
  protected TypeDecl boxed_value;

  /**
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/AutoBoxing.jrag:55
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/AutoBoxing.jrag:53")
  public TypeDecl boxed() {
    ASTState state = state();
    if (boxed_computed == ASTState.NON_CYCLE || boxed_computed == state().cycle()) {
      return boxed_value;
    }
    boxed_value = lookupType("java.lang", "Boolean");
    if (state().inCircle()) {
      boxed_computed = state().cycle();
    
    } else {
      boxed_computed = ASTState.NON_CYCLE;
    
    }
    return boxed_value;
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