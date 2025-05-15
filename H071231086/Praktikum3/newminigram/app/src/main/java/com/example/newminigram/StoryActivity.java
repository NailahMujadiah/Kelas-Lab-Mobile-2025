package com.example.newminigram;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newminigram.R;
import com.example.newminigram.StoryModel;
import com.example.newminigram.DataSource;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StoryActivity extends AppCompatActivity {
    private ImageView imageViewStory, imageViewClose;
    private CircleImageView imageViewProfile;
    private TextView textViewStoryTitle;
    private int currentImageIndex = 0;
    private LinearLayout storyProgressContainer;
    private List<View> progressBars = new ArrayList<>();


    private StoryModel story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);


        imageViewStory = findViewById(R.id.highlight);
        textViewStoryTitle = findViewById(R.id.tv_title);

        int storyId = getIntent().getIntExtra("STORY_ID", 0);
        if (storyId != 0) {
            for (StoryModel s : DataSource.getStoryList()) {
                if (s.getId() == storyId) {
                    story = s;
                    break;
                }
            }

            if (story != null) {
                setupStoryDetail();
            }
        }

        imageViewClose.setOnClickListener(v -> finish());
    }



    //    private void setupStoryDetail() {
//        imageViewStory.setImageResource(story.getImageResourceIds());
//
//        imageViewProfile.setImageResource(DataSource.getCurrentUser().getProfileImageResourceId());
//
//        textViewStoryTitle.setText(story.getTitle());
//    }
    private void setupStoryDetail() {
        List<Integer> images = story.getImageResourceIds();
        currentImageIndex = 0; // Reset posisi ke gambar pertama

        if (images != null && !images.isEmpty()) {
            imageViewStory.setImageResource(images.get(currentImageIndex));


            imageViewStory.setOnClickListener(v -> {
                currentImageIndex++;
                if (currentImageIndex >= images.size()) {
                    finish(); // Keluar dari Story setelah gambar terakhir
                } else {
                    imageViewStory.setImageResource(images.get(currentImageIndex));
                }
            });
        }

        imageViewProfile.setImageResource(DataSource.getCurrentUser().getProfileImageResourceId());
        textViewStoryTitle.setText(story.getTitle());

    }


}
