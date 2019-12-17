package com.example.project_rescuegroups.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.project_rescuegroups.R;
import com.example.project_rescuegroups.database.AnimalFavoritesTabel;

public class AnimalsFavoriteCursorAdapter extends CursorAdapter {
    private final LayoutInflater mLayoutInflater;

    public AnimalsFavoriteCursorAdapter(Context context, Cursor c) {
        super(context, c,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return mLayoutInflater.inflate(R.layout.animalsfavorite_view_record,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final TextView tv_id = (TextView) view.findViewById(R.id.txt_id);
        tv_id.setText(cursor.getString(cursor.getColumnIndex(AnimalFavoritesTabel.ID)));

        final TextView tv_animalId = (TextView) view.findViewById(R.id.txt_animalid);
        tv_animalId.setText(cursor.getString(cursor.getColumnIndex(AnimalFavoritesTabel.ANIMAL_ID)));

        final TextView tv_animalName = (TextView) view.findViewById(R.id.txt_naam);
        tv_animalName.setText(cursor.getString(cursor.getColumnIndex(AnimalFavoritesTabel.ANIMAL_NAME)));
    }
}
