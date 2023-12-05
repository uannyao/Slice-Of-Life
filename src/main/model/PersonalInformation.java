package model;

import model.exception.EmptyNameException;
import model.exception.IllegalLevelException;
import org.json.JSONObject;
import persistence.Writable;

// Represents an abstract class for the householdMember's personal information
public abstract class PersonalInformation implements Writable {
    public static final int MAX_LEVEL = 10;
    private String name;      // the name of the information
    private int level;        // the level of the information

    // EFFECTS: construct personal information with name and level
    //          name of the information is set to infoName;
    //          level of the information is set to infoLevel. if infoName is empty throw EmptyNameException,
    //          if infoLevel is (infoLevel > MAX_LEVEL || infoLevel < 0) throw IllegalLevelException
    public PersonalInformation(String infoName, int infoLevel) throws EmptyNameException, IllegalLevelException {
        if (infoName.isEmpty()) {
            throw new EmptyNameException();
        } else {
            if (infoLevel > MAX_LEVEL || infoLevel < 0) {
                throw new IllegalLevelException(infoLevel);
            }
        }
        this.name = infoName;
        this.level = infoLevel;
    }

    //MODIFIES : this
    //EFFECTS : level up the information by one level if level is lower than MAX_LEVEL
    public void levelUp() {
        if (level < MAX_LEVEL) {
            level++;
        }
    }

    // EFFECTS: return the personal information including name and level as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("level", level);
        return json;
    }

    // getters
    public String getInformationName() {
        return name;
    }

    public int getInformationLevel() {
        return level;
    }
}
