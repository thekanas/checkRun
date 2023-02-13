package com.thekan;

import com.thekan.dao.DataPlaceholderHandler;
import com.thekan.entity.DiscountCard;
import com.thekan.entity.Products;

import java.util.ArrayList;
import java.util.List;

public class LoadData {

    private Products products;
    private DiscountCard discountCard;
    private boolean isDiscountCardPresent;
    private List<String[]> order = new ArrayList<>();
    private String discountCardNumber;

    public LoadData(Products products, DiscountCard discountCard, boolean isDiscountCardPresent, List<String[]> order, String discountCardNumber) {
        this.products = products;
        this.discountCard = discountCard;
        this.isDiscountCardPresent = isDiscountCardPresent;
        this.order = order;
        this.discountCardNumber = discountCardNumber;
    }

    public LoadData(String[] args)  {

        if((args[args.length-1]).toLowerCase().startsWith("card-")) {
                isDiscountCardPresent = true;
                discountCardNumber = (args[args.length - 1]).substring(5);
                discountCard = DataPlaceholderHandler.fillCard(discountCardNumber);
                orderLoad(args, args.length-2);

        } else {
            isDiscountCardPresent = false;
            orderLoad(args, args.length-1);
        }

        products = new Products(order);

    }

    public void orderLoad(String[] args, int toElement){
        for (int i = 0; i <= toElement; i++ ){
            String[] pair = args[i].split("-");
            order.add(i, pair);
        }
    }

    public Products getProducts() {
        return products;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
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
