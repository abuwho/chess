package com.chess.backend.models;

import java.util.List;

public class Game {

    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private int turn;

    public Game() {
        this.board = new Board();
        this.whitePlayer = null;
        this.blackPlayer = null;
        this.turn = 1;
    }

    public Game(Player whitePlayer, Player blackPlayer) {
        this.board = new Board();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setWhitePlayer(Player whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public void setBlackPlayer(Player blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public int getTurn() {
        return this.turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void toggleTurn() {
        if (this.turn == 0) this.turn = 1;
        else this.turn = 0;
    }

    public void movePiece(int oldRow, int oldCol, int newRow, int newCol) {
        Piece piece = board.getPiece(oldRow, oldCol);
        Move move = new Move(piece, oldRow, oldCol, newRow, newCol);
        try {
            List<Move> validMoves = piece.getValidMoves(board);

            for (Move mv : validMoves) {
                if (mv.equals(move)) {
                    board.setPiece(newRow, newCol, piece);
                    board.setPiece(oldRow, oldCol, new EmptySquare(PieceColor.NULL, oldRow, oldCol, PieceType.EMPTY));
                }
            }
            this.toggleTurn();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String toFenString() {

        StringBuilder fenString = new StringBuilder();

        // board
        for (int i = 0; i < 8; i++) {
            int emptySquares = 0;
            for (int j = 0; j < 8; j++) {
                Piece piece = this.board.getPiece(i, j);
                if (piece.getType() == PieceType.EMPTY) {
                    emptySquares++;
                } else {
                    if (piece.getColor() == PieceColor.WHITE) fenString.append(piece.getType().toString().charAt(0));
                    else if (piece.getColor() == PieceColor.BLACK) fenString.append(piece.getType().toString().toLowerCase().charAt(0));
                }
            }
            if (emptySquares > 0) {
                fenString.append(emptySquares);
                emptySquares = 0;
            }

            if (i != 7) fenString.append('/');
        }

        return fenString.toString();
    }


    public void movePiece(Piece piece, int newRow, int newCol) {
        board.setPiece(newRow, newCol, piece);
        board.setPiece(piece.getRow(), piece.getCol(), new EmptySquare(PieceColor.NULL, piece.getRow(), piece.getCol(), PieceType.EMPTY));
    }

    public void printState() {
        board.printBoard();
    }

}
