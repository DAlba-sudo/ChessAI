package backend.chess;

import backend.chess.pieces.old.*;
import backend.chess.pieces.revised.*;

public class ChessBoard {

    private PieceV2[][] board = new PieceV2[8][8];

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
                            // PawnV2 is white
                            board[r][c] = new PawnV2(r, c, 1);
                            break;
                        case 6:
                            // PawnV2 is black
                            board[r][c] = new PawnV2(r, c, 0);
                    }
                } else if (r == 0 || r == 7){
                    switch (r){
                        // pieces are white
                        case 0:
                            switch (c){
                                case 0:
                                    board[r][c] = new RookV2(r, c, 1);
                                    break;
                                case 1:
                                    board[r][c] = new KnightV2(r, c, 1);
                                    break;
                                case 2:
                                    board[r][c] = new BishopV2(r, c, 1);
                                    break;
                                case 3:
                                    board[r][c] = new QueenV2(r, c, 1);
                                    break;
                                case 4:
                                    board[r][c] = new KingV2(r, c, 1);
                                    break;
                                case 5:
                                    board[r][c] = new BishopV2(r, c, 1);
                                    break;
                                case 6:
                                    board[r][c] = new KnightV2(r, c, 1);
                                    break;
                                case 7:
                                    board[r][c] = new RookV2(r, c, 1);
                                    break;
                            }
                            break;
                        // pieces are black
                        case 7:
                            switch (c){
                                case 0:
                                    board[r][c] = new RookV2(r, c, 0);
                                    break;
                                case 1:
                                    board[r][c] = new KnightV2(r, c, 0);
                                    break;
                                case 2:
                                    board[r][c] = new BishopV2(r, c, 0);
                                    break;
                                case 3:
                                    board[r][c] = new QueenV2(r, c, 0);
                                    break;
                                case 4:
                                    board[r][c] = new KingV2(r, c, 0);
                                    break;
                                case 5:
                                    board[r][c] = new BishopV2(r, c, 0);
                                    break;
                                case 6:
                                    board[r][c] = new KnightV2(r, c, 0);
                                    break;
                                case 7:
                                    board[r][c] = new RookV2(r, c, 0);
                                    break;
                            }
                            break;
                    }
                }
            }
        }
    }

    public PieceV2[][] getBoard() {
        return board;
    }
}
