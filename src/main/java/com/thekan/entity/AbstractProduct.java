package com.thekan.entity;

public abstract class AbstractProduct {
    private String id;
    private String name;
    private double price;
    private double tax;

    public abstract String getId();

    public abstract String getName();

    public abstract double getPrice();

    public abstract double getTax();
}
