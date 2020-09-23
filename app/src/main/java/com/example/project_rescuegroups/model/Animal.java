package com.example.project_rescuegroups.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.project_rescuegroups.R;

public class Animal implements Parcelable {
    private String animalId;
    private String animalName;
    private String animalSpecies;
    private String animalBreed;
    private String animalSex;
    private String animalBirthDate;
    private String animalImageUrl;
    private String animalDescription;
    private int imgFavorite;
    private int[] imgIds = {R.drawable.unfavorite, R.drawable.favorite};

    private String animalOrgID;

    public Animal(String animalId, String animalName, String animalSpecies, String animalBreed, String animalSex, String animalBirthDate, String animalImageUrl, String animalDescription, String animalOrgID) {
        this.animalId = animalId;
        this.animalName = animalName;
        this.animalSpecies = animalSpecies;
        this.animalBreed = animalBreed;
        this.animalSex = animalSex;
        this.animalBirthDate = animalBirthDate;
        this.animalImageUrl = animalImageUrl;
        this.animalDescription = animalDescription;
        this.animalOrgID = animalOrgID;
        this.imgFavorite = imgIds[0];
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
        animalOrgID = in.readString();
        imgFavorite = imgIds[0];
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

    public String getAnimalName() {
        return animalName;
    }

    public String getAnimalSpecies() {
        return animalSpecies;
    }

    public String getAnimalBreed() {
        return animalBreed;
    }

    public String getAnimalSex() {
        return animalSex;
    }

    public String getAnimalBirthDate() {
        return animalBirthDate;
    }

    public String getAnimalImageUrl() {
        return animalImageUrl;
    }

    public String getAnimalDescription() {
        return animalDescription;
    }

    public int getImgFavorite() {
        return imgFavorite;
    }

    public void setImgFavorite(int imgFavorite) {
        this.imgFavorite = imgFavorite;
    }

    public int[] getImgIds() {
        return imgIds;
    }

    public String getAnimalOrgID() {
        return animalOrgID;
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
        dest.writeInt(imgFavorite);
    }
}
