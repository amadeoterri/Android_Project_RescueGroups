package com.example.project_rescuegroups;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project_rescuegroups.adapter.AnimalAdapter;
import com.example.project_rescuegroups.asynctask.AnimalsAsyncTask;
import com.example.project_rescuegroups.asynctask.FavoriteAsyncTask;
import com.example.project_rescuegroups.database.AnimalDB;
import com.example.project_rescuegroups.database.AnimalFavoritesDB;
import com.example.project_rescuegroups.database.AnimalTabel;
import com.example.project_rescuegroups.model.Animal;
import com.example.project_rescuegroups.util.ChoiceParamData;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class AnimalActivity extends AppCompatActivity {

    private List<Animal> animalList;
    Context context;
    private RecyclerView recyclerView;
    private AnimalAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        context = this;

        recyclerView = findViewById(R.id.listViewAnimals);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        ChoiceParamData choice = intent.getParcelableExtra("choice");
        AnimalDB sh = new AnimalDB(this);
        final Cursor cursor = sh.getAnimalsWithParams(choice);
        animalList = new ArrayList<>();

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
                animalList.add(animal);
                cursor.moveToNext();

            }
        }

        mAdapter = new AnimalAdapter(animalList,context);
        recyclerView.setAdapter(mAdapter);

        /*AnimalFavoritesDB sh2 = new AnimalFavoritesDB(this);
        final Cursor cursor2 = sh2.getFavorites();*/

        mAdapter.setOnItemClickListener(new AnimalAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int position) {
                Toast.makeText(context,"hallo",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFavoriteClick(int position) {
                Toast.makeText(context,"favorited",Toast.LENGTH_LONG).show();
                Animal currentanimal = animalList.get(position);

                /*new FavoriteAsyncTask(context).execute(currentanimal);*/
                int isFavorited = currentanimal.getImgFavorite();
                if(isFavorited == currentanimal.getImgIds()[0]) {
                    currentanimal.setImgFavorite(currentanimal.getImgIds()[1]);
                    addDbFavorite(currentanimal);
                }
                else{
                    currentanimal.setImgFavorite(currentanimal.getImgIds()[0]);
                    removeDBFavorite(currentanimal);
                }
                mAdapter.notifyItemChanged(position);

/*                boolean isFavorited = false;
                if (cursor2.moveToFirst()) {
                    while (!cursor2.isAfterLast()) {
                        String id = cursor2.getString(1);
                        int favorited = cursor2.getInt(11);
                        String currentAnimalId = currentanimal.getAnimalId();
                        if(id.equals(currentAnimalId) && favorited == 1){
                            isFavorited = true;
                            currentanimal.setImgFavorite(R.drawable.favorite);
                            break;
                        }
                        cursor.moveToNext();
                    }
                }
                final boolean finalIsFavorited = isFavorited;
                if(!finalIsFavorited) {
                    currentanimal.setImgFavorite(R.drawable.favorite);
                    addDbFavorite(currentanimal);
                }
                else{
                    currentanimal.setImgFavorite(R.drawable.unfavorite);
                    removeDBFavorite(currentanimal);
                }*/
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
                Intent intent = new Intent(context,AnimalDetail.class);
                intent.putExtra("animal",currentanimal);
                context.startActivity(intent);
            }
        });

    }
}
