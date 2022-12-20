package com.thekan.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductsTest {
    Product product = new Product("1a", "Banana", 1.99, 0.05, true, 1.5, 10);
    List<Product> list = new ArrayList<>();
    Products products = new Products(list);

    @BeforeEach
     void setUp(){
        list.add(product);
    }

    @Test
    void getProduct1() {
        assertEquals (product, products.getProduct("1a"));
    }

//    @Test
//    void getProduct2() {
//        assertNull (products.getProduct("2"));
//    }
}
