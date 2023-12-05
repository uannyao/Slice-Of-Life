package model.exception;

// Represents empty name exception
public class EmptyNameException extends RuntimeException {

    // EFFECTS: call the super class with given comments
    public EmptyNameException() {
        super("Your name is empty, please try a name with at least one character.");
    }
}
