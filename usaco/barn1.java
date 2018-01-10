
/*
ID: alexthe3
LANG: JAVA
TASK: barn1
*/
import java.io.*;
import java.util.*;

class barn1 {

	public static void main(String[] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
		// input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));

		String input = f.readLine();
		StringTokenizer st = new StringTokenizer(input);
		int numBoards = Integer.parseInt(st.nextToken());
		int numStalls = Integer.parseInt(st.nextToken());
		int numCows = Integer.parseInt(st.nextToken());
		boolean[] stalls = new boolean[numStalls];

		int lowBound = numStalls;
		int upBound = 0;
		for (int i = 0; i < numCows; i++) {
			int num = Integer.parseInt(f.readLine());
			stalls[num - 1] = true;
			lowBound = Math.min(lowBound, num);
			upBound = Math.max(upBound, num);
		}
		stalls[lowBound - 1] = stalls[upBound - 1] = true;

		numStalls = upBound - lowBound + 1;
		System.out.println(numStalls);
		int c = 0;
		for (boolean b : stalls) {
			c++;
			System.out.println(c + " " + b);
		}

		for (int i = 1; i < numBoards; i++) {
			int gapCounter = 0;
			int maxGap = 0;
			int index = 0;
			for (int j = lowBound - 1; j < upBound; j++) {
				gapCounter = (stalls[j]) ? 0 : gapCounter + 1;

				if (gapCounter > maxGap) {
					maxGap = gapCounter;
					index = j;
				}
			}
			System.out.println(maxGap);
			numStalls -= maxGap;
			Arrays.fill(stalls, index + 1 - maxGap, index + 1, true);
		}

		out.println	(numStalls);
		out.flush();
		out.close(); // close the output file
		f.close();
	}
}