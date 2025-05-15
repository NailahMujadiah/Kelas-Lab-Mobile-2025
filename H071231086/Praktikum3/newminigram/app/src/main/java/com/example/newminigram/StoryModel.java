package com.example.newminigram;

import java.util.List;

public class StoryModel {
    private int id;
    private String title;
    private int thumbnailResourceId;
    private List<Integer> imageResourceIds;

    public StoryModel(int id, String title, int thumbnailResourceId) {
        this.id = id;
        this.title = title;
        this.thumbnailResourceId = thumbnailResourceId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getThumbnailResourceId() {
        return thumbnailResourceId;
    }

    public void setThumbnailResourceId(int thumbnailResourceId) {
        this.thumbnailResourceId = thumbnailResourceId;
    }

    public List<Integer> getImageResourceIds() {
        return imageResourceIds;
    }

    public void setImageResourceIds(List<Integer> imageResourceIds) {
        this.imageResourceIds = imageResourceIds;
    }

}
