package com.example.project_rescuegroups.util;

import android.widget.Spinner;

public class SearchParamData {
    private Spinner spinner;
    private String sSpinnerId;
    private String sRadioSelection;


    public SearchParamData(Spinner spinner, String sSpinnerId, String sRadioSelection) {
        this.spinner = spinner;
        this.sSpinnerId = sSpinnerId;
        this.sRadioSelection = sRadioSelection;
    }

    public Spinner getSpinner() {
        return spinner;
    }

    public String getsSpinnerId() {
        return sSpinnerId;
    }

    public String getsRadioSelection() {
        return sRadioSelection;
    }

    public void setSpinner(Spinner spinner) {
        this.spinner = spinner;
    }

    public void setsSpinnerId(String sSpinnerId) {
        this.sSpinnerId = sSpinnerId;
    }

    public void setsRadioSelection(String sRadioSelection) {
        this.sRadioSelection = sRadioSelection;
    }
}
