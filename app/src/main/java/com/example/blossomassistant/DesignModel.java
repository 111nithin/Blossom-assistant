package com.example.blossomassistant;

public class DesignModel {
    private final String name;
    private final int image;
    private final int price;
    private final String category;

    public DesignModel(String name, int image, int price) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.category = "Wedding";
    }

    public DesignModel(String name, int image, int price, String category) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}