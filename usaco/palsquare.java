
/*
ID: alexthe3
LANG: JAVA
TASK: palsquare
*/
import java.io.*;
import java.util.*;

class palsquare {
	static String number = "0"; // squared number
	static String startNum = "0"; // initial number

	public static void main(String[] args) throws IOException {
		 // Use BufferedReader rather than RandomAccessFile; it's much faster
		 BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
		 // input file name goes above
		 PrintWriter out = new PrintWriter(new BufferedWriter(new
		 FileWriter("palsquare.out")));

		int base = Integer.parseInt(f.readLine());

		for (int i = 1; i <= 300; i++) {
			int square = i * i;
			
			// Converts starting number and square to appropriate base
			if (base != 10) {
				number = recur(square, base);
				startNum = recur(i, base);
				startNum = (new StringBuilder(startNum).reverse().toString());
			}
			else {
				number = square + "";
				startNum = i + "";
			}

			if (number.equals(new StringBuilder(number).reverse().toString())) {
				out.println(startNum + " " + number);
			}
		}

		 out.flush();
		 out.close(); // close the output file
		 f.close();
	}
	
	public static String recur (int x, int y) {
		if (x == 0) return "";
		
		int q = x/y;
		int r = x%y;
		
		char[] c = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
		String s = r + "";
		if (r > 9) s = c[r-10] + "";
		return s + recur (q, y);
	}

}