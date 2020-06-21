package agh.wta.suchowiak.bookorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import books.model.Book;
import books.model.User;
import books.repository.BookRepository;


public class BooksListFragment extends Fragment implements BookViewAdapter.OnItemClicked {

    private ArrayList<Book> books;

    private int backgroundColor;
    private User user;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public BooksListFragment() {
        books = BookRepository.getUserBooks();
        backgroundColor = -1;
    }

    public BooksListFragment(ArrayList<Book> books, User user) {
        this.books = user.getBooks();
        backgroundColor = -1;
        this.user = user;
    }

    public BooksListFragment(ArrayList<Book> books, int color) {
        this.books = books;
        backgroundColor = color;
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.books_list_fragment, container, false);


        recyclerView = rootView.findViewById(R.id.booksView);
        layoutManager = new LinearLayoutManager(getActivity());
        BookViewAdapter adapter = new BookViewAdapter(books);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        if (backgroundColor != -1) {
            rootView.findViewById(R.id.booksView).setBackgroundColor(backgroundColor);
        } else {
            FloatingActionButton fab = rootView.findViewById(R.id.fab);
            Intent intent = new Intent(getActivity(), NewBookActivity.class);
            intent.putExtra("user", user);
            fab.setOnClickListener(view -> {
                startActivity(intent);
            });
        }

        adapter.setOnClick(this);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    void showBookDetails(int position){

        Fragment fragment = new BookDetailsFragment(books.get(position));

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public void onItemClick(int position) {
        showBookDetails(position);
    }
}
