package com.cs639.unofficialbronxzooaudiotourguide;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Starting from scratch
 *
 * @author Tom
 */
public class AnimalActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.cs639.unofficialbronxzooaudiotourguide";
    private Animal myAnimal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_screen);
        Intent myIntent = this.getIntent();
        XMLDataGetter allData = new XMLDataGetter(this);
        int myAnimalId = myIntent.getIntExtra("animalnumber", 0);
        String filter = myIntent.getStringExtra("filter");
        ArrayList<Animal> myAnimals = allData.getAnimals();
        String animalName = allData.getAnimals().get(myAnimalId).getZooName();
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(true);
        mActionBar.setTitle("Animal: " + animalName);
        mActionBar.setDisplayShowTitleEnabled(true);

        //Get different items in view
        TextView txtNaturallocation = 

        for(int x = 0; x < myAnimals.size();x++){
            if ( myAnimals.get(x).getZooName().equals(animalName)){
                Log.e("long", "name status: " + myAnimals.get(x).getZooName());
                Log.e("long", "binom nom status: " + myAnimals.get(x).getBinomialNomenclature());
                Log.e("long", "family status: " + myAnimals.get(x).getFamily());
                Log.e("long", "class status: " + myAnimals.get(x).getAnimalClass());
                Log.e("long", "concerveration status: " + myAnimals.get(x).getConservationStatus());
            }
        }
    }
}