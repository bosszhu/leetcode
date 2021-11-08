package 斐波那契;

public class Fib {
    public int fib(int n) {
    	if (n <= 1) return n;
    	int first = 0;
    	int second = 1;
    	for (int i = 0; i < n - 1; i++) {
			int sum = first + second;
			first = second;
			second = sum;
		}
    	return second;
    }
    
    public int fib1(int n) {
    	if (n <= 1) return n;
    	return fib1(n- 1) + fib1(n - 2);
    }
}
