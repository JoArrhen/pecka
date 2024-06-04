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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:181
 * @astdecl InterfaceDecl : ReferenceType ::= Modifiers <ID:String> SuperInterface:Access* BodyDecl*;
 * @production InterfaceDecl : {@link ReferenceType} ::= <span class="component">{@link Modifiers}</span> <span class="component">&lt;ID:{@link String}&gt;</span> <span class="component">SuperInterface:{@link Access}*</span> <span class="component">{@link BodyDecl}*</span>;

 */
public class InterfaceDecl extends ReferenceType implements Cloneable, DeclarationSite {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrint.jadd:399
   */
  public void prettyPrint(PrettyPrinter out) {
    if (hasDocComment()) {
      out.print(docComment());
    }
    if (!out.isNewLine()) {
      out.println();
    }
    out.print(getModifiers());
    out.print("interface ");
    out.print(getID());
    if (hasSuperInterface()) {
      out.print(" extends ");
      out.join(getSuperInterfaceList(), new PrettyPrinter.Joiner() {
        @Override
        public void printSeparator(PrettyPrinter out) {
          out.print(", ");
        }
      });
    }
    out.print(" {");
    out.println();
    out.indent(1);
    out.join(getBodyDecls(), new PrettyPrinter.Joiner() {
      @Override
      public void printSeparator(PrettyPrinter out) {
        out.println();
        out.println();
      }
    });
    if (!out.isNewLine()) {
      out.println();
    }
    out.print("}");
  }
  /**
   * @aspect Generics
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:312
   */
  public TypeDecl makeGeneric(Signatures.ClassSignature s) {
    if (s.hasFormalTypeParameters()) {
      ASTNode node = getParent();
      int index = node.getIndexOfChild(this);
      node.setChild(
          new GenericInterfaceDecl(
              getModifiersNoTransform(),
              getID(),
              s.hasSuperinterfaceSignature()
                  ? s.superinterfaceSignature()
                  : getSuperInterfaceListNoTransform(),
              getBodyDeclListNoTransform(),
              s.typeParameters()),
          index);
      return (TypeDecl) node.getChildNoTransform(index);
    } else {
      if (s.hasSuperinterfaceSignature()) {
        setSuperInterfaceList(s.superinterfaceSignature());
      }
      return this;
    }
  }
  /**
   * @declaredat ASTNode:1
   */
  public InterfaceDecl() {
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
    setChild(new List(), 2);
  }
  /**
   * @declaredat ASTNode:15
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Modifiers", "ID", "SuperInterface", "BodyDecl"},
    type = {"Modifiers", "String", "List<Access>", "List<BodyDecl>"},
    kind = {"Child", "Token", "List", "List"}
  )
  public InterfaceDecl(Modifiers p0, String p1, List<Access> p2, List<BodyDecl> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /**
   * @declaredat ASTNode:26
   */
  public InterfaceDecl(Modifiers p0, beaver.Symbol p1, List<Access> p2, List<BodyDecl> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:33
   */
  protected int numChildren() {
    return 3;
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
    superInterfacesTarget_reset();
    subtypesIncludingSelf_reset();
    isStatic_reset();
    memberFieldsMap_reset();
    memberFields_String_reset();
    methods_reset();
    ancestorMethods_String_reset();
    castingConversionTo_TypeDecl_reset();
    subtype_TypeDecl_reset();
    superInterfaces_reset();
    isCircular_reset();
    erasedAncestorMethodsMap_reset();
    implementedInterfaces_reset();
    iterableElementType_reset();
    hasAnnotationFunctionalInterface_reset();
    isFunctionalInterface_reset();
    isFunctional_reset();
    collectAbstractMethods_reset();
    hasFunctionDescriptor_reset();
    functionDescriptor_reset();
    hasOverridingMethodInSuper_MethodDecl_reset();
    strictSubtype_TypeDecl_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:69
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:74
   */
  public InterfaceDecl clone() throws CloneNotSupportedException {
    InterfaceDecl node = (InterfaceDecl) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:79
   */
  public InterfaceDecl copy() {
    try {
      InterfaceDecl node = (InterfaceDecl) clone();
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
   * @declaredat ASTNode:98
   */
  @Deprecated
  public InterfaceDecl fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:108
   */
  public InterfaceDecl treeCopyNoTransform() {
    InterfaceDecl tree = (InterfaceDecl) copy();
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
   * @declaredat ASTNode:128
   */
  public InterfaceDecl treeCopy() {
    InterfaceDecl tree = (InterfaceDecl) copy();
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
  public InterfaceDecl setModifiers(Modifiers node) {
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
  public InterfaceDecl setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public InterfaceDecl setID(beaver.Symbol symbol) {
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
   * Replaces the SuperInterface list.
   * @param list The new list node to be used as the SuperInterface list.
   * @apilevel high-level
   */
  public InterfaceDecl setSuperInterfaceList(List<Access> list) {
    setChild(list, 1);
    return this;
  }
  /**
   * Retrieves the number of children in the SuperInterface list.
   * @return Number of children in the SuperInterface list.
   * @apilevel high-level
   */
  public int getNumSuperInterface() {
    return getSuperInterfaceList().getNumChild();
  }
  /**
   * Retrieves the number of children in the SuperInterface list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the SuperInterface list.
   * @apilevel low-level
   */
  public int getNumSuperInterfaceNoTransform() {
    return getSuperInterfaceListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the SuperInterface list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the SuperInterface list.
   * @apilevel high-level
   */
  public Access getSuperInterface(int i) {
    return (Access) getSuperInterfaceList().getChild(i);
  }
  /**
   * Check whether the SuperInterface list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasSuperInterface() {
    return getSuperInterfaceList().getNumChild() != 0;
  }
  /**
   * Append an element to the SuperInterface list.
   * @param node The element to append to the SuperInterface list.
   * @apilevel high-level
   */
  public InterfaceDecl addSuperInterface(Access node) {
    List<Access> list = (parent == null) ? getSuperInterfaceListNoTransform() : getSuperInterfaceList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public InterfaceDecl addSuperInterfaceNoTransform(Access node) {
    List<Access> list = getSuperInterfaceListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the SuperInterface list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public InterfaceDecl setSuperInterface(Access node, int i) {
    List<Access> list = getSuperInterfaceList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the SuperInterface list.
   * @return The node representing the SuperInterface list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="SuperInterface")
  public List<Access> getSuperInterfaceList() {
    List<Access> list = (List<Access>) getChild(1);
    return list;
  }
  /**
   * Retrieves the SuperInterface list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the SuperInterface list.
   * @apilevel low-level
   */
  public List<Access> getSuperInterfaceListNoTransform() {
    return (List<Access>) getChildNoTransform(1);
  }
  /**
   * @return the element at index {@code i} in the SuperInterface list without
   * triggering rewrites.
   */
  public Access getSuperInterfaceNoTransform(int i) {
    return (Access) getSuperInterfaceListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the SuperInterface list.
   * @return The node representing the SuperInterface list.
   * @apilevel high-level
   */
  public List<Access> getSuperInterfaces() {
    return getSuperInterfaceList();
  }
  /**
   * Retrieves the SuperInterface list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the SuperInterface list.
   * @apilevel low-level
   */
  public List<Access> getSuperInterfacesNoTransform() {
    return getSuperInterfaceListNoTransform();
  }
  /**
   * Replaces the BodyDecl list.
   * @param list The new list node to be used as the BodyDecl list.
   * @apilevel high-level
   */
  public InterfaceDecl setBodyDeclList(List<BodyDecl> list) {
    setChild(list, 2);
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
  public InterfaceDecl addBodyDecl(BodyDecl node) {
    List<BodyDecl> list = (parent == null) ? getBodyDeclListNoTransform() : getBodyDeclList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public InterfaceDecl addBodyDeclNoTransform(BodyDecl node) {
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
  public InterfaceDecl setBodyDecl(BodyDecl node, int i) {
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
    List<BodyDecl> list = (List<BodyDecl>) getChild(2);
    return list;
  }
  /**
   * Retrieves the BodyDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the BodyDecl list.
   * @apilevel low-level
   */
  public List<BodyDecl> getBodyDeclListNoTransform() {
    return (List<BodyDecl>) getChildNoTransform(2);
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
   * @aspect Generics
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:141
   */
  private boolean refined_Generics_InterfaceDecl_castingConversionTo_TypeDecl(TypeDecl type)
{
    TypeDecl S = this;
    TypeDecl T = type;
    if (T.isArrayDecl()) {
      return T.subtype(S);
    } else if (T.isReferenceType() && !T.isFinal()) {
      return true;
    } else {
      return T.subtype(S);
    }
  }
  /** @apilevel internal */
  private void superInterfacesTarget_reset() {
    superInterfacesTarget_computed = null;
    superInterfacesTarget_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle superInterfacesTarget_computed = null;

  /** @apilevel internal */
  protected java.util.List<TypeDecl> superInterfacesTarget_value;

  /**
   * Returns the generic super types target of a ClassDecl.
   * @attribute syn
   * @aspect CHA
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:108
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CHA", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:108")
  public java.util.List<TypeDecl> superInterfacesTarget() {
    ASTState state = state();
    if (superInterfacesTarget_computed == ASTState.NON_CYCLE || superInterfacesTarget_computed == state().cycle()) {
      return superInterfacesTarget_value;
    }
    superInterfacesTarget_value = superInterfacesTarget_compute();
    if (state().inCircle()) {
      superInterfacesTarget_computed = state().cycle();
    
    } else {
      superInterfacesTarget_computed = ASTState.NON_CYCLE;
    
    }
    return superInterfacesTarget_value;
  }
  /** @apilevel internal */
  private java.util.List<TypeDecl> superInterfacesTarget_compute() {
      java.util.List<TypeDecl> interfaces = new ArrayList<TypeDecl>();
      Iterator iter = superInterfaces().iterator();
      while (iter.hasNext()) {
        TypeDecl t = (TypeDecl)iter.next();
        if (t.isParDecl()) {
          interfaces.add(((ParInterfaceDecl)t).genericDecl());
        } else if (t.isRawDecl()) {
          interfaces.add(((RawInterfaceDecl)t).genericDecl());
        } else {
          interfaces.add(t);
        }
      }
      return interfaces;
    }
  /** @apilevel internal */
  private void subtypesIncludingSelf_reset() {
    subtypesIncludingSelf_computed = null;
    subtypesIncludingSelf_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle subtypesIncludingSelf_computed = null;

  /** @apilevel internal */
  protected Set<TypeDecl> subtypesIncludingSelf_value;

  /**
   * @attribute syn
   * @aspect CHA
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:147
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CHA", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:147")
  public Set<TypeDecl> subtypesIncludingSelf() {
    ASTState state = state();
    if (subtypesIncludingSelf_computed == ASTState.NON_CYCLE || subtypesIncludingSelf_computed == state().cycle()) {
      return subtypesIncludingSelf_value;
    }
    subtypesIncludingSelf_value = subtypesIncludingSelf_compute();
    if (state().inCircle()) {
      subtypesIncludingSelf_computed = state().cycle();
    
    } else {
      subtypesIncludingSelf_computed = ASTState.NON_CYCLE;
    
    }
    return subtypesIncludingSelf_value;
  }
  /** @apilevel internal */
  private Set<TypeDecl> subtypesIncludingSelf_compute() {
      Set<TypeDecl> subtypes = new HashSet<>(subtypes());
      subtypes.add(this);
      return subtypes;
    }
  /**
   * @attribute syn
   * @aspect ConstructScope
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:52
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:47")
  public Collection<ConstructorDecl> lookupSuperConstructor() {
    Collection<ConstructorDecl> lookupSuperConstructor_value = typeObject().constructors();
    return lookupSuperConstructor_value;
  }
  /**
   * @attribute syn
   * @aspect AccessControl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AccessControl.jrag:215
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AccessControl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AccessControl.jrag:215")
  public Collection<Problem> accessControlProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
    
        if (!isCircular()) {
          // 9.1.2
          Collection<TypeDecl> interfaces = new HashSet<TypeDecl>();
          for (Access access : getSuperInterfaceList()) {
            TypeDecl decl = access.type();
            if (!decl.isInterfaceDecl() && !decl.isUnknown()) {
              problems.add(errorf("interface %s cannot extend non interface type %s",
                  fullName(), decl.fullName()));
            }
            if (!decl.isCircular() && !decl.accessibleFrom(this)) {
              problems.add(errorf("interface %s cannot extend non accessible type %s",
                  fullName(), decl.fullName()));
            }
            if (interfaces.contains(decl)) {
              problems.add(errorf(
                  "extended interface %s is mentionened multiple times in extends clause",
                  decl.fullName()));
            }
            interfaces.add(decl);
          }
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect TypeHierarchyCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:524
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeHierarchyCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:524")
  public Collection<Problem> typeHierarchyProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        //9.6.3.8
        if (hasAnnotationFunctionalInterface() && !isFunctional()) {
          problems.add(errorf("%s is not a functional interface", name()));
        }
    
        if (isCircular()) {
          problems.add(errorf("circular inheritance dependency in %s", typeName()));
        } else {
          for (int i = 0; i < getNumSuperInterface(); i++) {
            TypeDecl typeDecl = getSuperInterface(i).type();
            if (typeDecl.isCircular()) {
              problems.add(errorf("circular inheritance dependency in %s", typeName()));
            }
          }
        }
        for (SimpleSet<MethodDecl> set : methodsSignatureMap().values()) {
          if (set.size() > 1) {
            Iterator i2 = set.iterator();
            MethodDecl m = (MethodDecl) i2.next();
            while (i2.hasNext()) {
              MethodDecl n = (MethodDecl) i2.next();
              checkInterfaceMethodDecls(problems, m, n);
            }
          }
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:698
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeScopePropagation", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:698")
  public SimpleSet<TypeDecl> memberTypes(String name) {
    {
        SimpleSet<TypeDecl> result = localTypeDecls(name);
        if (!result.isEmpty()) {
          return result;
        }
        for (InterfaceDecl iface : superInterfaces()) {
          for (TypeDecl decl : iface.memberTypes(name)) {
            if (!decl.isPrivate()) {
              result = result.add(decl);
            }
          }
        }
        return result;
      }
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:239
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:238")
  public boolean isAbstract() {
    boolean isAbstract_value = true;
    return isAbstract_value;
  }
  /** @apilevel internal */
  private void isStatic_reset() {
    isStatic_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isStatic_computed = null;

  /** @apilevel internal */
  protected boolean isStatic_value;

  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:244
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:240")
  public boolean isStatic() {
    ASTState state = state();
    if (isStatic_computed == ASTState.NON_CYCLE || isStatic_computed == state().cycle()) {
      return isStatic_value;
    }
    isStatic_value = getModifiers().isStatic() || isMemberType();
    if (state().inCircle()) {
      isStatic_computed = state().cycle();
    
    } else {
      isStatic_computed = ASTState.NON_CYCLE;
    
    }
    return isStatic_value;
  }
  /** @apilevel internal */
  private void memberFieldsMap_reset() {
    memberFieldsMap_computed = null;
    memberFieldsMap_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle memberFieldsMap_computed = null;

  /** @apilevel internal */
  protected Map<String, SimpleSet<Variable>> memberFieldsMap_value;

  /**
   * @attribute syn
   * @aspect Fields
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:461
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Fields", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:433")
  public Map<String, SimpleSet<Variable>> memberFieldsMap() {
    ASTState state = state();
    if (memberFieldsMap_computed == ASTState.NON_CYCLE || memberFieldsMap_computed == state().cycle()) {
      return memberFieldsMap_value;
    }
    memberFieldsMap_value = memberFieldsMap_compute();
    if (state().inCircle()) {
      memberFieldsMap_computed = state().cycle();
    
    } else {
      memberFieldsMap_computed = ASTState.NON_CYCLE;
    
    }
    return memberFieldsMap_value;
  }
  /** @apilevel internal */
  private Map<String, SimpleSet<Variable>> memberFieldsMap_compute() {
      Map<String, SimpleSet<Variable>> map =
          new HashMap<String, SimpleSet<Variable>>(localFieldsMap());
      for (InterfaceDecl iface : superInterfaces()) {
        Iterator<Variable> iter = iface.fieldsIterator();
        while (iter.hasNext()) {
          Variable f = iter.next();
          if (f.accessibleFrom(this) && !f.isPrivate() && !localFieldsMap().containsKey(f.name())) {
            putSimpleSetElement(map, f.name(), f);
          }
        }
      }
      return map;
    }
  /** @apilevel internal */
  private void memberFields_String_reset() {
    memberFields_String_computed = null;
    memberFields_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map memberFields_String_values;
  /** @apilevel internal */
  protected java.util.Map memberFields_String_computed;
  /**
   * @attribute syn
   * @aspect Fields
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:531
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Fields", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:502")
  public SimpleSet<Variable> memberFields(String name) {
    Object _parameters = name;
    if (memberFields_String_computed == null) memberFields_String_computed = new java.util.HashMap(4);
    if (memberFields_String_values == null) memberFields_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (memberFields_String_values.containsKey(_parameters)
        && memberFields_String_computed.containsKey(_parameters)
        && (memberFields_String_computed.get(_parameters) == ASTState.NON_CYCLE || memberFields_String_computed.get(_parameters) == state().cycle())) {
      return (SimpleSet<Variable>) memberFields_String_values.get(_parameters);
    }
    SimpleSet<Variable> memberFields_String_value = memberFields_compute(name);
    if (state().inCircle()) {
      memberFields_String_values.put(_parameters, memberFields_String_value);
      memberFields_String_computed.put(_parameters, state().cycle());
    
    } else {
      memberFields_String_values.put(_parameters, memberFields_String_value);
      memberFields_String_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return memberFields_String_value;
  }
  /** @apilevel internal */
  private SimpleSet<Variable> memberFields_compute(String name) {
      SimpleSet<Variable> fields = localFields(name);
      if (!fields.isEmpty()) {
        return fields;
      }
      for (InterfaceDecl iface : superInterfaces()) {
        Iterator<Variable> iter = iface.memberFields(name).iterator();
        while (iter.hasNext()) {
          Variable f = iter.next();
          if (f.accessibleFrom(this) && !f.isPrivate()) {
            fields = fields.add(f);
          }
        }
      }
      return fields;
    }
  /** @apilevel internal */
  private void methods_reset() {
    methods_computed = null;
    methods_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle methods_computed = null;

  /** @apilevel internal */
  protected java.util.List<MethodDecl> methods_value;

  /**
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:1232
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MemberMethods", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:636")
  public java.util.List<MethodDecl> methods() {
    ASTState state = state();
    if (methods_computed == ASTState.NON_CYCLE || methods_computed == state().cycle()) {
      return methods_value;
    }
    methods_value = methods_compute();
    if (state().inCircle()) {
      methods_computed = state().cycle();
    
    } else {
      methods_computed = ASTState.NON_CYCLE;
    
    }
    return methods_value;
  }
  /** @apilevel internal */
  private java.util.List<MethodDecl> methods_compute() {
      Map<String, SimpleSet<MethodDecl>> localMap = localMethodsSignatureMap();
      ArrayList<MethodDecl> methods = new ArrayList<MethodDecl>(localMethods());
      for (MethodDecl m : interfacesMethods()) {
        if (!m.isStatic()
            && m.accessibleFrom(this)
            && !localMap.containsKey(m.signature())
            && !hasOverridingMethodInSuper(m)) {
          methods.add(m);
        }
      }
      for (MethodDecl m : typeObject().methods()) {
        // TODO(joqvist): is it possible to simplify this?
        if (m.isPublic()
            && !containsSignature(methods, m.signature())) {
          methods.add(m);
        }
      }
      return methods;
    }
  /** @apilevel internal */
  private void ancestorMethods_String_reset() {
    ancestorMethods_String_computed = null;
    ancestorMethods_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map ancestorMethods_String_values;
  /** @apilevel internal */
  protected java.util.Map ancestorMethods_String_computed;
  /**
   * @attribute syn
   * @aspect AncestorMethods
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:782
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AncestorMethods", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:743")
  public SimpleSet<MethodDecl> ancestorMethods(String signature) {
    Object _parameters = signature;
    if (ancestorMethods_String_computed == null) ancestorMethods_String_computed = new java.util.HashMap(4);
    if (ancestorMethods_String_values == null) ancestorMethods_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (ancestorMethods_String_values.containsKey(_parameters)
        && ancestorMethods_String_computed.containsKey(_parameters)
        && (ancestorMethods_String_computed.get(_parameters) == ASTState.NON_CYCLE || ancestorMethods_String_computed.get(_parameters) == state().cycle())) {
      return (SimpleSet<MethodDecl>) ancestorMethods_String_values.get(_parameters);
    }
    SimpleSet<MethodDecl> ancestorMethods_String_value = ancestorMethods_compute(signature);
    if (state().inCircle()) {
      ancestorMethods_String_values.put(_parameters, ancestorMethods_String_value);
      ancestorMethods_String_computed.put(_parameters, state().cycle());
    
    } else {
      ancestorMethods_String_values.put(_parameters, ancestorMethods_String_value);
      ancestorMethods_String_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return ancestorMethods_String_value;
  }
  /** @apilevel internal */
  private SimpleSet<MethodDecl> ancestorMethods_compute(String signature) {
      SimpleSet<MethodDecl> result = emptySet();
      for (InterfaceDecl typeDecl : superInterfaces()) {
        for (MethodDecl m : typeDecl.methodsSignature(signature)) {
          result = result.add(m);
        }
      }
      if (getNumSuperInterface() == 0) {
        for (MethodDecl m : typeObject().methodsSignature(signature)) {
          if (m.isPublic()) {
            result = result.add(m);
          }
        }
      }
      return result;
    }
  /**
   * @attribute syn
   * @aspect PrettyPrintUtil
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:330
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PrettyPrintUtil", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:330")
  public boolean hasModifiers() {
    boolean hasModifiers_value = getModifiers().getNumModifier() > 0;
    return hasModifiers_value;
  }
  /** @apilevel internal */
  private void castingConversionTo_TypeDecl_reset() {
    castingConversionTo_TypeDecl_computed = null;
    castingConversionTo_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map castingConversionTo_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map castingConversionTo_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/AutoBoxing.jrag:222
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeConversion", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:101")
  public boolean castingConversionTo(TypeDecl type) {
    Object _parameters = type;
    if (castingConversionTo_TypeDecl_computed == null) castingConversionTo_TypeDecl_computed = new java.util.HashMap(4);
    if (castingConversionTo_TypeDecl_values == null) castingConversionTo_TypeDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (castingConversionTo_TypeDecl_values.containsKey(_parameters)
        && castingConversionTo_TypeDecl_computed.containsKey(_parameters)
        && (castingConversionTo_TypeDecl_computed.get(_parameters) == ASTState.NON_CYCLE || castingConversionTo_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) castingConversionTo_TypeDecl_values.get(_parameters);
    }
    boolean castingConversionTo_TypeDecl_value = castingConversionTo_compute(type);
    if (state().inCircle()) {
      castingConversionTo_TypeDecl_values.put(_parameters, castingConversionTo_TypeDecl_value);
      castingConversionTo_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      castingConversionTo_TypeDecl_values.put(_parameters, castingConversionTo_TypeDecl_value);
      castingConversionTo_TypeDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return castingConversionTo_TypeDecl_value;
  }
  /** @apilevel internal */
  private boolean castingConversionTo_compute(TypeDecl type) {
      if (refined_Generics_InterfaceDecl_castingConversionTo_TypeDecl(type)) {
        return true;
      }
      boolean canUnboxThis = !unboxed().isUnknown();
      boolean canUnboxType = !type.unboxed().isUnknown();
      if (canUnboxThis && !canUnboxType) {
        return unboxed().wideningConversionTo(type);
      }
      return false;
    }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:228
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:227")
  public boolean isInterfaceDecl() {
    boolean isInterfaceDecl_value = true;
    return isInterfaceDecl_value;
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
        new_subtype_TypeDecl_value = type.supertypeInterfaceDecl(this);
        if (((Boolean)_value.value) != new_subtype_TypeDecl_value) {
          state.setChangeInCycle();
          _value.value = new_subtype_TypeDecl_value;
        }
      } while (state.testAndClearChangeInCycle());
      subtype_TypeDecl_values.put(_parameters, new_subtype_TypeDecl_value);
      state.startLastCycle();
      boolean $tmp = type.supertypeInterfaceDecl(this);

      state.leaveCircle();
      return new_subtype_TypeDecl_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_subtype_TypeDecl_value = type.supertypeInterfaceDecl(this);
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
   * @aspect Subtyping
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:471
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Subtyping", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:471")
  public boolean supertypeClassDecl(ClassDecl type) {
    {
        if (super.supertypeClassDecl(type)) {
          return true;
        }
        for (InterfaceDecl iface : type.superInterfaces()) {
          if (iface.subtype(this)) {
            return true;
          }
        }
        return type.hasSuperclass() && type.superclass().subtype(this);
      }
  }
  /**
   * @attribute syn
   * @aspect Subtyping
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:486
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Subtyping", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:486")
  public boolean supertypeInterfaceDecl(InterfaceDecl type) {
    {
        if (super.supertypeInterfaceDecl(type)) {
          return true;
        }
        for (InterfaceDecl superinterface : type.superInterfaces()) {
          if (superinterface.subtype(this)) {
            return true;
          }
        }
        return false;
      }
  }
  /**
   * @attribute syn
   * @aspect Subtyping
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:500
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Subtyping", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:500")
  public boolean supertypeArrayDecl(ArrayDecl type) {
    {
        if (super.supertypeArrayDecl(type)) {
          return true;
        }
        for (InterfaceDecl iface : type.superInterfaces()) {
          if (iface.subtype(this)) {
            return true;
          }
        }
        return false;
      }
  }
  /**
   * @attribute syn
   * @aspect SuperClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:731
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SuperClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:729")
  public TypeDecl supertype() {
    TypeDecl supertype_value = typeObject();
    return supertype_value;
  }
  /** @apilevel internal */
  private void superInterfaces_reset() {
    superInterfaces_computed = null;
    superInterfaces_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle superInterfaces_computed = null;

  /** @apilevel internal */
  protected Collection<InterfaceDecl> superInterfaces_value;

  /**
   * @attribute syn
   * @aspect SuperClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:756
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SuperClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:738")
  public Collection<InterfaceDecl> superInterfaces() {
    ASTState state = state();
    if (superInterfaces_computed == ASTState.NON_CYCLE || superInterfaces_computed == state().cycle()) {
      return superInterfaces_value;
    }
    superInterfaces_value = superInterfaces_compute();
    if (state().inCircle()) {
      superInterfaces_computed = state().cycle();
    
    } else {
      superInterfaces_computed = ASTState.NON_CYCLE;
    
    }
    return superInterfaces_value;
  }
  /** @apilevel internal */
  private Collection<InterfaceDecl> superInterfaces_compute() {
      Collection<InterfaceDecl> interfaces = new ArrayList<InterfaceDecl>();
      for (Access access : getSuperInterfaceList()) {
        TypeDecl implemented = access.type();
        if (implemented.isInterfaceDecl()) {
          // It is an error if implemented is not an interface (error check exists).
          interfaces.add((InterfaceDecl) implemented);
        }
      }
      return interfaces;
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
        boolean new_isCircular_value = isCircular_compute();
        if (isCircular_value != new_isCircular_value) {
          state.setChangeInCycle();
        }
        isCircular_value = new_isCircular_value;
      } while (state.testAndClearChangeInCycle());
      isCircular_computed = true;
      state.startLastCycle();
      boolean $tmp = isCircular_compute();

      state.leaveCircle();
    } else if (isCircular_cycle != state.cycle()) {
      isCircular_cycle = state.cycle();
      if (state.lastCycle()) {
        isCircular_computed = true;
        boolean new_isCircular_value = isCircular_compute();
        return new_isCircular_value;
      }
      boolean new_isCircular_value = isCircular_compute();
      if (isCircular_value != new_isCircular_value) {
        state.setChangeInCycle();
      }
      isCircular_value = new_isCircular_value;
    } else {
    }
    return isCircular_value;
  }
  /** @apilevel internal */
  private boolean isCircular_compute() {
      for (int i = 0; i < getNumSuperInterface(); i++) {
        Access a = getSuperInterface(i).lastAccess();
        while (a != null) {
          if (a.type().isCircular()) {
            return true;
          }
          a = (a.isQualified() && a.qualifier().isTypeAccess()) ? (Access) a.qualifier() : null;
        }
      }
      return false;
    }
  /** @apilevel internal */
  private void erasedAncestorMethodsMap_reset() {
    erasedAncestorMethodsMap_computed = null;
    erasedAncestorMethodsMap_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle erasedAncestorMethodsMap_computed = null;

  /** @apilevel internal */
  protected Map<String, SimpleSet<MethodDecl>> erasedAncestorMethodsMap_value;

  /**
   * @attribute syn
   * @aspect GenericsTypeCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:565
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsTypeCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:540")
  public Map<String, SimpleSet<MethodDecl>> erasedAncestorMethodsMap() {
    ASTState state = state();
    if (erasedAncestorMethodsMap_computed == ASTState.NON_CYCLE || erasedAncestorMethodsMap_computed == state().cycle()) {
      return erasedAncestorMethodsMap_value;
    }
    erasedAncestorMethodsMap_value = erasedAncestorMethodsMap_compute();
    if (state().inCircle()) {
      erasedAncestorMethodsMap_computed = state().cycle();
    
    } else {
      erasedAncestorMethodsMap_computed = ASTState.NON_CYCLE;
    
    }
    return erasedAncestorMethodsMap_value;
  }
  /** @apilevel internal */
  private Map<String, SimpleSet<MethodDecl>> erasedAncestorMethodsMap_compute() {
      Map<String, SimpleSet<MethodDecl>> localMap = localMethodsSignatureMap();
      Map<String, SimpleSet<MethodDecl>> map = new HashMap<String, SimpleSet<MethodDecl>>(localMap);
      for (MethodDecl m : interfacesMethods()) {
        if (m.accessibleFrom(this) && m.erasedMethod() != m) {
          String erasedSignature = m.erasedMethod().signature();
          if (!localMap.containsKey(erasedSignature)) {
            // Map erased signature to substituted method.
            putSimpleSetElement(map, m.erasedMethod().signature(), m);
          }
        }
      }
      for (MethodDecl m : typeObject().methods()) {
        if (m.isPublic() && m.erasedMethod() != m) {
          String erasedSignature = m.erasedMethod().signature();
          if (!localMap.containsKey(erasedSignature)) {
            // Map erased signature to substituted method.
            putSimpleSetElement(map, m.erasedMethod().signature(), m);
          }
        }
      }
      return map;
    }
  /** @apilevel internal */
  private void implementedInterfaces_reset() {
    implementedInterfaces_computed = null;
    implementedInterfaces_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle implementedInterfaces_computed = null;

  /** @apilevel internal */
  protected Collection<InterfaceDecl> implementedInterfaces_value;

  /**
   * @attribute syn
   * @aspect GenericsTypeCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:659
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsTypeCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:645")
  public Collection<InterfaceDecl> implementedInterfaces() {
    ASTState state = state();
    if (implementedInterfaces_computed == ASTState.NON_CYCLE || implementedInterfaces_computed == state().cycle()) {
      return implementedInterfaces_value;
    }
    implementedInterfaces_value = implementedInterfaces_compute();
    if (state().inCircle()) {
      implementedInterfaces_computed = state().cycle();
    
    } else {
      implementedInterfaces_computed = ASTState.NON_CYCLE;
    
    }
    return implementedInterfaces_value;
  }
  /** @apilevel internal */
  private Collection<InterfaceDecl> implementedInterfaces_compute() {
      HashSet<InterfaceDecl> set = new HashSet<InterfaceDecl>();
      set.addAll(typeObject().implementedInterfaces());
      for (InterfaceDecl decl : superInterfaces()) {
        set.add(decl);
        set.addAll(decl.implementedInterfaces());
      }
      return set;
    }
  /** @apilevel internal */
  private void iterableElementType_reset() {
    iterableElementType_computed = null;
    iterableElementType_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle iterableElementType_computed = null;

  /** @apilevel internal */
  protected TypeDecl iterableElementType_value;

  /**
   * @attribute syn
   * @aspect EnhancedFor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/EnhancedFor.jrag:95
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="EnhancedFor", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/EnhancedFor.jrag:77")
  public TypeDecl iterableElementType() {
    ASTState state = state();
    if (iterableElementType_computed == ASTState.NON_CYCLE || iterableElementType_computed == state().cycle()) {
      return iterableElementType_value;
    }
    iterableElementType_value = iterableElementType_compute();
    if (state().inCircle()) {
      iterableElementType_computed = state().cycle();
    
    } else {
      iterableElementType_computed = ASTState.NON_CYCLE;
    
    }
    return iterableElementType_value;
  }
  /** @apilevel internal */
  private TypeDecl iterableElementType_compute() {
      TypeDecl type = super.iterableElementType();
      if (!type.isUnknown()) {
        return type;
      } else {
        for (Access iface : getSuperInterfaceList()) {
          type = iface.type().iterableElementType();
          if (!type.isUnknown()) {
            break;
          }
        }
        return type;
      }
    }
  /** @apilevel internal */
  private void hasAnnotationFunctionalInterface_reset() {
    hasAnnotationFunctionalInterface_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle hasAnnotationFunctionalInterface_computed = null;

  /** @apilevel internal */
  protected boolean hasAnnotationFunctionalInterface_value;

  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/Annotations.jrag:29
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/Annotations.jrag:29")
  public boolean hasAnnotationFunctionalInterface() {
    ASTState state = state();
    if (hasAnnotationFunctionalInterface_computed == ASTState.NON_CYCLE || hasAnnotationFunctionalInterface_computed == state().cycle()) {
      return hasAnnotationFunctionalInterface_value;
    }
    hasAnnotationFunctionalInterface_value = getModifiers().hasAnnotationFunctionalInterface();
    if (state().inCircle()) {
      hasAnnotationFunctionalInterface_computed = state().cycle();
    
    } else {
      hasAnnotationFunctionalInterface_computed = ASTState.NON_CYCLE;
    
    }
    return hasAnnotationFunctionalInterface_value;
  }
  /** @apilevel internal */
  private void isFunctionalInterface_reset() {
    isFunctionalInterface_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isFunctionalInterface_computed = null;

  /** @apilevel internal */
  protected boolean isFunctionalInterface_value;

  /**
   * @attribute syn
   * @aspect FunctionalInterface
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:31
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FunctionalInterface", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:30")
  public boolean isFunctionalInterface() {
    ASTState state = state();
    if (isFunctionalInterface_computed == ASTState.NON_CYCLE || isFunctionalInterface_computed == state().cycle()) {
      return isFunctionalInterface_value;
    }
    isFunctionalInterface_value = isFunctional();
    if (state().inCircle()) {
      isFunctionalInterface_computed = state().cycle();
    
    } else {
      isFunctionalInterface_computed = ASTState.NON_CYCLE;
    
    }
    return isFunctionalInterface_value;
  }
  /** @apilevel internal */
  private void isFunctional_reset() {
    isFunctional_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isFunctional_computed = null;

  /** @apilevel internal */
  protected boolean isFunctional_value;

  /**
   * @attribute syn
   * @aspect FunctionalInterface
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:286
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FunctionalInterface", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:33")
  public boolean isFunctional() {
    ASTState state = state();
    if (isFunctional_computed == ASTState.NON_CYCLE || isFunctional_computed == state().cycle()) {
      return isFunctional_value;
    }
    isFunctional_value = isFunctional_compute();
    if (state().inCircle()) {
      isFunctional_computed = state().cycle();
    
    } else {
      isFunctional_computed = ASTState.NON_CYCLE;
    
    }
    return isFunctional_value;
  }
  /** @apilevel internal */
  private boolean isFunctional_compute() {
      java.util.List<MethodDecl> methods = collectAbstractMethods();
      boolean foundMethod = false;
  
      if (methods.isEmpty()) {
        return false;
      } else if (methods.size() == 1) {
        return true;
      } else {
        for (MethodDecl current : methods) {
          foundMethod = true;
          for (MethodDecl inner : methods) {
            if (!current.subsignatureTo(inner) || !current.returnTypeSubstitutableFor(inner)) {
              foundMethod = false;
            }
          }
          if (foundMethod) {
            break;
          }
        }
      }
      return foundMethod;
    }
  /** @apilevel internal */
  private void collectAbstractMethods_reset() {
    collectAbstractMethods_computed = null;
    collectAbstractMethods_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle collectAbstractMethods_computed = null;

  /** @apilevel internal */
  protected java.util.List<MethodDecl> collectAbstractMethods_value;

  /**
   * Collects all abstract methods in an interface. Used to compute
   * a function type from a functional interface.
   * @attribute syn
   * @aspect FunctionalInterface
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:317
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FunctionalInterface", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:317")
  public java.util.List<MethodDecl> collectAbstractMethods() {
    ASTState state = state();
    if (collectAbstractMethods_computed == ASTState.NON_CYCLE || collectAbstractMethods_computed == state().cycle()) {
      return collectAbstractMethods_value;
    }
    collectAbstractMethods_value = collectAbstractMethods_compute();
    if (state().inCircle()) {
      collectAbstractMethods_computed = state().cycle();
    
    } else {
      collectAbstractMethods_computed = ASTState.NON_CYCLE;
    
    }
    return collectAbstractMethods_value;
  }
  /** @apilevel internal */
  private java.util.List<MethodDecl> collectAbstractMethods_compute() {
      java.util.List<MethodDecl> methods = new ArrayList<MethodDecl>();
      Map<String, SimpleSet<MethodDecl>> map = localMethodsSignatureMap();
      Map<String, SimpleSet<MethodDecl>> objectMethods = typeObject().methodsSignatureMap();
      MethodDecl inObject;
  
      for (SimpleSet<MethodDecl> set : map.values()) {
        MethodDecl m = set.iterator().next();
  
        SimpleSet<MethodDecl> objectSet = objectMethods.get(m.signature());
        if (m.isAbstract()) {
          if (objectSet == null || objectSet.isEmpty()) {
            methods.add(m);
          } else {
            inObject = objectSet.iterator().next();
            if (!inObject.isPublic()) {
              methods.add(m);
            }
          }
        }
      }
  
      for (InterfaceDecl iface : superInterfaces()) {
        for (MethodDecl m : iface.methods()) {
          if (m.isAbstract() && !m.isPrivate() && m.accessibleFrom(this)) {
            SimpleSet<MethodDecl> objectSet = objectMethods.get(m.signature());
            if (objectSet == null || objectSet.isEmpty()) {
              methods.add(m);
            } else {
              inObject = objectSet.iterator().next();
              if (!inObject.isPublic()) {
                methods.add(m);
              }
            }
          }
        }
      }
      return methods;
    }
  /** @apilevel internal */
  private void hasFunctionDescriptor_reset() {
    hasFunctionDescriptor_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle hasFunctionDescriptor_computed = null;

  /** @apilevel internal */
  protected boolean hasFunctionDescriptor_value;

  /**
   * @attribute syn
   * @aspect FunctionDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionDescriptor.jrag:80
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FunctionDescriptor", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionDescriptor.jrag:80")
  public boolean hasFunctionDescriptor() {
    ASTState state = state();
    if (hasFunctionDescriptor_computed == ASTState.NON_CYCLE || hasFunctionDescriptor_computed == state().cycle()) {
      return hasFunctionDescriptor_value;
    }
    hasFunctionDescriptor_value = hasFunctionDescriptor_compute();
    if (state().inCircle()) {
      hasFunctionDescriptor_computed = state().cycle();
    
    } else {
      hasFunctionDescriptor_computed = ASTState.NON_CYCLE;
    
    }
    return hasFunctionDescriptor_value;
  }
  /** @apilevel internal */
  private boolean hasFunctionDescriptor_compute() {
      return functionDescriptor() != null;
    }
  /** @apilevel internal */
  private void functionDescriptor_reset() {
    functionDescriptor_computed = null;
    functionDescriptor_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle functionDescriptor_computed = null;

  /** @apilevel internal */
  protected FunctionDescriptor functionDescriptor_value;

  /**
   * Builds a function type from this interface.
   * 
   * If the interface cannot be used to construct a valid function
   * type then {@code null} is returned.
   * @attribute syn
   * @aspect FunctionDescriptor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionDescriptor.jrag:91
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FunctionDescriptor", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionDescriptor.jrag:91")
  public FunctionDescriptor functionDescriptor() {
    ASTState state = state();
    if (functionDescriptor_computed == ASTState.NON_CYCLE || functionDescriptor_computed == state().cycle()) {
      return functionDescriptor_value;
    }
    functionDescriptor_value = functionDescriptor_compute();
    if (state().inCircle()) {
      functionDescriptor_computed = state().cycle();
    
    } else {
      functionDescriptor_computed = ASTState.NON_CYCLE;
    
    }
    return functionDescriptor_value;
  }
  /** @apilevel internal */
  private FunctionDescriptor functionDescriptor_compute() {
      java.util.List<MethodDecl> methods = collectAbstractMethods();
  
      if (methods.isEmpty()) {
        // No abstract method in this interface.
        return null;
      } else if (methods.size() == 1) {
        MethodDecl m = methods.get(0);
        ArrayList<TypeDecl> throwsList = new ArrayList<TypeDecl>();
        for (Access exception : m.getExceptionList()) {
          throwsList.add(exception.type());
        }
        return new FunctionDescriptor(this, m, throwsList);
      } else {
        MethodDecl foundMethod = null;
  
        for (MethodDecl current : methods) {
          foundMethod = current;
          for (MethodDecl inner : methods) {
            if (!current.subsignatureTo(inner) || !current.returnTypeSubstitutableFor(inner)) {
              foundMethod = null;
            }
          }
          if (foundMethod != null) {
            break;
          }
        }
  
        if (foundMethod == null) {
          return null;
        }
  
        // Now the throws-list needs to be computed as stated in 9.8.
        ArrayList<Access> descriptorThrows = new ArrayList<Access>();
        for (MethodDecl current : methods) {
          for (Access exception : current.getExceptionList()) {
            boolean alreadyInserted = false;
            for (Access found : descriptorThrows) {
              if (found.sameType(exception)) {
                alreadyInserted = true;
                break;
              }
            }
            if (alreadyInserted) {
              continue;
            }
  
            boolean foundIncompatibleClause = false;
            // Has to be the subtype to at least one exception in each clause.
            if (foundMethod.isGeneric()) {
              for (MethodDecl inner : methods) {
                if (!inner.subtypeThrowsClause(exception)) {
                  foundIncompatibleClause = true;
                  break;
                }
              }
            } else {
              for (MethodDecl inner : methods) {
                if (!inner.subtypeThrowsClauseErased(exception)) {
                  foundIncompatibleClause = true;
                  break;
                }
              }
            }
  
            if (!foundIncompatibleClause) {
              // Was subtype to one exception in every clause
              descriptorThrows.add(exception);
            }
          }
        }
  
        // Found a suitable method and finished building throws-list,
        // now the descriptor just needs to be put together.
        if (descriptorThrows.size() == 0) {
          return new FunctionDescriptor(this, foundMethod, Collections.<TypeDecl>emptyList());
        } else {
          ArrayList<TypeDecl> throwsList = new ArrayList<TypeDecl>();
  
          // All type variables must be replaced with foundMethods
          // type variables if the descriptor is generic.
          if (foundMethod.isGeneric()) {
            GenericMethodDecl foundGeneric = foundMethod.genericDecl();
            for (Access exception : descriptorThrows) {
              if (exception.type() instanceof TypeVariable) {
                TypeVariable foundVar = (TypeVariable) exception.type();
                TypeVariable original = foundGeneric.getTypeParameter(foundVar.typeVarPosition());
                throwsList.add(original);
              } else {
                throwsList.add(exception.type());
              }
            }
          } else {
            // All throwed types must be erased if the descriptor is not generic.
            for (Access exception : descriptorThrows) {
              throwsList.add(exception.type().erasure());
            }
          }
          return new FunctionDescriptor(this, foundMethod, throwsList);
        }
      }
    }
  /** @apilevel internal */
  private void hasOverridingMethodInSuper_MethodDecl_reset() {
    hasOverridingMethodInSuper_MethodDecl_computed = null;
    hasOverridingMethodInSuper_MethodDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map hasOverridingMethodInSuper_MethodDecl_values;
  /** @apilevel internal */
  protected java.util.Map hasOverridingMethodInSuper_MethodDecl_computed;
  /**
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:1201
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:1201")
  public boolean hasOverridingMethodInSuper(MethodDecl m) {
    Object _parameters = m;
    if (hasOverridingMethodInSuper_MethodDecl_computed == null) hasOverridingMethodInSuper_MethodDecl_computed = new java.util.HashMap(4);
    if (hasOverridingMethodInSuper_MethodDecl_values == null) hasOverridingMethodInSuper_MethodDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (hasOverridingMethodInSuper_MethodDecl_values.containsKey(_parameters)
        && hasOverridingMethodInSuper_MethodDecl_computed.containsKey(_parameters)
        && (hasOverridingMethodInSuper_MethodDecl_computed.get(_parameters) == ASTState.NON_CYCLE || hasOverridingMethodInSuper_MethodDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) hasOverridingMethodInSuper_MethodDecl_values.get(_parameters);
    }
    boolean hasOverridingMethodInSuper_MethodDecl_value = hasOverridingMethodInSuper_compute(m);
    if (state().inCircle()) {
      hasOverridingMethodInSuper_MethodDecl_values.put(_parameters, hasOverridingMethodInSuper_MethodDecl_value);
      hasOverridingMethodInSuper_MethodDecl_computed.put(_parameters, state().cycle());
    
    } else {
      hasOverridingMethodInSuper_MethodDecl_values.put(_parameters, hasOverridingMethodInSuper_MethodDecl_value);
      hasOverridingMethodInSuper_MethodDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return hasOverridingMethodInSuper_MethodDecl_value;
  }
  /** @apilevel internal */
  private boolean hasOverridingMethodInSuper_compute(MethodDecl m) {
      for (InterfaceDecl iface : superInterfaces()) {
        for (MethodDecl superMethod : iface.methods()) {
          if (m != superMethod && superMethod.overrides(m)) {
            return true;
          }
        }
      }
      return false;
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
        new_strictSubtype_TypeDecl_value = type.strictSupertypeInterfaceDecl(this);
        if (((Boolean)_value.value) != new_strictSubtype_TypeDecl_value) {
          state.setChangeInCycle();
          _value.value = new_strictSubtype_TypeDecl_value;
        }
      } while (state.testAndClearChangeInCycle());
      strictSubtype_TypeDecl_values.put(_parameters, new_strictSubtype_TypeDecl_value);
      state.startLastCycle();
      boolean $tmp = type.strictSupertypeInterfaceDecl(this);

      state.leaveCircle();
      return new_strictSubtype_TypeDecl_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_strictSubtype_TypeDecl_value = type.strictSupertypeInterfaceDecl(this);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:376
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:376")
  public boolean strictSupertypeClassDecl(ClassDecl type) {
    {
        if (super.strictSupertypeClassDecl(type)) {
          return true;
        }
        for (InterfaceDecl iface : type.superInterfaces()) {
          if (iface.strictSubtype(this)) {
            return true;
          }
        }
        return type.hasSuperclass() && type.superclass() != null
            && type.superclass().strictSubtype(this);
      }
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:395
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:395")
  public boolean strictSupertypeInterfaceDecl(InterfaceDecl type) {
    {
        if (super.strictSupertypeInterfaceDecl(type)) {
          return true;
        }
        for (InterfaceDecl superinterface : type.superInterfaces()) {
          if (superinterface.strictSubtype(this)) {
            return true;
          }
        }
        return false;
      }
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:411
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:411")
  public boolean strictSupertypeArrayDecl(ArrayDecl type) {
    {
        if (super.strictSupertypeArrayDecl(type)) {
          return true;
        }
        for (InterfaceDecl iface : type.superInterfaces()) {
          if (iface.strictSubtype(this)) {
            return true;
          }
        }
        return false;
      }
  }
  /**
   * @attribute syn
   * @aspect FilterUtils
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:31
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FilterUtils", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:21")
  public TypeDecl type2() {
    TypeDecl type2_value = this;
    return type2_value;
  }
  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:58
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:58")
  public ASTNode getNode() {
    ASTNode getNode_value = this;
    return getNode_value;
  }
  /**
   * @attribute syn
   * @aspect Utilities
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:297
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Utilities", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:285")
  public DeclarationSite getDeclaration() {
    DeclarationSite getDeclaration_value = this;
    return getDeclaration_value;
  }
  /**
   * @attribute syn
   * @aspect Collection
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Collection.jrag:4
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Collection", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Collection.jrag:3")
  public boolean isList() {
    boolean isList_value = getID().equals("List");
    return isList_value;
  }
  /**
   * @attribute syn
   * @aspect Collection
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Collection.jrag:26
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Collection", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Collection.jrag:25")
  public boolean isMap() {
    boolean isMap_value = getID().equals("Map");
    return isMap_value;
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
   * @aspect TypeConversion
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:114
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TypeConversion", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:114")
  public MethodDecl unknownMethod() {
    MethodDecl unknownMethod_value = getParent().Define_unknownMethod(this, null);
    return unknownMethod_value;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:408
   * @apilevel internal
   */
  public TypeDecl Define_declaredIn(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return this;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:408
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute declaredIn
   */
  protected boolean canDefine_declaredIn(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getSuperInterfaceListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:99
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return NameType.TYPE_NAME;
    }
    else {
      return super.Define_nameType(_callerNode, _childNode);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:76
   * @apilevel internal
   */
  public TypeDecl Define_hostType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getSuperInterfaceListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:693
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return hostType();
    }
    else {
      return super.Define_hostType(_callerNode, _childNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:76
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute hostType
   */
  protected boolean canDefine_hostType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SuppressWarnings.jrag:37
   * @apilevel internal
   */
  public boolean Define_withinSuppressWarnings(ASTNode _callerNode, ASTNode _childNode, String annot) {
    if (_callerNode == getSuperInterfaceListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:417
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return hasAnnotationSuppressWarnings(annot) || withinSuppressWarnings(annot);
    }
    else {
      return getParent().Define_withinSuppressWarnings(this, _callerNode, annot);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/SuppressWarnings.jrag:37
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute withinSuppressWarnings
   */
  protected boolean canDefine_withinSuppressWarnings(ASTNode _callerNode, ASTNode _childNode, String annot) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:533
   * @apilevel internal
   */
  public boolean Define_withinDeprecatedAnnotation(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getSuperInterfaceListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:544
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return isDeprecated() || withinDeprecatedAnnotation();
    }
    else {
      return super.Define_withinDeprecatedAnnotation(_callerNode, _childNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:533
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute withinDeprecatedAnnotation
   */
  protected boolean canDefine_withinDeprecatedAnnotation(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:385
   * @apilevel internal
   */
  public boolean Define_inExtendsOrImplements(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getSuperInterfaceListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:384
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return true;
    }
    else {
      return getParent().Define_inExtendsOrImplements(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:385
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inExtendsOrImplements
   */
  protected boolean canDefine_inExtendsOrImplements(ASTNode _callerNode, ASTNode _childNode) {
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
  protected void collect_contributors_TypeDecl_subtypes(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:39
    {
      TypeDecl target = (TypeDecl) (supertype());
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:40
    for (TypeDecl target : (Iterable<? extends TypeDecl>) (superInterfacesTarget())) {
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_TypeDecl_subtypes(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_TypeDecl_supertypes(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:48
    for (TypeDecl target : (Iterable<? extends TypeDecl>) (subtypes())) {
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_TypeDecl_supertypes(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_Program_uniqueTypes(Program _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:156
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_Program_uniqueTypes(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_CompilationUnit_problems(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AccessControl.jrag:213
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:522
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
  protected void contributeTo_TypeDecl_subtypes(Set<TypeDecl> collection) {
    super.contributeTo_TypeDecl_subtypes(collection);
    collection.addAll(subtypesIncludingSelf());
    collection.addAll(subtypesIncludingSelf());
  }
  /** @apilevel internal */
  protected void contributeTo_TypeDecl_supertypes(Set<TypeDecl> collection) {
    super.contributeTo_TypeDecl_supertypes(collection);
    collection.add(this);
  }
  /** @apilevel internal */
  protected void contributeTo_Program_uniqueTypes(Set<TypeDecl> collection) {
    super.contributeTo_Program_uniqueTypes(collection);
    collection.add(this);
  }
  /** @apilevel internal */
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    for (Problem value : accessControlProblems()) {
      collection.add(value);
    }
    for (Problem value : typeHierarchyProblems()) {
      collection.add(value);
    }
  }

}
