package com.example.newminigram;

import java.util.Date;

public class PostModel {
    private int id;
    private UserModel user;
    private int imageResourceId;
    private String caption;

    public PostModel(int id, UserModel user, int imageResourceId, String caption) {
        this.id = id;
        this.user = user;
        this.imageResourceId = imageResourceId;
        this.caption = caption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }


    public int getImageResourceId() {
        return imageResourceId;
    }


    public String getCaption() {
        return caption;
    }




}
