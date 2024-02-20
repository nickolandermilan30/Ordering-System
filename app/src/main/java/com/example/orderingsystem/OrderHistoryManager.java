package com.example.orderingsystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class OrderHistoryManager {

    private static final String ORDER_HISTORY_PREF = "orderHistoryPref";
    private static final String ORDER_HISTORY_KEY = "orderHistoryKey";

    private static ArrayList<String> orderHistoryList;
    private static OrderHistoryAdapter adapter;

    public static void initializeAdapter(Context context, ListView listView) {
        if (orderHistoryList == null) {
            // Load order history from SharedPreferences if not loaded yet
            loadOrderHistory(context);
        }

        adapter = new OrderHistoryAdapter(context, orderHistoryList);
        listView.setAdapter(adapter);
    }

    public static void addOrderDetails(Context context, String name, double totalBill) {
        if (orderHistoryList == null) {
            orderHistoryList = new ArrayList<>();
            // Load order history from SharedPreferences if not loaded yet
            loadOrderHistory(context);
        }

        // Skip adding the entry when the name is null
        if (name == null) {
            return;
        }

        String orderDetails = name + " - Php " + String.format("%.2f", totalBill);
        orderHistoryList.add(orderDetails);

        // Save order history to SharedPreferences
        saveOrderHistory(context);

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private static void saveOrderHistory(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(ORDER_HISTORY_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Set<String> orderHistorySet = new HashSet<>(orderHistoryList);
        editor.putStringSet(ORDER_HISTORY_KEY, orderHistorySet);

        editor.apply();
    }

    private static void loadOrderHistory(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(ORDER_HISTORY_PREF, Context.MODE_PRIVATE);
        Set<String> orderHistorySet = preferences.getStringSet(ORDER_HISTORY_KEY, new HashSet<>());

        orderHistoryList = new ArrayList<>(orderHistorySet);
    }
}
