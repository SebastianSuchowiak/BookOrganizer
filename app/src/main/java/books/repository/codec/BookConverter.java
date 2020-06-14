package books.repository.codec;

import com.mongodb.BasicDBList;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import books.model.Book;
import books.model.Status;

public class BookConverter {

    public Document convert(Book book) {
        Document document = new Document();
        document.put("_id", book.getId());
        document.put("title", book.getTitle());
        document.put("isbn", book.getIsbn());
        document.put("authors", book.getAuthors());
        document.put("status", book.getStatus().toString());
        document.put("imageUrl", book.getImageUrl());
        document.put("score", book.getScore());
        document.put("review", book.getReview());
        document.put("tags",book.getTags());
        return document;
    }

    public Book convert(Document document) {
        Book book = new Book();
        book.setId(document.getObjectId("_id"));
        book.setTitle(document.getString("title"));
        book.setIsbn(document.getLong("isbn"));
        BasicDBList authors = (BasicDBList) document.get("authors");
        List<String> bookAuthors = new ArrayList<String>();
        for (Object o : authors) {
            bookAuthors.add((String) o);
        }
        book.setAuthors(bookAuthors);
        book.setStatus(Status.valueOf(document.getString("status")));
        book.setImageUrl(document.getString("imageUrl"));
        book.setScore(document.getInteger("score"));
        book.setReview(document.getString("review"));

        return book;
    }
}
