package com.thekan.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {


    Product product = new Product("1a", "Banana", 1.99, 0.05, true, 1.5, 10);

    @Test
    void getId() {
        assertEquals ("1a", product.getId());
    }

    @Test
    void getName() {
    }

    @Test
    void getPrice() {
    }

    @Test
    void getTax() {
    }

    @Test
    void isDiscount() {
    }

    @Test
    void getQuantityForDiscount() {
    }

    @Test
    void getProcentDiscount() {
    }
}