package com.thekan.entity;

import com.thekan.ConsoleHelper;

import java.util.ArrayList;
import java.util.List;

public class Products {

    private List<Product> products = new ArrayList<>();

    public Products(List<Product> list){
        products = list;
    }

    public Products(String patch){
        ArrayList<String> strings = ConsoleHelper.readToFile(patch);

        for (int i = 1; i < strings.size(); i++) {
            String[] str = strings.get(i).split("\\s+");
            if(str.length > 4 && str[4].equalsIgnoreCase("true")){
                products.add(new Product(str[0], str[1], Double.parseDouble(str[2]), Double.parseDouble(str[3]), Boolean.parseBoolean(str[4]), Double.parseDouble(str[5]), Integer.parseInt(str[6])));
            }
            else {
                products.add(new Product(str[0], str[1], Double.parseDouble(str[2]), Double.parseDouble(str[3])));
            }
        }
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
