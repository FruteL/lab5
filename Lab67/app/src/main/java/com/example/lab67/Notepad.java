package com.example.lab67;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import com.example.lab67.model.Note;


public class Notepad extends Application {

    public static final String NOTE_ID_ARG = "note_id";

    private static Notepad instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Notepad getInstance() {
        return Notepad.instance;
    }
}
