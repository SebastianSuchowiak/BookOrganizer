package books.model;


import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class Tag {
    private ObjectId id;
    private String name;
    private String color;
}
