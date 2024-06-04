package newTests;

import java.util.List;
import java.util.LinkedList;
import benchmark.internal.Benchmark;

class EnhancedFor {
    public static void main(String[] args) {
        Benchmark.alloc(1);
        A a = new A();

        List<A> list = new LinkedList<>();
        list.add(a);

        A b;
        for (A x : list) {
            b = x;
        }

        Benchmark.test("b",
            "{allocId:1, mayAlias:[], notMayAlias:[], mustAlias:[], notMustAlias:[]}");
    }
}

class A {}
