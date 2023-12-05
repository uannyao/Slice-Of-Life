package persistence;

import model.HouseholdMemberList;
import model.Relationship;
import model.Skill;
import model.exception.EmptyNameException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Represents test for JsonWriter
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            HouseholdMemberList householdMemberList = new HouseholdMemberList("MY CURRENT HOUSEHOLD");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        } catch (EmptyNameException e) {
            fail("Unexpected EmptyNameException is thrown.");
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            HouseholdMemberList memberList = new HouseholdMemberList("MY CURRENT HOUSEHOLD");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyHouseholdMemberList.json");
            checkWriter(writer, memberList);
            JsonReader reader = new JsonReader("./data/testWriterEmptyHouseholdMemberList.json");
            memberList = reader.read();
            assertEquals("MY CURRENT HOUSEHOLD", memberList.getName());
            assertEquals(0, memberList.getHouseholdMembers().size());
        } catch (Exception e) {
            fail("Unexpected Exception is thrown.");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            HouseholdMemberList memberList = new HouseholdMemberList("MY CURRENT HOUSEHOLD");
            memberList.addHouseholdMember(createNewTestMembers().get(0));
            memberList.addHouseholdMember(createNewTestMembers().get(1));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralHouseholdMemberList.json");
            checkWriter(writer, memberList);
            JsonReader reader = new JsonReader("./data/testWriterGeneralHouseholdMemberList.json");
            memberList = reader.read();
            assertEquals("MY CURRENT HOUSEHOLD", memberList.getName());
            assertEquals(2, memberList.getUnmodifiableHouseholdMembers().size());
            checkHouseholdMember("firstMember",19, createSkillList("fitness", 4),
                    createRelationshipList("boyfriend", 1), memberList.getHouseholdMembers().get(0));
            checkHouseholdMember("secondMember",20, createSkillList("painting", 6),
                    createRelationshipList("spouse", 7), memberList.getHouseholdMembers().get(1));
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }
}