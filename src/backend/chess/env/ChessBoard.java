package backend.chess.env;

import backend.chess.pieces.*;

import java.util.Arrays;
import java.util.LinkedList;

public class ChessBoard {
    private LinkedList<Piece> pieces = new LinkedList<>();
    public ChessBoard(){
        setUpPieces();
    }

    public void setUpPieces(){
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                setPawn(r, c);
                setRook(r, c);
                setBishop(r, c);
                setKnight(r, c);
                setQueen(r, c);
                setKing(r, c);
            }
        }
    }

    public void displayBoard(){
        System.out.println("--------------------------");
        String[][] piecesOnBoard = boardToString();
        for(int r = 7; r >= 0; r--){
            System.out.println();
            for(int c = 0; c < 8; c++){
                System.out.print(piecesOnBoard[r][c]);
            }
        }
        System.out.println();
        System.out.println("--------------------------");
    }

    public Piece locatePiece(int[] coordinate){
        for(Piece p : pieces){
            if(Arrays.equals(p.getPos(), coordinate)){
                return p;
            }
        }
        return null;
    }

    private String[][] boardToString(){
        String[][] board = new String[8][8];
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                int[] pos = {c, r};
                Piece piece_on_square = locatePiece(pos);
                String piece_abbreviation = "";
                if(piece_on_square != null){
                    // if we have a piece on the square
                    Character abbreviation = piece_on_square.getAbbreviation();
                    if(abbreviation != null){
                        // i.e., not a pawn
                        if(piece_on_square.getColor() == 1){
                            // piece is white
                            piece_abbreviation = " " + abbreviation + " ";
                        } else {
                            // piece is black
                            piece_abbreviation = " " + Character.toLowerCase(abbreviation) + " ";
                        }
                    } else {
                        if(piece_on_square.getColor() == 1){
                            // piece is white
                            piece_abbreviation = " P ";
                        } else {
                            // piece is black
                            piece_abbreviation = " p ";
                        }
                    }
                } else {
                    // no pieces on square
                    piece_abbreviation = " - ";
                }
                board[r][c] = piece_abbreviation;
            }
        }
        return board;
    }

    private void setKing(int r, int c) {
        if((r == 0 || r == 7) && c == 3){
            int[] pos = {c, r};
            switch (r){
                case 0:
                    // white pawns
                    pieces.add(new King(1, pos));
                    break;
                case 7:
                    // black pawns
                    pieces.add(new King(0, pos));
                    break;
            }
        }
    }

    private void setQueen(int r, int c) {
        if((r == 0 || r == 7) && c == 4){
            int[] pos = {c, r};
            switch (r){
                case 0:
                    // white pawns
                    pieces.add(new Queen(1, pos));
                    break;
                case 7:
                    // black pawns
                    pieces.add(new Queen(0, pos));
                    break;
            }
        }
    }

    private void setKnight(int r, int c) {
        if((r == 0 || r == 7) && (c == 1 || c == 6)){
            int[] pos = {c, r};
            switch (r){
                case 0:
                    // white pawns
                    pieces.add(new Knight(1, pos));
                    break;
                case 7:
                    // black pawns
                    pieces.add(new Knight(0, pos));
                    break;
            }
        }
    }

    private void setBishop(int r, int c) {
        if((r == 0 || r == 7) && (c == 2 || c == 5)){
            int[] pos = {c, r};
            switch (r){
                case 0:
                    // white pawns
                    pieces.add(new Bishop(1, pos));
                    break;
                case 7:
                    // black pawns
                    pieces.add(new Bishop(0, pos));
                    break;
            }
        }
    }

    private void setRook(int r, int c){
        if((r == 0 || r == 7) && (c == 0 || c == 7)){
            int[] pos = {c, r};
            switch (r){
                case 0:
                    // white pawns
                    pieces.add(new Rook(1, pos));
                    break;
                case 7:
                    // black pawns
                    pieces.add(new Rook(0, pos));
                    break;
            }
        }
    }

    private void setPawn(int r, int c){
        if(r == 1 || r == 6){
            int[] pos = {c, r};
            switch (r){
                case 1:
                    // white pawns
                    pieces.add(new Pawn(1, pos));
                    break;
                case 6:
                    // black pawns
                    pieces.add(new Pawn(0, pos));
                    break;
            }
        }
    }

    public LinkedList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(LinkedList<Piece> pieces) {
        this.pieces = pieces;
    }

    public boolean validateMove(int[] to, boolean isCapture, Piece piece) {
        boolean pieceLogicApplies = piece.isMoveLegal(to, isCapture);
        boolean pieceInTheWay = true;
        if(!piece.getCanJump()){
            // if the piece can't jump, make sure that we gucci with the
            // movement
            LinkedList<int[]> squares_to_check = CM.getSquaresBetween(to, piece.getPos());
            for(int[] square : squares_to_check){
                Piece p = locatePiece(square);
                if(p != null){
                    // if the piece can't jump, and there is a piece in the way
                    // say that it is an invalid move
                    if(isCapture && Arrays.equals(square, to) && p.getColor() != piece.getColor()){
                        // if we are capturing at the target square and it's not our own pieces,
                        // it is a valid move
                        pieceInTheWay = false;
                    } else {
                        // if there is a piece, but it is not the one we want to capture, it doesn't work!
                        pieceInTheWay = false;
                    }
                }
            }
            return pieceLogicApplies && pieceInTheWay;
        } else {
            return pieceLogicApplies;
        }
    }
}
