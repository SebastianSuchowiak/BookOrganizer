package agh.wta.suchowiak.bookorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import books.model.Book;
import books.model.Status;
import books.repository.BookRepository;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewBookActivity extends AppCompatActivity {

    @BindView(R.id.new_book_title)
    EditText title;
    @BindView(R.id.new_book_author)
    EditText author;
    @BindView(R.id.new_book_isbn)
    EditText isbn;
    @BindView(R.id.new_book_review)
    EditText review;
    @BindView(R.id.new_book_rate)
    RatingBar rate;
    @BindView(R.id.new_book_button)
    Button button;

    private Book newBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_add_book);
        ButterKnife.bind(this);

        rate.setOnRatingBarChangeListener(this::onRatingChanged);
        button.setOnClickListener(this::submit);

        newBook = new Book();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_finished:
                if (checked)
                    newBook.setStatus(Status.HAVE_READ);
                break;
            case R.id.radio_reading:
                if (checked)
                    newBook.setStatus(Status.ABANDONED);
                break;
            case R.id.radio_to_read:
                if (checked)
                    newBook.setStatus(Status.HAVE_TO_READ);
                break;
        }
    }

    private void submit(View view) {
        if (isValid()) {
            List<String> authors = new ArrayList<>();
            authors.add(author.getText().toString());
            newBook.setAuthors(authors);

            newBook.setIsbn(Double.parseDouble(isbn.getText().toString()));
            newBook.setReview(review.getText().toString());
            newBook.setTitle(title.getText().toString());

            ArrayList<Book> books = BookRepository.getUserBooks();
            books.add(newBook);
            BookRepository.setUserBooks(books);
            System.out.println(newBook);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void onRatingChanged(RatingBar ratingBar,
                                float rating,
                                boolean fromUser) {
        newBook.setScore(Math.round(rating));
    }

    public boolean isValid() {
        return true;
    }
}
