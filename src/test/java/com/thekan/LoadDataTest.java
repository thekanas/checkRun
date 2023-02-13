package com.thekan;

import com.thekan.entity.DiscountCard;
import com.thekan.entity.DiscountCards;
import com.thekan.entity.Product;
import com.thekan.entity.Products;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoadDataTest {
    
    Product product = new Product("1a", "Banana", 1.99, 0.05, true, 1.5, 10);
    ArrayList<Product> listProduct = new ArrayList<>();

    Products products = new Products(listProduct);
    
    DiscountCard discountCard = new DiscountCard("13a", 16);
    List<DiscountCard> listDiscountCard = new ArrayList<>();

    //DiscountCards discountCards = new DiscountCards(listDiscountCard);
    
    List<String[]> order = new ArrayList<>();

    LoadData loadData;

    @BeforeEach
    void setUp(){
        listProduct.add(product);
        listDiscountCard.add(discountCard);
        order.add(new String[]{"1a", "5"});
        loadData = new LoadData(products, discountCard, true, order, "13a");
    }


        

    @Test
    void orderLoad() {
        List<String[]> order1 = new ArrayList<>();
        order1.add(new String[]{"1", "1"});
        order1.add(new String[]{"2", "2"});
        order1.add(new String[]{"3", "3"});
        loadData.orderLoad(new String[]{"1-1", "2-2", "3-3", "4-4"}, 3);

        assertArrayEquals (order1.get(0), loadData.getOrder().get(0));
        assertArrayEquals (order1.get(1), loadData.getOrder().get(1));
        assertArrayEquals (order1.get(2), loadData.getOrder().get(2));
    }

    @Test
    void getProducts() {
        assertEquals (products, loadData.getProducts());
    }

    @Test
    void getDiscountCards() {
        assertEquals (discountCard, loadData.getDiscountCard());
    }

    @Test
    void getOrder() {
        List<String[]> order2 = new ArrayList<>();
        order2.add(new String[]{"1a", "5"});
        order2.add(new String[]{"2a", "1"});
        loadData.orderLoad(new String[]{"1a-5", "2a-1", "3-3", "4-4"}, 2);

        assertArrayEquals (order2.get(0), loadData.getOrder().get(0));
        assertArrayEquals (order2.get(1), loadData.getOrder().get(1));

    }

    @Test
    void getDiscountCardNumber() {
        assertEquals (discountCard.getId(), loadData.getDiscountCardNumber());
    }

    @Test
    void isDiscountCardPresent() {
        assertTrue(loadData.isDiscountCardPresent());
    }
}
