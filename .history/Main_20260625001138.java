
import models.*;
import models.Order;
import utils.CSVManager;
import utils.InputHelper;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import m1.TreeStructures;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            printMenu();

            String choice = sc.nextLine().trim();

            switch (choice) {

                case "1":
                    addWarehouse();
                    break;

                case "2":
                    addShipment();
                    break;

                case "3":
                    addOrder();
                    break;

                case "4":
                    addRoute();
                    break;

                case "5":
                    viewData();
                    break;

                case "6":
                    m1.TreeStructures.runMenu(sc);
                    break;

                case "7":
                    System.out.println("M2 coming next phase...");
                    break;

                case "8":
                    System.out.println("M3 coming next phase...");
                    break;

                case "9":
                    System.out.println("M4 coming next phase...");
                    break;

                case "10":
                    System.out.println("M5 coming next phase...");
                    break;

                case "11":
                    System.out.println("M6 coming next phase...");
                    break;

                case "12":
                    System.out.println("CSV data reloaded.");
                    break;

                case "0":
                    System.out.println("Thank you for using SmartLogix.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void printMenu() {

        System.out.println("\n========== SmartLogix Main Menu ==========");
        System.out.println("1. Add Warehouse");
        System.out.println("2. Add Shipment");
        System.out.println("3. Add Order");
        System.out.println("4. Add Route");
        System.out.println("5. View Logistics Data");
        System.out.println("6. M1 - Trees and Balanced Search Structures");
        System.out.println("7. M2 - Multiway Trees and Range Query Structures");
        System.out.println("8. M3 - Graph Algorithms");
        System.out.println("9. M4 - Shortest Path Optimization");
        System.out.println("10. M5 - Advanced Sorting");
        System.out.println("11. M6 - Greedy and Dynamic Programming");
        System.out.println("12. Reload CSV Data");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    static void addWarehouse() {

        String id = InputHelper.readString(sc, "Warehouse ID: ");
        String name = InputHelper.readString(sc, "Warehouse Name: ");
        String city = InputHelper.readString(sc, "City: ");

        CSVManager.saveWarehouse(
                new Warehouse(id, name, city)
        );

        System.out.println("Warehouse added.");
    }

static void addShipment() {

    int id = InputHelper.readInt(sc, "Shipment ID: ");
    String src = InputHelper.readString(sc, "Source Warehouse: ");
    String dest = InputHelper.readString(sc, "Destination Warehouse: ");
    String date = InputHelper.readString(sc, "Delivery Date (yyyy-MM-dd): ");
    int priority = InputHelper.readInt(sc, "Priority: ");
    double cost = InputHelper.readDouble(sc, "Cost: ");
    double weight = InputHelper.readDouble(sc, "Weight: ");
    String status = InputHelper.readString(sc, "Status: ");

    CSVManager.saveShipment(
            new Shipment(
                    id,
                    src,
                    dest,
                    LocalDate.parse(date),
                    priority,
                    cost,
                    weight,
                    status
            )
    );

    System.out.println("Shipment added.");
}

    static void addOrder() {

        int id = InputHelper.readInt(sc, "Order ID: ");
        String customer = InputHelper.readString(sc, "Customer Name: ");
        int shipmentId = InputHelper.readInt(sc, "Shipment ID: ");

        CSVManager.saveOrder(
                new Order(id, customer, shipmentId)
        );

        System.out.println("Order added.");
    }

    static void addRoute() {

        String src = InputHelper.readString(sc, "Source Warehouse: ");
        String dest = InputHelper.readString(sc, "Destination Warehouse: ");
        int distance = InputHelper.readInt(sc, "Distance: ");
        int cost = InputHelper.readInt(sc, "Transport Cost: ");

        CSVManager.saveRoute(
                new Route(src, dest, distance, cost)
        );

        System.out.println("Route added.");
    }

    static void viewData() {

        System.out.println("\nWAREHOUSES");
        List<Warehouse> warehouses = CSVManager.loadWarehouses();
        warehouses.forEach(System.out::println);

        System.out.println("\nSHIPMENTS");
        List<Shipment> shipments = CSVManager.loadShipments();
        shipments.forEach(System.out::println);

        System.out.println("\nORDERS");
        List<Order> orders = CSVManager.loadOrders();
        orders.forEach(System.out::println);

        System.out.println("\nROUTES");
        List<Route> routes = CSVManager.loadRoutes();
        routes.forEach(System.out::println);
    }
}