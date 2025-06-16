import java.util.*;

public class MinAbsDifferencePairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first array: ");
        int[] A = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.print("Enter second array: ");
        int[] B = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(A);
        Arrays.sort(B);

        int sum = 0;
        for (int i = 0; i < A.length; i++)
            sum += Math.abs(A[i] - B[i]);

        System.out.println("Minimum sum of absolute differences = " + sum);
    }
}
