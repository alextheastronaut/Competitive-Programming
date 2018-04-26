/*
ID: alexthe3
LANG: JAVA
TASK: numtri
 */

import java.util.*;
import java.io.*;

public class numtri {
	static int depth;
	static int[][] tri;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
		
		depth = Integer.parseInt(br.readLine());
		tri = new int[depth][];
		
		tri[0] = new int[1];
		tri[0][0] = Integer.parseInt(br.readLine());
		
		for (int i = 1; i < depth; i++) {
			tri[i] = new int[i + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			tri[i][0] = tri[i - 1][0] + Integer.parseInt(st.nextToken());
			
			for (int j = 1; j < tri[i].length - 1; j++) {
				int num = Integer.parseInt(st.nextToken());
				tri[i][j] = Math.max(tri[i - 1][j - 1] + num, tri[i - 1][j] + num);
			}
			tri[i][tri[i].length - 1] = tri[i - 1][tri[i - 1].length - 1] + Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(tri[depth - 1]);
		pw.println(tri[depth - 1][depth - 1]);
		
		
		pw.flush();
		pw.close();
		br.close();

	}

}
