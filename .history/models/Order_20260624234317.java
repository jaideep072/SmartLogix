package models;

public class Order {

    private int orderId;
    private String customerName;
    private int shipmentId;

    public Order(int orderId, String customerName, int shipmentId) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.shipmentId = shipmentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    @Override
    public String toString() {
        return orderId + " | " + customerName + " | Shipment: " + shipmentId;
    }
}