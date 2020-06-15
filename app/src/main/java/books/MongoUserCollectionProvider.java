package books;




import com.mongodb.ConnectionString;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObjectCodecProvider;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import org.bson.Document;
import org.bson.codecs.BsonValueCodecProvider;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.logging.Level;
import java.util.logging.Logger;

import books.model.Book;
import books.model.User;
import books.repository.codec.UserCodec;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class MongoUserCollectionProvider  {

    public static final String DATABASE = "bookOrganizer";

    public static final String CONNECTION_STRING ="mongodb://RWuser:3usJZpQtsLAfFWsW@bookorganizer-shard-00-00-hl9sd.mongodb.net:27017,bookorganizer-shard-00-01-hl9sd.mongodb.net:27017,bookorganizer-shard-00-02-hl9sd.mongodb.net:27017/bookOrganizer?ssl=true&replicaSet=BookOrganizer-shard-0&authSource=admin&w=majority";
    public static final String COLLECTION = "users";

    private static MongoClient mongoClient;

    private static MongoCollection userCollection;

    public static MongoCollection<User> getUsersCollection() {
        if(userCollection != null){
            return userCollection;
        }
        CodecRegistry codecRegistry = MongoClient.getDefaultCodecRegistry();
        Codec<Document> documentCodec = codecRegistry.get(Document.class);
        Codec<User> userCodec = new UserCodec(codecRegistry);
        codecRegistry = CodecRegistries.fromRegistries(
                MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromCodecs(
                        documentCodec,
                        userCodec

                ));
        MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();

        MongoClientURI uri = new MongoClientURI(CONNECTION_STRING,new MongoClientOptions.Builder(options));
        MongoClient mongo = new MongoClient(uri);
        MongoDatabase database = mongo.getDatabase(DATABASE);
        userCollection = database.getCollection(COLLECTION, User.class);

        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);

        return  userCollection;
    }

}
