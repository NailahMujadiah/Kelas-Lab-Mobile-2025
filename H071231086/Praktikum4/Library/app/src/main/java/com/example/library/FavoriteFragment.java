package com.example.library;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class FavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private FavoriteBookAdapter adapter, favadapter;
    private List<BookModel> favBookList;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        recyclerView = view.findViewById(R.id.rv_collection);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        favBookList = BookDataSource.getFavoriteBooks();
        adapter = new FavoriteBookAdapter(getContext(), favBookList);
        adapter.setOnItemClickListener(book -> {
            navigateToDetail(book.getId());
        });

        recyclerView.setAdapter(adapter);

        return view;
    }

    private void navigateToDetail(int bookId) {
        Intent intent = new Intent(getContext(), DetailBookActivity.class);
        intent.putExtra("book_id", bookId);
        startActivity(intent);
    }

}