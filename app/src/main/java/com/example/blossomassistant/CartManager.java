package com.example.blossomassistant;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    public static final ArrayList<String> cartItems = new ArrayList<>();

    public static boolean addItem(String item) {
        if (cartItems.contains(item)) {
            return false;
        }
        cartItems.add(item);
        return true;
    }

    public static void removeItem(int index) {
        if (index >= 0 && index < cartItems.size()) {
            cartItems.remove(index);
        }
    }

    public static void clearCart() {
        cartItems.clear();
    }

    public static List<String> getItems() {
        return cartItems;
    }
}