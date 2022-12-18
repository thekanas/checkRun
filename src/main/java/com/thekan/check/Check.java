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
    private int vatProcent;

    private String body;
    private String taxableTotal;
    private String vat;
    private String total;
    private String discountedTotal;
    private boolean isDiscountCardPresent;
    private String discountCardNumber;
    private int discountCardDiscount;

    public Check(String title, String storeName, String storeAddress, String storeTelephone, String idCashier, String[] columnName, String separator, int vatProcent, LoadData loadData) {

        this.title = title;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeTelephone = storeTelephone;
        this.idCashier =idCashier;
        this.columnName = columnName;
        this.separator = separator;
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
        if (separator == null || separator.isEmpty()){
            return "";
        }
        StringBuilder separators = new StringBuilder();
        for(int i = 0; i<CheckPattern.widthOfCheckInChar; i++){
            separators.append(separator);
        }

        return CheckPattern.patternSeparators(separators.toString()) + CheckPattern.lineBreakCharacter;
    }

    public String emptyStr(){
        return separator(" ");
    }

    public String checkPrint(){

        StringBuilder check = new StringBuilder();

        check.append(separator(separator));

        if (title != null && !title.isEmpty())
            check.append(CheckPattern.patternInfoCenter(title)).append(CheckPattern.lineBreakCharacter);

        if (storeName != null && !storeName.isEmpty())
            check.append(CheckPattern.patternInfoCenter(storeName)).append(CheckPattern.lineBreakCharacter);

        if (storeAddress != null && !storeAddress.isEmpty())
            check.append(CheckPattern.patternInfoCenter(storeAddress)).append(CheckPattern.lineBreakCharacter);

        if (storeTelephone != null && !storeTelephone.isEmpty())
            check.append(CheckPattern.patternInfoCenter(storeTelephone)).append(CheckPattern.lineBreakCharacter);

        check.append(emptyStr());

        LocalDateTime now = LocalDateTime.now();

        String idCashierNumber = "";
        if (idCashier != null && !idCashier.isEmpty())
            idCashierNumber = "Cashier: " + idCashier;

        check.append(CheckPattern.patternInfo(idCashierNumber, "DATE : " + CheckPattern.date.format(now))).append(CheckPattern.lineBreakCharacter);

        check.append(CheckPattern.patternInfo(" ","TIME : " + CheckPattern.time.format(now))).append(CheckPattern.lineBreakCharacter);

        check.append(emptyStr());

        check.append(separator(separator));

        if (columnName.length >= 4)
            check.append(columnsLoad(columnName)).append(CheckPattern.lineBreakCharacter);

        check.append(separator(separator));

        check.append(body);

        check.append(separator(separator));
        check.append(separator(separator));

        if(vatProcent > 0) {
            check.append(CheckPattern.patternInfo("TAXABLETOT  ", taxableTotal)).append(CheckPattern.lineBreakCharacter);
            check.append(CheckPattern.patternInfo("VAT" + vatProcent + "%", vat)).append(CheckPattern.lineBreakCharacter);
        }

        check.append(CheckPattern.patternInfo("TOTAL  ", total)).append(CheckPattern.lineBreakCharacter);

        if(isDiscountCardPresent){
            check.append(CheckPattern.patternInfo("Discount Card: " + discountCardNumber + "  " + discountCardDiscount + "%")).append(CheckPattern.lineBreakCharacter);
            check.append(CheckPattern.patternInfo("Discounted Total: ", discountedTotal)).append(CheckPattern.lineBreakCharacter);
        }

        check.append(separator(separator));

        ConsoleHelper.print(check.toString());
        return check.toString();
    }
}
