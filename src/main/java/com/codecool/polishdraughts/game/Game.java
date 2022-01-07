package com.codecool.polishdraughts.game;

import com.codecool.polishdraughts.board.Board;
import com.codecool.polishdraughts.pieces.ColorEnum;
import com.codecool.polishdraughts.pieces.Coordinates;
import com.codecool.polishdraughts.pieces.Pawn;
import com.codecool.polishdraughts.view.TerminalView;

public class Game implements GameInterface {

    private static final int ASCII_DEC_CODE_UPPERCASE_LETTER_A = 65;
    private static final int INDEX_CORRECTION = 1;
    public static final int MIN_NUMBER_OF_ROWS_AND_COLUMNS = 10;
    public static final int MAX_NUMBER_OF_ROWS_AND_COLUMNS = 20;

    private Board board;

    private boolean isGameRunning;
    private String player = "O";
    private Pawn actualPlayer;

    public void start() {
        TerminalView.clearScreen();
        int size;
        boolean isInputValid;
        do {
            String userInput = TerminalView.readInput("Give me a number between 10 and 20:");
            isInputValid = userInput.matches("^\\d{2}$") &&
                    (Integer.parseInt(userInput) >= MIN_NUMBER_OF_ROWS_AND_COLUMNS) &&
                    (Integer.parseInt(userInput) <= MAX_NUMBER_OF_ROWS_AND_COLUMNS);
            if (!isInputValid) System.out.print("Invalid input. Please retry. ");
            size = Integer.parseInt(userInput);
        } while (!isInputValid);
        board = new Board(size);
        this.isGameRunning = true;
        while (isGameRunning) {
            playRound(player);
            player = player.equals("O") ? ColorEnum.BLACK.getPawnChar() : "O";
        }
    }

    public void playRound(String player) {
        TerminalView.clearScreen();
        TerminalView.printBoard(board);
        actualPlayer = chooseValidPlayer(player);
        boolean isValidMove = false;
        Coordinates toCoordinate = null;
        do {
            String userInput = askForNextMove();
            if (userInput.equalsIgnoreCase("s")) {
                actualPlayer = chooseValidPlayer(player);
            } else {
                toCoordinate = convertToCoordinate(userInput);
                isValidMove = isValidMove(toCoordinate);
            }

        } while (!isValidMove);

        Coordinates interfieldCoordinate = interfieldCoordinate(actualPlayer.getPosition(), toCoordinate);
        if (twoFieldJump(actualPlayer.getPosition(), toCoordinate) && TerminalView.isValidEnemy(player, board, interfieldCoordinate)) {
            board.removePawn(board.getPawnsBoard()[interfieldCoordinate.getY()][interfieldCoordinate.getX()]);
        }
        board.movePawn(actualPlayer, toCoordinate);

        //checkForWinner();
        //printWinner(pawnBoard); // only prints if there is a winner
    }

    private Coordinates convertToCoordinate(String userInput) {
        return new Coordinates(userInput.charAt(0) - ASCII_DEC_CODE_UPPERCASE_LETTER_A,
                Integer.parseInt(userInput.substring(1)) - INDEX_CORRECTION);
    }

    private String askForNextMove() {
        String userInput = TerminalView.readInput("Pick a valid coordinate or select another Pawn (s): ");
        boolean isInputFormatValid = TerminalView.isCoordinatesInputFormatValid(userInput);
        if (!isInputFormatValid && !userInput.equalsIgnoreCase("s")) {
            System.out.println("Invalid input format. Please retry.");
        }
        return userInput;
    }

    private boolean isValidMove(Coordinates toCoordinate) {
        return !isCoordinatesOutsideBoard(toCoordinate) &&
                isActualPlayerSelected() &&
                isToCoordinateEmpty(toCoordinate) &&
                actualPlayer.isValidMoveByGameRules(toCoordinate) &&
                !isTwoFieldJumpAndNotEnemyPlayer(toCoordinate) &&
                isTargetFieldIsBlack();
    }

    private boolean isTwoFieldJumpAndNotEnemyPlayer(Coordinates toCoordinate) {
        boolean twoFieldJump = twoFieldJump(actualPlayer.getPosition(), toCoordinate);
        Coordinates interfieldCoordinate = interfieldCoordinate(actualPlayer.getPosition(), toCoordinate);
        return twoFieldJump && !TerminalView.isValidEnemy(player, board, interfieldCoordinate);
    }

    private boolean isToCoordinateEmpty(Coordinates toCoordinate) {
        return board.getPawnsBoard()[toCoordinate.getY()][toCoordinate.getX()] == null;
    }

    private boolean isActualPlayerSelected() {
        return actualPlayer != null;
    }

    private boolean isCoordinatesOutsideBoard(Coordinates toCoordinate) {
        return toCoordinate.getX() > board.getSize() - INDEX_CORRECTION
                || toCoordinate.getY() > board.getSize() - INDEX_CORRECTION;
    }

    public boolean twoFieldJump(Coordinates fromCoordinate, Coordinates toCoordinates) {
        boolean isTwoField = false;
        int moveX = fromCoordinate.getX() - toCoordinates.getX();
        int moveY = fromCoordinate.getY() - toCoordinates.getY();
        if (Math.abs(moveX) == 2 && 2 == Math.abs(moveY)) {
            isTwoField = true;
        }
        return isTwoField;
    }

    public Coordinates interfieldCoordinate(Coordinates fromCoordinate, Coordinates toCoordinates) {
        return new Coordinates(Math.abs((fromCoordinate.getY() + toCoordinates.getY()) / 2),
                Math.abs((fromCoordinate.getX() + toCoordinates.getX()) / 2));
    }

    public boolean isTargetFieldIsBlack() {
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
            fromCoordinate = convertToCoordinate(userInput);

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
}
