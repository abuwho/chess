package com.chess.backend.models;

import java.util.List;

import jakarta.persistence.Entity;


@Entity
public class Game {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;

    public Game() {
        this.board = new Board();
        this.whitePlayer = null;
        this.blackPlayer = null;
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void movePiece(Piece piece, int newRow, int newCol) {
        board.setPiece(newRow, newCol, piece);
        board.setPiece(piece.getRow(), piece.getCol(), new EmptySquare(PieceColor.NULL, piece.getRow(), piece.getCol(), PieceType.EMPTY));
    }

    public void printState() {
        board.printBoard();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.printState();
        game.movePiece(1, 0, 3, 0);
        game.printState();
    }
}
