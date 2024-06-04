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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/grammar/Lambda.ast:12
 * @astdecl BlockLambdaBody : LambdaBody ::= Block;
 * @production BlockLambdaBody : {@link LambdaBody} ::= <span class="component">{@link Block}</span>;

 */
public class BlockLambdaBody extends LambdaBody implements Cloneable {
  /**
   * @aspect PrettyPrintUtil8
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PrettyPrintUtil.jadd:79
   */
  @Override public String toString() {
    return "{ ... }";
  }
  /**
   * @aspect Java8PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PrettyPrint.jadd:39
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(getBlock());
  }
  /**
   * @aspect LambdaToClass
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaAnonymousDecl.jrag:88
   */
  protected Block toBlock() {
    return getBlock().treeCopyNoTransform();
  }
  /**
   * @aspect ReturnCompatible
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaBody.jrag:57
   */
  public boolean noReturnsHasResult() {
    ArrayList<ReturnStmt> returnList = lambdaReturns();
    for (int i = 0; i < returnList.size(); i++) {
      if (returnList.get(i).hasResult()) {
        return false;
      }
    }
    return true;
  }
  /**
   * @aspect ReturnCompatible
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaBody.jrag:67
   */
  public boolean allReturnsHasResult() {
    ArrayList<ReturnStmt> returnList = lambdaReturns();
    for (int i = 0; i < returnList.size(); i++) {
      if (!returnList.get(i).hasResult()) {
        return false;
      }
    }
    return true;
  }
  /**
   * @declaredat ASTNode:1
   */
  public BlockLambdaBody() {
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
    name = {"Block"},
    type = {"Block"},
    kind = {"Child"}
  )
  public BlockLambdaBody(Block p0) {
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
    congruentTo_FunctionDescriptor_reset();
    isBlockBody_reset();
    isExprBody_reset();
    voidCompatible_reset();
    valueCompatible_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:41
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
    BlockLambdaBody_lambdaReturns_computed = null;
    BlockLambdaBody_lambdaReturns_value = null;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:47
   */
  public BlockLambdaBody clone() throws CloneNotSupportedException {
    BlockLambdaBody node = (BlockLambdaBody) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:52
   */
  public BlockLambdaBody copy() {
    try {
      BlockLambdaBody node = (BlockLambdaBody) clone();
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
   * @declaredat ASTNode:71
   */
  @Deprecated
  public BlockLambdaBody fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:81
   */
  public BlockLambdaBody treeCopyNoTransform() {
    BlockLambdaBody tree = (BlockLambdaBody) copy();
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
   * @declaredat ASTNode:101
   */
  public BlockLambdaBody treeCopy() {
    BlockLambdaBody tree = (BlockLambdaBody) copy();
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
   * Replaces the Block child.
   * @param node The new node to replace the Block child.
   * @apilevel high-level
   */
  public BlockLambdaBody setBlock(Block node) {
    setChild(node, 0);
    return this;
  }
  /**
   * Retrieves the Block child.
   * @return The current node used as the Block child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Block")
  public Block getBlock() {
    return (Block) getChild(0);
  }
  /**
   * Retrieves the Block child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Block child.
   * @apilevel low-level
   */
  public Block getBlockNoTransform() {
    return (Block) getChildNoTransform(0);
  }
  /** @apilevel internal */
  private void congruentTo_FunctionDescriptor_reset() {
    congruentTo_FunctionDescriptor_computed = null;
    congruentTo_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map congruentTo_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map congruentTo_FunctionDescriptor_computed;
  /**
   * @attribute syn
   * @aspect LambdaExpr
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:105
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaExpr", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:89")
  public boolean congruentTo(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (congruentTo_FunctionDescriptor_computed == null) congruentTo_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (congruentTo_FunctionDescriptor_values == null) congruentTo_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (congruentTo_FunctionDescriptor_values.containsKey(_parameters)
        && congruentTo_FunctionDescriptor_computed.containsKey(_parameters)
        && (congruentTo_FunctionDescriptor_computed.get(_parameters) == ASTState.NON_CYCLE || congruentTo_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (Boolean) congruentTo_FunctionDescriptor_values.get(_parameters);
    }
    boolean congruentTo_FunctionDescriptor_value = congruentTo_compute(fd);
    if (state().inCircle()) {
      congruentTo_FunctionDescriptor_values.put(_parameters, congruentTo_FunctionDescriptor_value);
      congruentTo_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      congruentTo_FunctionDescriptor_values.put(_parameters, congruentTo_FunctionDescriptor_value);
      congruentTo_FunctionDescriptor_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return congruentTo_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private boolean congruentTo_compute(FunctionDescriptor fd) {
      if (fd.method.hasValue()) {
        TypeDecl methodType = fd.method.get().type();
        if (methodType.isVoid()) {
          return voidCompatible();
        } else {
          if (!valueCompatible()) {
            return false;
          }
          for (ReturnStmt returnStmt : lambdaReturns()) {
            if (!returnStmt.getResult().assignConversionTo(methodType)) {
              return false;
            }
          }
          return true;
        }
      } else {
        return false;
      }
    }
  /** @apilevel internal */
  private void isBlockBody_reset() {
    isBlockBody_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isBlockBody_computed = null;

  /** @apilevel internal */
  protected boolean isBlockBody_value;

  /**
   * @attribute syn
   * @aspect LambdaBody
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaBody.jrag:32
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaBody", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaBody.jrag:29")
  public boolean isBlockBody() {
    ASTState state = state();
    if (isBlockBody_computed == ASTState.NON_CYCLE || isBlockBody_computed == state().cycle()) {
      return isBlockBody_value;
    }
    isBlockBody_value = true;
    if (state().inCircle()) {
      isBlockBody_computed = state().cycle();
    
    } else {
      isBlockBody_computed = ASTState.NON_CYCLE;
    
    }
    return isBlockBody_value;
  }
  /** @apilevel internal */
  private void isExprBody_reset() {
    isExprBody_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isExprBody_computed = null;

  /** @apilevel internal */
  protected boolean isExprBody_value;

  /**
   * @attribute syn
   * @aspect LambdaBody
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaBody.jrag:33
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaBody", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaBody.jrag:30")
  public boolean isExprBody() {
    ASTState state = state();
    if (isExprBody_computed == ASTState.NON_CYCLE || isExprBody_computed == state().cycle()) {
      return isExprBody_value;
    }
    isExprBody_value = false;
    if (state().inCircle()) {
      isExprBody_computed = state().cycle();
    
    } else {
      isExprBody_computed = ASTState.NON_CYCLE;
    
    }
    return isExprBody_value;
  }
  /** @apilevel internal */
  private void voidCompatible_reset() {
    voidCompatible_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle voidCompatible_computed = null;

  /** @apilevel internal */
  protected boolean voidCompatible_value;

  /**
   * @attribute syn
   * @aspect ReturnCompatible
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaBody.jrag:44
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ReturnCompatible", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaBody.jrag:40")
  public boolean voidCompatible() {
    ASTState state = state();
    if (voidCompatible_computed == ASTState.NON_CYCLE || voidCompatible_computed == state().cycle()) {
      return voidCompatible_value;
    }
    voidCompatible_value = noReturnsHasResult();
    if (state().inCircle()) {
      voidCompatible_computed = state().cycle();
    
    } else {
      voidCompatible_computed = ASTState.NON_CYCLE;
    
    }
    return voidCompatible_value;
  }
  /** @apilevel internal */
  private void valueCompatible_reset() {
    valueCompatible_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle valueCompatible_computed = null;

  /** @apilevel internal */
  protected boolean valueCompatible_value;

  /**
   * @attribute syn
   * @aspect ReturnCompatible
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaBody.jrag:45
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ReturnCompatible", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaBody.jrag:41")
  public boolean valueCompatible() {
    ASTState state = state();
    if (valueCompatible_computed == ASTState.NON_CYCLE || valueCompatible_computed == state().cycle()) {
      return valueCompatible_value;
    }
    valueCompatible_value = allReturnsHasResult() && !getBlock().canCompleteNormally();
    if (state().inCircle()) {
      valueCompatible_computed = state().cycle();
    
    } else {
      valueCompatible_computed = ASTState.NON_CYCLE;
    
    }
    return valueCompatible_value;
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:45
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:43")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = getBlock().modifiedInScope(var);
    return modifiedInScope_Variable_value;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/UnreachableStatements.jrag:49
   * @apilevel internal
   */
  public boolean Define_reachable(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/UnreachableStatements.jrag:29
      return true;
    }
    else {
      return getParent().Define_reachable(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/UnreachableStatements.jrag:49
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute reachable
   */
  protected boolean canDefine_reachable(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:537
   * @apilevel internal
   */
  public TypeDecl Define_returnType(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:39
      {
          TypeDecl decl = enclosingLambda().targetType();
          if (decl == null) {
            return unknownType();
          } else if (!(decl instanceof InterfaceDecl)) {
            return unknownType();
          } else {
            InterfaceDecl iDecl = (InterfaceDecl) decl;
            if (!iDecl.isFunctional()) {
              return unknownType();
            } else {
              FunctionDescriptor fd = iDecl.functionDescriptor();
              if (fd.method.hasValue()) {
                return fd.method.get().type();
              } else {
                return unknownType();
              }
            }
          }
        }
    }
    else {
      return getParent().Define_returnType(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:537
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute returnType
   */
  protected boolean canDefine_returnType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:252
   * @apilevel internal
   */
  public boolean Define_assignmentContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:270
      return false;
    }
    else {
      return getParent().Define_assignmentContext(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:252
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute assignmentContext
   */
  protected boolean canDefine_assignmentContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:253
   * @apilevel internal
   */
  public boolean Define_invocationContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:271
      return false;
    }
    else {
      return getParent().Define_invocationContext(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:253
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute invocationContext
   */
  protected boolean canDefine_invocationContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:254
   * @apilevel internal
   */
  public boolean Define_castContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:272
      return false;
    }
    else {
      return getParent().Define_castContext(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:254
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute castContext
   */
  protected boolean canDefine_castContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:255
   * @apilevel internal
   */
  public boolean Define_stringContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:273
      return false;
    }
    else {
      return getParent().Define_stringContext(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:255
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute stringContext
   */
  protected boolean canDefine_stringContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:256
   * @apilevel internal
   */
  public boolean Define_numericContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:274
      return false;
    }
    else {
      return getParent().Define_numericContext(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:256
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute numericContext
   */
  protected boolean canDefine_numericContext(ASTNode _callerNode, ASTNode _childNode) {
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
  /**
   * @attribute coll
   * @aspect ReturnCompatible
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaBody.jrag:47
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="ReturnCompatible", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaBody.jrag:47")
  public ArrayList<ReturnStmt> lambdaReturns() {
    ASTState state = state();
    if (BlockLambdaBody_lambdaReturns_computed == ASTState.NON_CYCLE || BlockLambdaBody_lambdaReturns_computed == state().cycle()) {
      return BlockLambdaBody_lambdaReturns_value;
    }
    BlockLambdaBody_lambdaReturns_value = lambdaReturns_compute();
    if (state().inCircle()) {
      BlockLambdaBody_lambdaReturns_computed = state().cycle();
    
    } else {
      BlockLambdaBody_lambdaReturns_computed = ASTState.NON_CYCLE;
    
    }
    return BlockLambdaBody_lambdaReturns_value;
  }
  /** @apilevel internal */
  private ArrayList<ReturnStmt> lambdaReturns_compute() {
    ASTNode node = this;
    while (node != null && !(node instanceof Program)) {
      node = node.getParent();
    }
    Program root = (Program) node;
    root.survey_BlockLambdaBody_lambdaReturns();
    ArrayList<ReturnStmt> _computedValue = new ArrayList<ReturnStmt>();
    if (root.contributorMap_BlockLambdaBody_lambdaReturns.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_BlockLambdaBody_lambdaReturns.get(this)) {
        contributor.contributeTo_BlockLambdaBody_lambdaReturns(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle BlockLambdaBody_lambdaReturns_computed = null;

  /** @apilevel internal */
  protected ArrayList<ReturnStmt> BlockLambdaBody_lambdaReturns_value;

  /** @apilevel internal */
  protected void collect_contributors_CompilationUnit_problems(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:207
    if (!voidCompatible() && !valueCompatible()) {
      {
        java.util.Set<ASTNode> contributors = _map.get(_root);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) _root, contributors);
        }
        contributors.add(this);
      }
    }
    super.collect_contributors_CompilationUnit_problems(_root, _map);
  }
  /** @apilevel internal */
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    if (!voidCompatible() && !valueCompatible()) {
      collection.add(error("Block lambda bodies must be either void or value compatible"));
    }
  }

}
