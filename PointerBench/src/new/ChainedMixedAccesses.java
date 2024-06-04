package newTests;

import benchmark.internal.Benchmark;

class ChainedMixedAccesses {
    public static void main(String[] args) {
        A a = new A();
        D d = a.f.arr[0].d();

        Benchmark.test("d",
            "{allocId:1, mayAlias:[], notMayAlias:[], mustAlias:[], notMustAlias:[]}");
    }
}

class A {
    B f;

    A() {
        this.f = new B();
    }
}
class B {
    C[] arr;

    B() {
        this.arr = new C[1];
        this.arr[0] = new C();
    }
}

class C {
    D d() {
        Benchmark.alloc(1);
        D d = new D();
        return d;
    }
}

class D {}
