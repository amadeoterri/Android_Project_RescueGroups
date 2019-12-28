package com.example.project_rescuegroups.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.project_rescuegroups.model.Animal;

public class AnimalFavoritesDB extends SchemaHelper
{
    public AnimalFavoritesDB(Context context) {
        super(context);
    }

    public long addFavorite(Animal animal){
        if(!(hasObject(animal.getAnimalId()))){


        ContentValues cv = new ContentValues();
        cv.put(AnimalFavoritesTabel.ANIMAL_ID,animal.getAnimalId());
        cv.put(AnimalFavoritesTabel.ANIMAL_FAVORITED,1);

        SQLiteDatabase sd = getWritableDatabase();

        return sd.insert(AnimalFavoritesTabel.TABEL_NAAM,AnimalFavoritesTabel.ANIMAL_ID,cv);
        }
        else{
            return 0;
        }
    }

    public Cursor getFavorites(){
        SQLiteDatabase sd = getWritableDatabase();

        return sd.rawQuery("SELECT * FROM " + AnimalTabel.TABEL_NAAM + " , " + AnimalFavoritesTabel.TABEL_NAAM
                + " WHERE " + AnimalTabel.TABEL_NAAM + "." + AnimalTabel.ANIMAL_ID + " = " + AnimalFavoritesTabel.TABEL_NAAM + "." + AnimalFavoritesTabel.ANIMAL_ID
                + " AND " + AnimalFavoritesTabel.ANIMAL_FAVORITED + " = 1"
                ,null);
    }

    public boolean hasObject(String id) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + AnimalFavoritesTabel.TABEL_NAAM + " WHERE " + AnimalFavoritesTabel.ANIMAL_ID + " =?";

        Cursor cursor = db.rawQuery(selectString, new String[] {id});

        boolean hasObject = false;
        if(cursor.moveToFirst()){
            hasObject = true;
            int count = 0;
            while(cursor.moveToNext()){
                count++;
            }
            //here, count is records found
            Log.d("recordfound", String.format("%d records found", count));

            //endregion

        }

        cursor.close();
        db.close();
        return hasObject;
    }

    public void removeFavorite(Animal animal_favorite) {
        SQLiteDatabase sd = getWritableDatabase();
        sd.delete(AnimalFavoritesTabel.TABEL_NAAM,AnimalFavoritesTabel.ANIMAL_ID + " = ?",new String[]{animal_favorite.getAnimalId()});
    }
}
