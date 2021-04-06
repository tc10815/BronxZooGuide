package com.cs639.unofficialbronxzooaudiotourguide;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class AllAppData extends ViewModel {
    private final MutableLiveData<String> userLiveData = new MutableLiveData<>();
    private int screenSize;
    private String userFilter;
    private ArrayList<Animal> animals;
    private ArrayList<Structure> structures;
    private Animal currentlySelectedAnimal;
    private Structure currentlySelectedStructure;
    private CompassListFragment compassList;
    public Animal getCurrentlySelectedAnimal() {
        return currentlySelectedAnimal;
    }

    public void setCurrentlySelectedAnimal(Animal currentlySelectedAnimal) {
        this.currentlySelectedAnimal = currentlySelectedAnimal;
    }

    public Structure getCurrentlySelectedStructure() {
        return currentlySelectedStructure;
    }

    public void setCurrentlySelectedStructure(Structure currentlySelectedStructure) {
        this.currentlySelectedStructure = currentlySelectedStructure;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    public ArrayList<Structure> getStructures() {
        return structures;
    }

    public void setStructures(ArrayList<Structure> structures) {
        this.structures = structures;
    }


    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }
    public String getUserFilter() {
        return userFilter;
    }

    public void setUserFilter(String userFilter) {
        this.userFilter = userFilter;
    }
    public MutableLiveData<String> getUser() {
        return userLiveData;
    }

    public boolean isAnimalSelected(){
        return false;
    }
    public boolean isAnimalStub(){
        return false;
    }
    public void UserModel() {
        // trigger user load.
    }

    void doAction() {
        // depending on the action, do necessary business logic calls and update the
        // userLiveData.
    }

    public void compassViewClicked(int numberClicked){
        Log.i("TOMDEBUG","Number clicked is " + numberClicked + ". Animals size is " + animals.size());

        if(numberClicked < animals.size()) {
            Log.i("TOMDEBUG","Animal clicked");

            currentlySelectedAnimal = animals.get(numberClicked);
            compassList.launchAnimalActivity(numberClicked);
        } else {
            Log.i("TOMDEBUG","Structure clicked");
            int structureNum = numberClicked - animals.size();
            compassList.launchStructureActivity(structureNum);
        }
    }
    public CompassListFragment getCompassList() {
        return compassList;
    }

    public void setCompassList(CompassListFragment compassList) {
        this.compassList = compassList;
    }

}
