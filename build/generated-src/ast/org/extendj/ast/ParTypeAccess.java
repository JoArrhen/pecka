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
/** Parameterized type access. 
 * @ast node
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/grammar/Generics.ast:53
 * @astdecl ParTypeAccess : Access ::= TypeAccess:Access TypeArgument:Access*;
 * @production ParTypeAccess : {@link Access} ::= <span class="component">TypeAccess:{@link Access}</span> <span class="component">TypeArgument:{@link Access}*</span>;

 */
public class ParTypeAccess extends Access implements Cloneable {
  /**
   * @aspect Java5PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/PrettyPrint.jadd:332
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(getTypeAccess());
    out.print("<");
    out.join(getTypeArgumentList(), new PrettyPrinter.Joiner() {
      @Override
      public void printSeparator(PrettyPrinter out) {
        out.print(", ");
      }
    });
    out.print(">");
  }
  /**
   * @aspect GenericsTypeAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:453
   */
  public boolean isRaw() {
    return false;
  }
  /**
   * @aspect PrettyPrintUtil5
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/PrettyPrintUtil.jrag:103
   */
  @Override public String toString() {
    StringBuilder params = new StringBuilder();
    for (Access arg : getTypeArgumentListNoTransform()) {
      if (params.length() > 0) {
        params.append(", ");
      }
      params.append(arg.toString());
    }
    return String.format("%s<%s>", getTypeAccessNoTransform().toString(), params.toString());
  }
  /**
   * @aspect Diamond
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Diamond.jrag:272
   */
  @Override
  public Access substituted(Map<TypeVariable, String>  substitution) {
    List<Access> substArgs = new List<Access>();
    for (Access arg : getTypeArgumentList()) {
      substArgs.add(arg.substituted(substitution));
    }
    return new ParTypeAccess(getTypeAccess().substituted(substitution), substArgs);
  }
  /**
   * @aspect FunctionalInterface
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/FunctionalInterface.jrag:231
   */
  public boolean sameType(ParTypeAccess p) {
    TypeAccess ta1 = (TypeAccess) getTypeAccess();
    TypeAccess ta2 = (TypeAccess) p.getTypeAccess();
    if (!ta1.sameType(ta2)) {
      return false;
    }

    if (getNumTypeArgument() != p.getNumTypeArgument()) {
      return false;
    }

    for (int i = 0; i < getNumTypeArgument(); i++) {
      Access a1 = getTypeArgument(i);
      Access a2 = p.getTypeArgument(i);
      if (!a1.sameType(a2)) {
        return false;
      }
    }

    return true;
  }
  /**
   * @declaredat ASTNode:1
   */
  public ParTypeAccess() {
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
    name = {"TypeAccess", "TypeArgument"},
    type = {"Access", "List<Access>"},
    kind = {"Child", "List"}
  )
  public ParTypeAccess(Access p0, List<Access> p1) {
    setChild(p0, 0);
    setChild(p1, 1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:24
   */
  protected int numChildren() {
    return 2;
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
    type_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:39
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:44
   */
  public ParTypeAccess clone() throws CloneNotSupportedException {
    ParTypeAccess node = (ParTypeAccess) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:49
   */
  public ParTypeAccess copy() {
    try {
      ParTypeAccess node = (ParTypeAccess) clone();
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
   * @declaredat ASTNode:68
   */
  @Deprecated
  public ParTypeAccess fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:78
   */
  public ParTypeAccess treeCopyNoTransform() {
    ParTypeAccess tree = (ParTypeAccess) copy();
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
   * @declaredat ASTNode:98
   */
  public ParTypeAccess treeCopy() {
    ParTypeAccess tree = (ParTypeAccess) copy();
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
   * Replaces the TypeAccess child.
   * @param node The new node to replace the TypeAccess child.
   * @apilevel high-level
   */
  public ParTypeAccess setTypeAccess(Access node) {
    setChild(node, 0);
    return this;
  }
  /**
   * Retrieves the TypeAccess child.
   * @return The current node used as the TypeAccess child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="TypeAccess")
  public Access getTypeAccess() {
    return (Access) getChild(0);
  }
  /**
   * Retrieves the TypeAccess child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the TypeAccess child.
   * @apilevel low-level
   */
  public Access getTypeAccessNoTransform() {
    return (Access) getChildNoTransform(0);
  }
  /**
   * Replaces the TypeArgument list.
   * @param list The new list node to be used as the TypeArgument list.
   * @apilevel high-level
   */
  public ParTypeAccess setTypeArgumentList(List<Access> list) {
    setChild(list, 1);
    return this;
  }
  /**
   * Retrieves the number of children in the TypeArgument list.
   * @return Number of children in the TypeArgument list.
   * @apilevel high-level
   */
  public int getNumTypeArgument() {
    return getTypeArgumentList().getNumChild();
  }
  /**
   * Retrieves the number of children in the TypeArgument list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the TypeArgument list.
   * @apilevel low-level
   */
  public int getNumTypeArgumentNoTransform() {
    return getTypeArgumentListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the TypeArgument list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the TypeArgument list.
   * @apilevel high-level
   */
  public Access getTypeArgument(int i) {
    return (Access) getTypeArgumentList().getChild(i);
  }
  /**
   * Check whether the TypeArgument list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasTypeArgument() {
    return getTypeArgumentList().getNumChild() != 0;
  }
  /**
   * Append an element to the TypeArgument list.
   * @param node The element to append to the TypeArgument list.
   * @apilevel high-level
   */
  public ParTypeAccess addTypeArgument(Access node) {
    List<Access> list = (parent == null) ? getTypeArgumentListNoTransform() : getTypeArgumentList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public ParTypeAccess addTypeArgumentNoTransform(Access node) {
    List<Access> list = getTypeArgumentListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the TypeArgument list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public ParTypeAccess setTypeArgument(Access node, int i) {
    List<Access> list = getTypeArgumentList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the TypeArgument list.
   * @return The node representing the TypeArgument list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="TypeArgument")
  public List<Access> getTypeArgumentList() {
    List<Access> list = (List<Access>) getChild(1);
    return list;
  }
  /**
   * Retrieves the TypeArgument list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeArgument list.
   * @apilevel low-level
   */
  public List<Access> getTypeArgumentListNoTransform() {
    return (List<Access>) getChildNoTransform(1);
  }
  /**
   * @return the element at index {@code i} in the TypeArgument list without
   * triggering rewrites.
   */
  public Access getTypeArgumentNoTransform(int i) {
    return (Access) getTypeArgumentListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the TypeArgument list.
   * @return The node representing the TypeArgument list.
   * @apilevel high-level
   */
  public List<Access> getTypeArguments() {
    return getTypeArgumentList();
  }
  /**
   * Retrieves the TypeArgument list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeArgument list.
   * @apilevel low-level
   */
  public List<Access> getTypeArgumentsNoTransform() {
    return getTypeArgumentListNoTransform();
  }
  /**
   * @attribute syn
   * @aspect GenericsTypeAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:374
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupMethod", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:87")
  public Expr unqualifiedScope() {
    Expr unqualifiedScope_value = getParent() instanceof Access
          ? ((Access) getParent()).unqualifiedScope()
          : super.unqualifiedScope();
    return unqualifiedScope_value;
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
   * @aspect GenericsTypeAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:387
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:294")
  public TypeDecl type() {
    ASTState state = state();
    if (type_computed == ASTState.NON_CYCLE || type_computed == state().cycle()) {
      return type_value;
    }
    type_value = type_compute();
    if (state().inCircle()) {
      type_computed = state().cycle();
    
    } else {
      type_computed = ASTState.NON_CYCLE;
    
    }
    return type_value;
  }
  /** @apilevel internal */
  private TypeDecl type_compute() {
      TypeDecl typeDecl = genericDecl();
      if (typeDecl instanceof ParInterfaceDecl || typeDecl instanceof ParClassDecl) {
        typeDecl = typeDecl.original();
      }
      if (typeDecl instanceof GenericTypeDecl) {
        // Use signature in lookup for types that are used in extends and implements clauses.
        if (unqualifiedScope().inExtendsOrImplements()) {
          return ((GenericTypeDecl) typeDecl).lookupParTypeDecl(this);
        }
        ArrayList<TypeDecl> args = new ArrayList<TypeDecl>();
        for (Access argument : getTypeArgumentList()) {
          args.add(argument.type());
        }
        return ((GenericTypeDecl) typeDecl).lookupParTypeDecl(args);
      }
      return typeDecl;
    }
  /**
   * @attribute syn
   * @aspect GenericsTypeAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:406
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsTypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:406")
  public TypeDecl genericDecl() {
    TypeDecl genericDecl_value = getTypeAccess().type();
    return genericDecl_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsTypeAnalysis
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:408
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AccessTypes", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:35")
  public boolean isTypeAccess() {
    boolean isTypeAccess_value = true;
    return isTypeAccess_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsTypeCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:693
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsTypeCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:693")
  public Collection<Problem> typeProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        if (!genericDecl().isUnknown()) {
          TypeDecl type = type();
          if (!genericDecl().isGenericType()) {
            problems.add(errorf("%s is not a generic type but used as one in %s",
                genericDecl().typeName(), this.prettyPrint()));
          } else if (!type.isRawType() && type.isNestedType() && type.enclosingType().isRawType()) {
            problems.add(error("Cannot access a member type of a raw type as a parameterized type"));
          } else {
            ParTypeDecl decl = (ParTypeDecl) type;
            GenericTypeDecl original = (GenericTypeDecl) genericDecl().original();
            if (original.getNumTypeParameter() != getNumTypeArgument()) {
              problems.add(errorf("%s takes %d type parameters, not %d as used in %s",
                  original.typeName(), original.getNumTypeParameter(), getNumTypeArgument(),
                  this.prettyPrint()));
            } else {
              List<TypeVariable> typeParam = decl.getSubstTypeParamList();
              for (int i = 0; i < getNumTypeArgument(); i++) {
                if (!getTypeArgument(i).type().withinBounds(typeParam.getChild(i))) {
                  problems.add(errorf("type argument %d is of type %s which is not within"
                      + " the bounds of type parameter %s (%s)",
                      i + 1, getTypeArgument(i).type().typeName(),
                      original.getTypeParameter(i).typeName(),
                      original.getTypeParameter(i).prettyPrint()));
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
   * @aspect LookupParTypeDecl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1621
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:1617")
  public Access erasedCopy() {
    Access erasedCopy_value = getTypeAccess().erasedCopy();
    return erasedCopy_value;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:231
   * @apilevel internal
   */
  public SimpleSet<TypeDecl> Define_lookupType(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getTypeArgumentListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:379
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return unqualifiedScope().lookupType(name);
    }
    else {
      return getParent().Define_lookupType(this, _callerNode, name);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return NameType.TYPE_NAME;
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
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:691
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
    for (Problem value : typeProblems()) {
      collection.add(value);
    }
  }

}
