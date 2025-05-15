package com.example.library;

import static com.example.library.R.id.ivBookCover;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DetailBookActivity extends AppCompatActivity {

    private ImageView ivBookCover;
    private TextView etTitle, etAuthor, etYear, etDescription;
    private Button btnAddToFavorite;

    private BookModel currentBook;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book); // pake layout yg sama

        ivBookCover = findViewById(R.id.ivBookCover);
        etTitle = findViewById(R.id.etTitle);
        etAuthor = findViewById(R.id.etAuthor);
        etYear = findViewById(R.id.etYear);
        etDescription = findViewById(R.id.etDescription);
        btnAddToFavorite = findViewById(R.id.btnAddToFavorite);




        // Ambil ID dari intent
        int bookId = getIntent().getIntExtra("book_id", -1);
        if (bookId != -1) {
            for (BookModel book : BookDataSource.getAllBooks()) {
                if (book.getId() == bookId) {
                    currentBook = book;
                    break;
                }
            }
        }

        if (currentBook != null) {
            populateBookDetail(currentBook);
        }

        btnAddToFavorite.setOnClickListener(v -> {
            if (currentBook != null) {
                boolean isFav = currentBook.isFavorite();
                currentBook.setFavorite(!isFav);

                if (!isFav) {
                    Toast.makeText(this, "Buku ditambahkan ke favorit!", Toast.LENGTH_SHORT).show();
                    btnAddToFavorite.setText("Hapus dari Favorit");
                } else {
                    Toast.makeText(this, "Buku dihapus dari favorit!", Toast.LENGTH_SHORT).show();
                    btnAddToFavorite.setText("Tambah ke Favorit");
                }
            }
        });
    }


    private void populateBookDetail(BookModel book) {
        if (ImageUtils.hasUploadedImage(book.getId())) {
            Bitmap uploadedImage = ImageUtils.getUploadedImage(book.getId());
            ivBookCover.setImageBitmap(uploadedImage);
        } else {
            ivBookCover.setImageResource(book.getDrawableRes());
        }

        etTitle.setText(book.getTitle());
        etAuthor.setText(book.getAuthor());
        etYear.setText(String.valueOf(book.getYear()));
        etDescription.setText(book.getBlurb());

        if (book.isFavorite()) {
            btnAddToFavorite.setText("Hapus dari Favorit");
        } else {
            btnAddToFavorite.setText("Tambah ke Favorit");
        }

        btnAddToFavorite.setEnabled(true);
    }
}
