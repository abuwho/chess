package com.chess.backend.models;

public class Move {
    private Piece piece;
    private int startRow;
    private int startCol;
    private int endRow;
    private int endCol;

    public Move(Piece piece, int startRow, int startCol, int endRow, int endCol) {
        this.piece = piece;
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndCol() {
        return endCol;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public void setStartCol(int startCol) {
        this.startCol = startCol;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public void setEndCol(int endCol) {
        this.endCol = endCol;
    }

    public String toString() {
        return piece + " from (" + startRow + ", " + startCol + ") to (" + endRow + ", " + endCol + ")";
    }

    @Override
    public boolean equals(Object obj) {

        Move move = (Move) obj;

        if (move.getStartRow() == this.getStartRow() && move.getStartCol() == this.getStartCol() && move.getEndRow() == this.getEndRow() && move.getEndCol() == this.getEndCol() && move.getPiece().getType().equals(this.getPiece().getType())) {
            return true;
        }
        
        return false;
    }

}
