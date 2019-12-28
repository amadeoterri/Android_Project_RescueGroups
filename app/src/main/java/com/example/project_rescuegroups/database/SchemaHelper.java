package com.example.project_rescuegroups.database;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.project_rescuegroups.model.Animal;

import java.util.ArrayList;

/**
 * Created by jean-pierre on 2/03/2018.
 */

public class SchemaHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAAM = "Animals";
    private static final int DATABASE_VERSIE = 5;

    public SchemaHelper(Context context) {

        super(context, DATABASE_NAAM, null, DATABASE_VERSIE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "
                + AnimalTabel.TABEL_NAAM
                + " (" + AnimalTabel.KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AnimalTabel.ANIMAL_ID + " TEXT,"
                + AnimalTabel.ANIMAL_NAME + " TEXT,"
                + AnimalTabel.ANIMAL_SPECIES + " TEXT,"
                + AnimalTabel.ANIMAL_BREED + " TEXT,"
                + AnimalTabel.ANIMAL_SEX + " TEXT,"
                + AnimalTabel.ANIMAL_BIRTHDATE + " TEXT,"
                + AnimalTabel.ANIMAL_IMAGE + " TEXT,"
                + AnimalTabel.ANIMAL_DESC + " TEXT);"
        );

        db.execSQL("CREATE TABLE "
                + AnimalFavoritesTabel.TABEL_NAAM
                + " (" + AnimalFavoritesTabel.KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AnimalFavoritesTabel.ANIMAL_ID + " TEXT,"
                + AnimalFavoritesTabel.ANIMAL_FAVORITED + " INTEGER);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ AnimalTabel.TABEL_NAAM);
        db.execSQL("DROP TABLE IF EXISTS "+ AnimalFavoritesTabel.TABEL_NAAM);
        onCreate(db);
    }

}
