package m1;

import models.InventoryRecord;
import models.Shipment;
import utils.CSVManager;
import utils.InputHelper;

import java.util.Scanner;

public class TreeStructures {

    public static void runMenu(Scanner sc) {

        while (true) {

            System.out.println("\n===== M1 MENU =====");
            System.out.println("1. BST Shipment Tracking");
            System.out.println("2. AVL Inventory Management");
            System.out.println("0. Back");

            String choice = sc.nextLine();

            switch (choice) {

                case "1":
                    bstMenu(sc);
                    break;

                case "2":
                    avlMenu(sc);
                    break;

                case "0":
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void bstMenu(Scanner sc) {

        BSTShipmentTracker bst =
                new BSTShipmentTracker();

        for (Shipment s :
                CSVManager.loadShipments()) {

            bst.insert(s);
        }

        while (true) {

            System.out.println("\n--- BST Shipment Tracking ---");
            System.out.println("1. Search Shipment");
            System.out.println("2. Display Sorted Shipments");
            System.out.println("3. Delete Shipment");
            System.out.println("0. Back");

            String choice = sc.nextLine();

            switch (choice) {

                case "1":

                    int id =
                            InputHelper.readInt(
                                    sc,
                                    "Shipment ID: "
                            );

                    Shipment s =
                            bst.search(id);

                    if (s == null)
                        System.out.println("Not found");
                    else
                        System.out.println(s);

                    break;

                case "2":
                    bst.displaySorted();
                    break;

                case "3":

                    int del =
                            InputHelper.readInt(
                                    sc,
                                    "Shipment ID: "
                            );

                    bst.delete(del);

                    System.out.println(
                            "Deleted from BST session."
                    );

                    break;

                case "0":
                    return;
            }
        }
    }

    private static void avlMenu(Scanner sc) {

        AVLInventoryManager avl =
                new AVLInventoryManager();

        for (InventoryRecord r :
                CSVManager.loadInventory()) {

            avl.insert(r);
        }

        while (true) {

            System.out.println("\n--- AVL Inventory ---");
            System.out.println("1. Search Product");
            System.out.println("2. Display Inventory");
            System.out.println("0. Back");

            String choice = sc.nextLine();

            switch (choice) {

                case "1":

                    int pid =
                            InputHelper.readInt(
                                    sc,
                                    "Product ID: "
                            );

                    InventoryRecord record =
                            avl.search(pid);

                    if (record == null)
                        System.out.println("Not found");
                    else
                        System.out.println(record);

                    break;

                case "2":

                    avl.displayInventory();

                    break;

                case "0":

                    return;
            }
        }
    }
}