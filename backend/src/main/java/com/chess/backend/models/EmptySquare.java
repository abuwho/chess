package com.chess.backend.models;

import java.util.List;

public class EmptySquare extends Piece {
    public EmptySquare(PieceColor color, int row, int col, PieceType type) {
        super(color, row, col, type);
    }

    @Override
    public Boolean isFirstMove() {
        return null; 
    }

    @Override
    public List<Move> getValidMoves(Board board) {
        throw new UnsupportedOperationException("EmptySquare does not have any moves! EmptySquare.java");
    }
}
