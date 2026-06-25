package models;

public class Route {

    private String sourceWarehouse;
    private String destinationWarehouse;
    private int distance;
    private int transportCost;

    public Route(String sourceWarehouse,
                 String destinationWarehouse,
                 int distance,
                 int transportCost) {

        this.sourceWarehouse = sourceWarehouse;
        this.destinationWarehouse = destinationWarehouse;
        this.distance = distance;
        this.transportCost = transportCost;
    }

    public String getSourceWarehouse() {
        return sourceWarehouse;
    }

    public String getDestinationWarehouse() {
        return destinationWarehouse;
    }

    public int getDistance() {
        return distance;
    }

    public int getTransportCost() {
        return transportCost;
    }

    @Override
    public String toString() {
        return sourceWarehouse + " -> "
                + destinationWarehouse
                + " | Distance: "
                + distance
                + " | Cost: "
                + transportCost;
    }
}
