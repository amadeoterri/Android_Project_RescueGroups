package com.example.project_rescuegroups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.project_rescuegroups.adapter.AnimalAdapter;
import com.example.project_rescuegroups.asynctask.AnimalsAsyncTask;
import com.example.project_rescuegroups.database.AnimalDB;
import com.example.project_rescuegroups.database.AnimalFavoritesDB;
import com.example.project_rescuegroups.database.AnimalTabel;
import com.example.project_rescuegroups.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalActivity extends AppCompatActivity {

    private List<Animal> animalList;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        context = this;

/*        Intent intent = getIntent();
        String choice = intent.getStringExtra("species");


        final ListView listView = findViewById(R.id.listViewAnimals);

        //asynctask om elementen uit json file te halen
        //listview meegeven
        *//*new AnimalsAsyncTask(this).execute();*//*

        AnimalDB sh = new AnimalDB(this);
        Cursor cursor = sh.getAllAnimals();
        switch (choice){
            case "Cat": cursor = sh.getAnimalsWithParams("Cat");
            break;
            case "Dog": cursor = sh.getAnimalsWithParams("Dog");
            break;
            case "All": cursor = sh.getAllAnimals();
            break;
        }

        animalList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                int id = cursor.getInt(0);
                String naam = cursor.getString(1);
                String species = cursor.getString(2);
                String breed = cursor.getString(3);
                String sex = cursor.getString(4);
                String birthdate = cursor.getString(5);
                String imageurl = cursor.getString(6);
                String desc = cursor.getString(7);
                Animal animal = new Animal(id,naam,species,breed,sex,birthdate,imageurl,desc);
                animalList.add(animal);
                cursor.moveToNext();

            }
        }

        AnimalAdapter animalAdapter = new AnimalAdapter(listView.getContext(),animalList);
        listView.setAdapter(animalAdapter);

        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Animal animal = (Animal) listView.getItemAtPosition(position);
                Intent intent = new Intent();
                intent.setClass(context,AnimalDetail.class);
                intent.putExtra("animal",animal);
                startActivity(intent);
            }
        });*/
    }
}
