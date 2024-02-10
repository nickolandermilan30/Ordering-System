package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ResActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);

        ImageButton doneButton = findViewById(R.id.done);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data passed from Fav activity
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    String name = extras.getString("name");
                    int tableNumber = extras.getInt("tableNumber");
                    ArrayList<FoodItem> itemList = extras.getParcelableArrayList("itemList");
                    double totalBill = extras.getDouble("totalBill");

                    // Start the CheckActivity and pass the necessary data
                    Intent intent = new Intent(ResActivity.this, Check.class);
                    intent.putExtra("name", name);
                    intent.putExtra("tableNumber", tableNumber);
                    intent.putParcelableArrayListExtra("itemList", itemList);
                    intent.putExtra("totalBill", totalBill);
                    startActivity(intent);
                }
            }
        });

        // Retrieve data passed from Fav activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name");
            int tableNumber = extras.getInt("tableNumber");
            ArrayList<FoodItem> itemList = extras.getParcelableArrayList("itemList");
            double totalBill = extras.getDouble("totalBill");

            // Update UI with the retrieved data
            updateUI(name, tableNumber, itemList, totalBill);
        }
    }


    private void updateUI(String name, int tableNumber, ArrayList<FoodItem> itemList, double totalBill) {
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView tableNumberTextView = findViewById(R.id.tableNumberTextView);
        TextView orderDetailsTextView = findViewById(R.id.orderDetailsTextView);
        TextView totalBillTextView = findViewById(R.id.totalBillTextView);

        // Display name and table number
        nameTextView.setText(name);
        tableNumberTextView.setText(String.valueOf(tableNumber));

        // Display ordered items, prices, and images
        StringBuilder orderDetails = new StringBuilder("Order Details:\n");
        LinearLayout imageContainer = findViewById(R.id.imageContainer);
        imageContainer.removeAllViews(); // Clear existing image views

        for (FoodItem item : itemList) {
            orderDetails.append(item.getFoodName()).append(" - ")
                    .append(item.getPrice()).append("\n");

            // Create and add ImageView dynamically
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(item.getImageResource());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    200, 200); // Set the size of the image view
            layoutParams.setMargins(0, 8, 0, 0);
            imageView.setLayoutParams(layoutParams);
            imageContainer.addView(imageView);
        }

        orderDetailsTextView.setText(orderDetails.toString());

        // Display total bill
        totalBillTextView.setText("Php " + String.format("%.2f", totalBill));
    }
}