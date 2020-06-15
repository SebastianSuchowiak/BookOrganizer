//package books.repository.codec;
//
//import com.mongodb.MongoClient;
//
//import org.bson.BsonReader;
//import org.bson.BsonString;
//import org.bson.BsonValue;
//import org.bson.BsonWriter;
//import org.bson.Document;
//import org.bson.codecs.Codec;
//import org.bson.codecs.CollectibleCodec;
//import org.bson.codecs.DecoderContext;
//import org.bson.codecs.EncoderContext;
//import org.bson.codecs.configuration.CodecRegistry;
//import org.bson.types.ObjectId;
//
//import books.model.Book;
//
//public class BookCodec implements CollectibleCodec<Book> {
//
//    private final CodecRegistry registry;
//    private final Codec<Document> documentCodec;
//    private BookConverter converter;
//
//    public BookCodec() {
//        this.registry = MongoClient.getDefaultCodecRegistry();
//        this.documentCodec = this.registry.get(Document.class);
//        this.converter = new BookConverter();
//    }
//
//    public BookCodec(CodecRegistry registry) {
//        this.registry = registry;
//        this.documentCodec = this.registry.get(Document.class);
//        this.converter = new BookConverter();
//    }
//
//    public BookCodec(Codec<Document> documentCodec) {
//        this.documentCodec = documentCodec;
//        this.registry = MongoClient.getDefaultCodecRegistry();
//        this.converter = new BookConverter();
//    }
//
//    @Override
//    public Book generateIdIfAbsentFromDocument(Book book) {
//
//        return book;
//    }
//
//    @Override
//    public boolean documentHasId(Book book) {
//        return false;
//    }
//
//    @Override
//    public BsonValue getDocumentId(Book book) {
//        if (!documentHasId(book)) {
//            throw new IllegalStateException("The document does not contain an _id");
//        }
//
//        return new BsonString(book.getId().toHexString());
//    }
//
//    @Override
//    public Book decode(BsonReader bsonReader, DecoderContext decoderContext) {
//        Document document = documentCodec.decode(bsonReader, decoderContext);
//        Book book = this.converter.convert(document);
//
//        return book;
//    }
//
//    @Override
//    public void encode(BsonWriter bsonWriter, Book book, EncoderContext encoderContext) {
//        Document document = this.converter.convert(book);
//        documentCodec.encode(bsonWriter, document, encoderContext);
//    }
//
//    @Override
//    public Class<Book> getEncoderClass() {
//        return Book.class;
//    }
//}
