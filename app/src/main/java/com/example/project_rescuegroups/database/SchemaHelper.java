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
    private final static String DATABASE_NAAM = "Animalss";
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
                + AnimalFavoritesTabel.ANIMAL_NAME + " TEXT);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ AnimalFavoritesTabel.TABEL_NAAM);
        onCreate(db);
    }

    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }


}
