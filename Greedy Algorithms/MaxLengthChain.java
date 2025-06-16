import java.util.*;

public class MaxLengthChain {
    static class Pair {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of pairs: ");
        int n = sc.nextInt();
        Pair[] pairs = new Pair[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter pair " + (i + 1) + ": ");
            pairs[i] = new Pair(sc.nextInt(), sc.nextInt());
        }

        Arrays.sort(pairs, Comparator.comparingInt(p -> p.b));
        int count = 1, last = pairs[0].b;

        for (int i = 1; i < n; i++) {
            if (pairs[i].a > last) {
                count++;
                last = pairs[i].b;
            }
        }

        System.out.println("Maximum length of chain = " + count);
    }
}
