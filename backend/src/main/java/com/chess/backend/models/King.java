package com.chess.backend.models;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(PieceColor color, int row, int col, PieceType type) {
        super(color, row, col, type);
    }

    @Override
    public List<Move> getValidMoves(Board board) {
        List<Move> validMoves = new ArrayList<>();

        // King can move one square in any direction
        int[][] kingMoves = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, /* King stays in the same position (not included in queenMoves) */ {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] move : kingMoves) {
            int newRow = getRow() + move[0];
            int newCol = getCol() + move[1];

            if (isValidSquare(newRow, newCol, board)) {
                validMoves.add(new Move(this, getRow(), getCol(), newRow, newCol));
            }
        }

        return validMoves;
    }
}
