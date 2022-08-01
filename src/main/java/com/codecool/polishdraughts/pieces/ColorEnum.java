package com.codecool.polishdraughts.pieces;

public enum ColorEnum {

    BLACK(Pawn.ANSI_WHITE_BACKGROUND + Pawn.ANSI_BLACK + "X" + Pawn.ANSI_BLACK_BACKGROUND + Pawn.ANSI_RESET), WHITE("O");

    private String pawnChar;

    ColorEnum(String pChar) {
        this.pawnChar = pChar;
    }

    public String getPawnChar() {
        return pawnChar;
    }
}
