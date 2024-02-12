package com.example.orderingsystem;

public class OrderItem {
    private String name;
    private double totalBill;

    public OrderItem(String name, double totalBill) {
        this.name = name;
        this.totalBill = totalBill;
    }

    public String getName() {
        return name;
    }

    public double getTotalBill() {
        return totalBill;
    }
}
