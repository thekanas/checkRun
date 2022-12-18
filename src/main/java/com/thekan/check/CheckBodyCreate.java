package com.thekan.check;

import com.thekan.ConsoleHelper;
import com.thekan.LoadData;
import com.thekan.Products;
import java.util.List;
import java.util.Locale;

public class CheckBodyCreate {
    private Products products;
    private List<String[]> order;
    private double totalForProducts = 0;

    public CheckBodyCreate(LoadData loadData){
        this.products = loadData.getProducts();
        this.order = loadData.getOrder();
    }

    public double getTotalForProducts() {
        return totalForProducts;
    }

    public String bodyLoad() throws Exception {
        StringBuilder body = new StringBuilder();

        for(String[] pair : order){
            //!добавить проверку наличия индекса в коллекции продуктов
            String[] product = products.getProducts().get(pair[0]);

            if(product==null || !products.getProducts().containsKey(pair[0]))
                throw new Exception("товара с id " + pair[0] + " нет в базе");

                //если текущий продукт на акции и условие акции выполнено
            if(product.length>2 && product[2].equalsIgnoreCase("true") && Integer.parseInt(pair[1])>=Integer.parseInt(product[3])){
                    //то формируем в чеке вторую строчку, где указываем процент скидки и сумму скидки со знаком минус
                    String totalForProduct = String.format(Locale.US ,"%.2f", Double.parseDouble(product[1])*Double.parseDouble(pair[1]));
                    String discountAmount = String.format(Locale.US ,"%.2f", Double.parseDouble(product[1])*Double.parseDouble(pair[1])*Integer.parseInt(product[4])/100);
                    body.append(CheckPattern.patternBody(pair[1], product[0], CheckPattern.currency + product[1], CheckPattern.currency + totalForProduct));
                    body.append(CheckPattern.lineBreakCharacter);
                    body.append(CheckPattern.patternBody("", "discount", product[4]+"%", "" + CheckPattern.currency + "-" + discountAmount));
                    body.append(CheckPattern.lineBreakCharacter);

                    totalForProducts += Double.parseDouble(totalForProduct) - Double.parseDouble(discountAmount);

                    continue;
            }

            String totalForProduct = String.format(Locale.US ,"%.2f", Double.parseDouble(product[1])*Double.parseDouble(pair[1]));
            body.append(CheckPattern.patternBody(pair[1], product[0], CheckPattern.currency + product[1], CheckPattern.currency + totalForProduct));
            body.append(CheckPattern.lineBreakCharacter);

            totalForProducts += Double.parseDouble(totalForProduct);

        }

        return body.toString();
    }
}
