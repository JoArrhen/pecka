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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/grammar/MethodReference.ast:3
 * @astdecl ExprMethodReference : MethodReference ::= TypeArgument:Access* <ID:String> Expr;
 * @production ExprMethodReference : {@link MethodReference} ::= <span class="component">{@link Expr}</span>;

 */
public class ExprMethodReference extends MethodReference implements Cloneable {
  /**
   * @aspect PrettyPrintUtil8
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PrettyPrintUtil.jadd:113
   */
  @Override public String toString() {
    return getExprNoTransform().toString() + super.toString();
  }
  /**
   * @aspect Java8PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PrettyPrint.jadd:70
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(getExpr());
    out.print("::");
    if (hasTypeArgument()) {
      out.print("<");
      out.join(getTypeArguments(), new PrettyPrinter.Joiner() {
        @Override
        public void printSeparator(PrettyPrinter out) {
          out.print(", ");
        }
      });
      out.print(">");
    }
    out.print(name());
  }
  /**
   * @declaredat ASTNode:1
   */
  public ExprMethodReference() {
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
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"TypeArgument", "ID", "Expr"},
    type = {"List<Access>", "String", "Expr"},
    kind = {"List", "Token", "Child"}
  )
  public ExprMethodReference(List<Access> p0, String p1, Expr p2) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
  }
  /**
   * @declaredat ASTNode:24
   */
  public ExprMethodReference(List<Access> p0, beaver.Symbol p1, Expr p2) {
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
    targetMethod_FunctionDescriptor_reset();
    syntheticAccess_FunctionDescriptor_reset();
    syntheticMethodAccess_FunctionDescriptor_reset();
    congruentTo_FunctionDescriptor_reset();
    potentiallyApplicableMethods_FunctionDescriptor_reset();
    exactCompileTimeDeclaration_reset();
    potentiallyCompatible_TypeDecl_BodyDecl_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:51
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:56
   */
  public ExprMethodReference clone() throws CloneNotSupportedException {
    ExprMethodReference node = (ExprMethodReference) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:61
   */
  public ExprMethodReference copy() {
    try {
      ExprMethodReference node = (ExprMethodReference) clone();
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
   * @declaredat ASTNode:80
   */
  @Deprecated
  public ExprMethodReference fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:90
   */
  public ExprMethodReference treeCopyNoTransform() {
    ExprMethodReference tree = (ExprMethodReference) copy();
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
   * @declaredat ASTNode:110
   */
  public ExprMethodReference treeCopy() {
    ExprMethodReference tree = (ExprMethodReference) copy();
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
   * Replaces the TypeArgument list.
   * @param list The new list node to be used as the TypeArgument list.
   * @apilevel high-level
   */
  public ExprMethodReference setTypeArgumentList(List<Access> list) {
    setChild(list, 0);
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
  public ExprMethodReference addTypeArgument(Access node) {
    List<Access> list = (parent == null) ? getTypeArgumentListNoTransform() : getTypeArgumentList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public ExprMethodReference addTypeArgumentNoTransform(Access node) {
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
  public ExprMethodReference setTypeArgument(Access node, int i) {
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
    List<Access> list = (List<Access>) getChild(0);
    return list;
  }
  /**
   * Retrieves the TypeArgument list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeArgument list.
   * @apilevel low-level
   */
  public List<Access> getTypeArgumentListNoTransform() {
    return (List<Access>) getChildNoTransform(0);
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
   * Replaces the lexeme ID.
   * @param value The new value for the lexeme ID.
   * @apilevel high-level
   */
  public ExprMethodReference setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public ExprMethodReference setID(beaver.Symbol symbol) {
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
   * Replaces the Expr child.
   * @param node The new node to replace the Expr child.
   * @apilevel high-level
   */
  public ExprMethodReference setExpr(Expr node) {
    setChild(node, 1);
    return this;
  }
  /**
   * Retrieves the Expr child.
   * @return The current node used as the Expr child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Expr")
  public Expr getExpr() {
    return (Expr) getChild(1);
  }
  /**
   * Retrieves the Expr child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Expr child.
   * @apilevel low-level
   */
  public Expr getExprNoTransform() {
    return (Expr) getChildNoTransform(1);
  }
  /** @apilevel internal */
  private void targetMethod_FunctionDescriptor_reset() {
    targetMethod_FunctionDescriptor_computed = null;
    targetMethod_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map targetMethod_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map targetMethod_FunctionDescriptor_computed;
  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:33
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:33")
  public MethodDecl targetMethod(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (targetMethod_FunctionDescriptor_computed == null) targetMethod_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (targetMethod_FunctionDescriptor_values == null) targetMethod_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (targetMethod_FunctionDescriptor_values.containsKey(_parameters)
        && targetMethod_FunctionDescriptor_computed.containsKey(_parameters)
        && (targetMethod_FunctionDescriptor_computed.get(_parameters) == ASTState.NON_CYCLE || targetMethod_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (MethodDecl) targetMethod_FunctionDescriptor_values.get(_parameters);
    }
    MethodDecl targetMethod_FunctionDescriptor_value = targetMethod_compute(fd);
    if (state().inCircle()) {
      targetMethod_FunctionDescriptor_values.put(_parameters, targetMethod_FunctionDescriptor_value);
      targetMethod_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      targetMethod_FunctionDescriptor_values.put(_parameters, targetMethod_FunctionDescriptor_value);
      targetMethod_FunctionDescriptor_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return targetMethod_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private MethodDecl targetMethod_compute(FunctionDescriptor fd) {
      return syntheticMethodAccess(fd).decl();
    }
  /** @apilevel internal */
  private void syntheticAccess_FunctionDescriptor_reset() {
    syntheticAccess_FunctionDescriptor_values = null;
    syntheticAccess_FunctionDescriptor_proxy = null;
  }
  /** @apilevel internal */
  protected ASTNode syntheticAccess_FunctionDescriptor_proxy;
  /** @apilevel internal */
  protected java.util.Map syntheticAccess_FunctionDescriptor_values;

  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:37
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:37")
  public Access syntheticAccess(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (syntheticAccess_FunctionDescriptor_values == null) syntheticAccess_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (syntheticAccess_FunctionDescriptor_values.containsKey(_parameters)) {
      return (Access) syntheticAccess_FunctionDescriptor_values.get(_parameters);
    }
    state().enterLazyAttribute();
    Access syntheticAccess_FunctionDescriptor_value = syntheticAccess_compute(fd);
    if (syntheticAccess_FunctionDescriptor_proxy == null) {
      syntheticAccess_FunctionDescriptor_proxy = new ASTNode();
      syntheticAccess_FunctionDescriptor_proxy.setParent(this);
    }
    if (syntheticAccess_FunctionDescriptor_value != null) {
      syntheticAccess_FunctionDescriptor_value.setParent(syntheticAccess_FunctionDescriptor_proxy);
      if (syntheticAccess_FunctionDescriptor_value.mayHaveRewrite()) {
        syntheticAccess_FunctionDescriptor_value = (Access) syntheticAccess_FunctionDescriptor_value.rewrittenNode();
        syntheticAccess_FunctionDescriptor_value.setParent(syntheticAccess_FunctionDescriptor_proxy);
      }
    }
    syntheticAccess_FunctionDescriptor_values.put(_parameters, syntheticAccess_FunctionDescriptor_value);
    state().leaveLazyAttribute();
    return syntheticAccess_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private Access syntheticAccess_compute(FunctionDescriptor fd) {
      List<Expr> arguments = new List<Expr>();
      if (fd.method.hasValue()) {
        MethodDecl targetMethod = fd.method.get();
        for (int i = 0; i < targetMethod.getNumParameter(); i++) {
          TypeDecl argumentType = targetMethod.getParameter(i).type();
          arguments.add(new SyntheticTypeAccess(argumentType));
        }
      }
  
      if (!hasTypeArgument()) {
        MethodReferenceAccess mAccess = new MethodReferenceAccess(name(), arguments, fd);
        return ((Expr) getExpr().treeCopy()).qualifiesAccess(mAccess);
      } else {
        ParMethodReferenceAccess pmAccess = new ParMethodReferenceAccess(name(), arguments,
            (List<Access>) getTypeArgumentList().treeCopy(), fd);
        return ((Expr) getExpr().treeCopy()).qualifiesAccess(pmAccess);
      }
    }
  /** @apilevel internal */
  private void syntheticMethodAccess_FunctionDescriptor_reset() {
    syntheticMethodAccess_FunctionDescriptor_computed = null;
    syntheticMethodAccess_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map syntheticMethodAccess_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map syntheticMethodAccess_FunctionDescriptor_computed;
  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:57
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:57")
  public MethodAccess syntheticMethodAccess(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (syntheticMethodAccess_FunctionDescriptor_computed == null) syntheticMethodAccess_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (syntheticMethodAccess_FunctionDescriptor_values == null) syntheticMethodAccess_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (syntheticMethodAccess_FunctionDescriptor_values.containsKey(_parameters)
        && syntheticMethodAccess_FunctionDescriptor_computed.containsKey(_parameters)
        && (syntheticMethodAccess_FunctionDescriptor_computed.get(_parameters) == ASTState.NON_CYCLE || syntheticMethodAccess_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (MethodAccess) syntheticMethodAccess_FunctionDescriptor_values.get(_parameters);
    }
    MethodAccess syntheticMethodAccess_FunctionDescriptor_value = syntheticMethodAccess_compute(fd);
    if (state().inCircle()) {
      syntheticMethodAccess_FunctionDescriptor_values.put(_parameters, syntheticMethodAccess_FunctionDescriptor_value);
      syntheticMethodAccess_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      syntheticMethodAccess_FunctionDescriptor_values.put(_parameters, syntheticMethodAccess_FunctionDescriptor_value);
      syntheticMethodAccess_FunctionDescriptor_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return syntheticMethodAccess_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private MethodAccess syntheticMethodAccess_compute(FunctionDescriptor fd) {
      Access synAccess = syntheticAccess(fd);
      return (MethodAccess) synAccess.lastAccess();
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
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:241
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:239")
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
        MethodDecl decl = targetMethod(fd);
        if (unknownMethod() == decl) {
          return false;
        }
        if (methodType.isVoid()) {
          return true;
        }
        if (decl.type().isVoid()) {
          return false;
        }
        return decl.type().assignConversionTo(methodType, null);
      } else {
        // No target method.
        return false;
      }
    }
  /** @apilevel internal */
  private void potentiallyApplicableMethods_FunctionDescriptor_reset() {
    potentiallyApplicableMethods_FunctionDescriptor_computed = null;
    potentiallyApplicableMethods_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map potentiallyApplicableMethods_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map potentiallyApplicableMethods_FunctionDescriptor_computed;
  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:294
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:292")
  public java.util.List<MethodDecl> potentiallyApplicableMethods(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (potentiallyApplicableMethods_FunctionDescriptor_computed == null) potentiallyApplicableMethods_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (potentiallyApplicableMethods_FunctionDescriptor_values == null) potentiallyApplicableMethods_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (potentiallyApplicableMethods_FunctionDescriptor_values.containsKey(_parameters)
        && potentiallyApplicableMethods_FunctionDescriptor_computed.containsKey(_parameters)
        && (potentiallyApplicableMethods_FunctionDescriptor_computed.get(_parameters) == ASTState.NON_CYCLE || potentiallyApplicableMethods_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (java.util.List<MethodDecl>) potentiallyApplicableMethods_FunctionDescriptor_values.get(_parameters);
    }
    java.util.List<MethodDecl> potentiallyApplicableMethods_FunctionDescriptor_value = potentiallyApplicableMethods_compute(fd);
    if (state().inCircle()) {
      potentiallyApplicableMethods_FunctionDescriptor_values.put(_parameters, potentiallyApplicableMethods_FunctionDescriptor_value);
      potentiallyApplicableMethods_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      potentiallyApplicableMethods_FunctionDescriptor_values.put(_parameters, potentiallyApplicableMethods_FunctionDescriptor_value);
      potentiallyApplicableMethods_FunctionDescriptor_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return potentiallyApplicableMethods_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private java.util.List<MethodDecl> potentiallyApplicableMethods_compute(FunctionDescriptor fd) {
      if (fd.method.hasValue()) {
        MethodDecl targetMethod = fd.method.get();
        Collection<MethodDecl> col = getExpr().type().memberMethods(name());
        ArrayList<MethodDecl> applicable = new ArrayList<MethodDecl>();
        for (MethodDecl decl : col) {
          if (!decl.accessibleFrom(hostType())) {
            continue;
          }
          if (!(decl.arity() == targetMethod.arity())) {
            continue;
          }
          if (hasTypeArgument()) {
            if (!decl.isGeneric()) {
              continue;
            }
            GenericMethodDecl genDecl = decl.genericDecl();
            if (!(getNumTypeArgument() == genDecl.getNumTypeParameter())) {
            }
              continue;
          }
          applicable.add(decl);
        }
        return applicable;
      } else {
        // No target method.
        return Collections.emptyList();
      }
    }
  /** @apilevel internal */
  private void exactCompileTimeDeclaration_reset() {
    exactCompileTimeDeclaration_computed = null;
    exactCompileTimeDeclaration_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle exactCompileTimeDeclaration_computed = null;

  /** @apilevel internal */
  protected MethodDecl exactCompileTimeDeclaration_value;

  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:364
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:360")
  public MethodDecl exactCompileTimeDeclaration() {
    ASTState state = state();
    if (exactCompileTimeDeclaration_computed == ASTState.NON_CYCLE || exactCompileTimeDeclaration_computed == state().cycle()) {
      return exactCompileTimeDeclaration_value;
    }
    exactCompileTimeDeclaration_value = exactCompileTimeDeclaration_compute();
    if (state().inCircle()) {
      exactCompileTimeDeclaration_computed = state().cycle();
    
    } else {
      exactCompileTimeDeclaration_computed = ASTState.NON_CYCLE;
    
    }
    return exactCompileTimeDeclaration_value;
  }
  /** @apilevel internal */
  private MethodDecl exactCompileTimeDeclaration_compute() {
      Collection<MethodDecl> col = getExpr().type().memberMethods(name());
      MethodDecl latestDecl = null;
      for (MethodDecl decl  : col) {
        if (decl.accessibleFrom(hostType())) {
          if (latestDecl != null) {
            return unknownMethod();
          }
          latestDecl = decl;
        }
      }
      if (latestDecl == null) {
        return unknownMethod();
      }
      if (latestDecl.isVariableArity()) {
        return unknownMethod();
      }
      if (latestDecl.isGeneric()) {
        GenericMethodDecl genericDecl = latestDecl.genericDecl();
        if (getNumTypeArgument() == genericDecl.getNumTypeParameter()) {
          return latestDecl;
        } else {
          return unknownMethod();
        }
      }
      return latestDecl;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:591
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
      if (super.potentiallyCompatible(type, candidateDecl) && type.isTypeVariable()) {
        return true;
      } else if (!super.potentiallyCompatible(type, candidateDecl)) {
        return false;
      }
  
      InterfaceDecl iDecl = (InterfaceDecl) type;
      FunctionDescriptor fd = iDecl.functionDescriptor();
      boolean foundMethod = false;
      if (fd.method.hasValue()) {
        MethodDecl targetMethod = fd.method.get();
        for (MethodDecl decl : potentiallyApplicableMethods(fd)) {
          if (!decl.isStatic() && targetMethod.arity() == decl.arity()) {
            foundMethod = true;
            break;
          }
        }
      }
      return foundMethod;
    }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:252
   * @apilevel internal
   */
  public boolean Define_assignmentContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:410
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
    if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:411
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
    if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:412
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
    if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:413
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
    if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:414
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
  /** @apilevel internal */
  protected void collect_contributors_CompilationUnit_problems(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:515
    if (!getExpr().isSuperAccess() && !getExpr().type().isReferenceType()) {
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
    if (!getExpr().isSuperAccess() && !getExpr().type().isReferenceType()) {
      collection.add(error("Expression in a method reference must have reference type"));
    }
  }

}
