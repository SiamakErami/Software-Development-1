import java.util.*;

public class recursionAndIteration {

	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);

	        
	        System.out.print("Enter an element: ");
	        int n = scanner.nextInt();

	        System.out.println("Fibonacci iteration:");
	        long startTime = System.nanoTime();
	        System.out.printf("Fibonacci sequence(element at index %d) = %d \n", n, fiboIt(n));
	        System.out.printf("Time: %d ms\n", System.nanoTime() - startTime);

	        System.out.println("Fibonacci recursion:");
	        startTime = System.nanoTime();
	        System.out.printf("Fibonacci sequence(element at index %d) = %d \n", n, fiboRec(n));
	        System.out.printf("Time: %d ms\n", System.nanoTime() - startTime);
	    }

	    static int fiboIt(int n) {
	        int a = 0, b = 1, c = 1;
	        for (int i = 0; i < n; i++) {
	            a = b;
	            b = c;
	            c = a + b;
	        }
	        return a;
	    }

	    
	    static int fiboRec(int  n) {
	        if ((n == 1) || (n == 0)) {
	            return n;
	        }
	        return fiboIt(n - 1) + fiboRec(n - 2);
	    }
}




