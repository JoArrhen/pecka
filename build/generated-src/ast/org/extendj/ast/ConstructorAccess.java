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
/** A constructor invocation. 
 * @ast node
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:93
 * @astdecl ConstructorAccess : Access ::= <ID:String> Arg:Expr*;
 * @production ConstructorAccess : {@link Access} ::= <span class="component">&lt;ID:{@link String}&gt;</span> <span class="component">Arg:{@link Expr}*</span>;

 */
public class ConstructorAccess extends Access implements Cloneable {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrint.jadd:248
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:356
   */
  protected boolean reachedException(TypeDecl catchType) {
    for (Access exception : decl().getExceptionList()) {
      TypeDecl exceptionType = exception.type();
      if (catchType.mayCatch(exceptionType)) {
        return true;
      }
    }
    return super.reachedException(catchType);
  }
  /**
   * @declaredat ASTNode:1
   */
  public ConstructorAccess() {
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
  public ConstructorAccess(String p0, List<Expr> p1) {
    setID(p0);
    setChild(p1, 0);
  }
  /**
   * @declaredat ASTNode:23
   */
  public ConstructorAccess(beaver.Symbol p0, List<Expr> p1) {
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
    decl_reset();
    unassignedAfter_Variable_reset();
    type_reset();
    stmtCompatible_reset();
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
  public ConstructorAccess clone() throws CloneNotSupportedException {
    ConstructorAccess node = (ConstructorAccess) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:57
   */
  public ConstructorAccess copy() {
    try {
      ConstructorAccess node = (ConstructorAccess) clone();
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
  public ConstructorAccess fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:86
   */
  public ConstructorAccess treeCopyNoTransform() {
    ConstructorAccess tree = (ConstructorAccess) copy();
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
  public ConstructorAccess treeCopy() {
    ConstructorAccess tree = (ConstructorAccess) copy();
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
  public ConstructorAccess setID(String value) {
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
  public ConstructorAccess setID(beaver.Symbol symbol) {
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
  public ConstructorAccess setArgList(List<Expr> list) {
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
  public ConstructorAccess addArg(Expr node) {
    List<Expr> list = (parent == null) ? getArgListNoTransform() : getArgList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public ConstructorAccess addArgNoTransform(Expr node) {
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
  public ConstructorAccess setArg(Expr node, int i) {
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
   * @attribute syn
   * @aspect ConstructScope
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:87
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:85")
  public boolean applicableAndAccessible(ConstructorDecl decl) {
    boolean applicableAndAccessible_ConstructorDecl_value = decl.applicable(getArgList()) && decl.accessibleFrom(hostType());
    return applicableAndAccessible_ConstructorDecl_value;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:93
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:94")
  public SimpleSet<ConstructorDecl> decls() {
    ASTState state = state();
    if (decls_computed == ASTState.NON_CYCLE || decls_computed == state().cycle()) {
      return decls_value;
    }
    decls_value = chooseConstructor(lookupConstructor(), getArgList());
    if (state().inCircle()) {
      decls_computed = state().cycle();
    
    } else {
      decls_computed = ASTState.NON_CYCLE;
    
    }
    return decls_value;
  }
  /** @apilevel internal */
  private void decl_reset() {
    decl_computed = null;
    decl_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle decl_computed = null;

  /** @apilevel internal */
  protected ConstructorDecl decl_value;

  /**
   * @attribute syn
   * @aspect ConstructScope
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:102
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:102")
  public ConstructorDecl decl() {
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
  private ConstructorDecl decl_compute() {
      SimpleSet<ConstructorDecl> decls = decls();
      if (decls.isSingleton()) {
        return decls.singletonValue();
      }
      return unknownConstructor();
    }
  /**
   * @attribute syn
   * @aspect ExceptionHandling
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:152
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:152")
  public Collection<Problem> exceptionHandlingProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        for (Access exception : decl().getExceptionList()) {
          TypeDecl exceptionType = exception.type();
          if (exceptionType.isCheckedException() && !handlesException(exceptionType)) {
            problems.add(errorf("%s may throw uncaught exception %s",
                this.prettyPrint(), exceptionType.fullName()));
          }
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect DefiniteAssignment
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:341
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:268")
  public boolean assignedAfter(Variable v) {
    boolean assignedAfter_Variable_value = v.isField();
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
        new_unassignedAfter_Variable_value = !v.isField();
        if (((Boolean)_value.value) != new_unassignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfter_Variable_values.put(_parameters, new_unassignedAfter_Variable_value);
      state.startLastCycle();
      boolean $tmp = !v.isField();

      state.leaveCircle();
      return new_unassignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfter_Variable_value = !v.isField();
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
   * @aspect NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:177
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:177")
  public Collection<Problem> nameProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        ConstructorDecl decl = (ConstructorDecl) enclosingBodyDecl();
        if (((ExprStmt) decl.getConstructorInvocation()).getExpr() == this) {
          // Don't error-check the parsed constructor invocation in case it is not the used one.
          if (decls().isEmpty()) {
            problems.add(errorf("no constructor matches %s", this.prettyPrint()));
          } else if (decls().size() > 1 && validArgs()) {
            problems.add(errorf("several most specific constructors for %s", this.prettyPrint()));
            for (ConstructorDecl cons : decls()) {
              problems.add(errorf("         %s", cons.signature()));
            }
          }
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:194
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:194")
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:148
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SyntacticClassification", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:60")
  public NameType predNameType() {
    NameType predNameType_value = NameType.AMBIGUOUS_NAME;
    return predNameType_value;
  }
  /**
   * @attribute syn
   * @aspect Names
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/QualifiedNames.jrag:37
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Names", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/QualifiedNames.jrag:37")
  public String name() {
    String name_value = "this";
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
   * @aspect TypeAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:306
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:294")
  public TypeDecl type() {
    ASTState state = state();
    if (type_computed == ASTState.NON_CYCLE || type_computed == state().cycle()) {
      return type_value;
    }
    type_value = decl().type();
    if (state().inCircle()) {
      type_computed = state().cycle();
    
    } else {
      type_computed = ASTState.NON_CYCLE;
    
    }
    return type_value;
  }
  /**
   * @attribute syn
   * @aspect VariableArityParameters
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:75
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableArityParameters", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:75")
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:696
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:696")
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:146
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
  /**
   * @attribute syn
   * @aspect MethodCall
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:75
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodCall", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:75")
  public Set<InclusionEdge> inclusionEdges() {
    {
            Set<InclusionEdge> res = new HashSet<>();
            List<Expr> arguments = getArgList();
    
            for (InvocationTarget it : decls()) {
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
  /**
   * @attribute inh
   * @aspect ConstructScope
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:35
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ConstructScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:35")
  public Collection<ConstructorDecl> lookupConstructor() {
    Collection<ConstructorDecl> lookupConstructor_value = getParent().Define_lookupConstructor(this, null);
    return lookupConstructor_value;
  }
  /**
   * @attribute inh
   * @aspect ConstructScope
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:110
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ConstructScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:110")
  public ConstructorDecl unknownConstructor() {
    ConstructorDecl unknownConstructor_value = getParent().Define_unknownConstructor(this, null);
    return unknownConstructor_value;
  }
  /**
   * @attribute inh
   * @aspect ExceptionHandling
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:89
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:89")
  public boolean handlesException(TypeDecl exceptionType) {
    boolean handlesException_TypeDecl_value = getParent().Define_handlesException(this, null, exceptionType);
    return handlesException_TypeDecl_value;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:204
   * @apilevel internal
   */
  public boolean Define_inExplicitConstructorInvocation(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:207
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return true;
    }
    else {
      return getParent().Define_inExplicitConstructorInvocation(this, _callerNode);
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
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:216
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return hostType();
    }
    else {
      return getParent().Define_enclosingExplicitConstructorHostType(this, _callerNode);
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
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:116
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
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:407
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
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:242
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return unqualifiedScope().lookupVariable(name);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:140
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
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:130
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:44
   * @apilevel internal
   */
  public TypeDecl Define_targetType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:118
      int i = _callerNode.getIndexOfChild(_childNode);
      {
          ConstructorDecl decl = decl();
          if (unknownConstructor() == decl) {
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
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:374
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
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:375
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
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:376
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
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:377
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
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:378
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:150
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:175
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:513
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
    super.collect_contributors_CompilationUnit_problems(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_InvocationTarget_inclusionEdges(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:97
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
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    for (Problem value : exceptionHandlingProblems()) {
      collection.add(value);
    }
    for (Problem value : nameProblems()) {
      collection.add(value);
    }
    if (decl().isDeprecated()
              && !withinDeprecatedAnnotation()
              && hostType().topLevelType() != decl().hostType().topLevelType()
              && !withinSuppressWarnings("deprecation")) {
      collection.add(warning(decl().signature() + " in " + decl().hostType().typeName() + " has been deprecated"));
    }
  }
  /** @apilevel internal */
  protected void contributeTo_InvocationTarget_inclusionEdges(Set<InclusionEdge> collection) {
    super.contributeTo_InvocationTarget_inclusionEdges(collection);
    for (InclusionEdge value : inclusionEdges()) {
      collection.add(value);
    }
  }

}
