package com.example.orderingsystem;

public class SliderItems {

    private int image;
    private String text;

    SliderItems(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}
