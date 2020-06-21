package books.model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class User implements Serializable {
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
