
/*
ID: alethe3
LANG: JAVA
TASK: crypt1
*/
import java.io.*;
import java.util.*;

public class crypt1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("crypt1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));

		int numElements = Integer.parseInt(br.readLine());
		String[] numArr = br.readLine().split(" ");
		char[] numbers = new char[5];
		int counter = 0;
		for (int i = 0; i < numElements; i++) {
			numbers[0] = numArr[i].charAt(0);
			for (int j = 0; j < numElements; j++) {
				numbers[1] = numArr[j].charAt(0);
				for (int k = 0; k < numElements; k++) {
					numbers[2] = numArr[k].charAt(0);
					for (int l = 0; l < numElements; l++) {
						numbers[3] = numArr[l].charAt(0);
						for (int m = 0; m < numElements; m++) {
							numbers[4] = numArr[m].charAt(0);

							String numString = new String(numbers);
							StringBuilder sb = new StringBuilder(numString);
							int num1 = Integer.parseInt(sb.substring(0, 3));
							int num2 = Character.getNumericValue(numString.charAt(3));
							int num3 = Character.getNumericValue(numString.charAt(4));
							String subTotal1 = num2 * num1 + "";
							String subTotal2 = num3 * num1 + "";
							String total = num1 * (num2 * 10 + num3) + "";

							if (subTotal1.length() == 3 && subTotal2.length() == 3 && total.length() == 4) {
								boolean b = true;
								for (int y = 0; y < subTotal2.length(); y++) {
									if (!contains(subTotal2.charAt(y), numArr)) {
										b = false;
										break;
									}
								}

								for (int y = 0; y < subTotal1.length(); y++) {
									if (!contains(subTotal1.charAt(y), numArr)) {
										b = false;
										break;
									}
								}

								for (int z = 0; z < total.length(); z++) {
									if (!contains(total.charAt(z), numArr)) {
										b = false;
										break;
									}
								}
								if (b) {
									counter++;
//									System.out.println(total);
									}
							}
						}
					}
				}
			}
		}
		out.println(counter);
		out.flush();
		out.close();
		br.close();
	}

	public static boolean contains(char c, String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (c == arr[i].charAt(0))
				return true;
		}

		return false;
	}
}
