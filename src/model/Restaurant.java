package model;

import java.util.*;

public class Restaurant {
    public String name;
    public double rating;
    public int maxOrders;
    public Map<String, MenuItem> menu = new HashMap<>();
    List<Order> activeOrders = new ArrayList<>();

    public Restaurant(String name, double rating, int maxOrders) {
        this.name = name;
        this.rating = rating;
        this.maxOrders = maxOrders;
    }

    public boolean canAcceptOrder(Order order) {
        if (activeOrders.size() >= maxOrders) return false;
        for (OrderItem item : order.items) {
            if (!menu.containsKey(item.itemName)) return false;
        }
        return true;
    }

    public void acceptOrder(Order order) {
        activeOrders.add(order);
        order.assignedRestaurant = this;
    }

    public void completeOrder(int orderId) {
        activeOrders.removeIf(order -> order.orderId == orderId);
    }

    public void updateMenu(MenuItem item) {
        menu.put(item.name, item);
    }
}
