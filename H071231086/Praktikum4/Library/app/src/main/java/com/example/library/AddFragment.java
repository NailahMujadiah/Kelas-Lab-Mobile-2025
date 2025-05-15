package com.example.library;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "AddFragment";

    private ImageView imageViewCover;
    private EditText editTitle, editAuthor, editYear, editBlurb;
    private Button btnUpload, btnSave;
    private Uri selectedImageUri;
    private boolean isImageSelected = false;

    public AddFragment() {
        // Empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageViewCover = view.findViewById(R.id.imageViewCover);
        editTitle = view.findViewById(R.id.editTitle);
        editAuthor = view.findViewById(R.id.editAuthor);
        editYear = view.findViewById(R.id.editYear);
        editBlurb = view.findViewById(R.id.editBlurb);
        btnUpload = view.findViewById(R.id.btnUpload);
        btnSave = view.findViewById(R.id.btnSave);

        btnUpload.setOnClickListener(v -> openGallery());

        btnSave.setOnClickListener(v -> {
            if (!isImageSelected) {
                Toast.makeText(getContext(), "Pilih gambar dulu kakak", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = editTitle.getText().toString().trim();
            String author = editAuthor.getText().toString().trim();
            String yearStr = editYear.getText().toString().trim();
            String blurb = editBlurb.getText().toString().trim();

            if (title.isEmpty() || author.isEmpty() || yearStr.isEmpty() || blurb.isEmpty()) {
                Toast.makeText(getContext(), "Semua field wajib diisi!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int year = Integer.parseInt(yearStr);
                int bookId = BookDataSource.generateId();

                BitmapDrawable drawable = (BitmapDrawable) imageViewCover.getDrawable();
                Bitmap selectedBitmap = drawable.getBitmap();

                ImageUtils.addUploadedImage(bookId, selectedBitmap);

                BookModel newBook = new BookModel(bookId, title, author, year, blurb, -1, false, true);
                BookDataSource.addBook(newBook);

                Toast.makeText(getContext(), "Buku berhasil ditambahkan!", Toast.LENGTH_SHORT).show();

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment())
                        .commit();

            } catch (Exception e) {
                Log.e(TAG, "Error menambahkan buku: " + e.getMessage());
                Toast.makeText(getContext(), "Gagal menyimpan buku", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            try {
                imageViewCover.setImageURI(selectedImageUri);
                isImageSelected = true;
                Log.d(TAG, "Gambar dipilih: " + selectedImageUri.toString());
            } catch (Exception e) {
                Log.e(TAG, "Gagal set gambar: " + e.getMessage());
                Toast.makeText(getContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
