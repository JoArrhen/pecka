package newTests;

import benchmark.internal.Benchmark;
import java.util.*;

class FilterListCast {
    public static void main(String[] args) {
        List<A> list = new LinkedList<>();

        Benchmark.alloc(1);
        A a = new B();

        list.add(a);
        B b = (B) list.get(0);
        Benchmark.test("b",
            "{allocId:1, mayAlias:[], notMayAlias:[], mustAlias:[], notMustAlias:[]}");
    }
}

class A {}
class B extends A {}
