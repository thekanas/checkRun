package com.thekan.check;

import com.thekan.LoadData;
import com.thekan.entity.DiscountCard;
import com.thekan.entity.DiscountCards;
import com.thekan.entity.Product;
import com.thekan.entity.Products;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckBodyCreateTest {

    Product product1 = new Product("1a", "Banana", 2.00, 0.05, true, 1.5, 10);
    Product product2 = new Product("2a", "Bread", 3.00, 0.10);
    List<Product> listProduct = new ArrayList<>();

    Products products;

    List<String[]> order = new ArrayList<>();

    LoadData loadData;

    CheckBodyCreate checkBodyCreate;

    @BeforeEach
    void setUp() throws Exception {
        listProduct.add(product1);
        listProduct.add(product2);

        order.add(new String[]{"1a", "2"});
        order.add(new String[]{"2a", "2"});

        products = new Products(listProduct);

        loadData = new LoadData(products, null, false, order, null);

        checkBodyCreate = new CheckBodyCreate(loadData);

    }


    @Test
    void getTotalForProducts() throws Exception {
        checkBodyCreate.bodyLoad();
        assertEquals (9.6, checkBodyCreate.getTotalForProducts(), 0.0001);
    }

    @Test
    void getTotalForTax() throws Exception {
        checkBodyCreate.bodyLoad();
        assertEquals(0.29, checkBodyCreate.getTotalForTax(), 0.0001);
    }

    @Test
    void bodyLoad() throws Exception {

        CheckPattern.currency = "$";
        CheckPattern.patternBody = "%-5s%-14.13s%-7s%8s";
        CheckPattern.lineBreakCharacter = "\r\n";

        assertEquals("2    Banana        $2.0      $4.00" + CheckPattern.lineBreakCharacter +
                             "     discount      10%      $-0.40" + CheckPattern.lineBreakCharacter +
                             "2    Bread         $3.0      $6.00" + CheckPattern.lineBreakCharacter, checkBodyCreate.bodyLoad());

    }
}