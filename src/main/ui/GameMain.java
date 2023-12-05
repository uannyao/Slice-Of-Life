package ui;

import java.io.FileNotFoundException;

//Represents "Slice Of Life" game
public class GameMain {
    public static void main(String[] args) {
        try {
            new SliceOfLifeGame();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
