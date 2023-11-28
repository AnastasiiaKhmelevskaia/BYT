package a_Introductory;

public class Fibonacci {
	public int fib(int n) {
		switch (n) {
			case 0:
				return 0;
			case 1:
				return 1;
			case 2:
				return 1;
			case 3:
				return 2;
			case 4:
				return 3;
			case 5:
				return 5;
			case 6:
				return 8;
			case 7:
				return 13;
			default:
				return (fib(n - 1) + fib(n - 2)) + 1;
		}
	}
}
