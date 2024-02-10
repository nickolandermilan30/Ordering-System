package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Shop extends AppCompatActivity {
    private Fav favActivity;
    private FavManager favManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        // Find the b2 ImageButton
        ImageButton b1 = findViewById(R.id.b1);
        ImageButton b2 = findViewById(R.id.b2);
        ImageButton b3 = findViewById(R.id.b3);
        ImageButton b4 = findViewById(R.id.b4);

        //Food
        ImageButton addFavButton1 = findViewById(R.id.addfav1);
        ImageButton addFavButton2 = findViewById(R.id.addfav3);
        ImageButton addFavButton3 = findViewById(R.id.addfav4);
        ImageButton addFavButton4 = findViewById(R.id.addfav5);

        favManager = FavManager.getInstance();

        Button Drinks = findViewById(R.id.drinks);
        Button Dessert = findViewById(R.id.dessert);
        Dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(Shop.this, Dessert.class);
                startActivity(intent);
            }
        });

        // Set an OnClickListener for b2
        Drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(Shop.this, Drinks.class);
                startActivity(intent);
            }
        });

        // Set an OnClickListener for b2
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(Shop.this, LandingPage.class);
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(Shop.this, Check.class);
                startActivity(intent);
            }
        });

        // Set an OnClickListener for b2
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(Shop.this, Fav.class);
                startActivity(intent);
            }
        });

        addFavButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFav("Tandoori Roast Chicken", "Php 430.00", R.drawable.f6);
            }
        });

        addFavButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFav("Bountiful Year", "Php 170.00", R.drawable.f7);
            }
        });
        addFavButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFav("Butter Crab", "Php 570.00", R.drawable.f8);
            }
        });

        addFavButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFav("Jade Parcels", "Php 100.000", R.drawable.f2);
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