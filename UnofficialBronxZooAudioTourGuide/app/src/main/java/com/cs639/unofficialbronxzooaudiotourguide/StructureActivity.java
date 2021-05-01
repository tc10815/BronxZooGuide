package com.cs639.unofficialbronxzooaudiotourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class StructureActivity extends AppCompatActivity {
    private Structure myStructure;
    private TextView txtDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_structure);
        XMLDataGetter myData = new XMLDataGetter(this);

        //get the current intent
        Intent intent = getIntent();
        int structureNumber = intent.getIntExtra("structurenumber", 0);
        myStructure = myData.getStructures().get(structureNumber);
        ((AppCompatActivity) this).getSupportActionBar().setTitle(myStructure.getStructureName());

    }
}