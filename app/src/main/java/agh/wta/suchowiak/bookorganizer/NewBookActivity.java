package agh.wta.suchowiak.bookorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import books.model.Book;
import books.model.Status;
import books.model.Tag;
import books.model.User;
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
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = (User) getIntent().getSerializableExtra("user");
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_add_book);
        ButterKnife.bind(this);

        rate.setOnRatingBarChangeListener(this::onRatingChanged);
        button.setOnClickListener(this::submit);

        newBook = new Book();
        newBook.setTags(new ArrayList<>());
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

    //TODO: Very ugly to be replaced by something dynamic
    public void onRadioButtonTagClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.tag_long:
                if (checked)
                    newBook.getTags().add(tags.get(0));
                else {
                    newBook.getTags().remove(tags.get(0));
                }
                break;
            case R.id.tag_short:
                if (checked)
                    newBook.getTags().add(tags.get(1));
                else {
                    newBook.getTags().remove(tags.get(1));
                }
                break;
            case R.id.tag_fantasy:
                if (checked)
                    newBook.getTags().add(tags.get(2));
                else {
                    newBook.getTags().remove(tags.get(2));
                }
                break;
            case R.id.tag_horror:
                if (checked)
                    newBook.getTags().add(tags.get(3));
                else {
                    newBook.getTags().remove(tags.get(3));
                }
                break;
            case R.id.tag_for_kids:
                if (checked)
                    newBook.getTags().add(tags.get(4));
                else {
                    newBook.getTags().remove(tags.get(4));
                }
                break;
            case R.id.tag_the_best:
                if (checked)
                    newBook.getTags().add(tags.get(5));
                else {
                    newBook.getTags().remove(tags.get(5));
                }
                break;
        }
    }

    List<Tag> tags = Arrays.asList(
            new Tag("long", "#22222b"),
            new Tag("short", "#34ebeb"),
            new Tag("fantasy", "#7f75c7"),
            new Tag("horror", "#a2332b"),
            new Tag("for kids", "#34eb46"),
            new Tag("the best", "#e3716d")
            );

    private void submit(View view) {
        if (isValid()) {
            List<String> authors = new ArrayList<>();
            authors.add(author.getText().toString());
            newBook.setAuthors(authors);

            newBook.setIsbn(Double.parseDouble(isbn.getText().toString()));
            newBook.setReview(review.getText().toString());
            newBook.setTitle(title.getText().toString());

            user.getBooks().add(newBook);
            System.out.println(newBook);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
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
