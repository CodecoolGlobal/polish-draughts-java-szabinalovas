package com.codecool.polishdraughts.game;

public interface GameInterface {

    void start();
    void playRound();
    boolean tryToMakeMove();
    void checkForWinner();
}
