package com.thekan;
import com.thekan.check.*;

public class CheckRunner {

    public static void main(String[] args)  {


        String[] args1 = new String[]{"1-2", "3-1", "4-6", "5-8", "card-3"};
        LoadData data = new LoadData(args1);

        Check check = new CheckBuilder(data)
                .setTitle("CashCheck")
                .setStoreName("Pyaterochka")
                .setStoreAddress("Sovetskaya 163")
                .setStoreTelephone("+375 33 222-33-55")
                .setIdCashier("N124aab")
                .setColumnName(new String[]{"QTY", "DESCRIPTION", "PRICE", "TOTAL"})
                .setSeparator("-")
                .checkBuild();

        ConsoleHelper.writeToFile(check.checkPrint(), CheckPattern.patch);
    }
}
