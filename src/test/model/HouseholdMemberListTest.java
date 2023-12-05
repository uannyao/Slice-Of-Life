package model;

import model.exception.EmptyNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Represents HouseholdMemberList test class
class HouseholdMemberListTest {

    private HouseholdMemberList testList;
    private HouseholdMember testFirstMember;
    private HouseholdMember testSecondMember;
    private HouseholdMemberList emptyNameMemberList;

    @BeforeEach
    void runBefore() {
        testFirstMember = new HouseholdMember("UAnn", 19);
        testSecondMember = new HouseholdMember("testName", 19);
        try {
            testList = new HouseholdMemberList("MY CURRENT HOUSEHOLD");
        } catch (EmptyNameException e) {
            fail("Unexpected EmptyNameException is thrown");
        }
    }

    @Test
    void constructorTest() {
        assertEquals("MY CURRENT HOUSEHOLD",testList.getName());
        assertEquals(0, testList.getHouseholdMembers().size());
        assertEquals(0, testList.getListOfHouseholdMembersNames().size());
    }

    @Test
    void emptyNameMemberListTest() {
        try {
            emptyNameMemberList = new HouseholdMemberList("");
            fail("Expected EmptyNameException is not thrown");
        } catch (EmptyNameException e) {
            // expected
        }
    }

    @Test
    void addHouseholdMemberTest() {
        assertEquals(0, testList.getHouseholdMembers().size());
        assertEquals(0, testList.getListOfHouseholdMembersNames().size());
        testList.addHouseholdMember(testFirstMember);
        assertEquals(1, testList.getHouseholdMembers().size());
        assertEquals(1, testList.getListOfHouseholdMembersNames().size());
        assertEquals(testFirstMember, testList.getHouseholdMembers().get(0));
        assertEquals("UAnn", testList.getListOfHouseholdMembersNames().get(0));
        testList.addHouseholdMember(testSecondMember);
        assertEquals(2, testList.getHouseholdMembers().size());
        assertEquals(2, testList.getListOfHouseholdMembersNames().size());
        assertEquals(testSecondMember, testList.getHouseholdMembers().get(1));
        assertEquals("testName", testList.getListOfHouseholdMembersNames().get(1));
    }

    @Test
    void removeHouseholdMemberTest() {
        assertEquals(0, testList.getHouseholdMembers().size());
        assertEquals(0, testList.getListOfHouseholdMembersNames().size());
        testList.addHouseholdMember(testFirstMember);
        assertEquals(1, testList.getHouseholdMembers().size());
        assertEquals(1, testList.getListOfHouseholdMembersNames().size());
        assertEquals(testFirstMember, testList.getHouseholdMembers().get(0));
        assertEquals("UAnn", testList.getListOfHouseholdMembersNames().get(0));
        testList.addHouseholdMember(testSecondMember);
        assertEquals(2, testList.getHouseholdMembers().size());
        assertEquals(2, testList.getListOfHouseholdMembersNames().size());
        assertEquals(testSecondMember, testList.getHouseholdMembers().get(1));
        assertEquals("testName", testList.getListOfHouseholdMembersNames().get(1));
        testList.removeHouseholdMember(testFirstMember);
        assertEquals(1, testList.getHouseholdMembers().size());
        assertEquals(1, testList.getListOfHouseholdMembersNames().size());
        assertEquals(testSecondMember, testList.getHouseholdMembers().get(0));
        assertEquals("testName", testList.getListOfHouseholdMembersNames().get(0));
        testList.removeHouseholdMember(testSecondMember);
        assertEquals(0, testList.getHouseholdMembers().size());
        assertEquals(0, testList.getListOfHouseholdMembersNames().size());
    }

    @Test
    void nameExistsTest() {
        assertEquals(0, testList.getHouseholdMembers().size());
        assertEquals(0, testList.getListOfHouseholdMembersNames().size());
        assertFalse(testList.nameExists("UAnn"));
        testList.addHouseholdMember(testFirstMember);
        assertEquals(1, testList.getHouseholdMembers().size());
        assertEquals(1, testList.getListOfHouseholdMembersNames().size());
        assertTrue(testList.nameExists("UAnn"));
        assertFalse(testList.nameExists("testName"));
        testList.addHouseholdMember(testSecondMember);
        assertEquals(2, testList.getHouseholdMembers().size());
        assertEquals(2, testList.getListOfHouseholdMembersNames().size());
        assertTrue(testList.nameExists("testName"));
        assertFalse(testList.nameExists("Mom"));
    }
}
