package com.thekan.entity;


import com.thekan.dao.DataPlaceholderHandler;

import java.util.ArrayList;
import java.util.List;

public class Products {

    private List<Product> products;

    public Products(List<String[]> order)  {
        products = DataPlaceholderHandler.fillProductsList(order);

    }

    public Products(ArrayList<Product> listProduct) {
        products = listProduct;
    }

    public Product getProduct(String id) {
        try {
            return products.stream()
                    .filter(product -> product.getId().equals(id))
                    .findFirst()
                    .get();
        } catch (NullPointerException exception){
            return null;
        }
    }
}
