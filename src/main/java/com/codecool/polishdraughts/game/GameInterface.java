package com.codecool.polishdraughts.game;

import com.codecool.polishdraughts.pieces.Pawn;

public interface GameInterface {

    void start();

    void playRound(String player);

    String askForNextMove();

    String checkForWinner(Pawn[][] board);
}
