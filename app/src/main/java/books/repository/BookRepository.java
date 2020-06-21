package books.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import books.model.Book;


public class BookRepository {

    private static ArrayList<Book> userBooks;

    private BookRepository() {

    }

    public static ArrayList<Book> getUserBooks(){
        return userBooks;
    }

    public static void setUserBooks(ArrayList<Book> userBooks) {
        BookRepository.userBooks = userBooks;
    }
}
