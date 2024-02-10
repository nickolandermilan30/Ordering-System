// OrderAdapter.java
package com.example.orderingsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<String> {
    private ArrayList<String> orderNamesList;
    private Context context; // Add a context variable to use in the OnClickListener

    public OrderAdapter(Context context, ArrayList<String> orderNamesList) {
        super(context, 0, orderNamesList);
        this.orderNamesList = orderNamesList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_order, parent, false);
        }

        // Get the order name at the current position
        String currentOrderName = getItem(position);

        // Find the TextView in the list_item_order.xml layout
        TextView orderNameTextView = listItemView.findViewById(R.id.orderNameTextView);
        // Get the "See" button from the list item view
        ImageButton seeButton = listItemView.findViewById(R.id.See);

        // Set OnClickListener for the "See" button
        seeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the selected order name
                String orderName = orderNamesList.get(position);

                // Retrieve data from the extras
                Bundle extras = ((Activity) context).getIntent().getExtras();
                if (extras != null) {
                    String name = extras.getString("name");
                    int tableNumber = extras.getInt("tableNumber");
                    ArrayList<FoodItem> itemList = extras.getParcelableArrayList("itemList");
                    double totalBill = extras.getDouble("totalBill");

                    // Pass the necessary data to the OrderList activity
                    Intent intent = new Intent(context, OrderList.class);
                    intent.putExtra("name", name);
                    intent.putExtra("tableNumber", tableNumber);
                    intent.putParcelableArrayListExtra("itemList", itemList);
                    intent.putExtra("totalBill", totalBill);
                    intent.putExtra("orderName", orderName); // Pass the selected order name
                    context.startActivity(intent);
                }
            }
        });

        // Set the text of the TextView to the current order name
        orderNameTextView.setText(currentOrderName);

        return listItemView;
    }

    // Add a method to update the orderNamesList
    public void updateOrderNamesList(ArrayList<String> newOrderNamesList) {
        orderNamesList.clear();
        orderNamesList.addAll(newOrderNamesList);
        notifyDataSetChanged();
    }

}
