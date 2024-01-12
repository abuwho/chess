package com.chess.backend.models;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(PieceColor color, int row, int col, PieceType type) {
        super(color, row, col, type);
    }

    private Boolean isFirstMove() {
        return (getColor() == PieceColor.WHITE && getRow() == 6) || (getColor() == PieceColor.BLACK && getRow() == 1);
    }
    
    public List<Move> getValidMoves() {
        throw new UnsupportedOperationException("Not supported yet (Pawn.java). Parameterless getValidMoves() is not supported for Pawn.");
    }

    @Override
    public List<Move> getValidMoves(Board board) {
        List<Move> validMoves = new ArrayList<>();
        
        int direction = (getColor().equals(PieceColor.WHITE)) ? -1 : 1;

        // Move one square forward
        int newRow = getRow() + direction;
        if (isValidSquare(newRow, getCol(), board)) {
            validMoves.add(new Move(this, getRow(), getCol(), newRow, getCol()));
        }

        // Move two squares forward (if it's the pawn's first move)
        if (isFirstMove() && isValidSquare(newRow + direction, getCol(), board)) {
            validMoves.add(new Move(this, getRow(), getCol(), newRow + direction, getCol()));
        }

        // Check diagonal squares for capturing
        int[] captureCols = {getCol() - 1, getCol() + 1};
        for (int column : captureCols) {
            if (isValidSquare(newRow, column, board)) {
                validMoves.add(new Move(this, getRow(), getCol(), newRow, column));
            }
        }

        return validMoves;
    }



}
