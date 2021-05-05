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

/**
 * Starting from scratch
 *
 * @author Tom
 */
public class AnimalActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.cs639.unofficialbronxzooaudiotourguide";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = this.getIntent();
        XMLDataGetter allData = new XMLDataGetter(this);
        int myAnimalId = myIntent.getIntExtra("animalnumber", 0);
        String filter = myIntent.getStringExtra("filter");
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        String animalName = allData.getAnimals().get(myAnimalId).getZooName();
        Log.i("TOMDEBUG", "Animal name is " + animalName);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(true);
        mActionBar.setTitle("Animal: " + animalName);
        mActionBar.setDisplayShowTitleEnabled(true);
    }
}