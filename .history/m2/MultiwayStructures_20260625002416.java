package m2;

import models.Order;
import models.Shipment;
import utils.CSVManager;
import utils.InputHelper;

import java.time.LocalDate;
import java.util.Scanner;

public class MultiwayStructures {

    public static void runMenu(
            Scanner sc) {

        while (true) {

            System.out.println(
                "\n===== M2 MENU =====");

            System.out.println(
                "1. B-Tree Order Search");

            System.out.println(
                "2. B+ Tree Date Range Query");

            System.out.println(
                "3. Segment Tree Analytics");

            System.out.println(
                "4. Fenwick Tree Statistics");

            System.out.println(
                "0. Back");

            String choice =
                    sc.nextLine();

            switch (choice) {

                case "1":
                    bTreeMenu(sc);
                    break;

                case "2":
                    bPlusMenu(sc);
                    break;

                case "3":
                    segmentMenu(sc);
                    break;

                case "4":
                    fenwickMenu(sc);
                    break;

                case "0":
                    return;
            }
        }
    }

    private static void bTreeMenu(
            Scanner sc) {

        BTreeOrderArchive tree =
                new BTreeOrderArchive();

        for (Order order :
                CSVManager.loadOrders()) {

            tree.insert(order);
        }

        int id =
                InputHelper.readInt(
                        sc,
                        "Order ID: ");

        Order result =
                tree.search(id);

        if (result == null)
            System.out.println("Not found");
        else
            System.out.println(result);
    }

    private static void bPlusMenu(
            Scanner sc) {

        BPlusDeliveryIndex tree =
                new BPlusDeliveryIndex();

        for (Shipment s :
                CSVManager.loadShipments()) {

            tree.insert(s);
        }

LocalDate start =
        LocalDate.parse(
                InputHelper.readString(
                        sc,
                        "Start Date (yyyy-MM-dd): "));

LocalDate end =
        LocalDate.parse(
                InputHelper.readString(
                        sc,
                        "End Date (yyyy-MM-dd): "));

        tree.rangeQuery(start, end);
    }

    private static void segmentMenu(
            Scanner sc) {

        int[] deliveries =
                {15,20,12,18,25,10,30};

        SegmentTreeAnalytics seg =
                new SegmentTreeAnalytics(
                        deliveries);

        int left =
                InputHelper.readInt(
                        sc,
                        "Start Day Index (0-6): ");

        int right =
                InputHelper.readInt(
                        sc,
                        "End Day Index (0-6): ");

        System.out.println(
                "Total Deliveries = "
                        + seg.query(
                        left,
                        right
                ));
    }

    private static void fenwickMenu(
            Scanner sc) {

        FenwickInventoryStats bit =
                new FenwickInventoryStats(7);

        int[] movement =
                {5,7,3,9,6,4,8};

        for (int i = 0;
             i < movement.length;
             i++) {

            bit.update(
                    i + 1,
                    movement[i]
            );
        }

        int day =
                InputHelper.readInt(
                        sc,
                        "Day (1-7): ");

        System.out.println(
                "Prefix Inventory Movement = "
                        + bit.prefixSum(day)
        );
    }
}