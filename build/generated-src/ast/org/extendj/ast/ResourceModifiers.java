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
 * Subclass of Modifiers for resource declarations.
 * This subclass is added as a convenient method of making resource
 * declarations implicitly final.
 * @ast node
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/grammar/TryWithResources.ast:17
 * @astdecl ResourceModifiers : Modifiers ::= Modifier*;
 * @production ResourceModifiers : {@link Modifiers};

 */
public class ResourceModifiers extends Modifiers implements Cloneable {
  /**
   * @declaredat ASTNode:1
   */
  public ResourceModifiers() {
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
    children = new ASTNode[1];
    setChild(new List(), 0);
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Modifier"},
    type = {"List<Modifier>"},
    kind = {"List"}
  )
  public ResourceModifiers(List<Modifier> p0) {
    setChild(p0, 0);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:23
   */
  protected int numChildren() {
    return 1;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:29
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:33
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    isFinal_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:38
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:43
   */
  public ResourceModifiers clone() throws CloneNotSupportedException {
    ResourceModifiers node = (ResourceModifiers) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:48
   */
  public ResourceModifiers copy() {
    try {
      ResourceModifiers node = (ResourceModifiers) clone();
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
   * @declaredat ASTNode:67
   */
  @Deprecated
  public ResourceModifiers fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:77
   */
  public ResourceModifiers treeCopyNoTransform() {
    ResourceModifiers tree = (ResourceModifiers) copy();
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
   * @declaredat ASTNode:97
   */
  public ResourceModifiers treeCopy() {
    ResourceModifiers tree = (ResourceModifiers) copy();
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
   * Replaces the Modifier list.
   * @param list The new list node to be used as the Modifier list.
   * @apilevel high-level
   */
  public ResourceModifiers setModifierList(List<Modifier> list) {
    setChild(list, 0);
    return this;
  }
  /**
   * Retrieves the number of children in the Modifier list.
   * @return Number of children in the Modifier list.
   * @apilevel high-level
   */
  public int getNumModifier() {
    return getModifierList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Modifier list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the Modifier list.
   * @apilevel low-level
   */
  public int getNumModifierNoTransform() {
    return getModifierListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Modifier list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Modifier list.
   * @apilevel high-level
   */
  public Modifier getModifier(int i) {
    return (Modifier) getModifierList().getChild(i);
  }
  /**
   * Check whether the Modifier list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasModifier() {
    return getModifierList().getNumChild() != 0;
  }
  /**
   * Append an element to the Modifier list.
   * @param node The element to append to the Modifier list.
   * @apilevel high-level
   */
  public ResourceModifiers addModifier(Modifier node) {
    List<Modifier> list = (parent == null) ? getModifierListNoTransform() : getModifierList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public ResourceModifiers addModifierNoTransform(Modifier node) {
    List<Modifier> list = getModifierListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the Modifier list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public ResourceModifiers setModifier(Modifier node, int i) {
    List<Modifier> list = getModifierList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the Modifier list.
   * @return The node representing the Modifier list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Modifier")
  public List<Modifier> getModifierList() {
    List<Modifier> list = (List<Modifier>) getChild(0);
    return list;
  }
  /**
   * Retrieves the Modifier list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Modifier list.
   * @apilevel low-level
   */
  public List<Modifier> getModifierListNoTransform() {
    return (List<Modifier>) getChildNoTransform(0);
  }
  /**
   * @return the element at index {@code i} in the Modifier list without
   * triggering rewrites.
   */
  public Modifier getModifierNoTransform(int i) {
    return (Modifier) getModifierListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Modifier list.
   * @return The node representing the Modifier list.
   * @apilevel high-level
   */
  public List<Modifier> getModifiers() {
    return getModifierList();
  }
  /**
   * Retrieves the Modifier list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Modifier list.
   * @apilevel low-level
   */
  public List<Modifier> getModifiersNoTransform() {
    return getModifierListNoTransform();
  }
  /** @apilevel internal */
  private void isFinal_reset() {
    isFinal_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isFinal_computed = null;

  /** @apilevel internal */
  protected boolean isFinal_value;

  /**
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:305
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:449")
  public boolean isFinal() {
    ASTState state = state();
    if (isFinal_computed == ASTState.NON_CYCLE || isFinal_computed == state().cycle()) {
      return isFinal_value;
    }
    isFinal_value = true;
    if (state().inCircle()) {
      isFinal_computed = state().cycle();
    
    } else {
      isFinal_computed = ASTState.NON_CYCLE;
    
    }
    return isFinal_value;
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
