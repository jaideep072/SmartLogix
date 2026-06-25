package m6;

import java.util.*;

public class OptimizationAlgorithms {

    public static void runMenu(
            Scanner sc) {

        DynamicProgramming dp =
                new DynamicProgramming();

        while (true) {

            System.out.println(
                    "\n===== M6 MENU =====");

            System.out.println(
                    "1. Activity Selection");

            System.out.println(
                    "2. Fractional Knapsack");

            System.out.println(
                    "3. 0/1 Knapsack");

            System.out.println(
                    "4. Longest Increasing Subsequence");

            System.out.println(
                    "0. Back");

            String choice =
                    sc.nextLine();

            switch (choice) {

                case "1":

                    int[] start =
                            {1,3,0,5,8,5};

                    int[] finish =
                            {2,4,6,7,9,9};

                    dp.activitySelection(
                            start,
                            finish);

                    break;

                case "2":

                    fractionalDemo(dp);

                    break;

                case "3":

                    zeroOneDemo(dp);

                    break;

                case "4":

                    lisDemo(dp);

                    break;

                case "0":

                    return;
            }
        }
    }

    private static void fractionalDemo(
            DynamicProgramming dp) {

        List<CargoItem> items =
                new ArrayList<>();

        items.add(
                new CargoItem(
                        "Cargo-A",
                        10,
                        60));

        items.add(
                new CargoItem(
                        "Cargo-B",
                        20,
                        100));

        items.add(
                new CargoItem(
                        "Cargo-C",
                        30,
                        120));

        double result =
                dp.fractionalKnapsack(
                        items,
                        50);

        System.out.println(
                "Maximum Profit = "
                        + result);
    }

    private static void zeroOneDemo(
            DynamicProgramming dp) {

        List<CargoItem> items =
                new ArrayList<>();

        items.add(
                new CargoItem(
                        "Cargo-A",
                        10,
                        60));

        items.add(
                new CargoItem(
                        "Cargo-B",
                        20,
                        100));

        items.add(
                new CargoItem(
                        "Cargo-C",
                        30,
                        120));

        int result =
                dp.zeroOneKnapsack(
                        items,
                        50);

        System.out.println(
                "Maximum Profit = "
                        + result);
    }

    private static void lisDemo(
            DynamicProgramming dp) {

        int[] shipments =
                {5,7,3,8,10,12,4,15};

        int result =
                dp.longestIncreasingSubsequence(
                        shipments);

        System.out.println(
                "LIS Length = "
                        + result);
    }
}