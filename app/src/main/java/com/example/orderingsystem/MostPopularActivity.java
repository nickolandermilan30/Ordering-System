package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MostPopularActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_popular);

        ListView listView = findViewById(R.id.listView);

        // Initialize the adapter and manager (pass application context)
        OrderHistoryManager.initializeAdapter(getApplicationContext(), listView);

        // Get data from intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        double totalBill = intent.getDoubleExtra("totalBill", 0.0);

        // Add the order details using the manager
        OrderHistoryManager.addOrderDetails(getApplicationContext(), name, totalBill);
    }
}
