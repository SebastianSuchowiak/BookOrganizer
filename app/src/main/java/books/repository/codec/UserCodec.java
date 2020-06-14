package books.repository.codec;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import books.model.Book;
import books.model.Theme;
import books.model.User;

public class UserCodec implements CollectibleCodec<User> {

    private final CodecRegistry registry;
    private final Codec<Document> documentCodec;
    private BookConverter bookConverter;


    public UserCodec() {
        this.registry = MongoClient.getDefaultCodecRegistry();
        this.documentCodec = this.registry.get(Document.class);
        this.bookConverter = new BookConverter();
    }


    public UserCodec(CodecRegistry registry) {
        this.registry = registry;
        this.documentCodec = this.registry.get(Document.class);
        this.bookConverter = new BookConverter();
    }

    public UserCodec(Codec<Document> documentCodec) {
        this.registry = MongoClient.getDefaultCodecRegistry();
        this.bookConverter = new BookConverter();
        this.documentCodec = documentCodec;
    }

    //private final ItemConverter itemConverter;

    @Override
    public User generateIdIfAbsentFromDocument(User user) {
        if (!documentHasId(user)) {
            user.setId(new ObjectId());
        }

        return user;
    }

    @Override
    public boolean documentHasId(User user) {
        return (user.getId() != null);
    }

    @Override
    public BsonValue getDocumentId(User user) {
        if (!documentHasId(user)) {
            throw new IllegalStateException("The document does not contain an _id");
        }

        return new BsonString(user.getId().toHexString());
    }

    @Override
    public User decode(BsonReader bsonReader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(bsonReader, decoderContext);

        User user = new User();

        user.setId(document.getObjectId("_id"));
        user.setName(document.getString("name"));
        user.setHashedPassword(document.getString("hashedPassword"));
        user.setTheme(Theme.valueOf(document.getString("theme")));
        ArrayList<Document> docArr = (ArrayList) document.get("books");
        for (Document doc : docArr) {
            Book book = this.bookConverter.convert(doc);
            user.getBooks().add(book);
        }

        return user;
    }

    @Override
    public void encode(BsonWriter bsonWriter, User user, EncoderContext encoderContext) {
        Document document = new Document();
        document.put("_id", user.getId());
        document.put("hashedPassword", user.getHashedPassword());
        document.put("theme", user.getTheme().toString());
        document.put("name", user.getName());
        List<Document> books = new ArrayList<>();
        for (Book b : user.getBooks()){
            books.add(bookConverter.convert(b));
        }
        document.put("books", books);
        document.put("achievements", user.getAchievements());

        documentCodec.encode(bsonWriter, document, encoderContext);
    }

    @Override
    public Class<User> getEncoderClass() {
        return User.class;
    }
}
