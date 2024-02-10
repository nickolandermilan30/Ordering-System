package com.example.orderingsystem;


public class OrderItem {

    private String orderName;
    private String orderDetails;

    public OrderItem(String orderName, String orderDetails) {
        this.orderName = orderName;
        this.orderDetails = orderDetails;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getOrderDetails() {
        return orderDetails;
    }
}
