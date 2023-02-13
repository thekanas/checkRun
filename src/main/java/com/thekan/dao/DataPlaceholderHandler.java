package com.thekan.dao;

import com.thekan.entity.DiscountCard;
import com.thekan.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class DataPlaceholderHandler {
    public static List<Product> fillProductsList(List<String[]> order) {

        return FillDataFromSQL.fillProductList(order);
    }

    public static DiscountCard fillCard(String discountCardNumber) {

        return FillDataFromSQL.findCard(discountCardNumber);
    }
}
