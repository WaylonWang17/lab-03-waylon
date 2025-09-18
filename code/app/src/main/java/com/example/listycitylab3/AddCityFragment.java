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

public class AddCityFragment extends DialogFragment { //pop up dialog thats reusable
    interface AddCityDialogListener { //anytime the dialog comes up, it needs to add a city
        void addCity(City city);
    }
    private AddCityDialogListener listener;
    @Override
    public void onAttach(@NonNull Context context) { //reusable addcitydialog
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implementAddCityDialogListener");
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) { //logic for building pop up form for typing in city + province along with cancel
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null); //turns xml to view
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text); //these two allow us to see the text
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()); //dialogue (pop up to let us type stuff)
        return builder
                .setView(view) //uses custom layout
                .setTitle("Add a city") //add city dialogue
                .setNegativeButton("Cancel", null) //cancel button
                .setPositiveButton("Add", (dialog, which) -> { //add button
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();
                    listener.addCity(new City(cityName, provinceName)); //adds new city
                })
                .create();
    }
}