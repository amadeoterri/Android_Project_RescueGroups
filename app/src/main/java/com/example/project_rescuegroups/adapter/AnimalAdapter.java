package com.example.project_rescuegroups.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.project_rescuegroups.AnimalDetail;
import com.example.project_rescuegroups.R;
import com.example.project_rescuegroups.database.AnimalFavoritesDB;
import com.example.project_rescuegroups.model.Animal;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnimalAdapter extends ArrayAdapter<Animal> {

    private Context context;
    private List<Animal> animalList;
    boolean isEnable = false;

    public AnimalAdapter(Context context,List<Animal> animalList) {
        super(context, R.layout.animals_list_item,animalList);
        this.context = context;
        this.animalList = animalList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //als convertView null is een nieuwe View maken, anders hergebruiken
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.animals_list_item,parent,false);
        }

        final Animal animal = animalList.get(position);
        ImageView imageView = convertView.findViewById(R.id.imgAnimal);
        TextView textViewName = convertView.findViewById(R.id.tvAnimalName);
        TextView textViewInfo = convertView.findViewById(R.id.tvAnimalInfo);

        final ImageButton favorite = convertView.findViewById(R.id.btn_heart);

        Picasso.get().load(animal.getAnimalImageUrl()).into(imageView);
        textViewName.setText(animal.getAnimalName());
        textViewInfo.setText("Gender: " + animal.getAnimalSex() + "\n"
                                + "Breed: " + animal.getAnimalBreed() + "\n"
                                + "Birthdate: " + animal.getAnimalBirthDate());

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEnable) {
                    favorite.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.favorite));
                    addDbFavorite(animal);
                }
                else{
                    favorite.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.unfavorite));
                }
                isEnable = !isEnable;
            }
        });

        MaterialButton btnDetail = convertView.findViewById(R.id.btnShowDetail);
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AnimalDetail.class);
                intent.putExtra("animal",animal);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private void addDbFavorite(Animal animal_favorite){
        AnimalFavoritesDB sh = new AnimalFavoritesDB(context);
        sh.addFavorite(animal_favorite);
    }

}
