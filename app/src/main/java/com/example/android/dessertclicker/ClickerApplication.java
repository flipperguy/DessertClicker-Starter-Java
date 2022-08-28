package com.example.android.dessertclicker;

import android.app.Application;

import timber.log.Timber;

public class ClickerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
