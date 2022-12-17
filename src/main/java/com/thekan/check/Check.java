package com.thekan.check;

import java.time.LocalDateTime;
import java.util.Locale;
import com.thekan.ConsoleHelper;
import com.thekan.LoadData;

public class Check {

    private String title;
    private String storeName;
    private String storeАddress;
    private String storeTelephone;
    private String idCashier;
    private String columnName;
    private String separator;
    private int vatProcent;

    private String body;
    private String taxableTotal;
    private String vat;
    private String total;
    private String discountedTotal;
    private boolean isDiscountCardPresent;
    private String discountCardNumber;
    private int discountCardDiscount;

    public Check(String title, String storeName, String storeАddress, String storeTelephone, String idCashier, String[] columnName, String separator, int vatProcent, LoadData loadData) {

        this.title = title;
        this.storeName = storeName;
        this.storeАddress = storeАddress;
        this.storeTelephone = storeTelephone;
        this.idCashier =idCashier;
        this.columnName = columnsLoad(columnName);
        this.separator = separator(separator);
        this.vatProcent = vatProcent;

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
        return CheckPattern.patternSeparators(separators.toString());
    }

    public String emptyStr(){
        return separator(" ");
    }

    public String checkPrint(){

        StringBuilder check = new StringBuilder();

        check.append(separator).append("\n");

        if (!title.isEmpty())
            check.append(CheckPattern.patternInfoCenter(title)).append("\n");

        if (!storeName.isEmpty())
            check.append(CheckPattern.patternInfoCenter(storeName)).append("\n");

        if (!storeАddress.isEmpty())
            check.append(CheckPattern.patternInfoCenter(storeАddress)).append("\n");

        if (!storeTelephone.isEmpty())
            check.append(CheckPattern.patternInfoCenter(storeTelephone)).append("\n");

        check.append(emptyStr()).append("\n");

        LocalDateTime now = LocalDateTime.now();

        if (!idCashier.isEmpty())
            check.append(CheckPattern.patternInfo("Cashier: " + idCashier, "DATE : " + CheckPattern.date.format(now))).append("\n");

        check.append(CheckPattern.patternInfo(" ","TIME : " + CheckPattern.time.format(now))).append("\n");

        check.append(emptyStr()).append("\n");

        check.append(separator).append("\n");

        if (!columnName.isEmpty())
            check.append(columnName).append("\n");

        check.append(separator).append("\n");

        check.append(body);

        check.append(separator).append("\n");

        check.append(CheckPattern.patternInfo("TAXABLETOT  ", taxableTotal)).append("\n");

        check.append(CheckPattern.patternInfo("VAT" + vatProcent, vat)).append("\n");

        check.append(CheckPattern.patternInfo("TOTAL  ", total)).append("\n");

        if(isDiscountCardPresent){
            check.append(CheckPattern.patternInfo("Discount Card: " + discountCardNumber + "  " + discountCardDiscount + "%")).append("\n");
            check.append(CheckPattern.patternInfo("Discounted Total: ", discountedTotal)).append("\n");
        }

        check.append(separator).append("\n");

        ConsoleHelper.print(check.toString());
        return check.toString();
    }
}
