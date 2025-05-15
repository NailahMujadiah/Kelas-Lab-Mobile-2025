package com.example.library;

public class BookModel {
    private int id;
    private String title;
    private String author;
    private int year;
    private String blurb;
    private int drawableRes; // buat dummy cover dari drawable
    private boolean isFavorite;
    private boolean isFromGallery; // penanda kalau ini dari add new book

    public BookModel(int id, String title, String author, int year, String blurb, int drawableRes, boolean isFavorite, boolean isFromGallery) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.blurb = blurb;
        this.drawableRes = drawableRes;
        this.isFavorite = isFavorite;
        this.isFromGallery = isFromGallery;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public int getDrawableRes() {
        return drawableRes;
    }

    public void setDrawableRes(int drawableRes) {
        this.drawableRes = drawableRes;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isFromGallery() {
        return isFromGallery;
    }

    public void setFromGallery(boolean fromGallery) {
        isFromGallery = fromGallery;
    }


}