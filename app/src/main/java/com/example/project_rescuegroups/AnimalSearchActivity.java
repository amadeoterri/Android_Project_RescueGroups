package com.example.project_rescuegroups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project_rescuegroups.asynctask.SpinnerAsyncTask;
import com.example.project_rescuegroups.util.ChoiceParamData;
import com.example.project_rescuegroups.util.SearchParamData;
import com.google.android.material.button.MaterialButton;


public class AnimalSearchActivity extends AppCompatActivity{

    String species = "Dog";

    Spinner spinnerBreed;
    Spinner spinnerSex;
    SearchParamData asyncParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_search);

        spinnerBreed = (Spinner) findViewById(R.id.spinnerBreed);
        spinnerSex = (Spinner) findViewById(R.id.spinnerSex);

        //make sexSpinner (all,male,female)
        ArrayAdapter<CharSequence> sexAdapter = ArrayAdapter.createFromResource(this,R.array.sex_array,android.R.layout.simple_spinner_item);
        sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(sexAdapter);

        //update other spinners
        UpdateSpinners("Dog");


        /*spinnerSpecies.setOnItemSelectedListener(this);*/
        /*SearchParamData asyncParams2 = new SearchParamData(spinnerBreed,"breed");
        new SpinnerAsyncTask(this).execute(asyncParams2);*/

        /*String text = (String) spinnerSpecies.getSelectedItem();
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();*/

        //search
        MaterialButton btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimalSearchActivity.this,AnimalActivity.class);
                String breed = (String) spinnerBreed.getSelectedItem();
                String sex = (String) spinnerSex.getSelectedItem();
                ChoiceParamData searchChoice = new ChoiceParamData(species,breed,sex);
                intent.putExtra("choice",searchChoice);
                startActivity(intent);
            }
        });

    }

    private void UpdateSpinners(String radioSelection)
    {
        asyncParams = new SearchParamData(spinnerBreed,"breed",radioSelection);
        new SpinnerAsyncTask(this).execute(asyncParams);
    }

    public void radioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.rbtCats:
                if(checked){
                    UpdateSpinners("Cat");
                    species = "Cat";
                }
                break;
            case R.id.rbtDogs:
                if(checked){
                    UpdateSpinners("Dog");
                    species = "Dog";
                }
                break;
        }
    }
}
