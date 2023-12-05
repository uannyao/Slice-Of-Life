package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Represents SliceOfLife game GUI
public class SliceOfLifeGui extends JPanel implements ActionListener {
    private static final String JSON_FILE = "./data/householdmemberlist.json";
    private HouseholdMember householdMember;
    private HouseholdMemberList listOfHouseholdMember;
    private String memberName;
    private int memberAge;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JButton startGame;
    private JButton save;
    private JButton loadSave;
    private JButton quit;
    private JButton quitGame;
    private JButton gym;
    private JButton paint;
    private JButton dance;
    private JButton game;
    private JButton date;
    private JButton club;
    private JButton marriage;
    private JButton birthday;
    private JButton addMember;
    private JButton deleteMember;
    private JButton checkLevel;
    private List<JButton> listOfActivityButtons;

    private JFrame homepage = new JFrame();
    private JFrame mainPage = new JFrame();
    private JPanel startPanel = new JPanel();
    private JPanel activityPanel = new JPanel();
    private JPanel householdOptionPanel = new JPanel();
    private JPanel personalInfoPanel = new JPanel();
    private JPanel otherOptionPanel = new JPanel();

    // EFFECTS: setup new SliceOfLife Game and homepage with buttons and their own action command
    public SliceOfLifeGui() {
        setup();
        startGame = new JButton("  START  ");
        startGame.setActionCommand("start");
        loadSave = new JButton("  LOAD  ");
        loadSave.setActionCommand("load");
        quit = new JButton("  QUIT  ");
        quit.setActionCommand("quit");
        startGame.addActionListener(this);
        loadSave.addActionListener(this);
        quit.addActionListener(this);
        startPanel.add(startGame);
        startPanel.add(loadSave);
        startPanel.add(quit);
        startPanel.setBackground(Color.BLACK);
        homepage.setTitle("SLICE OF LIFE : Homepage");
        homepage.add(startPanel);
        homepage.setLocationRelativeTo(null);
        homepage.add(setHomePageBackground());
        homepage.setVisible(true);
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
    }

    // MODIFIES: this
    // EFFECTS: set the background picture to background.jpg
    public JLabel setHomePageBackground() {
        ImageIcon background = new ImageIcon("./data/background.jpg");
        Image backgroundPic = background.getImage();
        Image backgroundPicRevised = backgroundPic.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        ImageIcon backgroundRevised = new ImageIcon(backgroundPicRevised);
        JLabel backgroundLabel = new JLabel(backgroundRevised);
        backgroundLabel.setBounds(0, 0, 500, 500);
        return backgroundLabel;
    }


    // MODIFIES: this
    // EFFECTS: set up the game and the layout of homepage, the listOfHouseholdMember is initially empty
    public void setup() {
        listOfHouseholdMember = new HouseholdMemberList("MY CURRENT HOUSEHOLD");
        jsonWriter = new JsonWriter(JSON_FILE);
        jsonReader = new JsonReader(JSON_FILE);
        homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homepage.setLayout(new FlowLayout());
        homepage.setSize(500, 500);
        JLabel label = new JLabel("Welcome to SLICE OF LIFE!", SwingConstants.LEFT);
        label.setVerticalAlignment(SwingConstants.TOP);
        homepage.add(label);
        homepage.setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS : Perform homepage action depends on the action command
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "start":
                createHouseholdMember();
                break;
            case "load":
                loadHouseholdMemberList();
                break;
            case "quit":
                endGame();
                break;
            case "quit and print":
                quitAndPrint();
                break;
            default:
                skillActivityActionPerformed(e);
        }
    }



    // MODIFIES : this
    // EFFECTS : Perform skill activity action depends on the action command
    public void skillActivityActionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "gym":
                householdMember.goToSkillActivity(ActivityType.GO_TO_THE_GYM);
                JOptionPane.showMessageDialog(
                        null, "You went to the gym, and did some leg workout.");
                refreshMainPageFrame();
                break;
            case "paint":
                householdMember.goToSkillActivity(ActivityType.PAINT_A_DRAWING);
                JOptionPane.showMessageDialog(null, "You painted a masterpiece!");
                refreshMainPageFrame();
                break;
            case "dance":
                householdMember.goToSkillActivity(ActivityType.DANCE_TO_THE_MUSIC);
                JOptionPane.showMessageDialog(null, "You danced to your favourite song!");
                refreshMainPageFrame();
                break;
            default:
                otherActivityActionPerformed(e);
        }
    }

    // MODIFIES : this
    // EFFECTS : Perform any other activity action depends on the action command
    public void otherActivityActionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "date":
                householdMember.goToRelationshipActivity(ActivityType.GO_ON_A_DATE);
                JOptionPane.showMessageDialog(null,
                        "You went on a date with your romantic interest, and kissed her/him.");
                refreshMainPageFrame();
                break;
            case "club":
                householdMember.goToRelationshipActivity(ActivityType.GO_TO_A_CLUB_MEETING);
                JOptionPane.showMessageDialog(null,
                        "You went to a club meeting and met some new friends.");
                refreshMainPageFrame();
                break;
            case "marriage":
                householdMember.goToRelationshipActivity(ActivityType.GO_GET_MARRIED);
                JOptionPane.showMessageDialog(null, "SHE/HE/THEY SAID YES!!!!!!!!!");
                refreshMainPageFrame();
                break;
            default:
                moreActivityActionPerformed(e);
        }
    }

    // MODIFIES : this
    // EFFECTS : Perform more activity action depends on the action command
    public void moreActivityActionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "game":
                householdMember.goToSkillActivity(ActivityType.PLAY_THE_VIDEO_GAME);
                JOptionPane.showMessageDialog(null,
                        "You played sims 4 and wanted to create a mod for this game.");
                refreshMainPageFrame();
                break;
            case "birthday":
                householdMember.goToSkillActivity(ActivityType.HAVE_A_BIRTHDAY_PARTY);
                JOptionPane.showMessageDialog(null,
                        "HAPPY BIRTHDAY TO YOU!!!! MAKE A WISH!!!");
                refreshMainPageFrame();
                break;
            default:
                manageMemberActionPerformed(e);
        }
    }

    // MODIFIES : this
    // EFFECTS : Perform member related action depends on the action command
    public void manageMemberActionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add":
                createNewHouseholdMember();
                break;
            case "delete":
                deleteHouseholdMember();
                break;
            case "save":
                saveHouseholdMemberList();
                break;
            case "check":
                checkLevel();
                break;
            default:
                chooseHouseholdMember(e.getActionCommand());
        }
    }

    // MODIFIES: this
    // EFFECTS: show current household member's skills and relationships with levels on a dialog
    public void checkLevel() {
        List<String> skills = new ArrayList<>();
        for (Skill s : householdMember.getSkills()) {
            skills.add(s.getInformationName() + ": " + s.getInformationLevel());
        }
        List<String> relationships = new ArrayList<>();
        for (Relationship r : householdMember.getRelationships()) {
            relationships.add(r.getInformationName() + ": " + r.getInformationLevel());
        }
        JOptionPane.showMessageDialog(null,
                "SKILLS: " + "\n" + skills + "\n" + "RELATIONSHIPS: " + "\n" + relationships);
    }

    // MODIFIES : this
    // EFFECTS : show message and exit out of the system
    public void endGame() {
        JOptionPane.showMessageDialog(null,
                "Thanks for playing 'SLICE OF LIFE', See you next time!");
        System.exit(0);
    }

    // REQUIRES : name has a non-zero length, age is non-negative
    // MODIFIES: this
    // EFFECTS: create a household member with name and age
    public void createHouseholdMember() {
        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("What is your name?"));
        panel.add(field1);
        panel.add(new JLabel("What is your age?"));
        panel.add(field2);
        int result = JOptionPane.showConfirmDialog(null, panel,
                "Household Member",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            this.memberName = field1.getText();
            this.memberAge = Integer.parseInt(field2.getText());
            householdMember = new HouseholdMember(memberName, memberAge);
            listOfHouseholdMember
                    .addHouseholdMember(householdMember);
            homepage.setVisible(false);
            mainMenu();
        }
    }

    // REQUIRES : name has a non-zero length, age is non-negative
    // MODIFIES: this
    // EFFECTS: create a new household member with name and age
    public void createNewHouseholdMember() {
        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("What is your name?"));
        panel.add(field1);
        panel.add(new JLabel("What is your age?"));
        panel.add(field2);
        int result = JOptionPane.showConfirmDialog(null, panel,
                "Household Member",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        String name = field1.getText();
        int age = Integer.parseInt(field2.getText());
        if (result == JOptionPane.OK_OPTION && !(listOfHouseholdMember.nameExists(name))) {
            HouseholdMember newHouseholdMember = new HouseholdMember(name, age);
            listOfHouseholdMember
                    .addHouseholdMember(newHouseholdMember);
            homepage.setVisible(false);
            refreshMainPageFrame();
        }
    }

    // MODIFIES : this
    // EFFECTS : add different panels to the mainPage
    public void mainMenu() {
        setCloseWindowAction();
        mainPage.getContentPane().setBackground(Color.BLACK);
        mainPage.getContentPane().setLayout(new BoxLayout(mainPage.getContentPane(), BoxLayout.Y_AXIS));
        mainPage.setTitle("Let's start your new journey! - SLICE OF LIFE -");
        mainPage.setJMenuBar(createMenuBar());
        personalInfo();
        mainPage.add(personalInfoPanel);
        activityButtons();
        activityPanel.setBorder(BorderFactory.createTitledBorder("Activities"));
        activityPanel.setPreferredSize(new Dimension(300, 300));
        activityPanel.setMaximumSize(new Dimension(300, 300));
        activityPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPage.add(activityPanel);
        householdOptionButtons();
        householdOptionPanel.setBorder(BorderFactory.createTitledBorder("Household Options"));
        householdOptionPanel.setPreferredSize(new Dimension(300, 100));
        householdOptionPanel.setMaximumSize(new Dimension(300, 100));
        householdOptionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPage.add(householdOptionPanel);
        otherOptionsPanel();
        mainPage.add(otherOptionPanel);
        mainPage.setSize(800, 800);
        mainPage.setVisible(true);
    }


    //MODIFIES : mainPage
    //EFFECTS : set the close window action of mainPage to do nothing and print the eventLog, then close the system
    public void setCloseWindowAction() {
        mainPage.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainPage.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                for (Event e : EventLog.getInstance()) {
                    System.out.println(e.getDate() + ": " + e.getDescription());
                }
                System.exit(0);
            }
        });
    }

    //MODIFIES: mainPage
    //EFFECTS : close mainPage, print the EventLog, then quit the game
    public void quitAndPrint() {
        mainPage.dispose();
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.getDate() + ": " + e.getDescription());
        }
        endGame();
    }

    // EFFECTS : create a menu bar with selections of list of household member and return the bar
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu switchMenu;
        menuBar = new JMenuBar();
        switchMenu = new JMenu("Menu : Select Household Member");
        menuBar.add(switchMenu);

        for (HouseholdMember m : listOfHouseholdMember.getHouseholdMembers()) {
            JMenuItem member = new JMenuItem(m.getMemberName());
            member.setActionCommand(m.getMemberName());
            member.addActionListener(this);
            switchMenu.add(member);
        }

        return menuBar;
    }


    // MODIFIES: activityPanel
    // EFFECTS: create activity buttons and added to activityPanel
    public void activityButtons() {
        listOfActivityButtons = new ArrayList<>();
        listOfActivityButtons.add(gym = new JButton("GO TO THE GYM"));
        listOfActivityButtons.add(paint = new JButton("PAINT A DRAWING"));
        listOfActivityButtons.add(dance = new JButton("DANCE TO THE MUSIC"));
        listOfActivityButtons.add(game = new JButton("PLAY THE VIDEO GAME"));
        listOfActivityButtons.add(date = new JButton("GO ON A DATE"));
        listOfActivityButtons.add(club = new JButton("GO TO A CLUB MEETING"));
        listOfActivityButtons.add(marriage = new JButton("GO GET MARRIED"));
        listOfActivityButtons.add(birthday = new JButton("HAVE A BIRTHDAY PARTY"));
        birthday.setActionCommand("birthday");
        gym.setActionCommand("gym");
        paint.setActionCommand("paint");
        dance.setActionCommand("dance");
        game.setActionCommand("game");
        date.setActionCommand("date");
        club.setActionCommand("club");
        marriage.setActionCommand("marriage");
        for (JButton b : listOfActivityButtons) {
            b.addActionListener(this);
            activityPanel.add(b);
        }
    }

    // MODIFIES : householdOptionPanel
    // EFFECTS : create add and delete buttons and added to householdOptionPanel
    public void householdOptionButtons() {
        addMember = new JButton("ADD NEW MEMBER");
        deleteMember = new JButton("DELETE MEMBER");
        addMember.setActionCommand("add");
        deleteMember.setActionCommand("delete");
        addMember.addActionListener(this);
        deleteMember.addActionListener(this);
        householdOptionPanel.add(addMember);
        householdOptionPanel.add(deleteMember);
    }

    // MODIFIES: otherOptionPanel
    // EFFECTS : create save buttons and added to otherOptionPanel along with quit button
    public void otherOptionsPanel() {
        save = new JButton("  SAVE  ");
        save.setActionCommand("save");
        save.addActionListener(this);
        quitGame = new JButton("  QUIT  ");
        quitGame.setActionCommand("quit and print");
        quitGame.addActionListener(this);
        otherOptionPanel.add(save);
        otherOptionPanel.add(quitGame);
        otherOptionPanel.setBorder(BorderFactory.createTitledBorder("Other Options"));
        otherOptionPanel.setPreferredSize(new Dimension(300, 70));
        otherOptionPanel.setMaximumSize(new Dimension(300, 70));
        otherOptionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // MODIFIES: personalInfoPanel
    // EFFECTS: added personal information, name, age, skills, relationships to personalInfoPanel
    public void personalInfo() {
        personalInfoPanel.setLayout(new BoxLayout(personalInfoPanel, BoxLayout.Y_AXIS));
        personalInfoPanel.add(new JLabel("Name : " + householdMember.getMemberName()));
        personalInfoPanel.add(new JLabel("Age : " + householdMember.getMemberAge()));
        personalInfoPanel.add(new JLabel("  "));
        personalInfoPanel.add(new JLabel("Skills : "));
        personalInfoPanel.add(new JLabel(String.valueOf(householdMember.getSkillsName())));
        personalInfoPanel.add(new JLabel("  "));
        personalInfoPanel.add(new JLabel("Relationships : "));
        personalInfoPanel.add(new JLabel(String.valueOf(householdMember.getRelationshipsName())));
        personalInfoPanel.add(new JLabel("  "));
        personalInfoPanel.add(new JLabel("HouseholdMember : "));
        personalInfoPanel.add(new JLabel(String.valueOf(listOfHouseholdMember.getListOfHouseholdMembersNames())));
        personalInfoPanel.add(new JLabel("  "));
        checkLevel = new JButton("CHECK RELATIONSHIP/SKILL LEVEL");
        checkLevel.setActionCommand("check");
        checkLevel.addActionListener(this);
        personalInfoPanel.add(checkLevel);
        personalInfoPanel.setBorder(BorderFactory.createTitledBorder("Personal Info"));
        personalInfoPanel.setPreferredSize(new Dimension(300, 260));
        personalInfoPanel.setMaximumSize(new Dimension(300, 260));
        personalInfoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }


    // MODIFIES: this
    // EFFECTS: loads householdmemberlist from file
    public void loadHouseholdMemberList() {
        try {
            listOfHouseholdMember = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Successfully Loaded "
                    + "'" + listOfHouseholdMember.getName() + "'" + " from " + JSON_FILE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error, unable to read from file: "
                    + JSON_FILE);
        }
        homepage.setVisible(false);
        householdMember = listOfHouseholdMember.getHouseholdMembers().get(0);
        mainMenu();
        mainPage.setVisible(true);
    }

    // MODIFIES: mainPage
    // EFFECTS: remove all panels in mainPage and added them again
    public void refreshMainPageFrame() {
        personalInfoPanel.removeAll();
        activityPanel.removeAll();
        otherOptionPanel.removeAll();
        householdOptionPanel.removeAll();
        mainPage.getContentPane().removeAll();
        mainPage.validate();
        mainPage.repaint();
        mainMenu();
    }

    // MODIFIES: this
    // EFFECTS : saves the HouseholdMemberList to file
    public void saveHouseholdMemberList() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfHouseholdMember);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Successfully Saved "
                    + "'" + listOfHouseholdMember.getName() + "'" + " to " + JSON_FILE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error, unable to write to file: "
                    + JSON_FILE);
        }
    }

    // MODIFIES : this
    // EFFECTS : delete current HouseholdMember in listOfHouseholdMember, if listOfHouseholdMember is now empty,
    //           go back to the homepage
    public void deleteHouseholdMember() {
        JOptionPane.showMessageDialog(null,
                "Successfully deleted " + householdMember.getMemberName() + "!");
        listOfHouseholdMember.removeHouseholdMember(householdMember);
        if (listOfHouseholdMember.getHouseholdMembers().isEmpty()) {
            mainPage.setVisible(false);
            new SliceOfLifeGui();
        } else {
            householdMember = listOfHouseholdMember.getHouseholdMembers().get(0);
            refreshMainPageFrame();
        }
    }

    // MODIFIES : this
    // EFFECTS : choose HouseholdMember in the current listOfHouseholdMember and set householdMember to given member
    public void chooseHouseholdMember(String memberName) {
        for (HouseholdMember m : listOfHouseholdMember.getHouseholdMembers()) {
            if (m.getMemberName().equals(memberName)) {
                householdMember = m;
            }
        }

        JOptionPane.showMessageDialog(null, "Successfully switched to " + memberName + "!");
        refreshMainPageFrame();
    }
}
