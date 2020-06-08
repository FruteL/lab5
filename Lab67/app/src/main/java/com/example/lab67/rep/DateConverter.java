package com.example.lab67.rep;

import java.util.Date;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date toDateCreation(Long creation_timestamp) {
        return creation_timestamp == null ? null : new Date(creation_timestamp);
    }

    @TypeConverter
    public static Long toCreation_timestamp(Date dateCreation) {
        return dateCreation == null ? null : dateCreation.getTime();
    }
}
