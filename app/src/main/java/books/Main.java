package books;

import agh.wtm.books.model.Book;
import agh.wtm.books.model.User;
import agh.wtm.books.repository.UserRepository;

import java.util.Collections;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Main {

    public static void main(String[] args) throws Exception {
        UserRepository.registerUser("U", "P");
        User user = UserRepository.getUser("U", "P");

        user.getBooks().add(Book.builder()
                .authors(Collections.singletonList("Stephen"))
                .title("Title")
                .build());

        System.out.println(user.getBooks().size());

        UserRepository.updateUser(user);
    }
}
