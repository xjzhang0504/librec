package javatest;

public class TestTransation {
	public static void main(String[] args) {
//        System.out.println(0 + '0');
//        System.out.println('0' + 0);
//        System.out.println(0 + "0");
//        System.out.println("0" + 0);
		
//		int j=0;
//		for(int i=0;i<100;i++) {
//			
//		     j=j++;
//			System.out.println(j);
//		}
//		//System.out.println(j);
		
		
		int i=0;
		i=i++ + ++i;
		int j=0;
		j=++j + j++ + j++ + j++;
		int k=0;
		k=k++ + k++ + k++ + ++k;
		int h=0;
		h=++h + ++h;
		int p1=0,p2=0;
		int q1=0,q2=0;
		q1=++p1;
		q2=p2++;
		System.out.println(i);
		System.out.println(j);
		System.out.println(k);
		System.out.println(h);
		System.out.println(q1);
		System.out.println(q2);
	}
}
