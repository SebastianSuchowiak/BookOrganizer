package books.repository;

import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.util.ArrayList;

import books.MongoUserCollectionProvider;
import books.Password;
import books.model.Achievement;
import books.model.Book;
import books.model.Theme;
import books.model.User;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class UserRepository {

    public static boolean userExists(String name) {
        new MongoUserCollectionProvider();
        MongoCollection<User> collection = MongoUserCollectionProvider.getUsersCollection();
        return collection.find(new Document("name", name)).first() != null;
    }

    public static boolean login(String name, String password) throws Exception {
        new MongoUserCollectionProvider();
        MongoCollection<User> collection = MongoUserCollectionProvider.getUsersCollection();

        if (!userExists(name)) {
            throw new IllegalArgumentException("User with this name does not exists");
        }

        User user = collection.find(new Document("name", name)).first();
        if (!Password.check(password, user.getHashedPassword())) {
            throw new IllegalArgumentException("Password is not valid");
        }
        return true;
    }

    public static void registerUser(String name, String password) throws Exception {
        new MongoUserCollectionProvider();
        MongoCollection collection = MongoUserCollectionProvider.getUsersCollection();

        if (userExists(name)) {
            throw new IllegalArgumentException("User with this name already exists");
        }

        User user = new User();
        user.setName(name);
        user.setHashedPassword(Password.getSaltedHash(password));
        user.setTheme(Theme.LIGHT);
        user.setBooks(new ArrayList<Book>());
        user.setAchievements(new ArrayList<Achievement>());
        /*Book b = new Book();
        b.setTitle("aaa");
        b.setAuthors(new ArrayList<String>());
        b.setIsbn(11);
        b.setStatus(HAVE_TO_READ);
        b.setImageUrl("d3");
        b.setScore(3);
        b.setReview("srars");
        b.setTags(new ArrayList<Tag>());
        user.getBooks().add(b);*/
        collection.insertOne(user);
    }

    public static User getUser(String userName, String password) throws Exception {
        new MongoUserCollectionProvider();
        MongoCollection<User> collection = MongoUserCollectionProvider.getUsersCollection();

        if (!userExists(userName)) {
            throw new IllegalArgumentException("User with this name does not exists");
        }

        User user = collection.find(new Document("name", userName)).first();
        if (!Password.check(password, user.getHashedPassword())) {
            throw new IllegalArgumentException("Password is not valid");
        }

        return user;
    }

    public static void updateUser(User user) {
        new MongoUserCollectionProvider();
        MongoCollection<User> collection = MongoUserCollectionProvider.getUsersCollection();
        collection.replaceOne(and(eq("name", user.getName()), eq("_id", user.getId())), user);
    }
}
