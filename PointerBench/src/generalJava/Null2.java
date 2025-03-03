package generalJava;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import benchmark.objects.B;

/*
 * @testcase Null2
 * @version 1.0
 * @author Johannes Sp�th, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer Institute SIT)
 * 
 * @description Implicit alias to null
 * 
 */
public class Null2 {

	public static void main(String[] args) {

		// No allocation site
		A a = new A();
		A b = a;
		B x = b.h; // a.h is null
		Benchmark
				.test("x",
						"{allocId: 0, mayAlias:[], notMayAlias:[b,a], mustAlias:[b,a], notMustAlias:[i]}");
	}
}
