/*
ID: alexthe3
LANG: JAVA
TASK: preface
 */
import java.util.*;
import java.io.*;
public class preface {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("preface.in"));
        //PrintWriter pw = new PrintWriter(System.out);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
        int numPages = Integer.parseInt(br.readLine());
        int[] counter = new int[7];
        for (int i = 1; i <= numPages; i++)
            increment(i, counter);
        String[] numerals = new String[]{"I ", "V ", "X ", "L ", "C ", "D ", "M "};
        for (int i = 0; i < 7; i++) {
            if (counter[i] != 0) pw.println(numerals[i] + counter[i]);
        }
        br.close();
        pw.flush();
        pw.close();
    }
    public static void increment(int num, int[] counter) {
        for (int j = 0; j <= 6; j+=2) {
            int jthPlace = num % 10;
            num /= 10;
            if (jthPlace == 0) continue;
            else if (jthPlace < 4) counter[j] += jthPlace;
            else if(jthPlace < 9) {
                if (jthPlace == 4 || jthPlace == 6) counter[j]++;
                else counter[j] += jthPlace - 5;
                counter[j + 1]++;
            } else {
                counter[j]++;
                counter[j + 2]++;
            }
        }
    }
}
