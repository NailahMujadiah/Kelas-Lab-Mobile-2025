package com.example.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecomBookAdapter extends RecyclerView.Adapter<RecomBookAdapter.BookViewHolder> {

    private final List<BookModel> bookList;
    private final Context context;
    public final OnBookActionListener listener;


    public interface OnBookActionListener {
        void onBookCentered(BookModel book, int position);
        void onAddToFavorite(BookModel book, int position);
        void onBookClicked(BookModel book);
    }

    public RecomBookAdapter(Context context, List<BookModel> bookList, OnBookActionListener listener) {
        this.context = context;
        this.bookList = bookList;
        this.listener = listener;
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView imageCover;
        Button btnAddFav;
        RelativeLayout popUpContainer;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCover = itemView.findViewById(R.id.imageCover);
            btnAddFav = itemView.findViewById(R.id.btnAddFav);
            popUpContainer = itemView.findViewById(R.id.popUpContainer);
        }
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommendation_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        int realPosition = position % bookList.size(); // biar looping
        BookModel book = bookList.get(realPosition);

        // Cek apakah bitmap dari galeri atau drawable dummy
        if (book.isFromGallery() && ImageUtils.hasUploadedImage(book.getId())) {
            Bitmap bitmap = ImageUtils.getUploadedImage(book.getId());
            holder.imageCover.setImageBitmap(bitmap);
        } else if (book.getDrawableRes() != 0) {
            holder.imageCover.setImageResource(book.getDrawableRes());
        } else {
            holder.imageCover.setImageResource(R.drawable.book2); // fallback default
        }

        // Pop-up tambah favorite, hidden by default
        holder.popUpContainer.setVisibility(View.GONE);

        // Long press buat munculin tombol
        holder.itemView.setOnLongClickListener(v -> {
            holder.popUpContainer.setVisibility(View.VISIBLE);
            fadeIn(holder.popUpContainer);
            return true;
        });

        // Klik tombol untuk tambah ke favorite
        holder.btnAddFav.setOnClickListener(v -> {
            holder.popUpContainer.setVisibility(View.GONE);
            listener.onAddToFavorite(book, position);
        });

        holder.itemView.setOnClickListener(v -> {
            listener.onBookClicked(book);
        });
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    // Utility: animasi smooth
    private void fadeIn(View view) {
        AlphaAnimation anim = new AlphaAnimation(0f, 1f);
        anim.setDuration(300);
        view.startAnimation(anim);
    }
}
