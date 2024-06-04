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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:180
 * @astdecl ClassDecl : ReferenceType ::= Modifiers <ID:String> [SuperClass:Access] Implements:Access* BodyDecl* [ImplicitConstructor:ConstructorDecl];
 * @production ClassDecl : {@link ReferenceType} ::= <span class="component">{@link Modifiers}</span> <span class="component">&lt;ID:{@link String}&gt;</span> <span class="component">[SuperClass:{@link Access}]</span> <span class="component">Implements:{@link Access}*</span> <span class="component">{@link BodyDecl}*</span> <span class="component">[ImplicitConstructor:{@link ConstructorDecl}]</span>;

 */
public class ClassDecl extends ReferenceType implements Cloneable, DeclarationSite {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrint.jadd:147
   */
  public void prettyPrint(PrettyPrinter out) {
    if (hasDocComment()) {
      out.print(docComment());
    }
    if (!out.isNewLine()) {
      out.println();
    }
    out.print(getModifiers());
    out.print("class ");
    out.print(getID());
    if (hasSuperClass()) {
      out.print(" extends ");
      out.print(getSuperClass());
    }
    if (hasImplements()) {
      out.print(" implements ");
      out.join(getImplementss(), new PrettyPrinter.Joiner() {
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
   * Check compatibility of interface method and superclass method.
   * @param m interface method
   * @param n superclass method
   * @aspect TypeHierarchyCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:481
   */
  private void interfaceMethodCompatibleWithInherited(Collection<Problem> problems,
      MethodDecl m, MethodDecl n) {
    if (n.isAbstract()) {
      checkAbstractMethodDecls(problems, m, n);
    }
    if (n.isStatic()) {
      problems.add(error("Xa static method may not hide an instance method"));
    }
    if (!n.isAbstract() && !n.isPublic()) {
      problems.add(errorf("Xoverriding access modifier error for %s in %s and %s",
          m.fullSignature(), m.hostType().typeName(), n.hostType().typeName()));
    }
    if (!n.mayOverride(m) && !m.mayOverride(m)) {
      problems.add(errorf("Xthe return type of method %s in %s does not match"
          + " the return type of method %s in %s and may thus not be overridden",
          m.fullSignature(), m.hostType().typeName(), n.fullSignature(), n.hostType().typeName()));
    }

    if (!n.isAbstract()) {
      // If n implements and overrides method m in the interface then it
      // may not throw more checked exceptions.
      for (Access e: n.getExceptionList()) {
        if (e.type().isCheckedException()) {
          boolean found = false;
          for (Access declException: m.getExceptionList()) {
            if (e.type().subtype(declException.type())) {
              found = true;
              break;
            }
          }
          if (!found) {
            problems.add(errorf(
                "%s in %s may not throw more checked exceptions than overridden method %s in %s",
                n.fullSignature(), n.hostType().typeName(), m.fullSignature(),
                m.hostType().typeName()));
          }
        }
      }
    }
  }
  /**
   * @aspect SuperClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:707
   */
  public boolean hasSuperclass() {
    return !isObject();
  }
  /**
   * @aspect SuperClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:711
   */
  public TypeDecl superclass() {
    if (isObject()) {
      return unknownType();
    } else if (hasSuperClass()) {
      return getSuperClass().type();
    } else {
      return typeObject();
    }
  }
  /**
   * @aspect Generics
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:272
   */
  public TypeDecl makeGeneric(Signatures.ClassSignature s) {
    // NOTE: we are overwriting List- and Opt- children here using setSuperClassOpt
    // and setImplementsList. This is dangerous since those children are in some
    // cases NTAs, and we should not use set methods to try to overwrite NTA values.
    // However, we have to do this here in order to not trigger rewrites that in
    // turn need to access certain (inherited) lookup attributes, and we are reasonably
    // sure that we are in fact not overwriting NTA children. We exclude EnumDecl here
    // because its [SuperClass] and Implements* are in fact NTAs.
    // /Jesper 2015-01-22
    if (this instanceof EnumDecl) {
      return this; // Enum superclass and superinterfaces are NTAs.
    }
    if (s.hasFormalTypeParameters()) {
      ASTNode node = getParent();
      int index = node.getIndexOfChild(this);
      node.setChild(
          new GenericClassDecl(
              getModifiersNoTransform(),
              getID(),
              s.hasSuperclassSignature()
                  ? new Opt(s.superclassSignature())
                  : getSuperClassOptNoTransform(),
              s.hasSuperinterfaceSignature()
                  ? s.superinterfaceSignature()
                  : getImplementsListNoTransform(),
              getBodyDeclListNoTransform(),
              s.typeParameters()),
          index);
      return (TypeDecl) node.getChildNoTransform(index);
    } else {
      if (s.hasSuperclassSignature()) {
        setSuperClassOpt(new Opt(s.superclassSignature()));
      }
      if (s.hasSuperinterfaceSignature()) {
        setImplementsList(s.superinterfaceSignature());
      }
      return this;
    }
  }
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1515
   */
  public ClassDecl signatureCopy() {
    return new ClassDeclSubstituted(
        getModifiers().treeCopyNoTransform(),
        getID(),
        getSuperClassOpt().treeCopyNoTransform(),
        getImplementsList().treeCopyNoTransform(),
        this);
  }
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1605
   */
  public ClassDecl erasedCopy() {
    return new ClassDeclErased(
        getModifiers().treeCopyNoTransform(),
        getID(),
        hasSuperClass() ? new Opt<Access>(getSuperClass().erasedCopy()) : new Opt<Access>(),
        erasedAccessList(getImplementsList()),
        this);
  }
  /**
   * @declaredat ASTNode:1
   */
  public ClassDecl() {
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
    setChild(new Opt(), 1);
    setChild(new List(), 2);
    setChild(new List(), 3);
    setChild(new Opt(), 4);
  }
  /**
   * @declaredat ASTNode:17
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Modifiers", "ID", "SuperClass", "Implements", "BodyDecl"},
    type = {"Modifiers", "String", "Opt<Access>", "List<Access>", "List<BodyDecl>"},
    kind = {"Child", "Token", "Opt", "List", "List"}
  )
  public ClassDecl(Modifiers p0, String p1, Opt<Access> p2, List<Access> p3, List<BodyDecl> p4) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
    setChild(p4, 3);
  }
  /**
   * @declaredat ASTNode:29
   */
  public ClassDecl(Modifiers p0, beaver.Symbol p1, Opt<Access> p2, List<Access> p3, List<BodyDecl> p4) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
    setChild(p4, 3);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:37
   */
  protected int numChildren() {
    return 4;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:43
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:47
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    supertypeTarget_reset();
    superInterfacesTarget_reset();
    getInstanceInitailizer_reset();
    getStaticInitailizer_reset();
    constructors_reset();
    getImplicitConstructorOpt_reset();
    unimplementedMethods_reset();
    hasAbstract_reset();
    memberFieldsMap_reset();
    memberFields_String_reset();
    methods_reset();
    ancestorMethods_String_reset();
    castingConversionTo_TypeDecl_reset();
    isString_reset();
    isObject_reset();
    subtype_TypeDecl_reset();
    superInterfaces_reset();
    isCircular_reset();
    erasedAncestorMethodsMap_reset();
    implementedInterfaces_reset();
    iterableElementType_reset();
    hasOverridingMethodInSuper_MethodDecl_reset();
    strictSubtype_TypeDecl_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:74
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
    ClassDecl_instantiations_computed = null;
    ClassDecl_instantiations_value = null;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:80
   */
  public ClassDecl clone() throws CloneNotSupportedException {
    ClassDecl node = (ClassDecl) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:85
   */
  public ClassDecl copy() {
    try {
      ClassDecl node = (ClassDecl) clone();
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
   * @declaredat ASTNode:104
   */
  @Deprecated
  public ClassDecl fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:114
   */
  public ClassDecl treeCopyNoTransform() {
    ClassDecl tree = (ClassDecl) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 4:
          tree.children[i] = new Opt();
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
   * @declaredat ASTNode:139
   */
  public ClassDecl treeCopy() {
    ClassDecl tree = (ClassDecl) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 4:
          tree.children[i] = new Opt();
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
  public ClassDecl setModifiers(Modifiers node) {
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
  public ClassDecl setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public ClassDecl setID(beaver.Symbol symbol) {
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
   * Replaces the optional node for the SuperClass child. This is the <code>Opt</code>
   * node containing the child SuperClass, not the actual child!
   * @param opt The new node to be used as the optional node for the SuperClass child.
   * @apilevel low-level
   */
  public ClassDecl setSuperClassOpt(Opt<Access> opt) {
    setChild(opt, 1);
    return this;
  }
  /**
   * Replaces the (optional) SuperClass child.
   * @param node The new node to be used as the SuperClass child.
   * @apilevel high-level
   */
  public ClassDecl setSuperClass(Access node) {
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
   * Retrieves the optional node for the SuperClass child. This is the <code>Opt</code> node containing the child SuperClass, not the actual child!
   * @return The optional node for child the SuperClass child.
   * @apilevel low-level
   */
  @ASTNodeAnnotation.OptChild(name="SuperClass")
  public Opt<Access> getSuperClassOpt() {
    return (Opt<Access>) getChild(1);
  }
  /**
   * Retrieves the optional node for child SuperClass. This is the <code>Opt</code> node containing the child SuperClass, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child SuperClass.
   * @apilevel low-level
   */
  public Opt<Access> getSuperClassOptNoTransform() {
    return (Opt<Access>) getChildNoTransform(1);
  }
  /**
   * Replaces the Implements list.
   * @param list The new list node to be used as the Implements list.
   * @apilevel high-level
   */
  public ClassDecl setImplementsList(List<Access> list) {
    setChild(list, 2);
    return this;
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
  public ClassDecl addImplements(Access node) {
    List<Access> list = (parent == null) ? getImplementsListNoTransform() : getImplementsList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public ClassDecl addImplementsNoTransform(Access node) {
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
  public ClassDecl setImplements(Access node, int i) {
    List<Access> list = getImplementsList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the Implements list.
   * @return The node representing the Implements list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Implements")
  public List<Access> getImplementsList() {
    List<Access> list = (List<Access>) getChild(2);
    return list;
  }
  /**
   * Retrieves the Implements list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Implements list.
   * @apilevel low-level
   */
  public List<Access> getImplementsListNoTransform() {
    return (List<Access>) getChildNoTransform(2);
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
   * Replaces the BodyDecl list.
   * @param list The new list node to be used as the BodyDecl list.
   * @apilevel high-level
   */
  public ClassDecl setBodyDeclList(List<BodyDecl> list) {
    setChild(list, 3);
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
  public ClassDecl addBodyDecl(BodyDecl node) {
    List<BodyDecl> list = (parent == null) ? getBodyDeclListNoTransform() : getBodyDeclList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public ClassDecl addBodyDeclNoTransform(BodyDecl node) {
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
  public ClassDecl setBodyDecl(BodyDecl node, int i) {
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
    List<BodyDecl> list = (List<BodyDecl>) getChild(3);
    return list;
  }
  /**
   * Retrieves the BodyDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the BodyDecl list.
   * @apilevel low-level
   */
  public List<BodyDecl> getBodyDeclListNoTransform() {
    return (List<BodyDecl>) getChildNoTransform(3);
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
  public ClassDecl setImplicitConstructor(ConstructorDecl node) {
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
    return (Opt<ConstructorDecl>) getChildNoTransform(4);
  }
  /**
   * Retrieves the child position of the optional child ImplicitConstructor.
   * @return The the child position of the optional child ImplicitConstructor.
   * @apilevel low-level
   */
  protected int getImplicitConstructorOptChildPosition() {
    return 4;
  }
  /**
   * @aspect ConstructorLookup
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:149
   */
  private Collection<ConstructorDecl> refined_ConstructorLookup_ClassDecl_constructors()
{
    Collection<ConstructorDecl> c = new ArrayList<ConstructorDecl>(super.constructors());
    if (hasImplicitConstructor()) {
      c.add(getImplicitConstructor());
    }
    return c;
  }
  /**
   * @aspect TypeHierarchyCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:368
   */
  private Collection<Problem> refined_TypeHierarchyCheck_ClassDecl_typeProblems()
{
    Collection<Problem> problems = new LinkedList<Problem>(super.typeProblems());

    // Check if methods inherited from interfaces are compatible with the
    // overriding method in this class or method inherited from ancestor class.
    for (MethodDecl decl : interfacesMethods()) {
      // First we check locally declared methods, then if no local method
      // overrides the interface method we check ancestor methods.  We check
      // ancestor types in superclass order and stop at the first overriding method.
      boolean overridden = false;
      ClassDecl hostType = this;
      while (!overridden) {
        for (MethodDecl m : hostType.localMethodsSignature(decl.signature())) {
          if (m.overrideCandidate(decl)) {
            overridden = true;
            if (!m.mayOverride(decl)) {
              problems.add(errorf(
                  "the return type of method %s in %s does not match the return type of"
                  + " method %s in %s and may thus not be overridden",
                  m.fullSignature(), hostType.typeName(), decl.fullSignature(),
                  decl.hostType().typeName()));
            }
          }
        }
        if (!hostType.hasSuperclass()) {
          break;
        }
        hostType = (ClassDecl) hostType.superclass();
      }
    }
    return problems;
  }
  /**
   * @aspect TypeHierarchyCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:403
   */
  private Collection<Problem> refined_TypeHierarchyCheck_ClassDecl_nameProblems()
{
    Collection<Problem> problems = new LinkedList<Problem>(super.nameProblems());
    if (hasSuperClass() && !getSuperClass().type().isClassDecl()) {
      problems.add(errorf("a class may only inherit another class, which %s is not",
          getSuperClass().type().typeName()));
    }
    if (isObject() && hasSuperClass()) {
      problems.add(error("class Object may not have a superclass"));
    }
    if (isObject() && getNumImplements() != 0) {
      problems.add(error("class Object may not implement an interface"));
    }

    // 8.1.3
    if (isCircular()) {
      problems.add(errorf("circular inheritance dependency in %s", typeName()));
    }

    // 8.1.4
    Collection<TypeDecl> set = new HashSet<TypeDecl>();
    for (int i = 0; i < getNumImplements(); i++) {
      TypeDecl decl = getImplements(i).type();
      if (!decl.isInterfaceDecl() && !decl.isUnknown()) {
        problems.add(errorf("type %s cannot implement the non-interface type %s",
            fullName(), decl.fullName()));
      }
      if (set.contains(decl)) {
        problems.add(errorf("type %s is mentionened multiple times in implements clause",
            decl.fullName()));
      }
      set.add(decl);
    }

    for (MethodDecl m : interfacesMethods()) {
      if (localMethodsSignature(m.signature()).isEmpty()) {
        SimpleSet<MethodDecl> s = superclass().methodsSignature(m.signature());
        for (MethodDecl n : s) {
          if (n.accessibleFrom(this)) {
            interfaceMethodCompatibleWithInherited(problems, m, n);
          }
        }
        if (s.isEmpty()) {
          for (MethodDecl n : interfacesMethodsSignature(m.signature())) {
            // TODO(joqvist): don't report this error twice.
            checkAbstractMethodDecls(problems, m, n);
          }
        }
      }
    }

    // Check if there is a matching accessible super constructor for the default constructor.
    if (hasImplicitConstructor()) {
      Stmt superCall = getImplicitConstructor().getParsedConstructorInvocation();
      SuperConstructorAccess superAccess =
          (SuperConstructorAccess) ((ExprStmt) superCall).getExpr();
      if (superAccess.decls().isEmpty()) {
        problems.add(errorf(
            "cannot generate default constructor for class %s because "
            + "superclass %s has no accessible zero-argument constructor",
            name(), superclass().name()));
      } else if (!isAnonymous() && superclass().isInnerType()
          && !hasEnclosingType(superclass().enclosingType())) {
        problems.add(errorf(
            "cannot generate default constructor for class %s because its "
            + "superclass %s is an inner type of %s but %s is not an inner type of %s or a "
            + "subtype thereof",
            name(), superclass().typeName(), superclass().enclosingType().name(),
            name(), superclass().enclosingType().name()));
      }
    }
    return problems;
  }
  /**
   * @aspect TypeConversion
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:104
   */
  private boolean refined_TypeConversion_ClassDecl_castingConversionTo_TypeDecl(TypeDecl type)
{
    if (type.isArrayDecl()) {
      return isObject();
    } else if (type.isClassDecl()) {
      return this == type || subtype(type) || type.subtype(this);
    } else if (type.isInterfaceDecl()) {
      return !isFinal() || subtype(type);
    } else return super.castingConversionTo(type);
  }
  /**
   * @aspect Generics
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:120
   */
  private boolean refined_Generics_ClassDecl_castingConversionTo_TypeDecl(TypeDecl type)
{
    TypeDecl S = this;
    TypeDecl T = type;
    if (T instanceof TypeVariable) {
      TypeVariable t = (TypeVariable) T;
      if (t.getNumTypeBound() == 0) {
        return true;
      }
      for (int i = 0; i < t.getNumTypeBound(); i++) {
        if (castingConversionTo(t.getTypeBound(i).type())) {
          return true;
        }
      }
      return false;
    }
    if (S.erasure() != S || T.erasure() != T) {
      return S.erasure().castingConversionTo(T.erasure());
    }
    return refined_TypeConversion_ClassDecl_castingConversionTo_TypeDecl(type);
  }
  /** @apilevel internal */
  private void supertypeTarget_reset() {
    supertypeTarget_computed = null;
    supertypeTarget_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle supertypeTarget_computed = null;

  /** @apilevel internal */
  protected TypeDecl supertypeTarget_value;

  /**
   * Returns the generc supertype target of a ClassDecl.
   * @attribute syn
   * @aspect CHA
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:76
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CHA", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:76")
  public TypeDecl supertypeTarget() {
    ASTState state = state();
    if (supertypeTarget_computed == ASTState.NON_CYCLE || supertypeTarget_computed == state().cycle()) {
      return supertypeTarget_value;
    }
    supertypeTarget_value = supertypeTarget_compute();
    if (state().inCircle()) {
      supertypeTarget_computed = state().cycle();
    
    } else {
      supertypeTarget_computed = ASTState.NON_CYCLE;
    
    }
    return supertypeTarget_value;
  }
  /** @apilevel internal */
  private TypeDecl supertypeTarget_compute() {
      if (supertype().isParDecl()) {
        return ((ParClassDecl)supertype()).genericDecl();
      } else if (supertype().isRawDecl()) {
        return ((RawClassDecl)supertype()).genericDecl();
      } else {
        return superclass();
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:89
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CHA", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:89")
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
  private void getInstanceInitailizer_reset() {
    getInstanceInitailizer_computed = null;
    getInstanceInitailizer_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle getInstanceInitailizer_computed = null;

  /** @apilevel internal */
  protected java.util.List<InstanceInitializer> getInstanceInitailizer_value;

  /**
   * Returns a list of instance initializers declared within the class.
   * 
   * @return A list of instance initializers.
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:376
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:376")
  public java.util.List<InstanceInitializer> getInstanceInitailizer() {
    ASTState state = state();
    if (getInstanceInitailizer_computed == ASTState.NON_CYCLE || getInstanceInitailizer_computed == state().cycle()) {
      return getInstanceInitailizer_value;
    }
    getInstanceInitailizer_value = getInstanceInitailizer_compute();
    if (state().inCircle()) {
      getInstanceInitailizer_computed = state().cycle();
    
    } else {
      getInstanceInitailizer_computed = ASTState.NON_CYCLE;
    
    }
    return getInstanceInitailizer_value;
  }
  /** @apilevel internal */
  private java.util.List<InstanceInitializer> getInstanceInitailizer_compute() {
      java.util.List<InstanceInitializer> init = new ArrayList<>();
      for (BodyDecl bd : getBodyDeclList()) {
        if (bd.isInstanceInitializer()) {
          init.add((InstanceInitializer)bd);
        }
      }
      return init;
    }
  /** @apilevel internal */
  private void getStaticInitailizer_reset() {
    getStaticInitailizer_computed = null;
    getStaticInitailizer_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle getStaticInitailizer_computed = null;

  /** @apilevel internal */
  protected java.util.List<StaticInitializer> getStaticInitailizer_value;

  /**
   * Returns a list of static initializers declared within the class.
   * 
   * @return A list of static initializers.
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:392
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:392")
  public java.util.List<StaticInitializer> getStaticInitailizer() {
    ASTState state = state();
    if (getStaticInitailizer_computed == ASTState.NON_CYCLE || getStaticInitailizer_computed == state().cycle()) {
      return getStaticInitailizer_value;
    }
    getStaticInitailizer_value = getStaticInitailizer_compute();
    if (state().inCircle()) {
      getStaticInitailizer_computed = state().cycle();
    
    } else {
      getStaticInitailizer_computed = ASTState.NON_CYCLE;
    
    }
    return getStaticInitailizer_value;
  }
  /** @apilevel internal */
  private java.util.List<StaticInitializer> getStaticInitailizer_compute() {
      java.util.List<StaticInitializer> init = new ArrayList<>();
      for (BodyDecl bd : getBodyDeclList()) {
        if (bd.isStaticInitializer()) {
          init.add((StaticInitializer)bd);
        }
      }
      return init;
    }
  /**
   * @attribute syn
   * @aspect ConstructScope
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:49
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:47")
  public Collection<ConstructorDecl> lookupSuperConstructor() {
    Collection<ConstructorDecl> lookupSuperConstructor_value = hasSuperclass() ? superclass().constructors() : Collections.<ConstructorDecl>emptyList();
    return lookupSuperConstructor_value;
  }
  /** @apilevel internal */
  private void constructors_reset() {
    constructors_computed = null;
    constructors_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle constructors_computed = null;

  /** @apilevel internal */
  protected Collection<ConstructorDecl> constructors_value;

  /**
   * @attribute syn
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:184
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructorLookup", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:139")
  public Collection<ConstructorDecl> constructors() {
    ASTState state = state();
    if (constructors_computed == ASTState.NON_CYCLE || constructors_computed == state().cycle()) {
      return constructors_value;
    }
    constructors_value = constructors_compute();
    if (state().inCircle()) {
      constructors_computed = state().cycle();
    
    } else {
      constructors_computed = ASTState.NON_CYCLE;
    
    }
    return constructors_value;
  }
  /** @apilevel internal */
  private Collection<ConstructorDecl> constructors_compute() {
      Collection<ConstructorDecl> constructors = new ArrayList<ConstructorDecl>(refined_ConstructorLookup_ClassDecl_constructors());
      Collection<ConstructorDecl> transformed = new ArrayList<ConstructorDecl>(constructors);
      for (ConstructorDecl decl : constructors) {
        if (decl.transformed() != decl) {
          transformed.add(decl.transformed());
        }
      }
      return transformed;
    }
  /**
   * A class declaration requires an implicit constructor if it has no
   * explicit constructor.
   * @return <code>true</code> if this class requires an implicit default
   * contstructor.
   * @attribute syn
   * @aspect ImplicitConstructor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:225
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ImplicitConstructor", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:225")
  public boolean needsImplicitConstructor() {
    boolean needsImplicitConstructor_value = compilationUnit().fromSource() && !hasExplicitConstructor();
    return needsImplicitConstructor_value;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:248
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="ImplicitConstructor", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:248")
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
        Modifiers modifiers = new Modifiers();
        if (isPublic()) {
          modifiers.addModifier(new Modifier("public"));
        } else if (isProtected()) {
          modifiers.addModifier(new Modifier("protected"));
        } else if (isPrivate()) {
          modifiers.addModifier(new Modifier("private"));
        }
        ConstructorDecl constructor = new ConstructorDecl(
            modifiers,
            name(),
            new List(),
            new List(),
            new Opt(),
            new Block());
        constructor.setParsedConstructorInvocation(new ExprStmt(
            new SuperConstructorAccess("super", new List())));
        constructor.setImplicitConstructor();
        return new Opt<ConstructorDecl>(constructor);
      } else {
        return new Opt<ConstructorDecl>();
      }
    }
  /**
   * @attribute syn
   * @aspect ImplicitConstructor
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:329
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ImplicitConstructor", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:329")
  public boolean hasExplicitConstructor() {
    {
        for (int i = 0; i < getNumBodyDecl(); i++) {
          if (getBodyDecl(i) instanceof ConstructorDecl) {
            return true;
          }
        }
        return false;
      }
  }
  /**
   * Computes compile errors for each checked exception thrown by the default
   * constructor of this class.
   * @attribute syn
   * @aspect ExceptionHandling
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:388
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:388")
  public Collection<Problem> exceptionHandlingProblems() {
    {
        if (!hasImplicitConstructor() || isAnonymous()) {
          // If this class is anonymous, then exceptions are checked by the code
          // instantiating the anonymous class.
          return Collections.emptyList();
        }
        Collection<Problem> problems = new LinkedList<Problem>();
        Stmt superCall = getImplicitConstructor().getParsedConstructorInvocation();
        SuperConstructorAccess superAccess = (SuperConstructorAccess) ((ExprStmt) superCall).getExpr();
        for (Access exception : superAccess.decl().getExceptionList()) {
          if (exception.type().isCheckedException()) {
            problems.add(errorf(
                "default constructor for class %s throws unchecked exception %s via "
                + "superclass constructor", name(), exception.type().fullName()));
          }
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect AccessControl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AccessControl.jrag:187
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AccessControl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AccessControl.jrag:187")
  public Collection<Problem> accessControlProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
    
        // 8.1.1.2 final Classes
        TypeDecl typeDecl = superclass();
        if (!typeDecl.isUnknown() && !typeDecl.accessibleFromExtend(this)) {
          problems.add(errorf("class %s may not extend non accessible type %s",
              fullName(), typeDecl.fullName()));
        }
    
        if (hasSuperclass() && !superclass().accessibleFrom(this)) {
          problems.add(errorf("a superclass must be accessible which %s is not",
              superclass().name()));
        }
    
        // 8.1.4
        for (int i = 0; i < getNumImplements(); i++) {
          TypeDecl decl = getImplements(i).type();
          if (!decl.isCircular() && !decl.accessibleFrom(this)) {
            problems.add(errorf("class %s cannot implement non accessible type %s",
                fullName(), decl.fullName()));
          }
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:116
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:95")
  public Constant cast(Constant c) {
    Constant cast_Constant_value = Constant.create(c.stringValue());
    return cast_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:209
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:195")
  public Constant add(Constant c1, Constant c2) {
    Constant add_Constant_Constant_value = Constant.create(c1.stringValue() + c2.stringValue());
    return add_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:319
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:299")
  public Constant questionColon(Constant cond, Constant c1, Constant c2) {
    Constant questionColon_Constant_Constant_Constant_value = Constant.create(cond.booleanValue() ? c1.stringValue() : c2.stringValue());
    return questionColon_Constant_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:519
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ConstantExpression.jrag:499")
  public boolean eqIsTrue(Expr left, Expr right) {
    boolean eqIsTrue_Expr_Expr_value = isString() && left.constant().stringValue().equals(right.constant().stringValue());
    return eqIsTrue_Expr_Expr_value;
  }
  /**
   * @attribute syn
   * @aspect TypeHierarchyCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:368
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeHierarchyCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:368")
  public Collection<Problem> typeProblems() {
    {
        Collection<Problem> problems = refined_TypeHierarchyCheck_ClassDecl_typeProblems();
    
        if (hasSuperclass()) {
          // JLS SE7 8.4.8.4
          // Check for duplicate methods inherited from parameterized supertype.
          if (superclass().isParameterizedType()) {
            Map<String, SimpleSet<MethodDecl>> localMap = localMethodsSignatureMap();
            Map<String, SimpleSet<MethodDecl>> methodMap = superclass().localMethodsSignatureMap();
            for (Map.Entry<String, SimpleSet<MethodDecl>> entry: methodMap.entrySet()) {
              String signature = entry.getKey();
              if (!localMap.containsKey(signature)) {
                // Not locally overridden.
                SimpleSet<MethodDecl> set = entry.getValue();
                Iterator<MethodDecl> iter = set.iterator();
                iter.next();
                while (iter.hasNext()) {
                  iter.next();
                  problems.add(errorf(
                      "method with signature %s is multiply declared when inherited from %s",
                      signature, superclass().typeName()));
                }
              }
            }
          }
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect TypeHierarchyCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:403
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeHierarchyCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:403")
  public Collection<Problem> nameProblems() {
    {
        Collection<Problem> problems = refined_TypeHierarchyCheck_ClassDecl_nameProblems();
        for (SimpleSet<MethodDecl> set : methodsSignatureMap().values()) {
          if (set.size() > 1) {
            boolean foundClassAbstract = false;
            MethodDecl foundNonAbstract = null;
            for (MethodDecl m : set) {
              if (!m.isAbstract()) {
                foundNonAbstract = m;
              } else if (m.hostType().isClassDecl() && m.hostType() != this) {
                foundClassAbstract = true;
              }
            }
            // 8.4.8.1
            if (foundNonAbstract != null && !foundClassAbstract) {
              problems.add(errorf("Method %s is multiply declared in %s",
                  foundNonAbstract.fullSignature(), typeName()));
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
            if (!decl.isPrivate() && decl.accessibleFrom(this)) {
              result = result.add(decl);
            }
          }
        }
        if (hasSuperclass()) {
          for (TypeDecl decl : superclass().memberTypes(name)) {
            if (!decl.isPrivate() && decl.accessibleFrom(this)) {
              result = result.add(decl);
            }
          }
        }
        return result;
      }
  }
  /** @apilevel internal */
  private void unimplementedMethods_reset() {
    unimplementedMethods_computed = null;
    unimplementedMethods_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle unimplementedMethods_computed = null;

  /** @apilevel internal */
  protected Collection<MethodDecl> unimplementedMethods_value;

  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:36
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:34")
  public Collection<MethodDecl> unimplementedMethods() {
    ASTState state = state();
    if (unimplementedMethods_computed == ASTState.NON_CYCLE || unimplementedMethods_computed == state().cycle()) {
      return unimplementedMethods_value;
    }
    unimplementedMethods_value = unimplementedMethods_compute();
    if (state().inCircle()) {
      unimplementedMethods_computed = state().cycle();
    
    } else {
      unimplementedMethods_computed = ASTState.NON_CYCLE;
    
    }
    return unimplementedMethods_value;
  }
  /** @apilevel internal */
  private Collection<MethodDecl> unimplementedMethods_compute() {
      Collection<MethodDecl> methods = new ArrayList<MethodDecl>();
      for (MethodDecl m : interfacesMethods()) {
        boolean implemented = !m.isAbstract(); // Can be false in later Java versions.
        SimpleSet<MethodDecl> result = localMethodsSignature(m.signature());
        if (!implemented) {
          if (result.isSingleton()) {
            MethodDecl n = result.singletonValue();
            if (!n.isAbstract()) {
              implemented = true;
            }
          }
          if (!implemented) {
            result = ancestorMethods(m.signature());
            for (MethodDecl n : result) {
              if (!n.isAbstract() && n.isPublic()) {
                implemented = true;
                break;
              }
            }
          }
        }
        if (!implemented) {
          methods.add(m);
        }
      }
  
      if (hasSuperclass()) {
        for (MethodDecl m : superclass().unimplementedMethods()) {
          SimpleSet<MethodDecl> result = localMethodsSignature(m.signature());
          if (result.isSingleton()) {
            MethodDecl n = result.singletonValue();
            if (n.isAbstract() || !n.overrides(m)) {
              methods.add(m);
            }
          } else {
            methods.add(m);
          }
        }
      }
  
      for (MethodDecl m : localMethods()) {
        if (m.isAbstract()) {
          methods.add(m);
        }
      }
      return methods;
    }
  /** @apilevel internal */
  private void hasAbstract_reset() {
    hasAbstract_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle hasAbstract_computed = null;

  /** @apilevel internal */
  protected boolean hasAbstract_value;

  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:85
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:32")
  public boolean hasAbstract() {
    ASTState state = state();
    if (hasAbstract_computed == ASTState.NON_CYCLE || hasAbstract_computed == state().cycle()) {
      return hasAbstract_value;
    }
    hasAbstract_value = !unimplementedMethods().isEmpty();
    if (state().inCircle()) {
      hasAbstract_computed = state().cycle();
    
    } else {
      hasAbstract_computed = ASTState.NON_CYCLE;
    
    }
    return hasAbstract_value;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:435
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
      if (hasSuperclass()) {
        Iterator<Variable> iter = superclass().fieldsIterator();
        while (iter.hasNext()) {
          Variable decl = iter.next();
          if (!decl.isPrivate() && decl.accessibleFrom(this)
              && !localFieldsMap().containsKey(decl.name())) {
            putSimpleSetElement(map, decl.name(), decl);
          }
        }
      }
      for (InterfaceDecl iface : superInterfaces()) {
        Iterator<Variable> iter = iface.fieldsIterator();
        while (iter.hasNext()) {
          Variable decl = iter.next();
          if (!decl.isPrivate() && decl.accessibleFrom(this)
              && !localFieldsMap().containsKey(decl.name())) {
            putSimpleSetElement(map, decl.name(), decl);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:505
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
        return fields; // This causes hiding of fields in superclass and interfaces.
      }
      if (hasSuperclass()) {
        Iterator<Variable> iter = superclass().memberFields(name).iterator();
        while (iter.hasNext()) {
          Variable decl = iter.next();
          if (!decl.isPrivate() && decl.accessibleFrom(this)) {
            fields = fields.add(decl);
          }
        }
      }
      for (InterfaceDecl iface : superInterfaces()) {
        Iterator<Variable> iter = iface.memberFields(name).iterator();
        while (iter.hasNext()) {
          Variable decl = iter.next();
          if (!decl.isPrivate() && decl.accessibleFrom(this)) {
            fields = fields.add(decl);
          }
        }
      }
      return fields;
    }
  /**
   * Find method declarations inherited from superinterfaces with the given
   * signature.
   * The result can be multiple method declarations.
   * @attribute syn
   * @aspect MemberMethods
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:581
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MemberMethods", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:581")
  public SimpleSet<MethodDecl> interfacesMethodsSignature(String signature) {
    {
        SimpleSet<MethodDecl> result = interfacesMethodsSignatureMap().get(signature);
        if (result != null) {
          return result;
        } else {
          return emptySet();
        }
      }
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:1254
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
      Set<String> fromSuperClass = new HashSet<String>();
      if (hasSuperclass()) {
        for (MethodDecl m : superclass().methods()) {
          if (!m.isPrivate()
              && m.accessibleFrom(this)
              && !localMap.containsKey(m.signature())) {
            methods.add(m);
            if (!m.isAbstract()) {
              fromSuperClass.add(m.signature());
            }
          }
        }
      }
      for (MethodDecl m : interfacesMethods()) {
        if (!m.isStatic()
            && m.accessibleFrom(this)
            && !localMap.containsKey(m.signature())
            && !hasOverridingMethodInSuper(m)
            && !fromSuperClass.contains(m.signature())) {
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:745
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
      if (hasSuperclass()) {
        for (MethodDecl method : superclass().localMethodsSignature(signature)) {
          if (!method.isPrivate()) {
            result = result.add(method);
          }
        }
      }
      // Always add interface methods to the ancestorMethods set so that their
      // access modifiers are checked against local overriding methods.
      for (MethodDecl method : interfacesMethodsSignature(signature)) {
        result = result.add(method);
      }
      if (!hasSuperclass()) {
        return result;
      }
      if (result.isSingleton()) {
        MethodDecl m = result.singletonValue();
        if (!m.isAbstract()) {
          boolean done = true;
          for (MethodDecl n : superclass().ancestorMethods(signature)) {
            if (n.isPrivate() || !n.accessibleFrom(m.hostType())) {
              done = false;
            }
          }
          if (done) {
            return result;
          }
        }
      }
      for (MethodDecl m : superclass().ancestorMethods(signature)) {
        result = result.add(m);
      }
      return result;
    }
  /**
   * @attribute syn
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:54
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:328
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PrettyPrintUtil", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:328")
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/AutoBoxing.jrag:210
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
      if (refined_Generics_ClassDecl_castingConversionTo_TypeDecl(type)) {
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:224
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:223")
  public boolean isClassDecl() {
    boolean isClassDecl_value = true;
    return isClassDecl_value;
  }
  /** @apilevel internal */
  private void isString_reset() {
    isString_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isString_computed = null;

  /** @apilevel internal */
  protected boolean isString_value;

  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:239
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:238")
  public boolean isString() {
    ASTState state = state();
    if (isString_computed == ASTState.NON_CYCLE || isString_computed == state().cycle()) {
      return isString_value;
    }
    isString_value = fullName().equals("java.lang.String");
    if (state().inCircle()) {
      isString_computed = state().cycle();
    
    } else {
      isString_computed = ASTState.NON_CYCLE;
    
    }
    return isString_value;
  }
  /** @apilevel internal */
  private void isObject_reset() {
    isObject_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isObject_computed = null;

  /** @apilevel internal */
  protected boolean isObject_value;

  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:242
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:241")
  public boolean isObject() {
    ASTState state = state();
    if (isObject_computed == ASTState.NON_CYCLE || isObject_computed == state().cycle()) {
      return isObject_value;
    }
    isObject_value = name().equals("Object") && packageName().equals("java.lang");
    if (state().inCircle()) {
      isObject_computed = state().cycle();
    
    } else {
      isObject_computed = ASTState.NON_CYCLE;
    
    }
    return isObject_value;
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
        new_subtype_TypeDecl_value = type.supertypeClassDecl(this);
        if (((Boolean)_value.value) != new_subtype_TypeDecl_value) {
          state.setChangeInCycle();
          _value.value = new_subtype_TypeDecl_value;
        }
      } while (state.testAndClearChangeInCycle());
      subtype_TypeDecl_values.put(_parameters, new_subtype_TypeDecl_value);
      state.startLastCycle();
      boolean $tmp = type.supertypeClassDecl(this);

      state.leaveCircle();
      return new_subtype_TypeDecl_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_subtype_TypeDecl_value = type.supertypeClassDecl(this);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:472
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Subtyping", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:471")
  public boolean supertypeClassDecl(ClassDecl type) {
    boolean supertypeClassDecl_ClassDecl_value = super.supertypeClassDecl(type) || type.hasSuperclass() && type.superclass().subtype(this);
    return supertypeClassDecl_ClassDecl_value;
  }
  /**
   * @attribute syn
   * @aspect Subtyping
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:487
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Subtyping", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:486")
  public boolean supertypeInterfaceDecl(InterfaceDecl type) {
    boolean supertypeInterfaceDecl_InterfaceDecl_value = isObject();
    return supertypeInterfaceDecl_InterfaceDecl_value;
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
        return type.hasSuperclass() && type.superclass().subtype(this);
      }
  }
  /**
   * @attribute syn
   * @aspect NestedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:641
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NestedTypes", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:639")
  public boolean isInnerClass() {
    boolean isInnerClass_value = isNestedType() && !isStatic() && enclosingType().isClassDecl();
    return isInnerClass_value;
  }
  /**
   * @attribute syn
   * @aspect SuperClasses
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:730
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SuperClasses", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:729")
  public TypeDecl supertype() {
    TypeDecl supertype_value = superclass();
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:740
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
      if (isObject()) {
        return Collections.emptyList();
      } else {
        Collection<InterfaceDecl> interfaces = new ArrayList<InterfaceDecl>();
        for (Access access : getImplementsList()) {
          TypeDecl implemented = access.type();
          if (implemented.isInterfaceDecl()) {
            // It is an error if implemented is not an interface (error check exists).
            interfaces.add((InterfaceDecl) implemented);
          }
        }
        return interfaces;
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
      if (hasSuperClass()) {
        Access a = getSuperClass().lastAccess();
        while (a != null) {
          if (a.type().isCircular()) {
            return true;
          }
          a = (a.isQualified() && a.qualifier().isTypeAccess()) ? (Access) a.qualifier() : null;
        }
      }
      for (int i = 0; i < getNumImplements(); i++) {
        Access a = getImplements(i).lastAccess();
        while (a != null) {
          if (a.type().isCircular()) {
            return true;
          }
          a = (a.isQualified() && a.qualifier().isTypeAccess()) ? (Access) a.qualifier() : null;
        }
      }
      return false;
    }
  /**
   * A class is reifiable if its enclosing type is reifiable.
   * @attribute syn
   * @aspect ReifiableTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/ReifiableTypes.jrag:56
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ReifiableTypes", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/ReifiableTypes.jrag:39")
  public boolean isReifiable() {
    boolean isReifiable_value = !isInnerClass() || enclosingType().isReifiable();
    return isReifiable_value;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:347
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:347")
  public Annotation annotation(TypeDecl typeDecl) {
    {
        Annotation a = super.annotation(typeDecl);
        if (a != null) {
          return a;
        }
        if (hasSuperclass()) {
          // If the queried annotation is itself annotation with @Inherited then
          // delegate the query to the superclass.
          if (typeDecl.annotation(lookupType("java.lang.annotation", "Inherited")) != null) {
            return superclass().annotation(typeDecl);
          }
        }
        return null;
      }
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:543
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
      Map<String, SimpleSet<MethodDecl>> map = new HashMap<String, SimpleSet<MethodDecl>>();
      if (hasSuperclass()) {
        for (MethodDecl m : superclass().localMethods()) {
          if (!m.isPrivate() && m.accessibleFrom(this) && m.erasedMethod() != m) {
            // Map erased signature to substituted method.
            putSimpleSetElement(map, m.erasedMethod().signature(), m);
          }
        }
        mergeMap(map, superclass().erasedAncestorMethodsMap());
      }
      for (MethodDecl m : interfacesMethods()) {
        if (m.accessibleFrom(this) && m.erasedMethod() != m) {
          String erasedSignature = m.erasedMethod().signature();
          // Map erased signature to substituted method.
          putSimpleSetElement(map, erasedSignature, m);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:647
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
      Collection<InterfaceDecl> set = new HashSet<InterfaceDecl>();
      if (hasSuperclass()) {
        set.addAll(superclass().implementedInterfaces());
      }
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/EnhancedFor.jrag:110
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
      TypeDecl type = unknownType();
      for (Access iface : getImplementsList()) {
        type = iface.type().iterableElementType();
        if (!type.isUnknown()) {
          break;
        }
      }
      if (type.isUnknown() && hasSuperclass()) {
        return superclass().iterableElementType();
      } else {
        return type;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:1212
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:1212")
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
      for (MethodDecl superMethod : interfacesMethods()) {
        if (m != superMethod && superMethod.overrides(m)) {
          return true;
        }
  
      }
      if (hasSuperclass()) {
        for (MethodDecl superMethod : superclass().methods()) {
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
        new_strictSubtype_TypeDecl_value = type.strictSupertypeClassDecl(this);
        if (((Boolean)_value.value) != new_strictSubtype_TypeDecl_value) {
          state.setChangeInCycle();
          _value.value = new_strictSubtype_TypeDecl_value;
        }
      } while (state.testAndClearChangeInCycle());
      strictSubtype_TypeDecl_values.put(_parameters, new_strictSubtype_TypeDecl_value);
      state.startLastCycle();
      boolean $tmp = type.strictSupertypeClassDecl(this);

      state.leaveCircle();
      return new_strictSubtype_TypeDecl_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_strictSubtype_TypeDecl_value = type.strictSupertypeClassDecl(this);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:378
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:376")
  public boolean strictSupertypeClassDecl(ClassDecl type) {
    boolean strictSupertypeClassDecl_ClassDecl_value = super.strictSupertypeClassDecl(type) || type.hasSuperclass()
          && type.superclass() != null && type.superclass().strictSubtype(this);
    return strictSupertypeClassDecl_ClassDecl_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:397
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/GenericsSubtype.jrag:395")
  public boolean strictSupertypeInterfaceDecl(InterfaceDecl type) {
    boolean strictSupertypeInterfaceDecl_InterfaceDecl_value = isObject();
    return strictSupertypeInterfaceDecl_InterfaceDecl_value;
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
        return type.hasSuperclass() && type.superclass() != null
            && type.superclass().strictSubtype(this);
      }
  }
  /**
   * @attribute syn
   * @aspect FilterUtils
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:2
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FilterUtils", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:2")
  public Set<TypeDecl> fieldTypes() {
    { // should include supertype fields as well
            Set<TypeDecl> res = new HashSet<>();
            for (TypeDecl td : supertypesIncludingSelf()) {
                if (!(td instanceof ClassDecl)) continue;
    
                ClassDecl cd = (ClassDecl) td;
                for (BodyDecl bd : cd.getBodyDeclList()) {
                    if (bd instanceof FieldDecl) {
                        FieldDecl fd = (FieldDecl) bd;
                        for (FieldDeclarator field : fd.getDeclaratorList()) {
                            res.add(field.type());
                        }
                    }
                }
            }
            return res;
        }
  }
  /**
   * @attribute syn
   * @aspect FilterUtils
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:30
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:56
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:56")
  public ASTNode getNode() {
    ASTNode getNode_value = this;
    return getNode_value;
  }
  /**
   * @attribute syn
   * @aspect Utilities
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:295
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:376
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Utilities", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:376")
  public Set<InvocationTarget> constructorSet() {
    Set<InvocationTarget> constructorSet_value = constructors().stream().map(x -> (InvocationTarget) x).collect(Collectors.toSet());
    return constructorSet_value;
  }
  /**
   * @attribute syn
   * @aspect Collection
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Collection.jrag:3
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Collection", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Collection.jrag:3")
  public boolean isList() {
    {
            for (Access implemented : getImplementsList()) {
                if (implemented.type().isList()) {
                    return true;
                }
            }
            return false;
        }
  }
  /**
   * @attribute syn
   * @aspect Collection
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Collection.jrag:25
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Collection", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Collection.jrag:25")
  public boolean isMap() {
    {
            for (Access implemented : getImplementsList()) {
                if (implemented.type().isMap()) {
                    return true;
                }
            }
            return false;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:231
   * @apilevel internal
   */
  public SimpleSet<TypeDecl> Define_lookupType(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getImplicitConstructorOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:559
      return localLookupType(name);
    }
    else {
      return super.Define_lookupType(_callerNode, _childNode, name);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:231
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupType
   */
  protected boolean canDefine_lookupType(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:437
   * @apilevel internal
   */
  public boolean Define_mayBeFinal(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:312
      return true;
    }
    else {
      return super.Define_mayBeFinal(_callerNode, _childNode);
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
    if (_callerNode == getImplementsListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:98
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return NameType.TYPE_NAME;
    }
    else if (_callerNode == getSuperClassOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:97
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:593
   * @apilevel internal
   */
  public TypeDecl Define_enclosingType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getImplicitConstructorOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:583
      return this;
    }
    else {
      return super.Define_enclosingType(_callerNode, _childNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:593
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingType
   */
  protected boolean canDefine_enclosingType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:76
   * @apilevel internal
   */
  public TypeDecl Define_hostType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getImplementsListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:692
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return hostType();
    }
    else if (_callerNode == getSuperClassOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:691
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
    if (_callerNode == getImplementsListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:414
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return hasAnnotationSuppressWarnings(annot) || withinSuppressWarnings(annot);
    }
    else if (_callerNode == getSuperClassOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:411
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
    if (_callerNode == getImplementsListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:541
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return isDeprecated() || withinDeprecatedAnnotation();
    }
    else if (_callerNode == getSuperClassOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:538
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
    if (_callerNode == getImplementsListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:383
      int i = _callerNode.getIndexOfChild(_childNode);
      return true;
    }
    else if (_callerNode == getSuperClassOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:382
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
  /**
   * @attribute coll
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:130
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:130")
  public Set<ClassInstanceExpr> instantiations() {
    ASTState state = state();
    if (ClassDecl_instantiations_computed == ASTState.NON_CYCLE || ClassDecl_instantiations_computed == state().cycle()) {
      return ClassDecl_instantiations_value;
    }
    ClassDecl_instantiations_value = instantiations_compute();
    if (state().inCircle()) {
      ClassDecl_instantiations_computed = state().cycle();
    
    } else {
      ClassDecl_instantiations_computed = ASTState.NON_CYCLE;
    
    }
    return ClassDecl_instantiations_value;
  }
  /** @apilevel internal */
  private Set<ClassInstanceExpr> instantiations_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    ASTNode root = (ASTNode) node;
    root.survey_ClassDecl_instantiations();
    Set<ClassInstanceExpr> _computedValue = new HashSet<>();
    if (root.contributorMap_ClassDecl_instantiations.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_ClassDecl_instantiations.get(this)) {
        contributor.contributeTo_ClassDecl_instantiations(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle ClassDecl_instantiations_computed = null;

  /** @apilevel internal */
  protected Set<ClassInstanceExpr> ClassDecl_instantiations_value;

  /** @apilevel internal */
  protected void collect_contributors_TypeDecl_subtypes(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:37
    {
      TypeDecl target = (TypeDecl) (supertypeTarget());
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:38
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:46
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:155
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:382
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AccessControl.jrag:185
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:366
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:122
    if (!superclass().isUnknown() && superclass().isFinal()) {
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
  protected void collect_contributors_InvocationTarget_calledMethods(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {

  // Collecting NTAs
  
getImplicitConstructorOpt().collect_contributors_InvocationTarget_calledMethods(_root, _map);
    super.collect_contributors_InvocationTarget_calledMethods(_root, _map);
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
    for (Problem value : exceptionHandlingProblems()) {
      collection.add(value);
    }
    for (Problem value : accessControlProblems()) {
      collection.add(value);
    }
    for (Problem value : typeProblems()) {
      collection.add(value);
    }
    if (!superclass().isUnknown() && superclass().isFinal()) {
      collection.add(errorf("class %s may not extend final class %s", fullName(), superclass().fullName()));
    }
  }

}
