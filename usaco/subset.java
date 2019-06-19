/*
ID: alexthe3
LANG: JAVA
TASK: subset
*/
import java.io.*;

public class subset {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("subset.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
        int n = Integer.parseInt(br.readLine());
        int sum = n * (n + 1) / 2;
        if (sum % 2 != 0) pw.println(0);
        else {
            int target = sum / 2 - n;
            if (target == 0) target = n;
            int[][] table = new int[target + 1][n + 1];
            for (int i = 1; i <= target; i++) {
                int subsum, best = 0;
                for (int j = n - 1; j >= 1; j--) {
                    if (i < j) j = i;
                    subsum = i - j;
                    if (j == i) best = 1;
                    else if (subsum != j) best += table[subsum][j + 1];
                    table[i][j] = best;
                }
            }
            pw.println(table[target][1]);
        }
        br.close();
        pw.flush();
        pw.close();
    }
}
