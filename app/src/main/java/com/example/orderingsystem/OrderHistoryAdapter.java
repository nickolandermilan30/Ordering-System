package com.example.orderingsystem;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

// OrderHistoryAdapter.java
public class OrderHistoryAdapter extends ArrayAdapter<String> {

    public OrderHistoryAdapter(Context context, ArrayList<String> orderHistoryList) {
        super(context, 0, orderHistoryList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String orderDetails = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_bill, parent, false);
        }

        TextView orderDetailsTextView = convertView.findViewById(R.id.orderDetailsTextView);
        TextView totalBillTextView = convertView.findViewById(R.id.totalBillTextView);
        ImageButton deleteButton = convertView.findViewById(R.id.delete);

        // Set custom font family
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.balsamiq_sans);

        // Assuming orderDetails format is "Tianshu Meat - Php 25.00"
        String[] parts = orderDetails.split(" - Php ");
        if (parts.length == 2) {
            String itemName = parts[0];
            double totalBill = Double.parseDouble(parts[1]);

            // Set the text for the item name and total bill
            orderDetailsTextView.setText(itemName);
            orderDetailsTextView.setTypeface(typeface); // Set font family
            totalBillTextView.setText("Total Bill: Php " + String.format("%.2f", totalBill));
            totalBillTextView.setTypeface(typeface); // Set font family
        }

        // Set delete button click listener
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call a method in the manager to delete the item
                OrderHistoryManager.deleteOrderDetails(getContext(), orderDetails);
            }
        });

        return convertView;
    }

}

