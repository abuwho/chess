package com.chess.backend.models;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {    
    public Rook(PieceColor color, int row, int col, PieceType type) {
        super(color, row, col, type);
    }
    
    
    @Override
    public List<Move> getValidMoves(Board board) {
        List<Move> validMoves = new ArrayList<>();

        // Rooks can move horizontally and vertically
        // Check horizontal moves
        for (int column = 0; column < 8; column++) {
            if (column != getCol() && isValidSquare(getRow(), column, board)) {
                validMoves.add(new Move(this, getRow(), getCol(), getRow(), column));
            }
        }

        // Check vertical moves
        for (int r = 0; r < 8; r++) {
            if (r != getRow() && isValidSquare(r, getCol(), board)) {
                validMoves.add(new Move(this, getRow(), getCol(), r, getCol()));
            }
        }

        return validMoves;
    }
}
