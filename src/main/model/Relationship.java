package model;

import model.exception.EmptyNameException;
import model.exception.IllegalLevelException;
import org.json.JSONObject;
import persistence.Writable;

// Represents a relationship with relationship name and level, extends the PersonalInformation abstract class
public class Relationship extends PersonalInformation implements Writable {

    // EFFECTS: construct a relationship with relationshipName, and relationshipLevel
    public Relationship(String relationshipName, int relationshipLevel) throws IllegalLevelException,
            EmptyNameException {
        super(relationshipName, relationshipLevel);
    }
}
