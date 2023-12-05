package model;

import model.exception.EmptyNameException;
import model.exception.IllegalLevelException;
import org.json.JSONObject;
import persistence.Writable;

// Represents a skill having name and level, extends PersonalInformation abstract class
public class Skill extends PersonalInformation implements Writable {

    // EFFECTS: construct a skill with skillName, and skillLevel
    public Skill(String skillName, int skillLevel) throws IllegalLevelException, EmptyNameException {
        super(skillName, skillLevel);
    }
}
