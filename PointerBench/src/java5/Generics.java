package extra;

import benchmark.internal.Benchmark;
import benchmark.objects.A;

/*
 * @testcase Generics
 * 
 * @version 1.0
 * 
 * @author Johan Arrhen
 * 
 * @description Generics
 */
 
@java5
public class Generics {

  public static void main(String[] args) {
    Benchmark.alloc(1);
    A a = new A();
    A b = new A();


    Container<A> cont = new Container<>();
    cont.put(a);

    A t = cont.get();

    Benchmark.test("t", "{allocId:1}");
  }
}

class Container<E> {
  E elem;

  public void put(E elem) {
    this.elem = elem;
  }

  public E get() {
    return elem;
  }

}