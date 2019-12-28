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
        cv.put(AnimalFavoritesTabel.ANIMAL_FAVORITED,1);

        SQLiteDatabase sd = getWritableDatabase();

        return sd.insert(AnimalFavoritesTabel.TABEL_NAAM,AnimalFavoritesTabel.ANIMAL_ID,cv);
    }

    public Cursor getFavorites(){
        SQLiteDatabase sd = getWritableDatabase();
        /*return sd.rawQuery("SELECT "+ AnimalTabel.KEY_ID + ","
                                        + AnimalTabel.ANIMAL_ID + ","
                                        + AnimalTabel.ANIMAL_NAME + ","
                                        + AnimalTabel.ANIMAL_SPECIES + ","
                                        + AnimalTabel.ANIMAL_BREED + ","
                                        + AnimalTabel.ANIMAL_BIRTHDATE + ","
                                        + AnimalTabel.ANIMAL_IMAGE + ","
                                        + AnimalTabel.ANIMAL_DESC
                                        + " FROM " + AnimalFavoritesTabel.TABEL_NAAM + " INNER JOIN " + AnimalTabel.TABEL_NAAM
                                        + " ON " + AnimalTabel.TABEL_NAAM + "." + AnimalTabel.ANIMAL_ID + " = " + AnimalFavoritesTabel.TABEL_NAAM + "." + AnimalFavoritesTabel.ANIMAL_ID
                                        + " WHERE " + AnimalFavoritesTabel.ANIMAL_FAVORITED + " = 1" ,null);*/

        return sd.rawQuery("SELECT * FROM " + AnimalTabel.TABEL_NAAM + " , " + AnimalFavoritesTabel.TABEL_NAAM
                + " WHERE " + AnimalTabel.TABEL_NAAM + "." + AnimalTabel.ANIMAL_ID + " = " + AnimalFavoritesTabel.TABEL_NAAM + "." + AnimalFavoritesTabel.ANIMAL_ID
                + " AND " + AnimalFavoritesTabel.ANIMAL_FAVORITED + " = 1"
                ,null);
    }
}
