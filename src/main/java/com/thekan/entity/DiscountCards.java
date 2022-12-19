package com.thekan.entity;

import com.thekan.ConsoleHelper;
import java.util.ArrayList;
import java.util.List;

public class DiscountCards {

    private List<DiscountCard> discountCards = new ArrayList<>();

    public DiscountCards(List<DiscountCard> list){
        discountCards = list;
    }

    public DiscountCards(String patch){
        ArrayList<String> strings = ConsoleHelper.readToFile(patch);

        for (int i = 1; i < strings.size(); i++) {
            String[] str = strings.get(i).split("\\s+");

            discountCards.add(new DiscountCard(str[0], Integer.parseInt(str[1])));
        }
    }

    public DiscountCard getDiscountCard(String id) {
        try {
            return discountCards.stream()
                    .filter(product -> product.getId().equals(id))
                    .findFirst()
                    .get();
        } catch (Exception exception){

            return null;
        }
    }
}
