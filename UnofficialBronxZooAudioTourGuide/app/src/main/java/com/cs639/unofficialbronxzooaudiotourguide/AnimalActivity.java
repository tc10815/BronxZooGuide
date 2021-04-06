package com.cs639.unofficialbronxzooaudiotourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * This activity displays all xml information about an animal, provides a picture, provides
 * audio, and offers links to both wikipedia and EOL pages on the animal.
 *
 * @author Rucha
 */
public class AnimalActivity extends AppCompatActivity {
    private Animal myAnimal;
    private TextView txtZooName;
    private TextView txtBinom;
    private TextView txtDesc;

    public AnimalActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_screen);
        XMLDataGetter myData = new XMLDataGetter(this);
        //get the current intent
        Intent intent = getIntent();
        int animalNumber = intent.getIntExtra("animalnumber", 0);

        myAnimal = myData.getAnimals().get(animalNumber);
        txtZooName = findViewById(R.id.txtAnimalZooName);
        txtBinom = findViewById(R.id.txtAnimalBinom);
        txtDesc = findViewById(R.id.txtAnimalDesc);

        txtZooName.setText(myAnimal.getZooName());
        txtBinom.setText(myAnimal.getBinomialNomenclature());
        txtDesc.setText(myAnimal.getDescription().get(0));
   }

}