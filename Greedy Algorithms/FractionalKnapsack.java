import java.util.*;

public class FractionalKnapsack {
    static class Item {
        int weight, value;
        double ratio;

        Item(int v, int w) {
            value = v;
            weight = w;
            ratio = (double) v / w;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();
        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter value and weight of item " + (i + 1) + ": ");
            items[i] = new Item(sc.nextInt(), sc.nextInt());
        }

        System.out.print("Enter knapsack capacity: ");
        int capacity = sc.nextInt();

        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalValue = 0;
        for (Item item : items) {
            if (capacity == 0) break;
            if (item.weight <= capacity) {
                totalValue += item.value;
                capacity -= item.weight;
            } else {
                totalValue += item.ratio * capacity;
                capacity = 0;
            }
        }

        System.out.println("Maximum value in knapsack = " + totalValue);
    }
}
