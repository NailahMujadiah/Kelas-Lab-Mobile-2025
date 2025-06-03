package com.example.praktikum08;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormActivity extends AppCompatActivity {

    private EditText etTitle, etContent;
    private Button btnSave, btnDelete;
    private ImageButton btnBack;
    private NoteHelper noteHelper;

    private boolean isEdit = false;
    private int noteId = -1; // default ID jika belum ada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        btnBack = findViewById(R.id.btnBack);
        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        noteHelper = new NoteHelper(this);

        // Ambil data dari intent jika edit
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("id")) {
            isEdit = true;
            noteId = intent.getIntExtra("id", -1);
            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");

            etTitle.setText(title);
            etContent.setText(content);
            btnSave.setText("Update");
            btnDelete.setVisibility(View.VISIBLE);
        }



        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String content = etContent.getText().toString().trim();

            if (title.isEmpty()) {
                etTitle.setError("Title is required");
                return;
            }

            if (isEdit) {
                noteHelper.updateNote(noteId, title, content);
            } else {
                noteHelper.insertNote(title, content);
            }


            finish(); // kembali ke MainActivity
        });
        btnDelete.setOnClickListener(v -> {
            if (isEdit && noteId != -1) {
                new AlertDialog.Builder(FormActivity.this)
                        .setTitle("Konfirmasi Hapus")
                        .setMessage("Apakah kamu yakin ingin menghapus catatan ini?")
                        .setPositiveButton("Hapus", (dialog, which) -> {
                            noteHelper.deleteNote(noteId);
                            finish(); // Kembali ke MainActivity
                        })
                        .setNegativeButton("Batal", null)
                        .show();
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // kembali ke activity sebelumnya
            }
        });

    }
}
