package com.cs639.unofficialbronxzooaudiotourguide;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import java.util.ArrayList;

/**
 * MainActivity. The Guide XML is transformed into internal data in this class and	
 * entered into the ViewModel AllData where all fragments and activities can	
 * easily access the data.	
 *
 * @author Tom
 */
public class MainActivity extends AppCompatActivity  {
    private FusedLocationProviderClient mFusedLocationClient;
    private double wayLatitude = 0.0, wayLongitude = 0.0;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private final String CHECKMARK_KEY = "checkmarks";
    private final String ISMETRIC_KEY = "ismetric";
    private final String EMPTY_CHECKMARKS = "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu" +
            "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu" +
            "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu";
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.cs639.unofficialbronxzooaudiotourguide";

    private boolean isContinue = false;
    private boolean isGPS = false;
    protected Context context;
    public int ScreenWidth;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(4 * 1000); // 4 seconds	
        locationRequest.setFastestInterval(1 * 1000); // 1 second	
        new GpsUtils(this).turnGPSOn(new GpsUtils.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                // turn on GPS	
                isGPS = isGPSEnable;
            }
        });
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        wayLatitude = location.getLatitude();
                        wayLongitude = location.getLongitude();
                        if (!isContinue) {
                            Log.i("TOMDEBUG", "d "+  wayLatitude + " " + wayLongitude);
                        } else {
                            Log.i("TOMDEBUG", "c "+  wayLatitude + " " + wayLongitude);
                            setLocation(location);
                        }
                        if (!isContinue && mFusedLocationClient != null) {
                            mFusedLocationClient.removeLocationUpdates(locationCallback);
                        }
                    }
                }
            }
        };
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
        viewModel.setCurrentPhoneLocation(new Location(""));
        viewModel.setStructures(myDataGetter.getStructures());
        viewModel.setAnimalContainerStructures(myDataGetter.getAnimalContainerStructures());
        viewModel.setContext(this);
        DialogFragment newFragment = new StartLocationDialogFragment(this);
        newFragment.show(this.getSupportFragmentManager(), "Continue");
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
        if (id == R.id.action_clear_memory) {
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putString(CHECKMARK_KEY, EMPTY_CHECKMARKS);
            preferencesEditor.apply();
            AllAppData userModel = new ViewModelProvider(this).get(AllAppData.class);
            userModel.clickClear();

        }
        if (id == R.id.action_convert_to_metric) {

        }
        return super.onOptionsItemSelected(item);
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    AppConstants.LOCATION_REQUEST);
        } else {
            if (isContinue) {
                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
            } else {
                mFusedLocationClient.getLastLocation().addOnSuccessListener(MainActivity.this, location -> {
                    if (location != null) {
                        wayLatitude = location.getLatitude();
                        wayLongitude = location.getLongitude();
                        setLocation(location);
                        Log.i("TOMDEBUG", "Not this one: "+  wayLatitude + " " + wayLongitude);
                    } else {
                        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                        Log.i("TOMDEBUG", "Here? "+  wayLatitude + " " + wayLongitude);
                    }
                });
            }
        }
    }
    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                // If request is cancelled, the result arrays are empty.	
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (isContinue) {
                        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                    } else {
                        mFusedLocationClient.getLastLocation().addOnSuccessListener(MainActivity.this, location -> {
                            if (location != null) {
                                wayLatitude = location.getLatitude();
                                wayLongitude = location.getLongitude();
                                Log.i("TOMDEBUG", "b Not this one: "+  wayLatitude + " " + wayLongitude);
                            } else {
                                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                                Log.i("TOMDEBUG", "a "+  wayLatitude + " " + wayLongitude);
                            }
                        });
                    }
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == AppConstants.GPS_REQUEST) {
                isGPS = true; // flag maintain before get location	
            }
        }
    }
    public void setLocation(Location loc){
        AllAppData userModel = new ViewModelProvider(this).get(AllAppData.class);
        userModel.setCurrentPhoneLocation(loc);
    }

    public static class StartLocationDialogFragment extends DialogFragment {
        MainActivity parentActivity;
        public StartLocationDialogFragment(MainActivity mainActivity) {
            parentActivity = mainActivity;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Instructions: You can search for any animal by entering its name." +
                    " Find endangered or extinct animals by entering those terms. Searching for" +
                    " classes works: Aves only shows birds or Reptilia reptiles. " +
                    "Enable GPS to continue")
                    .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            parentActivity.isContinue = true;
                            parentActivity.getLocation();
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }
}