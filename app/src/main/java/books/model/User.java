package books.model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class User {
    private ObjectId id;
    private String hashedPassword;
    private ArrayList<Book> books;
    private List<Achievement> achievements;
    private Theme theme;
    private String name;

    public ArrayList<Book> getBooks(){
        return books;
    }
}
