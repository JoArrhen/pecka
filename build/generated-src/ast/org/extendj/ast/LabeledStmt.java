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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:333
 * @astdecl LabeledStmt : BranchTargetStmt ::= <Label:String> Stmt;
 * @production LabeledStmt : {@link BranchTargetStmt} ::= <span class="component">&lt;Label:{@link String}&gt;</span> <span class="component">{@link Stmt}</span>;

 */
public class LabeledStmt extends BranchTargetStmt implements Cloneable {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrint.jadd:433
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(getLabel());
    out.print(":");
    out.println();
    out.print(getStmt());
  }
  /**
   * @declaredat ASTNode:1
   */
  public LabeledStmt() {
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
    name = {"Label", "Stmt"},
    type = {"String", "Stmt"},
    kind = {"Token", "Child"}
  )
  public LabeledStmt(String p0, Stmt p1) {
    setLabel(p0);
    setChild(p1, 0);
  }
  /**
   * @declaredat ASTNode:22
   */
  public LabeledStmt(beaver.Symbol p0, Stmt p1) {
    setLabel(p0);
    setChild(p1, 0);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:27
   */
  protected int numChildren() {
    return 1;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:33
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:37
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    canCompleteNormally_reset();
    assignedAfter_Variable_reset();
    unassignedAfter_Variable_reset();
    lookupLabel_String_reset();
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
  public LabeledStmt clone() throws CloneNotSupportedException {
    LabeledStmt node = (LabeledStmt) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:55
   */
  public LabeledStmt copy() {
    try {
      LabeledStmt node = (LabeledStmt) clone();
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
  public LabeledStmt fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:84
   */
  public LabeledStmt treeCopyNoTransform() {
    LabeledStmt tree = (LabeledStmt) copy();
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
  public LabeledStmt treeCopy() {
    LabeledStmt tree = (LabeledStmt) copy();
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
   * Replaces the lexeme Label.
   * @param value The new value for the lexeme Label.
   * @apilevel high-level
   */
  public LabeledStmt setLabel(String value) {
    tokenString_Label = value;
    return this;
  }
  /** @apilevel internal 
   */
  protected String tokenString_Label;
  /**
   */
  public int Labelstart;
  /**
   */
  public int Labelend;
  /**
   * JastAdd-internal setter for lexeme Label using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme Label
   * @apilevel internal
   */
  public LabeledStmt setLabel(beaver.Symbol symbol) {
    if (symbol.value != null && !(symbol.value instanceof String))
    throw new UnsupportedOperationException("setLabel is only valid for String lexemes");
    tokenString_Label = (String)symbol.value;
    Labelstart = symbol.getStart();
    Labelend = symbol.getEnd();
    return this;
  }
  /**
   * Retrieves the value for the lexeme Label.
   * @return The value for the lexeme Label.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Token(name="Label")
  public String getLabel() {
    return tokenString_Label != null ? tokenString_Label : "";
  }
  /**
   * Replaces the Stmt child.
   * @param node The new node to replace the Stmt child.
   * @apilevel high-level
   */
  public LabeledStmt setStmt(Stmt node) {
    setChild(node, 0);
    return this;
  }
  /**
   * Retrieves the Stmt child.
   * @return The current node used as the Stmt child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Stmt")
  public Stmt getStmt() {
    return (Stmt) getChild(0);
  }
  /**
   * Retrieves the Stmt child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Stmt child.
   * @apilevel low-level
   */
  public Stmt getStmtNoTransform() {
    return (Stmt) getChildNoTransform(0);
  }
  /** @apilevel internal */
  private void canCompleteNormally_reset() {
    canCompleteNormally_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle canCompleteNormally_computed = null;

  /** @apilevel internal */
  protected boolean canCompleteNormally_value;

  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/UnreachableStatements.jrag:81
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="UnreachableStatements", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/UnreachableStatements.jrag:50")
  public boolean canCompleteNormally() {
    ASTState state = state();
    if (canCompleteNormally_computed == ASTState.NON_CYCLE || canCompleteNormally_computed == state().cycle()) {
      return canCompleteNormally_value;
    }
    canCompleteNormally_value = getStmt().canCompleteNormally() || reachableBreak();
    if (state().inCircle()) {
      canCompleteNormally_computed = state().cycle();
    
    } else {
      canCompleteNormally_computed = ASTState.NON_CYCLE;
    
    }
    return canCompleteNormally_value;
  }

  /** @apilevel internal */
  private void assignedAfter_Variable_reset() {
    assignedAfter_Variable_values = null;
  }
  protected java.util.Map assignedAfter_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:264")
  public boolean assignedAfter(Variable v) {
    Object _parameters = v;


    if (assignedAfter_Variable_values == null) assignedAfter_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (assignedAfter_Variable_values.containsKey(_parameters)) {
      Object _cache = assignedAfter_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      assignedAfter_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_assignedAfter_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_assignedAfter_Variable_value = assignedAfter_compute(v);
        if (((Boolean)_value.value) != new_assignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_assignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      assignedAfter_Variable_values.put(_parameters, new_assignedAfter_Variable_value);
      state.startLastCycle();
      boolean $tmp = assignedAfter_compute(v);

      state.leaveCircle();
      return new_assignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_assignedAfter_Variable_value = assignedAfter_compute(v);
      if (state.lastCycle()) {
        assignedAfter_Variable_values.put(_parameters, new_assignedAfter_Variable_value);
      }
      if (((Boolean)_value.value) != new_assignedAfter_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_assignedAfter_Variable_value;
      }
      return new_assignedAfter_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /** @apilevel internal */
  private boolean assignedAfter_compute(Variable v) {
      if (!getStmt().assignedAfter(v)) {
        return false;
      }
      for (BreakStmt stmt : targetBreaks()) {
        if (!stmt.assignedAfterReachedFinallyBlocks(v)) {
          return false;
        }
      }
      return true;
    }

  /** @apilevel internal */
  private void unassignedAfter_Variable_reset() {
    unassignedAfter_Variable_values = null;
  }
  protected java.util.Map unassignedAfter_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:895")
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
        new_unassignedAfter_Variable_value = unassignedAfter_compute(v);
        if (((Boolean)_value.value) != new_unassignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfter_Variable_values.put(_parameters, new_unassignedAfter_Variable_value);
      state.startLastCycle();
      boolean $tmp = unassignedAfter_compute(v);

      state.leaveCircle();
      return new_unassignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfter_Variable_value = unassignedAfter_compute(v);
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
  private boolean unassignedAfter_compute(Variable v) {
      if (!getStmt().unassignedAfter(v)) {
        return false;
      }
      for (BreakStmt stmt : targetBreaks()) {
        if (!stmt.unassignedAfterReachedFinallyBlocks(v)) {
          return false;
        }
      }
      return true;
    }
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:535
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:535")
  public Collection<Problem> nameProblems() {
    {
        LabeledStmt stmt = lookupLabel(getLabel());
        if (stmt != null) {
          if (stmt.enclosingBodyDecl() == enclosingBodyDecl()) {
            return Collections.singletonList(error("Labels cannot shadow labels in the same member"));
          }
        }
        return Collections.emptyList();
      }
  }
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:217
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="BranchTarget", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:215")
  public boolean potentialTargetOf(Stmt branch) {
    boolean potentialTargetOf_Stmt_value = branch.canBranchTo(this);
    return potentialTargetOf_Stmt_value;
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:96
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:78")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = getStmt().modifiedInScope(var);
    return modifiedInScope_Variable_value;
  }
  /** Lookup visible label. 
   * @attribute inh
   * @aspect BranchTarget
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:258
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="BranchTarget", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:258")
  public LabeledStmt lookupLabel(String name) {
    Object _parameters = name;
    if (lookupLabel_String_computed == null) lookupLabel_String_computed = new java.util.HashMap(4);
    if (lookupLabel_String_values == null) lookupLabel_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (lookupLabel_String_values.containsKey(_parameters)
        && lookupLabel_String_computed.containsKey(_parameters)
        && (lookupLabel_String_computed.get(_parameters) == ASTState.NON_CYCLE || lookupLabel_String_computed.get(_parameters) == state().cycle())) {
      return (LabeledStmt) lookupLabel_String_values.get(_parameters);
    }
    LabeledStmt lookupLabel_String_value = getParent().Define_lookupLabel(this, null, name);
    if (state().inCircle()) {
      lookupLabel_String_values.put(_parameters, lookupLabel_String_value);
      lookupLabel_String_computed.put(_parameters, state().cycle());
    
    } else {
      lookupLabel_String_values.put(_parameters, lookupLabel_String_value);
      lookupLabel_String_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return lookupLabel_String_value;
  }
  /** @apilevel internal */
  private void lookupLabel_String_reset() {
    lookupLabel_String_computed = null;
    lookupLabel_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map lookupLabel_String_values;
  /** @apilevel internal */
  protected java.util.Map lookupLabel_String_computed;
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/UnreachableStatements.jrag:49
   * @apilevel internal
   */
  public boolean Define_reachable(ASTNode _callerNode, ASTNode _childNode) {
    if (getStmtNoTransform() != null && _callerNode == getStmt()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/UnreachableStatements.jrag:83
      return reachable();
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (getStmtNoTransform() != null && _callerNode == getStmt()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:660
      return assignedBefore(v);
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
    if (getStmtNoTransform() != null && _callerNode == getStmt()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:1207
      return unassignedBefore(v);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:230
   * @apilevel internal
   */
  public Stmt Define_branchTarget(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return branch.canBranchTo(this) ? this : branchTarget(branch);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:255
   * @apilevel internal
   */
  public LabeledStmt Define_lookupLabel(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (getStmtNoTransform() != null && _callerNode == getStmt()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:260
      return name.equals(getLabel()) ? this : lookupLabel(name);
    }
    else {
      return getParent().Define_lookupLabel(this, _callerNode, name);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BranchTarget.jrag:255
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupLabel
   */
  protected boolean canDefine_lookupLabel(ASTNode _callerNode, ASTNode _childNode, String name) {
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:533
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
