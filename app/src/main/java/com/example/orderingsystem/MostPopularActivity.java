package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

// MostPopularActivity.java
public class MostPopularActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_popular);

        ListView listView = findViewById(R.id.listView);

        // Initialize the adapter and manager
        OrderHistoryManager.initializeAdapter(getApplicationContext(), listView);

        // Get data from intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        double totalBill = intent.getDoubleExtra("totalBill", 0.0);

        // Add the order details using the manager
        OrderHistoryManager.addOrderDetails(getApplicationContext(), name, totalBill);

        // Find the "Back" button by id
        ImageButton backButton = findViewById(R.id.back);

        // Set a click listener for the "Back" button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

