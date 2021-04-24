package com.cs639.unofficialbronxzooaudiotourguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.textview.MaterialTextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AnimalContainerActivity extends AppCompatActivity {

    private static final String TAG = AnimalContainerActivity.class.getSimpleName();
    AllAppData userModel;
    int indexRecived;
    private RecyclerView recyclerView;
    IndoorRecycleAdapter mAdapter;
    List<Animal> dataList = new ArrayList<>();
    int images[];
    ArrayList<Integer> imagesOfItems = new ArrayList<Integer>();
    MaterialTextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_container);
        XMLDataGetter myData = new XMLDataGetter(this);

        Bundle bundle = this.getIntent().getExtras();
        Intent i = this.getIntent();
        indexRecived = bundle.getInt("parentstructure");
        Log.e(TAG, "onCreate: I parentstructure " + i.getIntExtra("parentstructure", 0));
        Log.e(TAG, "onCreate: B parentstructure " + bundle.getInt("parentstructure"));
        Log.e(TAG, "onCreate: Size" + myData.getAnimalContainerStructures().size());
        Log.e(TAG, "onCreate: I filter" + i.getStringExtra("filter"));
        recyclerView = findViewById(R.id.rvAnimalContainerActivity);
        txtData = findViewById(R.id.txtDataNotPresent);
        dataList = new ArrayList<>();
        int index = 0;
        for (Animal items : myData.getAnimals()) {
            if (items.getParentStructure() == indexRecived) {
                dataList.add(items);
                imagesOfItems.add(CompassListFragment.animalimages[index]);
            }
            index++;
        }
        //Using private var instead of return since different type. Not good code.
        images = new int[imagesOfItems.size()];
        for (int j = 0; j < imagesOfItems.size(); j++) {
            images[j] = imagesOfItems.get(j);
        }


        if (dataList.size() <= 0) {
            String s = getResources().getString(R.string.no_data_for_patent_structure);
            s += " " + indexRecived;
            txtData.setText(s);
            txtData.setVisibility(View.VISIBLE);
        }
        mAdapter = new IndoorRecycleAdapter(this, dataList, images);
        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLinearLayoutManager);
        recyclerView.setAdapter(mAdapter);


    }

    private ViewModelStoreOwner requireActivity() {
        return this;
    }
}