package com.example.project_rescuegroups.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.project_rescuegroups.R;
import com.example.project_rescuegroups.database.AnimalFavoritesDB;
import com.example.project_rescuegroups.model.Animal;
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
        TextView textView = convertView.findViewById(R.id.tvAnimalName);
        final ImageButton star = convertView.findViewById(R.id.btn_star);

        Picasso.get().load(animal.getAnimalImageUrl()).into(imageView);
        textView.setText(animal.getAnimalName());
        star.setImageDrawable(convertView.getResources().getDrawable(android.R.drawable.btn_star));
        star.setFocusable(false);

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEnable) {
                    star.setImageDrawable(ContextCompat.getDrawable(getContext(), android.R.drawable.btn_star_big_on));
                    addDbFavorite(animal);
                }
                else{
                    star.setImageDrawable(ContextCompat.getDrawable(getContext(), android.R.drawable.btn_star_big_off));
                }
                isEnable = !isEnable;
            }
        });
        return convertView;
    }

    private void addDbFavorite(Animal animal_favorite){
        AnimalFavoritesDB sh = new AnimalFavoritesDB(context);
        sh.addFavorite(animal_favorite);
        Toast.makeText(context,animal_favorite.toString(),Toast.LENGTH_LONG).show();
    }

}
