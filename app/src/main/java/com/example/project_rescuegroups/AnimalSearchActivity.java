package com.example.project_rescuegroups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project_rescuegroups.asynctask.SpinnerAsyncTask;
import com.example.project_rescuegroups.util.SearchParamData;
import com.google.android.material.button.MaterialButton;


public class AnimalSearchActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_search);

        final Spinner spinnerSpecies = (Spinner) findViewById(R.id.spinnerSpecies);
        final Spinner spinnerBreed = (Spinner) findViewById(R.id.spinnerBreed);


        SearchParamData asyncParams = new SearchParamData(spinnerSpecies,"species");
        //spinners vullen
        new SpinnerAsyncTask(this).execute(asyncParams);
        /*spinnerSpecies.setOnItemSelectedListener(this);*/
        /*SearchParamData asyncParams2 = new SearchParamData(spinnerBreed,"breed");
        new SpinnerAsyncTask(this).execute(asyncParams2);*/

        /*String text = (String) spinnerSpecies.getSelectedItem();
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();*/

        if(spinnerSpecies != null) {
            spinnerSpecies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("deodebug", "hier nie wrs");
                    String text = (String) spinnerSpecies.getSelectedItem();
                    /*choice[0] = parent.getItemAtPosition(position).toString();*/
                    Toast.makeText(AnimalSearchActivity.this, text, Toast.LENGTH_LONG).show();
                    /*Toast.makeText(context, choice[0], Toast.LENGTH_LONG).show();*/
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

/*
        MaterialButton btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimalSearchActivity.this,AnimalActivity.class);
                intent.putExtra("species", choice[0]);
                startActivity(intent);
            }
        });*/

    }
}
