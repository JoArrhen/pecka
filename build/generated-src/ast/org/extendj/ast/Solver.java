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
 * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:48
 */
 class Solver extends java.lang.Object {
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:51
   */
  
        // Based on slides by Tian Tan at Nanjing University https://cs.nju.edu.cn/tiantan/software-analysis/PTA-FD.pdf

        private final Map<PFGNode, Set<PFGNode>> pfg = new HashMap<>();
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:52
   */
  
        private final WorkList worklist = new WorkList();
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:53
   */
   // might need set/uniqueness property
        private final Map<PFGNode, Set<AllocationSite>> pointsTo = new HashMap<>();
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:54
   */
  
        private Set<InclusionEdge> inclusionEdges;
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:55
   */
  
        private final Set<FieldStoreEdge> storeEdges;
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:56
   */
  
        private final Set<FieldLoadEdge> loadEdges;
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:57
   */
  
        private Set<PointsToEdge> pointsToEdges;
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:58
   */
  
        private final Map<DeclarationSite, Set<FieldLoadEdge>> loadMap = new HashMap<>();
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:59
   */
  
        private final Map<DeclarationSite, Set<FieldStoreEdge>> storeMap = new HashMap<>();
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:60
   */
  
        private final java.util.List<TypeDecl> pointerTypes;
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:62
   */
  

        public Solver(Set<InclusionEdge> inclusionEdges, Set<PointsToEdge> pointsToEdges, java.util.List<TypeDecl> pointerTypes) {
            this.inclusionEdges = inclusionEdges;
            this.pointsToEdges = pointsToEdges;
            //this.pointsToEdges.addAll(parameterPointsToEdges(inclusionEdges));
            this.pointerTypes = pointerTypes;

            //if (pointerTypes != null) { // null when testing the filter
            //    filter();
            //}

            this.storeEdges = this.inclusionEdges.stream().filter(x -> x instanceof FieldStoreEdge).map(x -> (FieldStoreEdge) x).collect(Collectors.toSet());
            this.loadEdges = this.inclusionEdges.stream().filter(x -> x instanceof FieldLoadEdge).map(x -> (FieldLoadEdge) x).collect(Collectors.toSet());
            this.inclusionEdges = this.inclusionEdges.stream().filter(x -> !(x instanceof FieldLoadEdge) && !(x instanceof FieldStoreEdge)).collect(Collectors.toSet());
            computeFieldSets();
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:78
   */
  

        private void addEdge(PFGNode s, PFGNode t) {
            if (pfg.containsKey(s) && pfg.get(s).contains(t)) return;
            
            pfg.putIfAbsent(s, new HashSet<>());
            pfg.get(s).add(t);
            if (pointsTo.containsKey(s)) {
                worklist.add(new Pair<>(t, pointsTo.get(s)));
            }
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:88
   */
  

        public Set<PointsToEdge> solution() {

            for (PointsToEdge pte : pointsToEdges) {
                worklist.add(new Pair<>(pte.from, new HashSet<>(Arrays.asList(pte.to))));
            }

            for (InclusionEdge ie : inclusionEdges) {
                addEdge(ie.from, ie.to);
            }

            while (!worklist.isEmpty()) {
                Pair<PFGNode, Set<AllocationSite>> item = worklist.remove();
                PFGNode n = item.fst;
                Set<AllocationSite> pts = item.snd;
                pts.removeAll(pointsTo.getOrDefault(n, Collections.EMPTY_SET)); // compute delta
                
                // Propagate
                if (!pts.isEmpty()) {
                    pointsTo.putIfAbsent(n, new HashSet<>());
                    pointsTo.get(n).addAll(pts);
                    for (PFGNode s : pfg.getOrDefault(n, Collections.emptySet())) {
                        worklist.add(new Pair<>(s, pts));
                    }
                }
                fieldLoop(n, pts);

                //if (!(n instanceof FieldAccess)) {
                //    DeclarationSite ds = (DeclarationSite) n;
                //    for (PFGNode oi : pts) {
                //        for (FieldLoadEdge load : loadEdges) {
                //            if (load.from.equals(ds)) {
                //                addEdge(load.to, new FieldAccess(oi, load.field));
                //            }
                //        }

                //        for (FieldStoreEdge store : storeEdges) {
                //            if (store.to.equals(ds)) {
                //                addEdge(new FieldAccess(oi, store.field), store.from);
                //            }
                //        }
                //    }
                //}
            }

            Set<PointsToEdge> res = new HashSet<>();
            for (Map.Entry<PFGNode, Set<AllocationSite>> entry : pointsTo.entrySet()) {
                PFGNode s = entry.getKey();
                if (s instanceof DeclarationSite) {
                    DeclarationSite ds = (DeclarationSite) s;
                    for (AllocationSite t : entry.getValue()) {
                        res.add(new GeneratedPointsToEdge(ds, t));
                    }
                }
            }

            return res;
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:146
   */
  

        private void fieldLoop(PFGNode n, Set<AllocationSite> pts) {
            if (!(n instanceof FieldAccess)) {
                DeclarationSite ds = (DeclarationSite) n;
                for (PFGNode oi : pts) {
                    //for (FieldLoadEdge load : loadEdges) {
                    //    if (load.from.equals(ds)) {
                    //        addEdge(load.to, new FieldAccess(oi, load.field));
                    //    }
                    //}
                    for (FieldLoadEdge load : loadMap.getOrDefault(ds, Collections.emptySet())) {
                        addEdge(load.to, new FieldAccess(oi, load.field));
                    }
                    for (FieldStoreEdge store : storeMap.getOrDefault(ds, Collections.emptySet())) {
                        addEdge(new FieldAccess(oi, store.field), store.from);
                    }

                    //for (FieldStoreEdge store : storeEdges) {
                    //    if (store.to.equals(ds)) {
                    //        addEdge(new FieldAccess(oi, store.field), store.from);
                    //    }
                    //}
                }
            }
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:171
   */
  

        private void println(String s) {
            if (pointerTypes.size() > 0) {
                pointerTypes.get(0).program().printStream.println(s);

            }
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:178
   */
  

        private void computeFieldSets() {
            for (FieldLoadEdge load : loadEdges) {
                DeclarationSite from = load.from;
                DeclarationSite to = load.to;
                loadMap.putIfAbsent(from, new HashSet<>());
                loadMap.get(from).add(load);
            }

            for (FieldStoreEdge store : storeEdges) {
                DeclarationSite from = store.from;
                DeclarationSite to = store.to;
                storeMap.putIfAbsent(to, new HashSet<>());
                storeMap.get(to).add(store);
            }
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:198
   */
  

        // In case a parameter does not have any inclusion edges pointing to it,
        // then treat it as an allocation site.
        // This can happen when not going back far enough to find the allocation site,
        // or when reaching main.
        private Set<PointsToEdge> parameterPointsToEdges(Set<InclusionEdge> inclusionEdges) {
            Set<ParameterDeclaration> fromParameters =
                inclusionEdges
                .stream()
                .filter(e -> e.from instanceof ParameterDeclaration && !((ParameterDeclaration) e.from).type().isPrimitive())
                .map(e -> (ParameterDeclaration) e.from)
                .collect(Collectors.toSet());
            
            Set<ParameterDeclaration> toParameters =
                inclusionEdges
                .stream()
                .filter(e -> e.to instanceof ParameterDeclaration && !((ParameterDeclaration) e.to).type().isPrimitive())
                .map(e -> (ParameterDeclaration) e.to)
                .collect(Collectors.toSet());

            HashSet<PointsToEdge> res = new HashSet<>();
            for (ParameterDeclaration from : fromParameters) {
                if (!toParameters.contains(from)) {
                    res.add(new GeneratedPointsToEdge(from, from));
                }
            }

            return res;
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:223
   */
  

        private void filter() {
            // find edge types
            Set<TypeDecl> candidates = new HashSet<>();
            inclusionEdges.forEach(ie -> {
                                        candidates.add(ie.from.type2()); 
                                        candidates.add(ie.to.type2());
                                    });

            Set<TypeDecl> relevantTypes = new HashSet<>();
            for (TypeDecl pointerType : pointerTypes) {
                Set<TypeDecl> candidateTypes = new HashSet<>(candidates);
                relevantTypes.add(pointerType);
                candidateTypes.remove(pointerType);
                candidateTypes.remove(pointerType.program().typeNull());

                boolean fixpointReached = false;
                while (!fixpointReached) {
                    fixpointReached = true;
                    Set<TypeDecl> toRemove = new HashSet<>();

                    for (TypeDecl candidate : candidateTypes) {
                        if (hasFieldContainingRelevantType(candidate, relevantTypes)
                        || isArrayContainingRelevantType(candidate, relevantTypes)
                        || isRawListOrMap(candidate)
                        || isListOrMapContainingRelevantType(candidate, relevantTypes)
                        || sharesTypeHierarchyWithAny(candidate, relevantTypes)) {
                            fixpointReached = false;
                            relevantTypes.add(candidate);
                            toRemove.add(candidate);
                        }
                    }
                    candidateTypes.removeAll(toRemove);
                }
            }

            // filter edges
            inclusionEdges = inclusionEdges.stream().filter(ie -> relevantTypes.contains(ie.from.type2()) || relevantTypes.contains(ie.to.type2()) || ie.from instanceof TempVertex && ie.to instanceof TempVertex).collect(Collectors.toSet());
            pointsToEdges = pointsToEdges.stream().filter(pte -> relevantTypes.contains(pte.from.type2())).collect(Collectors.toSet());
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:263
   */
  

        private boolean hasFieldContainingRelevantType(TypeDecl type, Set<TypeDecl> relevant) {
            return type.fieldTypes().stream().anyMatch(ft -> sharesTypeHierarchyWithAny(ft, relevant));
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:267
   */
  

        private boolean sharesTypeHierarchy(TypeDecl type1, TypeDecl type2) {
            return type1.subtype(type2) || type2.subtype(type1);
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:271
   */
  

        private boolean sharesTypeHierarchyWithAny(TypeDecl type, Collection<TypeDecl> types) {
            return types.stream().anyMatch(t -> sharesTypeHierarchy(type, t));
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:275
   */
  

        private boolean isArrayContainingRelevantType(TypeDecl type, Set<TypeDecl> relevant) {
            if (type.isArrayDecl()) {
                ArrayDecl ad = (ArrayDecl) type;
                TypeDecl elementType = ad.elementType();
                return !elementType.isPrimitive() && sharesTypeHierarchyWithAny(elementType, relevant);
            } else {
                return false;
            }
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:285
   */
  

        private boolean isListOrMapContainingRelevantType(TypeDecl type, Set<TypeDecl> relevant) {
            if ((type.isList() || type.isMap()) && type.isParameterizedType()) {
                ParTypeDecl par = (ParTypeDecl) type;
                for (TypeDecl inner : par.getParameterization().args) {
                    if (sharesTypeHierarchyWithAny(inner, relevant)) {
                        return true;
                    }
                }
            }
            return false;
        }
  /**
   * @aspect Solver
   * @declaredat /Users/johanarrhen/Git/ProgramAnalysis/pecka/src/main/jastadd/algorithm/Solver.jrag:297
   */
  

        private boolean isRawListOrMap(TypeDecl type) { 
            return type.isMap()  && type.isRawType()
                || type.isList() && type.isRawType();
        }

}
