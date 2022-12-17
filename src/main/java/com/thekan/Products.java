package com.thekan;

import java.util.HashMap;

public class Products {


    private HashMap<String, String[]> products = new HashMap<>();

    public HashMap<String, String[]> getProducts() {
        return products;
    }

    public Products(){
        products.put("1", new String[]{"Banana", "30.99"});
        products.put("2", new String[]{"milk", "1.99"});
        products.put("3", new String[]{"Bread", "0.80"});
        products.put("4", new String[]{"Apple", "3.50", "true", "5", "10"});
        products.put("5", new String[]{"Cheese", "7.10"});
        products.put("6", new String[]{"Juice", "3.69"});
    }
    public Products(String patch){
        products.put("1", new String[]{"Banana", "30.99"});
        products.put("2", new String[]{"milk", "1.99"});
        products.put("3", new String[]{"Bread", "0.80"});
        products.put("4", new String[]{"Applegbljhbgljbhljbh", "3.50", "true", "5", "50"});
        products.put("5", new String[]{"Cheese", "7.10"});
        products.put("6", new String[]{"Juice", "3.69"});
    }
}
