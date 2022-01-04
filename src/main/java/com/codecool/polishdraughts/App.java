package com.codecool.polishdraughts;

import com.codecool.polishdraughts.board.Board;
import com.codecool.polishdraughts.view.TerminalView;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        System.out.println("Give me a number between 10 and 20:");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        Board board = new Board(size);
        TerminalView view = new TerminalView();
        view.printBoard(board);
    }
}
