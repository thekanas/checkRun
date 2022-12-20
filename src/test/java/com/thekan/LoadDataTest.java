package com.thekan;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoadDataTest {
    
    Product product = new Product("1a", "Banana", 1.99, 0.05, true, 1.5, 10);
    List<Product> listProduct = new ArrayList<>();
    list.add(product);
    Products products = new Products(listProduct);
    
    DiscountCard discountCard = new DiscountCard("13a", 16);
    List<DiscountCard> listDiscountCard = new ArrayList<>();
    list.add(discountCard);
    DiscountCards discountCards = new DiscountCards(listDiscountCard);
    
    List<String[]> order = new ArrayList<>();
    order.add(new String{"1a", "5"};
        
    LoadData loadData = new LoadData(products, discountCards, true, order, "13a");
        

    @Test
    void orderLoad() {
        List<String[]> order1 = new ArrayList<>();
        order1.add(new String{"1", "1"};
        order1.add(new String{"2", "2"};
        assertIterableEquals (order1, loadData.orderLoad({"1-1", "2-2", "3-3", "4-4"}, 2));
    }

    @Test
    void getProducts() {
        assertEquals (products, loadData.getProducts());
    }

    @Test
    void getDiscountCards() {
        assertEquals (discountCards, loadData.getDiscountCards());
    }

    @Test
    void getOrder() {
        List<String[]> order2 = new ArrayList<>();
        order2.add(new String{"1a", "5"};
        order2.add(new String{"2a", "1"};
        loadData.orderLoad({"1a-5", "2a-1", "3-3", "4-4"}, 2)
        assertIterableEquals (order2, loadData.getOrder());
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
