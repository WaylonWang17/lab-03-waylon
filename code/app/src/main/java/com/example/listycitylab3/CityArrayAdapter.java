package com.example.listycitylab3;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

//used to make the data show in the ui so its not super ugly
public class CityArrayAdapter extends ArrayAdapter<City> { //arrayadapter is a built in android class that bridges data and ui <city> just means its a city object
    public CityArrayAdapter(Context context, ArrayList<City> cities) {

        super(context, 0, cities); //calls the parent (arrayadapter)
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) { //convertview is a way to recycle old views inside the listview.
        View view; //if no reusable view then inflate/build a new row. Recycling views means reusing old rows that once had views so as to not waste memory
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.content,
                    parent, false);
        } else {
            view = convertView;
        }
        City city = getItem(position);
        TextView cityName = view.findViewById(R.id.city_text);
        TextView provinceName = view.findViewById(R.id.province_text);
        cityName.setText(city.getName());
        provinceName.setText(city.getProvince());
        return view;
    }
}

