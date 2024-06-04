package newTests;

import java.util.List;
import java.util.LinkedList;
import benchmark.internal.Benchmark;

class FieldList {
    public static void main(String[] args) {
        Benchmark.alloc(1);
        A a = new A();

        B listOwner = new B();
        listOwner.list.add(a);

        A b = listOwner.list.get(0);
        Benchmark.test("b",
            "{allocId:1, mayAlias:[], notMayAlias:[], mustAlias:[], notMustAlias:[]}");
    }
}

class A {}
class B {
    List<A> list = new LinkedList<>();
}
