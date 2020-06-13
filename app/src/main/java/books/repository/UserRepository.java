package books.repository;

import agh.wtm.books.MongoUserCollectionProvider;
import agh.wtm.books.Password;
import agh.wtm.books.model.Theme;
import agh.wtm.books.model.User;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;

public class UserRepository {

    public static boolean userExists(String name) {
        MongoCollection<User> collection = MongoUserCollectionProvider.getUsersCollection();
        return collection.find(new Document("name", name)).first() != null;
    }

    public static void registerUser(String name, String password) throws Exception {
        MongoCollection<User> collection = MongoUserCollectionProvider.getUsersCollection();

        if (userExists(name)) {
            throw new IllegalArgumentException("User with this name already exists");
        }

        User user = new User();
        user.setName(name);
        user.setHashedPassword(Password.getSaltedHash(password));
        user.setTheme(Theme.LIGHT);
        user.setBooks(new ArrayList<>());
        user.setAchievements(new ArrayList<>());

        collection.insertOne(user);
    }

    public static User getUser(String userName, String password) throws Exception {
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
        MongoCollection<User> collection = MongoUserCollectionProvider.getUsersCollection();
        collection.replaceOne(and(eq("name", user.getName()), eq("_id", user.getId())), user);
    }
}
