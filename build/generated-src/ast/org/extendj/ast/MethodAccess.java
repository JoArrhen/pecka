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
 * A method invocation/call expression.
 * The {@code type()} attribute computes the result type.
 * @ast node
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:90
 * @astdecl MethodAccess : Access ::= <ID:String> Arg:Expr*;
 * @production MethodAccess : {@link Access} ::= <span class="component">&lt;ID:{@link String}&gt;</span> <span class="component">Arg:{@link Expr}*</span>;

 */
public class MethodAccess extends Access implements Cloneable, Invocable, AllocationSite, DeclarationSite {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrint.jadd:457
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(getID());
    out.print("(");
    out.join(getArgList(), new PrettyPrinter.Joiner() {
      @Override
      public void printSeparator(PrettyPrinter out) {
        out.print(", ");
      }
    });
    out.print(")");
  }
  /**
   * @aspect ExceptionHandling
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:335
   */
  protected boolean reachedException(TypeDecl catchType) {
    for (TypeDecl exceptionType : exceptionCollection()) {
      if (catchType.mayCatch(exceptionType)) {
        return true;
      }
    }
    return super.reachedException(catchType);
  }
  /**
   * @aspect NodeConstructors
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NodeConstructors.jrag:70
   */
  public MethodAccess(String name, List args, int start, int end) {
    this(name, args);
    setStart(start);
    setEnd(end);
  }
  /**
   * Filter a set of methods, keeping only the static methods
   * from the input set.
   * @aspect LookupMethod
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:285
   */
  protected static SimpleSet<MethodDecl> keepStaticMethods(
      SimpleSet<MethodDecl> methods) {
    SimpleSet<MethodDecl> result = emptySet();
    for (MethodDecl method : methods) {
      if (method.isStatic()) {
        result = result.add(method);
      }
    }
    return result;
  }
  /**
   * Determine if a candidate method declaration is applicable
   * for this invocation.
   * @aspect MethodDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:391
   */
  public boolean applicable(MethodDecl decl) {
    if (getNumArg() != decl.getNumParameter()) {
      return false;
    }
    if (!name().equals(decl.name())) {
      return false;
    }
    for (int i = 0; i < getNumArg(); i++) {
      if (!getArg(i).type().subtype(decl.paramType(i))) {
        return false;
      }
    }
    return true;
  }
  /**
   * @aspect AnonymousClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:133
   */
  protected void collectExceptions(Collection<TypeDecl> exceptions) {
    super.collectExceptions(exceptions);
    for (int i = 0; i < decl().getNumException(); i++) {
      exceptions.add(decl().getException(i).type());
    }
  }
  /**
   * @aspect PrettyPrintUtil
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:109
   */
  @Override public String toString() {
    return name() + "()";
  }
  /**
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:797
   */
  protected boolean moreSpecificThan(MethodDecl m1, MethodDecl m2) {
    if (m1 instanceof ParMethodDecl) {
      return m1.moreSpecificThan(m2);
    }
    if (m1.getNumParameter() == 0) {
      return false;
    }
    if (!m1.isVariableArity() && !m2.isVariableArity()) {
      for (int i = 0; i < m1.getNumParameter(); i++) {
        if (!getArg(i).moreSpecificThan(m1.getParameter(i).type(), m2.getParameter(i).type())) {
          return false;
        }
      }
      return true;
    }

    boolean expandVarargs = m1.isVariableArity() && m2.isVariableArity();

    int num = getNumArg();
    for (int i = 0; i < num; i++) {
      ParameterDeclaration p1 = i < m1.getNumParameter()
          ? m1.getParameter(i)
          : m1.getParameter(m1.getNumParameter() - 1);
      ParameterDeclaration p2 = i < m2.getNumParameter()
          ? m2.getParameter(i)
          : m2.getParameter(m2.getNumParameter() - 1);
      TypeDecl t1 = expandVarargs && p1.isVariableArity() ? p1.type().componentType() : p1.type();
      TypeDecl t2 = expandVarargs && p2.isVariableArity() ? p2.type().componentType() : p2.type();
      if (!getArg(i).moreSpecificThan(t1, t2)) {
          return false;
      }
    }
    num++;
    if (m2.getNumParameter() == num) {
      ParameterDeclaration p1 = num < m1.getNumParameter()
          ? m1.getParameter(num)
          : m1.getParameter(m1.getNumParameter() - 1);
      ParameterDeclaration p2 = num < m2.getNumParameter()
          ? m2.getParameter(num)
          : m2.getParameter(m2.getNumParameter() - 1);
      TypeDecl t1 = expandVarargs && p1.isVariableArity() ? p1.type().componentType() : p1.type();
      TypeDecl t2 = expandVarargs && p2.isVariableArity() ? p2.type().componentType() : p2.type();
      if (!t1.subtype(t2) && !t1.withinBounds(t2)) {
        return false;
      }
    }
    return true;
  }
  /**
   * @declaredat ASTNode:1
   */
  public MethodAccess() {
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
  public MethodAccess(String p0, List<Expr> p1) {
    setID(p0);
    setChild(p1, 0);
  }
  /**
   * @declaredat ASTNode:23
   */
  public MethodAccess(beaver.Symbol p0, List<Expr> p1) {
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
    allDecls_reset();
    exceptionCollection_reset();
    computeDAbefore_int_Variable_reset();
    computeDUbefore_int_Variable_reset();
    unassignedAfter_Variable_reset();
    unassignedAfterTrue_Variable_reset();
    unassignedAfterFalse_Variable_reset();
    decls_reset();
    decl_reset();
    type_reset();
    isBooleanExpression_reset();
    isNumericExpression_reset();
    isPolyExpression_reset();
    stmtCompatible_reset();
    boundAccess_MethodDecl_reset();
    enclosingTarget_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:58
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:63
   */
  public MethodAccess clone() throws CloneNotSupportedException {
    MethodAccess node = (MethodAccess) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:68
   */
  public MethodAccess copy() {
    try {
      MethodAccess node = (MethodAccess) clone();
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
   * @declaredat ASTNode:87
   */
  @Deprecated
  public MethodAccess fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:97
   */
  public MethodAccess treeCopyNoTransform() {
    MethodAccess tree = (MethodAccess) copy();
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
   * @declaredat ASTNode:117
   */
  public MethodAccess treeCopy() {
    MethodAccess tree = (MethodAccess) copy();
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
  public MethodAccess setID(String value) {
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
  public MethodAccess setID(beaver.Symbol symbol) {
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
  public MethodAccess setArgList(List<Expr> list) {
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
  public MethodAccess addArg(Expr node) {
    List<Expr> list = (parent == null) ? getArgListNoTransform() : getArgList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public MethodAccess addArgNoTransform(Expr node) {
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
  public MethodAccess setArg(Expr node, int i) {
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
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:870
   */
   
  protected SimpleSet<MethodDecl> potentiallyApplicable(
      Iterable<MethodDecl> candidates) {
    SimpleSet<MethodDecl> potentiallyApplicable = emptySet();
    // Select potentially applicable methods.
    for (MethodDecl method : candidates) {
      if (potentiallyApplicable(method) && accessible(method)) {
        if (method.isGeneric()) {
          GenericMethodDecl gm = method.genericDecl();
          MethodAccess inferenceNode;
          if (this instanceof ParMethodAccess) {
            inferenceNode = this;
          } else {
            inferenceNode = boundAccess(gm);
          }
          Collection<TypeDecl> typeArguments = inferenceNode.inferTypeArguments(
              method.type(),
              method.getParameterList(),
              inferenceNode.getArgList(),
              gm.getTypeParameterList(),
              gm.getExceptionList());
          method = gm.lookupParMethodDecl(typeArguments);
        }
        potentiallyApplicable = potentiallyApplicable.add(method);
      }
    }
    return potentiallyApplicable;
  }
  /**
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:846
   */
   
  private SimpleSet<MethodDecl> mostSpecific(SimpleSet<MethodDecl> maxSpecific,
      MethodDecl decl) {
    SimpleSet<MethodDecl> newMax;
    if (maxSpecific.isEmpty()) {
      newMax = maxSpecific.add(decl);
    } else {
      boolean foundStricter = false;
      newMax = emptySet();
      for (MethodDecl toCompare : maxSpecific) {
        if (!(moreSpecificThan(decl, toCompare) && !moreSpecificThan(toCompare, decl))) {
          newMax = newMax.add(toCompare);
        }
        if (!moreSpecificThan(decl, toCompare) && moreSpecificThan(toCompare, decl)) {
          foundStricter = true;
        }
      }
      if (!foundStricter) {
        newMax = newMax.add(decl);
      }
    }
    return newMax;
  }
  /**
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:33
   */
   
  protected SimpleSet<MethodDecl> refined_MethodSignature15_MethodAccess_maxSpecific(Iterable<MethodDecl> candidates) {
    SimpleSet<MethodDecl> potentiallyApplicable = potentiallyApplicable(candidates);

    // First phase.
    SimpleSet<MethodDecl> maxSpecific = emptySet();
    for (MethodDecl method : potentiallyApplicable) {
      if (applicableBySubtyping(method)) {
        maxSpecific = mostSpecific(maxSpecific, method);
      }
    }

    // Second phase.
    if (maxSpecific.isEmpty()) {
      for (MethodDecl method : potentiallyApplicable) {
        if (applicableByMethodInvocationConversion(method)) {
          maxSpecific = mostSpecific(maxSpecific, method);
        }
      }
    }

    // Third phase.
    if (maxSpecific.isEmpty()) {
      for (MethodDecl method : potentiallyApplicable) {
        if (method.isVariableArity() && applicableVariableArity(method)) {
          maxSpecific = mostSpecific(maxSpecific, method);
        }
      }
    }
    return maxSpecific;
  }
  /**
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:714
   */
   
  protected SimpleSet<MethodDecl> maxSpecific(Iterable<MethodDecl> candidates) {
    SimpleSet<MethodDecl> potentiallyApplicable = potentiallyApplicable(candidates);

    // First phase.
    SimpleSet<MethodDecl> maxSpecific = emptySet();
    for (MethodDecl method : potentiallyApplicable) {
      if (boundAccess(method).applicableBySubtyping(method)) {
        maxSpecific = mostSpecific(maxSpecific, method);
      }
    }

    // Second phase.
    if (maxSpecific.isEmpty()) {
      for (MethodDecl method : potentiallyApplicable) {
        if (boundAccess(method).applicableByMethodInvocationConversion(method)) {
          maxSpecific = mostSpecific(maxSpecific, method);
        }
      }
    }

    // Third phase.
    if (maxSpecific.isEmpty()) {
      for (MethodDecl method : potentiallyApplicable) {
        if (method.isVariableArity() && boundAccess(method).applicableVariableArity(method)) {
          maxSpecific = mostSpecific(maxSpecific, method);
        }
      }
    }
    return maxSpecific;
  }
  /**
   * @aspect TypeAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:305
   */
  private TypeDecl refined_TypeAnalysis_MethodAccess_type()
{ return decl().type(); }
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
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:332
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:330")
  public Set<InvocationTarget> allDecls() {
    ASTState state = state();
    if (allDecls_computed == ASTState.NON_CYCLE || allDecls_computed == state().cycle()) {
      return allDecls_value;
    }
    allDecls_value = allDecls_compute();
    if (state().inCircle()) {
      allDecls_computed = state().cycle();
    
    } else {
      allDecls_computed = ASTState.NON_CYCLE;
    
    }
    return allDecls_value;
  }
  /** @apilevel internal */
  private Set<InvocationTarget> allDecls_compute() {
      Set<InvocationTarget> decls = new LinkedHashSet<>();
      decls.add(decl());
      if (decl().isPrivate()) {
        return decls;
      }
      MethodDecl targetDecl = decl();
      TypeDecl host = targetDecl.hostType().getGenericHostDecl();
      // Set<TypeDecl> allInstances = enclosingTarget().newInstances();
      for (TypeDecl t : host.subtypes()) {
        if (t instanceof ClassDecl || t instanceof InterfaceDecl) {
          decls.addAll(
              t.methods()
                  .stream()
                  .filter(md->(md.sameSignature(targetDecl) && md.hasBlock()))
                  .collect(Collectors.toList()));
        }
      }
  
      return decls;
    }
  /**
   * @attribute syn
   * @aspect ExceptionHandling
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:100
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:100")
  public Collection<Problem> exceptionHandlingProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        for (TypeDecl exceptionType : exceptionCollection()) {
          if (exceptionType.isCheckedException() && !handlesException(exceptionType)) {
            problems.add(errorf("%s.%s invoked in %s may throw uncaught exception %s",
                decl().hostType().fullName(), this.name(),
                hostType().fullName(), exceptionType.fullName()));
          }
        }
        return problems;
      }
  }
  /** @apilevel internal */
  private void exceptionCollection_reset() {
    exceptionCollection_computed = null;
    exceptionCollection_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle exceptionCollection_computed = null;

  /** @apilevel internal */
  protected Collection<TypeDecl> exceptionCollection_value;

  /** @return the exception types possibly thrown by this method access 
   * @attribute syn
   * @aspect ExceptionHandling
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:113
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:113")
  public Collection<TypeDecl> exceptionCollection() {
    ASTState state = state();
    if (exceptionCollection_computed == ASTState.NON_CYCLE || exceptionCollection_computed == state().cycle()) {
      return exceptionCollection_value;
    }
    exceptionCollection_value = exceptionCollection_compute();
    if (state().inCircle()) {
      exceptionCollection_computed = state().cycle();
    
    } else {
      exceptionCollection_computed = ASTState.NON_CYCLE;
    
    }
    return exceptionCollection_value;
  }
  /** @apilevel internal */
  private Collection<TypeDecl> exceptionCollection_compute() {
      Collection<TypeDecl> exceptions = new HashSet<TypeDecl>();
      Iterator<MethodDecl> iter = decls().iterator();
      if (!iter.hasNext()) {
        return exceptions;
      }
  
      MethodDecl m = iter.next();
  
      for (int i = 0; i < m.getNumException(); i++) {
        TypeDecl exceptionType = m.getException(i).type();
        exceptions.add(exceptionType);
      }
  
      while (iter.hasNext()) {
        Collection<TypeDecl> first = new HashSet<TypeDecl>();
        first.addAll(exceptions);
        Collection<TypeDecl> second = new HashSet<TypeDecl>();
        m = iter.next();
        for (int i = 0; i < m.getNumException(); i++) {
          TypeDecl exceptionType = m.getException(i).type();
          second.add(exceptionType);
        }
        exceptions = new HashSet<TypeDecl>();
        for (TypeDecl firstType : first) {
          for (TypeDecl secondType : second) {
            if (firstType.subtype(secondType)) {
              exceptions.add(firstType);
            } else if (secondType.subtype(firstType)) {
              exceptions.add(secondType);
            }
          }
        }
      }
      return exceptions;
    }
  /**
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:49
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AccessTypes", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:47")
  public boolean isMethodAccess() {
    boolean isMethodAccess_value = true;
    return isMethodAccess_value;
  }

  /** @apilevel internal */
  private void computeDAbefore_int_Variable_reset() {
    computeDAbefore_int_Variable_values = null;
  }
  protected java.util.Map computeDAbefore_int_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:502")
  public boolean computeDAbefore(int i, Variable v) {
    java.util.List _parameters = new java.util.ArrayList(2);
    _parameters.add(i);
    _parameters.add(v);


    if (computeDAbefore_int_Variable_values == null) computeDAbefore_int_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (computeDAbefore_int_Variable_values.containsKey(_parameters)) {
      Object _cache = computeDAbefore_int_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      computeDAbefore_int_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_computeDAbefore_int_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_computeDAbefore_int_Variable_value = i == 0 ? assignedBefore(v) : getArg(i-1).assignedAfter(v);
        if (((Boolean)_value.value) != new_computeDAbefore_int_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_computeDAbefore_int_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      computeDAbefore_int_Variable_values.put(_parameters, new_computeDAbefore_int_Variable_value);
      state.startLastCycle();
      boolean $tmp = i == 0 ? assignedBefore(v) : getArg(i-1).assignedAfter(v);

      state.leaveCircle();
      return new_computeDAbefore_int_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_computeDAbefore_int_Variable_value = i == 0 ? assignedBefore(v) : getArg(i-1).assignedAfter(v);
      if (state.lastCycle()) {
        computeDAbefore_int_Variable_values.put(_parameters, new_computeDAbefore_int_Variable_value);
      }
      if (((Boolean)_value.value) != new_computeDAbefore_int_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_computeDAbefore_int_Variable_value;
      }
      return new_computeDAbefore_int_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /**
   * @attribute syn
   * @aspect DefiniteAssignment
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:505
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:268")
  public boolean assignedAfter(Variable v) {
    boolean assignedAfter_Variable_value = getNumArg() == 0
          ? assignedBefore(v)
          : getArg(getNumArg()-1).assignedAfter(v);
    return assignedAfter_Variable_value;
  }
  /**
   * @attribute syn
   * @aspect DefiniteAssignment
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:510
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:375")
  public boolean assignedAfterTrue(Variable v) {
    boolean assignedAfterTrue_Variable_value = isFalse() || (getNumArg() == 0 ? assignedBefore(v) : getArg(getNumArg()-1).assignedAfter(v));
    return assignedAfterTrue_Variable_value;
  }
  /**
   * @attribute syn
   * @aspect DefiniteAssignment
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:513
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:377")
  public boolean assignedAfterFalse(Variable v) {
    boolean assignedAfterFalse_Variable_value = isTrue() || (getNumArg() == 0 ? assignedBefore(v) : getArg(getNumArg()-1).assignedAfter(v));
    return assignedAfterFalse_Variable_value;
  }

  /** @apilevel internal */
  private void computeDUbefore_int_Variable_reset() {
    computeDUbefore_int_Variable_values = null;
  }
  protected java.util.Map computeDUbefore_int_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:1118")
  public boolean computeDUbefore(int i, Variable v) {
    java.util.List _parameters = new java.util.ArrayList(2);
    _parameters.add(i);
    _parameters.add(v);


    if (computeDUbefore_int_Variable_values == null) computeDUbefore_int_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (computeDUbefore_int_Variable_values.containsKey(_parameters)) {
      Object _cache = computeDUbefore_int_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      computeDUbefore_int_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_computeDUbefore_int_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_computeDUbefore_int_Variable_value = i == 0 ? unassignedBefore(v) : getArg(i-1).unassignedAfter(v);
        if (((Boolean)_value.value) != new_computeDUbefore_int_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_computeDUbefore_int_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      computeDUbefore_int_Variable_values.put(_parameters, new_computeDUbefore_int_Variable_value);
      state.startLastCycle();
      boolean $tmp = i == 0 ? unassignedBefore(v) : getArg(i-1).unassignedAfter(v);

      state.leaveCircle();
      return new_computeDUbefore_int_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_computeDUbefore_int_Variable_value = i == 0 ? unassignedBefore(v) : getArg(i-1).unassignedAfter(v);
      if (state.lastCycle()) {
        computeDUbefore_int_Variable_values.put(_parameters, new_computeDUbefore_int_Variable_value);
      }
      if (((Boolean)_value.value) != new_computeDUbefore_int_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_computeDUbefore_int_Variable_value;
      }
      return new_computeDUbefore_int_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
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
        new_unassignedAfter_Variable_value = getNumArg() == 0 ? unassignedBefore(v) : getArg(getNumArg()-1).unassignedAfter(v);
        if (((Boolean)_value.value) != new_unassignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfter_Variable_values.put(_parameters, new_unassignedAfter_Variable_value);
      state.startLastCycle();
      boolean $tmp = getNumArg() == 0 ? unassignedBefore(v) : getArg(getNumArg()-1).unassignedAfter(v);

      state.leaveCircle();
      return new_unassignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfter_Variable_value = getNumArg() == 0 ? unassignedBefore(v) : getArg(getNumArg()-1).unassignedAfter(v);
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

  /** @apilevel internal */
  private void unassignedAfterTrue_Variable_reset() {
    unassignedAfterTrue_Variable_values = null;
  }
  protected java.util.Map unassignedAfterTrue_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:905")
  public boolean unassignedAfterTrue(Variable v) {
    Object _parameters = v;


    if (unassignedAfterTrue_Variable_values == null) unassignedAfterTrue_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (unassignedAfterTrue_Variable_values.containsKey(_parameters)) {
      Object _cache = unassignedAfterTrue_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      unassignedAfterTrue_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_unassignedAfterTrue_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_unassignedAfterTrue_Variable_value = isFalse()
              || (getNumArg() == 0 ? unassignedBefore(v) : getArg(getNumArg()-1).unassignedAfter(v));
        if (((Boolean)_value.value) != new_unassignedAfterTrue_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfterTrue_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfterTrue_Variable_values.put(_parameters, new_unassignedAfterTrue_Variable_value);
      state.startLastCycle();
      boolean $tmp = isFalse()
            || (getNumArg() == 0 ? unassignedBefore(v) : getArg(getNumArg()-1).unassignedAfter(v));

      state.leaveCircle();
      return new_unassignedAfterTrue_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfterTrue_Variable_value = isFalse()
            || (getNumArg() == 0 ? unassignedBefore(v) : getArg(getNumArg()-1).unassignedAfter(v));
      if (state.lastCycle()) {
        unassignedAfterTrue_Variable_values.put(_parameters, new_unassignedAfterTrue_Variable_value);
      }
      if (((Boolean)_value.value) != new_unassignedAfterTrue_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_unassignedAfterTrue_Variable_value;
      }
      return new_unassignedAfterTrue_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }

  /** @apilevel internal */
  private void unassignedAfterFalse_Variable_reset() {
    unassignedAfterFalse_Variable_values = null;
  }
  protected java.util.Map unassignedAfterFalse_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:907")
  public boolean unassignedAfterFalse(Variable v) {
    Object _parameters = v;


    if (unassignedAfterFalse_Variable_values == null) unassignedAfterFalse_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (unassignedAfterFalse_Variable_values.containsKey(_parameters)) {
      Object _cache = unassignedAfterFalse_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      unassignedAfterFalse_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_unassignedAfterFalse_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_unassignedAfterFalse_Variable_value = isTrue()
              || (getNumArg() == 0 ? unassignedBefore(v) : getArg(getNumArg()-1).unassignedAfter(v));
        if (((Boolean)_value.value) != new_unassignedAfterFalse_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfterFalse_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfterFalse_Variable_values.put(_parameters, new_unassignedAfterFalse_Variable_value);
      state.startLastCycle();
      boolean $tmp = isTrue()
            || (getNumArg() == 0 ? unassignedBefore(v) : getArg(getNumArg()-1).unassignedAfter(v));

      state.leaveCircle();
      return new_unassignedAfterFalse_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfterFalse_Variable_value = isTrue()
            || (getNumArg() == 0 ? unassignedBefore(v) : getArg(getNumArg()-1).unassignedAfter(v));
      if (state.lastCycle()) {
        unassignedAfterFalse_Variable_values.put(_parameters, new_unassignedAfterFalse_Variable_value);
      }
      if (((Boolean)_value.value) != new_unassignedAfterFalse_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_unassignedAfterFalse_Variable_value;
      }
      return new_unassignedAfterFalse_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /**
   * Test if this method call has an argument of Unknown type.
   * 
   * <p>If an argument has Unknown type, then some error messages should
   * be skipped because the root error is that the type of the argument
   * could not be decided.
   * @attribute syn
   * @aspect TypeHierarchyCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:58
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeHierarchyCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:58")
  public boolean hasUnknownArg() {
    {
        for (Expr arg : getArgList()) {
          if (arg.type().isUnknown()) {
            return true;
          }
        }
        return false;
      }
  }
  /**
   * @attribute syn
   * @aspect TypeHierarchyCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:69
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeHierarchyCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:69")
  public Collection<Problem> typeHierarchyProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        if (isQualified() && qualifier().isPackageAccess() && !qualifier().isUnknown()) {
          problems.add(errorf("The method %s cannot be qualified by a package name.",
              decl().fullSignature()));
        }
        if (isQualified() && decl().isAbstract() && qualifier().isSuperAccess()) {
          problems.add(error("may not access abstract methods in superclass"));
        }
        if (decls().isEmpty() && !hasUnknownArg() && (!isQualified() || !qualifier().isUnknown())) {
          StringBuilder sb = new StringBuilder();
          sb.append("no method named " + name());
          sb.append("(");
          for (int i = 0; i < getNumArg(); i++) {
            TypeDecl argType = getArg(i).type();
            if (argType.isVoid()) {
              // Error will be reported for the void argument in typeCheck
              // so we return now to avoid confusing double errors.
              return problems;
            }
            if (i != 0) {
              sb.append(", ");
            }
            sb.append(argType.typeName());
          }
          sb.append(")" + " in " + methodHost() + " matches.");
          if (singleCandidateDecl() != null) {
            sb.append(" However, there is a method " + singleCandidateDecl().fullSignature());
          }
          problems.add(error(sb.toString()));
        }
        if (decls().size() > 1) {
          boolean allAbstract = true;
          for (MethodDecl m : decls()) {
            if (!m.isAbstract() && !m.hostType().isObject()) {
              allAbstract = false;
              break;
            }
          }
          if (!allAbstract && validArgs()) {
            StringBuilder sb = new StringBuilder();
            sb.append("several most specific methods for " + this.prettyPrint() + "\n");
            for (MethodDecl m : decls()) {
              sb.append("    " + m.fullSignature() + " in " + m.hostType().typeName() + "\n");
            }
            problems.add(error(sb.toString()));
          }
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect TypeCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:152
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:152")
  public Collection<Problem> typeProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        for (int i = 0; i < getNumArg(); ++i) {
          if (getArg(i).type().isVoid()) {
            problems.add(errorf("expression '%s' has type void and is not a valid method argument",
                getArg(i).prettyPrint()));
          }
        }
        if (isQualified() && decl().isAbstract() && qualifier().isSuperAccess()) {
          problems.add(error("may not access abstract methods in superclass"));
        }
        if (!decl().isVariableArity() || invokesVariableArityAsArray()) {
          for (int i = 0; i < decl().getNumParameter(); i++) {
            TypeDecl exprType = getArg(i).type();
            TypeDecl parmType = decl().getParameter(i).type();
            if (!exprType.methodInvocationConversionTo(parmType) &&
                !exprType.isUnknown() && !parmType.isUnknown()) {
              problems.add(errorf("argument '%s' of type %s is not compatible with the method parameter type %s",
                  getArg(i).prettyPrint(), exprType.typeName(), parmType.typeName()));
            }
          }
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:100
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:100")
  public boolean validArgs() {
    {
        for (int i = 0; i < getNumArg(); i++) {
          if (!getArg(i).isPolyExpression() && getArg(i).type().isUnknown()) {
            return false;
          }
        }
        return true;
      }
  }
  /**
   * @attribute syn
   * @aspect SyntacticClassification
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:131
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SyntacticClassification", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:60")
  public NameType predNameType() {
    NameType predNameType_value = NameType.AMBIGUOUS_NAME;
    return predNameType_value;
  }
  /**
   * Returns one of the candidate declarations of this method access,
   * or {@code null} if there exist no possible candidate declarations.
   * 
   * <p>This attribute is only used in error reporting, to give a hint about
   * a candidate declaration that was not applicable for method invocation
   * for some reason.
   * @attribute syn
   * @aspect LookupMethod
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:204
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupMethod", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:204")
  public MethodDecl singleCandidateDecl() {
    {
        String signature = "";
        MethodDecl result = null;
        for (MethodDecl m : lookupMethod(name())) {
          String otherSignature = m.fullSignature();
          // Choose the candidate matching arity or the lexicographically first signature.
          if (result == null
              || (m.getNumParameter() == getNumArg() && result.getNumParameter() != getNumArg())
              || (m.getNumParameter() == getNumArg() && otherSignature.compareTo(signature) < 0)) {
            signature = otherSignature;
            result = m;
          }
        }
        return result;
      }
  }
  /** @apilevel internal */
  private void decls_reset() {
    decls_computed = null;
    decls_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle decls_computed = null;

  /** @apilevel internal */
  protected SimpleSet<MethodDecl> decls_value;

  /**
   * Find all most specific applicable method declarations for this invocation.
   * @attribute syn
   * @aspect LookupMethod
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:246
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupMethod", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:246")
  public SimpleSet<MethodDecl> decls() {
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
  private SimpleSet<MethodDecl> decls_compute() {
      SimpleSet<MethodDecl> maxSpecific = maxSpecific(lookupMethod(name()));
      if (isQualified() ? qualifier().staticContextQualifier() : inStaticContext()) {
        maxSpecific = keepStaticMethods(maxSpecific);
      }
      return maxSpecific;
    }
  /** @apilevel internal */
  private void decl_reset() {
    decl_computed = null;
    decl_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle decl_computed = null;

  /** @apilevel internal */
  protected MethodDecl decl_value;

  /**
   * Find the single method declaration matching this invocation.
   * 
   * <p>If no such declaration exists, the unknown method declaration is returned
   * instead.
   * @attribute syn
   * @aspect LookupMethod
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:260
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupMethod", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:260")
  public MethodDecl decl() {
    ASTState state = state();
    if (decl_computed == ASTState.NON_CYCLE || decl_computed == state().cycle()) {
      return decl_value;
    }
    decl_value = decl_compute();
    if (state().inCircle()) {
      decl_computed = state().cycle();
    
    } else {
      decl_computed = ASTState.NON_CYCLE;
    
    }
    return decl_value;
  }
  /** @apilevel internal */
  private MethodDecl decl_compute() {
      SimpleSet<MethodDecl> decls = decls();
      if (decls.isSingleton()) {
        return decls.singletonValue();
      }
  
      // Only return the first method in case of multiply inherited abstract methods.
      // See JLS6 section 8.4.6.4.
      boolean allAbstract = true;
      for (MethodDecl m : decls) {
        if (!m.isAbstract() && !m.hostType().isObject()) {
          allAbstract = false;
          break;
        }
      }
      if (decls.size() > 1 && allAbstract) {
        return decls.iterator().next();
      }
      return unknownMethod();
    }
  /**
   * Determine if a candidate method declaration is accessible
   * from this invocation.
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:410
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:410")
  public boolean accessible(MethodDecl m) {
    {
        if (!isQualified()) {
          return true;
        }
        if (!m.accessibleFrom(hostType())) {
          return false;
        }
        // The method is not accessible if the type is not accessible.
        if (!qualifier().type().accessibleFrom(hostType())) {
          return false;
        }
        // 6.6.2.1 -  include qualifier type for protected access
        if (m.isProtected() && !m.hostPackage().equals(hostPackage())
            && !m.isStatic() && !qualifier().isSuperAccess()) {
          return hostType().mayAccess(this, m);
        }
        return true;
      }
  }
  /**
   * @attribute syn
   * @aspect Names
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/QualifiedNames.jrag:36
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Names", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/QualifiedNames.jrag:36")
  public String name() {
    String name_value = getID();
    return name_value;
  }
  /** @apilevel internal */
  private void type_reset() {
    type_computed = null;
    type_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle type_computed = null;

  /** @apilevel internal */
  protected TypeDecl type_value;

  /**
   * @attribute syn
   * @aspect Generics
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:92
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:294")
  public TypeDecl type() {
    ASTState state = state();
    if (type_computed == ASTState.NON_CYCLE || type_computed == state().cycle()) {
      return type_value;
    }
    type_value = type_compute();
    if (state().inCircle()) {
      type_computed = state().cycle();
    
    } else {
      type_computed = ASTState.NON_CYCLE;
    
    }
    return type_value;
  }
  /** @apilevel internal */
  private TypeDecl type_compute() {
      if (getNumArg() == 0 && name().equals("getClass") && decl().hostType().isObject()) {
        TypeDecl typeClass = lookupType("java.lang", "Class");
        if (typeClass instanceof GenericClassDecl) {
          TypeDecl bound = isQualified() ? qualifier().type() : hostType();
          ArrayList<TypeDecl> args = new ArrayList<TypeDecl>();
          args.add(bound.erasure().asWildcardExtends());
          return ((GenericClassDecl) typeClass).lookupParTypeDecl(args);
        }
      }
      // Legacy getClass access using non-generic java.lang.Class.
      return refined_TypeAnalysis_MethodAccess_type();
    }
  /**
   * @attribute syn
   * @aspect VariableArityParameters
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:65
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableArityParameters", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:65")
  public boolean invokesVariableArityAsArray() {
    {
        if (!decl().isVariableArity()) {
          return false;
        }
        if (arity() != decl().arity()) {
          return false;
        }
        return getArg(getNumArg()-1).type().methodInvocationConversionTo(decl().lastParameter().type());
      }
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:327
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:327")
  public boolean applicableBySubtyping(MethodDecl m) {
    {
        if (m.getNumParameter() != getNumArg()) {
          return false;
        }
        for (int i = 0; i < m.getNumParameter(); i++) {
          if (!getArg(i).pertinentToApplicability(this, m, i)) {
            continue;
          } else if (!getArg(i).compatibleStrictContext(m.getParameter(i).type())) {
            return false;
          }
        }
        return true;
      }
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:355
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:355")
  public boolean applicableByMethodInvocationConversion(MethodDecl m) {
    {
        if (m.getNumParameter() != getNumArg()) {
          return false;
        }
        for (int i = 0; i < m.getNumParameter(); i++) {
          if (!getArg(i).pertinentToApplicability(this, m, i)) {
            continue;
          } else if (!getArg(i).compatibleLooseContext(m.getParameter(i).type())) {
            return false;
          }
        }
        return true;
      }
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:380
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:380")
  public boolean applicableVariableArity(MethodDecl m) {
    {
        for (int i = 0; i < m.getNumParameter() - 1; i++) {
          if (!getArg(i).pertinentToApplicability(this, m, i)) {
            continue;
          }
          if (!getArg(i).compatibleLooseContext(m.getParameter(i).type())) {
            return false;
          }
        }
        for (int i = m.getNumParameter() - 1; i < getNumArg(); i++) {
          if (!getArg(i).pertinentToApplicability(this, m, i)) {
            continue;
          }
          if (!getArg(i).compatibleLooseContext(m.lastParameter().type().componentType())) {
            return false;
          }
        }
        return true;
      }
  }
  /**
   * A member method is potentially applicable to a method invocation if and
   * only if all of the following are true:
   * <ul>
   * <li>The name of the member is identical to the name of the method in the
   * method invocation.
   * <li>The member is accessible (\ufffd6.6) to the class or interface in which
   * the method invocation appears.
   * <li>The arity of the member is lesser or equal to the arity of the
   * method invocation.
   * <li>If the member is a variable arity method with arity n, the arity of
   * the method invocation is greater or equal to n-1.
   * <li>If the member is a fixed arity method with arity n, the arity of the
   * method invocation is equal to n.
   * <li>If the method invocation includes explicit type parameters, and the
   * member is a generic method, then the number of actual type parameters is
   * equal to the number of formal type parameters.
   * </ul>
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:480
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:480")
  public boolean potentiallyApplicable(MethodDecl m) {
    {
        if (!m.name().equals(name())) {
          return false;
        }
        if (!m.accessibleFrom(hostType())) {
          return false;
        }
        if (!m.isVariableArity()) {
          if (arity() != m.arity()) {
            return false;
          }
          for (int i = 0; i < getNumArg(); i++) {
            if (!getArg(i).potentiallyCompatible(m.getParameter(i).type(), m)) {
              return false;
            }
          }
        }
        if (m.isVariableArity()) {
          if (!(arity() >= m.arity() - 1)) {
            return false;
          }
          for (int i = 0; i < m.arity() - 2; i++) {
            if (!getArg(i).potentiallyCompatible(m.getParameter(i).type(), m)) {
              return false;
            }
          }
          TypeDecl varArgType = m.getParameter(m.arity() - 1).type();
          if (arity() == m.arity()) {
            if (!getArg(arity() - 1).potentiallyCompatible(varArgType, m)
                && !getArg(arity() - 1).potentiallyCompatible(varArgType.componentType(), m)) {
              return false;
            }
          } else if (arity() > m.arity()) {
            for (int i = m.arity() - 1; i < arity(); i++) {
              if (!getArg(i).potentiallyCompatible(varArgType.componentType(), m)) {
                return false;
              }
            }
          }
        }
    
        if (m.isGeneric()) {
          GenericMethodDecl gm = m.genericDecl();
          MethodAccess inferenceNode;
          if (this instanceof ParMethodAccess) {
            // Type inference is not needed for a parameterized method access.
            inferenceNode = this;
          } else {
            inferenceNode = boundAccess(gm);
          }
          ArrayList<TypeDecl> typeArguments = inferenceNode.inferTypeArguments(
              gm.type(),
              gm.getParameterList(),
              inferenceNode.getArgList(),
              gm.getTypeParameterList(),
              gm.getExceptionList());
          if (!typeArguments.isEmpty()) {
            if (gm.getNumTypeParameter() != typeArguments.size()) {
              return false;
            }
            ParMethodDecl parMethod = gm.lookupParMethodDecl(typeArguments);
            for (int i = 0; i < gm.getNumTypeParameter(); i++) {
              if (!((TypeDecl) typeArguments.get(i)).withinBounds(parMethod.getTypeParameter(i))) {
                return false;
              }
            }
          }
        }
    
        return true;
      }
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:517
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:517")
  public int arity() {
    int arity_value = getNumArg();
    return arity_value;
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:149
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:149")
  public boolean modifiedInScope(Variable var) {
    {
        for (int i = 0; i < getNumArg(); ++i) {
          if (getArg(i).modifiedInScope(var)) {
            return true;
          }
        }
        return false;
      }
  }
  /**
   * @attribute syn
   * @aspect SafeVarargs
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SafeVarargs.jrag:70
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SafeVarargs", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SafeVarargs.jrag:70")
  public Collection<Problem> uncheckedWarnings() {
    {
        MethodDecl decl = decl();
        if (decl.getNumParameter() == 0 || decl.getNumParameter() > getNumArg()) {
          return Collections.emptyList();
        }
    
        ParameterDeclaration param = decl.getParameter(decl.getNumParameter()-1);
        if (!withinSuppressWarnings("unchecked")
            && !decl.hasAnnotationSafeVarargs()
            && param.isVariableArity()
            && !param.type().isReifiable()) {
          return Collections.singletonList(
              warning("unchecked array creation for variable arity parameter of " + decl().name()));
        }
        return Collections.emptyList();
      }
  }
  /** @apilevel internal */
  private void isBooleanExpression_reset() {
    isBooleanExpression_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isBooleanExpression_computed = null;

  /** @apilevel internal */
  protected boolean isBooleanExpression_value;

  /**
   * @attribute syn
   * @aspect PolyExpressions
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:43
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PolyExpressions", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:29")
  public boolean isBooleanExpression() {
    ASTState state = state();
    if (isBooleanExpression_computed == ASTState.NON_CYCLE || isBooleanExpression_computed == state().cycle()) {
      return isBooleanExpression_value;
    }
    isBooleanExpression_value = isBooleanExpression_compute();
    if (state().inCircle()) {
      isBooleanExpression_computed = state().cycle();
    
    } else {
      isBooleanExpression_computed = ASTState.NON_CYCLE;
    
    }
    return isBooleanExpression_value;
  }
  /** @apilevel internal */
  private boolean isBooleanExpression_compute() {
      MethodDecl decl = decl();
      if (decl instanceof ParMethodDecl) {
        return ((ParMethodDecl) decl).genericMethodDecl().type().isBoolean();
      } else {
        return decl.type().isBoolean();
      }
    }
  /** @apilevel internal */
  private void isNumericExpression_reset() {
    isNumericExpression_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isNumericExpression_computed = null;

  /** @apilevel internal */
  protected boolean isNumericExpression_value;

  /**
   * @attribute syn
   * @aspect PolyExpressions
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:63
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PolyExpressions", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:60")
  public boolean isNumericExpression() {
    ASTState state = state();
    if (isNumericExpression_computed == ASTState.NON_CYCLE || isNumericExpression_computed == state().cycle()) {
      return isNumericExpression_value;
    }
    isNumericExpression_value = isNumericExpression_compute();
    if (state().inCircle()) {
      isNumericExpression_computed = state().cycle();
    
    } else {
      isNumericExpression_computed = ASTState.NON_CYCLE;
    
    }
    return isNumericExpression_value;
  }
  /** @apilevel internal */
  private boolean isNumericExpression_compute() {
      MethodDecl decl = decl();
      if (decl instanceof ParMethodDecl) {
        return ((ParMethodDecl) decl).genericMethodDecl().type().isNumericType();
      } else {
        return decl.type().isNumericType();
      }
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:106
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PolyExpressions", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:86")
  public boolean isPolyExpression() {
    ASTState state = state();
    if (isPolyExpression_computed == ASTState.NON_CYCLE || isPolyExpression_computed == state().cycle()) {
      return isPolyExpression_value;
    }
    isPolyExpression_value = isPolyExpression_compute();
    if (state().inCircle()) {
      isPolyExpression_computed = state().cycle();
    
    } else {
      isPolyExpression_computed = ASTState.NON_CYCLE;
    
    }
    return isPolyExpression_value;
  }
  /** @apilevel internal */
  private boolean isPolyExpression_compute() {
      if (!assignmentContext() && !invocationContext()) {
        return false;
      }
      if (!decl().isGeneric()) {
        return false;
      }
      GenericMethodDecl genericDecl = decl().genericDecl();
      return genericDecl.typeVariableInReturn();
    }
  /** @apilevel internal */
  private void stmtCompatible_reset() {
    stmtCompatible_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle stmtCompatible_computed = null;

  /** @apilevel internal */
  protected boolean stmtCompatible_value;

  /**
   * @attribute syn
   * @aspect StmtCompatible
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:153
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StmtCompatible", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:145")
  public boolean stmtCompatible() {
    ASTState state = state();
    if (stmtCompatible_computed == ASTState.NON_CYCLE || stmtCompatible_computed == state().cycle()) {
      return stmtCompatible_value;
    }
    stmtCompatible_value = true;
    if (state().inCircle()) {
      stmtCompatible_computed = state().cycle();
    
    } else {
      stmtCompatible_computed = ASTState.NON_CYCLE;
    
    }
    return stmtCompatible_value;
  }
  /** @apilevel internal */
  private void boundAccess_MethodDecl_reset() {
    boundAccess_MethodDecl_values = null;
    boundAccess_MethodDecl_proxy = null;
  }
  /** @apilevel internal */
  protected ASTNode boundAccess_MethodDecl_proxy;
  /** @apilevel internal */
  protected java.util.Map boundAccess_MethodDecl_values;

  /**
   * Creates a method access with a fixed target method declaration.
   * 
   * <p>This is used to pin the type inference to inferring types based on a
   * candidate method declaration, instead of inferring types for an unbound
   * method access - which would cause circularity.
   * 
   * <p>Binding a method access to a fixed method declaration solves a
   * circularity issue by creating bottom values for the inference attributes.
   * This fixes circularlity problems in nested type inference (inferred type
   * inside generic method argument).
   * When the innermost type inference is performed, it will try to
   * access the target type given by the context - this is the point
   * where the inference becomes circular.
   * 
   * @return a BoundMethodAccess with the argument method as the target
   * declaration.
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:49
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:49")
  public MethodAccess boundAccess(MethodDecl decl) {
    Object _parameters = decl;
    if (boundAccess_MethodDecl_values == null) boundAccess_MethodDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (boundAccess_MethodDecl_values.containsKey(_parameters)) {
      return (MethodAccess) boundAccess_MethodDecl_values.get(_parameters);
    }
    state().enterLazyAttribute();
    MethodAccess boundAccess_MethodDecl_value = boundAccess_compute(decl);
    if (boundAccess_MethodDecl_proxy == null) {
      boundAccess_MethodDecl_proxy = new ASTNode();
      boundAccess_MethodDecl_proxy.setParent(this);
    }
    if (boundAccess_MethodDecl_value != null) {
      boundAccess_MethodDecl_value.setParent(boundAccess_MethodDecl_proxy);
      if (boundAccess_MethodDecl_value.mayHaveRewrite()) {
        boundAccess_MethodDecl_value = (MethodAccess) boundAccess_MethodDecl_value.rewrittenNode();
        boundAccess_MethodDecl_value.setParent(boundAccess_MethodDecl_proxy);
      }
    }
    boundAccess_MethodDecl_values.put(_parameters, boundAccess_MethodDecl_value);
    state().leaveLazyAttribute();
    return boundAccess_MethodDecl_value;
  }
  /** @apilevel internal */
  private MethodAccess boundAccess_compute(MethodDecl decl) {
      return new BoundMethodAccess(name(), getArgList().treeCopyNoTransform(), decl);
    }
  /**
   * @attribute syn
   * @aspect Utilities
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:330
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Utilities", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:326")
  public String name2() {
    String name2_value = name();
    return name2_value;
  }
  /**
   * @attribute syn
   * @aspect MethodCall
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:3
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodCall", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:3")
  public Set<InclusionEdge> inclusionEdges() {
    {
            Set<InclusionEdge> res = new HashSet<>();
            List<Expr> arguments = getArgList();
    
            for (InvocationTarget it : allDecls()) {
                MethodDecl decl = (MethodDecl) it; // Should be safe since the declaration of a method access should always be a method declaration
                List<ParameterDeclaration> parameters = decl.getParameterList();
    
                for (int i = 0; i < arguments.getNumChild(); i++) {
                    int parameterIndex = Math.min(i, parameters.getNumChild() - 1); // To handle T... parameter
                    ParameterDeclaration parameter = parameters.getChild(parameterIndex);
                    if (arguments.getChild(i).hasDeclaration() && !parameter.isVariableArity()) {
                        res.add(new InclusionEdge(arguments.getChild(i).getDeclaration(), parameter.getDeclaration()));
                    }
                    else if (arguments.getChild(i).hasDeclaration() && parameter.isVariableArity()) {
                        res.add(new FieldLoadEdge(parameter.getDeclaration(), arguments.getChild(i).getDeclaration(), "-COLLECTION_ACCESS"));
                    }
                }
            }
            return res;
        }
  }
  /**
   * @attribute syn
   * @aspect MethodCall
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:119
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodCall", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:119")
  public ASTNode getNode() {
    ASTNode getNode_value = this;
    return getNode_value;
  }
  /**
   * @attribute syn
   * @aspect Utilities
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:285
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Utilities", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:285")
  public DeclarationSite getDeclaration() {
    {
            return this;
            //if (decl().isAnalyzable()) {
            //    return this;
            //} else {
            //    if (decl().type().isPrimitive()) {
            //        return null;
            //    } else {
            //        return null; // Should treat this case as allocation
            //    }
            //}
        }
  }
  /**
   * @attribute syn
   * @aspect pointerbench
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:30
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="pointerbench", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:30")
  public String getTestArg(int arg) {
    String getTestArg_int_value = getArg(arg).getTestArg(arg);
    return getTestArg_int_value;
  }
  /**
   * @attribute syn
   * @aspect pointerbench
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:56
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="pointerbench", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:56")
  public int stmtID() {
    int stmtID_value = getNumArg() > 0 ? getArg(0).stmtID() : program().NULL_BENCHMARK_ID;
    return stmtID_value;
  }
  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:75
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:75")
  public String info() {
    String info_value = getClass().toString() + " " + compilationUnit().pathName() + " " + lineNumber();
    return info_value;
  }
  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:76
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:76")
  public boolean isNull() {
    boolean isNull_value = type().isNull();
    return isNull_value;
  }
  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:78
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:78")
  public TypeDecl atype() {
    TypeDecl atype_value = type();
    return atype_value;
  }
  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:189
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:189")
  public String fileName() {
    String fileName_value = compilationUnit().pathName();
    return fileName_value;
  }
  /**
   * @attribute syn
   * @aspect pointerbench
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:63
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="pointerbench", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:63")
  public int allocID() {
    int allocID_value = program().NULL_BENCHMARK_ID;
    return allocID_value;
  }
  /**
   * @attribute syn
   * @aspect FilterUtils
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:21
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FilterUtils", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:21")
  public TypeDecl type2() {
    TypeDecl type2_value = assignConvertedType();
    return type2_value;
  }
  /**
   * @attribute syn
   * @aspect Utilities
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:315
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Utilities", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:315")
  public boolean hasDeclaration() {
    boolean hasDeclaration_value = getDeclaration() != null;
    return hasDeclaration_value;
  }
  /**
   * @attribute inh
   * @aspect ExceptionHandling
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:88
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:88")
  public boolean handlesException(TypeDecl exceptionType) {
    boolean handlesException_TypeDecl_value = getParent().Define_handlesException(this, null, exceptionType);
    return handlesException_TypeDecl_value;
  }
  /**
   * @attribute inh
   * @aspect TypeHierarchyCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:200
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TypeHierarchyCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:200")
  public boolean inExplicitConstructorInvocation() {
    boolean inExplicitConstructorInvocation_value = getParent().Define_inExplicitConstructorInvocation(this, null);
    return inExplicitConstructorInvocation_value;
  }
  /**
   * Returns a method declaration representing unknown methods.
   * Used in method lookup when no matching method was found.
   * @attribute inh
   * @aspect LookupMethod
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:49
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="LookupMethod", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:49")
  public MethodDecl unknownMethod() {
    MethodDecl unknownMethod_value = getParent().Define_unknownMethod(this, null);
    return unknownMethod_value;
  }
  /**
   * @attribute inh
   * @aspect SuppressWarnings
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SuppressWarnings.jrag:39
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="SuppressWarnings", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SuppressWarnings.jrag:39")
  public boolean withinSuppressWarnings(String annot) {
    boolean withinSuppressWarnings_String_value = getParent().Define_withinSuppressWarnings(this, null, annot);
    return withinSuppressWarnings_String_value;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:101
   * @apilevel internal
   */
  public boolean Define_isRightChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:107
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else {
      int childIndex = this.getIndexOfChild(_callerNode);
      return isRightChildOfDot();
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:118
   * @apilevel internal
   */
  public Expr Define_prevExpr(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:123
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return prevExprError();
    }
    else {
      int childIndex = this.getIndexOfChild(_callerNode);
      return prevExpr();
    }
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:500
      int i = _callerNode.getIndexOfChild(_childNode);
      return computeDAbefore(i, v);
    }
    else {
      return getParent().Define_assignedBefore(this, _callerNode, v);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute assignedBefore
   */
  protected boolean canDefine_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:887
   * @apilevel internal
   */
  public boolean Define_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:1116
      int i = _callerNode.getIndexOfChild(_childNode);
      return computeDUbefore(i, v);
    }
    else {
      return getParent().Define_unassignedBefore(this, _callerNode, v);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:887
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unassignedBefore
   */
  protected boolean canDefine_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:33
   * @apilevel internal
   */
  public String Define_methodHost(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return unqualifiedScope().methodHost();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:33
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute methodHost
   */
  protected boolean canDefine_methodHost(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:113
   * @apilevel internal
   */
  public boolean Define_hasPackage(ASTNode _callerNode, ASTNode _childNode, String packageName) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:114
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return unqualifiedScope().hasPackage(packageName);
    }
    else {
      return getParent().Define_hasPackage(this, _callerNode, packageName);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:231
   * @apilevel internal
   */
  public SimpleSet<TypeDecl> Define_lookupType(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == boundAccess_MethodDecl_proxy) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:54
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return unqualifiedScope().lookupType(name);
    }
    else if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:405
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return unqualifiedScope().lookupType(name);
    }
    else {
      return getParent().Define_lookupType(this, _callerNode, name);
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
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:30
   * @apilevel internal
   */
  public SimpleSet<Variable> Define_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return unqualifiedScope().lookupVariable(name);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:139
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return NameType.EXPRESSION_NAME;
    }
    else {
      return getParent().Define_nameType(this, _callerNode);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:116
   * @apilevel internal
   */
  public Collection<MethodDecl> Define_lookupMethod(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:128
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return unqualifiedScope().lookupMethod(name);
    }
    else {
      return getParent().Define_lookupMethod(this, _callerNode, name);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:116
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupMethod
   */
  protected boolean canDefine_lookupMethod(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/AssignConvertedType.jrag:39
   * @apilevel internal
   */
  public TypeDecl Define_assignConvertedType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == boundAccess_MethodDecl_proxy) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:198
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return assignConvertedType();
    }
    else if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:201
      int index = _callerNode.getIndexOfChild(_childNode);
      return decl().paramType(index);
    }
    else {
      return getParent().Define_assignConvertedType(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/AssignConvertedType.jrag:39
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute assignConvertedType
   */
  protected boolean canDefine_assignConvertedType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:44
   * @apilevel internal
   */
  public TypeDecl Define_targetType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:105
      int i = _callerNode.getIndexOfChild(_childNode);
      {
          MethodDecl decl = decl();
          if (decl.unknownMethod() == decl) {
            return decl.type().unknownType();
          }
      
          if (decl.isVariableArity() && i >= decl.arity() - 1) {
            return decl.getParameter(decl.arity() - 1).type().componentType();
          } else {
            return decl.getParameter(i).type();
          }
        }
    }
    else {
      return getParent().Define_targetType(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:44
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute targetType
   */
  protected boolean canDefine_targetType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:252
   * @apilevel internal
   */
  public boolean Define_assignmentContext(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:368
      int childIndex = _callerNode.getIndexOfChild(_childNode);
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
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:369
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return true;
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
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:370
      int childIndex = _callerNode.getIndexOfChild(_childNode);
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
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:371
      int childIndex = _callerNode.getIndexOfChild(_childNode);
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
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:372
      int childIndex = _callerNode.getIndexOfChild(_childNode);
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:98
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:67
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:150
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:496
    if (decl().isDeprecated()
              && !withinDeprecatedAnnotation()
              && hostType().topLevelType() != decl().hostType().topLevelType()
              && !withinSuppressWarnings("deprecation")) {
      {
        java.util.Set<ASTNode> contributors = _map.get(_root);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) _root, contributors);
        }
        contributors.add(this);
      }
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SafeVarargs.jrag:68
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Collection.jrag:16
    if (getID().equals("add") && decl().hostType().isList() && getArgList().getNumChild() == 1 && hasParentDot()
                && getArgList().getChild(0).hasDeclaration()) {
      for (InvocationTarget target : (Iterable<? extends InvocationTarget>) (enclosingMethods())) {
        java.util.Set<ASTNode> contributors = _map.get(target);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) target, contributors);
        }
        contributors.add(this);
      }
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Collection.jrag:21
    if (getID().equals("get") && decl().hostType().isList() && hasParentDot()) {
      for (InvocationTarget target : (Iterable<? extends InvocationTarget>) (enclosingMethods())) {
        java.util.Set<ASTNode> contributors = _map.get(target);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) target, contributors);
        }
        contributors.add(this);
      }
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Collection.jrag:37
    if (getID().equals("put") && decl().hostType().isMap() && hasParentDot()
                && getArgList().getChild(0).hasDeclaration() && getArgList().getChild(1).hasDeclaration()) {
      for (InvocationTarget target : (Iterable<? extends InvocationTarget>) (enclosingMethods())) {
        java.util.Set<ASTNode> contributors = _map.get(target);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) target, contributors);
        }
        contributors.add(this);
      }
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Collection.jrag:42
    if (getID().equals("get") && decl().hostType().isMap() && hasParentDot()) {
      for (InvocationTarget target : (Iterable<? extends InvocationTarget>) (enclosingMethods())) {
        java.util.Set<ASTNode> contributors = _map.get(target);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) target, contributors);
        }
        contributors.add(this);
      }
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:25
    for (InvocationTarget target : (Iterable<? extends InvocationTarget>) (enclosingMethods())) {
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:140
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
    for (Problem value : exceptionHandlingProblems()) {
      collection.add(value);
    }
    for (Problem value : typeHierarchyProblems()) {
      collection.add(value);
    }
    for (Problem value : typeProblems()) {
      collection.add(value);
    }
    if (decl().isDeprecated()
              && !withinDeprecatedAnnotation()
              && hostType().topLevelType() != decl().hostType().topLevelType()
              && !withinSuppressWarnings("deprecation")) {
      collection.add(warning(decl().signature() + " in " + decl().hostType().typeName() + " has been deprecated"));
    }
    for (Problem value : uncheckedWarnings()) {
      collection.add(value);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_InvocationTarget_inclusionEdges(Set<InclusionEdge> collection) {
    super.contributeTo_InvocationTarget_inclusionEdges(collection);
    if (getID().equals("add") && decl().hostType().isList() && getArgList().getNumChild() == 1 && hasParentDot()
                && getArgList().getChild(0).hasDeclaration()) {
      collection.add(new FieldLoadEdge(parentDot().leftmostDot().temp(), getArgList().getChild(0).getDeclaration(), "-COLLECTION_ACCESS"));
    }
    if (getID().equals("get") && decl().hostType().isList() && hasParentDot()) {
      collection.add(new FieldStoreEdge(parentDot().topDot().dotExpressionTemp(), parentDot().leftmostDot().temp(), "-COLLECTION_ACCESS"));
    }
    if (getID().equals("put") && decl().hostType().isMap() && hasParentDot()
                && getArgList().getChild(0).hasDeclaration() && getArgList().getChild(1).hasDeclaration()) {
      collection.add(new FieldLoadEdge(parentDot().leftmostDot().temp(), getArgList().getChild(1).getDeclaration(), "-COLLECTION_ACCESS"));
    }
    if (getID().equals("get") && decl().hostType().isMap() && hasParentDot()) {
      collection.add(new FieldStoreEdge(parentDot().topDot().dotExpressionTemp(), parentDot().leftmostDot().temp(), "-COLLECTION_ACCESS"));
    }
    for (InclusionEdge value : inclusionEdges()) {
      collection.add(value);
    }
    for (InclusionEdge value : allDecls()
                .stream()
                .map(decl -> new InclusionEdge(((MethodDecl) decl).getDeclaration(), getDeclaration()))
                .collect(Collectors.toSet())) {
      collection.add(value);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_InvocationTarget_calledMethods(Set<Invocable> collection) {
    super.contributeTo_InvocationTarget_calledMethods(collection);
    collection.add(this);
  }

}
