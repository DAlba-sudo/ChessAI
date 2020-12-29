package backend.chess;

import backend.chess.pieces.*;

public class ChessBoard {

    private Piece[][] board = new Piece[8][8];

    public ChessBoard(){
        populateBoard();
    }

    private void populateBoard(){
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){

                if(r == 1 || r == 6){
                    board[r][c] = new Pawn(r, c);
                } else if (r == 0 || r == 7){
                    switch (c){
                        case 0:
                            board[r][c] = new Rook(r, c);
                            break;
                        case 1:
                            board[r][c] = new Knight(r, c);
                            break;
                        case 2:
                            board[r][c] = new Bishop(r, c);
                            break;
                        case 3:
                            board[r][c] = new Queen(r, c);
                            break;
                        case 4:
                            board[r][c] = new King(r, c);
                            break;
                        case 5:
                            board[r][c] = new Bishop(r, c);
                            break;
                        case 6:
                            board[r][c] = new Knight(r, c);
                            break;
                        case 7:
                            board[r][c] = new Rook(r, c);
                            break;
                    }
                }
            }
        }
    }

    public boolean move(String notation){
        return false;
    }


}
