package m1;

import models.InventoryRecord;

public class AVLInventoryManager {

    class Node {

        InventoryRecord record;
        Node left, right;
        int height;

        Node(InventoryRecord record) {
            this.record = record;
            height = 1;
        }
    }

    private Node root;

    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    private int balance(Node n) {

        if (n == null)
            return 0;

        return height(n.left) - height(n.right);
    }

    private Node rightRotate(Node y) {

        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height =
                Math.max(height(y.left),
                        height(y.right)) + 1;

        x.height =
                Math.max(height(x.left),
                        height(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node x) {

        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height =
                Math.max(height(x.left),
                        height(x.right)) + 1;

        y.height =
                Math.max(height(y.left),
                        height(y.right)) + 1;

        return y;
    }

    public void insert(InventoryRecord record) {
        root = insert(root, record);
    }

    private Node insert(Node node,
                        InventoryRecord record) {

        if (node == null)
            return new Node(record);

        if (record.getProductId()
                < node.record.getProductId())

            node.left =
                    insert(node.left, record);

        else if (record.getProductId()
                > node.record.getProductId())

            node.right =
                    insert(node.right, record);

        else
            return node;

        node.height =
                1 + Math.max(height(node.left),
                        height(node.right));

        int balance = balance(node);

        // LL
        if (balance > 1 &&
                record.getProductId()
                        < node.left.record.getProductId())

            return rightRotate(node);

        // RR
        if (balance < -1 &&
                record.getProductId()
                        > node.right.record.getProductId())

            return leftRotate(node);

        // LR
        if (balance > 1 &&
                record.getProductId()
                        > node.left.record.getProductId()) {

            node.left =
                    leftRotate(node.left);

            return rightRotate(node);
        }

        // RL
        if (balance < -1 &&
                record.getProductId()
                        < node.right.record.getProductId()) {

            node.right =
                    rightRotate(node.right);

            return leftRotate(node);
        }

        return node;
    }

    public InventoryRecord search(int productId) {

        Node current = root;

        while (current != null) {

            if (productId ==
                    current.record.getProductId())
                return current.record;

            if (productId <
                    current.record.getProductId())
                current = current.left;
            else
                current = current.right;
        }

        return null;
    }

    public void displayInventory() {
        inorder(root);
    }

    private void inorder(Node node) {

        if (node == null)
            return;

        inorder(node.left);
        System.out.println(node.record);
        inorder(node.right);
    }
}