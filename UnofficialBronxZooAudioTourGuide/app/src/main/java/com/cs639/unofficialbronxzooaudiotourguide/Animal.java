package com.cs639.unofficialbronxzooaudiotourguide;

import android.location.Location;
import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;

public class Animal {
    private int id;
    private int parentStructure;
    private String zooName;
    private String family;
    private String animalClass;
    private String conservationStatus;
    private String naturalLocation;
    private String binomialNomenclature;
    private String eolLink;
    private String wikiLink;
    private ArrayList<String> otherResourceLinks;
    private ArrayList<String> otherResourceNames;
    private ArrayList<String> commonNames;
    private ArrayList<String> description;
    private ArrayList<Location> viewingPoints;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public ArrayList<Location> getViewingPoints() {
        return viewingPoints;
    }

    public void setViewingPoints(ArrayList<Location> viewingPoints) {
        this.viewingPoints = viewingPoints;
    }

    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

    public String getZooName() {
        return zooName;
    }

    public void setZooName(String zooName) {
        this.zooName = zooName;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getAnimalClass() {
        return animalClass;
    }

    public void setAnimalClass(String animalClass) {
        this.animalClass = animalClass;
    }

    public String getConservationStatus() {
        return conservationStatus;
    }

    public void setConservationStatus(String conservationStatus) {
        this.conservationStatus = conservationStatus;
    }

    public String getNaturalLocation() {
        return naturalLocation;
    }

    public void setNaturalLocation(String naturalLocation) {
        this.naturalLocation = naturalLocation;
    }

    public String getBinomialNomenclature() {
        return binomialNomenclature;
    }

    public void setBinomialNomenclature(String binomialNomenclature) {
        this.binomialNomenclature = binomialNomenclature;
    }

    public String getEolLink() {
        return eolLink;
    }

    public void setEolLink(String eolLink) {
        this.eolLink = eolLink;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public ArrayList<String> getOtherResourceLinks() {
        return otherResourceLinks;
    }

    public void setOtherResourceLinks(ArrayList<String> otherResourceLinks) {
        this.otherResourceLinks = otherResourceLinks;
    }

    public ArrayList<String> getOtherResourceNames() {
        return otherResourceNames;
    }

    public void setOtherResourceNames(ArrayList<String> otherResourceNames) {
        this.otherResourceNames = otherResourceNames;
    }

    public ArrayList<String> getCommonNames() {
        return commonNames;
    }

    public void setCommonNames(ArrayList<String> commonNames) {
        this.commonNames = commonNames;
    }

    public int getParentStructure() {
        return parentStructure;
    }

    public void setParentStructure(int parentStructure) {
        this.parentStructure = parentStructure;
    }
    public Animal(){
        zooName = "error";
        family = "error";
        animalClass = "error";
        conservationStatus = "error";
        naturalLocation = "error";
        binomialNomenclature = "error";
        eolLink = "error";
        wikiLink = "error";
        id = -1;
        parentStructure = 0;
    }
}
