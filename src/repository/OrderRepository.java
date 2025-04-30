package repository;

import model.Order;
import java.util.*;

public class OrderRepository {
    private Map<Integer, Order> orderMap = new HashMap<>();

    public void save(Order order) {
        orderMap.put(order.orderId, order);
    }

    public Order findById(int orderId) {
        return orderMap.get(orderId);
    }

    public void deleteById(int orderId) {
        orderMap.remove(orderId);
    }
}

