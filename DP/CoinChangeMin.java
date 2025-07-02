

// Coin Change Minimum Number of Coins Problem
// Given a set of coin denominations and an amount, find the minimum number of coins needed to make that amount.
public class CoinChangeMin {

    static int minCoinsRecursive(int[] coins, int amount, int n) {
        if (amount == 0) {
            return 0; // no coins needed for 0 amount
        }
        if (n == 0) {
            return Integer.MAX_VALUE - 1; // can't make change with 0 coins
        }

        if (coins[n - 1] <= amount) {
            int include = 1 + minCoinsRecursive(coins, amount - coins[n - 1], n); // reuse coin
            int exclude = minCoinsRecursive(coins, amount, n - 1);

            if(include == Integer.MAX_VALUE - 1 && exclude == Integer.MAX_VALUE - 1) {
                return -1; // not possible to make change
            }
            return Math.min(include, exclude);
        } else {
            int exclude = minCoinsRecursive(coins, amount, n - 1);
            if(exclude == Integer.MAX_VALUE - 1) {
                return -1; // not possible to make change
            }
            return exclude; // can't include this coin, so just return the result of excluding it
        }
    }

    static int minCoinsMemo(int[] coins, int amount, int n, int[][] dp) {
        
        if(amount == 0) {
            return 0; // no coins needed for 0 amount
        }

        if (n == 0) {
            return Integer.MAX_VALUE - 1; // can't make change with 0 coins
        }

        if (dp[n][amount] != -1) {
            return dp[n][amount]; // return already computed value
        }

        if (coins[n - 1] <= amount) {
            int include = 1 + minCoinsMemo(coins, amount - coins[n - 1], n, dp);
            int exclude = minCoinsMemo(coins, amount, n - 1, dp);
            dp[n][amount] = Math.min(include, exclude);
        } else {
            dp[n][amount] = minCoinsMemo(coins, amount, n - 1, dp);
        }

        if(dp[n][amount] == Integer.MAX_VALUE - 1) {
            return -1; // not possible to make change
        } else {
            return dp[n][amount]; // return the minimum number of coins needed
        }
    }

     
    public static void main(String[] args) {
        int[] coins = {4,6,7};
       // int amount = 3;
        int amount = 11; // Example amount to make change for
        int n = coins.length;

         
        int res = minCoinsRecursive(coins, amount, n);
        if (res == - 1) {
            System.out.println("Min Coins Recursive: Not possible to make change");
        } else {
            System.out.println("Min Coins Recursive: " + res);
        }

        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = -1; // Initialize dp array with -1 for memoization
            }
        }
        res = minCoinsMemo(coins, amount, n, dp);
        if (res == -1) {
            System.out.println("Min Coins Memoization: Not possible to make change");
        } else {
            System.out.println("Min Coins Memoization: " + res);
        }
 
    }
}