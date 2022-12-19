package com.thekan.entity;

public class Product extends AbstractProduct {
    private String id;
    private String name;
    private double price;
    private double tax;

    private boolean isDiscount = false;
    private double quantityForDiscount = 0.0;
    private int procentDiscount = 0;

    public Product(String id, String name, double price, double tax) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.tax = tax;
    }

    public Product(String id, String name, double price, double tax, boolean isDiscount, double quantityForDiscount, int procentDiscount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.tax = tax;
        this.isDiscount = isDiscount;
        this.quantityForDiscount = quantityForDiscount;
        this.procentDiscount = procentDiscount;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getTax() {
        return tax;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    public double getQuantityForDiscount() {
        return quantityForDiscount;
    }

    public int getProcentDiscount() {
        return procentDiscount;
    }
}
