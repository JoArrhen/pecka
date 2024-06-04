package newTests;

import benchmark.internal.Benchmark;

class FilterCast {
    public static void main(String[] args) {
        A a = new A();

        A b = (A) a.newA();
        Benchmark.test("b",
            "{allocId:1, mayAlias:[], notMayAlias:[], mustAlias:[], notMustAlias:[]}");
    }
}

class A {
    Object newA() { 
        Benchmark.alloc(1);
        Object a = new A();
        return a; 
    }
}
