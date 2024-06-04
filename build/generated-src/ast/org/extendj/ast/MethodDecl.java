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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:214
 * @astdecl MethodDecl : MemberDecl ::= Modifiers TypeAccess:Access <ID:String> Parameter:ParameterDeclaration* Exception:Access* [Block];
 * @production MethodDecl : {@link MemberDecl} ::= <span class="component">{@link Modifiers}</span> <span class="component">TypeAccess:{@link Access}</span> <span class="component">&lt;ID:{@link String}&gt;</span> <span class="component">Parameter:{@link ParameterDeclaration}*</span> <span class="component">Exception:{@link Access}*</span> <span class="component">[{@link Block}]</span>;

 */
public class MethodDecl extends MemberDecl implements Cloneable, InvocationTarget, SimpleSet<MethodDecl>, DeclarationSite {
  /**
   * @aspect CallGraphWithRAGS
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:90
   */
  public
  int sccID = -1;
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrint.jadd:468
   */
  public void prettyPrint(PrettyPrinter out) {
    if (!isSynthetic()) {
      if (hasDocComment()) {
        out.print(docComment());
      }
      if (!out.isNewLine()) {
        out.println();
      }
      out.print(getModifiers());
      out.print(getTypeAccess());
      out.print(" ");
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
      if (hasBlock()) {
        out.print(" ");
        out.print(getBlock());
      } else {
        out.print(";");
      }
    }
  }
  /**
   * @aspect BoundNames
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BoundNames.jrag:95
   */
  public Access createBoundAccess(List<Expr> args) {
    return createBoundAccess(args, hostType());
  }
  /**
   * @aspect BoundNames
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BoundNames.jrag:99
   */
  public Access createBoundAccess(List<Expr> args, TypeDecl hostType) {
    if (isStatic()) {
      return hostType().createQualifiedAccess().qualifiesAccess(
          new BoundMethodAccess(name(), args, this, hostType));
    } else {
      return new BoundMethodAccess(name(), args, this, hostType);
    }
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:421
   */
  @Override
  public int size() {
    return 1;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:426
   */
  @Override
  public boolean isEmpty() {
    return false;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:431
   */
  @Override
  public SimpleSet<MethodDecl> add(MethodDecl o) {
    return new SimpleSetImpl<MethodDecl>(this, o);
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:436
   */
  @Override
  public boolean contains(Object o) {
    return this == o;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:441
   */
  @Override
  public boolean isSingleton() {
    return true;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:446
   */
  @Override
  public boolean isSingleton(MethodDecl o) {
    return contains(o);
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:451
   */
  @Override
  public MethodDecl singletonValue() {
    return this;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:456
   */
  @Override
  public boolean equals(Object o) {
    return this == o;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:461
   */
  @Override
  public Iterator<MethodDecl> iterator() {
    return new SingleItemIterator(this);
  }
  /**
   * @aspect PrettyPrintUtil
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:125
   */
  @Override public String toString() {
    int numParams = 0;
    StringBuilder params = new StringBuilder();
    for (ParameterDeclaration param : getParameterListNoTransform()) {
      if (numParams > 0) {
        params.append(", ");
      }
      params.append(param.toString());
      numParams += 1;
    }
    return String.format("%s %s(%s)",
        getTypeAccessNoTransform().toString(),
        getID(),
        params);
  }
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1442
   */
  public BodyDecl signatureCopy() {
    return new MethodDeclSubstituted(
        getModifiers().treeCopyNoTransform(),
        getTypeAccessNoTransform().treeCopyNoTransform(),
        getID(),
        getParameterList().treeCopyNoTransform(),
        getExceptionList().treeCopyNoTransform(),
        new Opt<Block>(),
        this);
  }
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1543
   */
  public BodyDecl erasedCopy() {
    return new MethodDeclSubstituted(
        getModifiers().treeCopyNoTransform(),
        getTypeAccess().erasedCopy(),
        getID(),
        erasedParameterList(getParameterList()),
        erasedAccessList(getExceptionList()),
        new Opt<Block>(),
        this);
  }
  /**
   * Checks that the argument exception is a subtype to all exceptions
   * in the methods throws-clause. This takes the position of the type
   * parameters into account.
   * @aspect FunctionDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionDescriptor.jrag:199
   */
  public boolean subtypeThrowsClause(Access exception) {
    boolean foundCompatible = false;
    for (Access throwsException : getExceptionList()) {
      if (exception.type().strictSubtype(throwsException.type())) {
        foundCompatible = true;
        break;
      }
    }
    return foundCompatible;
  }
  /**
   * Checks that the argument exception is a subtype to all exceptions
   * in the methods throws-clause. Performs erasure on all types before
   * comparing them.
   * @aspect FunctionDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionDescriptor.jrag:215
   */
  public boolean subtypeThrowsClauseErased(Access exception) {
    boolean foundCompatible = false;
    for (Access throwsException : getExceptionList()) {
      if (exception.type().erasure().strictSubtype(throwsException.type().erasure())) {
        foundCompatible = true;
        break;
      }
    }
    return foundCompatible;
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
  public MethodDecl() {
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
    children = new ASTNode[5];
    setChild(new List(), 2);
    setChild(new List(), 3);
    setChild(new Opt(), 4);
  }
  /**
   * @declaredat ASTNode:16
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Modifiers", "TypeAccess", "ID", "Parameter", "Exception", "Block"},
    type = {"Modifiers", "Access", "String", "List<ParameterDeclaration>", "List<Access>", "Opt<Block>"},
    kind = {"Child", "Child", "Token", "List", "List", "Opt"}
  )
  public MethodDecl(Modifiers p0, Access p1, String p2, List<ParameterDeclaration> p3, List<Access> p4, Opt<Block> p5) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
    setChild(p3, 2);
    setChild(p4, 3);
    setChild(p5, 4);
  }
  /**
   * @declaredat ASTNode:29
   */
  public MethodDecl(Modifiers p0, Access p1, beaver.Symbol p2, List<ParameterDeclaration> p3, List<Access> p4, Opt<Block> p5) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
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
    returnType_reset();
    paramTypes_reset();
    shouldBeConsiderAsMethod_reset();
    targetSignature_reset();
    isAnAttribute_reset();
    isSynAttribute_reset();
    isInhAttribute_reset();
    isCircularAttribute_reset();
    isCollectionAttribute_reset();
    throwsException_TypeDecl_reset();
    accessibleFrom_TypeDecl_reset();
    parameterDeclaration_String_reset();
    signature_reset();
    lessSpecificThan_MethodDecl_reset();
    overrideCandidate_MethodDecl_reset();
    overrides_MethodDecl_reset();
    hides_MethodDecl_reset();
    type_reset();
    usesTypeVariable_reset();
    sourceMethodDecl_reset();
    subsignatureTo_MethodDecl_reset();
    returnTypeSubstitutableFor_MethodDecl_reset();
    reversedCG_reset();
    cg_reset();
    completeCallGraph_reset();
    getSCCID_reset();
    implicitCalls_reset();
    getContribution_reset();
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
    declaredIn_reset();
    handlesException_TypeDecl_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:92
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
    MethodDecl_returnValues_computed = null;
    MethodDecl_returnValues_value = null;
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
   * @declaredat ASTNode:112
   */
  public MethodDecl clone() throws CloneNotSupportedException {
    MethodDecl node = (MethodDecl) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:117
   */
  public MethodDecl copy() {
    try {
      MethodDecl node = (MethodDecl) clone();
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
   * @declaredat ASTNode:136
   */
  @Deprecated
  public MethodDecl fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:146
   */
  public MethodDecl treeCopyNoTransform() {
    MethodDecl tree = (MethodDecl) copy();
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
   * @declaredat ASTNode:166
   */
  public MethodDecl treeCopy() {
    MethodDecl tree = (MethodDecl) copy();
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
   * Replaces the Modifiers child.
   * @param node The new node to replace the Modifiers child.
   * @apilevel high-level
   */
  public MethodDecl setModifiers(Modifiers node) {
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
   * Replaces the TypeAccess child.
   * @param node The new node to replace the TypeAccess child.
   * @apilevel high-level
   */
  public MethodDecl setTypeAccess(Access node) {
    setChild(node, 1);
    return this;
  }
  /**
   * Retrieves the TypeAccess child.
   * @return The current node used as the TypeAccess child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="TypeAccess")
  public Access getTypeAccess() {
    return (Access) getChild(1);
  }
  /**
   * Retrieves the TypeAccess child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the TypeAccess child.
   * @apilevel low-level
   */
  public Access getTypeAccessNoTransform() {
    return (Access) getChildNoTransform(1);
  }
  /**
   * Replaces the lexeme ID.
   * @param value The new value for the lexeme ID.
   * @apilevel high-level
   */
  public MethodDecl setID(String value) {
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
  public MethodDecl setID(beaver.Symbol symbol) {
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
  public MethodDecl setParameterList(List<ParameterDeclaration> list) {
    setChild(list, 2);
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
  public MethodDecl addParameter(ParameterDeclaration node) {
    List<ParameterDeclaration> list = (parent == null) ? getParameterListNoTransform() : getParameterList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public MethodDecl addParameterNoTransform(ParameterDeclaration node) {
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
  public MethodDecl setParameter(ParameterDeclaration node, int i) {
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
    List<ParameterDeclaration> list = (List<ParameterDeclaration>) getChild(2);
    return list;
  }
  /**
   * Retrieves the Parameter list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Parameter list.
   * @apilevel low-level
   */
  public List<ParameterDeclaration> getParameterListNoTransform() {
    return (List<ParameterDeclaration>) getChildNoTransform(2);
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
  public MethodDecl setExceptionList(List<Access> list) {
    setChild(list, 3);
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
  public MethodDecl addException(Access node) {
    List<Access> list = (parent == null) ? getExceptionListNoTransform() : getExceptionList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public MethodDecl addExceptionNoTransform(Access node) {
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
  public MethodDecl setException(Access node, int i) {
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
    List<Access> list = (List<Access>) getChild(3);
    return list;
  }
  /**
   * Retrieves the Exception list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Exception list.
   * @apilevel low-level
   */
  public List<Access> getExceptionListNoTransform() {
    return (List<Access>) getChildNoTransform(3);
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
   * Replaces the optional node for the Block child. This is the <code>Opt</code>
   * node containing the child Block, not the actual child!
   * @param opt The new node to be used as the optional node for the Block child.
   * @apilevel low-level
   */
  public MethodDecl setBlockOpt(Opt<Block> opt) {
    setChild(opt, 4);
    return this;
  }
  /**
   * Replaces the (optional) Block child.
   * @param node The new node to be used as the Block child.
   * @apilevel high-level
   */
  public MethodDecl setBlock(Block node) {
    getBlockOpt().setChild(node, 0);
    return this;
  }
  /**
   * Check whether the optional Block child exists.
   * @return {@code true} if the optional Block child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasBlock() {
    return getBlockOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) Block child.
   * @return The Block child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public Block getBlock() {
    return (Block) getBlockOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for the Block child. This is the <code>Opt</code> node containing the child Block, not the actual child!
   * @return The optional node for child the Block child.
   * @apilevel low-level
   */
  @ASTNodeAnnotation.OptChild(name="Block")
  public Opt<Block> getBlockOpt() {
    return (Opt<Block>) getChild(4);
  }
  /**
   * Retrieves the optional node for child Block. This is the <code>Opt</code> node containing the child Block, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child Block.
   * @apilevel low-level
   */
  public Opt<Block> getBlockOptNoTransform() {
    return (Opt<Block>) getChildNoTransform(4);
  }
  /**
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:152
   */
  private Collection<Problem> refined_Modifiers_MethodDecl_modifierProblems()
{
    Collection<Problem> problems = new LinkedList<Problem>();
    if (hostType().isClassDecl()) {
      // 8.4.3.1
      if (isAbstract() && !hostType().isAbstract()) {
        problems.add(error("Non-abstract class cannot include abstract methods."));
      }
      // 8.4.3.1
      if (isAbstract() && isPrivate()) {
        problems.add(error("method cannot be abstract and private"));
      }
      // 8.4.3.1
      // 8.4.3.2
      if (isAbstract() && isStatic()) {
        problems.add(error("method cannot be abstract and static"));
      }
      if (isAbstract() && isSynchronized()) {
        problems.add(error("method cannot be abstract and synchronized"));
      }
      // 8.4.3.4
      if (isAbstract() && isNative()) {
        problems.add(error("method cannot be abstract and native"));
      }
      if (isAbstract() && isStrictfp()) {
        problems.add(error("method cannot be abstract and strictfp"));
      }
      if (isNative() && isStrictfp()) {
        problems.add(error("method cannot be native and strictfp"));
      }
    }
    if (hostType().isInterfaceDecl()) {
      // 9.4
      if (isStatic()) {
        problems.add(errorf("interface method %s in %s cannot be static",
            signature(), hostType().typeName()));
      }
      if (isStrictfp()) {
        problems.add(errorf("interface method %s in %s cannot be strictfp",
            signature(), hostType().typeName()));
      }
      if (isNative()) {
        problems.add(errorf("interface method %s in %s cannot be native",
            signature(), hostType().typeName()));
      }
      if (isSynchronized()) {
        problems.add(errorf("interface method %s in %s cannot be synchronized",
            signature(), hostType().typeName()));
      }
      if (isProtected()) {
        problems.add(errorf("interface method %s in %s cannot be protected",
            signature(), hostType().typeName()));
      }
      if (isPrivate()) {
        problems.add(errorf("interface method %s in %s cannot be private",
            signature(), hostType().typeName()));
      }
      if (isFinal()) {
        problems.add(errorf("interface method %s in %s cannot be final",
            signature(), hostType().typeName()));
      }
    }
    return problems;
  }
  /**
   * @aspect MethodDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:349
   */
  private boolean refined_MethodDecl_MethodDecl_sameSignature_MethodDecl(MethodDecl other)
{ return signature().equals(other.signature()); }
  /**
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:833
   */
  private Collection<Problem> refined_Enums_MethodDecl_modifierProblems()
{
    Collection<Problem> problems = refined_Modifiers_MethodDecl_modifierProblems();
    if (hostType().isEnumDecl()) {
      // An enum counts as a class but can have abstract methods, so we have to filter out the
      // error about abstract methods for enum types.
      Collection<Problem> filtered = new LinkedList<Problem>();
      for (Problem problem : problems) {
        if (!problem.message().equals("Non-abstract class cannot include abstract methods.")) {
          filtered.add(problem);
        }
      }
      return filtered;
    }
    return problems;
  }
  /**
   * @attribute syn
   * @aspect AllMethods
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:37
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AllMethods", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:36")
  public boolean visible() {
    boolean visible_value = true;
    return visible_value;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:217
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:216")
  public String returnType() {
    ASTState state = state();
    if (returnType_computed == ASTState.NON_CYCLE || returnType_computed == state().cycle()) {
      return returnType_value;
    }
    returnType_value = type().name();
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:221
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:219")
  public Map<String, String> paramTypes() {
    ASTState state = state();
    if (paramTypes_computed == ASTState.NON_CYCLE || paramTypes_computed == state().cycle()) {
      return paramTypes_value;
    }
    paramTypes_value = paramTypes_compute();
    if (state().inCircle()) {
      paramTypes_computed = state().cycle();
    
    } else {
      paramTypes_computed = ASTState.NON_CYCLE;
    
    }
    return paramTypes_value;
  }
  /** @apilevel internal */
  private Map<String, String> paramTypes_compute() {
      Map<String, String> paramTypes = new LinkedHashMap<>();
      for (int i = 0; i < getNumParameter(); i++) {
        TypeDecl paramType = getParameter(i).type();
  
        paramTypes.put(getParameter(i).name(), paramType.name());
      }
      return paramTypes;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:244
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:242")
  public boolean shouldBeConsiderAsMethod() {
    ASTState state = state();
    if (shouldBeConsiderAsMethod_computed == ASTState.NON_CYCLE || shouldBeConsiderAsMethod_computed == state().cycle()) {
      return shouldBeConsiderAsMethod_value;
    }
    shouldBeConsiderAsMethod_value = (!program().attributesOnly || isAnAttribute()) && hasBlock();
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:281
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:276")
  public String targetSignature() {
    ASTState state = state();
    if (targetSignature_computed == ASTState.NON_CYCLE || targetSignature_computed == state().cycle()) {
      return targetSignature_value;
    }
    targetSignature_value = targetSignature_compute();
    if (state().inCircle()) {
      targetSignature_computed = state().cycle();
    
    } else {
      targetSignature_computed = ASTState.NON_CYCLE;
    
    }
    return targetSignature_value;
  }
  /** @apilevel internal */
  private String targetSignature_compute() {
      String signature = "";
      String anonymous = "";
      if (hostType() instanceof AnonymousDecl) {
        anonymous = "." + hashCode();
      }
      signature = (program().mergeNames ? ""
                                        : hostType().packageName() + "." +
                                              hostType().name() + anonymous) +
                  "::" + (program().mergeNames ? name() : fullSignature());
      return signature;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:463
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:462")
  public boolean isAnAttribute() {
    ASTState state = state();
    if (isAnAttribute_computed == ASTState.NON_CYCLE || isAnAttribute_computed == state().cycle()) {
      return isAnAttribute_value;
    }
    isAnAttribute_value = isAnAttribute_compute();
    if (state().inCircle()) {
      isAnAttribute_computed = state().cycle();
    
    } else {
      isAnAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isAnAttribute_value;
  }
  /** @apilevel internal */
  private boolean isAnAttribute_compute() {
      return hasAttributeInModifiers(getModifiers().getModifiers(),
                                     Modifier::isAnAttribute);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:469
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:468")
  public boolean isSynAttribute() {
    ASTState state = state();
    if (isSynAttribute_computed == ASTState.NON_CYCLE || isSynAttribute_computed == state().cycle()) {
      return isSynAttribute_value;
    }
    isSynAttribute_value = isSynAttribute_compute();
    if (state().inCircle()) {
      isSynAttribute_computed = state().cycle();
    
    } else {
      isSynAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isSynAttribute_value;
  }
  /** @apilevel internal */
  private boolean isSynAttribute_compute() {
      return hasAttributeInModifiers(getModifiers().getModifiers(),
                                     Modifier::isSynAttribute);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:475
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:474")
  public boolean isInhAttribute() {
    ASTState state = state();
    if (isInhAttribute_computed == ASTState.NON_CYCLE || isInhAttribute_computed == state().cycle()) {
      return isInhAttribute_value;
    }
    isInhAttribute_value = isInhAttribute_compute();
    if (state().inCircle()) {
      isInhAttribute_computed = state().cycle();
    
    } else {
      isInhAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isInhAttribute_value;
  }
  /** @apilevel internal */
  private boolean isInhAttribute_compute() {
      return hasAttributeInModifiers(getModifiers().getModifiers(),
                                     Modifier::isInhAttribute);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:481
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:480")
  public boolean isCircularAttribute() {
    ASTState state = state();
    if (isCircularAttribute_computed == ASTState.NON_CYCLE || isCircularAttribute_computed == state().cycle()) {
      return isCircularAttribute_value;
    }
    isCircularAttribute_value = isCircularAttribute_compute();
    if (state().inCircle()) {
      isCircularAttribute_computed = state().cycle();
    
    } else {
      isCircularAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isCircularAttribute_value;
  }
  /** @apilevel internal */
  private boolean isCircularAttribute_compute() {
      return hasAttributeInModifiers(getModifiers().getModifiers(),
                                     Modifier::isCircularAttribute);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:487
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:486")
  public boolean isCollectionAttribute() {
    ASTState state = state();
    if (isCollectionAttribute_computed == ASTState.NON_CYCLE || isCollectionAttribute_computed == state().cycle()) {
      return isCollectionAttribute_value;
    }
    isCollectionAttribute_value = isCollectionAttribute_compute();
    if (state().inCircle()) {
      isCollectionAttribute_computed = state().cycle();
    
    } else {
      isCollectionAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isCollectionAttribute_value;
  }
  /** @apilevel internal */
  private boolean isCollectionAttribute_compute() {
      return hasAttributeInModifiers(getModifiers().getModifiers(),
                                     Modifier::isCollectionAttribute);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:204
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:204")
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AccessControl.jrag:104
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AccessControl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AccessControl.jrag:104")
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
      if (isPublic()) {
        return true;
      } else if (isProtected()) {
        if (hostPackage().equals(type.hostPackage())) {
          return true;
        }
        if (type.withinBodyThatSubclasses(hostType()) != null) {
          return true;
        }
        return false;
      } else if (isPrivate()) {
        return hostType().topLevelType() == type.topLevelType();
      } else {
        return hostPackage().equals(type.hostPackage());
      }
    }
  /**
   * @attribute syn
   * @aspect DefiniteUnassignment
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:919
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:917")
  public boolean unassignedBefore(Variable v) {
    boolean unassignedBefore_Variable_value = false;
    return unassignedBefore_Variable_value;
  }
  /**
   * @attribute syn
   * @aspect TypeHierarchyCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:401
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeHierarchyCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:401")
  public boolean mayOverride(MethodDecl m) {
    {
        // 9.4.3
        if (isDefault() && m.hostType().isType("java.lang", "Object") && !m.isPrivate()) {
          return false;
        } else {
          MethodDecl self = this;
          if (self.isGeneric()) {
            self = genericDecl().rawMethodDecl();
          }
          if (m.isGeneric()) {
            m = m.genericDecl().rawMethodDecl();
          }
          return self.returnTypeSubstitutableFor(m);
        }
      }
  }
  /**
   * @attribute syn
   * @aspect TypeCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:517
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:517")
  public Collection<Problem> typeProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        // Thrown vs super class method see MethodDecl.nameCheck.
        // 8.4.4
        TypeDecl exceptionType = typeThrowable();
        for (int i = 0; i < getNumException(); i++) {
          TypeDecl typeDecl = getException(i).type();
          if (!typeDecl.subtype(exceptionType)) {
            problems.add(errorf("%s throws non throwable type %s", signature(), typeDecl.fullName()));
          }
        }
        // Check returns.
        if (!isVoid() && hasBlock() && getBlock().canCompleteNormally()) {
          problems.add(error("the body of a non void method may not complete normally"));
        }
        return problems;
      }
  }
  /** Enumerate all errors in the modifiers depending on context. 
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:152
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:152")
  public Collection<Problem> modifierProblems() {
    {
        Collection<Problem> problems = refined_Enums_MethodDecl_modifierProblems(); // Reuse Java 5 modifer problems with some changes.
        Collection<Problem> filtered = new LinkedList<Problem>();
        if (hostType().isClassDecl()) {
          filtered.addAll(problems);
          if (isDefault()) {
            filtered.add(error("The default modifier cannot be used for non-interface methods."));
          }
        } else if (hostType().isInterfaceDecl()) {
          // Filter out old errors that don't apply to Java 8:
          for (Problem problem : problems) {
            if (!problem.message().contains("cannot be static")
                && !problem.message().contains("cannot be strictfp")) {
              filtered.add(problem);
            }
          }
          // Add additional Java 8 errors:
          if (getModifiers().isAbstract()) {
            if (isStatic()) {
              filtered.add(errorf("Interface method %s in %s cannot be both abstract and static.",
                  signature(), hostType().typeName()));
            }
            if (isDefault()) {
              filtered.add(errorf("Interface method %s in %s cannot be both abstract and default.",
                  signature(), hostType().typeName()));
            }
            if (isStrictfp()) {
              filtered.add(errorf("Interface method %s in %s cannot be both abstract and strictfp.",
                  signature(), hostType().typeName()));
            }
          }
          if (isStatic() && isDefault()) {
            filtered.add(errorf("Interface method %s in %s cannot be both static and default.",
                signature(), hostType().typeName()));
          }
        } else {
          filtered.addAll(problems);
        }
        return filtered;
      }
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:251
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:250")
  public boolean isSynthetic() {
    boolean isSynthetic_value = getModifiers().isSynthetic();
    return isSynthetic_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:260
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:260")
  public boolean isPublic() {
    boolean isPublic_value = getModifiers().isPublic() || hostType().isInterfaceDecl();
    return isPublic_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:261
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:261")
  public boolean isPrivate() {
    boolean isPrivate_value = getModifiers().isPrivate();
    return isPrivate_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:262
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:262")
  public boolean isProtected() {
    boolean isProtected_value = getModifiers().isProtected();
    return isProtected_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:263
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:263")
  public boolean isAbstract() {
    {
        return getModifiers().isAbstract() || (hostType().isInterfaceDecl() && !isStatic() && !isDefault());
      }
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:264
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:264")
  public boolean isStatic() {
    boolean isStatic_value = getModifiers().isStatic();
    return isStatic_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:267
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:267")
  public boolean isFinal() {
    boolean isFinal_value = getModifiers().isFinal();
    return isFinal_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:268
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:268")
  public boolean isSynchronized() {
    boolean isSynchronized_value = getModifiers().isSynchronized();
    return isSynchronized_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:269
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:269")
  public boolean isNative() {
    boolean isNative_value = getModifiers().isNative();
    return isNative_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:270
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:270")
  public boolean isStrictfp() {
    boolean isStrictfp_value = getModifiers().isStrictfp();
    return isStrictfp_value;
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
  /** @return the first variable declaration with the given name. 
   * @attribute syn
   * @aspect VariableScope
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:174
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:174")
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:152
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:152")
  public Collection<Problem> nameProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        // 8.4
        // 8.4.2
        if (hostType().methodsSignature(signature()).size() > 1) {
          problems.add(errorf("method with signature %s is multiply declared in type %s", signature(),
              hostType().typeName()));
        }
        // 8.4.3.4
        if (isNative() && hasBlock()) {
          problems.add(error("native methods must have an empty semicolon body"));
        }
        // 8.4.5
        if (isAbstract() && hasBlock()) {
          problems.add(error("abstract methods must have an empty semicolon body"));
        }
        // 8.4.5
        if (!hasBlock() && !(isNative() || isAbstract())) {
          problems.add(error("only abstract and native methods may have an empty semicolon body"));
        }
        return problems;
      }
  }
  /**
   * Safe parameter type access.
   * 
   * @return the type of the parameter at the given index, or
   * UnknownType if there is not parameter at the given index.
   * @attribute syn
   * @aspect LookupMethod
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:60
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupMethod", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:60")
  public TypeDecl paramType(int index) {
    TypeDecl paramType_int_value = index >= 0 && index < getNumParameter()
          ? getParameter(index).type()
          : unknownType();
    return paramType_int_value;
  }
  /**
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:298
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:298")
  public String name() {
    String name_value = getID();
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
   * A method signature that is used to discriminate methods with the same name
   * and argument types.
   * 
   * <p>See JLS6 &sect;8.4.2.
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:727
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:306")
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
      StringBuilder sb = new StringBuilder();
      sb.append(name() + "(");
      for (int i = 0; i < getNumParameter(); i++) {
        if (i != 0) {
          sb.append(", ");
        }
        sb.append(getParameter(i).type().erasure().typeName());
      }
      sb.append(")");
      return sb.toString();
    }
  /** Method signature, including type arguments.  
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:320
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:320")
  public String fullSignature() {
    {
        StringBuilder sb = new StringBuilder();
        sb.append(name() + "(");
        for (int i = 0; i < getNumParameter(); i++) {
          if (i != 0) {
            sb.append(", ");
          }
          // TODO: TypeDecl.fullName() should not include @primitive.
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
  }
  /**
   * Compare the signature of this method declaration with another
   * method declaration.
   * 
   * See JLS6 &sect;8.4.2.
   * 
   * @return {@code true} if the signature of this method is equal to
   * the signature of the argument method.
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:349
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:349")
  public boolean sameSignature(MethodDecl other) {
    {
        if (!refined_MethodDecl_MethodDecl_sameSignature_MethodDecl(other)) {
          return false;
        }
        for (int i = 0; i < getNumParameter(); ++i) {
          TypeDecl p1 = getParameter(i).type();
          TypeDecl p2 = other.getParameter(i).type();
          // JLSv7 $8.4.8.1 exception: if one parameter type is raw, then don't check type bounds
          if (p1 != p2 && !p1.isRawType() && !p2.isRawType()) {
            return false;
          }
        }
        return true;
      }
  }
  /**
   * Determine if this method declaration is more specific than another
   * method declaration.
   * 
   * @param m argument method to compare to
   * @return {@code true} if this the argument method is less specific than this
   * and this is not less specific than the argument
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:360
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:360")
  public boolean moreSpecificThan(MethodDecl m) {
    boolean moreSpecificThan_MethodDecl_value = m.lessSpecificThan(this) && !this.lessSpecificThan(m);
    return moreSpecificThan_MethodDecl_value;
  }
  /** @apilevel internal */
  private void lessSpecificThan_MethodDecl_reset() {
    lessSpecificThan_MethodDecl_computed = null;
    lessSpecificThan_MethodDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map lessSpecificThan_MethodDecl_values;
  /** @apilevel internal */
  protected java.util.Map lessSpecificThan_MethodDecl_computed;
  /**
   * Determine if this method declaration is less specific than another
   * method declaration.
   * 
   * <p>Caution: that {@code a} is less specific than {@code b} does not mean that
   * {@code b} is not less specific than {@code a}!
   * 
   * @param m argument method to compare to.
   * @return {@code true} if any parameter of this method declaration is not a
   * (non-proper) subtype of the corresponding parameter of the argument
   * method.
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:203
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:375")
  public boolean lessSpecificThan(MethodDecl m) {
    Object _parameters = m;
    if (lessSpecificThan_MethodDecl_computed == null) lessSpecificThan_MethodDecl_computed = new java.util.HashMap(4);
    if (lessSpecificThan_MethodDecl_values == null) lessSpecificThan_MethodDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (lessSpecificThan_MethodDecl_values.containsKey(_parameters)
        && lessSpecificThan_MethodDecl_computed.containsKey(_parameters)
        && (lessSpecificThan_MethodDecl_computed.get(_parameters) == ASTState.NON_CYCLE || lessSpecificThan_MethodDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) lessSpecificThan_MethodDecl_values.get(_parameters);
    }
    boolean lessSpecificThan_MethodDecl_value = lessSpecificThan_compute(m);
    if (state().inCircle()) {
      lessSpecificThan_MethodDecl_values.put(_parameters, lessSpecificThan_MethodDecl_value);
      lessSpecificThan_MethodDecl_computed.put(_parameters, state().cycle());
    
    } else {
      lessSpecificThan_MethodDecl_values.put(_parameters, lessSpecificThan_MethodDecl_value);
      lessSpecificThan_MethodDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return lessSpecificThan_MethodDecl_value;
  }
  /** @apilevel internal */
  private boolean lessSpecificThan_compute(MethodDecl m) {
      // TODO(joqvist): fix code duplication between MethodDecl and ConstructorDecl.
      // Here we have a non-obvious precondition: either both methods are
      // variable arity or both are fixed arity.
      // An applicable fixed arity method is always chosen instead of an
      // applicable variable arity method, so a fixed arity method and
      // a variable arity method will not be compared for most specificity.
      if (!isVariableArity()) {
        // Both methods have fixed arity.
        for (int i = 0; i < getNumParameter(); i++) {
          TypeDecl t1 = getParameter(i).type();
          TypeDecl t2 = m.getParameter(i).type();
          if (!t1.subtype(t2) && !t1.withinBounds(t2)) {
            return true;
          }
        }
      } else {
        // Both methods have variable arity.
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
  /** @apilevel internal */
  private void overrideCandidate_MethodDecl_reset() {
    overrideCandidate_MethodDecl_computed = null;
    overrideCandidate_MethodDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map overrideCandidate_MethodDecl_values;
  /** @apilevel internal */
  protected java.util.Map overrideCandidate_MethodDecl_computed;
  /**
   * Only check if this method would be able to override other method,
   * not if this method is declared in a subtype of the hostType of
   * other method. NB: does not check for equal signature!
   * 
   * @param m other method.
   * @return {@code true} of the method could potentially override.
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:455
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:455")
  public boolean overrideCandidate(MethodDecl m) {
    Object _parameters = m;
    if (overrideCandidate_MethodDecl_computed == null) overrideCandidate_MethodDecl_computed = new java.util.HashMap(4);
    if (overrideCandidate_MethodDecl_values == null) overrideCandidate_MethodDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (overrideCandidate_MethodDecl_values.containsKey(_parameters)
        && overrideCandidate_MethodDecl_computed.containsKey(_parameters)
        && (overrideCandidate_MethodDecl_computed.get(_parameters) == ASTState.NON_CYCLE || overrideCandidate_MethodDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) overrideCandidate_MethodDecl_values.get(_parameters);
    }
    boolean overrideCandidate_MethodDecl_value = !isStatic() && !m.isPrivate() && m.accessibleFrom(hostType());
    if (state().inCircle()) {
      overrideCandidate_MethodDecl_values.put(_parameters, overrideCandidate_MethodDecl_value);
      overrideCandidate_MethodDecl_computed.put(_parameters, state().cycle());
    
    } else {
      overrideCandidate_MethodDecl_values.put(_parameters, overrideCandidate_MethodDecl_value);
      overrideCandidate_MethodDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return overrideCandidate_MethodDecl_value;
  }
  /** @apilevel internal */
  private void overrides_MethodDecl_reset() {
    overrides_MethodDecl_computed = null;
    overrides_MethodDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map overrides_MethodDecl_values;
  /** @apilevel internal */
  protected java.util.Map overrides_MethodDecl_computed;
  /**
   * Determine if this method declaration actually overrides
   * another declaration from a supertype.
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:462
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:462")
  public boolean overrides(MethodDecl m) {
    Object _parameters = m;
    if (overrides_MethodDecl_computed == null) overrides_MethodDecl_computed = new java.util.HashMap(4);
    if (overrides_MethodDecl_values == null) overrides_MethodDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (overrides_MethodDecl_values.containsKey(_parameters)
        && overrides_MethodDecl_computed.containsKey(_parameters)
        && (overrides_MethodDecl_computed.get(_parameters) == ASTState.NON_CYCLE || overrides_MethodDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) overrides_MethodDecl_values.get(_parameters);
    }
    boolean overrides_MethodDecl_value = !isStatic() && !m.isPrivate() && m.accessibleFrom(hostType())
          && hostType().subtype(m.hostType()) && m.signature().equals(signature());
    if (state().inCircle()) {
      overrides_MethodDecl_values.put(_parameters, overrides_MethodDecl_value);
      overrides_MethodDecl_computed.put(_parameters, state().cycle());
    
    } else {
      overrides_MethodDecl_values.put(_parameters, overrides_MethodDecl_value);
      overrides_MethodDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return overrides_MethodDecl_value;
  }
  /** @apilevel internal */
  private void hides_MethodDecl_reset() {
    hides_MethodDecl_computed = null;
    hides_MethodDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map hides_MethodDecl_values;
  /** @apilevel internal */
  protected java.util.Map hides_MethodDecl_computed;
  /**
   * A method declaration hides another declaration from a superclass
   * if the other method would otherwise be accessible, and this
   * method is static and has the same signature.
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:471
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:471")
  public boolean hides(MethodDecl m) {
    Object _parameters = m;
    if (hides_MethodDecl_computed == null) hides_MethodDecl_computed = new java.util.HashMap(4);
    if (hides_MethodDecl_values == null) hides_MethodDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (hides_MethodDecl_values.containsKey(_parameters)
        && hides_MethodDecl_computed.containsKey(_parameters)
        && (hides_MethodDecl_computed.get(_parameters) == ASTState.NON_CYCLE || hides_MethodDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) hides_MethodDecl_values.get(_parameters);
    }
    boolean hides_MethodDecl_value = isStatic() && !m.isPrivate() && m.accessibleFrom(hostType())
          && hostType().subtype(m.hostType()) && m.signature().equals(signature());
    if (state().inCircle()) {
      hides_MethodDecl_values.put(_parameters, hides_MethodDecl_value);
      hides_MethodDecl_computed.put(_parameters, state().cycle());
    
    } else {
      hides_MethodDecl_values.put(_parameters, hides_MethodDecl_value);
      hides_MethodDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return hides_MethodDecl_value;
  }
  /**
   * @attribute syn
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:56
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ErrorCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:46")
  public int lineNumber() {
    int lineNumber_value = getLine(IDstart);
    return lineNumber_value;
  }
  /**
   * @attribute syn
   * @aspect PrettyPrintUtil
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:334
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PrettyPrintUtil", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:334")
  public boolean hasModifiers() {
    boolean hasModifiers_value = getModifiers().getNumModifier() > 0;
    return hasModifiers_value;
  }
  /**
   * @attribute syn
   * @aspect PrettyPrintUtil
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:346
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PrettyPrintUtil", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:346")
  public boolean hasExceptions() {
    boolean hasExceptions_value = getNumException() > 0;
    return hasExceptions_value;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:288
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:288")
  public TypeDecl type() {
    ASTState state = state();
    if (type_computed == ASTState.NON_CYCLE || type_computed == state().cycle()) {
      return type_value;
    }
    type_value = getTypeAccess().type();
    if (state().inCircle()) {
      type_computed = state().cycle();
    
    } else {
      type_computed = ASTState.NON_CYCLE;
    
    }
    return type_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:291
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:290")
  public boolean isVoid() {
    boolean isVoid_value = type().isVoid();
    return isVoid_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsParTypeDecl.jrag:99
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsParTypeDecl.jrag:98")
  public boolean visibleTypeParameters() {
    boolean visibleTypeParameters_value = !isStatic();
    return visibleTypeParameters_value;
  }
  /**
   * @attribute syn
   * @aspect VariableArityParameters
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:53
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableArityParameters", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:53")
  public boolean isVariableArity() {
    boolean isVariableArity_value = getNumParameter() > 0 && getParameter(getNumParameter() - 1).isVariableArity();
    return isVariableArity_value;
  }
  /**
   * @attribute syn
   * @aspect VariableArityParameters
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:63
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableArityParameters", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:63")
  public ParameterDeclaration lastParameter() {
    ParameterDeclaration lastParameter_value = getParameter(getNumParameter() - 1);
    return lastParameter_value;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:231
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:231")
  public boolean annotationMethodOverride() {
    boolean annotationMethodOverride_value = !hostType().ancestorMethods(signature()).isEmpty();
    return annotationMethodOverride_value;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:430
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:482
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:482")
  public boolean isDeprecated() {
    boolean isDeprecated_value = getModifiers().hasDeprecatedAnnotation();
    return isDeprecated_value;
  }
  /** @apilevel internal */
  private void usesTypeVariable_reset() {
    usesTypeVariable_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle usesTypeVariable_computed = null;

  /** @apilevel internal */
  protected boolean usesTypeVariable_value;

  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1332
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1332")
  public boolean usesTypeVariable() {
    ASTState state = state();
    if (usesTypeVariable_computed == ASTState.NON_CYCLE || usesTypeVariable_computed == state().cycle()) {
      return usesTypeVariable_value;
    }
    usesTypeVariable_value = getModifiers().usesTypeVariable() || getTypeAccess().usesTypeVariable()
          || getParameterList().usesTypeVariable() || getExceptionList().usesTypeVariable();
    if (state().inCircle()) {
      usesTypeVariable_computed = state().cycle();
    
    } else {
      usesTypeVariable_computed = ASTState.NON_CYCLE;
    
    }
    return usesTypeVariable_value;
  }
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1679
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1679")
  public MethodDecl erasedMethod() {
    MethodDecl erasedMethod_value = this;
    return erasedMethod_value;
  }
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1736
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1735")
  public boolean isSubstitutable() {
    boolean isSubstitutable_value = !isStatic();
    return isSubstitutable_value;
  }
  /** @apilevel internal */
  private void sourceMethodDecl_reset() {
    sourceMethodDecl_computed = null;
    sourceMethodDecl_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle sourceMethodDecl_computed = null;

  /** @apilevel internal */
  protected MethodDecl sourceMethodDecl_value;

  /**
   * @attribute syn
   * @aspect SourceDeclarations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1914
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SourceDeclarations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1914")
  public MethodDecl sourceMethodDecl() {
    ASTState state = state();
    if (sourceMethodDecl_computed == ASTState.NON_CYCLE || sourceMethodDecl_computed == state().cycle()) {
      return sourceMethodDecl_value;
    }
    sourceMethodDecl_value = this;
    if (state().inCircle()) {
      sourceMethodDecl_computed = state().cycle();
    
    } else {
      sourceMethodDecl_computed = ASTState.NON_CYCLE;
    
    }
    return sourceMethodDecl_value;
  }
  /**
   * Note: isGeneric must be called first to check if this declaration is generic.
   * Otherwise this attribute will throw an error!
   * @return the original generic declaration of this method.
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:426
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:426")
  public GenericMethodDecl genericDecl() {
    {
        throw new Error("cannot evaulate generic declaration of non-generic method");
      }
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:516
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:516")
  public int arity() {
    int arity_value = getNumParameter();
    return arity_value;
  }
  /**
   * @see AST.Modifiers#hasAnnotationSafeVarargs() Modifiers.hasAnnotationSafeVarargs()
   * @attribute syn
   * @aspect SafeVarargs
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SafeVarargs.jrag:51
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
                  "illegal use of @SafeVarargs on non-varargs method %s().", name()));
          } else if (!isFinal() && !isStatic()) {
            return Collections.singleton(errorf(
                  "illegal use of @SafeVarargs on non-final and non-static method %s().", name()));
          }
        }
        return Collections.emptySet();
      }
  }
  /**
   * @attribute syn
   * @aspect SuppressWarnings
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SuppressWarnings.jrag:45
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SuppressWarnings", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SuppressWarnings.jrag:45")
  public boolean suppressWarnings(String type) {
    boolean suppressWarnings_String_value = hasAnnotationSuppressWarnings(type) || withinSuppressWarnings(type);
    return suppressWarnings_String_value;
  }
  /** @apilevel internal */
  private void subsignatureTo_MethodDecl_reset() {
    subsignatureTo_MethodDecl_computed = null;
    subsignatureTo_MethodDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map subsignatureTo_MethodDecl_values;
  /** @apilevel internal */
  protected java.util.Map subsignatureTo_MethodDecl_computed;
  /**
   * @attribute syn
   * @aspect FunctionalInterface
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:44
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FunctionalInterface", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:44")
  public boolean subsignatureTo(MethodDecl m) {
    Object _parameters = m;
    if (subsignatureTo_MethodDecl_computed == null) subsignatureTo_MethodDecl_computed = new java.util.HashMap(4);
    if (subsignatureTo_MethodDecl_values == null) subsignatureTo_MethodDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (subsignatureTo_MethodDecl_values.containsKey(_parameters)
        && subsignatureTo_MethodDecl_computed.containsKey(_parameters)
        && (subsignatureTo_MethodDecl_computed.get(_parameters) == ASTState.NON_CYCLE || subsignatureTo_MethodDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) subsignatureTo_MethodDecl_values.get(_parameters);
    }
    boolean subsignatureTo_MethodDecl_value = subsignatureTo_compute(m);
    if (state().inCircle()) {
      subsignatureTo_MethodDecl_values.put(_parameters, subsignatureTo_MethodDecl_value);
      subsignatureTo_MethodDecl_computed.put(_parameters, state().cycle());
    
    } else {
      subsignatureTo_MethodDecl_values.put(_parameters, subsignatureTo_MethodDecl_value);
      subsignatureTo_MethodDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return subsignatureTo_MethodDecl_value;
  }
  /** @apilevel internal */
  private boolean subsignatureTo_compute(MethodDecl m) {
      if (fullSignature().equals(m.fullSignature())) {
        return true;
      } else if (fullSignature().equals(m.signature())) {
        return true;
      } else {
        return false;
      }
    }
  /** @apilevel internal */
  private void returnTypeSubstitutableFor_MethodDecl_reset() {
    returnTypeSubstitutableFor_MethodDecl_computed = null;
    returnTypeSubstitutableFor_MethodDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map returnTypeSubstitutableFor_MethodDecl_values;
  /** @apilevel internal */
  protected java.util.Map returnTypeSubstitutableFor_MethodDecl_computed;
  /**
   * @attribute syn
   * @aspect FunctionalInterface
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:68
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FunctionalInterface", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:68")
  public boolean returnTypeSubstitutableFor(MethodDecl m) {
    Object _parameters = m;
    if (returnTypeSubstitutableFor_MethodDecl_computed == null) returnTypeSubstitutableFor_MethodDecl_computed = new java.util.HashMap(4);
    if (returnTypeSubstitutableFor_MethodDecl_values == null) returnTypeSubstitutableFor_MethodDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (returnTypeSubstitutableFor_MethodDecl_values.containsKey(_parameters)
        && returnTypeSubstitutableFor_MethodDecl_computed.containsKey(_parameters)
        && (returnTypeSubstitutableFor_MethodDecl_computed.get(_parameters) == ASTState.NON_CYCLE || returnTypeSubstitutableFor_MethodDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) returnTypeSubstitutableFor_MethodDecl_values.get(_parameters);
    }
    boolean returnTypeSubstitutableFor_MethodDecl_value = returnTypeSubstitutableFor_compute(m);
    if (state().inCircle()) {
      returnTypeSubstitutableFor_MethodDecl_values.put(_parameters, returnTypeSubstitutableFor_MethodDecl_value);
      returnTypeSubstitutableFor_MethodDecl_computed.put(_parameters, state().cycle());
    
    } else {
      returnTypeSubstitutableFor_MethodDecl_values.put(_parameters, returnTypeSubstitutableFor_MethodDecl_value);
      returnTypeSubstitutableFor_MethodDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return returnTypeSubstitutableFor_MethodDecl_value;
  }
  /** @apilevel internal */
  private boolean returnTypeSubstitutableFor_compute(MethodDecl m) {
      TypeDecl t1 = type();
      TypeDecl t2 = m.type();
      if (t1 instanceof VoidType && t2 instanceof VoidType) {
        return true;
      } else if (t1 instanceof PrimitiveType && t2 instanceof PrimitiveType) {
        PrimitiveType p1 = (PrimitiveType) t1;
        PrimitiveType p2 = (PrimitiveType) t2;
        return p1 == p2;
      } else if (t1.isReferenceType() && t2.isReferenceType()) {
        if (t1.strictSubtype(t2)) {
          return true;
        } else {
          return !sameSignature(m) && (t1 == t2.erasure() || t1.erasure() == t2);
        }
      } else {
        return false;
      }
    }
  /**
   * If the method is parameterized, this returns the non-wildcard parameterization
   * according to the rules specified in JLS 8 &sect;9.9.
   * Otherwise, an unknown method is returned.
   * @attribute syn
   * @aspect LambdaParametersInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:536
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaParametersInference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:536")
  public Option<MethodDecl> nonWildcardParameterization() {
    Option<MethodDecl> nonWildcardParameterization_value = Option.some(this);
    return nonWildcardParameterization_value;
  }
  /**
   * @attribute syn
   * @aspect Java8Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/Modifiers.jrag:31
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Java8Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/Modifiers.jrag:31")
  public boolean isDefault() {
    boolean isDefault_value = getModifiers().isDefault();
    return isDefault_value;
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:72
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:40")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = hasBlock() && getBlock().modifiedInScope(var);
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
   * @aspect FilterUtils
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:22
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FilterUtils", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:21")
  public TypeDecl type2() {
    TypeDecl type2_value = type();
    return type2_value;
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
                                                  .getOrDefault(param, Collections.emptySet())
                                                  .stream()
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
   * @aspect MethodCall
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:106
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodCall", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:106")
  public Set<InclusionEdge> declarationReturnValues() {
    Set<InclusionEdge> declarationReturnValues_value = returnValues()
            .stream()
            .filter(x -> x.hasDeclaration())
            .map(x -> new InclusionEdge(x.getDeclaration(), this))
            .collect(Collectors.toSet());
    return declarationReturnValues_value;
  }
  /**
   * @attribute syn
   * @aspect MethodCall
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:115
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodCall", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:115")
  public ASTNode getNode() {
    ASTNode getNode_value = this;
    return getNode_value;
  }
  /**
   * @attribute syn
   * @aspect MethodCall
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:116
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Utilities", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:285")
  public DeclarationSite getDeclaration() {
    DeclarationSite getDeclaration_value = this;
    return getDeclaration_value;
  }
  /**
   * @attribute syn
   * @aspect MethodCall
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:133
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodCall", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:133")
  public boolean isAnalyzable() {
    boolean isAnalyzable_value = compilationUnit().fromSource()
            || hostType().isMap()  && (getID().equals("put") || getID().equals("get"))
            || hostType().isList() && (getID().equals("add") || getID().equals("get"));
    return isAnalyzable_value;
  }
  /**
   * @attribute syn
   * @aspect runtimebenchmark
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/RuntimeBenchmark.jrag:18
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="runtimebenchmark", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/RuntimeBenchmark.jrag:18")
  public boolean hasParameterWithSubtype() {
    {
            for (ParameterDeclaration parameter : getParameterList()) {
                if (parameter.hasSubtypes()) {
                    return true;
                }
            }
            return false;
        }
  }
  /**
   * @attribute syn
   * @aspect pointerbench
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:112
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="pointerbench", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:112")
  public boolean isTestBlock() {
    {
            if (getBlockOpt().getNumChild() > 0) {
                return getBlockOpt().getChild(0).isTestBlock();
            } else {
                return false;
            }
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
   * Inherited attribute that represents the type declaration where this method
   * is declared.
   * 
   * @return The type declaration where this method is declared.
   * @attribute inh
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:408
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:408")
  public TypeDecl declaredIn() {
    ASTState state = state();
    if (declaredIn_computed == ASTState.NON_CYCLE || declaredIn_computed == state().cycle()) {
      return declaredIn_value;
    }
    declaredIn_value = getParent().Define_declaredIn(this, null);
    if (state().inCircle()) {
      declaredIn_computed = state().cycle();
    
    } else {
      declaredIn_computed = ASTState.NON_CYCLE;
    
    }
    return declaredIn_value;
  }
  /** @apilevel internal */
  private void declaredIn_reset() {
    declaredIn_computed = null;
    declaredIn_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle declaredIn_computed = null;

  /** @apilevel internal */
  protected TypeDecl declaredIn_value;

  /**
   * @attribute inh
   * @aspect ExceptionHandling
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:95
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:95")
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
   * Returns a method declaration representing unknown methods.
   * Used in method lookup when no matching method was found.
   * @attribute inh
   * @aspect LookupMethod
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:43
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="LookupMethod", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:43")
  public MethodDecl unknownMethod() {
    MethodDecl unknownMethod_value = getParent().Define_unknownMethod(this, null);
    return unknownMethod_value;
  }
  /**
   * @attribute inh
   * @aspect LookupMethod
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:52
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="LookupMethod", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:52")
  public TypeDecl unknownType() {
    TypeDecl unknownType_value = getParent().Define_unknownType(this, null);
    return unknownType_value;
  }
  /**
   * @attribute inh
   * @aspect SuppressWarnings
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SuppressWarnings.jrag:38
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="SuppressWarnings", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SuppressWarnings.jrag:38")
  public boolean withinSuppressWarnings(String annot) {
    boolean withinSuppressWarnings_String_value = getParent().Define_withinSuppressWarnings(this, null, annot);
    return withinSuppressWarnings_String_value;
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
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:92
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return true;
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
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:93
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
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
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:94
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
    if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:201
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
    if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/UnreachableStatements.jrag:62
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
    if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:547
      return v.isField() || assignedBefore(v);
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
    if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:1159
      return !v.isField();
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:223
   * @apilevel internal
   */
  public boolean Define_inStaticContext(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:231
      return isStatic();
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:537
   * @apilevel internal
   */
  public TypeDecl Define_returnType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:539
      return type();
    }
    else {
      return getParent().Define_returnType(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:537
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute returnType
   */
  protected boolean canDefine_returnType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:433
   * @apilevel internal
   */
  public boolean Define_mayBePublic(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:327
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
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:328
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
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:329
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:438
   * @apilevel internal
   */
  public boolean Define_mayBeAbstract(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:330
      return true;
    }
    else {
      return getParent().Define_mayBeAbstract(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:438
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeAbstract
   */
  protected boolean canDefine_mayBeAbstract(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:436
   * @apilevel internal
   */
  public boolean Define_mayBeStatic(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:331
      return true;
    }
    else {
      return getParent().Define_mayBeStatic(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:436
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeStatic
   */
  protected boolean canDefine_mayBeStatic(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:437
   * @apilevel internal
   */
  public boolean Define_mayBeFinal(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:332
      return true;
    }
    else {
      return getParent().Define_mayBeFinal(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:437
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeFinal
   */
  protected boolean canDefine_mayBeFinal(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:442
   * @apilevel internal
   */
  public boolean Define_mayBeSynchronized(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:333
      return true;
    }
    else {
      return getParent().Define_mayBeSynchronized(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:442
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeSynchronized
   */
  protected boolean canDefine_mayBeSynchronized(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:443
   * @apilevel internal
   */
  public boolean Define_mayBeNative(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:334
      return true;
    }
    else {
      return getParent().Define_mayBeNative(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:443
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeNative
   */
  protected boolean canDefine_mayBeNative(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:441
   * @apilevel internal
   */
  public boolean Define_mayBeStrictfp(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:335
      return true;
    }
    else {
      return getParent().Define_mayBeStrictfp(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:441
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeStrictfp
   */
  protected boolean canDefine_mayBeStrictfp(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:30
   * @apilevel internal
   */
  public SimpleSet<Variable> Define_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:76
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return parameterDeclaration(name);
    }
    else if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:64
      {
          SimpleSet<Variable> result = parameterDeclaration(name);
          // A declaration of a method parameter name shadows any other variable declarations.
          if (!result.isEmpty()) {
            return result;
          }
          // Delegate to other declarations in scope.
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
    if (_callerNode == getExceptionListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:104
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return NameType.TYPE_NAME;
    }
    else if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:103
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return NameType.TYPE_NAME;
    }
    else if (getTypeAccessNoTransform() != null && _callerNode == getTypeAccess()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:102
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:46
   * @apilevel internal
   */
  public boolean Define_variableArityValid(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:42
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
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:154
      return name.equals("METHOD");
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
    return false;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:30
   * @apilevel internal
   */
  public boolean Define_inhModifiedInScope(ASTNode _callerNode, ASTNode _childNode, Variable var) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:73
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return hasBlock() && getBlock().modifiedInScope(var);
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
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:209
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
   * @aspect MethodCall
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:99
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="MethodCall", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:99")
  public Set<Expr> returnValues() {
    ASTState state = state();
    if (MethodDecl_returnValues_computed == ASTState.NON_CYCLE || MethodDecl_returnValues_computed == state().cycle()) {
      return MethodDecl_returnValues_value;
    }
    MethodDecl_returnValues_value = returnValues_compute();
    if (state().inCircle()) {
      MethodDecl_returnValues_computed = state().cycle();
    
    } else {
      MethodDecl_returnValues_computed = ASTState.NON_CYCLE;
    
    }
    return MethodDecl_returnValues_value;
  }
  /** @apilevel internal */
  private Set<Expr> returnValues_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    ASTNode root = (ASTNode) node;
    root.survey_MethodDecl_returnValues();
    Set<Expr> _computedValue = new HashSet<>();
    if (root.contributorMap_MethodDecl_returnValues.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_MethodDecl_returnValues.get(this)) {
        contributor.contributeTo_MethodDecl_returnValues(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle MethodDecl_returnValues_computed = null;

  /** @apilevel internal */
  protected Set<Expr> MethodDecl_returnValues_value;

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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:515
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:149
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:150
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SafeVarargs.jrag:131
    if (!suppressWarnings("unchecked")
              && !hasAnnotationSafeVarargs()
              && isVariableArity()
              && !getParameter(getNumParameter()-1).type().isReifiable()) {
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:113
    {
      InvocationTarget target = (InvocationTarget) (this);
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
  protected void collect_contributors_Program_methodsOfInterest(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/RuntimeBenchmark.jrag:28
    if (hasParameterWithSubtype() && calledIn().size() > 0) {
      {
        Program target = (Program) (program());
        java.util.Set<ASTNode> contributors = _map.get(target);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) target, contributors);
        }
        contributors.add(this);
      }
    }
    super.collect_contributors_Program_methodsOfInterest(_root, _map);
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
    for (Problem value : typeProblems()) {
      collection.add(value);
    }
    for (Problem value : modifierProblems()) {
      collection.add(value);
    }
    for (Problem value : nameProblems()) {
      collection.add(value);
    }
    if (!suppressWarnings("unchecked")
              && !hasAnnotationSafeVarargs()
              && isVariableArity()
              && !getParameter(getNumParameter()-1).type().isReifiable()) {
      collection.add(warning("possible heap pollution for variable arity parameter"));
    }
  }
  /** @apilevel internal */
  protected void contributeTo_InvocationTarget_inclusionEdges(Set<InclusionEdge> collection) {
    super.contributeTo_InvocationTarget_inclusionEdges(collection);
    for (InclusionEdge value : declarationReturnValues()) {
      collection.add(value);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_Program_methodsOfInterest(Set<MethodDecl> collection) {
    super.contributeTo_Program_methodsOfInterest(collection);
    if (hasParameterWithSubtype() && calledIn().size() > 0) {
      collection.add(this);
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
