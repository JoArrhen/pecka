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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:199
 * @astdecl VariableDeclarator : Declarator ::= <ID:String> Dims* [Init:Expr] TypeAccess:Access;
 * @production VariableDeclarator : {@link Declarator};

 */
public class VariableDeclarator extends Declarator implements Cloneable, DeclarationSite, Pointer {
  /**
   * @declaredat ASTNode:1
   */
  public VariableDeclarator() {
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
  public VariableDeclarator(String p0, List<Dims> p1, Opt<Expr> p2) {
    setID(p0);
    setChild(p1, 0);
    setChild(p2, 1);
  }
  /**
   * @declaredat ASTNode:25
   */
  public VariableDeclarator(beaver.Symbol p0, List<Dims> p1, Opt<Expr> p2) {
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
    isEffectivelyFinal_reset();
    unfilteredGeneratedEdges_reset();
    generatedEdges_reset();
    pointsToSet_reset();
    blockIndex_reset();
    enclosingLambda_reset();
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
  public VariableDeclarator clone() throws CloneNotSupportedException {
    VariableDeclarator node = (VariableDeclarator) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:62
   */
  public VariableDeclarator copy() {
    try {
      VariableDeclarator node = (VariableDeclarator) clone();
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
  public VariableDeclarator fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:91
   */
  public VariableDeclarator treeCopyNoTransform() {
    VariableDeclarator tree = (VariableDeclarator) copy();
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
  public VariableDeclarator treeCopy() {
    VariableDeclarator tree = (VariableDeclarator) copy();
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
  public VariableDeclarator setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public VariableDeclarator setID(beaver.Symbol symbol) {
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
  public VariableDeclarator setDimsList(List<Dims> list) {
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
  public VariableDeclarator addDims(Dims node) {
    List<Dims> list = (parent == null) ? getDimsListNoTransform() : getDimsList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public VariableDeclarator addDimsNoTransform(Dims node) {
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
  public VariableDeclarator setDims(Dims node, int i) {
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
  public VariableDeclarator setInitOpt(Opt<Expr> opt) {
    setChild(opt, 1);
    return this;
  }
  /**
   * Replaces the (optional) Init child.
   * @param node The new node to be used as the Init child.
   * @apilevel high-level
   */
  public VariableDeclarator setInit(Expr node) {
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:35
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:35")
  public boolean isParameter() {
    boolean isParameter_value = false;
    return isParameter_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:38
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:38")
  public boolean isClassVariable() {
    boolean isClassVariable_value = false;
    return isClassVariable_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:39
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:39")
  public boolean isInstanceVariable() {
    boolean isInstanceVariable_value = false;
    return isInstanceVariable_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:40
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:40")
  public boolean isMethodParameter() {
    boolean isMethodParameter_value = false;
    return isMethodParameter_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:41
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:41")
  public boolean isConstructorParameter() {
    boolean isConstructorParameter_value = false;
    return isConstructorParameter_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:42
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:42")
  public boolean isExceptionHandlerParameter() {
    boolean isExceptionHandlerParameter_value = false;
    return isExceptionHandlerParameter_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:43
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:43")
  public boolean isLocalVariable() {
    boolean isLocalVariable_value = true;
    return isLocalVariable_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:44
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:44")
  public boolean isField() {
    boolean isField_value = false;
    return isField_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:46
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:46")
  public boolean isFinal() {
    boolean isFinal_value = getModifiers().isFinal();
    return isFinal_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:47
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:47")
  public boolean isVolatile() {
    boolean isVolatile_value = getModifiers().isVolatile();
    return isVolatile_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:48
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:48")
  public boolean isBlank() {
    boolean isBlank_value = !hasInit();
    return isBlank_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:49
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:49")
  public boolean isStatic() {
    boolean isStatic_value = false;
    return isStatic_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:51
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:51")
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:53
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:53")
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
  /** @return {@code true} if this declarator declares a local variable with the given name. 
   * @attribute syn
   * @aspect VariableScope
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:238
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:238")
  public boolean declaresVariable(String name) {
    boolean declaresVariable_String_value = getID().equals(name);
    return declaresVariable_String_value;
  }
  /**
   * Check for illegal duplicate variable declarations and parameter shadowing.
   * See JLS8 $6.4.
   * @attribute syn
   * @aspect NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:486
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:486")
  public Collection<Problem> nameProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        for (Variable var : lookupVariable(name())) {
          if (var instanceof VariableDeclarator) {
            VariableDeclarator decl = (VariableDeclarator) var;
            if (decl != this && decl.enclosingBodyDecl() == enclosingBodyDecl()) {
              problems.add(errorf("duplicate declaration of local variable %s", name()));
            }
          } else if (var instanceof ParameterDeclaration) {
            ParameterDeclaration decl = (ParameterDeclaration) var;
            if (decl.enclosingBodyDecl() == enclosingBodyDecl()) {
              problems.add(errorf("formal parameter is shadowed by local variable %s", name()));
            }
          } else if (var instanceof CatchParameterDeclaration) {
            CatchParameterDeclaration decl = (CatchParameterDeclaration) var;
            if (decl.enclosingBodyDecl() == enclosingBodyDecl()) {
              problems.add(errorf("catch parameter is shadowed by local variable %s", name()));
            }
          } else if (var instanceof InferredParameterDeclaration) {
            InferredParameterDeclaration decl = (InferredParameterDeclaration) var;
            if (decl.enclosingBodyDecl() == enclosingBodyDecl()) {
              problems.add(errorf("lambda parameter is shadowed by local variable %s", name()));
            }
          }
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:89
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:89")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = hasInit() && getInit().modifiedInScope(var);
    return modifiedInScope_Variable_value;
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
   * Note: this attribute deviates from what the JLS says about effectively finalness,
   * simply because the attribute name would be confusing if it did not return true
   * when the variable was explicitly declared final. The JLS considers declared final
   * and effectively final to be mutually exclusive, we don't.
   * @attribute syn
   * @aspect EffectivelyFinal
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:152
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="EffectivelyFinal", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:152")
  public boolean isEffectivelyFinal() {
    ASTState state = state();
    if (isEffectivelyFinal_computed == ASTState.NON_CYCLE || isEffectivelyFinal_computed == state().cycle()) {
      return isEffectivelyFinal_value;
    }
    isEffectivelyFinal_value = isFinal() || !inhModifiedInScope(this);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:26
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:48
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:48")
  public ASTNode getNode() {
    ASTNode getNode_value = this;
    return getNode_value;
  }
  /**
   * @attribute syn
   * @aspect Utilities
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:282
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Utilities", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:282")
  public boolean hasInitializer() {
    boolean hasInitializer_value = getInitOpt().getNumChild() > 0;
    return hasInitializer_value;
  }
  /**
   * @attribute syn
   * @aspect Utilities
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:283
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Utilities", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:283")
  public Expr getInitializer() {
    Expr getInitializer_value = getInitOpt().getChild(0);
    return getInitializer_value;
  }
  /**
   * @attribute syn
   * @aspect Utilities
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:287
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:317
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Utilities", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:317")
  public VarAccess getInitializerAsVarAccess() {
    VarAccess getInitializerAsVarAccess_value = (VarAccess) getInitOpt().getChild(0);
    return getInitializerAsVarAccess_value;
  }
  /**
   * @attribute syn
   * @aspect Utilities
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:371
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Utilities", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:371")
  public Set<InvocationTarget> enclosingMethods() {
    { BodyDecl decl = enclosingBodyDecl(); return new HashSet<>(Arrays.asList((InvocationTarget) decl)); }
  }
  /**
   * @attribute syn
   * @aspect pointerbench
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:105
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="pointerbench", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:105")
  public boolean inTestBlock() {
    boolean inTestBlock_value = enclosingBodyDecl().isTestBlock();
    return inTestBlock_value;
  }
  /**
   * @attribute syn
   * @aspect requests
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Requests.jrag:89
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="requests", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Requests.jrag:89")
  public String pointsToString() {
    {
            String types = pointsToSet().stream()
                                    .map(allocsite -> allocsite.typeName())
                                    .distinct()
                                    .collect(Collectors.joining(", "));
    
            StringBuilder sb = new StringBuilder();
            sb.append(getID()).append(" -> ").append(types.isEmpty() ? "?" : types);
            return sb.toString();
        }
  }
  /**
   * @attribute syn
   * @aspect FilterUtils
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:53
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FilterUtils", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:53")
  public TypeDecl typ() {
    TypeDecl typ_value = type();
    return typ_value;
  }
  /** @apilevel internal */
  private void unfilteredGeneratedEdges_reset() {
    unfilteredGeneratedEdges_computed = null;
    unfilteredGeneratedEdges_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle unfilteredGeneratedEdges_computed = null;

  /** @apilevel internal */
  protected Set<PointsToEdge> unfilteredGeneratedEdges_value;

  /**
   * @attribute syn
   * @aspect FilterUtils
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:55
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FilterUtils", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:55")
  public Set<PointsToEdge> unfilteredGeneratedEdges() {
    ASTState state = state();
    if (unfilteredGeneratedEdges_computed == ASTState.NON_CYCLE || unfilteredGeneratedEdges_computed == state().cycle()) {
      return unfilteredGeneratedEdges_value;
    }
    unfilteredGeneratedEdges_value = unfilteredGeneratedEdges_compute();
    if (state().inCircle()) {
      unfilteredGeneratedEdges_computed = state().cycle();
    
    } else {
      unfilteredGeneratedEdges_computed = ASTState.NON_CYCLE;
    
    }
    return unfilteredGeneratedEdges_value;
  }
  /** @apilevel internal */
  private Set<PointsToEdge> unfilteredGeneratedEdges_compute() {
          Set<InvocationTarget> activeMethods = enclosingMethods().stream().flatMap(m -> m.selectedMethods().stream()).collect(Collectors.toSet());
          return new Solver(activeInclusionEdges(), activePointsToEdges(), null).solution();
      }
  /**
   * @attribute syn
   * @aspect FilterUtils
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:60
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FilterUtils", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:60")
  public Set<AllocationSite> unfilteredPointsToSet() {
    {
            if (type().isPrimitive()) {
                return Collections.emptySet();
            }
    
            return unfilteredGeneratedEdges()
            .stream()
            .filter(edge -> edge.from == this)
            .map(edge -> edge.to)
            .collect(Collectors.toSet());
        }
  }
  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:188
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:188")
  public String fileName() {
    String fileName_value = compilationUnit().pathName();
    return fileName_value;
  }
  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:191
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:191")
  public boolean isPointer() {
    boolean isPointer_value = !type().isPrimitive();
    return isPointer_value;
  }
  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:193
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:193")
  public DeclarationSite declaration() {
    DeclarationSite declaration_value = this;
    return declaration_value;
  }
  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:203
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:203")
  public Set<PointsToEdge> activePointsToEdges() {
    {
            HashSet<PointsToEdge> res = new HashSet<>();
            Set<InvocationTarget> activeMethods = enclosingMethods().stream().flatMap(m -> m.selectedMethods().stream()).collect(Collectors.toSet());
            for (InvocationTarget activeMethod : activeMethods) {
                res.addAll(activeMethod.pointsToEdges());
            }
            return res;
        }
  }
  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:216
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:216")
  public Set<InclusionEdge> activeInclusionEdges() {
    {
            HashSet<InclusionEdge> res = new HashSet<>();
            Set<InvocationTarget> activeMethods = enclosingMethods().stream().flatMap(m -> m.selectedMethods().stream()).collect(Collectors.toSet());
            for (InvocationTarget activeMethod : activeMethods) {
                res.addAll(activeMethod.inclusionEdges());
            }
            return res;
        }
  }
  /** @apilevel internal */
  private void generatedEdges_reset() {
    generatedEdges_computed = null;
    generatedEdges_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle generatedEdges_computed = null;

  /** @apilevel internal */
  protected Set<PointsToEdge> generatedEdges_value;

  /**
   * @attribute syn
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:5
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Solver", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:5")
  public Set<PointsToEdge> generatedEdges() {
    ASTState state = state();
    if (generatedEdges_computed == ASTState.NON_CYCLE || generatedEdges_computed == state().cycle()) {
      return generatedEdges_value;
    }
    generatedEdges_value = generatedEdges_compute();
    if (state().inCircle()) {
      generatedEdges_computed = state().cycle();
    
    } else {
      generatedEdges_computed = ASTState.NON_CYCLE;
    
    }
    return generatedEdges_value;
  }
  /** @apilevel internal */
  private Set<PointsToEdge> generatedEdges_compute() {
          Set<InvocationTarget> activeMethods = enclosingMethods().stream().flatMap(m -> m.selectedMethods().stream()).collect(Collectors.toSet());
          java.util.List<TypeDecl> types = new ArrayList<>();
          types.add(type());
  
          return new Solver(activeInclusionEdges(), activePointsToEdges(), types).solution();
      }
  /** @apilevel internal */
  private void pointsToSet_reset() {
    pointsToSet_computed = null;
    pointsToSet_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle pointsToSet_computed = null;

  /** @apilevel internal */
  protected Set<AllocationSite> pointsToSet_value;

  /**
   * @attribute syn
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:32
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Solver", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:32")
  public Set<AllocationSite> pointsToSet() {
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
  private Set<AllocationSite> pointsToSet_compute() {
          if (type().isPrimitive()) {
              return Collections.emptySet();
          }
  
          return generatedEdges().stream()
                                 .filter(edge -> edge.from == declaration())
                                 .map(edge -> edge.to)
                                 .filter(allocsite -> allocsite.atype().subtype(typ()))
                                 .collect(Collectors.toSet());
      }
  /**
   * @attribute syn
   * @aspect codeprober
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Codeprober.jrag:8
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="codeprober", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Codeprober.jrag:8")
  public Object visualisePointsToHover() {
    {
            String YELLOW = "#FF08";
    
            // Hover info
            if (!pointsToSet().isEmpty()) {
                String line = "INFO@" +
                    getStart() + ";" +
                    getEnd() + ";" +
                    pointsToString();
                System.out.println(line);
            } 
            return null;
        }
  }
  /**
   * @attribute syn
   * @aspect codeprober
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Codeprober.jrag:33
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="codeprober", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Codeprober.jrag:33")
  public Object visualiseConstraints() {
    {
            visualiseInitialConstraits();
    
            for (PointsToEdge p : generatedEdges()) {
                System.out.println(p);
                p.drawArrow();
            }
            return null;
        }
  }
  /**
   * @attribute syn
   * @aspect codeprober
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Codeprober.jrag:42
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="codeprober", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Codeprober.jrag:42")
  public Object visualiseInitialConstraits() {
    {
            for (PointsToEdge p : activePointsToEdges()) {
                System.out.println(p);
                p.drawArrow();
            }
    
            for (InclusionEdge p : activeInclusionEdges()) {
                System.out.println(p);
                p.drawArrow();
            }
            return null;
        }
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
   * @aspect NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:464
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:464")
  public VariableScope outerScope() {
    VariableScope outerScope_value = getParent().Define_outerScope(this, null);
    return outerScope_value;
  }
  /**
   * @attribute inh
   * @aspect DeclareBeforeUse
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DeclareBeforeUse.jrag:35
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="DeclareBeforeUse", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DeclareBeforeUse.jrag:35")
  public int blockIndex() {
    ASTState state = state();
    if (blockIndex_computed == ASTState.NON_CYCLE || blockIndex_computed == state().cycle()) {
      return blockIndex_value;
    }
    blockIndex_value = getParent().Define_blockIndex(this, null);
    if (state().inCircle()) {
      blockIndex_computed = state().cycle();
    
    } else {
      blockIndex_computed = ASTState.NON_CYCLE;
    
    }
    return blockIndex_value;
  }
  /** @apilevel internal */
  private void blockIndex_reset() {
    blockIndex_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle blockIndex_computed = null;

  /** @apilevel internal */
  protected int blockIndex_value;

  /**
   * @attribute inh
   * @aspect NestedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:616
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="NestedTypes", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:616")
  public BodyDecl enclosingBodyDecl() {
    BodyDecl enclosingBodyDecl_value = getParent().Define_enclosingBodyDecl(this, null);
    return enclosingBodyDecl_value;
  }
  /**
   * @attribute inh
   * @aspect PreciseRethrow
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:32
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:32")
  public boolean inhModifiedInScope(Variable var) {
    boolean inhModifiedInScope_Variable_value = getParent().Define_inhModifiedInScope(this, null, var);
    return inhModifiedInScope_Variable_value;
  }
  /**
   * @attribute inh
   * @aspect EnclosingLambda
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EnclosingLambda.jrag:36
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="EnclosingLambda", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EnclosingLambda.jrag:36")
  public LambdaExpr enclosingLambda() {
    ASTState state = state();
    if (enclosingLambda_computed == ASTState.NON_CYCLE || enclosingLambda_computed == state().cycle()) {
      return enclosingLambda_value;
    }
    enclosingLambda_value = getParent().Define_enclosingLambda(this, null);
    if (state().inCircle()) {
      enclosingLambda_computed = state().cycle();
    
    } else {
      enclosingLambda_computed = ASTState.NON_CYCLE;
    
    }
    return enclosingLambda_value;
  }
  /** @apilevel internal */
  private void enclosingLambda_reset() {
    enclosingLambda_computed = null;
    enclosingLambda_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle enclosingLambda_computed = null;

  /** @apilevel internal */
  protected LambdaExpr enclosingLambda_value;

  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:30
   * @apilevel internal
   */
  public SimpleSet<Variable> Define_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getInitOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:230
      {
          if (declaresVariable(name)) {
            return this;
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:40
    if (hasInit()
              && !getInit().type().assignConversionTo(type(), getInit())
              && !getInit().type().isUnknown()) {
      {
        java.util.Set<ASTNode> contributors = _map.get(_root);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) _root, contributors);
        }
        contributors.add(this);
      }
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:480
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:93
    if (hasInitializer() && getInitializer().hasDeclaration()) {
      for (InvocationTarget target : (Iterable<? extends InvocationTarget>) (enclosingMethods())) {
        java.util.Set<ASTNode> contributors = _map.get(target);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) target, contributors);
        }
        contributors.add(this);
      }
    }
    super.collect_contributors_InvocationTarget_inclusionEdges(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_InvocationTarget_pointsToEdges(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:106
    if (!hasInitializer()) {
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
  protected void collect_contributors_Program_getTargetDeclaration(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:97
    if (inTestBlock() && program().benchmarkTestsTargets().contains(getID())) {
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
    super.collect_contributors_Program_getTargetDeclaration(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_InvocationTarget_pointers(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:200
    for (InvocationTarget target : (Iterable<? extends InvocationTarget>) (enclosingMethods())) {
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_InvocationTarget_pointers(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_Program_visualiseAllPointsToHover(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/Codeprober.jrag:26
    {
      Program target = (Program) (program());
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_Program_visualiseAllPointsToHover(_root, _map);
  }
  /** @apilevel internal */
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    if (hasInit()
              && !getInit().type().assignConversionTo(type(), getInit())
              && !getInit().type().isUnknown()) {
      collection.add(errorf("cannot assign variable %s of type %s a value of type %s",
                name(), type().typeName(), getInit().type().typeName()));
    }
    for (Problem value : nameProblems()) {
      collection.add(value);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_InvocationTarget_inclusionEdges(Set<InclusionEdge> collection) {
    super.contributeTo_InvocationTarget_inclusionEdges(collection);
    if (hasInitializer() && getInitializer().hasDeclaration()) {
      collection.add(new InclusionEdge(getInitializer().getDeclaration(), getDeclaration()));
    }
  }
  /** @apilevel internal */
  protected void contributeTo_InvocationTarget_pointsToEdges(Set<PointsToEdge> collection) {
    super.contributeTo_InvocationTarget_pointsToEdges(collection);
    if (!hasInitializer()) {
      collection.add(new PointsToEdge(this, (NullType) program().typeNull()));
    }
  }
  /** @apilevel internal */
  protected void contributeTo_Program_getTargetDeclaration(Set<Pointer> collection) {
    super.contributeTo_Program_getTargetDeclaration(collection);
    if (inTestBlock() && program().benchmarkTestsTargets().contains(getID())) {
      collection.add(this);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_InvocationTarget_pointers(Set<Pointer> collection) {
    super.contributeTo_InvocationTarget_pointers(collection);
    collection.add(this);
  }
  /** @apilevel internal */
  protected void contributeTo_Program_visualiseAllPointsToHover(Set<Object> collection) {
    super.contributeTo_Program_visualiseAllPointsToHover(collection);
    collection.add(visualisePointsToHover());
  }

}
