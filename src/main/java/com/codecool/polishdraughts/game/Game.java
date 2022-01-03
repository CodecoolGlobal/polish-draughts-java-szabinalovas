package com.codecool.polishdraughts.game;

import com.codecool.polishdraughts.board.Board;

public class Game implements GameInterface{
    private Board board;
    private boolean isGameRunning;

    @Override
    public void start() {
        int player = 1;
        this.board = new Board();
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
