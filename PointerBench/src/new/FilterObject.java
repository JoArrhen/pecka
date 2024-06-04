package newTests;

import benchmark.internal.Benchmark;

class FilterObject {
    public static void main(String[] args) {
        Benchmark.alloc(1);
        A a = new A();
        Object b = a;

        Benchmark.test("b",
            "{allocId:1, mayAlias:[], notMayAlias:[], mustAlias:[], notMustAlias:[]}");
    }
}

class A {}
