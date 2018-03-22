/*
ID: alethe3
LANG: JAVA
TASK: skidesign	
*/
import java.io.*;
import java.util.*;

public class skidesign {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("skidesign.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));

		int numHills = Integer.parseInt(br.readLine());

		int[] hills = new int[numHills];
		for (int i = 0; i < numHills; i++) {
			hills[i] = Integer.parseInt(br.readLine());
		}

		int[] cost = new int[numHills];

		Arrays.sort(hills);
		int small = hills[0];
		int large = hills[numHills - 1];

		while (large - small > 17) {
			int costSmall = 0;
			int costLarge = 0;
			int indexSmall = 0;
			int indexLarge = numHills - 1;

			for (int i = 0; small == hills[i]; i++) {
				costSmall += Math.pow(cost[i] + 1, 2) - Math.pow(cost[i], 2);
				indexSmall = i;
			}

			for (int i = numHills - 1; large == hills[i]; i--) {
				costLarge += Math.pow(cost[i] + 1, 2) - Math.pow(cost[i], 2);
				indexLarge = i;
			}

			if (costLarge <= costSmall) {
				for (int i = numHills - 1; i >= indexLarge; i--) {
					hills[i]--;
					cost[i]++;
				}
				large--;

			} else {
				for (int i = 0; i <= indexSmall; i++) {
					hills[i]++;
					cost[i]++;
				}
				small++;
			}
		}

		int total = 0;
		for (int i = 0; i < numHills; i++) {
			total += Math.pow(cost[i], 2);
		}

		out.println(total);
		br.close();
		out.flush();
		out.close();

	}
}
