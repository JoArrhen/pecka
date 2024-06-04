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
/** A superconstructor invocation. 
 * @ast node
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:96
 * @astdecl SuperConstructorAccess : ConstructorAccess ::= <ID:String> Arg:Expr*;
 * @production SuperConstructorAccess : {@link ConstructorAccess};

 */
public class SuperConstructorAccess extends ConstructorAccess implements Cloneable, Invocable {
  /**
   * @declaredat ASTNode:1
   */
  public SuperConstructorAccess() {
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
    name = {"ID", "Arg"},
    type = {"String", "List<Expr>"},
    kind = {"Token", "List"}
  )
  public SuperConstructorAccess(String p0, List<Expr> p1) {
    setID(p0);
    setChild(p1, 0);
  }
  /**
   * @declaredat ASTNode:23
   */
  public SuperConstructorAccess(beaver.Symbol p0, List<Expr> p1) {
    setID(p0);
    setChild(p1, 0);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:28
   */
  protected int numChildren() {
    return 1;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:34
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:38
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    decls_reset();
    unassignedAfter_Variable_reset();
    allDecls_reset();
    enclosingTarget_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:46
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:51
   */
  public SuperConstructorAccess clone() throws CloneNotSupportedException {
    SuperConstructorAccess node = (SuperConstructorAccess) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:56
   */
  public SuperConstructorAccess copy() {
    try {
      SuperConstructorAccess node = (SuperConstructorAccess) clone();
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
   * @declaredat ASTNode:75
   */
  @Deprecated
  public SuperConstructorAccess fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:85
   */
  public SuperConstructorAccess treeCopyNoTransform() {
    SuperConstructorAccess tree = (SuperConstructorAccess) copy();
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
   * @declaredat ASTNode:105
   */
  public SuperConstructorAccess treeCopy() {
    SuperConstructorAccess tree = (SuperConstructorAccess) copy();
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
  public SuperConstructorAccess setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public SuperConstructorAccess setID(beaver.Symbol symbol) {
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
  public SuperConstructorAccess setArgList(List<Expr> list) {
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
  public SuperConstructorAccess addArg(Expr node) {
    List<Expr> list = (parent == null) ? getArgListNoTransform() : getArgList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public SuperConstructorAccess addArgNoTransform(Expr node) {
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
  public SuperConstructorAccess setArg(Expr node, int i) {
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
  /** @apilevel internal */
  private void decls_reset() {
    decls_computed = null;
    decls_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle decls_computed = null;

  /** @apilevel internal */
  protected SimpleSet<ConstructorDecl> decls_value;

  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:96
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:97")
  public SimpleSet<ConstructorDecl> decls() {
    ASTState state = state();
    if (decls_computed == ASTState.NON_CYCLE || decls_computed == state().cycle()) {
      return decls_value;
    }
    decls_value = decls_compute();
    if (state().inCircle()) {
      decls_computed = state().cycle();
    
    } else {
      decls_computed = ASTState.NON_CYCLE;
    
    }
    return decls_value;
  }
  /** @apilevel internal */
  private SimpleSet<ConstructorDecl> decls_compute() {
      Collection<ConstructorDecl> c = hasPrevExpr() && !prevExpr().isTypeAccess()
          ? hostType().lookupSuperConstructor()
          : lookupSuperConstructor();
      return chooseConstructor(c, getArgList());
    }
  /**
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:82
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AccessTypes", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:80")
  public boolean isSuperConstructorAccess() {
    boolean isSuperConstructorAccess_value = true;
    return isSuperConstructorAccess_value;
  }
  /**
   * @attribute syn
   * @aspect DefiniteAssignment
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:343
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:268")
  public boolean assignedAfter(Variable v) {
    boolean assignedAfter_Variable_value = v.isInstanceVariable() ? assignedBefore(v) : v.isClassVariable();
    return assignedAfter_Variable_value;
  }

  /** @apilevel internal */
  private void unassignedAfter_Variable_reset() {
    unassignedAfter_Variable_values = null;
  }
  protected java.util.Map unassignedAfter_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:899")
  public boolean unassignedAfter(Variable v) {
    Object _parameters = v;


    if (unassignedAfter_Variable_values == null) unassignedAfter_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (unassignedAfter_Variable_values.containsKey(_parameters)) {
      Object _cache = unassignedAfter_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      unassignedAfter_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_unassignedAfter_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_unassignedAfter_Variable_value = v.isInstanceVariable() ? unassignedBefore(v) : !v.isClassVariable();
        if (((Boolean)_value.value) != new_unassignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfter_Variable_values.put(_parameters, new_unassignedAfter_Variable_value);
      state.startLastCycle();
      boolean $tmp = v.isInstanceVariable() ? unassignedBefore(v) : !v.isClassVariable();

      state.leaveCircle();
      return new_unassignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfter_Variable_value = v.isInstanceVariable() ? unassignedBefore(v) : !v.isClassVariable();
      if (state.lastCycle()) {
        unassignedAfter_Variable_values.put(_parameters, new_unassignedAfter_Variable_value);
      }
      if (((Boolean)_value.value) != new_unassignedAfter_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_unassignedAfter_Variable_value;
      }
      return new_unassignedAfter_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /**
   * @attribute syn
   * @aspect TypeHierarchyCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:122
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeHierarchyCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:122")
  public Collection<Problem> typeHierarchyProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        // JLS 5?: 8.8.5.1
        // JLS 7: 8.8.7.1
        TypeDecl c = hostType();
        TypeDecl s = c.isClassDecl() ? ((ClassDecl) c).superclass() : unknownType();
        if (isQualified()) {
          if (!s.isInnerType() || s.inStaticContext()) {
            problems.add(errorf("the super type %s of %s is not an inner class",
                s.typeName(), c.typeName()));
          } else if (!qualifier().type().subtype(s.enclosingType())) {
            problems.add(errorf(
                "the type of this primary expression, %s is not enclosing the super type, %s, of %s",
                qualifier().type().typeName(), s.typeName(), c.typeName()));
          }
        }
        if (!isQualified() && s.isInnerType() && !c.hasEnclosingType(s.enclosingType())) {
          problems.add(errorf("superconstructor call in constructor for class %s requires "
              + "enclosing instance for the enclosing class %s of superclass %s",
              c.name(), s.enclosingType().name(), s.typeName()));
        }
        if (s.isInnerType() && hostType().subtype(s.enclosingType())) {
          problems.add(error("cannot reference 'this' before supertype constructor has been called"));
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect SyntacticClassification
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:119
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SyntacticClassification", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:60")
  public NameType predNameType() {
    NameType predNameType_value = NameType.EXPRESSION_NAME;
    return predNameType_value;
  }
  /**
   * @attribute syn
   * @aspect Names
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/QualifiedNames.jrag:38
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Names", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/QualifiedNames.jrag:38")
  public String name() {
    String name_value = "super";
    return name_value;
  }
  /**
   * @attribute syn
   * @aspect MethodCall
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:51
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodCall", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:51")
  public Set<InclusionEdge> inclusionEdges() {
    {
            Set<InclusionEdge> res = new HashSet<>();
            List<Expr> arguments = getArgList();
    
            for (InvocationTarget it : allDecls()) {
                ConstructorDecl decl = (ConstructorDecl) it;
                List<ParameterDeclaration> parameters = decl.getParameterList();
    
                for (int i = 0; i < arguments.getNumChild(); i++) {
                    int parameterIndex = Math.min(i, parameters.getNumChild() - 1);
                    ParameterDeclaration parameter = parameters.getChild(parameterIndex);
                    if (arguments.getChild(i).hasDeclaration() && !parameter.isVariableArity()) {
                        res.add(new InclusionEdge(arguments.getChild(i).getDeclaration(), parameters.getChild(parameterIndex).getDeclaration()));
                    }
                    else if (arguments.getChild(i).hasDeclaration() && parameter.isVariableArity()) {
                        res.add(new FieldLoadEdge(parameter.getDeclaration(), arguments.getChild(i).getDeclaration(), "-COLLECTION_ACCESS"));
                    }
                }
            }
            return res;
        }
  }
  /** @apilevel internal */
  private void allDecls_reset() {
    allDecls_computed = null;
    allDecls_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle allDecls_computed = null;

  /** @apilevel internal */
  protected Set<InvocationTarget> allDecls_value;

  /**
   * Returns a set of all declarations of the invocable entity.
   * 
   * @return A set containing all declarations of the invocable entity.
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:330
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:330")
  public Set<InvocationTarget> allDecls() {
    ASTState state = state();
    if (allDecls_computed == ASTState.NON_CYCLE || allDecls_computed == state().cycle()) {
      return allDecls_value;
    }
    allDecls_value = Collections.<InvocationTarget>singleton(decl().getContribution());
    if (state().inCircle()) {
      allDecls_computed = state().cycle();
    
    } else {
      allDecls_computed = ASTState.NON_CYCLE;
    
    }
    return allDecls_value;
  }
  /**
   * @attribute inh
   * @aspect ConstructScope
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:43
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ConstructScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:43")
  public Collection<ConstructorDecl> lookupSuperConstructor() {
    Collection<ConstructorDecl> lookupSuperConstructor_value = getParent().Define_lookupSuperConstructor(this, null);
    return lookupSuperConstructor_value;
  }
  /**
   * @attribute inh
   * @aspect TypeCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:668
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:668")
  public TypeDecl enclosingInstance() {
    TypeDecl enclosingInstance_value = getParent().Define_enclosingInstance(this, null);
    return enclosingInstance_value;
  }
  /**
   * Inherited attribute that represents the nearest enclosing invocation target
   * of an invocable entity.
   * 
   * @return The nearest enclosing invocation target or null if none.
   * @attribute inh
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:212
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:212")
  public InvocationTarget enclosingTarget() {
    ASTState state = state();
    if (enclosingTarget_computed == ASTState.NON_CYCLE || enclosingTarget_computed == state().cycle()) {
      return enclosingTarget_value;
    }
    enclosingTarget_value = getParent().Define_enclosingTarget(this, null);
    if (state().inCircle()) {
      enclosingTarget_computed = state().cycle();
    
    } else {
      enclosingTarget_computed = ASTState.NON_CYCLE;
    
    }
    return enclosingTarget_value;
  }
  /** @apilevel internal */
  private void enclosingTarget_reset() {
    enclosingTarget_computed = null;
    enclosingTarget_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle enclosingTarget_computed = null;

  /** @apilevel internal */
  protected InvocationTarget enclosingTarget_value;

  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:204
   * @apilevel internal
   */
  public boolean Define_inExplicitConstructorInvocation(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:208
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return true;
    }
    else {
      return super.Define_inExplicitConstructorInvocation(_callerNode, _childNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:204
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inExplicitConstructorInvocation
   */
  protected boolean canDefine_inExplicitConstructorInvocation(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:212
   * @apilevel internal
   */
  public TypeDecl Define_enclosingExplicitConstructorHostType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:217
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return hostType();
    }
    else {
      return super.Define_enclosingExplicitConstructorHostType(_callerNode, _childNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:212
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingExplicitConstructorHostType
   */
  protected boolean canDefine_enclosingExplicitConstructorHostType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:113
   * @apilevel internal
   */
  public boolean Define_hasPackage(ASTNode _callerNode, ASTNode _childNode, String packageName) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:118
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return unqualifiedScope().hasPackage(packageName);
    }
    else {
      return super.Define_hasPackage(_callerNode, _childNode, packageName);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:113
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute hasPackage
   */
  protected boolean canDefine_hasPackage(ASTNode _callerNode, ASTNode _childNode, String packageName) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:30
   * @apilevel internal
   */
  public SimpleSet<Variable> Define_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:245
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return unqualifiedScope().lookupVariable(name);
    }
    else {
      return super.Define_lookupVariable(_callerNode, _childNode, name);
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:120
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
  protected void collect_contributors_InvocationTarget_inclusionEdges(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:73
    for (InvocationTarget target : (Iterable<? extends InvocationTarget>) (enclosingMethods())) {
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_InvocationTarget_inclusionEdges(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_InvocationTarget_calledMethods(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:182
    {
      InvocationTarget target = (InvocationTarget) (enclosingTarget());
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_InvocationTarget_calledMethods(_root, _map);
  }
  /** @apilevel internal */
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    for (Problem value : typeHierarchyProblems()) {
      collection.add(value);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_InvocationTarget_inclusionEdges(Set<InclusionEdge> collection) {
    super.contributeTo_InvocationTarget_inclusionEdges(collection);
    for (InclusionEdge value : inclusionEdges()) {
      collection.add(value);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_InvocationTarget_calledMethods(Set<Invocable> collection) {
    super.contributeTo_InvocationTarget_calledMethods(collection);
    collection.add(this);
  }

}
