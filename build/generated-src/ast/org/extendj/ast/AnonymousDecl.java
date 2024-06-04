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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:184
 * @astdecl AnonymousDecl : ClassDecl ::= Modifiers <ID:String> BodyDecl* [ImplicitConstructor:ConstructorDecl] [SuperClass:Access] Implements:Access*;
 * @production AnonymousDecl : {@link ClassDecl} ::= <span class="component">{@link Modifiers}</span> <span class="component">&lt;ID:{@link String}&gt;</span> <span class="component">[SuperClass:{@link Access}]</span> <span class="component">Implements:{@link Access}*</span> <span class="component">{@link BodyDecl}*</span>;

 */
public class AnonymousDecl extends ClassDecl implements Cloneable {
  /**
   * @declaredat ASTNode:1
   */
  public AnonymousDecl() {
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
    setChild(new List(), 1);
    setChild(new Opt(), 2);
    setChild(new Opt(), 3);
    setChild(new List(), 4);
  }
  /**
   * @declaredat ASTNode:17
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Modifiers", "ID", "BodyDecl"},
    type = {"Modifiers", "String", "List<BodyDecl>"},
    kind = {"Child", "Token", "List"}
  )
  public AnonymousDecl(Modifiers p0, String p1, List<BodyDecl> p2) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
  }
  /**
   * @declaredat ASTNode:27
   */
  public AnonymousDecl(Modifiers p0, beaver.Symbol p1, List<BodyDecl> p2) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:33
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:39
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:43
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    getImplicitConstructorOpt_reset();
    isCircular_reset();
    getSuperClassOpt_reset();
    getImplementsList_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:51
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:56
   */
  public AnonymousDecl clone() throws CloneNotSupportedException {
    AnonymousDecl node = (AnonymousDecl) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:61
   */
  public AnonymousDecl copy() {
    try {
      AnonymousDecl node = (AnonymousDecl) clone();
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
   * @declaredat ASTNode:80
   */
  @Deprecated
  public AnonymousDecl fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:90
   */
  public AnonymousDecl treeCopyNoTransform() {
    AnonymousDecl tree = (AnonymousDecl) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 2:
        case 3:
          tree.children[i] = new Opt();
          continue;
        case 4:
          tree.children[i] = new List();
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
   * @declaredat ASTNode:119
   */
  public AnonymousDecl treeCopy() {
    AnonymousDecl tree = (AnonymousDecl) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 2:
        case 3:
          tree.children[i] = new Opt();
          continue;
        case 4:
          tree.children[i] = new List();
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
  public AnonymousDecl setModifiers(Modifiers node) {
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
  public AnonymousDecl setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public AnonymousDecl setID(beaver.Symbol symbol) {
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
   * Replaces the BodyDecl list.
   * @param list The new list node to be used as the BodyDecl list.
   * @apilevel high-level
   */
  public AnonymousDecl setBodyDeclList(List<BodyDecl> list) {
    setChild(list, 1);
    return this;
  }
  /**
   * Retrieves the number of children in the BodyDecl list.
   * @return Number of children in the BodyDecl list.
   * @apilevel high-level
   */
  public int getNumBodyDecl() {
    return getBodyDeclList().getNumChild();
  }
  /**
   * Retrieves the number of children in the BodyDecl list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the BodyDecl list.
   * @apilevel low-level
   */
  public int getNumBodyDeclNoTransform() {
    return getBodyDeclListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the BodyDecl list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the BodyDecl list.
   * @apilevel high-level
   */
  public BodyDecl getBodyDecl(int i) {
    return (BodyDecl) getBodyDeclList().getChild(i);
  }
  /**
   * Check whether the BodyDecl list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasBodyDecl() {
    return getBodyDeclList().getNumChild() != 0;
  }
  /**
   * Append an element to the BodyDecl list.
   * @param node The element to append to the BodyDecl list.
   * @apilevel high-level
   */
  public AnonymousDecl addBodyDecl(BodyDecl node) {
    List<BodyDecl> list = (parent == null) ? getBodyDeclListNoTransform() : getBodyDeclList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public AnonymousDecl addBodyDeclNoTransform(BodyDecl node) {
    List<BodyDecl> list = getBodyDeclListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the BodyDecl list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public AnonymousDecl setBodyDecl(BodyDecl node, int i) {
    List<BodyDecl> list = getBodyDeclList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the BodyDecl list.
   * @return The node representing the BodyDecl list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="BodyDecl")
  public List<BodyDecl> getBodyDeclList() {
    List<BodyDecl> list = (List<BodyDecl>) getChild(1);
    return list;
  }
  /**
   * Retrieves the BodyDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the BodyDecl list.
   * @apilevel low-level
   */
  public List<BodyDecl> getBodyDeclListNoTransform() {
    return (List<BodyDecl>) getChildNoTransform(1);
  }
  /**
   * @return the element at index {@code i} in the BodyDecl list without
   * triggering rewrites.
   */
  public BodyDecl getBodyDeclNoTransform(int i) {
    return (BodyDecl) getBodyDeclListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the BodyDecl list.
   * @return The node representing the BodyDecl list.
   * @apilevel high-level
   */
  public List<BodyDecl> getBodyDecls() {
    return getBodyDeclList();
  }
  /**
   * Retrieves the BodyDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the BodyDecl list.
   * @apilevel low-level
   */
  public List<BodyDecl> getBodyDeclsNoTransform() {
    return getBodyDeclListNoTransform();
  }
  /**
   * Replaces the (optional) ImplicitConstructor child.
   * @param node The new node to be used as the ImplicitConstructor child.
   * @apilevel high-level
   */
  public AnonymousDecl setImplicitConstructor(ConstructorDecl node) {
    getImplicitConstructorOpt().setChild(node, 0);
    return this;
  }
  /**
   * Check whether the optional ImplicitConstructor child exists.
   * @return {@code true} if the optional ImplicitConstructor child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasImplicitConstructor() {
    return getImplicitConstructorOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) ImplicitConstructor child.
   * @return The ImplicitConstructor child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public ConstructorDecl getImplicitConstructor() {
    return (ConstructorDecl) getImplicitConstructorOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for child ImplicitConstructor. This is the <code>Opt</code> node containing the child ImplicitConstructor, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child ImplicitConstructor.
   * @apilevel low-level
   */
  public Opt<ConstructorDecl> getImplicitConstructorOptNoTransform() {
    return (Opt<ConstructorDecl>) getChildNoTransform(2);
  }
  /**
   * Retrieves the child position of the optional child ImplicitConstructor.
   * @return The the child position of the optional child ImplicitConstructor.
   * @apilevel low-level
   */
  protected int getImplicitConstructorOptChildPosition() {
    return 2;
  }
  /**
   * This method should not be called. This method throws an exception due to
   * the corresponding child being an NTA shadowing a non-NTA child.
   * @param node
   * @apilevel internal
   */
  public AnonymousDecl setSuperClassOpt(Opt<Access> node) {
    throw new Error("Can not replace NTA child SuperClassOpt in AnonymousDecl!");
  }
  /**
   * Replaces the (optional) SuperClass child.
   * @param node The new node to be used as the SuperClass child.
   * @apilevel high-level
   */
  public AnonymousDecl setSuperClass(Access node) {
    getSuperClassOpt().setChild(node, 0);
    return this;
  }
  /**
   * Check whether the optional SuperClass child exists.
   * @return {@code true} if the optional SuperClass child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasSuperClass() {
    return getSuperClassOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) SuperClass child.
   * @return The SuperClass child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public Access getSuperClass() {
    return (Access) getSuperClassOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for child SuperClass. This is the <code>Opt</code> node containing the child SuperClass, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child SuperClass.
   * @apilevel low-level
   */
  public Opt<Access> getSuperClassOptNoTransform() {
    return (Opt<Access>) getChildNoTransform(3);
  }
  /**
   * Retrieves the child position of the optional child SuperClass.
   * @return The the child position of the optional child SuperClass.
   * @apilevel low-level
   */
  protected int getSuperClassOptChildPosition() {
    return 3;
  }
  /**
   * This method should not be called. This method throws an exception due to
   * the corresponding child being an NTA shadowing a non-NTA child.
   * @param node
   * @apilevel internal
   */
  public AnonymousDecl setImplementsList(List<Access> node) {
    throw new Error("Can not replace NTA child ImplementsList in AnonymousDecl!");
  }
  /**
   * Retrieves the number of children in the Implements list.
   * @return Number of children in the Implements list.
   * @apilevel high-level
   */
  public int getNumImplements() {
    return getImplementsList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Implements list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the Implements list.
   * @apilevel low-level
   */
  public int getNumImplementsNoTransform() {
    return getImplementsListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Implements list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Implements list.
   * @apilevel high-level
   */
  public Access getImplements(int i) {
    return (Access) getImplementsList().getChild(i);
  }
  /**
   * Check whether the Implements list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasImplements() {
    return getImplementsList().getNumChild() != 0;
  }
  /**
   * Append an element to the Implements list.
   * @param node The element to append to the Implements list.
   * @apilevel high-level
   */
  public AnonymousDecl addImplements(Access node) {
    List<Access> list = (parent == null) ? getImplementsListNoTransform() : getImplementsList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public AnonymousDecl addImplementsNoTransform(Access node) {
    List<Access> list = getImplementsListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the Implements list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public AnonymousDecl setImplements(Access node, int i) {
    List<Access> list = getImplementsList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the child position of the Implements list.
   * @return The the child position of the Implements list.
   * @apilevel low-level
   */
  protected int getImplementsListChildPosition() {
    return 4;
  }
  /**
   * Retrieves the Implements list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Implements list.
   * @apilevel low-level
   */
  public List<Access> getImplementsListNoTransform() {
    return (List<Access>) getChildNoTransform(4);
  }
  /**
   * @return the element at index {@code i} in the Implements list without
   * triggering rewrites.
   */
  public Access getImplementsNoTransform(int i) {
    return (Access) getImplementsListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Implements list.
   * @return The node representing the Implements list.
   * @apilevel high-level
   */
  public List<Access> getImplementss() {
    return getImplementsList();
  }
  /**
   * Retrieves the Implements list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Implements list.
   * @apilevel low-level
   */
  public List<Access> getImplementssNoTransform() {
    return getImplementsListNoTransform();
  }
  /**
   * @aspect VariableArityParameters
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:132
   */
   
  protected List constructorParameterList(ConstructorDecl decl) {
    List parameterList = new List();
    for (int i = 0; i < decl.getNumParameter(); i++) {
      ParameterDeclaration param = decl.getParameter(i);
      if (param instanceof VariableArityParameterDeclaration) {
        parameterList.add(
            new VariableArityParameterDeclaration(
              new Modifiers(new List()),
              ((ArrayDecl) param.type()).componentType().createBoundAccess(),
              param.name()
              ));
      } else {
        parameterList.add(
            new ParameterDeclaration(
              param.type().createBoundAccess(),
              param.name()
              ));
      }
    }

    return parameterList;
  }
  /** @apilevel internal */
  private void getImplicitConstructorOpt_reset() {
    getImplicitConstructorOpt_computed = false;
    
    getImplicitConstructorOpt_value = null;
  }
  /** @apilevel internal */
  protected boolean getImplicitConstructorOpt_computed = false;

  /** @apilevel internal */
  protected Opt<ConstructorDecl> getImplicitConstructorOpt_value;

  /**
   * @attribute syn nta
   * @aspect ImplicitConstructor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:274
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="ImplicitConstructor", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:274")
  public Opt<ConstructorDecl> getImplicitConstructorOpt() {
    ASTState state = state();
    if (getImplicitConstructorOpt_computed) {
      return (Opt<ConstructorDecl>) getChild(getImplicitConstructorOptChildPosition());
    }
    state().enterLazyAttribute();
    getImplicitConstructorOpt_value = getImplicitConstructorOpt_compute();
    setChild(getImplicitConstructorOpt_value, getImplicitConstructorOptChildPosition());
    getImplicitConstructorOpt_computed = true;
    state().leaveLazyAttribute();
    Opt<ConstructorDecl> node = (Opt<ConstructorDecl>) this.getChild(getImplicitConstructorOptChildPosition());
    return node;
  }
  /** @apilevel internal */
  private Opt<ConstructorDecl> getImplicitConstructorOpt_compute() {
      if (needsImplicitConstructor()) {
        ConstructorDecl decl = constructorDecl();
        Modifiers modifiers = new Modifiers();
  
        ConstructorDecl constructor = new ConstructorDecl(modifiers, getID(),
            constructorParameterList(decl), new List(), new Opt(), new Block());
  
        List argList = new List();
        for (int i = 0; i < constructor.getNumParameter(); i++) {
          argList.add(new VarAccess(constructor.getParameter(i).name()));
        }
  
        constructor.setParsedConstructorInvocation(
          new ExprStmt(
            new SuperConstructorAccess("super", argList)
          )
        );
  
        Collection<TypeDecl> set = new HashSet<TypeDecl>();
  
        // Add initializer and field declaration exceptions.
        for (int i = 0; i < getNumBodyDecl(); i++) {
          if (getBodyDecl(i) instanceof InstanceInitializer) {
            InstanceInitializer init = (InstanceInitializer) getBodyDecl(i);
            set.addAll(init.exceptions());
          } else if (getBodyDecl(i) instanceof FieldDecl) {
            FieldDecl f = (FieldDecl) getBodyDecl(i);
            for (FieldDeclarator field : f.getDeclaratorList()) {
              if (field.isInstanceVariable()) {
                set.addAll(field.exceptions());
              }
            }
          }
        }
  
        // Add superconstructor exceptions.
        for (int i = 0; i < decl.getNumException(); ++i) {
          set.add(decl.getException(i).type());
        }
  
        List<Access> exceptionList = new List<Access>();
        for (TypeDecl exceptionType : set) {
          if (exceptionType.isNull()) {
            exceptionType = typeNullPointerException();
          }
          exceptionList.add(exceptionType.createQualifiedAccess());
        }
        constructor.setExceptionList(exceptionList);
        return new Opt<ConstructorDecl>(constructor);
      } else {
        return new Opt<ConstructorDecl>();
      }
    }
/** @apilevel internal */
protected ASTState.Cycle isCircular_cycle = null;

  /** @apilevel internal */
  private void isCircular_reset() {
    isCircular_computed = false;
    isCircular_initialized = false;
    isCircular_cycle = null;
  }
  /** @apilevel internal */
  protected boolean isCircular_computed = false;

  /** @apilevel internal */
  protected boolean isCircular_value;
  /** @apilevel internal */
  protected boolean isCircular_initialized = false;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="Circularity", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:772")
  public boolean isCircular() {
    if (isCircular_computed) {
      return isCircular_value;
    }
    ASTState state = state();
    if (!isCircular_initialized) {
      isCircular_initialized = true;
      isCircular_value = true;
    }
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      do {
        isCircular_cycle = state.nextCycle();
        boolean new_isCircular_value = false;
        if (isCircular_value != new_isCircular_value) {
          state.setChangeInCycle();
        }
        isCircular_value = new_isCircular_value;
      } while (state.testAndClearChangeInCycle());
      isCircular_computed = true;
      state.startLastCycle();
      boolean $tmp = false;

      state.leaveCircle();
    } else if (isCircular_cycle != state.cycle()) {
      isCircular_cycle = state.cycle();
      if (state.lastCycle()) {
        isCircular_computed = true;
        boolean new_isCircular_value = false;
        return new_isCircular_value;
      }
      boolean new_isCircular_value = false;
      if (isCircular_value != new_isCircular_value) {
        state.setChangeInCycle();
      }
      isCircular_value = new_isCircular_value;
    } else {
    }
    return isCircular_value;
  }
  /** @apilevel internal */
  private void getSuperClassOpt_reset() {
    getSuperClassOpt_computed = false;
    
    getSuperClassOpt_value = null;
  }
  /** @apilevel internal */
  protected boolean getSuperClassOpt_computed = false;

  /** @apilevel internal */
  protected Opt getSuperClassOpt_value;

  /**
   * @attribute syn nta
   * @aspect AnonymousClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:54
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="AnonymousClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:54")
  public Opt getSuperClassOpt() {
    ASTState state = state();
    if (getSuperClassOpt_computed) {
      return (Opt) getChild(getSuperClassOptChildPosition());
    }
    state().enterLazyAttribute();
    getSuperClassOpt_value = getSuperClassOpt_compute();
    setChild(getSuperClassOpt_value, getSuperClassOptChildPosition());
    getSuperClassOpt_computed = true;
    state().leaveLazyAttribute();
    Opt node = (Opt) this.getChild(getSuperClassOptChildPosition());
    return node;
  }
  /** @apilevel internal */
  private Opt getSuperClassOpt_compute() {
      if (superType().isInterfaceDecl()) {
        return new Opt(typeObject().createQualifiedAccess());
      } else {
        return new Opt(superType().createBoundAccess());
      }
    }
  /** @apilevel internal */
  private void getImplementsList_reset() {
    getImplementsList_computed = false;
    
    getImplementsList_value = null;
  }
  /** @apilevel internal */
  protected boolean getImplementsList_computed = false;

  /** @apilevel internal */
  protected List getImplementsList_value;

  /**
   * @attribute syn nta
   * @aspect AnonymousClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:62
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="AnonymousClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:62")
  public List getImplementsList() {
    ASTState state = state();
    if (getImplementsList_computed) {
      return (List) getChild(getImplementsListChildPosition());
    }
    state().enterLazyAttribute();
    getImplementsList_value = getImplementsList_compute();
    setChild(getImplementsList_value, getImplementsListChildPosition());
    getImplementsList_computed = true;
    state().leaveLazyAttribute();
    List node = (List) this.getChild(getImplementsListChildPosition());
    return node;
  }
  /** @apilevel internal */
  private List getImplementsList_compute() {
      if (superType().isInterfaceDecl()) {
        return new List().add(superType().createBoundAccess());
      } else {
        return new List();
      }
    }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:233
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:232")
  public boolean isAnonymous() {
    boolean isAnonymous_value = true;
    return isAnonymous_value;
  }
  /**
   * @attribute inh
   * @aspect AnonymousClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:33
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AnonymousClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:33")
  public TypeDecl superType() {
    TypeDecl superType_value = getParent().Define_superType(this, null);
    return superType_value;
  }
  /**
   * @attribute inh
   * @aspect AnonymousClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:39
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AnonymousClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:39")
  public ConstructorDecl constructorDecl() {
    ConstructorDecl constructorDecl_value = getParent().Define_constructorDecl(this, null);
    return constructorDecl_value;
  }
  /**
   * @attribute inh
   * @aspect AnonymousClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:86
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AnonymousClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:86")
  public TypeDecl typeNullPointerException() {
    TypeDecl typeNullPointerException_value = getParent().Define_typeNullPointerException(this, null);
    return typeNullPointerException_value;
  }
  /** @apilevel internal */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
  /** @apilevel internal */
  public boolean canRewrite() {
    return false;
  }

}
