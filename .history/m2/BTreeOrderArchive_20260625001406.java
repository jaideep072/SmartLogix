package m2;

import models.Order;

import java.util.ArrayList;
import java.util.List;

public class BTreeOrderArchive {

    private List<Order> orders =
            new ArrayList<>();

    public void insert(Order order) {

        orders.add(order);
    }

    public Order search(int orderId) {

        for (Order order : orders) {

            if (order.getOrderId() == orderId)
                return order;
        }

        return null;
    }

    public void display() {

        for (Order order : orders)
            System.out.println(order);
    }
}