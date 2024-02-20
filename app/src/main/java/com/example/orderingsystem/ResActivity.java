package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ResActivity extends AppCompatActivity {

    private String name; // Declare as a class-level field
    private double totalBill; // Declare as a class-level field

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);

        ImageButton doneButton = findViewById(R.id.done);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a custom dialogue
                showCustomDialogue();
            }
        });

        // Retrieve data passed from Fav activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name"); // Assign value to class-level field
            int tableNumber = extras.getInt("tableNumber");
            ArrayList<FoodItem> itemList = extras.getParcelableArrayList("itemList");
            totalBill = extras.getDouble("totalBill"); // Assign value to class-level field

            // Update UI with the retrieved data
            updateUI(name, tableNumber, itemList, totalBill);
        }
    }

    private void showCustomDialogue() {
        // Inflate the custom layout
        View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog_layout, null);

        // Initialize views from the custom layout
        TextView dialogTitle = dialogView.findViewById(R.id.dialogTitle);
        TextView dialogMessage = dialogView.findViewById(R.id.dialogMessage);
        Button okButton = dialogView.findViewById(R.id.okButton);

        // Set title and message
        dialogTitle.setText("Thank You for Ordering!");
        dialogMessage.setText("Your order has been received. We appreciate your business.");

        // Build the custom dialogue
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        // Create and show the dialogue
        AlertDialog dialog = builder.create();
        dialog.show();

        // Set click listener for the OK button
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the dialogue
                dialog.dismiss();

                // Pass data to MostPopularActivity
                Intent mostPopularIntent = new Intent(ResActivity.this, MostPopularActivity.class);
                mostPopularIntent.putExtra("name", name);
                mostPopularIntent.putExtra("totalBill", totalBill);
                startActivity(mostPopularIntent);
            }
        });

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