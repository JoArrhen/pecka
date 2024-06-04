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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:201
 * @astdecl FieldDeclarator : Declarator ::= <ID:String> Dims* [Init:Expr] TypeAccess:Access;
 * @production FieldDeclarator : {@link Declarator};

 */
public class FieldDeclarator extends Declarator implements Cloneable, DeclarationSite {
  /** Create an access to this field. 
   * @aspect BoundNames
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/BoundNames.jrag:33
   */
  public Access createBoundAccess() {
    if (isStatic()) {
      return hostType().createQualifiedAccess().qualifiesAccess(new BoundFieldAccess(this));
    } else {
      return new ThisAccess().qualifiesAccess(new BoundFieldAccess(this));
    }
  }
  /**
   * Copies the declarator without initializer.
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1507
   */
  public FieldDeclarator signatureCopy() {
    return new FieldDeclarator(getID(), getDimsList().treeCopyNoTransform(), new Opt<Expr>());
  }
  /**
   * @declaredat ASTNode:1
   */
  public FieldDeclarator() {
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
    setChild(new List(), 0);
    setChild(new Opt(), 1);
  }
  /**
   * @declaredat ASTNode:15
   */
  @ASTNodeAnnotation.Constructor(
    name = {"ID", "Dims", "Init"},
    type = {"String", "List<Dims>", "Opt<Expr>"},
    kind = {"Token", "List", "Opt"}
  )
  public FieldDeclarator(String p0, List<Dims> p1, Opt<Expr> p2) {
    setID(p0);
    setChild(p1, 0);
    setChild(p2, 1);
  }
  /**
   * @declaredat ASTNode:25
   */
  public FieldDeclarator(beaver.Symbol p0, List<Dims> p1, Opt<Expr> p2) {
    setID(p0);
    setChild(p1, 0);
    setChild(p2, 1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:31
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:37
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:41
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    constant_reset();
    accessibleFrom_TypeDecl_reset();
    assignedAfter_Variable_reset();
    unassignedAfter_Variable_reset();
    exceptions_reset();
    usesTypeVariable_reset();
    isEffectivelyFinal_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:52
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:57
   */
  public FieldDeclarator clone() throws CloneNotSupportedException {
    FieldDeclarator node = (FieldDeclarator) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:62
   */
  public FieldDeclarator copy() {
    try {
      FieldDeclarator node = (FieldDeclarator) clone();
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
   * @declaredat ASTNode:81
   */
  @Deprecated
  public FieldDeclarator fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:91
   */
  public FieldDeclarator treeCopyNoTransform() {
    FieldDeclarator tree = (FieldDeclarator) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 2:
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
   * @declaredat ASTNode:116
   */
  public FieldDeclarator treeCopy() {
    FieldDeclarator tree = (FieldDeclarator) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 2:
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
   * Replaces the lexeme ID.
   * @param value The new value for the lexeme ID.
   * @apilevel high-level
   */
  public FieldDeclarator setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public FieldDeclarator setID(beaver.Symbol symbol) {
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
   * Replaces the Dims list.
   * @param list The new list node to be used as the Dims list.
   * @apilevel high-level
   */
  public FieldDeclarator setDimsList(List<Dims> list) {
    setChild(list, 0);
    return this;
  }
  /**
   * Retrieves the number of children in the Dims list.
   * @return Number of children in the Dims list.
   * @apilevel high-level
   */
  public int getNumDims() {
    return getDimsList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Dims list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the Dims list.
   * @apilevel low-level
   */
  public int getNumDimsNoTransform() {
    return getDimsListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Dims list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Dims list.
   * @apilevel high-level
   */
  public Dims getDims(int i) {
    return (Dims) getDimsList().getChild(i);
  }
  /**
   * Check whether the Dims list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasDims() {
    return getDimsList().getNumChild() != 0;
  }
  /**
   * Append an element to the Dims list.
   * @param node The element to append to the Dims list.
   * @apilevel high-level
   */
  public FieldDeclarator addDims(Dims node) {
    List<Dims> list = (parent == null) ? getDimsListNoTransform() : getDimsList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public FieldDeclarator addDimsNoTransform(Dims node) {
    List<Dims> list = getDimsListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the Dims list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public FieldDeclarator setDims(Dims node, int i) {
    List<Dims> list = getDimsList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the Dims list.
   * @return The node representing the Dims list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Dims")
  public List<Dims> getDimsList() {
    List<Dims> list = (List<Dims>) getChild(0);
    return list;
  }
  /**
   * Retrieves the Dims list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Dims list.
   * @apilevel low-level
   */
  public List<Dims> getDimsListNoTransform() {
    return (List<Dims>) getChildNoTransform(0);
  }
  /**
   * @return the element at index {@code i} in the Dims list without
   * triggering rewrites.
   */
  public Dims getDimsNoTransform(int i) {
    return (Dims) getDimsListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Dims list.
   * @return The node representing the Dims list.
   * @apilevel high-level
   */
  public List<Dims> getDimss() {
    return getDimsList();
  }
  /**
   * Retrieves the Dims list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Dims list.
   * @apilevel low-level
   */
  public List<Dims> getDimssNoTransform() {
    return getDimsListNoTransform();
  }
  /**
   * Replaces the optional node for the Init child. This is the <code>Opt</code>
   * node containing the child Init, not the actual child!
   * @param opt The new node to be used as the optional node for the Init child.
   * @apilevel low-level
   */
  public FieldDeclarator setInitOpt(Opt<Expr> opt) {
    setChild(opt, 1);
    return this;
  }
  /**
   * Replaces the (optional) Init child.
   * @param node The new node to be used as the Init child.
   * @apilevel high-level
   */
  public FieldDeclarator setInit(Expr node) {
    getInitOpt().setChild(node, 0);
    return this;
  }
  /**
   * Check whether the optional Init child exists.
   * @return {@code true} if the optional Init child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasInit() {
    return getInitOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) Init child.
   * @return The Init child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public Expr getInit() {
    return (Expr) getInitOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for the Init child. This is the <code>Opt</code> node containing the child Init, not the actual child!
   * @return The optional node for child the Init child.
   * @apilevel low-level
   */
  @ASTNodeAnnotation.OptChild(name="Init")
  public Opt<Expr> getInitOpt() {
    return (Opt<Expr>) getChild(1);
  }
  /**
   * Retrieves the optional node for child Init. This is the <code>Opt</code> node containing the child Init, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child Init.
   * @apilevel low-level
   */
  public Opt<Expr> getInitOptNoTransform() {
    return (Opt<Expr>) getChildNoTransform(1);
  }
  /**
   * Retrieves the TypeAccess child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the TypeAccess child.
   * @apilevel low-level
   */
  public Access getTypeAccessNoTransform() {
    return (Access) getChildNoTransform(2);
  }
  /**
   * Retrieves the child position of the optional child TypeAccess.
   * @return The the child position of the optional child TypeAccess.
   * @apilevel low-level
   */
  protected int getTypeAccessChildPosition() {
    return 2;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:55
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:55")
  public boolean isParameter() {
    boolean isParameter_value = false;
    return isParameter_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:57
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:57")
  public boolean isClassVariable() {
    boolean isClassVariable_value = isStatic() || hostType().isInterfaceDecl();
    return isClassVariable_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:58
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:58")
  public boolean isInstanceVariable() {
    boolean isInstanceVariable_value = (hostType().isClassDecl() || hostType().isAnonymous()) && !isStatic();
    return isInstanceVariable_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:60
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:60")
  public boolean isMethodParameter() {
    boolean isMethodParameter_value = false;
    return isMethodParameter_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:61
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:61")
  public boolean isConstructorParameter() {
    boolean isConstructorParameter_value = false;
    return isConstructorParameter_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:62
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:62")
  public boolean isExceptionHandlerParameter() {
    boolean isExceptionHandlerParameter_value = false;
    return isExceptionHandlerParameter_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:63
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:63")
  public boolean isLocalVariable() {
    boolean isLocalVariable_value = false;
    return isLocalVariable_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:64
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:64")
  public boolean isField() {
    boolean isField_value = true;
    return isField_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:66
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:66")
  public boolean isBlank() {
    boolean isBlank_value = !hasInit();
    return isBlank_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:68
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:68")
  public String name() {
    String name_value = getID();
    return name_value;
  }
  /** @apilevel internal */
  private void constant_reset() {
    constant_computed = null;
    constant_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle constant_computed = null;

  /** @apilevel internal */
  protected Constant constant_value;

  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:69
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:69")
  public Constant constant() {
    ASTState state = state();
    if (constant_computed == ASTState.NON_CYCLE || constant_computed == state().cycle()) {
      return constant_value;
    }
    constant_value = type().cast(getInit().constant());
    if (state().inCircle()) {
      constant_computed = state().cycle();
    
    } else {
      constant_computed = ASTState.NON_CYCLE;
    
    }
    return constant_value;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AccessControl.jrag:138
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AccessControl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AccessControl.jrag:138")
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
   * @aspect DefiniteAssignment
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:197
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:197")
  public Collection<Problem> definiteAssignmentProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        if (isBlank() && isFinal() && isClassVariable()) {
          boolean found = false;
          TypeDecl typeDecl = hostType();
          for (int i = 0; i < typeDecl.getNumBodyDecl(); i++) {
            if (typeDecl.getBodyDecl(i) instanceof StaticInitializer) {
              StaticInitializer s = (StaticInitializer) typeDecl.getBodyDecl(i);
              if (s.assignedAfter(this)) {
                found = true;
              }
            } else if (typeDecl.getBodyDecl(i) instanceof FieldDecl) {
              FieldDecl f = (FieldDecl) typeDecl.getBodyDecl(i);
              if (f.isStatic() && f.assignedAfter(this)) {
                found = true;
              }
            }
          }
          if (!found) {
            problems.add(errorf("blank final class variable %s in %s is not definitely assigned "
                + "in static initializer", name(), hostType().typeName()));
          }
        }
        if (isBlank() && isFinal() && isInstanceVariable()) {
          TypeDecl typeDecl = hostType();
          boolean found = false;
          for (int i = 0; !found && i < typeDecl.getNumBodyDecl(); i++) {
            if (typeDecl.getBodyDecl(i) instanceof FieldDecl) {
              FieldDecl f = (FieldDecl) typeDecl.getBodyDecl(i);
              if (!f.isStatic() && f.assignedAfter(this)) {
                found = true;
              }
            } else if (typeDecl.getBodyDecl(i) instanceof InstanceInitializer) {
              InstanceInitializer ii = (InstanceInitializer) typeDecl.getBodyDecl(i);
              if (ii.getBlock().assignedAfter(this)) {
                found = true;
              }
            }
          }
          if (!found) {
            for (ConstructorDecl cons : typeDecl.constructors()) {
              if (!cons.assignedAfter(this)) {
                problems.add(errorf(
                    "blank final instance variable %s in %s is not definitely assigned after %s",
                    name(), hostType().typeName(), cons.signature()));
              }
            }
          }
        }
        if (isBlank() && hostType().isInterfaceDecl()) {
          problems.add(errorf("variable  %s in %s which is an interface must have an initializer",
              name(), hostType().typeName()));
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
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:632")
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
        new_assignedAfter_Variable_value = hasInit()
              ? v == this || getInit().assignedAfter(v)
              : assignedBefore(v);
        if (((Boolean)_value.value) != new_assignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_assignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      assignedAfter_Variable_values.put(_parameters, new_assignedAfter_Variable_value);
      state.startLastCycle();
      boolean $tmp = hasInit()
            ? v == this || getInit().assignedAfter(v)
            : assignedBefore(v);

      state.leaveCircle();
      return new_assignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_assignedAfter_Variable_value = hasInit()
            ? v == this || getInit().assignedAfter(v)
            : assignedBefore(v);
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
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:1181")
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
        new_unassignedAfter_Variable_value = hasInit()
              ? v != this && getInit().unassignedAfter(v)
              : v == this || unassignedBefore(v);
        if (((Boolean)_value.value) != new_unassignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfter_Variable_values.put(_parameters, new_unassignedAfter_Variable_value);
      state.startLastCycle();
      boolean $tmp = hasInit()
            ? v != this && getInit().unassignedAfter(v)
            : v == this || unassignedBefore(v);

      state.leaveCircle();
      return new_unassignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfter_Variable_value = hasInit()
            ? v != this && getInit().unassignedAfter(v)
            : v == this || unassignedBefore(v);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:51
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:51")
  public Collection<Problem> typeProblems() {
    {
        if (hasInit()) {
          TypeDecl source = getInit().type();
          TypeDecl dest = type();
          if (!source.assignConversionTo(dest, getInit())
              && !getInit().type().isUnknown()) {
            return Collections.singletonList(errorf(
                "cannot assign field %s of type %s a value of type %s",
                name(), dest.typeName(), source.typeName()));
          }
        }
        return Collections.emptyList();
      }
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:278
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:276")
  public boolean isPublic() {
    boolean isPublic_value = getModifiers().isPublic() || hostType().isInterfaceDecl();
    return isPublic_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:295
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:295")
  public boolean isStatic() {
    boolean isStatic_value = getModifiers().isStatic() || hostType().isInterfaceDecl();
    return isStatic_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:297
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:297")
  public boolean isFinal() {
    boolean isFinal_value = getModifiers().isFinal() || hostType().isInterfaceDecl();
    return isFinal_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:298
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:298")
  public boolean isTransient() {
    boolean isTransient_value = getModifiers().isTransient();
    return isTransient_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:299
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:299")
  public boolean isVolatile() {
    boolean isVolatile_value = getModifiers().isVolatile();
    return isVolatile_value;
  }
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:451
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:451")
  public Collection<Problem> nameProblems() {
    {
        // 8.3
        for (Variable v : hostType().memberFields(name())) {
          if (v != this && v.hostType() == hostType()) {
            return Collections.singletonList(errorf("field named %s is multiply declared in type %s",
                name(), hostType().typeName()));
          }
        }
        return Collections.emptyList();
      }
  }
  /** @apilevel internal */
  private void exceptions_reset() {
    exceptions_computed = null;
    exceptions_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle exceptions_computed = null;

  /** @apilevel internal */
  protected Collection<TypeDecl> exceptions_value;

  /**
   * Used in bytecode generation to generate the exception list for this field.
   * @attribute syn
   * @aspect AnonymousClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:91
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AnonymousClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:91")
  public Collection<TypeDecl> exceptions() {
    ASTState state = state();
    if (exceptions_computed == ASTState.NON_CYCLE || exceptions_computed == state().cycle()) {
      return exceptions_value;
    }
    exceptions_value = exceptions_compute();
    if (state().inCircle()) {
      exceptions_computed = state().cycle();
    
    } else {
      exceptions_computed = ASTState.NON_CYCLE;
    
    }
    return exceptions_value;
  }
  /** @apilevel internal */
  private Collection<TypeDecl> exceptions_compute() {
      Collection<TypeDecl> exceptions = new HashSet<TypeDecl>();
      if (isInstanceVariable() && hasInit()) {
        collectExceptions(exceptions);
      }
      return exceptions;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1338
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1338")
  public boolean usesTypeVariable() {
    ASTState state = state();
    if (usesTypeVariable_computed == ASTState.NON_CYCLE || usesTypeVariable_computed == state().cycle()) {
      return usesTypeVariable_value;
    }
    usesTypeVariable_value = getTypeAccess().usesTypeVariable();
    if (state().inCircle()) {
      usesTypeVariable_computed = state().cycle();
    
    } else {
      usesTypeVariable_computed = ASTState.NON_CYCLE;
    
    }
    return usesTypeVariable_value;
  }
  /** @apilevel internal */
  private void isEffectivelyFinal_reset() {
    isEffectivelyFinal_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isEffectivelyFinal_computed = null;

  /** @apilevel internal */
  protected boolean isEffectivelyFinal_value;

  /**
   * @attribute syn
   * @aspect EffectivelyFinal
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:139
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="EffectivelyFinal", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:139")
  public boolean isEffectivelyFinal() {
    ASTState state = state();
    if (isEffectivelyFinal_computed == ASTState.NON_CYCLE || isEffectivelyFinal_computed == state().cycle()) {
      return isEffectivelyFinal_value;
    }
    isEffectivelyFinal_value = isFinal();
    if (state().inCircle()) {
      isEffectivelyFinal_computed = state().cycle();
    
    } else {
      isEffectivelyFinal_computed = ASTState.NON_CYCLE;
    
    }
    return isEffectivelyFinal_value;
  }
  /**
   * @attribute syn
   * @aspect FilterUtils
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:25
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FilterUtils", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:21")
  public TypeDecl type2() {
    TypeDecl type2_value = type();
    return type2_value;
  }
  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:49
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:49")
  public ASTNode getNode() {
    ASTNode getNode_value = this;
    return getNode_value;
  }
  /**
   * @attribute syn
   * @aspect Utilities
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:288
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Utilities", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:285")
  public DeclarationSite getDeclaration() {
    DeclarationSite getDeclaration_value = this;
    return getDeclaration_value;
  }
  /**
   * @attribute syn
   * @aspect Utilities
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:373
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Utilities", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:373")
  public Set<InvocationTarget> enclosingMethods() {
    { return hostType().constructorSet(); }
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
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1664
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1664")
  public FieldDeclarator erasedField() {
    FieldDeclarator erasedField_value = getParent().Define_erasedField(this, null);
    return erasedField_value;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (_callerNode == getInitOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:368
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:195
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:49
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:449
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/UncheckedConversion.jrag:46
    if (hasInit() && !suppressWarnings("unchecked")) {
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:101
    if (getInitOpt().getNumChild() > 0 && getInitOpt().getChild(0).hasDeclaration()) {
      for (InvocationTarget target : (Iterable<? extends InvocationTarget>) (enclosingMethods())) {
        java.util.Set<ASTNode> contributors = _map.get(target);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) target, contributors);
        }
        contributors.add(this);
      }
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:115
    for (InvocationTarget target : (Iterable<? extends InvocationTarget>) (enclosingMethods())) {
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:135
    for (InvocationTarget target : (Iterable<? extends InvocationTarget>) (enclosingMethods())) {
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:145
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
  protected void collect_contributors_InvocationTarget_pointsToEdges(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:110
    if (getInitOpt().getNumChild() == 0) {
      for (InvocationTarget target : (Iterable<? extends InvocationTarget>) (enclosingMethods())) {
        java.util.Set<ASTNode> contributors = _map.get(target);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) target, contributors);
        }
        contributors.add(this);
      }
    }
    super.collect_contributors_InvocationTarget_pointsToEdges(_root, _map);
  }
  /** @apilevel internal */
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    for (Problem value : definiteAssignmentProblems()) {
      collection.add(value);
    }
    for (Problem value : typeProblems()) {
      collection.add(value);
    }
    for (Problem value : nameProblems()) {
      collection.add(value);
    }
    if (hasInit() && !suppressWarnings("unchecked")) {
      for (Problem value : uncheckedConversionWarnings(getInit().type(), type())) {
        collection.add(value);
      }
    }
  }
  /** @apilevel internal */
  protected void contributeTo_InvocationTarget_inclusionEdges(Set<InclusionEdge> collection) {
    super.contributeTo_InvocationTarget_inclusionEdges(collection);
    if (getInitOpt().getNumChild() > 0 && getInitOpt().getChild(0).hasDeclaration()) {
      collection.add(new InclusionEdge(getInitOpt().getChild(0).getDeclaration(), this));
    }
    for (InclusionEdge value : ((ClassDecl) hostType())
                .subtypesIncludingSelf()
                .stream()
                .filter(subtype -> subtype.isClassDecl())
                .flatMap(subtype -> ((ClassDecl) subtype).instantiations().stream())
                .map(inst -> new FieldStoreEdge(this, inst, name()))
                .collect(Collectors.toSet())) {
      collection.add(value);
    }
    for (InclusionEdge value : ((ClassDecl) hostType())
                .instantiations()
                .stream()
                .map(inst -> new FieldLoadEdge(inst, getDeclaration(), name())) // Seems to work??
                //.map(inst -> new FieldStoreEdge(getDeclaration(), inst, name()))
                .collect(Collectors.toSet())) {
      collection.add(value);
    }
    for (InclusionEdge value : ((ClassDecl) hostType())
                .subtypes()
                .stream()
                .filter(x -> x.isClassDecl())
                .flatMap(subtype -> ((ClassDecl) subtype).instantiations().stream())
                .map(inst -> new FieldLoadEdge(inst, getDeclaration(), name()))
                .collect(Collectors.toSet())) {
      collection.add(value);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_InvocationTarget_pointsToEdges(Set<PointsToEdge> collection) {
    super.contributeTo_InvocationTarget_pointsToEdges(collection);
    if (getInitOpt().getNumChild() == 0) {
      collection.add(new PointsToEdge(this, (NullType) program().typeNull()));
    }
  }

}
