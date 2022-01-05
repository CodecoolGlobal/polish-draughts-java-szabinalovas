package com.codecool.polishdraughts.pieces;

public enum ColorEnum {

    BLACK("X"), WHITE("O");

    private String pawnChar;

    ColorEnum(String pChar) {
        this.pawnChar = pChar;
    }

    public String getPawnChar() {
        return pawnChar;
    }
}
