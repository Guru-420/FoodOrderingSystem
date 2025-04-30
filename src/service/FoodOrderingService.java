package service;

import model.*;
import repository.*;
import strategy.*;

import java.util.*;

public class FoodOrderingService {
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;

    public FoodOrderingService(RestaurantRepository restaurantRepository, OrderRepository orderRepository) {
        this.restaurantRepository = restaurantRepository;
        this.orderRepository = orderRepository;
    }

    public void onboardRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        System.out.println("Onboarded Restaurant: " + restaurant.name);
    }

    public void updateRestaurantMenu(String restaurantName, MenuItem item) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        if (restaurant != null) {
            restaurant.updateMenu(item);
            System.out.println("Updated Menu for " + restaurantName + ": " + item.name + " -> INR " + item.price);
        } else {
            System.out.println("Restaurant " + restaurantName + " not found!");
        }
    }

    public Order placeOrder(String user, List<OrderItem> items, SelectionStrategy strategy) throws Exception {
        Order order = new Order(user, items);
        Restaurant selectedRestaurant = strategy.selectRestaurant(restaurantRepository.findAll(), order);
        if (selectedRestaurant == null) {
            System.out.println("No restaurant can fulfill the order for user " + user);
            throw new Exception("No restaurant can fulfill the order");
        }
        selectedRestaurant.acceptOrder(order);
        orderRepository.save(order);
        System.out.println("Order " + order.orderId + " placed by " + user + " assigned to " + selectedRestaurant.name);
        return order;
    }

    public void completeOrder(int orderId) throws Exception {
        Order order = orderRepository.findById(orderId);
        if (order == null || order.status == OrderStatus.COMPLETED) {
            System.out.println("Invalid or already completed order for ID: " + orderId);
            throw new Exception("Invalid or already completed order");
        }
        order.status = OrderStatus.COMPLETED;
        order.assignedRestaurant.completeOrder(orderId);
        orderRepository.save(order);
        System.out.println("Order " + orderId + " marked as COMPLETED.");
    }
}
