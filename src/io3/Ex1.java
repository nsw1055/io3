package io3;

public class Ex1 {

	private static int doA(int num1, int num2) {
		try {
			return num1 / num2;
		} catch (Exception e) {
			return 0;
		}
	}

	public static void main(String[] args) {

		for (int i = -10; i < 10; i++) {
			
			System.out.println(Ex1.doA(10, i));
		}
	}

}
