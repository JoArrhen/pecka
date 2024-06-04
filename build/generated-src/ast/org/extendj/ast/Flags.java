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
 * Bytecode flags for fields etc.
 * @ast interface
 * @aspect BytecodeSignatures
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/extendj/java5/frontend/BytecodeSignatures.jrag:396
 */
public interface Flags {

     
    public int ACC_PUBLIC = 0x0001;

     
    public int ACC_PRIVATE = 0x0002;

     
    public int ACC_PROTECTED = 0x0004;

     
    public int ACC_STATIC = 0x0008;

     
    public int ACC_FINAL = 0x0010;

     
    public int ACC_SUPER = 0x0020;

     
    public int ACC_SYNCHRONIZED = 0x0020;

     
    public int ACC_VOLATILE = 0x0040;

     
    public int ACC_BRIDGE = 0x0040;

     
    public int ACC_TRANSIENT = 0x0080;

     
    public int ACC_VARARGS = 0x0080;

     
    public int ACC_NATIVE = 0x0100;

     
    public int ACC_INTERFACE = 0x0200;

     
    public int ACC_ABSTRACT = 0x0400;

     
    public int ACC_STRICT = 0x0800;

     
    public int ACC_SYNTHETIC = 0x1000;

     
    public int ACC_ANNOTATION = 0x2000;

     
    public int ACC_ENUM = 0x4000;
}
