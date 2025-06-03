package com.example.praktikum08;

import android.database.Cursor;

import java.util.ArrayList;

public class MappingHelper {

    public static ArrayList<Note> mapCursorToArrayList(Cursor cursor) {
        ArrayList<Note> noteList = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
                note.setContent(cursor.getString(cursor.getColumnIndexOrThrow("content")));
                note.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow("created_at")));
                note.setUpdatedAt(cursor.getString(cursor.getColumnIndexOrThrow("updated_at")));

                noteList.add(note);
            } while (cursor.moveToNext());
        }

        return noteList;
    }
}
