package com.example.blossomassistant;

public class OrderModel {
    private final String name;
    private final String phone;
    private final String address;
    private final String date;
    private final String time;
    private final String designName;
    private final String price;

    public OrderModel(String name, String phone, String address, String date, String time, String designName, String price) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.date = date;
        this.time = time;
        this.designName = designName;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDesignName() {
        return designName;
    }

    public String getPrice() {
        return price;
    }
}

