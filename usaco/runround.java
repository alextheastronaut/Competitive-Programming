/*
ID: alexthe3
LANG: JAVA
TASK: runround
 */
import java.io.*;
public class runround {
    static int M, logM, numDigits;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("runround.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
        String stringM = br.readLine();
        M = Integer.parseInt(stringM);
        logM = stringM.length() - 1;
        numDigits = logM + 1;
        int mostSigDig, best = 1000000000;
        mostSigDig = Character.getNumericValue(stringM.charAt(0));
        while (best == 1000000000) {
            int N, takenNums, takenDigitPos;
            N = addDigitToN(0, mostSigDig, logM);
            takenNums = 1 << mostSigDig;
            takenDigitPos = 1 << logM;
            best = findBest(best, N, takenNums, takenDigitPos, 1, findNextSpot(mostSigDig, logM));
            if (mostSigDig == 9) {
                logM++;
                numDigits++;
                mostSigDig = 1;
            } else mostSigDig++;
        }
        pw.println(best);
        br.close();
        pw.flush();
        pw.close();
    }

    static int findBest(int best, int N, int takenNums, int takenDigitPos, int numTakenSpots, int spot) {
        if (numTakenSpots == logM + 1) return best;
        for (int i = 1; i <= 9; i++) {
            int nextSpot = findNextSpot(i, spot);
            if (numTakenSpots == logM && nextSpot == logM && !isSpotTaken(takenNums, i)) {
                int num = Math.min(best, addDigitToN(N, i, spot));
                if (num > M) best = num;
            }
            else if (isSpotTaken(takenNums, i) || isSpotTaken(takenDigitPos, nextSpot) || spot == nextSpot) continue;
            else best = Math.min(best,
                    findBest(best, addDigitToN(N, i, spot), takeSpot(takenNums, i), takeSpot(takenDigitPos, spot), numTakenSpots + 1, nextSpot));
        }
        return best;
    }
    static int addDigitToN(int N, int num, int pow) {
        return (int) (N + num * Math.pow(10, pow));
    }
    static boolean isSpotTaken(int bitmap, int spot) {
        return (bitmap & (1 << spot)) != 0;
    }
    static int takeSpot(int bitmap, int spot) {
        return bitmap | (1 << spot);
    }
    static int findNextSpot(int num, int currentSpot) {
        return ((numDigits - num % numDigits) + currentSpot) % numDigits;
    }
}
