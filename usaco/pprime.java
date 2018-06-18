
/*
ID: alethe3
LANG: JAVA
TASK: pprime
*/

import java.util.*;
import java.io.*;

public class pprime {
	static ArrayList<Integer> list = new ArrayList<>();
	static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("pprime.in"));
		pw = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));

		String[] bounds = br.readLine().split(" ");
		String lowString = bounds[0];
		String highString = bounds[1];
		int lowLength = lowString.length();
		int lowB = Integer.parseInt(lowString);
		int highB = Integer.parseInt(highString);

		if (lowString.length() == 1 && highString.length() == 1) {
			for (int i = lowB; i <= highB; i++) {
				list.add(i);
			}
		} else {
			if (lowString.length() == 1) {
				for (int i = 1; i < 10; i += 2) {
					list.add(i);
				}

				lowLength = 2;
			}

			for (int i = lowLength; i <= highString.length(); i++) {
				genPal(new char[i], 0);
			}
		}
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) >= lowB && list.get(i) <= highB && list.get(i) % 2 != 0)
				checkPrime(list.get(i));
		}
		
		pw.flush();
		pw.close();
		br.close();
	}

	private static void checkPrime(Integer num) {
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0)
				return;
		}
		
		System.out.println(num);
		pw.println(num);
	}

	private static void genPal(char[] pal, int index) {
		if (index == pal.length / 2) {
			for (int i = index; i > 0; i--) {
				pal[pal.length - i] = pal[i - 1];
			}
			if (pal.length % 2 == 0) {
				list.add(Integer.parseInt(new String(pal)));
				}
			else {
				for (int i = 0; i < 10; i++) {
					pal[index] = (char) (i + 48);
					list.add(Integer.parseInt(new String(pal)));
				}
			}
			
			return;
		}
		
		int position = index == 0 ? 1 : 0;
			
		for (int i = position; i < 10; i++) {
			pal[index] = (char)(i + 48);
			genPal(pal, index + 1);
		}
	}
}
