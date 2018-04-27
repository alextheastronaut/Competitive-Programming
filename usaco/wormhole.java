/*
ID: alexthe3
LANG: JAVA
TASK: wormhole
 */

import java.util.*;
import java.io.*;

public class wormhole {
	static Hole[] holes;
	static int numHoles, loopCount;

	public static class Hole {
		int x, y, pair, next;

		public Hole(int x, int y) {
			this.x = x;
			this.y = y;
			pair = next = -1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("wormhole.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));

		numHoles = Integer.parseInt(br.readLine());
		holes = new Hole[numHoles];
		for (int i = 0; i < numHoles; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			holes[i] = new Hole(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i < numHoles; i++) {
			for (int j = 0; j < numHoles; j++) {
				if (holes[i].y == holes[j].y && holes[i].x < holes[j].x
						&& (holes[i].next == -1 || holes[holes[i].next].x > holes[j].x))
					holes[i].next = j;
			}
		}

		dfs(0);

		pw.println(loopCount);
		pw.flush();
		pw.close();
		br.close();
	}

	private static void dfs(int bitmap) {
		if (bitmap == Math.pow(2, numHoles) - 1) {
			isLoop();
			return;
		}

		int first = -1;

		for (int i = 0; i < numHoles; i++) {
			if ((bitmap & (1 << i)) == 0) {
				if (first == -1) {
					bitmap |= (1 << i);
					first = i;
					continue;
				}

				holes[first].pair = i;
				holes[i].pair = first;
				dfs(bitmap | (1 << i));
			}

		}

	}

	private static void isLoop() {
		for (int i = 0; i < numHoles; i++) {
			boolean[] visitedIn = new boolean[numHoles];
			int hole = i;
			while (true) {
				if (visitedIn[hole]) {
					loopCount++;
					return;
				}

				visitedIn[hole] = true;
				hole = holes[hole].pair;

				if (holes[hole].next == -1)
					break;

				hole = holes[hole].next;

			}
		}

	}

}
