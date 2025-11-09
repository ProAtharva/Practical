import java.util.*;

class Item {
    int value;
    int weight;

    Item(int v, int w) {
        value = v;
        weight = w;
    }

    double ratio() {
        return (double) value / weight;
    }
}

public class FractionalKnapsackExplained {

    static double fractionalKnapsack(int[] values, int[] weights, int W) {
        int n = values.length;
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < n; i++)
            items.add(new Item(values[i], weights[i]));

        items.sort((a, b) -> Double.compare(b.ratio(), a.ratio()));

        double totalValue = 0.0;
        int usedWeight = 0;

        for (Item it : items) {
            if (usedWeight + it.weight <= W) {
                usedWeight += it.weight;
                totalValue += it.value;
            } else {
                int remain = W - usedWeight;
                totalValue += it.ratio() * remain;
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        int[] weights = {2, 3, 4, 7, 8};
        int[] values  = {1, 2, 3, 4, 5};
        int capacity  = 5;

        double ans = fractionalKnapsack(values, weights, capacity);
        System.out.printf("Maximum value = %.2f%n", ans);
    }
}
