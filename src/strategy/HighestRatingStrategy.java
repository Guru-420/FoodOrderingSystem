package strategy;

import model.*;

import java.util.*;

public class HighestRatingStrategy implements SelectionStrategy {
    public Restaurant selectRestaurant(List<Restaurant> restaurants, Order order) {
        Restaurant selected = null;
        double maxRating = -1;
        for (Restaurant r : restaurants) {
            if (!r.canAcceptOrder(order)) continue;
            if (r.rating > maxRating) {
                maxRating = r.rating;
                selected = r;
            }
        }
        return selected;
    }
}
