package books.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Achievement {
    private ObjectId id;
    private String name;
    private int currentStage;
}
