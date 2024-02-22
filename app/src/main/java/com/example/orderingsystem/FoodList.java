package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FoodList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        ListView listView = findViewById(R.id.listView);

        // Find the "Back" button by id
        ImageButton backButton = findViewById(R.id.back);

        // Set a click listener for the "Back" button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Sample data
        List<FoodItemReview> foodItemList = new ArrayList<>();
        foodItemList.add(new FoodItemReview("Jade Parcels", R.drawable.f2, "Php 100.000"));
        foodItemList.add(new FoodItemReview("Tianshu Meat", R.drawable.f4, "Php 270.00"));
        foodItemList.add(new FoodItemReview("Sashimi Platter", R.drawable.f5, "Php 470.00"));
        foodItemList.add(new FoodItemReview("Tandoori Roast Chicken", R.drawable.f6,"Php 430.00"));
        foodItemList.add(new FoodItemReview("Bountiful Year", R.drawable.f7, "Php 170.00"));
        foodItemList.add(new FoodItemReview("Butter Crab", R.drawable.f8,    "Php 570.00"));

        foodItemList.add(new FoodItemReview("Dango Milk", R.drawable.d1, "Php 95.00"));
        foodItemList.add(new FoodItemReview("Fruits of the Festival", R.drawable.d2, "Php 120.00"));
        foodItemList.add(new FoodItemReview("Padisarah Pudding", R.drawable.d3, "Php 230.00"));
        foodItemList.add(new FoodItemReview("Apple Cider", R.drawable.d4, "Php 100.000"));

        foodItemList.add(new FoodItemReview("La Lettre a Focalors", R.drawable.d5, "Php 295.00"));
        foodItemList.add(new FoodItemReview("Coffee Bavarois", R.drawable.d6, "Php 370.00"));
        foodItemList.add(new FoodItemReview("Taiyaki", R.drawable.d7, "Php 240.00"));
        foodItemList.add(new FoodItemReview("Tricolor Dango", R.drawable.d8, "Php 200.000"));

        // Create a custom adapter
        CustomListAdapter adapter = new CustomListAdapter(this, foodItemList);
        listView.setAdapter(adapter);

        // Set item click listener for the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item
                FoodItemReview selectedItem = foodItemList.get(position);

                // Create an Intent to start the ReviewActivity
                Intent intent = new Intent(FoodList.this, ReviewActivity.class);

                // Pass relevant data to the ReviewActivity
                intent.putExtra("itemName", selectedItem.getName());
                intent.putExtra("itemPrice", selectedItem.getPrice());
                intent.putExtra("itemImage", selectedItem.getImageResourceId());
                // Add more data if needed

                // Pass different texts for each item
                String[] additionalTexts = {
                        "Ibang teksto para sa item 1",
                        "Ibang teksto para sa item 2",
                        "Ibang teksto para sa item 3",
                        "Ibang teksto para sa item 4",
                        "Ibang teksto para sa item 5",
                        "Ibang teksto para sa item 6",
                        "Ibang teksto para sa item 7",
                        "Ibang teksto para sa item 8",
                        "Ibang teksto para sa item 9",
                        "Ibang teksto para sa item 10",
                        "Ibang teksto para sa item 11",
                        "Ibang teksto para sa item 12",
                        "Ibang teksto para sa item 13",
                        "Ibang teksto para sa item 14",

                };
                intent.putExtra("additionalText", additionalTexts[position]);


                // Start the ReviewActivity
                startActivity(intent);
            }
        });

    }
}
