package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

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