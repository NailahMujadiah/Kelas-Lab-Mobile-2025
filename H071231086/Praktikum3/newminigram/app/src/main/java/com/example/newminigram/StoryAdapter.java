package com.example.newminigram;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    private Context context;
    private List<StoryModel> storyList;

    public StoryAdapter(Context context, List<StoryModel> storyList) {
        this.context = context;
        this.storyList = storyList;
    }

    @NonNull
    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_story, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.ViewHolder holder, int position) {
        StoryModel story = storyList.get(position);

        // Set story thumbnail and title
        holder.imageViewStory.setImageResource(story.getThumbnailResourceId());
        holder.textViewStoryTitle.setText(story.getTitle());

        // Click listener to navigate to story detail
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailStoryActivity.class);
            intent.putExtra("STORY_ID", story.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageViewStory;
        TextView textViewStoryTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewStory = itemView.findViewById(R.id.highlight);
            textViewStoryTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
