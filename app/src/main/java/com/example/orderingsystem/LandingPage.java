package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LandingPage extends AppCompatActivity {

    private FavManager favManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        // Find the b2 ImageButton
        ImageButton b1 = findViewById(R.id.b1);
        ImageButton b2 = findViewById(R.id.b2);
        ImageButton b3 = findViewById(R.id.b3);
        ImageButton b4 = findViewById(R.id.b4);

        ImageButton arrow1 = findViewById(R.id.imageButton4);
        ImageButton arrow2 = findViewById(R.id.imageButton5);

        arrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(LandingPage.this, FoodList.class);
                startActivity(intent);
            }
        });

        arrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(LandingPage.this, FoodList.class);
                startActivity(intent);
            }
        });

        //Food
        Button addFavButton1 = findViewById(R.id.seller);
        ImageButton addFavButton2 = findViewById(R.id.addfav2);
        ImageButton addFavButton3 = findViewById(R.id.addfav3);

        TextView countTextView = findViewById(R.id.count);
        ImageButton Option = findViewById(R.id.option);
        favManager = FavManager.getInstance();

        // Set the initial count
        countTextView.setText(String.valueOf(favManager.getFavItemCount()));

        Option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(LandingPage.this, Fav.class);
                startActivity(intent);
            }
        });

        addFavButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFav("Jade Parcels", "Php 100.000", R.drawable.f2);
            }
        });

        addFavButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFav("Tianshu Meat", "Php 270.00", R.drawable.f4);
            }
        });

        addFavButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFav("Sashimi Platter", "Php 470.00", R.drawable.f5);
            }
        });

        // Set an OnClickListener for b2
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(LandingPage.this, Shop.class);
                startActivity(intent);
            }
        });

        // Set an OnClickListener for b2
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(LandingPage.this, Fav.class);
                startActivity(intent);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(LandingPage.this, Check.class);
                startActivity(intent);
            }
        });
    }

    private void addToFav(String foodName, String price, int imageResource) {
        FoodItem favItem = new FoodItem(foodName, price, imageResource);
        favManager.addFavItem(favItem);
        updateFavCount();
        Intent intent = new Intent(this, Fav.class);
        startActivity(intent);
    }

    private void updateFavCount() {
        TextView countTextView = findViewById(R.id.count);
        countTextView.setText(String.valueOf(favManager.getFavItemCount()));
    }
}
