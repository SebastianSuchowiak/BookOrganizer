package books;

import agh.wtm.books.model.User;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import javax.print.Doc;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoUserCollectionProvider {

    public static final String DATABASE = "bookOrganizer";
    public static final String CONNECTION_STRING = "mongodb+srv://RWuser:3usJZpQtsLAfFWsW@bookorganizer-hl9sd.mongodb.net/bookOrganizer?retryWrites=true&w=majority";
    public static final String COLLECTION = "users";

    private static MongoClient mongoClient;

    private MongoUserCollectionProvider() {

    }

    public static MongoCollection<User> getUsersCollection() {
        if (mongoClient == null) {
            MongoClientURI uri = new MongoClientURI(CONNECTION_STRING);
            mongoClient = new MongoClient(uri);
        }

        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoCollection<User> collection = database.getCollection(COLLECTION, User.class).withCodecRegistry(pojoCodecRegistry);

        return collection;
    }

}
