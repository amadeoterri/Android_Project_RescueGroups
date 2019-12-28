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
        cv.put(AnimalFavoritesTabel.ANIMAL_SPECIES,animal.getAnimalSpecies());
        cv.put(AnimalFavoritesTabel.ANIMAL_BREED,animal.getAnimalBreed());
        cv.put(AnimalFavoritesTabel.ANIMAL_SEX,animal.getAnimalSex());
        cv.put(AnimalFavoritesTabel.ANIMAL_BIRTHDATE,animal.getAnimalBirthDate());
        cv.put(AnimalFavoritesTabel.ANIMAL_IMAGE,animal.getAnimalImageUrl());
        cv.put(AnimalFavoritesTabel.ANIMAL_DESC,animal.getAnimalDescription());

        SQLiteDatabase sd = getWritableDatabase();

        return sd.insert(AnimalFavoritesTabel.TABEL_NAAM,AnimalFavoritesTabel.ANIMAL_ID,cv);
    }

    public Cursor getFavorites(){
        SQLiteDatabase sd = getWritableDatabase();
        return sd.rawQuery("SELECT * FROM " + AnimalFavoritesTabel.TABEL_NAAM,null);
    }
}
