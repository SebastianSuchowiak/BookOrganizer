package books.model;


import org.bson.types.ObjectId;

import java.io.Serializable;

import lombok.Data;

@Data
public class Tag implements Serializable {
    private String name;
    private String color;

    public Tag(String name, String color) {
        this.name = name;
        this.color = color;
    }
}
