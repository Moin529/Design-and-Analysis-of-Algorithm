
// Given a number n, count the number of ways to express n as the sum of 1, 3, and 4.

import java.util.*;
public class NumberFactor {

    static int countWaysRecursion(int n) {

        // Base case: If n is 0, there is one way to express it (using no numbers)
        if (n == 0) {
            return 1;
        }
        // If n is negative, there are no ways to express it
        if (n < 0) {
            return 0;
        }

        // Recursive calls for using 1, 3, and 4
        return countWaysRecursion(n - 1) + countWaysRecursion(n - 3) + countWaysRecursion(n - 4);

    }

    static int countWaysMemoization(int n, int[] dp) {
        // Base case: If n is 0, there is one way to express it (using no numbers)
        if (n == 0) {
            return 1;
        }
        // If n is negative, there are no ways to express it
        if (n < 0) {
            return 0;
        }

        // Check if the value is already computed
        if (dp[n] != -1) {
            return dp[n];
        }

        // Recursive calls for using 1, 3, and 4
        dp[n] = countWaysMemoization(n - 1, dp) + countWaysMemoization(n - 3, dp) + countWaysMemoization(n - 4, dp);

        return dp[n];
    }

    static int countWaysTabulation(int n) {
        // Create an array to store the number of ways to express each number
        int[] dp = new int[n + 1];

        // Base case: There is one way to express 0 (using no numbers)
        dp[0] = 1;
        // Fill the dp array using tabulation
        for (int i = 1; i <= n; i++) {
            // If we can use 1
            if (i - 1 >= 0) {
                dp[i] += dp[i - 1];
            }
            // If we can use 3
            if (i - 3 >= 0) {
                dp[i] += dp[i - 3];
            }
            // If we can use 4
            if (i - 4 >= 0) {
                dp[i] += dp[i - 4];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // Example input
        // Using Recursion

        System.out.println("Number of ways to express " + n + " using recursion: " + countWaysRecursion(n));

        // Using Memoization
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = -1; // Initialize the dp array with -1
        }

        System.out.println("Number of ways to express " + n + " using memoization: " + countWaysMemoization(n, dp));

        // Using Tabulation
        System.out.println("Number of ways to express " + n + " using tabulation: " + countWaysTabulation(n));

    }
}