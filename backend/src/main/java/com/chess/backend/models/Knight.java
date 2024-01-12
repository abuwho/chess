package com.chess.backend.models;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(PieceColor color, int row, int col, PieceType type) {
        super(color, row, col, type);
    }

    @Override
    public List<Move> getValidMoves(Board board) {
        List<Move> validMoves = new ArrayList<>();

        // Knight can make L-shaped moves: two squares in one direction and one square in another
        int[][] knightMoves = {
                {-2, -1}, {-2, 1},
                {-1, -2}, {-1, 2},
                {1, -2}, {1, 2},
                {2, -1}, {2, 1}
        };

        for (int[] move : knightMoves) {
            int newRow = getRow() + move[0];
            int newCol = getCol() + move[1];

            if (isValidSquare(newRow, newCol, board)) {
                validMoves.add(new Move(this, getRow(), getCol(), newRow, newCol));
            }
        }

        return validMoves;
    }

}
