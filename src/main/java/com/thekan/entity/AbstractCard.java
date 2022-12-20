package com.thekan.entity;

public abstract class AbstractCard {
    private String id;
    private int procentDiscount;

    public abstract String getId();

    public abstract int getProcentDiscount();
}
