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
/** @apilevel internal 
 * @ast class
 * @declaredat ASTState:34
 */
public class ASTState extends java.lang.Object {
  /**
   * This class stores an attribute value tagged with an iteration ID for
   * a circular evaluation.
   * 
   * @apilevel internal
   * @declaredat ASTState:41
   */
  
  /**
   * This class stores an attribute value tagged with an iteration ID for
   * a circular evaluation.
   *
   * @apilevel internal
   */
  protected static class CircularValue {
    Object value;
    Cycle cycle;
  }
  /**
   * Instances of this class are used to uniquely identify circular evaluation iterations.
   * These iteration ID objects are created for each new fixed-point iteration in
   * a circular evaluation.
   * 
   * @apilevel internal
   * @declaredat ASTState:53
   */
  

  /**
   * Instances of this class are used to uniquely identify circular evaluation iterations.
   * These iteration ID objects are created for each new fixed-point iteration in
   * a circular evaluation.
   *
   * @apilevel internal
   */
  protected static class Cycle {
  }
  /**
   * The iteration ID used outside of circular evaluation.
   * 
   * <p>This is the iteration ID when no circular evaluation is ongoing.
   * @declaredat ASTState:61
   */
  

  /**
   * The iteration ID used outside of circular evaluation.
   *
   * <p>This is the iteration ID when no circular evaluation is ongoing.
   */
  public static final Cycle NON_CYCLE = new Cycle();
  /**
   * Tracks the state of the current circular evaluation. This class defines a
   * stack structure where the next element on the stack is pointed to by the
   * {@code next} field.
   * 
   * @apilevel internal
   * @declaredat ASTState:70
   */
  

  /**
   * Tracks the state of the current circular evaluation. This class defines a
   * stack structure where the next element on the stack is pointed to by the
   * {@code next} field.
   *
   * @apilevel internal
   */
  protected static class CircleState {
    final CircleState next;
    boolean change = false;

    /** Evaluation depth of lazy attributes. */
    int lazyAttribute = 0;

    boolean lastCycle = false;

    /** Cycle ID of the latest cycle in this circular evaluation. */
    Cycle cycle = NON_CYCLE;


    protected CircleState(CircleState next) {
      this.next = next;
    }
  }
  /** Sentinel circle state representing non-circular evaluation. 
   * @declaredat ASTState:90
   */
  


  /** Sentinel circle state representing non-circular evaluation. */
  private static final CircleState CIRCLE_BOTTOM = new CircleState(null);
  /**
   * Current circular state.
   * @apilevel internal
   * @declaredat ASTState:96
   */
  

  /**
   * Current circular state.
   * @apilevel internal
   */
  private CircleState circle = CIRCLE_BOTTOM;
  /** @apilevel internal 
   * @declaredat ASTState:104
   */
  






  /** @apilevel internal */
  protected boolean inCircle() {
    return circle != CIRCLE_BOTTOM;
  }
  /** @apilevel internal 
   * @declaredat ASTState:109
   */
  

  /** @apilevel internal */
  protected boolean calledByLazyAttribute() {
    return circle.lazyAttribute > 0;
  }
  /** @apilevel internal 
   * @declaredat ASTState:114
   */
  

  /** @apilevel internal */
  protected void enterLazyAttribute() {
    circle.lazyAttribute += 1;
  }
  /** @apilevel internal 
   * @declaredat ASTState:119
   */
  

  /** @apilevel internal */
  protected void leaveLazyAttribute() {
    circle.lazyAttribute -= 1;
  }
  /** @apilevel internal 
   * @declaredat ASTState:124
   */
  

  /** @apilevel internal */
  protected void enterCircle() {
    CircleState next = new CircleState(circle);
    circle = next;
  }
  /** @apilevel internal 
   * @declaredat ASTState:131
   */
  


  /** @apilevel internal */
  protected void leaveCircle() {
    circle = circle.next;
  }
  /** @apilevel internal 
   * @declaredat ASTState:136
   */
  

  /** @apilevel internal */
  protected Cycle nextCycle() {
    Cycle cycle = new Cycle();
    circle.cycle = cycle;
    return cycle;
  }
  /** @apilevel internal 
   * @declaredat ASTState:143
   */
  

  /** @apilevel internal */
  protected Cycle cycle() {
    return circle.cycle;
  }
  /** @apilevel internal 
   * @declaredat ASTState:148
   */
  

  /** @apilevel internal */
  protected CircleState currentCircle() {
    return circle;
  }
  /** @apilevel internal 
   * @declaredat ASTState:154
   */
  


  /** @apilevel internal */
  protected void setChangeInCycle() {
    circle.change = true;
  }
  /** @apilevel internal 
   * @declaredat ASTState:159
   */
  

  /** @apilevel internal */
  protected boolean testAndClearChangeInCycle() {
    boolean change = circle.change;
    circle.change = false;
    return change;
  }
  /** @apilevel internal 
   * @declaredat ASTState:166
   */
  

  /** @apilevel internal */
  protected boolean changeInCycle() {
    return circle.change;
  }
  /** @apilevel internal 
   * @declaredat ASTState:171
   */
  

  /** @apilevel internal */
  protected boolean lastCycle() {
    return circle.lastCycle;
  }
  /**
   * This is part of the cacheCycle optimization:
   * a circular attribute is evaluated one extra time after the
   * last iteration of Case1, in order to mark all dependencies
   * used in the last iteration as memoized.
   * @apilevel internal
   * @declaredat ASTState:182
   */
  

  /**
   * This is part of the cacheCycle optimization:
   * a circular attribute is evaluated one extra time after the
   * last iteration of Case1, in order to mark all dependencies
   * used in the last iteration as memoized.
   * @apilevel internal
   */
  protected void startLastCycle() {
    nextCycle();
    circle.lastCycle = true;
  }
  /**
   * @declaredat ASTState:187
   */
  

  protected ASTState() {
  }
  /**
   * @declaredat ASTState:190
   */
  

  public interface ReceiverFactory {
    Trace.Receiver build();
  }
  /**
   * @declaredat ASTState:194
   */
  

  public static ReceiverFactory receiverFactory = new ReceiverFactory() {
    public Trace.Receiver build() {
      return new Trace.Receiver() {
        public void accept(ASTState.Trace.Event event, ASTNode node, String attribute,
            Object params, Object value) {
        }
      };
    }
  };
  /**
   * @declaredat ASTState:204
   */
  

  private Trace trace = null;
  /** @return the tracer instance used for tracing attribute evaluation in this AST. 
   * @declaredat ASTState:207
   */
  

  /** @return the tracer instance used for tracing attribute evaluation in this AST. */
  public Trace trace() {
    if (trace == null) {
      trace = new Trace(receiverFactory.build());
    }
    return trace;
  }
  /**
   * @declaredat ASTState:214
   */
  

  public static class Trace {
    /**
     * Trace events corresponding to attribute evaluation events.
     *
     * <p>These events can be filtered statically using the flag --tracing to
     * JastAdd2. For example, the flag {@code --tracing=compute,cache} will only trace
     * compute events and cache events. The flag --tracing will enable all events.
     *
     * <p>To access the trace events you will need to register an event receiver.
     * This can be done using the method setReceiver(ASTState.Trace.Receiver).
     */
    public enum Event {
      // Flag: --tracing=compute
      COMPUTE_BEGIN,
      COMPUTE_END,
  
      // Flag: --tracing=cache
      CACHE_WRITE,
      CACHE_READ,
      CACHE_ABORT,
  
      // Flag: --tracing=rewrite
      REWRITE_CASE1_START,
      REWRITE_CASE1_CHANGE,
      REWRITE_CASE1_RETURN,
      REWRITE_CASE2_RETURN,
      REWRITE_CASE3_RETURN,
  
      // Flag: --tracing=circular
      CIRCULAR_NTA_CASE1_START,
      CIRCULAR_NTA_CASE1_CHANGE,
      CIRCULAR_NTA_CASE1_RETURN,
      CIRCULAR_NTA_CASE2_START,
      CIRCULAR_NTA_CASE2_CHANGE,
      CIRCULAR_NTA_CASE2_RETURN,
      CIRCULAR_NTA_CASE3_RETURN,
      CIRCULAR_CASE1_START,
      CIRCULAR_CASE1_CHANGE,
      CIRCULAR_CASE1_RETURN,
      CIRCULAR_CASE2_START,
      CIRCULAR_CASE2_CHANGE,
      CIRCULAR_CASE2_RETURN,
      CIRCULAR_CASE3_RETURN,
  
      // Flag: --tracing=copy
      COPY_NODE,
  
      // Flag: --tracing=flush
      FLUSH_ATTR,
      FLUSH_REWRITE,
      FLUSH_REWRITE_INIT,
      INC_FLUSH_ATTR;
    }
  
    /**
     * Functional interface for a trace event receiver.
     * This can be implemented by applications that want to trace attribute evaluation.
     */
    public interface Receiver {
      void accept(ASTState.Trace.Event event, ASTNode node, String attribute,
          Object params, Object value);
    }
  
    public Trace(Receiver receiver) {
      this.receiver = receiver;
    }
  
    public Trace() {
    }
  
    // The default event receiver does nothing.
    private Receiver receiver = new Receiver() {
      public void accept(ASTState.Trace.Event event, ASTNode node, String attribute,
          Object params, Object value) {
      }
    };
  
    /**
     * Registers an input filter to use during tracing.
     * @param filter The input filter to register.
     */
    public void setReceiver(ASTState.Trace.Receiver receiver) {
      this.receiver = receiver;
    }
  
    public Receiver getReceiver() {
      return receiver;
    }
  
    /**
     * Trace that an attribute instance started its computation.
     * @param value The value of the attribute instance.
     */
    public void computeBegin(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(Event.COMPUTE_BEGIN, node, attr, params, value);
    }
  
    /**
     * Trace that an attribute instance ended its computation.
     * @param value The value of the attribute instance.
     */
    public void computeEnd(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.COMPUTE_END, node, attr, params, value);
    }
  
    /**
     * Trace that the cache of an attribute instances was read.
     * @param value The value of the attribute instance.
     */
    public void cacheRead(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.CACHE_READ, node, attr, params, value);
    }
  
    /**
     * Trace that an attribute instance was cached.
     * @param value The value of the attribute instance.
     */
    public void cacheWrite(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.CACHE_WRITE, node, attr, params, value);
    }
  
    /**
     * Trace that the caching of an attribute instance was aborted.
     * @param value The value of the attribute instance.
     */
    public void cacheAbort(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.CACHE_ABORT, node, attr, params, value);
    }
  
    /**
     * Trace that a rewrite evaluation entered case 1.
     * @param value The value of the rewrite.
     */
    public void enterRewriteCase1(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.REWRITE_CASE1_START, node, attr, params, value);
    }
  
    /**
     * Trace that a rewrite in evaluation case 1 changed value.
     * @param value The value of the rewrite before and after.
     */
    public void rewriteChange(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.REWRITE_CASE1_CHANGE, node, attr, params, value);
    }
  
    /**
     * Trace that a rewrite returned from evaluation case 1.
     * @param value The value of the rewrite.
     */
    public void exitRewriteCase1(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.REWRITE_CASE1_RETURN, node, attr, params, value);
    }
  
    /**
     * Trace that a rewrite returned from evaluation case 2.
     * @param value The value of the rewrite.
     */
    public void exitRewriteCase2(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.REWRITE_CASE2_RETURN, node, attr, params, value);
    }
  
    /**
     * Trace that a rewrite returned from evaluation case 3.
     * @param value The value of the rewrite.
     */
    public void exitRewriteCase3(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.REWRITE_CASE3_RETURN, node, attr, params, value);
    }
  
    /**
     * Trace that a circular attribute instance entered evaluation case 1.
     * @param value The value of the circular attribute instance.
     */
    public void enterCircularCase1(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.CIRCULAR_CASE1_START, node, attr, params, value);
    }
  
    /**
     * Trace that a circular attribute instance in evaluation case 1 changed value.
     * @param value The value of the circular attribute instance, before and after.
     */
    public void circularCase1Change(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.CIRCULAR_CASE1_CHANGE, node, attr, params, value);
    }
  
    /**
     * Trace that a circular attribute instance returned from evaluation case 1.
     * @param value The value of the circular attribute instance.
     */
    public void exitCircularCase1(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.CIRCULAR_CASE1_RETURN, node, attr, params, value);
    }
  
    /**
     * Trace that a circular attribute instance entered evaluation case 2.
     * @param value The value of the circular attribute instance.
     */
    public void enterCircularCase2(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.CIRCULAR_CASE2_START, node, attr, params, value);
    }
  
    /**
     * Trace that a circular attribute instance in evaluation case 2 changed value.
     * @param value The value of the circular attribute instance, before and after.
     */
    public void circularCase2Change(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.CIRCULAR_CASE2_CHANGE, node, attr, params, value);
    }
  
    /**
     * Trace that a circular attribute instance returned from evaluation case 2.
     * @param value The value of the circular attribute instance.
     */
    public void exitCircularCase2(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.CIRCULAR_CASE2_RETURN, node, attr, params, value);
    }
  
    /**
     * Trace that a circular attribute instance returned from evaluation case 2.
     * @param value The value of the circular attribute instance.
     */
    public void exitCircularCase3(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.CIRCULAR_CASE3_RETURN, node, attr, params, value);
    }
  
    /**
     * Trace that a circular NTA entered evaluation case 1.
     * @param value The value of the circular NTA.
     */
    public void enterCircularNTACase1(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.CIRCULAR_NTA_CASE1_START, node, attr, params, value);
    }
  
    /**
     * Trace that a circular NTA in evaluation case 1 changed value.
     * @param value The value of the circular NTA, before and after.
     */
    public void circularNTACase1Change(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.CIRCULAR_NTA_CASE1_CHANGE, node, attr, params, value);
    }
  
    /**
     * Trace that a circular NTA returned from evaluation case 1.
     * @param value The value of the circular NTA.
     */
    public void exitCircularNTACase1(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.CIRCULAR_NTA_CASE1_RETURN, node, attr, params, value);
    }
  
    /**
     * Trace that a circular NTA entered evaluation case 2.
     * @param value The value of the circular NTA.
     */
    public void enterCircularNTACase2(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.CIRCULAR_NTA_CASE2_START, node, attr, params, value);
    }
  
    /**
     * Trace that a circular NTA in evaluation case 2 changed value.
     * @param value The value of the circular NTA, before and after.
     */
    public void circularNTACase2Change(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.CIRCULAR_NTA_CASE2_CHANGE, node, attr, params, value);
    }
  
    /**
     * Trace that a circular NTA returned from evaluation case 2.
     * @param value The value of the circular NTA.
     */
    public void exitCircularNTACase2(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.CIRCULAR_NTA_CASE2_RETURN, node, attr, params, value);
    }
  
    /**
     * Trace that a circular NTA returned from evaluation case 2.
     * @param value The value of the circular NTA.
     */
    public void exitCircularNTACase3(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.CIRCULAR_NTA_CASE3_RETURN, node, attr, params, value);
    }
  
    /**
     * Trace that an AST node was copied.
     * @param node The copied node.
     * @param value The value of the node.
     */
    public void copyNode(ASTNode node, Object value) {
      receiver.accept(ASTState.Trace.Event.COPY_NODE, node, "ASTNode.copy", "", value);
    }
  
    /**
     * Trace that an attribute was flushed.
     * @param value The value of the attribute.
     */
    public void flushAttr(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.FLUSH_ATTR, node, attr, params, value);
    }
  
    /**
     * Trace that an attribute was flushed by incremental evaluation.
     */
    public void flushIncAttr(ASTNode node, String attr, Object params, Object value) {
      receiver.accept(ASTState.Trace.Event.INC_FLUSH_ATTR, node, attr, params, value);
    }
  }
  /** @apilevel internal 
   * @declaredat ASTNode:287
   */
  public void reset() {
    // Reset circular evaluation state.
    circle = CIRCLE_BOTTOM;
  }

}
