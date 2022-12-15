package telran.test;

import java.lang.reflect.Method;

public class TestAppl {

	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			System.out.println("Too few arguments; Usage - first argument is method name, second is method argument.");
		} else {
			Tests tests = new Tests();
			Method method = tests.getClass().getDeclaredMethod(args[0], String.class);
			method.setAccessible(true);
			method.invoke(tests, args[1]);
		}

	}

}
