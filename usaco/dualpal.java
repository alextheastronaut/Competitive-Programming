
/*
ID: alexthe3
LANG: JAVA
TASK: dualpal
*/
import java.io.*;
import java.util.*;

class dualpal {

	public static void main(String[] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
		// input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));

		String s = f.readLine();
//		String s = "9 10";
		StringTokenizer st = new StringTokenizer(s);
		int additional = Integer.parseInt(st.nextToken());
		int number = Integer.parseInt(st.nextToken()) + 1;

		while (additional > 0) {
			String convNum;
			int count = 0;

			for (int j = 2; j <= 10; j++) {
				int value = number;
				StringBuilder sb = new StringBuilder();

				while (value > 0) {
					int digit = value % j;
					value /= j;
					sb.insert(0, digit);
				}
				convNum = sb.toString();
//				System.out.println(convNum);
				
				if (convNum.equals(sb.reverse().toString()))
					count++;
			}
			if (count > 1) {
				out.println(number);
				additional--;
			}
			number++;
		}

		out.flush();
		out.close(); // close the output file
		f.close();
	}
}