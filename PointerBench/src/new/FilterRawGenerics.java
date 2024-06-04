package newTests;

import java.util.List;
import java.util.LinkedList;
import benchmark.internal.Benchmark;

class FilterRawGenerics {
    public static void main(String[] args) {
        Benchmark.alloc(1);
        A a = new A();

        List list = new LinkedList();
        list.add(a);

        A b = (A) list.get(0);

        Benchmark.test("b",
            "{allocId:1, mayAlias:[], notMayAlias:[], mustAlias:[], notMustAlias:[]}");
    }
}

class A {}
