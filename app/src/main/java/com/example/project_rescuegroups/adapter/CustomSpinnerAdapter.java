package com.example.project_rescuegroups.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project_rescuegroups.R;

import java.util.List;

public class CustomSpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringList;
    LayoutInflater inflater;

    public CustomSpinnerAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
        inflater =(LayoutInflater.from(context));
    }


    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {

        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.custom_spinner_items,null);
        TextView choices = (TextView) view.findViewById(R.id.tvSpinnerItem);
        choices.setText(stringList.get(position));

        return view;
    }
}
