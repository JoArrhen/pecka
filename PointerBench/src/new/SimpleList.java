package newTests;

import java.util.List;
import java.util.LinkedList;
import benchmark.internal.Benchmark;

class SimpleList {
    public static void main(String[] args) {
        Benchmark.alloc(1);
        A a = new A();

        List<A> list = new LinkedList<>();
        list.add(a);

        A b = list.get(0);
        Benchmark.test("b",
            "{allocId:1, mayAlias:[], notMayAlias:[], mustAlias:[], notMustAlias:[]}");
    }
}

class A {}
