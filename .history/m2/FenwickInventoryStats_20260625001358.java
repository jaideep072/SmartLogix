package m2;

public class FenwickInventoryStats {

    private int[] bit;
    private int size;

    public FenwickInventoryStats(int size) {

        this.size = size;
        bit = new int[size + 1];
    }

    public void update(int index, int value) {

        while (index <= size) {

            bit[index] += value;
            index += index & (-index);
        }
    }

    public int prefixSum(int index) {

        int sum = 0;

        while (index > 0) {

            sum += bit[index];
            index -= index & (-index);
        }

        return sum;
    }

    public int rangeSum(int left, int right) {

        return prefixSum(right)
                - prefixSum(left - 1);
    }
}
