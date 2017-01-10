package com.kevin.agecalculator;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

/**
 * Created by kevin on 1/9/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
    }
}
