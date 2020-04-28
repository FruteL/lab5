package com.example.lab5_3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CameraAlert extends DialogFragment {
    //Класс обработчки ШС для Камеры
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Camera");
        builder.setMessage("Camera is ON");
        builder.setPositiveButton("Thx", null);
        return builder.create();
    }
}
