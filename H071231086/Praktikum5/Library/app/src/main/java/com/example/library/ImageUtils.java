package com.example.library;

import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.Map;

public class ImageUtils {
    private static final Map<Integer, Bitmap> uploadedImages = new HashMap<>();

    public static void addUploadedImage(int bookId, Bitmap bitmap) {
        uploadedImages.put(bookId, bitmap);
    }

    public static Bitmap getUploadedImage(int bookId) {
        return uploadedImages.get(bookId);
    }

    public static boolean hasUploadedImage(int bookId) {
        return uploadedImages.containsKey(bookId);
    }
}