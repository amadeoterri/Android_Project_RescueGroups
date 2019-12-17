package com.example.project_rescuegroups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_rescuegroups.model.Animal;
import com.squareup.picasso.Picasso;

public class AnimalDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_detail);

        final TextView tvName = (TextView) findViewById(R.id.tvNameDetail);
        final ImageView imgDetail = (ImageView) findViewById(R.id.imgDetail);
        final TextView tvBreed = (TextView) findViewById(R.id.tvBreedDetail);
        final TextView tvSex = (TextView) findViewById(R.id.tvSexDetail);
        final TextView tvSpecies = (TextView) findViewById(R.id.tvSpeciesDetail);
        final TextView tvBirthDate = (TextView) findViewById(R.id.tvBirthDateDetail);
        final TextView tvDescription = (TextView) findViewById(R.id.tvDescriptionDetail);

        Intent i = getIntent();
        Animal a = (Animal) i.getParcelableExtra("animal");

        tvName.setText(a.getAnimalName());
        Picasso.get().load(a.getAnimalImageUrl()).into(imgDetail);
        tvBreed.setText(a.getAnimalBreed());
        tvSex.setText(a.getAnimalSex());
        tvSpecies.setText(a.getAnimalSpecies());
        tvBirthDate.setText(a.getAnimalBirthDate());
        tvDescription.setText(a.getAnimalDescription());
    }
}
