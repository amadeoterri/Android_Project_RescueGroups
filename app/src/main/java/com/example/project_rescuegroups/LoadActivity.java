package com.example.project_rescuegroups;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_rescuegroups.asynctask.AnimalsAsyncTask;
import com.example.project_rescuegroups.asynctask.OrganizationAsyncTask;

import java.io.File;

public class LoadActivity extends AppCompatActivity {

    Context context;
    private final static String LOG_TAG = "logging";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_load);

        //kijken of de database al bestaat
        if (!doesDatabaseExist(this, "Animals")) {
            new AnimalsAsyncTask(this).execute("pets.json");
            new AnimalsAsyncTask(this).execute("pets2.json");
            new AnimalsAsyncTask(this).execute("pets3.json");
            new AnimalsAsyncTask(this).execute("pets4.json");
            new AnimalsAsyncTask(this).execute("pets5.json");
            new AnimalsAsyncTask(this).execute("pets6.json");
            new AnimalsAsyncTask(this).execute("pets7.json");
            new OrganizationAsyncTask(this).execute();
            LoadingScreen();
        } else {
            Log.e(LOG_TAG, "Database already exists");
            Intent i = new Intent(LoadActivity.this, MainActivity.class);
            startActivity(i);
        }
    }

    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    private void LoadingScreen() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading animals");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();

        new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                dialog.dismiss();
                dialog.hide();
                Intent i = new Intent(LoadActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }.start();
    }
}
