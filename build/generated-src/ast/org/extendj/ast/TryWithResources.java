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
 * The JSR 334 try with resources statement.
 * @ast node
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/grammar/TryWithResources.ast:4
 * @astdecl TryWithResources : TryStmt ::= Resource* Block CatchClause* [Finally:Block] ExceptionHandler:Block;
 * @production TryWithResources : {@link TryStmt} ::= <span class="component">{@link Resource}*</span> <span class="component">{@link Block}</span> <span class="component">{@link CatchClause}*</span> <span class="component">[Finally:{@link Block}]</span>;

 */
public class TryWithResources extends TryStmt implements Cloneable, VariableScope {
  /**
   * @aspect Java7PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PrettyPrint.jadd:68
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print("try (");
    out.join(getResourceList(), new PrettyPrinter.Joiner() {
      @Override
      public void printSeparator(PrettyPrinter out) {
        out.print("; ");
      }
    });
    out.print(") ");
    out.print(getBlock());
    if (hasCatchClause()) {
      out.print(" ");
      out.join(getCatchClauseList(), new PrettyPrinter.Joiner() {
        @Override
        public void printSeparator(PrettyPrinter out) {
          out.print(" ");
        }
      });
    }
    if (hasFinally()) {
      out.print(" finally ");
      out.print(getFinally());
    }
  }
  /**
   * Returns {@code true} if the try-with-resources statement can throw
   * an exception of type (or a subtype of) catchType.
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:225
   */
  protected boolean reachedException(TypeDecl catchType) {
    boolean found = false;
    // Found is true if the exception type is caught by a catch clause.
    for (int i = 0; i < getNumCatchClause() && !found; i++) {
      if (getCatchClause(i).handles(catchType)) {
        found = true;
      }
    }
    // If an exception is thrown in the block and the exception is not caught and
    // either there is no finally block or the finally block can complete normally.
    if (!found && (!hasNonEmptyFinally() || getFinally().canCompleteNormally()) ) {
      if (catchableException(catchType)) {
        return true;
      }
    }
    // Even if the exception is caught by the catch clauses they may
    // throw new exceptions.
    for (int i = 0; i < getNumCatchClause(); i++) {
      if (getCatchClause(i).reachedException(catchType)) {
        return true;
      }
    }
    return hasNonEmptyFinally() && getFinally().reachedException(catchType);
  }
  /**
   * @declaredat ASTNode:1
   */
  public TryWithResources() {
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
    setChild(new List(), 0);
    setChild(new List(), 2);
    setChild(new Opt(), 3);
  }
  /**
   * @declaredat ASTNode:16
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Resource", "Block", "CatchClause", "Finally"},
    type = {"List<Resource>", "Block", "List<CatchClause>", "Opt<Block>"},
    kind = {"List", "Child", "List", "Opt"}
  )
  public TryWithResources(List<Resource> p0, Block p1, List<CatchClause> p2, Opt<Block> p3) {
    setChild(p0, 0);
    setChild(p1, 1);
    setChild(p2, 2);
    setChild(p3, 3);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:28
   */
  protected int numChildren() {
    return 4;
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
    localLookup_String_reset();
    localVariableDeclaration_String_reset();
    assignedAfter_Variable_reset();
    catchableException_TypeDecl_reset();
    handlesException_TypeDecl_reset();
    typeError_reset();
    typeRuntimeException_reset();
    lookupVariable_String_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:50
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:55
   */
  public TryWithResources clone() throws CloneNotSupportedException {
    TryWithResources node = (TryWithResources) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:60
   */
  public TryWithResources copy() {
    try {
      TryWithResources node = (TryWithResources) clone();
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
   * @declaredat ASTNode:79
   */
  @Deprecated
  public TryWithResources fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:89
   */
  public TryWithResources treeCopyNoTransform() {
    TryWithResources tree = (TryWithResources) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 4:
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
   * @declaredat ASTNode:114
   */
  public TryWithResources treeCopy() {
    TryWithResources tree = (TryWithResources) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 4:
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
   * Replaces the Resource list.
   * @param list The new list node to be used as the Resource list.
   * @apilevel high-level
   */
  public TryWithResources setResourceList(List<Resource> list) {
    setChild(list, 0);
    return this;
  }
  /**
   * Retrieves the number of children in the Resource list.
   * @return Number of children in the Resource list.
   * @apilevel high-level
   */
  public int getNumResource() {
    return getResourceList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Resource list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the Resource list.
   * @apilevel low-level
   */
  public int getNumResourceNoTransform() {
    return getResourceListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Resource list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Resource list.
   * @apilevel high-level
   */
  public Resource getResource(int i) {
    return (Resource) getResourceList().getChild(i);
  }
  /**
   * Check whether the Resource list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasResource() {
    return getResourceList().getNumChild() != 0;
  }
  /**
   * Append an element to the Resource list.
   * @param node The element to append to the Resource list.
   * @apilevel high-level
   */
  public TryWithResources addResource(Resource node) {
    List<Resource> list = (parent == null) ? getResourceListNoTransform() : getResourceList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public TryWithResources addResourceNoTransform(Resource node) {
    List<Resource> list = getResourceListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the Resource list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public TryWithResources setResource(Resource node, int i) {
    List<Resource> list = getResourceList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the Resource list.
   * @return The node representing the Resource list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Resource")
  public List<Resource> getResourceList() {
    List<Resource> list = (List<Resource>) getChild(0);
    return list;
  }
  /**
   * Retrieves the Resource list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Resource list.
   * @apilevel low-level
   */
  public List<Resource> getResourceListNoTransform() {
    return (List<Resource>) getChildNoTransform(0);
  }
  /**
   * @return the element at index {@code i} in the Resource list without
   * triggering rewrites.
   */
  public Resource getResourceNoTransform(int i) {
    return (Resource) getResourceListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Resource list.
   * @return The node representing the Resource list.
   * @apilevel high-level
   */
  public List<Resource> getResources() {
    return getResourceList();
  }
  /**
   * Retrieves the Resource list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Resource list.
   * @apilevel low-level
   */
  public List<Resource> getResourcesNoTransform() {
    return getResourceListNoTransform();
  }
  /**
   * Replaces the Block child.
   * @param node The new node to replace the Block child.
   * @apilevel high-level
   */
  public TryWithResources setBlock(Block node) {
    setChild(node, 1);
    return this;
  }
  /**
   * Retrieves the Block child.
   * @return The current node used as the Block child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Block")
  public Block getBlock() {
    return (Block) getChild(1);
  }
  /**
   * Retrieves the Block child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Block child.
   * @apilevel low-level
   */
  public Block getBlockNoTransform() {
    return (Block) getChildNoTransform(1);
  }
  /**
   * Replaces the CatchClause list.
   * @param list The new list node to be used as the CatchClause list.
   * @apilevel high-level
   */
  public TryWithResources setCatchClauseList(List<CatchClause> list) {
    setChild(list, 2);
    return this;
  }
  /**
   * Retrieves the number of children in the CatchClause list.
   * @return Number of children in the CatchClause list.
   * @apilevel high-level
   */
  public int getNumCatchClause() {
    return getCatchClauseList().getNumChild();
  }
  /**
   * Retrieves the number of children in the CatchClause list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the CatchClause list.
   * @apilevel low-level
   */
  public int getNumCatchClauseNoTransform() {
    return getCatchClauseListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the CatchClause list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the CatchClause list.
   * @apilevel high-level
   */
  public CatchClause getCatchClause(int i) {
    return (CatchClause) getCatchClauseList().getChild(i);
  }
  /**
   * Check whether the CatchClause list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasCatchClause() {
    return getCatchClauseList().getNumChild() != 0;
  }
  /**
   * Append an element to the CatchClause list.
   * @param node The element to append to the CatchClause list.
   * @apilevel high-level
   */
  public TryWithResources addCatchClause(CatchClause node) {
    List<CatchClause> list = (parent == null) ? getCatchClauseListNoTransform() : getCatchClauseList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public TryWithResources addCatchClauseNoTransform(CatchClause node) {
    List<CatchClause> list = getCatchClauseListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the CatchClause list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public TryWithResources setCatchClause(CatchClause node, int i) {
    List<CatchClause> list = getCatchClauseList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the CatchClause list.
   * @return The node representing the CatchClause list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="CatchClause")
  public List<CatchClause> getCatchClauseList() {
    List<CatchClause> list = (List<CatchClause>) getChild(2);
    return list;
  }
  /**
   * Retrieves the CatchClause list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the CatchClause list.
   * @apilevel low-level
   */
  public List<CatchClause> getCatchClauseListNoTransform() {
    return (List<CatchClause>) getChildNoTransform(2);
  }
  /**
   * @return the element at index {@code i} in the CatchClause list without
   * triggering rewrites.
   */
  public CatchClause getCatchClauseNoTransform(int i) {
    return (CatchClause) getCatchClauseListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the CatchClause list.
   * @return The node representing the CatchClause list.
   * @apilevel high-level
   */
  public List<CatchClause> getCatchClauses() {
    return getCatchClauseList();
  }
  /**
   * Retrieves the CatchClause list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the CatchClause list.
   * @apilevel low-level
   */
  public List<CatchClause> getCatchClausesNoTransform() {
    return getCatchClauseListNoTransform();
  }
  /**
   * Replaces the optional node for the Finally child. This is the <code>Opt</code>
   * node containing the child Finally, not the actual child!
   * @param opt The new node to be used as the optional node for the Finally child.
   * @apilevel low-level
   */
  public TryWithResources setFinallyOpt(Opt<Block> opt) {
    setChild(opt, 3);
    return this;
  }
  /**
   * Replaces the (optional) Finally child.
   * @param node The new node to be used as the Finally child.
   * @apilevel high-level
   */
  public TryWithResources setFinally(Block node) {
    getFinallyOpt().setChild(node, 0);
    return this;
  }
  /**
   * Check whether the optional Finally child exists.
   * @return {@code true} if the optional Finally child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasFinally() {
    return getFinallyOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) Finally child.
   * @return The Finally child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public Block getFinally() {
    return (Block) getFinallyOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for the Finally child. This is the <code>Opt</code> node containing the child Finally, not the actual child!
   * @return The optional node for child the Finally child.
   * @apilevel low-level
   */
  @ASTNodeAnnotation.OptChild(name="Finally")
  public Opt<Block> getFinallyOpt() {
    return (Opt<Block>) getChild(3);
  }
  /**
   * Retrieves the optional node for child Finally. This is the <code>Opt</code> node containing the child Finally, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child Finally.
   * @apilevel low-level
   */
  public Opt<Block> getFinallyOptNoTransform() {
    return (Opt<Block>) getChildNoTransform(3);
  }
  /**
   * Retrieves the ExceptionHandler child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the ExceptionHandler child.
   * @apilevel low-level
   */
  public Block getExceptionHandlerNoTransform() {
    return (Block) getChildNoTransform(4);
  }
  /**
   * Retrieves the child position of the optional child ExceptionHandler.
   * @return The the child position of the optional child ExceptionHandler.
   * @apilevel low-level
   */
  protected int getExceptionHandlerChildPosition() {
    return 4;
  }
  /**
   * Exception error checks.
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:65
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:65")
  public Collection<Problem> exceptionHandlingProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        // Check exception handling of exceptions on auto closing of resource.
        for (Resource resource : getResourceList()) {
          MethodDecl close = resource.closeAccess().decl();
          for (Access exception : close.getExceptionList()) {
            TypeDecl exceptionType = exception.type();
            if (!twrHandlesException(exceptionType)) {
              problems.add(errorf(
                  "automatic closing of resource %s may raise the uncaught exception %s; "
                  + "it must be caught or declared as being thrown",
                  resource.name(), exceptionType.fullName()));
            }
          }
        }
        return problems;
      }
  }
  /**
   * This attribute computes whether or not the TWR statement
   * has a catch clause which handles the exception.
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:87
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:87")
  public boolean catchHandlesException(TypeDecl exceptionType) {
    {
        for (int i = 0; i < getNumCatchClause(); i++) {
          if (getCatchClause(i).handles(exceptionType)) {
            return true;
          }
        }
        return false;
      }
  }
  /**
   * Returns true if exceptions of type exceptionType are handled
   * in the try-with-resources statement or any containing statement
   * within the directly enclosing method or initializer block.
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:101
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:101")
  public boolean twrHandlesException(TypeDecl exceptionType) {
    {
        if (catchHandlesException(exceptionType)) {
          return true;
        }
        if (hasNonEmptyFinally() && !getFinally().canCompleteNormally()) {
          return true;
        }
        return handlesException(exceptionType);
      }
  }
  /** @apilevel internal */
  private void localLookup_String_reset() {
    localLookup_String_computed = null;
    localLookup_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map localLookup_String_values;
  /** @apilevel internal */
  protected java.util.Map localLookup_String_computed;
  /**
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:155
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:155")
  public SimpleSet<Variable> localLookup(String name) {
    Object _parameters = name;
    if (localLookup_String_computed == null) localLookup_String_computed = new java.util.HashMap(4);
    if (localLookup_String_values == null) localLookup_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (localLookup_String_values.containsKey(_parameters)
        && localLookup_String_computed.containsKey(_parameters)
        && (localLookup_String_computed.get(_parameters) == ASTState.NON_CYCLE || localLookup_String_computed.get(_parameters) == state().cycle())) {
      return (SimpleSet<Variable>) localLookup_String_values.get(_parameters);
    }
    SimpleSet<Variable> localLookup_String_value = localLookup_compute(name);
    if (state().inCircle()) {
      localLookup_String_values.put(_parameters, localLookup_String_value);
      localLookup_String_computed.put(_parameters, state().cycle());
    
    } else {
      localLookup_String_values.put(_parameters, localLookup_String_value);
      localLookup_String_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return localLookup_String_value;
  }
  /** @apilevel internal */
  private SimpleSet<Variable> localLookup_compute(String name) {
      VariableDeclarator v = localVariableDeclaration(name);
      if (v != null) {
        return v;
      }
      return lookupVariable(name);
    }
  /** @apilevel internal */
  private void localVariableDeclaration_String_reset() {
    localVariableDeclaration_String_computed = null;
    localVariableDeclaration_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map localVariableDeclaration_String_values;
  /** @apilevel internal */
  protected java.util.Map localVariableDeclaration_String_computed;
  /**
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:163
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:163")
  public VariableDeclarator localVariableDeclaration(String name) {
    Object _parameters = name;
    if (localVariableDeclaration_String_computed == null) localVariableDeclaration_String_computed = new java.util.HashMap(4);
    if (localVariableDeclaration_String_values == null) localVariableDeclaration_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (localVariableDeclaration_String_values.containsKey(_parameters)
        && localVariableDeclaration_String_computed.containsKey(_parameters)
        && (localVariableDeclaration_String_computed.get(_parameters) == ASTState.NON_CYCLE || localVariableDeclaration_String_computed.get(_parameters) == state().cycle())) {
      return (VariableDeclarator) localVariableDeclaration_String_values.get(_parameters);
    }
    VariableDeclarator localVariableDeclaration_String_value = localVariableDeclaration_compute(name);
    if (state().inCircle()) {
      localVariableDeclaration_String_values.put(_parameters, localVariableDeclaration_String_value);
      localVariableDeclaration_String_computed.put(_parameters, state().cycle());
    
    } else {
      localVariableDeclaration_String_values.put(_parameters, localVariableDeclaration_String_value);
      localVariableDeclaration_String_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return localVariableDeclaration_String_value;
  }
  /** @apilevel internal */
  private VariableDeclarator localVariableDeclaration_compute(String name) {
      for (Resource resource : getResourceList()) {
        if (resource.declaresVariable(name)) {
          return resource.getDeclarator();
        }
      }
      return null;
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
  /**
   * True if the automatic closing of resources in this try-with-resources statement
   * may throw an exception of type catchType.
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:271
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:271")
  public boolean resourceClosingException(TypeDecl catchType) {
    {
        for (Resource resource : getResourceList()) {
          MethodDecl close = resource.closeAccess().decl();
          for (Access exception : close.getExceptionList()) {
            TypeDecl exceptionType = exception.type();
            if (catchType.mayCatch(exception.type())) {
              return true;
            }
          }
        }
        return false;
      }
  }
  /**
   * True if the resource initialization of this try-with-resources statement
   * may throw an exception of type catchType.
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:288
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:288")
  public boolean resourceInitializationException(TypeDecl catchType) {
    {
        for (Resource resource : getResourceList()) {
          if (resource.reachedException(catchType)) {
            return true;
          }
        }
        return false;
      }
  }
  /** @apilevel internal */
  private void catchableException_TypeDecl_reset() {
    catchableException_TypeDecl_computed = null;
    catchableException_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map catchableException_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map catchableException_TypeDecl_computed;
  /**
   * @see AST.TryStmt#catchableException(TypeDecl) TryStmt.catchableException(TypeDecl)
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:300
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:289")
  public boolean catchableException(TypeDecl type) {
    Object _parameters = type;
    if (catchableException_TypeDecl_computed == null) catchableException_TypeDecl_computed = new java.util.HashMap(4);
    if (catchableException_TypeDecl_values == null) catchableException_TypeDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (catchableException_TypeDecl_values.containsKey(_parameters)
        && catchableException_TypeDecl_computed.containsKey(_parameters)
        && (catchableException_TypeDecl_computed.get(_parameters) == ASTState.NON_CYCLE || catchableException_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) catchableException_TypeDecl_values.get(_parameters);
    }
    boolean catchableException_TypeDecl_value = getBlock().reachedException(type)
          || resourceClosingException(type)
          || resourceInitializationException(type);
    if (state().inCircle()) {
      catchableException_TypeDecl_values.put(_parameters, catchableException_TypeDecl_value);
      catchableException_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      catchableException_TypeDecl_values.put(_parameters, catchableException_TypeDecl_value);
      catchableException_TypeDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return catchableException_TypeDecl_value;
  }
  /**
   * Inherit the handlesException attribute from methoddecl.
   * @attribute inh
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:114
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:114")
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
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:122
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:122")
  public TypeDecl typeError() {
    ASTState state = state();
    if (typeError_computed == ASTState.NON_CYCLE || typeError_computed == state().cycle()) {
      return typeError_value;
    }
    typeError_value = getParent().Define_typeError(this, null);
    if (state().inCircle()) {
      typeError_computed = state().cycle();
    
    } else {
      typeError_computed = ASTState.NON_CYCLE;
    
    }
    return typeError_value;
  }
  /** @apilevel internal */
  private void typeError_reset() {
    typeError_computed = null;
    typeError_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeError_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeError_value;

  /**
   * @attribute inh
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:124
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:124")
  public TypeDecl typeRuntimeException() {
    ASTState state = state();
    if (typeRuntimeException_computed == ASTState.NON_CYCLE || typeRuntimeException_computed == state().cycle()) {
      return typeRuntimeException_value;
    }
    typeRuntimeException_value = getParent().Define_typeRuntimeException(this, null);
    if (state().inCircle()) {
      typeRuntimeException_computed = state().cycle();
    
    } else {
      typeRuntimeException_computed = ASTState.NON_CYCLE;
    
    }
    return typeRuntimeException_value;
  }
  /** @apilevel internal */
  private void typeRuntimeException_reset() {
    typeRuntimeException_computed = null;
    typeRuntimeException_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeRuntimeException_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeRuntimeException_value;

  /**
   * @attribute inh
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:174
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:174")
  public SimpleSet<Variable> lookupVariable(String name) {
    Object _parameters = name;
    if (lookupVariable_String_computed == null) lookupVariable_String_computed = new java.util.HashMap(4);
    if (lookupVariable_String_values == null) lookupVariable_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (lookupVariable_String_values.containsKey(_parameters)
        && lookupVariable_String_computed.containsKey(_parameters)
        && (lookupVariable_String_computed.get(_parameters) == ASTState.NON_CYCLE || lookupVariable_String_computed.get(_parameters) == state().cycle())) {
      return (SimpleSet<Variable>) lookupVariable_String_values.get(_parameters);
    }
    SimpleSet<Variable> lookupVariable_String_value = getParent().Define_lookupVariable(this, null, name);
    if (state().inCircle()) {
      lookupVariable_String_values.put(_parameters, lookupVariable_String_value);
      lookupVariable_String_computed.put(_parameters, state().cycle());
    
    } else {
      lookupVariable_String_values.put(_parameters, lookupVariable_String_value);
      lookupVariable_String_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return lookupVariable_String_value;
  }
  /** @apilevel internal */
  private void lookupVariable_String_reset() {
    lookupVariable_String_computed = null;
    lookupVariable_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map lookupVariable_String_values;
  /** @apilevel internal */
  protected java.util.Map lookupVariable_String_computed;
  /**
   * @attribute inh
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:180
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:180")
  public boolean resourcePreviouslyDeclared(String name) {
    boolean resourcePreviouslyDeclared_String_value = getParent().Define_resourcePreviouslyDeclared(this, null, name);
    return resourcePreviouslyDeclared_String_value;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:114
   * @apilevel internal
   */
  public boolean Define_handlesException(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:119
      return twrHandlesException(exceptionType);
    }
    else if (_callerNode == getResourceListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:116
      int i = _callerNode.getIndexOfChild(_childNode);
      return twrHandlesException(exceptionType);
    }
    else {
      return super.Define_handlesException(_callerNode, _childNode, exceptionType);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/UnreachableStatements.jrag:182
   * @apilevel internal
   */
  public boolean Define_reachableCatchClause(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    if (_callerNode == getCatchClauseListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:126
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      {
          for (int i = 0; i < childIndex; i++) {
            if (getCatchClause(i).handles(exceptionType)) {
              return false;
            }
          }
          if (catchableException(exceptionType)) {
            return true;
          }
          if (exceptionType.mayCatch(typeError()) || exceptionType.mayCatch(typeRuntimeException())) {
            return true;
          }
          return false;
        }
    }
    else {
      return super.Define_reachableCatchClause(_callerNode, _childNode, exceptionType);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/UnreachableStatements.jrag:182
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute reachableCatchClause
   */
  protected boolean canDefine_reachableCatchClause(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:30
   * @apilevel internal
   */
  public SimpleSet<Variable> Define_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getResourceListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:146
      int index = _callerNode.getIndexOfChild(_childNode);
      {
          for (int i = index - 1; i >= 0; --i) {
            if (getResource(i).declaresVariable(name)) {
              return getResource(i).getDeclarator();
            }
          }
          return lookupVariable(name);
        }
    }
    else if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:144
      return localLookup(name);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:31
   * @apilevel internal
   */
  public VariableScope Define_outerScope(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getResourceListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:176
      int i = _callerNode.getIndexOfChild(_childNode);
      return this;
    }
    else {
      return getParent().Define_outerScope(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:31
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute outerScope
   */
  protected boolean canDefine_outerScope(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:178
   * @apilevel internal
   */
  public boolean Define_resourcePreviouslyDeclared(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getResourceListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:182
      int index = _callerNode.getIndexOfChild(_childNode);
      {
          for (int i = 0; i < index; ++i) {
            if(getResource(i).name()!=null){
              if (getResource(i).name().equals(name)) {
                return true;
              }
            }
          }
          return false;
        }
    }
    else {
      return getParent().Define_resourcePreviouslyDeclared(this, _callerNode, name);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:178
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute resourcePreviouslyDeclared
   */
  protected boolean canDefine_resourcePreviouslyDeclared(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:211
      return getNumResource() == 0
            ? assignedBefore(v)
            : getResource(getNumResource() - 1).assignedAfter(v);
    }
    else if (_callerNode == getResourceListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:208
      int index = _callerNode.getIndexOfChild(_childNode);
      return index == 0 ? assignedBefore(v) : getResource(index - 1).assignedAfter(v);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:133
   * @apilevel internal
   */
  public Modifiers Define_declarationModifiers(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getResourceListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:313
      int index = _callerNode.getIndexOfChild(_childNode);
      return getResource(index).getModifiers();
    }
    else {
      return getParent().Define_declarationModifiers(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:133
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute declarationModifiers
   */
  protected boolean canDefine_declarationModifiers(ASTNode _callerNode, ASTNode _childNode) {
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:60
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
    for (Problem value : exceptionHandlingProblems()) {
      collection.add(value);
    }
  }

}
