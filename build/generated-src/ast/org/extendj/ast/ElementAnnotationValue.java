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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/grammar/Annotations.ast:12
 * @astdecl ElementAnnotationValue : ElementValue ::= Annotation;
 * @production ElementAnnotationValue : {@link ElementValue} ::= <span class="component">{@link Annotation}</span>;

 */
public class ElementAnnotationValue extends ElementValue implements Cloneable {
  /**
   * @aspect Java5PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/PrettyPrint.jadd:86
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(getAnnotation());
  }
  /**
   * @declaredat ASTNode:1
   */
  public ElementAnnotationValue() {
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
  }
  /**
   * @declaredat ASTNode:13
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Annotation"},
    type = {"Annotation"},
    kind = {"Child"}
  )
  public ElementAnnotationValue(Annotation p0) {
    setChild(p0, 0);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:22
   */
  protected int numChildren() {
    return 1;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:28
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:32
   */
  public void flushAttrCache() {
    super.flushAttrCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:37
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:42
   */
  public ElementAnnotationValue clone() throws CloneNotSupportedException {
    ElementAnnotationValue node = (ElementAnnotationValue) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:47
   */
  public ElementAnnotationValue copy() {
    try {
      ElementAnnotationValue node = (ElementAnnotationValue) clone();
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
   * @declaredat ASTNode:66
   */
  @Deprecated
  public ElementAnnotationValue fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:76
   */
  public ElementAnnotationValue treeCopyNoTransform() {
    ElementAnnotationValue tree = (ElementAnnotationValue) copy();
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
   * @declaredat ASTNode:96
   */
  public ElementAnnotationValue treeCopy() {
    ElementAnnotationValue tree = (ElementAnnotationValue) copy();
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
   * Replaces the Annotation child.
   * @param node The new node to replace the Annotation child.
   * @apilevel high-level
   */
  public ElementAnnotationValue setAnnotation(Annotation node) {
    setChild(node, 0);
    return this;
  }
  /**
   * Retrieves the Annotation child.
   * @return The current node used as the Annotation child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Annotation")
  public Annotation getAnnotation() {
    return (Annotation) getChild(0);
  }
  /**
   * Retrieves the Annotation child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Annotation child.
   * @apilevel low-level
   */
  public Annotation getAnnotationNoTransform() {
    return (Annotation) getChildNoTransform(0);
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:675
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:675")
  public boolean commensurateWithTypeDecl(TypeDecl type) {
    {
        return type() == type;
      }
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:735
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:731")
  public TypeDecl type() {
    TypeDecl type_value = getAnnotation().type();
    return type_value;
  }
  /**
   * @attribute inh
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:604
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:604")
  public Annotation lookupAnnotation(TypeDecl typeDecl) {
    Annotation lookupAnnotation_TypeDecl_value = getParent().Define_lookupAnnotation(this, null, typeDecl);
    return lookupAnnotation_TypeDecl_value;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:131
   * @apilevel internal
   */
  public boolean Define_mayUseAnnotationTarget(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (getAnnotationNoTransform() != null && _callerNode == getAnnotation()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:167
      return true;
    }
    else {
      return getParent().Define_mayUseAnnotationTarget(this, _callerNode, name);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:131
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayUseAnnotationTarget
   */
  protected boolean canDefine_mayUseAnnotationTarget(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:604
   * @apilevel internal
   */
  public Annotation Define_lookupAnnotation(ASTNode _callerNode, ASTNode _childNode, TypeDecl typeDecl) {
    if (getAnnotationNoTransform() != null && _callerNode == getAnnotation()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:610
      return getAnnotation().type() == typeDecl ? getAnnotation() : lookupAnnotation(typeDecl);
    }
    else {
      return getParent().Define_lookupAnnotation(this, _callerNode, typeDecl);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:604
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupAnnotation
   */
  protected boolean canDefine_lookupAnnotation(ASTNode _callerNode, ASTNode _childNode, TypeDecl typeDecl) {
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
