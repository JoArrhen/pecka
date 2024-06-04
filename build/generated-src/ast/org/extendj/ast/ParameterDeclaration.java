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
 * A parameter declaration as used in either method parameter lists
 * or as a catch clause parameter.
 * @ast node
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:207
 * @astdecl ParameterDeclaration : ASTNode ::= Modifiers TypeAccess:Access <ID:String>;
 * @production ParameterDeclaration : {@link ASTNode} ::= <span class="component">{@link Modifiers}</span> <span class="component">TypeAccess:{@link Access}</span> <span class="component">&lt;ID:{@link String}&gt;</span>;

 */
public class ParameterDeclaration extends ASTNode<ASTNode> implements Cloneable, Variable, SimpleSet<Variable>, AllocationSite, DeclarationSite, Pointer {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrint.jadd:530
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(getModifiers());
    out.print(getTypeAccess());
    out.print(" ");
    out.print(getID());
  }
  /**
   * @aspect NodeConstructors
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NodeConstructors.jrag:32
   */
  public ParameterDeclaration(Access type, String name) {
    this(new Modifiers(new List()), type, name);
  }
  /**
   * @aspect NodeConstructors
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NodeConstructors.jrag:36
   */
  public ParameterDeclaration(TypeDecl type, String name) {
    this(new Modifiers(new List()), type.createQualifiedAccess(), name);
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:335
   */
  @Override
  public int size() {
    return 1;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:340
   */
  @Override
  public boolean isEmpty() {
    return false;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:345
   */
  @Override
  public SimpleSet<Variable> add(Variable o) {
    return new SimpleSetImpl<Variable>(this, o);
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:350
   */
  @Override
  public boolean contains(Object o) {
    return this == o;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:355
   */
  @Override
  public boolean isSingleton() {
    return true;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:360
   */
  @Override
  public boolean isSingleton(Variable o) {
    return contains(o);
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:365
   */
  @Override
  public Variable singletonValue() {
    return this;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DataStructures.jrag:370
   */
  @Override
  public Iterator<Variable> iterator() {
    return new SingleItemIterator(this);
  }
  /**
   * @aspect PrettyPrintUtil
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:141
   */
  @Override public String toString() {
    return String.format("%s %s",
        getTypeAccessNoTransform().toString(),
        getID());
  }
  /**
   * Builds a copy of this ParameterDeclaration node where all occurrences
   * of type variables in the original type parameter list have been replaced
   * by the substitution type parameters.
   * 
   * <p>This should only be used to generate candidate methods for Diamond type
   * inference.
   * 
   * @return the substituted ParameterDeclaration node
   * @aspect Diamond
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Diamond.jrag:224
   */
  public ParameterDeclaration substituted(
      Map<TypeVariable, String>  substitution) {
    return new ParameterDeclaration(
        (Modifiers) getModifiers().treeCopyNoTransform(),
        getTypeAccess().substituted(substitution),
        getID());
  }
  /**
   * @declaredat ASTNode:1
   */
  public ParameterDeclaration() {
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
    children = new ASTNode[2];
  }
  /**
   * @declaredat ASTNode:13
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Modifiers", "TypeAccess", "ID"},
    type = {"Modifiers", "Access", "String"},
    kind = {"Child", "Child", "Token"}
  )
  public ParameterDeclaration(Modifiers p0, Access p1, String p2) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
  }
  /**
   * @declaredat ASTNode:23
   */
  public ParameterDeclaration(Modifiers p0, Access p1, beaver.Symbol p2) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:29
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:35
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:39
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    type_reset();
    throwTypes_reset();
    inferredReferenceAccess_TypeAccess_reset();
    unfilteredGeneratedEdges_reset();
    generatedEdges_reset();
    pointsToSet_reset();
    enclosingLambda_reset();
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
  public ParameterDeclaration clone() throws CloneNotSupportedException {
    ParameterDeclaration node = (ParameterDeclaration) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:60
   */
  public ParameterDeclaration copy() {
    try {
      ParameterDeclaration node = (ParameterDeclaration) clone();
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
  public ParameterDeclaration fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:89
   */
  public ParameterDeclaration treeCopyNoTransform() {
    ParameterDeclaration tree = (ParameterDeclaration) copy();
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
   * @declaredat ASTNode:109
   */
  public ParameterDeclaration treeCopy() {
    ParameterDeclaration tree = (ParameterDeclaration) copy();
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
  public ParameterDeclaration setModifiers(Modifiers node) {
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
  public ParameterDeclaration setTypeAccess(Access node) {
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
  public ParameterDeclaration setID(String value) {
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
  public ParameterDeclaration setID(beaver.Symbol symbol) {
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
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:73
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:73")
  public boolean isParameter() {
    boolean isParameter_value = true;
    return isParameter_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:75
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:75")
  public boolean isConstant() {
    boolean isConstant_value = false;
    return isConstant_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:76
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:76")
  public boolean isPublic() {
    boolean isPublic_value = false;
    return isPublic_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:77
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:77")
  public boolean accessibleFrom(TypeDecl type) {
    boolean accessibleFrom_TypeDecl_value = false;
    return accessibleFrom_TypeDecl_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:80
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:80")
  public boolean isClassVariable() {
    boolean isClassVariable_value = false;
    return isClassVariable_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:81
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:81")
  public boolean isInstanceVariable() {
    boolean isInstanceVariable_value = false;
    return isInstanceVariable_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:85
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:85")
  public boolean isLocalVariable() {
    boolean isLocalVariable_value = false;
    return isLocalVariable_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:86
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:86")
  public boolean isField() {
    boolean isField_value = false;
    return isField_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:104
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:104")
  public boolean isFinal() {
    boolean isFinal_value = getModifiers().isFinal();
    return isFinal_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:105
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:105")
  public boolean isVolatile() {
    boolean isVolatile_value = getModifiers().isVolatile();
    return isVolatile_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:106
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:106")
  public boolean isBlank() {
    boolean isBlank_value = true;
    return isBlank_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:107
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:107")
  public boolean isStatic() {
    boolean isStatic_value = false;
    return isStatic_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:109
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:109")
  public String name() {
    String name_value = getID();
    return name_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:111
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:111")
  public boolean hasInit() {
    boolean hasInit_value = false;
    return hasInit_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:112
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:112")
  public Expr getInit() {
    {
        throw new UnsupportedOperationException();
      }
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:115
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:115")
  public Constant constant() {
    {
        throw new UnsupportedOperationException();
      }
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:256
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:256")
  public boolean isSynthetic() {
    boolean isSynthetic_value = getModifiers().isSynthetic();
    return isSynthetic_value;
  }
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:507
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:507")
  public Collection<Problem> nameProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        SimpleSet<Variable> decls = outerScope().lookupVariable(name());
        for (Variable var : decls) {
          if (var instanceof VariableDeclarator) {
            VariableDeclarator decl = (VariableDeclarator) var;
            if (decl.enclosingBodyDecl() == enclosingBodyDecl()) {
              problems.add(errorf("duplicate declaration of parameter %s", name()));
            }
          } else if (var instanceof ParameterDeclaration) {
            ParameterDeclaration decl = (ParameterDeclaration) var;
            if (decl.enclosingBodyDecl() == enclosingBodyDecl()) {
              problems.add(errorf("duplicate declaration of parameter %s", name()));
            }
          } else if (var instanceof InferredParameterDeclaration) {
            InferredParameterDeclaration decl = (InferredParameterDeclaration) var;
            if (decl.enclosingBodyDecl() == enclosingBodyDecl()) {
              problems.add(errorf("duplicate declaration of parameter %s", name()));
            }
          } else if (var instanceof CatchParameterDeclaration) {
            CatchParameterDeclaration decl = (CatchParameterDeclaration) var;
            if (decl.enclosingBodyDecl() == enclosingBodyDecl()) {
              problems.add(errorf("duplicate declaration of parameter %s", name()));
            }
          }
        }
    
        // 8.4.1
        if (!lookupVariable(name()).contains(this)) {
          problems.add(errorf("duplicate declaration of parameter %s", name()));
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect PrettyPrintUtil
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:340
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PrettyPrintUtil", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:340")
  public boolean hasModifiers() {
    boolean hasModifiers_value = getModifiers().getNumModifier() > 0;
    return hasModifiers_value;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:271
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:271")
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
   * @aspect VariableArityParameters
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:59
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableArityParameters", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:59")
  public boolean isVariableArity() {
    boolean isVariableArity_value = false;
    return isVariableArity_value;
  }
  /**
   * Creates a copy of this parameter declaration where parameterized types have been erased.
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1630
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1630")
  public ParameterDeclaration erasedCopy() {
    ParameterDeclaration erasedCopy_value = new ParameterDeclaration(
              getModifiers().treeCopyNoTransform(),
              getTypeAccess().erasedCopy(),
              getID());
    return erasedCopy_value;
  }
  /** @apilevel internal */
  private void throwTypes_reset() {
    throwTypes_computed = null;
    throwTypes_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle throwTypes_computed = null;

  /** @apilevel internal */
  protected Collection<TypeDecl> throwTypes_value;

  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:47
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:47")
  public Collection<TypeDecl> throwTypes() {
    ASTState state = state();
    if (throwTypes_computed == ASTState.NON_CYCLE || throwTypes_computed == state().cycle()) {
      return throwTypes_value;
    }
    throwTypes_value = throwTypes_compute();
    if (state().inCircle()) {
      throwTypes_computed = state().cycle();
    
    } else {
      throwTypes_computed = ASTState.NON_CYCLE;
    
    }
    return throwTypes_value;
  }
  /** @apilevel internal */
  private Collection<TypeDecl> throwTypes_compute() {
      if (isCatchParam() && isEffectivelyFinal()) {
        // The catch parameter must be final to refine the throw type.
        return catchClause().caughtExceptions();
      } else {
        return Collections.singleton(type());
      }
    }
  /**
   * Note: this attribute deviates from what the JLS says about "effectively final",
   * simply because the attribute name would be too confusing if it did not return true
   * when the variable was explicitly declared final. The JLS considers declared final
   * and effectively final to be mutually exclusive, we don't.
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:65
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:65")
  public boolean isEffectivelyFinal() {
    boolean isEffectivelyFinal_value = isFinal() || !inhModifiedInScope(this);
    return isEffectivelyFinal_value;
  }
  /** @apilevel internal */
  private void inferredReferenceAccess_TypeAccess_reset() {
    inferredReferenceAccess_TypeAccess_values = null;
    inferredReferenceAccess_TypeAccess_proxy = null;
  }
  /** @apilevel internal */
  protected ASTNode inferredReferenceAccess_TypeAccess_proxy;
  /** @apilevel internal */
  protected java.util.Map inferredReferenceAccess_TypeAccess_values;

  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:116
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:116")
  public ParTypeAccess inferredReferenceAccess(TypeAccess typeAccess) {
    Object _parameters = typeAccess;
    if (inferredReferenceAccess_TypeAccess_values == null) inferredReferenceAccess_TypeAccess_values = new java.util.HashMap(4);
    ASTState state = state();
    if (inferredReferenceAccess_TypeAccess_values.containsKey(_parameters)) {
      return (ParTypeAccess) inferredReferenceAccess_TypeAccess_values.get(_parameters);
    }
    state().enterLazyAttribute();
    ParTypeAccess inferredReferenceAccess_TypeAccess_value = inferredReferenceAccess_compute(typeAccess);
    if (inferredReferenceAccess_TypeAccess_proxy == null) {
      inferredReferenceAccess_TypeAccess_proxy = new ASTNode();
      inferredReferenceAccess_TypeAccess_proxy.setParent(this);
    }
    if (inferredReferenceAccess_TypeAccess_value != null) {
      inferredReferenceAccess_TypeAccess_value.setParent(inferredReferenceAccess_TypeAccess_proxy);
      if (inferredReferenceAccess_TypeAccess_value.mayHaveRewrite()) {
        inferredReferenceAccess_TypeAccess_value = (ParTypeAccess) inferredReferenceAccess_TypeAccess_value.rewrittenNode();
        inferredReferenceAccess_TypeAccess_value.setParent(inferredReferenceAccess_TypeAccess_proxy);
      }
    }
    inferredReferenceAccess_TypeAccess_values.put(_parameters, inferredReferenceAccess_TypeAccess_value);
    state().leaveLazyAttribute();
    return inferredReferenceAccess_TypeAccess_value;
  }
  /** @apilevel internal */
  private ParTypeAccess inferredReferenceAccess_compute(TypeAccess typeAccess) {
      if (!(getTypeAccess() instanceof ParTypeAccess)) {
        return new ParTypeAccess((TypeAccess) typeAccess.treeCopy(), new List<Access>());
      }
      ParTypeAccess parTypeAccess = (ParTypeAccess) getTypeAccess();
      return new ParTypeAccess((TypeAccess) typeAccess.treeCopy(),
          (List<Access>) parTypeAccess.getTypeArgumentList().treeCopy());
    }
  /**
   * @attribute syn
   * @aspect FilterUtils
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:24
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:68
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:44")
  public ASTNode getNode() {
    ASTNode getNode_value = this;
    return getNode_value;
  }
  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:73
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:73")
  public String typeName() {
    String typeName_value = type().getID();
    return typeName_value;
  }
  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:190
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:189")
  public String fileName() {
    String fileName_value = compilationUnit().pathName();
    return fileName_value;
  }
  /**
   * @attribute syn
   * @aspect Utilities
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:289
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:372
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Utilities", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:372")
  public Set<InvocationTarget> enclosingMethods() {
    { BodyDecl decl = enclosingBodyDecl(); return new HashSet<>(Arrays.asList((InvocationTarget) decl)); }
  }
  /**
   * @attribute syn
   * @aspect runtimebenchmark
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/RuntimeBenchmark.jrag:8
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="runtimebenchmark", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/RuntimeBenchmark.jrag:8")
  public boolean hasSubtypes() {
    {
            Access access = getTypeAccess();
            TypeAccess typeaccess;
            if (access instanceof TypeAccess) {
                return ((TypeAccess) access).decl().subtypes().size() > 0;
            }
            return false;
    
        }
  }
  /**
   * @attribute syn
   * @aspect pointerbench
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:108
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="pointerbench", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:108")
  public boolean inTestBlock() {
    boolean inTestBlock_value = enclosingBodyDecl().isTestBlock();
    return inTestBlock_value;
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
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:281
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:281")
  public boolean isProtected() {
    boolean isProtected_value = getModifiers().isProtected();
    return isProtected_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:283
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:283")
  public boolean isPrivate() {
    boolean isPrivate_value = getModifiers().isPrivate();
    return isPrivate_value;
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
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:82
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:82")
  public boolean isMethodParameter() {
    boolean isMethodParameter_value = getParent().Define_isMethodParameter(this, null);
    return isMethodParameter_value;
  }
  /**
   * @attribute inh
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:83
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:83")
  public boolean isConstructorParameter() {
    boolean isConstructorParameter_value = getParent().Define_isConstructorParameter(this, null);
    return isConstructorParameter_value;
  }
  /**
   * @attribute inh
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:84
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/VariableDeclaration.jrag:84")
  public boolean isExceptionHandlerParameter() {
    boolean isExceptionHandlerParameter_value = getParent().Define_isExceptionHandlerParameter(this, null);
    return isExceptionHandlerParameter_value;
  }
  /**
   * @attribute inh
   * @aspect VariableScope
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:46
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="VariableScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:46")
  public SimpleSet<Variable> lookupVariable(String name) {
    SimpleSet<Variable> lookupVariable_String_value = getParent().Define_lookupVariable(this, null, name);
    return lookupVariable_String_value;
  }
  /**
   * @attribute inh
   * @aspect NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:462
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:462")
  public VariableScope outerScope() {
    VariableScope outerScope_value = getParent().Define_outerScope(this, null);
    return outerScope_value;
  }
  /**
   * @attribute inh
   * @aspect NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:531
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:531")
  public BodyDecl enclosingBodyDecl() {
    BodyDecl enclosingBodyDecl_value = getParent().Define_enclosingBodyDecl(this, null);
    return enclosingBodyDecl_value;
  }
  /**
   * @attribute inh
   * @aspect NestedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:702
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="NestedTypes", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:702")
  public TypeDecl hostType() {
    TypeDecl hostType_value = getParent().Define_hostType(this, null);
    return hostType_value;
  }
  /**
   * @return true if the variable var is modified in the local scope
   * @attribute inh
   * @aspect PreciseRethrow
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:70
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:70")
  public boolean inhModifiedInScope(Variable var) {
    boolean inhModifiedInScope_Variable_value = getParent().Define_inhModifiedInScope(this, null, var);
    return inhModifiedInScope_Variable_value;
  }
  /**
   * @return true if this is the parameter declaration of a catch clause
   * @attribute inh
   * @aspect PreciseRethrow
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:206
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:206")
  public boolean isCatchParam() {
    boolean isCatchParam_value = getParent().Define_isCatchParam(this, null);
    return isCatchParam_value;
  }
  /**
   * @attribute inh
   * @aspect PreciseRethrow
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:212
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:212")
  public CatchClause catchClause() {
    CatchClause catchClause_value = getParent().Define_catchClause(this, null);
    return catchClause_value;
  }
  /**
   * @attribute inh
   * @aspect EnclosingLambda
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EnclosingLambda.jrag:34
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="EnclosingLambda", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EnclosingLambda.jrag:34")
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
   * @attribute inh
   * @aspect NestedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:684
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="NestedTypes", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:684")
  public String hostPackage() {
    String hostPackage_value = getParent().Define_hostPackage(this, null);
    return hostPackage_value;
  }
  /**
   * @attribute inh
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1405
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1405")
  public FieldDecl fieldDecl() {
    FieldDecl fieldDecl_value = getParent().Define_fieldDecl(this, null);
    return fieldDecl_value;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:437
   * @apilevel internal
   */
  public boolean Define_mayBeFinal(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:342
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:131
   * @apilevel internal
   */
  public boolean Define_mayUseAnnotationTarget(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:150
      return name.equals("PARAMETER");
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (getTypeAccessNoTransform() != null && _callerNode == getTypeAccess()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:98
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:505
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
  protected void collect_contributors_Program_getTargetDeclaration(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:101
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
    for (Problem value : nameProblems()) {
      collection.add(value);
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
