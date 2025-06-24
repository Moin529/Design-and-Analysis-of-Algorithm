import java.util.*;

public class Fibonacci{
//1, 1, 2, 3, 5, 8, 13, 21, 34, (55)
    //Top-Down Memoization
    public static int fibMemo(int n, HashMap<Integer, Integer> memo) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) return memo.get(n);
        int result = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    //Bottom-Up Tabulation
    public static int fibTab(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Give input 'n' : ");
        int n = sc.nextInt();
        System.out.println("fibMemo: " + fibMemo(n, new HashMap<>()));
        System.out.println("fibTab: " + fibTab(n));
    }
}