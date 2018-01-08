
/*
ID: alexthe3
LANG: JAVA
TASK: milk
*/
import java.io.*;
import java.util.*;

class milk {

	public static class Pair implements Comparable<Pair> {
		public int price, quantity;

		Pair(int price, int quantity) {
			this.price = price;
			this.quantity = quantity;
		}

		@Override
		public int compareTo(Pair o) {
			return this.price - o.price;
		}
	}

	public static void main(String[] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("milk.in"));
		// input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));

		String input = f.readLine();
		StringTokenizer st = new StringTokenizer(input);
		int numBottles = Integer.parseInt(st.nextToken());
		int numFarmers = Integer.parseInt(st.nextToken());
		TreeMap<Integer, Integer> data = new TreeMap<>();

		Pair[] pairs = new Pair[numFarmers];

		for (int i = 0; i < numFarmers; i++) {
			input = f.readLine();
			st = new StringTokenizer(input);

			pairs[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(pairs);

		int totalCost = 0;
		for (Pair p : pairs) {
			if (numBottles >= p.quantity) {
				numBottles -= p.quantity;
				totalCost += p.price * p.quantity;
			} else {
				totalCost += p.price * numBottles;
				break;
			}
		}

		out.println(totalCost);

		out.flush();
		out.close(); // close the output file
		f.close();
	}
}