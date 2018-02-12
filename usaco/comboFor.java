/*
ID: alexthe3
LANG: JAVA
TASK: combo
*/
import java.io.*;
import java.util.*;

public class combo {
	
	static HashSet<String> h = new HashSet<>();
	static int numNum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("combo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));

		numNum = Integer.parseInt(f.readLine());
		String[] comb1 = f.readLine().split(" ");
		String[] comb2 = f.readLine().split(" ");
		
		listComb(Integer.parseInt(comb1[0]) - 1, Integer.parseInt(comb1[1]) - 1, Integer.parseInt(comb1[2]) - 1);
		listComb(Integer.parseInt(comb2[0]) - 1, Integer.parseInt(comb2[1]) - 1, Integer.parseInt(comb2[2]) - 1);
		
		out.println(h.size());
		out.flush();
		out.close(); // close the output file
		f.close();
	}
	
	public static void listComb(int a, int b, int c) {
		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
				for (int k = -2; k <= 2; k++) {
					h.add((((a + i) + numNum) % numNum) + " "  + (((b + j)+ numNum) % numNum) + " " 
							+ (((c + k) + numNum) % numNum));
				}
			}
		}
	}
}
