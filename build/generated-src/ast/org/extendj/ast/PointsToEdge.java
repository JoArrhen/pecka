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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:16
 */
public class PointsToEdge extends Edge {
@Override protected   /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:27
   */
   void drawArrow() {
        drawArrow(from.getNode(), to.getNode(), BLUE);
    }
@Override public   /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:32
   */
   String toString() {
        return "PointsTo: " + from + " -> " + to;
    }
@Override public   /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:37
   */
   boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PointsToEdge)) return false;
        PointsToEdge o2 = (PointsToEdge) o;
        return o2.from == this.from && o2.to == this.to;
    }
@Override public final   /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:45
   */
   int hashCode() {
        return from.hashCode() ^ to.hashCode();
    }
public final   /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:17
   */
   DeclarationSite from;
public final   /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:18
   */
   AllocationSite to;
protected   /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:19
   */
   String BLUE = "#0CF8";
public   /**
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Edge.jrag:21
   */
   PointsToEdge(DeclarationSite from, AllocationSite to) {
        this.from = from;
        this.to = to;
    }

}
