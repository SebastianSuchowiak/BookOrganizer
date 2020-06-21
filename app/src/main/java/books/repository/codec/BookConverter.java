package books.repository.codec;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import books.model.Book;
import books.model.Status;
import books.model.Tag;

public class BookConverter {

    public Document convert(Book book) {
        Document document = new Document();
        document.put("title", book.getTitle());
        document.put("isbn", book.getIsbn());
        document.put("authors", book.getAuthors());
        document.put("status", book.getStatus().toString());
        document.put("imageUrl", book.getImageUrl());
        document.put("score", book.getScore());
        document.put("review", book.getReview());
        List<Document> tags = new ArrayList<>();
        for (Tag tag: book.getTags()) {
            tags.add(new Document().append("color", tag.getColor()).append("name", tag.getName()));
        }
        document.put("tags", tags);
        return document;
    }

    public Book convert(Document document) {
        Book book = new Book();
        book.setTitle(document.getString("title"));
        book.setIsbn(document.getDouble("isbn"));
        book.setAuthors((List<String>) document.get("authors"));
        book.setStatus(Status.valueOf(document.getString("status")));
        book.setImageUrl(document.getString("imageUrl"));
        book.setScore(document.getInteger("score").intValue());
        book.setReview(document.getString("review"));
        List<Tag> tags = new ArrayList<>();
        for (Document tagDocument: (List<Document>) document.get("tags")) {
            tags.add(new Tag(tagDocument.getString("name"), tagDocument.getString("color")));
        }
        book.setTags(tags);

        return book;
    }
}
