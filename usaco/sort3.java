
/*
ID: alethe3
LANG: JAVA
TASK: sort3
 */

import java.util.*;
import java.io.*;

public class sort3 {
	static int numSwaps, visitedCounter, numOnes, numTwos;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));

		int[] arr = new int[Integer.parseInt(br.readLine())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		numOnes = numTwos = 0;
		arr = trimArray(arr);
		boolean[] visited = new boolean[arr.length];
		numSwaps = visitedCounter = 0;
		if (arr.length > 0) {
			for (int i = 0; i < numOnes; i++) {
				if (arr[i] == 2)
					if (findPairs(arr, visited, numOnes, numOnes + numTwos, 1)) {
						visited[i] = true;
						visitedCounter++;
					}
				if (arr[i] == 3)
					if (findPairs(arr, visited, numOnes + numTwos, arr.length, 1)) {
						visited[i] = true;
						visitedCounter++;
					}
			}

			for (int i = numOnes; i < numOnes + numTwos; i++) {
				if (arr[i] == 1 && !visited[i])
					if (findPairs(arr, visited, 0, numOnes, 2)) {
						visited[i] = true;
						visitedCounter++;
					}
				if (arr[i] == 3 && !visited[i])
					if (findPairs(arr, visited, numOnes + numTwos, arr.length, 2)) {
						visited[i] = true;
						visitedCounter++;
					}
			}

			for (int i = numOnes + numTwos; i < arr.length; i++) {
				if (arr[i] == 1 && !visited[i])
					if (findPairs(arr, visited, 0, numOnes, 3)) {
						visited[i] = true;
						visitedCounter++;
					}
				if (arr[i] == 2 && !visited[i])
					if (findPairs(arr, visited, numOnes, numOnes + numTwos, 3)) {
						visited[i] = true;
						visitedCounter++;
					}
			}
		}
		
		System.out.println(numSwaps);
		System.out.println(arr.length);
		System.out.println(visitedCounter);
		pw.println(numSwaps + 2 * (arr.length - visitedCounter) / 3);
		pw.flush();
		pw.close();
		br.close();
	}

	private static boolean findPairs(int[] arr, boolean[] visited, int low, int high, int num) {
		for (int i = low; i < high; i++) {
			if (!visited[i] && arr[i] == num) {
				numSwaps++;
				visited[i] = true;
				visitedCounter++;

				return true;
			}
		}

		return false;
	}

	private static int[] trimArray(int[] arr) {
		int[] trimArr = new int[arr.length];
		int[] sortArr = Arrays.copyOf(arr, arr.length);
		Arrays.sort(sortArr);
		int idx = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != sortArr[i]) {
				trimArr[idx++] = arr[i];
				if (arr[i] == 1)
					numOnes++;
				if (arr[i] == 2)
					numTwos++;
			}
		}

		return Arrays.copyOf(trimArr, idx);
	}
}
