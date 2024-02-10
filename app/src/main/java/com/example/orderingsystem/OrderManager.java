package com.example.orderingsystem;

import java.util.ArrayList;


public class OrderManager {
    private static OrderManager instance;
    private ArrayList<String> orderNamesList;

    private OrderManager() {
        orderNamesList = new ArrayList<>();
    }

    public static synchronized OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public ArrayList<String> getOrderNamesList() {
        return orderNamesList;
    }

    public void addOrderName(String orderName) {
        orderNamesList.add(orderName);
    }

    // Add any other methods or functionality you need
}
