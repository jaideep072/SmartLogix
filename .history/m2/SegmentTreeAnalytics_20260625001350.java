package m2;

public class SegmentTreeAnalytics {

    private int[] tree;
    private int n;

    public SegmentTreeAnalytics(int[] deliveries) {

        n = deliveries.length;
        tree = new int[4 * n];

        build(deliveries, 1, 0, n - 1);
    }

    private void build(int[] arr,
                       int node,
                       int start,
                       int end) {

        if (start == end) {

            tree[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;

        build(arr, node * 2, start, mid);
        build(arr, node * 2 + 1, mid + 1, end);

        tree[node] =
                tree[node * 2] +
                tree[node * 2 + 1];
    }

    public int query(int left, int right) {

        return query(
                1,
                0,
                n - 1,
                left,
                right
        );
    }

    private int query(int node,
                      int start,
                      int end,
                      int left,
                      int right) {

        if (right < start || left > end)
            return 0;

        if (left <= start && end <= right)
            return tree[node];

        int mid = (start + end) / 2;

        return query(
                node * 2,
                start,
                mid,
                left,
                right
        ) +
        query(
                node * 2 + 1,
                mid + 1,
                end,
                left,
                right
        );
    }
}