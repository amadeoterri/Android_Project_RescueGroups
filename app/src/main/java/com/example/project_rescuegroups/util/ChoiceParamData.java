package com.example.project_rescuegroups.util;

import android.os.Parcel;
import android.os.Parcelable;

public class ChoiceParamData implements Parcelable {

    private String sChoiceSpecies;
    private String sChoiceBreed;
    private String sChoiceSex;
    private String sChoiceState;

    public ChoiceParamData(String sChoiceSpecies, String sChoiceBreed, String sChoiceSex, String sChoiceState) {
        this.sChoiceSpecies = sChoiceSpecies;
        this.sChoiceBreed = sChoiceBreed;
        this.sChoiceSex = sChoiceSex;
        this.sChoiceState = sChoiceState;
    }

    public String getsChoiceSpecies() {
        return sChoiceSpecies;
    }

    public String getsChoiceBreed() {
        return sChoiceBreed;
    }

    public String getsChoiceSex() {
        return sChoiceSex;
    }

    public String getsChoiceState() {
        return sChoiceState;
    }

    protected ChoiceParamData(Parcel in) {
        sChoiceSpecies = in.readString();
        sChoiceBreed = in.readString();
        sChoiceSex = in.readString();
        sChoiceState = in.readString();
    }

    public static final Creator<ChoiceParamData> CREATOR = new Creator<ChoiceParamData>() {
        @Override
        public ChoiceParamData createFromParcel(Parcel in) {
            return new ChoiceParamData(in);
        }

        @Override
        public ChoiceParamData[] newArray(int size) {
            return new ChoiceParamData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sChoiceSpecies);
        dest.writeString(sChoiceBreed);
        dest.writeString(sChoiceSex);
        dest.writeString(sChoiceState);
    }
}
