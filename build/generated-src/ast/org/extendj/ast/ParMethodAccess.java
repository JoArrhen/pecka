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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/grammar/GenericMethods.ast:17
 * @astdecl ParMethodAccess : MethodAccess ::= <ID:String> Arg:Expr* TypeArgument:Access*;
 * @production ParMethodAccess : {@link MethodAccess} ::= <span class="component">TypeArgument:{@link Access}*</span>;

 */
public class ParMethodAccess extends MethodAccess implements Cloneable {
  /**
   * @aspect GenericMethodsPrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:252
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print("<");
    for (int i = 0; i < getNumTypeArgument(); i++) {
      if (i != 0) {
        out.print(", ");
      }
      out.print(getTypeArgument(i));
    }
    out.print(">");
    super.prettyPrint(out);
  }
  /**
   * @aspect PrettyPrintUtil5
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/PrettyPrintUtil.jrag:114
   */
  @Override public String toString() {
    StringBuilder params = new StringBuilder();
    for (Access arg : getTypeArgumentListNoTransform()) {
      if (params.length() > 0) {
        params.append(", ");
      }
      params.append(arg.toString());
    }
    return String.format("<%s>%s", params.toString(), super.toString());
  }
  /**
   * @declaredat ASTNode:1
   */
  public ParMethodAccess() {
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
    setChild(new List(), 0);
    setChild(new List(), 1);
  }
  /**
   * @declaredat ASTNode:15
   */
  @ASTNodeAnnotation.Constructor(
    name = {"ID", "Arg", "TypeArgument"},
    type = {"String", "List<Expr>", "List<Access>"},
    kind = {"Token", "List", "List"}
  )
  public ParMethodAccess(String p0, List<Expr> p1, List<Access> p2) {
    setID(p0);
    setChild(p1, 0);
    setChild(p2, 1);
  }
  /**
   * @declaredat ASTNode:25
   */
  public ParMethodAccess(beaver.Symbol p0, List<Expr> p1, List<Access> p2) {
    setID(p0);
    setChild(p1, 0);
    setChild(p2, 1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:31
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:37
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:41
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__reset();
    isPolyExpression_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:47
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:52
   */
  public ParMethodAccess clone() throws CloneNotSupportedException {
    ParMethodAccess node = (ParMethodAccess) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:57
   */
  public ParMethodAccess copy() {
    try {
      ParMethodAccess node = (ParMethodAccess) clone();
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
   * @declaredat ASTNode:76
   */
  @Deprecated
  public ParMethodAccess fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:86
   */
  public ParMethodAccess treeCopyNoTransform() {
    ParMethodAccess tree = (ParMethodAccess) copy();
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
   * @declaredat ASTNode:106
   */
  public ParMethodAccess treeCopy() {
    ParMethodAccess tree = (ParMethodAccess) copy();
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
  public ParMethodAccess setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public ParMethodAccess setID(beaver.Symbol symbol) {
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
   * Replaces the Arg list.
   * @param list The new list node to be used as the Arg list.
   * @apilevel high-level
   */
  public ParMethodAccess setArgList(List<Expr> list) {
    setChild(list, 0);
    return this;
  }
  /**
   * Retrieves the number of children in the Arg list.
   * @return Number of children in the Arg list.
   * @apilevel high-level
   */
  public int getNumArg() {
    return getArgList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Arg list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the Arg list.
   * @apilevel low-level
   */
  public int getNumArgNoTransform() {
    return getArgListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Arg list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Arg list.
   * @apilevel high-level
   */
  public Expr getArg(int i) {
    return (Expr) getArgList().getChild(i);
  }
  /**
   * Check whether the Arg list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasArg() {
    return getArgList().getNumChild() != 0;
  }
  /**
   * Append an element to the Arg list.
   * @param node The element to append to the Arg list.
   * @apilevel high-level
   */
  public ParMethodAccess addArg(Expr node) {
    List<Expr> list = (parent == null) ? getArgListNoTransform() : getArgList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public ParMethodAccess addArgNoTransform(Expr node) {
    List<Expr> list = getArgListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the Arg list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public ParMethodAccess setArg(Expr node, int i) {
    List<Expr> list = getArgList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the Arg list.
   * @return The node representing the Arg list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Arg")
  public List<Expr> getArgList() {
    List<Expr> list = (List<Expr>) getChild(0);
    return list;
  }
  /**
   * Retrieves the Arg list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Arg list.
   * @apilevel low-level
   */
  public List<Expr> getArgListNoTransform() {
    return (List<Expr>) getChildNoTransform(0);
  }
  /**
   * @return the element at index {@code i} in the Arg list without
   * triggering rewrites.
   */
  public Expr getArgNoTransform(int i) {
    return (Expr) getArgListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Arg list.
   * @return The node representing the Arg list.
   * @apilevel high-level
   */
  public List<Expr> getArgs() {
    return getArgList();
  }
  /**
   * Retrieves the Arg list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Arg list.
   * @apilevel low-level
   */
  public List<Expr> getArgsNoTransform() {
    return getArgListNoTransform();
  }
  /**
   * Replaces the TypeArgument list.
   * @param list The new list node to be used as the TypeArgument list.
   * @apilevel high-level
   */
  public ParMethodAccess setTypeArgumentList(List<Access> list) {
    setChild(list, 1);
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
  public ParMethodAccess addTypeArgument(Access node) {
    List<Access> list = (parent == null) ? getTypeArgumentListNoTransform() : getTypeArgumentList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public ParMethodAccess addTypeArgumentNoTransform(Access node) {
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
  public ParMethodAccess setTypeArgument(Access node, int i) {
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
    List<Access> list = (List<Access>) getChild(1);
    return list;
  }
  /**
   * Retrieves the TypeArgument list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeArgument list.
   * @apilevel low-level
   */
  public List<Access> getTypeArgumentListNoTransform() {
    return (List<Access>) getChildNoTransform(1);
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
   * @attribute syn
   * @aspect GenericMethods
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:35
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericMethods", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:35")
  public Collection<Problem> typeProblems() {
    {
        if (!decl().hostType().isUnknown()) {
          // JLS 15.12.2.1 specifically permits type arguments on non-generic method access.
          if (decl() instanceof ParMethodDecl) {
            ParMethodDecl m = (ParMethodDecl) decl();
            if (!(m instanceof RawMethodDecl) && m.numTypeParameter() != getNumTypeArgument()) {
              return Collections.singletonList(errorf("generic method %s requires %s type arguments",
                  m.signature(), m.numTypeParameter()));
            }
          }
        }
        return Collections.emptyList();
      }
  }
  /** @apilevel internal */
  private void inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__reset() {
    inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__computed = null;
    inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__values = null;
  }
  /** @apilevel internal */
  protected java.util.Map inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__values;
  /** @apilevel internal */
  protected java.util.Map inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__computed;
  /**
   * The type arguments for a parameterized method access are provided directly
   * without type inference.
   * 
   * @return the type arguments for this parameterized method access.
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:653
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:548")
  public ArrayList<TypeDecl> inferTypeArguments(TypeDecl resultType, List<ParameterDeclaration> params, List<Expr> args, List<TypeVariable> typeParams, List<Access> thrownTypes) {
    java.util.List _parameters = new java.util.ArrayList(5);
    _parameters.add(resultType);
    _parameters.add(params);
    _parameters.add(args);
    _parameters.add(typeParams);
    _parameters.add(thrownTypes);
    if (inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__computed == null) inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__computed = new java.util.HashMap(4);
    if (inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__values == null) inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__values = new java.util.HashMap(4);
    ASTState state = state();
    if (inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__values.containsKey(_parameters)
        && inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__computed.containsKey(_parameters)
        && (inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__computed.get(_parameters) == ASTState.NON_CYCLE || inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__computed.get(_parameters) == state().cycle())) {
      return (ArrayList<TypeDecl>) inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__values.get(_parameters);
    }
    ArrayList<TypeDecl> inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__value = inferTypeArguments_compute(resultType, params, args, typeParams, thrownTypes);
    if (state().inCircle()) {
      inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__values.put(_parameters, inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__value);
      inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__computed.put(_parameters, state().cycle());
    
    } else {
      inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__values.put(_parameters, inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__value);
      inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return inferTypeArguments_TypeDecl_List_ParameterDeclaration__List_Expr__List_TypeVariable__List_Access__value;
  }
  /** @apilevel internal */
  private ArrayList<TypeDecl> inferTypeArguments_compute(TypeDecl resultType, List<ParameterDeclaration> params, List<Expr> args, List<TypeVariable> typeParams, List<Access> thrownTypes) {
      ArrayList<TypeDecl> typeArguments = new ArrayList<TypeDecl>();
      for (Access typeArgument : getTypeArgumentList()) {
        typeArguments.add(typeArgument.type());
      }
      return typeArguments;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:104
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PolyExpressions", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:86")
  public boolean isPolyExpression() {
    ASTState state = state();
    if (isPolyExpression_computed == ASTState.NON_CYCLE || isPolyExpression_computed == state().cycle()) {
      return isPolyExpression_value;
    }
    isPolyExpression_value = false;
    if (state().inCircle()) {
      isPolyExpression_computed = state().cycle();
    
    } else {
      isPolyExpression_computed = ASTState.NON_CYCLE;
    
    }
    return isPolyExpression_value;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:101
   * @apilevel internal
   */
  public boolean Define_isRightChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getTypeArgumentListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:33
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else {
      return super.Define_isRightChildOfDot(_callerNode, _childNode);
    }
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getTypeArgumentListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:204
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
    if (_callerNode == getTypeArgumentListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:205
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return unqualifiedScope().lookupType(name);
    }
    else {
      return super.Define_lookupType(_callerNode, _childNode, name);
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
