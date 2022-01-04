package com.codecool.polishdraughts.pieces;
public enum ColorEnum {
    BLACK(Pawn.ANSI_WHITE_BACKGROUND + Pawn.ANSI_BLACK +"O"+Pawn.ANSI_BLACK_BACKGROUND+Pawn.ANSI_RESET), WHITE("O");
    private String pownChar;

    ColorEnum(String pChar) {
        this.pownChar = pChar;
    }

    public String getPownChar() {
        return pownChar;
    }
}
