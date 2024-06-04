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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:313
 * @astdecl Modifier : ASTNode ::= <ID:String>;
 * @production Modifier : {@link ASTNode} ::= <span class="component">&lt;ID:{@link String}&gt;</span>;

 */
public class Modifier extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrint.jadd:505
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(getID());
  }
  /**
   * @declaredat ASTNode:1
   */
  public Modifier() {
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
  }
  /**
   * @declaredat ASTNode:12
   */
  @ASTNodeAnnotation.Constructor(
    name = {"ID"},
    type = {"String"},
    kind = {"Token"}
  )
  public Modifier(String p0) {
    setID(p0);
  }
  /**
   * @declaredat ASTNode:20
   */
  public Modifier(beaver.Symbol p0) {
    setID(p0);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:24
   */
  protected int numChildren() {
    return 0;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:30
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:34
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    isAnAttribute_reset();
    isCollectionAttribute_reset();
    isSynAttribute_reset();
    isInhAttribute_reset();
    isCircularAttribute_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:43
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:48
   */
  public Modifier clone() throws CloneNotSupportedException {
    Modifier node = (Modifier) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:53
   */
  public Modifier copy() {
    try {
      Modifier node = (Modifier) clone();
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
   * @declaredat ASTNode:72
   */
  @Deprecated
  public Modifier fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:82
   */
  public Modifier treeCopyNoTransform() {
    Modifier tree = (Modifier) copy();
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
   * @declaredat ASTNode:102
   */
  public Modifier treeCopy() {
    Modifier tree = (Modifier) copy();
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
   * Replaces the lexeme ID.
   * @param value The new value for the lexeme ID.
   * @apilevel high-level
   */
  public Modifier setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /** @apilevel internal 
   */
  protected String tokenString_ID;
  /**
   */
  public int IDstart;
  /**
   */
  public int IDend;
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public Modifier setID(beaver.Symbol symbol) {
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
  /** @apilevel internal */
  private void isAnAttribute_reset() {
    isAnAttribute_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isAnAttribute_computed = null;

  /** @apilevel internal */
  protected boolean isAnAttribute_value;

  /**
   * @attribute syn
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:435
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:435")
  public boolean isAnAttribute() {
    ASTState state = state();
    if (isAnAttribute_computed == ASTState.NON_CYCLE || isAnAttribute_computed == state().cycle()) {
      return isAnAttribute_value;
    }
    isAnAttribute_value = false;
    if (state().inCircle()) {
      isAnAttribute_computed = state().cycle();
    
    } else {
      isAnAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isAnAttribute_value;
  }
  /** @apilevel internal */
  private void isCollectionAttribute_reset() {
    isCollectionAttribute_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isCollectionAttribute_computed = null;

  /** @apilevel internal */
  protected boolean isCollectionAttribute_value;

  /**
   * @attribute syn
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:439
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:439")
  public boolean isCollectionAttribute() {
    ASTState state = state();
    if (isCollectionAttribute_computed == ASTState.NON_CYCLE || isCollectionAttribute_computed == state().cycle()) {
      return isCollectionAttribute_value;
    }
    isCollectionAttribute_value = false;
    if (state().inCircle()) {
      isCollectionAttribute_computed = state().cycle();
    
    } else {
      isCollectionAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isCollectionAttribute_value;
  }
  /** @apilevel internal */
  private void isSynAttribute_reset() {
    isSynAttribute_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isSynAttribute_computed = null;

  /** @apilevel internal */
  protected boolean isSynAttribute_value;

  /**
   * @attribute syn
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:442
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:442")
  public boolean isSynAttribute() {
    ASTState state = state();
    if (isSynAttribute_computed == ASTState.NON_CYCLE || isSynAttribute_computed == state().cycle()) {
      return isSynAttribute_value;
    }
    isSynAttribute_value = false;
    if (state().inCircle()) {
      isSynAttribute_computed = state().cycle();
    
    } else {
      isSynAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isSynAttribute_value;
  }
  /** @apilevel internal */
  private void isInhAttribute_reset() {
    isInhAttribute_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isInhAttribute_computed = null;

  /** @apilevel internal */
  protected boolean isInhAttribute_value;

  /**
   * @attribute syn
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:445
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:445")
  public boolean isInhAttribute() {
    ASTState state = state();
    if (isInhAttribute_computed == ASTState.NON_CYCLE || isInhAttribute_computed == state().cycle()) {
      return isInhAttribute_value;
    }
    isInhAttribute_value = false;
    if (state().inCircle()) {
      isInhAttribute_computed = state().cycle();
    
    } else {
      isInhAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isInhAttribute_value;
  }
  /** @apilevel internal */
  private void isCircularAttribute_reset() {
    isCircularAttribute_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isCircularAttribute_computed = null;

  /** @apilevel internal */
  protected boolean isCircularAttribute_value;

  /**
   * @attribute syn
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:448
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:448")
  public boolean isCircularAttribute() {
    ASTState state = state();
    if (isCircularAttribute_computed == ASTState.NON_CYCLE || isCircularAttribute_computed == state().cycle()) {
      return isCircularAttribute_value;
    }
    isCircularAttribute_value = false;
    if (state().inCircle()) {
      isCircularAttribute_computed = state().cycle();
    
    } else {
      isCircularAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isCircularAttribute_value;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:342
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:342")
  public boolean isAnnotation(String packageName, String name) {
    boolean isAnnotation_String_String_value = false;
    return isAnnotation_String_String_value;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:86
   * @apilevel internal
   */
  public boolean Define_isLeftChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:86
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isLeftChildOfDot
   */
  protected boolean canDefine_isLeftChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:101
   * @apilevel internal
   */
  public boolean Define_isRightChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:101
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isRightChildOfDot
   */
  protected boolean canDefine_isRightChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:118
   * @apilevel internal
   */
  public Expr Define_prevExpr(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return prevExprError();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:118
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute prevExpr
   */
  protected boolean canDefine_prevExpr(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:142
   * @apilevel internal
   */
  public Access Define_nextAccess(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return nextAccessError();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:142
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute nextAccess
   */
  protected boolean canDefine_nextAccess(ASTNode _callerNode, ASTNode _childNode) {
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
