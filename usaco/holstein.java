
/*
ID: alethe3
LANG: JAVA
TASK: holstein
 */

import java.util.*;
import java.io.*;

public class holstein {
	static int ori, minScoops;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));

		int[] minVit = new int[Integer.parseInt(br.readLine())];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < minVit.length; i++) {
			minVit[i] = Integer.parseInt(st.nextToken());
		}

		int[][] feed = new int[Integer.parseInt(br.readLine())][minVit.length];
		for (int i = 0; i < feed.length; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			for (int j = 0; j < feed[i].length; j++) {
				feed[i][j] = Integer.parseInt(st1.nextToken());
			}
		}

		minScoops = feed.length + 1;
		ori = 0;
		dfs(feed, minVit, 0, 0, 0);
		pw.print(minScoops);
		System.out.println(ori);
		for (int i = 0; i < feed.length; i++) {
			if ((ori & (1 << i)) != 0) {
				pw.print(" ");
				pw.print(i + 1);
			}
		}
		pw.println();

		pw.flush();
		pw.close();
		br.close();
	}

	private static void dfs(int[][] feed, int[] minVit, int depth, int scoops, int currOri) {
		
		if (nutriMet(minVit)) {
			if (scoops <= minScoops) {
				ori = currOri;
				minScoops = scoops;
			}
			return;
		}

		if (depth == feed.length)
			return;
		
		dfs(feed, Arrays.copyOf(minVit, minVit.length), depth + 1, scoops, currOri);

		currOri += Math.pow(2, depth);
		int[] minVitCopy = Arrays.copyOf(minVit, minVit.length);
		for (int i = 0; i < minVitCopy.length; i++) {
			minVitCopy[i] -= feed[depth][i];
		}
		scoops++;

		dfs(feed, Arrays.copyOf(minVitCopy, minVitCopy.length), depth + 1, scoops, currOri);
	}

	private static boolean nutriMet(int[] minVit) {
		for (int i = 0; i < minVit.length; i++) {
			if (minVit[i] > 0)
				return false;
		}
		return true;
	}
}
