import models.*;
import utils.DatabaseManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

public class Migration {
    public static void main(String[] args) {
        System.out.println("Starting Data Migration from CSV to PostgreSQL...");

        migrateWarehouses();
        migrateShipments();
        migrateOrders();
        migrateRoutes();
        migrateInventory();

        System.out.println("✅ Migration Complete! Check pgAdmin to see your data.");
    }

    private static void migrateWarehouses() {
        try (BufferedReader br = new BufferedReader(new FileReader("data/warehouses.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 3) {
                    DatabaseManager.saveWarehouse(new Warehouse(p[0].trim(), p[1].trim(), p[2].trim()));
                }
            }
            System.out.println("Warehouses migrated.");
        } catch (Exception e) {
            System.out.println("Error migrating warehouses: " + e.getMessage());
        }
    }

    private static void migrateShipments() {
        try (BufferedReader br = new BufferedReader(new FileReader("data/shipments.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 8) {
                    DatabaseManager.saveShipment(new Shipment(
                            Integer.parseInt(p[0].trim()), p[1].trim(), p[2].trim(),
                            LocalDate.parse(p[3].trim()), Integer.parseInt(p[4].trim()),
                            Double.parseDouble(p[5].trim()), Double.parseDouble(p[6].trim()), p[7].trim()
                    ));
                }
            }
            System.out.println("Shipments migrated.");
        } catch (Exception e) {
            System.out.println("Error migrating shipments: " + e.getMessage());
        }
    }

    private static void migrateOrders() {
        try (BufferedReader br = new BufferedReader(new FileReader("data/orders.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 3) {
                    DatabaseManager.saveOrder(new Order(
                            Integer.parseInt(p[0].trim()), p[1].trim(), Integer.parseInt(p[2].trim())
                    ));
                }
            }
            System.out.println("Orders migrated.");
        } catch (Exception e) {
            System.out.println("Error migrating orders: " + e.getMessage());
        }
    }

    private static void migrateRoutes() {
        try (BufferedReader br = new BufferedReader(new FileReader("data/routes.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 4) {
                    DatabaseManager.saveRoute(new Route(
                            p[0].trim(), p[1].trim(), Integer.parseInt(p[2].trim()), Integer.parseInt(p[3].trim())
                    ));
                }
            }
            System.out.println("Routes migrated.");
        } catch (Exception e) {
            System.out.println("Error migrating routes: " + e.getMessage());
        }
    }

    private static void migrateInventory() {
        try (BufferedReader br = new BufferedReader(new FileReader("data/inventory.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 3) {
                    DatabaseManager.saveInventory(new InventoryRecord(
                            Integer.parseInt(p[0].trim()), p[1].trim(), Integer.parseInt(p[2].trim())
                    ));
                }
            }
            System.out.println("Inventory migrated.");
        } catch (Exception e) {
            System.out.println("Error migrating inventory: " + e.getMessage());
        }
    }
}
