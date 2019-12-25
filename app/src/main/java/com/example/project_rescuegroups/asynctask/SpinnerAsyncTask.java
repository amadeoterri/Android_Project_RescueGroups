package com.example.project_rescuegroups.asynctask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project_rescuegroups.R;
import com.example.project_rescuegroups.adapter.CustomSpinnerAdapter;
import com.example.project_rescuegroups.database.AnimalDB;
import com.example.project_rescuegroups.util.SearchParamData;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAsyncTask extends AsyncTask<SearchParamData, Void, List<String>> {

    private Context context;
    private Spinner spinner;
    private String species;

    //constructor om context te weten
    public SpinnerAsyncTask(Context myContext) {
        this.context = myContext;
    }

    @Override
    protected List<String> doInBackground(SearchParamData... searchParamData) {
        spinner = searchParamData[0].getSpinner();
        species = searchParamData[0].getsRadioSelection();
        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("All");
        AnimalDB sh = new AnimalDB(context);
        Cursor cursor = null;
        switch(searchParamData[0].getsSpinnerId()){
            case "breed":cursor = sh.getAnimalsUniqueBreed(species);
            break;
            default:break;
        }
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                spinnerArray.add(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        return spinnerArray;
    }

    @Override
    protected void onPostExecute(List<String> strings) {
        CustomSpinnerAdapter spinnerArrayAdapter = new CustomSpinnerAdapter(context,strings);
        spinner.setAdapter(spinnerArrayAdapter);
    }
}
