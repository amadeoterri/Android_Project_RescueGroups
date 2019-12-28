package com.example.project_rescuegroups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.project_rescuegroups.adapter.FavoritesGridAdapter;
import com.example.project_rescuegroups.database.AnimalFavoritesDB;
import com.example.project_rescuegroups.database.AnimalFavoritesTabel;
import com.example.project_rescuegroups.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private GridView gridView;
    private List<Animal> animalFavoritesList;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        setContentView(R.layout.activity_favorites);

        AnimalFavoritesDB sh = new AnimalFavoritesDB(this);

        gridView = (GridView) findViewById(R.id.gridFavorites);

        Cursor cursor = sh.getFavorites();
        animalFavoritesList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                String id = cursor.getString(1);
                String naam = cursor.getString(2);
                String species = cursor.getString(3);
                String breed = cursor.getString(4);
                String sex = cursor.getString(5);
                String birthdate = cursor.getString(6);
                String imageurl = cursor.getString(7);
                String desc = cursor.getString(8);
                Animal animal = new Animal(id,naam,species,breed,sex,birthdate,imageurl,desc);
                animalFavoritesList.add(animal);
                cursor.moveToNext();

            }
        }

        FavoritesGridAdapter adapter  = new FavoritesGridAdapter(this,animalFavoritesList);
        gridView.setAdapter(adapter);

    }
}
