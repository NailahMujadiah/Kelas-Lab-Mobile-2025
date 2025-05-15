package com.example.library;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private SearchView SearchView;
    private FavoriteBookAdapter adapter;
    private List<BookModel> favBookList;

    private ProgressBar progressBar;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        SearchView = view.findViewById(R.id.searchView);
        recyclerView = view.findViewById(R.id.rv_collection);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Tampilkan loading
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        // Load data di background thread
        executor.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            favBookList = BookDataSource.getFavoriteBooks();

            handler.post(() -> {
                adapter = new FavoriteBookAdapter(getContext(), favBookList);
                adapter.setOnItemClickListener(book -> {
                    navigateToDetail(book.getId());
                });

                recyclerView.setAdapter(adapter);

                // setelah data muncul
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            });
        });

        SearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        return view;
    }


    private void filterList(String newText) {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        executor.execute(() -> {
            List<BookModel> filteredList = new ArrayList<>();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (BookModel book : favBookList) {
                if (book.getTitle().toLowerCase().contains(newText.toLowerCase())) {
                    filteredList.add(book);
                }
            }

            handler.post(() -> {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                if (filteredList.isEmpty()) {
                    Toast.makeText(getContext(), "Buku favorit tidak ditemukan", Toast.LENGTH_SHORT).show();
                }

                adapter.setFilteredList(filteredList);
            });
        });
    }


    private void navigateToDetail(int bookId) {
        Intent intent = new Intent(getContext(), DetailBookActivity.class);
        intent.putExtra("book_id", bookId);
        startActivity(intent);
    }
}
