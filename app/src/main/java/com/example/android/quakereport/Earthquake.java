/*
 * Created by Ostap Kozak on 2/16/17 12:21 PM.
 * Copyright (c) 2017. All rights reserved.
 *
 *  Last modified 2/16/17 12:03 PM
 */

package com.example.android.quakereport;

/**
 * Created by ostapkozak on 16/02/2017.
 */

public class Earthquake {

    // Earthquake magnitude
    private double mMagnitude;

    // Location where the earthquake occurred
    private String mLocation;

    /** Time of the earthquake */
    private long mTimeInMilliseconds;

    /** Earhquake URL **/
    private String mUrl;


    public Earthquake(double magnitude, String location, long date, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = date;
        mUrl = url;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getEarthquakeUrl() { return mUrl; }
}
