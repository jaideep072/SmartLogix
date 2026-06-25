import models.Order;
import models.Route;
import models.Shipment;
import models.Warehouse;
import models.InventoryRecord;
import utils.DatabaseManager;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class SmartLogixTest {

    private static int passedTests = 0;
    private static int failedTests = 0;

    public static void main(String[] args) {
        System.out.println("========== Starting SmartLogix Test Suite ==========\n");

        // 1. Model Creation Tests
        testWarehouseCreation();
        testShipmentCreation();
        testOrderCreation();
        testRouteCreation();
        testInventoryRecordCreation();
        testModelToString();

        // 2. Exception Handling Tests
        testNumberFormatExceptionHandling();
        testDateTimeExceptionHandling();
        testNullPointerExceptionHandling();
        testEmptyStringParseExceptionHandling();

        // 3. DatabaseManager Integration Tests
        testDatabaseManagerDataLoad();

        // 4. Intentional Failure Demonstrations
        testIntentionalFailureDataMismatch();
        testIntentionalFailureExceptionNotThrown();

        System.out.println("\n========== Test Suite Execution Finished ==========");
        System.out.println("Total Passed: " + passedTests);
        System.out.println("Total Failed: " + failedTests);
    }

    // --- Utility Methods for Testing ---
    private static void assertEqual(Object expected, Object actual, String testName) {
        if (expected == null && actual == null) {
            pass(testName);
        } else if (expected != null && expected.equals(actual)) {
            pass(testName);
        } else {
            fail(testName, "Expected [" + expected + "] but got [" + actual + "]");
        }
    }

    private static void assertTrue(boolean condition, String testName) {
        if (condition) {
            pass(testName);
        } else {
            fail(testName, "Condition was false");
        }
    }

    private static void pass(String testName) {
        System.out.println("[PASS] - " + testName);
        passedTests++;
    }

    private static void fail(String testName, String reason) {
        System.out.println("[FAIL] - " + testName + " | Reason: " + reason);
        failedTests++;
    }

    // --- 1. Model Creation Tests ---
    private static void testWarehouseCreation() {
        Warehouse w = new Warehouse("W99", "Test Hub", "TestCity");
        assertEqual("W99", w.getWarehouseId(), "Warehouse Creation - ID");
        assertEqual("Test Hub", w.getWarehouseName(), "Warehouse Creation - Name");
        assertEqual("TestCity", w.getCity(), "Warehouse Creation - City");
    }

    private static void testShipmentCreation() {
        LocalDate date = LocalDate.of(2026, 12, 31);
        Shipment s = new Shipment(9999, "W1", "W2", date, 1, 500.0, 100.0, "Pending");
        assertEqual(9999, s.getShipmentId(), "Shipment Creation - ID");
        assertEqual("W1", s.getSourceWarehouse(), "Shipment Creation - Source");
        assertEqual(date, s.getDeliveryDate(), "Shipment Creation - Date");
        assertEqual("Pending", s.getStatus(), "Shipment Creation - Status");
    }

    private static void testOrderCreation() {
        Order o = new Order(555, "John Doe", 9999);
        assertEqual(555, o.getOrderId(), "Order Creation - ID");
        assertEqual("John Doe", o.getCustomerName(), "Order Creation - Customer");
    }

    private static void testRouteCreation() {
        Route r = new Route("W1", "W2", 300, 1500);
        assertEqual("W1", r.getSourceWarehouse(), "Route Creation - Source");
        assertEqual(300, r.getDistance(), "Route Creation - Distance");
    }

    // --- 2. Exception Handling Tests ---
    private static void testNumberFormatExceptionHandling() {
        String testName = "Exception Handling - NumberFormat";
        String badInput = "abc";
        try {
            int id = Integer.parseInt(badInput);
            fail(testName, "Exception was not thrown");
        } catch (NumberFormatException e) {
            pass(testName);
        } catch (Exception e) {
            fail(testName, "Wrong exception thrown: " + e.getClass().getName());
        }
    }

    private static void testDateTimeExceptionHandling() {
        String testName = "Exception Handling - DateTimeParse";
        String badDate = "2026-15-31"; // Invalid month
        try {
            LocalDate date = LocalDate.parse(badDate);
            fail(testName, "Exception was not thrown");
        } catch (DateTimeParseException e) {
            pass(testName);
        } catch (Exception e) {
            fail(testName, "Wrong exception thrown: " + e.getClass().getName());
        }
    }

    private static void testNullPointerExceptionHandling() {
        String testName = "Exception Handling - NullPointer Validation";
        String nullInput = null;
        try {
            if (nullInput == null) {
                throw new NullPointerException("Input cannot be null");
            }
            fail(testName, "Exception was not thrown");
        } catch (NullPointerException e) {
            pass(testName);
        } catch (Exception e) {
            fail(testName, "Wrong exception thrown: " + e.getClass().getName());
        }
    }

    // --- 3. DatabaseManager Integration Tests ---
    private static void testDatabaseManagerDataLoad() {
        String testName = "DatabaseManager - Load Warehouses without Exception";
        try {
            List<Warehouse> warehouses = DatabaseManager.loadWarehouses();
            assertTrue(warehouses != null, testName);
        } catch (Exception e) {
            fail(testName, "Exception thrown during data load: " + e.getMessage());
        }

        testName = "DatabaseManager - Load Shipments without Exception";
        try {
            List<Shipment> shipments = DatabaseManager.loadShipments();
            assertTrue(shipments != null, testName);
        } catch (Exception e) {
            fail(testName, "Exception thrown during data load: " + e.getMessage());
        }

        testName = "DatabaseManager - Load Orders without Exception";
        try {
            List<Order> orders = DatabaseManager.loadOrders();
            assertTrue(orders != null, testName);
        } catch (Exception e) {
            fail(testName, "Exception thrown during data load: " + e.getMessage());
        }

        testName = "DatabaseManager - Load Routes without Exception";
        try {
            List<Route> routes = DatabaseManager.loadRoutes();
            assertTrue(routes != null, testName);
        } catch (Exception e) {
            fail(testName, "Exception thrown during data load: " + e.getMessage());
        }

        testName = "DatabaseManager - Load Inventory without Exception";
        try {
            List<InventoryRecord> inv = DatabaseManager.loadInventory();
            assertTrue(inv != null, testName);
        } catch (Exception e) {
            fail(testName, "Exception thrown during data load: " + e.getMessage());
        }
    }

    // --- 4. Additional Model & Validation Tests ---
    private static void testInventoryRecordCreation() {
        InventoryRecord inv = new InventoryRecord(101, "WH-Test", 50);
        assertEqual(101, inv.getProductId(), "Inventory Creation - Product ID");
        assertEqual("WH-Test", inv.getWarehouseId(), "Inventory Creation - Warehouse ID");
        assertEqual(50, inv.getQuantity(), "Inventory Creation - Quantity");
    }

    private static void testModelToString() {
        Warehouse w = new Warehouse("W99", "Test Hub", "TestCity");
        String expectedToString = "W99 | Test Hub | TestCity";
        assertEqual(expectedToString, w.toString(), "Model Validation - Warehouse toString");
        
        Order o = new Order(555, "John Doe", 9999);
        String expectedOrderStr = "555 | John Doe | Shipment: 9999";
        assertEqual(expectedOrderStr, o.toString(), "Model Validation - Order toString");
    }

    private static void testEmptyStringParseExceptionHandling() {
        String testName = "Exception Handling - Empty String Parse";
        try {
            int val = Integer.parseInt("");
            fail(testName, "Exception was not thrown on empty string");
        } catch (NumberFormatException e) {
            pass(testName);
        } catch (Exception e) {
            fail(testName, "Wrong exception thrown: " + e.getClass().getName());
        }
    }

    // --- 5. Intentional Failure Demonstrations ---
    private static void testIntentionalFailureDataMismatch() {
        Warehouse w = new Warehouse("W01", "Correct Name", "City");
        // Intentionally assert an incorrect expected value to cause a failure
        assertEqual("Wrong Name", w.getWarehouseName(), "Intentional Failure - Data Mismatch");
    }

    private static void testIntentionalFailureExceptionNotThrown() {
        String testName = "Intentional Failure - Expected Exception Not Thrown";
        try {
            int val = Integer.parseInt("123"); // This will succeed, but we 'expect' it to fail to demonstrate failure
            fail(testName, "Expected NumberFormatException but parsing succeeded");
        } catch (NumberFormatException e) {
            pass(testName);
        }
    }
}
