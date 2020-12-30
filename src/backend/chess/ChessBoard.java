package backend.chess;

import backend.chess.pieces.old.*;

public class ChessBoard {

    private Piece[][] board = new Piece[8][8];

    public ChessBoard(){
        populateBoard();
    }

    // adds the pieces to the board
    private void populateBoard(){
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                if(r == 1 || r == 6){
                    switch (r){
                        case 1:
                            // pawn is white
                            board[r][c] = new Pawn(r, c, 1);
                            break;
                        case 6:
                            // pawn is black
                            board[r][c] = new Pawn(r, c, 0);
                    }
                } else if (r == 0 || r == 7){
                    switch (r){
                        // pieces are white
                        case 0:
                            switch (c){
                                case 0:
                                    board[r][c] = new Rook(r, c, 1);
                                    break;
                                case 1:
                                    board[r][c] = new Knight(r, c, 1);
                                    break;
                                case 2:
                                    board[r][c] = new Bishop(r, c, 1);
                                    break;
                                case 3:
                                    board[r][c] = new Queen(r, c, 1);
                                    break;
                                case 4:
                                    board[r][c] = new King(r, c, 1);
                                    break;
                                case 5:
                                    board[r][c] = new Bishop(r, c, 1);
                                    break;
                                case 6:
                                    board[r][c] = new Knight(r, c, 1);
                                    break;
                                case 7:
                                    board[r][c] = new Rook(r, c, 1);
                                    break;
                            }
                            break;
                        // pieces are black
                        case 7:
                            switch (c){
                                case 0:
                                    board[r][c] = new Rook(r, c, 0);
                                    break;
                                case 1:
                                    board[r][c] = new Knight(r, c, 0);
                                    break;
                                case 2:
                                    board[r][c] = new Bishop(r, c, 0);
                                    break;
                                case 3:
                                    board[r][c] = new Queen(r, c, 0);
                                    break;
                                case 4:
                                    board[r][c] = new King(r, c, 0);
                                    break;
                                case 5:
                                    board[r][c] = new Bishop(r, c, 0);
                                    break;
                                case 6:
                                    board[r][c] = new Knight(r, c, 0);
                                    break;
                                case 7:
                                    board[r][c] = new Rook(r, c, 0);
                                    break;
                            }
                            break;
                    }
                }
            }
        }
    }

    public Piece[][] getBoard() {
        return board;
    }
}
