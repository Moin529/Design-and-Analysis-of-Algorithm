import java.util.Scanner;

public class RockClimbingMin {

    // Recursive with memoization (min version)
    public static int climbRecMin(int[][] wall, int row, int col, int[][] memo) {
        if (row < 0 || col < 0 || col >= wall[0].length)
            return Integer.MAX_VALUE; // Invalid move
        if (row == 0) return wall[0][col]; // Base case: top row
        if (memo[row][col] != -1) return memo[row][col];

        int minPrev = Math.min(
            climbRecMin(wall, row - 1, col, memo),
            Math.min(
                climbRecMin(wall, row - 1, col - 1, memo),
                climbRecMin(wall, row - 1, col + 1, memo)
            )
        );

        memo[row][col] = wall[row][col] + (minPrev == Integer.MAX_VALUE ? 0 : minPrev);
        return memo[row][col];
    }

    // DP bottom-up (min version)
    public static int climbDPMin(int[][] wall) {
        int rows = wall.length, cols = wall[0].length;
        int[][] dp = new int[rows][cols];

        // Initialize top row
        for (int j = 0; j < cols; j++)
            dp[0][j] = wall[0][j];

        // Fill from top to bottom
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int minPrev = dp[i - 1][j];
                if (j > 0) minPrev = Math.min(minPrev, dp[i - 1][j - 1]);
                if (j < cols - 1) minPrev = Math.min(minPrev, dp[i - 1][j + 1]);
                dp[i][j] = wall[i][j] + minPrev;
            }
        }

        int minEnergy = Integer.MAX_VALUE;
        for (int j = 0; j < cols; j++)
            minEnergy = Math.min(minEnergy, dp[rows - 1][j]);

        return minEnergy;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input
        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();

        int[][] wall = new int[rows][cols];
        System.out.println("Enter the energy values of the wall:");
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                wall[i][j] = sc.nextInt();

        // Memo table initialization
        int[][] memo = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                memo[i][j] = -1;

        // Recursive solution
        int minEnergyRec = Integer.MAX_VALUE;
        for (int j = 0; j < cols; j++)
            minEnergyRec = Math.min(minEnergyRec, climbRecMin(wall, rows - 1, j, memo));

        // DP solution
        int minEnergyDP = climbDPMin(wall);

        // Output
        System.out.println("rockClimbingRec (min): " + minEnergyRec);
        System.out.println("rockClimbingDP (min): " + minEnergyDP);
    }
}
