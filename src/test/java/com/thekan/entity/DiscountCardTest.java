package com.thekan.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountCardTest {
    DiscountCard discountCard = new DiscountCard("12a", 13);

    @Test
    void getId() {
        assertEquals ("12a", discountCard.getId());
    }

    @Test
    void getProcentDiscount() {
       assertEquals (13, discountCard.getProcentDiscount()); 
    }
}
