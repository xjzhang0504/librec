package javatest;

import org.apache.commons.lang.ObjectUtils.Null;
import org.omg.CORBA.SystemException;

public class TestInt {
	static int x = 0;
	static String str;

	int y = 10;
	static float z;
	static boolean h;
	static char i;
	static byte j;

	public static void main(String[] args) {
		System.out.println(str);
		System.out.println(z);
		System.out.println(h);
		System.out.println(i);
		System.out.println(j);
		
//		boolean
//		byte
//		char
//		short
//		int 
//		long
//		float
//		double


		
		String aString = null;
		String bString = "";
		
		boolean c = bString.equals(aString);
		TestInt testInt = new TestInt();
		int a = testInt.y;
		System.out.println(a);
		testInt.y++;
		int b = TestInt.x;
		System.out.println(b);
		TestInt.x++;

		TestInt testInt1 = new TestInt();
		System.out.println(testInt1.y);
		System.out.println(TestInt.x);

	}
}
