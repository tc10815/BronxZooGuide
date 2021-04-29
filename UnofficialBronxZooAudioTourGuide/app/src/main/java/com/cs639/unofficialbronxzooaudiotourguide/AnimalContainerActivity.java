package com.cs639.unofficialbronxzooaudiotourguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textview.MaterialTextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AnimalContainerActivity extends AppCompatActivity {

    private static final String TAG = AnimalContainerActivity.class.getSimpleName();
    int indexRecived;
    private RecyclerView recyclerView;
    private String filter;
    protected IndoorRecycleAdapter mAdapter;
    private LinearLayoutManager myLinearLayoutManager;
    private TextView txtTopText;

    int images[] =
            {
                    R.drawable.a1,
                    R.drawable.a2,
                    R.drawable.a3,
                    R.drawable.a4,
                    R.drawable.a5,
                    R.drawable.a6,
                    R.drawable.a7,
                    R.drawable.a8,
                    R.drawable.a9,
                    R.drawable.a10,
                    R.drawable.a11,
                    R.drawable.a12,
                    R.drawable.a13,
                    R.drawable.a14,
                    R.drawable.a15,
                    R.drawable.a16,
                    R.drawable.a17,
                    R.drawable.a18,
                    R.drawable.a19,
                    R.drawable.a20,
                    R.drawable.a21,
                    R.drawable.a22,
                    R.drawable.a23,
                    R.drawable.a24,
                    R.drawable.a25,
                    R.drawable.a26,
                    R.drawable.a27,
                    R.drawable.a28,
                    R.drawable.a29,
                    R.drawable.a30,
                    R.drawable.a31,
                    R.drawable.a32,
                    R.drawable.a33,
                    R.drawable.a34,
                    R.drawable.a35,
                    R.drawable.a36,
                    R.drawable.a37,
                    R.drawable.a38,
                    R.drawable.a39,
                    R.drawable.a40,
                    R.drawable.a41,
                    R.drawable.a42,
                    R.drawable.a43,
                    R.drawable.a44,
                    R.drawable.a45,
                    R.drawable.a46,
                    R.drawable.a47,
                    R.drawable.a48,
                    R.drawable.a49,
                    R.drawable.a50,
                    R.drawable.a51,
                    R.drawable.a52,
                    R.drawable.a53,
                    R.drawable.a54,
                    R.drawable.a55,
                    R.drawable.a56,
                    R.drawable.a57,
                    R.drawable.a58,
                    R.drawable.a59,
                    R.drawable.a60,
                    R.drawable.a61,
                    R.drawable.a62,
                    R.drawable.a63,
                    R.drawable.a64,
                    R.drawable.a65,
                    R.drawable.a66,
                    R.drawable.a67,
                    R.drawable.a68,
                    R.drawable.a69,
                    R.drawable.a70,
                    R.drawable.a71,
                    R.drawable.a72,
                    R.drawable.a73,
                    R.drawable.a74,
                    R.drawable.a75,
                    R.drawable.a76,
                    R.drawable.a77,
                    R.drawable.a78,
                    R.drawable.a79,
                    R.drawable.a80,
                    R.drawable.a81,
                    R.drawable.a82,
                    R.drawable.a83,
                    R.drawable.a84,
                    R.drawable.a85,
                    R.drawable.a86,
                    R.drawable.a87,
                    R.drawable.a88,
                    R.drawable.a89,
                    R.drawable.a90,
                    R.drawable.a91,
                    R.drawable.a92,
                    R.drawable.a93,
                    R.drawable.a94,
                    R.drawable.a95,
                    R.drawable.a96
            };
    ArrayList<Integer> imagesOfItems = new ArrayList<Integer>();
    ArrayList<Animal> animalsListed;
    MaterialTextView txtData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_container);
        txtTopText = findViewById(R.id.txtIndoorTopText);
        XMLDataGetter myData = new XMLDataGetter(this);
        ArrayList<AnimalContainerStructure> parentStructures = myData.getAnimalContainerStructures();
        animalsListed = new ArrayList<Animal>();
        ArrayList<Integer> imagesOfItems = new ArrayList<Integer>();


        Bundle bundle = this.getIntent().getExtras();
        Intent i = this.getIntent();
        indexRecived = i.getIntExtra("parentstructure", 0);
        filter = i.getStringExtra("filter");
        Log.e(TAG, "onCreate: I parentstructure " + i.getIntExtra("parentstructure", 0));
        Log.e(TAG, "onCreate: B parentstructure " + bundle.getInt("parentstructure"));
        Log.e(TAG, "onCreate: I filter" + i.getStringExtra("filter"));
        recyclerView = findViewById(R.id.IndoorRecyclerView);
        String parentStructureName = parentStructures.get(indexRecived).getContainerName();
        if(!filter.equals("")){
            parentStructureName += " filtering results by '" + filter + "'";
        }
        txtTopText.setText(parentStructureName);

        ArrayList<Animal> allAnimalsTemp = myData.getAnimals();
        Log.i("TOMDEBUG", "Here: " + allAnimalsTemp.size());
        for(int iter = 0; iter < allAnimalsTemp.size(); iter++){
            if(allAnimalsTemp.get(iter).getParentStructure() == indexRecived + 2){
                if(filter.equals("") || allAnimalsTemp.get(iter).matchesFilter(filter)) {
                    animalsListed.add(allAnimalsTemp.get(iter));
                    imagesOfItems.add(images[iter]);
                }
            }

        }
        int[] imagesRecycleData = new int[imagesOfItems.size()];
        String[] recycleData = new String[animalsListed.size()];
        String[] recycleData2 = new String[animalsListed.size()];
        for (int iter = 0; iter < animalsListed.size(); iter++){
            recycleData[iter] = animalsListed.get(iter).getZooName();
            recycleData2[iter] = animalsListed.get(iter).getBinomialNomenclature();
            imagesRecycleData[iter] = imagesOfItems.get(iter);
        }



        mAdapter = new IndoorRecycleAdapter(this, recycleData, recycleData2, imagesRecycleData, this);
        myLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLinearLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    private ViewModelStoreOwner requireActivity() {
        return this;
    }

    public void launchAnimal(String animalToLaunch){
        for(int iter = 0; iter < animalsListed.size(); iter++){
            if(animalsListed.get(iter).getZooName().equals(animalToLaunch)){
                Intent intent = new Intent(this, AnimalActivity.class);
                intent.putExtra("animalnumber", (animalsListed.get(iter).getId() -1));
                startActivity(intent);
            }
        }
    }
}