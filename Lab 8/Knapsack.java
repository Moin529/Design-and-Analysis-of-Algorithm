import java.util.*;

public class Knapsack {

    // Recursive with memoization
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
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of items: ");
        int items = sc.nextInt();

        int[] weights = new int[items];
        int[] values = new int [items];
        for(int i=0; i<items; i++){
            System.out.print("Weight of item " + (i+1) + ": ");
            weights[i] = sc.nextInt();
            System.out.print("Value of value " + (i+1) + ": ");
            values[i] = sc.nextInt();
        }

        System.out.print("Capacity : ");
        int capacity = sc.nextInt();
        int[][] dp = new int[weights.length + 1][capacity + 1];
        for (int i = 0; i < dp.length; i++)
            for (int j = 0; j < dp[0].length; j++)
                dp[i][j] = -1;

        // Result from Memoization
        //System.out.println(knapsackRec(weights, values, weights.length, capacity, dp));
        // Result from DP
        System.out.println(knapsackDP(weights, values, capacity));
    }
}
