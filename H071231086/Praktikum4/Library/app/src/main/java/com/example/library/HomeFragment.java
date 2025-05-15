package com.example.library;

import static com.example.library.R.id.rvFavorit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

//import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rvRekomendasi, rvFavorit, allbook;
    private TextView tvTitleRekomendasi, tvkoleksi;
    private SearchView SearchView;
    private RecomBookAdapter adapter;
    private BookAdapter alladapter;

    private List<BookModel> rekomendasiList;

    public HomeFragment() {}

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SearchView = view.findViewById(R.id.searchView);
        SearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    rvRekomendasi.setVisibility(View.VISIBLE);
                    rvFavorit.setVisibility(View.VISIBLE);
                } else {
                    rvRekomendasi.setVisibility(View.GONE);
                    rvFavorit.setVisibility(View.GONE);
                }
                filterList(newText);
                return true;
            }
        });
        SearchView.clearFocus();

        tvTitleRekomendasi = view.findViewById(R.id.tvTitleRekomendasi);
        rvRekomendasi = view.findViewById(R.id.rvRekomendasi);
        rvFavorit = view.findViewById(R.id.rvFavorit);
        allbook = view.findViewById(R.id.allbook);
        tvkoleksi = view.findViewById(R.id.tvkoleksi);

        setupRekomendasi();

        return view;
    }

    private void filterList(String newText) {
        List<BookModel> filteredList = new ArrayList<>();

        for (BookModel book : BookDataSource.getAllBooks()) {
            if (book.getTitle().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(book);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(), "Buku tidak ditemukan", Toast.LENGTH_SHORT).show();
        } else {
            alladapter.setFilteredList(filteredList); // ✅ pakai instance, bukan class static
        }
    }

    private void setupRekomendasi() {
        rekomendasiList = BookDataSource.getRecommendedBooks();

        adapter = new RecomBookAdapter(requireContext(), rekomendasiList, new RecomBookAdapter.OnBookActionListener() {
            @Override
            public void onBookCentered(BookModel book, int position) {
                tvTitleRekomendasi.setText(book.getTitle());
                tvTitleRekomendasi.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAddToFavorite(BookModel book, int position) {
                book.setFavorite(true);
                rekomendasiList.remove(book);

                BookModel newBook = BookDataSource.getRandomNonFavoriteBook();
                if (newBook != null) rekomendasiList.add(newBook);

                adapter.notifyDataSetChanged();
            }

            public void onBookClicked(BookModel book) {
                Intent intent = new Intent(getContext(), DetailBookActivity.class);
                intent.putExtra("book_id", book.getId());
                startActivity(intent);
            }

        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        rvRekomendasi.setLayoutManager(layoutManager);
        rvRekomendasi.setAdapter(adapter);

        int middle = Integer.MAX_VALUE / 2;
        int mod = middle % rekomendasiList.size();
        rvRekomendasi.scrollToPosition(middle - mod);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvRekomendasi);

        rvFavorit.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        List<BookModel> favoritList = BookDataSource.getFavoriteBooks();
        BookAdapter favoritAdapter = new BookAdapter(favoritList);

// Cek apakah favoritList kosong atau tidak
        if (favoritList != null && !favoritList.isEmpty()) {
            // Jika ada koleksi buku, tampilkan TextView
            tvkoleksi.setVisibility(View.VISIBLE);
        } else {
            // Jika tidak ada buku, sembunyikan TextView
            tvkoleksi.setVisibility(View.GONE);
        }

        favoritAdapter.setOnItemClickListener(book -> {
            navigateToDetail(book.getId());
        });

        rvFavorit.setAdapter(favoritAdapter);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        allbook.setLayoutManager(gridLayoutManager);
        List<BookModel> allList = BookDataSource.getAllNonFavoriteBooks();
        Collections.reverse(allList);
        alladapter = new BookAdapter(allList);
        alladapter.setOnItemClickListener(book -> {
            navigateToDetail(book.getId());
        });
        allbook.setAdapter(alladapter);

        rvRekomendasi.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    View snapView = snapHelper.findSnapView(layoutManager);
                    if (snapView != null) {
                        int snapPosition = layoutManager.getPosition(snapView);
                        if (snapPosition != RecyclerView.NO_POSITION) {
                            int realPosition = snapPosition % rekomendasiList.size();
                            BookModel centeredBook = rekomendasiList.get(realPosition);
                            adapter.listener.onBookCentered(centeredBook, snapPosition);
                        }
                    }
                }
            }

            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int centerX = recyclerView.getWidth() / 2;
                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    View child = recyclerView.getChildAt(i);
                    float childCenterX = (child.getLeft() + child.getRight()) / 2f;
                    float distanceToCenter = Math.abs(centerX - childCenterX);

                    float scale = 1 - (distanceToCenter / centerX);
                    float scaleFactor = 0.05f;

                    float finalScale = 1 + scale * scaleFactor;
                    child.setScaleX(finalScale);
                    child.setScaleY(finalScale);
                }
            }
        });
    }

    private void navigateToDetail(int bookId) {
        Intent intent = new Intent(getContext(), DetailBookActivity.class);
        intent.putExtra("book_id", bookId);
        startActivity(intent);
    }

}
