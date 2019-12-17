package com.example.project_rescuegroups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnAnimalList = findViewById(R.id.btnShowAnimals);
        final Button btnOrganizationList = findViewById(R.id.btnShowOrganizations);
        final Button btnFavoritesList = findViewById(R.id.btnShowFavorites);

        btnAnimalList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AnimalSearchActivity.class);
                startActivity(intent);
            }
        });

        btnOrganizationList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,OrganizationActivity.class);
                startActivity(intent);
            }
        });

        btnFavoritesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FavoritesActivity.class);
                startActivity(intent);
            }
        });


    }
}
