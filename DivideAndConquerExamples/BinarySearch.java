import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {
    public static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input array size
        System.out.print("Enter size of array: ");
        int size = sc.nextInt();

        // Input array elements
        int[] arr = new int[size];
        System.out.println("Enter " + size + " sorted elements:");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        // Input target element
        System.out.print("Enter number to search: ");
        int target = sc.nextInt();

        // Perform binary search
        int index = binarySearch(arr, target);

        // Output results
        System.out.println("Array: " + Arrays.toString(arr));
        if (index != -1) {
            System.out.println("Element " + target + " found at index: " + index);
        } else {
            System.out.println("Element " + target + " not found in the array.");
        }

        sc.close();
    }
}
