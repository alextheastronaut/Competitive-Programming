
/* 
ID: alexthe3
LANG: JAVA
TASK: ariprog
 */

import java.util.*;
import java.io.*;

public class ariprog {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("ariprog.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
		int numEl = Integer.parseInt(br.readLine()) - 1;
		int pqMax = Integer.parseInt(br.readLine());
		int counter = 0;

		boolean[] boolArr = new boolean[2 * pqMax * pqMax + 1];
		ArrayList<Integer> arr = new ArrayList<>(2 * pqMax * pqMax); 
		for (int i = 0; i <= pqMax; i++) {
			for (int j = i; j <= pqMax; j++) {
				if (boolArr[i * i + j * j])
					continue;
				arr.add(i * i + j * j);
				boolArr[i * i + j * j] = true;
			}
		}
		
		Collections.sort(arr);

		for (int b = 1; b <= (int) (2 * Math.pow(pqMax, 2)) / numEl; b++) {
			for (Integer num : arr) {
				boolean bool = true;
				for (int i = 0; i <= numEl; i++) {
					if (num + i * b >= boolArr.length || !boolArr[num + i * b] ) {
						bool = false;
						break;
					}
				}
				if (bool) {
					pw.println(num + " " + b);
					counter++;
				}
			}
		}

		if (counter == 0)
			pw.println("NONE");
		pw.flush();
		pw.close();
		br.close();

	}

}
