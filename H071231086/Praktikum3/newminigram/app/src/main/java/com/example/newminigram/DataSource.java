package com.example.newminigram;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static List<UserModel> userList = new ArrayList<>();
    private static List<PostModel> feedPostList = new ArrayList<>();
    private static List<PostModel> profilePostList = new ArrayList<>();
    private static List<StoryModel> storyList = new ArrayList<>();
    private static UserModel currentUser;

    static {
        try {
            initUsers();
            initFeedPosts();
            initProfilePosts();
            initStories();
        } catch (Exception e) {
            Log.e("DataSource", "Error initializing data: " + e.getMessage());
        }
    }

    private static void initUsers() {
        currentUser = new UserModel(
                1,
                "reinyourheart",
                R.drawable.mainprofile,
                "REI",
                "Bio",
                1000,
                0,
                6
        );
        userList.add(new UserModel(2, "_yujin_an", R.drawable.profile1, "Yujin", "bio", 999345, 15, 1));
        userList.add(new UserModel(3,"fallingin__fall", R.drawable.profile2, "Gaeul", "bio",99999,17,1));
        userList.add(new UserModel(4,"liz.yeyo",R.drawable.profile3,"Liz","bio",77777,8,1));
        userList.add(new UserModel(5,"eeseooes",R.drawable.profile4,"Leeseo","bio",757557,8,1));
        userList.add(new UserModel(6,"for_everyoung10",R.drawable.profile5,"Wonyoung","bio",88888,8,1));
        userList.add(new UserModel(7,"ivestarship",R.drawable.profile6,"IVE Official","bio",77777,8,1));
        userList.add(new UserModel(8,"aerichandesu",R.drawable.profile7,"Giselle","bio",88888,8,1));
        userList.add(new UserModel(9,"imwinter",R.drawable.profile8,"Winter","bio",890,8,1));
        userList.add(new UserModel(10,"katarinabluu",R.drawable.profile9,"Karina","bio",890,8,1));
        userList.add(new UserModel(11,"imnotningning",R.drawable.profile10,"Ning Ning","bio",890,8,1));
        userList.add(currentUser);
    }

    private static void initFeedPosts() {
        feedPostList.add(new PostModel(1, userList.get(0), R.drawable.post1, "caption"));
        feedPostList.add(new PostModel(2, userList.get(1), R.drawable.post2, "caption"));
        feedPostList.add(new PostModel(3, userList.get(2), R.drawable.post3, "caption"));
        feedPostList.add(new PostModel(4, userList.get(3), R.drawable.post4, "caption"));
        feedPostList.add(new PostModel(5, userList.get(4), R.drawable.post5, "caption"));
        feedPostList.add(new PostModel(6, userList.get(5), R.drawable.post6, "caption"));
        feedPostList.add(new PostModel(7, userList.get(6), R.drawable.post7, "caption"));
        feedPostList.add(new PostModel(8, userList.get(7), R.drawable.post8, "caption"));
        feedPostList.add(new PostModel(9, userList.get(8), R.drawable.post9, "caption"));
        feedPostList.add(new PostModel(10, userList.get(9), R.drawable.post10, "caption"));


    }

    private static void initProfilePosts() {
        profilePostList.add(new PostModel(111, currentUser, R.drawable.feed1, "hola"));
        profilePostList.add(new PostModel(112, currentUser, R.drawable.feed3, "hola"));
        profilePostList.add(new PostModel(113, currentUser, R.drawable.feed2, "hola"));
        profilePostList.add(new PostModel(114, currentUser, R.drawable.feed4, "caption"));
        profilePostList.add(new PostModel(115, currentUser, R.drawable.feed5, "caption"));
        currentUser.setPostsCount(profilePostList.size());
    }

    private static void initStories() {
        storyList.add(new StoryModel(
                1,
                "myself",
                R.drawable.story1
        ));
        storyList.add(new StoryModel(
                2,
                "me",
                R.drawable.story3
        ));
        storyList.add(new StoryModel(
                3,
                "beach",
                R.drawable.story2
        ));
        storyList.add(new StoryModel(
                4,
                "coffee",
                R.drawable.story4
        ));
        storyList.add(new StoryModel(
                5,
                "fav",
                R.drawable.story5
        ));
        storyList.add(new StoryModel(
                7,
                "y",
                R.drawable.story6
        ));
        storyList.add(new StoryModel(
                6,
                "l",
                R.drawable.story7
        ));
    }

    public static List<UserModel> getUserList() {
        return userList;
    }
    public static List<PostModel> getFeedPostList() {
        return feedPostList;
    }
    public static List<PostModel> getProfilePostList() {
        return profilePostList;
    }
    public static List<StoryModel> getStoryList() {
        return storyList;
    }
    public static UserModel getCurrentUser() {
        return currentUser;
    }

    public static void addPost(PostModel post) {
        profilePostList.add(0, post);
        currentUser.setPostsCount(profilePostList.size());
    }

    public static List<PostModel> getPostsByUser(UserModel user) {
        List<PostModel> result = new ArrayList<>();
        for (PostModel post : feedPostList) {
            if (post.getUser().getId() == user.getId()) {
                result.add(post);
            }
        }

        if (user.getId() == currentUser.getId()) {
            result.addAll(profilePostList);
        }

        return result;
    }

}
