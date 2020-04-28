package com.example.lab5;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDialoge extends DialogFragment {


    private static final String ARG_PARAM1 = "param1";
    private String mParam1;


    public static MyDialoge newInstance(String param1) {
        MyDialoge fragment = new MyDialoge();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }


    // Получаем данные из Bundle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mParam1 = getArguments() == null ? "null" : getArguments().getString(ARG_PARAM1);
    }


    //Окно оповещения
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("You chose number: "+ mParam1);
        builder.setTitle("Number");
        builder.setPositiveButton("OK", null);

        return builder.create();
    }
}
