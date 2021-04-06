package com.cs639.unofficialbronxzooaudiotourguide;

import android.location.Location;
import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;

public class Structure {
    private ArrayList<String> description;
    private ArrayList<Location> viewingPoints;
    private ArrayList<String> primaryResourceLinks;
    private ArrayList<String> primaryResourceNames;
    private ArrayList<String> secondaryResourceLinks;
    private ArrayList<String> secondaryResourceNames;
    private String structureName;
    private int id;

    public Structure(){
        ArrayList<String> description = new ArrayList<String>();
        ArrayList<Location> viewingPoints = new ArrayList<Location>();
        ArrayList<String> primaryResourceLinks = new ArrayList<String>();
        ArrayList<String> primaryResourceNames = new ArrayList<String>();
        ArrayList<String> secondaryResourceLinks = new ArrayList<String>();
        ArrayList<String> secondaryResourceNames = new ArrayList<String>();
        structureName = "error";
        id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

    public ArrayList<Location> getViewingPoints() {
        return viewingPoints;
    }

    public void setViewingPoints(ArrayList<Location> viewingPoints) {
        this.viewingPoints = viewingPoints;
    }

    public ArrayList<String> getPrimaryResourceLinks() {
        return primaryResourceLinks;
    }

    public void setPrimaryResourceLinks(ArrayList<String> primaryResourceLinks) {
        this.primaryResourceLinks = primaryResourceLinks;
    }

    public ArrayList<String> getPrimaryResourceNames() {
        return primaryResourceNames;
    }

    public void setPrimaryResourceNames(ArrayList<String> primaryResourceNames) {
        this.primaryResourceNames = primaryResourceNames;
    }

    public ArrayList<String> getSecondaryResourceLinks() {
        return secondaryResourceLinks;
    }

    public void setSecondaryResourceLinks(ArrayList<String> secondaryResourceLinks) {
        this.secondaryResourceLinks = secondaryResourceLinks;
    }

    public ArrayList<String> getSecondaryResourceNames() {
        return secondaryResourceNames;
    }

    public void setSecondaryResourceNames(ArrayList<String> secondaryResourceNames) {
        this.secondaryResourceNames = secondaryResourceNames;
    }
}
