package agh.wta.suchowiak.bookorganizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import books.model.Book;
import books.model.Tag;
import books.repository.BookRepository;

public class BooksListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.books_list_fragment, container, false);
        ArrayList<Book> books = BookRepository.getUserBooks();
        for(Book b : books){
            // b.setAuthors();
        }

        List<Tag> tags = Arrays.asList(
                new Tag("top10", "#8803DA"),
                new Tag("fantasy", "#3503DA"));

        List<String> authors = Collections.singletonList("Brandon Sanderson");

        /*Book book1 = new Book("The Way of Kings", authors, 10L, Status.HAVE_TO_READ, tags, "", 8, "");
        Book book2 = new Book("The Way of Kings", new ArrayList<>(authors), 10L, Status.HAVE_TO_READ, new ArrayList<>(tags), "", 8, "");
        Book book3 = new Book("The Way of Kings", new ArrayList<>(authors), 10L, Status.HAVE_TO_READ, new ArrayList<>(tags), "", 8, "");
        Book book4 = new Book("The Way of Kings", new ArrayList<>(authors), 10L, Status.HAVE_TO_READ, new ArrayList<>(tags), "", 8, "");*/

        /*books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);*/

        recyclerView = rootView.findViewById(R.id.booksView);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new BookViewAdapter(books);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

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
}
