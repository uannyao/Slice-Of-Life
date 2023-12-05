package model.exception;

import model.PersonalInformation;

// Represents illegal level exceptions
public class IllegalLevelException extends RuntimeException {

    // EFFECTS: call the super class with given level and comments
    public IllegalLevelException(int level) {
        super(level + " is an illegal level, please try a non-negative level and cannot exceed "
                + PersonalInformation.MAX_LEVEL + ".");
    }
}
