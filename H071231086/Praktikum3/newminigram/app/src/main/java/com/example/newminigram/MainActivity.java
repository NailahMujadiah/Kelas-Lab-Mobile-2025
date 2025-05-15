package com.example.newminigram;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private CircleImageView imageViewProfile;
    private TextView textViewUsername, textViewProfileName, textViewBio;
    private TextView textViewPostsCount, textViewFollowersCount, textViewFollowingCount;
    private RecyclerView recyclerViewStories, recyclerViewProfilePosts;
    private StoryAdapter storyAdapter;
    private ProfilePostAdapter profilePostAdapter;
    private UserModel user;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewProfile = findViewById(R.id.Prof);
        textViewUsername = findViewById(R.id.uname);
        textViewProfileName = findViewById(R.id.nama);
        textViewBio = findViewById(R.id.biodata);
        textViewPostsCount = findViewById(R.id.posts);
        textViewFollowersCount = findViewById(R.id.followers);
        textViewFollowingCount = findViewById(R.id.following);
        recyclerViewStories = findViewById(R.id.rv_highlight);
        recyclerViewProfilePosts = findViewById(R.id.rv_feed);

        int userId = getIntent().getIntExtra("USER_ID", 0);
        if (userId != 0) {
            for (UserModel u : DataSource.getUserList()) {
                if (u.getId() == userId) {
                    user = u;
                    break;
                }
            }
        } else {
            user = DataSource.getCurrentUser();
        }

        if (user == null) {
            user = DataSource.getCurrentUser();
        }

        setupUserProfile();
        if (user.getId() == DataSource.getCurrentUser().getId()) {
            setupStoryHighlights();
        } else {
            recyclerViewStories.setVisibility(View.GONE);
        }

        setupStoryHighlights();

        setupProfilePosts();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_profile); // Ganti sesuai activity




        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.nav_add) {
                startActivity(new Intent(MainActivity.this, AddPostActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.nav_profile) {
//                startActivity(new Intent(MainProfile.this, MainProfile.class));
                overridePendingTransition(0, 0);
                return true;
            }

            return false;
        });
    }

    private void setupUserProfile() {
        imageViewProfile.setImageResource(user.getProfileImageResourceId());
        textViewUsername.setText(user.getUsername());
        textViewProfileName.setText(user.getProfileName());
        textViewBio.setText(user.getBio());
        textViewPostsCount.setText(String.valueOf(user.getPostsCount()));
        textViewFollowersCount.setText(String.valueOf(user.getFollowersCount()));
        textViewFollowingCount.setText(String.valueOf(user.getFollowingCount()));


    }

    private void setupStoryHighlights() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewStories.setLayoutManager(layoutManager);

        storyAdapter = new StoryAdapter(this, DataSource.getStoryList());
        recyclerViewStories.setAdapter(storyAdapter);
    }

    private void setupProfilePosts() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerViewProfilePosts.setLayoutManager(gridLayoutManager);
        profilePostAdapter = new ProfilePostAdapter(this, DataSource.getPostsByUser(user));
        recyclerViewProfilePosts.setAdapter(profilePostAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupUserProfile();
        profilePostAdapter.notifyDataSetChanged();
    }
}
