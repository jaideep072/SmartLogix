package m5;

import models.Shipment;

import java.util.ArrayList;
import java.util.List;

public class SortingAlgorithms {

    // ================= MERGE SORT =================

    public void mergeSortByDate(List<Shipment> shipments) {

        if (shipments.size() <= 1)
            return;

        int mid = shipments.size() / 2;

        List<Shipment> left =
                new ArrayList<>(
                        shipments.subList(0, mid));

        List<Shipment> right =
                new ArrayList<>(
                        shipments.subList(mid,
                                shipments.size()));

        mergeSortByDate(left);
        mergeSortByDate(right);

        mergeByDate(
                shipments,
                left,
                right);
    }

    private void mergeByDate(
            List<Shipment> result,
            List<Shipment> left,
            List<Shipment> right) {

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.size()
                && j < right.size()) {

            if (
                    left.get(i)
                            .getDeliveryDate()
                            .compareTo(
                                    right.get(j)
                                            .getDeliveryDate())
                            <= 0
            ) {

                result.set(
                        k++,
                        left.get(i++));
            }

            else {

                result.set(
                        k++,
                        right.get(j++));
            }
        }

        while (i < left.size())
            result.set(
                    k++,
                    left.get(i++));

        while (j < right.size())
            result.set(
                    k++,
                    right.get(j++));
    }

    // ================= QUICK SORT =================

    public void quickSortByCost(
            List<Shipment> list,
            int low,
            int high) {

        if (low < high) {

            int p =
                    partition(
                            list,
                            low,
                            high);

            quickSortByCost(
                    list,
                    low,
                    p - 1);

            quickSortByCost(
                    list,
                    p + 1,
                    high);
        }
    }

    private int partition(
            List<Shipment> list,
            int low,
            int high) {

        double pivot =
                list.get(high)
                        .getCost();

        int i = low - 1;

        for (int j = low;
             j < high;
             j++) {

            if (
                    list.get(j)
                            .getCost()
                            < pivot
            ) {

                i++;

                Shipment temp =
                        list.get(i);

                list.set(
                        i,
                        list.get(j));

                list.set(
                        j,
                        temp);
            }
        }

        Shipment temp =
                list.get(i + 1);

        list.set(
                i + 1,
                list.get(high));

        list.set(
                high,
                temp);

        return i + 1;
    }

    // ================= HEAP SORT =================

    public void heapSortByPriority(
            List<Shipment> list) {

        int n = list.size();

        for (int i = n / 2 - 1;
             i >= 0;
             i--) {

            heapify(
                    list,
                    n,
                    i);
        }

        for (int i = n - 1;
             i > 0;
             i--) {

            Shipment temp =
                    list.get(0);

            list.set(
                    0,
                    list.get(i));

            list.set(
                    i,
                    temp);

            heapify(
                    list,
                    i,
                    0);
        }
    }

    private void heapify(
            List<Shipment> list,
            int n,
            int i) {

        int largest = i;

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (
                left < n &&
                        list.get(left)
                                .getPriority()
                                >
                                list.get(largest)
                                        .getPriority()
        ) {

            largest = left;
        }

        if (
                right < n &&
                        list.get(right)
                                .getPriority()
                                >
                                list.get(largest)
                                        .getPriority()
        ) {

            largest = right;
        }

        if (largest != i) {

            Shipment temp =
                    list.get(i);

            list.set(
                    i,
                    list.get(largest));

            list.set(
                    largest,
                    temp);

            heapify(
                    list,
                    n,
                    largest);
        }
    }

    // ================= RADIX SORT =================

    public void radixSortIds(
            int[] ids) {

        int max = ids[0];

        for (int id : ids)
            max = Math.max(max, id);

        for (int exp = 1;
             max / exp > 0;
             exp *= 10) {

            countingSort(
                    ids,
                    exp);
        }
    }

    private void countingSort(
            int[] arr,
            int exp) {

        int n = arr.length;

        int[] output =
                new int[n];

        int[] count =
                new int[10];

        for (int value : arr)
            count[(value / exp) % 10]++;

        for (int i = 1;
             i < 10;
             i++)

            count[i] += count[i - 1];

        for (int i = n - 1;
             i >= 0;
             i--) {

            output[
                    count[
                            (arr[i] / exp) % 10
                            ] - 1
                    ] = arr[i];

            count[
                    (arr[i] / exp) % 10
                    ]--;
        }

        System.arraycopy(
                output,
                0,
                arr,
                0,
                n);
    }
}
