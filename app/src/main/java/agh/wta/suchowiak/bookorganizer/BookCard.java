package agh.wta.suchowiak.bookorganizer;

import books.model.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCard {
    private Book book;

    public BookCard(Book book) {
        this.book = book;
    }
}
