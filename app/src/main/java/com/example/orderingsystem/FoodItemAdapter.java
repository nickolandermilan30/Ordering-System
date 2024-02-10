package com.example.orderingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// FoodItemAdapter.java
public class FoodItemAdapter extends ArrayAdapter<FoodItem> {

    private OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public FoodItemAdapter(Context context, List<FoodItem> items, OnDeleteClickListener onDeleteClickListener) {
        super(context, 0, items);
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final FoodItem item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        // Lookup view for data population
        TextView itemNameTextView = convertView.findViewById(R.id.foodname);
        ImageView itemImageView = convertView.findViewById(R.id.foodimg);
        TextView itemPriceTextView = convertView.findViewById(R.id.price);
        ImageButton deleteButton = convertView.findViewById(R.id.delete);

        // Populate the data into the template view using the data object
        itemNameTextView.setText(item.getFoodName());
        itemImageView.setImageResource(item.getImageResource());
        itemPriceTextView.setText(item.getPrice());

        // Set click listener for delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.onDeleteClick(position);
                }
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}
