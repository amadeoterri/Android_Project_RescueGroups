package com.example.project_rescuegroups;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_rescuegroups.adapter.FavoritesRecyclerAdapter;
import com.example.project_rescuegroups.database.AnimalFavoritesDB;
import com.example.project_rescuegroups.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FavoritesRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Animal> animalFavoritesList;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        setContentView(R.layout.activity_favorites);

        makeFavoritesRecyclerView();
        fillListFromDB();

        mAdapter = new FavoritesRecyclerAdapter(this, animalFavoritesList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new FavoritesRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Animal currentanimal = animalFavoritesList.get(position);
                Intent intent = new Intent(context, AnimalDetail.class);
                intent.putExtra("animal", currentanimal);
                context.startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {
                Animal deleteanimal = animalFavoritesList.remove(position);
                mAdapter.notifyItemRemoved(position);
                AnimalFavoritesDB sh = new AnimalFavoritesDB(context);
                sh.removeFavorite(deleteanimal);
            }
        });

    }

    private void fillListFromDB() {

        AnimalFavoritesDB sh = new AnimalFavoritesDB(this);
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
                String animalOrgID = cursor.getString(9);
                Animal animal = new Animal(id, naam, species, breed, sex, birthdate, imageurl, desc, animalOrgID);
                animalFavoritesList.add(animal);
                cursor.moveToNext();

            }
        }
    }

    private void makeFavoritesRecyclerView() {
        mRecyclerView = findViewById(R.id.favoriteRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
    }
}
