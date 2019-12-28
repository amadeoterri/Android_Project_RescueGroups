package com.example.project_rescuegroups.util;

import android.app.Application;

import com.example.project_rescuegroups.asynctask.AnimalsAsyncTask;

public class ApplicationStartup extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new AnimalsAsyncTask(this).execute();
    }
}
