
/*
ID: alethe3
LANG: JAVA
TASK: sprime
 */

import java.util.*;
import java.io.*;

public class sprime {
	public static ArrayList<Integer> primes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("sprime.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));

		int length = Integer.parseInt(br.readLine());
		int num = (int) (Math.sqrt(Math.pow(10, length)));
		boolean[] arr = new boolean[num + 1];
		Arrays.fill(arr, true);
		arr[1] = false;
		primes = new ArrayList<Integer>(num);

		for (int i = 2; i <= num; i++) {
			if (arr[i]) {
				primes.add(i);
				for (int j = 2; i * j <= num; j++) {
					arr[i * j] = false;
				}
			}
		}

		for (int i = (int) (Math.pow(10, length - 1)) + 1; i < Math.pow(10, length); ) {
			int pow = isPrimeRib(length - 1, i);
			if (pow == -1) {
				pw.println(i);
				System.out.println(i);
				i += 2;
			}
			else {
				i += Math.pow(10, pow);
			}
		}

		pw.flush();
		pw.close();
		br.close();
	}

	private static boolean isPrime(int n) {
		if (n == 1) {
			return false;
		}
		
		for (int prime : primes) {
			if (prime >= n) 
				return true;
			if (n % prime == 0)
				return false;
		}

		return true;
	}

	private static int isPrimeRib(int pow, int i) {
		for (int j = pow; j >= 0; j--) {
			if (!isPrime(i / (int)(Math.pow(10, j))))
				return j;
		}

		return -1;
	}

}
