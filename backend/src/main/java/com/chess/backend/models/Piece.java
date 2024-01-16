package com.chess.backend.models;

import java.util.List;

public abstract class Piece {
    private PieceColor color;
    private int row;
    private int col;
    private PieceType type;

    public Piece(PieceColor color, int row, int col, PieceType type) {
        this.color = color;
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public PieceColor getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType newType) {
        this.type = newType;
    }

    public String toString() {
        return color + " " + type;
    }

    public Boolean isMoveWithinBound(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public Boolean isEmptySquare(int row, int col, Board board) {
        return isMoveWithinBound(row, col) && board.getPiece(row, col).getType() == PieceType.EMPTY;
    }

    public Boolean isOpponentPiece(int row, int col, Board board) {
        return isMoveWithinBound(row, col) && !isEmptySquare(row, col, board) && board.getPiece(row, col).getColor() != color;
    }

    public Boolean isValidSquare(int row, int col, Board board) {
        return isMoveWithinBound(row, col) && (isEmptySquare(row, col, board) || isOpponentPiece(row, col, board));
    }

    // Abstract method to be implemented by each piece subclass separately
    public abstract List<Move> getValidMoves(Board board);

    // Abstract method to be implemented by each piece subclass separately
    public abstract Boolean isFirstMove();

}
