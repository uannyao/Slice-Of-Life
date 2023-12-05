package persistence;

import org.json.JSONObject;

// Represents an interface turns this as JSON object
public interface Writable {
    // EFFECTS: returns this as JSON object
    // Method taken from Writable interface in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    JSONObject toJson();
}
