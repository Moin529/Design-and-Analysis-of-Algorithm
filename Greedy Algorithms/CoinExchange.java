import java.util.*;

public class CoinExchange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter coin denominations (space-separated): ");
        String[] input = sc.nextLine().split(" ");
        int[] coins = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(coins);
        System.out.print("Enter amount: ");
        int amount = sc.nextInt();

        List<Integer> result = new ArrayList<>();
        for (int i = coins.length - 1; i >= 0 && amount > 0; i--) {
            while (amount >= coins[i]) {
                amount -= coins[i];
                result.add(coins[i]);
            }
        }

        if (amount > 0)
            System.out.println("Cannot make exact change.");
        else
            System.out.println("Coins used: " + result);
    }
}
