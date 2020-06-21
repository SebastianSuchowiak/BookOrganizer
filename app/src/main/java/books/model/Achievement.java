package books.model;

import lombok.Data;

import org.bson.types.ObjectId;

import java.io.Serializable;

@Data
public class Achievement implements Serializable {
    private ObjectId id;
    private String name;
    private int currentStage;
}
