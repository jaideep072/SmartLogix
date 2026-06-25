package utils;

import models.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String URL = "jdbc:postgresql://localhost:5432/smartlogix";
    private static final String USER = "postgres";
    private static final String PASSWORD = "cybersec123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ---------------- WAREHOUSES ----------------

    public static List<Warehouse> loadWarehouses() {
        List<Warehouse> list = new ArrayList<>();
        String query = "SELECT warehouse_id, warehouse_name, city FROM warehouses";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                list.add(new Warehouse(
                        rs.getString("warehouse_id"),
                        rs.getString("warehouse_name"),
                        rs.getString("city")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error loading warehouses from database: " + e.getMessage());
        }

        return list;
    }

    public static void saveWarehouse(Warehouse warehouse) {
        String sql = "INSERT INTO warehouses (warehouse_id, warehouse_name, city) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, warehouse.getWarehouseId());
            pstmt.setString(2, warehouse.getWarehouseName());
            pstmt.setString(3, warehouse.getCity());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error saving warehouse to database: " + e.getMessage());
        }
    }

    // ---------------- SHIPMENTS ----------------

    public static List<Shipment> loadShipments() {
        List<Shipment> list = new ArrayList<>();
        String query = "SELECT shipment_id, source_warehouse, destination_warehouse, delivery_date, priority, cost, weight, status FROM shipments";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                list.add(new Shipment(
                        rs.getInt("shipment_id"),
                        rs.getString("source_warehouse"),
                        rs.getString("destination_warehouse"),
                        rs.getDate("delivery_date").toLocalDate(),
                        rs.getInt("priority"),
                        rs.getDouble("cost"),
                        rs.getDouble("weight"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error loading shipments from database: " + e.getMessage());
        }

        return list;
    }

    public static void saveShipment(Shipment s) {
        String sql = "INSERT INTO shipments (shipment_id, source_warehouse, destination_warehouse, delivery_date, priority, cost, weight, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, s.getShipmentId());
            pstmt.setString(2, s.getSourceWarehouse());
            pstmt.setString(3, s.getDestinationWarehouse());
            pstmt.setDate(4, java.sql.Date.valueOf(s.getDeliveryDate()));
            pstmt.setInt(5, s.getPriority());
            pstmt.setDouble(6, s.getCost());
            pstmt.setDouble(7, s.getWeight());
            pstmt.setString(8, s.getStatus());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error saving shipment to database: " + e.getMessage());
        }
    }

    // ---------------- ORDERS ----------------

    public static List<Order> loadOrders() {
        List<Order> list = new ArrayList<>();
        String query = "SELECT order_id, customer_name, shipment_id FROM orders";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                list.add(new Order(
                        rs.getInt("order_id"),
                        rs.getString("customer_name"),
                        rs.getInt("shipment_id")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error loading orders from database: " + e.getMessage());
        }

        return list;
    }

    public static void saveOrder(Order order) {
        String sql = "INSERT INTO orders (order_id, customer_name, shipment_id) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, order.getOrderId());
            pstmt.setString(2, order.getCustomerName());
            pstmt.setInt(3, order.getShipmentId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error saving order to database: " + e.getMessage());
        }
    }

    // ---------------- ROUTES ----------------

    public static List<Route> loadRoutes() {
        List<Route> list = new ArrayList<>();
        String query = "SELECT source_warehouse, destination_warehouse, distance, transport_cost FROM routes";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                list.add(new Route(
                        rs.getString("source_warehouse"),
                        rs.getString("destination_warehouse"),
                        rs.getInt("distance"),
                        rs.getInt("transport_cost")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error loading routes from database: " + e.getMessage());
        }

        return list;
    }

    public static void saveRoute(Route route) {
        String sql = "INSERT INTO routes (source_warehouse, destination_warehouse, distance, transport_cost) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, route.getSourceWarehouse());
            pstmt.setString(2, route.getDestinationWarehouse());
            pstmt.setInt(3, route.getDistance());
            pstmt.setInt(4, route.getTransportCost());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error saving route to database: " + e.getMessage());
        }
    }

    // ---------------- INVENTORY ----------------

    public static List<InventoryRecord> loadInventory() {
        List<InventoryRecord> list = new ArrayList<>();
        String query = "SELECT product_id, product_name, quantity FROM inventory";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                list.add(new InventoryRecord(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("quantity")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error loading inventory from database: " + e.getMessage());
        }

        return list;
    }
<<<<<<< HEAD
=======

    public static void saveInventory(InventoryRecord record) {
        String sql = "INSERT INTO inventory (product_id, product_name, quantity) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, record.getProductId());
            pstmt.setString(2, record.getWarehouseId()); // The model names this warehouseId but it's actually the product name
            pstmt.setInt(3, record.getQuantity());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error saving inventory to database: " + e.getMessage());
        }
    }
>>>>>>> 443813a (Migrate application from CSV to PostgreSQL database)
}
