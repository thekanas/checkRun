package com.thekan;
import com.thekan.check.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;


public class CheckRunner {


    public static void main(String[] args) {
        //String[] argss = new String[]{"1-1","2-2.4","3-3", "4-6", "5-2", "6-3", "card-678", "products.txt", "discountCard.txt"};
        String[] argss = new String[]{"1-1","2-2.4","3-3", "4-6", "5-2", "6-3", "products.txt"};
        //String[] argss = new String[]{"4-6", "products.txt"};
        //String[] argss = new String[]{"4-6", "card-3456", "products.txt", "discountCard.txt"};


        LoadData data = new LoadData(argss);
        //Products products = new Products();
        //DiscountCards discountCards = new DiscountCards();
        Check check = new Check("CashCheck", "Pyaterochka","id1234",new Date(),"-", new String[]{"qty", "name", "price", "total"},data);
        check.checkPrint();
    }
}
