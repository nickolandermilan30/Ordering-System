package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Check extends AppCompatActivity {

    private ArrayList<String> orderNamesList = new ArrayList<>();
    private OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        // Find the b2 ImageButton
        ImageButton b1 = findViewById(R.id.b1);
        ImageButton b2 = findViewById(R.id.b2);
        ImageButton b3 = findViewById(R.id.b3);
        ImageButton b4 = findViewById(R.id.b4);

        // Find the ListView
        ListView listView = findViewById(R.id.listView);

        // Initialize the ArrayAdapter
        OrderManager orderManager = OrderManager.getInstance();
        ArrayList<String> orderNamesList = orderManager.getOrderNamesList();
        adapter = new OrderAdapter(this, orderNamesList);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);

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

        // Set an item click listener for the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve the selected order name
                String orderName = orderNamesList.get(position);

                // Retrieve data from the extras
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    String name = extras.getString("name");
                    int tableNumber = extras.getInt("tableNumber");
                    ArrayList<FoodItem> itemList = extras.getParcelableArrayList("itemList");
                    double totalBill = extras.getDouble("totalBill");

                    // Pass the necessary data to the OrderList activity
                    Intent intent = new Intent(Check.this, OrderList.class);
                    intent.putExtra("name", name);
                    intent.putExtra("tableNumber", tableNumber);
                    intent.putParcelableArrayListExtra("itemList", itemList);
                    intent.putExtra("totalBill", totalBill);
                    intent.putExtra("orderName", orderName); // Pass the selected order name
                    startActivity(intent);
                }
            }
        });


        // Retrieve data from ResActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name");
            int tableNumber = extras.getInt("tableNumber");
            ArrayList<FoodItem> itemList = extras.getParcelableArrayList("itemList");
            double totalBill = extras.getDouble("totalBill");

            // Add the item to the orderNamesList
            orderNamesList.add(name);

            // Notify the adapter of the change
            adapter.notifyDataSetChanged();
        }
    }
}