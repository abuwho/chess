package com.chess.backend.models;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private int turn; // 1 -> white player's turn, 0 -> black player's turn
    private List<Move> moves;

    public Game() {
        this.board = new Board();
        this.whitePlayer = null;
        this.blackPlayer = null;
        this.turn = 1;
        this.moves = new ArrayList<Move>();
    }

    public Game(Player whitePlayer, Player blackPlayer) {
        this.board = new Board();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.turn = 1;
        this.moves = new ArrayList<Move>();
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
        if (this.turn == 0) this.setTurn(1);
        else this.setTurn(0);
    }

    public List<Move> getMoves() {
        return this.moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public void movePiece(int oldRow, int oldCol, int newRow, int newCol) {
        Piece piece = board.getPiece(oldRow, oldCol);
        Move move = new Move(piece, oldRow, oldCol, newRow, newCol);

        
        if ((piece.getColor() == PieceColor.BLACK && this.turn == 0) || (piece.getColor() == PieceColor.WHITE && this.turn == 1)) {
            List<Move> validMoves = piece.getValidMoves(board);

            for (Move mv : validMoves) {
                if (mv.equals(move)) {
                    board.setPiece(newRow, newCol, piece);
                    board.setPiece(oldRow, oldCol, new EmptySquare(PieceColor.NULL, oldRow, oldCol, PieceType.EMPTY));
                    this.toggleTurn();
                    this.moves.add(mv);
                }
            }
            System.out.println("Invalid move: list of valid moves does not include the tried move.");
        } else {
            System.out.println("Invalid move: Player tried to make a move when it is not their turn.");
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

    public static void main(String args[]) {

        Game game = new Game();
        Player p1 = new Player("alice", "alice@alice.com");
        Player p2 = new Player("bob", "bob@bob.com");

        game.setWhitePlayer(p1);
        game.setBlackPlayer(p2);

        System.out.println( game.getMoves());
        game.movePiece(6, 0, 2, 0);
        game.printState();

    }

}
