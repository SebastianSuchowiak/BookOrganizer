package books.model;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@Builder
public class Book {
    private ObjectId id;
    private String title;
    private List<String> authors;
    private long isbn;
    private Status status;
    private List<Tag> tags;
    private String imageUrl;
    private int score;
    private String review;
}
