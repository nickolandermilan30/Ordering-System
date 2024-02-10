package com.example.orderingsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class Fav extends AppCompatActivity implements FoodItemAdapter.OnDeleteClickListener {

    private static final String KEY_ITEM_LIST = "item_list";
    private ArrayList<FoodItem> itemList;
    private FoodItemAdapter adapter;
    private FavManager favManager;

    private TextView noItemsTextView; // Add this variable
    private TextView countTextView; // Add this variable
    private TextView billTextView;
    private double totalBill = 0.0;
    private TextView nameTextView; // Add this variable
    private ImageButton buyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        // Find the b2 ImageButton
        ImageButton b1 = findViewById(R.id.b1);
        ImageButton b2 = findViewById(R.id.b2);
        ImageButton b3 = findViewById(R.id.b3);
        ImageButton b4 = findViewById(R.id.b4);

        noItemsTextView = findViewById(R.id.noItemsTextView);
        countTextView = findViewById(R.id.count);
        billTextView = findViewById(R.id.bill);
        ImageButton removeAllButton = findViewById(R.id.refresh);

        buyButton = findViewById(R.id.buy);  // Moved buyButton declaration here

        nameTextView = new TextView(this); // Initialize the TextView for name


        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!itemList.isEmpty()) {
                    showNameInputDialog();
                } else {
                    // Show a message or handle the case where the list is empty
                }
            }
        });

        removeAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When remove-all button is clicked, remove all items from the list
                removeAllItems();
            }
        });



        // Set an OnClickListener for b2
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(Fav.this, LandingPage.class);
                startActivity(intent);
            }
        });

        // Set an OnClickListener for b2
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(Fav.this, Shop.class);
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When b2 is clicked, open the ShopActivity
                Intent intent = new Intent(Fav.this, Check.class);
                startActivity(intent);
            }
        });
        itemList = new ArrayList<>();
        favManager = FavManager.getInstance();

        // Retrieve saved items from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("fav_items", MODE_PRIVATE);
        String json = sharedPreferences.getString("fav_items_list", "");
        if (!json.isEmpty()) {
            Gson gson = new Gson();
            FoodItem[] savedItems = gson.fromJson(json, FoodItem[].class);
            itemList.addAll(Arrays.asList(savedItems));
        }

        // Initialize the item list
        itemList = new ArrayList<>();
        favManager = FavManager.getInstance();

        // Create the adapter and set it to the ListView
        adapter = new FoodItemAdapter(this, itemList, this);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // Get data from Intent and add it to the list
        String foodName = getIntent().getStringExtra("foodName");
        String price = getIntent().getStringExtra("price");
        int imageResource = getIntent().getIntExtra("imageResource", 0);
        for (FoodItem favItem : favManager.getFavItemList()) {
            addFavItem(favItem.getFoodName(), favItem.getPrice(), favItem.getImageResource());
        }

        // Update the visibility of the TextView
        updateNoItemsTextViewVisibility();
    }
    private void showNameInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Inflate the custom layout for the AlertDialog
        View customLayout = getLayoutInflater().inflate(R.layout.custom_alert_layout, null);
        builder.setView(customLayout);

        final EditText input = customLayout.findViewById(R.id.editTextName);
        TextView titleTextView = customLayout.findViewById(R.id.textViewTitle);
        Button btnCancel = customLayout.findViewById(R.id.btnCancel);
        Button btnOK = customLayout.findViewById(R.id.btnOK);

        titleTextView.setText("Teyvat Resto");

        final AlertDialog dialog = builder.create();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = input.getText().toString().trim();
                if (!name.isEmpty()) {
                    dialog.dismiss();
                    showRandomNumberDialog(name);
                } else {
                    // Handle empty name, for example, show an error message
                }
            }
        });

        dialog.show();
    }


    private void showRandomNumberDialog(String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Inflate the custom layout for the AlertDialog result
        View customLayout = getLayoutInflater().inflate(R.layout.custom_alert_result_layout, null);
        builder.setView(customLayout);

        TextView messageTextView = customLayout.findViewById(R.id.textViewMessage);
        Button btnOKResult = customLayout.findViewById(R.id.btnOKResult);

        int randomNumber = (int) (Math.random() * 20) + 1; // Generate a random number between 1 and 20
        String message = "Hello, " + name + "! Your Table number is: " + randomNumber;

        // Set the message with the random number
        messageTextView.setText(message);

        final AlertDialog dialog = builder.create();

        btnOKResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                Intent resIntent = new Intent(Fav.this, ResActivity.class);
                resIntent.putExtra("name", name);
                resIntent.putExtra("tableNumber", randomNumber);
                resIntent.putParcelableArrayListExtra("itemList", itemList); // Make sure itemList implements Parcelable
                resIntent.putExtra("totalBill", totalBill);
                startActivity(resIntent);

            }
        });

        dialog.show();
    }

    // Helper method to calculate the total bill
    private double calculateTotalBill() {
        double totalBill = 0.0;
        for (FoodItem item : itemList) {
            String numericPrice = item.getPrice().replaceAll("[^\\d.]", "");
            try {
                totalBill += Double.parseDouble(numericPrice);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Handle the exception (e.g., log, show an error message)
            }
        }
        return totalBill;
    }



    @Override
    protected void onPause() {
        super.onPause();
        // Sa pagtawag sa onPause, i-save ang listahan ng mga item
        saveFavItems(itemList);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Sa pagtawag sa onResume, i-retrieve ang listahan ng mga item mula sa SharedPreferences
        // at i-update ang iyong list view
        loadFavItems();

        // Update total bill and item count
        updateTotalBillAndItemCount();

        // Get data from Intent and add it to the list if not already present
        String foodName = getIntent().getStringExtra("foodName");
        String price = getIntent().getStringExtra("price");
        int imageResource = getIntent().getIntExtra("imageResource", 0);

        if (foodName != null && price != null && !isItemAlreadyInList(foodName, price)) {
            addFavItem(foodName, price, imageResource);
        }
    }

    // Method to check if an item is already in the list
    private boolean isItemAlreadyInList(String foodName, String price) {
        for (FoodItem item : itemList) {
            if (item.getFoodName().equals(foodName) && item.getPrice().equals(price)) {
                return true;
            }
        }
        return false;
    }

    private void updateTotalBillAndItemCount() {
        totalBill = 0.0;
        for (FoodItem item : itemList) {
            String numericPrice = item.getPrice().replaceAll("[^\\d.]", "");
            try {
                totalBill += Double.parseDouble(numericPrice);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Handle the exception (e.g., log, show an error message)
            }
        }

        // Update the count TextView and total bill TextView
        updateItemCount();
        updateTotalBill(totalBill);
    }

    private void loadFavItems() {
        SharedPreferences sharedPreferences = getSharedPreferences("fav_items", MODE_PRIVATE);
        String json = sharedPreferences.getString("fav_items_list", "");
        if (!json.isEmpty()) {
            Gson gson = new Gson();
            FoodItem[] savedItems = gson.fromJson(json, FoodItem[].class);

            // Clear the list only if there are items to load
            if (savedItems.length > 0) {
                itemList.clear(); // Clear existing list
                itemList.addAll(Arrays.asList(savedItems));
                adapter.notifyDataSetChanged(); // Notify adapter of data change
            }
        }

        // Update the visibility of the TextView
        updateNoItemsTextViewVisibility();
    }


    // Method to remove all items from the list
    private void removeAllItems() {
        itemList.clear(); // Clear the list
        adapter.notifyDataSetChanged(); // Notify the adapter of the data change

        // Reset totalBill, count, and update corresponding TextViews
        totalBill = 0.0;
        updateItemCount();
        updateTotalBill(totalBill);

        // Save the updated list (empty list)
        saveFavItems(itemList);

        // Update the visibility of the TextView
        updateNoItemsTextViewVisibility();

        // Update the favManager
        favManager.clearFavItemList();
    }






    private void updateNoItemsTextViewVisibility() {
        if (itemList.isEmpty()) {
            noItemsTextView.setVisibility(View.VISIBLE);
            buyButton.setEnabled(false);  // Disable the button when the list is empty
        } else {
            noItemsTextView.setVisibility(View.GONE);
            buyButton.setEnabled(true);   // Enable the button when the list has items
        }
    }

    // Method to add a new favorite item to the list
    public void addFavItem(String foodName, String price, int imageResource) {
        FoodItem favItem = new FoodItem(foodName, price, imageResource);
        itemList.add(favItem);
        adapter.notifyDataSetChanged(); // Notify the adapter of the data change
        String numericPrice = price.replaceAll("[^\\d.]", ""); // Keep only digits and dot

        try {
            // Update the count TextView
            updateItemCount();

            // Update the total bill
            updateTotalBill(Double.parseDouble(numericPrice));

            // Save the updated list
            saveFavItems(itemList);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log, show an error message)
        }
    }

    private void updateTotalBill(double totalBill) {
        billTextView.setText(String.format("$%.2f", totalBill));
    }

    private void saveFavItems(ArrayList<FoodItem> itemList) {
        SharedPreferences sharedPreferences = getSharedPreferences("fav_items", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(itemList);
        editor.putString("fav_items_list", json);
        editor.apply();
    }






    // Method to update the count TextView
    private void updateItemCount() {
        int itemCount = itemList.size();
        countTextView.setText(String.valueOf(itemCount));
    }


    @Override
    public void onDeleteClick(int position) {
        // Handle delete button click here
        if (position >= 0 && position < itemList.size()) {
            FoodItem deletedItem = itemList.remove(position);
            double deletedItemPrice = Double.parseDouble(deletedItem.getPrice().replaceAll("[^\\d.]", ""));
            totalBill -= deletedItemPrice;

            // Update the count TextView
            updateItemCount();

            // Update the total bill
            billTextView.setText(String.format("$%.2f", totalBill));

            // Save the updated list
            saveFavItems(itemList);

            // Notify the adapter of the data change
            adapter.notifyDataSetChanged();

            // Update the visibility of the TextView
            updateNoItemsTextViewVisibility();

            favManager.clearFavItemList();
        }
    }
}
