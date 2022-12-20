package com.thekan.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductsTest {
    Product product = new Product("1a", "Banana", 1.99, 0.05, true, 1.5, 10);
    List<Product> list = new ArrayList<>();
    list.add(product);
    Products products = new Products(list);
    

    @Test
    void getProduct() {
        assertEquals (product, products.getProduct("1a"));
    }
    @Test
    void getProduct() {
        assertNull (products.getProduct("2"));
    }
}
