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

    }

    @Override
    public void tryToMakeMove() {

    }

    @Override
    public void checkForWinner() {

    }
}
