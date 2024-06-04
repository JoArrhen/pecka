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
 * @aspect Solver
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:357
 */
 class WorkList extends java.lang.Object {
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:358
   */
  
        private final Map<PFGNode, Set<AllocationSite>> map = new HashMap<>();
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:359
   */
  
        private final Queue<PFGNode> queue = new LinkedList<>();
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:360
   */
  
        private final Map<PFGNode, Set<AllocationSite>> done = new HashMap<>();
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:362
   */
  

        public void add(Pair<PFGNode, Set<AllocationSite>> e) {
            PFGNode node = e.fst;
            Set<AllocationSite> allocs = e.snd;

            if (!(node instanceof FieldAccess)) {
                DeclarationSite declaration = (DeclarationSite) node;
                Set<AllocationSite> filtered_allocs = new HashSet<>();
                for (AllocationSite alloc : allocs) {
                    if (declaration.type2().subtype(alloc.atype()) || !alloc.isNull()) {
                        filtered_allocs.add(alloc);
                    }
                }
                allocs = filtered_allocs;
            }

            if (!map.containsKey(node)) {
                map.put(node, new HashSet<>(allocs));
                queue.add(node);
            } else {
                map.get(node).addAll(allocs);
            }
            if (!done.containsKey(node)) done.put(node, new HashSet<>());
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:386
   */
  

        public Pair<PFGNode, Set<AllocationSite>> remove() {
            PFGNode node = queue.remove();
            Set<AllocationSite> set = map.remove(node);
            set.removeAll(done.get(node));
            done.get(node).addAll(set);
            return new Pair<>(node, set);
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:394
   */
  

        public boolean isEmpty() {
            return queue.isEmpty();
        }

}
