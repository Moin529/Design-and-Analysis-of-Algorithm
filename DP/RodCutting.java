// Rod Cutting Problem: Given a rod of length n and an array of prices where prices[i] is the price of a rod of length i+1,
//  find the maximum obtainable value by cutting the rod into pieces.
// This is a classic dynamic programming problem that can be solved using a bottom-up approach.

import java.util.Scanner;

public class RodCutting {

    static int cutRod(int[] prices, int rodLength) {
        int n = prices.length;
        int[][] dp = new int[n + 1][rodLength + 1];
        
        // Build the dp table in bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= rodLength; j++) {
                if (i <= j) {
                    // Include the piece or exclude it
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - i] + prices[i - 1]);
                } else {
                    // Exclude the piece
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][rodLength];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter rod length: ");
        int rodLength = sc.nextInt();

        System.out.println("Enter prices for rod lengths 1 to " + rodLength + ":");
        int[] prices = new int[rodLength];
        for (int i = 0; i < rodLength; i++) {
            prices[i] = sc.nextInt();
        }

        int maxValue = cutRod(prices, rodLength);
        System.out.println("Maximum obtainable value is " + maxValue);
    }
}
