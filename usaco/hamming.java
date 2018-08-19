/*
ID: alethe3
LANG: JAVA
TASK: hamming
 */

import java.util.*;
import java.io.*;

public class hamming {
	static int index;
	
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("hamming.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] codes = new int[Integer.parseInt(st.nextToken())];
		int bits = Integer.parseInt(st.nextToken());
		int ham = Integer.parseInt(st.nextToken());
		int max = 1 << (bits + 1);
		index = 1;
		codes[0] = 0;
		
		for (int i = 1; i < max && index < codes.length; i++) {
			if (checkHam(codes, ham, bits, i))
				codes[index++] = i;
		}
		
		int wordLineCounter = 1;
		for (int i = 0; i < codes.length; i++) {
			if (wordLineCounter == 10) {
				pw.print(codes[i]);
				pw.println();
				wordLineCounter = 1;
			} else if (i == codes.length - 1) {
				pw.println(codes[i]);
			}
			else {
				pw.print(codes[i] + " ");
				wordLineCounter++;
			}
		}
		
		
		pw.flush();
		pw.close();
		br.close();
	}

	private static boolean checkHam(int[] codes, int ham, int bits, int num) {
		for (int i = 0; i < index; i++) {
			if (notEnoughHam(ham, num, bits, codes[i])) 
				return false;
		}
		
		return true;
	}

	private static boolean notEnoughHam(int ham, int num, int bits, int comp) {
		int hamNum = num ^ comp;
		int hamCount = 0;
		for (int i = 0; i <= bits; i++) {
			if ((hamNum & (1 << i)) != 0)
				hamCount++;
		}
		if (hamCount < ham)
			return true;
		
		return false;
	}

}
