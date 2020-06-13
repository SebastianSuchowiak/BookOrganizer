package books.model;

import agh.wtm.books.model.Achievement;
import agh.wtm.books.model.Book;
import agh.wtm.books.model.Theme;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class User {
    private ObjectId id;
    private String hashedPassword;
    private List<Book> books;
    private List<Achievement> achievements;
    private Theme theme;
    private String name;
}
