package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class Check extends AppCompatActivity {

    private AutoCompleteTextView searchAutoCompleteTextView;
    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

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

        viewPager2 = findViewById(R.id.viewPagerImageSlider);

        List<SliderItems> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItems(R.drawable.fuhua, "1k"));
        sliderItems.add(new SliderItems(R.drawable.navia, "5k"));
        sliderItems.add(new SliderItems(R.drawable.mei, "6k"));
        sliderItems.add(new SliderItems(R.drawable.march, "10k"));

        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 2000); // slide duration 2 seconds
            }
        });



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

                switchToMatchingActivity(selectedFood);
            }
        });

        ImageButton Most = findViewById(R.id.most);


        // Set click listeners for the ImageButtons
        Most.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Check.this, MostPopularActivity.class);
                startActivity(intent);
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


    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 2000);
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

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };



}

