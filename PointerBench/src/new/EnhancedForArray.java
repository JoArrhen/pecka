package newTests;

import benchmark.internal.Benchmark;

class EnhancedForArray {
    public static void main(String[] args) {
        Benchmark.alloc(1);
        A a = new A();

        A[] arr = new A[5];
        arr[0] = a;

        A b;
        for (A x : arr) {
            b = x;
        }

        Benchmark.test("b",
            "{allocId:1, mayAlias:[], notMayAlias:[], mustAlias:[], notMustAlias:[]}");
    }
}

class A {}
