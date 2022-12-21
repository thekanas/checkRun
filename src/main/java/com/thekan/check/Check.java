package com.thekan.check;

import java.time.LocalDateTime;
import java.util.Locale;
import com.thekan.ConsoleHelper;
import com.thekan.LoadData;

public class Check {

    private String title;
    private String storeName;
    private String storeAddress;
    private String storeTelephone;
    private String idCashier;
    private String[] columnName;
    private String separator;

    private String body;
    private String taxableTotal;
    private String vat;
    private String total;
    private String discountedTotal;
    private boolean isDiscountCardPresent;
    private String discountCardNumber;
    private int discountCardDiscount;

    public Check(String title, String storeName, String storeAddress, String storeTelephone, String idCashier, String[] columnName, String separator, LoadData loadData) {

        this.title = title;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeTelephone = storeTelephone;
        this.idCashier =idCashier;
        this.columnName = columnName;
        this.separator = separator;

        CheckBodyCreate checkBody = new CheckBodyCreate(loadData);

        try {
            this.body = checkBody.bodyLoad();
        } catch (Exception e) {
            ConsoleHelper.print(e.getMessage());
            ConsoleHelper.writeToFile(e.getMessage(), CheckPattern.patch);
            System.exit(0);
        }

        this.taxableTotal =CheckPattern.currency + checkBody.getTotalForProducts();
        String vat = String.format(Locale.US ,"%.2f", (checkBody.getTotalForTax()));
        this.vat = CheckPattern.currency + vat;
        String total = String.format(Locale.US ,"%.2f", (checkBody.getTotalForProducts() + Double.parseDouble(vat)));
        this.total = CheckPattern.currency + total;
        this.isDiscountCardPresent = loadData.isDiscountCardPresent();

        if(loadData.isDiscountCardPresent()) {
            try {
                this.discountCardNumber = loadData.getDiscountCards().getDiscountCard(loadData.getDiscountCardNumber()).getId();
            } catch (Exception e) {
                ConsoleHelper.print("карты с id " + loadData.getDiscountCardNumber() + " нет в базе");
                ConsoleHelper.print(e.getMessage());
                ConsoleHelper.writeToFile("карты с id " + loadData.getDiscountCardNumber() + " нет в базе" + CheckPattern.lineBreakCharacter + e.getMessage(), CheckPattern.patch);
                System.exit(0);
            }
            discountCardDiscount = loadData.getDiscountCards().getDiscountCard(discountCardNumber).getProcentDiscount();
            discountedTotal = String.format(Locale.US ,CheckPattern.currency + "%.2f", (Double.parseDouble(total)*(100-discountCardDiscount)/100));
        }
    }

    public String columnsLoad(String[] s){
        String columns = CheckPattern.patternBody(s[0] , s[1], s[2], s[3]);
        return columns;
    }

    public String separator(String separator){
        if (separator == null || separator.isEmpty()){
            return "";
        }

        return CheckPattern.patternSeparators(separator.repeat(CheckPattern.widthOfCheckInChar)) + CheckPattern.lineBreakCharacter;
    }

    public String emptyStr(){
        return separator(" ");
    }

    public boolean isNotNullOrEmpty(String s){
        if (s != null && !s.isEmpty())
            return true;
        return false;
    }

    public String printLineCenter(String s){
        if (isNotNullOrEmpty(s))
            return CheckPattern.patternInfoCenter(s) + CheckPattern.lineBreakCharacter;
        return "";
    }

    public String printLine(String s){
        if (isNotNullOrEmpty(s))
            return CheckPattern.patternInfo(s) + CheckPattern.lineBreakCharacter;
        return "";
    }

    public String printLineTwoColums(String s1, String s2){
        return CheckPattern.patternInfo(s1, s2) + CheckPattern.lineBreakCharacter;

    }

    public String checkPrint() {
        StringBuilder check = new StringBuilder();

        check.append(separator(separator));
        check.append(printLineCenter(title));
        check.append(printLineCenter(storeName));
        check.append(printLineCenter(storeAddress));
        check.append(printLineCenter(storeTelephone));

        check.append(emptyStr());

        LocalDateTime now = LocalDateTime.now();

        String idCashierNumber = "";
        if (idCashier != null && !idCashier.isEmpty())
            idCashierNumber = "Cashier: " + idCashier;

        check.append(printLineTwoColums(idCashierNumber, "DATE : " + CheckPattern.date.format(now)));
        check.append(printLineTwoColums(" ", "TIME : " + CheckPattern.time.format(now)));

        check.append(emptyStr());
        check.append(separator(separator));

        if (columnName!=null && columnName.length >= 4)
            check.append(columnsLoad(columnName)).append(CheckPattern.lineBreakCharacter);

        check.append(separator(separator));

        check.append(body);

        check.append(separator(separator));
        check.append(separator(separator));

        check.append(printLineTwoColums("TAXABLE TOT.", taxableTotal));
        check.append(printLineTwoColums("VAT", vat));
        check.append(printLineTwoColums("TOTAL", total));

        if (isDiscountCardPresent) {
            check.append(printLine("Discount Card: " + discountCardNumber + "  " + discountCardDiscount + "%"));
            check.append(printLineTwoColums("Discounted Total:", discountedTotal));
        }

        check.append(separator(separator));

        ConsoleHelper.print(check.toString());
        return check.toString();
    }

}
