package basic;

import benchmark.internal.Benchmark;

public class StaticFieldTypeAccess {

    public static void main(String[] args) {
        B b = A.b;
        Benchmark.test("b",
            "{allocId:1, mayAlias:[], notMayAlias:[], mustAlias:[], notMustAlias:[]}");
    }
}

class A {
    public static B b = newB();

    private static B newB() {
        Benchmark.alloc(1);
        B b = new B();
        return b;
    }
}

class B {}
