package com.thekan.check;


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

    public String bodyLoad(){
        StringBuilder body = new StringBuilder();
        int i = 0;
        for(String[] pair : order){
            //!добавить проверку наличия индекса в коллекции продуктов
            String[] product = products.getProducts().get(pair[0]);

            //если текущий продукт на акции и условие акции выполнено
            if(product.length>2 && product[2].equalsIgnoreCase("true") && Integer.parseInt(pair[1])>=Integer.parseInt(product[3])){
                //то формируем в чеке вторую строчку, где указываем размер скидки и сумму со скидкой
                String totalForProduct = String.format(Locale.US ,"%.2f", Double.parseDouble(product[1])*Double.parseDouble(pair[1])*(100-Integer.parseInt(product[4]))/100);
                body.append(CheckPattern.patternBody(pair[1], product[0], CheckPattern.currency + product[1], ""));
                body.append("\n");
                body.append(CheckPattern.patternBody("", "discount", product[4]+"%", "" + CheckPattern.currency + totalForProduct));
                body.append("\n");

                totalForProducts += Double.parseDouble(totalForProduct);
                i++;
                continue;
            }

            String totalForProduct = String.format(Locale.US ,"%.2f", Double.parseDouble(product[1])*Double.parseDouble(pair[1]));
            body.append(CheckPattern.patternBody(pair[1], product[0], CheckPattern.currency + product[1], CheckPattern.currency + totalForProduct));
            body.append("\n");

            totalForProducts += Double.parseDouble(totalForProduct);
            i++;
        }

        return body.toString();
    }
}
