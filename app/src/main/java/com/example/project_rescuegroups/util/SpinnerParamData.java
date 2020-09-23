package com.example.project_rescuegroups.util;

import android.widget.Spinner;

import java.util.List;

public class SpinnerParamData {
    private Spinner spinner;
    private String sSpinnerId;
    private String sRadioSelection;
    private List<String> arrSpinnerList;

    public SpinnerParamData(Spinner spinner, String sSpinnerId, String sRadioSelection, List<String> arrSpinnerList) {
        this.spinner = spinner;
        this.sSpinnerId = sSpinnerId;
        this.sRadioSelection = sRadioSelection;
        this.arrSpinnerList = arrSpinnerList;
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

    public List<String> getArrSpinnerList() {
        return arrSpinnerList;
    }


    public void setArrSpinnerList(List<String> arrSpinnerList) {
        this.arrSpinnerList = arrSpinnerList;
    }
}
