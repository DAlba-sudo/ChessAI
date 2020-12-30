package backend.chess;

import backend.chess.pieces.old.*;
import backend.chess.pieces.revised.*;

import java.util.Arrays;

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
                            board[r][c] = new PawnV2(c, r, 1);
                            break;
                        case 6:
                            // PawnV2 is black
                            board[r][c] = new PawnV2(c, r, 0);
                    }
                } else if (r == 0 || r == 7){
                    switch (r){
                        // pieces are white
                        case 0:
                            switch (c){
                                case 0:
                                    board[r][c] = new RookV2(c, r, 1);
                                    break;
                                case 1:
                                    board[r][c] = new KnightV2(c, r, 1);
                                    break;
                                case 2:
                                    board[r][c] = new BishopV2(c, r, 1);
                                    break;
                                case 3:
                                    board[r][c] = new QueenV2(c, r, 1);
                                    break;
                                case 4:
                                    board[r][c] = new KingV2(c, r, 1);
                                    break;
                                case 5:
                                    board[r][c] = new BishopV2(c, r, 1);
                                    break;
                                case 6:
                                    board[r][c] = new KnightV2(c, r, 1);
                                    break;
                                case 7:
                                    board[r][c] = new RookV2(c, r, 1);
                                    break;
                            }
                            break;
                        // pieces are black
                        case 7:
                            switch (c){
                                case 0:
                                    board[r][c] = new RookV2(c, r, 0);
                                    break;
                                case 1:
                                    board[r][c] = new KnightV2(c, r, 0);
                                    break;
                                case 2:
                                    board[r][c] = new BishopV2(c, r, 0);
                                    break;
                                case 3:
                                    board[r][c] = new QueenV2(c, r, 0);
                                    break;
                                case 4:
                                    board[r][c] = new KingV2(c, r, 0);
                                    break;
                                case 5:
                                    board[r][c] = new BishopV2(c, r, 0);
                                    break;
                                case 6:
                                    board[r][c] = new KnightV2(c, r, 0);
                                    break;
                                case 7:
                                    board[r][c] = new RookV2(c, r, 0);
                                    break;
                            }
                            break;
                    }
                }
            }
        }
    }

    public void display(){
        String[][] board = new String[8][8];
        System.out.println();
        for(int r = board.length-1; r >= 0; r--){
            System.out.println();
            for(int c = 0; c < board[r].length; c++){
                // find piece that matches current_position
                int[] coordinate = {c+1, r+1};
                PieceV2 tempPiece = findPieceByCoordinate(coordinate);
                if(tempPiece != null){
                    if(tempPiece.getNotation() != null){
                        if(tempPiece.getColor() == 1){
                            System.out.print(" " + Character.toUpperCase(tempPiece.getNotation()) + " ");
                        } else {
                            System.out.print(" " + Character.toLowerCase(tempPiece.getNotation()) + " ");
                        }
                    } else {
                        if(tempPiece.getColor() == 1){
                            System.out.print(" " + Character.toUpperCase('p') + " ");
                        } else {
                            System.out.print(" " + Character.toLowerCase('p') + " ");
                        }
                    }
                } else {
                    System.out.print(" - ");
                }
            }
        }
    }

    public PieceV2 findPieceByCoordinate(int[] coordinate){
        for(PieceV2[] rank : board){
            for(PieceV2 piece : rank){
                if(piece != null){
                    if(Arrays.equals(coordinate, piece.getCurrentCoordinate())){
                        return piece;
                    }
                }
            }
        }
        return null;
    }

    public boolean checkIfPiecesCollide(PieceV2 p1, String notation, boolean isCapturing){
        //TODO: Fix this function
        int[] target_location = ChessManager.getCoordinateToMoveTo(notation);

        boolean isDiagonal = p1.isMovingDiagonal(target_location[0], target_location[1]);
        if(isDiagonal){
            System.out.println();
            System.out.println(p1);
            return piecesOnDiagonal(p1, target_location);
        }
        return false;
    }

    private boolean piecesOnDiagonal(PieceV2 p, int[] target_location){
        // checks for pieces between current piece and end location (specified by the notation)
        int x_mult = 1, y_mult = 1;

        if(target_location[0] < p.getCurrentCoordinate()[0]) {
            x_mult = -1;
        }
        if(target_location[1] < p.getCurrentCoordinate()[1]){
            y_mult = -1;
        }

        for(int i = 1; i < p.delta(p.getCurrentCoordinate()[0], target_location[0]); i++){
            if(findPieceByCoordinate(new int[]{p.getCurrentCoordinate()[0]+(x_mult*i),
                    p.getCurrentCoordinate()[1]+(y_mult*i)}) != null){
                return true;
            };
        }
        return false;
    }


    public PieceV2[][] getBoard() {
        return board;
    }
}
