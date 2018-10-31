package com.ksntechnology.projectfilemanager;

import android.app.Application;

import com.ksntechnology.projectfilemanager.manager.Contextor;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
