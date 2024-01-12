package com.chess.backend.models;

public class Board {
    private Piece[][] board;

    public Board() {
        this.board = new Piece[8][8];

        // initialize pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(PieceColor.BLACK, 1, i, PieceType.PAWN);
            board[6][i] = new Pawn(PieceColor.WHITE, 6, i, PieceType.PAWN);
        }

        // initialize rooks
        board[0][0] = new Rook(PieceColor.BLACK, 0, 0, PieceType.ROOK);
        board[0][7] = new Rook(PieceColor.BLACK, 0, 7, PieceType.ROOK);
        board[7][0] = new Rook(PieceColor.WHITE, 7, 0, PieceType.ROOK);
        board[7][7] = new Rook(PieceColor.WHITE, 7, 7, PieceType.ROOK);

        // initialize knights
        board[0][1] = new Knight(PieceColor.BLACK, 0, 1, PieceType.KNIGHT);
        board[0][6] = new Knight(PieceColor.BLACK, 0, 6, PieceType.KNIGHT);
        board[7][1] = new Knight(PieceColor.WHITE, 7, 1, PieceType.KNIGHT);
        board[7][6] = new Knight(PieceColor.WHITE, 7, 6, PieceType.KNIGHT);

        // initialize bishops
        board[0][2] = new Bishop(PieceColor.BLACK, 0, 2, PieceType.BISHOP);
        board[0][5] = new Bishop(PieceColor.BLACK, 0, 5, PieceType.BISHOP);
        board[7][2] = new Bishop(PieceColor.WHITE, 7, 2, PieceType.BISHOP);
        board[7][5] = new Bishop(PieceColor.WHITE, 7, 5, PieceType.BISHOP);

        // initialize queens
        board[0][3] = new Queen(PieceColor.BLACK, 0, 3, PieceType.QUEEN);
        board[7][3] = new Queen(PieceColor.WHITE, 7, 3, PieceType.QUEEN);

        // initialize kings
        board[0][4] = new King(PieceColor.BLACK, 0, 4, PieceType.KING);
        board[7][4] = new King(PieceColor.WHITE, 7, 4, PieceType.KING);

        // initialize empty squares
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new EmptySquare(PieceColor.NULL, i, j, PieceType.EMPTY);
            }
        }
    }

    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            System.out.print(8 - i + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j].getColor().toString() + board[i][j].getType().toString() +" ");
            }
            System.out.println();
        }
        System.out.println("  a \t b \t c \t d \t e \t f \t g \t h");
    }

}
