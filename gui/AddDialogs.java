package gui;

import models.Order;
import models.Route;
import models.Shipment;
import models.Warehouse;
import utils.DatabaseManager;

import javax.swing.*;
import java.time.LocalDate;

public class AddDialogs {

    public static void showAddWarehouseDialog(JFrame parent, DataViewerPanel viewerPanel) {
        JTextField idField = new JTextField(10);
        JTextField nameField = new JTextField(15);
        JTextField cityField = new JTextField(15);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Warehouse ID:"));
        panel.add(idField);
        panel.add(new JLabel("Warehouse Name:"));
        panel.add(nameField);
        panel.add(new JLabel("City:"));
        panel.add(cityField);

        int result = JOptionPane.showConfirmDialog(parent, panel, 
                 "Add Warehouse", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Warehouse w = new Warehouse(idField.getText().trim(), nameField.getText().trim(), cityField.getText().trim());
                DatabaseManager.saveWarehouse(w);
                JOptionPane.showMessageDialog(parent, "Warehouse added successfully!");
                if (viewerPanel != null) viewerPanel.refreshData();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parent, "Error adding warehouse: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void showAddShipmentDialog(JFrame parent, DataViewerPanel viewerPanel) {
        JTextField idField = new JTextField(10);
        JTextField srcField = new JTextField(15);
        JTextField destField = new JTextField(15);
        JTextField dateField = new JTextField(10);
        dateField.setText(LocalDate.now().toString());
        JTextField priorityField = new JTextField(5);
        JTextField costField = new JTextField(8);
        JTextField weightField = new JTextField(8);
        JTextField statusField = new JTextField(10);
        statusField.setText("Pending");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Shipment ID:")); panel.add(idField);
        panel.add(new JLabel("Source Warehouse:")); panel.add(srcField);
        panel.add(new JLabel("Destination Warehouse:")); panel.add(destField);
        panel.add(new JLabel("Delivery Date (yyyy-MM-dd):")); panel.add(dateField);
        panel.add(new JLabel("Priority:")); panel.add(priorityField);
        panel.add(new JLabel("Cost:")); panel.add(costField);
        panel.add(new JLabel("Weight:")); panel.add(weightField);
        panel.add(new JLabel("Status:")); panel.add(statusField);

        int result = JOptionPane.showConfirmDialog(parent, panel, 
                 "Add Shipment", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Shipment s = new Shipment(
                        Integer.parseInt(idField.getText().trim()),
                        srcField.getText().trim(),
                        destField.getText().trim(),
                        LocalDate.parse(dateField.getText().trim()),
                        Integer.parseInt(priorityField.getText().trim()),
                        Double.parseDouble(costField.getText().trim()),
                        Double.parseDouble(weightField.getText().trim()),
                        statusField.getText().trim()
                );
                DatabaseManager.saveShipment(s);
                JOptionPane.showMessageDialog(parent, "Shipment added successfully!");
                if (viewerPanel != null) viewerPanel.refreshData();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parent, "Error adding shipment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void showAddOrderDialog(JFrame parent, DataViewerPanel viewerPanel) {
        JTextField idField = new JTextField(10);
        JTextField customerField = new JTextField(15);
        JTextField shipmentIdField = new JTextField(10);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Order ID:")); panel.add(idField);
        panel.add(new JLabel("Customer Name:")); panel.add(customerField);
        panel.add(new JLabel("Shipment ID:")); panel.add(shipmentIdField);

        int result = JOptionPane.showConfirmDialog(parent, panel, 
                 "Add Order", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Order o = new Order(
                        Integer.parseInt(idField.getText().trim()),
                        customerField.getText().trim(),
                        Integer.parseInt(shipmentIdField.getText().trim())
                );
                DatabaseManager.saveOrder(o);
                JOptionPane.showMessageDialog(parent, "Order added successfully!");
                if (viewerPanel != null) viewerPanel.refreshData();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parent, "Error adding order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void showAddRouteDialog(JFrame parent, DataViewerPanel viewerPanel) {
        JTextField srcField = new JTextField(15);
        JTextField destField = new JTextField(15);
        JTextField distField = new JTextField(8);
        JTextField costField = new JTextField(8);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Source Warehouse:")); panel.add(srcField);
        panel.add(new JLabel("Destination Warehouse:")); panel.add(destField);
        panel.add(new JLabel("Distance:")); panel.add(distField);
        panel.add(new JLabel("Transport Cost:")); panel.add(costField);

        int result = JOptionPane.showConfirmDialog(parent, panel, 
                 "Add Route", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Route r = new Route(
                        srcField.getText().trim(),
                        destField.getText().trim(),
                        Integer.parseInt(distField.getText().trim()),
                        Integer.parseInt(costField.getText().trim())
                );
                DatabaseManager.saveRoute(r);
                JOptionPane.showMessageDialog(parent, "Route added successfully!");
                if (viewerPanel != null) viewerPanel.refreshData();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parent, "Error adding route: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
