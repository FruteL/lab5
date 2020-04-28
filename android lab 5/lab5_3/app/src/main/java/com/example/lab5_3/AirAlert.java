package com.example.lab5_3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class AirAlert extends DialogFragment {
    //класс обработчика ШС для Авиорежима

    boolean isSwitched;

    public AirAlert(boolean isSwitched) {
        this.isSwitched = isSwitched;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setTitle("Airplane mode");

        if (isSwitched) {
            builder.setMessage("AirPlane Mod id switched ON");
        }
        else {
            builder.setMessage("AirPlane Mod id switched OFF");
        }
        builder.setPositiveButton("Thx", null);
        return builder.create();
    }
}

