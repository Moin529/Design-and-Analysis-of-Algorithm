import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();

        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();

        int[][] wall = new int[rows][cols];
        System.out.println("Enter the energy values of the wall:");
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                wall[i][j] = sc.nextInt();

        // Initialize memoization array
        int[][] memo = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                memo[i][j] = -1;

        // Calculate using recursive approach
        int maxEnergyRec = 0;
        for (int j = 0; j < cols; j++)
            maxEnergyRec = Math.max(maxEnergyRec, climbRec(wall, rows - 1, j, memo));

        // Calculate using DP
        int maxEnergyDP = climbDP(wall);

        System.out.println("rockClimbingRec: " + maxEnergyRec);
        System.out.println("rockClimbingDP: " + maxEnergyDP);
    }
}
