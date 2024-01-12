package com.chess.backend.models;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(PieceColor color, int row, int col, PieceType type) {
        super(color, row, col, type);
    }

    @Override
    public List<Move> getValidMoves(Board board) {
        List<Move> validMoves = new ArrayList<>();

        // Bishop can move diagonally
        int[][] bishopMoves = {
                {-1, -1}, {-1, 1},
                {1, -1}, {1, 1}
        };

        for (int[] move : bishopMoves) {
            int newRow = getRow() + move[0];
            int newCol = getCol() + move[1];

            while (isValidSquare(newRow, newCol, board)) {
                validMoves.add(new Move(this, getRow(), getCol(), newRow, newCol));

                // Continue moving diagonally until an obstacle is encountered
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
