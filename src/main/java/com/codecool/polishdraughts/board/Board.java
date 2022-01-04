package com.codecool.polishdraughts.board;

public class Board {

    private String[][] board;

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
}
