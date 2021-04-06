package com.cs639.unofficialbronxzooaudiotourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * This activity displays all xml information about an strcture, provides a picture, provides
 * audio, and offers links.
 *
 * @author Janvi
 */
public class StructureActivity extends AppCompatActivity {
    private Structure myStructure;
    private TextView txtName;
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
        txtName = findViewById(R.id.txtStructureName);
        txtDesc = findViewById(R.id.txtStructureDesc);

        txtName.setText(myStructure.getStructureName());
        txtDesc.setText(myStructure.getDescription().get(0));
    }
}