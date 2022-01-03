package com.codecool.polishdraughts.game;

import com.codecool.polishdraughts.board.Board;

public class Game implements GameInterface{
    private Board board;
    private boolean isGameRunning;



    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public void start() {
        int player = 1;
        this.board = new Board();
        clearScreen();
        this.isGameRunning = true;
        while (isGameRunning) {
            playRound();
            player = player == 1 ? 2 : 1;
        }
    }

    @Override
    public void playRound() {
        //clearScreen();
        // printBoard(board);
        //

    }

    @Override
    public boolean tryToMakeMove() {
        // move can be made if:
        // 1. move is from a black to another black field
        // 2. starting field has a pawn
        // 3. target field is empty
        // returns true if move is valid -> makeMove()
        // returns false if move isn't valid -> player needs to provide another input
    }

    @Override
    public String checkForWinner(Board board) {

        return "";
    }

    public String printWinner(Board board) {

        return "";
    }

    public int[] makeMove() {
        
    }
}
