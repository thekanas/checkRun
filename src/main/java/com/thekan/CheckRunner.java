package com.thekan;
import com.thekan.check.*;

public class CheckRunner {

    public static void main(String[] args) {
        String[] argss = new String[]{"1-1","2-2.4","3-3", "4-6", "5-2", "6-3", "card-678", "products.txt", "discountCard.txt"};
        //String[] argss = new String[]{"1-1","2-2.4","3-3", "4-6", "5-2", "6-3", "products.txt"};
        //String[] argss = new String[]{"4-6", "products.txt"};
        //String[] argss = new String[]{"4-6", "card-3456", "products.txt", "discountCard.txt"};


        LoadData data = new LoadData(argss);
        //Products products = new Products();
        //DiscountCards discountCards = new DiscountCards();
        Check check = new Check("CashCheck", "Pyaterochka","Sovetskaya 163","+375 33 222-33-55", "N124aab", new String[]{"QTY", "DESCRIPTION", "PRICE", "TOTAL"}, "-", 10,data);
        check.checkPrint();
    }
}
