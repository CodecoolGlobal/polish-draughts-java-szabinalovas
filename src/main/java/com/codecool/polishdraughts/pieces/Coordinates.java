package com.codecool.polishdraughts.pieces;

public class Coordinates {
    private final int x;
    private final int y;

    public Coordinates(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
