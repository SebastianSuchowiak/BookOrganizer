package books.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import books.model.Book;

@Singleton
public class BookRepository {

    private static ArrayList<Book> userBooks;

    public static ArrayList<Book> getUserBooks(){
        return userBooks;
    }

    public static void setUserBooks(ArrayList<Book> userBooks) {
        BookRepository.userBooks = userBooks;
    }
}
