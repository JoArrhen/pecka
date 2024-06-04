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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/grammar/Annotations.ast:6
 * @astdecl Annotation : Modifier ::= <ID:String> Access ElementValuePair*;
 * @production Annotation : {@link Modifier} ::= <span class="component">&lt;ID:{@link String}&gt;</span> <span class="component">{@link Access}</span> <span class="component">{@link ElementValuePair}*</span>;

 */
public class Annotation extends Modifier implements Cloneable {
  /**
   * @aspect Java5PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/PrettyPrint.jadd:35
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print("@");
    out.print(getAccess());
    if (hasElementValuePair()) {
      out.print("(");
      out.join(getElementValuePairList(), new PrettyPrinter.Joiner() {
        @Override
        public void printSeparator(PrettyPrinter out) {
          out.print(", ");
        }
      });
      out.print(")");
    }
  }
  /**
   * @declaredat ASTNode:1
   */
  public Annotation() {
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
    setChild(new List(), 1);
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"ID", "Access", "ElementValuePair"},
    type = {"String", "Access", "List<ElementValuePair>"},
    kind = {"Token", "Child", "List"}
  )
  public Annotation(String p0, Access p1, List<ElementValuePair> p2) {
    setID(p0);
    setChild(p1, 0);
    setChild(p2, 1);
  }
  /**
   * @declaredat ASTNode:24
   */
  public Annotation(beaver.Symbol p0, Access p1, List<ElementValuePair> p2) {
    setID(p0);
    setChild(p1, 0);
    setChild(p2, 1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:30
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:36
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:40
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    getKind_int_reset();
    isAnAttribute_reset();
    isCollectionAttribute_reset();
    isSynAttribute_reset();
    isInhAttribute_reset();
    isCircularAttribute_reset();
    decl_reset();
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
  public Annotation clone() throws CloneNotSupportedException {
    Annotation node = (Annotation) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:61
   */
  public Annotation copy() {
    try {
      Annotation node = (Annotation) clone();
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
  public Annotation fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:90
   */
  public Annotation treeCopyNoTransform() {
    Annotation tree = (Annotation) copy();
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
   * @declaredat ASTNode:110
   */
  public Annotation treeCopy() {
    Annotation tree = (Annotation) copy();
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
  public Annotation setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public Annotation setID(beaver.Symbol symbol) {
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
   * Replaces the Access child.
   * @param node The new node to replace the Access child.
   * @apilevel high-level
   */
  public Annotation setAccess(Access node) {
    setChild(node, 0);
    return this;
  }
  /**
   * Retrieves the Access child.
   * @return The current node used as the Access child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Access")
  public Access getAccess() {
    return (Access) getChild(0);
  }
  /**
   * Retrieves the Access child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Access child.
   * @apilevel low-level
   */
  public Access getAccessNoTransform() {
    return (Access) getChildNoTransform(0);
  }
  /**
   * Replaces the ElementValuePair list.
   * @param list The new list node to be used as the ElementValuePair list.
   * @apilevel high-level
   */
  public Annotation setElementValuePairList(List<ElementValuePair> list) {
    setChild(list, 1);
    return this;
  }
  /**
   * Retrieves the number of children in the ElementValuePair list.
   * @return Number of children in the ElementValuePair list.
   * @apilevel high-level
   */
  public int getNumElementValuePair() {
    return getElementValuePairList().getNumChild();
  }
  /**
   * Retrieves the number of children in the ElementValuePair list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the ElementValuePair list.
   * @apilevel low-level
   */
  public int getNumElementValuePairNoTransform() {
    return getElementValuePairListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the ElementValuePair list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the ElementValuePair list.
   * @apilevel high-level
   */
  public ElementValuePair getElementValuePair(int i) {
    return (ElementValuePair) getElementValuePairList().getChild(i);
  }
  /**
   * Check whether the ElementValuePair list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasElementValuePair() {
    return getElementValuePairList().getNumChild() != 0;
  }
  /**
   * Append an element to the ElementValuePair list.
   * @param node The element to append to the ElementValuePair list.
   * @apilevel high-level
   */
  public Annotation addElementValuePair(ElementValuePair node) {
    List<ElementValuePair> list = (parent == null) ? getElementValuePairListNoTransform() : getElementValuePairList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public Annotation addElementValuePairNoTransform(ElementValuePair node) {
    List<ElementValuePair> list = getElementValuePairListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the ElementValuePair list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public Annotation setElementValuePair(ElementValuePair node, int i) {
    List<ElementValuePair> list = getElementValuePairList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the ElementValuePair list.
   * @return The node representing the ElementValuePair list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="ElementValuePair")
  public List<ElementValuePair> getElementValuePairList() {
    List<ElementValuePair> list = (List<ElementValuePair>) getChild(1);
    return list;
  }
  /**
   * Retrieves the ElementValuePair list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the ElementValuePair list.
   * @apilevel low-level
   */
  public List<ElementValuePair> getElementValuePairListNoTransform() {
    return (List<ElementValuePair>) getChildNoTransform(1);
  }
  /**
   * @return the element at index {@code i} in the ElementValuePair list without
   * triggering rewrites.
   */
  public ElementValuePair getElementValuePairNoTransform(int i) {
    return (ElementValuePair) getElementValuePairListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the ElementValuePair list.
   * @return The node representing the ElementValuePair list.
   * @apilevel high-level
   */
  public List<ElementValuePair> getElementValuePairs() {
    return getElementValuePairList();
  }
  /**
   * Retrieves the ElementValuePair list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the ElementValuePair list.
   * @apilevel low-level
   */
  public List<ElementValuePair> getElementValuePairsNoTransform() {
    return getElementValuePairListNoTransform();
  }
  /** @apilevel internal */
  private void getKind_int_reset() {
    getKind_int_computed = null;
    getKind_int_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map getKind_int_values;
  /** @apilevel internal */
  protected java.util.Map getKind_int_computed;
  /**
   * @attribute syn
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:416
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:416")
  public String getKind(int i) {
    Object _parameters = i;
    if (getKind_int_computed == null) getKind_int_computed = new java.util.HashMap(4);
    if (getKind_int_values == null) getKind_int_values = new java.util.HashMap(4);
    ASTState state = state();
    if (getKind_int_values.containsKey(_parameters)
        && getKind_int_computed.containsKey(_parameters)
        && (getKind_int_computed.get(_parameters) == ASTState.NON_CYCLE || getKind_int_computed.get(_parameters) == state().cycle())) {
      return (String) getKind_int_values.get(_parameters);
    }
    String getKind_int_value = getKind_compute(i);
    if (state().inCircle()) {
      getKind_int_values.put(_parameters, getKind_int_value);
      getKind_int_computed.put(_parameters, state().cycle());
    
    } else {
      getKind_int_values.put(_parameters, getKind_int_value);
      getKind_int_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return getKind_int_value;
  }
  /** @apilevel internal */
  private String getKind_compute(int i) {
      try {
        ElementValuePair pair = getElementValuePair(i);
        ElementConstantValue ecv = (ElementConstantValue)pair.getElementValue();
        Expr e = ecv.getExpr();
        Dot d = (Dot)e;
        VarAccess va = (VarAccess)d.lastAccess();
        return va.getID();
      } catch (Throwable ignored) {
        try {
          ElementValuePair pair = getElementValuePair(i);
          return pair.getName();
        } catch (Throwable t) {
        }
        // it's not an attribute
      }
      return "";
    }
  /** @apilevel internal */
  private void isAnAttribute_reset() {
    isAnAttribute_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isAnAttribute_computed = null;

  /** @apilevel internal */
  protected boolean isAnAttribute_value;

  /**
   * @attribute syn
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:436
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:435")
  public boolean isAnAttribute() {
    ASTState state = state();
    if (isAnAttribute_computed == ASTState.NON_CYCLE || isAnAttribute_computed == state().cycle()) {
      return isAnAttribute_value;
    }
    isAnAttribute_value = isCollectionAttribute() || isSynAttribute() ||
                                      isInhAttribute() || isCircularAttribute();
    if (state().inCircle()) {
      isAnAttribute_computed = state().cycle();
    
    } else {
      isAnAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isAnAttribute_value;
  }
  /** @apilevel internal */
  private void isCollectionAttribute_reset() {
    isCollectionAttribute_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isCollectionAttribute_computed = null;

  /** @apilevel internal */
  protected boolean isCollectionAttribute_value;

  /**
   * @attribute syn
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:440
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:439")
  public boolean isCollectionAttribute() {
    ASTState state = state();
    if (isCollectionAttribute_computed == ASTState.NON_CYCLE || isCollectionAttribute_computed == state().cycle()) {
      return isCollectionAttribute_value;
    }
    isCollectionAttribute_value = getKind(0).equals("COLL");
    if (state().inCircle()) {
      isCollectionAttribute_computed = state().cycle();
    
    } else {
      isCollectionAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isCollectionAttribute_value;
  }
  /** @apilevel internal */
  private void isSynAttribute_reset() {
    isSynAttribute_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isSynAttribute_computed = null;

  /** @apilevel internal */
  protected boolean isSynAttribute_value;

  /**
   * @attribute syn
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:443
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:442")
  public boolean isSynAttribute() {
    ASTState state = state();
    if (isSynAttribute_computed == ASTState.NON_CYCLE || isSynAttribute_computed == state().cycle()) {
      return isSynAttribute_value;
    }
    isSynAttribute_value = getKind(0).equals("SYN");
    if (state().inCircle()) {
      isSynAttribute_computed = state().cycle();
    
    } else {
      isSynAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isSynAttribute_value;
  }
  /** @apilevel internal */
  private void isInhAttribute_reset() {
    isInhAttribute_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isInhAttribute_computed = null;

  /** @apilevel internal */
  protected boolean isInhAttribute_value;

  /**
   * @attribute syn
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:446
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:445")
  public boolean isInhAttribute() {
    ASTState state = state();
    if (isInhAttribute_computed == ASTState.NON_CYCLE || isInhAttribute_computed == state().cycle()) {
      return isInhAttribute_value;
    }
    isInhAttribute_value = getKind(0).equals("INH");
    if (state().inCircle()) {
      isInhAttribute_computed = state().cycle();
    
    } else {
      isInhAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isInhAttribute_value;
  }
  /** @apilevel internal */
  private void isCircularAttribute_reset() {
    isCircularAttribute_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isCircularAttribute_computed = null;

  /** @apilevel internal */
  protected boolean isCircularAttribute_value;

  /**
   * @attribute syn
   * @aspect AttributeKind
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:449
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AttributeKind", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:448")
  public boolean isCircularAttribute() {
    ASTState state = state();
    if (isCircularAttribute_computed == ASTState.NON_CYCLE || isCircularAttribute_computed == state().cycle()) {
      return isCircularAttribute_value;
    }
    isCircularAttribute_value = getKind(1).equals("isCircular");
    if (state().inCircle()) {
      isCircularAttribute_computed = state().cycle();
    
    } else {
      isCircularAttribute_computed = ASTState.NON_CYCLE;
    
    }
    return isCircularAttribute_value;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:71
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:71")
  public Collection<Problem> modifierProblems() {
    {
        if (inComplexAnnotation()) {
          return Collections.emptyList();
        }
        if (decl() instanceof AnnotationDecl) {
          AnnotationDecl T = (AnnotationDecl) decl();
          Annotation m = T.annotation(lookupType("java.lang.annotation", "Target"));
          if (m != null && m.getNumElementValuePair() == 1
              && m.getElementValuePair(0).getName().equals("value")) {
            ElementValue v = m.getElementValuePair(0).getElementValue();
            if (!v.validTarget(this)) {
              return Collections.singletonList(errorf(
                  "annotation type %s is not applicable to this kind of declaration",
                  T.typeName()));
            }
          }
        }
        return Collections.emptyList();
      }
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:344
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:342")
  public boolean isAnnotation(String packageName, String name) {
    boolean isAnnotation_String_String_value = decl().isType(packageName, name);
    return isAnnotation_String_String_value;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:368
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:368")
  public Collection<Problem> overrideProblems() {
    {
        if (decl().fullName().equals("java.lang.Override")
            && enclosingBodyDecl() instanceof MethodDecl) {
          MethodDecl method = (MethodDecl) enclosingBodyDecl();
          TypeDecl host = method.hostType();
          SimpleSet<MethodDecl> ancestors = host.ancestorMethods(method.signature());
          boolean found = false;
          for (MethodDecl decl : ancestors) {
            if (method.overrides(decl)) {
              found = true;
              break;
            }
          }
          if (!found) {
            TypeDecl typeObject = lookupType("java.lang", "Object");
            SimpleSet<MethodDecl> overrides = typeObject.localMethodsSignature(method.signature());
            if (overrides.isEmpty() || !overrides.iterator().next().isPublic()) {
              return Collections.singletonList(
                  error("method does not override a method from a supertype"));
            }
          }
        }
        return Collections.emptyList();
      }
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:555
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:555")
  public Collection<Problem> typeProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        if (!decl().isAnnotationDecl()) {
          /* TypeName names the annotation type corresponding to the annotation. It is a
          compile-time error if TypeName does not name an annotation type.*/
          if (!decl().isUnknown()) {
            problems.add(errorf("%s is not an annotation type", decl().typeName()));
          }
        } else {
          TypeDecl typeDecl = decl();
          /* It is a compile-time error if a declaration is annotated with more than one
          annotation for a given annotation type.*/
          if (lookupAnnotation(typeDecl) != this) {
            problems.add(errorf("duplicate annotation %s", typeDecl.typeName()));
          }
          /* Annotations must contain an element-value pair for every element of the
          corresponding annotation type, except for those elements with default
          values, or a compile-time error occurs. Annotations may, but are not
          required to, contain element-value pairs for elements with default values.*/
          for (int i = 0; i < typeDecl.getNumBodyDecl(); i++) {
            if (typeDecl.getBodyDecl(i) instanceof MethodDecl) {
              MethodDecl decl = (MethodDecl) typeDecl.getBodyDecl(i);
              if (elementValueFor(decl.name()) == null
                  && (!(decl instanceof AnnotationMethodDecl)
                      || !((AnnotationMethodDecl) decl).hasDefaultValue())) {
                problems.add(errorf("missing value for %s", decl.name()));
              }
            }
          }
          /* The Identifier in an ElementValuePair must be the simple name of one of the
          elements of the annotation type identified by TypeName in the containing
          annotation. Otherwise, a compile-time error occurs. (In other words, the
          identifier in an element-value pair must also be a method name in the interface
          identified by TypeName.) */
          for (int i = 0; i < getNumElementValuePair(); i++) {
            ElementValuePair pair = getElementValuePair(i);
            if (typeDecl.memberMethods(pair.getName()).isEmpty()) {
              problems.add(errorf("cannot find element named %s in %s",
                  pair.getName(), typeDecl.typeName()));
            }
          }
        }
        return problems;
      }
  }
  /** @apilevel internal */
  private void decl_reset() {
    decl_computed = null;
    decl_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle decl_computed = null;

  /** @apilevel internal */
  protected TypeDecl decl_value;

  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:600
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:600")
  public TypeDecl decl() {
    ASTState state = state();
    if (decl_computed == ASTState.NON_CYCLE || decl_computed == state().cycle()) {
      return decl_value;
    }
    decl_value = getAccess().type();
    if (state().inCircle()) {
      decl_computed = state().cycle();
    
    } else {
      decl_computed = ASTState.NON_CYCLE;
    
    }
    return decl_value;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:615
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:615")
  public ElementValue elementValueFor(String name) {
    {
        for (int i = 0; i < getNumElementValuePair(); i++) {
          ElementValuePair pair = getElementValuePair(i);
          if (pair.getName().equals(name)) {
            return pair.getElementValue();
          }
        }
        return null;
      }
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:737
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:737")
  public TypeDecl type() {
    TypeDecl type_value = getAccess().type();
    return type_value;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:767
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:767")
  public boolean isMetaAnnotation() {
    boolean isMetaAnnotation_value = hostType().isAnnotationDecl();
    return isMetaAnnotation_value;
  }
  /**
   * @return {@code true} if this annotation is used inside another annotation, i.e. it is
   * used in a complex annoation.
   * @attribute inh
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:95
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:95")
  public boolean inComplexAnnotation() {
    boolean inComplexAnnotation_value = getParent().Define_inComplexAnnotation(this, null);
    return inComplexAnnotation_value;
  }
  /**
   * @attribute inh
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:107
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:107")
  public TypeDecl lookupType(String packageName, String typeName) {
    TypeDecl lookupType_String_String_value = getParent().Define_lookupType(this, null, packageName, typeName);
    return lookupType_String_String_value;
  }
  /**
   * @attribute inh
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:131
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:131")
  public boolean mayUseAnnotationTarget(String name) {
    boolean mayUseAnnotationTarget_String_value = getParent().Define_mayUseAnnotationTarget(this, null, name);
    return mayUseAnnotationTarget_String_value;
  }
  /**
   * @attribute inh
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:390
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:390")
  public BodyDecl enclosingBodyDecl() {
    BodyDecl enclosingBodyDecl_value = getParent().Define_enclosingBodyDecl(this, null);
    return enclosingBodyDecl_value;
  }
  /**
   * @attribute inh
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:602
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:602")
  public Annotation lookupAnnotation(TypeDecl typeDecl) {
    Annotation lookupAnnotation_TypeDecl_value = getParent().Define_lookupAnnotation(this, null, typeDecl);
    return lookupAnnotation_TypeDecl_value;
  }
  /**
   * @attribute inh
   * @aspect Annotations
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:769
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:769")
  public TypeDecl hostType() {
    TypeDecl hostType_value = getParent().Define_hostType(this, null);
    return hostType_value;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:95
   * @apilevel internal
   */
  public boolean Define_inComplexAnnotation(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:95
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inComplexAnnotation
   */
  protected boolean canDefine_inComplexAnnotation(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:648
   * @apilevel internal
   */
  public TypeDecl Define_enclosingAnnotationDecl(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getElementValuePairListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:650
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return decl();
    }
    else {
      return getParent().Define_enclosingAnnotationDecl(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:648
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingAnnotationDecl
   */
  protected boolean canDefine_enclosingAnnotationDecl(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (getAccessNoTransform() != null && _callerNode == getAccess()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:776
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:65
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:366
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:553
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
    for (Problem value : overrideProblems()) {
      collection.add(value);
    }
    for (Problem value : typeProblems()) {
      collection.add(value);
    }
  }

}
