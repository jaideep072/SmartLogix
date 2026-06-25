package models;

public class Warehouse {
    private String warehouseId;
    private String warehouseName;
    private String city;

    public Warehouse(String warehouseId, String warehouseName, String city) {
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.city = city;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return warehouseId + " | " + warehouseName + " | " + city;
    }
}
