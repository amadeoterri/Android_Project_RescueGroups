package com.example.project_rescuegroups;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.project_rescuegroups.adapter.AnimalsFavoriteCursorAdapter;
import com.example.project_rescuegroups.database.AnimalFavoritesDB;
import com.example.project_rescuegroups.database.AnimalFavoritesTabel;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        AnimalFavoritesDB sh = new AnimalFavoritesDB(this);

        final ListView listView = findViewById(R.id.listViewFavorites);
        Cursor cursor = sh.getFavorites();

        String[] columns = new String[]{
                AnimalFavoritesTabel.ID,
                AnimalFavoritesTabel.ANIMAL_ID,
                AnimalFavoritesTabel.ANIMAL_NAME

        };

        int[]to = new int[]{
                R.id.txt_id,
                R.id.txt_animalid,
                R.id.txt_naam,
        };

        SimpleCursorAdapter favoriteCursorAdapter = new SimpleCursorAdapter(this,R.layout.animalsfavorite_view_record,cursor,columns,to,0);
        listView.setAdapter(favoriteCursorAdapter);

    }
}
