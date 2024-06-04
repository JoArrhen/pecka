package newTests;

import benchmark.internal.Benchmark;

// Det här funkar inte utan par expr heller
class MethodAccessAfterParExpr {
    public static void main(String[] args) {
        A a = new A();
        B b = (a).getThis().b;

        Benchmark.test("b",
            "{allocId:1, mayAlias:[], notMayAlias:[], mustAlias:[], notMustAlias:[]}");
    }
}

class A {
    B b = createB();

    A getThis() {
        return this;
    }

    B createB() {
        Benchmark.alloc(1);
        return new B();
    }
}

class B {}
