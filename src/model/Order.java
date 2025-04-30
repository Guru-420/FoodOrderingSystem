package model;

import java.util.*;

public class Order {
    static int idCounter = 1;
    public int orderId;
    public List<OrderItem> items;
    public Restaurant assignedRestaurant;
    public OrderStatus status;
    public String user;

    public Order(String user, List<OrderItem> items) {
        this.user = user;
        this.orderId = idCounter++;
        this.items = items;
        this.status = OrderStatus.ACCEPTED;
    }

    public double getTotalBill() {
        double total = 0;
        for (OrderItem item : items) {
            MenuItem menuItem = assignedRestaurant.menu.get(item.itemName);
            if (menuItem != null) {
                total += menuItem.price * item.quantity;
            }
        }
        return total;
    }
}
