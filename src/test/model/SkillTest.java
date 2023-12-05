package model;

import model.exception.EmptyNameException;
import model.exception.IllegalLevelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Represents Skill test class
class SkillTest {

    private Skill testSkill;
    private Skill emptyNameSkill;
    private Skill illegalLevelSkill;
    private Skill emptyNameAndIllegalLevelSkill;

    @BeforeEach
    void runBefore() {
        try {
            testSkill = new Skill("testSkill", 0);
        } catch (EmptyNameException e) {
            fail("Unexpected EmptyNameException is thrown.");
        } catch (IllegalLevelException e) {
            fail("Unexpected IllegalLevelException is thrown.");
        }
    }

    @Test
    void constructorTest() {
        assertEquals("testSkill", testSkill.getInformationName());
        assertEquals(0, testSkill.getInformationLevel());
    }

    @Test
    void emptyNameTest() {
        try {
            emptyNameSkill = new Skill("", 0);
            fail("Expected EmptyNameException is not thrown.");
        } catch (EmptyNameException e) {
           // expected
        } catch (IllegalLevelException e) {
            fail("Unexpected IllegalLevelException is thrown.");
        }
    }

    @Test
    void illegalLevelUpperBoundTest() {
        try {
            illegalLevelSkill = new Skill("illegalLevelSkill", 11);
            fail("Expected illegalLevelSkill is not thrown.");
        } catch (EmptyNameException e) {
            fail("Unexpected EmptyNameException is thrown.");
        } catch (IllegalLevelException e) {
            // expected
        }
    }

    @Test
    void illegalLevelLowerBoundTest() {
        try {
            illegalLevelSkill = new Skill("illegalLevelSkill", -1);
            fail("Expected illegalLevelSkill is not thrown.");
        } catch (EmptyNameException e) {
            fail("Unexpected EmptyNameException is thrown.");
        } catch (IllegalLevelException e) {
            // expected
        }
    }

    @Test
    void emptyNameAndIllegalLevelTest() {
        try {
            emptyNameAndIllegalLevelSkill = new Skill("", -100);
            fail("Expected EmptyNameException is not thrown.");
        } catch (EmptyNameException e) {
            // expected
        } catch (IllegalLevelException e) {
            fail("Unexpected IllegalLevelException is thrown.");
        }
    }


    @Test
    void levelUpTest() {
        assertEquals(0, testSkill.getInformationLevel());
        testSkill.levelUp();
        assertEquals(1, testSkill.getInformationLevel());
        testSkill.levelUp();
        assertEquals(2, testSkill.getInformationLevel());
        testSkill.levelUp();
        testSkill.levelUp();
        testSkill.levelUp();
        testSkill.levelUp();
        testSkill.levelUp();
        testSkill.levelUp();
        testSkill.levelUp();
        testSkill.levelUp();
        assertEquals(10, testSkill.getInformationLevel());
        testSkill.levelUp();
        assertEquals(10, testSkill.getInformationLevel());
    }
}
