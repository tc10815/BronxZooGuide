package com.cs639.unofficialbronxzooaudiotourguide;

import android.location.Location;


public class AnimalContainerStructure {
    private int id;
    private Location viewingPoints;
    private String containerName;


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
    }

}
