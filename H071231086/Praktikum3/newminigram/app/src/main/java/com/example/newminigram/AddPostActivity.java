package com.example.newminigram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;

public class AddPostActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "AddPostActivity";

    private ImageButton newPostImageBtn;
    private EditText captionEditText;
    private Button postButton;

    private Uri selectedImageUri;
    private boolean isImageSelected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_post);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        newPostImageBtn = findViewById(R.id.new_post);
        captionEditText = findViewById(R.id.new_caption);
        postButton = findViewById(R.id.btnPost);

        postButton.setOnClickListener(v -> {
            if (isImageSelected) {
                createNewPost();
            } else {
                Toast.makeText(AddPostActivity.this, "Please select an image first", Toast.LENGTH_SHORT).show();
            }
        });

        newPostImageBtn.setOnClickListener(v -> {
            openGallery();
        });


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_add); // Ganti sesuai activity

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                startActivity(new Intent(AddPostActivity.this, HomeActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.nav_add) {
                startActivity(new Intent(AddPostActivity.this, AddPostActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(AddPostActivity.this, MainActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }

            return false;
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            try {
                newPostImageBtn.setImageURI(selectedImageUri);
                isImageSelected = true;
                Log.d("AddPostActivity", "Image selected: " + selectedImageUri.toString());
            } catch (Exception e) {
                Log.e(TAG, "Error setting image: " + e.getMessage());
                Toast.makeText(this, "Error loading image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void createNewPost() {
        String caption = captionEditText.getText().toString().trim();

        try {
            int newPostId = DataSource.getProfilePostList().size() + 200;

            BitmapDrawable drawable = (BitmapDrawable) newPostImageBtn.getDrawable();
            Bitmap selectedBitmap = drawable.getBitmap();

            ImageUtils.addUploadedImage(newPostId, selectedBitmap);

            int defaultImageId = R.drawable.post1;


            PostModel newPost = new PostModel(
                    newPostId,
                    DataSource.getCurrentUser(),
                    defaultImageId,
                    caption
            );

            DataSource.addPost(newPost);

            Toast.makeText(this, "Post created successfully!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(AddPostActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        } catch (Exception e) {
            Log.e(TAG, "Error creating post: " + e.getMessage());
            Toast.makeText(this, "Error creating post", Toast.LENGTH_SHORT).show();
        }
    }
}