package com.example.project_rescuegroups.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jean-pierre on 2/03/2018.
 */

public class SchemaHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAAM = "Animals";
    private static final int DATABASE_VERSIE = 5;

    public SchemaHelper(Context context) {

        super(context, DATABASE_NAAM, null, DATABASE_VERSIE);
    }

    public static String getDatabaseNaam() {
        return DATABASE_NAAM;
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
                + AnimalTabel.ANIMAL_DESC + " TEXT,"
                + AnimalTabel.ANIMAL_ORGID + " TEXT);"
        );

        db.execSQL("CREATE TABLE "
                + AnimalFavoritesTabel.TABEL_NAAM
                + " (" + AnimalFavoritesTabel.KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AnimalFavoritesTabel.ANIMAL_ID + " TEXT,"
                + AnimalFavoritesTabel.ANIMAL_FAVORITED + " INTEGER);"
        );

        db.execSQL("CREATE TABLE "
                + OrganizationTabel.TABEL_NAAM
                + " (" + OrganizationTabel.KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + OrganizationTabel.ORGANIZATION_ID + " TEXT,"
                + OrganizationTabel.ORGANIZATION_NAME + " TEXT,"
                + OrganizationTabel.ORGANIZATION_ADDRESS + " TEXT,"
                + OrganizationTabel.ORGANIZATION_CITY + " TEXT,"
                + OrganizationTabel.ORGANIZATION_STATE + " TEXT,"
                + OrganizationTabel.ORGANIZATION_ZIP + " TEXT,"
                + OrganizationTabel.ORGANIZATION_COUNTRY + " TEXT,"
                + OrganizationTabel.ORGANIZATION_PHONE + " TEXT,"
                + OrganizationTabel.ORGANIZATION_EMAIL + " TEXT);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ AnimalTabel.TABEL_NAAM);
        db.execSQL("DROP TABLE IF EXISTS "+ AnimalFavoritesTabel.TABEL_NAAM);
        onCreate(db);
    }

}
