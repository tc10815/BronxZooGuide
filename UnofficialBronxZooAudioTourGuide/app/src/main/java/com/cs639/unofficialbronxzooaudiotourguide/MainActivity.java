package com.cs639.unofficialbronxzooaudiotourguide;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.DisplayMetrics;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


/**
 * MainActivity. The Guide XML is transformed into internal data in this class and
 * entered into the ViewModel AllData where all fragments and activities can
 * easily access the data.
 *
 * @author Tom
 */
public class MainActivity extends AppCompatActivity  {

    ArrayList<Animal> animals;
    ArrayList<Structure> structures;
    ArrayList<AnimalContainerStructure> animalContainerStructures;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;


    public int ScreenWidth;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AllAppData viewModel = new ViewModelProvider(this).get(AllAppData.class);
        viewModel.getUser().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String data) {
                // update ui.
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DisplayMetrics myMetric = new DisplayMetrics();
        getDisplay().getRealMetrics(myMetric);
        viewModel.setScreenSize(myMetric.widthPixels);
        XMLDataGetter myDataGetter = new XMLDataGetter(this);
        viewModel.setAnimals(myDataGetter.getAnimals());
        viewModel.setStructures(myDataGetter.getStructures());
        viewModel.setAnimalContainerStructures(myDataGetter.getAnimalContainerStructures());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
