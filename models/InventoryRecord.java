package models;

public class InventoryRecord {

    private int productId;
    private String warehouseId;
    private int quantity;

    public InventoryRecord(int productId,
                           String warehouseId,
                           int quantity) {

        this.productId = productId;
        this.warehouseId = warehouseId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product "
                + productId
                + " | Warehouse "
                + warehouseId
                + " | Qty "
                + quantity;
    }
}