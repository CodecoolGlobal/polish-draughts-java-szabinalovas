package com.codecool.polishdraughts.board;

import com.codecool.polishdraughts.pieces.ColorEnum;
import com.codecool.polishdraughts.pieces.Coordinates;
import com.codecool.polishdraughts.pieces.Pawn;

public class Board {

    private String[][] board;
    private Pawn[][] pawnsBoard;

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

    public int getSize() {
        return board.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        for (int i = 0; i < getBoard().length; i++) {
            sb.append(String.format("%3s", i + 1));
        }

        sb.append("\n");

        for (int y = 0; y < getBoard().length; y++) {
            sb.append((char) ('A' + y));
            for (int x = 0; x < getBoard()[y].length; x++) {
                if (getPawnsBoard()[y][x] != null) {
                    getBoard()[y][x] = getPawnsBoard()[y][x].getColor().getPawnChar();
                }
                sb.append("  ").append(getBoard()[y][x]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String[][] initBoard(int n) {
        board = new String[n][n];

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if ((y % 2 == 0 && x % 2 == 0) || (y % 2 != 0 && x % 2 != 0)) {
                    board[y][x] = " ";
                } else {
                    board[y][x] = "#";
                }
            }
        }
        return board;
    }

    public Pawn[][] initPawnsBoard(int n) {
        pawnsBoard = new Pawn[n][n];
        // az első 4 sor fekete mezőire pakolja a fekete gyalogokat
        final int NUMBER_OF_INIT_PAWN_LINES = 4;
        for (int y = 0; y < NUMBER_OF_INIT_PAWN_LINES; y++) {
            for (int x = 0; x < pawnsBoard[0].length; x++) {
                if (!((y % 2 == 0 && x % 2 == 0) || (y % 2 != 0 && x % 2 != 0))) {
                    pawnsBoard[y][x] = new Pawn(ColorEnum.BLACK, new Coordinates(y, x));
                }
            }
        }
        // az utolsó 4 sor fekete mezőire pakolja a fehér gyalogokat
        for (int y = board.length - NUMBER_OF_INIT_PAWN_LINES; y < board.length; y++) {
            for (int x = 0; x < pawnsBoard[0].length; x++) {
                if (!((y % 2 == 0 && x % 2 == 0) || (y % 2 != 0 && x % 2 != 0))) {
                    pawnsBoard[y][x] = new Pawn(ColorEnum.WHITE, new Coordinates(y, x));
                }
            }
        }
        return pawnsBoard;
    }

    public void removePawn(Pawn pawnToRemove) {
        int x = pawnToRemove.getPosition().getX();
        int y = pawnToRemove.getPosition().getY();
        pawnsBoard[y][x] = null;
        board[y][x] = "#";
        pawnToRemove.setPosition(new Coordinates(-1, -1));
    }

    public void movePawn(Pawn movingPawn, Coordinates toCoordinates) {
        int oldX = movingPawn.getPosition().getX();
        int oldY = movingPawn.getPosition().getY();
        pawnsBoard[oldY][oldX] = null;
        board[oldY][oldX] = "#";
        movingPawn.setPosition(toCoordinates);
        pawnsBoard[toCoordinates.getY()][toCoordinates.getX()] = movingPawn;
    }
}
