package com.example.blossomassistant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class OrderManager {
    private static final ArrayList<OrderModel> orders = new ArrayList<>();

    private OrderManager() {}

    public static void addOrder(OrderModel order) {
        if (order == null) return;
        orders.add(order);
    }

    public static List<OrderModel> getOrders() {
        return Collections.unmodifiableList(orders);
    }
}

