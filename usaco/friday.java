
/*
ID: alexthe3
LANG: JAVA
TASK: friday
 */

import java.io.*;
import java.util.*;

public class friday {
	public static void main(String[] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("friday.in"));
		// input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));

		int n = Integer.parseInt(f.readLine());
		int count = 13;
		int year = 1900;
		int[] month = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int[] c = new int[7];
		c[0] = 1;
		

		while (year < 1900 + n) {
			if(year % 4 == 0) {
				if(year % 100 != 0 || year % 400 == 0) {
					month[1] = 29;
				}
			}
			
			for (int i = 0; i < month.length; i++) {
				
				if(year == 1900 + n - 1 && i == 11) break;
				
				count += month[i];
				c[(count + 1) % 7]++;
			}
			
			month[1] = 28;
			year++;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < c.length; i++) {
			sb.append(c[i] + " "); // output result
		}
		
		out.println(sb.toString().trim());
		
		out.flush();
		out.close(); // close the output file
	}
}
