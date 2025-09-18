package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public class EditCityFragment extends DialogFragment {
    interface EditCityDialogListener { //anytime dialog comes up we need to edit city
        void updateCity(int position, City updatedCity);
    }

    private EditCityDialogListener listener; //connection between fragment and activity

    public static EditCityFragment newInstance(City city, int position) { //gets all the information when you click on a city
        EditCityFragment fragment = new EditCityFragment();
        Bundle args = new Bundle(); // basically a hashmap

        //puts all these arguments into the hashmap
        args.putString("city_name", city.getName());
        args.putString("province", city.getProvince());
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) { //reusable edit city
        super.onAttach(context);
        if (context instanceof EditCityDialogListener) {
            listener = (EditCityDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement EditCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null); //takes fragmentaddcity.xml and turns it in java object view

        //finding these two from the view we got up there
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);

        //THIS IS WHERE USERS GIVE INPUTS - if there are arguments then read what we stored in ARG_CITY_NAME and set that value to editcityname (prefilles the box with current city)
        if (getArguments() != null) {
            editCityName.setText(getArguments().getString("city_name"));
            editProvinceName.setText(getArguments().getString("province"));
        }

        //getting position of the argument and storing in position
        int position = getArguments().getInt("position");

        return new AlertDialog.Builder(getContext())
                .setView(view) //brings the view so it shows city + province in the edit dialog
                .setTitle("Edit City") //title = edit city
                .setNegativeButton("Cancel", null) //can hit cancel
                .setPositiveButton("Save", (dialog, which) -> { //
                    String cityName = editCityName.getText().toString(); //changes cityname to editcityname
                    String provinceName = editProvinceName.getText().toString();
                    listener.updateCity(position, new City(cityName, provinceName)); //makes the changes
                })
                .create();
    }
}
