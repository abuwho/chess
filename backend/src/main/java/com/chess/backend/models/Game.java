package com.chess.backend.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "games")
public class Game implements Serializable {

    @Id @GeneratedValue
    private Long id;

    private String fenString;
    private @Transient Board board;
    private @Transient Player whitePlayer;
    private @Transient Player blackPlayer;
    private @Transient GameTurn turn; // 1 -> white player's turn, 0 -> black player's turn
    private @Transient List<Move> moves;

    public Game() {
        this.board = new Board();
        this.whitePlayer = null;
        this.blackPlayer = null;
        this.turn = GameTurn.WHITE;
        this.moves = new ArrayList<Move>();
        this.fenString = this.toFenString();
    }

    public Game(Player whitePlayer, Player blackPlayer) {
        this.board = new Board();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.turn = GameTurn.WHITE;
        this.moves = new ArrayList<Move>();
        this.fenString = this.toFenString();
    }

    public Long getId() {
        return this.id;
    }

    public String getFenString() {
        return this.fenString;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setFenString() {
        this.fenString = this.toFenString();
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

    public GameTurn getTurn() {
        return this.turn;
    }

    public void setTurn(GameTurn turn) {
        this.turn = turn;
    }

    public void toggleTurn() {
        if (this.turn == GameTurn.WHITE) this.setTurn(GameTurn.BLACK);
        else this.setTurn(GameTurn.WHITE);
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

        
        if ((piece.getColor() == PieceColor.BLACK && this.turn == GameTurn.BLACK) || (piece.getColor() == PieceColor.WHITE && this.turn == GameTurn.WHITE)) {
            List<Move> validMoves = piece.getValidMoves(board);

            for (Move mv : validMoves) {
                if (mv.equals(move)) {
                    board.setPiece(newRow, newCol, piece);
                    board.setPiece(oldRow, oldCol, new EmptySquare(PieceColor.NULL, oldRow, oldCol, PieceType.EMPTY));
                    this.toggleTurn();
                    this.moves.add(mv);
                    break;
                }
            }
        } else {
            System.out.println("Invalid move: Player tried to make a move when it is not their turn.");
        }
    }

    // Implemented using the logic descriped on the Wiki page https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation
    public String toFenString() {

        StringBuilder fenString = new StringBuilder();

        // 1. Piece placement data
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
        fenString.append(' ');

        // 2. Active color
        if (this.getTurn() == GameTurn.WHITE) fenString.append('w');
        else fenString.append('b');
        fenString.append(' ');


        // 3. Castling availability (https://en.wikipedia.org/wiki/Castling)
        Boolean canWhiteKingSideCastle = this.board.getPiece(7, 4).isFirstMove() && this.board.getPiece(7, 7).isFirstMove();
        Boolean canWhiteQueenSideCastle = this.board.getPiece(7, 4).isFirstMove() && this.board.getPiece(7, 0).isFirstMove();
        Boolean canBlackKingSideCastle = this.board.getPiece(0, 4).isFirstMove() && this.board.getPiece(0, 7).isFirstMove();
        Boolean canBlackQueenSideCastle = this.board.getPiece(0, 4).isFirstMove() && this.board.getPiece(0, 0).isFirstMove();
        // a. if White can castle king-side
        if (canWhiteKingSideCastle) fenString.append('K');
        // b. if White can castle queen-side
        if (canWhiteQueenSideCastle) fenString.append('Q');
        // c. if Black can castle king-side
        if (canBlackKingSideCastle) fenString.append('k');
        // d. if Black can castle queen-side
        if (canBlackQueenSideCastle) fenString.append('q');
        // if no castling is possible
        if (!canWhiteKingSideCastle && !canWhiteQueenSideCastle && !canBlackKingSideCastle && !canBlackQueenSideCastle) fenString.append('-');
        // eventually just add a space sequence
        fenString.append(' ');

        // 4. En passant target square (To-do)
        if (this.getMoves().size() > 0) {
            Move latestMove = this.moves.get(moves.size() - 1);

            char[] cols = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

            if (latestMove.getPiece().getType() == PieceType.PAWN && Math.abs(latestMove.getStartRow() - latestMove.getEndRow()) == 2) {
                fenString.append(cols[latestMove.getPiece().getCol()]);
                if (latestMove.getPiece().getColor() == PieceColor.WHITE) {
                    fenString.append(latestMove.getEndRow() - 1);
                } else if (latestMove.getPiece().getColor() == PieceColor.BLACK) {
                    fenString.append(8 - latestMove.getEndRow() + 1);
                }
            } else {
                fenString.append('-');
            }
        } else {
            fenString.append('-');
        }
        // eventually just add a space sequence
        fenString.append(' ');

    
        return fenString.toString();
    }


    public void movePiece(Piece piece, int newRow, int newCol) {
        board.setPiece(newRow, newCol, piece);
        board.setPiece(piece.getRow(), piece.getCol(), new EmptySquare(PieceColor.NULL, piece.getRow(), piece.getCol(), PieceType.EMPTY));
    }

    public void printState() {
        board.printBoard();
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + this.id + ", fenString='" + this.fenString + "'";
    }

    // public static void main(String args[]) {

    //     Player p1 = new Player("alice", "alice@alice.com");
    //     Player p2 = new Player("bob", "bob@bob.com");

    //     Game game = new Game(p1, p2);

    //     game.setWhitePlayer(p1);
    //     game.setBlackPlayer(p2);
    //     System.out.println(game.toFenString()); 

    
    //     System.out.println( game.getMoves());
    //     game.movePiece(6, 0, 4, 0);
    //     game.printState();

    //     System.out.println(game.toFenString());

    //     game.movePiece(1, 0, 3, 0);
    //     game.printState();
    //     System.out.println(game.toFenString());

    // }
}
