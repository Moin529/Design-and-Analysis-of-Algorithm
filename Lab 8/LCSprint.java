import java.util.*;
public class LCSprint {
    static int lcsMemoization(String str1, String str2, int i, int j, int[][] dp) {
        if (i == 0 || j == 0) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
            dp[i][j] = 1 + lcsMemoization(str1, str2, i - 1, j - 1, dp);
        } else {
            int excludeStr1 = lcsMemoization(str1, str2, i - 1, j, dp);
            int excludeStr2 = lcsMemoization(str1, str2, i, j - 1, dp);
            dp[i][j] = Math.max(excludeStr1, excludeStr2);
        }
        return dp[i][j];
    }

    static String buildLCS(String str1, String str2, int[][] dp) {
        int i = str1.length();
        int j = str2.length();
        StringBuilder lcs = new StringBuilder();

        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return lcs.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input String 1: ");
        String str1 = sc.nextLine();
        System.out.print("Input String 2: ");
        String str2 = sc.nextLine();
       

        int i = str1.length();
        int j = str2.length();

        int[][] dp = new int[i + 1][j + 1];
        for (int x = 0; x <= i; x++) {
            for (int y = 0; y <= j; y++) {
                dp[x][y] = -1;
            }
        }

        int lcsLength = lcsMemoization(str1, str2, i, j, dp);
        System.out.println("Length of LCS: " + lcsLength);

        String lcsStr = buildLCS(str1, str2, dp);
        System.out.println("LCS: " + lcsStr);
    }
}