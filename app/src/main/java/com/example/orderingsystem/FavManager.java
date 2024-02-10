package com.example.orderingsystem;

import java.util.ArrayList;
import java.util.List;

public class FavManager {
    private static FavManager instance;
    private List<FoodItem> favItemList;

    private FavManager() {
        favItemList = new ArrayList<>();
    }

    public static synchronized FavManager getInstance() {
        if (instance == null) {
            instance = new FavManager();
        }
        return instance;
    }

    public List<FoodItem> getFavItemList() {
        return favItemList;
    }

    public void addFavItem(FoodItem favItem) {
        favItemList.add(favItem);
    }

    public int getFavItemCount() {
        return favItemList.size();
    }

    public void clearFavItemList() {
        favItemList.clear();
    }
}
