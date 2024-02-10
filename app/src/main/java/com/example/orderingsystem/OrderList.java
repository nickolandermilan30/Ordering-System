package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OrderList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        // Find TextViews in the layout
        TextView orderNameTextView = findViewById(R.id.orderNameTextView);
        TextView tableNameTextView = findViewById(R.id.tableNameTextView);
        TextView orderDetailsTextView = findViewById(R.id.orderDetailsTextView);
        TextView totalBillTextView = findViewById(R.id.totalBillTextView);

        // Retrieve data from the intent
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            int tableNumber = intent.getIntExtra("tableNumber", 0);
            ArrayList<FoodItem> itemList = intent.getParcelableArrayListExtra("itemList");
            double totalBill = intent.getDoubleExtra("totalBill", 0.0);
            String orderName = intent.getStringExtra("orderName");

            // Display the retrieved data in the TextViews
            orderNameTextView.setText("Order: " + orderName);
            tableNameTextView.setText("Table: " + tableNumber);

            // Build the order details string
            StringBuilder orderDetails = new StringBuilder("Order Details:\n");
            for (FoodItem item : itemList) {
                orderDetails.append(item.getFoodName()).append(" - ")
                        .append(item.getPrice()).append("\n");
            }
            orderDetailsTextView.setText(orderDetails.toString());

            totalBillTextView.setText("Total Bill: Php" + String.format("%.2f", totalBill));
        }
    }
}

