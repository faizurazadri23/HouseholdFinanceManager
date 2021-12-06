package com.faizurazadri.householdfinancemanager.utils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class ShowSnackbar {

    public void showSnackbarMessage(View view, String message){
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }
}
