package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        // Find the "Back" button by id
        ImageButton backButton = findViewById(R.id.back);
        ImageButton Check = findViewById(R.id.home);
        ImageButton Start = findViewById(R.id.start);
        ImageButton Message = findViewById(R.id.imageButton2);

        Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When Message button is clicked, go to Message activity
                Intent intent = new Intent(ReviewActivity.this, Message.class);
                startActivity(intent);
            }
        });


        // Set a click listener for the "Back" button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(ReviewActivity.this, Check.class);
                startActivity(intent);
            }
        });
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(ReviewActivity.this, LandingPage.class);
                startActivity(intent);
            }
        });

        // Retrieve data from Intent
        String itemName = getIntent().getStringExtra("itemName");
        String itemPrice = getIntent().getStringExtra("itemPrice");
        int itemImageResourceId = getIntent().getIntExtra("itemImage", 0);

        // Get the additional text
        String additionalText = getIntent().getStringExtra("additionalText");
        String additionalText2 = getIntent().getStringExtra("additionalText2");
        String additionalText3 = getIntent().getStringExtra("additionalText3");
        String additionalText4 = getIntent().getStringExtra("additionalText4");

        // Retrieve image resource from Intent
        int imageResources = getIntent().getIntExtra("imageResources", 0);

        // Set data to the corresponding views
        ImageView imageView = findViewById(R.id.image);
        TextView nameTextView = findViewById(R.id.textView20);
        TextView billTextView = findViewById(R.id.billTextView);
        TextView additionalTextView = findViewById(R.id.additionalTextView);
        TextView additionalTextView2 = findViewById(R.id.additionalTextView2);
        TextView additionalTextView3 = findViewById(R.id.additionalTextView3);
        TextView additionalTextView4 = findViewById(R.id.additionalTextView4);

        ImageView imageResourcesImageView = findViewById(R.id.imageResources);

        imageView.setImageResource(itemImageResourceId);
        nameTextView.setText(itemName);
        billTextView.setText(itemPrice);
        additionalTextView.setText(additionalText);
        additionalTextView2.setText(additionalText2);
        additionalTextView3.setText(additionalText3);
        additionalTextView4.setText(additionalText4);

        // Set image resource
        imageResourcesImageView.setImageResource(imageResources);
    }


}