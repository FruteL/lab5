package com.example.lab67.rep;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import com.example.lab67.model.Note;

@Dao
public interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Query("DELETE FROM notes WHERE id=:id")
    void deleteNoteById(int id);

    @Query("SELECT * FROM notes WHERE id=:id")
    Note getNoteById(int id);

    @Query("DELETE FROM notes")
    void deleteAll();

    @Query("SELECT * FROM notes ORDER BY dateUpdate DESC")
    List<Note> getAll();
}

