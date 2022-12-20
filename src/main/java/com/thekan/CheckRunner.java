package com.thekan;
import com.thekan.check.*;

public class CheckRunner {

    public static void main(String[] args) {


        LoadData data = new LoadData(args);

        Check check = new CheckBuilder(data)
                .setTitle("CashCheck")
                .setStoreName("Pyaterochka")
                .setStoreAddress("Sovetskaya 163")
                .setStoreTelephone("+375 33 222-33-55")
                .setIdCashier("N124aab")
                .setColumnName(new String[]{"QTY", "DESCRIPTION", "PRICE", "TOTAL"})
                .setSeparator("-")
                .checkBuild();

        ConsoleHelper.writeToFile(check.checkPrint(), "check.txt");
    }
}
