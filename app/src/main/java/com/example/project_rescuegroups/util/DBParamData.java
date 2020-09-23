package com.example.project_rescuegroups.util;

import com.example.project_rescuegroups.database.AnimalDB;
import com.example.project_rescuegroups.model.Animal;

import java.util.List;

public class DBParamData {
    private AnimalDB sh;
    private List<Animal> AnimalList;

    public DBParamData(AnimalDB sh, List<Animal> animalList) {
        this.sh = sh;
        AnimalList = animalList;
    }

    public AnimalDB getSh() {
        return sh;
    }

    public List<Animal> getAnimalList() {
        return AnimalList;
    }
}
