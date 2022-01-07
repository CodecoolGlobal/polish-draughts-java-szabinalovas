package com.codecool.polishdraughts.view;

import java.util.Scanner;

import com.codecool.polishdraughts.board.Board;
import com.codecool.polishdraughts.pieces.Coordinates;

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

    public static boolean isCoordinatesInputFormatValid(String userInput) {
        return userInput.matches("^[A-Z]\\d{1,2}$");
    }

    public static boolean isValidPlayer(String player, Board board, Coordinates coordinates) {
        return board.getPawnsBoard()[coordinates.getY()][coordinates.getX()] != null &&
                board.getPawnsBoard()[coordinates.getY()][coordinates.getX()].getPawnChar().equals(player);
    }

    public static boolean isValidEnemy(String player, Board board, Coordinates coordinates) {
        return board.getPawnsBoard()[coordinates.getY()][coordinates.getX()] != null &&
                !board.getPawnsBoard()[coordinates.getY()][coordinates.getX()].getPawnChar().equals(player);
    }

    public static boolean isMenuInputValid(String userInput) {
        return userInput.matches("^[1-9]$");
    }

    public static void printBoard(Board board) {
        System.out.println(board.toString());
    }

    public static void printWinner(String winner) {
        System.out.println("Player " + winner + " has won.");
    }
}
