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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;


public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";


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
        TextView locationOffset = (TextView) convertView.findViewById(R.id.location_offset);
        TextView primaryLocation = (TextView) convertView.findViewById(R.id.primary_location);

        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView time = (TextView) convertView.findViewById(R.id.time);

        magnitude.setText(formatMagnitude(earthquake.getMagnitude()));
        String[] parts = formatString(earthquake.getLocation());
        locationOffset.setText(parts[0] + LOCATION_SEPARATOR);
        primaryLocation.setText(parts[1].trim());

        Date dateObj = new Date(earthquake.getTimeInMilliseconds());

        date.setText(formatDate(dateObj));
        time.setText(formatTime(dateObj));

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


    /**
     * @return return the formatted date string.
     */
    public String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(date);
    }

    /**
     * @return formatted time from a given time in milliseconds.
     */
    public String formatTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(date);
    }



    public String[] formatString(String string) {
        if (string.contains(LOCATION_SEPARATOR)) {
            // then split
            return string.split(LOCATION_SEPARATOR);
        } else {
            String[] parts = new String[] {getContext().getResources().getString(R.string.near_the), string};
            return parts;
        }
    }
}
