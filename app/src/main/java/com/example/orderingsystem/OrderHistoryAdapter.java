package com.example.orderingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderHistoryAdapter extends ArrayAdapter<String> {

    public OrderHistoryAdapter(Context context, ArrayList<String> orderHistoryList) {
        super(context, 0, orderHistoryList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String orderDetails = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(orderDetails);

        return convertView;
    }
}
