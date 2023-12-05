package persistence;

import model.HouseholdMember;
import model.HouseholdMemberList;
import model.Relationship;
import model.Skill;
import model.exception.EmptyNameException;
import model.exception.IllegalLevelException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Represents a reader that reads householdmemberlist from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads householdmemberlist from file and returns it;
    // throws IOException if an error occurs reading data from file
    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public HouseholdMemberList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHouseholdMemberList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: memberList
    // EFFECTS: parses householdmemberlist from JSON object and returns it
    private HouseholdMemberList parseHouseholdMemberList(JSONObject jsonObject) {
        String name = jsonObject.getString("save");
        HouseholdMemberList memberList = new HouseholdMemberList(name);
        addHouseholdMembers(memberList, jsonObject);
        return memberList;
    }

    // MODIFIES: memberList
    // EFFECTS: parses householdmembers from JSON object and adds them to householdmemberlist
    private void addHouseholdMembers(HouseholdMemberList memberList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Household Members");
        for (Object json : jsonArray) {
            JSONObject nextMember = (JSONObject) json;
            addMember(memberList, nextMember);
        }
    }

    // MODIFIES: memberList
    // EFFECTS: parses householdmember from JSON object and adds them to householdmemberlist
    private void addMember(HouseholdMemberList memberList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        HouseholdMember member = new HouseholdMember(name, age);
        addSkills(member,jsonObject);
        addRelationships(member, jsonObject);
        memberList.addHouseholdMember(member);
    }

    // MODIFIES: member
    // EFFECTS: parse skills from JSON objects and adds them to member's skill list
    private void addSkills(HouseholdMember member, JSONObject jsonObject) {
        JSONArray jsonArraySkill = jsonObject.getJSONArray("skills");
        for (Object json : jsonArraySkill) {
            JSONObject skill = (JSONObject) json;
            String skillName = skill.getString("name");
            int skillLevel = skill.getInt("level");
            Skill newSkill = new Skill(skillName, skillLevel);
            member.getSkills().add(newSkill);
            member.getSkillsName().add(skillName);
        }
    }

    // MODIFIES: member
    // EFFECTS: parse relationships from JSON objects and adds them to member's relationship list
    private void addRelationships(HouseholdMember member, JSONObject jsonObject) {
        JSONArray jsonArrayRelationship = jsonObject.getJSONArray("relationships");
        for (Object json : jsonArrayRelationship) {
            JSONObject relationship = (JSONObject) json;
            String relationshipName = relationship.getString("name");
            int relationshipLevel = relationship.getInt("level");
            Relationship newRelationship = new Relationship(relationshipName, relationshipLevel);
            member.getRelationships().add(newRelationship);
            member.getRelationshipsName().add(relationshipName);
        }
    }


}
