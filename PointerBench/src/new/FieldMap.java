package newTests;

import java.util.Map;
import java.util.HashMap;
import benchmark.internal.Benchmark;

class FieldMap {
    public static void main(String[] args) {
        Benchmark.alloc(1);
        A a = new A();

        B mapOwner = new B();
        mapOwner.map.put("a", a);

        A b = mapOwner.map.get("a");
        Benchmark.test("b",
            "{allocId:1, mayAlias:[], notMayAlias:[], mustAlias:[], notMustAlias:[]}");
    }
}

class A {}
class B {
    Map<String, A> map = new HashMap<>();
}
