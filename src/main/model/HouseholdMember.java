package model;

import model.exception.EmptyNameException;
import model.exception.IllegalLevelException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


//Represents a household member with name, age, and list of skills and relationships, implements Writable interface
public class HouseholdMember implements Writable {

    private int age;                              // Household member's age
    private String name;                          // Household member's name
    private List<Relationship> relationships;     // Household member's list of relationships
    private List<Skill> skills;                   // Household member's list of skills
    private List<String> listOfRelationshipsName; // Household member's list of relationships' names
    private List<String> listOfSkillsName;        // Household member's list of skills' names


    // REQUIRES: memberName must not be empty, memberAge needs to be non-negative
    // EFFECTS: name on the member is set to memberName; member's age is set
    //          to memberAge; relationships and skills are initially empty;
    //          listOfRelationshipsName and listOfSkillsName are initially empty
    public HouseholdMember(String memberName, int memberAge) {
        this.age = memberAge;
        this.name = memberName;
        this.relationships = new ArrayList<>();
        this.skills = new ArrayList<>();
        this.listOfRelationshipsName = new ArrayList<>();
        this.listOfSkillsName = new ArrayList<>();
    }


    //MODIFIES : this
    //EFFECTS : check all skill activity type or grow by one year
    public void goToSkillActivity(ActivityType activityType) {
        switch (activityType) {
            case GO_TO_THE_GYM:
                levelUpOrAddSkill("fitness");
                break;
            case PAINT_A_DRAWING:
                levelUpOrAddSkill("painting");
                break;
            case DANCE_TO_THE_MUSIC:
                levelUpOrAddSkill("dancing");
                break;
            case PLAY_THE_VIDEO_GAME:
                levelUpOrAddSkill("gaming");
                break;
            case HAVE_A_BIRTHDAY_PARTY:
                age++;
                break;
        }
    }


    //MODIFIES : this
    //EFFECTS : check the relationship activity type
    public void goToRelationshipActivity(ActivityType activityType) {
        switch (activityType) {
            case GO_ON_A_DATE:
                levelUpOrAddRelationship("boyfriend");
                break;
            case GO_TO_A_CLUB_MEETING:
                levelUpOrAddRelationship("friendship");
                break;
            case GO_GET_MARRIED:
                levelUpOrAddRelationship("spouse");
                break;
        }
    }


    //MODIFIES : this
    //EFFECTS : level up the skill if contain the skill, if not add the new skill with level 0
    public void levelUpOrAddSkill(String skillName) {
        if (containsSkill(skillName)) {
            Skill skill = getSkill(skillName);
            skill.levelUp();
        } else {
            this.skills.add(new Skill(skillName, 0));
            this.listOfSkillsName.add(skillName);
            EventLog.getInstance().logEvent(new Event("'" + skillName + "'" + " added to " + name
                    + "'s skills."));
        }
    }


    //MODIFIES : this
    //EFFECTS : level up the relationship if contain the relationship, if not add the new relationship with level 0
    public void levelUpOrAddRelationship(String relationshipName) {
        if (containsRelationship(relationshipName)) {
            Relationship relationship = getRelationship(relationshipName);
            relationship.levelUp();
        } else {
            this.relationships.add(new Relationship(relationshipName, 0));
            this.listOfRelationshipsName.add(relationshipName);
            EventLog.getInstance().logEvent(new Event("'" + relationshipName + "'" + " added to " + name
                    + "'s relationships."));
        }
    }


    //EFFECTS : check if skills list contain the skill of the given name
    public boolean containsSkill(String skillName) {
        for (Skill skill : skills) {
            if (skill.getInformationName().equals(skillName)) {
                return true;
            }
        }
        return false;
    }


    //EFFECTS : check if relationships list contain the relationship of the given name
    public boolean containsRelationship(String relationshipName) {
        for (Relationship relationship : relationships) {
            if (relationship.getInformationName().equals(relationshipName)) {
                return true;
            }
        }
        return false;
    }


    // getter
    //EFFECTS : return the skill of the given name if exists in skills, otherwise return null
    public Skill getSkill(String skillName) {
        for (Skill skill : skills) {
            if (skill.getInformationName().equals(skillName)) {
                return skill;
            }
        }
        return null;
    }


    //EFFECTS : return the relationship of the given name if exists in relationships, otherwise return null
    public Relationship getRelationship(String relationshipName) {
        for (Relationship relationship : relationships) {
            if (relationship.getInformationName().equals(relationshipName)) {
                return relationship;
            }
        }
        return null;
    }

    public int getMemberAge() {
        return age;
    }

    public String getMemberName() {
        return name;
    }


    //EFFECTS : return a list of relationships' name of all the relationships
    public List<String> getRelationshipsName() {
        return listOfRelationshipsName;
    }

    //EFFECTS : return a list of skills' name of all the skills
    public List<String> getSkillsName() {
        return listOfSkillsName;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    // EFFECTS: returns HouseholdMember and his/hers/theirs name, age, and personal information as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("age", age);
        json.put("name", name);
        JSONArray skillList = new JSONArray();
        for (Skill skill : skills) {
            skillList.put(skill.toJson());
        }
        json.put("skills", skillList);
        JSONArray relationshipList = new JSONArray();
        for (Relationship relationship : relationships) {
            relationshipList.put(relationship.toJson());
        }
        json.put("relationships", relationshipList);
        return json;
    }
}
