/*
ID: alexthe3
LANG: JAVA
TASK: milk3
*/

import java.util.*;
import java.io.*;

public class milk3 {
	public static ArrayList<Integer> data = new ArrayList<>();
	static HashSet<String> pos = new HashSet<>();
	static int a;
	static int b;
	static int c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("milk3.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());

		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		int[] buckets = new int[3];
		buckets[2] = c;

		dfs(0, buckets);

		Collections.sort(data);
		
		for (int i = 0; i < data.size() - 1; i++) {
			pw.print(data.get(i) + " ");
		}
		
		pw.println(data.get(data.size() - 1));
		
		pw.flush();
		pw.close();
		br.close();
	}

	public static void dfs(int depth, int[] buckets) {
		
		if (pos.contains(toString(buckets))) 
			return;
	
		pos.add(toString(buckets));
	
		if (buckets[0] == 0) {
			if (!data.contains(buckets[2])) {
				System.out.println(toString(buckets));
				data.add(buckets[2]);
			}
		}

		if (buckets[0] != 0) {
			if (buckets[1] < b) {
				dfs(depth + 1, pour(0, 1, buckets, b));
			}
			if (buckets[2] < c) {
				dfs(depth + 1, pour(0, 2, buckets, c));
			}
		}
		if (buckets[1] != 0) {
			if (buckets[0] < a) {
				dfs(depth + 1, pour(1, 0, buckets, a));
			}
			if (buckets[2] < c) {
				dfs(depth + 1, pour(1, 2, buckets, c));
			}
		}
		if (buckets[2] != 0) {
			if (buckets[0] < a) {
				dfs(depth + 1, pour(2, 0, buckets, a));
			}
			if (buckets[1] < b) {
				dfs(depth + 1, pour(2, 1, buckets, b));
			}

			return;
		}

	}

	public static int[] pour(int i1, int i2, int[] copy, int max) {
		int[] buckets = Arrays.copyOf(copy, copy.length);
		if (buckets[i1] + buckets[i2] > max) {
			buckets[i1] = buckets[i1] + buckets[i2] - max;
			buckets[i2] = max;
			
		}
		else {
			buckets[i2] += buckets[i1];
			buckets[i1] = 0;
		}
		
		return buckets;
	}
	
	public static String toString(int[] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i] + " ");
		}
		
		return sb.toString();
	}
}
