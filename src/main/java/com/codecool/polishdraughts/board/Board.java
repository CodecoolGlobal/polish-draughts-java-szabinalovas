package com.codecool.polishdraughts.board;

import com.codecool.polishdraughts.pieces.ColorEnum;
import com.codecool.polishdraughts.pieces.Coordinates;
import com.codecool.polishdraughts.pieces.Pawn;

public class Board {

    private String[][] board;
    private Pawn[][] pawnsBoard;
    private final int NUMBER_OF_INIT_PAWN_LINES = 4;

    public String[][] getBoard() {
        return board;
    }

    public Pawn[][] getPawnsBoard() {
        return pawnsBoard;
    }

    public Board(int n) {
        if (10 <= n && n <= 20) {
            this.board = initBoard(n);
            this.pawnsBoard = initPawnsBoard(n);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        for (int i = 0; i < getBoard().length; i++) {
            sb.append(String.format("%3s", i + 1));
        }

        sb.append("\n");

        for (int x = 0; x < getBoard().length; x++) {
            sb.append((char) ('A' + x));
            for (int y = 0; y < getBoard()[x].length; y++) {
                if (getPawnsBoard()[x][y] != null) {
                    getBoard()[x][y] = getPawnsBoard()[x][y].getColor().getPawnChar();
                }
                sb.append(String.format("%3s", getBoard()[x][y]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String[][] initBoard(int n) {
        board = new String[n][n];

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if ((x % 2 == 0 && y % 2 == 0) || (x % 2 != 0 && y % 2 != 0)) {
                    board[x][y] = " ";
                } else {
                    board[x][y] = "#";
                }
            }
        }
        return board;
    }

    public Pawn[][] initPawnsBoard(int n) {
        pawnsBoard = new Pawn[n][n];
        // az első 4 sor fekete mezőire pakolja a fekete gyalogokat
        for (int x = 0; x < NUMBER_OF_INIT_PAWN_LINES; x++) {
            for (int y = 0; y < pawnsBoard[0].length; y++) {
                if (!((x % 2 == 0 && y % 2 == 0) || (x % 2 != 0 && y % 2 != 0))) {
                    pawnsBoard[x][y] = new Pawn(ColorEnum.BLACK, new Coordinates(x, y));
                }
            }
        }
        // az utolsó 4 sor fekete mezőire pakolja a fehér gyalogokat
        for (int x = board.length - NUMBER_OF_INIT_PAWN_LINES; x < board.length; x++) {
            for (int y = 0; y < pawnsBoard[0].length; y++) {
                if (!((x % 2 == 0 && y % 2 == 0) || (x % 2 != 0 && y % 2 != 0))) {
                    pawnsBoard[x][y] = new Pawn(ColorEnum.WHITE, new Coordinates(x, y));
                }
            }
        }
        return pawnsBoard;
    }

    public void removePawn(Pawn pawnToRemove) {
        int x = pawnToRemove.getPosition().getX();
        int y = pawnToRemove.getPosition().getY();
        pawnsBoard[x][y] = null;
        pawnToRemove.setPosition(new Coordinates(-1, -1));
    }
}
