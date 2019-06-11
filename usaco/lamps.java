/*
ID: alexthe3
LANG: JAVA
TASK: lamps
 */

import java.util.*;
import java.io.*;

public class lamps {

    public static int flipSwitch(int lamp) {
        return lamp == 1 ? 0 : 1;
    }

    public static void printBinary(int digits, int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < digits; i++) {
            sb.append((num & (1 << i)) != 0 ? 1 : 0);
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lamps.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));

        int n, c, next, nextCombinations, combinations = 0;
        n = Integer.parseInt(br.readLine());
        c = Integer.parseInt(br.readLine());
        StringTokenizer st;
        boolean[] on = new boolean[101];
        boolean[] off = new boolean[101];
        boolean impossible = false;
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            while ((next = Integer.parseInt(st.nextToken())) != -1) {
                if (i == 0) on[next] = true;
                else {
                    if (on[next]) {
                        impossible = true;
                        break;
                    }
                    off[next] = true;
                }
            }
        }

        if (impossible) pw.println("IMPOSSIBLE");
        else {
            for (int i = 0; i < c; i++) {
                nextCombinations = 0;
                if (combinations == 0) for (int j = 0; j < 4; j++) nextCombinations |= (1 << (1 << j));
                else {
                    nextCombinations = 0;
                    for (int j = 0; j < 4; j++) {
                        for (int k = 0; k < 16; k++) {
                            if ((combinations & (1 << k)) != 0) {
                                //if (i == 1) System.out.println(k + " " + (1 << j) + " " + (k ^ (1 << j)));
                                nextCombinations |= (1 << (k ^ (1 << j)));
                                //printBinary(16, nextCombinations);
                            }
                        }
                    }
                }
                combinations = nextCombinations;
            }
        }

        if (combinations == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (off[i]) {
                    impossible = true;
                    break;
                }
                sb.append('1');
            }
            if (impossible) pw.println("IMPOSSIBLE");
            else pw.println(sb.toString());
        }
        else {
            ArrayList<String> okayOri = new ArrayList<>(10);
            for (int i = 0; i < 16; i++) {
                if ((combinations & (1 << i)) != 0) {
                    StringBuilder sb = new StringBuilder();
                    boolean[] buttons = new boolean[4];
                    //System.out.println(i);
                    for (int j = 0; j < 4; j++) {
                        if ((i & (1 << j)) != 0) {
                            buttons[j] = true;
                        }
                        //System.out.print(buttons[j] + " ");
                    }
                    //System.out.println();
                    boolean meetsCriteria = true;
                    for (int j = 1; j <= n; j++) {
                        int lampStatus = 1;
                        if (buttons[0]) lampStatus = flipSwitch(lampStatus);
                        if (buttons[1] && j % 2 == 0) lampStatus = flipSwitch(lampStatus);
                        if (buttons[2] && j % 2 != 0) lampStatus = flipSwitch(lampStatus);
                        if (buttons[3] && j % 3 == 1) lampStatus = flipSwitch(lampStatus);
                        if (lampStatus == 0 && on[j] || lampStatus == 1 && off[j]) {
                            meetsCriteria = false;
                            break;
                        }
                        sb.append(lampStatus);
                    }
                    if (meetsCriteria) okayOri.add(sb.toString());
                }
            }
            if (okayOri.isEmpty()) pw.println("IMPOSSIBLE");
            else {
                Collections.sort(okayOri);
                for (String lamp : okayOri) pw.println(lamp);
            }
        }

        pw.flush();
        pw.close();
        br.close();
    }
}
