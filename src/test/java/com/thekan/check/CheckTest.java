package com.thekan.check;

import com.thekan.LoadData;
import com.thekan.entity.DiscountCard;
import com.thekan.entity.DiscountCards;
import com.thekan.entity.Product;
import com.thekan.entity.Products;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckTest {

    Product product1 = new Product("1a", "Banana", 2.00, 0.05, true, 1.5, 10);
    Product product2 = new Product("2a", "Bread", 3.00, 0.10);
    ArrayList<Product> listProduct = new ArrayList<>();

    Products products;

    DiscountCard discountCard = new DiscountCard("13a", 16);
    List<DiscountCard> listDiscountCard = new ArrayList<>();

    DiscountCards discountCards;

    List<String[]> order = new ArrayList<>();

    LoadData loadData;

    Check check;

    @BeforeEach
    void setUp() {
        CheckPattern.widthOfCheckInChar = 40;
        CheckPattern.patternBody = "%-10s%-10.9s%-10s%10s";
        CheckPattern.patternInfoOneColumn = "%-40s";
        CheckPattern.patternInfoTwoColumn = "%-20s%20s";
        CheckPattern.patternForSeparators = "%-40s";

        listProduct.add(product1);
        listProduct.add(product2);

        order.add(new String[]{"1a", "2"});
        order.add(new String[]{"2a", "2"});

        products = new Products(listProduct);
        listDiscountCard.add(discountCard);
        discountCards = new DiscountCards(listDiscountCard);

        loadData = new LoadData(products, discountCard, true, order, "13a");

        check = new Check("title", "storeName", "storeAddress", "storeTelephone", "idCashier", new String[]{"1","2","3","4"},"*",loadData);


    }

    @Test
    void columnsLoad() {
        assertEquals ("1         2         3                  4", check.columnsLoad(new String[]{"1","2","3","4"}));
    }

    @Test
    void separator() {
        assertEquals ("****************************************" + CheckPattern.lineBreakCharacter, check.separator("*"));
        assertEquals ("", check.separator(null));
    }

    @Test
    void emptyStr() {
        assertEquals ("                                        " + CheckPattern.lineBreakCharacter, check.separator(" "));
    }

    @Test
    void isNotNullOrEmpty() {
        assertTrue(check.isNotNullOrEmpty("a"));
        assertFalse(check.isNotNullOrEmpty(""));
        assertFalse(check.isNotNullOrEmpty(null));
    }

    @Test
    void printLineCenter() {
        assertEquals ("                 title                  " + CheckPattern.lineBreakCharacter, check.printLineCenter("title"));
        assertEquals ("", check.printLineCenter(null));
    }

    @Test
    void printLine() {
        assertEquals ("a                                       " + CheckPattern.lineBreakCharacter, check.printLine("a"));
        assertEquals ("", check.printLine(null));
    }

    @Test
    void printLineTwoColums() {
        assertEquals ("ab                                    cd" + CheckPattern.lineBreakCharacter, check.printLineTwoColums("ab", "cd"));
    }

    @Test
    void checkPrint() {
        LocalDateTime now = LocalDateTime.now();
        assertEquals ("****************************************" + CheckPattern.lineBreakCharacter +
                              "                 title                  " + CheckPattern.lineBreakCharacter +
                              "               storeName                " + CheckPattern.lineBreakCharacter +
                              "              storeAddress              " + CheckPattern.lineBreakCharacter +
                              "             storeTelephone             " + CheckPattern.lineBreakCharacter +
                              "                                        " + CheckPattern.lineBreakCharacter +
                              "Cashier: idCashier       DATE : " + CheckPattern.date.format(now) + CheckPattern.lineBreakCharacter +
                              "                         TIME : " + CheckPattern.time.format(now) + CheckPattern.lineBreakCharacter +
                              "                                        " + CheckPattern.lineBreakCharacter +
                              "****************************************" + CheckPattern.lineBreakCharacter +
                              "1         2         3                  4" + CheckPattern.lineBreakCharacter +
                              "****************************************" + CheckPattern.lineBreakCharacter +
                              "2         Banana    $2.0           $4.00" + CheckPattern.lineBreakCharacter +
                              "          discount  10%           $-0.40" + CheckPattern.lineBreakCharacter +
                              "2         Bread     $3.0           $6.00" + CheckPattern.lineBreakCharacter +
                              "****************************************" + CheckPattern.lineBreakCharacter +
                              "****************************************" + CheckPattern.lineBreakCharacter +
                              "TAXABLE TOT.                        $9.6" + CheckPattern.lineBreakCharacter +
                              "VAT                                $0.29" + CheckPattern.lineBreakCharacter +
                              "TOTAL                              $9.89" + CheckPattern.lineBreakCharacter +
                              "Discount Card: 13a  16%                 " + CheckPattern.lineBreakCharacter +
                              "Discounted Total:                  $8.31" + CheckPattern.lineBreakCharacter +
                              "****************************************" + CheckPattern.lineBreakCharacter,  check.checkPrint());
    }
}