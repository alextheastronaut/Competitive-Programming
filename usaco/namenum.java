
/*
ID: alexthe3
LANG: JAVA
TASK: namenum
*/
import java.io.*;
import java.util.*;

class namenum {

	// Make the printwriter global so you can use it in your recursive method (only
	// do this in programming contests)
	private static PrintWriter out;
	private static char[][] arr;
	private static TreeSet<String> list;
	private static TreeSet<String> compare;

	public static void main(String[] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
		// Elligible names from dictionary
		BufferedReader names = new BufferedReader(new FileReader("dict.txt"));
		// input file name goes above
		out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));

		String numbers = f.readLine();
		String name = "";
		list = new TreeSet<>();
		compare = new TreeSet<>();

		// Puts names from dictionary with same length into treeset called "compare"
		while ((name = names.readLine()) != null) {
			if (name.length() == numbers.length())
				compare.add(name);
		}

		arr = new char[][] { null, null, { 'A', 'B', 'C' }, // 2
				{ 'D', 'E', 'F' }, // 3
				{ 'G', 'H', 'I' }, // 4
				{ 'J', 'K', 'L' }, // 5
				{ 'M', 'N', 'O' }, // 6
				{ 'P', 'R', 'S' }, // 7
				{ 'T', 'U', 'V' }, // 8
				{ 'W', 'X', 'Y' } // 9
		};

		// initial recursive call
		recur(numbers.toCharArray(), 0, new char[numbers.length()]);

		for (String s : list) {
			out.println(s);
		}

		if (list.isEmpty()) {
			out.println("NONE");
		}

		out.flush();
		out.close(); // close the output file
		f.close();
		names.close();
	}

	// recursive method
	public static void recur(char[] numbers, int index, char[] name) {

		// base case / termination condition
		if (index == numbers.length) {

			// if valid, add name to list
			if (compare.contains(new String(name))) {
				list.add(new String(name));

				// you can also just print it off right here
				// out.println(new String(name));
			}

			// terminate when the end of the name is reached
			return;
		}

		int num = numbers[index] - '0';
		for (int i = 0; i < 3; i++) {
			name[index] = arr[num][i];

			// recursive call
			recur(numbers, index + 1, name);
		}
	}
}
