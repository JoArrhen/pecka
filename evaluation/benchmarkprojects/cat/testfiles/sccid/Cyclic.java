// PackageName: org.extendj.sccid.Cyclic
// MethodName: foo

package org.extendj.sccid;

public class Cyclic {
  void foo() { bar(); }

  void bar() { baz(); }

  void baz() { zzz(); }
  void zzz() { foo(); }
}