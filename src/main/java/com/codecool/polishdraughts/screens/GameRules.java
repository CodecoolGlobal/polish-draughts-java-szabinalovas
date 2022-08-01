package com.codecool.polishdraughts.screens;

public class GameRules {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";
    public static final String WHITE_BRIGHT = "\033[0;97m";
    public static final String BLUE_BACKGROUND = "\033[44m";

    public static void gameRules() {
        StringBuilder gameRules = new StringBuilder();
        gameRules.append("\n" + BLUE_BACKGROUND + "\n" + ANSI_RESET)
                .append(BLUE_BOLD_BRIGHT)
                .append("\n        The goal of \"Draughts\" is to remove all your opponent's pieces from the board.")
                .append(WHITE_BRIGHT)
                .append("\n\n")
                .append("Your pieces can only move forward one tile diagonally (they always stay on the brown tiles).")
                .append("\n\n")
                .append("To capture an opponent's piece and remove it from the board, \n" +
                        "you need to \"jump\" over their piece with one of yours. \n" +
                        "If one of your pieces gets to the opposite side of the board (your opponent's back row),\n" +
                        "it will turn into a Crown.")
                .append("\n\n")
                .append("Crowns can move and jump diagonally in any direction.")
                .append("\n\n")
                .append("You win by removing all of your opponent's pieces from the board, or if your opponent can't make a move.")
                .append("\n")
                .append("\n" + BLUE_BACKGROUND + "\n" + ANSI_RESET);

        System.out.println(gameRules);
    }

}
