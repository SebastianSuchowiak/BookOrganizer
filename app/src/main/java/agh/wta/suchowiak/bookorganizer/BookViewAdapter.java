package agh.wta.suchowiak.bookorganizer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import books.model.Book;
import co.lujun.androidtagview.TagContainerLayout;
import lombok.Getter;
import lombok.Setter;

public class BookViewAdapter extends RecyclerView.Adapter<BookViewAdapter.BookViewHolder> {

    private ArrayList<Book> books;

    @Getter
    static class BookViewHolder extends RecyclerView.ViewHolder {
        private ImageView cover;
        private TextView title;
        private TextView author;
        private TextView score;
        private TagContainerLayout tags;

        BookViewHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.cover);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            score = itemView.findViewById(R.id.score);
            tags = itemView.findViewById(R.id.tags);
        }
    }

    public BookViewAdapter(ArrayList<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_card, parent, false);
        BookViewHolder bookViewHolder = new BookViewHolder(v);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BookViewHolder holder, int position) {
        Book book = books.get(position);

        holder.getAuthor().setText(book.getAuthorsRep());
        holder.getTitle().setText(book.getTitle());
        holder.getScore().setText(book.getScore().toString());
        book.getTags().forEach((tag) -> holder.getTags().addTag(tag.getName()));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
