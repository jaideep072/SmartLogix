package utils;

import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

public class CSVManager {

    private static final String WAREHOUSE_FILE = "data/warehouses.csv";
    private static final String SHIPMENT_FILE = "data/shipments.csv";
    private static final String ORDER_FILE = "data/orders.csv";
    private static final String ROUTE_FILE = "data/routes.csv";
    private static final String INVENTORY_FILE = "data/inventory.csv";

    // ---------------- WAREHOUSES ----------------

    public static List<Warehouse> loadWarehouses() {
        List<Warehouse> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(WAREHOUSE_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");

                if (p.length >= 3) {
                    list.add(new Warehouse(
                            p[0],
                            p[1],
                            p[2]
                    ));
                }
            }

        } catch (Exception e) {
            System.out.println("Warehouse CSV not found.");
        }

        return list;
    }

    public static void saveWarehouse(Warehouse warehouse) {

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(WAREHOUSE_FILE, true))) {

            bw.write(
                    warehouse.getWarehouseId() + "," +
                    warehouse.getWarehouseName() + "," +
                    warehouse.getCity()
            );

            bw.newLine();

        } catch (Exception e) {
            System.out.println("Error saving warehouse.");
        }
    }

    // ---------------- SHIPMENTS ----------------

    public static List<Shipment> loadShipments() {

        List<Shipment> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(SHIPMENT_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] p = line.split(",");

                if (p.length >= 8) {

            list.add(new Shipment(
                    Integer.parseInt(p[0]),
                    p[1],
                    p[2],
                    LocalDate.parse(p[3]),
                    Integer.parseInt(p[4]),
                    Double.parseDouble(p[5]),
                    Double.parseDouble(p[6]),
                    p[7]
            ));
                }
            }

        } catch (Exception e) {
            System.out.println("Shipment CSV not found.");
        }

        return list;
    }

    public static void saveShipment(Shipment s) {

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(SHIPMENT_FILE, true))) {

            bw.write(
                    s.getShipmentId() + "," +
                    s.getSourceWarehouse() + "," +
                    s.getDestinationWarehouse() + "," +
                    s.getDeliveryDate() + "," +
                    s.getPriority() + "," +
                    s.getCost() + "," +
                    s.getWeight() + "," +
                    s.getStatus()
            );

            bw.newLine();

        } catch (Exception e) {
            System.out.println("Error saving shipment.");
        }
    }

    // ---------------- ORDERS ----------------

    public static List<Order> loadOrders() {

        List<Order> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ORDER_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] p = line.split(",");

                if (p.length >= 3) {

                    list.add(new Order(
                            Integer.parseInt(p[0]),
                            p[1],
                            Integer.parseInt(p[2])
                    ));
                }
            }

        } catch (Exception e) {
            System.out.println("Order CSV not found.");
        }

        return list;
    }

    public static void saveOrder(Order order) {

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(ORDER_FILE, true))) {

            bw.write(
                    order.getOrderId() + "," +
                    order.getCustomerName() + "," +
                    order.getShipmentId()
            );

            bw.newLine();

        } catch (Exception e) {
            System.out.println("Error saving order.");
        }
    }

    // ---------------- ROUTES ----------------

    public static List<Route> loadRoutes() {

        List<Route> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ROUTE_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] p = line.split(",");

                if (p.length >= 4) {

                    list.add(new Route(
                            p[0],
                            p[1],
                            Integer.parseInt(p[2]),
                            Integer.parseInt(p[3])
                    ));
                }
            }

        } catch (Exception e) {
            System.out.println("Route CSV not found.");
        }

        return list;
    }

    public static void saveRoute(Route route) {

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(ROUTE_FILE, true))) {

            bw.write(
                    route.getSourceWarehouse() + "," +
                    route.getDestinationWarehouse() + "," +
                    route.getDistance() + "," +
                    route.getTransportCost()
            );

            bw.newLine();

        } catch (Exception e) {
            System.out.println("Error saving route.");
        }
    }

    // ---------------- INVENTORY ----------------

    public static List<InventoryRecord> loadInventory() {

        List<InventoryRecord> list = new ArrayList<>();

        try (BufferedReader br =
                     new BufferedReader(new FileReader(INVENTORY_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] p = line.split(",");

                if (p.length >= 3) {

                    list.add(new InventoryRecord(
                            Integer.parseInt(p[0]),
                            p[1],
                            Integer.parseInt(p[2])
                    ));
                }
            }

        } catch (Exception e) {
            System.out.println("Inventory CSV not found.");
        }

        return list;
    }
}