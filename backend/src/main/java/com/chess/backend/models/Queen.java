package com.chess.backend.models;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(PieceColor color, int row, int col, PieceType type) {
        super(color, row, col, type);
    }

    @Override
    public List<Move> getValidMoves(Board board) {
        List<Move> validMoves = new ArrayList<>();

        // Queen can move horizontally, vertically, and diagonally (combining Rook and Bishop moves)
        int[][] queenMoves = {
                {-1, 0}, {1, 0}, // Vertical
                {0, -1}, {0, 1}, // Horizontal
                {-1, -1}, {-1, 1}, // Diagonal
                {1, -1}, {1, 1} // Diagonal
        };

        for (int[] move : queenMoves) {
            int newRow = getRow() + move[0];
            int newCol = getCol() + move[1];

            while (isValidSquare(newRow, newCol, board)) {
                validMoves.add(new Move(this, getRow(), getCol(), newRow, newCol));

                // Continue moving in the same direction until an obstacle is encountered
                if (!isEmptySquare(newRow, newCol, board)) {
                    break;
                }

                newRow += move[0];
                newCol += move[1];
            }
        }

        return validMoves;
    }
}
