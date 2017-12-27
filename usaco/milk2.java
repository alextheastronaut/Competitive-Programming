
/*
ID: alexthe3
LANG: JAVA
TASK: milk2
*/
import java.io.*;
import java.util.*;

class milk2 {
	public static void main(String[] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
		// input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));

		int farmers = Integer.parseInt(f.readLine());
		int maxMilk = 0;
		int maxNoMilk = 0;
		int counterTrue = 0;
		int counterFalse = 0;
		int biggestFinish = 0;
		int smallestStart = 1000000;
		
		boolean[] time = new boolean[1000000];
		
		for (int i = 0; i < farmers; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken());
			int finish = Integer.parseInt(st.nextToken()) - 1;
			
			biggestFinish = (biggestFinish > finish) ? biggestFinish : finish;
			smallestStart = (smallestStart < start) ? smallestStart : start;
			
			for (int j = start; j <= finish; j++) {
				time[j] = true;
			}
		}
		
		for (int i = smallestStart; i <= biggestFinish; i++) {
			counterTrue++;
			counterFalse++;
			
			if (time[i] == false) {
				counterTrue = 0;
			}
			if (time[i] == true) {
				counterFalse = 0;
			}

			if (maxMilk < counterTrue) {
				maxMilk = counterTrue;
			}
			if (maxNoMilk < counterFalse) {
				maxNoMilk = counterFalse;
			}
		}

		out.println(maxMilk + " " + maxNoMilk); // output result
		out.flush();
		out.close(); // close the output file
		f.close();
	}
}