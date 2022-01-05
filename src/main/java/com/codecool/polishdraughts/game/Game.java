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


    public void start() {
        int player = 1; //should be replaced with Pawn instance
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
        //this.isGameRunning = true; //commented out to prevent infinite loop
        while (isGameRunning) {
            playRound();    // valszeg kell neki egy player parameter
            player = player == 1 ? 2 : 1;   //should be replaced with Pawn instance
        }
    }

    public void playRound() {
        TerminalView.clearScreen();
        TerminalView.printBoard(board);
        //getMove(int player);
        //Pawn.isValidMove(Coordinates newPos);
        //tryToMakeMove;
        //Board.movePawn();
        //Board.removePawn(); - if needed
        //checkForWinner(); -> ennek kellenne meghívni a printWinner metodust
        //printWinner(pawnBoard); // only prints if there is a winner

    }

    // move can be made if:
    // 1. move is from a black to another black field
    // 2. starting field has a pawn
    // 3. target field is empty
    // returns true if move is valid -> Board movePawn()
    // returns false if move isn't valid -> player needs to provide another input
    public boolean tryToMakeMove(Pawn[][] board, int fromX, int fromY, int toX, int toY) {
        boolean isMoveOK = false;
        if (board[fromX][fromY] != null && board[toX][toY] == null && targetFieldIsBlack(board)) {
            //Board.movePawn(); to be implemented in Board class;
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
        return winner;
    }

    // should be moved to TerminalView
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
