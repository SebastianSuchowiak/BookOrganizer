package agh.wta.suchowiak.bookorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import books.model.Book;
import books.model.Status;
import books.model.Tag;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Book> books = new ArrayList<>();

        List<Tag> tags = Arrays.asList(
                new Tag("top10", "#8803DA"),
                new Tag("fantasy", "#3503DA"));

        List<String> authors = Collections.singletonList("Brandon Sanderson");

        Book book1 = new Book("The Way of Kings", authors, 10L, Status.HAVE_TO_READ, tags, "", 8, "");
        Book book2 = new Book("The Way of Kings", new ArrayList<>(authors), 10L, Status.HAVE_TO_READ, new ArrayList<>(tags), "", 8, "");
        Book book3 = new Book("The Way of Kings", new ArrayList<>(authors), 10L, Status.HAVE_TO_READ, new ArrayList<>(tags), "", 8, "");
        Book book4 = new Book("The Way of Kings", new ArrayList<>(authors), 10L, Status.HAVE_TO_READ, new ArrayList<>(tags), "", 8, "");

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);

        recyclerView = findViewById(R.id.booksView);
        layoutManager = new LinearLayoutManager(this);
        adapter = new BookViewAdapter(books);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
