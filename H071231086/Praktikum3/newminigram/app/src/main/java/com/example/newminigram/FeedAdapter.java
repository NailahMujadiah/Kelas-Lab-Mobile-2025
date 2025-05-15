package com.example.newminigram;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private Context context;
    private List<PostModel> postList;

    public FeedAdapter(Context context, List<PostModel> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_main_detail_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.ViewHolder holder, int position) {

        PostModel post = postList.get(position);

        holder.imageViewProfile.setImageResource(post.getUser().getProfileImageResourceId());
        holder.textViewUsername.setText(post.getUser().getUsername());

        if (ImageUtils.hasUploadedImage(post.getId())) {
            Bitmap uploadedImage = ImageUtils.getUploadedImage(post.getId());
            holder.imageViewPost.setImageBitmap(uploadedImage);
        } else {
            holder.imageViewPost.setImageResource(post.getImageResourceId());
        }

        holder.textViewCaption.setText(post.getCaption());

        holder.imageViewProfile.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("USER_ID", post.getUser().getId());
            context.startActivity(intent);
        });

        holder.imageViewPost.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainDetailFeed.class);
            intent.putExtra("POST_ID", post.getId());
            context.startActivity(intent);
        });
        Log.d("FeedAdapter", "Binding post: " + post.getCaption());

    }


    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutPostHeader;
        CircleImageView imageViewProfile;
        ImageView imageViewPost;
        TextView textViewUsername, textViewCaption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProfile = itemView.findViewById(R.id.profile);
            imageViewPost = itemView.findViewById(R.id.postingan);
            textViewUsername = itemView.findViewById(R.id.username);
            textViewCaption = itemView.findViewById(R.id.caption);

        }
    }
}
