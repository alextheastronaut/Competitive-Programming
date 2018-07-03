
/*
ID: alethe3
LANG: JAVA
TASK: frac1
 */

import java.util.*;
import java.io.*;

public class frac1 {
	public static class frac implements Comparable<frac> {
		int num, den;
		double val;

		public frac(int numerator, int denominator) {
			num = numerator;
			den = denominator;
			val = 1.0 * num / den;

		}

		@Override
		public String toString() {
			return num + "/" + den;
		}

		@Override
		public int compareTo(frac compFrac) {
			return val > compFrac.val ? 1 : -1;
		}
	}
	
	private static boolean[] genSieve(int max) {
		boolean[] isPrime = new boolean[max + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i <= Math.sqrt(max); i++) {
			if (isPrime[i]) {
				for (int j = 2; j * i <= max; j++) {
					isPrime[i * j] = false;
				}
			}
		}
		
		return isPrime;
		
	}
	
	private static ArrayList<Integer> listPrimes(boolean[] isPrime){
		ArrayList<Integer> primes = new ArrayList<Integer>(10);
		for (int i = 0; i < isPrime.length; i++) {
			if (isPrime[i])
				primes.add(i);
		}
		
		return primes;
	}
	
	private static boolean isCoPrime(int num, int den, ArrayList<Integer> primes) {
		for (int i = 0; i < primes.size(); i++) {
			if (num % primes.get(i) == 0 && den % primes.get(i) == 0)
				return false;
		}
		
		return true;
		
	} 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("frac1.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));

		int max = Integer.parseInt(br.readLine());
		ArrayList<frac> list = new ArrayList<frac>();

		ArrayList<Integer> primes = listPrimes(genSieve(max));
		
		for (int i = 1; i <= max; i++) {
			list.add(new frac(1, i));
			if (i != 2)
				list.add(new frac(i - 1, i));
			for (int j = 2; j <= i / 2; j++) {
				if (i % j != 0) {
					if (isCoPrime(j, i, primes)) {
						list.add(new frac(j, i));
						list.add(new frac(i - j, i));
					}
				}
			}
		}

		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).toString());
			pw.println(list.get(i).toString());
		}

		pw.flush();
		pw.close();
		br.close();
	}

}
