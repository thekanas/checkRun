package com.thekan;
import com.thekan.check.*;

public class CheckRunner {

    public static void main(String[] args) {
        //String[] argss = new String[]{"1-1","2-2.4","3-3", "4-6", "5-2", "6-3", "card-2a", "products.txt", "discountCards.txt"};
        //String[] argss = new String[]{"1-1","2-2.4","3-3", "4-6", "5-2", "6-3", "products.txt"};
        //String[] argss = new String[]{"4-6", "products.txt"};
        //String[] argss = new String[]{"4-6", "card-3456", "products.txt", "discountCard.txt"};

        LoadData data = new LoadData(args);

        Check check = new CheckBuilder(data)
                .setTitle("CashCheck")
                .setStoreName("Pyaterochka")
                .setStoreAddress("Sovetskaya 163")
                .setStoreTelephone("+375 33 222-33-55")
                .setIdCashier("N124aab")
                .setColumnName(new String[]{"QTY", "DESCRIPTION", "PRICE", "TOTAL"})
                .setSeparator("-")
                .setVatProcent(10)
                .checkBuild();

        ConsoleHelper.writeToFile(check.checkPrint(), "check.txt");
    }
}
