package com.codecool.polishdraughts.game;

import com.codecool.polishdraughts.board.Board;
import com.codecool.polishdraughts.pieces.ColorEnum;
import com.codecool.polishdraughts.pieces.Coordinates;
import com.codecool.polishdraughts.pieces.Pawn;
import com.codecool.polishdraughts.view.TerminalView;

public class Game implements GameInterface {
    private static final int NUMBER_OF_COORDINATE_ELEMENTS = 2;
    private static final int ASCII_DEC_CODE_UPPERCASE_LETTER_A = 65;
    private static final int INDEX_CORRECTION = 1;
    private static final int MAX_NUMBER_OF_ROWS_AND_COLUMNS = 20;
    private Board board;
    private Pawn[][] pawnBoard;
    private boolean isGameRunning;
    private boolean hasWon;


    public void start() {

        TerminalView.clearScreen();
        int size;
        boolean isInputValid;

        do {
            String userInput = TerminalView.readInput("Give me a number between 10 and 20:");
            isInputValid = userInput.matches("^\\d{2}$") && Integer.parseInt(userInput) > 9 && Integer.parseInt(userInput) < 21;
            if (!isInputValid) System.out.print("Invalid input. Please retry. ");
            size = Integer.parseInt(userInput);
        } while (!isInputValid);

        board = new Board(size);
        board.initBoard(size);
        playRound();

    }

    public void playRound() {
        TerminalView.clearScreen();
        TerminalView.printBoard(board);
        System.out.println("White pawn makes the first move.");
        do {
            if (pawn.getColor().equals("white")) {

            }


        } while (!(hasWon));

        TerminalView.clearScreen();
        TerminalView.printBoard(board);
        printWinner(pawnBoard);

    }


    public boolean tryToMakeMove(Pawn[][] board, int fromX, int fromY, int toX, int toY) {
        boolean isMoveOK = false;
        if (board[fromX][fromY] != null && board[toX][toY] == null && targetFieldIsBlack(board)) {
            isMoveOK = true;
        } else {
            System.out.println("This is not a valid move. Try again.");
        }
        return isMoveOK;
    }

    public boolean twoFieldJump(int fromX, int fromY, int toX, int toY) {
        boolean isTwoField = false;
        int moveX = fromX - toX;
        int moveY = fromY - toY;
        if (moveX == Math.abs(2) && moveY == Math.abs(2)) {
            isTwoField = true;
        }
        return isTwoField;
    }

    public Coordinates interfieldCoord(int fromX, int fromY, int toX, int toY) {
        return new Coordinates(Math.abs((fromX + toX) / 2), Math.abs((fromY + toY) / 2));
    }

    public boolean targetFieldIsBlack(Pawn[][] board) {
        boolean isTargetBlack = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((i + j) % 2 != 0) {
                    isTargetBlack = true;
                }
            }
        }
        return isTargetBlack;
    }

    // checks the colorEnum of pawns on each field
    public String checkForWinner(Pawn[][] board) {
        int blacks = 0;
        int whites = 0;
        String winner = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getColor().equals(ColorEnum.BLACK)) {
                    blacks++;
                } else if (board[i][j].getColor().equals(ColorEnum.WHITE)) {
                    whites++;
                }
            }
        }
        if (blacks == 0) {
            winner = "white";

        } else if (whites == 0) {
            winner = "black";

        }
        hasWon = true;
        return winner;
    }


    public void printWinner(Pawn[][] board) {
        String winner = checkForWinner(board);
        String messageOnConsole = "";
        if (winner.equals( "white")) {
            messageOnConsole = "White has won.";
        } else if (winner.equals( "black")) {
            messageOnConsole = "Black has won.";
        }
        System.out.println(messageOnConsole);
        isGameRunning = false;
    }

    public int[] getMove(int player) {
        int[] coordinates = new int[NUMBER_OF_COORDINATE_ELEMENTS];
        String userInput;
        boolean isCoordinatesOutsideBoard = false;
        boolean isInputFormatValid;
        do {
            userInput = TerminalView.readInput("Player " + player + " comes next:");
            isInputFormatValid = TerminalView.isCoordinatesInputFormatValid(userInput);
            if (!isInputFormatValid) {
                System.out.println("Invalid input format. Please retry.");
                continue;
            }
            coordinates[0] = userInput.charAt(0) - ASCII_DEC_CODE_UPPERCASE_LETTER_A;
            coordinates[1] = Integer.parseInt(userInput.substring(1)) - INDEX_CORRECTION;

            isCoordinatesOutsideBoard = coordinates[0] > board.getSize() - INDEX_CORRECTION || coordinates[1] > board.getSize() - INDEX_CORRECTION;
            if (isCoordinatesOutsideBoard) {
                System.out.println("Invalid input: coordinates are outside of board. Please retry.");
            }
        } while (isCoordinatesOutsideBoard || !isInputFormatValid);
        return coordinates;
    }
}
