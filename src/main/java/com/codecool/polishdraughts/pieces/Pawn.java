package com.codecool.polishdraughts.pieces;

public class Pawn {
    private ColorEnum color;
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

    public void setColor(ColorEnum color) {
        this.color = color;
    }

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public boolean isCrowned() {
        return isCrowned;
    }

    public void setCrowned(boolean crowned) {
        isCrowned = crowned;
    }

    public boolean isValidMove(Coordinates newpos) {
//Board needed?  isValidMove(Board board, Coordinates newpos)
        return false;
    }
}
