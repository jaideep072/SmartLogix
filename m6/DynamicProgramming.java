package m6;

import java.util.*;

public class DynamicProgramming {

    // =====================================
    // Activity Selection
    // =====================================

    public void activitySelection(
            int[] start,
            int[] finish) {

        int n = start.length;

        System.out.println(
                "\nSelected Dispatches:");

        int last = 0;

        System.out.println(
                "(" + start[0] +
                        "," +
                        finish[0] + ")");

        for (int i = 1;
             i < n;
             i++) {

            if (start[i]
                    >= finish[last]) {

                System.out.println(
                        "(" +
                                start[i] +
                                "," +
                                finish[i] +
                                ")");

                last = i;
            }
        }
    }

    // =====================================
    // Fractional Knapsack
    // =====================================

    public double fractionalKnapsack(
            List<CargoItem> items,
            int capacity) {

        items.sort((a, b) -> Double.compare(
                (double) b.getProfit()
                        / b.getWeight(),

                (double) a.getProfit()
                        / a.getWeight()
        ));

        double totalProfit = 0;

        for (CargoItem item : items) {

            if (capacity
                    >= item.getWeight()) {

                capacity -=
                        item.getWeight();

                totalProfit +=
                        item.getProfit();
            }

            else {

                totalProfit +=
                        ((double) item.getProfit()
                                / item.getWeight())
                                * capacity;

                break;
            }
        }

        return totalProfit;
    }

    // =====================================
    // 0/1 Knapsack
    // =====================================

    public int zeroOneKnapsack(
            List<CargoItem> items,
            int capacity) {

        int n = items.size();

        int[][] dp =
                new int[n + 1]
                        [capacity + 1];

        for (int i = 1;
             i <= n;
             i++) {

            CargoItem item =
                    items.get(i - 1);

            for (int w = 0;
                 w <= capacity;
                 w++) {

                if (
                        item.getWeight()
                                <= w
                ) {

                    dp[i][w] =
                            Math.max(

                                    item.getProfit()
                                            +
                                            dp[i - 1]
                                                    [w - item.getWeight()],

                                    dp[i - 1][w]
                            );
                }

                else {

                    dp[i][w] =
                            dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity];
    }

    // =====================================
    // LIS
    // =====================================

    public int longestIncreasingSubsequence(
            int[] arr) {

        int n = arr.length;

        int[] lis =
                new int[n];

        Arrays.fill(lis, 1);

        int answer = 1;

        for (int i = 1;
             i < n;
             i++) {

            for (int j = 0;
                 j < i;
                 j++) {

                if (
                        arr[i] > arr[j]
                                &&
                                lis[i]
                                        <
                                        lis[j] + 1
                ) {

                    lis[i] =
                            lis[j] + 1;
                }
            }

            answer =
                    Math.max(
                            answer,
                            lis[i]);
        }

        return answer;
    }
}