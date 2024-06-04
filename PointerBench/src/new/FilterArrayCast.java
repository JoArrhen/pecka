package newTests;

import benchmark.internal.Benchmark;

class FilterArrayCast {
    public static void main(String[] args) {
        Object[] arr = new Object[2];

        Benchmark.alloc(1);
        A a = new A();

        arr[0] = a;
        arr[1] = new B();

        A b = (A) arr[0];
        Benchmark.test("b",
            "{allocId:1, mayAlias:[], notMayAlias:[], mustAlias:[], notMustAlias:[]}");
    }
}

class A {}
class B {}
