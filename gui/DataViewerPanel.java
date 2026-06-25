package gui;

import models.Order;
import models.Route;
import models.Shipment;
import models.Warehouse;
import utils.CSVManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DataViewerPanel extends JPanel {
    private JTabbedPane tabbedPane;

    public DataViewerPanel() {
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 18));

        refreshData();

        add(tabbedPane, BorderLayout.CENTER);
    }

    public void refreshData() {
        tabbedPane.removeAll();
        tabbedPane.addTab("Warehouses", createWarehousePanel());
        tabbedPane.addTab("Shipments", createShipmentPanel());
        tabbedPane.addTab("Orders", createOrderPanel());
        tabbedPane.addTab("Routes", createRoutePanel());
    }

    private void formatTable(JTable table) {
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setPreferredSize(new Dimension(table.getColumnModel().getTotalColumnWidth(), 40));
    }

    private JScrollPane createWarehousePanel() {
        String[] columns = {"Warehouse ID", "Name", "City"};
        List<Warehouse> warehouses = CSVManager.loadWarehouses();
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Warehouse w : warehouses) {
            model.addRow(new Object[]{w.getWarehouseId(), w.getWarehouseName(), w.getCity()});
        }
        JTable table = new JTable(model);
        formatTable(table);
        return new JScrollPane(table);
    }

    private JScrollPane createShipmentPanel() {
        String[] columns = {"ID", "Source", "Destination", "Date", "Priority", "Cost", "Weight", "Status"};
        List<Shipment> shipments = CSVManager.loadShipments();
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Shipment s : shipments) {
            model.addRow(new Object[]{
                    s.getShipmentId(), s.getSourceWarehouse(), s.getDestinationWarehouse(),
                    s.getDeliveryDate(), s.getPriority(), s.getCost(), s.getWeight(), s.getStatus()
            });
        }
        JTable table = new JTable(model);
        formatTable(table);
        return new JScrollPane(table);
    }

    private JScrollPane createOrderPanel() {
        String[] columns = {"Order ID", "Customer Name", "Shipment ID"};
        List<Order> orders = CSVManager.loadOrders();
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Order o : orders) {
            model.addRow(new Object[]{o.getOrderId(), o.getCustomerName(), o.getShipmentId()});
        }
        JTable table = new JTable(model);
        formatTable(table);
        return new JScrollPane(table);
    }

    private JScrollPane createRoutePanel() {
        String[] columns = {"Source", "Destination", "Distance", "Transport Cost"};
        List<Route> routes = CSVManager.loadRoutes();
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Route r : routes) {
            model.addRow(new Object[]{
                    r.getSourceWarehouse(), r.getDestinationWarehouse(), r.getDistance(), r.getTransportCost()
            });
        }
        JTable table = new JTable(model);
        formatTable(table);
        return new JScrollPane(table);
    }
}
