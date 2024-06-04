package newTests;

import benchmark.internal.Benchmark;
import java.util.*;

class FilterGenericInterface {
    public static void main(String[] args) {
        Benchmark.alloc(1);
        I<B> a = new A<>();

        Benchmark.test("a",
            "{allocId:1, mayAlias:[], notMayAlias:[], mustAlias:[], notMustAlias:[]}");
    }
}

interface I<T> {}
class A<T> implements I<T> {}
class B {}
