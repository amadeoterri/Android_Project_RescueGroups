package com.example.project_rescuegroups.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_rescuegroups.model.Animal;

public class AnimalFavoritesDB extends SchemaHelper
{
    public AnimalFavoritesDB(Context context) {
        super(context);
    }

    public long addFavorite(Animal animal){
        ContentValues cv = new ContentValues();
        cv.put(AnimalFavoritesTabel.ANIMAL_ID,animal.getAnimalId());
        cv.put(AnimalFavoritesTabel.ANIMAL_NAME,animal.getAnimalName());

        SQLiteDatabase sd = getWritableDatabase();

        return sd.insert(AnimalFavoritesTabel.TABEL_NAAM,AnimalFavoritesTabel.ANIMAL_ID,cv);
    }

    public Cursor getFavorites(){
        String[] resultColumn = {AnimalFavoritesTabel.ID,AnimalFavoritesTabel.ANIMAL_ID,AnimalFavoritesTabel.ANIMAL_NAME};

        SQLiteDatabase sd = getWritableDatabase();
        return sd.query(AnimalFavoritesTabel.TABEL_NAAM,resultColumn,null,null,null,null,null,null);
    }
}
