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
 * A class instance creation expression. Can optionally contain
 * a class declaration, resulting in an anonymous class.
 * 
 * <p> The Access child is either the class being constructed, or the supertype
 * for the anonymous class declaration.
 * @ast node
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:144
 * @astdecl ClassInstanceExpr : Access ::= Access Arg:Expr* [TypeDecl];
 * @production ClassInstanceExpr : {@link Access} ::= <span class="component">{@link Access}</span> <span class="component">Arg:{@link Expr}*</span> <span class="component">[{@link TypeDecl}]</span>;

 */
public class ClassInstanceExpr extends Access implements Cloneable, Invocable, AllocationSite, DeclarationSite {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrint.jadd:185
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print("new ");
    out.print(getAccess());
    out.print("(");
    out.join(getArgList(), new PrettyPrinter.Joiner() {
      @Override
      public void printSeparator(PrettyPrinter out) {
        out.print(", ");
      }
    });
    out.print(")");
    if (hasTypeDecl()) {
      if (hasPrintableBodyDecl()) {
        out.print(" {");
        out.println();
        out.indent(1);
        out.join(bodyDecls(), new PrettyPrinter.Joiner() {
          @Override
          public void printSeparator(PrettyPrinter out) {
            out.println();
          }
        });
        out.print("}");
      } else {
        out.print(" { }");
      }
    }
  }
  /**
   * @aspect ExceptionHandling
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:366
   */
  protected boolean reachedException(TypeDecl catchType) {
    ConstructorDecl decl = decl();
    for (Access exception : decl().getExceptionList()) {
      TypeDecl exceptionType = exception.type();
      if (catchType.mayCatch(exceptionType)) {
        return true;
      }
    }
    for (int i = 0; i < getNumArg(); i++) {
      if (getArg(i).reachedException(catchType)) {
        return true;
      }
    }
    return false;
  }
  /**
   * @aspect NodeConstructors
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NodeConstructors.jrag:88
   */
  public ClassInstanceExpr(Access type, List args) {
    this(type, args, new Opt());
  }
  /**
   * @aspect TypeScopePropagation
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:618
   */
  public SimpleSet<TypeDecl> keepInnerClasses(SimpleSet<TypeDecl> types) {
    SimpleSet<TypeDecl> result = emptySet();
    for (TypeDecl type : types) {
      if (type.isInnerType() && type.isClassDecl()) {
        result = result.add(type); // Note: fixed potential error found by type checking.
      }
    }
    return result;
  }
  /**
   * @aspect TypeCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:606
   */
  public void typeCheckEnclosingInstance(Collection<Problem> problems) {
    TypeDecl C = type();
    if (!C.isInnerClass()) {
      return;
    }

    TypeDecl enclosing = null;
    if (C.isAnonymous()) {
      if (noEnclosingInstance()) {
        enclosing = null;
      } else {
        enclosing = hostType();
      }
    } else if (C.isLocalClass()) {
      if (C.inStaticContext()) {
        enclosing = null;
      } else if (noEnclosingInstance()) {
        enclosing = unknownType();
      } else {
        TypeDecl nest = hostType();
        while (nest != null && !nest.subtype(C.enclosingType())) {
          nest = nest.enclosingType();
        }
        enclosing = nest;
      }
    } else if (C.isMemberType()) {
      if (!isQualified()) {
        if (noEnclosingInstance()) {
          problems.add(errorf("No enclosing instance to initialize %s with", C.typeName()));
          enclosing = unknownType();
        } else {
          TypeDecl nest = hostType();
          while (nest != null && !nest.subtype(C.enclosingType())) {
            if (nest.isStatic()) {
              problems.add(errorf("No enclosing instance to initialize %s with", C.typeName()));
              nest = unknownType();
              break;
            }
            nest = nest.enclosingType();
          }
          enclosing = nest == null ? unknownType() : nest;
        }
      } else {
        enclosing = enclosingInstance();
      }
    }
    if (enclosing != null) {
      if (enclosing.isUnknown()) {
        problems.add(errorf("No enclosing instance to initialize %s with", C.typeName()));
      } else if (!enclosing.subtype(C.enclosingType())) {
        problems.add(errorf("*** Cannot instantiate %s with the enclosing instance %s due to "
            + "incorrect enclosing instance",
            C.typeName(), enclosing.typeName()));
      } else if (!isQualified() && C.isMemberType()
          && inExplicitConstructorInvocation() && enclosing == hostType()) {
        problems.add(errorf("*** The innermost enclosing instance of type %s is this which is "
            + "not yet initialized here.",
            enclosing.typeName()));
      }
    }
  }
  /**
   * @aspect TypeCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:698
   */
  public void typeCheckAnonymousSuperclassEnclosingInstance(
      Collection<Problem> problems) {
    if (type().isAnonymous() && ((ClassDecl) type()).superclass().isInnerType()) {
      TypeDecl S = ((ClassDecl) type()).superclass();
      if (S.isLocalClass()) {
        if (S.inStaticContext()) {
        } else if (noEnclosingInstance()) {
          problems.add(errorf("*** No enclosing instance to class %s due to static context",
              type().typeName()));
        } else if (inExplicitConstructorInvocation()) {
          problems.add(errorf("*** No enclosing instance to superclass %s of %s since this is "
              + "not initialized yet",
              S.typeName(), type().typeName()));
        }
      } else if (S.isMemberType()) {
        if (!isQualified()) {
          // 15.9.2 2nd paragraph
          if (noEnclosingInstance()) {
            problems.add(errorf("*** No enclosing instance to class %s due to static context",
                type().typeName()));
          } else {
            TypeDecl nest = hostType();
            while (nest != null && !nest.subtype(S.enclosingType())) {
              nest = nest.enclosingType();
            }
            if (nest == null) {
              problems.add(errorf("*** No enclosing instance to superclass %s of %s",
                  S.typeName(), type().typeName()));
            } else if (inExplicitConstructorInvocation()) {
              problems.add(errorf("*** No enclosing instance to superclass %s of %s since this is "
                  + "not initialized yet",
                  S.typeName(), type().typeName()));
            }
          }
        }
      }
    }
  }
  /**
   * @declaredat ASTNode:1
   */
  public ClassInstanceExpr() {
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
    setChild(new Opt(), 2);
  }
  /**
   * @declaredat ASTNode:15
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Access", "Arg", "TypeDecl"},
    type = {"Access", "List<Expr>", "Opt<TypeDecl>"},
    kind = {"Child", "List", "Opt"}
  )
  public ClassInstanceExpr(Access p0, List<Expr> p1, Opt<TypeDecl> p2) {
    setChild(p0, 0);
    setChild(p1, 1);
    setChild(p2, 2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:26
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:32
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:36
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    decls_reset();
    decl_reset();
    assignedAfterInstance_Variable_reset();
    computeDAbefore_int_Variable_reset();
    unassignedAfterInstance_Variable_reset();
    unassignedAfter_Variable_reset();
    computeDUbefore_int_Variable_reset();
    localLookupType_String_reset();
    type_reset();
    isBooleanExpression_reset();
    isPolyExpression_reset();
    assignConversionTo_TypeDecl_reset();
    stmtCompatible_reset();
    compatibleStrictContext_TypeDecl_reset();
    compatibleLooseContext_TypeDecl_reset();
    allDecls_reset();
    enclosingTarget_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:57
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:62
   */
  public ClassInstanceExpr clone() throws CloneNotSupportedException {
    ClassInstanceExpr node = (ClassInstanceExpr) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:67
   */
  public ClassInstanceExpr copy() {
    try {
      ClassInstanceExpr node = (ClassInstanceExpr) clone();
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
   * @declaredat ASTNode:86
   */
  @Deprecated
  public ClassInstanceExpr fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:96
   */
  public ClassInstanceExpr treeCopyNoTransform() {
    ClassInstanceExpr tree = (ClassInstanceExpr) copy();
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
   * @declaredat ASTNode:116
   */
  public ClassInstanceExpr treeCopy() {
    ClassInstanceExpr tree = (ClassInstanceExpr) copy();
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
   * Replaces the Access child.
   * @param node The new node to replace the Access child.
   * @apilevel high-level
   */
  public ClassInstanceExpr setAccess(Access node) {
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
   * Replaces the Arg list.
   * @param list The new list node to be used as the Arg list.
   * @apilevel high-level
   */
  public ClassInstanceExpr setArgList(List<Expr> list) {
    setChild(list, 1);
    return this;
  }
  /**
   * Retrieves the number of children in the Arg list.
   * @return Number of children in the Arg list.
   * @apilevel high-level
   */
  public int getNumArg() {
    return getArgList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Arg list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the Arg list.
   * @apilevel low-level
   */
  public int getNumArgNoTransform() {
    return getArgListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Arg list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Arg list.
   * @apilevel high-level
   */
  public Expr getArg(int i) {
    return (Expr) getArgList().getChild(i);
  }
  /**
   * Check whether the Arg list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasArg() {
    return getArgList().getNumChild() != 0;
  }
  /**
   * Append an element to the Arg list.
   * @param node The element to append to the Arg list.
   * @apilevel high-level
   */
  public ClassInstanceExpr addArg(Expr node) {
    List<Expr> list = (parent == null) ? getArgListNoTransform() : getArgList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public ClassInstanceExpr addArgNoTransform(Expr node) {
    List<Expr> list = getArgListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the Arg list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public ClassInstanceExpr setArg(Expr node, int i) {
    List<Expr> list = getArgList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the Arg list.
   * @return The node representing the Arg list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Arg")
  public List<Expr> getArgList() {
    List<Expr> list = (List<Expr>) getChild(1);
    return list;
  }
  /**
   * Retrieves the Arg list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Arg list.
   * @apilevel low-level
   */
  public List<Expr> getArgListNoTransform() {
    return (List<Expr>) getChildNoTransform(1);
  }
  /**
   * @return the element at index {@code i} in the Arg list without
   * triggering rewrites.
   */
  public Expr getArgNoTransform(int i) {
    return (Expr) getArgListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Arg list.
   * @return The node representing the Arg list.
   * @apilevel high-level
   */
  public List<Expr> getArgs() {
    return getArgList();
  }
  /**
   * Retrieves the Arg list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Arg list.
   * @apilevel low-level
   */
  public List<Expr> getArgsNoTransform() {
    return getArgListNoTransform();
  }
  /**
   * Replaces the optional node for the TypeDecl child. This is the <code>Opt</code>
   * node containing the child TypeDecl, not the actual child!
   * @param opt The new node to be used as the optional node for the TypeDecl child.
   * @apilevel low-level
   */
  public ClassInstanceExpr setTypeDeclOpt(Opt<TypeDecl> opt) {
    setChild(opt, 2);
    return this;
  }
  /**
   * Replaces the (optional) TypeDecl child.
   * @param node The new node to be used as the TypeDecl child.
   * @apilevel high-level
   */
  public ClassInstanceExpr setTypeDecl(TypeDecl node) {
    getTypeDeclOpt().setChild(node, 0);
    return this;
  }
  /**
   * Check whether the optional TypeDecl child exists.
   * @return {@code true} if the optional TypeDecl child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasTypeDecl() {
    return getTypeDeclOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) TypeDecl child.
   * @return The TypeDecl child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public TypeDecl getTypeDecl() {
    return (TypeDecl) getTypeDeclOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for the TypeDecl child. This is the <code>Opt</code> node containing the child TypeDecl, not the actual child!
   * @return The optional node for child the TypeDecl child.
   * @apilevel low-level
   */
  @ASTNodeAnnotation.OptChild(name="TypeDecl")
  public Opt<TypeDecl> getTypeDeclOpt() {
    return (Opt<TypeDecl>) getChild(2);
  }
  /**
   * Retrieves the optional node for child TypeDecl. This is the <code>Opt</code> node containing the child TypeDecl, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child TypeDecl.
   * @apilevel low-level
   */
  public Opt<TypeDecl> getTypeDeclOptNoTransform() {
    return (Opt<TypeDecl>) getChildNoTransform(2);
  }
  /**
   * @aspect NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:214
   */
  private Collection<Problem> refined_NameCheck_ClassInstanceExpr_nameProblems()
{
    Collection<Problem> problems = new LinkedList<Problem>();
    if (decls().isEmpty()) {
      problems.add(errorf("cannot instantiate %s no matching constructor found in %s",
          type().typeName(), type().typeName()));
    } else if (decls().size() > 1 && validArgs()) {
      problems.add(error("several most specific constructors found"));
      for (ConstructorDecl cons : decls()) {
        problems.add(errorf("         %s", cons.signature()));
      }
    } else if (!hasTypeDecl()) {
      // Check if the constructor is accessible (stricter when not in a class
      // instance expression) if constructor is private it cannot be accessed
      // outside the host class or a subtype of it.
      ConstructorDecl decl = decl();
      if (decl.isProtected() && !hostPackage().equals(decl.hostPackage()) &&
          !hostType().subtype(decl.hostType())) {
        problems.add(errorf("cannot access the constructor %s", this.prettyPrint()));
      }
    }
    return problems;
  }
  /**
   * @attribute syn
   * @aspect ConstructScope
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:90
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:85")
  public boolean applicableAndAccessible(ConstructorDecl decl) {
    boolean applicableAndAccessible_ConstructorDecl_value = decl.applicable(getArgList()) && decl.accessibleFrom(hostType())
          && (!decl.isProtected() || hasTypeDecl() || decl.hostPackage().equals(hostPackage()));
    return applicableAndAccessible_ConstructorDecl_value;
  }
  /** @apilevel internal */
  private void decls_reset() {
    decls_computed = null;
    decls_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle decls_computed = null;

  /** @apilevel internal */
  protected SimpleSet<ConstructorDecl> decls_value;

  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:87
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:112")
  public SimpleSet<ConstructorDecl> decls() {
    ASTState state = state();
    if (decls_computed == ASTState.NON_CYCLE || decls_computed == state().cycle()) {
      return decls_value;
    }
    decls_value = decls_compute();
    if (state().inCircle()) {
      decls_computed = state().cycle();
    
    } else {
      decls_computed = ASTState.NON_CYCLE;
    
    }
    return decls_value;
  }
  /** @apilevel internal */
  private SimpleSet<ConstructorDecl> decls_compute() {
      TypeDecl typeDecl = hasTypeDecl() ? getTypeDecl() : getAccess().type();
      return chooseConstructor(typeDecl.constructors(), getArgList());
    }
  /** @apilevel internal */
  private void decl_reset() {
    decl_computed = null;
    decl_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle decl_computed = null;

  /** @apilevel internal */
  protected ConstructorDecl decl_value;

  /**
   * @attribute syn
   * @aspect ConstructScope
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:117
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:117")
  public ConstructorDecl decl() {
    ASTState state = state();
    if (decl_computed == ASTState.NON_CYCLE || decl_computed == state().cycle()) {
      return decl_value;
    }
    decl_value = decl_compute();
    if (state().inCircle()) {
      decl_computed = state().cycle();
    
    } else {
      decl_computed = ASTState.NON_CYCLE;
    
    }
    return decl_value;
  }
  /** @apilevel internal */
  private ConstructorDecl decl_compute() {
      SimpleSet<ConstructorDecl> decls = decls();
      if (decls.isSingleton()) {
        return decls.singletonValue();
      } else {
        return unknownConstructor();
      }
    }
  /**
   * @attribute syn
   * @aspect ExceptionHandling
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:166
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:166")
  public Collection<Problem> exceptionHandlingProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        for (Access exception : decl().getExceptionList()) {
          TypeDecl exceptionType = exception.type();
          if (exceptionType.isCheckedException() && !handlesException(exceptionType)) {
            problems.add(errorf(
                "%s may throw uncaught exception %s; it must be caught or declared as being thrown",
                this.prettyPrint(), exceptionType.fullName()));
          }
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect NameResolution
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:562
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameResolution", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:556")
  public boolean containsParseName() {
    boolean containsParseName_value = getAccess().containsParseName();
    return containsParseName_value;
  }
  /**
   * @attribute syn
   * @aspect AccessControl
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AccessControl.jrag:174
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AccessControl", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AccessControl.jrag:174")
  public Collection<Problem> accessControlProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        if (type().isAbstract()) {
          problems.add(errorf("cannot instantiate abstract class %s", type().fullName()));
        }
        if (!decl().accessibleFrom(hostType())) {
          problems.add(errorf("constructor %s is not accessible", decl().signature()));
        }
        return problems;
      }
  }

  /** @apilevel internal */
  private void assignedAfterInstance_Variable_reset() {
    assignedAfterInstance_Variable_values = null;
  }
  protected java.util.Map assignedAfterInstance_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:522")
  public boolean assignedAfterInstance(Variable v) {
    Object _parameters = v;


    if (assignedAfterInstance_Variable_values == null) assignedAfterInstance_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (assignedAfterInstance_Variable_values.containsKey(_parameters)) {
      Object _cache = assignedAfterInstance_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      assignedAfterInstance_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_assignedAfterInstance_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_assignedAfterInstance_Variable_value = assignedAfterInstance_compute(v);
        if (((Boolean)_value.value) != new_assignedAfterInstance_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_assignedAfterInstance_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      assignedAfterInstance_Variable_values.put(_parameters, new_assignedAfterInstance_Variable_value);
      state.startLastCycle();
      boolean $tmp = assignedAfterInstance_compute(v);

      state.leaveCircle();
      return new_assignedAfterInstance_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_assignedAfterInstance_Variable_value = assignedAfterInstance_compute(v);
      if (state.lastCycle()) {
        assignedAfterInstance_Variable_values.put(_parameters, new_assignedAfterInstance_Variable_value);
      }
      if (((Boolean)_value.value) != new_assignedAfterInstance_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_assignedAfterInstance_Variable_value;
      }
      return new_assignedAfterInstance_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /** @apilevel internal */
  private boolean assignedAfterInstance_compute(Variable v) {
      if (getNumArg() == 0) {
        return assignedBefore(v);
      }
      return getArg(getNumArg()-1).assignedAfter(v);
    }
  /**
   * @attribute syn
   * @aspect DefiniteAssignment
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:529
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:268")
  public boolean assignedAfter(Variable v) {
    boolean assignedAfter_Variable_value = assignedAfterInstance(v);
    return assignedAfter_Variable_value;
  }

  /** @apilevel internal */
  private void computeDAbefore_int_Variable_reset() {
    computeDAbefore_int_Variable_values = null;
  }
  protected java.util.Map computeDAbefore_int_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:533")
  public boolean computeDAbefore(int i, Variable v) {
    java.util.List _parameters = new java.util.ArrayList(2);
    _parameters.add(i);
    _parameters.add(v);


    if (computeDAbefore_int_Variable_values == null) computeDAbefore_int_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (computeDAbefore_int_Variable_values.containsKey(_parameters)) {
      Object _cache = computeDAbefore_int_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      computeDAbefore_int_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_computeDAbefore_int_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_computeDAbefore_int_Variable_value = i == 0 ? assignedBefore(v) : getArg(i-1).assignedAfter(v);
        if (((Boolean)_value.value) != new_computeDAbefore_int_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_computeDAbefore_int_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      computeDAbefore_int_Variable_values.put(_parameters, new_computeDAbefore_int_Variable_value);
      state.startLastCycle();
      boolean $tmp = i == 0 ? assignedBefore(v) : getArg(i-1).assignedAfter(v);

      state.leaveCircle();
      return new_computeDAbefore_int_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_computeDAbefore_int_Variable_value = i == 0 ? assignedBefore(v) : getArg(i-1).assignedAfter(v);
      if (state.lastCycle()) {
        computeDAbefore_int_Variable_values.put(_parameters, new_computeDAbefore_int_Variable_value);
      }
      if (((Boolean)_value.value) != new_computeDAbefore_int_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_computeDAbefore_int_Variable_value;
      }
      return new_computeDAbefore_int_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }

  /** @apilevel internal */
  private void unassignedAfterInstance_Variable_reset() {
    unassignedAfterInstance_Variable_values = null;
  }
  protected java.util.Map unassignedAfterInstance_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:1132")
  public boolean unassignedAfterInstance(Variable v) {
    Object _parameters = v;


    if (unassignedAfterInstance_Variable_values == null) unassignedAfterInstance_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (unassignedAfterInstance_Variable_values.containsKey(_parameters)) {
      Object _cache = unassignedAfterInstance_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      unassignedAfterInstance_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_unassignedAfterInstance_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_unassignedAfterInstance_Variable_value = unassignedAfterInstance_compute(v);
        if (((Boolean)_value.value) != new_unassignedAfterInstance_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfterInstance_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfterInstance_Variable_values.put(_parameters, new_unassignedAfterInstance_Variable_value);
      state.startLastCycle();
      boolean $tmp = unassignedAfterInstance_compute(v);

      state.leaveCircle();
      return new_unassignedAfterInstance_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfterInstance_Variable_value = unassignedAfterInstance_compute(v);
      if (state.lastCycle()) {
        unassignedAfterInstance_Variable_values.put(_parameters, new_unassignedAfterInstance_Variable_value);
      }
      if (((Boolean)_value.value) != new_unassignedAfterInstance_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_unassignedAfterInstance_Variable_value;
      }
      return new_unassignedAfterInstance_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /** @apilevel internal */
  private boolean unassignedAfterInstance_compute(Variable v) {
      if (getNumArg() == 0) {
        return unassignedBefore(v);
      } else {
        return getArg(getNumArg()-1).unassignedAfter(v);
      }
    }

  /** @apilevel internal */
  private void unassignedAfter_Variable_reset() {
    unassignedAfter_Variable_values = null;
  }
  protected java.util.Map unassignedAfter_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:899")
  public boolean unassignedAfter(Variable v) {
    Object _parameters = v;


    if (unassignedAfter_Variable_values == null) unassignedAfter_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (unassignedAfter_Variable_values.containsKey(_parameters)) {
      Object _cache = unassignedAfter_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      unassignedAfter_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_unassignedAfter_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_unassignedAfter_Variable_value = unassignedAfterInstance(v);
        if (((Boolean)_value.value) != new_unassignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfter_Variable_values.put(_parameters, new_unassignedAfter_Variable_value);
      state.startLastCycle();
      boolean $tmp = unassignedAfterInstance(v);

      state.leaveCircle();
      return new_unassignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfter_Variable_value = unassignedAfterInstance(v);
      if (state.lastCycle()) {
        unassignedAfter_Variable_values.put(_parameters, new_unassignedAfter_Variable_value);
      }
      if (((Boolean)_value.value) != new_unassignedAfter_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_unassignedAfter_Variable_value;
      }
      return new_unassignedAfter_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }

  /** @apilevel internal */
  private void computeDUbefore_int_Variable_reset() {
    computeDUbefore_int_Variable_values = null;
  }
  protected java.util.Map computeDUbefore_int_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:1144")
  public boolean computeDUbefore(int i, Variable v) {
    java.util.List _parameters = new java.util.ArrayList(2);
    _parameters.add(i);
    _parameters.add(v);


    if (computeDUbefore_int_Variable_values == null) computeDUbefore_int_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (computeDUbefore_int_Variable_values.containsKey(_parameters)) {
      Object _cache = computeDUbefore_int_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      computeDUbefore_int_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_computeDUbefore_int_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_computeDUbefore_int_Variable_value = i == 0 ? unassignedBefore(v) : getArg(i-1).unassignedAfter(v);
        if (((Boolean)_value.value) != new_computeDUbefore_int_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_computeDUbefore_int_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      computeDUbefore_int_Variable_values.put(_parameters, new_computeDUbefore_int_Variable_value);
      state.startLastCycle();
      boolean $tmp = i == 0 ? unassignedBefore(v) : getArg(i-1).unassignedAfter(v);

      state.leaveCircle();
      return new_computeDUbefore_int_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_computeDUbefore_int_Variable_value = i == 0 ? unassignedBefore(v) : getArg(i-1).unassignedAfter(v);
      if (state.lastCycle()) {
        computeDUbefore_int_Variable_values.put(_parameters, new_computeDUbefore_int_Variable_value);
      }
      if (((Boolean)_value.value) != new_computeDUbefore_int_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_computeDUbefore_int_Variable_value;
      }
      return new_computeDUbefore_int_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /**
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:632
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeScopePropagation", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:632")
  public SimpleSet<TypeDecl> qualifiedLookupType(String name) {
    {
        SimpleSet<TypeDecl> result = keepAccessibleTypes(type().memberTypes(name));
        if (!result.isEmpty()) {
          return result;
        }
        if (type().name().equals(name)) {
          return type();
        }
        return emptySet();
      }
  }
  /** @apilevel internal */
  private void localLookupType_String_reset() {
    localLookupType_String_computed = null;
    localLookupType_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map localLookupType_String_values;
  /** @apilevel internal */
  protected java.util.Map localLookupType_String_computed;
  /**
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:671
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeScopePropagation", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:671")
  public SimpleSet<TypeDecl> localLookupType(String name) {
    Object _parameters = name;
    if (localLookupType_String_computed == null) localLookupType_String_computed = new java.util.HashMap(4);
    if (localLookupType_String_values == null) localLookupType_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (localLookupType_String_values.containsKey(_parameters)
        && localLookupType_String_computed.containsKey(_parameters)
        && (localLookupType_String_computed.get(_parameters) == ASTState.NON_CYCLE || localLookupType_String_computed.get(_parameters) == state().cycle())) {
      return (SimpleSet<TypeDecl>) localLookupType_String_values.get(_parameters);
    }
    SimpleSet<TypeDecl> localLookupType_String_value = hasTypeDecl() && getTypeDecl().name().equals(name)
          ? getTypeDecl()
          : ASTNode.<TypeDecl>emptySet();
    if (state().inCircle()) {
      localLookupType_String_values.put(_parameters, localLookupType_String_value);
      localLookupType_String_computed.put(_parameters, state().cycle());
    
    } else {
      localLookupType_String_values.put(_parameters, localLookupType_String_value);
      localLookupType_String_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return localLookupType_String_value;
  }
  /**
   * @attribute syn
   * @aspect TypeCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:586
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:586")
  public Collection<Problem> typeProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        if (isQualified() && qualifier().isTypeAccess() && !qualifier().type().isUnknown()) {
          problems.add(error(
              "*** The expression in a qualified class instance expr must not be a type name"));
        }
        // 15.9
        if (isQualified() && !type().isInnerClass()
            && !((ClassDecl) type()).superclass().isInnerClass() && !type().isUnknown()) {
          problems.add(error("*** Qualified class instance creation can only instantiate inner "
              + "classes and their anonymous subclasses"));
        }
        if (!type().isClassDecl()) {
          problems.add(errorf("*** Can only instantiate classes, which %s is not", type().typeName()));
        }
        typeCheckEnclosingInstance(problems);
        typeCheckAnonymousSuperclassEnclosingInstance(problems);
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect TypeCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:695
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:695")
  public boolean noEnclosingInstance() {
    boolean noEnclosingInstance_value = isQualified() ? qualifier().staticContextQualifier() : inStaticContext();
    return noEnclosingInstance_value;
  }
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:203
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:203")
  public boolean validArgs() {
    {
        for (int i = 0; i < getNumArg(); i++) {
          if (!getArg(i).isPolyExpression() && getArg(i).type().isUnknown()) {
            return false;
          }
        }
        return true;
      }
  }
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:214
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:214")
  public Collection<Problem> nameProblems() {
    {
        if (getAccess().type().isEnumDecl() && !enclosingBodyDecl().isEnumConstant()) {
          return Collections.singletonList(error("enum types may not be instantiated explicitly"));
        } else {
          return refined_NameCheck_ClassInstanceExpr_nameProblems();
        }
      }
  }
  /**
   * @attribute syn
   * @aspect SyntacticClassification
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:120
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SyntacticClassification", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:60")
  public NameType predNameType() {
    NameType predNameType_value = NameType.EXPRESSION_NAME;
    return predNameType_value;
  }
  /**
   * @return <code>true</code> if there is any printable body decl
   * @attribute syn
   * @aspect PrettyPrintUtil
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:311
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PrettyPrintUtil", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:311")
  public boolean hasPrintableBodyDecl() {
    {
        TypeDecl decl = getTypeDecl();
        for (int i = 0; i < decl.getNumBodyDecl(); ++i) {
          if (decl.getBodyDecl(i) instanceof ConstructorDecl) {
            ConstructorDecl cd = (ConstructorDecl) decl.getBodyDecl(i);
            if (!cd.isImplicitConstructor()) {
              return true;
            }
          } else {
            return true;
          }
        }
        return false;
      }
  }
  /**
   * @attribute syn
   * @aspect PrettyPrintUtil
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:326
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PrettyPrintUtil", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:326")
  public List<BodyDecl> bodyDecls() {
    List<BodyDecl> bodyDecls_value = getTypeDecl().getBodyDeclList();
    return bodyDecls_value;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:331
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:294")
  public TypeDecl type() {
    ASTState state = state();
    if (type_computed == ASTState.NON_CYCLE || type_computed == state().cycle()) {
      return type_value;
    }
    type_value = hasTypeDecl() ? getTypeDecl() : getAccess().type();
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:85
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableArityParameters", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/VariableArityParameters.jrag:85")
  public boolean invokesVariableArityAsArray() {
    {
        if (!decl().isVariableArity()) {
          return false;
        }
        if (arity() != decl().arity()) {
          return false;
        }
        return getArg(getNumArg()-1).type().methodInvocationConversionTo(decl().lastParameter().type());
      }
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:697
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:697")
  public int arity() {
    int arity_value = getNumArg();
    return arity_value;
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:149
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/PreciseRethrow.jrag:149")
  public boolean modifiedInScope(Variable var) {
    {
        for (int i = 0; i < getNumArg(); ++i) {
          if (getArg(i).modifiedInScope(var)) {
            return true;
          }
        }
        if (hasTypeDecl()) {
          return getTypeDecl().modifiedInScope(var);
        } else {
          return false;
        }
      }
  }
  /** @apilevel internal */
  private void isBooleanExpression_reset() {
    isBooleanExpression_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isBooleanExpression_computed = null;

  /** @apilevel internal */
  protected boolean isBooleanExpression_value;

  /**
   * @attribute syn
   * @aspect PolyExpressions
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:35
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PolyExpressions", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:29")
  public boolean isBooleanExpression() {
    ASTState state = state();
    if (isBooleanExpression_computed == ASTState.NON_CYCLE || isBooleanExpression_computed == state().cycle()) {
      return isBooleanExpression_value;
    }
    isBooleanExpression_value = isBooleanExpression_compute();
    if (state().inCircle()) {
      isBooleanExpression_computed = state().cycle();
    
    } else {
      isBooleanExpression_computed = ASTState.NON_CYCLE;
    
    }
    return isBooleanExpression_value;
  }
  /** @apilevel internal */
  private boolean isBooleanExpression_compute() {
      if (getAccess() instanceof TypeAccess) {
        TypeAccess typeAccess = (TypeAccess) getAccess();
        return typeAccess.name().equals("Boolean");
      }
      return false;
    }
  /** @apilevel internal */
  private void isPolyExpression_reset() {
    isPolyExpression_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle isPolyExpression_computed = null;

  /** @apilevel internal */
  protected boolean isPolyExpression_value;

  /**
   * @attribute syn
   * @aspect PolyExpressions
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:92
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PolyExpressions", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:86")
  public boolean isPolyExpression() {
    ASTState state = state();
    if (isPolyExpression_computed == ASTState.NON_CYCLE || isPolyExpression_computed == state().cycle()) {
      return isPolyExpression_value;
    }
    isPolyExpression_value = (getAccess() instanceof DiamondAccess) && (assignmentContext() || invocationContext());
    if (state().inCircle()) {
      isPolyExpression_computed = state().cycle();
    
    } else {
      isPolyExpression_computed = ASTState.NON_CYCLE;
    
    }
    return isPolyExpression_value;
  }
  /** @apilevel internal */
  private void assignConversionTo_TypeDecl_reset() {
    assignConversionTo_TypeDecl_computed = null;
    assignConversionTo_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map assignConversionTo_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map assignConversionTo_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect PolyExpressions
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:186
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PolyExpressions", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PolyExpressions.jrag:149")
  public boolean assignConversionTo(TypeDecl type) {
    Object _parameters = type;
    if (assignConversionTo_TypeDecl_computed == null) assignConversionTo_TypeDecl_computed = new java.util.HashMap(4);
    if (assignConversionTo_TypeDecl_values == null) assignConversionTo_TypeDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (assignConversionTo_TypeDecl_values.containsKey(_parameters)
        && assignConversionTo_TypeDecl_computed.containsKey(_parameters)
        && (assignConversionTo_TypeDecl_computed.get(_parameters) == ASTState.NON_CYCLE || assignConversionTo_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) assignConversionTo_TypeDecl_values.get(_parameters);
    }
    boolean assignConversionTo_TypeDecl_value = assignConversionTo_compute(type);
    if (state().inCircle()) {
      assignConversionTo_TypeDecl_values.put(_parameters, assignConversionTo_TypeDecl_value);
      assignConversionTo_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      assignConversionTo_TypeDecl_values.put(_parameters, assignConversionTo_TypeDecl_value);
      assignConversionTo_TypeDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return assignConversionTo_TypeDecl_value;
  }
  /** @apilevel internal */
  private boolean assignConversionTo_compute(TypeDecl type) {
      if (!isPolyExpression()) {
        return super.assignConversionTo(type);
      } else {
        return ((DiamondAccess) getAccess()).getTypeAccess().type().assignConversionTo(
            type, ((DiamondAccess) getAccess()).getTypeAccess());
      }
    }
  /** @apilevel internal */
  private void stmtCompatible_reset() {
    stmtCompatible_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle stmtCompatible_computed = null;

  /** @apilevel internal */
  protected boolean stmtCompatible_value;

  /**
   * @attribute syn
   * @aspect StmtCompatible
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:147
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StmtCompatible", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LambdaExpr.jrag:145")
  public boolean stmtCompatible() {
    ASTState state = state();
    if (stmtCompatible_computed == ASTState.NON_CYCLE || stmtCompatible_computed == state().cycle()) {
      return stmtCompatible_value;
    }
    stmtCompatible_value = true;
    if (state().inCircle()) {
      stmtCompatible_computed = state().cycle();
    
    } else {
      stmtCompatible_computed = ASTState.NON_CYCLE;
    
    }
    return stmtCompatible_value;
  }
  /** @apilevel internal */
  private void compatibleStrictContext_TypeDecl_reset() {
    compatibleStrictContext_TypeDecl_computed = null;
    compatibleStrictContext_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map compatibleStrictContext_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map compatibleStrictContext_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:95
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:58")
  public boolean compatibleStrictContext(TypeDecl type) {
    Object _parameters = type;
    if (compatibleStrictContext_TypeDecl_computed == null) compatibleStrictContext_TypeDecl_computed = new java.util.HashMap(4);
    if (compatibleStrictContext_TypeDecl_values == null) compatibleStrictContext_TypeDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (compatibleStrictContext_TypeDecl_values.containsKey(_parameters)
        && compatibleStrictContext_TypeDecl_computed.containsKey(_parameters)
        && (compatibleStrictContext_TypeDecl_computed.get(_parameters) == ASTState.NON_CYCLE || compatibleStrictContext_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) compatibleStrictContext_TypeDecl_values.get(_parameters);
    }
    boolean compatibleStrictContext_TypeDecl_value = isPolyExpression()
          ? assignConversionTo(type)
          : super.compatibleStrictContext(type);
    if (state().inCircle()) {
      compatibleStrictContext_TypeDecl_values.put(_parameters, compatibleStrictContext_TypeDecl_value);
      compatibleStrictContext_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      compatibleStrictContext_TypeDecl_values.put(_parameters, compatibleStrictContext_TypeDecl_value);
      compatibleStrictContext_TypeDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return compatibleStrictContext_TypeDecl_value;
  }
  /** @apilevel internal */
  private void compatibleLooseContext_TypeDecl_reset() {
    compatibleLooseContext_TypeDecl_computed = null;
    compatibleLooseContext_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map compatibleLooseContext_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map compatibleLooseContext_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:123
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:102")
  public boolean compatibleLooseContext(TypeDecl type) {
    Object _parameters = type;
    if (compatibleLooseContext_TypeDecl_computed == null) compatibleLooseContext_TypeDecl_computed = new java.util.HashMap(4);
    if (compatibleLooseContext_TypeDecl_values == null) compatibleLooseContext_TypeDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (compatibleLooseContext_TypeDecl_values.containsKey(_parameters)
        && compatibleLooseContext_TypeDecl_computed.containsKey(_parameters)
        && (compatibleLooseContext_TypeDecl_computed.get(_parameters) == ASTState.NON_CYCLE || compatibleLooseContext_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) compatibleLooseContext_TypeDecl_values.get(_parameters);
    }
    boolean compatibleLooseContext_TypeDecl_value = isPolyExpression()
          ? assignConversionTo(type)
          : super.compatibleLooseContext(type);
    if (state().inCircle()) {
      compatibleLooseContext_TypeDecl_values.put(_parameters, compatibleLooseContext_TypeDecl_value);
      compatibleLooseContext_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      compatibleLooseContext_TypeDecl_values.put(_parameters, compatibleLooseContext_TypeDecl_value);
      compatibleLooseContext_TypeDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return compatibleLooseContext_TypeDecl_value;
  }
  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:50
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:50")
  public ASTNode getNode() {
    ASTNode getNode_value = this;
    return getNode_value;
  }
  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:70
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:70")
  public String typeName() {
    String typeName_value = type().getID();
    return typeName_value;
  }
  /**
   * @attribute syn
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:81
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:81")
  public boolean isAllocationSite() {
    boolean isAllocationSite_value = true;
    return isAllocationSite_value;
  }
  /**
   * @attribute syn
   * @aspect Utilities
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:298
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:318
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Utilities", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:318")
  public String toString() {
    String toString_value = "alloc " + type().getID() + " line " + lineNumber();
    return toString_value;
  }
  /**
   * @attribute syn
   * @aspect MethodCall
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:27
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodCall", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:27")
  public Set<InclusionEdge> inclusionEdges() {
    {
            Set<InclusionEdge> res = new HashSet<>();
            List<Expr> arguments = getArgList();
    
            for (InvocationTarget it : allDecls()) {
                ConstructorDecl decl = (ConstructorDecl) it;
                List<ParameterDeclaration> parameters = decl.getParameterList();
    
                for (int i = 0; i < arguments.getNumChild(); i++) {
                    int parameterIndex = Math.min(i, parameters.getNumChild() - 1);
                    ParameterDeclaration parameter = parameters.getChild(parameterIndex);
                    if (arguments.getChild(i).hasDeclaration() && !parameter.isVariableArity()) {
                        res.add(new InclusionEdge(arguments.getChild(i).getDeclaration(), parameters.getChild(parameterIndex).getDeclaration()));
                    }
                    else if (arguments.getChild(i).hasDeclaration() && parameter.isVariableArity()) {
                        res.add(new FieldLoadEdge(parameter.getDeclaration(), arguments.getChild(i).getDeclaration(), "-COLLECTION_ACCESS"));
                    }
                }
            }
            return res;
        }
  }
  /**
   * @attribute syn
   * @aspect pointerbench
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:63
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="pointerbench", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/PointerBench.jrag:63")
  public int allocID() {
    {
            if (enclosingStmt() != null && enclosingStmt().prevStmt() != null) {
                return enclosingStmt().prevStmt().stmtID();
            } else {
                return program().NULL_BENCHMARK_ID;
            }
        }
  }
  /** @apilevel internal */
  private void allDecls_reset() {
    allDecls_computed = null;
    allDecls_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle allDecls_computed = null;

  /** @apilevel internal */
  protected Set<InvocationTarget> allDecls_value;

  /**
   * Returns a set of all declarations of the invocable entity.
   * 
   * @return A set containing all declarations of the invocable entity.
   * @attribute syn
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:330
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:330")
  public Set<InvocationTarget> allDecls() {
    ASTState state = state();
    if (allDecls_computed == ASTState.NON_CYCLE || allDecls_computed == state().cycle()) {
      return allDecls_value;
    }
    allDecls_value = Collections.<InvocationTarget>singleton(decl().getContribution());
    if (state().inCircle()) {
      allDecls_computed = state().cycle();
    
    } else {
      allDecls_computed = ASTState.NON_CYCLE;
    
    }
    return allDecls_value;
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
   * @aspect PointsTo
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:189
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PointsTo", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:189")
  public String fileName() {
    String fileName_value = compilationUnit().pathName();
    return fileName_value;
  }
  /**
   * @attribute syn
   * @aspect FilterUtils
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:21
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FilterUtils", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/FilterUtils.jrag:21")
  public TypeDecl type2() {
    TypeDecl type2_value = assignConvertedType();
    return type2_value;
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
   * @aspect ConstructScope
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:58
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ConstructScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:58")
  public TypeDecl typeObject() {
    TypeDecl typeObject_value = getParent().Define_typeObject(this, null);
    return typeObject_value;
  }
  /**
   * @attribute inh
   * @aspect ConstructScope
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:126
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ConstructScope", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupConstructor.jrag:126")
  public ConstructorDecl unknownConstructor() {
    ConstructorDecl unknownConstructor_value = getParent().Define_unknownConstructor(this, null);
    return unknownConstructor_value;
  }
  /**
   * @attribute inh
   * @aspect ExceptionHandling
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:96
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:96")
  public boolean handlesException(TypeDecl exceptionType) {
    boolean handlesException_TypeDecl_value = getParent().Define_handlesException(this, null, exceptionType);
    return handlesException_TypeDecl_value;
  }
  /**
   * @attribute inh
   * @aspect TypeHierarchyCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:203
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TypeHierarchyCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:203")
  public boolean inExplicitConstructorInvocation() {
    boolean inExplicitConstructorInvocation_value = getParent().Define_inExplicitConstructorInvocation(this, null);
    return inExplicitConstructorInvocation_value;
  }
  /**
   * @attribute inh
   * @aspect TypeCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:670
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:670")
  public TypeDecl enclosingInstance() {
    TypeDecl enclosingInstance_value = getParent().Define_enclosingInstance(this, null);
    return enclosingInstance_value;
  }
  /**
   * Inherited attribute that represents the nearest enclosing invocation target
   * of an invocable entity.
   * 
   * @return The nearest enclosing invocation target or null if none.
   * @attribute inh
   * @aspect CallGraph
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:212
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="CallGraph", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:212")
  public InvocationTarget enclosingTarget() {
    ASTState state = state();
    if (enclosingTarget_computed == ASTState.NON_CYCLE || enclosingTarget_computed == state().cycle()) {
      return enclosingTarget_value;
    }
    enclosingTarget_value = getParent().Define_enclosingTarget(this, null);
    if (state().inCircle()) {
      enclosingTarget_computed = state().cycle();
    
    } else {
      enclosingTarget_computed = ASTState.NON_CYCLE;
    
    }
    return enclosingTarget_value;
  }
  /** @apilevel internal */
  private void enclosingTarget_reset() {
    enclosingTarget_computed = null;
    enclosingTarget_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle enclosingTarget_computed = null;

  /** @apilevel internal */
  protected InvocationTarget enclosingTarget_value;

  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (_callerNode == getTypeDeclOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:536
      return assignedAfterInstance(v);
    }
    else if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:531
      int i = _callerNode.getIndexOfChild(_childNode);
      return computeDAbefore(i, v);
    }
    else {
      return getParent().Define_assignedBefore(this, _callerNode, v);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute assignedBefore
   */
  protected boolean canDefine_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:887
   * @apilevel internal
   */
  public boolean Define_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:1142
      int i = _callerNode.getIndexOfChild(_childNode);
      return computeDUbefore(i, v);
    }
    else {
      return getParent().Define_unassignedBefore(this, _callerNode, v);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:887
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unassignedBefore
   */
  protected boolean canDefine_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:223
   * @apilevel internal
   */
  public boolean Define_inStaticContext(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getTypeDeclOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:237
      return isQualified() ?
            qualifier().staticContextQualifier() : inStaticContext();
    }
    else {
      return getParent().Define_inStaticContext(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeHierarchyCheck.jrag:223
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inStaticContext
   */
  protected boolean canDefine_inStaticContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:113
   * @apilevel internal
   */
  public boolean Define_hasPackage(ASTNode _callerNode, ASTNode _childNode, String packageName) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:124
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return unqualifiedScope().hasPackage(packageName);
    }
    else {
      return getParent().Define_hasPackage(this, _callerNode, packageName);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:113
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute hasPackage
   */
  protected boolean canDefine_hasPackage(ASTNode _callerNode, ASTNode _childNode, String packageName) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:231
   * @apilevel internal
   */
  public SimpleSet<TypeDecl> Define_lookupType(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getTypeDeclOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:606
      {
          SimpleSet<TypeDecl> result = localLookupType(name);
          if (!result.isEmpty()) {
            return result;
          }
          result = lookupType(name);
          if (!result.isEmpty()) {
            return result;
          }
          return unqualifiedScope().lookupType(name);
        }
    }
    else if (getAccessNoTransform() != null && _callerNode == getAccess()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:598
      {
          SimpleSet<TypeDecl> result = lookupType(name);
          if (result.isSingleton() && isQualified()) {
            result = keepInnerClasses(result);
          }
          return result;
        }
    }
    else if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:414
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:30
   * @apilevel internal
   */
  public SimpleSet<Variable> Define_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupVariable.jrag:254
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return unqualifiedScope().lookupVariable(name);
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
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:146
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return NameType.EXPRESSION_NAME;
    }
    else if (_callerNode == getTypeDeclOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:145
      return NameType.TYPE_NAME;
    }
    else if (getAccessNoTransform() != null && _callerNode == getAccess()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:144
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
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:33
   * @apilevel internal
   */
  public TypeDecl Define_superType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getTypeDeclOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:35
      return getAccess().type();
    }
    else {
      return getParent().Define_superType(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:33
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute superType
   */
  protected boolean canDefine_superType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:39
   * @apilevel internal
   */
  public ConstructorDecl Define_constructorDecl(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getTypeDeclOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:104
      {
          Collection<ConstructorDecl> c = getAccess().type().constructors();
          SimpleSet<ConstructorDecl> maxSpecific = chooseConstructor(c, getArgList());
          if (maxSpecific.isSingleton()) {
            return maxSpecific.singletonValue();
          }
          return unknownConstructor();
        }
    }
    else {
      return getParent().Define_constructorDecl(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AnonymousClasses.jrag:39
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute constructorDecl
   */
  protected boolean canDefine_constructorDecl(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:631
   * @apilevel internal
   */
  public boolean Define_isMemberType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getTypeDeclOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:635
      return false;
    }
    else {
      return getParent().Define_isMemberType(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:631
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isMemberType
   */
  protected boolean canDefine_isMemberType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:76
   * @apilevel internal
   */
  public TypeDecl Define_hostType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getTypeDeclOptNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:690
      return hostType();
    }
    else {
      return getParent().Define_hostType(this, _callerNode);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Diamond.jrag:99
   * @apilevel internal
   */
  public ClassInstanceExpr Define_getClassInstanceExpr(ASTNode _callerNode, ASTNode _childNode) {
    if (getAccessNoTransform() != null && _callerNode == getAccess()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Diamond.jrag:100
      return this;
    }
    else {
      return getParent().Define_getClassInstanceExpr(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Diamond.jrag:99
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute getClassInstanceExpr
   */
  protected boolean canDefine_getClassInstanceExpr(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Diamond.jrag:284
   * @apilevel internal
   */
  public boolean Define_isAnonymousDecl(ASTNode _callerNode, ASTNode _childNode) {
    if (getAccessNoTransform() != null && _callerNode == getAccess()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Diamond.jrag:289
      return hasTypeDecl();
    }
    else {
      return getParent().Define_isAnonymousDecl(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/Diamond.jrag:284
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isAnonymousDecl
   */
  protected boolean canDefine_isAnonymousDecl(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:44
   * @apilevel internal
   */
  public TypeDecl Define_targetType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:131
      int i = _callerNode.getIndexOfChild(_childNode);
      {
          ConstructorDecl decl = decl();
          if (unknownConstructor() == decl) {
            return decl.type().unknownType();
          }
      
          if (decl.isVariableArity() && i >= decl.arity() - 1) {
            return decl.getParameter(decl.arity() - 1).type().componentType();
          } else {
            return decl.getParameter(i).type();
          }
        }
    }
    else {
      return getParent().Define_targetType(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:44
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute targetType
   */
  protected boolean canDefine_targetType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/AssignConvertedType.jrag:39
   * @apilevel internal
   */
  public TypeDecl Define_assignConvertedType(ASTNode _callerNode, ASTNode _childNode) {
    if (getAccessNoTransform() != null && _callerNode == getAccess()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:222
      return targetType();
    }
    else {
      return getParent().Define_assignConvertedType(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/AssignConvertedType.jrag:39
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute assignConvertedType
   */
  protected boolean canDefine_assignConvertedType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:252
   * @apilevel internal
   */
  public boolean Define_assignmentContext(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:362
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else {
      return getParent().Define_assignmentContext(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:252
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute assignmentContext
   */
  protected boolean canDefine_assignmentContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:253
   * @apilevel internal
   */
  public boolean Define_invocationContext(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:363
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return true;
    }
    else {
      return getParent().Define_invocationContext(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:253
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute invocationContext
   */
  protected boolean canDefine_invocationContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:254
   * @apilevel internal
   */
  public boolean Define_castContext(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:364
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else {
      return getParent().Define_castContext(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:254
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute castContext
   */
  protected boolean canDefine_castContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:255
   * @apilevel internal
   */
  public boolean Define_stringContext(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:365
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else {
      return getParent().Define_stringContext(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:255
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute stringContext
   */
  protected boolean canDefine_stringContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:256
   * @apilevel internal
   */
  public boolean Define_numericContext(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getArgListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:366
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else {
      return getParent().Define_numericContext(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:256
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute numericContext
   */
  protected boolean canDefine_numericContext(ASTNode _callerNode, ASTNode _childNode) {
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
  protected void collect_contributors_Program_instanciatedTypes(Program _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/cha.jrag:160
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_Program_instanciatedTypes(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_CompilationUnit_problems(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:164
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/AccessControl.jrag:172
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeCheck.jrag:584
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:212
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:521
    if (decl().isDeprecated()
              && !withinDeprecatedAnnotation()
              && hostType().topLevelType() != decl().hostType().topLevelType()
              && !withinSuppressWarnings("deprecation")) {
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
  protected void collect_contributors_InvocationTarget_pointsToEdges(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:86
    for (InvocationTarget target : (Iterable<? extends InvocationTarget>) (enclosingMethods())) {
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_InvocationTarget_pointsToEdges(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_ClassDecl_instantiations(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Andersen.jrag:131
    {
      ClassDecl target = (ClassDecl) (decl().hostType());
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_ClassDecl_instantiations(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_InvocationTarget_inclusionEdges(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/MethodCall.jrag:49
    for (InvocationTarget target : (Iterable<? extends InvocationTarget>) (enclosingMethods())) {
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_InvocationTarget_inclusionEdges(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_InvocationTarget_calledMethods(ASTNode _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/callgraph.jrag:182
    {
      InvocationTarget target = (InvocationTarget) (enclosingTarget());
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_InvocationTarget_calledMethods(_root, _map);
  }
  /** @apilevel internal */
  protected void contributeTo_Program_instanciatedTypes(Set<TypeDecl> collection) {
    super.contributeTo_Program_instanciatedTypes(collection);
    collection.add(type());
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
    for (Problem value : nameProblems()) {
      collection.add(value);
    }
    if (decl().isDeprecated()
              && !withinDeprecatedAnnotation()
              && hostType().topLevelType() != decl().hostType().topLevelType()
              && !withinSuppressWarnings("deprecation")) {
      collection.add(warning(decl().signature() + " in " + decl().hostType().typeName() + " has been deprecated"));
    }
  }
  /** @apilevel internal */
  protected void contributeTo_InvocationTarget_pointsToEdges(Set<PointsToEdge> collection) {
    super.contributeTo_InvocationTarget_pointsToEdges(collection);
    collection.add(new PointsToEdge(this, this));
  }
  /** @apilevel internal */
  protected void contributeTo_ClassDecl_instantiations(Set<ClassInstanceExpr> collection) {
    super.contributeTo_ClassDecl_instantiations(collection);
    collection.add(this);
  }
  /** @apilevel internal */
  protected void contributeTo_InvocationTarget_inclusionEdges(Set<InclusionEdge> collection) {
    super.contributeTo_InvocationTarget_inclusionEdges(collection);
    for (InclusionEdge value : inclusionEdges()) {
      collection.add(value);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_InvocationTarget_calledMethods(Set<Invocable> collection) {
    super.contributeTo_InvocationTarget_calledMethods(collection);
    collection.add(this);
  }

}
