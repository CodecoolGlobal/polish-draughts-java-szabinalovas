package com.codecool.polishdraughts.board;

import com.codecool.polishdraughts.pieces.ColorEnum;
import com.codecool.polishdraughts.pieces.Coordinates;
import com.codecool.polishdraughts.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private String[][] board;
    private final int NUMBER_OF_INIT_PAWN_LINES = 4;

    public Board(int n) {
        if (10 <= n && n <= 20) {
            this.board = initBoard(n);
        }
    }

    public String[][] initBoard(int n) {
        board = new String[n][n];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
                    board[i][j] = " ";
                } else {
                    board[i][j] = "#";
                }
                System.out.printf("%3s", board[i][j]);
            }
            System.out.println();
        }
        return board;
    }

    public Pawn[][] initPawnsBoard(int n) {
        Pawn[][] pawnsBoard = new Pawn[n][n];
        // az első 4 sor fekete mezőire pakolja a fekete gyalogokat
        for (int i = 0; i < NUMBER_OF_INIT_PAWN_LINES; i++) {
            for (int j = 0; j < pawnsBoard[0].length; j++) {
                if (!((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0))) {
                    pawnsBoard[i][j] = new Pawn(ColorEnum.BLACK, new Coordinates(i, j));
                }
            }
        }
        // az utolsó 4 sor fekete mezőire pakolja a fehér gyalogokat
        for (int i = board.length - NUMBER_OF_INIT_PAWN_LINES - 1; i < board.length; i++) {
            for (int j = 0; j < pawnsBoard[0].length; j++) {
                if (!((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0))) {
                    pawnsBoard[i][j] = new Pawn(ColorEnum.WHITE, new Coordinates(i, j));
                }
            }
        }
        return pawnsBoard;
    }
}
