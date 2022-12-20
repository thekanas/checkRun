package com.thekan.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {


    Product product = new Product("1a", "Banana", 1.99, 0.05, true, 1.5, 10);
    double delta = 0.0000001;

    @Test
    void getId() {
        assertEquals ("1a", product.getId());
    }

    @Test
    void getName() {
        assertEquals ("Banana", product.getName());
    }

    @Test
    void getPrice() {
        assertEquals (1.99, product.getPrice(), delta);
    }

    @Test
    void getTax() {
        assertEquals (0.05, product.getTax(), delta);
    }

    @Test
    void isDiscount() {
        assertEquals (true, product.isDiscount());
    }

    @Test
    void getQuantityForDiscount() {
        assertEquals (1.5, product.getQuantityForDiscount(), delta);
    }

    @Test
    void getProcentDiscount() {
        assertEquals (10, product.getProcentDiscount());
    }
}
