package agh.wta.suchowiak.bookorganizer;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agh.wta.suchowiak.tagorganizer.TagViewAdapter;
import books.model.Book;
import books.model.Tag;
import books.repository.BookRepository;

public class TagsFragment extends Fragment {
    private RecyclerView recyclerView;
    private agh.wta.suchowiak.tagorganizer.TagViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.books_list_fragment, container, false);

        ArrayList<Book> books = BookRepository.getUserBooks();
        Map<Tag, List<Book>> tagsBooksMap = new HashMap<>();

        for (Book book: books) {
            for (Tag tag: book.getTags()) {
                if (!tagsBooksMap.containsKey(tag)) {
                    tagsBooksMap.put(tag, new ArrayList<>());
                }
                tagsBooksMap.get(tag).add(book);
            }
        }
        List<Map.Entry<Tag, List<Book>>> tagsBooks = new ArrayList<>(tagsBooksMap.entrySet());

        recyclerView = rootView.findViewById(R.id.booksView);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new agh.wta.suchowiak.tagorganizer.TagViewAdapter(tagsBooks);
        adapter.setOnItemClickListener(position -> {
            List<Book> currentBooks = tagsBooks.get(position).getValue();
            int color = Color.parseColor(tagsBooks.get(position).getKey().getColor());
            BooksListFragment nextFrag= new BooksListFragment(currentBooks, color);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
