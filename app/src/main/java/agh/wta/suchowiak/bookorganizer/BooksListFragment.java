package agh.wta.suchowiak.bookorganizer;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import books.model.Book;
import books.model.Tag;
import books.repository.BookRepository;

public class BooksListFragment extends Fragment implements BookViewAdapter.OnItemClicked {

    ArrayList<Book> books;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.books_list_fragment, container, false);
        books = BookRepository.getUserBooks();
        for(Book b : books){
            // b.setAuthors();
        }

        List<Tag> tags = Arrays.asList(
                new Tag("top10", "#8803DA"),
                new Tag("fantasy", "#3503DA"));

        List<String> authors = Collections.singletonList("Brandon Sanderson");


        recyclerView = rootView.findViewById(R.id.booksView);
        layoutManager = new LinearLayoutManager(getActivity());
        BookViewAdapter adapter = new BookViewAdapter(books);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnClick(this);
        // Inflate the layout for this fragment
        return rootView;
    }

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

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
