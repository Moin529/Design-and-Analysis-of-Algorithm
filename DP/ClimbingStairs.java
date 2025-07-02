// You are climbing a staircase. It takes n steps to reach the top.
// Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

import java.util.Scanner;

public class ClimbingStairs {

    // Recursive approach
    static int usingRecursion(int n) {
        if (n <= 1) return 1;
        return usingRecursion(n - 1) + usingRecursion(n - 2);
    }

    // Memoization (top-down DP)
    static int dpmemoization(int n, int[] d) {
        if (n <= 1) return 1;
        if (d[n] != 0) return d[n];
        d[n] = dpmemoization(n - 1, d) + dpmemoization(n - 2, d);
        return d[n];
    }

    // Tabulation (bottom-up DP)
    static int dpTabulation(int n) {
        if (n <= 1) return 1;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];

        return dp[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of stairs (1 to 45): ");
        int n = scanner.nextInt();

        if (n < 1 || n > 45) {
            System.out.println("Invalid input. Please enter a value between 1 and 45.");
            return;
        }

        System.out.println("Using recursion: " + usingRecursion(n));

        int[] memo = new int[n + 1];
        System.out.println("Using memoization: " + dpmemoization(n, memo));

        System.out.println("Using tabulation: " + dpTabulation(n));
    }
}
