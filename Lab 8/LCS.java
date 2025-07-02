import java.util.*;

public class LCS {

    // with recursion
    static int lcsRecursion(String str1, String str2, int i, int j) {
        if (i == 0 || j == 0) {
            return 0; // Base case: one string is empty
        }
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {

            int includeChar = 1 + lcsRecursion(str1, str2, i - 1, j - 1); // Include character from both strings
            return includeChar; // Return the length of LCS including this character
        } else {
            int excludeStr1 = lcsRecursion(str1, str2, i - 1, j); // Exclude character from str1
            int excludeStr2 = lcsRecursion(str1, str2, i, j - 1); // Exclude character from str2
            return Math.max(excludeStr1, excludeStr2); // Take the maximum of excluding either character
        }
    }
    
    // with memoization
    public static int lcsMem(String s1, String s2, int i, int j, int[][] memo) {
        if (i == 0 || j == 0) return 0;
        if (memo[i][j] != -1) return memo[i][j];

        if (s1.charAt(i - 1) == s2.charAt(j - 1))
            memo[i][j] = 1 + lcsRec(s1, s2, i - 1, j - 1, memo);
        else
            memo[i][j] = Math.max(lcsRec(s1, s2, i - 1, j, memo),
                                  lcsRec(s1, s2, i, j - 1, memo));
        return memo[i][j];
    }

    // DP Tabulation
    public static int lcsDP(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input String 1: ");
        String s1 = sc.nextLine();
        System.out.print("Input String 2: ");
        String s2 = sc.nextLine();

        int[][] memo = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++)
            for (int j = 0; j <= s2.length(); j++)
                memo[i][j] = -1;

        System.out.println("LCS with memoization: " + lcsMem(s1, s2, s1.length(), s2.length(), memo));
        System.out.println("LCS with DP: " + lcsDP(s1, s2));
    }
}
