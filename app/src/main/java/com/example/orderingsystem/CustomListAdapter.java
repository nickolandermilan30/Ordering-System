package com.example.orderingsystem;// CustomListAdapter.java
// import statements...

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<FoodItemReview> {

    public CustomListAdapter(Context context, List<FoodItemReview> foodItemList) {
        super(context, 0, foodItemList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_review, parent, false);
        }

        FoodItemReview currentItem = getItem(position);

        ImageView itemImage = convertView.findViewById(R.id.foodimg);
        TextView itemName = convertView.findViewById(R.id.foodname);
        TextView itemPrice = convertView.findViewById(R.id.price);

        if (currentItem != null) {
            itemImage.setImageResource(currentItem.getImageResourceId());
            itemName.setText(currentItem.getName());

            // Display the price in the TextView
            itemPrice.setText(currentItem.getPrice());
        }

        return convertView;
    }
}
