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
 * A resource declaration in a try with resources statement.
 * @ast node
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/grammar/TryWithResources.ast:10
 * @astdecl ResourceDeclaration : Resource ::= VariableDeclarator ResourceModifiers Access;
 * @production ResourceDeclaration : {@link Resource} ::= <span class="component">{@link VariableDeclarator}</span> <span class="component">{@link ResourceModifiers}</span> <span class="component">{@link Access}</span>;

 */
public class ResourceDeclaration extends Resource implements Cloneable {
  /**
   * @aspect Java7PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PrettyPrint.jadd:56
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(getTypeAccess());
    out.print(" ");
    out.print(getID());
    out.print(getDimsList());
    if (hasInit()) {
      out.print(" = ");
      out.print(getInit());
    }
  }
  /**
   * @declaredat ASTNode:1
   */
  public ResourceDeclaration() {
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
    children = new ASTNode[3];
  }
  /**
   * @declaredat ASTNode:13
   */
  @ASTNodeAnnotation.Constructor(
    name = {"VariableDeclarator", "ResourceModifiers", "Access"},
    type = {"VariableDeclarator", "ResourceModifiers", "Access"},
    kind = {"Child", "Child", "Child"}
  )
  public ResourceDeclaration(VariableDeclarator p0, ResourceModifiers p1, Access p2) {
    setChild(p0, 0);
    setChild(p1, 1);
    setChild(p2, 2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:24
   */
  protected int numChildren() {
    return 3;
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
    assignedAfter_Variable_reset();
    getTypeAccess_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:40
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:45
   */
  public ResourceDeclaration clone() throws CloneNotSupportedException {
    ResourceDeclaration node = (ResourceDeclaration) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:50
   */
  public ResourceDeclaration copy() {
    try {
      ResourceDeclaration node = (ResourceDeclaration) clone();
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
   * @declaredat ASTNode:69
   */
  @Deprecated
  public ResourceDeclaration fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:79
   */
  public ResourceDeclaration treeCopyNoTransform() {
    ResourceDeclaration tree = (ResourceDeclaration) copy();
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
   * @declaredat ASTNode:99
   */
  public ResourceDeclaration treeCopy() {
    ResourceDeclaration tree = (ResourceDeclaration) copy();
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
   * Replaces the VariableDeclarator child.
   * @param node The new node to replace the VariableDeclarator child.
   * @apilevel high-level
   */
  public ResourceDeclaration setVariableDeclarator(VariableDeclarator node) {
    setChild(node, 0);
    return this;
  }
  /**
   * Retrieves the VariableDeclarator child.
   * @return The current node used as the VariableDeclarator child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="VariableDeclarator")
  public VariableDeclarator getVariableDeclarator() {
    return (VariableDeclarator) getChild(0);
  }
  /**
   * Retrieves the VariableDeclarator child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the VariableDeclarator child.
   * @apilevel low-level
   */
  public VariableDeclarator getVariableDeclaratorNoTransform() {
    return (VariableDeclarator) getChildNoTransform(0);
  }
  /**
   * Replaces the ResourceModifiers child.
   * @param node The new node to replace the ResourceModifiers child.
   * @apilevel high-level
   */
  public ResourceDeclaration setResourceModifiers(ResourceModifiers node) {
    setChild(node, 1);
    return this;
  }
  /**
   * Retrieves the ResourceModifiers child.
   * @return The current node used as the ResourceModifiers child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="ResourceModifiers")
  public ResourceModifiers getResourceModifiers() {
    return (ResourceModifiers) getChild(1);
  }
  /**
   * Retrieves the ResourceModifiers child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the ResourceModifiers child.
   * @apilevel low-level
   */
  public ResourceModifiers getResourceModifiersNoTransform() {
    return (ResourceModifiers) getChildNoTransform(1);
  }
  /**
   * Replaces the Access child.
   * @param node The new node to replace the Access child.
   * @apilevel high-level
   */
  public ResourceDeclaration setAccess(Access node) {
    setChild(node, 2);
    return this;
  }
  /**
   * Retrieves the Access child.
   * @return The current node used as the Access child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Access")
  public Access getAccess() {
    return (Access) getChild(2);
  }
  /**
   * Retrieves the Access child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Access child.
   * @apilevel low-level
   */
  public Access getAccessNoTransform() {
    return (Access) getChildNoTransform(2);
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:92
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:78")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = getDeclarator().hasInit() && getDeclarator().getInit().modifiedInScope(var);
    return modifiedInScope_Variable_value;
  }
  /**
   * Type checking for TWR.
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:49
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:49")
  public Collection<Problem> typeProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        TypeDecl typeAutoCloseable = lookupType("java.lang", "AutoCloseable");
        if (typeAutoCloseable.isUnknown()) {
          problems.add(error("java.lang.AutoCloseable not found"));
        } else if (!getAccess().type().subtype(typeAutoCloseable)) {
          problems.add(error("Resource specification must declare an AutoCloseable resource"));
        }
        return problems;
      }
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
      return getDeclarator().assignedAfter(v);
    }
  /**
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:309
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:307")
  public Modifiers getModifiers() {
    Modifiers getModifiers_value = getResourceModifiers();
    return getModifiers_value;
  }
  /** @apilevel internal */
  private void getTypeAccess_reset() {
    getTypeAccess_computed = null;
    getTypeAccess_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle getTypeAccess_computed = null;

  /** @apilevel internal */
  protected Access getTypeAccess_value;

  /**
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:311
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:311")
  public Access getTypeAccess() {
    ASTState state = state();
    if (getTypeAccess_computed == ASTState.NON_CYCLE || getTypeAccess_computed == state().cycle()) {
      return getTypeAccess_value;
    }
    getTypeAccess_value = getAccess().treeCopyNoTransform();
    if (state().inCircle()) {
      getTypeAccess_computed = state().cycle();
    
    } else {
      getTypeAccess_computed = ASTState.NON_CYCLE;
    
    }
    return getTypeAccess_value;
  }
  /**
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:334
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:333")
  public boolean declaresVariable(String name) {
    boolean declaresVariable_String_value = getDeclarator().declaresVariable(name);
    return declaresVariable_String_value;
  }
  /**
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:337
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:336")
  public TypeDecl type() {
    TypeDecl type_value = getDeclarator().type();
    return type_value;
  }
  /**
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:339
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:338")
  public String getID() {
    String getID_value = getDeclarator().getID();
    return getID_value;
  }
  /**
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:341
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:340")
  public List<Dims> getDimsList() {
    List<Dims> getDimsList_value = getDeclarator().getDimsList();
    return getDimsList_value;
  }
  /**
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:342
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:342")
  public boolean hasInit() {
    boolean hasInit_value = getDeclarator().hasInit();
    return hasInit_value;
  }
  /**
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:343
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:343")
  public Expr getInit() {
    Expr getInit_value = getDeclarator().getInit();
    return getInit_value;
  }
  /**
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:345
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:344")
  public String name() {
    String name_value = getDeclarator().name();
    return name_value;
  }
  /**
   * @attribute syn
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:348
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:347")
  public VariableDeclarator getDeclarator() {
    VariableDeclarator getDeclarator_value = getVariableDeclarator();
    return getDeclarator_value;
  }
  /**
   * Inherit the lookupType attribute in ResourceDeclaration.
   * @attribute inh
   * @aspect TryWithResources
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:42
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TryWithResources", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:42")
  public TypeDecl lookupType(String packageName, String typeName) {
    TypeDecl lookupType_String_String_value = getParent().Define_lookupType(this, null, packageName, typeName);
    return lookupType_String_String_value;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:116
   * @apilevel internal
   */
  public Collection<MethodDecl> Define_lookupMethod(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == closeAccess_value) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:260
      return getAccess().type().memberMethods(name);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:223
   * @apilevel internal
   */
  public boolean Define_inStaticContext(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == closeAccess_value) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:265
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:133
   * @apilevel internal
   */
  public Modifiers Define_declarationModifiers(ASTNode _callerNode, ASTNode _childNode) {
    if (getVariableDeclaratorNoTransform() != null && _callerNode == getVariableDeclarator()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:316
      return getResourceModifiers();
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
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:144
   * @apilevel internal
   */
  public Access Define_declarationType(ASTNode _callerNode, ASTNode _childNode) {
    if (getVariableDeclaratorNoTransform() != null && _callerNode == getVariableDeclarator()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:319
      return getAccess();
    }
    else {
      return getParent().Define_declarationType(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:144
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute declarationType
   */
  protected boolean canDefine_declarationType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:437
   * @apilevel internal
   */
  public boolean Define_mayBeFinal(ASTNode _callerNode, ASTNode _childNode) {
    if (getResourceModifiersNoTransform() != null && _callerNode == getResourceModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:323
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (getAccessNoTransform() != null && _callerNode == getAccess()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:327
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:131
   * @apilevel internal
   */
  public boolean Define_mayUseAnnotationTarget(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (getResourceModifiersNoTransform() != null && _callerNode == getResourceModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:329
      return name.equals("LOCAL_VARIABLE");
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:44
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:198
    if (resourcePreviouslyDeclared(name())) {
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
    for (Problem value : typeProblems()) {
      collection.add(value);
    }
    if (resourcePreviouslyDeclared(name())) {
      collection.add(errorf("A resource with the name %s has already been declared in this try statement.",
                name()));
    }
  }

}
