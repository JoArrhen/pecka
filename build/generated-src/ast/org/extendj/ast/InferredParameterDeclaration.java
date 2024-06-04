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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/grammar/Lambda.ast:6
 * @astdecl InferredParameterDeclaration : ASTNode ::= <ID:String>;
 * @production InferredParameterDeclaration : {@link ASTNode} ::= <span class="component">&lt;ID:{@link String}&gt;</span>;

 */
public class InferredParameterDeclaration extends ASTNode<ASTNode> implements Cloneable, Variable, SimpleSet<Variable> {
  /**
   * @aspect PrettyPrintUtil8
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PrettyPrintUtil.jadd:71
   */
  @Override public String toString() {
    return getID();
  }
  /**
   * @aspect Java8PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PrettyPrint.jadd:95
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(name());
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/DataStructures.jrag:34
   */
  @Override
  public int size() {
    return 1;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/DataStructures.jrag:39
   */
  @Override
  public boolean isEmpty() {
    return false;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/DataStructures.jrag:44
   */
  public SimpleSet<Variable> add(Variable o) {
    return new SimpleSetImpl<Variable>(this, o);
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/DataStructures.jrag:48
   */
  @Override
  public boolean contains(Object o) {
    return this == o;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/DataStructures.jrag:53
   */
  @Override
  public boolean isSingleton() {
    return true;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/DataStructures.jrag:58
   */
  @Override
  public boolean isSingleton(Variable o) {
    return contains(o);
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/DataStructures.jrag:63
   */
  @Override
  public Variable singletonValue() {
    return this;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/DataStructures.jrag:68
   */
  public Iterator<Variable> iterator() {
    return new SingleItemIterator(this);
  }
  /**
   * @declaredat ASTNode:1
   */
  public InferredParameterDeclaration() {
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
  }
  /**
   * @declaredat ASTNode:12
   */
  @ASTNodeAnnotation.Constructor(
    name = {"ID"},
    type = {"String"},
    kind = {"Token"}
  )
  public InferredParameterDeclaration(String p0) {
    setID(p0);
  }
  /**
   * @declaredat ASTNode:20
   */
  public InferredParameterDeclaration(beaver.Symbol p0) {
    setID(p0);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:24
   */
  protected int numChildren() {
    return 0;
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
    isEffectivelyFinal_reset();
    inferredType_reset();
    lookupVariable_String_reset();
    enclosingLambda_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:42
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:47
   */
  public InferredParameterDeclaration clone() throws CloneNotSupportedException {
    InferredParameterDeclaration node = (InferredParameterDeclaration) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:52
   */
  public InferredParameterDeclaration copy() {
    try {
      InferredParameterDeclaration node = (InferredParameterDeclaration) clone();
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
   * @declaredat ASTNode:71
   */
  @Deprecated
  public InferredParameterDeclaration fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:81
   */
  public InferredParameterDeclaration treeCopyNoTransform() {
    InferredParameterDeclaration tree = (InferredParameterDeclaration) copy();
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
   * @declaredat ASTNode:101
   */
  public InferredParameterDeclaration treeCopy() {
    InferredParameterDeclaration tree = (InferredParameterDeclaration) copy();
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
  public InferredParameterDeclaration setID(String value) {
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
  public InferredParameterDeclaration setID(beaver.Symbol symbol) {
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:30
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:30")
  public boolean isParameter() {
    boolean isParameter_value = true;
    return isParameter_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:31
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:31")
  public boolean isClassVariable() {
    boolean isClassVariable_value = false;
    return isClassVariable_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:32
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:32")
  public boolean isInstanceVariable() {
    boolean isInstanceVariable_value = false;
    return isInstanceVariable_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:33
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:33")
  public boolean isConstructorParameter() {
    boolean isConstructorParameter_value = false;
    return isConstructorParameter_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:34
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:34")
  public boolean isExceptionHandlerParameter() {
    boolean isExceptionHandlerParameter_value = false;
    return isExceptionHandlerParameter_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:35
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:35")
  public boolean isMethodParameter() {
    boolean isMethodParameter_value = false;
    return isMethodParameter_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:36
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:36")
  public boolean isLocalVariable() {
    boolean isLocalVariable_value = false;
    return isLocalVariable_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:37
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:37")
  public boolean isField() {
    boolean isField_value = false;
    return isField_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:38
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:38")
  public boolean isFinal() {
    boolean isFinal_value = false;
    return isFinal_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:39
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:39")
  public boolean isVolatile() {
    boolean isVolatile_value = false;
    return isVolatile_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:40
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:40")
  public boolean isBlank() {
    boolean isBlank_value = true;
    return isBlank_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:41
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:41")
  public boolean isStatic() {
    boolean isStatic_value = false;
    return isStatic_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:42
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:42")
  public boolean isSynthetic() {
    boolean isSynthetic_value = false;
    return isSynthetic_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:44
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:44")
  public Modifiers getModifiers() {
    Modifiers getModifiers_value = null;
    return getModifiers_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:46
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:46")
  public boolean hasInit() {
    boolean hasInit_value = false;
    return hasInit_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:48
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:48")
  public boolean isConstant() {
    boolean isConstant_value = false;
    return isConstant_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:50
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:50")
  public boolean isPublic() {
    boolean isPublic_value = false;
    return isPublic_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:52
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:52")
  public boolean accessibleFrom(TypeDecl type) {
    boolean accessibleFrom_TypeDecl_value = false;
    return accessibleFrom_TypeDecl_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:54
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:54")
  public Expr getInit() {
    {
        throw new UnsupportedOperationException();
      }
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:58
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:58")
  public Constant constant() {
    {
        throw new UnsupportedOperationException();
      }
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:62
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:62")
  public Collection<TypeDecl> throwTypes() {
    Collection<TypeDecl> throwTypes_value = null;
    return throwTypes_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:75
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:75")
  public TypeDecl hostType() {
    TypeDecl hostType_value = enclosingLambdaType();
    return hostType_value;
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:81
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:81")
  public TypeDecl type() {
    TypeDecl type_value = inferredType();
    return type_value;
  }
  /**
   * @attribute syn
   * @aspect Java8NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:41
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Java8NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:41")
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:136
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="EffectivelyFinal", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:136")
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
   * @aspect Names
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/QualifiedNames.jrag:29
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Names", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/QualifiedNames.jrag:29")
  public String name() {
    String name_value = getID();
    return name_value;
  }
  /**
   * @attribute syn
   * @aspect FilterUtils
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:28
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FilterUtils", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:21")
  public TypeDecl type2() {
    TypeDecl type2_value = type();
    return type2_value;
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
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:44
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:44")
  public ASTNode getNode() {
    ASTNode getNode_value = null;
    return getNode_value;
  }
  /**
   * @attribute syn
   * @aspect Utilities
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:285
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Utilities", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:285")
  public DeclarationSite getDeclaration() {
    DeclarationSite getDeclaration_value = null;
    return getDeclaration_value;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:77
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Variables", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/VariableDeclaration.jrag:77")
  public TypeDecl enclosingLambdaType() {
    TypeDecl enclosingLambdaType_value = getParent().Define_enclosingLambdaType(this, null);
    return enclosingLambdaType_value;
  }
  /**
   * @attribute inh
   * @aspect TypeCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:31
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:31")
  public TypeDecl unknownType() {
    TypeDecl unknownType_value = getParent().Define_unknownType(this, null);
    return unknownType_value;
  }
  /**
   * @attribute inh
   * @aspect LambdaParametersInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:506
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="LambdaParametersInference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:506")
  public TypeDecl inferredType() {
    ASTState state = state();
    if (inferredType_computed == ASTState.NON_CYCLE || inferredType_computed == state().cycle()) {
      return inferredType_value;
    }
    inferredType_value = getParent().Define_inferredType(this, null);
    if (state().inCircle()) {
      inferredType_computed = state().cycle();
    
    } else {
      inferredType_computed = ASTState.NON_CYCLE;
    
    }
    return inferredType_value;
  }
  /** @apilevel internal */
  private void inferredType_reset() {
    inferredType_computed = null;
    inferredType_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle inferredType_computed = null;

  /** @apilevel internal */
  protected TypeDecl inferredType_value;

  /**
   * @attribute inh
   * @aspect VariableScope
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:31
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="VariableScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:31")
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
   * @aspect Java8NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:30
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Java8NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:30")
  public BodyDecl enclosingBodyDecl() {
    BodyDecl enclosingBodyDecl_value = getParent().Define_enclosingBodyDecl(this, null);
    return enclosingBodyDecl_value;
  }
  /**
   * @attribute inh
   * @aspect Java8NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:31
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Java8NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:31")
  public VariableScope outerScope() {
    VariableScope outerScope_value = getParent().Define_outerScope(this, null);
    return outerScope_value;
  }
  /**
   * @attribute inh
   * @aspect PreciseRethrow
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:30
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EffectivelyFinal.jrag:30")
  public boolean inhModifiedInScope(Variable var) {
    boolean inhModifiedInScope_Variable_value = getParent().Define_inhModifiedInScope(this, null, var);
    return inhModifiedInScope_Variable_value;
  }
  /**
   * @attribute inh
   * @aspect EnclosingLambda
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EnclosingLambda.jrag:35
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="EnclosingLambda", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EnclosingLambda.jrag:35")
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:39
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
