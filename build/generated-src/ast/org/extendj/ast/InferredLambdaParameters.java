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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/grammar/Lambda.ast:8
 * @astdecl InferredLambdaParameters : LambdaParameters ::= Parameter:InferredParameterDeclaration*;
 * @production InferredLambdaParameters : {@link LambdaParameters} ::= <span class="component">Parameter:{@link InferredParameterDeclaration}*</span>;

 */
public class InferredLambdaParameters extends LambdaParameters implements Cloneable {
  /**
   * @aspect PrettyPrintUtil8
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PrettyPrintUtil.jadd:41
   */
  @Override public String toString() {
    int numParams = 0;
    StringBuilder params = new StringBuilder();
    for (InferredParameterDeclaration param : getParameterListNoTransform()) {
      if (numParams > 0) {
        params.append(", ");
      }
      params.append(param.toString());
      numParams += 1;
    }
    if (numParams == 1) {
      return params.toString();
    } else {
      return String.format("(%s)", params.toString());
    }
  }
  /**
   * @aspect Java8PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PrettyPrint.jadd:85
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print("(");
    out.join(getParameters(), new PrettyPrinter.Joiner() {
      @Override
      public void printSeparator(PrettyPrinter out) {
        out.print(", ");
      }
    });
    out.print(")");
  }
  /**
   * @aspect LambdaToClass
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaAnonymousDecl.jrag:121
   */
  protected List<ParameterDeclaration> toParameterList() {
    List<ParameterDeclaration> paramList = new List<ParameterDeclaration>();
    FunctionDescriptor fd = enclosingLambda().targetInterface().functionDescriptor();
    if (fd.method.hasValue()) {
      MethodDecl targetMethod = fd.method.get();
      int i = 0;
      for (InferredParameterDeclaration infDecl : getParameterList()) {
        ParameterDeclaration funcDecl = targetMethod.getParameter(i);
        paramList.add(
            new ParameterDeclaration(funcDecl.type().createQualifiedAccess(), infDecl.name()));
        i += 1;
      }
    }
    return paramList;
  }
  /**
   * @declaredat ASTNode:1
   */
  public InferredLambdaParameters() {
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
    name = {"Parameter"},
    type = {"List<InferredParameterDeclaration>"},
    kind = {"List"}
  )
  public InferredLambdaParameters(List<InferredParameterDeclaration> p0) {
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
    numParameters_reset();
    congruentTo_FunctionDescriptor_reset();
    parameterDeclaration_String_reset();
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
  public InferredLambdaParameters clone() throws CloneNotSupportedException {
    InferredLambdaParameters node = (InferredLambdaParameters) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:50
   */
  public InferredLambdaParameters copy() {
    try {
      InferredLambdaParameters node = (InferredLambdaParameters) clone();
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
  public InferredLambdaParameters fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:79
   */
  public InferredLambdaParameters treeCopyNoTransform() {
    InferredLambdaParameters tree = (InferredLambdaParameters) copy();
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
  public InferredLambdaParameters treeCopy() {
    InferredLambdaParameters tree = (InferredLambdaParameters) copy();
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
   * Replaces the Parameter list.
   * @param list The new list node to be used as the Parameter list.
   * @apilevel high-level
   */
  public InferredLambdaParameters setParameterList(List<InferredParameterDeclaration> list) {
    setChild(list, 0);
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
  public InferredParameterDeclaration getParameter(int i) {
    return (InferredParameterDeclaration) getParameterList().getChild(i);
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
  public InferredLambdaParameters addParameter(InferredParameterDeclaration node) {
    List<InferredParameterDeclaration> list = (parent == null) ? getParameterListNoTransform() : getParameterList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public InferredLambdaParameters addParameterNoTransform(InferredParameterDeclaration node) {
    List<InferredParameterDeclaration> list = getParameterListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the Parameter list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public InferredLambdaParameters setParameter(InferredParameterDeclaration node, int i) {
    List<InferredParameterDeclaration> list = getParameterList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the Parameter list.
   * @return The node representing the Parameter list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Parameter")
  public List<InferredParameterDeclaration> getParameterList() {
    List<InferredParameterDeclaration> list = (List<InferredParameterDeclaration>) getChild(0);
    return list;
  }
  /**
   * Retrieves the Parameter list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Parameter list.
   * @apilevel low-level
   */
  public List<InferredParameterDeclaration> getParameterListNoTransform() {
    return (List<InferredParameterDeclaration>) getChildNoTransform(0);
  }
  /**
   * @return the element at index {@code i} in the Parameter list without
   * triggering rewrites.
   */
  public InferredParameterDeclaration getParameterNoTransform(int i) {
    return (InferredParameterDeclaration) getParameterListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Parameter list.
   * @return The node representing the Parameter list.
   * @apilevel high-level
   */
  public List<InferredParameterDeclaration> getParameters() {
    return getParameterList();
  }
  /**
   * Retrieves the Parameter list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Parameter list.
   * @apilevel low-level
   */
  public List<InferredParameterDeclaration> getParametersNoTransform() {
    return getParameterListNoTransform();
  }
  /** @apilevel internal */
  private void numParameters_reset() {
    numParameters_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle numParameters_computed = null;

  /** @apilevel internal */
  protected int numParameters_value;

  /**
   * @attribute syn
   * @aspect LambdaExpr
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:50
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaExpr", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:47")
  public int numParameters() {
    ASTState state = state();
    if (numParameters_computed == ASTState.NON_CYCLE || numParameters_computed == state().cycle()) {
      return numParameters_value;
    }
    numParameters_value = getNumParameter();
    if (state().inCircle()) {
      numParameters_computed = state().cycle();
    
    } else {
      numParameters_computed = ASTState.NON_CYCLE;
    
    }
    return numParameters_value;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:56
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaExpr", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:53")
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
        return numParameters() == fd.method.get().getNumParameter();
      } else {
        return false;
      }
    }
  /** @apilevel internal */
  private void parameterDeclaration_String_reset() {
    parameterDeclaration_String_computed = null;
    parameterDeclaration_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map parameterDeclaration_String_values;
  /** @apilevel internal */
  protected java.util.Map parameterDeclaration_String_computed;
  /**
   * @attribute syn
   * @aspect VariableScope
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:47
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:47")
  public SimpleSet<Variable> parameterDeclaration(String name) {
    Object _parameters = name;
    if (parameterDeclaration_String_computed == null) parameterDeclaration_String_computed = new java.util.HashMap(4);
    if (parameterDeclaration_String_values == null) parameterDeclaration_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (parameterDeclaration_String_values.containsKey(_parameters)
        && parameterDeclaration_String_computed.containsKey(_parameters)
        && (parameterDeclaration_String_computed.get(_parameters) == ASTState.NON_CYCLE || parameterDeclaration_String_computed.get(_parameters) == state().cycle())) {
      return (SimpleSet<Variable>) parameterDeclaration_String_values.get(_parameters);
    }
    SimpleSet<Variable> parameterDeclaration_String_value = parameterDeclaration_compute(name);
    if (state().inCircle()) {
      parameterDeclaration_String_values.put(_parameters, parameterDeclaration_String_value);
      parameterDeclaration_String_computed.put(_parameters, state().cycle());
    
    } else {
      parameterDeclaration_String_values.put(_parameters, parameterDeclaration_String_value);
      parameterDeclaration_String_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return parameterDeclaration_String_value;
  }
  /** @apilevel internal */
  private SimpleSet<Variable> parameterDeclaration_compute(String name) {
      for (int i = 0; i < getNumParameter(); i++) {
        if (getParameter(i).name().equals(name)) {
          return (InferredParameterDeclaration) getParameter(i);
        }
      }
      return emptySet();
    }
  /**
   * @attribute syn
   * @aspect Java8NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:560
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Java8NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:560")
  public Collection<Problem> nameProblems() {
    {
        for (int i = 0; i < getNumParameter(); i++) {
          if (getParameter(i).name().equals("_")) {
            // 15.27.1
            return Collections.singletonList(
                error("Underscore is not a valid identifier for a lambda parameter"));
          }
        }
        return Collections.emptyList();
      }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:506
   * @apilevel internal
   */
  public TypeDecl Define_inferredType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:507
      int i = _callerNode.getIndexOfChild(_childNode);
      {
          if (enclosingLambda().targetInterface() == null) {
            return unknownType();
          }
          InterfaceDecl iDecl = (InterfaceDecl) enclosingLambda().targetInterface();
          if (!iDecl.isFunctional()) {
            return unknownType();
          } else {
            FunctionDescriptor fd = iDecl.functionDescriptor();
            if (fd.method.hasValue()) {
              MethodDecl targetMethod = fd.method.get();
              if (targetMethod.getNumParameter() < i + 1) {
                return unknownType();
              } else {
                // The target functional interface matches this lambda.
                return targetMethod.getParameter(i).type();
              }
            } else {
              // No target method.
              return unknownType();
            }
          }
        }
    }
    else {
      return getParent().Define_inferredType(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:506
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inferredType
   */
  protected boolean canDefine_inferredType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:30
   * @apilevel internal
   */
  public SimpleSet<Variable> Define_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:35
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return parameterDeclaration(name);
    }
    else {
      return getParent().Define_lookupVariable(this, _callerNode, name);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:30
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupVariable
   */
  protected boolean canDefine_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:30
   * @apilevel internal
   */
  public boolean Define_inhModifiedInScope(ASTNode _callerNode, ASTNode _childNode, Variable var) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:37
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return inhModifiedInScope(var);
    }
    else {
      return getParent().Define_inhModifiedInScope(this, _callerNode, var);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:30
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inhModifiedInScope
   */
  protected boolean canDefine_inhModifiedInScope(ASTNode _callerNode, ASTNode _childNode, Variable var) {
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
  /** @apilevel internal */
  protected void collect_contributors_CompilationUnit_problems(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:558
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_CompilationUnit_problems(_root, _map);
  }
  /** @apilevel internal */
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    for (Problem value : nameProblems()) {
      collection.add(value);
    }
  }

}
