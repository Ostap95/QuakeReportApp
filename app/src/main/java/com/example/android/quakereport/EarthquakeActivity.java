/*
 * Created by Ostap Kozak on 2/16/17 12:21 PM.
 * Copyright (c) 2017. All rights reserved.
 *
 *  Last modified 2/16/17 11:52 AM
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.android.quakereport.QueryUtils.fetchData;

public class EarthquakeActivity extends AppCompatActivity {


    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_REQUEST_LINK = "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_LINK);

    }


    private void setAdapter(ArrayList<Earthquake> list) {
        // Create a fake list of earthquake locations.
        ArrayList<Earthquake> earthquakes = list;

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        final EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Earthquake earthquake = adapter.getItem(i);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(earthquake.getEarthquakeUrl()));
                startActivity(intent);
            }
        });
    }


    private class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {

        @Override
        protected ArrayList<Earthquake> doInBackground(String... strings) {
            if (strings.length != 0) {
                return fetchData(strings[0]);
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
            super.onPostExecute(earthquakes);
            setAdapter(earthquakes);
        }
    }
}
