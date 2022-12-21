package com.thekan;

import com.thekan.check.CheckPattern;
import com.thekan.entity.DiscountCards;
import com.thekan.entity.Products;

import java.util.ArrayList;
import java.util.List;

public class LoadData {
    //класс разделяет входящие данные на
    //список покупок, дисконтную карту, пути к файлам с товароми и с диск.картами
    private Products products;
    private DiscountCards discountCards;
    private boolean isDiscountCardPresent;
    private List<String[]> order = new ArrayList<>();
    private String discountCardNumber;

    public LoadData(Products products, DiscountCards discountCards, boolean isDiscountCardPresent, List<String[]> order, String discountCardNumber) {
        this.products = products;
        this.discountCards = discountCards;
        this.isDiscountCardPresent = isDiscountCardPresent;
        this.order = order;
        this.discountCardNumber = discountCardNumber;
    }

    public LoadData(String[] args){
        //принимаем что путь к файлу с товарами указывается в исходных данных всегда.
        //файл с дисконтными картами может быть не указан если дисконтная карта не предъявлена.

        //принимаем что после списка id товаров и их колличества
        //указываются следующие данные:
        //card-id - id номер дисконтной карты (не указывается если дисконтная карта не предъяевлена)
        //***.txt - путь к файлу с товарами c расширением .txt (указывается всегда)
        //***.txt - путь к файлу с дисконтными картами c расширением .txt (не указывается если дисконтная карта не предъяевлена)
        if ((args[args.length-2]).toLowerCase().endsWith(".txt")) {
            products = new Products(args[args.length-2]);

            if((args[args.length-3]).toLowerCase().startsWith("card-") && !(args[args.length-3]).equalsIgnoreCase("card-")) {
                isDiscountCardPresent = true;
                discountCardNumber = (args[args.length - 3]).substring(5);
                if ((args[args.length-1]).toLowerCase().endsWith(".txt")) {
                    discountCards = new DiscountCards(args[args.length-1]);
                } else {
                    ConsoleHelper.print("не указан файл с дисконтными картами");
                    ConsoleHelper.writeToFile("не указан файл с дисконтными картами", CheckPattern.patch);
                    System.exit(0);
                }
            }
            else {
                ConsoleHelper.print("не указан номер дисконтной карты");
                ConsoleHelper.writeToFile("не указан номер дисконтной карты", CheckPattern.patch);
                System.exit(0);
            }

            orderLoad(args, args.length-3);
        }
        else if((args[args.length-1]).toLowerCase().endsWith(".txt")) {
            products = new Products(args[args.length-1]);
            isDiscountCardPresent = false;
            orderLoad(args, args.length-1);
        }
        else {
            ConsoleHelper.print("не указан файл с товарами");
            ConsoleHelper.writeToFile("не указан файл с товарами", CheckPattern.patch);
            System.exit(0);
        }

    }

    public void orderLoad(String[] args, int toElement){
        for (int i = 0; i < toElement; i++ ){
            String[] pair = args[i].split("-");
            order.add(i, pair);
        }
    }

    public Products getProducts() {
        return products;
    }

    public DiscountCards getDiscountCards() {
        return discountCards;
    }

    public List<String[]> getOrder() {
        return order;
    }

    public String getDiscountCardNumber() {
        return discountCardNumber;
    }

    public boolean isDiscountCardPresent() {
        return isDiscountCardPresent;
    }
}
