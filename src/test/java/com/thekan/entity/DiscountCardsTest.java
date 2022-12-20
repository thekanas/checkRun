package com.thekan.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountCardsTest {
    DiscountCard discountCard = new DiscountCard("13a", 16);
    List<DiscountCard> list = new ArrayList<>();
    list.add(discountCard);
    DiscountCards discountCards = new DiscountCards(list);

    @Test
    void getDiscountCard() {
        assertEquals (discountCard, discountCards.getDiscountCard("13a"));
    }
    
    @Test
    void getDiscountCard() {
        assertNull (discountCards.getDiscountCard("13"));
    }
}
