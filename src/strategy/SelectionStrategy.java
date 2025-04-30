package strategy;

import model.*;

import java.util.*;

public interface SelectionStrategy {
    Restaurant selectRestaurant(List<Restaurant> restaurants, Order order);
}
