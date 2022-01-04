package com.codecool.polishdraughts.screens;

import com.codecool.polishdraughts.util.Util;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class IntroScreen implements Screen {

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
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Polish Draughts game");
        System.out.println(" 1 -  Game rules");
        System.out.println(" 2 -  Play");
        System.out.println();
        System.out.print("Choose from the below options: ");
        String userInput = sc.nextLine();
        switch (userInput) {
            case "1":
                //Implement game rules screen here
            case "2":
        }
    }

}





