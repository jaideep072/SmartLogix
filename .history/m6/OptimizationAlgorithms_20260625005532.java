package m6;

import models.Shipment;
import utils.CSVManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OptimizationAlgorithms {

    public static void runMenu(
            Scanner sc) {

        DynamicProgramming dp =
                new DynamicProgramming();

        while (true) {

            System.out.println(
                    "\n===== M6 MENU =====");

            System.out.println(
                    "1. Activity Selection (Vehicle Dispatch Scheduling)");

            System.out.println(
                    "2. Fractional Knapsack (Partial Cargo Loading)");

            System.out.println(
                    "3. 0/1 Knapsack (Truck Loading)");

            System.out.println(
                    "4. Longest Increasing Subsequence (Shipment Growth Trend)");

            System.out.println(
                    "0. Back");

            String choice =
                    sc.nextLine();

            switch (choice) {

                case "1":

                    System.out.println(
                            "\nVehicle Dispatch Scheduling");

                    int[] start =
                            {1, 2, 4, 6, 8};

                    int[] finish =
                            {3, 5, 6, 8, 10};

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

                default:

                    System.out.println(
                            "Invalid Choice");
            }
        }
    }

    private static void fractionalDemo(
            DynamicProgramming dp) {

        List<CargoItem> items =
                new ArrayList<>();

        List<Shipment> shipments =
                CSVManager.loadShipments();

        for (Shipment s : shipments) {

            items.add(
                    new CargoItem(
                            "Shipment-" +
                                    s.getShipmentId(),

                            (int) s.getWeight(),

                            (int) s.getCost()
                    )
            );
        }

        double result =
                dp.fractionalKnapsack(
                        items,
                        600
                );

        System.out.println(
                "\nTruck Capacity = 600 kg");

        System.out.println(
                "Maximum Cargo Profit = "
                        + result);
    }

    private static void zeroOneDemo(
            DynamicProgramming dp) {

        List<CargoItem> items =
                new ArrayList<>();

        List<Shipment> shipments =
                CSVManager.loadShipments();

        for (Shipment s : shipments) {

            items.add(
                    new CargoItem(
                            "Shipment-" +
                                    s.getShipmentId(),

                            (int) s.getWeight(),

                            (int) s.getCost()
                    )
            );
        }

        int result =
                dp.zeroOneKnapsack(
                        items,
                        600
                );

        System.out.println(
                "\nTruck Capacity = 600 kg");

        System.out.println(
                "Maximum Profit = "
                        + result);
    }

    private static void lisDemo(
            DynamicProgramming dp) {

        int[] monthlyVolume =
                {100, 120, 90, 140, 180, 160, 220};

        int result =
                dp.longestIncreasingSubsequence(
                        monthlyVolume);

        System.out.println(
                "\nMonthly Shipment Volumes:");

        for (int volume :
                monthlyVolume) {

            System.out.print(
                    volume + " ");
        }

        System.out.println();

        System.out.println(
                "Shipment Growth Trend Length = "
                        + result);
    }
}11