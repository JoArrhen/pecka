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
 * @ast class
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:50
 */
public class InclusionEdge extends Edge {
@Override public   /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:62
   */
   void drawArrow() {
        drawArrow(from.getNode(), to.getNode(), GREEN);
    }
@Override public   /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:67
   */
   String toString() {
        return "Inclusion: " + from + " -> " + to;
    }
@Override public   /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:72
   */
   boolean equals(Object o) {
        if (o == this) return true;
        if (getClass() != o.getClass()) return false;
        InclusionEdge o2 = (InclusionEdge) o;
        return o2.from.equals(this.from) && o2.to.equals(this.to);
    }
@Override public final   /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:80
   */
   int hashCode() {
        return from.hashCode() ^ to.hashCode();
    }
public final   /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:51
   */
   DeclarationSite from;
public final   /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:52
   */
   DeclarationSite to;
protected   /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:53
   */
   String GREEN = "#2F08";
public   /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:55
   */
   InclusionEdge(DeclarationSite from, DeclarationSite to) {
        if (from == null || to == null) throw new Error(String.format("from/to cannot be null, %s, %s", from, to));
        this.from = from;
        this.to = to;
    }

}
