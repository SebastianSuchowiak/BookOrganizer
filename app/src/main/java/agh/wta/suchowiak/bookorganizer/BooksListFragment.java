package agh.wta.suchowiak.bookorganizer;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        }

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
