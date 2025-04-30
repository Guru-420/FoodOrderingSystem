# 🍽️ Online Food Ordering System (Java - In-Memory)

This is an **in-memory Java-based food ordering system** that supports restaurant integration, menu management, customer ordering, and pluggable restaurant selection strategies. Designed with clean architecture using Model-Service-Repository-Strategy layers.

---

## ✅ Features

- 🚀 Onboard new restaurants with menu, rating, and capacity
- 🧾 Update restaurant menu (add/update items only)
- 🍽️ Customers can place orders with item quantity and selection strategy
- 📊 Orders are automatically assigned based on:
  - Lowest Bill Cost
  - Highest Restaurant Rating
- 🔁 Orders can be marked as **COMPLETED**
- ❌ If a restaurant cannot fulfill all items or is at capacity, the order is rejected
- ⚙️ Selection strategy is pluggable (Strategy Pattern)

---

## 🗂️ Project Structure

```plaintext
src/
├── model/
│   ├── Restaurant.java
│   ├── MenuItem.java
│   ├── Order.java
│   ├── OrderItem.java
│   └── OrderStatus.java
│
├── repository/
│   ├── RestaurantRepository.java
│   └── OrderRepository.java
│
├── service/
│   └── FoodOrderingService.java
│
├── strategy/
│   ├── SelectionStrategy.java
│   ├── LowestBillCostStrategy.java
│   └── HighestRatingStrategy.java
│
└── Main.java
