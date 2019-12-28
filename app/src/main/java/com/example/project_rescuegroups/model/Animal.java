package com.example.project_rescuegroups.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Animal implements Parcelable {
    private String animalId;
    private String animalName;
    private String animalSpecies;
    private String animalBreed;
    private String animalSex;
    private String animalBirthDate;
    private String animalImageUrl;
    private String animalDescription;

    public Animal(String animalId, String animalName, String animalSpecies, String animalBreed, String animalSex, String animalBirthDate, String animalImageUrl, String animalDescription) {
        this.animalId = animalId;
        this.animalName = animalName;
        this.animalSpecies = animalSpecies;
        this.animalBreed = animalBreed;
        this.animalSex = animalSex;
        this.animalBirthDate = animalBirthDate;
        this.animalImageUrl = animalImageUrl;
        this.animalDescription = animalDescription;

    }

    protected Animal(Parcel in) {
        animalId = in.readString();
        animalName = in.readString();
        animalSpecies = in.readString();
        animalBreed = in.readString();
        animalSex = in.readString();
        animalBirthDate = in.readString();
        animalImageUrl = in.readString();
        animalDescription = in.readString();
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimalSpecies() {
        return animalSpecies;
    }

    public void setAnimalSpecies(String animalSpecies) {
        this.animalSpecies = animalSpecies;
    }

    public String getAnimalBreed() {
        return animalBreed;
    }

    public void setAnimalBreed(String animalBreed) {
        this.animalBreed = animalBreed;
    }

    public String getAnimalSex() {
        return animalSex;
    }

    public void setAnimalSex(String animalSex) {
        this.animalSex = animalSex;
    }

    public String getAnimalBirthDate() {
        return animalBirthDate;
    }

    public void setAnimalBirthDate(String animalBirthDate) {
        this.animalBirthDate = animalBirthDate;
    }

    public String getAnimalImageUrl() {
        return animalImageUrl;
    }

    public void setAnimalImageUrl(String animalImageUrl) {
        this.animalImageUrl = animalImageUrl;
    }

    public String getAnimalDescription() {
        return animalDescription;
    }

    public void setAnimalDescription(String animalDescription) {
        this.animalDescription = animalDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(animalId);
        dest.writeString(animalName);
        dest.writeString(animalSpecies);
        dest.writeString(animalBreed);
        dest.writeString(animalSex);
        dest.writeString(animalBirthDate);
        dest.writeString(animalImageUrl);
        dest.writeString(animalDescription);
    }
}
