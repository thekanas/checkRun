package com.thekan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoadData {
    private Products products;
    private DiscountCards discountCards;
    private boolean isDiscountCardPresent;
    private List<String[]> order = new ArrayList<>();
    private String discountCardNumber;



    public LoadData(String[] args){
        if ((args[args.length-2]).toLowerCase().endsWith("txt")) {
            products = new Products(args[args.length-2]);

            if((args[args.length-3]).toLowerCase().startsWith("card")) {
                isDiscountCardPresent = true;
                discountCardNumber = (args[args.length - 3]).substring(5);
                //System.out.println(discountCardNumber);
                discountCards = new DiscountCards(args[args.length-1]);
            }

            orderLoad(args, args.length-3);
        }
        else {
            products = new Products(args[args.length-1]);
            isDiscountCardPresent = false;
            orderLoad(args, args.length-1);
        }

    }

    public void orderLoad(String[] args, int toElement){
        for (int i = 0; i < toElement; i++ ){
            String[] pair = args[i].split("-");
            order.add(i, pair);
        }
    }

    public Products getProducts() {
        return products;
    }

    public DiscountCards getDiscountCards() {
        return discountCards;
    }

    public List<String[]> getOrder() {
        return order;
    }

    public String getDiscountCardNumber() {
        return discountCardNumber;
    }

    public boolean isDiscountCardPresent() {
        return isDiscountCardPresent;
    }
}
