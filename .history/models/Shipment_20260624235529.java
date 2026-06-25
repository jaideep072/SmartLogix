package models;
import java.time.LocalDate;

public class Shipment {

    private int shipmentId;
    private String sourceWarehouse;
    private String destinationWarehouse;
    private LocalDate deliveryDate;
    private int priority;
    private double cost;
    private double weight;
    private String status;

    public Shipment(int shipmentId,
                    String sourceWarehouse,
                    String destinationWarehouse,
                    LocalDate deliveryDate,
                    int priority,
                    double cost,
                    double weight,
                    String status) {

        this.shipmentId = shipmentId;
        this.sourceWarehouse = sourceWarehouse;
        this.destinationWarehouse = destinationWarehouse;
        this.deliveryDate = deliveryDate;
        this.priority = priority;
        this.cost = cost;
        this.weight = weight;
        this.status = status;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public String getSourceWarehouse() {
        return sourceWarehouse;
    }

    public String getDestinationWarehouse() {
        return destinationWarehouse;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public int getPriority() {
        return priority;
    }

    public double getCost() {
        return cost;
    }

    public double getWeight() {
        return weight;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return shipmentId + " | "
                + sourceWarehouse + " -> "
                + destinationWarehouse + " | "
                + deliveryDate + " | Priority: "
                + priority + " | Cost: "
                + cost + " | Status: "
                + status;
    }
}