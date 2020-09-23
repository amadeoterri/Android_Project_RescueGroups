package com.example.project_rescuegroups;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_rescuegroups.util.ChoiceParamData;
import com.example.project_rescuegroups.util.SearchParamData;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AnimalSearchActivity extends AppCompatActivity {

    String species = "Dog";
    Spinner spinnerBreed;
    Spinner spinnerSex;
    Spinner spinnerStates;
    CheckBox checkboxUSA;
    CheckBox checkBoxCanada;

    SearchParamData asyncParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_search);

        spinnerBreed = findViewById(R.id.spinnerBreed);
        spinnerSex = findViewById(R.id.spinnerSex);
        spinnerStates = findViewById(R.id.spinnerState);
        checkboxUSA = findViewById(R.id.checkBoxUS);
        checkBoxCanada = findViewById(R.id.checkboxCanada);

        //update other spinners
        UpdateSpinners("Dog");

        //checkbox

        checkboxUSA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!checkboxUSA.isChecked() && !checkBoxCanada.isChecked()) {
                    checkBoxCanada.setChecked(true);
                }
                UpdateSpinners(species);

            }
        });
        checkBoxCanada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!checkBoxCanada.isChecked() && !checkBoxCanada.isChecked()) {
                    checkboxUSA.setChecked(true);
                }
                UpdateSpinners(species);

            }
        });

        //search
        MaterialButton btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimalSearchActivity.this, AnimalActivity.class);
                String breed = (String) spinnerBreed.getSelectedItem();
                String sex = (String) spinnerSex.getSelectedItem();
                String state = (String) spinnerStates.getSelectedItem();
                ChoiceParamData searchChoice = new ChoiceParamData(species, breed, sex, state);
                intent.putExtra("choice", searchChoice);
                startActivity(intent);
            }
        });

    }

    private void UpdateSpinners(String radioSelection) {
        //sex
        ArrayAdapter sexadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Arrays.asList(getResources().getStringArray(R.array.sex_array)));
        sexadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(sexadapter);

        //states
        List<String> statesUSA = Arrays.asList(getResources().getStringArray(R.array.us_states));
        List<String> statesCanada = Arrays.asList(getResources().getStringArray(R.array.canada_states));
        List<String> statesspinnerlist = new ArrayList<>();
        if (checkboxUSA.isChecked() && checkBoxCanada.isChecked()) {
            statesspinnerlist.add("All");
            statesspinnerlist.addAll(statesUSA);
            statesspinnerlist.addAll(statesCanada);
        } else if (checkboxUSA.isChecked() && !checkBoxCanada.isChecked()) {
            statesspinnerlist.addAll(statesUSA);
        } else if (!checkboxUSA.isChecked() && checkBoxCanada.isChecked()) {
            statesspinnerlist.addAll(statesCanada);
        }

        ArrayAdapter statesadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, statesspinnerlist);
        statesadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStates.setAdapter(statesadapter);

        //breed
        List<String> breed = new ArrayList<>();
        if (radioSelection.equals("Dog")) {
            breed = Arrays.asList(getResources().getStringArray(R.array.breed_array_dog));
        } else if (radioSelection.equals("Cat")) {
            breed = Arrays.asList(getResources().getStringArray(R.array.breed_array_cat));
        }

        ArrayAdapter a = new ArrayAdapter(this, android.R.layout.simple_spinner_item, breed);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBreed.setAdapter(a);

    }

    public void radioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.rbtCats:
                if(checked){
                    UpdateSpinners("Cat");
                    //UpdateSpinnersWithDatabase("Cat");
                    species = "Cat";
                }
                break;
            case R.id.rbtDogs:
                if(checked){
                    UpdateSpinners("Dog");
                    //UpdateSpinnersWithDatabase("Dog");
                    species = "Dog";
                }
                break;
        }
    }

}
