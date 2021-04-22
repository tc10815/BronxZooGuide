package com.cs639.unofficialbronxzooaudiotourguide;

/**
 * All this class does is contain all the data that is in a CompassList item
 * in a sortable format so it can be programmatically sorted when a
 * location changes.
 */
public class ComparableDataItem implements Comparable<ComparableDataItem> {
    String s1;
    String s2;
    String s3;
    int i;
    double d;
    int originalSpot;

    /**
     *
     * @param myS1 - name
     * @param myS2 - binom or message
     * @param myI - Image number
     * @param myD - distance from walker in meters as a double
     * @param myOriginalSpot - The original index of this location for mapping
     */
    public ComparableDataItem(String myS1, String myS2, String myS3, int myI, double myD, int myOriginalSpot){
        s1 = myS1;
        s2 = myS2;
        s3 = myS3;
        i = myI;
        d = myD;
        originalSpot = myOriginalSpot;
    }
    public int getOriginalSpot() {
        return originalSpot;
    }

    public void setOriginalSpot(int originalSpot) {
        this.originalSpot = originalSpot;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }



    @Override
    public int compareTo(ComparableDataItem o) {
        double myDouble = o.getD();
        int ret = 0;
        if(d > myDouble) ret = 1;
        if(d < myDouble) ret = -1;
        return ret;
    }
}
