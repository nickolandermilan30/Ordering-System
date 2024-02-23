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

                // Pass different texts for each item
                String[] additionalTexts = {
                        "2.8 | 179 sold",
                        "4.3 | 46 sold",
                        "1.2 | 19 sold",
                        "2.6 | 99 sold",
                        "3.4 | 190 sold",
                        "5.6 | 109 sold",
                        "1.1 | 155 sold",
                        "3.3 | 189 sold",
                        "9.8 | 155 sold",
                        "1.3 | 197 sold",
                        "3.8 | 209 sold",
                        "9.0 | 777 sold",
                        "2.2 | 899 sold",
                        "5.5 | 156 sold"
                };
                intent.putExtra("additionalText", additionalTexts[position]);

                // Pass different texts for each item
                String[] additionalTexts2 = {
                        "Zhongli",
                        "Zhongli",
                        "Raiden",
                        "Nahida",
                        "Zhongli",
                        "Raiden",
                        "Raiden",
                        "Venti",
                        "Nahida",
                        "Venti",
                        "Furina",
                        "Furina",
                        "Raiden",
                        "Raiden"
                };
                intent.putExtra("additionalText2", additionalTexts2[position]);

                String[] additionalTexts3 = {
                        "Archon",
                        "Archon",
                        "Archon",
                        "Archon",
                        "Archon",
                        "Archon",
                        "Archon",
                        "Archon",
                        "Archon",
                        "Archon",
                        "People",
                        "People",
                        "Archon",
                        "Archon"
                };
                intent.putExtra("additionalText3", additionalTexts3[position]);

                String[] additionalTexts4 = {
                        "An exquisite-looking dish. " +
                                "The ham's sweetness is locked inside the fresh vegetables, " +
                                "drizzled with a spicy broth. Delicious is an understatement.",

                        "A braised meat dish. The cut of meat is a happy medium between lean and fatty, " +
                                "and has been braised to smoking perfection. " +
                                "The ingredients glisten, and are soft but not greasy. Word has it that the " +
                                "Tianshu of a certain generation of the Liyue Qixing invented this dish after " +
                                "much painstaking development.",

                        "A seafood dish made using fresh ingredients. " +
                                "The fish and shellfish are cut into pieces and arranged neatly. " +
                                "The slices have an elegant and tender taste, providing rich and high-quality " +
                                "nutrition. The lingering sweet aftertaste is derived fully from the ingredients' " +
                                "freshness, and every bite is an enjoyment worth closing one's eyes and savoring.",

                        "A signature chicken dish of Sumeru. " +
                                "The chicken was first marinated with red spices, and then roasted in a tandoor. " +
                                "The process of making this Sumeru classic does not involve any fancy skills, " +
                                "and the use of such a humble clay oven might be surprising to some. " +
                                "In Sumeru, a tandoor to a veteran chef is as important as a trusty sword to an adventurer.",

                        "Luxurious and exquisite raw fish. The fish is sliced thinly and arranged in the shape of a flower " +
                                "before a ring of side dishes is set all around it. Normally, the side dishes will be mixed with " +
                                "the raw fish according to one's taste before consumption — this act is said to ensure that things " +
                                "will be smooth sailing in the coming days.",

                        "A grilled crab dish. Appropriate heat control has allowed the crab meat to stay moist and fatty. " +
                                "The butter has seeped into the crab meat, producing an aroma that has people salivating even from afar.",

                        "A creative snack made by adding sticky dango to milk. It is sweet and has a dense mouthfeel. " +
                                "All the customers who have tried it love it.\n" +
                                "Still, it is dango that's been added in — drink too much and you might lose your appetite.",

                        "A brightly colored non-alcoholic beverage. " +
                                "Freshly squeezed Sunsettia and Wolfhook juices have been poured into a cup in a specific order." +
                                " The cool and refreshing flavor and vibrant color remind people of the beautiful holiday times." +
                                " Do not mess the order up if you want to get the gradient effect right!",

                        "A popular dessert in Sumeru. Juices are squeezed from the petals of Padisarahs and Sumeru Roses " +
                                "before being mixed with milk, gelatin, sugar, and other ingredients, and then left to solidify. " +
                                "Cute appearance and sweet flavor make it a common sight at celebratory events in Sumeru.",

                        "A freshly squeezed, fashionable, and fruity non-alcoholic beverage. " +
                                "Said to have a strong sobering effect, tavern patrons often order " +
                                "this as the last drink of the night.",

                        "A small cake, shaped into a long rectangle. " +
                                "It is said that long, long ago, a certain patissier used the great love the people of Fontaine " +
                                "held for their Hydro Archon as inspiration to create this confectionary masterpiece. " +
                                "The manifold layers stacked one upon the other are a true test of the chef's skills, " +
                                "and each aspect of this cake is proof of the great Hydro Archon's influence.",

                        "A refreshing dessert. Though it is one of Fontaine's trademark desserts, " +
                                "its recipe is very simple and straightforward. As long as the proportion of ingredients is right, " +
                                "hardly anyone could fail at making this dessert. It has an exquisite texture with a touch of refreshing " +
                                "cold taste, and can instantly lift your spirits",
                        "A snack shaped like a snapper. The crisp exterior wraps the sweet filling within and is " +
                                "very popular amongst Inazumans. It is said that in the beginning, " +
                                "this was just a cylindrical snack, but constant improvements wound up shaping " +
                                "it into the cutesy image of a fish.",

                        "A soft, glutinous snack. Glutinous rice has been grown into powder and rolled into a ball " +
                                "before being steamed. The Sakura Bloom and Snapdragon colors lend an extra liveliness to " +
                                "these dango."
                };
                intent.putExtra("additionalText4", additionalTexts4[position]);


                // Pass different texts for each item
                int[] imageResources = {
                        R.drawable.zhosq2,
                        R.drawable.zhosq2,
                        R.drawable.raisq4,
                        R.drawable.nahsq3,
                        R.drawable.zhosq2,
                        R.drawable.raisq4,
                        R.drawable.raisq4,
                        R.drawable.vensq1,
                        R.drawable.nahsq3,
                        R.drawable.vensq1,
                        R.drawable.fursq5,
                        R.drawable.fursq5,
                        R.drawable.raisq4,
                        R.drawable.raisq4,
                };
                intent.putExtra("imageResources", imageResources [position]);


                // Start the ReviewActivity
                startActivity(intent);
            }
        });

    }
}
