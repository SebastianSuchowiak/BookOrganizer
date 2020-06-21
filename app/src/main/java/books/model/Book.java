package books.model;
import lombok.*;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Book implements Serializable {
    private String title;
    private List<String> authors;
    private double isbn;
    private Status status;
    private List<Tag> tags;
    private String imageUrl;
    private Integer score;
    private String review;

    public Book(String title, List<String> authors, long isbn, Status status, List<Tag> tags, String imageUrl, int score, String review) {
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.status = status;
        this.tags = tags;
        this.imageUrl = imageUrl;
        this.score = score;
        this.review = review;
    }

    public String getAuthorsRep() {
        return authors.stream().collect(Collectors.joining(", "));
    }
}
