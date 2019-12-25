package com.example.project_rescuegroups.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_rescuegroups.model.Animal;

public class AnimalDB extends SchemaHelper{

    public AnimalDB(Context context) {
        super(context);
    }

    public long addAnimals(Animal animal){
        ContentValues cv = new ContentValues();
        cv.put(AnimalTabel.ANIMAL_ID,animal.getAnimalId());
        cv.put(AnimalTabel.ANIMAL_NAME,animal.getAnimalName());
        cv.put(AnimalTabel.ANIMAL_SPECIES,animal.getAnimalSpecies());
        cv.put(AnimalTabel.ANIMAL_BREED,animal.getAnimalBreed());
        cv.put(AnimalTabel.ANIMAL_SEX,animal.getAnimalSex());
        cv.put(AnimalTabel.ANIMAL_BIRTHDATE,animal.getAnimalBirthDate());
        cv.put(AnimalTabel.ANIMAL_IMAGE,animal.getAnimalImageUrl());
        cv.put(AnimalTabel.ANIMAL_DESC,animal.getAnimalDescription());

        SQLiteDatabase sd = getWritableDatabase();

        return sd.insert(AnimalTabel.TABEL_NAAM,AnimalTabel.ANIMAL_NAME,cv);
    }

    public Cursor getAllAnimals(){
        SQLiteDatabase sd = getWritableDatabase();
        /*return sd.query(AnimalTabel.TABEL_NAAM,resultColumn,null,null,null,null,null,null);*/
        return sd.rawQuery("SELECT * FROM " + AnimalTabel.TABEL_NAAM,null);
    }

    public Cursor getAnimalsWithParams(String choice) {
        SQLiteDatabase sd = getWritableDatabase();
        /*return sd.query(AnimalTabel.TABEL_NAAM,resultColumn,null,null,null,null,null,null);*/
        return sd.rawQuery("SELECT * FROM " + AnimalTabel.TABEL_NAAM + " WHERE " + AnimalTabel.ANIMAL_SPECIES + " =  ?",new String[]{choice});
    }

    public Cursor getAnimalsUniqueSpecies(){
        /*String[] resultColumn = {AnimalTabel.ANIMAL_ID,AnimalTabel.ANIMAL_NAME,AnimalTabel.ANIMAL_SPECIES,AnimalTabel.ANIMAL_BREED,
                AnimalTabel.ANIMAL_SEX,AnimalTabel.ANIMAL_BIRTHDATE,AnimalTabel.ANIMAL_IMAGE,AnimalTabel.ANIMAL_DESC};*/
        SQLiteDatabase sd = getReadableDatabase();
        return sd.query(true,AnimalTabel.TABEL_NAAM,new String[]{AnimalTabel.ANIMAL_SPECIES},null,null,AnimalTabel.ANIMAL_SPECIES,null,null,null);
    }

    public Cursor getAnimalsUniqueBreed(){
        SQLiteDatabase sd = getReadableDatabase();
        return sd.query(true,AnimalTabel.TABEL_NAAM,new String[]{AnimalTabel.ANIMAL_BREED},null,null,AnimalTabel.ANIMAL_BREED,null,null,null);
    }
}
