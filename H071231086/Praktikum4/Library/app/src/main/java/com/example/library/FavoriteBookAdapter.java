package com.example.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoriteBookAdapter extends RecyclerView.Adapter<FavoriteBookAdapter.FavViewHolder> {

    private final Context context;
    private final List<BookModel> favBooks;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(BookModel book);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public FavoriteBookAdapter(Context context, List<BookModel> favBooks) {
        this.context = context;
        this.favBooks = favBooks;
    }

    public static class FavViewHolder extends RecyclerView.ViewHolder {
        ImageView imageCover;
        TextView tvTitle, tvBlurb;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCover = itemView.findViewById(R.id.imageView); // sesuaikan kalau id gambar beda
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvBlurb = itemView.findViewById(R.id.tv_blurb);
        }
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        BookModel book = favBooks.get(position);

        holder.tvTitle.setText(book.getTitle());
        holder.tvBlurb.setText(book.getBlurb());

        // Cek image source
        if (book.isFromGallery() && ImageUtils.hasUploadedImage(book.getId())) {
            Bitmap bitmap = ImageUtils.getUploadedImage(book.getId());
            holder.imageCover.setImageBitmap(bitmap);
        } else if (book.getDrawableRes() != 0) {
            holder.imageCover.setImageResource(book.getDrawableRes());
        }
        holder.itemView.setOnClickListener(v -> {

            if (listener != null) {
                listener.onItemClick(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favBooks.size();
    }
}
