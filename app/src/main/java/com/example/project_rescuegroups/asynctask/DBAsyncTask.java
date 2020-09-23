package com.example.project_rescuegroups.asynctask;

import android.os.AsyncTask;

import com.example.project_rescuegroups.database.AnimalDB;
import com.example.project_rescuegroups.model.Animal;
import com.example.project_rescuegroups.util.DBParamData;

import java.util.List;

public class DBAsyncTask extends AsyncTask<DBParamData, Void, Void> {
    List<Animal> animalList;
    AnimalDB db;

    @Override
    protected Void doInBackground(DBParamData... dbParamData) {
        animalList = dbParamData[0].getAnimalList();
        db = dbParamData[0].getSh();

        if (animalList != null) {
            for (Animal animal : animalList) {
                db.addAnimals(animal);
            }
        }
        return null;
    }


}
