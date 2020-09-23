package com.example.project_rescuegroups;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_rescuegroups.adapter.AnimalAdapter;
import com.example.project_rescuegroups.asynctask.FavoriteAsyncTask;
import com.example.project_rescuegroups.database.AnimalDB;
import com.example.project_rescuegroups.database.AnimalFavoritesDB;
import com.example.project_rescuegroups.model.Animal;
import com.example.project_rescuegroups.util.ChoiceParamData;

import java.util.ArrayList;
import java.util.List;

public class AnimalActivity extends AppCompatActivity {

    private List<Animal> animalList;
    Context context;
    private AnimalAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        context = this;

        makeRecyclerView();
        getAnimalListFromDB();

        //set favorited icon in listview
        for (Animal animal : animalList) {
            new FavoriteAsyncTask(context).execute(animal);
        }

        mAdapter = new AnimalAdapter(animalList, context);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AnimalAdapter.OnItemClickListener() {

            @Override
            public void onFavoriteClick(int position) {
                Animal currentanimal = animalList.get(position);

                new FavoriteAsyncTask(context).execute(currentanimal);
                int isFavorited = currentanimal.getImgFavorite();
                if(isFavorited == currentanimal.getImgIds()[0]) {
                    currentanimal.setImgFavorite(currentanimal.getImgIds()[1]);
                    addDbFavorite(currentanimal);
                } else{
                    currentanimal.setImgFavorite(currentanimal.getImgIds()[0]);
                    removeDBFavorite(currentanimal);
                }
                mAdapter.notifyItemChanged(position);

            }

            private void addDbFavorite(Animal animal_favorite){
                AnimalFavoritesDB sh = new AnimalFavoritesDB(context);
                sh.addFavorite(animal_favorite);
            }

            private void removeDBFavorite(Animal animal_favorite){
                AnimalFavoritesDB sh = new AnimalFavoritesDB(context);
                sh.removeFavorite(animal_favorite);
            }

            @Override
            public void onDetailClick(int position) {
                Animal currentanimal = animalList.get(position);
                Intent intent = new Intent(context, AnimalDetail.class);
                intent.putExtra("animal", currentanimal);
                context.startActivity(intent);

            }
        });
    }

    private void getAnimalListFromDB() {
        Intent intent = getIntent();
        ChoiceParamData choice = intent.getParcelableExtra("choice");
        AnimalDB sh = new AnimalDB(this);
        Cursor cursor = null;
        if (choice != null) {
            cursor = sh.getAnimalsWithParams(choice);
        }

        animalList = new ArrayList<>();
        if (cursor != null) {
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
                    animalList.add(animal);
                    cursor.moveToNext();

                }
            }
        }

    }

    private void makeRecyclerView() {
        recyclerView = findViewById(R.id.listViewAnimals);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

}
