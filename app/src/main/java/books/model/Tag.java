package books.model;


import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class Tag {
    private String name;
    private String color;

    public Tag(String name, String color) {
        this.name = name;
        this.color = color;
    }
}
