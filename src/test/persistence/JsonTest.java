package persistence;

import model.HouseholdMember;
import model.HouseholdMemberList;
import model.Relationship;
import model.Skill;
import model.exception.EmptyNameException;
import model.exception.IllegalLevelException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Represents extra method JsonWriter and jsonReader test
public class JsonTest {

    // EFFECTS: checking personal information for HouseholdMember
    protected void checkHouseholdMember(String name, int age, List<Skill> skills,
                                        List<Relationship> relationships, HouseholdMember member) {
        assertEquals(name, member.getMemberName());
        assertEquals(age, member.getMemberAge());
        assertEquals(skills.get(0).getInformationName(), member.getSkills().get(0).getInformationName());
        assertEquals(relationships.get(0).getInformationName(), member.getRelationships().get(0).getInformationName());
        assertEquals(skills.get(0).getInformationLevel(), member.getSkills().get(0).getInformationLevel());
        assertEquals(relationships.get(0).getInformationLevel(), member.getRelationships().get(0).getInformationLevel());
    }

    // EFFECTS: creating test new members and returns members
    protected List<HouseholdMember> createNewTestMembers() {
        List<HouseholdMember> testList = new ArrayList<>();
        HouseholdMember firstMember = new HouseholdMember("firstMember", 19);
        HouseholdMember secondMember = new HouseholdMember("secondMember", 20);
        try {
            firstMember.getSkills().add(new Skill("fitness",4));
            secondMember.getSkills().add(new Skill("painting",6));
            firstMember.getRelationships().add(new Relationship("boyfriend",1));
            secondMember.getRelationships().add(new Relationship("spouse",7));
        } catch (EmptyNameException e) {
            e.getMessage();
        } catch (IllegalLevelException e) {
            e.getMessage();
        }
        testList.add(firstMember);
        testList.add(secondMember);
        return testList;
    }

    // EFFECTS: open and write memberList, then close the writer
    protected void checkWriter(JsonWriter writer, HouseholdMemberList memberList) {
        try {
            writer.open();
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        writer.write(memberList);
        writer.close();
    }

    // EFFECTS: create a new skill list, with given name and level
    protected List<Skill> createSkillList (String name, int level) {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill(name, level));
        return skills;
    }

    // EFFECTS: create a new relationship list, with given name and level
    protected List<Relationship> createRelationshipList (String name, int level) {
        List<Relationship> relationships = new ArrayList<>();
        relationships.add(new Relationship(name, level));
        return relationships;
    }

}
