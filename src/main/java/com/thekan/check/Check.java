package com.thekan.check;

import java.util.Date;
import java.util.Locale;

import com.thekan.DiscountCards;
import com.thekan.LoadData;
import com.thekan.Products;


public class Check {

    private String title;
    private String infoStore;
    private String idCashier;
    private Date data;
    private String separator;
    private String columnName;
    private String body;
    private String taxableTotal;
    private int vatProcent = 10;
    private String vat;
    private String total;
    private String discountedTotal;
    private boolean isDiscountCardPresent;
    private String discountCardNumber;
    private int discountCardDiscount;

    public Check(String title, String infoStore, String idCashier, Date data, String separator, String[] columnName, LoadData loadData) {
        this.title = title;
        this.infoStore = infoStore;
        this.idCashier ="Cashier:" + idCashier;
        this.data = data;
        this.separator = separator(separator);
        this.columnName = columnsLoad(columnName);

        CheckBodyCreate checkBody = new CheckBodyCreate(loadData);
        this.body = checkBody.bodyLoad();
        this.taxableTotal =CheckPattern.currency + checkBody.getTotalForProducts();
        String vat = String.format(Locale.US ,"%.2f", (checkBody.getTotalForProducts()*vatProcent/100));
        this.vat = CheckPattern.currency + vat;
        String total = String.format(Locale.US ,"%.2f", (checkBody.getTotalForProducts() + Double.parseDouble(vat)));
        this.total = CheckPattern.currency + total;
        this.isDiscountCardPresent = loadData.isDiscountCardPresent();
        this.discountCardNumber = loadData.getDiscountCardNumber();
        if(loadData.isDiscountCardPresent()) {
            discountCardDiscount = loadData.getDiscountCards().getDiscountCards().get(loadData.getDiscountCardNumber());
            discountedTotal = String.format(Locale.US ,CheckPattern.currency + "%.2f", (Double.parseDouble(total)*(100-discountCardDiscount)/100));
        }


    }



    public String columnsLoad(String[] s){
        String columns = CheckPattern.patternBody(s[0] , s[1], s[2], s[3]);
        return columns;
    }

    public String separator(String separator){
        StringBuilder separators = new StringBuilder();
        for(int i = 0; i<CheckPattern.widthOfCheckInChar; i++){
            separators.append(separator);
        }
        return separators.toString();
    }

    public void checkPrint(){
        System.out.println(separator);
        System.out.println(title);
        System.out.println(infoStore);
        System.out.println(idCashier);
        System.out.println(separator);
        System.out.println(columnName);
        System.out.println(body);
        System.out.println(separator);
        System.out.println(separator);
        System.out.println("TAXABLETOT  " + taxableTotal);
        System.out.println("VAT" + vatProcent + "  " + vat);
        System.out.println("TOTAL  " + total);
        System.out.println(separator);
        if(isDiscountCardPresent){
            System.out.println("Discount Card: " + discountCardNumber + "  " +  discountCardDiscount + "%");

            System.out.println("Discounted Total: " + discountedTotal);
        }
    }
}
