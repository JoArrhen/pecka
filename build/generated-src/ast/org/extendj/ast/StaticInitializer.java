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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:188
 * @astdecl StaticInitializer : BodyDecl ::= Block;
 * @production StaticInitializer : {@link BodyDecl} ::= <span class="component">{@link Block}</span>;

 */
public class StaticInitializer extends BodyDecl implements Cloneable, InvocationTarget {
  /**
   * @aspect CallGraphWithRAGS
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:96
   */
  public
  int sccID = -1;
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrint.jadd:556
   */
  public void prettyPrint(PrettyPrinter out) {
    if (!blockIsEmpty()) {
      out.print("static ");
      out.print(getBlock());
    }
  }
  /**
   * @aspect CG2JSON
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cg2json.jrag:94
   */
  public
  void nodes2JSON(PrintStream out) {

    out.println("      \"methodName\": \"" + targetSignature() + "\",");
    out.println("      \"sccId\": " + getSCCID() + ",");
    out.println("      \"uniqueSCCAndNoSelfLoop\": " + !inSCC() + ",");
    out.println("      \"path\": \"" + enclosingCompilationUnit().pathName() +
                ":" + getLine(getStart()) + "\",");
    out.println("      \"bridge\": false,");
    out.println("      \"paramTypes\": {");
    boolean isFirstParam = true;
    for (Map.Entry<String, String> entry : paramTypes().entrySet()) {
      if (!isFirstParam) {
        out.print(",\n");
      }
      out.print("        \"");
      out.print(entry.getKey());
      out.print("\": \"");
      out.print(entry.getValue());
      out.print("\"");
      isFirstParam = false;
    }
    out.print("\n      },\n");
    out.println("      \"kind\": [");
    for (int i = 0; i < getKindOfAttribute().size(); i++) {
      out.println("        \"" + getKindOfAttribute().get(i) + "\"" +
                  (i < getKindOfAttribute().size() - 1 ? "," : ""));
    }
    out.println("      ]");
  }
  /**
   * @aspect CG2JSON
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cg2json.jrag:124
   */
  public
  void edges2JSON(PrintStream out, boolean first,
                                   boolean forward) {
    for (InvocationTarget neighbor : (forward ? cg() : reversedCG())) {
      out.println((first ? "    " : "   ,") + "    {");
      if (first) {
        first = false;
      }
      out.println("      \"source\": \"" + targetSignature() + "\",");
      out.println("      \"target\": \"" + neighbor.targetSignature() + "\"");
      out.println("    }");
    }
  }
  /**
   * @aspect CallGraphWithRAGS
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:67
   */
  public
  void processAttributeDeclarations(
      Set<InvocationTarget> declarations, Set<InvocationTarget> callGraph) {
    for (InvocationTarget declaration : declarations) {
      if (declaration.shouldBeConsiderAsMethod()) {
        callGraph.add(declaration);
      } else {
        callGraph.addAll(declaration.cg());
      }
    }
  }
  /**
   * @aspect CallGraphWithRAGS
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:79
   */
  public
  void processAllDeclarations(
      Set<InvocationTarget> declarations, Set<InvocationTarget> callGraph) {
    for (InvocationTarget declaration : declarations) {
      callGraph.add(declaration);
    }
  }
  /**
   * @aspect CallGraphWithRAGS
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:102
   */
  public
  int computeSCCs() {
    Stack<InvocationTarget> stack = new Stack<>();
    Set<InvocationTarget> visited = new LinkedHashSet<>();
    Set<InvocationTarget> scc = new LinkedHashSet<>();
    scc.add(this);
    scc.addAll(cg());

    for (InvocationTarget node : scc) {
      if (!visited.contains(node)) {
        node.dfs(stack, visited);
      }
    }

    visited.clear();
    while (!stack.isEmpty()) {
      InvocationTarget node = stack.pop();
      if (!visited.contains(node)) {
        node.dfs(visited, program().currentSccId);
        program().currentSccId++;
      }
    }

    return this.sccID;
  }
  /**
   * @aspect CallGraphWithRAGS
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:128
   */
  public
  void dfs(Stack<InvocationTarget> stack,
                            Set<InvocationTarget> visited) {
    visited.add(this);

    for (InvocationTarget neighbor : cg()) {
      if (!visited.contains(neighbor)) {
        neighbor.dfs(stack, visited);
      }
    }

    stack.push(this);
  }
  /**
   * @aspect CallGraphWithRAGS
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:142
   */
  public
  void dfs(Set<InvocationTarget> visited, int currentSccId) {
    visited.add(this);
    sccID = currentSccId;

    for (InvocationTarget neighbor : reversedCG()) {
      if (!visited.contains(neighbor)) {
        neighbor.dfs(visited, currentSccId);
      }
    }
  }
  /**
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:451
   */
  public
  boolean hasAttributeInModifiers(
      Iterable<Modifier> modifiers, Predicate<Modifier> attributeCheck) {
    for (Modifier m : modifiers) {
      if (attributeCheck.test(m)) {
        return true;
      }
    }
    return false;
  }
  /**
   * @declaredat ASTNode:1
   */
  public StaticInitializer() {
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
  public StaticInitializer(Block p0) {
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
    targetSignature_reset();
    isStaticInitializer_reset();
    assignedAfter_Variable_reset();
    unassignedAfter_Variable_reset();
    reversedCG_reset();
    cg_reset();
    completeCallGraph_reset();
    getSCCID_reset();
    returnType_reset();
    paramTypes_reset();
    shouldBeConsiderAsMethod_reset();
    implicitCalls_reset();
    getContribution_reset();
    isAnAttribute_reset();
    isSynAttribute_reset();
    isInhAttribute_reset();
    isCircularAttribute_reset();
    isCollectionAttribute_reset();
    getKindOfAttribute_reset();
    withinDistance_int_reset();
    reachable_reset();
    backwardsActive_reset();
    kBoundedBackwardsActive_int_reset();
    forwardsActive_Set_InvocationTarget__reset();
    activePointsToEdges_reset();
    activeInclusionEdges_reset();
    selectedMethods_reset();
    pointsToSet_reset();
    handlesException_TypeDecl_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:65
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
    InvocationTarget_backwardCG_computed = null;
    InvocationTarget_backwardCG_value = null;
    InvocationTarget_calledMethods_computed = null;
    InvocationTarget_calledMethods_value = null;
    InvocationTarget_calledIn_computed = null;
    InvocationTarget_calledIn_value = null;
    InvocationTarget_pointsToEdges_computed = null;
    InvocationTarget_pointsToEdges_value = null;
    InvocationTarget_inclusionEdges_computed = null;
    InvocationTarget_inclusionEdges_value = null;
    InvocationTarget__accessedTypeConstructors_computed = null;
    InvocationTarget__accessedTypeConstructors_value = null;
    InvocationTarget_pointers_computed = null;
    InvocationTarget_pointers_value = null;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:83
   */
  public StaticInitializer clone() throws CloneNotSupportedException {
    StaticInitializer node = (StaticInitializer) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:88
   */
  public StaticInitializer copy() {
    try {
      StaticInitializer node = (StaticInitializer) clone();
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
   * @declaredat ASTNode:107
   */
  @Deprecated
  public StaticInitializer fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:117
   */
  public StaticInitializer treeCopyNoTransform() {
    StaticInitializer tree = (StaticInitializer) copy();
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
   * @declaredat ASTNode:137
   */
  public StaticInitializer treeCopy() {
    StaticInitializer tree = (StaticInitializer) copy();
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
  public StaticInitializer setBlock(Block node) {
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
  private void targetSignature_reset() {
    targetSignature_computed = null;
    targetSignature_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle targetSignature_computed = null;

  /** @apilevel internal */
  protected String targetSignature_value;

  /**
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:293
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:276")
  public String targetSignature() {
    ASTState state = state();
    if (targetSignature_computed == ASTState.NON_CYCLE || targetSignature_computed == state().cycle()) {
      return targetSignature_value;
    }
    targetSignature_value = hostType().packageName() + "." + hostType().name() + "::clinit<>";
    if (state().inCircle()) {
      targetSignature_computed = state().cycle();
    
    } else {
      targetSignature_computed = ASTState.NON_CYCLE;
    
    }
    return targetSignature_value;
  }
  /** @apilevel internal */
  private void isStaticInitializer_reset() {
    isStaticInitializer_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isStaticInitializer_computed = null;

  /** @apilevel internal */
  protected boolean isStaticInitializer_value;

  /**
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:369
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:368")
  public boolean isStaticInitializer() {
    ASTState state = state();
    if (isStaticInitializer_computed == ASTState.NON_CYCLE || isStaticInitializer_computed == state().cycle()) {
      return isStaticInitializer_value;
    }
    isStaticInitializer_value = true;
    if (state().inCircle()) {
      isStaticInitializer_computed = state().cycle();
    
    } else {
      isStaticInitializer_computed = ASTState.NON_CYCLE;
    
    }
    return isStaticInitializer_value;
  }

  /** @apilevel internal */
  private void assignedAfter_Variable_reset() {
    assignedAfter_Variable_values = null;
  }
  protected java.util.Map assignedAfter_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:272")
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
        new_assignedAfter_Variable_value = getBlock().assignedAfter(v);
        if (((Boolean)_value.value) != new_assignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_assignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      assignedAfter_Variable_values.put(_parameters, new_assignedAfter_Variable_value);
      state.startLastCycle();
      boolean $tmp = getBlock().assignedAfter(v);

      state.leaveCircle();
      return new_assignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_assignedAfter_Variable_value = getBlock().assignedAfter(v);
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
  private void unassignedAfter_Variable_reset() {
    unassignedAfter_Variable_values = null;
  }
  protected java.util.Map unassignedAfter_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:911")
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
        new_unassignedAfter_Variable_value = getBlock().unassignedAfter(v);
        if (((Boolean)_value.value) != new_unassignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfter_Variable_values.put(_parameters, new_unassignedAfter_Variable_value);
      state.startLastCycle();
      boolean $tmp = getBlock().unassignedAfter(v);

      state.leaveCircle();
      return new_unassignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfter_Variable_value = getBlock().unassignedAfter(v);
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
   * @aspect PrettyPrintUtil
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:304
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PrettyPrintUtil", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:304")
  public boolean blockIsEmpty() {
    boolean blockIsEmpty_value = getBlock().getNumStmt() == 0;
    return blockIsEmpty_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsParTypeDecl.jrag:101
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsParTypeDecl.jrag:98")
  public boolean visibleTypeParameters() {
    boolean visibleTypeParameters_value = false;
    return visibleTypeParameters_value;
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:66
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:40")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = getBlock().modifiedInScope(var);
    return modifiedInScope_Variable_value;
  }
  /**
   * @attribute syn
   * @aspect CallGraphAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/CallGraphAnalysis.jrag:32
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraphAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/CallGraphAnalysis.jrag:32")
  public boolean inSCC() {
    {
        Set<InvocationTarget> neighbors = new HashSet<InvocationTarget>();
        neighbors.addAll(cg());         // Adding successors
        neighbors.addAll(reversedCG()); // Adding predecessors
        if (neighbors.contains(this))
          return true; // Self-loop
        for (InvocationTarget neighbor : neighbors) {
          if (neighbor.getSCCID() ==
              this.getSCCID()) { // If at least one neighbor is in the same SCC
            return true;
          }
        }
        return false;
      }
  }
  /**
   * @attribute syn
   * @aspect AllMethods
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:36
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AllMethods", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:36")
  public boolean visible() {
    boolean visible_value = false;
    return visible_value;
  }
  /**
   * @attribute syn
   * @aspect AllMethods
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:67
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AllMethods", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:67")
  public String methodName() {
    {
        String res = "";
        try {
          res = targetSignature().substring(targetSignature().indexOf("::") + 2,
                                            targetSignature().indexOf("("));
        } catch (Exception e) {
        }
        return res;
      }
  }
  /**
   * @attribute syn
   * @aspect AllMethods
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:76
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AllMethods", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:76")
  public String packageName() {
    String packageName_value = hostType().packageName() + "." + hostType().name();
    return packageName_value;
  }
  /**
   * @attribute syn
   * @aspect AllMethods
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:78
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AllMethods", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:78")
  public int lineStart() {
    int lineStart_value = getLine(getStart());
    return lineStart_value;
  }
  /** @apilevel internal */
  private void reversedCG_reset() {
    reversedCG_computed = null;
    reversedCG_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle reversedCG_computed = null;

  /** @apilevel internal */
  protected Set<InvocationTarget> reversedCG_value;

  /**
   * @attribute syn
   * @aspect CallGraphWithRAGS
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:41
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraphWithRAGS", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:41")
  public Set<InvocationTarget> reversedCG() {
    ASTState state = state();
    if (reversedCG_computed == ASTState.NON_CYCLE || reversedCG_computed == state().cycle()) {
      return reversedCG_value;
    }
    reversedCG_value = reversedCG_compute();
    if (state().inCircle()) {
      reversedCG_computed = state().cycle();
    
    } else {
      reversedCG_computed = ASTState.NON_CYCLE;
    
    }
    return reversedCG_value;
  }
  /** @apilevel internal */
  private Set<InvocationTarget> reversedCG_compute() {
      return backwardCG();
    }
/** @apilevel internal */
protected ASTState.Cycle cg_cycle = null;

  /** @apilevel internal */
  private void cg_reset() {
    cg_computed = false;
    cg_initialized = false;
    cg_value = null;
    cg_cycle = null;
  }
  /** @apilevel internal */
  protected boolean cg_computed = false;

  /** @apilevel internal */
  protected Set<InvocationTarget> cg_value;
  /** @apilevel internal */
  protected boolean cg_initialized = false;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="CallGraphWithRAGS", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:45")
  public Set<InvocationTarget> cg() {
    if (cg_computed) {
      return cg_value;
    }
    ASTState state = state();
    if (!cg_initialized) {
      cg_initialized = true;
      cg_value = new LinkedHashSet<>();
    }
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      do {
        cg_cycle = state.nextCycle();
        Set<InvocationTarget> new_cg_value = cg_compute();
        if (!AttributeValue.equals(cg_value, new_cg_value)) {
          state.setChangeInCycle();
        }
        cg_value = new_cg_value;
      } while (state.testAndClearChangeInCycle());
      cg_computed = true;
      state.startLastCycle();
      Set<InvocationTarget> $tmp = cg_compute();

      state.leaveCircle();
    } else if (cg_cycle != state.cycle()) {
      cg_cycle = state.cycle();
      if (state.lastCycle()) {
        cg_computed = true;
        Set<InvocationTarget> new_cg_value = cg_compute();
        return new_cg_value;
      }
      Set<InvocationTarget> new_cg_value = cg_compute();
      if (!AttributeValue.equals(cg_value, new_cg_value)) {
        state.setChangeInCycle();
      }
      cg_value = new_cg_value;
    } else {
    }
    return cg_value;
  }
  /** @apilevel internal */
  private Set<InvocationTarget> cg_compute() {
      Set<InvocationTarget> cg = new LinkedHashSet<>();
      for (Invocable m : calledMethods()) {
        processAttributeDeclarations(m.allDecls(), cg);
      }
      processAttributeDeclarations(implicitCalls(), cg);
  
      return cg;
    }
/** @apilevel internal */
protected ASTState.Cycle completeCallGraph_cycle = null;

  /** @apilevel internal */
  private void completeCallGraph_reset() {
    completeCallGraph_computed = false;
    completeCallGraph_initialized = false;
    completeCallGraph_value = null;
    completeCallGraph_cycle = null;
  }
  /** @apilevel internal */
  protected boolean completeCallGraph_computed = false;

  /** @apilevel internal */
  protected Set<InvocationTarget> completeCallGraph_value;
  /** @apilevel internal */
  protected boolean completeCallGraph_initialized = false;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="CallGraphWithRAGS", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:56")
  public Set<InvocationTarget> completeCallGraph() {
    if (completeCallGraph_computed) {
      return completeCallGraph_value;
    }
    ASTState state = state();
    if (!completeCallGraph_initialized) {
      completeCallGraph_initialized = true;
      completeCallGraph_value = new LinkedHashSet<>();
    }
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      do {
        completeCallGraph_cycle = state.nextCycle();
        Set<InvocationTarget> new_completeCallGraph_value = completeCallGraph_compute();
        if (!AttributeValue.equals(completeCallGraph_value, new_completeCallGraph_value)) {
          state.setChangeInCycle();
        }
        completeCallGraph_value = new_completeCallGraph_value;
      } while (state.testAndClearChangeInCycle());
      completeCallGraph_computed = true;
      state.startLastCycle();
      Set<InvocationTarget> $tmp = completeCallGraph_compute();

      state.leaveCircle();
    } else if (completeCallGraph_cycle != state.cycle()) {
      completeCallGraph_cycle = state.cycle();
      if (state.lastCycle()) {
        completeCallGraph_computed = true;
        Set<InvocationTarget> new_completeCallGraph_value = completeCallGraph_compute();
        return new_completeCallGraph_value;
      }
      Set<InvocationTarget> new_completeCallGraph_value = completeCallGraph_compute();
      if (!AttributeValue.equals(completeCallGraph_value, new_completeCallGraph_value)) {
        state.setChangeInCycle();
      }
      completeCallGraph_value = new_completeCallGraph_value;
    } else {
    }
    return completeCallGraph_value;
  }
  /** @apilevel internal */
  private Set<InvocationTarget> completeCallGraph_compute() {
      Set<InvocationTarget> cg = new LinkedHashSet<>();
      for (Invocable m : calledMethods()) {
        processAllDeclarations(m.allDecls(), cg);
      }
      processAllDeclarations(implicitCalls(), cg);
  
      return cg;
    }
  /** @apilevel internal */
  private void getSCCID_reset() {
    getSCCID_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle getSCCID_computed = null;

  /** @apilevel internal */
  protected int getSCCID_value;

  /**
   * @attribute syn
   * @aspect CallGraphWithRAGS
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:99
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraphWithRAGS", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:99")
  public int getSCCID() {
    ASTState state = state();
    if (getSCCID_computed == ASTState.NON_CYCLE || getSCCID_computed == state().cycle()) {
      return getSCCID_value;
    }
    getSCCID_value = (sccID == -1) ? computeSCCs() : sccID;
    if (state().inCircle()) {
      getSCCID_computed = state().cycle();
    
    } else {
      getSCCID_computed = ASTState.NON_CYCLE;
    
    }
    return getSCCID_value;
  }
  /** @apilevel internal */
  private void returnType_reset() {
    returnType_computed = null;
    returnType_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle returnType_computed = null;

  /** @apilevel internal */
  protected String returnType_value;

  /**
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:216
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:216")
  public String returnType() {
    ASTState state = state();
    if (returnType_computed == ASTState.NON_CYCLE || returnType_computed == state().cycle()) {
      return returnType_value;
    }
    returnType_value = "";
    if (state().inCircle()) {
      returnType_computed = state().cycle();
    
    } else {
      returnType_computed = ASTState.NON_CYCLE;
    
    }
    return returnType_value;
  }
  /** @apilevel internal */
  private void paramTypes_reset() {
    paramTypes_computed = null;
    paramTypes_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle paramTypes_computed = null;

  /** @apilevel internal */
  protected Map<String, String> paramTypes_value;

  /**
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:219
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:219")
  public Map<String, String> paramTypes() {
    ASTState state = state();
    if (paramTypes_computed == ASTState.NON_CYCLE || paramTypes_computed == state().cycle()) {
      return paramTypes_value;
    }
    paramTypes_value = new LinkedHashMap<>();
    if (state().inCircle()) {
      paramTypes_computed = state().cycle();
    
    } else {
      paramTypes_computed = ASTState.NON_CYCLE;
    
    }
    return paramTypes_value;
  }
  /** @apilevel internal */
  private void shouldBeConsiderAsMethod_reset() {
    shouldBeConsiderAsMethod_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle shouldBeConsiderAsMethod_computed = null;

  /** @apilevel internal */
  protected boolean shouldBeConsiderAsMethod_value;

  /**
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:242
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:242")
  public boolean shouldBeConsiderAsMethod() {
    ASTState state = state();
    if (shouldBeConsiderAsMethod_computed == ASTState.NON_CYCLE || shouldBeConsiderAsMethod_computed == state().cycle()) {
      return shouldBeConsiderAsMethod_value;
    }
    shouldBeConsiderAsMethod_value = !program().attributesOnly;
    if (state().inCircle()) {
      shouldBeConsiderAsMethod_computed = state().cycle();
    
    } else {
      shouldBeConsiderAsMethod_computed = ASTState.NON_CYCLE;
    
    }
    return shouldBeConsiderAsMethod_value;
  }
  /** @apilevel internal */
  private void implicitCalls_reset() {
    implicitCalls_computed = null;
    implicitCalls_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle implicitCalls_computed = null;

  /** @apilevel internal */
  protected Set<InvocationTarget> implicitCalls_value;

  /**
   * Returns a set of implicit call targets for this invocation target.
   * 
   * @return The set of implicit call targets.
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:263
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:263")
  public Set<InvocationTarget> implicitCalls() {
    ASTState state = state();
    if (implicitCalls_computed == ASTState.NON_CYCLE || implicitCalls_computed == state().cycle()) {
      return implicitCalls_value;
    }
    implicitCalls_value = new LinkedHashSet<>();
    if (state().inCircle()) {
      implicitCalls_computed = state().cycle();
    
    } else {
      implicitCalls_computed = ASTState.NON_CYCLE;
    
    }
    return implicitCalls_value;
  }
  /** @apilevel internal */
  private void getContribution_reset() {
    getContribution_computed = null;
    getContribution_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle getContribution_computed = null;

  /** @apilevel internal */
  protected InvocationTarget getContribution_value;

  /**
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:322
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:322")
  public InvocationTarget getContribution() {
    ASTState state = state();
    if (getContribution_computed == ASTState.NON_CYCLE || getContribution_computed == state().cycle()) {
      return getContribution_value;
    }
    getContribution_value = this;
    if (state().inCircle()) {
      getContribution_computed = state().cycle();
    
    } else {
      getContribution_computed = ASTState.NON_CYCLE;
    
    }
    return getContribution_value;
  }
  /** @apilevel internal */
  private void isAnAttribute_reset() {
    isAnAttribute_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isAnAttribute_computed = null;

  /** @apilevel internal */
  protected boolean isAnAttribute_value;

  /**
   * @attribute syn
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:462
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:462")
  public boolean isAnAttribute() {
    ASTState state = state();
    if (isAnAttribute_computed == ASTState.NON_CYCLE || isAnAttribute_computed == state().cycle()) {
      return isAnAttribute_value;
    }
    isAnAttribute_value = false;
    if (state().inCircle()) {
      isAnAttribute_computed = state().cycle();
    
    } else {
      isAnAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isAnAttribute_value;
  }
  /** @apilevel internal */
  private void isSynAttribute_reset() {
    isSynAttribute_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isSynAttribute_computed = null;

  /** @apilevel internal */
  protected boolean isSynAttribute_value;

  /**
   * @attribute syn
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:468
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:468")
  public boolean isSynAttribute() {
    ASTState state = state();
    if (isSynAttribute_computed == ASTState.NON_CYCLE || isSynAttribute_computed == state().cycle()) {
      return isSynAttribute_value;
    }
    isSynAttribute_value = false;
    if (state().inCircle()) {
      isSynAttribute_computed = state().cycle();
    
    } else {
      isSynAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isSynAttribute_value;
  }
  /** @apilevel internal */
  private void isInhAttribute_reset() {
    isInhAttribute_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isInhAttribute_computed = null;

  /** @apilevel internal */
  protected boolean isInhAttribute_value;

  /**
   * @attribute syn
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:474
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:474")
  public boolean isInhAttribute() {
    ASTState state = state();
    if (isInhAttribute_computed == ASTState.NON_CYCLE || isInhAttribute_computed == state().cycle()) {
      return isInhAttribute_value;
    }
    isInhAttribute_value = false;
    if (state().inCircle()) {
      isInhAttribute_computed = state().cycle();
    
    } else {
      isInhAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isInhAttribute_value;
  }
  /** @apilevel internal */
  private void isCircularAttribute_reset() {
    isCircularAttribute_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isCircularAttribute_computed = null;

  /** @apilevel internal */
  protected boolean isCircularAttribute_value;

  /**
   * @attribute syn
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:480
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:480")
  public boolean isCircularAttribute() {
    ASTState state = state();
    if (isCircularAttribute_computed == ASTState.NON_CYCLE || isCircularAttribute_computed == state().cycle()) {
      return isCircularAttribute_value;
    }
    isCircularAttribute_value = false;
    if (state().inCircle()) {
      isCircularAttribute_computed = state().cycle();
    
    } else {
      isCircularAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isCircularAttribute_value;
  }
  /** @apilevel internal */
  private void isCollectionAttribute_reset() {
    isCollectionAttribute_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isCollectionAttribute_computed = null;

  /** @apilevel internal */
  protected boolean isCollectionAttribute_value;

  /**
   * @attribute syn
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:486
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:486")
  public boolean isCollectionAttribute() {
    ASTState state = state();
    if (isCollectionAttribute_computed == ASTState.NON_CYCLE || isCollectionAttribute_computed == state().cycle()) {
      return isCollectionAttribute_value;
    }
    isCollectionAttribute_value = false;
    if (state().inCircle()) {
      isCollectionAttribute_computed = state().cycle();
    
    } else {
      isCollectionAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isCollectionAttribute_value;
  }
  /** @apilevel internal */
  private void getKindOfAttribute_reset() {
    getKindOfAttribute_computed = null;
    getKindOfAttribute_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle getKindOfAttribute_computed = null;

  /** @apilevel internal */
  protected java.util.List<String> getKindOfAttribute_value;

  /**
   * @attribute syn
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:492
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:492")
  public java.util.List<String> getKindOfAttribute() {
    ASTState state = state();
    if (getKindOfAttribute_computed == ASTState.NON_CYCLE || getKindOfAttribute_computed == state().cycle()) {
      return getKindOfAttribute_value;
    }
    getKindOfAttribute_value = getKindOfAttribute_compute();
    if (state().inCircle()) {
      getKindOfAttribute_computed = state().cycle();
    
    } else {
      getKindOfAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return getKindOfAttribute_value;
  }
  /** @apilevel internal */
  private java.util.List<String> getKindOfAttribute_compute() {
      java.util.List<String> kinds = new ArrayList<String>();
      if (isSynAttribute()) {
        kinds.add("syn");
      }
      if (isInhAttribute()) {
        kinds.add("inh");
      }
      if (isCircularAttribute()) {
        kinds.add("circular");
      }
      if (isCollectionAttribute()) {
        kinds.add("collection");
      }
      if (kinds.isEmpty()) {
        kinds.add("method");
      }
      return kinds;
    }
  /**
   * @attribute syn
   * @aspect requests
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Requests.jrag:5
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="requests", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Requests.jrag:5")
  public String pointsToResults() {
    {
            StringBuilder sb = new StringBuilder();
            sb.append("Points to results for '" + targetSignature() + "':\n");
            Map<Pointer, Set<AllocationSite>> pointsToMap = pointsToSet();
            for (Pointer p : pointers()) {
                java.util.List<String> types = new ArrayList<>();
    
                if(p.typ().isPrimitive()) {
                    // We always know the type
                    types.add(p.typ().getID());
                } else if (pointsToMap.containsKey(p)) {
                    Set<String> uniqueTypes = new HashSet<>();
                    for (AllocationSite alloc : pointsToMap.get(p)) {
                        uniqueTypes.add(alloc.typeName());
                    }
                    types.addAll(uniqueTypes);
                } 
    
                sb.append(p).append(" -> ")
                            .append(types.isEmpty() ? "?" : (String.join(", ", types)))
                            .append("\n");
            }
            return sb.toString();
        }
  }
  /**
   * @attribute syn
   * @aspect requests
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Requests.jrag:30
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="requests", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Requests.jrag:30")
  public Map<String, java.util.List<String>> parameterPointers() {
    Map<String, java.util.List<String>> parameterPointers_value = new HashMap<>();
    return parameterPointers_value;
  }
  /**
   * @attribute syn
   * @aspect requests
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Requests.jrag:61
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="requests", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Requests.jrag:61")
  public long pointsToResultCount() {
    {
            long sum = 0;
            for (Map.Entry<Pointer, Set<AllocationSite>> entry : pointsToSet().entrySet()) {
                if(pointers().contains(entry.getKey())) {
                    sum += entry.getValue().size();
                }
    
            }
            return sum;
        }
  }
  /**
   * @attribute syn
   * @aspect requests
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Requests.jrag:75
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="requests", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Requests.jrag:75")
  public long uniqueTypes() {
    {
            long sum = 0;
            for (Map.Entry<Pointer, Set<AllocationSite>> entry : pointsToSet().entrySet()) {
                if(pointers().contains(entry.getKey())) {
                    sum += entry.getValue().stream().map(alloc -> alloc.typeName()).distinct().count();
                }
            }
            return sum;
        }
  }
  /**
   * @attribute syn
   * @aspect FilterUtils
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:41
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FilterUtils", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:41")
  public Set<AllocationSite> allPointerSets() {
    {
            Set<AllocationSite> res = new HashSet<>();
            program().printStream.println("Analyzing pointers in method " + this + " in file " + compilationUnit().pathName());
            for (Pointer pointer : pointers()) {
                long t0 = System.currentTimeMillis();
                res.addAll(pointer.pointsToSet());
                long t = (System.currentTimeMillis() - t0) / 1000;
                program().printStream.println("Type: " + pointer.typ() + ", time: " + t);
                program().printStream.println("Pointer " + pointer + " points to " + pointer.pointsToSet());
            }
            return res;
        }
  }
  /**
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:8
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:8")
  public Set<InvocationTarget> calledInSyn() {
    Set<InvocationTarget> calledInSyn_value = calledIn();
    return calledInSyn_value;
  }
  /** @apilevel internal */
  private void withinDistance_int_reset() {
    withinDistance_int_computed = null;
    withinDistance_int_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map withinDistance_int_values;
  /** @apilevel internal */
  protected java.util.Map withinDistance_int_computed;
  /**
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:11
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:11")
  public Set<InvocationTarget> withinDistance(int maxDistance) {
    Object _parameters = maxDistance;
    if (withinDistance_int_computed == null) withinDistance_int_computed = new java.util.HashMap(4);
    if (withinDistance_int_values == null) withinDistance_int_values = new java.util.HashMap(4);
    ASTState state = state();
    if (withinDistance_int_values.containsKey(_parameters)
        && withinDistance_int_computed.containsKey(_parameters)
        && (withinDistance_int_computed.get(_parameters) == ASTState.NON_CYCLE || withinDistance_int_computed.get(_parameters) == state().cycle())) {
      return (Set<InvocationTarget>) withinDistance_int_values.get(_parameters);
    }
    Set<InvocationTarget> withinDistance_int_value = withinDistance_compute(maxDistance);
    if (state().inCircle()) {
      withinDistance_int_values.put(_parameters, withinDistance_int_value);
      withinDistance_int_computed.put(_parameters, state().cycle());
    
    } else {
      withinDistance_int_values.put(_parameters, withinDistance_int_value);
      withinDistance_int_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return withinDistance_int_value;
  }
  /** @apilevel internal */
  private Set<InvocationTarget> withinDistance_compute(int maxDistance) {
          if (maxDistance < 0) {
              throw new IllegalArgumentException("Distance cannot be negative");
          }
  
          Set<InvocationTarget> active = new HashSet<>(); // also visited set
          active.add(this);
  
          Queue<InvocationTarget> queue = new LinkedList<>();
          queue.add(this);
          Queue<Integer> distances = new LinkedList<>();
          distances.add(0);
  
          while (!queue.isEmpty()) {
              InvocationTarget node = queue.remove();
              int distance = distances.remove();
  
              if (distance < maxDistance) {
                  for (InvocationTarget next : node.calledInSyn()) {
                      if (!active.contains(next)) {
                          active.add(next);
                          queue.add(next);
                          distances.add(distance + 1);
                      }
                  }
  
                  for (InvocationTarget next : node.calledMethodDecls()) {
                      if (!active.contains(next)) {
                          active.add(next);
                          queue.add(next);
                          distances.add(distance + 1);
                      }
                  }
  
                  for (InvocationTarget next : node.accessedTypeConstructors()) {
                      if (!active.contains(next)) {
                          active.add(next);
                          queue.add(next);
                          distances.add(distance + 1);
                      }
                  }
              }
          }
          return active;
      }
/** @apilevel internal */
protected ASTState.Cycle reachable_cycle = null;

  /** @apilevel internal */
  private void reachable_reset() {
    reachable_computed = false;
    reachable_initialized = false;
    reachable_value = null;
    reachable_cycle = null;
  }
  /** @apilevel internal */
  protected boolean reachable_computed = false;

  /** @apilevel internal */
  protected Set<InvocationTarget> reachable_value;
  /** @apilevel internal */
  protected boolean reachable_initialized = false;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:58")
  public Set<InvocationTarget> reachable() {
    if (reachable_computed) {
      return reachable_value;
    }
    ASTState state = state();
    if (!reachable_initialized) {
      reachable_initialized = true;
      reachable_value = new LinkedHashSet<>();
    }
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      do {
        reachable_cycle = state.nextCycle();
        Set<InvocationTarget> new_reachable_value = reachable_compute();
        if (!AttributeValue.equals(reachable_value, new_reachable_value)) {
          state.setChangeInCycle();
        }
        reachable_value = new_reachable_value;
      } while (state.testAndClearChangeInCycle());
      reachable_computed = true;
      state.startLastCycle();
      Set<InvocationTarget> $tmp = reachable_compute();

      state.leaveCircle();
    } else if (reachable_cycle != state.cycle()) {
      reachable_cycle = state.cycle();
      if (state.lastCycle()) {
        reachable_computed = true;
        Set<InvocationTarget> new_reachable_value = reachable_compute();
        return new_reachable_value;
      }
      Set<InvocationTarget> new_reachable_value = reachable_compute();
      if (!AttributeValue.equals(reachable_value, new_reachable_value)) {
        state.setChangeInCycle();
      }
      reachable_value = new_reachable_value;
    } else {
    }
    return reachable_value;
  }
  /** @apilevel internal */
  private Set<InvocationTarget> reachable_compute() {
          Set<InvocationTarget> active = new LinkedHashSet<>();
          active.add(this);
  
          for (InvocationTarget it : calledIn()) {
              active.addAll(it.reachable());
          }
  
          for (InvocationTarget it : calledMethodDecls()) {
              active.addAll(it.reachable());
          }
  
          for (InvocationTarget it : accessedTypeConstructors()) {
              active.addAll(it.reachable());
          }
  
          return active;
      }
  /**
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:79
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:79")
  public Set<InvocationTarget> reachableNonCircular() {
    {
            Set<InvocationTarget> active = new HashSet<>();
            active.add(this);
            Queue<InvocationTarget> queue = new LinkedList<>();
            queue.add(this);
            
            while (!queue.isEmpty()) {
                InvocationTarget cur = queue.remove();
                Set<InvocationTarget> neighbors = new HashSet<>();
                neighbors.addAll(cur.calledInSyn());
                neighbors.addAll(cur.calledMethodDecls());
                neighbors.addAll(cur.accessedTypeConstructors());
                neighbors.removeAll(active);
    
                queue.addAll(neighbors);
                active.addAll(neighbors);
            }
            return active;
        }
  }
/** @apilevel internal */
protected ASTState.Cycle backwardsActive_cycle = null;

  /** @apilevel internal */
  private void backwardsActive_reset() {
    backwardsActive_computed = false;
    backwardsActive_initialized = false;
    backwardsActive_value = null;
    backwardsActive_cycle = null;
  }
  /** @apilevel internal */
  protected boolean backwardsActive_computed = false;

  /** @apilevel internal */
  protected Set<InvocationTarget> backwardsActive_value;
  /** @apilevel internal */
  protected boolean backwardsActive_initialized = false;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:99")
  public Set<InvocationTarget> backwardsActive() {
    if (backwardsActive_computed) {
      return backwardsActive_value;
    }
    ASTState state = state();
    if (!backwardsActive_initialized) {
      backwardsActive_initialized = true;
      backwardsActive_value = new LinkedHashSet<>();
    }
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      do {
        backwardsActive_cycle = state.nextCycle();
        Set<InvocationTarget> new_backwardsActive_value = backwardsActive_compute();
        if (!AttributeValue.equals(backwardsActive_value, new_backwardsActive_value)) {
          state.setChangeInCycle();
        }
        backwardsActive_value = new_backwardsActive_value;
      } while (state.testAndClearChangeInCycle());
      backwardsActive_computed = true;
      state.startLastCycle();
      Set<InvocationTarget> $tmp = backwardsActive_compute();

      state.leaveCircle();
    } else if (backwardsActive_cycle != state.cycle()) {
      backwardsActive_cycle = state.cycle();
      if (state.lastCycle()) {
        backwardsActive_computed = true;
        Set<InvocationTarget> new_backwardsActive_value = backwardsActive_compute();
        return new_backwardsActive_value;
      }
      Set<InvocationTarget> new_backwardsActive_value = backwardsActive_compute();
      if (!AttributeValue.equals(backwardsActive_value, new_backwardsActive_value)) {
        state.setChangeInCycle();
      }
      backwardsActive_value = new_backwardsActive_value;
    } else {
    }
    return backwardsActive_value;
  }
  /** @apilevel internal */
  private Set<InvocationTarget> backwardsActive_compute() {
          Set<InvocationTarget> active = new LinkedHashSet<>();
          active.add(this);
  
          for (InvocationTarget it : calledIn()) {
              active.addAll(it.backwardsActive());
          }
  
          return active;
      }

  /** @apilevel internal */
  private void kBoundedBackwardsActive_int_reset() {
    kBoundedBackwardsActive_int_values = null;
  }
  protected java.util.Map kBoundedBackwardsActive_int_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:112")
  public Set<InvocationTarget> kBoundedBackwardsActive(int k) {
    Object _parameters = k;


    if (kBoundedBackwardsActive_int_values == null) kBoundedBackwardsActive_int_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (kBoundedBackwardsActive_int_values.containsKey(_parameters)) {
      Object _cache = kBoundedBackwardsActive_int_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Set<InvocationTarget>) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      kBoundedBackwardsActive_int_values.put(_parameters, _value);
      _value.value = new LinkedHashSet<>();
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      Set<InvocationTarget> new_kBoundedBackwardsActive_int_value;
      do {
        _value.cycle = state.nextCycle();
        new_kBoundedBackwardsActive_int_value = kBoundedBackwardsActive_compute(k);
        if (!AttributeValue.equals(((Set<InvocationTarget>)_value.value), new_kBoundedBackwardsActive_int_value)) {
          state.setChangeInCycle();
          _value.value = new_kBoundedBackwardsActive_int_value;
        }
      } while (state.testAndClearChangeInCycle());
      kBoundedBackwardsActive_int_values.put(_parameters, new_kBoundedBackwardsActive_int_value);
      state.startLastCycle();
      Set<InvocationTarget> $tmp = kBoundedBackwardsActive_compute(k);

      state.leaveCircle();
      return new_kBoundedBackwardsActive_int_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      Set<InvocationTarget> new_kBoundedBackwardsActive_int_value = kBoundedBackwardsActive_compute(k);
      if (state.lastCycle()) {
        kBoundedBackwardsActive_int_values.put(_parameters, new_kBoundedBackwardsActive_int_value);
      }
      if (!AttributeValue.equals(((Set<InvocationTarget>)_value.value), new_kBoundedBackwardsActive_int_value)) {
        state.setChangeInCycle();
        _value.value = new_kBoundedBackwardsActive_int_value;
      }
      return new_kBoundedBackwardsActive_int_value;
    } else {
      return (Set<InvocationTarget>) _value.value;
    }
  }
  /** @apilevel internal */
  private Set<InvocationTarget> kBoundedBackwardsActive_compute(int k) {
          if (k < 0) {
              throw new IllegalArgumentException("k cannot be negative");
          }
  
          Set<InvocationTarget> active = new LinkedHashSet<>();
          active.add(this);
  
          if (k == 0) {
              return active;
          }
  
          for (InvocationTarget it : calledIn()) {
              active.addAll(it.kBoundedBackwardsActive(k - 1));
          }
  
          return active;
      }

  /** @apilevel internal */
  private void forwardsActive_Set_InvocationTarget__reset() {
    forwardsActive_Set_InvocationTarget__values = null;
  }
  protected java.util.Map forwardsActive_Set_InvocationTarget__values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:133")
  public Set<InvocationTarget> forwardsActive(Set<InvocationTarget> backwardsActive) {
    Object _parameters = backwardsActive;


    if (forwardsActive_Set_InvocationTarget__values == null) forwardsActive_Set_InvocationTarget__values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (forwardsActive_Set_InvocationTarget__values.containsKey(_parameters)) {
      Object _cache = forwardsActive_Set_InvocationTarget__values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Set<InvocationTarget>) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      forwardsActive_Set_InvocationTarget__values.put(_parameters, _value);
      _value.value = new LinkedHashSet<>();
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      Set<InvocationTarget> new_forwardsActive_Set_InvocationTarget__value;
      do {
        _value.cycle = state.nextCycle();
        new_forwardsActive_Set_InvocationTarget__value = forwardsActive_compute(backwardsActive);
        if (!AttributeValue.equals(((Set<InvocationTarget>)_value.value), new_forwardsActive_Set_InvocationTarget__value)) {
          state.setChangeInCycle();
          _value.value = new_forwardsActive_Set_InvocationTarget__value;
        }
      } while (state.testAndClearChangeInCycle());
      forwardsActive_Set_InvocationTarget__values.put(_parameters, new_forwardsActive_Set_InvocationTarget__value);
      state.startLastCycle();
      Set<InvocationTarget> $tmp = forwardsActive_compute(backwardsActive);

      state.leaveCircle();
      return new_forwardsActive_Set_InvocationTarget__value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      Set<InvocationTarget> new_forwardsActive_Set_InvocationTarget__value = forwardsActive_compute(backwardsActive);
      if (state.lastCycle()) {
        forwardsActive_Set_InvocationTarget__values.put(_parameters, new_forwardsActive_Set_InvocationTarget__value);
      }
      if (!AttributeValue.equals(((Set<InvocationTarget>)_value.value), new_forwardsActive_Set_InvocationTarget__value)) {
        state.setChangeInCycle();
        _value.value = new_forwardsActive_Set_InvocationTarget__value;
      }
      return new_forwardsActive_Set_InvocationTarget__value;
    } else {
      return (Set<InvocationTarget>) _value.value;
    }
  }
  /** @apilevel internal */
  private Set<InvocationTarget> forwardsActive_compute(Set<InvocationTarget> backwardsActive) {
          Set<InvocationTarget> active = new LinkedHashSet<>();
  
          // Return early if this node was not visited in the backwards sweep
          if (!backwardsActive.contains(this)) {
              return active;
          }
  
          active.add(this);
  
          for (InvocationTarget it : calledMethodDecls()) {
              active.addAll(it.forwardsActive(backwardsActive));
          }
          return active;
      }
  /**
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:151
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:151")
  public Set<InvocationTarget> active() {
    Set<InvocationTarget> active_value = program()
            .getTarget(program().entryPointPackage, program().entryPointMethod)
            .forwardsActive(backwardsActive());
    return active_value;
  }
  /**
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:156
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:156")
  public Set<InvocationTarget> kBoundedActive(int k) {
    Set<InvocationTarget> kBoundedActive_int_value = program()
            .getTarget(program().entryPointPackage, program().entryPointMethod)
            .forwardsActive(kBoundedBackwardsActive(k));
    return kBoundedActive_int_value;
  }
  /**
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:161
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:161")
  public Set<InvocationTarget> calledMethodDecls() {
    Set<InvocationTarget> calledMethodDecls_value = calledMethods()
            .stream()
            .flatMap(x -> x.allDecls().stream())
            .collect(Collectors.toSet());
    return calledMethodDecls_value;
  }
  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:127
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:127")
  public Set<InvocationTarget> accessedTypeConstructors() {
    Set<InvocationTarget> accessedTypeConstructors_value = _accessedTypeConstructors();
    return accessedTypeConstructors_value;
  }
  /** @apilevel internal */
  private void activePointsToEdges_reset() {
    activePointsToEdges_computed = null;
    activePointsToEdges_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle activePointsToEdges_computed = null;

  /** @apilevel internal */
  protected Set<PointsToEdge> activePointsToEdges_value;

  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:225
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:225")
  public Set<PointsToEdge> activePointsToEdges() {
    ASTState state = state();
    if (activePointsToEdges_computed == ASTState.NON_CYCLE || activePointsToEdges_computed == state().cycle()) {
      return activePointsToEdges_value;
    }
    activePointsToEdges_value = activePointsToEdges_compute();
    if (state().inCircle()) {
      activePointsToEdges_computed = state().cycle();
    
    } else {
      activePointsToEdges_computed = ASTState.NON_CYCLE;
    
    }
    return activePointsToEdges_value;
  }
  /** @apilevel internal */
  private Set<PointsToEdge> activePointsToEdges_compute() {
          HashSet<PointsToEdge> res = new HashSet<>();
          for (InvocationTarget selectedMethod : selectedMethods()) {
              res.addAll(selectedMethod.pointsToEdges());
          }
          return res;
      }
  /** @apilevel internal */
  private void activeInclusionEdges_reset() {
    activeInclusionEdges_computed = null;
    activeInclusionEdges_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle activeInclusionEdges_computed = null;

  /** @apilevel internal */
  protected Set<InclusionEdge> activeInclusionEdges_value;

  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:233
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:233")
  public Set<InclusionEdge> activeInclusionEdges() {
    ASTState state = state();
    if (activeInclusionEdges_computed == ASTState.NON_CYCLE || activeInclusionEdges_computed == state().cycle()) {
      return activeInclusionEdges_value;
    }
    activeInclusionEdges_value = activeInclusionEdges_compute();
    if (state().inCircle()) {
      activeInclusionEdges_computed = state().cycle();
    
    } else {
      activeInclusionEdges_computed = ASTState.NON_CYCLE;
    
    }
    return activeInclusionEdges_value;
  }
  /** @apilevel internal */
  private Set<InclusionEdge> activeInclusionEdges_compute() {
          HashSet<InclusionEdge> res = new HashSet<>();
          for (InvocationTarget selectedMethod : selectedMethods()) {
              res.addAll(selectedMethod.inclusionEdges());
          }
          return res;
      }
  /** @apilevel internal */
  private void selectedMethods_reset() {
    selectedMethods_computed = null;
    selectedMethods_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle selectedMethods_computed = null;

  /** @apilevel internal */
  protected Set<InvocationTarget> selectedMethods_value;

  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:271
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:271")
  public Set<InvocationTarget> selectedMethods() {
    ASTState state = state();
    if (selectedMethods_computed == ASTState.NON_CYCLE || selectedMethods_computed == state().cycle()) {
      return selectedMethods_value;
    }
    selectedMethods_value = selectedMethods_compute();
    if (state().inCircle()) {
      selectedMethods_computed = state().cycle();
    
    } else {
      selectedMethods_computed = ASTState.NON_CYCLE;
    
    }
    return selectedMethods_value;
  }
  /** @apilevel internal */
  private Set<InvocationTarget> selectedMethods_compute() {
          switch (program().strategy) {
              case BACKWARDS: return backwardsActive();
              case REACHABLE: return reachable();
              case DISTANCE: return withinDistance(program().analysisDistance);
              default: throw new Error("Strategy not included in selectedMethods() switch");
          }
      }
  /**
   * @attribute syn
   * @aspect WholeProgram
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/WholeProgram.jrag:30
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="WholeProgram", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/WholeProgram.jrag:30")
  public Map<String, java.util.List<String>> wholeProgramParameterPointers() {
    Map<String, java.util.List<String>> wholeProgramParameterPointers_value = new HashMap<>();
    return wholeProgramParameterPointers_value;
  }
  /**
   * @attribute syn
   * @aspect WholeProgram
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/WholeProgram.jrag:69
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="WholeProgram", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/WholeProgram.jrag:69")
  public boolean hasPointerParameter() {
    boolean hasPointerParameter_value = false;
    return hasPointerParameter_value;
  }
  /** @apilevel internal */
  private void pointsToSet_reset() {
    pointsToSet_computed = null;
    pointsToSet_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle pointsToSet_computed = null;

  /** @apilevel internal */
  protected Map<Pointer, Set<AllocationSite>> pointsToSet_value;

  /**
   * @attribute syn
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:13
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Solver", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:13")
  public Map<Pointer, Set<AllocationSite>> pointsToSet() {
    ASTState state = state();
    if (pointsToSet_computed == ASTState.NON_CYCLE || pointsToSet_computed == state().cycle()) {
      return pointsToSet_value;
    }
    pointsToSet_value = pointsToSet_compute();
    if (state().inCircle()) {
      pointsToSet_computed = state().cycle();
    
    } else {
      pointsToSet_computed = ASTState.NON_CYCLE;
    
    }
    return pointsToSet_value;
  }
  /** @apilevel internal */
  private Map<Pointer, Set<AllocationSite>> pointsToSet_compute() {
          java.util.List<TypeDecl> types = new ArrayList<>();
          for (Pointer p : pointers()) {
              types.add(p.typ());
          }
  
          Map<Pointer, Set<AllocationSite>> res = new HashMap<>();
          Set<PointsToEdge> solution = new Solver(activeInclusionEdges(), activePointsToEdges(), types).solution();
          for (Pointer pointer : pointers()) {
              Set<AllocationSite> pte = solution.stream()
                                         .filter(edge -> edge.from == pointer.declaration())
                                         .map(edge -> edge.to)
                                         .filter(allocsite -> allocsite.atype().subtype(pointer.typ()))
                                         .collect(Collectors.toSet());
              res.put(pointer, pte);
          }
          return res;
      }
  /**
   * @attribute inh
   * @aspect ExceptionHandling
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:92
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:92")
  public boolean handlesException(TypeDecl exceptionType) {
    Object _parameters = exceptionType;
    if (handlesException_TypeDecl_computed == null) handlesException_TypeDecl_computed = new java.util.HashMap(4);
    if (handlesException_TypeDecl_values == null) handlesException_TypeDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (handlesException_TypeDecl_values.containsKey(_parameters)
        && handlesException_TypeDecl_computed.containsKey(_parameters)
        && (handlesException_TypeDecl_computed.get(_parameters) == ASTState.NON_CYCLE || handlesException_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) handlesException_TypeDecl_values.get(_parameters);
    }
    boolean handlesException_TypeDecl_value = getParent().Define_handlesException(this, null, exceptionType);
    if (state().inCircle()) {
      handlesException_TypeDecl_values.put(_parameters, handlesException_TypeDecl_value);
      handlesException_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      handlesException_TypeDecl_values.put(_parameters, handlesException_TypeDecl_value);
      handlesException_TypeDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return handlesException_TypeDecl_value;
  }
  /** @apilevel internal */
  private void handlesException_TypeDecl_reset() {
    handlesException_TypeDecl_computed = null;
    handlesException_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map handlesException_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map handlesException_TypeDecl_computed;
  /**
   * @attribute inh
   * @aspect AllMethods
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:40
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AllMethods", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:40")
  public CompilationUnit enclosingCompilationUnit() {
    CompilationUnit enclosingCompilationUnit_value = getParent().Define_enclosingCompilationUnit(this, null);
    return enclosingCompilationUnit_value;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:114
   * @apilevel internal
   */
  public boolean Define_handlesException(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:256
      return hostType().isAnonymous() && handlesException(exceptionType);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/UnreachableStatements.jrag:49
   * @apilevel internal
   */
  public boolean Define_reachable(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/UnreachableStatements.jrag:63
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:551
      return assignedBefore(v);
    }
    else {
      return super.Define_assignedBefore(_callerNode, _childNode, v);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:223
   * @apilevel internal
   */
  public boolean Define_inStaticContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:228
      return true;
    }
    else {
      return getParent().Define_inStaticContext(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:223
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inStaticContext
   */
  protected boolean canDefine_inStaticContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:672
   * @apilevel internal
   */
  public TypeDecl Define_enclosingInstance(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:676
      return null;
    }
    else {
      return getParent().Define_enclosingInstance(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:672
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingInstance
   */
  protected boolean canDefine_enclosingInstance(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:212
   * @apilevel internal
   */
  public InvocationTarget Define_enclosingTarget(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return this;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:212
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingTarget
   */
  protected boolean canDefine_enclosingTarget(ASTNode _callerNode, ASTNode _childNode) {
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
   * @aspect CallGraphWithRAGS
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:37
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="CallGraphWithRAGS", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:37")
  public Set<InvocationTarget> backwardCG() {
    ASTState state = state();
    if (InvocationTarget_backwardCG_computed == ASTState.NON_CYCLE || InvocationTarget_backwardCG_computed == state().cycle()) {
      return InvocationTarget_backwardCG_value;
    }
    InvocationTarget_backwardCG_value = backwardCG_compute();
    if (state().inCircle()) {
      InvocationTarget_backwardCG_computed = state().cycle();
    
    } else {
      InvocationTarget_backwardCG_computed = ASTState.NON_CYCLE;
    
    }
    return InvocationTarget_backwardCG_value;
  }
  /** @apilevel internal */
  private Set<InvocationTarget> backwardCG_compute() {
    ASTNode node = this;
    while (node != null && !(node instanceof Program)) {
      node = node.getParent();
    }
    Program root = (Program) node;
    root.survey_InvocationTarget_backwardCG();
    Set<InvocationTarget> _computedValue = new LinkedHashSet<>();
    if (root.contributorMap_InvocationTarget_backwardCG.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_InvocationTarget_backwardCG.get(this)) {
        contributor.contributeTo_InvocationTarget_backwardCG(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle InvocationTarget_backwardCG_computed = null;

  /** @apilevel internal */
  protected Set<InvocationTarget> InvocationTarget_backwardCG_value;

  /**
   * @attribute coll
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:180
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:180")
  public Set<Invocable> calledMethods() {
    ASTState state = state();
    if (InvocationTarget_calledMethods_computed == ASTState.NON_CYCLE || InvocationTarget_calledMethods_computed == state().cycle()) {
      return InvocationTarget_calledMethods_value;
    }
    InvocationTarget_calledMethods_value = calledMethods_compute();
    if (state().inCircle()) {
      InvocationTarget_calledMethods_computed = state().cycle();
    
    } else {
      InvocationTarget_calledMethods_computed = ASTState.NON_CYCLE;
    
    }
    return InvocationTarget_calledMethods_value;
  }
  /** @apilevel internal */
  private Set<Invocable> calledMethods_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    ASTNode root = (ASTNode) node;
    root.survey_InvocationTarget_calledMethods();
    Set<Invocable> _computedValue = new LinkedHashSet<>();
    if (root.contributorMap_InvocationTarget_calledMethods.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_InvocationTarget_calledMethods.get(this)) {
        contributor.contributeTo_InvocationTarget_calledMethods(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle InvocationTarget_calledMethods_computed = null;

  /** @apilevel internal */
  protected Set<Invocable> InvocationTarget_calledMethods_value;

  /**
   * @attribute coll
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:2
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:2")
  public Set<InvocationTarget> calledIn() {
    ASTState state = state();
    if (InvocationTarget_calledIn_computed == ASTState.NON_CYCLE || InvocationTarget_calledIn_computed == state().cycle()) {
      return InvocationTarget_calledIn_value;
    }
    InvocationTarget_calledIn_value = calledIn_compute();
    if (state().inCircle()) {
      InvocationTarget_calledIn_computed = state().cycle();
    
    } else {
      InvocationTarget_calledIn_computed = ASTState.NON_CYCLE;
    
    }
    return InvocationTarget_calledIn_value;
  }
  /** @apilevel internal */
  private Set<InvocationTarget> calledIn_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    ASTNode root = (ASTNode) node;
    root.survey_InvocationTarget_calledIn();
    Set<InvocationTarget> _computedValue = new LinkedHashSet<>();
    if (root.contributorMap_InvocationTarget_calledIn.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_InvocationTarget_calledIn.get(this)) {
        contributor.contributeTo_InvocationTarget_calledIn(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle InvocationTarget_calledIn_computed = null;

  /** @apilevel internal */
  protected Set<InvocationTarget> InvocationTarget_calledIn_value;

  /**
   * @attribute coll
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:83
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:83")
  public Set<PointsToEdge> pointsToEdges() {
    ASTState state = state();
    if (InvocationTarget_pointsToEdges_computed == ASTState.NON_CYCLE || InvocationTarget_pointsToEdges_computed == state().cycle()) {
      return InvocationTarget_pointsToEdges_value;
    }
    InvocationTarget_pointsToEdges_value = pointsToEdges_compute();
    if (state().inCircle()) {
      InvocationTarget_pointsToEdges_computed = state().cycle();
    
    } else {
      InvocationTarget_pointsToEdges_computed = ASTState.NON_CYCLE;
    
    }
    return InvocationTarget_pointsToEdges_value;
  }
  /** @apilevel internal */
  private Set<PointsToEdge> pointsToEdges_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    ASTNode root = (ASTNode) node;
    root.survey_InvocationTarget_pointsToEdges();
    Set<PointsToEdge> _computedValue = new HashSet<>();
    if (root.contributorMap_InvocationTarget_pointsToEdges.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_InvocationTarget_pointsToEdges.get(this)) {
        contributor.contributeTo_InvocationTarget_pointsToEdges(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle InvocationTarget_pointsToEdges_computed = null;

  /** @apilevel internal */
  protected Set<PointsToEdge> InvocationTarget_pointsToEdges_value;

  /**
   * @attribute coll
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:84
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:84")
  public Set<InclusionEdge> inclusionEdges() {
    ASTState state = state();
    if (InvocationTarget_inclusionEdges_computed == ASTState.NON_CYCLE || InvocationTarget_inclusionEdges_computed == state().cycle()) {
      return InvocationTarget_inclusionEdges_value;
    }
    InvocationTarget_inclusionEdges_value = inclusionEdges_compute();
    if (state().inCircle()) {
      InvocationTarget_inclusionEdges_computed = state().cycle();
    
    } else {
      InvocationTarget_inclusionEdges_computed = ASTState.NON_CYCLE;
    
    }
    return InvocationTarget_inclusionEdges_value;
  }
  /** @apilevel internal */
  private Set<InclusionEdge> inclusionEdges_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    ASTNode root = (ASTNode) node;
    root.survey_InvocationTarget_inclusionEdges();
    Set<InclusionEdge> _computedValue = new HashSet<>();
    if (root.contributorMap_InvocationTarget_inclusionEdges.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_InvocationTarget_inclusionEdges.get(this)) {
        contributor.contributeTo_InvocationTarget_inclusionEdges(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle InvocationTarget_inclusionEdges_computed = null;

  /** @apilevel internal */
  protected Set<InclusionEdge> InvocationTarget_inclusionEdges_value;

  /**
   * @attribute coll
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:126
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:126")
  public Set<InvocationTarget> _accessedTypeConstructors() {
    ASTState state = state();
    if (InvocationTarget__accessedTypeConstructors_computed == ASTState.NON_CYCLE || InvocationTarget__accessedTypeConstructors_computed == state().cycle()) {
      return InvocationTarget__accessedTypeConstructors_value;
    }
    InvocationTarget__accessedTypeConstructors_value = _accessedTypeConstructors_compute();
    if (state().inCircle()) {
      InvocationTarget__accessedTypeConstructors_computed = state().cycle();
    
    } else {
      InvocationTarget__accessedTypeConstructors_computed = ASTState.NON_CYCLE;
    
    }
    return InvocationTarget__accessedTypeConstructors_value;
  }
  /** @apilevel internal */
  private Set<InvocationTarget> _accessedTypeConstructors_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    ASTNode root = (ASTNode) node;
    root.survey_InvocationTarget__accessedTypeConstructors();
    Set<InvocationTarget> _computedValue = new HashSet<>();
    if (root.contributorMap_InvocationTarget__accessedTypeConstructors.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_InvocationTarget__accessedTypeConstructors.get(this)) {
        contributor.contributeTo_InvocationTarget__accessedTypeConstructors(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle InvocationTarget__accessedTypeConstructors_computed = null;

  /** @apilevel internal */
  protected Set<InvocationTarget> InvocationTarget__accessedTypeConstructors_value;

  /**
   * @attribute coll
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:199
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:199")
  public Set<Pointer> pointers() {
    ASTState state = state();
    if (InvocationTarget_pointers_computed == ASTState.NON_CYCLE || InvocationTarget_pointers_computed == state().cycle()) {
      return InvocationTarget_pointers_value;
    }
    InvocationTarget_pointers_value = pointers_compute();
    if (state().inCircle()) {
      InvocationTarget_pointers_computed = state().cycle();
    
    } else {
      InvocationTarget_pointers_computed = ASTState.NON_CYCLE;
    
    }
    return InvocationTarget_pointers_value;
  }
  /** @apilevel internal */
  private Set<Pointer> pointers_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    ASTNode root = (ASTNode) node;
    root.survey_InvocationTarget_pointers();
    Set<Pointer> _computedValue = new HashSet<>();
    if (root.contributorMap_InvocationTarget_pointers.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_InvocationTarget_pointers.get(this)) {
        contributor.contributeTo_InvocationTarget_pointers(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle InvocationTarget_pointers_computed = null;

  /** @apilevel internal */
  protected Set<Pointer> InvocationTarget_pointers_value;

  /** @apilevel internal */
  protected void collect_contributors_CompilationUnit_problems(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/UnreachableStatements.jrag:38
    if (!getBlock().canCompleteNormally()) {
      {
        java.util.Set<ASTNode> contributors = _map.get(_root);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) _root, contributors);
        }
        contributors.add(this);
      }
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:217
    if (hostType().isInnerClass()) {
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
  protected void collect_contributors_Program_allMethods(Program _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:34
    if (visible()) {
      {
        java.util.Set<ASTNode> contributors = _map.get(_root);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) _root, contributors);
        }
        contributors.add(this);
      }
    }
    super.collect_contributors_Program_allMethods(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_InvocationTarget_backwardCG(Program _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:39
    for (InvocationTarget target : (Iterable<? extends InvocationTarget>) (cg())) {
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_InvocationTarget_backwardCG(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_InvocationTarget_calledIn(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/PointerAnalysis.jrag:5
    for (InvocationTarget target : (Iterable<? extends InvocationTarget>) (calledMethodDecls())) {
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_InvocationTarget_calledIn(_root, _map);
  }
  /** @apilevel internal */
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    if (!getBlock().canCompleteNormally()) {
      collection.add(errorf("static initializer in %s cannot complete normally", hostType().fullName()));
    }
    if (hostType().isInnerClass()) {
      collection.add(error("*** Inner classes may not declare static initializers"));
    }
  }
  /** @apilevel internal */
  protected void contributeTo_Program_allMethods(Set<InvocationTarget> collection) {
    super.contributeTo_Program_allMethods(collection);
    if (visible()) {
      collection.add(this);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_InvocationTarget_backwardCG(Set<InvocationTarget> collection) {
    super.contributeTo_InvocationTarget_backwardCG(collection);
    collection.add(getContribution());
  }
  /** @apilevel internal */
  protected void contributeTo_InvocationTarget_calledIn(Set<InvocationTarget> collection) {
    super.contributeTo_InvocationTarget_calledIn(collection);
    collection.add(this);
  }

}
