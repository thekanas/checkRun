package com.thekan.entity;

public abstract class AbstractProduct {
    private String id;
    private String name;
    private double price;
    private double tax;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getTax() {
        return tax;
    }
}
