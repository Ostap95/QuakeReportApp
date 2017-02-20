/*
 * Created by Ostap Kozak on 2/20/17 8:43 AM.
 * Copyright (c) 2017. All rights reserved.
 *
 *  Last modified 2/20/17 8:43 AM
 */

package com.example.android.quakereport;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import static com.example.android.quakereport.QueryUtils.fetchData;

/**
 * Created by ostapkozak on 20/02/2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    public List<Earthquake> loadInBackground() {
        return fetchData(mUrl);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
