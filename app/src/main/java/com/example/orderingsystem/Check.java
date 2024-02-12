package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Check extends AppCompatActivity {

    private AutoCompleteTextView searchAutoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        // Find the b2 ImageButton
        ImageButton b1 = findViewById(R.id.b1);
        ImageButton b2 = findViewById(R.id.b2);
        ImageButton b3 = findViewById(R.id.b3);
        ImageButton b4 = findViewById(R.id.b4);

        // Retrieve data from intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        double totalBill = intent.getDoubleExtra("totalBill", 0.0);
        // Assuming you have a ListView in your layout with the id listView
        ListView listView = findViewById(R.id.listView);

        searchAutoCompleteTextView = findViewById(R.id.searchAutoCompleteTextView);

        List<String> foodSuggestions = new ArrayList<>();
        foodSuggestions.add("Tianshu Meat");
        foodSuggestions.add("Sashimi Platter");

        foodSuggestions.add("Tandoori Roast Chicken");
        foodSuggestions.add("Bountiful Year");
        foodSuggestions.add("Butter Crab");
        foodSuggestions.add("Jade Parcels");

        foodSuggestions.add("Dango Milk");
        foodSuggestions.add("Fruits of the Festival");
        foodSuggestions.add("Padisarah Pudding");
        foodSuggestions.add("Apple Cider");

        foodSuggestions.add("La Lettre a Focalors");
        foodSuggestions.add("Coffee Bavarois");
        foodSuggestions.add("Taiyaki");
        foodSuggestions.add("Tricolor Dango");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, foodSuggestions);

        searchAutoCompleteTextView.setAdapter(adapter);

        // Sa iyong Check.java
        List<OrderItem> checkDataList = new ArrayList<>();
        checkDataList.add(new OrderItem(name, totalBill));

        ArrayAdapter<OrderItem> checkDataAdapter = new ArrayAdapter<OrderItem>
                (this, R.layout.list_item_bill, R.id.itemNameTextView, checkDataList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                // Check if the view is being reused, otherwise inflate the view
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_bill, parent, false);
                }

                // Get the data item for this position
                OrderItem orderItem = getItem(position);

                // Lookup view for data population
                TextView itemNameTextView = convertView.findViewById(R.id.itemNameTextView);
                TextView itemTotalBillTextView = convertView.findViewById(R.id.itemTotalBillTextView);

                // Populate the data into the template view using the data object
                if (orderItem != null) {
                    itemNameTextView.setText(orderItem.getName());

                    // Convert total bill to PHP currency format
                    double totalBill = orderItem.getTotalBill();
                    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("fil", "PH"));
                    String formattedTotalBill = currencyFormat.format(totalBill);
                    itemTotalBillTextView.setText(formattedTotalBill);
                }

                // Return the completed view to render on screen
                return convertView;
            }
        };

// Set the adapter to the ListView
        listView.setAdapter(checkDataAdapter);




        searchAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFood = (String) parent.getItemAtPosition(position);
                // Ito ay halimbawa lamang. Dapat mong palitan ito base sa iyong pangangailangan.
                switchToMatchingActivity(selectedFood);
            }
        });

        // Set click listeners for the ImageButtons
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Check.this, LandingPage.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Check.this, Shop.class);
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Check.this, Fav.class);
                startActivity(intent);
            }
        });


    }



    private void switchToMatchingActivity(String foodName) {
        Intent intent;

        switch (foodName) {
            case "Tianshu Meat":
            case "Sashimi Platter":
                intent = new Intent(Check.this, LandingPage.class);
                break;
            case "Tandoori Roast Chicken":
            case "Bountiful Year":
            case "Butter Crab":
            case "Jade Parcels":
                intent = new Intent(Check.this, Shop.class);
                break;
            case "Dango Milk":
            case "Fruits of the Festival":
            case "Padisarah Pudding":
            case "Apple Cider":
                intent = new Intent(Check.this, Drinks.class);
                break;
            case "La Lettre a Focalors":
            case "Coffee Bavarois":
            case "Taiyaki":
            case "Tricolor Dango":
                intent = new Intent(Check.this, Dessert.class);
                break;
            default:
                // Default action, if needed
                return;
        }

        // Add search query as extra to intent
        intent.putExtra("searchQuery", foodName);
        startActivity(intent);
    }



}