package model;

import model.exception.EmptyNameException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//Represents a list of HouseholdMember, implements Writable interface
public class HouseholdMemberList implements Writable {
    private List<HouseholdMember> householdMembers;        // a list of Household member
    private List<String> listOfHouseholdMembersNames;      // a list of Household member's name
    private String name;                                   // the household's name


    //EFFECTS : construct a list of household member with a name, householdMembers and listOfHouseholdMembersNames
    //          lists are initially empty, if name is empty throw EmptyNameException
    public HouseholdMemberList(String name) throws EmptyNameException {
        if (name.isEmpty()) {
            throw new EmptyNameException();
        }
        this.name = name;
        householdMembers = new ArrayList<>();
        listOfHouseholdMembersNames = new ArrayList<>();
    }

    //MODIFIES : this
    //EFFECTS : add the member to the householdMembers and member's name to listOfHouseholdMembersNames
    public void addHouseholdMember(HouseholdMember member) {
        householdMembers.add(member);
        listOfHouseholdMembersNames.add(member.getMemberName());
        EventLog.getInstance().logEvent(new Event("'" + member.getMemberName() + "'" + " added to current Household."));
    }


    //REQUIRES : the householdMembers and listOfHouseholdMembersNames is nonempty
    //MODIFIES : this
    //EFFECTS : remove the member from the householdMembers and remove member's name from listOfHouseholdMembersNames
    public void removeHouseholdMember(HouseholdMember member) {
        householdMembers.remove(member);
        listOfHouseholdMembersNames.remove(member.getMemberName());
        EventLog.getInstance().logEvent(new Event("'" + member.getMemberName() + "'"
                + " removed from current Household."));
    }


    //EFFECTS : return true if the name already exist in the listOfHouseholdMembersNames
    public boolean nameExists(String name) {
        for (String householdMember : listOfHouseholdMembersNames) {
            if (householdMember.equals(name)) {
                return true;
            }
        }
        return false;
    }


    //getters
    public List<HouseholdMember> getHouseholdMembers() {
        return householdMembers;
    }

    // EFFECTS: returns an unmodifiable list of HouseholdMembers in this HouseholdMembersList
    public List<HouseholdMember> getUnmodifiableHouseholdMembers() {
        return Collections.unmodifiableList(householdMembers);
    }

    public List<String> getListOfHouseholdMembersNames() {
        return listOfHouseholdMembersNames;
    }

    public String getName() {
        return name;
    }

    // EFFECTS: returns all householdMember in the list as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("save", name);
        json.put("Household Members", householdMembersToJson());
        return json;
    }

    // EFFECTS: returns all householdMember as a JSON array
    public JSONArray householdMembersToJson() {
        JSONArray jsonList = new JSONArray();
        for (HouseholdMember member : householdMembers) {
            jsonList.put(member.toJson());
        }
        return jsonList;
    }
}
