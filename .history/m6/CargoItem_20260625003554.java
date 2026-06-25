package m6;

public class CargoItem {

    private String name;
    private int weight;
    private int profit;

    public CargoItem(String name,
                     int weight,
                     int profit) {

        this.name = name;
        this.weight = weight;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getProfit() {
        return profit;
    }

    @Override
    public String toString() {

        return name +
                " Weight=" + weight +
                " Profit=" + profit;
    }
}
