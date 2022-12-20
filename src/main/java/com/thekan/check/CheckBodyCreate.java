package com.thekan.check;

import com.thekan.LoadData;
import com.thekan.entity.Product;
import com.thekan.entity.Products;
import java.util.List;
import java.util.Locale;

//данный класс формирует тело чека с названием товаров, их количеством, стоимостью за единицу и общей стоимостью
//также считает общую стоимость товаров до добавления VAT и сумму VAT налога

public class CheckBodyCreate {
    private Products products;
    private List<String[]> order;
    private double totalForProducts = 0.0;
    private double totalForTax = 0.0;

    public CheckBodyCreate(LoadData loadData){
        this.products = loadData.getProducts();
        this.order = loadData.getOrder();
    }

    public double getTotalForProducts() {
        return totalForProducts;
    }

    public double getTotalForTax() {
        return totalForTax;
    }

    public String bodyLoad() throws Exception {
        StringBuilder body = new StringBuilder();

        for(String[] orderIdAndQty : order){

            Product product = products.getProduct(orderIdAndQty[0]);

            if(product==null)
                throw new Exception("товара с id " + orderIdAndQty[0] + " нет в базе");

                //если текущий продукт на акции и условие акции выполнено
            if(product.isDiscount() && Integer.parseInt(orderIdAndQty[1])>=product.getQuantityForDiscount()){
                    //то формируем в чеке вторую строчку, где указываем процент скидки и сумму скидки со знаком минус
                    String totalForProduct = String.format(Locale.US ,"%.2f", product.getPrice() * Double.parseDouble(orderIdAndQty[1]));
                    String discountAmount = String.format(Locale.US ,"%.2f", product.getPrice() * Double.parseDouble(orderIdAndQty[1]) * product.getProcentDiscount()/100);
                    body.append(CheckPattern.patternBody(orderIdAndQty[1], product.getName(), CheckPattern.currency + product.getPrice(), CheckPattern.currency + totalForProduct));
                    body.append(CheckPattern.lineBreakCharacter);
                    body.append(CheckPattern.patternBody("", "discount", product.getProcentDiscount() + "%", CheckPattern.currency + "-" + discountAmount));
                    body.append(CheckPattern.lineBreakCharacter);

                    totalForProducts += Double.parseDouble(totalForProduct) - Double.parseDouble(discountAmount);
                    totalForTax += Double.parseDouble(String.format(Locale.US ,"%.2f", product.getTax() * Double.parseDouble(orderIdAndQty[1])));
                    continue;
            }

            String totalForProduct = String.format(Locale.US ,"%.2f", product.getPrice() * Double.parseDouble(orderIdAndQty[1]));
            body.append(CheckPattern.patternBody(orderIdAndQty[1], product.getName(), CheckPattern.currency + product.getPrice(), CheckPattern.currency + totalForProduct));
            body.append(CheckPattern.lineBreakCharacter);

            totalForProducts += Double.parseDouble(totalForProduct);
            totalForTax += Double.parseDouble(String.format(Locale.US ,"%.2f", product.getTax() * Double.parseDouble(orderIdAndQty[1])));
        }

        return body.toString();
    }
}
