package com.codecool.polishdraughts.screens;

import com.codecool.polishdraughts.game.Game;
import com.codecool.polishdraughts.util.Util;
import com.codecool.polishdraughts.view.TerminalView;

import java.io.File;
import java.io.IOException;

public class IntroScreen implements Screen {
    private Game game = new Game();

    @Override
    public void display() {
        try {
            File fileTitle = new File("src/main/resources/gametitle.txt");
            Util.readFile(fileTitle);
        } catch (IOException ioe) {
            System.out.println("File does not exist!");
        }
    }

    public void menu() {
        System.out.println("Welcome to the Polish Draughts game");
        System.out.println(" 1 -  Game rules");
        System.out.println(" 2 -  Play");
        System.out.println(" 3 -  Exit");
        System.out.println();
        System.out.print("Choose from the below options:");
        String userInput;
        boolean isInputValid;
        do {
            userInput = TerminalView.readInput("");
            isInputValid = TerminalView.isMenuInputValid(userInput);
            if (!isInputValid) System.out.print("Invalid input. Please retry:");
        } while (!isInputValid);
        switch (userInput) {
            case "1":
                GameRules.gameRules();
            case "2":
                game.start();
                break;
            default:
                System.exit(0);
        }
    }
}





