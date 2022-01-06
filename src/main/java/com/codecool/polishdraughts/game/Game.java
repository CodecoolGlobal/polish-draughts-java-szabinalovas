package com.codecool.polishdraughts.game;

import com.codecool.polishdraughts.board.Board;
import com.codecool.polishdraughts.pieces.ColorEnum;
import com.codecool.polishdraughts.pieces.Coordinates;
import com.codecool.polishdraughts.pieces.Pawn;
import com.codecool.polishdraughts.view.TerminalView;

public class Game implements GameInterface {

    private static final int ASCII_DEC_CODE_UPPERCASE_LETTER_A = 65;
    private static final int INDEX_CORRECTION = 1;

    private Board board;

    private boolean isGameRunning;


    public void start() {
        String player = "O"; //should be replaced with Pawn instance
        TerminalView.clearScreen();
        int size;
        boolean isInputValid;
        do {
            String userInput = TerminalView.readInput("Give me a number between 10 and 20:");
            isInputValid = userInput.matches("^\\d{2}$") && (Integer.parseInt(userInput) > 9) && (Integer.parseInt(userInput) < 21);
            if (!isInputValid) System.out.print("Invalid input. Please retry. ");
            size = Integer.parseInt(userInput);
        } while (!isInputValid);
        board = new Board(size);
        this.isGameRunning = true; //commented out to prevent infinite loop
        while (isGameRunning) {
            playRound(player);    // valszeg kell neki egy player parameter
            player = player.equals("O") ? "X" : "O";   //should be replaced with Pawn instance
        }
    }

    public void playRound(String player) {
        TerminalView.clearScreen();
        TerminalView.printBoard(board);
        Pawn actualPlayer = chooseValidPlayer(player);
        Coordinates toCoordinate = chooseValidStep(actualPlayer);
        board.movePawn(actualPlayer, toCoordinate);
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
    public boolean tryToMakeMove(Pawn actualPlayer, Coordinates toCoordinate) {
        boolean isMoveOK = false;
        if ((actualPlayer != null && toCoordinate != null) &&
                (board.getPawnsBoard()[toCoordinate.getY()][toCoordinate.getX()] == null &&
                        targetFieldIsBlack(actualPlayer))) {
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

    public boolean targetFieldIsBlack(Pawn actualPlayer) {

        boolean isTargetBlack = false;
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
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
        for (Pawn[] pawns : board) {
            for (Pawn pawn : pawns) {
                if (pawn.getColor().equals(ColorEnum.BLACK)) {
                    blacks++;
                } else if (pawn.getColor().equals(ColorEnum.WHITE)) {
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
        if (winner.equals("white")) {
            messageOnConsole = "White has won.";
        } else if (winner.equals("black")) {
            messageOnConsole = "Black has won.";
        }
        System.out.println(messageOnConsole);
        isGameRunning = false;
    }

    public Pawn chooseValidPlayer(String player) {
        String userInput;
        boolean isCoordinatesOutsideBoard = false;
        boolean isInputFormatValid;
        boolean isValidPlayer = false;
        Coordinates fromCoordinate = null;
        do {
            userInput = TerminalView.readInput("Player " + player + " comes next:");
            isInputFormatValid = TerminalView.isCoordinatesInputFormatValid(userInput);
            if (!isInputFormatValid) {
                System.out.println("Invalid input format. Please retry.");
                continue;
            }
            fromCoordinate = new Coordinates(userInput.charAt(0) - ASCII_DEC_CODE_UPPERCASE_LETTER_A,
                    Integer.parseInt(userInput.substring(1)) - INDEX_CORRECTION);

            isCoordinatesOutsideBoard = fromCoordinate.getX() > board.getSize() - INDEX_CORRECTION || fromCoordinate.getY() > board.getSize() - INDEX_CORRECTION;
            if (isCoordinatesOutsideBoard) {
                System.out.println("Invalid input: coordinates are outside of board. Please retry.");
            }
            isValidPlayer = TerminalView.isValidPlayer(player, board, fromCoordinate);
            if (!isValidPlayer) {
                System.out.println("Invalid player.");
            }

        } while (isCoordinatesOutsideBoard || !isInputFormatValid || !isValidPlayer);

        return board.getPawnsBoard()[fromCoordinate.getY()][fromCoordinate.getX()];
    }

    public Coordinates chooseValidStep(Pawn actualPlayer) {
        String userInput;
        boolean isCoordinatesOutsideBoard = false;
        boolean isInputFormatValid;
        boolean tryToMakeMove = false;
        boolean isValidMove = false;
        Coordinates toCoordinate = null;
        do {
            userInput = TerminalView.readInput("Pick a valid coordinate:");
            isInputFormatValid = TerminalView.isCoordinatesInputFormatValid(userInput);
            if (!isInputFormatValid) {
                System.out.println("Invalid input format. Please retry.");
                continue;
            }
            toCoordinate = new Coordinates(userInput.charAt(0) - ASCII_DEC_CODE_UPPERCASE_LETTER_A,
                    Integer.parseInt(userInput.substring(1)) - INDEX_CORRECTION);

            isCoordinatesOutsideBoard = toCoordinate.getX() > board.getSize() - INDEX_CORRECTION || toCoordinate.getY() > board.getSize() - INDEX_CORRECTION;
            if (isCoordinatesOutsideBoard) {
                System.out.println("Invalid input: coordinates are outside of board. Please retry.");
            }
            tryToMakeMove = tryToMakeMove(actualPlayer, toCoordinate);
            if (!tryToMakeMove) {
                System.out.println("Invalid move.");
            }
            isValidMove = actualPlayer.isValidMove(toCoordinate, actualPlayer.getPosition());
            if (!isValidMove) {
                System.out.println("Invalid move.");
            }

        } while (isCoordinatesOutsideBoard || !isInputFormatValid || !tryToMakeMove || !isValidMove);

        return toCoordinate;
    }
}
