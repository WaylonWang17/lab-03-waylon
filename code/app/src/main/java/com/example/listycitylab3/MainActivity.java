package com.example.listycitylab3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener, EditCityFragment.EditCityDialogListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;

    @Override
    public void addCity(City city){
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }
    @Override
    public void updateCity(int position, City updatedCity){ //allows us to update the city and province IMPLEMENTED BECAUSE OF THE INTERFACE
        dataList.set(position, updatedCity);
        cityAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){ //saves the bundles state so if you rotate your phone the previous state is saved
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            String[] cities = {"Edmonton", "Vancouver", "Toronto"};
            String[] provinces = {"AB", "BC", "ON"};

            dataList = new ArrayList<>();
            for (int i = 0; i < cities.length; i++) {
                dataList.add(new City(cities[i], provinces[i]));
            }

            cityList = findViewById(R.id.city_list); //finds the list of cities in the view

            //adapters are used when you have a list or something and want to show it in the ui in row format
            cityAdapter = new CityArrayAdapter(this, dataList); //calls class that turns the objects into rows of text
            cityList.setAdapter(cityAdapter); // connects listview to the correct adapter (cityarrayadapter)

            //when clicking on a city
            cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //default params
                    City selectedCity = dataList.get(position);
                    EditCityFragment editDialog = EditCityFragment.newInstance(selectedCity, position); //creates an instance of the editing dialog with this position
                    editDialog.show(getSupportFragmentManager(), "Edit City"); //edit city is just a tag and getsupportfragmentmanager just keeps track of all the fragments so it adds edit city to the list of fragments its managing
                }
            });

            FloatingActionButton fab = findViewById(R.id.button_add_city);
            fab.setOnClickListener(v -> {
                new AddCityFragment().show(getSupportFragmentManager(), "Add City");
            });
    }
}
