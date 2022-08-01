package com.codecool.polishdraughts.pieces;

public class Pawn {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private final ColorEnum color;
    private Coordinates position;
    private boolean isCrowned;

    public Pawn(ColorEnum color, Coordinates position) {
        this.color = color;
        this.position = position;
        this.isCrowned = false;
    }


    public ColorEnum getColor() {
        return color;
    }

    public String getPawnChar() {
        return this.color.getPawnChar();
    }

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public boolean isValidMoveByGameRules(Coordinates newPos) {
        if (newPos.getX() < 0 || newPos.getY() < 0) return false;
        int moveX = getPosition().getX() - newPos.getX();
        int moveY = getPosition().getY() - newPos.getY();
        if (this.getColor() == ColorEnum.WHITE) {
            if (moveX == 1 && moveY == 1) return true;
            else if (moveX == 2 && moveY == 2) return true;
            else if (moveX == -1 && moveY == 1) return true;
            else if (moveX == -2 && moveY == 2) return true;
            else return false;
        } else {
            if (moveX == 1 && moveY == -1) return true;
            else if (moveX == 2 && moveY == -2) return true;
            else if (moveX == -1 && moveY == -1) return true;
            else if (moveX == -2 && moveY == -2) return true;
            else return false;
        }
    }
}
