package ui;

import model.*;
import model.exception.EmptyNameException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Represents "Slice Of Life" game
public class SliceOfLifeGame {
    private static final String JSON_FILE = "./data/householdmemberlist.json";
    private HouseholdMember householdMember;
    private HouseholdMemberList listOfHouseholdMember;
    private String memberName;
    private int memberAge;
    private Scanner input;
    private boolean runGame;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private static final String START_COMMAND = "start";
    private static final String QUIT_COMMAND = "quit";
    private static final String GO_BACK_COMMAND = "back";
    private static final String CHECK_PERSONAL_PROFILE_COMMAND = "personal";
    private static final String ADD_NEW_MEMBER_COMMAND = "add";
    private static final String SWITCH_MEMBER_COMMAND = "switch";
    private static final String DELETE_MEMBER_COMMAND = "delete";
    private static final String SAVE_COMMAND = "save";
    private static final String PRINT_COMMAND = "print";
    private static final String LOAD_COMMAND = "load";
    private static final String START_MENU_COMMAND = "menu";


    // EFFECTS: set the HouseholdMember with memberName and memberAge;
    //          the listOfHouseholdMember is initially empty;
    //          runs the game
    public SliceOfLifeGame() throws FileNotFoundException {
        householdMember = new HouseholdMember(memberName, memberAge);
        try {
            listOfHouseholdMember = new HouseholdMemberList("MY CURRENT HOUSEHOLD");
        } catch (EmptyNameException e) {
            e.getMessage();
        }
        jsonWriter = new JsonWriter(JSON_FILE);
        jsonReader = new JsonReader(JSON_FILE);
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: process user input
    public void runGame() {
        input = new Scanner(System.in);
        runGame = true;
        String command;
        startGameOptions();

        while (runGame) {
            command = getUserCommand();
            processStartGameCommand(command);
        }
    }


    // EFFECTS: print options at the start of the game
    public void startGameOptions() {
        System.out.println("\nSLICE OF LIFE");
        System.out.println("\nLet's start the game with creating a Member of the Household:");
        System.out.println("\tEnter '" + LOAD_COMMAND + "' to load the save file.");
        System.out.println("\tEnter '" + START_COMMAND + "' to start a new game.");
        System.out.println("\tEnter '" + QUIT_COMMAND + "' to quit.");
    }


    // MODIFIES: this
    // EFFECTS: processes user command at the start menu of the game
    public void processStartGameCommand(String command) {
        switch (command) {
            case LOAD_COMMAND:
                loadHouseholdMemberList();
                chooseHouseholdMember();
                break;
            case START_COMMAND:
                createHouseholdMember();
                break;
            case QUIT_COMMAND:
                endGame();
                break;
            default:
                System.out.println("\nSelection not valid! please select again.");
        }
    }

    // REQUIRES : name has a non-zero length, age is non-negative
    // MODIFIES: this
    // EFFECTS: create a household member with name and age, and show activities
    public void createHouseholdMember() {
        System.out.println("\nWhat's your name?");
        String name = getUserCommand();
        System.out.println("\nWhat's your age?");
        int age = input.nextInt();
        input.nextLine();
        listOfHouseholdMember.addHouseholdMember(new HouseholdMember(name, age));
        mainMenu();
    }

    // EFFECTS : print other options
    public void mainMenu() {
        System.out.println("\nCurrent Household Member: " + householdMember.getMemberName());
        System.out.println("\nHere are the activities that you can choose from:");
        activityOptions();
        System.out.println("\nTo check you personal profile, enter '" + CHECK_PERSONAL_PROFILE_COMMAND + "'.");
        System.out.println("To create a new Household member, enter '" + ADD_NEW_MEMBER_COMMAND + "'.");
        System.out.println("To switch to another Household member, enter '" + SWITCH_MEMBER_COMMAND + "'.");
        System.out.println("To delete a Household member, enter '" + DELETE_MEMBER_COMMAND + "'.");
        System.out.println("\nTo save the game , enter '" + SAVE_COMMAND + "'.");
        System.out.println("To print the current Household, enter '" + PRINT_COMMAND + "'.");
        System.out.println("To go back to the start menu, enter '" + START_MENU_COMMAND + "'.");
        System.out.println("To quit the game, enter '" + QUIT_COMMAND + "'.");
        String command = getUserCommand();
        handleSkillActivityCommand(command);
    }

    // EFFECTS : print all the activities that is available
    public void activityOptions() {
        System.out.println("Enter 'gym' to go to the gym.");
        System.out.println("Enter 'paint' to paint a drawing.");
        System.out.println("Enter 'dance' to dance to the music.");
        System.out.println("Enter 'game' to play a video game.");
        System.out.println("Enter 'date' to go on a blind date.");
        System.out.println("Enter 'club' to go to a club meeting.");
        System.out.println("Enter 'marriage' to go get married.");
        System.out.println("Enter 'birthday' to have a birthday party.");
    }


    // MODIFIES : this
    // EFFECTS : processes skill activity command and print a sentence depend on command
    public void handleSkillActivityCommand(String command) {
        switch (command) {
            case "gym":
                householdMember.goToSkillActivity(ActivityType.GO_TO_THE_GYM);
                System.out.println("\nYou went to the gym, and did some leg workout.");
                goBackCommand();
                break;
            case "paint":
                householdMember.goToSkillActivity(ActivityType.PAINT_A_DRAWING);
                System.out.println("\nYou painted a masterpiece!");
                goBackCommand();
                break;
            case "dance":
                householdMember.goToSkillActivity(ActivityType.DANCE_TO_THE_MUSIC);
                System.out.println("\nYou danced to your favourite song!");
                goBackCommand();
                break;
            default:
                handleRestActivityCommand(command);
        }
    }


    // MODIFIES : this
    // EFFECTS : processes the rest of the activities and print a sentence depend on command
    public void handleRestActivityCommand(String command) {
        switch (command) {
            case "birthday":
                householdMember.goToSkillActivity(ActivityType.HAVE_A_BIRTHDAY_PARTY);
                System.out.println("\nHAPPY BIRTHDAY TO YOU!!!! MAKE A WISH!!!");
                goBackCommand();
                break;
            case "game":
                householdMember.goToSkillActivity(ActivityType.PLAY_THE_VIDEO_GAME);
                System.out.println("\nYou played sims 4 and wanted to create a mod for this game.");
                goBackCommand();
                break;
            default:
                handleRelationshipCommand(command);
        }
    }


    // MODIFIES : this
    // EFFECTS : processes all relationship activity command and print a sentence depend on command
    public void handleRelationshipCommand(String command) {
        switch (command) {
            case "date":
                householdMember.goToRelationshipActivity(ActivityType.GO_ON_A_DATE);
                System.out.println("\nYou went on a date with your romantic interest, and kissed her/him.");
                goBackCommand();
                break;
            case "club":
                householdMember.goToRelationshipActivity(ActivityType.GO_TO_A_CLUB_MEETING);
                System.out.println("\nYou went to a club meeting and met some new friends.");
                goBackCommand();
                break;
            case "marriage":
                householdMember.goToRelationshipActivity(ActivityType.GO_GET_MARRIED);
                System.out.println("\nSHE/HE/THEY SAID YES!!!!!!!!!");
                goBackCommand();
                break;
            default:
                checkProfileCommand(command);
        }
    }


    // MODIFIES : this
    // EFFECTS : processes checking personal profile command and switching, adding, deleting member
    //          command
    public void checkProfileCommand(String command) {
        switch (command) {
            case CHECK_PERSONAL_PROFILE_COMMAND:
                personalProfile();
                checkLevel();
                break;
            case SWITCH_MEMBER_COMMAND:
                chooseHouseholdMember();
                break;
            case QUIT_COMMAND:
                endGame();
                break;
            case ADD_NEW_MEMBER_COMMAND:
                createNewHouseholdMember();
                break;
            case DELETE_MEMBER_COMMAND:
                deleteHouseholdMember();
                break;
            default:
                processHouseholdMemberListCommand(command);
        }
    }

    // MODIFIES : this
    // EFFECTS : process HouseholdMemberList command, save, print, load the HouseholdMemberList
    public void processHouseholdMemberListCommand(String command) {
        switch (command) {
            case SAVE_COMMAND:
                saveHouseholdMemberList();
                goBackCommand();
                break;
            case PRINT_COMMAND:
                printHouseholdMembersList();
                goBackCommand();
                break;
            case START_MENU_COMMAND:
                startGameOptions();
                break;
            default:
                System.out.println("\nWrong demand, please enter again.");
                String commandAgain = getUserCommand();
                handleSkillActivityCommand(commandAgain);
        }
    }


    // EFFECTS : print the name, age, skills, and relationships of current HouseholdMember
    public void personalProfile() {
        System.out.println("\n- Name:");
        System.out.println(householdMember.getMemberName());
        System.out.println("- Age:");
        System.out.println(householdMember.getMemberAge());
        System.out.println("- Here are all of your skills:");
        System.out.println(householdMember.getSkillsName());
        System.out.println("- Here are all of your relationships:");
        System.out.println(householdMember.getRelationshipsName());
    }


    // REQUIRES : name has a non-zero length, age is non-negative
    // MODIFIES : this
    // EFFECTS : create a new HouseholdMember to listOfHouseholdMember with none-existing name
    public void createNewHouseholdMember() {
        System.out.println("\nAdd a new member to the Household here:");
        System.out.println("\nWhat's your name?");
        String name = getUserCommand();
        if (!(listOfHouseholdMember.nameExists(name))) {
            System.out.println("\nWhat's your age?");
            int age = input.nextInt();
            input.nextLine();
            HouseholdMember newHouseholdMember;
            newHouseholdMember = new HouseholdMember(name, age);
            listOfHouseholdMember.addHouseholdMember(newHouseholdMember);
            System.out.println("\nSuccessfully added " + name + "!");
            goBackCommand();
        } else {
            System.out.println("\n" + name + " already exists, please try a new name.");
            createNewHouseholdMember();
        }
    }


    // REQUIRES : cannot delete the current HouseholdMember
    // MODIFIES : this
    // EFFECTS : delete a HouseholdMember in listOfHouseholdMember with given name
    public void deleteHouseholdMember() {
        System.out.println("\nHere are all of the HouseholdMember to choose from, delete by entering the name:");
        System.out.println(listOfHouseholdMember.getListOfHouseholdMembersNames());
        String command = getUserCommand();
        if (command.equals(householdMember.getMemberName())) {
            System.out.println("\nCannot delete the current HouseholdMember.");
            goBackCommand();
        } else {
            for (HouseholdMember member : listOfHouseholdMember.getHouseholdMembers()) {
                if (member.getMemberName().equals(command)) {
                    listOfHouseholdMember.removeHouseholdMember(member);
                    System.out.println("\nSuccessfully deleted " + command + "!");
                    goBackCommand();
                    break;
                }
            }
        }
    }


    // EFFECTS : check and print the level of the skill or the relationship, or go back to activity menu
    public void checkLevel() {
        System.out.println("\nEnter the name of the skill or relationship to check level:");
        System.out.println("OR");
        System.out.println("Enter '" + GO_BACK_COMMAND + "' to go back to the Activity menu.");
        String command = getUserCommand();
        if (command.equals(GO_BACK_COMMAND)) {
            mainMenu();
        } else {
            if (householdMember.containsSkill(command)) {
                Skill skill = householdMember.getSkill(command);
                System.out.println("\n" + command + " level = " + skill.getInformationLevel());
            } else {
                if (householdMember.containsRelationship(command)) {
                    Relationship relationship = householdMember.getRelationship(command);
                    System.out.println("\n" + command + " level = " + relationship.getInformationLevel());
                } else {
                    System.out.println("\nSorry, no " + command + " skill or relationship found.");
                }
            }
            goBackCommand();
        }
    }


    // MODIFIES : this
    // EFFECTS : choose HouseholdMember in the current listOfHouseholdMember and set householdMember to given member
    public void chooseHouseholdMember() {
        System.out.println("\nHere are all of the HouseholdMember to choose from, choose by entering the name:");
        System.out.println(listOfHouseholdMember.getListOfHouseholdMembersNames());
        String command = getUserCommand();
        for (HouseholdMember member : listOfHouseholdMember.getHouseholdMembers()) {
            if (member.getMemberName().equals(command)) {
                householdMember = member;
                mainMenu();
                break;
            }
        }
    }


    // EFFECTS : saves the HouseholdMemberList to file
    public void saveHouseholdMemberList() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfHouseholdMember);
            jsonWriter.close();
            System.out.println("\nSuccessfully Saved " + listOfHouseholdMember.getName() + " to " + JSON_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("\nError, unable to write to file: " + JSON_FILE);
        }
    }


    // EFFECTS: prints all the householdmember in householdmemberList to the console
    public void printHouseholdMembersList() {
        List<HouseholdMember> members = listOfHouseholdMember.getHouseholdMembers();
        System.out.println("Household Members listed: ");
        for (int i = 1; i <= members.size(); i++) {
            System.out.println("\n" + i + ". " + "\nname: " + members.get(i - 1).getMemberName());
            System.out.println("age: " + members.get(i - 1).getMemberAge());
            System.out.println("skills: ");
            for (Skill skill : members.get(i - 1).getSkills()) {
                System.out.println("name: " + skill.getInformationName());
                System.out.println("level: " + skill.getInformationLevel());
            }
            System.out.println("\nrelationships: ");
            for (Relationship relationship : members.get(i - 1).getRelationships()) {
                System.out.println("name: " + relationship.getInformationName());
                System.out.println("level: " + relationship.getInformationLevel());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads householdmemberlist from file
    public void loadHouseholdMemberList() {
        try {
            listOfHouseholdMember = jsonReader.read();
            System.out.println("\nSuccessfully Loaded " + listOfHouseholdMember.getName() + " from " + JSON_FILE);
        } catch (IOException e) {
            System.out.println("\nError, unable to read from file: " + JSON_FILE);
        }
    }


    // EFFECTS : prints the go back option and go back to activity menu
    public void goBackCommand() {
        System.out.println("\nEnter '" + GO_BACK_COMMAND + "' to go back to the Activity menu.");
        String command = getUserCommand();
        if (command.equals(GO_BACK_COMMAND)) {
            mainMenu();
        }
    }


    // MODIFIES : this
    // EFFECTS: remind to save the game, then end the game and stops receiving user input
    public void endGame() {
        System.out.println("\nWould you like to save your game?");
        System.out.println("Enter '" + SAVE_COMMAND + "' to save.");
        System.out.println("Enter '" + QUIT_COMMAND + "' to quit.");
        String command = getUserCommand();
        if (command.equals(SAVE_COMMAND)) {
            saveHouseholdMemberList();
            runGame = false;
            System.out.println("\nThank you for playing, see u next time :@");
            input.close();
        } else {
            if (command.equals(QUIT_COMMAND)) {
                runGame = false;
                System.out.println("\nThank you for playing, see u next time :@");
                input.close();
            } else {
                System.out.println("\nWrong demand, please try again.");
                endGame();
            }
        }
    }


    // getter
    // EFFECTS : get all user command and returned the fixed command
    private String getUserCommand() {
        String command = "";
        if (input.hasNext()) {
            command = input.nextLine();
            command = command.toLowerCase();
        }
        return command;
    }
}
