package agh.wta.suchowiak.bookorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import books.model.Book;

public class BooksListFragment extends Fragment {

    private List<Book> books;
    int backgroundColor;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public BooksListFragment(List<Book> books) {
        this.books = books;
        backgroundColor = -1;
    }

    public BooksListFragment(List<Book> books, int color) {
        this.books = books;
        backgroundColor = color;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.books_list_fragment, container, false);

        recyclerView = rootView.findViewById(R.id.booksView);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new BookViewAdapter(books);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        if (backgroundColor != -1) {
            rootView.findViewById(R.id.booksView).setBackgroundColor(backgroundColor);
        } else {
            FloatingActionButton fab = rootView.findViewById(R.id.fab);
            Intent intent = new Intent(getActivity(), NewBookActivity.class);
            fab.setOnClickListener(view -> {
                startActivity(intent);
            });
        }



        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
