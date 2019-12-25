package com.example.project_rescuegroups.util;

import android.widget.Spinner;

public class SearchParamData {
    private final Spinner spinner;
    private final String sSpinnerId;

    public SearchParamData(Spinner spinner, String sSpinnerId) {
        this.spinner = spinner;
        this.sSpinnerId = sSpinnerId;
    }

    public Spinner getSpinnerSpecies() {
        return spinner;
    }

    public String getsSpinnerId() {
        return sSpinnerId;
    }
}
