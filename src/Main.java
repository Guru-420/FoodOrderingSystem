import model.*;
import repository.*;
import service.*;
import strategy.*;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Initialize repositories
        RestaurantRepository restaurantRepository = new RestaurantRepository();
        OrderRepository orderRepository = new OrderRepository();

        // Create service object
        FoodOrderingService service = new FoodOrderingService(restaurantRepository, orderRepository);

        // Onboard Restaurants
        System.out.println("\n=== Onboarding Restaurants ===");

        Restaurant r1 = new Restaurant("R1", 4.5, 5);
        r1.updateMenu(new MenuItem("Veg Biryani", 100));
        r1.updateMenu(new MenuItem("Chicken Biryani", 150));

        Restaurant r2 = new Restaurant("R2", 4.0, 5);
        r2.updateMenu(new MenuItem("Idli", 10));
        r2.updateMenu(new MenuItem("Dosa", 50));
        r2.updateMenu(new MenuItem("Veg Biryani", 80));
        r2.updateMenu(new MenuItem("Chicken Biryani", 175));

        Restaurant r3 = new Restaurant("R3", 4.9, 1);
        r3.updateMenu(new MenuItem("Idli", 15));
        r3.updateMenu(new MenuItem("Dosa", 30));
        r3.updateMenu(new MenuItem("Gobi Manchurian", 150));
        r3.updateMenu(new MenuItem("Chicken Biryani", 175));

        service.onboardRestaurant(r1);
        service.onboardRestaurant(r2);
        service.onboardRestaurant(r3);

        // Update restaurant menus
        System.out.println("\n=== Updating Menu for R1 ===");
        service.updateRestaurantMenu("R1", new MenuItem("Chicken65", 250));

        System.out.println("\n=== Updating Price in Menu for R2 ===");
        service.updateRestaurantMenu("R2", new MenuItem("Chicken Biryani", 150));

        // Place Orders
        System.out.println("\n=== Placing Order 1 ===");
        List<OrderItem> o1 = List.of(new OrderItem("Idli", 3), new OrderItem("Dosa", 1));
        Order order1 = service.placeOrder("Navneet", o1, new LowestBillCostStrategy());
        System.out.println("Order " + order1.orderId + " assigned to " + order1.assignedRestaurant.name);

        System.out.println("\n=== Placing Order 2 ===");
        Order order2 = service.placeOrder("Gaurav", o1, new LowestBillCostStrategy());
        System.out.println("Order " + order2.orderId + " assigned to " + order2.assignedRestaurant.name);

        System.out.println("\n=== Placing Order 3 ===");
        List<OrderItem> o3 = List.of(new OrderItem("Veg Biryani", 3), new OrderItem("Dosa", 1));
        Order order3 = service.placeOrder("Shruthi", o3, new HighestRatingStrategy());
        System.out.println("Order " + order3.orderId + " assigned to " + order3.assignedRestaurant.name);

        // Complete Order 1
        System.out.println("\n=== Completing Order 1 ===");
        service.completeOrder(order1.orderId);

        // Place another order
        System.out.println("\n=== Placing Order 4 ===");
        Order order4 = service.placeOrder("Gaurav", o1, new LowestBillCostStrategy());
        System.out.println("Order " + order4.orderId + " assigned to " + order4.assignedRestaurant.name);

        // Attempt to place an order that cannot be fulfilled
        System.out.println("\n=== Placing Order 5 ===");
        try {
            List<OrderItem> o5 = List.of(new OrderItem("Idli", 3), new OrderItem("Paneer Tikka", 1));
            Order order5 = service.placeOrder("Deeksha", o5, new LowestBillCostStrategy());
            System.out.println("Order " + order5.orderId + " assigned to " + order5.assignedRestaurant.name);
        } catch (Exception e) {
            System.out.println("Order failed: " + e.getMessage());
        }

        System.out.println("\n=== Done ===");
    }
}
