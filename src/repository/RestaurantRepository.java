package repository;

import model.Restaurant;
import java.util.*;

public class RestaurantRepository {
    private Map<String, Restaurant> restaurantMap = new HashMap<>();

    public void save(Restaurant restaurant) {
        restaurantMap.put(restaurant.name, restaurant);
    }

    public Restaurant findByName(String name) {
        return restaurantMap.get(name);
    }

    public List<Restaurant> findAll() {
        return new ArrayList<>(restaurantMap.values());
    }
}

