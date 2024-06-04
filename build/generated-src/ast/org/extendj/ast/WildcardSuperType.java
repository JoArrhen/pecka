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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/grammar/Generics.ast:73
 * @astdecl WildcardSuperType : AbstractWildcardType ::= Modifiers <ID:String> BodyDecl* Access;
 * @production WildcardSuperType : {@link AbstractWildcardType} ::= <span class="component">{@link Access}</span>;

 */
public class WildcardSuperType extends AbstractWildcardType implements Cloneable {
  /**
   * @aspect PrettyPrintUtil5
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/PrettyPrintUtil.jrag:145
   */
  @Override public String toString() {
    return String.format("? super %s", getAccessNoTransform().toString());
  }
  /**
   * @declaredat ASTNode:1
   */
  public WildcardSuperType() {
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
    setChild(new List(), 1);
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Modifiers", "ID", "BodyDecl", "Access"},
    type = {"Modifiers", "String", "List<BodyDecl>", "Access"},
    kind = {"Child", "Token", "List", "Child"}
  )
  public WildcardSuperType(Modifiers p0, String p1, List<BodyDecl> p2, Access p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /**
   * @declaredat ASTNode:25
   */
  public WildcardSuperType(Modifiers p0, beaver.Symbol p1, List<BodyDecl> p2, Access p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:32
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:38
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:42
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    involvesTypeParameters_reset();
    erasure_reset();
    usesTypeVariable_reset();
    typeName_reset();
    subtype_TypeDecl_reset();
    containedIn_TypeDecl_reset();
    sameStructure_TypeDecl_reset();
    strictSubtype_TypeDecl_reset();
    strictContainedIn_TypeDecl_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:55
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:60
   */
  public WildcardSuperType clone() throws CloneNotSupportedException {
    WildcardSuperType node = (WildcardSuperType) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:65
   */
  public WildcardSuperType copy() {
    try {
      WildcardSuperType node = (WildcardSuperType) clone();
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
   * @declaredat ASTNode:84
   */
  @Deprecated
  public WildcardSuperType fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:94
   */
  public WildcardSuperType treeCopyNoTransform() {
    WildcardSuperType tree = (WildcardSuperType) copy();
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
   * @declaredat ASTNode:114
   */
  public WildcardSuperType treeCopy() {
    WildcardSuperType tree = (WildcardSuperType) copy();
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
  public WildcardSuperType setModifiers(Modifiers node) {
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
  public WildcardSuperType setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public WildcardSuperType setID(beaver.Symbol symbol) {
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
  public WildcardSuperType setBodyDeclList(List<BodyDecl> list) {
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
  public WildcardSuperType addBodyDecl(BodyDecl node) {
    List<BodyDecl> list = (parent == null) ? getBodyDeclListNoTransform() : getBodyDeclList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public WildcardSuperType addBodyDeclNoTransform(BodyDecl node) {
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
  public WildcardSuperType setBodyDecl(BodyDecl node, int i) {
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
   * Replaces the Access child.
   * @param node The new node to replace the Access child.
   * @apilevel high-level
   */
  public WildcardSuperType setAccess(Access node) {
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
/** @apilevel internal */
protected ASTState.Cycle involvesTypeParameters_cycle = null;

  /** @apilevel internal */
  private void involvesTypeParameters_reset() {
    involvesTypeParameters_computed = false;
    involvesTypeParameters_initialized = false;
    involvesTypeParameters_cycle = null;
  }
  /** @apilevel internal */
  protected boolean involvesTypeParameters_computed = false;

  /** @apilevel internal */
  protected boolean involvesTypeParameters_value;
  /** @apilevel internal */
  protected boolean involvesTypeParameters_initialized = false;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="GenericMethodsInference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethodsInference.jrag:35")
  public boolean involvesTypeParameters() {
    if (involvesTypeParameters_computed) {
      return involvesTypeParameters_value;
    }
    ASTState state = state();
    if (!involvesTypeParameters_initialized) {
      involvesTypeParameters_initialized = true;
      involvesTypeParameters_value = false;
    }
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      do {
        involvesTypeParameters_cycle = state.nextCycle();
        boolean new_involvesTypeParameters_value = superType().involvesTypeParameters();
        if (involvesTypeParameters_value != new_involvesTypeParameters_value) {
          state.setChangeInCycle();
        }
        involvesTypeParameters_value = new_involvesTypeParameters_value;
      } while (state.testAndClearChangeInCycle());
      involvesTypeParameters_computed = true;
      state.startLastCycle();
      boolean $tmp = superType().involvesTypeParameters();

      state.leaveCircle();
    } else if (involvesTypeParameters_cycle != state.cycle()) {
      involvesTypeParameters_cycle = state.cycle();
      if (state.lastCycle()) {
        involvesTypeParameters_computed = true;
        boolean new_involvesTypeParameters_value = superType().involvesTypeParameters();
        return new_involvesTypeParameters_value;
      }
      boolean new_involvesTypeParameters_value = superType().involvesTypeParameters();
      if (involvesTypeParameters_value != new_involvesTypeParameters_value) {
        state.setChangeInCycle();
      }
      involvesTypeParameters_value = new_involvesTypeParameters_value;
    } else {
    }
    return involvesTypeParameters_value;
  }
  /** @apilevel internal */
  private void erasure_reset() {
    erasure_computed = null;
    erasure_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle erasure_computed = null;

  /** @apilevel internal */
  protected TypeDecl erasure_value;

  /**
   * @attribute syn
   * @aspect GenericsErasure
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:468
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsErasure", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:462")
  public TypeDecl erasure() {
    ASTState state = state();
    if (erasure_computed == ASTState.NON_CYCLE || erasure_computed == state().cycle()) {
      return erasure_value;
    }
    erasure_value = typeObject();
    if (state().inCircle()) {
      erasure_computed = state().cycle();
    
    } else {
      erasure_computed = ASTState.NON_CYCLE;
    
    }
    return erasure_value;
  }
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:869
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:869")
  public boolean sameSignature(Access a) {
    {
        if (a instanceof WildcardSuper) {
          return getAccess().type().sameSignature(((WildcardSuper) a).getAccess());
        }
        return super.sameSignature(a);
      }
  }
/** @apilevel internal */
protected ASTState.Cycle usesTypeVariable_cycle = null;

  /** @apilevel internal */
  private void usesTypeVariable_reset() {
    usesTypeVariable_computed = false;
    usesTypeVariable_initialized = false;
    usesTypeVariable_cycle = null;
  }
  /** @apilevel internal */
  protected boolean usesTypeVariable_computed = false;

  /** @apilevel internal */
  protected boolean usesTypeVariable_value;
  /** @apilevel internal */
  protected boolean usesTypeVariable_initialized = false;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1342")
  public boolean usesTypeVariable() {
    if (usesTypeVariable_computed) {
      return usesTypeVariable_value;
    }
    ASTState state = state();
    if (!usesTypeVariable_initialized) {
      usesTypeVariable_initialized = true;
      usesTypeVariable_value = false;
    }
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      do {
        usesTypeVariable_cycle = state.nextCycle();
        boolean new_usesTypeVariable_value = getAccess().type().usesTypeVariable();
        if (usesTypeVariable_value != new_usesTypeVariable_value) {
          state.setChangeInCycle();
        }
        usesTypeVariable_value = new_usesTypeVariable_value;
      } while (state.testAndClearChangeInCycle());
      usesTypeVariable_computed = true;
      state.startLastCycle();
      boolean $tmp = getAccess().type().usesTypeVariable();

      state.leaveCircle();
    } else if (usesTypeVariable_cycle != state.cycle()) {
      usesTypeVariable_cycle = state.cycle();
      if (state.lastCycle()) {
        usesTypeVariable_computed = true;
        boolean new_usesTypeVariable_value = getAccess().type().usesTypeVariable();
        return new_usesTypeVariable_value;
      }
      boolean new_usesTypeVariable_value = getAccess().type().usesTypeVariable();
      if (usesTypeVariable_value != new_usesTypeVariable_value) {
        state.setChangeInCycle();
      }
      usesTypeVariable_value = new_usesTypeVariable_value;
    } else {
    }
    return usesTypeVariable_value;
  }
  /**
   * Gives the lower bound for this wildcard type.
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1755
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1755")
  public TypeDecl superType() {
    TypeDecl superType_value = getAccess().type();
    return superType_value;
  }
  /** @apilevel internal */
  private void typeName_reset() {
    typeName_computed = null;
    typeName_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeName_computed = null;

  /** @apilevel internal */
  protected String typeName_value;

  /**
   * @attribute syn
   * @aspect NewGenerics
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1903
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeName", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/QualifiedNames.jrag:100")
  public String typeName() {
    ASTState state = state();
    if (typeName_computed == ASTState.NON_CYCLE || typeName_computed == state().cycle()) {
      return typeName_value;
    }
    typeName_value = name();
    if (state().inCircle()) {
      typeName_computed = state().cycle();
    
    } else {
      typeName_computed = ASTState.NON_CYCLE;
    
    }
    return typeName_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:77
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:73")
  public boolean supertypeWildcard(WildcardType type) {
    boolean supertypeWildcard_WildcardType_value = superType().subtype(typeObject());
    return supertypeWildcard_WildcardType_value;
  }

  /** @apilevel internal */
  private void subtype_TypeDecl_reset() {
    subtype_TypeDecl_values = null;
  }
  protected java.util.Map subtype_TypeDecl_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="Subtyping", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:455")
  public boolean subtype(TypeDecl type) {
    Object _parameters = type;


    if (subtype_TypeDecl_values == null) subtype_TypeDecl_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (subtype_TypeDecl_values.containsKey(_parameters)) {
      Object _cache = subtype_TypeDecl_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      subtype_TypeDecl_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_subtype_TypeDecl_value;
      do {
        _value.cycle = state.nextCycle();
        new_subtype_TypeDecl_value = type.supertypeWildcardSuper(this);
        if (((Boolean)_value.value) != new_subtype_TypeDecl_value) {
          state.setChangeInCycle();
          _value.value = new_subtype_TypeDecl_value;
        }
      } while (state.testAndClearChangeInCycle());
      subtype_TypeDecl_values.put(_parameters, new_subtype_TypeDecl_value);
      state.startLastCycle();
      boolean $tmp = type.supertypeWildcardSuper(this);

      state.leaveCircle();
      return new_subtype_TypeDecl_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_subtype_TypeDecl_value = type.supertypeWildcardSuper(this);
      if (state.lastCycle()) {
        subtype_TypeDecl_values.put(_parameters, new_subtype_TypeDecl_value);
      }
      if (((Boolean)_value.value) != new_subtype_TypeDecl_value) {
        state.setChangeInCycle();
        _value.value = new_subtype_TypeDecl_value;
      }
      return new_subtype_TypeDecl_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:92
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:89")
  public boolean supertypeWildcardSuper(WildcardSuperType type) {
    boolean supertypeWildcardSuper_WildcardSuperType_value = type.superType().subtype(superType());
    return supertypeWildcardSuper_WildcardSuperType_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:116
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Subtyping", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:471")
  public boolean supertypeClassDecl(ClassDecl type) {
    boolean supertypeClassDecl_ClassDecl_value = type.subtype(superType());
    return supertypeClassDecl_ClassDecl_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:117
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Subtyping", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:486")
  public boolean supertypeInterfaceDecl(InterfaceDecl type) {
    boolean supertypeInterfaceDecl_InterfaceDecl_value = type.subtype(superType());
    return supertypeInterfaceDecl_InterfaceDecl_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:118
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:152")
  public boolean supertypeParClassDecl(ParClassDecl type) {
    boolean supertypeParClassDecl_ParClassDecl_value = type.subtype(superType());
    return supertypeParClassDecl_ParClassDecl_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:119
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:156")
  public boolean supertypeParInterfaceDecl(ParInterfaceDecl type) {
    boolean supertypeParInterfaceDecl_ParInterfaceDecl_value = type.subtype(superType());
    return supertypeParInterfaceDecl_ParInterfaceDecl_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:120
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:53")
  public boolean supertypeRawClassDecl(RawClassDecl type) {
    boolean supertypeRawClassDecl_RawClassDecl_value = type.subtype(superType());
    return supertypeRawClassDecl_RawClassDecl_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:121
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:57")
  public boolean supertypeRawInterfaceDecl(RawInterfaceDecl type) {
    boolean supertypeRawInterfaceDecl_RawInterfaceDecl_value = type.subtype(superType());
    return supertypeRawInterfaceDecl_RawInterfaceDecl_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:122
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:368")
  public boolean supertypeTypeVariable(TypeVariable type) {
    boolean supertypeTypeVariable_TypeVariable_value = type.subtype(superType());
    return supertypeTypeVariable_TypeVariable_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:123
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Subtyping", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:500")
  public boolean supertypeArrayDecl(ArrayDecl type) {
    boolean supertypeArrayDecl_ArrayDecl_value = type.subtype(superType());
    return supertypeArrayDecl_ArrayDecl_value;
  }

  /** @apilevel internal */
  private void containedIn_TypeDecl_reset() {
    containedIn_TypeDecl_values = null;
  }
  protected java.util.Map containedIn_TypeDecl_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:168")
  public boolean containedIn(TypeDecl type) {
    Object _parameters = type;


    if (containedIn_TypeDecl_values == null) containedIn_TypeDecl_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (containedIn_TypeDecl_values.containsKey(_parameters)) {
      Object _cache = containedIn_TypeDecl_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      containedIn_TypeDecl_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_containedIn_TypeDecl_value;
      do {
        _value.cycle = state.nextCycle();
        new_containedIn_TypeDecl_value = containedIn_compute(type);
        if (((Boolean)_value.value) != new_containedIn_TypeDecl_value) {
          state.setChangeInCycle();
          _value.value = new_containedIn_TypeDecl_value;
        }
      } while (state.testAndClearChangeInCycle());
      containedIn_TypeDecl_values.put(_parameters, new_containedIn_TypeDecl_value);
      state.startLastCycle();
      boolean $tmp = containedIn_compute(type);

      state.leaveCircle();
      return new_containedIn_TypeDecl_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_containedIn_TypeDecl_value = containedIn_compute(type);
      if (state.lastCycle()) {
        containedIn_TypeDecl_values.put(_parameters, new_containedIn_TypeDecl_value);
      }
      if (((Boolean)_value.value) != new_containedIn_TypeDecl_value) {
        state.setChangeInCycle();
        _value.value = new_containedIn_TypeDecl_value;
      }
      return new_containedIn_TypeDecl_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /** @apilevel internal */
  private boolean containedIn_compute(TypeDecl type) {
      if (type == this || type instanceof WildcardType) {
        return true;
      } else if (type instanceof WildcardExtendsType) {
        TypeDecl typeExtends = ((WildcardExtendsType) type).extendsType();
        return superType().subtype(typeExtends);
      } else if (type instanceof WildcardSuperType) {
        TypeDecl typeSuper = ((WildcardSuperType) type).superType();
        return superType().sameStructure(typeSuper) || typeSuper.subtype(superType());
      } else {
        return false;
      }
    }

  /** @apilevel internal */
  private void sameStructure_TypeDecl_reset() {
    sameStructure_TypeDecl_values = null;
  }
  protected java.util.Map sameStructure_TypeDecl_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericsSubtype.jrag:222")
  public boolean sameStructure(TypeDecl t) {
    Object _parameters = t;


    if (sameStructure_TypeDecl_values == null) sameStructure_TypeDecl_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (sameStructure_TypeDecl_values.containsKey(_parameters)) {
      Object _cache = sameStructure_TypeDecl_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      sameStructure_TypeDecl_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_sameStructure_TypeDecl_value;
      do {
        _value.cycle = state.nextCycle();
        new_sameStructure_TypeDecl_value = super.sameStructure(t) || t instanceof WildcardSuperType
              && ((WildcardSuperType) t).superType().sameStructure(superType());
        if (((Boolean)_value.value) != new_sameStructure_TypeDecl_value) {
          state.setChangeInCycle();
          _value.value = new_sameStructure_TypeDecl_value;
        }
      } while (state.testAndClearChangeInCycle());
      sameStructure_TypeDecl_values.put(_parameters, new_sameStructure_TypeDecl_value);
      state.startLastCycle();
      boolean $tmp = super.sameStructure(t) || t instanceof WildcardSuperType
            && ((WildcardSuperType) t).superType().sameStructure(superType());

      state.leaveCircle();
      return new_sameStructure_TypeDecl_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_sameStructure_TypeDecl_value = super.sameStructure(t) || t instanceof WildcardSuperType
            && ((WildcardSuperType) t).superType().sameStructure(superType());
      if (state.lastCycle()) {
        sameStructure_TypeDecl_values.put(_parameters, new_sameStructure_TypeDecl_value);
      }
      if (((Boolean)_value.value) != new_sameStructure_TypeDecl_value) {
        state.setChangeInCycle();
        _value.value = new_sameStructure_TypeDecl_value;
      }
      return new_sameStructure_TypeDecl_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /**
   * @attribute syn
   * @aspect GenericBoundCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericBoundCheck.jrag:50
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericBoundCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericBoundCheck.jrag:40")
  public boolean withinBounds(TypeDecl bound) {
    boolean withinBounds_TypeDecl_value = bound.boundOf(superType());
    return withinBounds_TypeDecl_value;
  }
  /**
   * @attribute syn
   * @aspect GenericBoundCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericBoundCheck.jrag:90
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericBoundCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericBoundCheck.jrag:86")
  public boolean boundOfArray(ArrayDecl type) {
    boolean boundOfArray_ArrayDecl_value = true;
    return boundOfArray_ArrayDecl_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:73
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:68")
  public boolean strictSupertypeWildcard(WildcardType type) {
    boolean strictSupertypeWildcard_WildcardType_value = superType().strictSubtype(typeObject());
    return strictSupertypeWildcard_WildcardType_value;
  }

  /** @apilevel internal */
  private void strictSubtype_TypeDecl_reset() {
    strictSubtype_TypeDecl_values = null;
  }
  protected java.util.Map strictSubtype_TypeDecl_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:363")
  public boolean strictSubtype(TypeDecl type) {
    Object _parameters = type;


    if (strictSubtype_TypeDecl_values == null) strictSubtype_TypeDecl_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (strictSubtype_TypeDecl_values.containsKey(_parameters)) {
      Object _cache = strictSubtype_TypeDecl_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      strictSubtype_TypeDecl_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_strictSubtype_TypeDecl_value;
      do {
        _value.cycle = state.nextCycle();
        new_strictSubtype_TypeDecl_value = type.strictSupertypeWildcardSuper(this);
        if (((Boolean)_value.value) != new_strictSubtype_TypeDecl_value) {
          state.setChangeInCycle();
          _value.value = new_strictSubtype_TypeDecl_value;
        }
      } while (state.testAndClearChangeInCycle());
      strictSubtype_TypeDecl_values.put(_parameters, new_strictSubtype_TypeDecl_value);
      state.startLastCycle();
      boolean $tmp = type.strictSupertypeWildcardSuper(this);

      state.leaveCircle();
      return new_strictSubtype_TypeDecl_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_strictSubtype_TypeDecl_value = type.strictSupertypeWildcardSuper(this);
      if (state.lastCycle()) {
        strictSubtype_TypeDecl_values.put(_parameters, new_strictSubtype_TypeDecl_value);
      }
      if (((Boolean)_value.value) != new_strictSubtype_TypeDecl_value) {
        state.setChangeInCycle();
        _value.value = new_strictSubtype_TypeDecl_value;
      }
      return new_strictSubtype_TypeDecl_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:83
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:79")
  public boolean strictSupertypeWildcardSuper(WildcardSuperType type) {
    boolean strictSupertypeWildcardSuper_WildcardSuperType_value = type.superType().strictSubtype(superType());
    return strictSupertypeWildcardSuper_WildcardSuperType_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:112
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:376")
  public boolean strictSupertypeClassDecl(ClassDecl type) {
    boolean strictSupertypeClassDecl_ClassDecl_value = superType().strictSubtype(type);
    return strictSupertypeClassDecl_ClassDecl_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:114
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:395")
  public boolean strictSupertypeInterfaceDecl(InterfaceDecl type) {
    boolean strictSupertypeInterfaceDecl_InterfaceDecl_value = superType().strictSubtype(type);
    return strictSupertypeInterfaceDecl_InterfaceDecl_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:116
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:149")
  public boolean strictSupertypeParClassDecl(ParClassDecl type) {
    boolean strictSupertypeParClassDecl_ParClassDecl_value = superType().strictSubtype(type);
    return strictSupertypeParClassDecl_ParClassDecl_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:118
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:153")
  public boolean strictSupertypeParInterfaceDecl(ParInterfaceDecl type) {
    boolean strictSupertypeParInterfaceDecl_ParInterfaceDecl_value = superType().strictSubtype(type);
    return strictSupertypeParInterfaceDecl_ParInterfaceDecl_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:120
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:52")
  public boolean strictSupertypeRawClassDecl(RawClassDecl type) {
    boolean strictSupertypeRawClassDecl_RawClassDecl_value = superType().strictSubtype(type);
    return strictSupertypeRawClassDecl_RawClassDecl_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:122
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:56")
  public boolean strictSupertypeRawInterfaceDecl(RawInterfaceDecl type) {
    boolean strictSupertypeRawInterfaceDecl_RawInterfaceDecl_value = superType().strictSubtype(type);
    return strictSupertypeRawInterfaceDecl_RawInterfaceDecl_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:124
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:281")
  public boolean strictSupertypeTypeVariable(TypeVariable type) {
    boolean strictSupertypeTypeVariable_TypeVariable_value = superType().strictSubtype(type);
    return strictSupertypeTypeVariable_TypeVariable_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:126
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:411")
  public boolean strictSupertypeArrayDecl(ArrayDecl type) {
    boolean strictSupertypeArrayDecl_ArrayDecl_value = superType().strictSubtype(type);
    return strictSupertypeArrayDecl_ArrayDecl_value;
  }

  /** @apilevel internal */
  private void strictContainedIn_TypeDecl_reset() {
    strictContainedIn_TypeDecl_values = null;
  }
  protected java.util.Map strictContainedIn_TypeDecl_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:165")
  public boolean strictContainedIn(TypeDecl type) {
    Object _parameters = type;


    if (strictContainedIn_TypeDecl_values == null) strictContainedIn_TypeDecl_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (strictContainedIn_TypeDecl_values.containsKey(_parameters)) {
      Object _cache = strictContainedIn_TypeDecl_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      strictContainedIn_TypeDecl_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_strictContainedIn_TypeDecl_value;
      do {
        _value.cycle = state.nextCycle();
        new_strictContainedIn_TypeDecl_value = strictContainedIn_compute(type);
        if (((Boolean)_value.value) != new_strictContainedIn_TypeDecl_value) {
          state.setChangeInCycle();
          _value.value = new_strictContainedIn_TypeDecl_value;
        }
      } while (state.testAndClearChangeInCycle());
      strictContainedIn_TypeDecl_values.put(_parameters, new_strictContainedIn_TypeDecl_value);
      state.startLastCycle();
      boolean $tmp = strictContainedIn_compute(type);

      state.leaveCircle();
      return new_strictContainedIn_TypeDecl_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_strictContainedIn_TypeDecl_value = strictContainedIn_compute(type);
      if (state.lastCycle()) {
        strictContainedIn_TypeDecl_values.put(_parameters, new_strictContainedIn_TypeDecl_value);
      }
      if (((Boolean)_value.value) != new_strictContainedIn_TypeDecl_value) {
        state.setChangeInCycle();
        _value.value = new_strictContainedIn_TypeDecl_value;
      }
      return new_strictContainedIn_TypeDecl_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /** @apilevel internal */
  private boolean strictContainedIn_compute(TypeDecl type) {
      if (type == this || type instanceof WildcardType) {
        return true;
      } else if (type instanceof WildcardSuperType) {
        return ((WildcardSuperType) type).superType().strictSubtype(superType());
      } else {
        return false;
      }
    }
  /**
   * @attribute syn
   * @aspect LambdaParametersInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:645
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaParametersInference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:639")
  public TypeDecl boundType() {
    TypeDecl boundType_value = superType();
    return boundType_value;
  }
  /**
   * @attribute syn
   * @aspect LambdaParametersInference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:662
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaParametersInference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeCheck.jrag:651")
  public TypeDecl nonWildcardParamType(TypeVariable bound) {
    TypeDecl nonWildcardParamType_TypeVariable_value = superType().nonWildcardParamType(bound);
    return nonWildcardParamType_TypeVariable_value;
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
