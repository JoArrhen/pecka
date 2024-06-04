package newTests;

import java.util.Map;
import java.util.HashMap;
import benchmark.internal.Benchmark;

class SimpleMap {
    public static void main(String[] args) {
        Benchmark.alloc(1);
        A a = new A();

        Map<String, A> map = new HashMap<>();
        map.put("a", a);

        A b = map.get("a");
        Benchmark.test("b",
            "{allocId:1, mayAlias:[], notMayAlias:[], mustAlias:[], notMustAlias:[]}");
    }
}

class A {}
