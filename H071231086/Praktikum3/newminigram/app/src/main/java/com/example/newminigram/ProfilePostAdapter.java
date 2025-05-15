package com.example.newminigram;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProfilePostAdapter extends RecyclerView.Adapter<ProfilePostAdapter.ViewHolder> {
    private Context context;
    private List<PostModel> postList;

    public ProfilePostAdapter(Context context, List<PostModel> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public ProfilePostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_main_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePostAdapter.ViewHolder holder, int position) {

        PostModel post = postList.get(position);

        // Cek apakah post ini memiliki gambar yang diupload oleh user
        if (ImageUtils.hasUploadedImage(post.getId())) {
            // Gunakan gambar yang diupload
            Bitmap uploadedImage = ImageUtils.getUploadedImage(post.getId());
            holder.imageViewPost.setImageBitmap(uploadedImage);
        } else {
            // Gunakan gambar default dari resource
            holder.imageViewPost.setImageResource(post.getImageResourceId());
        }

        // Click listener to navigate to post detail
        holder.imageViewPost.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainDetailFeed.class);
            intent.putExtra("POST_ID", post.getId());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewPost;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPost = itemView.findViewById(R.id.feedmain);
        }
    }
}
