package com.example.library;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<BookModel> data;

    public BookAdapter(List<BookModel> data) {
        this.data = data;
    }

    public void setFilteredList(List<BookModel> filteredList) {
        data.clear(); // hapus data lama
        data.addAll(filteredList); // tambahkan data baru hasil filter
        notifyDataSetChanged(); // refresh RecyclerView-nya
    }



    public interface OnItemClickListener {
        void onItemClick(BookModel book);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Sesuaikan dengan layout item kamu nanti
        public ImageView ivBookCover;
        public TextView tvTitle;

        public interface OnItemClickListener {
            void onItemClick(BookModel book);
        }

        public ViewHolder(View itemView) {
            super(itemView);
            ivBookCover = itemView.findViewById(R.id.iv_cover); // ganti sesuai id-mu
            tvTitle = itemView.findViewById(R.id.title);
        }
    }

    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BookAdapter.ViewHolder holder, int position) {
        BookModel book = data.get(position);

        if (ImageUtils.hasUploadedImage(book.getId())) {
            Bitmap uploadedImage = ImageUtils.getUploadedImage(book.getId());
            holder.ivBookCover.setImageBitmap(uploadedImage);
        } else {
            holder.ivBookCover.setImageResource(book.getDrawableRes());
        }

        holder.tvTitle.setText(book.getTitle());

        // 👇 Tambahkan click listener ke itemView (satu item utuh)
        holder.itemView.setOnClickListener(v -> {

            if (listener != null) {
                listener.onItemClick(book);
            }
        });
    }




    @Override
    public int getItemCount() {
        return data.size();
    }
}
