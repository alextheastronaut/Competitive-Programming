/*
ID: alexthe3
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;

public class gift1 {
	public static void main(String[] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
		// input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
		// Use StringTokenizer vs. readLine/split -- lots faster
		
		// Get line, break into tokens
		HashMap<String, Integer> m = new HashMap<>();
		int numberOfPeople = Integer.parseInt(f.readLine());
		String list = "";
		for (int i = 0; i < numberOfPeople; i++) {
			String name = f.readLine();
			m.put(name, 0);
			list += name + " ";
		}
		
		String person = "";
		
		while ((person = f.readLine()) != null) 
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			int gift = Integer.parseInt(st.nextToken());
			int receivers = Integer.parseInt(st.nextToken());
			if (receivers != 0) m.put(person, m.get(person) - gift + (gift % receivers));
			
			for (int i = 0; i < receivers; i++) {
				String rName = f.readLine();
				m.put(rName, m.get(rName) + gift/receivers);
			}
			
		}
		
		StringTokenizer t = new StringTokenizer(list);
		while (t.hasMoreTokens()) {
			String name = t.nextToken();
			out.println(name + " " + m.get(name));
		}		
		
		out.flush();
		out.close();
		f.close();
	}
}
