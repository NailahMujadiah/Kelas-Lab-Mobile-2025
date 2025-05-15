package com.example.newminigram;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import de.hdodenhof.circleimageview.CircleImageView;

public class MainDetailFeed extends AppCompatActivity {

    private ImageView imageViewBack, imageViewPost;
    private CircleImageView imageViewProfile;
    private TextView textViewUsername, textViewLikes, textViewComments, textViewShare, textViewCaption;

    private PostModel post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail_feed);

        imageViewProfile = findViewById(R.id.profile);
        imageViewPost = findViewById(R.id.postingan);
        textViewUsername = findViewById(R.id.username);;
        textViewCaption = findViewById(R.id.caption);

        int postId = getIntent().getIntExtra("POST_ID", 0);
        if (postId != 0) {
            // Cari di FeedPostList
            for (PostModel p : DataSource.getFeedPostList()) {
                if (p.getId() == postId) {
                    post = p;
                    break;
                }
            }

            // Kalau belum ketemu, cari di ProfilePostList
            if (post == null) {
                for (PostModel p : DataSource.getProfilePostList()) {
                    if (p.getId() == postId) {
                        post = p;
                        break;
                    }
                }
            }

            if (post != null) {
                // Set up the UI with post data
                setupPostDetail();
            }
        }

    }

    private void setupPostDetail() {
        // Set post image - Cek apakah ada gambar yang diupload
        if (ImageUtils.hasUploadedImage(post.getId())) {
            // Gunakan gambar yang diupload
            Bitmap uploadedImage = ImageUtils.getUploadedImage(post.getId());
            imageViewPost.setImageBitmap(uploadedImage);
        } else {
            // Gunakan gambar default dari resource
            imageViewPost.setImageResource(post.getImageResourceId());
        }

        // Set user profile image and username
        imageViewProfile.setImageResource(post.getUser().getProfileImageResourceId());
        textViewUsername.setText(post.getUser().getUsername());

        // Set likes and caption
        textViewCaption.setText(post.getCaption());
    }
}