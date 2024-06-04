package newTests;

import benchmark.internal.Benchmark;

class AssignExpression {
    public static void main(String[] args) {
        A a;
        A b = new A();

        Benchmark.alloc(1);
        B c = new B();

        (a = b).f = c;

        B d = a.f;

        Benchmark.test("d",
            "{allocId:1, mayAlias:[], notMayAlias:[], mustAlias:[], notMustAlias:[]}");
    }
}

class A {
    B f;
}

class B {}
