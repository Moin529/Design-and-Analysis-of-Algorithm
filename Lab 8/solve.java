// Problem 1

import java.util.HashMap;

public class Fibonacci {

    // Top-down Memoization
    public static int fibMemo(int n, HashMap<Integer, Integer> memo) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) return memo.get(n);
        int result = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    // Bottom-up Tabulation
    public static int fibTab(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println("fibMemo: " + fibMemo(10, new HashMap<>()));
        System.out.println("fibTab: " + fibTab(10));
    }
}



// problem 2

public class Knapsack {

    // Recursive solution with memoization
    public static int knapsackRec(int[] weights, int[] values, int n, int capacity, int[][] dp) {
        if (n == 0 || capacity == 0) return 0;
        if (dp[n][capacity] != -1) return dp[n][capacity];

        if (weights[n - 1] <= capacity) {
            int include = values[n - 1] + knapsackRec(weights, values, n - 1, capacity - weights[n - 1], dp);
            int exclude = knapsackRec(weights, values, n - 1, capacity, dp);
            dp[n][capacity] = Math.max(include, exclude);
        } else {
            dp[n][capacity] = knapsackRec(weights, values, n - 1, capacity, dp);
        }
        return dp[n][capacity];
    }

    // DP tabulation
    public static int knapsackDP(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i - 1] <= w)
                    dp[i][w] = Math.max(dp[i - 1][w],
                            dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                else
                    dp[i][w] = dp[i - 1][w];
            }
        }
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 3};
        int[] values = {10, 15, 40};
        int capacity = 4;
        int[][] dp = new int[weights.length + 1][capacity + 1];
        for (int i = 0; i < dp.length; i++)
            for (int j = 0; j < dp[0].length; j++)
                dp[i][j] = -1;

        System.out.println("knapsackRec: " + knapsackRec(weights, values, weights.length, capacity, dp));
        System.out.println("knapsackDP: " + knapsackDP(weights, values, capacity));
    }
}


// Problem 3

public class LCS {

    // Recursive with memoization
    public static int lcsRec(String s1, String s2, int i, int j, int[][] memo) {
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
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        int[][] memo = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++)
            for (int j = 0; j <= s2.length(); j++)
                memo[i][j] = -1;

        System.out.println("lcsRec: " + lcsRec(s1, s2, s1.length(), s2.length(), memo));
        System.out.println("lcsDP: " + lcsDP(s1, s2));
    }
}



//Problem 4

public class RockClimbing {

    // Recursive with memoization
    public static int climbRec(int[][] wall, int row, int col, int[][] memo) {
        if (row < 0 || col < 0 || col >= wall[0].length)
            return 0;
        if (memo[row][col] != -1) return memo[row][col];

        int maxPrev = Math.max(
            climbRec(wall, row - 1, col, memo),
            Math.max(
                climbRec(wall, row - 1, col - 1, memo),
                climbRec(wall, row - 1, col + 1, memo)
            )
        );
        memo[row][col] = wall[row][col] + maxPrev;
        return memo[row][col];
    }

    // DP bottom-up
    public static int climbDP(int[][] wall) {
        int rows = wall.length, cols = wall[0].length;
        int[][] dp = new int[rows][cols];

        // Initialize top row
        for (int j = 0; j < cols; j++)
            dp[0][j] = wall[0][j];

        // Fill from top to bottom
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int maxPrev = dp[i - 1][j];
                if (j > 0) maxPrev = Math.max(maxPrev, dp[i - 1][j - 1]);
                if (j < cols - 1) maxPrev = Math.max(maxPrev, dp[i - 1][j + 1]);
                dp[i][j] = wall[i][j] + maxPrev;
            }
        }

        int maxEnergy = 0;
        for (int j = 0; j < cols; j++)
            maxEnergy = Math.max(maxEnergy, dp[rows - 1][j]);

        return maxEnergy;
    }

    public static void main(String[] args) {
        int[][] wall = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int rows = wall.length, cols = wall[0].length;
        int[][] memo = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                memo[i][j] = -1;

        int maxEnergyRec = 0;
        for (int j = 0; j < cols; j++)
            maxEnergyRec = Math.max(maxEnergyRec, climbRec(wall, rows - 1, j, memo));

        System.out.println("rockClimbingRec: " + maxEnergyRec);
        System.out.println("rockClimbingDP: " + climbDP(wall));
    }
}


