package model;

import model.exception.EmptyNameException;
import model.exception.IllegalLevelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Represents Relationship test class
class RelationshipTest {

    private Relationship testRelationship;
    private Relationship emptyNameRelationship;
    private Relationship illegalLevelRelationship;
    private Relationship emptyNameAndIllegalLevelRelationship;

    @BeforeEach
    void runBefore() {
        try {
            testRelationship = new Relationship("testRelationship", 0);
        } catch (EmptyNameException e) {
            fail("Unexpected EmptyNameException is thrown.");
        } catch (IllegalLevelException e) {
            fail("Unexpected IllegalLevelException is thrown.");
        }
    }

    @Test
    void constructorTest() {
        assertEquals("testRelationship", testRelationship.getInformationName());
        assertEquals(0, testRelationship.getInformationLevel());
    }


    @Test
    void emptyNameRelationshipTest() {
        try {
            emptyNameRelationship = new Relationship("", 0);
            fail("Expected EmptyNameException is not thrown.");
        } catch (EmptyNameException e) {
           // expected
        } catch (IllegalLevelException e) {
            fail("Unexpected IllegalLevelException is thrown.");
        }
    }

    @Test
    void illegalLevelRelationshipUpperBoundTest() {
        try {
            illegalLevelRelationship = new Relationship("illegalLevelRelationship", 11);
            fail("Expected IllegalLevelException is not thrown.");
        } catch (EmptyNameException e) {
            fail("Unexpected EmptyNameException is thrown.");
        } catch (IllegalLevelException e) {
            // expected
        }
    }

    @Test
    void illegalLevelRelationshipLowerBoundTest() {
        try {
            illegalLevelRelationship = new Relationship("illegalLevelRelationship", -1);
            fail("Expected IllegalLevelException is not thrown.");
        } catch (EmptyNameException e) {
            fail("Unexpected EmptyNameException is thrown.");
        } catch (IllegalLevelException e) {
            // expected
        }
    }

    @Test
    void emptyNameAndIllegalLevelRelationshipTest() {
        try {
            emptyNameRelationship = new Relationship("", -1);
            fail("Expected EmptyNameException is not thrown.");
        } catch (EmptyNameException e) {
            // expected
        } catch (IllegalLevelException e) {
            fail("Unexpected IllegalLevelException is thrown.");
        }
    }

    @Test
    void levelUpTest() {
        assertEquals(0, testRelationship.getInformationLevel());
        testRelationship.levelUp();
        assertEquals(1, testRelationship.getInformationLevel());
        testRelationship.levelUp();
        assertEquals(2, testRelationship.getInformationLevel());
        testRelationship.levelUp();
        testRelationship.levelUp();
        testRelationship.levelUp();
        testRelationship.levelUp();
        testRelationship.levelUp();
        testRelationship.levelUp();
        testRelationship.levelUp();
        testRelationship.levelUp();
        assertEquals(10, testRelationship.getInformationLevel());
        testRelationship.levelUp();
        assertEquals(10, testRelationship.getInformationLevel());
    }
}
