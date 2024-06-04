package newTests;

import benchmark.internal.Benchmark;

class ChainedFieldAccesses {
    public static void main(String[] args) {
        A a = new A();
        D d = a.f1.f2.f3;

        Benchmark.test("d",
            "{allocId:1, mayAlias:[], notMayAlias:[], mustAlias:[], notMustAlias:[]}");
    }
}

class A {
    B f1;

    A() {
        this.f1 = new B();
    }
}
class B {
    C f2;

    B() {
        this.f2 = new C();
    }
}

class C {
    D f3;

    C() {
        Benchmark.alloc(1);
        D d = new D();
        this.f3 = d;
    }
}

class D {}
