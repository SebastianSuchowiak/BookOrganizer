package agh.wta.suchowiak.bookorganizer;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import books.model.Book;
import books.model.Tag;
import co.lujun.androidtagview.TagContainerLayout;
import lombok.Getter;

public class BookViewAdapter extends RecyclerView.Adapter<BookViewAdapter.BookViewHolder> {

    private List<Book> books;

    public BookViewAdapter(List<Book> books) {
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

        List<int[]> colors = book.getTags()
                .stream()
                .map((tag) -> {
                    int mainColor = Color.parseColor(tag.getColor());
                    return new int[]{mainColor, mainColor, Color.BLACK, mainColor};
                })
                .collect(Collectors.toList());
        List<String> tags = book.getTags().stream().map(Tag::getName).collect(Collectors.toList());
        holder.getTags().setTags(tags, colors);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

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
}
