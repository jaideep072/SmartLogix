package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SmartLogixGUI extends JFrame {

    private DataViewerPanel viewerPanel;
    private boolean isAdmin;

    public SmartLogixGUI(boolean isAdmin) {
        this.isAdmin = isAdmin;
        setTitle("SmartLogix - Logistics Management System (" + (isAdmin ? "Admin" : "User") + " Mode)");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JTabbedPane mainTabs = new JTabbedPane();

        // 1. Dashboard Tab (Actions)
        JPanel dashboardPanel = createDashboardPanel();
        mainTabs.addTab("Dashboard", dashboardPanel);

        // 2. Data Viewer Tab
        viewerPanel = new DataViewerPanel();
        mainTabs.addTab("Logistics Data", viewerPanel);

        // Add tabs to frame
        add(mainTabs, BorderLayout.CENTER);
    }

    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel titleLabel = new JLabel("SmartLogix Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        Font btnFont = new Font("Arial", Font.PLAIN, 18);

        JButton btnAddWarehouse = new JButton("Add Warehouse");
        btnAddWarehouse.setFont(btnFont);
        btnAddWarehouse.addActionListener(e -> AddDialogs.showAddWarehouseDialog(this, viewerPanel));
        btnAddWarehouse.setEnabled(isAdmin);
        panel.add(btnAddWarehouse, gbc);

        gbc.gridx = 1;
        JButton btnAddShipment = new JButton("Add Shipment");
        btnAddShipment.setFont(btnFont);
        btnAddShipment.addActionListener(e -> AddDialogs.showAddShipmentDialog(this, viewerPanel));
        btnAddShipment.setEnabled(isAdmin);
        panel.add(btnAddShipment, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JButton btnAddOrder = new JButton("Add Order");
        btnAddOrder.setFont(btnFont);
        btnAddOrder.addActionListener(e -> AddDialogs.showAddOrderDialog(this, viewerPanel));
        btnAddOrder.setEnabled(isAdmin);
        panel.add(btnAddOrder, gbc);

        gbc.gridx = 1;
        JButton btnAddRoute = new JButton("Add Route");
        btnAddRoute.setFont(btnFont);
        btnAddRoute.addActionListener(e -> AddDialogs.showAddRouteDialog(this, viewerPanel));
        btnAddRoute.setEnabled(isAdmin);
        panel.add(btnAddRoute, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton btnReload = new JButton("Reload CSV Data");
        btnReload.setFont(btnFont);
        btnReload.addActionListener(e -> {
            viewerPanel.refreshData();
            JOptionPane.showMessageDialog(this, "Data reloaded successfully!");
        });
        panel.add(btnReload, gbc);

        gbc.gridy++;
        JButton btnModules = new JButton("Run Modules (M1-M6)");
        btnModules.setFont(btnFont);
        btnModules.addActionListener(e -> JOptionPane.showMessageDialog(this, 
                "Modules M1-M6 are currently accessible via the console application.", 
                "Modules", JOptionPane.INFORMATION_MESSAGE));
        panel.add(btnModules, gbc);

        return panel;
    }

    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            String[] options = {"Admin", "User"};
            int choice = JOptionPane.showOptionDialog(null, 
                "Select your role to continue:", 
                "Role Selection", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, options, options[0]);

            if (choice == JOptionPane.CLOSED_OPTION) {
                return;
            }

            boolean isAdmin = (choice == 0);
            new SmartLogixGUI(isAdmin).setVisible(true);
        });
    }
}
