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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/grammar/Enums.ast:1
 * @astdecl EnumDecl : ClassDecl ::= Modifiers <ID:String> Implements:Access* BodyDecl* [ImplicitConstructor:ConstructorDecl] [SuperClass:Access];
 * @production EnumDecl : {@link ClassDecl} ::= <span class="component">{@link Modifiers}</span> <span class="component">&lt;ID:{@link String}&gt;</span> <span class="component">[SuperClass:{@link Access}]</span> <span class="component">Implements:{@link Access}*</span> <span class="component">{@link BodyDecl}*</span>;

 */
public class EnumDecl extends ClassDecl implements Cloneable {
  /**
   * Not implemented using generated pretty-print code because we have to separate
   * enum constants and enum body declarations.
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:670
   */
  public void prettyPrint(PrettyPrinter out) {
    if (!docComment.isEmpty()) {
      out.print(docComment);
    }
    out.print(getModifiers());
    out.print("enum " + name());
    if (getNumImplements() > 0) {
      out.print(" implements ");
      out.join(getImplementsList(), new PrettyPrinter.Joiner() {
        @Override
        public void printSeparator(PrettyPrinter out) {
          out.print(", ");
        }
      });
    }
    out.print(" {");
    out.println();
    out.indent(1);
    out.pushIndentation();
    boolean inConstantList = true;
    for (int i = 0; i < getNumBodyDecl(); i++) {
      BodyDecl d = getBodyDecl(i);
      if (d instanceof EnumConstant) {
        if (i > 0) {
          out.print(",");
          out.println();
        }
      } else if (!d.isSynthetic()) {
        if (inConstantList) {
          out.println();
          out.print(";");
          inConstantList = false;
        }
        out.println();
        out.println();
      }
      out.print(d);
    }
    out.popIndentation();
    out.println();
    out.print("}");
  }
  /**
   * @declaredat ASTNode:1
   */
  public EnumDecl() {
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
    setChild(new List(), 2);
    setChild(new Opt(), 3);
    setChild(new Opt(), 4);
  }
  /**
   * @declaredat ASTNode:17
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Modifiers", "ID", "Implements", "BodyDecl"},
    type = {"Modifiers", "String", "List<Access>", "List<BodyDecl>"},
    kind = {"Child", "Token", "List", "List"}
  )
  public EnumDecl(Modifiers p0, String p1, List<Access> p2, List<BodyDecl> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /**
   * @declaredat ASTNode:28
   */
  public EnumDecl(Modifiers p0, beaver.Symbol p1, List<Access> p2, List<BodyDecl> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:35
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:41
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:45
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    isStatic_reset();
    getSuperClassOpt_reset();
    getImplicitConstructorOpt_reset();
    localFieldsMap_reset();
    enumConstants_reset();
    methodsNameMap_reset();
    valuesFieldDeclarator_reset();
    implicitValuesField_reset();
    implicitValuesMethod_reset();
    implicitValueOfMethod_reset();
    unimplementedMethods_reset();
    localMethods_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:61
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:66
   */
  public EnumDecl clone() throws CloneNotSupportedException {
    EnumDecl node = (EnumDecl) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:71
   */
  public EnumDecl copy() {
    try {
      EnumDecl node = (EnumDecl) clone();
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
   * @declaredat ASTNode:90
   */
  @Deprecated
  public EnumDecl fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:100
   */
  public EnumDecl treeCopyNoTransform() {
    EnumDecl tree = (EnumDecl) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 3:
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
   * @declaredat ASTNode:126
   */
  public EnumDecl treeCopy() {
    EnumDecl tree = (EnumDecl) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 3:
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
  public EnumDecl setModifiers(Modifiers node) {
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
  public EnumDecl setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public EnumDecl setID(beaver.Symbol symbol) {
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
   * Replaces the Implements list.
   * @param list The new list node to be used as the Implements list.
   * @apilevel high-level
   */
  public EnumDecl setImplementsList(List<Access> list) {
    setChild(list, 1);
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
  public EnumDecl addImplements(Access node) {
    List<Access> list = (parent == null) ? getImplementsListNoTransform() : getImplementsList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public EnumDecl addImplementsNoTransform(Access node) {
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
  public EnumDecl setImplements(Access node, int i) {
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
    List<Access> list = (List<Access>) getChild(1);
    return list;
  }
  /**
   * Retrieves the Implements list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Implements list.
   * @apilevel low-level
   */
  public List<Access> getImplementsListNoTransform() {
    return (List<Access>) getChildNoTransform(1);
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
  public EnumDecl setBodyDeclList(List<BodyDecl> list) {
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
  public EnumDecl addBodyDecl(BodyDecl node) {
    List<BodyDecl> list = (parent == null) ? getBodyDeclListNoTransform() : getBodyDeclList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public EnumDecl addBodyDeclNoTransform(BodyDecl node) {
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
  public EnumDecl setBodyDecl(BodyDecl node, int i) {
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
   * Replaces the (optional) ImplicitConstructor child.
   * @param node The new node to be used as the ImplicitConstructor child.
   * @apilevel high-level
   */
  public EnumDecl setImplicitConstructor(ConstructorDecl node) {
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
    return (Opt<ConstructorDecl>) getChildNoTransform(3);
  }
  /**
   * Retrieves the child position of the optional child ImplicitConstructor.
   * @return The the child position of the optional child ImplicitConstructor.
   * @apilevel low-level
   */
  protected int getImplicitConstructorOptChildPosition() {
    return 3;
  }
  /**
   * This method should not be called. This method throws an exception due to
   * the corresponding child being an NTA shadowing a non-NTA child.
   * @param node
   * @apilevel internal
   */
  public EnumDecl setSuperClassOpt(Opt<Access> node) {
    throw new Error("Can not replace NTA child SuperClassOpt in EnumDecl!");
  }
  /**
   * Replaces the (optional) SuperClass child.
   * @param node The new node to be used as the SuperClass child.
   * @apilevel high-level
   */
  public EnumDecl setSuperClass(Access node) {
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
    return (Opt<Access>) getChildNoTransform(4);
  }
  /**
   * Retrieves the child position of the optional child SuperClass.
   * @return The the child position of the optional child SuperClass.
   * @apilevel low-level
   */
  protected int getSuperClassOptChildPosition() {
    return 4;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:217
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:199")
  public boolean isValidAnnotationMethodReturnType() {
    boolean isValidAnnotationMethodReturnType_value = true;
    return isValidAnnotationMethodReturnType_value;
  }
  /**
   * @attribute syn
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:38
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Enums", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:37")
  public boolean isEnumDecl() {
    boolean isEnumDecl_value = true;
    return isEnumDecl_value;
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
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:65
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:240")
  public boolean isStatic() {
    ASTState state = state();
    if (isStatic_computed == ASTState.NON_CYCLE || isStatic_computed == state().cycle()) {
      return isStatic_value;
    }
    isStatic_value = isNestedType();
    if (state().inCircle()) {
      isStatic_computed = state().cycle();
    
    } else {
      isStatic_computed = ASTState.NON_CYCLE;
    
    }
    return isStatic_value;
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
        Collection<Problem> problems = new LinkedList<Problem>(super.typeProblems());
        for (MethodDecl m : memberMethods("finalize")) {
          if (m.getNumParameter() == 0 && m.hostType() == this) {
            problems.add(error("an enum may not declare a finalizer"));
          }
        }
        return problems;
      }
  }
  /** @apilevel internal */
  private void getSuperClassOpt_reset() {
    getSuperClassOpt_computed = false;
    
    getSuperClassOpt_value = null;
  }
  /** @apilevel internal */
  protected boolean getSuperClassOpt_computed = false;

  /** @apilevel internal */
  protected Opt<Access> getSuperClassOpt_value;

  /**
   * @attribute syn nta
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:86
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="Enums", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:86")
  public Opt<Access> getSuperClassOpt() {
    ASTState state = state();
    if (getSuperClassOpt_computed) {
      return (Opt<Access>) getChild(getSuperClassOptChildPosition());
    }
    state().enterLazyAttribute();
    getSuperClassOpt_value = new Opt<Access>(new ParTypeAccess(
              new TypeAccess("java.lang", "Enum"),
              new List<Access>(createQualifiedAccess())));
    setChild(getSuperClassOpt_value, getSuperClassOptChildPosition());
    getSuperClassOpt_computed = true;
    state().leaveLazyAttribute();
    Opt<Access> node = (Opt<Access>) this.getChild(getSuperClassOptChildPosition());
    return node;
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
   * Implicit constructor for enum type.
   * @attribute syn nta
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:103
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="Enums", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:103")
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
        List<ParameterDeclaration> parameters = new List<ParameterDeclaration>(
            new ParameterDeclaration(new TypeAccess("java.lang", "String"), "p0"),
            new ParameterDeclaration(new TypeAccess("int"), "p1"));
        ConstructorDecl constructor = new ConstructorDecl(
            new Modifiers(new List<Modifier>(new Modifier("synthetic"))),
            name(),
            parameters,
            new List<Access>(),
            new Opt<Stmt>(new ExprStmt(
                new SuperConstructorAccess(
                    "super",
                    new List<Expr>(
                        new VarAccess("p0"),
                        new VarAccess("p1"))))),
            new Block());
        return new Opt<ConstructorDecl>(constructor);
      } else {
        return new Opt<ConstructorDecl>();
      }
    }
  /** @apilevel internal */
  private void localFieldsMap_reset() {
    localFieldsMap_computed = null;
    localFieldsMap_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle localFieldsMap_computed = null;

  /** @apilevel internal */
  protected Map<String, SimpleSet<Variable>> localFieldsMap_value;

  /**
   * Adds enum constants as local fields.
   * @attribute syn
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:261
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Fields", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:421")
  public Map<String, SimpleSet<Variable>> localFieldsMap() {
    ASTState state = state();
    if (localFieldsMap_computed == ASTState.NON_CYCLE || localFieldsMap_computed == state().cycle()) {
      return localFieldsMap_value;
    }
    localFieldsMap_value = localFieldsMap_compute();
    if (state().inCircle()) {
      localFieldsMap_computed = state().cycle();
    
    } else {
      localFieldsMap_computed = ASTState.NON_CYCLE;
    
    }
    return localFieldsMap_value;
  }
  /** @apilevel internal */
  private Map<String, SimpleSet<Variable>> localFieldsMap_compute() {
      Map<String, SimpleSet<Variable>> map = new HashMap<String, SimpleSet<Variable>>(
          super.localFieldsMap());
      for (BodyDecl decl : getBodyDeclList()) {
        if (decl instanceof EnumConstant) {
          EnumConstant constant = (EnumConstant) decl;
          putSimpleSetElement(map, constant.name(), constant);
        }
      }
      putSimpleSetElement(map, "$VALUES", valuesFieldDeclarator());
      return map;
    }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:245
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:245")
  public boolean isFinal() {
    {
        for (EnumConstant c : enumConstants()) {
          ClassInstanceExpr e = (ClassInstanceExpr) c.getInit();
          if (e.hasTypeDecl()) {
            return false;
          }
        }
        return true;
      }
  }
  /** @apilevel internal */
  private void enumConstants_reset() {
    enumConstants_computed = null;
    enumConstants_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle enumConstants_computed = null;

  /** @apilevel internal */
  protected Collection<EnumConstant> enumConstants_value;

  /**
   * @attribute syn
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:393
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Enums", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:393")
  public Collection<EnumConstant> enumConstants() {
    ASTState state = state();
    if (enumConstants_computed == ASTState.NON_CYCLE || enumConstants_computed == state().cycle()) {
      return enumConstants_value;
    }
    enumConstants_value = enumConstants_compute();
    if (state().inCircle()) {
      enumConstants_computed = state().cycle();
    
    } else {
      enumConstants_computed = ASTState.NON_CYCLE;
    
    }
    return enumConstants_value;
  }
  /** @apilevel internal */
  private Collection<EnumConstant> enumConstants_compute() {
      Collection<EnumConstant> list = new ArrayList<EnumConstant>();
      for (int i = 0; i < getNumBodyDecl(); i++) {
        if (getBodyDecl(i).isEnumConstant()) {
          list.add((EnumConstant) getBodyDecl(i));
        }
      }
      return list;
    }
  /** @apilevel internal */
  private void methodsNameMap_reset() {
    methodsNameMap_computed = null;
    methodsNameMap_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle methodsNameMap_computed = null;

  /** @apilevel internal */
  protected Map<String, Collection<MethodDecl>> methodsNameMap_value;

  /**
   * @attribute syn
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:429
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MemberMethods", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:499")
  public Map<String, Collection<MethodDecl>> methodsNameMap() {
    ASTState state = state();
    if (methodsNameMap_computed == ASTState.NON_CYCLE || methodsNameMap_computed == state().cycle()) {
      return methodsNameMap_value;
    }
    methodsNameMap_value = methodsNameMap_compute();
    if (state().inCircle()) {
      methodsNameMap_computed = state().cycle();
    
    } else {
      methodsNameMap_computed = ASTState.NON_CYCLE;
    
    }
    return methodsNameMap_value;
  }
  /** @apilevel internal */
  private Map<String, Collection<MethodDecl>> methodsNameMap_compute() {
      Map<String, Collection<MethodDecl>> map = new HashMap<String, Collection<MethodDecl>>();
      // Create a deep copy to avoid aliases.
      for (Map.Entry<String, Collection<MethodDecl>> entry : super.methodsNameMap().entrySet()) {
        map.put(entry.getKey(), new ArrayList<MethodDecl>(entry.getValue()));
      }
      if (compilationUnit().fromSource()) {
        // Add implicit method declarations only if needed.
        addMethodToMap(implicitValuesMethod(), map);
        addMethodToMap(implicitValueOfMethod(), map);
      }
      return map;
    }
  /** @apilevel internal */
  private void valuesFieldDeclarator_reset() {
    valuesFieldDeclarator_computed = null;
    valuesFieldDeclarator_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle valuesFieldDeclarator_computed = null;

  /** @apilevel internal */
  protected FieldDeclarator valuesFieldDeclarator_value;

  /**
   * @attribute syn
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:443
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Enums", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:443")
  public FieldDeclarator valuesFieldDeclarator() {
    ASTState state = state();
    if (valuesFieldDeclarator_computed == ASTState.NON_CYCLE || valuesFieldDeclarator_computed == state().cycle()) {
      return valuesFieldDeclarator_value;
    }
    valuesFieldDeclarator_value = implicitValuesField().getDeclarator(0);
    if (state().inCircle()) {
      valuesFieldDeclarator_computed = state().cycle();
    
    } else {
      valuesFieldDeclarator_computed = ASTState.NON_CYCLE;
    
    }
    return valuesFieldDeclarator_value;
  }
  /** @apilevel internal */
  private void implicitValuesField_reset() {
    implicitValuesField_computed = false;
    
    implicitValuesField_value = null;
  }
  /** @apilevel internal */
  protected boolean implicitValuesField_computed = false;

  /** @apilevel internal */
  protected FieldDecl implicitValuesField_value;

  /**
   * @attribute syn
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:446
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="Enums", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:446")
  public FieldDecl implicitValuesField() {
    ASTState state = state();
    if (implicitValuesField_computed) {
      return implicitValuesField_value;
    }
    state().enterLazyAttribute();
    implicitValuesField_value = implicitValuesField_compute();
    implicitValuesField_value.setParent(this);
    implicitValuesField_computed = true;
    state().leaveLazyAttribute();
    return implicitValuesField_value;
  }
  /** @apilevel internal */
  private FieldDecl implicitValuesField_compute() {
      int numConstants = enumConstants().size();
      List initValues = new List();
      for (EnumConstant c : enumConstants()) {
        initValues.add(c.createBoundAccess());
      }
      FieldDeclarator values = new FieldDeclarator(
          "$VALUES",
          new List<Dims>(),
          new Opt<Expr>(
              new ArrayCreationExpr(
                new ArrayTypeWithSizeAccess(
                  createQualifiedAccess(),
                  Literal.buildIntegerLiteral(enumConstants().size())),
                new Opt(
                  new ArrayInit(
                    initValues)))));
      return new FieldDecl(
          new Modifiers(new List<Modifier>(
              new Modifier("private"),
              new Modifier("static"),
              new Modifier("final"),
              new Modifier("synthetic"))),
          arrayType().createQualifiedAccess(),
          new List<FieldDeclarator>(values));
    }
  /** @apilevel internal */
  private void implicitValuesMethod_reset() {
    implicitValuesMethod_computed = false;
    
    implicitValuesMethod_value = null;
  }
  /** @apilevel internal */
  protected boolean implicitValuesMethod_computed = false;

  /** @apilevel internal */
  protected MethodDecl implicitValuesMethod_value;

  /**
   * @attribute syn
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:474
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="Enums", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:474")
  public MethodDecl implicitValuesMethod() {
    ASTState state = state();
    if (implicitValuesMethod_computed) {
      return implicitValuesMethod_value;
    }
    state().enterLazyAttribute();
    implicitValuesMethod_value = new MethodDecl(
            new Modifiers(new List<Modifier>(
              new Modifier("public"),
              new Modifier("static"))),
            arrayType().createQualifiedAccess(),
            "values",
            new List(),
            new List(),
            new Opt(
              new Block(
                new List().add(
                  new ReturnStmt(
                    new Opt(
                      new CastExpr(
                        arrayType().createQualifiedAccess(),
                        valuesFieldDeclarator().createBoundAccess().qualifiesAccess(
                          new MethodAccess(
                            "clone",
                            new List())))))))));
    implicitValuesMethod_value.setParent(this);
    implicitValuesMethod_computed = true;
    state().leaveLazyAttribute();
    return implicitValuesMethod_value;
  }
  /** @apilevel internal */
  private void implicitValueOfMethod_reset() {
    implicitValueOfMethod_computed = false;
    
    implicitValueOfMethod_value = null;
  }
  /** @apilevel internal */
  protected boolean implicitValueOfMethod_computed = false;

  /** @apilevel internal */
  protected MethodDecl implicitValueOfMethod_value;

  /**
   * @attribute syn
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:496
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="Enums", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:496")
  public MethodDecl implicitValueOfMethod() {
    ASTState state = state();
    if (implicitValueOfMethod_computed) {
      return implicitValueOfMethod_value;
    }
    state().enterLazyAttribute();
    implicitValueOfMethod_value = new MethodDecl(
            new Modifiers(new List<Modifier>(
                new Modifier("public"),
                new Modifier("static"))),
            createQualifiedAccess(),
            "valueOf",
            new List().add(
              new ParameterDeclaration(
                new Modifiers(new List()),
                typeString().createQualifiedAccess(),
                "s")),
            new List(),
            new Opt(
              new Block(
                new List().add(
                  new ReturnStmt(
                    new Opt(
                      new CastExpr(
                        createQualifiedAccess(),
                        lookupType("java.lang", "Enum").createQualifiedAccess().qualifiesAccess(
                          new MethodAccess(
                            "valueOf",
                            new List().add(
                              createQualifiedAccess().qualifiesAccess(new ClassAccess())
                            ).add(
                              new VarAccess(
                                "s")))))))))));
    implicitValueOfMethod_value.setParent(this);
    implicitValueOfMethod_computed = true;
    state().leaveLazyAttribute();
    return implicitValueOfMethod_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:238
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:238")
  public boolean isAbstract() {
    {
        for (int i = 0; i < getNumBodyDecl(); i++) {
          if (getBodyDecl(i) instanceof MethodDecl) {
            MethodDecl m = (MethodDecl) getBodyDecl(i);
            if (m.isAbstract()) {
              return true;
            }
          }
        }
        return false;
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
   * From the Java Language Specification, third edition, section 8.9 Enums:
   * 
   * It is a compile-time error for an enum type E to have an abstract method
   * m as a member unless E has one or more enum constants, and all of E's enum
   * constants have class bodies that provide concrete implementations of m.
   * @attribute syn
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:720
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
      Collection<MethodDecl> methods = new LinkedList<MethodDecl>();
      for (MethodDecl method : interfacesMethods()) {
        SimpleSet<MethodDecl> set = localMethodsSignature(method.signature());
        if (set.isSingleton()) {
          MethodDecl n = set.singletonValue();
          if (!n.isAbstract()) {
            continue;
          }
        }
        boolean implemented = false;
        set = ancestorMethods(method.signature());
        for (MethodDecl n : set) {
          if (!n.isAbstract()) {
            implemented = true;
            break;
          }
        }
        if (!implemented) {
          methods.add(method);
        }
      }
  
      for (MethodDecl method : localMethods()) {
        if (method.isAbstract()) {
          methods.add(method);
        }
      }
  
      Collection<MethodDecl> unimplemented = new ArrayList<MethodDecl>();
      for (MethodDecl method : methods) {
        if (enumConstants().isEmpty()) {
          unimplemented.add(method);
          continue;
        }
        boolean missing = false;
        for (EnumConstant c : enumConstants()) {
          if (!c.implementsMethod(method)) {
            missing = true;
            break;
          }
        }
        if (missing) {
          unimplemented.add(method);
        }
      }
  
      return unimplemented;
    }
  /**
   * Check that the enum does not contain unimplemented abstract methods.
   * @attribute syn
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:775
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Enums", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:775")
  public Collection<Problem> modifierProblems() {
    {
        if (!unimplementedMethods().isEmpty()) {
          StringBuilder sb = new StringBuilder();
          sb.append("" + name() + " lacks implementations in one or more "
              + "enum constants for the following methods:\n");
          for (MethodDecl m : unimplementedMethods()) {
            sb.append("  " + m.signature() + " in " + m.hostType().typeName() + "\n");
          }
          return Collections.singletonList(error(sb.toString()));
        }
        return Collections.emptyList();
      }
  }
  /** @apilevel internal */
  private void localMethods_reset() {
    localMethods_computed = null;
    localMethods_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle localMethods_computed = null;

  /** @apilevel internal */
  protected java.util.List<MethodDecl> localMethods_value;

  /**
   * Add the implicit methods to the local method list.
   * @attribute syn
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:812
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MemberMethods", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:546")
  public java.util.List<MethodDecl> localMethods() {
    ASTState state = state();
    if (localMethods_computed == ASTState.NON_CYCLE || localMethods_computed == state().cycle()) {
      return localMethods_value;
    }
    localMethods_value = localMethods_compute();
    if (state().inCircle()) {
      localMethods_computed = state().cycle();
    
    } else {
      localMethods_computed = ASTState.NON_CYCLE;
    
    }
    return localMethods_value;
  }
  /** @apilevel internal */
  private java.util.List<MethodDecl> localMethods_compute() {
      ArrayList<MethodDecl> methods = new ArrayList<MethodDecl>(super.localMethods());
      if (compilationUnit().fromSource()) {
        // Add implicit method declarations only if needed.
        methods.add(implicitValuesMethod());
        methods.add(implicitValueOfMethod());
      }
      return methods;
    }
  /**
   * @attribute inh
   * @aspect Enums
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:525
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Enums", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:525")
  public TypeDecl typeString() {
    TypeDecl typeString_value = getParent().Define_typeString(this, null);
    return typeString_value;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:438
   * @apilevel internal
   */
  public boolean Define_mayBeAbstract(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:59
      return false;
    }
    else {
      return super.Define_mayBeAbstract(_callerNode, _childNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:438
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeAbstract
   */
  protected boolean canDefine_mayBeAbstract(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:436
   * @apilevel internal
   */
  public boolean Define_mayBeStatic(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:66
      return isNestedType();
    }
    else {
      return super.Define_mayBeStatic(_callerNode, _childNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:436
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeStatic
   */
  protected boolean canDefine_mayBeStatic(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:129
   * @apilevel internal
   */
  public boolean Define_isOriginalEnumConstructor(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getImplicitConstructorOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:133
      return false;
    }
    else if (_callerNode == getBodyDeclListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:131
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return true;
    }
    else {
      return getParent().Define_isOriginalEnumConstructor(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:129
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isOriginalEnumConstructor
   */
  protected boolean canDefine_isOriginalEnumConstructor(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/Modifiers.jrag:437
   * @apilevel internal
   */
  public boolean Define_mayBeFinal(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:391
      return false;
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:770
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
    for (Problem value : modifierProblems()) {
      collection.add(value);
    }
  }

}
