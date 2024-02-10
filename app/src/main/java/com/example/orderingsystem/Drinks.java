package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Drinks extends AppCompatActivity {

    private Fav favActivity;
    private FavManager favManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);

        // Find the b2 ImageButton
        ImageButton b1 = findViewById(R.id.b1);
        ImageButton b2 = findViewById(R.id.b2);
        ImageButton b3 = findViewById(R.id.b3);
        ImageButton b4 = findViewById(R.id.b4);


        //Food
        ImageButton addFavButton1 = findViewById(R.id.addfav1);
        ImageButton addFavButton2 = findViewById(R.id.addfav2);
        ImageButton addFavButton3 = findViewById(R.id.addfav3);
        ImageButton addFavButton4 = findViewById(R.id.addfav4);

        favManager = FavManager.getInstance();

        Button Food = findViewById(R.id.food);
        Button Dessert = findViewById(R.id.dessert);
        // Set an OnClickListener for b2
        Food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(Drinks.this, Shop.class);
                startActivity(intent);
            }
        });

        Dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(Drinks.this, Dessert.class);
                startActivity(intent);
            }
        });

        // Set an OnClickListener for b2
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(Drinks.this, LandingPage.class);
                startActivity(intent);
            }
        });

        // Set an OnClickListener for b2
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(Drinks.this, Fav.class);
                startActivity(intent);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(Drinks.this, Check.class);
                startActivity(intent);
            }
        });

        addFavButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFav("Dango Milk", "Php 95.00", R.drawable.d1);
            }
        });

        addFavButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFav("Fruits of the Festival", "Php 120.00", R.drawable.d2);
            }
        });
        addFavButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFav("Padisarah Pudding", "Php 230.00", R.drawable.d3);
            }
        });

        addFavButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFav("Item Apple Cider", "Php 100.00", R.drawable.d4);
            }
        });

    }

    private void addToFav(String foodName, String price, int imageResource) {
        FoodItem favItem = new FoodItem(foodName, price, imageResource);
        favManager.addFavItem(favItem);
        Intent intent = new Intent(this, Fav.class);
        startActivity(intent);
    }

}