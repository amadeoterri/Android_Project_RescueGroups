package com.example.project_rescuegroups.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_rescuegroups.R;
import com.example.project_rescuegroups.model.Animal;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder>  {

    // attributes
    private List<Animal> animalList;
    private OnItemClickListener mListener;
    Context context;

    public interface OnItemClickListener{
        //void onItemClick(int position);
        void onFavoriteClick(int position);
        void onDetailClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewName;
        public TextView textViewInfo;
        public MaterialButton btnDetail;
        public ImageView favorite;

        public ViewHolder(View itemView,final OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgAnimal);
            textViewName = itemView.findViewById(R.id.tvAnimalName);
            textViewInfo = itemView.findViewById(R.id.tvAnimalInfo);
            favorite = itemView.findViewById(R.id.btn_heart);
            btnDetail = itemView.findViewById(R.id.btnShowDetail);

/*            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }


                }
            });*/

            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            //favorite.setImageDrawable(ContextCompat.getDrawable(v.getContext(),R.drawable.favorite));
                            listener.onFavoriteClick(position);
                        }
                    }
                }
            });

            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDetailClick(position);
                        }
                    }
                }
            });
        }
    }

    public AnimalAdapter(List<Animal> animalList,Context context) {
        this.animalList = animalList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.animals_list_item,parent,false);
        ViewHolder evh = new ViewHolder(convertView, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        final Animal animal = animalList.get(position);

        Picasso.get().load(animal.getAnimalImageUrl()).into(viewHolder.imageView);
        viewHolder.textViewName.setText(animal.getAnimalName());
        viewHolder.textViewInfo.setText("Gender: " + animal.getAnimalSex() + "\n"
                + "Breed: " + animal.getAnimalBreed() + "\n"
                + "Birthdate: " + animal.getAnimalBirthDate());
        int isFavorited = animal.getImgFavorite();
        if(isFavorited == animal.getImgIds()[1]) {
            viewHolder.favorite.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.favorite));
        }
        else{
            viewHolder.favorite.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.unfavorite));
        }

    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }
}
