package com.example.project_rescuegroups.asynctask;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import com.example.project_rescuegroups.R;
import com.example.project_rescuegroups.database.AnimalFavoritesDB;
import com.example.project_rescuegroups.model.Animal;

import java.util.List;

public class FavoriteAsyncTask extends AsyncTask<Animal, Void, Animal> {

    Context context;

    public FavoriteAsyncTask(Context context) {
        this.context = context;
    }


    @Override
    protected Animal doInBackground(Animal... animals) {
        Animal currentanimal = animals[0];
        AnimalFavoritesDB sh = new AnimalFavoritesDB(context);
        final Cursor cursor = sh.getFavorites();
        int isFavorited = currentanimal.getImgFavorite();
        /*if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String id = cursor.getString(1);
                int favorited = cursor.getInt(11);
                String currentAnimalId = currentanimal.getAnimalId();
                if(id.equals(currentAnimalId) && favorited == 1){
                    isFavorited = true;
                    currentanimal.setImgFavorite(R.drawable.favorite);
                    break;
                }
                cursor.moveToNext();
            }
        }*/
        if(isFavorited == currentanimal.getImgIds()[0]) {
            currentanimal.setImgFavorite(currentanimal.getImgIds()[1]);
            addDbFavorite(currentanimal);
        }
        else{
            currentanimal.setImgFavorite(currentanimal.getImgIds()[0]);
            removeDBFavorite(currentanimal);
        }
        return currentanimal;
    }

    @Override
    protected void onPostExecute(Animal animal) {
        int isFavorited = animal.getImgFavorite();
        if(isFavorited == 0) {
            addDbFavorite(animal);
        }
        else{
            removeDBFavorite(animal);
        }
    }

    private void addDbFavorite(Animal animal_favorite){
        AnimalFavoritesDB sh = new AnimalFavoritesDB(context);
        sh.addFavorite(animal_favorite);
    }

    private void removeDBFavorite(Animal animal_favorite){
        AnimalFavoritesDB sh = new AnimalFavoritesDB(context);
        sh.removeFavorite(animal_favorite);
    }
}
