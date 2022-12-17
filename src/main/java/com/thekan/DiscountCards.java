package com.thekan;

import java.util.HashMap;

public class DiscountCards {
    private HashMap<String, Integer> discountCards = new HashMap<>();

    public HashMap<String, Integer> getDiscountCards() {
        return discountCards;
    }

    public DiscountCards(){
        discountCards.put("1234", 15);
        discountCards.put("23", 5);
        discountCards.put("3456", 10);
        discountCards.put("678", 11);

    }

    public DiscountCards(String patch){
        discountCards.put("1234", 15);
        discountCards.put("23", 5);
        discountCards.put("3456", 10);
        discountCards.put("678", 11);
    }
}
