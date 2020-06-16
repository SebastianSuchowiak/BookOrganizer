package agh.wta.suchowiak.bookorganizer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import books.model.Book;
import books.model.Tag;
import books.repository.BookRepository;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.lujun.androidtagview.TagContainerLayout;

public class BookDetailsFragment extends Fragment {

    @BindView(R.id.cover) ImageView _cover;
    @BindView(R.id.title) TextView _title;
    @BindView(R.id.author) TextView _author;
    @BindView(R.id.score) TextView _score;
    @BindView(R.id.review) TextView _review;
    @BindView(R.id.status) TextView _status;
    @BindView(R.id.tagsText) TextView _tagsText;
    @BindView(R.id.tags) TagContainerLayout _tags;
    private Book book;

    public BookDetailsFragment(Book book){
        this.book = book;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.book_details_fragment, container, false);
        ButterKnife.bind(this, rootView);



       _title.setText(book.getTitle());
       _score.setText("Score: " + String.valueOf(book.getScore()));
       _author.setText(book.getAuthorsRep());
       _status.setText(book.getStatus().name());
       _review.setText(book.getReview());

        book.getTags().forEach((tag) -> _tags.addTag(tag.getName()));
        return rootView;
    }
}
