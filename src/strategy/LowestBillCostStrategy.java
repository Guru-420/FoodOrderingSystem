package strategy;

import model.*;

import java.util.*;

public class LowestBillCostStrategy implements SelectionStrategy {
    public Restaurant selectRestaurant(List<Restaurant> restaurants, Order order) {
        Restaurant selected = null;
        double minCost = Double.MAX_VALUE;
        for (Restaurant r : restaurants) {
            if (!r.canAcceptOrder(order)) continue;
            double cost = 0;
            for (OrderItem item : order.items) {
                cost += r.menu.get(item.itemName).price * item.quantity;
            }
            if (cost < minCost) {
                minCost = cost;
                selected = r;
            }
        }
        return selected;
    }
}
