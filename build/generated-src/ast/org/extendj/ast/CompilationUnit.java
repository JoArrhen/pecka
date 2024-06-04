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
 * A compilation unit.
 * Each compilation unit represents an input source file.
 * 
 * <p>See JLS 6 &sect;7.3.
 * @ast node
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/grammar/Java.ast:45
 * @astdecl CompilationUnit : ASTNode ::= <PackageDecl:String> ImportDecl* TypeDecl*;
 * @production CompilationUnit : {@link ASTNode} ::= <span class="component">&lt;PackageDecl:{@link String}&gt;</span> <span class="component">{@link ImportDecl}*</span> <span class="component">{@link TypeDecl}*</span>;

 */
public class CompilationUnit extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrint.jadd:213
   */
  public void prettyPrint(PrettyPrinter out) {
    if (hasPackageDecl()) {
      out.print("package ");
      out.print(getPackageDecl());
      out.print(";");
      out.println();
    }
    if (!out.isNewLine()) {
      out.println();
    }
    out.print(getImportDeclList());
    if (!out.isNewLine()) {
      out.println();
    }
    out.println();
    out.join(getTypeDecls(), new PrettyPrinter.Joiner() {
      @Override
      public void printSeparator(PrettyPrinter out) {
        out.println();
        out.println();
      }
    });
  }
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:533
   */
  private ClassSource classSource = ClassSource.NONE;
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:535
   */
  private boolean fromSource = false;
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:537
   */
  public void setClassSource(ClassSource source) {
    this.classSource = source;
  }
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:541
   */
  public ClassSource getClassSource() {
    return classSource;
  }
  /**
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:545
   */
  public void setFromSource(boolean value) {
    this.fromSource = value;
  }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:92
   */
  public Collection<Problem> parseErrors() {
    return parseErrors;
  }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:96
   */
  public void addParseError(Problem msg) {
    parseErrors.add(msg);
  }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:100
   */
  protected Collection<Problem> parseErrors = new ArrayList<Problem>();
  /**
   * @aspect PrettyPrintUtil
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:67
   */
  @Override public String toString() {
    return classSource.sourceName();
  }
  /**
   * @declaredat ASTNode:1
   */
  public CompilationUnit() {
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
    setChild(new List(), 1);
  }
  /**
   * @declaredat ASTNode:15
   */
  @ASTNodeAnnotation.Constructor(
    name = {"PackageDecl", "ImportDecl", "TypeDecl"},
    type = {"String", "List<ImportDecl>", "List<TypeDecl>"},
    kind = {"Token", "List", "List"}
  )
  public CompilationUnit(String p0, List<ImportDecl> p1, List<TypeDecl> p2) {
    setPackageDecl(p0);
    setChild(p1, 0);
    setChild(p2, 1);
  }
  /**
   * @declaredat ASTNode:25
   */
  public CompilationUnit(beaver.Symbol p0, List<ImportDecl> p1, List<TypeDecl> p2) {
    setPackageDecl(p0);
    setChild(p1, 0);
    setChild(p2, 1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:31
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:37
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:41
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    packageName_reset();
    lookupType_String_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:47
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
    CompilationUnit_problems_computed = null;
    CompilationUnit_problems_value = null;
    contributorMap_CompilationUnit_problems = null;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:54
   */
  public CompilationUnit clone() throws CloneNotSupportedException {
    CompilationUnit node = (CompilationUnit) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:59
   */
  public CompilationUnit copy() {
    try {
      CompilationUnit node = (CompilationUnit) clone();
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
   * @declaredat ASTNode:78
   */
  @Deprecated
  public CompilationUnit fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:88
   */
  public CompilationUnit treeCopyNoTransform() {
    CompilationUnit tree = (CompilationUnit) copy();
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
   * @declaredat ASTNode:108
   */
  public CompilationUnit treeCopy() {
    CompilationUnit tree = (CompilationUnit) copy();
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
   * Replaces the lexeme PackageDecl.
   * @param value The new value for the lexeme PackageDecl.
   * @apilevel high-level
   */
  public CompilationUnit setPackageDecl(String value) {
    tokenString_PackageDecl = value;
    return this;
  }
  /** @apilevel internal 
   */
  protected String tokenString_PackageDecl;
  /**
   */
  public int PackageDeclstart;
  /**
   */
  public int PackageDeclend;
  /**
   * JastAdd-internal setter for lexeme PackageDecl using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme PackageDecl
   * @apilevel internal
   */
  public CompilationUnit setPackageDecl(beaver.Symbol symbol) {
    if (symbol.value != null && !(symbol.value instanceof String))
    throw new UnsupportedOperationException("setPackageDecl is only valid for String lexemes");
    tokenString_PackageDecl = (String)symbol.value;
    PackageDeclstart = symbol.getStart();
    PackageDeclend = symbol.getEnd();
    return this;
  }
  /**
   * Retrieves the value for the lexeme PackageDecl.
   * @return The value for the lexeme PackageDecl.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Token(name="PackageDecl")
  public String getPackageDecl() {
    return tokenString_PackageDecl != null ? tokenString_PackageDecl : "";
  }
  /**
   * Replaces the ImportDecl list.
   * @param list The new list node to be used as the ImportDecl list.
   * @apilevel high-level
   */
  public CompilationUnit setImportDeclList(List<ImportDecl> list) {
    setChild(list, 0);
    return this;
  }
  /**
   * Retrieves the number of children in the ImportDecl list.
   * @return Number of children in the ImportDecl list.
   * @apilevel high-level
   */
  public int getNumImportDecl() {
    return getImportDeclList().getNumChild();
  }
  /**
   * Retrieves the number of children in the ImportDecl list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the ImportDecl list.
   * @apilevel low-level
   */
  public int getNumImportDeclNoTransform() {
    return getImportDeclListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the ImportDecl list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the ImportDecl list.
   * @apilevel high-level
   */
  public ImportDecl getImportDecl(int i) {
    return (ImportDecl) getImportDeclList().getChild(i);
  }
  /**
   * Check whether the ImportDecl list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasImportDecl() {
    return getImportDeclList().getNumChild() != 0;
  }
  /**
   * Append an element to the ImportDecl list.
   * @param node The element to append to the ImportDecl list.
   * @apilevel high-level
   */
  public CompilationUnit addImportDecl(ImportDecl node) {
    List<ImportDecl> list = (parent == null) ? getImportDeclListNoTransform() : getImportDeclList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public CompilationUnit addImportDeclNoTransform(ImportDecl node) {
    List<ImportDecl> list = getImportDeclListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the ImportDecl list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public CompilationUnit setImportDecl(ImportDecl node, int i) {
    List<ImportDecl> list = getImportDeclList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the ImportDecl list.
   * @return The node representing the ImportDecl list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="ImportDecl")
  public List<ImportDecl> getImportDeclList() {
    List<ImportDecl> list = (List<ImportDecl>) getChild(0);
    return list;
  }
  /**
   * Retrieves the ImportDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the ImportDecl list.
   * @apilevel low-level
   */
  public List<ImportDecl> getImportDeclListNoTransform() {
    return (List<ImportDecl>) getChildNoTransform(0);
  }
  /**
   * @return the element at index {@code i} in the ImportDecl list without
   * triggering rewrites.
   */
  public ImportDecl getImportDeclNoTransform(int i) {
    return (ImportDecl) getImportDeclListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the ImportDecl list.
   * @return The node representing the ImportDecl list.
   * @apilevel high-level
   */
  public List<ImportDecl> getImportDecls() {
    return getImportDeclList();
  }
  /**
   * Retrieves the ImportDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the ImportDecl list.
   * @apilevel low-level
   */
  public List<ImportDecl> getImportDeclsNoTransform() {
    return getImportDeclListNoTransform();
  }
  /**
   * Replaces the TypeDecl list.
   * @param list The new list node to be used as the TypeDecl list.
   * @apilevel high-level
   */
  public CompilationUnit setTypeDeclList(List<TypeDecl> list) {
    setChild(list, 1);
    return this;
  }
  /**
   * Retrieves the number of children in the TypeDecl list.
   * @return Number of children in the TypeDecl list.
   * @apilevel high-level
   */
  public int getNumTypeDecl() {
    return getTypeDeclList().getNumChild();
  }
  /**
   * Retrieves the number of children in the TypeDecl list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the TypeDecl list.
   * @apilevel low-level
   */
  public int getNumTypeDeclNoTransform() {
    return getTypeDeclListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the TypeDecl list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the TypeDecl list.
   * @apilevel high-level
   */
  public TypeDecl getTypeDecl(int i) {
    return (TypeDecl) getTypeDeclList().getChild(i);
  }
  /**
   * Check whether the TypeDecl list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasTypeDecl() {
    return getTypeDeclList().getNumChild() != 0;
  }
  /**
   * Append an element to the TypeDecl list.
   * @param node The element to append to the TypeDecl list.
   * @apilevel high-level
   */
  public CompilationUnit addTypeDecl(TypeDecl node) {
    List<TypeDecl> list = (parent == null) ? getTypeDeclListNoTransform() : getTypeDeclList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public CompilationUnit addTypeDeclNoTransform(TypeDecl node) {
    List<TypeDecl> list = getTypeDeclListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the TypeDecl list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public CompilationUnit setTypeDecl(TypeDecl node, int i) {
    List<TypeDecl> list = getTypeDeclList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the TypeDecl list.
   * @return The node representing the TypeDecl list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="TypeDecl")
  public List<TypeDecl> getTypeDeclList() {
    List<TypeDecl> list = (List<TypeDecl>) getChild(1);
    return list;
  }
  /**
   * Retrieves the TypeDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeDecl list.
   * @apilevel low-level
   */
  public List<TypeDecl> getTypeDeclListNoTransform() {
    return (List<TypeDecl>) getChildNoTransform(1);
  }
  /**
   * @return the element at index {@code i} in the TypeDecl list without
   * triggering rewrites.
   */
  public TypeDecl getTypeDeclNoTransform(int i) {
    return (TypeDecl) getTypeDeclListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the TypeDecl list.
   * @return The node representing the TypeDecl list.
   * @apilevel high-level
   */
  public List<TypeDecl> getTypeDecls() {
    return getTypeDeclList();
  }
  /**
   * Retrieves the TypeDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeDecl list.
   * @apilevel low-level
   */
  public List<TypeDecl> getTypeDeclsNoTransform() {
    return getTypeDeclListNoTransform();
  }
  /**
   * @aspect NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:73
   */
  private Collection<Problem> refined_NameCheck_CompilationUnit_nameProblems()
{
    Collection<Problem> problems = new LinkedList<Problem>();
    for (int i = 0; i < getNumImportDecl(); i++) {
      ImportDecl decl = getImportDecl(i);
      if (!decl.isOnDemand()) {
        for (TypeDecl importedType : decl.importedTypes()) {
          for (TypeDecl local : localLookupType(importedType.name())) {
            if (local != importedType) {
              problems.add(errorf("imported type %s conflicts with visible type", decl.typeName()));
            }
          }
        }
      }
    }
    return problems;
  }
  /**
   * @aspect TypeScopePropagation
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:430
   */
  private SimpleSet<TypeDecl> refined_TypeScopePropagation_CompilationUnit_Child_lookupType_String(String name)
{
    // Locally declared types in the compilation unit.
    SimpleSet<TypeDecl> result = localLookupType(name);
    if (!result.isEmpty()) {
      return result;
    }

    // Imported types.
    result = importedTypes(name);
    if (!result.isEmpty()) {
      return result;
    }

    // Types in the same package.
    TypeDecl pkgType = lookupType(packageName(), name);
    if (pkgType.accessibleFromPackage(packageName())) {
      return pkgType;
    }

    // Types imported on demand.
    result = importedTypesOnDemand(name);
    if (!result.isEmpty()) {
      return result;
    }

    // Include primitive types.
    TypeDecl primitiveType = lookupType(PRIMITIVE_PACKAGE_NAME, name);
    if (!primitiveType.isUnknown()) {
      return primitiveType;
    }

    // 7.5.5 Automatic Imports
    TypeDecl defaultType = lookupType("java.lang", name);
    if (defaultType.accessibleFromPackage(packageName())) {
      return defaultType;
    }
    return lookupType(name);
  }
  /**
   * @aspect <NoAspect>
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:268
   */
  /** @apilevel internal */
protected java.util.Map<ASTNode, java.util.Set<ASTNode>> contributorMap_CompilationUnit_problems = null;

  /** @apilevel internal */
  protected void survey_CompilationUnit_problems() {
    if (contributorMap_CompilationUnit_problems == null) {
      contributorMap_CompilationUnit_problems = new java.util.IdentityHashMap<ASTNode, java.util.Set<ASTNode>>();
      collect_contributors_CompilationUnit_problems(this, contributorMap_CompilationUnit_problems);
    }
  }

  /**
   * @return The path to the source file, or the path to the file inside a Jar
   * file.
   * @attribute syn
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:97
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ClassPath", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:97")
  public String relativeName() {
    String relativeName_value = getClassSource().relativeName();
    return relativeName_value;
  }
  /**
   * @return The path to the source file, or the enclosing Jar file.
   * @attribute syn
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:102
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ClassPath", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:102")
  public String pathName() {
    String pathName_value = getClassSource().pathName();
    return pathName_value;
  }
  /**
   * @return {@code true} if this compilation unit was parsed from source.
   * @attribute syn
   * @aspect ClassPath
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:107
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ClassPath", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:107")
  public boolean fromSource() {
    boolean fromSource_value = fromSource;
    return fromSource_value;
  }
  /** Searches for a type with the given simple name in this compilation unit. 
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:470
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeScopePropagation", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:470")
  public SimpleSet<TypeDecl> localLookupType(String name) {
    {
        for (int i = 0; i < getNumTypeDecl(); i++) {
          if (getTypeDecl(i).name().equals(name)) {
            return getTypeDecl(i);
          }
        }
        return emptySet();
      }
  }
  /**
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:479
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeScopePropagation", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:479")
  public SimpleSet<TypeDecl> importedTypes(String name) {
    {
        SimpleSet<TypeDecl> result = emptySet();
        for (int i = 0; i < getNumImportDecl(); i++) {
          if (!getImportDecl(i).isOnDemand()) {
            for (TypeDecl type : getImportDecl(i).importedTypes(name)) {
              result = result.add(type);
            }
          }
        }
        return result;
      }
  }
  /**
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:491
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeScopePropagation", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:491")
  public SimpleSet<TypeDecl> importedTypesOnDemand(String name) {
    {
        SimpleSet<TypeDecl> result = emptySet();
        for (int i = 0; i < getNumImportDecl(); i++) {
          if (getImportDecl(i).isOnDemand()) {
            for (TypeDecl type : getImportDecl(i).importedTypes(name)) {
              result = result.add(type);
            }
          }
        }
        return result;
      }
  }
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:73
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:73")
  public Collection<Problem> nameProblems() {
    {
        Collection<Problem> problems = refined_NameCheck_CompilationUnit_nameProblems();
        for (int i = 0; i < getNumImportDecl(); i++) {
          if (getImportDecl(i) instanceof SingleStaticImportDecl) {
            SingleStaticImportDecl decl = (SingleStaticImportDecl) getImportDecl(i);
            String name = decl.name();
            if (!decl.importedTypes(name).isEmpty()) {
              TypeDecl type = decl.importedTypes(name).iterator().next();
              if (localLookupType(name).contains(type)) {
                problems.add(decl.errorf("the imported name %s.%s is already declared in this compilation unit",
                    packageName(), name));
              }
            }
          }
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:270
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ErrorCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:270")
  public Collection<Problem> errors() {
    {
        Collection<Problem> errors = new LinkedList<Problem>();
        for (Problem problem : problems()) {
          if (problem.severity() == Problem.Severity.ERROR) {
            errors.add(problem);
          }
        }
        return errors;
      }
  }
  /**
   * @attribute syn
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:280
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ErrorCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:280")
  public Collection<Problem> warnings() {
    {
        Collection<Problem> warnings = new LinkedList<Problem>();
        for (Problem problem : problems()) {
          if (problem.severity() == Problem.Severity.WARNING) {
            warnings.add(problem);
          }
        }
        return warnings;
      }
  }
  /** @apilevel internal */
  private void packageName_reset() {
    packageName_computed = null;
    packageName_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle packageName_computed = null;

  /** @apilevel internal */
  protected String packageName_value;

  /**
   * @attribute syn
   * @aspect TypeName
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/QualifiedNames.jrag:117
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeName", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/QualifiedNames.jrag:117")
  public String packageName() {
    ASTState state = state();
    if (packageName_computed == ASTState.NON_CYCLE || packageName_computed == state().cycle()) {
      return packageName_value;
    }
    packageName_value = packageName_compute();
    if (state().inCircle()) {
      packageName_computed = state().cycle();
    
    } else {
      packageName_computed = ASTState.NON_CYCLE;
    
    }
    return packageName_value;
  }
  /** @apilevel internal */
  private String packageName_compute() {return getPackageDecl();}
  /**
   * @attribute syn
   * @aspect PrettyPrintUtil
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:348
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PrettyPrintUtil", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/PrettyPrintUtil.jrag:348")
  public boolean hasPackageDecl() {
    boolean hasPackageDecl_value = !getPackageDecl().isEmpty();
    return hasPackageDecl_value;
  }
  /**
   * @attribute syn
   * @aspect StaticImports
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/StaticImports.jrag:213
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StaticImports", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/StaticImports.jrag:213")
  public SimpleSet<Variable> importedFields(String name) {
    {
        SimpleSet<Variable> set = emptySet();
        for (int i = 0; i < getNumImportDecl(); i++) {
          if (!getImportDecl(i).isOnDemand()) {
            for (Variable field : getImportDecl(i).importedFields(name)) {
              set = set.add(field);
            }
          }
        }
        return set;
      }
  }
  /**
   * @attribute syn
   * @aspect StaticImports
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/StaticImports.jrag:225
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StaticImports", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/StaticImports.jrag:225")
  public SimpleSet<Variable> importedFieldsOnDemand(String name) {
    {
        SimpleSet<Variable> set = emptySet();
        for (int i = 0; i < getNumImportDecl(); i++) {
          if (getImportDecl(i).isOnDemand()) {
            for (Variable field : getImportDecl(i).importedFields(name)) {
              set = set.add(field);
            }
          }
        }
        return set;
      }
  }
  /**
   * @attribute syn
   * @aspect StaticImports
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/StaticImports.jrag:255
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StaticImports", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/StaticImports.jrag:255")
  public Collection<MethodDecl> importedMethods(String name) {
    {
        Collection<MethodDecl> methods = new ArrayList<MethodDecl>();
        for (int i = 0; i < getNumImportDecl(); i++) {
          if (!getImportDecl(i).isOnDemand()) {
            methods.addAll(getImportDecl(i).importedMethods(name));
          }
        }
        return methods;
      }
  }
  /**
   * @attribute syn
   * @aspect StaticImports
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/StaticImports.jrag:265
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StaticImports", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/StaticImports.jrag:265")
  public Collection<MethodDecl> importedMethodsOnDemand(String name) {
    {
        Collection<MethodDecl> methods = new ArrayList<MethodDecl>();
        for (int i = 0; i < getNumImportDecl(); i++) {
          if (getImportDecl(i).isOnDemand()) {
            methods.addAll(getImportDecl(i).importedMethods(name));
          }
        }
        return methods;
      }
  }
  /**
   * @attribute inh
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:131
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="LookupFullyQualifiedTypes", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:131")
  public TypeDecl lookupType(String packageName, String typeName) {
    TypeDecl lookupType_String_String_value = getParent().Define_lookupType(this, null, packageName, typeName);
    return lookupType_String_String_value;
  }
  /**
   * @attribute inh
   * @aspect TypeScopePropagation
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:416
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TypeScopePropagation", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:416")
  public SimpleSet<TypeDecl> lookupType(String name) {
    Object _parameters = name;
    if (lookupType_String_computed == null) lookupType_String_computed = new java.util.HashMap(4);
    if (lookupType_String_values == null) lookupType_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (lookupType_String_values.containsKey(_parameters)
        && lookupType_String_computed.containsKey(_parameters)
        && (lookupType_String_computed.get(_parameters) == ASTState.NON_CYCLE || lookupType_String_computed.get(_parameters) == state().cycle())) {
      return (SimpleSet<TypeDecl>) lookupType_String_values.get(_parameters);
    }
    SimpleSet<TypeDecl> lookupType_String_value = getParent().Define_lookupType(this, null, name);
    if (state().inCircle()) {
      lookupType_String_values.put(_parameters, lookupType_String_value);
      lookupType_String_computed.put(_parameters, state().cycle());
    
    } else {
      lookupType_String_values.put(_parameters, lookupType_String_value);
      lookupType_String_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return lookupType_String_value;
  }
  /** @apilevel internal */
  private void lookupType_String_reset() {
    lookupType_String_computed = null;
    lookupType_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map lookupType_String_values;
  /** @apilevel internal */
  protected java.util.Map lookupType_String_computed;
  /**
   * @attribute inh
   * @aspect StaticImports
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/StaticImports.jrag:211
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="StaticImports", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/StaticImports.jrag:211")
  public SimpleSet<Variable> lookupVariable(String name) {
    SimpleSet<Variable> lookupVariable_String_value = getParent().Define_lookupVariable(this, null, name);
    return lookupVariable_String_value;
  }
  /**
   * @attribute inh
   * @aspect StaticImports
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/StaticImports.jrag:253
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="StaticImports", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/StaticImports.jrag:253")
  public Collection<MethodDecl> lookupMethod(String name) {
    Collection<MethodDecl> lookupMethod_String_value = getParent().Define_lookupMethod(this, null, name);
    return lookupMethod_String_value;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:40
   * @apilevel internal
   */
  public CompilationUnit Define_enclosingCompilationUnit(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return this;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/cat/src/jastadd/callgraph/extension.jrag:40
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingCompilationUnit
   */
  protected boolean canDefine_enclosingCompilationUnit(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:114
   * @apilevel internal
   */
  public boolean Define_handlesException(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    if (_callerNode == getImportDeclListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/StaticImports.jrag:321
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else if (_callerNode == getTypeDeclListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ExceptionHandling.jrag:199
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else {
      return getParent().Define_handlesException(this, _callerNode, exceptionType);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/TryWithResources.jrag:114
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute handlesException
   */
  protected boolean canDefine_handlesException(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:541
   * @apilevel internal
   */
  public boolean Define_canResolve(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ResolveAmbiguousNames.jrag:541
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute canResolve
   */
  protected boolean canDefine_canResolve(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:110
   * @apilevel internal
   */
  public CompilationUnit Define_compilationUnit(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return this;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ClassPath.jrag:110
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute compilationUnit
   */
  protected boolean canDefine_compilationUnit(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:66
   * @apilevel internal
   */
  public boolean Define_isIncOrDec(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getTypeDeclListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:68
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else {
      return getParent().Define_isIncOrDec(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/DefiniteAssignment.jrag:66
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isIncOrDec
   */
  protected boolean canDefine_isIncOrDec(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/GenericMethods.jrag:231
   * @apilevel internal
   */
  public SimpleSet<TypeDecl> Define_lookupType(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getImportDeclListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupType.jrag:555
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return lookupType(name);
    }
    else {
      int childIndex = this.getIndexOfChild(_callerNode);
      {
          SimpleSet<TypeDecl> result = emptySet();
          for (TypeDecl typeDecl : refined_TypeScopePropagation_CompilationUnit_Child_lookupType_String(name)) {
            if (typeDecl instanceof ParTypeDecl) {
              result = result.add(((ParTypeDecl) typeDecl).genericDecl());
            } else {
              result = result.add(typeDecl);
            }
          }
          return result;
        }
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:54
   * @apilevel internal
   */
  public SimpleSet<TypeDecl> Define_allImportedTypes(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getImportDeclListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:56
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return importedTypes(name);
    }
    else {
      return getParent().Define_allImportedTypes(this, _callerNode, name);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:54
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute allImportedTypes
   */
  protected boolean canDefine_allImportedTypes(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getImportDeclListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/SyntacticClassification.jrag:93
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return NameType.PACKAGE_NAME;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/QualifiedNames.jrag:113
   * @apilevel internal
   */
  public String Define_packageName(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return packageName();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/QualifiedNames.jrag:113
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute packageName
   */
  protected boolean canDefine_packageName(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:593
   * @apilevel internal
   */
  public TypeDecl Define_enclosingType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:30
   * @apilevel internal
   */
  public BodyDecl Define_enclosingBodyDecl(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/NameCheck.jrag:30
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingBodyDecl
   */
  protected boolean canDefine_enclosingBodyDecl(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:622
   * @apilevel internal
   */
  public boolean Define_isNestedType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:622
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isNestedType
   */
  protected boolean canDefine_isNestedType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:631
   * @apilevel internal
   */
  public boolean Define_isMemberType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getTypeDeclListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:634
      int childIndex = _callerNode.getIndexOfChild(_childNode);
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:649
   * @apilevel internal
   */
  public boolean Define_isLocalClass(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:649
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isLocalClass
   */
  protected boolean canDefine_isLocalClass(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:684
   * @apilevel internal
   */
  public String Define_hostPackage(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return packageName();
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:684
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute hostPackage
   */
  protected boolean canDefine_hostPackage(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java7/frontend/MultiCatch.jrag:76
   * @apilevel internal
   */
  public TypeDecl Define_hostType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getImportDeclListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/TypeAnalysis.jrag:696
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return null;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Annotations.jrag:95
   * @apilevel internal
   */
  public boolean Define_inComplexAnnotation(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:857
   * @apilevel internal
   */
  public String Define_typeVariableContext(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return "";
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Generics.jrag:857
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeVariableContext
   */
  protected boolean canDefine_typeVariableContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:129
   * @apilevel internal
   */
  public boolean Define_isOriginalEnumConstructor(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:550
   * @apilevel internal
   */
  public boolean Define_inEnumInitializer(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/Enums.jrag:550
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inEnumInitializer
   */
  protected boolean canDefine_inEnumInitializer(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:522
   * @apilevel internal
   */
  public Block Define_enclosingBlock(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/MethodSignature.jrag:522
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingBlock
   */
  protected boolean canDefine_enclosingBlock(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/LookupVariable.jrag:30
   * @apilevel internal
   */
  public SimpleSet<Variable> Define_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getTypeDeclListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/StaticImports.jrag:199
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      {
          SimpleSet<Variable> set = importedFields(name);
          if (!set.isEmpty()) {
            return set;
          }
          set = importedFieldsOnDemand(name);
          if (!set.isEmpty()) {
            return set;
          }
          return lookupVariable(name);
        }
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
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:116
   * @apilevel internal
   */
  public Collection<MethodDecl> Define_lookupMethod(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getTypeDeclListNoTransform()) {
      // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/StaticImports.jrag:241
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      {
          Collection<MethodDecl> methods = importedMethods(name);
          if (!methods.isEmpty()) {
            return methods;
          }
          methods = importedMethodsOnDemand(name);
          if (!methods.isEmpty()) {
            return methods;
          }
          return lookupMethod(name);
        }
    }
    else {
      return getParent().Define_lookupMethod(this, _callerNode, name);
    }
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/LookupMethod.jrag:116
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupMethod
   */
  protected boolean canDefine_lookupMethod(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:29
   * @apilevel internal
   */
  public int Define_typeVarPosition(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return -1;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:29
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeVarPosition
   */
  protected boolean canDefine_typeVarPosition(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:32
   * @apilevel internal
   */
  public boolean Define_typeVarInMethod(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:32
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeVarInMethod
   */
  protected boolean canDefine_typeVarInMethod(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:31
   * @apilevel internal
   */
  public int Define_genericMethodLevel(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return 0;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TypeVariablePositions.jrag:31
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute genericMethodLevel
   */
  protected boolean canDefine_genericMethodLevel(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:252
   * @apilevel internal
   */
  public boolean Define_assignmentContext(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
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
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
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
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
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
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
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
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/TargetType.jrag:256
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute numericContext
   */
  protected boolean canDefine_numericContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EnclosingLambda.jrag:29
   * @apilevel internal
   */
  public LambdaExpr Define_enclosingLambda(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java8/frontend/EnclosingLambda.jrag:29
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingLambda
   */
  protected boolean canDefine_enclosingLambda(ASTNode _callerNode, ASTNode _childNode) {
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
   * Collects semantic errors in the AST.
   * 
   * <p>Separate error checks are added using individual contribution statements.
   * @attribute coll
   * @aspect ErrorCheck
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:268
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="ErrorCheck", declaredAt="/Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/ErrorCheck.jrag:268")
  public LinkedList<Problem> problems() {
    ASTState state = state();
    if (CompilationUnit_problems_computed == ASTState.NON_CYCLE || CompilationUnit_problems_computed == state().cycle()) {
      return CompilationUnit_problems_value;
    }
    CompilationUnit_problems_value = problems_compute();
    if (state().inCircle()) {
      CompilationUnit_problems_computed = state().cycle();
    
    } else {
      CompilationUnit_problems_computed = ASTState.NON_CYCLE;
    
    }
    return CompilationUnit_problems_value;
  }
  /** @apilevel internal */
  private LinkedList<Problem> problems_compute() {
    ASTNode node = this;
    while (node != null && !(node instanceof CompilationUnit)) {
      node = node.getParent();
    }
    CompilationUnit root = (CompilationUnit) node;
    root.survey_CompilationUnit_problems();
    LinkedList<Problem> _computedValue = new LinkedList<Problem>();
    if (root.contributorMap_CompilationUnit_problems.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_CompilationUnit_problems.get(this)) {
        contributor.contributeTo_CompilationUnit_problems(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle CompilationUnit_problems_computed = null;

  /** @apilevel internal */
  protected LinkedList<Problem> CompilationUnit_problems_value;

  /** @apilevel internal */
  protected void collect_contributors_CompilationUnit_problems(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java4/frontend/NameCheck.jrag:71
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
