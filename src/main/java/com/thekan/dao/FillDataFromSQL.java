package com.thekan.dao;

import com.thekan.entity.DiscountCard;
import com.thekan.entity.Product;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FillDataFromSQL {
    private static final String userName = "root";
    private static final String password = "090690";
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/test";
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    public static List<Product> fillProductList(List<String[]> order) {
        List<Product> products = new ArrayList<>();
        StringBuilder id = new StringBuilder();
        id.append("(");
        for (String[] idAndQty : order) {
            id.append(idAndQty[0]);
            id.append(", ");
        }
        id.delete(id.length()-2, id.length()-1);
        id.append(")");
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try(Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
                Statement statement = connection.createStatement()) {
                ResultSet resultSet =  statement.executeQuery("select * from products where id in " + id.toString());


                while(resultSet.next()){
                    products.add(new Product(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3),
                            resultSet.getDouble(4), resultSet.getBoolean(5), resultSet.getDouble(6), resultSet.getInt(7)));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public static DiscountCard findCard(String discountCardNumber) {
        DiscountCard discountCard = null;
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try(Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
                Statement statement = connection.createStatement()) {
                ResultSet resultSet =  statement.executeQuery("select procentDiscount from cards where id = " + discountCardNumber);


                if(resultSet.next()){
                    discountCard = new DiscountCard(discountCardNumber, resultSet.getInt(1));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discountCard;
    }
}


