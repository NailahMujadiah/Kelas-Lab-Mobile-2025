package com.example.newminigram;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailStoryActivity extends AppCompatActivity {
    private TextView textViewTitle;
    private ImageView imageViewThumbnail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_story);

        textViewTitle = findViewById(R.id.tv_title);
        imageViewThumbnail = findViewById(R.id.highlight);

        // Ambil data dari intent
        int storyId = getIntent().getIntExtra("STORY_ID", -1);

        // Cek dan ambil data sesuai ID
        StoryModel story = getStoryById(storyId); // kamu bisa ganti ini sesuai sumber datamu

        if (story != null) {
            textViewTitle.setText(story.getTitle());
            imageViewThumbnail.setImageResource(story.getThumbnailResourceId());
            // Set juga deskripsi atau apapun yang kamu mau
        } else {
            Toast.makeText(this, "Story nggak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    // Fungsi contoh buat ambil data berdasarkan ID (dummy, tinggal disesuaikan)
    private StoryModel getStoryById(int id) {
        for (StoryModel story : DataSource.getStoryList()) {
            if (story.getId() == id) {
                return story;
            }
        }
        return null;

    }
}