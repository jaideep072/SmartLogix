package m1;

import models.Shipment;

public class BSTShipmentTracker {

    class Node {
        Shipment shipment;
        Node left, right;

        Node(Shipment shipment) {
            this.shipment = shipment;
        }
    }

    private Node root;

    // Insert
    public void insert(Shipment shipment) {
        root = insert(root, shipment);
    }

    private Node insert(Node node, Shipment shipment) {

        if (node == null)
            return new Node(shipment);

        if (shipment.getShipmentId() < node.shipment.getShipmentId())
            node.left = insert(node.left, shipment);

        else if (shipment.getShipmentId() > node.shipment.getShipmentId())
            node.right = insert(node.right, shipment);

        return node;
    }

    // Search
    public Shipment search(int shipmentId) {

        Node current = root;

        while (current != null) {

            if (shipmentId == current.shipment.getShipmentId())
                return current.shipment;

            if (shipmentId < current.shipment.getShipmentId())
                current = current.left;
            else
                current = current.right;
        }

        return null;
    }

    // Inorder Traversal
    public void displaySorted() {
        inorder(root);
    }

    private void inorder(Node node) {

        if (node == null)
            return;

        inorder(node.left);
        System.out.println(node.shipment);
        inorder(node.right);
    }

    // Delete
    public void delete(int shipmentId) {
        root = delete(root, shipmentId);
    }

    private Node delete(Node node, int shipmentId) {

        if (node == null)
            return null;

        if (shipmentId < node.shipment.getShipmentId()) {

            node.left = delete(node.left, shipmentId);

        } else if (shipmentId > node.shipment.getShipmentId()) {

            node.right = delete(node.right, shipmentId);

        } else {

            if (node.left == null)
                return node.right;

            if (node.right == null)
                return node.left;

            Node successor = findMin(node.right);

            node.shipment = successor.shipment;

            node.right =
                    delete(node.right,
                            successor.shipment.getShipmentId());
        }

        return node;
    }

    private Node findMin(Node node) {

        while (node.left != null)
            node = node.left;

        return node;
    }
}
