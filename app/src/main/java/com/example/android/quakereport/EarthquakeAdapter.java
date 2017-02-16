/*
 * Created by Ostap Kozak on 2/16/17 12:21 PM.
 * Copyright (c) 2017. All rights reserved.
 *
 *  Last modified 2/16/17 12:21 PM
 */

package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;



public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for current position
        Earthquake earthquake = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView magnitude = (TextView) convertView.findViewById(R.id.magnitude);
        TextView location = (TextView) convertView.findViewById(R.id.location);
        TextView date = (TextView) convertView.findViewById(R.id.date);

        magnitude.setText(Integer.toString(earthquake.getMagnitude()));
        location.setText(earthquake.getLocation());



        return convertView;
    }


    /**
     * Formats the magnitude value, showing 1 decimal place.
     * @param magnitude
     * @return the formatted value from double magnitude value
     */
    public String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
}
