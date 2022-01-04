package com.codecool.polishdraughts.view;

import com.codecool.polishdraughts.board.Board;

public class TerminalView {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void printBoard(Board board) {
        System.out.println(board.toString());
    }
}
