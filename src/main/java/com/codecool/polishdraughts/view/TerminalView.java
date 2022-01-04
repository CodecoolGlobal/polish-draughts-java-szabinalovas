package com.codecool.polishdraughts.view;

import java.util.Scanner;

public class TerminalView {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String readInput(String prompt) {
        System.out.print(prompt + " ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().toUpperCase();
        if (userInput.equals("QUIT")) System.exit(0);
        return userInput;
    }
}
