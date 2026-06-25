package m5;

import models.Shipment;
import utils.CSVManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdvancedSorting {

    public static void runMenu(
            Scanner sc) {

        while (true) {

            System.out.println(
                    "\n===== M5 MENU =====");

            System.out.println(
                    "1. Merge Sort by Delivery Date");

            System.out.println(
                    "2. Quick Sort by Cost");

            System.out.println(
                    "3. Heap Sort by Priority");

            System.out.println(
                    "4. Radix Sort Shipment IDs");

            System.out.println(
                    "0. Back");

            String choice =
                    sc.nextLine();

            SortingAlgorithms sort =
                    new SortingAlgorithms();

            List<Shipment> shipments =
                    new ArrayList<>(
                            CSVManager.loadShipments());

            switch (choice) {

                case "1":

                    sort.mergeSortByDate(
                            shipments);

                    shipments.forEach(
                            System.out::println);

                    break;

                case "2":

                    sort.quickSortByCost(
                            shipments,
                            0,
                            shipments.size() - 1);

                    shipments.forEach(
                            System.out::println);

                    break;

                case "3":

                    sort.heapSortByPriority(
                            shipments);

                    shipments.forEach(
                            System.out::println);

                    break;

                case "4":

                    int[] ids =
                            shipments.stream()
                                    .mapToInt(
                                            Shipment::getShipmentId)
                                    .toArray();

                    sort.radixSortIds(ids);

                    for (int id : ids)
                        System.out.println(id);

                    break;

                case "0":

                    return;
            }
        }
    }
}