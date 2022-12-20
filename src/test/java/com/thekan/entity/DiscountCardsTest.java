package com.thekan.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscountCardsTest {
    DiscountCard discountCard = new DiscountCard("13a", 16);
    List<DiscountCard> list = new ArrayList<>();
    DiscountCards discountCards = new DiscountCards(list);
    @BeforeEach
    void setUp() {
        list.add(discountCard);
    }

    @Test
    void getDiscountCard1() {
        assertEquals (discountCard, discountCards.getDiscountCard("13a"));
    }
    
    @Test
    void getDiscountCard2() {
        assertNull (discountCards.getDiscountCard("13"));
    }
}
