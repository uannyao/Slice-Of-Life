package persistence;

import model.HouseholdMemberList;
import model.Relationship;
import model.Skill;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Represents the tests for JsonReader class
class JsonReaderTest extends JsonTest {

    @Test
        // Method taken from JsonReaderTest Class in
        // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            HouseholdMemberList memberList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyHouseholdMemberList.json");
        try {
            HouseholdMemberList memberList = reader.read();
            assertEquals("MY CURRENT HOUSEHOLD", memberList.getName());
            assertEquals(0, memberList.getUnmodifiableHouseholdMembers().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralHouseholdMemberList.json");
        try {
            HouseholdMemberList memberList = reader.read();
            assertEquals("MY CURRENT HOUSEHOLD", memberList.getName());
            assertEquals(2, memberList.getUnmodifiableHouseholdMembers().size());
            checkHouseholdMember("j",7,createSkillList("gaming", 10),
                    createRelationshipList("friendship", 10), memberList.getHouseholdMembers().get(0));
            checkHouseholdMember("y",15, createSkillList("dancing", 10),
                    createRelationshipList("boyfriend", 10), memberList.getHouseholdMembers().get(1));
        } catch (Exception e) {
            fail("Unexpected exception is thrown.");
        }
    }
}