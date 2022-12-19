package com.thekan.entity;

public class DiscountCard extends AbstractCard {
    private String id;
    private int procentDiscount;

    public DiscountCard(String id, int procentDiscount) {
        this.id = id;
        this.procentDiscount = procentDiscount;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getProcentDiscount() {
        return procentDiscount;
    }
}
