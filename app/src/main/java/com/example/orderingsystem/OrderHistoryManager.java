package com.example.orderingsystem;

import android.widget.ListView;

import java.util.ArrayList;

public class OrderHistoryManager {

    private static ArrayList<String> orderHistoryList = new ArrayList<>();
    private static OrderHistoryAdapter adapter;

    public static void initializeAdapter(ListView listView) {
        adapter = new OrderHistoryAdapter(listView.getContext(), orderHistoryList);
        listView.setAdapter(adapter);
    }

    public static void addOrderDetails(String name, double totalBill) {
        // Check if the list is empty before adding the first order
        if (orderHistoryList.isEmpty() && name == null) {
            return; // Skip adding the initial "null - Php 0.00" entry
        }

        // Skip adding the entry when the name is null
        if (name == null) {
            return;
        }

        String orderDetails = name + " - Php " + String.format("%.2f", totalBill);
        orderHistoryList.add(orderDetails);

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }


}
