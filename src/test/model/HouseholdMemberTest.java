package model;

import model.exception.EmptyNameException;
import model.exception.IllegalLevelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Represents HouseholdMember test class
class HouseholdMemberTest {
    private HouseholdMember testMember;

    @BeforeEach
    void runBefore() {
        testMember = new HouseholdMember("UAnn", 19);
    }

    @Test
    void constructorTest() {
        assertEquals("UAnn", testMember.getMemberName());
        assertEquals(19, testMember.getMemberAge());
        assertEquals(0, testMember.getRelationships().size());
        assertEquals(0, testMember.getSkills().size());
        assertEquals(0, testMember.getRelationshipsName().size());
        assertEquals(0, testMember.getSkillsName().size());
    }

    @Test
    void goToSkillActivityTest() {
        testMember.goToSkillActivity(ActivityType.GO_TO_THE_GYM);
        assertEquals(1, testMember.getSkills().size());
        assertEquals("fitness", testMember.getSkills().get(0).getInformationName());
        testMember.goToSkillActivity(ActivityType.PAINT_A_DRAWING);
        assertEquals(2, testMember.getSkills().size());
        assertEquals("painting", testMember.getSkills().get(1).getInformationName());
        testMember.goToSkillActivity(ActivityType.PAINT_A_DRAWING);
        assertEquals(2, testMember.getSkills().size());
        assertEquals("painting", testMember.getSkills().get(1).getInformationName());
        testMember.goToSkillActivity(ActivityType.PLAY_THE_VIDEO_GAME);
        assertEquals(3, testMember.getSkills().size());
        assertEquals("gaming", testMember.getSkills().get(2).getInformationName());
        testMember.goToSkillActivity(ActivityType.DANCE_TO_THE_MUSIC);
        assertEquals(4, testMember.getSkills().size());
        assertEquals("dancing", testMember.getSkills().get(3).getInformationName());
        testMember.goToSkillActivity(ActivityType.HAVE_A_BIRTHDAY_PARTY);
        assertEquals(4, testMember.getSkills().size());
        assertEquals(20, testMember.getMemberAge());
        testMember.goToSkillActivity(ActivityType.GO_ON_A_DATE);
        assertEquals(4, testMember.getSkills().size());
    }

    @Test
    void goToRelationshipActivityTest() {
        testMember.goToRelationshipActivity(ActivityType.GO_ON_A_DATE);
        assertEquals(1, testMember.getRelationships().size());
        assertEquals("boyfriend", testMember.getRelationships().get(0).getInformationName());
        testMember.goToRelationshipActivity(ActivityType.GO_TO_A_CLUB_MEETING);
        assertEquals(2, testMember.getRelationships().size());
        assertEquals("friendship", testMember.getRelationships().get(1).getInformationName());
        testMember.goToRelationshipActivity(ActivityType.GO_TO_A_CLUB_MEETING);
        assertEquals(2, testMember.getRelationships().size());
        assertEquals("friendship", testMember.getRelationships().get(1).getInformationName());
        testMember.goToRelationshipActivity(ActivityType.GO_GET_MARRIED);
        assertEquals(3, testMember.getRelationships().size());
        assertEquals("spouse", testMember.getRelationships().get(2).getInformationName());
        testMember.goToRelationshipActivity(ActivityType.GO_TO_THE_GYM);
        assertEquals(3, testMember.getRelationships().size());
    }

    @Test
    void levelUpOrAddSkillTest() {
        try {
            assertEquals(0, testMember.getSkills().size());
            testMember.levelUpOrAddSkill("fitness");
            assertEquals(1, testMember.getSkills().size());
            assertEquals("fitness", testMember.getSkills().get(0).getInformationName());
            assertEquals(0, testMember.getSkills().get(0).getInformationLevel());
            testMember.levelUpOrAddSkill("fitness");
            assertEquals(1, testMember.getSkills().size());
            assertEquals("fitness", testMember.getSkills().get(0).getInformationName());
            assertEquals(1, testMember.getSkills().get(0).getInformationLevel());
            testMember.levelUpOrAddSkill("painting");
            assertEquals(2, testMember.getSkills().size());
            assertEquals("painting", testMember.getSkills().get(1).getInformationName());
            assertEquals(0, testMember.getSkills().get(1).getInformationLevel());
        }catch (Exception e) {
            fail("Unexpected exception is thrown.");
        }
    }

    @Test
    void levelUpOrAddRelationshipTest() {
        try {
            assertEquals(0, testMember.getRelationships().size());
            testMember.levelUpOrAddRelationship("boyfriend");
            assertEquals(1, testMember.getRelationships().size());
            assertEquals("boyfriend", testMember.getRelationships().get(0).getInformationName());
            assertEquals(0, testMember.getRelationships().get(0).getInformationLevel());
            testMember.levelUpOrAddRelationship("boyfriend");
            assertEquals(1, testMember.getRelationships().size());
            assertEquals("boyfriend", testMember.getRelationships().get(0).getInformationName());
            assertEquals(1, testMember.getRelationships().get(0).getInformationLevel());
            testMember.levelUpOrAddRelationship("friendship");
            assertEquals(2, testMember.getRelationships().size());
            assertEquals("friendship", testMember.getRelationships().get(1).getInformationName());
            assertEquals(0, testMember.getRelationships().get(1).getInformationLevel());
            testMember.levelUpOrAddRelationship("boyfriend");
            assertEquals(2, testMember.getRelationships().size());
            assertEquals("boyfriend", testMember.getRelationships().get(0).getInformationName());
            assertEquals(2, testMember.getRelationships().get(0).getInformationLevel());
            testMember.levelUpOrAddRelationship("boyfriend");
            assertEquals(2, testMember.getRelationships().size());
            assertEquals("boyfriend", testMember.getRelationships().get(0).getInformationName());
            assertEquals(3, testMember.getRelationships().get(0).getInformationLevel());
        } catch(Exception e) {
            fail("Unexpected exception is thrown.");
        }
    }

    @Test
    void containsSkillTest() {
        assertFalse(testMember.containsSkill("fitness"));
        testMember.goToSkillActivity(ActivityType.GO_TO_THE_GYM);
        assertTrue(testMember.containsSkill("fitness"));
        assertFalse(testMember.containsSkill("painting"));
        testMember.goToSkillActivity(ActivityType.PAINT_A_DRAWING);
        assertTrue(testMember.containsSkill("painting"));
    }

    @Test
    void containsRelationshipTest() {
        assertFalse(testMember.containsRelationship("boyfriend"));
        testMember.goToRelationshipActivity(ActivityType.GO_ON_A_DATE);
        assertTrue(testMember.containsRelationship("boyfriend"));
        assertFalse(testMember.containsRelationship("friendship"));
        testMember.goToRelationshipActivity(ActivityType.GO_TO_A_CLUB_MEETING);
        assertTrue(testMember.containsRelationship("friendship"));
    }

    @Test
    void getSkillTest() {
        assertEquals(null, testMember.getSkill("fitness"));
        testMember.goToSkillActivity(ActivityType.PAINT_A_DRAWING);
        assertEquals(null, testMember.getSkill("fitness"));
        testMember.goToSkillActivity(ActivityType.GO_TO_THE_GYM);
        assertEquals(testMember.getSkills().get(1), testMember.getSkill("fitness"));
    }

    @Test
    void getRelationshipTest() {
        assertEquals(null, testMember.getRelationship("boyfriend"));
        testMember.goToRelationshipActivity(ActivityType.GO_TO_A_CLUB_MEETING);
        assertEquals(null, testMember.getRelationship("boyfriend"));
        testMember.goToRelationshipActivity(ActivityType.GO_ON_A_DATE);
        assertEquals(testMember.getRelationships().get(1), testMember.getRelationship("boyfriend"));
    }

    @Test
    void getRelationshipsNameTest() {
        assertEquals(0, testMember.getRelationshipsName().size());
        testMember.goToRelationshipActivity(ActivityType.GO_ON_A_DATE);
        assertEquals(1, testMember.getRelationshipsName().size());
        testMember.goToRelationshipActivity(ActivityType.GO_TO_A_CLUB_MEETING);
        assertEquals(2, testMember.getRelationshipsName().size());
    }

    @Test
    void getSkillsNameTest() {
        assertEquals(0, testMember.getSkillsName().size());
        testMember.goToSkillActivity(ActivityType.GO_TO_THE_GYM);
        assertEquals(1, testMember.getSkillsName().size());
        testMember.goToSkillActivity(ActivityType.PLAY_THE_VIDEO_GAME);
        assertEquals(2, testMember.getSkillsName().size());
    }
}


