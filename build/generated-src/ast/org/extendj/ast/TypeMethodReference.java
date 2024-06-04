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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/grammar/MethodReference.ast:4
 * @astdecl TypeMethodReference : MethodReference ::= TypeArgument:Access* <ID:String> TypeAccess:Access;
 * @production TypeMethodReference : {@link MethodReference} ::= <span class="component">TypeAccess:{@link Access}</span>;

 */
public class TypeMethodReference extends MethodReference implements Cloneable {
  /**
   * @aspect PrettyPrintUtil8
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PrettyPrintUtil.jadd:117
   */
  @Override public String toString() {
    return getTypeAccessNoTransform().toString() + super.toString();
  }
  /**
   * @aspect Java8PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/PrettyPrint.jadd:118
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(getTypeAccess());
    out.print("::");
    if (hasTypeArgument()) {
      out.print("<");
      out.join(getTypeArguments(), new PrettyPrinter.Joiner() {
        @Override
        public void printSeparator(PrettyPrinter out) {
          out.print(", ");
        }
      });
      out.print(">");
    }
    out.print(name());
  }
  /**
   * @declaredat ASTNode:1
   */
  public TypeMethodReference() {
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
    setChild(new List(), 0);
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"TypeArgument", "ID", "TypeAccess"},
    type = {"List<Access>", "String", "Access"},
    kind = {"List", "Token", "Child"}
  )
  public TypeMethodReference(List<Access> p0, String p1, Access p2) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
  }
  /**
   * @declaredat ASTNode:24
   */
  public TypeMethodReference(List<Access> p0, beaver.Symbol p1, Access p2) {
    setChild(p0, 0);
    setID(p1);
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
    targetStaticMethod_FunctionDescriptor_reset();
    targetInstanceMethod_FunctionDescriptor_reset();
    validStaticMethod_FunctionDescriptor_reset();
    validInstanceMethod_FunctionDescriptor_reset();
    inferredReferenceType_FunctionDescriptor_reset();
    syntheticStaticAccess_FunctionDescriptor_reset();
    syntheticStaticMethodAccess_FunctionDescriptor_reset();
    syntheticInstanceAccess_FunctionDescriptor_reset();
    syntheticInstanceMethodAccess_FunctionDescriptor_reset();
    congruentTo_FunctionDescriptor_reset();
    potentiallyApplicableMethods_FunctionDescriptor_reset();
    exactCompileTimeDeclaration_reset();
    potentiallyCompatible_TypeDecl_BodyDecl_reset();
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
  public TypeMethodReference clone() throws CloneNotSupportedException {
    TypeMethodReference node = (TypeMethodReference) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:67
   */
  public TypeMethodReference copy() {
    try {
      TypeMethodReference node = (TypeMethodReference) clone();
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
  public TypeMethodReference fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:96
   */
  public TypeMethodReference treeCopyNoTransform() {
    TypeMethodReference tree = (TypeMethodReference) copy();
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
  public TypeMethodReference treeCopy() {
    TypeMethodReference tree = (TypeMethodReference) copy();
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
   * Replaces the TypeArgument list.
   * @param list The new list node to be used as the TypeArgument list.
   * @apilevel high-level
   */
  public TypeMethodReference setTypeArgumentList(List<Access> list) {
    setChild(list, 0);
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
  public TypeMethodReference addTypeArgument(Access node) {
    List<Access> list = (parent == null) ? getTypeArgumentListNoTransform() : getTypeArgumentList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public TypeMethodReference addTypeArgumentNoTransform(Access node) {
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
  public TypeMethodReference setTypeArgument(Access node, int i) {
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
    List<Access> list = (List<Access>) getChild(0);
    return list;
  }
  /**
   * Retrieves the TypeArgument list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeArgument list.
   * @apilevel low-level
   */
  public List<Access> getTypeArgumentListNoTransform() {
    return (List<Access>) getChildNoTransform(0);
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
   * Replaces the lexeme ID.
   * @param value The new value for the lexeme ID.
   * @apilevel high-level
   */
  public TypeMethodReference setID(String value) {
    tokenString_ID = value;
    return this;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public TypeMethodReference setID(beaver.Symbol symbol) {
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
   * Replaces the TypeAccess child.
   * @param node The new node to replace the TypeAccess child.
   * @apilevel high-level
   */
  public TypeMethodReference setTypeAccess(Access node) {
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
  /** @apilevel internal */
  private void targetStaticMethod_FunctionDescriptor_reset() {
    targetStaticMethod_FunctionDescriptor_computed = null;
    targetStaticMethod_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map targetStaticMethod_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map targetStaticMethod_FunctionDescriptor_computed;
  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:70
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:70")
  public MethodDecl targetStaticMethod(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (targetStaticMethod_FunctionDescriptor_computed == null) targetStaticMethod_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (targetStaticMethod_FunctionDescriptor_values == null) targetStaticMethod_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (targetStaticMethod_FunctionDescriptor_values.containsKey(_parameters)
        && targetStaticMethod_FunctionDescriptor_computed.containsKey(_parameters)
        && (targetStaticMethod_FunctionDescriptor_computed.get(_parameters) == ASTState.NON_CYCLE || targetStaticMethod_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (MethodDecl) targetStaticMethod_FunctionDescriptor_values.get(_parameters);
    }
    MethodDecl targetStaticMethod_FunctionDescriptor_value = targetStaticMethod_compute(fd);
    if (state().inCircle()) {
      targetStaticMethod_FunctionDescriptor_values.put(_parameters, targetStaticMethod_FunctionDescriptor_value);
      targetStaticMethod_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      targetStaticMethod_FunctionDescriptor_values.put(_parameters, targetStaticMethod_FunctionDescriptor_value);
      targetStaticMethod_FunctionDescriptor_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return targetStaticMethod_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private MethodDecl targetStaticMethod_compute(FunctionDescriptor fd) {
      MethodAccess synAcc = syntheticStaticMethodAccess(fd);
      SimpleSet<MethodDecl> maxSpecific = synAcc.maxSpecific(synAcc.lookupMethod(synAcc.name()));
      maxSpecific = MethodAccess.keepStaticMethods(maxSpecific);
      if (maxSpecific.isSingleton()) {
        return maxSpecific.singletonValue();
      } else {
        return unknownMethod();
      }
    }
  /** @apilevel internal */
  private void targetInstanceMethod_FunctionDescriptor_reset() {
    targetInstanceMethod_FunctionDescriptor_computed = null;
    targetInstanceMethod_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map targetInstanceMethod_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map targetInstanceMethod_FunctionDescriptor_computed;
  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:81
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:81")
  public MethodDecl targetInstanceMethod(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (targetInstanceMethod_FunctionDescriptor_computed == null) targetInstanceMethod_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (targetInstanceMethod_FunctionDescriptor_values == null) targetInstanceMethod_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (targetInstanceMethod_FunctionDescriptor_values.containsKey(_parameters)
        && targetInstanceMethod_FunctionDescriptor_computed.containsKey(_parameters)
        && (targetInstanceMethod_FunctionDescriptor_computed.get(_parameters) == ASTState.NON_CYCLE || targetInstanceMethod_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (MethodDecl) targetInstanceMethod_FunctionDescriptor_values.get(_parameters);
    }
    MethodDecl targetInstanceMethod_FunctionDescriptor_value = targetInstanceMethod_compute(fd);
    if (state().inCircle()) {
      targetInstanceMethod_FunctionDescriptor_values.put(_parameters, targetInstanceMethod_FunctionDescriptor_value);
      targetInstanceMethod_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      targetInstanceMethod_FunctionDescriptor_values.put(_parameters, targetInstanceMethod_FunctionDescriptor_value);
      targetInstanceMethod_FunctionDescriptor_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return targetInstanceMethod_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private MethodDecl targetInstanceMethod_compute(FunctionDescriptor fd) {
      if (fd.method.hasValue()) {
        MethodDecl targetMethod = fd.method.get();
        if (targetMethod.getNumParameter() == 0
            || !targetMethod.getParameter(0).type().strictSubtype(getTypeAccess().type())) {
          return unknownMethod();
        }
  
        MethodAccess synAcc = syntheticInstanceMethodAccess(fd);
        SimpleSet<MethodDecl> maxSpecific = synAcc.maxSpecific(synAcc.lookupMethod(synAcc.name()));
        if (maxSpecific.isSingleton()) {
          return maxSpecific.singletonValue();
        } else {
          return unknownMethod();
        }
      } else {
        return unknownMethod();
      }
    }
  /** @apilevel internal */
  private void validStaticMethod_FunctionDescriptor_reset() {
    validStaticMethod_FunctionDescriptor_computed = null;
    validStaticMethod_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map validStaticMethod_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map validStaticMethod_FunctionDescriptor_computed;
  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:101
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:101")
  public boolean validStaticMethod(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (validStaticMethod_FunctionDescriptor_computed == null) validStaticMethod_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (validStaticMethod_FunctionDescriptor_values == null) validStaticMethod_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (validStaticMethod_FunctionDescriptor_values.containsKey(_parameters)
        && validStaticMethod_FunctionDescriptor_computed.containsKey(_parameters)
        && (validStaticMethod_FunctionDescriptor_computed.get(_parameters) == ASTState.NON_CYCLE || validStaticMethod_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (Boolean) validStaticMethod_FunctionDescriptor_values.get(_parameters);
    }
    boolean validStaticMethod_FunctionDescriptor_value = validStaticMethod_compute(fd);
    if (state().inCircle()) {
      validStaticMethod_FunctionDescriptor_values.put(_parameters, validStaticMethod_FunctionDescriptor_value);
      validStaticMethod_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      validStaticMethod_FunctionDescriptor_values.put(_parameters, validStaticMethod_FunctionDescriptor_value);
      validStaticMethod_FunctionDescriptor_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return validStaticMethod_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private boolean validStaticMethod_compute(FunctionDescriptor fd) {
      MethodDecl decl = targetStaticMethod(fd);
      return !(decl == unknownMethod() || !decl.isStatic());
    }
  /** @apilevel internal */
  private void validInstanceMethod_FunctionDescriptor_reset() {
    validInstanceMethod_FunctionDescriptor_computed = null;
    validInstanceMethod_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map validInstanceMethod_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map validInstanceMethod_FunctionDescriptor_computed;
  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:106
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:106")
  public boolean validInstanceMethod(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (validInstanceMethod_FunctionDescriptor_computed == null) validInstanceMethod_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (validInstanceMethod_FunctionDescriptor_values == null) validInstanceMethod_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (validInstanceMethod_FunctionDescriptor_values.containsKey(_parameters)
        && validInstanceMethod_FunctionDescriptor_computed.containsKey(_parameters)
        && (validInstanceMethod_FunctionDescriptor_computed.get(_parameters) == ASTState.NON_CYCLE || validInstanceMethod_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (Boolean) validInstanceMethod_FunctionDescriptor_values.get(_parameters);
    }
    boolean validInstanceMethod_FunctionDescriptor_value = validInstanceMethod_compute(fd);
    if (state().inCircle()) {
      validInstanceMethod_FunctionDescriptor_values.put(_parameters, validInstanceMethod_FunctionDescriptor_value);
      validInstanceMethod_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      validInstanceMethod_FunctionDescriptor_values.put(_parameters, validInstanceMethod_FunctionDescriptor_value);
      validInstanceMethod_FunctionDescriptor_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return validInstanceMethod_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private boolean validInstanceMethod_compute(FunctionDescriptor fd) {
      MethodDecl decl = targetInstanceMethod(fd);
      return !(decl == unknownMethod() || decl.isStatic());
    }
  /** @apilevel internal */
  private void inferredReferenceType_FunctionDescriptor_reset() {
    inferredReferenceType_FunctionDescriptor_computed = null;
    inferredReferenceType_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map inferredReferenceType_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map inferredReferenceType_FunctionDescriptor_computed;
  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:129
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:129")
  public TypeDecl inferredReferenceType(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (inferredReferenceType_FunctionDescriptor_computed == null) inferredReferenceType_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (inferredReferenceType_FunctionDescriptor_values == null) inferredReferenceType_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (inferredReferenceType_FunctionDescriptor_values.containsKey(_parameters)
        && inferredReferenceType_FunctionDescriptor_computed.containsKey(_parameters)
        && (inferredReferenceType_FunctionDescriptor_computed.get(_parameters) == ASTState.NON_CYCLE || inferredReferenceType_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (TypeDecl) inferredReferenceType_FunctionDescriptor_values.get(_parameters);
    }
    TypeDecl inferredReferenceType_FunctionDescriptor_value = inferredReferenceType_compute(fd);
    if (state().inCircle()) {
      inferredReferenceType_FunctionDescriptor_values.put(_parameters, inferredReferenceType_FunctionDescriptor_value);
      inferredReferenceType_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      inferredReferenceType_FunctionDescriptor_values.put(_parameters, inferredReferenceType_FunctionDescriptor_value);
      inferredReferenceType_FunctionDescriptor_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return inferredReferenceType_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private TypeDecl inferredReferenceType_compute(FunctionDescriptor fd) {
      if (fd.method.hasValue()) {
        MethodDecl targetMethod = fd.method.get();
        if (targetMethod.getNumParameter() == 0) {
          return null;
        } else if (!(targetMethod.getParameter(0).getTypeAccess() instanceof ParTypeAccess)) {
          return null;
        } else if (!getTypeAccess().type().isRawType() || !(getTypeAccess() instanceof TypeAccess)) {
          return null;
        }
  
        ParameterDeclaration param = targetMethod.getParameter(0);
        if (!param.type().strictSubtype(param
              .inferredReferenceAccess((TypeAccess) getTypeAccess()).type())) {
          return null;
        }
        return param.inferredReferenceAccess((TypeAccess) getTypeAccess()).type();
      } else {
        return null;
      }
    }
  /** @apilevel internal */
  private void syntheticStaticAccess_FunctionDescriptor_reset() {
    syntheticStaticAccess_FunctionDescriptor_values = null;
    syntheticStaticAccess_FunctionDescriptor_proxy = null;
  }
  /** @apilevel internal */
  protected ASTNode syntheticStaticAccess_FunctionDescriptor_proxy;
  /** @apilevel internal */
  protected java.util.Map syntheticStaticAccess_FunctionDescriptor_values;

  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:151
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:151")
  public Access syntheticStaticAccess(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (syntheticStaticAccess_FunctionDescriptor_values == null) syntheticStaticAccess_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (syntheticStaticAccess_FunctionDescriptor_values.containsKey(_parameters)) {
      return (Access) syntheticStaticAccess_FunctionDescriptor_values.get(_parameters);
    }
    state().enterLazyAttribute();
    Access syntheticStaticAccess_FunctionDescriptor_value = syntheticStaticAccess_compute(fd);
    if (syntheticStaticAccess_FunctionDescriptor_proxy == null) {
      syntheticStaticAccess_FunctionDescriptor_proxy = new ASTNode();
      syntheticStaticAccess_FunctionDescriptor_proxy.setParent(this);
    }
    if (syntheticStaticAccess_FunctionDescriptor_value != null) {
      syntheticStaticAccess_FunctionDescriptor_value.setParent(syntheticStaticAccess_FunctionDescriptor_proxy);
      if (syntheticStaticAccess_FunctionDescriptor_value.mayHaveRewrite()) {
        syntheticStaticAccess_FunctionDescriptor_value = (Access) syntheticStaticAccess_FunctionDescriptor_value.rewrittenNode();
        syntheticStaticAccess_FunctionDescriptor_value.setParent(syntheticStaticAccess_FunctionDescriptor_proxy);
      }
    }
    syntheticStaticAccess_FunctionDescriptor_values.put(_parameters, syntheticStaticAccess_FunctionDescriptor_value);
    state().leaveLazyAttribute();
    return syntheticStaticAccess_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private Access syntheticStaticAccess_compute(FunctionDescriptor fd) {
      List<Expr> arguments = new List<Expr>();
      if (fd.method.hasValue()) {
        MethodDecl targetMethod = fd.method.get();
        for (int i = 0; i < targetMethod.getNumParameter(); i++) {
          TypeDecl argumentType = targetMethod.getParameter(i).type();
          arguments.add(new SyntheticTypeAccess(argumentType));
        }
      }
  
      if (!hasTypeArgument()) {
        MethodReferenceAccess mAccess = new MethodReferenceAccess(name(), arguments, fd);
        return ((Access) getTypeAccess().treeCopy()).qualifiesAccess(mAccess);
      } else {
        ParMethodReferenceAccess pmAccess = new ParMethodReferenceAccess(name(), arguments,
            (List<Access>) getTypeArgumentList().treeCopy(), fd);
        return ((Access) getTypeAccess().treeCopy()).qualifiesAccess(pmAccess);
      }
    }
  /** @apilevel internal */
  private void syntheticStaticMethodAccess_FunctionDescriptor_reset() {
    syntheticStaticMethodAccess_FunctionDescriptor_computed = null;
    syntheticStaticMethodAccess_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map syntheticStaticMethodAccess_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map syntheticStaticMethodAccess_FunctionDescriptor_computed;
  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:171
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:171")
  public MethodAccess syntheticStaticMethodAccess(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (syntheticStaticMethodAccess_FunctionDescriptor_computed == null) syntheticStaticMethodAccess_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (syntheticStaticMethodAccess_FunctionDescriptor_values == null) syntheticStaticMethodAccess_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (syntheticStaticMethodAccess_FunctionDescriptor_values.containsKey(_parameters)
        && syntheticStaticMethodAccess_FunctionDescriptor_computed.containsKey(_parameters)
        && (syntheticStaticMethodAccess_FunctionDescriptor_computed.get(_parameters) == ASTState.NON_CYCLE || syntheticStaticMethodAccess_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (MethodAccess) syntheticStaticMethodAccess_FunctionDescriptor_values.get(_parameters);
    }
    MethodAccess syntheticStaticMethodAccess_FunctionDescriptor_value = syntheticStaticMethodAccess_compute(fd);
    if (state().inCircle()) {
      syntheticStaticMethodAccess_FunctionDescriptor_values.put(_parameters, syntheticStaticMethodAccess_FunctionDescriptor_value);
      syntheticStaticMethodAccess_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      syntheticStaticMethodAccess_FunctionDescriptor_values.put(_parameters, syntheticStaticMethodAccess_FunctionDescriptor_value);
      syntheticStaticMethodAccess_FunctionDescriptor_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return syntheticStaticMethodAccess_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private MethodAccess syntheticStaticMethodAccess_compute(FunctionDescriptor fd) {
      Access synAccess = syntheticStaticAccess(fd);
      return (MethodAccess) synAccess.lastAccess();
    }
  /** @apilevel internal */
  private void syntheticInstanceAccess_FunctionDescriptor_reset() {
    syntheticInstanceAccess_FunctionDescriptor_values = null;
    syntheticInstanceAccess_FunctionDescriptor_proxy = null;
  }
  /** @apilevel internal */
  protected ASTNode syntheticInstanceAccess_FunctionDescriptor_proxy;
  /** @apilevel internal */
  protected java.util.Map syntheticInstanceAccess_FunctionDescriptor_values;

  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:176
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:176")
  public Access syntheticInstanceAccess(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (syntheticInstanceAccess_FunctionDescriptor_values == null) syntheticInstanceAccess_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (syntheticInstanceAccess_FunctionDescriptor_values.containsKey(_parameters)) {
      return (Access) syntheticInstanceAccess_FunctionDescriptor_values.get(_parameters);
    }
    state().enterLazyAttribute();
    Access syntheticInstanceAccess_FunctionDescriptor_value = syntheticInstanceAccess_compute(fd);
    if (syntheticInstanceAccess_FunctionDescriptor_proxy == null) {
      syntheticInstanceAccess_FunctionDescriptor_proxy = new ASTNode();
      syntheticInstanceAccess_FunctionDescriptor_proxy.setParent(this);
    }
    if (syntheticInstanceAccess_FunctionDescriptor_value != null) {
      syntheticInstanceAccess_FunctionDescriptor_value.setParent(syntheticInstanceAccess_FunctionDescriptor_proxy);
      if (syntheticInstanceAccess_FunctionDescriptor_value.mayHaveRewrite()) {
        syntheticInstanceAccess_FunctionDescriptor_value = (Access) syntheticInstanceAccess_FunctionDescriptor_value.rewrittenNode();
        syntheticInstanceAccess_FunctionDescriptor_value.setParent(syntheticInstanceAccess_FunctionDescriptor_proxy);
      }
    }
    syntheticInstanceAccess_FunctionDescriptor_values.put(_parameters, syntheticInstanceAccess_FunctionDescriptor_value);
    state().leaveLazyAttribute();
    return syntheticInstanceAccess_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private Access syntheticInstanceAccess_compute(FunctionDescriptor fd) {
      List<Expr> arguments = new List<Expr>();
      if (fd.method.hasValue()) {
        MethodDecl targetMethod = fd.method.get();
        for (int i = 1; i < targetMethod.getNumParameter(); i++) {
          TypeDecl argumentType = targetMethod.getParameter(i).type();
          arguments.add(new SyntheticTypeAccess(argumentType));
        }
      }
  
      Access qualifier = null;
  
      if (inferredReferenceType(fd) != null) {
        qualifier = new SyntheticTypeAccess(inferredReferenceType(fd));
      } else {
        qualifier = (Access) getTypeAccess().treeCopy();
      }
  
      if (!hasTypeArgument()) {
        MethodReferenceAccess mAccess = new MethodReferenceAccess(name(), arguments, fd);
        return qualifier.qualifiesAccess(mAccess);
      } else {
        ParMethodReferenceAccess pmAccess = new ParMethodReferenceAccess(name(), arguments,
            (List<Access>) getTypeArgumentList().treeCopy(), fd);
        return qualifier.qualifiesAccess(pmAccess);
      }
    }
  /** @apilevel internal */
  private void syntheticInstanceMethodAccess_FunctionDescriptor_reset() {
    syntheticInstanceMethodAccess_FunctionDescriptor_computed = null;
    syntheticInstanceMethodAccess_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map syntheticInstanceMethodAccess_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map syntheticInstanceMethodAccess_FunctionDescriptor_computed;
  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:204
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:204")
  public MethodAccess syntheticInstanceMethodAccess(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (syntheticInstanceMethodAccess_FunctionDescriptor_computed == null) syntheticInstanceMethodAccess_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (syntheticInstanceMethodAccess_FunctionDescriptor_values == null) syntheticInstanceMethodAccess_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (syntheticInstanceMethodAccess_FunctionDescriptor_values.containsKey(_parameters)
        && syntheticInstanceMethodAccess_FunctionDescriptor_computed.containsKey(_parameters)
        && (syntheticInstanceMethodAccess_FunctionDescriptor_computed.get(_parameters) == ASTState.NON_CYCLE || syntheticInstanceMethodAccess_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (MethodAccess) syntheticInstanceMethodAccess_FunctionDescriptor_values.get(_parameters);
    }
    MethodAccess syntheticInstanceMethodAccess_FunctionDescriptor_value = syntheticInstanceMethodAccess_compute(fd);
    if (state().inCircle()) {
      syntheticInstanceMethodAccess_FunctionDescriptor_values.put(_parameters, syntheticInstanceMethodAccess_FunctionDescriptor_value);
      syntheticInstanceMethodAccess_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      syntheticInstanceMethodAccess_FunctionDescriptor_values.put(_parameters, syntheticInstanceMethodAccess_FunctionDescriptor_value);
      syntheticInstanceMethodAccess_FunctionDescriptor_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return syntheticInstanceMethodAccess_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private MethodAccess syntheticInstanceMethodAccess_compute(FunctionDescriptor fd) {
      Access synAccess = syntheticInstanceAccess(fd);
      return (MethodAccess) synAccess.lastAccess();
    }
  /** @apilevel internal */
  private void congruentTo_FunctionDescriptor_reset() {
    congruentTo_FunctionDescriptor_computed = null;
    congruentTo_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map congruentTo_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map congruentTo_FunctionDescriptor_computed;
  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:261
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:239")
  public boolean congruentTo(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (congruentTo_FunctionDescriptor_computed == null) congruentTo_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (congruentTo_FunctionDescriptor_values == null) congruentTo_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (congruentTo_FunctionDescriptor_values.containsKey(_parameters)
        && congruentTo_FunctionDescriptor_computed.containsKey(_parameters)
        && (congruentTo_FunctionDescriptor_computed.get(_parameters) == ASTState.NON_CYCLE || congruentTo_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (Boolean) congruentTo_FunctionDescriptor_values.get(_parameters);
    }
    boolean congruentTo_FunctionDescriptor_value = congruentTo_compute(fd);
    if (state().inCircle()) {
      congruentTo_FunctionDescriptor_values.put(_parameters, congruentTo_FunctionDescriptor_value);
      congruentTo_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      congruentTo_FunctionDescriptor_values.put(_parameters, congruentTo_FunctionDescriptor_value);
      congruentTo_FunctionDescriptor_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return congruentTo_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private boolean congruentTo_compute(FunctionDescriptor fd) {
      if (fd.method.hasValue()) {
        TypeDecl methodType = fd.method.get().type();
        MethodDecl staticMethod = targetStaticMethod(fd);
        MethodDecl instanceMethod = targetInstanceMethod(fd);
        if (unknownMethod() != staticMethod && unknownMethod() != instanceMethod) {
          return false;
        } else if (unknownMethod() == staticMethod && unknownMethod() == instanceMethod) {
          return false;
        }
        MethodDecl found;
        if (unknownMethod() != staticMethod) {
          found = staticMethod;
        } else {
          found = instanceMethod;
        }
        if (methodType.isVoid()) {
          return true;
        }
        if (found.type().isVoid()) {
          return false;
        }
        return found.type().assignConversionTo(methodType, null);
      } else {
        // No target method.
        return false;
      }
    }
  /** @apilevel internal */
  private void potentiallyApplicableMethods_FunctionDescriptor_reset() {
    potentiallyApplicableMethods_FunctionDescriptor_computed = null;
    potentiallyApplicableMethods_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map potentiallyApplicableMethods_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map potentiallyApplicableMethods_FunctionDescriptor_computed;
  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:324
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:292")
  public java.util.List<MethodDecl> potentiallyApplicableMethods(FunctionDescriptor fd) {
    Object _parameters = fd;
    if (potentiallyApplicableMethods_FunctionDescriptor_computed == null) potentiallyApplicableMethods_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (potentiallyApplicableMethods_FunctionDescriptor_values == null) potentiallyApplicableMethods_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTState state = state();
    if (potentiallyApplicableMethods_FunctionDescriptor_values.containsKey(_parameters)
        && potentiallyApplicableMethods_FunctionDescriptor_computed.containsKey(_parameters)
        && (potentiallyApplicableMethods_FunctionDescriptor_computed.get(_parameters) == ASTState.NON_CYCLE || potentiallyApplicableMethods_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (java.util.List<MethodDecl>) potentiallyApplicableMethods_FunctionDescriptor_values.get(_parameters);
    }
    java.util.List<MethodDecl> potentiallyApplicableMethods_FunctionDescriptor_value = potentiallyApplicableMethods_compute(fd);
    if (state().inCircle()) {
      potentiallyApplicableMethods_FunctionDescriptor_values.put(_parameters, potentiallyApplicableMethods_FunctionDescriptor_value);
      potentiallyApplicableMethods_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      potentiallyApplicableMethods_FunctionDescriptor_values.put(_parameters, potentiallyApplicableMethods_FunctionDescriptor_value);
      potentiallyApplicableMethods_FunctionDescriptor_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return potentiallyApplicableMethods_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private java.util.List<MethodDecl> potentiallyApplicableMethods_compute(FunctionDescriptor fd) {
      if (fd.method.hasValue()) {
        MethodDecl targetMethod = fd.method.get();
        Collection<MethodDecl> col = getTypeAccess().type().memberMethods(name());
        ArrayList<MethodDecl> applicable = new ArrayList<MethodDecl>();
        for (MethodDecl decl : col) {
          if (!decl.accessibleFrom(hostType())) {
            continue;
          }
          if (!(decl.arity() == targetMethod.arity())
              && !(decl.arity() == targetMethod.arity() - 1)) {
            continue;
          }
          if (hasTypeArgument()) {
            if (!decl.isGeneric()) {
              continue;
            }
            GenericMethodDecl genDecl = decl.genericDecl();
            if (!(getNumTypeArgument() == genDecl.getNumTypeParameter())) {
              continue;
            }
          }
          applicable.add(decl);
        }
        return applicable;
      } else {
        // No target method.
        return Collections.emptyList();
      }
    }
  /** @apilevel internal */
  private void exactCompileTimeDeclaration_reset() {
    exactCompileTimeDeclaration_computed = null;
    exactCompileTimeDeclaration_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle exactCompileTimeDeclaration_computed = null;

  /** @apilevel internal */
  protected MethodDecl exactCompileTimeDeclaration_value;

  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:392
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:360")
  public MethodDecl exactCompileTimeDeclaration() {
    ASTState state = state();
    if (exactCompileTimeDeclaration_computed == ASTState.NON_CYCLE || exactCompileTimeDeclaration_computed == state().cycle()) {
      return exactCompileTimeDeclaration_value;
    }
    exactCompileTimeDeclaration_value = exactCompileTimeDeclaration_compute();
    if (state().inCircle()) {
      exactCompileTimeDeclaration_computed = state().cycle();
    
    } else {
      exactCompileTimeDeclaration_computed = ASTState.NON_CYCLE;
    
    }
    return exactCompileTimeDeclaration_value;
  }
  /** @apilevel internal */
  private MethodDecl exactCompileTimeDeclaration_compute() {
      if (getTypeAccess().type().isRawType()) {
        return unknownMethod();
      }
      Collection<MethodDecl> col = getTypeAccess().type().memberMethods(name());
      int foundCompatible = 0;
      MethodDecl latestDecl = null;
      for (MethodDecl decl  : col) {
        if (decl.accessibleFrom(hostType())) {
          foundCompatible++;
          latestDecl = decl;
        }
      }
      if (foundCompatible != 1) {
        return unknownMethod();
      }
      if (latestDecl.isVariableArity()) {
        return unknownMethod();
      }
      if (latestDecl.isGeneric()) {
        GenericMethodDecl genericDecl = latestDecl.genericDecl();
        if (getNumTypeArgument() == genericDecl.getNumTypeParameter()) {
          return latestDecl;
        } else {
          return unknownMethod();
        }
      }
      return latestDecl;
    }
  /** @apilevel internal */
  private void potentiallyCompatible_TypeDecl_BodyDecl_reset() {
    potentiallyCompatible_TypeDecl_BodyDecl_computed = null;
    potentiallyCompatible_TypeDecl_BodyDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map potentiallyCompatible_TypeDecl_BodyDecl_values;
  /** @apilevel internal */
  protected java.util.Map potentiallyCompatible_TypeDecl_BodyDecl_computed;
  /**
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:613
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodSignature.jrag:511")
  public boolean potentiallyCompatible(TypeDecl type, BodyDecl candidateDecl) {
    java.util.List _parameters = new java.util.ArrayList(2);
    _parameters.add(type);
    _parameters.add(candidateDecl);
    if (potentiallyCompatible_TypeDecl_BodyDecl_computed == null) potentiallyCompatible_TypeDecl_BodyDecl_computed = new java.util.HashMap(4);
    if (potentiallyCompatible_TypeDecl_BodyDecl_values == null) potentiallyCompatible_TypeDecl_BodyDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (potentiallyCompatible_TypeDecl_BodyDecl_values.containsKey(_parameters)
        && potentiallyCompatible_TypeDecl_BodyDecl_computed.containsKey(_parameters)
        && (potentiallyCompatible_TypeDecl_BodyDecl_computed.get(_parameters) == ASTState.NON_CYCLE || potentiallyCompatible_TypeDecl_BodyDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) potentiallyCompatible_TypeDecl_BodyDecl_values.get(_parameters);
    }
    boolean potentiallyCompatible_TypeDecl_BodyDecl_value = potentiallyCompatible_compute(type, candidateDecl);
    if (state().inCircle()) {
      potentiallyCompatible_TypeDecl_BodyDecl_values.put(_parameters, potentiallyCompatible_TypeDecl_BodyDecl_value);
      potentiallyCompatible_TypeDecl_BodyDecl_computed.put(_parameters, state().cycle());
    
    } else {
      potentiallyCompatible_TypeDecl_BodyDecl_values.put(_parameters, potentiallyCompatible_TypeDecl_BodyDecl_value);
      potentiallyCompatible_TypeDecl_BodyDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return potentiallyCompatible_TypeDecl_BodyDecl_value;
  }
  /** @apilevel internal */
  private boolean potentiallyCompatible_compute(TypeDecl type, BodyDecl candidateDecl) {
      if (super.potentiallyCompatible(type, candidateDecl) && type.isTypeVariable()) {
        return true;
      } else if (!super.potentiallyCompatible(type, candidateDecl)) {
        return false;
      }
  
      InterfaceDecl iDecl = (InterfaceDecl) type;
      FunctionDescriptor fd = iDecl.functionDescriptor();
      boolean foundMethod = false;
      if (fd.method.hasValue()) {
        MethodDecl targetMethod = fd.method.get();
        for (MethodDecl decl : potentiallyApplicableMethods(fd)) {
          if (decl.isStatic() && targetMethod.arity() == decl.arity()) {
            foundMethod = true;
            break;
          } else if (!decl.isStatic() && targetMethod.arity() - 1 == decl.arity()) {
            foundMethod = true;
            break;
          }
        }
      }
      return foundMethod;
    }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (getTypeAccessNoTransform() != null && _callerNode == getTypeAccess()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/MethodReference.jrag:216
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:252
   * @apilevel internal
   */
  public boolean Define_assignmentContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getTypeAccessNoTransform() != null && _callerNode == getTypeAccess()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:416
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
    if (getTypeAccessNoTransform() != null && _callerNode == getTypeAccess()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:417
      return false;
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
    if (getTypeAccessNoTransform() != null && _callerNode == getTypeAccess()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:418
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
    if (getTypeAccessNoTransform() != null && _callerNode == getTypeAccess()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:419
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
    if (getTypeAccessNoTransform() != null && _callerNode == getTypeAccess()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:420
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

}
