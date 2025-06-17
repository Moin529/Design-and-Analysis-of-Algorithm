import java.util.*;

public class FractionalKnapsack {
    static class item {
        int value;
        int weight;
        double ratio;

        item(int v, int w) {
            value = v;
            weight = w;
            ratio = (double) v / w;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        item[] items = new item[N];

        for (int i = 0; i < N; i++) {
            items[i] = new item(sc.nextInt(), sc.nextInt());
        }

        int capacity = sc.nextInt();

        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        int totalValue = 0;
        for (item i : items) {
            if (capacity == 0)
                break;
            if (i.weight <= capacity) {
                totalValue += i.value;
                capacity -= i.weight;
            } else {
                totalValue += i.ratio * capacity;
                capacity = 0;
            }
        }
        System.out.println(totalValue);
    }
}