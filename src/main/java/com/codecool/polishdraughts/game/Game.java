package com.codecool.polishdraughts.game;

import com.codecool.polishdraughts.board.Board;
import com.codecool.polishdraughts.pieces.Pawn;
import com.codecool.polishdraughts.view.TerminalView;

public class Game implements GameInterface{
    private Board board;
    private boolean isGameRunning;





    public void start() {
        int player = 1;
       // this.board = Board.getBoard(); // getter to be implemented
        TerminalView.clearScreen();
        this.isGameRunning = true;
        while (isGameRunning) {
            playRound();
            player = player == 1 ? 2 : 1;
        }
    }

    public void playRound() {
        TerminalView.clearScreen();
        //board.initBoard(size);
        //Board.movePawn();
        checkForWinner(board);

    }

    public boolean tryToMakeMove(Pawn[][] board, int fromX, int fromY, int toX, int toY) {
        boolean isMoveOK = false;
        if (board[fromX][fromY] != null && board[toX][toY] == null && targetFieldIsBlack(board)) {
            //Board.movePawn(); to be implemented in Board class;
            isMoveOK = true;
        }
        // move can be made if:
        // 1. move is from a black to another black field
        // 2. starting field has a pawn
        // 3. target field is empty
        // returns true if move is valid -> Board movePawn()
        // returns false if move isn't valid -> player needs to provide another input
        else {
            System.out.println("This is not a valid move. Try again.");
        }
        return isMoveOK;
    }

    public boolean targetFieldIsBlack(Pawn[][] board) {
        boolean isTargetBlack = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((i+j) % 2 != 0) {
                   isTargetBlack = true;
                }
            }
        }
        return isTargetBlack;
    }

    public String checkForWinner(Board board) {

        return "";
    }

    public String printWinner(Board board) {

        return "";
    }


}
