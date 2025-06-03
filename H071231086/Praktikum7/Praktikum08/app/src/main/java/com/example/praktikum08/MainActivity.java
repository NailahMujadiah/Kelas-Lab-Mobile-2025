package com.example.praktikum08;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter adapter;

    private NoteHelper noteHelper;
    private Button btnAdd;
    private TextView tvNoData;
    private SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        tvNoData = findViewById(R.id.tvNoData);
        searchView = findViewById(R.id.searchView);
        btnAdd = findViewById(R.id.btnAdd);


        adapter = new NoteAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        noteHelper = new NoteHelper(this);

        loadNotes(""); // tampilkan semua saat awal

        // Search listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadNotes(query); // cari saat submit
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                loadNotes(newText); // cari saat teks berubah
                return true;
            }
        });


        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes(searchView.getQuery().toString()); // refresh saat kembali dari FormActivity
    }

    private void loadNotes(String query) {
        Cursor cursor;
        if (query == null || query.isEmpty()) {
            cursor = noteHelper.getAllNotes();
        } else {
            cursor = noteHelper.searchNotesByTitle(query);
        }

        ArrayList<Note> notes = MappingHelper.mapCursorToArrayList(cursor);
        adapter.setData(notes);

        if (notes.isEmpty()) {
            tvNoData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            btnAdd.setVisibility(View.VISIBLE);
        } else {
            tvNoData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.VISIBLE);
        }
        btnAdd.setVisibility(View.VISIBLE);
    }
}
