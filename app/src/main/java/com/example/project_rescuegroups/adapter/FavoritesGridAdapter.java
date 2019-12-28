package com.example.project_rescuegroups.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_rescuegroups.R;
import com.example.project_rescuegroups.model.Animal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoritesGridAdapter extends BaseAdapter
{
    private Context context;
    private final List<Animal> animals;

    public FavoritesGridAdapter(Context context, List<Animal> animals) {
        this.context = context;
        this.animals = animals;
    }

    @Override
    public int getCount() {
        return animals.size();
    }

    @Override
    public Object getItem(int position) {
        return animals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridItemView;
        if(convertView != null){
            gridItemView = convertView;
        }
        else{
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridItemView = inflater.inflate(R.layout.animalsfavorite_view_record,null);
        }

        TextView textView = (TextView) gridItemView.findViewById(R.id.grid_name);
        ImageView imageView = (ImageView) gridItemView.findViewById(R.id.grid_image);
        Animal animal = animals.get(position);
        textView.setText(animal.getAnimalName());
        Picasso.get().load(animal.getAnimalImageUrl()).into(imageView);
        return gridItemView;
    }
}
