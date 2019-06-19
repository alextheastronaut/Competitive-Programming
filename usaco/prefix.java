/*
ID: alexthe3
LANG: JAVA
TASK: prefix
 */

import java.io.*;
import java.util.*;

public class prefix {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("prefix.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
        HashSet<String>[] primitives = new HashSet[11];
        int longest, best;
        String line;
        StringTokenizer st;
        longest = best = 0;
        while (!(line = br.readLine()).equals(".")) {
            st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                if (primitives[token.length()] == null) primitives[token.length()] = new HashSet<>();
                primitives[token.length()].add(token);
                longest = Math.max(token.length(), longest);
            }
        }
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) sb.append(line);

        int skipped = 0;
        for (int i = 1; i <= sb.length(); i++) {
            if (skipped < longest) {
                if (substringInSet(sb, primitives, i, skipped + 1, longest)) {
                    best = i;
                    skipped = 0;
                }
                else skipped++;
            } else {
                break;
            }
        }
        pw.println(best);
        pw.flush();
        pw.close();
        br.close();
    }

    public static boolean substringInSet(StringBuilder sb, HashSet<String>[] primitives, int subEnd, int shortest, int longest) {
        for (int i = shortest; i <= longest; i++) {
            if (subEnd - i >= 0 && primitives[i] != null) {
                if (primitives[i].contains(sb.substring(subEnd - i, subEnd))) return true;
            }
        }

        return false;
    }
}

