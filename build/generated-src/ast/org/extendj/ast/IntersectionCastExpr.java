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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/grammar/IntersectionCasts.ast:1
 * @astdecl IntersectionCastExpr : Expr ::= TypeAccess:Access TypeList:Access* Expr;
 * @production IntersectionCastExpr : {@link Expr} ::= <span class="component">TypeAccess:{@link Access}</span> <span class="component">TypeList:{@link Access}*</span> <span class="component">{@link Expr}</span>;

 */
public class IntersectionCastExpr extends Expr implements Cloneable {
  /**
   * @aspect Java8PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PrettyPrint.jadd:98
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print("(");
    out.print(getTypeAccess());
    if (hasTypeList()) {
      out.print(" & ");
      out.join(getTypeLists(), new PrettyPrinter.Joiner() {
        @Override
        public void printSeparator(PrettyPrinter out) {
          out.print(" & ");
        }
      });
    }
    out.print(") ");
    out.print(getExpr());
  }
  /**
   * @declaredat ASTNode:1
   */
  public IntersectionCastExpr() {
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
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"TypeAccess", "TypeList", "Expr"},
    type = {"Access", "List<Access>", "Expr"},
    kind = {"Child", "List", "Child"}
  )
  public IntersectionCastExpr(Access p0, List<Access> p1, Expr p2) {
    setChild(p0, 0);
    setChild(p1, 1);
    setChild(p2, 2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:25
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:31
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:35
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    type_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:40
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:45
   */
  public IntersectionCastExpr clone() throws CloneNotSupportedException {
    IntersectionCastExpr node = (IntersectionCastExpr) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:50
   */
  public IntersectionCastExpr copy() {
    try {
      IntersectionCastExpr node = (IntersectionCastExpr) clone();
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
   * @declaredat ASTNode:69
   */
  @Deprecated
  public IntersectionCastExpr fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:79
   */
  public IntersectionCastExpr treeCopyNoTransform() {
    IntersectionCastExpr tree = (IntersectionCastExpr) copy();
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
   * @declaredat ASTNode:99
   */
  public IntersectionCastExpr treeCopy() {
    IntersectionCastExpr tree = (IntersectionCastExpr) copy();
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
   * Replaces the TypeAccess child.
   * @param node The new node to replace the TypeAccess child.
   * @apilevel high-level
   */
  public IntersectionCastExpr setTypeAccess(Access node) {
    setChild(node, 0);
    return this;
  }
  /**
   * Retrieves the TypeAccess child.
   * @return The current node used as the TypeAccess child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="TypeAccess")
  public Access getTypeAccess() {
    return (Access) getChild(0);
  }
  /**
   * Retrieves the TypeAccess child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the TypeAccess child.
   * @apilevel low-level
   */
  public Access getTypeAccessNoTransform() {
    return (Access) getChildNoTransform(0);
  }
  /**
   * Replaces the TypeList list.
   * @param list The new list node to be used as the TypeList list.
   * @apilevel high-level
   */
  public IntersectionCastExpr setTypeListList(List<Access> list) {
    setChild(list, 1);
    return this;
  }
  /**
   * Retrieves the number of children in the TypeList list.
   * @return Number of children in the TypeList list.
   * @apilevel high-level
   */
  public int getNumTypeList() {
    return getTypeListList().getNumChild();
  }
  /**
   * Retrieves the number of children in the TypeList list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the TypeList list.
   * @apilevel low-level
   */
  public int getNumTypeListNoTransform() {
    return getTypeListListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the TypeList list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the TypeList list.
   * @apilevel high-level
   */
  public Access getTypeList(int i) {
    return (Access) getTypeListList().getChild(i);
  }
  /**
   * Check whether the TypeList list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasTypeList() {
    return getTypeListList().getNumChild() != 0;
  }
  /**
   * Append an element to the TypeList list.
   * @param node The element to append to the TypeList list.
   * @apilevel high-level
   */
  public IntersectionCastExpr addTypeList(Access node) {
    List<Access> list = (parent == null) ? getTypeListListNoTransform() : getTypeListList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public IntersectionCastExpr addTypeListNoTransform(Access node) {
    List<Access> list = getTypeListListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the TypeList list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public IntersectionCastExpr setTypeList(Access node, int i) {
    List<Access> list = getTypeListList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the TypeList list.
   * @return The node representing the TypeList list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="TypeList")
  public List<Access> getTypeListList() {
    List<Access> list = (List<Access>) getChild(1);
    return list;
  }
  /**
   * Retrieves the TypeList list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeList list.
   * @apilevel low-level
   */
  public List<Access> getTypeListListNoTransform() {
    return (List<Access>) getChildNoTransform(1);
  }
  /**
   * @return the element at index {@code i} in the TypeList list without
   * triggering rewrites.
   */
  public Access getTypeListNoTransform(int i) {
    return (Access) getTypeListListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the TypeList list.
   * @return The node representing the TypeList list.
   * @apilevel high-level
   */
  public List<Access> getTypeLists() {
    return getTypeListList();
  }
  /**
   * Retrieves the TypeList list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeList list.
   * @apilevel low-level
   */
  public List<Access> getTypeListsNoTransform() {
    return getTypeListListNoTransform();
  }
  /**
   * Replaces the Expr child.
   * @param node The new node to replace the Expr child.
   * @apilevel high-level
   */
  public IntersectionCastExpr setExpr(Expr node) {
    setChild(node, 2);
    return this;
  }
  /**
   * Retrieves the Expr child.
   * @return The current node used as the Expr child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Expr")
  public Expr getExpr() {
    return (Expr) getChild(2);
  }
  /**
   * Retrieves the Expr child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Expr child.
   * @apilevel low-level
   */
  public Expr getExprNoTransform() {
    return (Expr) getChildNoTransform(2);
  }
  /** @apilevel internal */
  private void type_reset() {
    type_computed = null;
    type_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle type_computed = null;

  /** @apilevel internal */
  protected TypeDecl type_value;

  /**
   * @attribute syn
   * @aspect TypeCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:36
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:36")
  public TypeDecl type() {
    ASTState state = state();
    if (type_computed == ASTState.NON_CYCLE || type_computed == state().cycle()) {
      return type_value;
    }
    type_value = unknownType();
    if (state().inCircle()) {
      type_computed = state().cycle();
    
    } else {
      type_computed = ASTState.NON_CYCLE;
    
    }
    return type_value;
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
