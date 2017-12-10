package javatest;

public class Test {
	static {
		int x = 5;
		System.out.println("zhixing");
	}
	static int x, y;
	public static void main(String[] args) {
		System.out.println(x);
		System.out.println(y);
		x--;
		myMethod();
		System.out.println(x + y++ + x);
	}
	public static void myMethod() {
		y=x++ + ++x;
		System.out.println(x);
		System.out.println(y);
	}
}
