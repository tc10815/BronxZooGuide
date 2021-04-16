package com.cs639.unofficialbronxzooaudiotourguide;

import android.location.Location;
import android.util.Log;


public class AnimalContainerStructure {
    private int id;
    private Location viewingPoints;
    private String containerName;
    public String searchString;

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getViewingPoints() {
        return viewingPoints;
    }

    public void setViewingPoints(Location viewingPoints) {
        this.viewingPoints = viewingPoints;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public AnimalContainerStructure(){
        id = 999;
        viewingPoints = null;
        searchString = "";
    }
    public boolean matchesFilter(String filter){
        String lowerFilter = filter.toLowerCase();
        boolean ret = false;
        searchString = searchString.toLowerCase();
        if(searchString.indexOf(lowerFilter) != -1){
            ret = true;
        }
        return ret;
    }

}
