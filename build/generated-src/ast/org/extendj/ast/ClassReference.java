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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/grammar/ConstructorReference.ast:3
 * @astdecl ClassReference : ConstructorReference ::= TypeAccess:Access TypeArgument:Access*;
 * @production ClassReference : {@link ConstructorReference} ::= <span class="component">TypeArgument:{@link Access}*</span>;

 */
public class ClassReference extends ConstructorReference implements Cloneable {
  /**
   * @aspect PrettyPrintUtil8
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PrettyPrintUtil.jadd:87
   */
  @Override public String toString() {
    StringBuilder params = new StringBuilder();
    for (Access arg : getTypeArgumentListNoTransform()) {
      if (params.length() > 0) {
        params.append(", ");
      }
      params.append(arg.toString());
    }
    return String.format("%s::<%s>new", getTypeAccessNoTransform().toString(), params.toString());
  }
  /**
   * @aspect Java8PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PrettyPrint.jadd:42
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(getTypeAccess());
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
    out.print("new");
  }
  /**
   * @declaredat ASTNode:1
   */
  public ClassReference() {
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
    name = {"TypeAccess", "TypeArgument"},
    type = {"Access", "List<Access>"},
    kind = {"Child", "List"}
  )
  public ClassReference(Access p0, List<Access> p1) {
    setChild(p0, 0);
    setChild(p1, 1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:24
   */
  protected int numChildren() {
    return 2;
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
    targetConstructor_FunctionDescriptor_reset();
    syntheticInstanceExpr_FunctionDescriptor_reset();
    congruentTo_FunctionDescriptor_reset();
    potentiallyApplicableConstructors_FunctionDescriptor_reset();
    exactCompileTimeDeclaration_reset();
    isExact_reset();
    potentiallyCompatible_TypeDecl_BodyDecl_reset();
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
  public ClassReference clone() throws CloneNotSupportedException {
    ClassReference node = (ClassReference) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:55
   */
  public ClassReference copy() {
    try {
      ClassReference node = (ClassReference) clone();
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
  public ClassReference fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:84
   */
  public ClassReference treeCopyNoTransform() {
    ClassReference tree = (ClassReference) copy();
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
  public ClassReference treeCopy() {
    ClassReference tree = (ClassReference) copy();
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
  public ClassReference setTypeAccess(Access node) {
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
   * Replaces the TypeArgument list.
   * @param list The new list node to be used as the TypeArgument list.
   * @apilevel high-level
   */
  public ClassReference setTypeArgumentList(List<Access> list) {
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
  public ClassReference addTypeArgument(Access node) {
    List<Access> list = (parent == null) ? getTypeArgumentListNoTransform() : getTypeArgumentList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public ClassReference addTypeArgumentNoTransform(Access node) {
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
  public ClassReference setTypeArgument(Access node, int i) {
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
  /** @apilevel internal */
  private void targetConstructor_FunctionDescriptor_reset() {
    targetConstructor_FunctionDescriptor_computed = null;
    targetConstructor_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map targetConstructor_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map targetConstructor_FunctionDescriptor_computed;
  /**
   * @attribute syn
   * @aspect ConstructorReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/ConstructorReference.jrag:32
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructorReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/ConstructorReference.jrag:32")
  public ConstructorDecl targetConstructor(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (targetConstructor_FunctionDescriptor_computed == null) targetConstructor_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (targetConstructor_FunctionDescriptor_values == null) targetConstructor_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (targetConstructor_FunctionDescriptor_values.containsKey(_parameters)
        && targetConstructor_FunctionDescriptor_computed.containsKey(_parameters)
        && (targetConstructor_FunctionDescriptor_computed.get(_parameters) == ASTState.NON_CYCLE || targetConstructor_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (ConstructorDecl) targetConstructor_FunctionDescriptor_values.get(_parameters);
    }
    ConstructorDecl targetConstructor_FunctionDescriptor_value = syntheticInstanceExpr(fd).decl();
    if (state().inCircle()) {
      targetConstructor_FunctionDescriptor_values.put(_parameters, targetConstructor_FunctionDescriptor_value);
      targetConstructor_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      targetConstructor_FunctionDescriptor_values.put(_parameters, targetConstructor_FunctionDescriptor_value);
      targetConstructor_FunctionDescriptor_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return targetConstructor_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private void syntheticInstanceExpr_FunctionDescriptor_reset() {
    syntheticInstanceExpr_FunctionDescriptor_values = null;
    syntheticInstanceExpr_FunctionDescriptor_proxy = null;
  }
  /** @apilevel internal */
  protected ASTNode syntheticInstanceExpr_FunctionDescriptor_proxy;
  /** @apilevel internal */
  protected java.util.Map syntheticInstanceExpr_FunctionDescriptor_values;

  /**
   * @attribute syn
   * @aspect ConstructorReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/ConstructorReference.jrag:35
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="ConstructorReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/ConstructorReference.jrag:35")
  public ClassInstanceExpr syntheticInstanceExpr(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (syntheticInstanceExpr_FunctionDescriptor_values == null) syntheticInstanceExpr_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (syntheticInstanceExpr_FunctionDescriptor_values.containsKey(_parameters)) {
      return (ClassInstanceExpr) syntheticInstanceExpr_FunctionDescriptor_values.get(_parameters);
    }
    state().enterLazyAttribute();
    ClassInstanceExpr syntheticInstanceExpr_FunctionDescriptor_value = syntheticInstanceExpr_compute(fd);
    if (syntheticInstanceExpr_FunctionDescriptor_proxy == null) {
      syntheticInstanceExpr_FunctionDescriptor_proxy = new ASTNode();
      syntheticInstanceExpr_FunctionDescriptor_proxy.setParent(this);
    }
    if (syntheticInstanceExpr_FunctionDescriptor_value != null) {
      syntheticInstanceExpr_FunctionDescriptor_value.setParent(syntheticInstanceExpr_FunctionDescriptor_proxy);
      if (syntheticInstanceExpr_FunctionDescriptor_value.mayHaveRewrite()) {
        syntheticInstanceExpr_FunctionDescriptor_value = (ClassInstanceExpr) syntheticInstanceExpr_FunctionDescriptor_value.rewrittenNode();
        syntheticInstanceExpr_FunctionDescriptor_value.setParent(syntheticInstanceExpr_FunctionDescriptor_proxy);
      }
    }
    syntheticInstanceExpr_FunctionDescriptor_values.put(_parameters, syntheticInstanceExpr_FunctionDescriptor_value);
    state().leaveLazyAttribute();
    return syntheticInstanceExpr_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private ClassInstanceExpr syntheticInstanceExpr_compute(FunctionDescriptor fd) {
      List<Expr> arguments = new List<Expr>();
      if (fd.method.hasValue()) {
        MethodDecl targetMethod = fd.method.get();
        for (int i = 0; i < targetMethod.getNumParameter(); i++) {
          TypeDecl argumentType = targetMethod.getParameter(i).type();
          arguments.add(new SyntheticTypeAccess(argumentType));
        }
      }
  
      ClassInstanceExpr instanceExpr = null;
      if (hasTypeArgument()) {
        instanceExpr = new ParConstructorReferenceAccess(
            (Access) getTypeAccess().treeCopyNoTransform(), arguments,
            new Opt(), (List<Access>) getTypeArgumentList().treeCopyNoTransform(), fd);
      } else {
        // Must check for raw reference type, and in that case infer using diamond (JLS 15.13.1)
        boolean checkDiamond = true;
        if (getTypeAccess().type().hostType() != null && !getTypeAccess().type().isStatic()
            && getTypeAccess().type().hostType().isRawType()) {
          checkDiamond = false;
        }
        if (getTypeAccess().type().isRawType() && checkDiamond) {
          DiamondAccess diamond = new DiamondAccess((Access) getTypeAccess().treeCopyNoTransform());
          instanceExpr = new ConstructorReferenceAccess(diamond, arguments, fd);
        } else {
          instanceExpr = new ConstructorReferenceAccess(
              (Access) getTypeAccess().treeCopyNoTransform(), arguments, fd);
        }
      }
      return instanceExpr;
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
   * @aspect ConstructorReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/ConstructorReference.jrag:74
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructorReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/ConstructorReference.jrag:72")
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
      ConstructorDecl decl = targetConstructor(fd);
      if (unknownConstructor() == decl) {
        return false;
      }
      if (fd.method.hasValue()) {
        MethodDecl targetMethod = fd.method.get();
        if (!targetMethod.type().isVoid()) {
          TypeDecl returnType = syntheticInstanceExpr(fd).type();
          if (!returnType.assignConversionTo(targetMethod.type(), null)) {
            return false;
          }
        }
        return true;
      } else {
        // No target method.
        return false;
      }
    }
  /** @apilevel internal */
  private void potentiallyApplicableConstructors_FunctionDescriptor_reset() {
    potentiallyApplicableConstructors_FunctionDescriptor_computed = null;
    potentiallyApplicableConstructors_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map potentiallyApplicableConstructors_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map potentiallyApplicableConstructors_FunctionDescriptor_computed;
  /**
   * Find potentially applicable constructors for a class reference matching a given
   * function descriptor.
   * @attribute syn
   * @aspect ConstructorReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/ConstructorReference.jrag:120
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructorReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/ConstructorReference.jrag:120")
  public java.util.List<ConstructorDecl> potentiallyApplicableConstructors(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (potentiallyApplicableConstructors_FunctionDescriptor_computed == null) potentiallyApplicableConstructors_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (potentiallyApplicableConstructors_FunctionDescriptor_values == null) potentiallyApplicableConstructors_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (potentiallyApplicableConstructors_FunctionDescriptor_values.containsKey(_parameters)
        && potentiallyApplicableConstructors_FunctionDescriptor_computed.containsKey(_parameters)
        && (potentiallyApplicableConstructors_FunctionDescriptor_computed.get(_parameters) == ASTState.NON_CYCLE || potentiallyApplicableConstructors_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (java.util.List<ConstructorDecl>) potentiallyApplicableConstructors_FunctionDescriptor_values.get(_parameters);
    }
    java.util.List<ConstructorDecl> potentiallyApplicableConstructors_FunctionDescriptor_value = potentiallyApplicableConstructors_compute(fd);
    if (state().inCircle()) {
      potentiallyApplicableConstructors_FunctionDescriptor_values.put(_parameters, potentiallyApplicableConstructors_FunctionDescriptor_value);
      potentiallyApplicableConstructors_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      potentiallyApplicableConstructors_FunctionDescriptor_values.put(_parameters, potentiallyApplicableConstructors_FunctionDescriptor_value);
      potentiallyApplicableConstructors_FunctionDescriptor_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return potentiallyApplicableConstructors_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private java.util.List<ConstructorDecl> potentiallyApplicableConstructors_compute(FunctionDescriptor fd) {
      if (fd.method.hasValue()) {
        MethodDecl targetMethod = fd.method.get();
        TypeDecl classType = syntheticInstanceExpr(fd).type();
        Collection<ConstructorDecl> col = classType.constructors();
        ArrayList<ConstructorDecl> applicable = new ArrayList<ConstructorDecl>();
        for (ConstructorDecl decl : col) {
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
            GenericConstructorDecl genDecl = decl.genericDecl();
            if (!(getNumTypeArgument() == genDecl.getNumTypeParameter())) {
              continue;
            }
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
  protected ConstructorDecl exactCompileTimeDeclaration_value;

  /**
   * @attribute syn
   * @aspect ConstructorReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/ConstructorReference.jrag:155
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructorReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/ConstructorReference.jrag:155")
  public ConstructorDecl exactCompileTimeDeclaration() {
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
  private ConstructorDecl exactCompileTimeDeclaration_compute() {
      if (getTypeAccess().type().isRawType()) {
        if (getTypeAccess().type().hostType() == null || getTypeAccess().type().isStatic()
            || getTypeAccess().type().hostType().isRawType()) {
          return unknownConstructor();
        }
      }
      TypeDecl classType = getTypeAccess().type();
      Collection<ConstructorDecl> col = classType.constructors();
      ArrayList<ConstructorDecl> applicable = new ArrayList<ConstructorDecl>();
      ConstructorDecl latestDecl = null;
      for (ConstructorDecl decl : col) {
        if (decl.accessibleFrom(hostType())) {
          if (latestDecl != null) {
            return unknownConstructor();
          }
          latestDecl = decl;
        }
      }
      if (latestDecl == null) {
        return unknownConstructor();
      }
      if (latestDecl.isVariableArity()) {
        return unknownConstructor();
      }
      if (latestDecl.isGeneric()) {
        GenericConstructorDecl genericDecl = latestDecl.genericDecl();
        if (getNumTypeArgument() == genericDecl.getNumTypeParameter()) {
          return latestDecl;
        } else {
          return unknownConstructor();
        }
      }
      return latestDecl;
    }
  /** @apilevel internal */
  private void isExact_reset() {
    isExact_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isExact_computed = null;

  /** @apilevel internal */
  protected boolean isExact_value;

  /**
   * @attribute syn
   * @aspect ConstructorReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/ConstructorReference.jrag:191
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructorReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/ConstructorReference.jrag:153")
  public boolean isExact() {
    ASTState state = state();
    if (isExact_computed == ASTState.NON_CYCLE || isExact_computed == state().cycle()) {
      return isExact_value;
    }
    isExact_value = exactCompileTimeDeclaration() != unknownConstructor();
    if (state().inCircle()) {
      isExact_computed = state().cycle();
    
    } else {
      isExact_computed = ASTState.NON_CYCLE;
    
    }
    return isExact_value;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:661
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
        for (ConstructorDecl decl : potentiallyApplicableConstructors(fd)) {
          if (targetMethod.arity() == decl.arity()) {
            foundMethod = true;
            break;
          }
        }
      }
      return foundMethod;
    }
  /**
   * @attribute syn
   * @aspect Java8NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:522
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Java8NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:522")
  public Collection<Problem> nameProblems() {
    {
        for (int i = 0; i < getNumTypeArgument(); i++) {
          if (getTypeArgument(i) instanceof AbstractWildcard) {
            return Collections.singletonList(
                error("Wildcard not allowed in constructor reference type argument lists"));
          }
        }
        return Collections.emptyList();
      }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getTypeArgumentListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/ConstructorReference.jrag:69
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:520
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
