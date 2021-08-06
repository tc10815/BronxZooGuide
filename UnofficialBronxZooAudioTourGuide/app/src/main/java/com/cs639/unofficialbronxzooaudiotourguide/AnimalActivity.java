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
        TextView txtNaturallocation = findViewById(R.id.txtNaturallocation);
        TextView txtFamily = findViewById(R.id.txtFamily);
        TextView txtClass = findViewById(R.id.txtClass);
        TextView txtBinom = findViewById(R.id.txtBinomialNomenclature);
        TextView txtIUCNConservationStatus = findViewById(R.id.txtIUCNConservationStatus);

        for(int x = 0; x < myAnimals.size();x++){
            if ( myAnimals.get(x).getZooName().equals(animalName)){
                txtBinom.setText("Binomial Nomenclature: " + myAnimals.get(x).getBinomialNomenclature());
                txtFamily.setText("Family: " + myAnimals.get(x).getFamily());
                txtNaturallocation.setText("Natural Location: " + myAnimals.get(x).getNaturalLocation());
                txtClass.setText("Class: " + myAnimals.get(x).getAnimalClass());
                txtIUCNConservationStatus.setText("Converation Status: " + myAnimals.get(x).getConservationStatus());
            }
        }
    }
}