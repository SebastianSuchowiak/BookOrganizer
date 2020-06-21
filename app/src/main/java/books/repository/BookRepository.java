package books.repository;

import android.util.Log;

import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import books.MongoUserCollectionProvider;
import books.model.Book;
import books.model.User;


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

    public static void insertBook(){
       // MongoCollection collection = MongoUserCollectionProvider.getUsersCollection();
        User user = UserRepository.user;
        user.setBooks(getUserBooks());
        UserRepository.updateUser(user);
    }
}
