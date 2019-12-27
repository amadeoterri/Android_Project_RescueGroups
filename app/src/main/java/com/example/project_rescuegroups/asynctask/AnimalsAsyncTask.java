package com.example.project_rescuegroups.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.example.project_rescuegroups.database.AnimalDB;
import com.example.project_rescuegroups.model.Animal;
import com.example.project_rescuegroups.response.JSONResponseImplSingle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AnimalsAsyncTask extends AsyncTask<Void, Void, List<Animal>> {

    private ListView listView;
    Context context;

    public AnimalsAsyncTask(Context myContext) {
        this.context = myContext;
    }

    @Override
    protected List<Animal> doInBackground(Void... voids) {
        List<Animal> animalList = loadJSONFromAsset();
        return animalList;
    }

    @Override
    protected void onPostExecute(List<Animal> animals) {
        super.onPostExecute(animals);
        AnimalDB sh = new AnimalDB(context);
        for(Animal animal:animals){
            sh.addAnimals(animal);
        }
    }

    public List<Animal> loadJSONFromAsset() {
        List<Animal> animals = new ArrayList();
        try {
            InputStream is = context.getAssets().open("pets.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line ="";
            int counter = 1;
            while (((line = br.readLine()) != null && counter < 1000)) {
                JSONResponseImplSingle responseImpl = new JSONResponseImplSingle();
                Animal animal = responseImpl.handleAnimalResponse(line);
                if(animal !=null){
                animals.add(animal);}
                counter++;
            }
            return animals;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

    }


}
