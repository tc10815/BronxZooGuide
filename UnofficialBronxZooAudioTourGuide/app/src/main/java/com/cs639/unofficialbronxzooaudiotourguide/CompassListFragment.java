package com.cs639.unofficialbronxzooaudiotourguide;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 *
 * This Fragment shows the all outside animals; it's the fragment that is loaded first when the
 * project is loaded, showing a list of outside animals and structures with a compass arrow
 * pointing towards it.
 *
 * @author Tom Cookson
 */
public class CompassListFragment extends Fragment {
    String s1[], s2[];
    int images[] = {R.drawable.a1, R.drawable.a2, R.drawable.a3,
            R.drawable.a4, R.drawable.a5, R.drawable.a6,
            R.drawable.a7, R.drawable.a8, R.drawable.s1,
            R.drawable.s2};
    RecyclerView recyclerView;
    protected OutdoorRecycleAdapter mAdapter;
    View rootView;
    AllAppData userModel;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

    ) {
        rootView = inflater.inflate(R.layout.fragment_first, container, false);
        // Inflate the layout for this fragment
        recyclerView = rootView.findViewById(R.id.OutdoorRecyclerView);
        userModel = new ViewModelProvider(requireActivity()).get(AllAppData.class);
        int width = userModel.getScreenSize();
        userModel.setCompassList(this);
        s1 = buildNameList(userModel.getAnimals(), userModel.getStructures());
        s2 = buildBinomList(userModel.getAnimals(), userModel.getStructures());
        mAdapter = new OutdoorRecycleAdapter(rootView.getContext(), s1, s2, images, 10);
        mAdapter.setMyAppData(userModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView.setAdapter(mAdapter);


        return rootView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private String[] buildNameList(ArrayList<Animal> animals, ArrayList<Structure> structures){
        int size = animals.size() + structures.size();
        String[] ret = new String[size];
        for(int i = 0 ; i < animals.size(); i++){
            ret[i] = animals.get(i).getZooName();
        }
        for(int i = animals.size(); i < size; i++){
            int temp = 0;
            ret[i] = structures.get(temp).getStructureName();
            temp++;
        }
        return ret;
    }

    private String[] buildBinomList(ArrayList<Animal> animals, ArrayList<Structure> structures){
        int size = animals.size() + structures.size();
        String[] ret = new String[size];
        for(int i = 0 ; i < animals.size(); i++){
            ret[i] = animals.get(i).getBinomialNomenclature();
        }
        for(int i = animals.size(); i < size; i++){
            int temp = 0;
            ret[i] = "structure";
            temp++;
        }
        return ret;
    }

    public void launchAnimalActivity(int animalNumber){
        Intent intent = new Intent(rootView.getContext(), AnimalActivity.class);
        intent.putExtra("animalnumber", animalNumber);
        Log.i("TOMDEBUG", "Launching animal");
        startActivity(intent);
    }

    public void launchStructureActivity(int structureNumber){
        Intent intent = new Intent(rootView.getContext(), StructureActivity.class);
        Log.i("TOMDEBUG", "Launching structure");
        intent.putExtra("structurenumber", structureNumber);
        startActivity(intent);
    }
    public void launchAnimalsStructureActivity(int structureNumber){
        Intent intent = new Intent(rootView.getContext(), AnimalActivity.class);
        startActivity(intent);
    }
}