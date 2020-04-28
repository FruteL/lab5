package com.example.lab5_3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class BetteryAlert extends DialogFragment {
    //Класс обрабочик ШС для Батареи
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setTitle("Battery");
        builder.setMessage("Low Bettery! Please charge your device");
        builder.setPositiveButton("Thx", null);
        return builder.create();
    }
}
