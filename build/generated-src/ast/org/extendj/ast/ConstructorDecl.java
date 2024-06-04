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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:189
 * @astdecl ConstructorDecl : BodyDecl ::= Modifiers <ID:String> Parameter:ParameterDeclaration* Exception:Access* [ParsedConstructorInvocation:Stmt] Block ImplicitConstructorInvocation:Stmt;
 * @production ConstructorDecl : {@link BodyDecl} ::= <span class="component">{@link Modifiers}</span> <span class="component">&lt;ID:{@link String}&gt;</span> <span class="component">Parameter:{@link ParameterDeclaration}*</span> <span class="component">Exception:{@link Access}*</span> <span class="component">[ParsedConstructorInvocation:{@link Stmt}]</span> <span class="component">{@link Block}</span> <span class="component">ImplicitConstructorInvocation:{@link Stmt}</span>;

 */
public class ConstructorDecl extends BodyDecl implements Cloneable, InvocationTarget {
  /**
   * @aspect CallGraphWithRAGS
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:92
   */
  public
  int sccID = -1;
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrint.jadd:259
   */
  public void prettyPrint(PrettyPrinter out) {
    if (!isImplicitConstructor()) {
      if (hasDocComment()) {
        out.print(docComment());
      }
      if (!out.isNewLine()) {
        out.println();
      }
      out.print(getModifiers());
      out.print(getID());
      out.print("(");
      out.join(getParameterList(), new PrettyPrinter.Joiner() {
        @Override
        public void printSeparator(PrettyPrinter out) {
          out.print(", ");
        }
      });
      out.print(")");
      if (hasExceptions()) {
        out.print(" throws ");
        out.join(getExceptionList(), new PrettyPrinter.Joiner() {
          @Override
          public void printSeparator(PrettyPrinter out) {
            out.print(", ");
          }
        });
      }
      out.print(" {");
      out.println();
      out.indent(1);
      out.print(getParsedConstructorInvocationOpt());
      if (!out.isNewLine()) {
        out.println();
      }
      out.indent(1);
      out.join(blockStmts(), new PrettyPrinter.Joiner() {
        @Override
        public void printSeparator(PrettyPrinter out) {
          out.println();
        }
      });
      if (!out.isNewLine()) {
        out.println();
      }
      out.print("}");
    }
  }
  /**
   * @aspect ConstructorDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:202
   */
  public boolean applicable(List<Expr> argList) {
    if (getNumParameter() != argList.getNumChild()) {
      return false;
    }
    for (int i = 0; i < getNumParameter(); i++) {
      TypeDecl arg = argList.getChild(i).type();
      TypeDecl parameter = getParameter(i).type();
      if (!arg.subtype(parameter)) {
        return false;
      }
    }
    return true;
  }
  /**
   * Flag to indicate if this constructor is an auto-generated
   * default constructor. Implicit constructors are not pretty
   * printed.
   * @aspect ImplicitConstructor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:233
   */
  private boolean isImplicitConstructor = false;
  /**
   * Set the default constructor flag. Causes this constructor
   * to not be pretty printed.
   * @aspect ImplicitConstructor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:239
   */
  public void setImplicitConstructor() {
    isImplicitConstructor = true;
  }
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1469
   */
  public BodyDecl signatureCopy() {
    return new ConstructorDeclSubstituted(
        getModifiers().treeCopyNoTransform(),
        getID(),
        getParameterList().treeCopyNoTransform(),
        getExceptionList().treeCopyNoTransform(),
        new Opt(),
        new Block(),
        this);
  }
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1566
   */
  public BodyDecl erasedCopy() {
    return new ConstructorDeclSubstituted(
        getModifiers().treeCopyNoTransform(),
        getID(),
        erasedParameterList(getParameterList()),
        erasedAccessList(getExceptionList()),
        new Opt<Stmt>(),
        new Block(),
        this);
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
  public ConstructorDecl() {
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
    children = new ASTNode[6];
    setChild(new List(), 1);
    setChild(new List(), 2);
    setChild(new Opt(), 3);
  }
  /**
   * @declaredat ASTNode:16
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Modifiers", "ID", "Parameter", "Exception", "ParsedConstructorInvocation", "Block"},
    type = {"Modifiers", "String", "List<ParameterDeclaration>", "List<Access>", "Opt<Stmt>", "Block"},
    kind = {"Child", "Token", "List", "List", "Opt", "Child"}
  )
  public ConstructorDecl(Modifiers p0, String p1, List<ParameterDeclaration> p2, List<Access> p3, Opt<Stmt> p4, Block p5) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
    setChild(p4, 3);
    setChild(p5, 4);
  }
  /**
   * @declaredat ASTNode:29
   */
  public ConstructorDecl(Modifiers p0, beaver.Symbol p1, List<ParameterDeclaration> p2, List<Access> p3, Opt<Stmt> p4, Block p5) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
    setChild(p4, 3);
    setChild(p5, 4);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:38
   */
  protected int numChildren() {
    return 5;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:44
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:48
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    implicitCalls_reset();
    fullSignature_reset();
    name_reset();
    signature_reset();
    sameSignature_ConstructorDecl_reset();
    lessSpecificThan_ConstructorDecl_reset();
    getImplicitConstructorInvocation_reset();
    throwsException_TypeDecl_reset();
    accessibleFrom_TypeDecl_reset();
    assignedAfter_Variable_reset();
    unassignedAfter_Variable_reset();
    parameterDeclaration_String_reset();
    circularThisInvocation_ConstructorDecl_reset();
    sourceConstructorDecl_reset();
    transformed_reset();
    transformedEnumConstructor_reset();
    reversedCG_reset();
    cg_reset();
    completeCallGraph_reset();
    getSCCID_reset();
    returnType_reset();
    paramTypes_reset();
    shouldBeConsiderAsMethod_reset();
    targetSignature_reset();
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
   * @declaredat ASTNode:93
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
   * @declaredat ASTNode:111
   */
  public ConstructorDecl clone() throws CloneNotSupportedException {
    ConstructorDecl node = (ConstructorDecl) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:116
   */
  public ConstructorDecl copy() {
    try {
      ConstructorDecl node = (ConstructorDecl) clone();
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
   * @declaredat ASTNode:135
   */
  @Deprecated
  public ConstructorDecl fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:145
   */
  public ConstructorDecl treeCopyNoTransform() {
    ConstructorDecl tree = (ConstructorDecl) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 5:
          tree.children[i] = null;
          continue;
        }
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
   * @declaredat ASTNode:170
   */
  public ConstructorDecl treeCopy() {
    ConstructorDecl tree = (ConstructorDecl) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 5:
          tree.children[i] = null;
          continue;
        }
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
   * Replaces the Modifiers child.
   * @param node The new node to replace the Modifiers child.
   * @apilevel high-level
   */
  public ConstructorDecl setModifiers(Modifiers node) {
    setChild(node, 0);
    return this;
  }
  /**
   * Retrieves the Modifiers child.
   * @return The current node used as the Modifiers child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Modifiers")
  public Modifiers getModifiers() {
    return (Modifiers) getChild(0);
  }
  /**
   * Retrieves the Modifiers child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Modifiers child.
   * @apilevel low-level
   */
  public Modifiers getModifiersNoTransform() {
    return (Modifiers) getChildNoTransform(0);
  }
  /**
   * Replaces the lexeme ID.
   * @param value The new value for the lexeme ID.
   * @apilevel high-level
   */
  public ConstructorDecl setID(String value) {
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
  public ConstructorDecl setID(beaver.Symbol symbol) {
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
   * Replaces the Parameter list.
   * @param list The new list node to be used as the Parameter list.
   * @apilevel high-level
   */
  public ConstructorDecl setParameterList(List<ParameterDeclaration> list) {
    setChild(list, 1);
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
  public ParameterDeclaration getParameter(int i) {
    return (ParameterDeclaration) getParameterList().getChild(i);
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
  public ConstructorDecl addParameter(ParameterDeclaration node) {
    List<ParameterDeclaration> list = (parent == null) ? getParameterListNoTransform() : getParameterList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public ConstructorDecl addParameterNoTransform(ParameterDeclaration node) {
    List<ParameterDeclaration> list = getParameterListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the Parameter list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public ConstructorDecl setParameter(ParameterDeclaration node, int i) {
    List<ParameterDeclaration> list = getParameterList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the Parameter list.
   * @return The node representing the Parameter list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Parameter")
  public List<ParameterDeclaration> getParameterList() {
    List<ParameterDeclaration> list = (List<ParameterDeclaration>) getChild(1);
    return list;
  }
  /**
   * Retrieves the Parameter list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Parameter list.
   * @apilevel low-level
   */
  public List<ParameterDeclaration> getParameterListNoTransform() {
    return (List<ParameterDeclaration>) getChildNoTransform(1);
  }
  /**
   * @return the element at index {@code i} in the Parameter list without
   * triggering rewrites.
   */
  public ParameterDeclaration getParameterNoTransform(int i) {
    return (ParameterDeclaration) getParameterListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Parameter list.
   * @return The node representing the Parameter list.
   * @apilevel high-level
   */
  public List<ParameterDeclaration> getParameters() {
    return getParameterList();
  }
  /**
   * Retrieves the Parameter list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Parameter list.
   * @apilevel low-level
   */
  public List<ParameterDeclaration> getParametersNoTransform() {
    return getParameterListNoTransform();
  }
  /**
   * Replaces the Exception list.
   * @param list The new list node to be used as the Exception list.
   * @apilevel high-level
   */
  public ConstructorDecl setExceptionList(List<Access> list) {
    setChild(list, 2);
    return this;
  }
  /**
   * Retrieves the number of children in the Exception list.
   * @return Number of children in the Exception list.
   * @apilevel high-level
   */
  public int getNumException() {
    return getExceptionList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Exception list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the Exception list.
   * @apilevel low-level
   */
  public int getNumExceptionNoTransform() {
    return getExceptionListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Exception list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Exception list.
   * @apilevel high-level
   */
  public Access getException(int i) {
    return (Access) getExceptionList().getChild(i);
  }
  /**
   * Check whether the Exception list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasException() {
    return getExceptionList().getNumChild() != 0;
  }
  /**
   * Append an element to the Exception list.
   * @param node The element to append to the Exception list.
   * @apilevel high-level
   */
  public ConstructorDecl addException(Access node) {
    List<Access> list = (parent == null) ? getExceptionListNoTransform() : getExceptionList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public ConstructorDecl addExceptionNoTransform(Access node) {
    List<Access> list = getExceptionListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the Exception list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public ConstructorDecl setException(Access node, int i) {
    List<Access> list = getExceptionList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the Exception list.
   * @return The node representing the Exception list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Exception")
  public List<Access> getExceptionList() {
    List<Access> list = (List<Access>) getChild(2);
    return list;
  }
  /**
   * Retrieves the Exception list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Exception list.
   * @apilevel low-level
   */
  public List<Access> getExceptionListNoTransform() {
    return (List<Access>) getChildNoTransform(2);
  }
  /**
   * @return the element at index {@code i} in the Exception list without
   * triggering rewrites.
   */
  public Access getExceptionNoTransform(int i) {
    return (Access) getExceptionListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Exception list.
   * @return The node representing the Exception list.
   * @apilevel high-level
   */
  public List<Access> getExceptions() {
    return getExceptionList();
  }
  /**
   * Retrieves the Exception list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Exception list.
   * @apilevel low-level
   */
  public List<Access> getExceptionsNoTransform() {
    return getExceptionListNoTransform();
  }
  /**
   * Replaces the optional node for the ParsedConstructorInvocation child. This is the <code>Opt</code>
   * node containing the child ParsedConstructorInvocation, not the actual child!
   * @param opt The new node to be used as the optional node for the ParsedConstructorInvocation child.
   * @apilevel low-level
   */
  public ConstructorDecl setParsedConstructorInvocationOpt(Opt<Stmt> opt) {
    setChild(opt, 3);
    return this;
  }
  /**
   * Replaces the (optional) ParsedConstructorInvocation child.
   * @param node The new node to be used as the ParsedConstructorInvocation child.
   * @apilevel high-level
   */
  public ConstructorDecl setParsedConstructorInvocation(Stmt node) {
    getParsedConstructorInvocationOpt().setChild(node, 0);
    return this;
  }
  /**
   * Check whether the optional ParsedConstructorInvocation child exists.
   * @return {@code true} if the optional ParsedConstructorInvocation child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasParsedConstructorInvocation() {
    return getParsedConstructorInvocationOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) ParsedConstructorInvocation child.
   * @return The ParsedConstructorInvocation child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public Stmt getParsedConstructorInvocation() {
    return (Stmt) getParsedConstructorInvocationOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for the ParsedConstructorInvocation child. This is the <code>Opt</code> node containing the child ParsedConstructorInvocation, not the actual child!
   * @return The optional node for child the ParsedConstructorInvocation child.
   * @apilevel low-level
   */
  @ASTNodeAnnotation.OptChild(name="ParsedConstructorInvocation")
  public Opt<Stmt> getParsedConstructorInvocationOpt() {
    return (Opt<Stmt>) getChild(3);
  }
  /**
   * Retrieves the optional node for child ParsedConstructorInvocation. This is the <code>Opt</code> node containing the child ParsedConstructorInvocation, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child ParsedConstructorInvocation.
   * @apilevel low-level
   */
  public Opt<Stmt> getParsedConstructorInvocationOptNoTransform() {
    return (Opt<Stmt>) getChildNoTransform(3);
  }
  /**
   * Replaces the Block child.
   * @param node The new node to replace the Block child.
   * @apilevel high-level
   */
  public ConstructorDecl setBlock(Block node) {
    setChild(node, 4);
    return this;
  }
  /**
   * Retrieves the Block child.
   * @return The current node used as the Block child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Block")
  public Block getBlock() {
    return (Block) getChild(4);
  }
  /**
   * Retrieves the Block child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Block child.
   * @apilevel low-level
   */
  public Block getBlockNoTransform() {
    return (Block) getChildNoTransform(4);
  }
  /**
   * Retrieves the ImplicitConstructorInvocation child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the ImplicitConstructorInvocation child.
   * @apilevel low-level
   */
  public Stmt getImplicitConstructorInvocationNoTransform() {
    return (Stmt) getChildNoTransform(5);
  }
  /**
   * Retrieves the child position of the optional child ImplicitConstructorInvocation.
   * @return The the child position of the optional child ImplicitConstructorInvocation.
   * @apilevel low-level
   */
  protected int getImplicitConstructorInvocationChildPosition() {
    return 5;
  }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:305
   */
  private boolean refined_ErrorCheck_ConstructorDecl_checkImplicitConstructorInvocation()
{ return !hasParsedConstructorInvocation() && !hostType().isObject(); }
  /**
   * @attribute syn
   * @aspect AllMethods
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:38
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AllMethods", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:36")
  public boolean visible() {
    boolean visible_value = true;
    return visible_value;
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
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:265
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:263")
  public Set<InvocationTarget> implicitCalls() {
    ASTState state = state();
    if (implicitCalls_computed == ASTState.NON_CYCLE || implicitCalls_computed == state().cycle()) {
      return implicitCalls_value;
    }
    implicitCalls_value = implicitCalls_compute();
    if (state().inCircle()) {
      implicitCalls_computed = state().cycle();
    
    } else {
      implicitCalls_computed = ASTState.NON_CYCLE;
    
    }
    return implicitCalls_value;
  }
  /** @apilevel internal */
  private Set<InvocationTarget> implicitCalls_compute() {
      Set<InvocationTarget> implicitCallTargets = new LinkedHashSet<>();
      implicitCallTargets.addAll(
          ((ClassDecl)hostType()).getInstanceInitailizer());
      implicitCallTargets.addAll(((ClassDecl)hostType()).getStaticInitailizer());
      return implicitCallTargets;
    }
  /** @apilevel internal */
  private void fullSignature_reset() {
    fullSignature_computed = null;
    fullSignature_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle fullSignature_computed = null;

  /** @apilevel internal */
  protected String fullSignature_value;

  /** Method signature, including type arguments.  
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:299
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:299")
  public String fullSignature() {
    ASTState state = state();
    if (fullSignature_computed == ASTState.NON_CYCLE || fullSignature_computed == state().cycle()) {
      return fullSignature_value;
    }
    fullSignature_value = fullSignature_compute();
    if (state().inCircle()) {
      fullSignature_computed = state().cycle();
    
    } else {
      fullSignature_computed = ASTState.NON_CYCLE;
    
    }
    return fullSignature_value;
  }
  /** @apilevel internal */
  private String fullSignature_compute() {
      StringBuilder sb = new StringBuilder();
      String anonymous = "";
      if (hostType() instanceof AnonymousDecl) {
        anonymous = "." + hashCode();
      }
      sb.append(anonymous);
      sb.append(name() + "(");
      for (int i = 0; i < getNumParameter(); i++) {
        if (i != 0) {
          sb.append(", ");
        }
        TypeDecl paramType = getParameter(i).type();
        if (paramType instanceof PrimitiveType) {
          sb.append(paramType.typeName());
        } else {
          sb.append(paramType.fullName());
        }
      }
      sb.append(")");
      return sb.toString();
    }
  /** @apilevel internal */
  private void name_reset() {
    name_computed = null;
    name_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle name_computed = null;

  /** @apilevel internal */
  protected String name_value;

  /**
   * @attribute syn
   * @aspect ConstructorDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:159
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructorDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:159")
  public String name() {
    ASTState state = state();
    if (name_computed == ASTState.NON_CYCLE || name_computed == state().cycle()) {
      return name_value;
    }
    name_value = getID();
    if (state().inCircle()) {
      name_computed = state().cycle();
    
    } else {
      name_computed = ASTState.NON_CYCLE;
    
    }
    return name_value;
  }
  /** @apilevel internal */
  private void signature_reset() {
    signature_computed = null;
    signature_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle signature_computed = null;

  /** @apilevel internal */
  protected String signature_value;

  /**
   * @attribute syn
   * @aspect ConstructorDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:161
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructorDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:161")
  public String signature() {
    ASTState state = state();
    if (signature_computed == ASTState.NON_CYCLE || signature_computed == state().cycle()) {
      return signature_value;
    }
    signature_value = signature_compute();
    if (state().inCircle()) {
      signature_computed = state().cycle();
    
    } else {
      signature_computed = ASTState.NON_CYCLE;
    
    }
    return signature_value;
  }
  /** @apilevel internal */
  private String signature_compute() {
      StringBuilder s = new StringBuilder();
      s.append(name() + "(");
      for (int i = 0; i < getNumParameter(); i++) {
        s.append(getParameter(i).type().typeName());
        if (i != getNumParameter() - 1) {
          s.append(", ");
        }
      }
      s.append(")");
      return s.toString();
    }
  /** @apilevel internal */
  private void sameSignature_ConstructorDecl_reset() {
    sameSignature_ConstructorDecl_computed = null;
    sameSignature_ConstructorDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map sameSignature_ConstructorDecl_values;
  /** @apilevel internal */
  protected java.util.Map sameSignature_ConstructorDecl_computed;
  /**
   * @attribute syn
   * @aspect ConstructorDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:175
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructorDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:175")
  public boolean sameSignature(ConstructorDecl c) {
    Object _parameters = c;
    if (sameSignature_ConstructorDecl_computed == null) sameSignature_ConstructorDecl_computed = new java.util.HashMap(4);
    if (sameSignature_ConstructorDecl_values == null) sameSignature_ConstructorDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (sameSignature_ConstructorDecl_values.containsKey(_parameters)
        && sameSignature_ConstructorDecl_computed.containsKey(_parameters)
        && (sameSignature_ConstructorDecl_computed.get(_parameters) == ASTState.NON_CYCLE || sameSignature_ConstructorDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) sameSignature_ConstructorDecl_values.get(_parameters);
    }
    boolean sameSignature_ConstructorDecl_value = sameSignature_compute(c);
    if (state().inCircle()) {
      sameSignature_ConstructorDecl_values.put(_parameters, sameSignature_ConstructorDecl_value);
      sameSignature_ConstructorDecl_computed.put(_parameters, state().cycle());
    
    } else {
      sameSignature_ConstructorDecl_values.put(_parameters, sameSignature_ConstructorDecl_value);
      sameSignature_ConstructorDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return sameSignature_ConstructorDecl_value;
  }
  /** @apilevel internal */
  private boolean sameSignature_compute(ConstructorDecl c) {
      if (!name().equals(c.name())) {
        return false;
      }
      if (c.getNumParameter() != getNumParameter()) {
        return false;
      }
      for (int i = 0; i < getNumParameter(); i++) {
        if (!c.getParameter(i).type().equals(getParameter(i).type())) {
          return false;
        }
      }
      return true;
    }
  /**
   * @attribute syn
   * @aspect ConstructorDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:190
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructorDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:190")
  public boolean moreSpecificThan(ConstructorDecl m) {
    boolean moreSpecificThan_ConstructorDecl_value = m.lessSpecificThan(this) && !this.lessSpecificThan(m);
    return moreSpecificThan_ConstructorDecl_value;
  }
  /** @apilevel internal */
  private void lessSpecificThan_ConstructorDecl_reset() {
    lessSpecificThan_ConstructorDecl_computed = null;
    lessSpecificThan_ConstructorDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map lessSpecificThan_ConstructorDecl_values;
  /** @apilevel internal */
  protected java.util.Map lessSpecificThan_ConstructorDecl_computed;
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:269
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructorDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:193")
  public boolean lessSpecificThan(ConstructorDecl m) {
    Object _parameters = m;
    if (lessSpecificThan_ConstructorDecl_computed == null) lessSpecificThan_ConstructorDecl_computed = new java.util.HashMap(4);
    if (lessSpecificThan_ConstructorDecl_values == null) lessSpecificThan_ConstructorDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (lessSpecificThan_ConstructorDecl_values.containsKey(_parameters)
        && lessSpecificThan_ConstructorDecl_computed.containsKey(_parameters)
        && (lessSpecificThan_ConstructorDecl_computed.get(_parameters) == ASTState.NON_CYCLE || lessSpecificThan_ConstructorDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) lessSpecificThan_ConstructorDecl_values.get(_parameters);
    }
    boolean lessSpecificThan_ConstructorDecl_value = lessSpecificThan_compute(m);
    if (state().inCircle()) {
      lessSpecificThan_ConstructorDecl_values.put(_parameters, lessSpecificThan_ConstructorDecl_value);
      lessSpecificThan_ConstructorDecl_computed.put(_parameters, state().cycle());
    
    } else {
      lessSpecificThan_ConstructorDecl_values.put(_parameters, lessSpecificThan_ConstructorDecl_value);
      lessSpecificThan_ConstructorDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return lessSpecificThan_ConstructorDecl_value;
  }
  /** @apilevel internal */
  private boolean lessSpecificThan_compute(ConstructorDecl m) {
      // Here we have a non-obvious precondition: either both constructors are
      // variable arity or both are fixed arity.
      // An applicable fixed arity constructors is always chosen instead of an
      // applicable variable arity constructors, so a fixed arity constructors and
      // a variable arity constructors will not be compared for most specificity.
      if (!isVariableArity()) {
        // Both constructors have fixed arity.
        for (int i = 0; i < getNumParameter(); i++) {
          TypeDecl t1 = getParameter(i).type();
          TypeDecl t2 = m.getParameter(i).type();
          if (!t1.subtype(t2) && !t1.withinBounds(t2)) {
            return true;
          }
        }
      } else {
        // Both constructors have variable arity.
        int numA = getNumParameter();
        int numB = m.getNumParameter();
        int num = Math.min(numA, numB);
        for (int i = 0; i < num - 1; i++) {
          TypeDecl t1 = getParameter(i).type();
          TypeDecl t2 = m.getParameter(i).type();
          if (!t1.subtype(t2) && !t1.withinBounds(t2)) {
            return true;
          }
        }
        if (numA <= numB) {
          for (int i = num - 1; i < numB - 1; i++) {
            TypeDecl t1 = getParameter(numA - 1).type().componentType();
            TypeDecl t2 = m.getParameter(i).type();
            if (!t1.subtype(t2) && !t1.withinBounds(t2)) {
              return true;
            }
          }
          TypeDecl t1 = getParameter(numA - 1).type().componentType();
          TypeDecl t2 = m.getParameter(numB - 1).type().componentType();
          if (!t1.subtype(t2) && !t1.withinBounds(t2)) {
            return true;
          }
        } else {
          for (int i = num - 1; i < numA - 1; i++) {
            TypeDecl t1 = getParameter(i).type();
            TypeDecl t2 = m.getParameter(numB - 1).type().componentType();
            if (!t1.subtype(t2) && !t1.withinBounds(t2)) {
              return true;
            }
          }
          TypeDecl t1 = getParameter(numA - 1).type().componentType();
          TypeDecl t2 = m.getParameter(numB - 1).type().componentType();
          if (!t1.subtype(t2) && !t1.withinBounds(t2)) {
            return true;
          }
        }
      }
      return false;
    }
  /**
   * @return true if this is an auto-generated default constructor
   * @attribute syn
   * @aspect ImplicitConstructor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:246
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ImplicitConstructor", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:246")
  public boolean isImplicitConstructor() {
    boolean isImplicitConstructor_value = isImplicitConstructor;
    return isImplicitConstructor_value;
  }
  /** @apilevel internal */
  private void getImplicitConstructorInvocation_reset() {
    getImplicitConstructorInvocation_computed = false;
    
    getImplicitConstructorInvocation_value = null;
  }
  /** @apilevel internal */
  protected boolean getImplicitConstructorInvocation_computed = false;

  /** @apilevel internal */
  protected Stmt getImplicitConstructorInvocation_value;

  /**
   * Nonterminal attribute for implicit constructor invocation.
   * This is used when an explicit constructor invocation is missing
   * in a constructor declaration.
   * 
   * The implicit constructor invocation used to be inserted in the
   * same node where the parsed constructor declaration was stored.
   * This meant that it was impossible to distinguish a parsed constructor
   * from an implicit one.
   * @attribute syn nta
   * @aspect ImplicitConstructor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:350
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="ImplicitConstructor", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:350")
  public Stmt getImplicitConstructorInvocation() {
    ASTState state = state();
    if (getImplicitConstructorInvocation_computed) {
      return (Stmt) getChild(getImplicitConstructorInvocationChildPosition());
    }
    state().enterLazyAttribute();
    getImplicitConstructorInvocation_value = new ExprStmt(new SuperConstructorAccess("super", new List<Expr>()));
    setChild(getImplicitConstructorInvocation_value, getImplicitConstructorInvocationChildPosition());
    getImplicitConstructorInvocation_computed = true;
    state().leaveLazyAttribute();
    Stmt node = (Stmt) this.getChild(getImplicitConstructorInvocationChildPosition());
    return node;
  }
  /**
   * Test if there is an explicit or implicit constructor invocation available.
   * This should be false only if the host type is java.lang.Object.
   * @return {@code true} if there is a constructor invocation.
   * @attribute syn
   * @aspect ImplicitConstructor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:358
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ImplicitConstructor", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:358")
  public boolean hasConstructorInvocation() {
    boolean hasConstructorInvocation_value = hasParsedConstructorInvocation() || !hostType().isObject();
    return hasConstructorInvocation_value;
  }
  /**
   * @attribute syn
   * @aspect ImplicitConstructor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:361
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ImplicitConstructor", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:361")
  public Stmt getConstructorInvocation() {
    {
        if (hasParsedConstructorInvocation()) {
          return getParsedConstructorInvocation();
        } else {
          return getImplicitConstructorInvocation();
        }
      }
  }
  /** @apilevel internal */
  private void throwsException_TypeDecl_reset() {
    throwsException_TypeDecl_computed = null;
    throwsException_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map throwsException_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map throwsException_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect ExceptionHandling
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:222
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:222")
  public boolean throwsException(TypeDecl exceptionType) {
    Object _parameters = exceptionType;
    if (throwsException_TypeDecl_computed == null) throwsException_TypeDecl_computed = new java.util.HashMap(4);
    if (throwsException_TypeDecl_values == null) throwsException_TypeDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (throwsException_TypeDecl_values.containsKey(_parameters)
        && throwsException_TypeDecl_computed.containsKey(_parameters)
        && (throwsException_TypeDecl_computed.get(_parameters) == ASTState.NON_CYCLE || throwsException_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) throwsException_TypeDecl_values.get(_parameters);
    }
    boolean throwsException_TypeDecl_value = throwsException_compute(exceptionType);
    if (state().inCircle()) {
      throwsException_TypeDecl_values.put(_parameters, throwsException_TypeDecl_value);
      throwsException_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      throwsException_TypeDecl_values.put(_parameters, throwsException_TypeDecl_value);
      throwsException_TypeDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return throwsException_TypeDecl_value;
  }
  /** @apilevel internal */
  private boolean throwsException_compute(TypeDecl exceptionType) {
      for (Access exception : getExceptionList()) {
        if (exceptionType.subtype(exception.type())) {
          return true;
        }
      }
      return false;
    }
  /** @apilevel internal */
  private void accessibleFrom_TypeDecl_reset() {
    accessibleFrom_TypeDecl_computed = null;
    accessibleFrom_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map accessibleFrom_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map accessibleFrom_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect AccessControl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AccessControl.jrag:122
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AccessControl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AccessControl.jrag:122")
  public boolean accessibleFrom(TypeDecl type) {
    Object _parameters = type;
    if (accessibleFrom_TypeDecl_computed == null) accessibleFrom_TypeDecl_computed = new java.util.HashMap(4);
    if (accessibleFrom_TypeDecl_values == null) accessibleFrom_TypeDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (accessibleFrom_TypeDecl_values.containsKey(_parameters)
        && accessibleFrom_TypeDecl_computed.containsKey(_parameters)
        && (accessibleFrom_TypeDecl_computed.get(_parameters) == ASTState.NON_CYCLE || accessibleFrom_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) accessibleFrom_TypeDecl_values.get(_parameters);
    }
    boolean accessibleFrom_TypeDecl_value = accessibleFrom_compute(type);
    if (state().inCircle()) {
      accessibleFrom_TypeDecl_values.put(_parameters, accessibleFrom_TypeDecl_value);
      accessibleFrom_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      accessibleFrom_TypeDecl_values.put(_parameters, accessibleFrom_TypeDecl_value);
      accessibleFrom_TypeDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return accessibleFrom_TypeDecl_value;
  }
  /** @apilevel internal */
  private boolean accessibleFrom_compute(TypeDecl type) {
      if (!hostType().accessibleFrom(type)) {
        return false;
      } else if (isPublic()) {
        return true;
      } else if (isProtected()) {
        return true;
      } else if (isPrivate()) {
        return hostType().topLevelType() == type.topLevelType();
      } else {
        return hostPackage().equals(type.hostPackage());
      }
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
        new_assignedAfter_Variable_value = getBlock().assignedAfter(v) && getBlock().assignedAfterReturn(v);
        if (((Boolean)_value.value) != new_assignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_assignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      assignedAfter_Variable_values.put(_parameters, new_assignedAfter_Variable_value);
      state.startLastCycle();
      boolean $tmp = getBlock().assignedAfter(v) && getBlock().assignedAfterReturn(v);

      state.leaveCircle();
      return new_assignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_assignedAfter_Variable_value = getBlock().assignedAfter(v) && getBlock().assignedAfterReturn(v);
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
        new_unassignedAfter_Variable_value = getBlock().unassignedAfter(v) && getBlock().unassignedAfterReturn(v);
        if (((Boolean)_value.value) != new_unassignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfter_Variable_values.put(_parameters, new_unassignedAfter_Variable_value);
      state.startLastCycle();
      boolean $tmp = getBlock().unassignedAfter(v) && getBlock().unassignedAfterReturn(v);

      state.leaveCircle();
      return new_unassignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfter_Variable_value = getBlock().unassignedAfter(v) && getBlock().unassignedAfterReturn(v);
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
   * @aspect TypeCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:571
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:571")
  public Collection<Problem> typeProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        // 8.8.4 (8.4.4)
        TypeDecl exceptionType = typeThrowable();
        for (int i = 0; i < getNumException(); i++) {
          TypeDecl typeDecl = getException(i).type();
          if (!typeDecl.subtype(exceptionType)) {
            problems.add(errorf("%s throws non throwable type %s", signature(), typeDecl.fullName()));
          }
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:253
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:253")
  public boolean isSynthetic() {
    boolean isSynthetic_value = getModifiers().isSynthetic();
    return isSynthetic_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:272
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:272")
  public boolean isPublic() {
    boolean isPublic_value = getModifiers().isPublic();
    return isPublic_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:273
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:273")
  public boolean isPrivate() {
    boolean isPrivate_value = getModifiers().isPrivate();
    return isPrivate_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:274
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:274")
  public boolean isProtected() {
    boolean isProtected_value = getModifiers().isProtected();
    return isProtected_value;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:183
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:183")
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
          return (ParameterDeclaration) getParameter(i);
        }
      }
      return emptySet();
    }
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:111
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:111")
  public Collection<Problem> nameProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        // 8.8
        if (!hostType().name().equals(name())) {
          problems.add(errorf(
              "constructor %s does not have the same name as the simple name of the host class %s",
              name(), hostType().name()));
        }
    
        // 8.8.2
        if (hostType().lookupConstructor(this) != this) {
          problems.add(errorf("constructor with signature %s is multiply declared in type %s",
              signature(), hostType().typeName()));
        }
    
        if (circularThisInvocation(this)) {
          problems.add(errorf("The constructor %s may not directly or indirectly invoke itself",
              signature()));
        }
        return problems;
      }
  }
  /** @apilevel internal */
  private void circularThisInvocation_ConstructorDecl_reset() {
    circularThisInvocation_ConstructorDecl_computed = null;
    circularThisInvocation_ConstructorDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map circularThisInvocation_ConstructorDecl_values;
  /** @apilevel internal */
  protected java.util.Map circularThisInvocation_ConstructorDecl_computed;
  /**
   * @return {@code true} if this constructor (possibly indirectly) calls the given constructor.
   * @attribute syn
   * @aspect NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:136
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:136")
  public boolean circularThisInvocation(ConstructorDecl decl) {
    Object _parameters = decl;
    if (circularThisInvocation_ConstructorDecl_computed == null) circularThisInvocation_ConstructorDecl_computed = new java.util.HashMap(4);
    if (circularThisInvocation_ConstructorDecl_values == null) circularThisInvocation_ConstructorDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (circularThisInvocation_ConstructorDecl_values.containsKey(_parameters)
        && circularThisInvocation_ConstructorDecl_computed.containsKey(_parameters)
        && (circularThisInvocation_ConstructorDecl_computed.get(_parameters) == ASTState.NON_CYCLE || circularThisInvocation_ConstructorDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) circularThisInvocation_ConstructorDecl_values.get(_parameters);
    }
    boolean circularThisInvocation_ConstructorDecl_value = circularThisInvocation_compute(decl);
    if (state().inCircle()) {
      circularThisInvocation_ConstructorDecl_values.put(_parameters, circularThisInvocation_ConstructorDecl_value);
      circularThisInvocation_ConstructorDecl_computed.put(_parameters, state().cycle());
    
    } else {
      circularThisInvocation_ConstructorDecl_values.put(_parameters, circularThisInvocation_ConstructorDecl_value);
      circularThisInvocation_ConstructorDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return circularThisInvocation_ConstructorDecl_value;
  }
  /** @apilevel internal */
  private boolean circularThisInvocation_compute(ConstructorDecl decl) {
      if (hasConstructorInvocation()) {
        Expr e = ((ExprStmt) getConstructorInvocation()).getExpr();
        if (e instanceof ConstructorAccess) {
          ConstructorDecl constructorDecl = ((ConstructorAccess) e).decl();
          if (constructorDecl == decl) {
            return true;
          }
          return constructorDecl.circularThisInvocation(decl);
        }
      }
      return false;
    }
  /**
   * Safe parameter type access.
   * 
   * @return the type of the parameter at the given index, or
   * UnknownType if there is not parameter at the given index.
   * @attribute syn
   * @aspect LookupMethod
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:72
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupMethod", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:72")
  public TypeDecl paramType(int index) {
    TypeDecl paramType_int_value = index >= 0 && index < getNumParameter()
          ? getParameter(index).type()
          : unknownType();
    return paramType_int_value;
  }
  /**
   * Attribute to determine if the implicit constructor invocation should
   * be checked for semantic errors.
   * 
   * @return {@code true} if this constructor declaration has an implicit
   * constructor invocation
   * @attribute syn
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:126
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ErrorCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:305")
  public boolean checkImplicitConstructorInvocation() {
    boolean checkImplicitConstructorInvocation_value = !isOriginalEnumConstructor() && refined_ErrorCheck_ConstructorDecl_checkImplicitConstructorInvocation();
    return checkImplicitConstructorInvocation_value;
  }
  /**
   * @attribute syn
   * @aspect PrettyPrintUtil
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:332
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PrettyPrintUtil", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:332")
  public boolean hasModifiers() {
    boolean hasModifiers_value = getModifiers().getNumModifier() > 0;
    return hasModifiers_value;
  }
  /**
   * @attribute syn
   * @aspect PrettyPrintUtil
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:342
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PrettyPrintUtil", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:342")
  public boolean hasExceptions() {
    boolean hasExceptions_value = getNumException() > 0;
    return hasExceptions_value;
  }
  /**
   * @attribute syn
   * @aspect PrettyPrintUtil
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:344
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PrettyPrintUtil", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:344")
  public List<Stmt> blockStmts() {
    List<Stmt> blockStmts_value = getBlock().getStmtList();
    return blockStmts_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:287
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:287")
  public TypeDecl type() {
    TypeDecl type_value = unknownType();
    return type_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:292
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:290")
  public boolean isVoid() {
    boolean isVoid_value = true;
    return isVoid_value;
  }
  /**
   * @attribute syn
   * @aspect VariableArityParameters
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:56
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableArityParameters", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:56")
  public boolean isVariableArity() {
    boolean isVariableArity_value = getNumParameter() == 0 ? false : getParameter(getNumParameter() - 1).isVariableArity();
    return isVariableArity_value;
  }
  /**
   * @attribute syn
   * @aspect VariableArityParameters
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:95
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableArityParameters", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:95")
  public ParameterDeclaration lastParameter() {
    ParameterDeclaration lastParameter_value = getParameter(getNumParameter() - 1);
    return lastParameter_value;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:433
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:425")
  public boolean hasAnnotationSuppressWarnings(String annot) {
    boolean hasAnnotationSuppressWarnings_String_value = getModifiers().hasAnnotationSuppressWarnings(annot);
    return hasAnnotationSuppressWarnings_String_value;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:484
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:484")
  public boolean isDeprecated() {
    boolean isDeprecated_value = getModifiers().hasDeprecatedAnnotation();
    return isDeprecated_value;
  }
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1737
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1735")
  public boolean isSubstitutable() {
    boolean isSubstitutable_value = true;
    return isSubstitutable_value;
  }
  /** @apilevel internal */
  private void sourceConstructorDecl_reset() {
    sourceConstructorDecl_computed = null;
    sourceConstructorDecl_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle sourceConstructorDecl_computed = null;

  /** @apilevel internal */
  protected ConstructorDecl sourceConstructorDecl_value;

  /**
   * @attribute syn
   * @aspect SourceDeclarations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1918
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SourceDeclarations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1918")
  public ConstructorDecl sourceConstructorDecl() {
    ASTState state = state();
    if (sourceConstructorDecl_computed == ASTState.NON_CYCLE || sourceConstructorDecl_computed == state().cycle()) {
      return sourceConstructorDecl_value;
    }
    sourceConstructorDecl_value = this;
    if (state().inCircle()) {
      sourceConstructorDecl_computed = state().cycle();
    
    } else {
      sourceConstructorDecl_computed = ASTState.NON_CYCLE;
    
    }
    return sourceConstructorDecl_value;
  }
  /** @apilevel internal */
  private void transformed_reset() {
    transformed_computed = null;
    transformed_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle transformed_computed = null;

  /** @apilevel internal */
  protected ConstructorDecl transformed_value;

  /**
   * @attribute syn
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:140
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Enums", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:140")
  public ConstructorDecl transformed() {
    ASTState state = state();
    if (transformed_computed == ASTState.NON_CYCLE || transformed_computed == state().cycle()) {
      return transformed_value;
    }
    transformed_value = transformed_compute();
    if (state().inCircle()) {
      transformed_computed = state().cycle();
    
    } else {
      transformed_computed = ASTState.NON_CYCLE;
    
    }
    return transformed_value;
  }
  /** @apilevel internal */
  private ConstructorDecl transformed_compute() {
      if (isOriginalEnumConstructor()) {
        return transformedEnumConstructor();
      } else {
        return this;
      }
    }
  /** @apilevel internal */
  private void transformedEnumConstructor_reset() {
    transformedEnumConstructor_computed = false;
    
    transformedEnumConstructor_value = null;
  }
  /** @apilevel internal */
  protected boolean transformedEnumConstructor_computed = false;

  /** @apilevel internal */
  protected ConstructorDecl transformedEnumConstructor_value;

  /**
   * @attribute syn
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:148
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="Enums", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:148")
  public ConstructorDecl transformedEnumConstructor() {
    ASTState state = state();
    if (transformedEnumConstructor_computed) {
      return transformedEnumConstructor_value;
    }
    state().enterLazyAttribute();
    transformedEnumConstructor_value = transformedEnumConstructor_compute();
    transformedEnumConstructor_value.setParent(this);
    transformedEnumConstructor_computed = true;
    state().leaveLazyAttribute();
    return transformedEnumConstructor_value;
  }
  /** @apilevel internal */
  private ConstructorDecl transformedEnumConstructor_compute() {
      List<ParameterDeclaration> parameters = new List<ParameterDeclaration>();
      parameters.add(new ParameterDeclaration(new TypeAccess("java.lang", "String"), "@p0"));
      parameters.add(new ParameterDeclaration(new TypeAccess("int"), "@p1"));
      for (ParameterDeclaration param : getParameterList()) {
        parameters.add(param.treeCopyNoTransform());
      }
      ConstructorAccess constructorInvocation;
      List<Expr> args = new List<Expr>();
      args.add(new VarAccess("@p0"));
      args.add(new VarAccess("@p1"));
      if (hasParsedConstructorInvocation()) {
        ExprStmt invocation = (ExprStmt) getParsedConstructorInvocation();
        ConstructorAccess access = (ConstructorAccess) invocation.getExpr();
        for (Expr arg : access.getArgList()) {
          args.add(arg.treeCopyNoTransform());
        }
        if (access instanceof SuperConstructorAccess) {
          constructorInvocation = new SuperConstructorAccess("super", args);
        } else {
          constructorInvocation = new ConstructorAccess(access.getID(), args);
        }
      } else {
        constructorInvocation = new SuperConstructorAccess("super", args);
      }
      return new ConstructorDecl(
          getModifiers().treeCopyNoTransform(),
          getID(),
          parameters,
          getExceptionList().treeCopyNoTransform(),
          new Opt<Stmt>(new ExprStmt(constructorInvocation)),
          getBlock().treeCopyNoTransform());
    }
  /**
   * Check if the enum constructor has an incorrect access modifier
   * @attribute syn
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:577
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Enums", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:577")
  public Collection<Problem> enumProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        if (hostType().isEnumDecl()) {
          if (isPublic()) {
            problems.add(error("enum constructors cannot be declared public"));
          } else if (isProtected()) {
            problems.add(error("enum constructors cannot be declared public"));
          }
    
          if (hasParsedConstructorInvocation()) {
            ExprStmt invocation = (ExprStmt) getParsedConstructorInvocation();
            if (invocation.getExpr() instanceof SuperConstructorAccess) {
              problems.add(error("cannot call super() in enum constructor"));
            }
          }
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:341
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:341")
  public boolean applicableBySubtyping(List<Expr> argList) {
    {
        if (getNumParameter() != argList.getNumChild()) {
          return false;
        }
        for (int i = 0; i < getNumParameter(); i++) {
          TypeDecl arg = argList.getChild(i).type();
          TypeDecl param = getParameter(i).type();
          if (!arg.subtype(param)) {
            return false;
          }
        }
        return true;
      }
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:367
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:367")
  public boolean applicableByMethodInvocationConversion(List<Expr> argList) {
    {
        if (getNumParameter() != argList.getNumChild()) {
          return false;
        }
        for (int i = 0; i < getNumParameter(); i++) {
          TypeDecl arg = argList.getChild(i).type();
          if (!arg.methodInvocationConversionTo(getParameter(i).type())) {
            return false;
          }
        }
        return true;
      }
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:395
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:395")
  public boolean applicableVariableArity(List argList) {
    {
        for (int i = 0; i < getNumParameter() - 1; i++) {
          TypeDecl arg = ((Expr) argList.getChild(i)).type();
          if (!arg.methodInvocationConversionTo(getParameter(i).type())) {
            return false;
          }
        }
        for (int i = getNumParameter() - 1; i < argList.getNumChild(); i++) {
          TypeDecl arg = ((Expr) argList.getChild(i)).type();
          if (!arg.methodInvocationConversionTo(lastParameter().type().componentType())) {
            return false;
          }
        }
        return true;
      }
  }
  /**
   * Note: isGeneric must be called first to check if this declaration is generic.
   * Otherwise this attribute will throw an error!
   * @return original generic declaration of this constructor.
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:437
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:437")
  public GenericConstructorDecl genericDecl() {
    {
        throw new Error("cannot evaulate generic declaration of non-generic constructor");
      }
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:685
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:685")
  public boolean potentiallyApplicable(List<Expr> argList) {
    {
        int argArity = argList.getNumChild();
        if (!isVariableArity()) {
          if (argArity != arity()) {
            return false;
          }
          for (int i = 0; i < argArity; i++) {
            Expr expr = argList.getChild(i);
            if (!expr.potentiallyCompatible(getParameter(i).type(), this)) {
              return false;
            }
          }
        } else {
        //if (isVariableArity()) {
          if (!(argArity >= arity() - 1)) {
            return false;
          }
          for (int i = 0; i < arity() - 2; i++) {
            Expr expr = argList.getChild(i);
            if (!expr.potentiallyCompatible(getParameter(i).type(), this)) {
              return false;
            }
          }
          TypeDecl varArgType = getParameter(arity() - 1).type();
          if (argArity == arity()) {
            Expr expr = argList.getChild(argArity - 1);
            if (!expr.potentiallyCompatible(varArgType, this)
                && !expr.potentiallyCompatible(varArgType.componentType(), this)) {
              return false;
            }
          } else if (argArity > arity()) {
            for (int i = arity() - 1; i < argArity; i++) {
              Expr expr = argList.getChild(i);
              if (!expr.potentiallyCompatible(varArgType.componentType(), this)) {
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:695
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:695")
  public int arity() {
    int arity_value = getNumParameter();
    return arity_value;
  }
  /**
   * @see AST.Modifiers#hasAnnotationSafeVarargs() Modifiers.hasAnnotationSafeVarargs()
   * @attribute syn
   * @aspect SafeVarargs
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SafeVarargs.jrag:56
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SafeVarargs", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SafeVarargs.jrag:41")
  public boolean hasAnnotationSafeVarargs() {
    boolean hasAnnotationSafeVarargs_value = getModifiers().hasAnnotationSafeVarargs();
    return hasAnnotationSafeVarargs_value;
  }
  /**
   * @attribute syn
   * @aspect SafeVarargs
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SafeVarargs.jrag:93
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SafeVarargs", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SafeVarargs.jrag:93")
  public Collection<Problem> safeVarargsProblems() {
    {
        if (hasAnnotationSafeVarargs()) {
          if (!isVariableArity()) {
            return Collections.singleton(errorf(
                  "illegal use of @SafeVarargs on non-varargs constructor.", name()));
          }
        }
        return Collections.emptySet();
      }
  }
  /**
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:1068
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:1068")
  public boolean applicableByStrictInvocation(Expr expr, List<Expr> argList) {
    {
        if (getNumParameter() != argList.getNumChild()) {
          return false;
        }
        for (int i = 0; i < getNumParameter(); i++) {
          Expr arg = argList.getChild(i);
          if (!arg.pertinentToApplicability(expr, this, i)) {
            continue;
          }
          if (!arg.compatibleStrictContext(getParameter(i).type())) {
            return false;
          }
        }
        return true;
      }
  }
  /**
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:1084
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:1084")
  public boolean applicableByLooseInvocation(Expr expr, List<Expr> argList) {
    {
        if (getNumParameter() != argList.getNumChild()) {
          return false;
        }
        for (int i = 0; i < getNumParameter(); i++) {
          Expr arg = argList.getChild(i);
          if (!arg.pertinentToApplicability(expr, this, i)) {
            continue;
          }
          if (!arg.compatibleLooseContext(getParameter(i).type())) {
            return false;
          }
        }
        return true;
      }
  }
  /**
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:1100
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:1100")
  public boolean applicableByVariableArityInvocation(Expr expr, List<Expr> argList) {
    {
        for (int i = 0; i < getNumParameter() - 1; i++) {
          Expr arg = argList.getChild(i);
          if (!arg.pertinentToApplicability(expr, this, i)) {
            continue;
          }
          if (!arg.compatibleLooseContext(getParameter(i).type())) {
            return false;
          }
        }
        for (int i = getNumParameter() - 1; i < argList.getNumChild(); i++) {
          Expr arg = argList.getChild(i);
          if (!arg.pertinentToApplicability(expr, this, i)) {
            continue;
          }
          if (!arg.compatibleLooseContext(lastParameter().type().componentType())) {
            return false;
          }
        }
        return true;
      }
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:64
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:40")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = getBlock().modifiedInScope(var);
    return modifiedInScope_Variable_value;
  }
  /**
   * @attribute syn
   * @aspect requests
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Requests.jrag:30
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="requests", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Requests.jrag:30")
  public Map<String, java.util.List<String>> parameterPointers() {
    {
            Map<String, java.util.List<String>> res = new HashMap<>();
            for (ParameterDeclaration param : getParameterList()) {
                if (param.type().isPrimitive()) {
                    continue;
                }
                java.util.List<String> types = pointsToSet().get(param).stream().map(allocsite -> allocsite.typeName()).distinct().collect(Collectors.toList());
                res.put(param.getID(), types);
    
            }
    
            return res;
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
    {
            Map<String, java.util.List<String>> res = new HashMap<>();
            for (ParameterDeclaration param : getParameterList()) {
                if (param.type().isPrimitive()) {
                    continue;
                }
                java.util.List<String> types = program()
                                                  .paramMap()
                                                  .getOrDefault(param, Collections.emptySet()).stream()
                                                  .filter(allocsite -> allocsite.atype().subtype(param.type()))
                                                  .map(allocsite -> allocsite.typeName())
                                                  .distinct()
                                                  .collect(Collectors.toList());
                res.put(param.getID(), types);
            }
    
            return res;
        }
  }
  /**
   * @attribute syn
   * @aspect WholeProgram
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/WholeProgram.jrag:69
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="WholeProgram", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/WholeProgram.jrag:69")
  public boolean hasPointerParameter() {
    {
            for (ParameterDeclaration pd : getParameterList()) {
                if (!pd.type().isPrimitive()) return true;
            }
            return false;
        }
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:276
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:276")
  public String targetSignature() {
    ASTState state = state();
    if (targetSignature_computed == ASTState.NON_CYCLE || targetSignature_computed == state().cycle()) {
      return targetSignature_value;
    }
    targetSignature_value = (program().mergeNames
               ? ""
               : hostType().packageName() + "." + hostType().name()) +
          "::" + (program().mergeNames ? name() : fullSignature());
    if (state().inCircle()) {
      targetSignature_computed = state().cycle();
    
    } else {
      targetSignature_computed = ASTState.NON_CYCLE;
    
    }
    return targetSignature_value;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:94
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:94")
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
   * @aspect TypeAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:286
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:286")
  public TypeDecl unknownType() {
    TypeDecl unknownType_value = getParent().Define_unknownType(this, null);
    return unknownType_value;
  }
  /**
   * @attribute inh
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:129
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Enums", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:129")
  public boolean isOriginalEnumConstructor() {
    boolean isOriginalEnumConstructor_value = getParent().Define_isOriginalEnumConstructor(this, null);
    return isOriginalEnumConstructor_value;
  }
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:44
   * @apilevel internal
   */
  public boolean Define_isMethodParameter(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:89
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else {
      return getParent().Define_isMethodParameter(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:44
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isMethodParameter
   */
  protected boolean canDefine_isMethodParameter(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:45
   * @apilevel internal
   */
  public boolean Define_isConstructorParameter(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:90
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return true;
    }
    else {
      return getParent().Define_isConstructorParameter(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:45
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isConstructorParameter
   */
  protected boolean canDefine_isConstructorParameter(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:46
   * @apilevel internal
   */
  public boolean Define_isExceptionHandlerParameter(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:91
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else {
      return getParent().Define_isExceptionHandlerParameter(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:46
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isExceptionHandlerParameter
   */
  protected boolean canDefine_isExceptionHandlerParameter(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:114
   * @apilevel internal
   */
  public boolean Define_handlesException(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    if (getImplicitConstructorInvocationNoTransform() != null && _callerNode == getImplicitConstructorInvocation()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:219
      return throwsException(exceptionType);
    }
    else if (_callerNode == getParsedConstructorInvocationOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:216
      return throwsException(exceptionType);
    }
    else if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:213
      return throwsException(exceptionType);
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
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/UnreachableStatements.jrag:57
      return hasParsedConstructorInvocation()
            ? getParsedConstructorInvocation().canCompleteNormally()
            : true;
    }
    else if (getImplicitConstructorInvocationNoTransform() != null && _callerNode == getImplicitConstructorInvocation()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/UnreachableStatements.jrag:53
      return true;
    }
    else if (_callerNode == getParsedConstructorInvocationOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/UnreachableStatements.jrag:52
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
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:346
      return getConstructorInvocation().assignedAfter(v);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:887
   * @apilevel internal
   */
  public boolean Define_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:974
      return getConstructorInvocation().unassignedAfter(v);
    }
    else {
      return super.Define_unassignedBefore(_callerNode, _childNode, v);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:204
   * @apilevel internal
   */
  public boolean Define_inExplicitConstructorInvocation(ASTNode _callerNode, ASTNode _childNode) {
    if (getImplicitConstructorInvocationNoTransform() != null && _callerNode == getImplicitConstructorInvocation()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:210
      return true;
    }
    else if (_callerNode == getParsedConstructorInvocationOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:209
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
    if (getImplicitConstructorInvocationNoTransform() != null && _callerNode == getImplicitConstructorInvocation()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:220
      return hostType();
    }
    else if (_callerNode == getParsedConstructorInvocationOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:218
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:223
   * @apilevel internal
   */
  public boolean Define_inStaticContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getImplicitConstructorInvocationNoTransform() != null && _callerNode == getImplicitConstructorInvocation()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:234
      return false;
    }
    else if (_callerNode == getParsedConstructorInvocationOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:233
      return false;
    }
    else if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:232
      return false;
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
    if (getImplicitConstructorInvocationNoTransform() != null && _callerNode == getImplicitConstructorInvocation()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:693
      return unknownType();
    }
    else if (_callerNode == getParsedConstructorInvocationOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:691
      return unknownType();
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:433
   * @apilevel internal
   */
  public boolean Define_mayBePublic(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:338
      return true;
    }
    else {
      return getParent().Define_mayBePublic(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:433
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBePublic
   */
  protected boolean canDefine_mayBePublic(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:435
   * @apilevel internal
   */
  public boolean Define_mayBeProtected(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:339
      return true;
    }
    else {
      return getParent().Define_mayBeProtected(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:435
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeProtected
   */
  protected boolean canDefine_mayBeProtected(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:434
   * @apilevel internal
   */
  public boolean Define_mayBePrivate(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:340
      return true;
    }
    else {
      return getParent().Define_mayBePrivate(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:434
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBePrivate
   */
  protected boolean canDefine_mayBePrivate(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:30
   * @apilevel internal
   */
  public SimpleSet<Variable> Define_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:112
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return parameterDeclaration(name);
    }
    else if (getImplicitConstructorInvocationNoTransform() != null && _callerNode == getImplicitConstructorInvocation()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:99
      {
          SimpleSet<Variable> result = parameterDeclaration(name);
          if (!result.isEmpty()) {
            return result;
          }
          for (Variable v : lookupVariable(name)) {
            if (!hostType().memberFields(name).contains(v) || v.isStatic()) {
              result = result.add(v);
            }
          }
          return result;
        }
    }
    else if (_callerNode == getParsedConstructorInvocationOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:86
      {
          SimpleSet<Variable> result = parameterDeclaration(name);
          if (!result.isEmpty()) {
            return result;
          }
          for (Variable v : lookupVariable(name)) {
            if (!hostType().memberFields(name).contains(v) || v.isStatic()) {
              result = result.add(v);
            }
          }
          return result;
        }
    }
    else if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:78
      {
          SimpleSet<Variable> result = parameterDeclaration(name);
          if (!result.isEmpty()) {
            return result;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (getImplicitConstructorInvocationNoTransform() != null && _callerNode == getImplicitConstructorInvocation()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:136
      return NameType.EXPRESSION_NAME;
    }
    else if (_callerNode == getParsedConstructorInvocationOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:135
      return NameType.EXPRESSION_NAME;
    }
    else if (_callerNode == getExceptionListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:106
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return NameType.TYPE_NAME;
    }
    else if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:105
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return NameType.TYPE_NAME;
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
    if (getImplicitConstructorInvocationNoTransform() != null && _callerNode == getImplicitConstructorInvocation()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:167
      {
          Collection<MethodDecl> methods = new ArrayList<MethodDecl>();
          for (MethodDecl m : lookupMethod(name)) {
            if (!hostType().memberMethods(name).contains(m) || m.isStatic()) {
              methods.add(m);
            }
          }
          return methods;
        }
    }
    else if (_callerNode == getParsedConstructorInvocationOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:157
      {
          Collection<MethodDecl> methods = new ArrayList<MethodDecl>();
          for (MethodDecl m : lookupMethod(name)) {
            if (!hostType().memberMethods(name).contains(m) || m.isStatic()) {
              methods.add(m);
            }
          }
          return methods;
        }
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:46
   * @apilevel internal
   */
  public boolean Define_variableArityValid(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:41
      int i = _callerNode.getIndexOfChild(_childNode);
      return i == getNumParameter() - 1;
    }
    else {
      return getParent().Define_variableArityValid(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:46
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute variableArityValid
   */
  protected boolean canDefine_variableArityValid(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:131
   * @apilevel internal
   */
  public boolean Define_mayUseAnnotationTarget(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:158
      return name.equals("CONSTRUCTOR");
    }
    else {
      return getParent().Define_mayUseAnnotationTarget(this, _callerNode, name);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:131
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayUseAnnotationTarget
   */
  protected boolean canDefine_mayUseAnnotationTarget(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:857
   * @apilevel internal
   */
  public String Define_typeVariableContext(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return hostType().typeName() + "." + signature();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:857
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeVariableContext
   */
  protected boolean canDefine_typeVariableContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:550
   * @apilevel internal
   */
  public boolean Define_inEnumInitializer(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return hostType().isEnumDecl();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:550
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inEnumInitializer
   */
  protected boolean canDefine_inEnumInitializer(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:522
   * @apilevel internal
   */
  public Block Define_enclosingBlock(ASTNode _callerNode, ASTNode _childNode) {
    if (getImplicitConstructorInvocationNoTransform() != null && _callerNode == getImplicitConstructorInvocation()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:529
      return getBlock();
    }
    else if (_callerNode == getParsedConstructorInvocationOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:528
      return getBlock();
    }
    else {
      return getParent().Define_enclosingBlock(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:522
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingBlock
   */
  protected boolean canDefine_enclosingBlock(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:30
   * @apilevel internal
   */
  public boolean Define_inhModifiedInScope(ASTNode _callerNode, ASTNode _childNode, Variable var) {
    if (getImplicitConstructorInvocationNoTransform() != null && _callerNode == getImplicitConstructorInvocation()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:61
      return false;
    }
    else if (_callerNode == getParsedConstructorInvocationOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:60
      return false;
    }
    else if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:110
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      {
          return getBlock().modifiedInScope(var) || getConstructorInvocation().modifiedInScope(var);
        }
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:206
   * @apilevel internal
   */
  public boolean Define_isCatchParam(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:208
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else {
      return getParent().Define_isCatchParam(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:206
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isCatchParam
   */
  protected boolean canDefine_isCatchParam(ASTNode _callerNode, ASTNode _childNode) {
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:569
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:109
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:572
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }


  // Add problem contributions from the implicit constructor invocation NTA.
  
{
    if (checkImplicitConstructorInvocation()) {
      getImplicitConstructorInvocation().collect_contributors_CompilationUnit_problems(_root, _map);
    }
    super.collect_contributors_CompilationUnit_problems(_root, _map);
  }
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
  protected void collect_contributors_InvocationTarget_calledMethods(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {

  
getImplicitConstructorInvocation().collect_contributors_InvocationTarget_calledMethods(_root, _map);
    super.collect_contributors_InvocationTarget_calledMethods(_root, _map);
  }
  /** @apilevel internal */
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    for (Problem value : typeProblems()) {
      collection.add(value);
    }
    for (Problem value : nameProblems()) {
      collection.add(value);
    }
    for (Problem value : enumProblems()) {
      collection.add(value);
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
