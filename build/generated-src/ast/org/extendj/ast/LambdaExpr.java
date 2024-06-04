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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/grammar/Lambda.ast:1
 * @astdecl LambdaExpr : Expr ::= LambdaParameters LambdaBody;
 * @production LambdaExpr : {@link Expr} ::= <span class="component">{@link LambdaParameters}</span> <span class="component">{@link LambdaBody}</span>;

 */
public class LambdaExpr extends Expr implements Cloneable, VariableScope {
  /**
   * @aspect PrettyPrintUtil8
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PrettyPrintUtil.jadd:35
   */
  @Override public String toString() {
    return String.format("%s->%s",
        getLambdaParametersNoTransform().toString(),
        getLambdaBodyNoTransform().toString());
  }
  /**
   * @aspect Java8PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PrettyPrint.jadd:113
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(getLambdaParameters());
    out.print(" -> ");
    out.print(getLambdaBody());
  }
  /**
   * Build an anonymous class which will be converted to byte code. Since a
   * lambda can't target generic methods, eventual type variables don't have to
   * be taken into account.
   * @aspect LambdaToClass
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaAnonymousDecl.jrag:44
   */
  protected TypeDecl buildAnonymousDecl() {
    List<Access> implementsList = new List<Access>();
    InterfaceDecl iDecl = targetInterface();

    // Compute the interface type implemented by the anonymous class:
    iDecl = (InterfaceDecl) iDecl.nonWildcardParameterization().getOrElse(iDecl);
    implementsList.add(iDecl.createQualifiedAccess());

    // Now we will build the single method of the anonymous class.
    List<BodyDecl> bodyDecls = new List<BodyDecl>();
    Modifiers methodModifiers = new Modifiers(new List<Modifier>().add(new Modifier("public")));
    FunctionDescriptor fd = iDecl.functionDescriptor();
    TypeDecl methodType;
    String methodName;
    if (fd.method.hasValue()) {
      methodType = fd.method.get().type();
      methodName = fd.method.get().name();
    } else {
      methodType = unknownType();
      methodName = "<unknown>";
    }
    Access returnType = methodType.createQualifiedAccess();
    List<ParameterDeclaration> methodParams = getLambdaParameters().toParameterList();
    List<Access> methodThrows = new List<Access>();
    for (TypeDecl throwsType : iDecl.functionDescriptor().throwsList) {
      methodThrows.add(throwsType.createQualifiedAccess());
    }
    Opt<Block> methodBlock = new Opt<Block>(getLambdaBody().toBlock());
    MethodDecl method = new MethodDecl(methodModifiers, returnType,
        methodName, methodParams, methodThrows, methodBlock);

    bodyDecls.add(method);

    // Now the anonymous class can be built. We use the type
    // LambdaAnonymousDecl instead of a normal AnonymousDecl in order for this
    // and super keywords to get the type of the outer scope.
    return new LambdaAnonymousDecl(new Modifiers(), "Lambda", implementsList, bodyDecls);
  }
  /**
   * @aspect LambdaExpr
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:31
   */
  public void collectBranches(Collection<Stmt> c) {
    // Don't add branches inside the lambda.
  }
  /**
   * @declaredat ASTNode:1
   */
  public LambdaExpr() {
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
  }
  /**
   * @declaredat ASTNode:13
   */
  @ASTNodeAnnotation.Constructor(
    name = {"LambdaParameters", "LambdaBody"},
    type = {"LambdaParameters", "LambdaBody"},
    kind = {"Child", "Child"}
  )
  public LambdaExpr(LambdaParameters p0, LambdaBody p1) {
    setChild(p0, 0);
    setChild(p1, 1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:23
   */
  protected int numChildren() {
    return 2;
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
    isPolyExpression_reset();
    assignConversionTo_TypeDecl_reset();
    toClass_reset();
    arity_reset();
    numParameters_reset();
    isImplicit_reset();
    isExplicit_reset();
    congruentTo_FunctionDescriptor_reset();
    compatibleStrictContext_TypeDecl_reset();
    compatibleLooseContext_TypeDecl_reset();
    pertinentToApplicability_Expr_BodyDecl_int_reset();
    moreSpecificThan_TypeDecl_TypeDecl_reset();
    potentiallyCompatible_TypeDecl_BodyDecl_reset();
    type_reset();
    targetInterface_reset();
    enclosingLambda_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:53
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:58
   */
  public LambdaExpr clone() throws CloneNotSupportedException {
    LambdaExpr node = (LambdaExpr) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:63
   */
  public LambdaExpr copy() {
    try {
      LambdaExpr node = (LambdaExpr) clone();
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
   * @declaredat ASTNode:82
   */
  @Deprecated
  public LambdaExpr fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:92
   */
  public LambdaExpr treeCopyNoTransform() {
    LambdaExpr tree = (LambdaExpr) copy();
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
   * @declaredat ASTNode:112
   */
  public LambdaExpr treeCopy() {
    LambdaExpr tree = (LambdaExpr) copy();
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
   * Replaces the LambdaParameters child.
   * @param node The new node to replace the LambdaParameters child.
   * @apilevel high-level
   */
  public LambdaExpr setLambdaParameters(LambdaParameters node) {
    setChild(node, 0);
    return this;
  }
  /**
   * Retrieves the LambdaParameters child.
   * @return The current node used as the LambdaParameters child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="LambdaParameters")
  public LambdaParameters getLambdaParameters() {
    return (LambdaParameters) getChild(0);
  }
  /**
   * Retrieves the LambdaParameters child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the LambdaParameters child.
   * @apilevel low-level
   */
  public LambdaParameters getLambdaParametersNoTransform() {
    return (LambdaParameters) getChildNoTransform(0);
  }
  /**
   * Replaces the LambdaBody child.
   * @param node The new node to replace the LambdaBody child.
   * @apilevel high-level
   */
  public LambdaExpr setLambdaBody(LambdaBody node) {
    setChild(node, 1);
    return this;
  }
  /**
   * Retrieves the LambdaBody child.
   * @return The current node used as the LambdaBody child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="LambdaBody")
  public LambdaBody getLambdaBody() {
    return (LambdaBody) getChild(1);
  }
  /**
   * Retrieves the LambdaBody child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the LambdaBody child.
   * @apilevel low-level
   */
  public LambdaBody getLambdaBodyNoTransform() {
    return (LambdaBody) getChildNoTransform(1);
  }
  /** @apilevel internal */
  private void isPolyExpression_reset() {
    isPolyExpression_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isPolyExpression_computed = null;

  /** @apilevel internal */
  protected boolean isPolyExpression_value;

  /**
   * @attribute syn
   * @aspect PolyExpressions
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:99
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PolyExpressions", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:86")
  public boolean isPolyExpression() {
    ASTState state = state();
    if (isPolyExpression_computed == ASTState.NON_CYCLE || isPolyExpression_computed == state().cycle()) {
      return isPolyExpression_value;
    }
    isPolyExpression_value = true;
    if (state().inCircle()) {
      isPolyExpression_computed = state().cycle();
    
    } else {
      isPolyExpression_computed = ASTState.NON_CYCLE;
    
    }
    return isPolyExpression_value;
  }
  /** @apilevel internal */
  private void assignConversionTo_TypeDecl_reset() {
    assignConversionTo_TypeDecl_computed = null;
    assignConversionTo_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map assignConversionTo_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map assignConversionTo_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect PolyExpressions
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:151
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PolyExpressions", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:149")
  public boolean assignConversionTo(TypeDecl type) {
    Object _parameters = type;
    if (assignConversionTo_TypeDecl_computed == null) assignConversionTo_TypeDecl_computed = new java.util.HashMap(4);
    if (assignConversionTo_TypeDecl_values == null) assignConversionTo_TypeDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (assignConversionTo_TypeDecl_values.containsKey(_parameters)
        && assignConversionTo_TypeDecl_computed.containsKey(_parameters)
        && (assignConversionTo_TypeDecl_computed.get(_parameters) == ASTState.NON_CYCLE || assignConversionTo_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) assignConversionTo_TypeDecl_values.get(_parameters);
    }
    boolean assignConversionTo_TypeDecl_value = assignConversionTo_compute(type);
    if (state().inCircle()) {
      assignConversionTo_TypeDecl_values.put(_parameters, assignConversionTo_TypeDecl_value);
      assignConversionTo_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      assignConversionTo_TypeDecl_values.put(_parameters, assignConversionTo_TypeDecl_value);
      assignConversionTo_TypeDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return assignConversionTo_TypeDecl_value;
  }
  /** @apilevel internal */
  private boolean assignConversionTo_compute(TypeDecl type) {
      if (!type.isFunctionalInterface()) {
        return false;
      }
      FunctionDescriptor f = ((InterfaceDecl) type).functionDescriptor();
      return congruentTo(f);
    }
  /** @apilevel internal */
  private void toClass_reset() {
    toClass_computed = false;
    
    toClass_value = null;
  }
  /** @apilevel internal */
  protected boolean toClass_computed = false;

  /** @apilevel internal */
  protected ClassInstanceExpr toClass_value;

  /** Constructs an anonymous class instance expression based on this lambda. 
   * @attribute syn
   * @aspect LambdaToClass
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaAnonymousDecl.jrag:31
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="LambdaToClass", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaAnonymousDecl.jrag:31")
  public ClassInstanceExpr toClass() {
    ASTState state = state();
    if (toClass_computed) {
      return toClass_value;
    }
    state().enterLazyAttribute();
    toClass_value = new ClassInstanceExpr(
              targetInterface().createQualifiedAccess(),
              new List<Expr>(),
              new Opt<TypeDecl>(buildAnonymousDecl()));
    toClass_value.setParent(this);
    toClass_computed = true;
    state().leaveLazyAttribute();
    return toClass_value;
  }
  /**
   * @attribute syn
   * @aspect LambdaToClass
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaAnonymousDecl.jrag:37
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaToClass", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaAnonymousDecl.jrag:37")
  public TypeDecl anonymousDecl() {
    TypeDecl anonymousDecl_value = toClass().getTypeDecl();
    return anonymousDecl_value;
  }
  /** @apilevel internal */
  private void arity_reset() {
    arity_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle arity_computed = null;

  /** @apilevel internal */
  protected int arity_value;

  /**
   * @attribute syn
   * @aspect LambdaExpr
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:45
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaExpr", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:45")
  public int arity() {
    ASTState state = state();
    if (arity_computed == ASTState.NON_CYCLE || arity_computed == state().cycle()) {
      return arity_value;
    }
    arity_value = numParameters();
    if (state().inCircle()) {
      arity_computed = state().cycle();
    
    } else {
      arity_computed = ASTState.NON_CYCLE;
    
    }
    return arity_value;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:48
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaExpr", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:48")
  public int numParameters() {
    ASTState state = state();
    if (numParameters_computed == ASTState.NON_CYCLE || numParameters_computed == state().cycle()) {
      return numParameters_value;
    }
    numParameters_value = getLambdaParameters().numParameters();
    if (state().inCircle()) {
      numParameters_computed = state().cycle();
    
    } else {
      numParameters_computed = ASTState.NON_CYCLE;
    
    }
    return numParameters_value;
  }
  /** @apilevel internal */
  private void isImplicit_reset() {
    isImplicit_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isImplicit_computed = null;

  /** @apilevel internal */
  protected boolean isImplicit_value;

  /**
   * @attribute syn
   * @aspect LambdaExpr
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:84
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaExpr", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:84")
  public boolean isImplicit() {
    ASTState state = state();
    if (isImplicit_computed == ASTState.NON_CYCLE || isImplicit_computed == state().cycle()) {
      return isImplicit_value;
    }
    isImplicit_value = getLambdaParameters() instanceof InferredLambdaParameters;
    if (state().inCircle()) {
      isImplicit_computed = state().cycle();
    
    } else {
      isImplicit_computed = ASTState.NON_CYCLE;
    
    }
    return isImplicit_value;
  }
  /** @apilevel internal */
  private void isExplicit_reset() {
    isExplicit_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isExplicit_computed = null;

  /** @apilevel internal */
  protected boolean isExplicit_value;

  /**
   * @attribute syn
   * @aspect LambdaExpr
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:87
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaExpr", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:87")
  public boolean isExplicit() {
    ASTState state = state();
    if (isExplicit_computed == ASTState.NON_CYCLE || isExplicit_computed == state().cycle()) {
      return isExplicit_value;
    }
    isExplicit_value = !isImplicit();
    if (state().inCircle()) {
      isExplicit_computed = state().cycle();
    
    } else {
      isExplicit_computed = ASTState.NON_CYCLE;
    
    }
    return isExplicit_value;
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
   * BEWARE! READ THIS BEFORE USING THIS ATTRIBUTE!
   * 
   * The congruency check will currently not infer different types for eventual
   * inferred parameters, but the target type function descriptor will always
   * be used for inference. Thus this check will NOT work for arbitrary
   * function descriptors if there are inferred parameters in the lambda.
   * Currently, there is no use for this to work anyway because a lambda with
   * inferred parameters will never be pertinent to applicability and thus not
   * need to be congruency checked, but in case there is need for arbitary
   * congruency checks that handle inferrence differently depending on the
   * function descriptor input to this method, then this check must be altered!
   * @attribute syn
   * @aspect LambdaExpr
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:140
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaExpr", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:140")
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
    boolean congruentTo_FunctionDescriptor_value = !fd.isGeneric() && getLambdaParameters().congruentTo(fd) && getLambdaBody().congruentTo(fd);
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
  private void compatibleStrictContext_TypeDecl_reset() {
    compatibleStrictContext_TypeDecl_computed = null;
    compatibleStrictContext_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map compatibleStrictContext_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map compatibleStrictContext_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:60
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:58")
  public boolean compatibleStrictContext(TypeDecl type) {
    Object _parameters = type;
    if (compatibleStrictContext_TypeDecl_computed == null) compatibleStrictContext_TypeDecl_computed = new java.util.HashMap(4);
    if (compatibleStrictContext_TypeDecl_values == null) compatibleStrictContext_TypeDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (compatibleStrictContext_TypeDecl_values.containsKey(_parameters)
        && compatibleStrictContext_TypeDecl_computed.containsKey(_parameters)
        && (compatibleStrictContext_TypeDecl_computed.get(_parameters) == ASTState.NON_CYCLE || compatibleStrictContext_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) compatibleStrictContext_TypeDecl_values.get(_parameters);
    }
    boolean compatibleStrictContext_TypeDecl_value = compatibleStrictContext_compute(type);
    if (state().inCircle()) {
      compatibleStrictContext_TypeDecl_values.put(_parameters, compatibleStrictContext_TypeDecl_value);
      compatibleStrictContext_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      compatibleStrictContext_TypeDecl_values.put(_parameters, compatibleStrictContext_TypeDecl_value);
      compatibleStrictContext_TypeDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return compatibleStrictContext_TypeDecl_value;
  }
  /** @apilevel internal */
  private boolean compatibleStrictContext_compute(TypeDecl type) {
      if (!type.isFunctionalInterface()) {
        return false;
      }
      InterfaceDecl iDecl = (InterfaceDecl) type;
      return congruentTo(iDecl.functionDescriptor());
    }
  /** @apilevel internal */
  private void compatibleLooseContext_TypeDecl_reset() {
    compatibleLooseContext_TypeDecl_computed = null;
    compatibleLooseContext_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map compatibleLooseContext_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map compatibleLooseContext_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:106
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:102")
  public boolean compatibleLooseContext(TypeDecl type) {
    Object _parameters = type;
    if (compatibleLooseContext_TypeDecl_computed == null) compatibleLooseContext_TypeDecl_computed = new java.util.HashMap(4);
    if (compatibleLooseContext_TypeDecl_values == null) compatibleLooseContext_TypeDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (compatibleLooseContext_TypeDecl_values.containsKey(_parameters)
        && compatibleLooseContext_TypeDecl_computed.containsKey(_parameters)
        && (compatibleLooseContext_TypeDecl_computed.get(_parameters) == ASTState.NON_CYCLE || compatibleLooseContext_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) compatibleLooseContext_TypeDecl_values.get(_parameters);
    }
    boolean compatibleLooseContext_TypeDecl_value = compatibleStrictContext(type);
    if (state().inCircle()) {
      compatibleLooseContext_TypeDecl_values.put(_parameters, compatibleLooseContext_TypeDecl_value);
      compatibleLooseContext_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      compatibleLooseContext_TypeDecl_values.put(_parameters, compatibleLooseContext_TypeDecl_value);
      compatibleLooseContext_TypeDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return compatibleLooseContext_TypeDecl_value;
  }
  /** @apilevel internal */
  private void pertinentToApplicability_Expr_BodyDecl_int_reset() {
    pertinentToApplicability_Expr_BodyDecl_int_computed = null;
    pertinentToApplicability_Expr_BodyDecl_int_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map pertinentToApplicability_Expr_BodyDecl_int_values;
  /** @apilevel internal */
  protected java.util.Map pertinentToApplicability_Expr_BodyDecl_int_computed;
  /**
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:132
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:130")
  public boolean pertinentToApplicability(Expr access, BodyDecl decl, int argIndex) {
    java.util.List _parameters = new java.util.ArrayList(3);
    _parameters.add(access);
    _parameters.add(decl);
    _parameters.add(argIndex);
    if (pertinentToApplicability_Expr_BodyDecl_int_computed == null) pertinentToApplicability_Expr_BodyDecl_int_computed = new java.util.HashMap(4);
    if (pertinentToApplicability_Expr_BodyDecl_int_values == null) pertinentToApplicability_Expr_BodyDecl_int_values = new java.util.HashMap(4);
    ASTState state = state();
    if (pertinentToApplicability_Expr_BodyDecl_int_values.containsKey(_parameters)
        && pertinentToApplicability_Expr_BodyDecl_int_computed.containsKey(_parameters)
        && (pertinentToApplicability_Expr_BodyDecl_int_computed.get(_parameters) == ASTState.NON_CYCLE || pertinentToApplicability_Expr_BodyDecl_int_computed.get(_parameters) == state().cycle())) {
      return (Boolean) pertinentToApplicability_Expr_BodyDecl_int_values.get(_parameters);
    }
    boolean pertinentToApplicability_Expr_BodyDecl_int_value = pertinentToApplicability_compute(access, decl, argIndex);
    if (state().inCircle()) {
      pertinentToApplicability_Expr_BodyDecl_int_values.put(_parameters, pertinentToApplicability_Expr_BodyDecl_int_value);
      pertinentToApplicability_Expr_BodyDecl_int_computed.put(_parameters, state().cycle());
    
    } else {
      pertinentToApplicability_Expr_BodyDecl_int_values.put(_parameters, pertinentToApplicability_Expr_BodyDecl_int_value);
      pertinentToApplicability_Expr_BodyDecl_int_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return pertinentToApplicability_Expr_BodyDecl_int_value;
  }
  /** @apilevel internal */
  private boolean pertinentToApplicability_compute(Expr access, BodyDecl decl, int argIndex) {
      if (isImplicit()) {
        return false;
      }
      if (decl instanceof MethodDecl
          && decl.isGeneric()
          && !(access instanceof ParMethodAccess)
          && ((MethodDecl) decl).genericDecl().getParameter(argIndex).type().isTypeVariable()) {
        GenericMethodDecl genericDecl = ((MethodDecl) decl).genericDecl();
        TypeVariable typeVar = (TypeVariable) genericDecl.getParameter(argIndex).type();
        for (int i = 0; i < genericDecl.getNumTypeParameter(); i++) {
          if (typeVar == genericDecl.getTypeParameter(i)) {
            return false;
          }
        }
      } else if (decl instanceof ConstructorDecl
          && decl.isGeneric()
          && !(access instanceof ParConstructorAccess)
          && !(access instanceof ParSuperConstructorAccess)
          && !(access instanceof ParClassInstanceExpr)
          && ((ConstructorDecl) decl).genericDecl().getParameter(argIndex).type().isTypeVariable()) {
        GenericConstructorDecl genericDecl = ((ConstructorDecl) decl).genericDecl();
        TypeVariable typeVar = (TypeVariable) genericDecl.getParameter(argIndex).type();
        for (int i = 0; i < genericDecl.getNumTypeParameter(); i++) {
          if (typeVar == genericDecl.getTypeParameter(i)) {
            return false;
          }
        }
      }
      if (getLambdaBody() instanceof ExprLambdaBody) {
        ExprLambdaBody exprBody = (ExprLambdaBody) getLambdaBody();
        if (!exprBody.getExpr().pertinentToApplicability(access, decl, argIndex)) {
          return false;
        }
      } else {
        BlockLambdaBody blockBody = (BlockLambdaBody) getLambdaBody();
        ArrayList<ReturnStmt> returnList = blockBody.lambdaReturns();
        for (ReturnStmt returnStmt : returnList) {
          if (returnStmt.hasResult()
              && !returnStmt.getResult().pertinentToApplicability(access, decl, argIndex)) {
            return false;
          }
        }
      }
      return true;
    }
  /** @apilevel internal */
  private void moreSpecificThan_TypeDecl_TypeDecl_reset() {
    moreSpecificThan_TypeDecl_TypeDecl_computed = null;
    moreSpecificThan_TypeDecl_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map moreSpecificThan_TypeDecl_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map moreSpecificThan_TypeDecl_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:261
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:256")
  public boolean moreSpecificThan(TypeDecl type1, TypeDecl type2) {
    java.util.List _parameters = new java.util.ArrayList(2);
    _parameters.add(type1);
    _parameters.add(type2);
    if (moreSpecificThan_TypeDecl_TypeDecl_computed == null) moreSpecificThan_TypeDecl_TypeDecl_computed = new java.util.HashMap(4);
    if (moreSpecificThan_TypeDecl_TypeDecl_values == null) moreSpecificThan_TypeDecl_TypeDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (moreSpecificThan_TypeDecl_TypeDecl_values.containsKey(_parameters)
        && moreSpecificThan_TypeDecl_TypeDecl_computed.containsKey(_parameters)
        && (moreSpecificThan_TypeDecl_TypeDecl_computed.get(_parameters) == ASTState.NON_CYCLE || moreSpecificThan_TypeDecl_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) moreSpecificThan_TypeDecl_TypeDecl_values.get(_parameters);
    }
    boolean moreSpecificThan_TypeDecl_TypeDecl_value = moreSpecificThan_compute(type1, type2);
    if (state().inCircle()) {
      moreSpecificThan_TypeDecl_TypeDecl_values.put(_parameters, moreSpecificThan_TypeDecl_TypeDecl_value);
      moreSpecificThan_TypeDecl_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      moreSpecificThan_TypeDecl_TypeDecl_values.put(_parameters, moreSpecificThan_TypeDecl_TypeDecl_value);
      moreSpecificThan_TypeDecl_TypeDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return moreSpecificThan_TypeDecl_TypeDecl_value;
  }
  /** @apilevel internal */
  private boolean moreSpecificThan_compute(TypeDecl type1, TypeDecl type2) {
      if (super.moreSpecificThan(type1, type2)) {
        return true;
      }
      if (!type1.isFunctionalInterface() || !type2.isFunctionalInterface()) {
        return false;
      }
      if (type2.subtype(type1)) {
        // type1 cannot be more specific than type2 if it is a subtype of type2
        return false;
      }
      InterfaceDecl iDecl1 = (InterfaceDecl) type1;
      InterfaceDecl iDecl2 = (InterfaceDecl) type2;
  
      if (isImplicit()) {
        return false;
      }
  
      FunctionDescriptor fd1 = iDecl1.functionDescriptor();
      FunctionDescriptor fd2 = iDecl2.functionDescriptor();
  
      if (fd1.method.hasValue() && fd2.method.hasValue()) {
        // Can only compare method types if both function descriptors have target methods.
        TypeDecl methodType1 = fd1.method.get().type();
        TypeDecl methodType2 = fd2.method.get().type();
  
        // First bullet
        if (methodType2.isVoid()) {
          return true;
        }
  
        // Second bullet
        if (methodType1.subtype(methodType2)) {
          return true;
        }
  
        // Third bullet
        if (methodType1.isFunctionalInterface() && methodType2.isFunctionalInterface()) {
          if (getLambdaBody().isBlockBody()) {
            BlockLambdaBody blockBody = (BlockLambdaBody) getLambdaBody();
            boolean allMoreSpecific = true;
            ArrayList<ReturnStmt> returnList = blockBody.lambdaReturns();
            for (ReturnStmt returnStmt : returnList) {
              if (returnStmt.hasResult() && !returnStmt.getResult().moreSpecificThan(methodType1,
                  methodType2)) {
                allMoreSpecific = false;
                break;
              }
            }
            return allMoreSpecific;
          } else {
            ExprLambdaBody exprBody = (ExprLambdaBody) getLambdaBody();
            return exprBody.getExpr().moreSpecificThan(methodType1, methodType2);
          }
        }
  
        // Fourth bullet
        if (methodType1.isPrimitiveType() && methodType2.isReferenceType()) {
          if (getLambdaBody().isBlockBody()) {
            BlockLambdaBody blockBody = (BlockLambdaBody) getLambdaBody();
            boolean allPrimitive = true;
            ArrayList<ReturnStmt> returnList = blockBody.lambdaReturns();
            for (ReturnStmt returnStmt : returnList) {
              if (returnStmt.hasResult() && returnStmt.getResult().isPolyExpression()) {
                allPrimitive = false;
                break;
              } else if (returnStmt.hasResult() && !returnStmt.getResult().type().isPrimitiveType()) {
                allPrimitive = false;
                break;
              }
            }
            return allPrimitive;
          } else {
            ExprLambdaBody exprBody = (ExprLambdaBody) getLambdaBody();
            if (exprBody.getExpr().isPolyExpression()) {
              return false;
            }
            return exprBody.getExpr().type().isPrimitiveType();
          }
        }
  
        // Fifth bullet
        if (methodType1.isReferenceType() && methodType2.isPrimitiveType()) {
          if (getLambdaBody().isBlockBody()) {
            BlockLambdaBody blockBody = (BlockLambdaBody) getLambdaBody();
            boolean allReference = true;
            ArrayList<ReturnStmt> returnList = blockBody.lambdaReturns();
            for (ReturnStmt returnStmt : returnList) {
              if (returnStmt.hasResult() && !returnStmt.getResult().isPolyExpression()
                  && !returnStmt.getResult().type().isReferenceType()) {
                allReference = false;
                break;
              }
            }
            return allReference;
          } else {
            ExprLambdaBody exprBody = (ExprLambdaBody) getLambdaBody();
            if (exprBody.getExpr().isPolyExpression()) {
              return true;
            }
            return exprBody.getExpr().type().isReferenceType();
          }
        }
      }
      return false;
    }
  /** @apilevel internal */
  private void potentiallyCompatible_TypeDecl_BodyDecl_reset() {
    potentiallyCompatible_TypeDecl_BodyDecl_computed = null;
    potentiallyCompatible_TypeDecl_BodyDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map potentiallyCompatible_TypeDecl_BodyDecl_values;
  /** @apilevel internal */
  protected java.util.Map potentiallyCompatible_TypeDecl_BodyDecl_computed;
  /**
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:513
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:511")
  public boolean potentiallyCompatible(TypeDecl type, BodyDecl candidateDecl) {
    java.util.List _parameters = new java.util.ArrayList(2);
    _parameters.add(type);
    _parameters.add(candidateDecl);
    if (potentiallyCompatible_TypeDecl_BodyDecl_computed == null) potentiallyCompatible_TypeDecl_BodyDecl_computed = new java.util.HashMap(4);
    if (potentiallyCompatible_TypeDecl_BodyDecl_values == null) potentiallyCompatible_TypeDecl_BodyDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (potentiallyCompatible_TypeDecl_BodyDecl_values.containsKey(_parameters)
        && potentiallyCompatible_TypeDecl_BodyDecl_computed.containsKey(_parameters)
        && (potentiallyCompatible_TypeDecl_BodyDecl_computed.get(_parameters) == ASTState.NON_CYCLE || potentiallyCompatible_TypeDecl_BodyDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) potentiallyCompatible_TypeDecl_BodyDecl_values.get(_parameters);
    }
    boolean potentiallyCompatible_TypeDecl_BodyDecl_value = potentiallyCompatible_compute(type, candidateDecl);
    if (state().inCircle()) {
      potentiallyCompatible_TypeDecl_BodyDecl_values.put(_parameters, potentiallyCompatible_TypeDecl_BodyDecl_value);
      potentiallyCompatible_TypeDecl_BodyDecl_computed.put(_parameters, state().cycle());
    
    } else {
      potentiallyCompatible_TypeDecl_BodyDecl_values.put(_parameters, potentiallyCompatible_TypeDecl_BodyDecl_value);
      potentiallyCompatible_TypeDecl_BodyDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return potentiallyCompatible_TypeDecl_BodyDecl_value;
  }
  /** @apilevel internal */
  private boolean potentiallyCompatible_compute(TypeDecl type, BodyDecl candidateDecl) {
      if (type.isTypeVariable()) {
        if (candidateDecl.isGeneric()) {
          boolean foundTypeVariable = false;
          List<TypeVariable> typeParams = candidateDecl.typeParameters();
          for (int i = 0; i < typeParams.getNumChild(); i++) {
            if (type == typeParams.getChild(i)) {
              foundTypeVariable = true;
              break;
            }
          }
          return foundTypeVariable;
        } else {
          return false;
        }
      }
  
      if (!type.isFunctionalInterface()) {
        return false;
      }
      InterfaceDecl iDecl = (InterfaceDecl) type;
      FunctionDescriptor fd = iDecl.functionDescriptor();
      if (fd.method.hasValue()) {
        MethodDecl targetMethod = fd.method.get();
  
        if (arity() != targetMethod.arity()) {
          return false;
        }
        if (targetMethod.type().isVoid()) {
          if (getLambdaBody().isExprBody()) {
            ExprLambdaBody exprBody = (ExprLambdaBody) getLambdaBody();
            if (!exprBody.getExpr().stmtCompatible()) {
              return false;
            }
          } else {
            BlockLambdaBody blockBody = (BlockLambdaBody) getLambdaBody();
            if (!blockBody.voidCompatible()) {
              return false;
            }
          }
        } else {
          if (getLambdaBody().isBlockBody()) {
            BlockLambdaBody blockBody = (BlockLambdaBody) getLambdaBody();
            if (!blockBody.valueCompatible()) {
              return false;
            }
          }
        }
        return true;
      } else {
        // No target method.
        return false;
      }
    }
/** @apilevel internal */
protected ASTState.Cycle type_cycle = null;

  /** @apilevel internal */
  private void type_reset() {
    type_computed = false;
    type_initialized = false;
    type_value = null;
    type_cycle = null;
  }
  /** @apilevel internal */
  protected boolean type_computed = false;

  /** @apilevel internal */
  protected TypeDecl type_value;
  /** @apilevel internal */
  protected boolean type_initialized = false;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:61")
  public TypeDecl type() {
    if (type_computed) {
      return type_value;
    }
    ASTState state = state();
    if (!type_initialized) {
      type_initialized = true;
      type_value = unknownType();
    }
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      do {
        type_cycle = state.nextCycle();
        TypeDecl new_type_value = type_compute();
        if (!AttributeValue.equals(type_value, new_type_value)) {
          state.setChangeInCycle();
        }
        type_value = new_type_value;
      } while (state.testAndClearChangeInCycle());
      type_computed = true;
      state.startLastCycle();
      TypeDecl $tmp = type_compute();

      state.leaveCircle();
    } else if (type_cycle != state.cycle()) {
      type_cycle = state.cycle();
      if (state.lastCycle()) {
        type_computed = true;
        TypeDecl new_type_value = type_compute();
        return new_type_value;
      }
      TypeDecl new_type_value = type_compute();
      if (!AttributeValue.equals(type_value, new_type_value)) {
        state.setChangeInCycle();
      }
      type_value = new_type_value;
    } else {
    }
    return type_value;
  }
  /** @apilevel internal */
  private TypeDecl type_compute() {
      // 15.27.3
      if (!assignmentContext() && !castContext() && !invocationContext()) {
        return unknownType();
      }
      if (targetInterface() == null) {
        return unknownType();
      }
  
      InterfaceDecl iDecl = targetInterface();
      if (!iDecl.isFunctional()) {
        return unknownType();
      }
      if (congruentTo(iDecl.functionDescriptor())) {
        return iDecl;
      } else {
        return unknownType();
      }
    }
  /**
   * @attribute syn
   * @aspect TypeCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:122
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:122")
  public Collection<Problem> typeProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        if (!assignmentContext() && !castContext() && !invocationContext()) {
          // 15.27
          problems.add(error("Lambda expressions must target a functional interface"));
          return problems;
        }
    
        // This means there was an error in the overload resolution, will be reported elsewhere
        if (invocationContext() && targetType() == unknownType()) {
          return Collections.emptyList();
        }
    
        if (!targetType().isFunctionalInterface()) {
          // 15.27
          problems.add(error("Lambda expressions must target a functional interface"));
          return problems;
        }
    
        InterfaceDecl iDecl = targetInterface();
    
        if (!iDecl.isFunctional()) {
          // 15.27
          problems.add(errorf(
              "Interface %s is not functional and can therefore not be targeted by a lambda expression",
              iDecl.typeName()));
          return problems;
        }
    
        FunctionDescriptor fd = iDecl.functionDescriptor();
        if (!fd.method.hasValue()) {
          problems.add(errorf(
                "Found no matching method in the interface %s for this lambda expression.",
                iDecl.typeName()));
        } else {
          MethodDecl targetMethod = fd.method.get();
    
          if (fd.isGeneric()) {
            // 15.27
            problems.add(errorf("Illegal lambda expression: Method %s in interface %s is generic",
                targetMethod.name(), iDecl.typeName()));
            return problems;
          }
    
          if (!getLambdaParameters().congruentTo(fd)) {
            problems.add(errorf("Lambda expression parameters incompatible with"
                + " parameters in method %s in interface %s",
                targetMethod.name(), iDecl.typeName()));
          }
    
          if (getLambdaBody() instanceof ExprLambdaBody) {
            ExprLambdaBody exprBody = (ExprLambdaBody) getLambdaBody();
            if (targetMethod.type().isVoid()) {
              if (!exprBody.getExpr().stmtCompatible()) {
                problems.add(errorf("Lambda expression body must be a statement expression,"
                    + " because the method %s in interface %s has return type void",
                    targetMethod.name(), iDecl.typeName()));
              }
            } else {
              if (!exprBody.getExpr().type().assignConversionTo(targetMethod.type(), exprBody.getExpr())) {
                problems.add(errorf("Lambda expression body is not compatible with"
                    + " the return type %s in method %s in interface %s",
                    targetMethod.type().typeName(), targetMethod.name(), iDecl.typeName()));
              }
            }
          } else {
            BlockLambdaBody blockBody = (BlockLambdaBody) getLambdaBody();
            if (targetMethod.type().isVoid()) {
              if (!blockBody.voidCompatible()) {
                problems.add(errorf("Lambda expression body is not allowed to return a value,"
                    + " because the method %s in interface %s has return type void",
                    targetMethod.name(), iDecl.typeName()));
              }
            } else if (!blockBody.valueCompatible()) {
              problems.add(errorf("Lambda expression body must not complete normally or contain empty return"
                  + " statments, because the method %s in interface"
                  + " %s has a return type which is non-void",
                  targetMethod.name(), iDecl.typeName()));
            }
          }
        }
        return problems;
      }
  }
  /** @apilevel internal */
  private void targetInterface_reset() {
    targetInterface_computed = null;
    targetInterface_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle targetInterface_computed = null;

  /** @apilevel internal */
  protected InterfaceDecl targetInterface_value;

  /**
   * @attribute syn
   * @aspect TargetType
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:165
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TargetType", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:165")
  public InterfaceDecl targetInterface() {
    ASTState state = state();
    if (targetInterface_computed == ASTState.NON_CYCLE || targetInterface_computed == state().cycle()) {
      return targetInterface_value;
    }
    targetInterface_value = targetInterface_compute();
    if (state().inCircle()) {
      targetInterface_computed = state().cycle();
    
    } else {
      targetInterface_computed = ASTState.NON_CYCLE;
    
    }
    return targetInterface_value;
  }
  /** @apilevel internal */
  private InterfaceDecl targetInterface_compute() {
      if (targetType().isNull()) {
        return null;
      } else if (!(targetType() instanceof InterfaceDecl)) {
        return null;
      } else {
        return (InterfaceDecl) targetType();
      }
    }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:44
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:149")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = getLambdaBody().modifiedInScope(var);
    return modifiedInScope_Variable_value;
  }
  /**
   * @attribute inh
   * @aspect EnclosingLambda
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EnclosingLambda.jrag:38
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="EnclosingLambda", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EnclosingLambda.jrag:38")
  public LambdaExpr enclosingLambda() {
    ASTState state = state();
    if (enclosingLambda_computed == ASTState.NON_CYCLE || enclosingLambda_computed == state().cycle()) {
      return enclosingLambda_value;
    }
    enclosingLambda_value = getParent().Define_enclosingLambda(this, null);
    if (state().inCircle()) {
      enclosingLambda_computed = state().cycle();
    
    } else {
      enclosingLambda_computed = ASTState.NON_CYCLE;
    
    }
    return enclosingLambda_value;
  }
  /** @apilevel internal */
  private void enclosingLambda_reset() {
    enclosingLambda_computed = null;
    enclosingLambda_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle enclosingLambda_computed = null;

  /** @apilevel internal */
  protected LambdaExpr enclosingLambda_value;

  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:77
   * @apilevel internal
   */
  public TypeDecl Define_enclosingLambdaType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return anonymousDecl();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:77
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingLambdaType
   */
  protected boolean canDefine_enclosingLambdaType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:114
   * @apilevel internal
   */
  public boolean Define_handlesException(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    if (getLambdaBodyNoTransform() != null && _callerNode == getLambdaBody()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:158
      {
          InterfaceDecl iDecl = targetInterface();
          if (iDecl == null || !iDecl.isFunctional()) {
            return false;
          }
          for (TypeDecl exception : iDecl.functionDescriptor().throwsList) {
            if (exceptionType.strictSubtype(exception)) {
              return true;
            }
          }
          return false;
        }
    }
    else {
      return getParent().Define_handlesException(this, _callerNode, exceptionType);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:114
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute handlesException
   */
  protected boolean canDefine_handlesException(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:273
   * @apilevel internal
   */
  public FinallyHost Define_enclosingFinally(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:273
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingFinally
   */
  protected boolean canDefine_enclosingFinally(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:230
   * @apilevel internal
   */
  public Stmt Define_branchTarget(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    int childIndex = this.getIndexOfChild(_callerNode);
    {
        throw new Error("Found no branch targets for " + branch.getClass().getName());
      }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:230
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute branchTarget
   */
  protected boolean canDefine_branchTarget(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:703
   * @apilevel internal
   */
  public SimpleSet<TypeDecl> Define_otherLocalClassDecls(ASTNode _callerNode, ASTNode _childNode, String name) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return emptySet();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:703
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute otherLocalClassDecls
   */
  protected boolean canDefine_otherLocalClassDecls(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:33
   * @apilevel internal
   */
  public TypeDecl Define_superType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return targetInterface();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:33
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute superType
   */
  protected boolean canDefine_superType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:32
   * @apilevel internal
   */
  public TypeDecl Define_unknownType(ASTNode _callerNode, ASTNode _childNode) {
    if (getLambdaBodyNoTransform() != null && _callerNode == getLambdaBody()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:34
      return unknownType();
    }
    else if (getLambdaParametersNoTransform() != null && _callerNode == getLambdaParameters()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:33
      return unknownType();
    }
    else {
      return getParent().Define_unknownType(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:32
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unknownType
   */
  protected boolean canDefine_unknownType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:30
   * @apilevel internal
   */
  public SimpleSet<Variable> Define_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (getLambdaBodyNoTransform() != null && _callerNode == getLambdaBody()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:56
      {
          if (getLambdaParameters() instanceof DeclaredLambdaParameters) {
            SimpleSet<Variable> decls = ((DeclaredLambdaParameters) getLambdaParameters())
                .parameterDeclaration(name);
            if (!decls.isEmpty()) {
              return decls;
            }
          } else if (getLambdaParameters() instanceof InferredLambdaParameters) {
            SimpleSet<Variable> decls = ((InferredLambdaParameters) getLambdaParameters())
                .parameterDeclaration(name);
            if (!decls.isEmpty()) {
              return decls;
            }
          }
          return lookupVariable(name);
        }
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:31
   * @apilevel internal
   */
  public VariableScope Define_outerScope(ASTNode _callerNode, ASTNode _childNode) {
    if (getLambdaBodyNoTransform() != null && _callerNode == getLambdaBody()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:36
      return this;
    }
    else if (getLambdaParametersNoTransform() != null && _callerNode == getLambdaParameters()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:35
      return this;
    }
    else {
      return getParent().Define_outerScope(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:31
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute outerScope
   */
  protected boolean canDefine_outerScope(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:30
   * @apilevel internal
   */
  public boolean Define_inhModifiedInScope(ASTNode _callerNode, ASTNode _childNode, Variable var) {
    if (getLambdaParametersNoTransform() != null && _callerNode == getLambdaParameters()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:34
      return modifiedInScope(var);
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
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EnclosingLambda.jrag:29
   * @apilevel internal
   */
  public LambdaExpr Define_enclosingLambda(ASTNode _callerNode, ASTNode _childNode) {
    if (getLambdaParametersNoTransform() != null && _callerNode == getLambdaParameters()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EnclosingLambda.jrag:42
      return this;
    }
    else if (getLambdaBodyNoTransform() != null && _callerNode == getLambdaBody()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EnclosingLambda.jrag:41
      return this;
    }
    else {
      return getParent().Define_enclosingLambda(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EnclosingLambda.jrag:29
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingLambda
   */
  protected boolean canDefine_enclosingLambda(ASTNode _callerNode, ASTNode _childNode) {
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:119
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
    for (Problem value : typeProblems()) {
      collection.add(value);
    }
  }

}
