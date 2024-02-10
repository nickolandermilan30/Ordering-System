package com.example.orderingsystem;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodItem implements Parcelable {
    private String foodName;
    private String price;
    private int imageResource;


    public FoodItem(String foodName, String price, int imageResource) {
        this.foodName = foodName;
        this.price = price;
        this.imageResource = imageResource;
    }

    protected FoodItem(Parcel in) {
        foodName = in.readString();
        price = in.readString();
        imageResource = in.readInt();
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

    public String getFoodName() {
        return foodName;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResource() {
        return imageResource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(foodName);
        dest.writeString(price);
        dest.writeInt(imageResource);
    }
}
