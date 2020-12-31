package backend.chess;

import backend.chess.pieces.revised.*;

import java.util.Arrays;

public class ChessBoard {

    private PieceV2[] piecesOnBoard = new PieceV2[32];

    public ChessBoard(){
        populateBoard();
    }

    // adds the pieces to the board
    private void populateBoard(){
        // spawn pawns (white)
        PawnV2 pa2 = new PawnV2("a2", 1);
        PawnV2 pb2 = new PawnV2("b2", 1);
        PawnV2 pc2 = new PawnV2("c2", 1);
        PawnV2 pd2 = new PawnV2("d2", 1);
        PawnV2 pe2 = new PawnV2("e2", 1);
        PawnV2 pf2 = new PawnV2("f2", 1);
        PawnV2 pg2 = new PawnV2("g2", 1);
        PawnV2 ph2 = new PawnV2("h2", 1);

        // spawn pawns (black)
        PawnV2 pa7 = new PawnV2("a7", 0);
        PawnV2 pb7 = new PawnV2("b7", 0);
        PawnV2 pc7 = new PawnV2("c7", 0);
        PawnV2 pd7 = new PawnV2("d7", 0);
        PawnV2 pe7 = new PawnV2("e7", 0);
        PawnV2 pf7 = new PawnV2("f7", 0);
        PawnV2 pg7 = new PawnV2("g7", 0);
        PawnV2 ph7 = new PawnV2("h7", 0);

        // spawn other pieces (white)
        RookV2 ra1 = new RookV2("a1", 1);
        RookV2 rh1 = new RookV2("h1", 1);

        KnightV2 kb1 = new KnightV2("b1", 1);
        KnightV2 kg1 = new KnightV2("g1", 1);

        BishopV2 bc1 = new BishopV2("c1", 1);
        BishopV2 bf1 = new BishopV2("f1", 1);

        KingV2 whiteKing = new KingV2("e1", 1);
        QueenV2 whiteQueen = new QueenV2("d1", 1);

        // spawn other pieces (white)
        RookV2 ra8 = new RookV2("a8", 0);
        RookV2 rh8 = new RookV2("h8", 0);

        KnightV2 kb8 = new KnightV2("b8", 0);
        KnightV2 kg8 = new KnightV2("g8", 0);

        BishopV2 bc8 = new BishopV2("c8", 0);
        BishopV2 bf8 = new BishopV2("f8", 0);

        KingV2 blackKing = new KingV2("e8", 0);
        QueenV2 blackQueen = new QueenV2("d8", 0);

        piecesOnBoard = new PieceV2[]{
                ra8, kb8, bc8, blackQueen, blackKing, bf8, kg8, rh8,
                pa7, pb7, pc7, pd7, pe7, pf7, pg7, ph7,
                pa2, pb2, pc2, pd2, pe2, pf2, pg2, ph2,
                ra1, kb1, bc1, whiteKing, whiteQueen, bf1, kg1, rh1
        };
    }

    public void display(){
        // 'populates the board with the pieces where they are supposed to be
        String[][] board = new String[8][8];

        // places all the pieces in their corresponding board location
        for(PieceV2 piece : piecesOnBoard){
            int[] coordinate = piece.getCurrentCoordinate();
            int row = coordinate[1]-1;
            int col = coordinate[0]-1;

            // if the piece is not a pawn
            if(!piece.getDead()){
                if(piece.getNotation() != null){
                    Character piece_notation = piece.getNotation();
                    // if the piece is white
                    if(piece.getColor() == 1){
                        // capitalize the notation
                        board[row][col] = " " + Character.toString(Character.toUpperCase(piece_notation)) + " ";
                    } else if(piece.getColor() == 0){
                        // if black lowercase the notation
                        board[row][col] = " " + Character.toString(Character.toLowerCase(piece_notation)) + " ";
                    } else {
                        // else, set as null
                        board[row][col] = " - ";
                    }
                } else {
                    // same as above, but for pawn (pawns don't have a notation)
                    Character piece_notation = 'p';
                    if(piece.getColor() == 1){
                        board[row][col] = " " + Character.toString(Character.toUpperCase(piece_notation)) + " ";
                    } else if(piece.getColor() == 0){
                        board[row][col] = " " + Character.toString(Character.toLowerCase(piece_notation)) + " ";
                    } else {
                        board[row][col] = " - ";
                    }
                }
            }
        }

        // 'renders' the board as text
        for(int r = board.length-1; r >= 0; r--){
            if(!(r == board.length-1)){
                System.out.println();
            }
            for(int c = 0; c < board[r].length; c++){
                if(board[r][c] != null){
                    System.out.print(board[r][c]);
                } else {
                    System.out.print(" - ");
                }
            }
        }
    }

    public PieceV2 findPieceByCoordinate(int[] coordinate){
        // finds a piece by a given coordinate (returns a piece given a coordinate)
        for(PieceV2 piece : piecesOnBoard){
            if(Arrays.equals(coordinate, piece.getCurrentCoordinate()) && !piece.getDead()){
                return piece;
            }
        }
        return null;
    }

    public boolean doesCollide(PieceV2 p1, String notation){
        int[] target_location = ChessManager.getCoordinateToMoveTo(notation);

        // types of supported movement
        boolean isDiagonal = p1.isMovingDiagonal(target_location[0], target_location[1]);
        boolean isMovingInFile = target_location[0] == p1.getCurrentCoordinate()[0];
        boolean isMovingInRank = target_location[1] == p1.getCurrentCoordinate()[1];

        if(p1.isMoving(target_location[0], target_location[1])){
            if(isDiagonal){
                // checks for pieces on the same diagonal
                return !piecesOnDiagonal(p1, target_location);
            } else if (isMovingInFile && !isMovingInRank){
                // checks for pieces on the same file (up and down)
                return !piecesOnFile(p1, target_location[1]);
            } else if (!isMovingInFile && isMovingInRank){
                // checks for pieces on the same rank (side to side)
                return !piecesOnRank(p1, target_location[0]);
            } else {
                // unnacounted for movement
                return true;
            }
        }
        return true;
    }

    private boolean piecesOnFile(PieceV2 p1, int target_rank) {
        // "scan" each rank on this set file for possible pieces
        int file_movement_dir = 1;
        if(p1.getCurrentCoordinate()[1] > target_rank){
            file_movement_dir = -1;
        }
        int dy = p1.delta(target_rank, p1.getCurrentCoordinate()[1]);
        // if a piece is found between our current location and our target, return that there are pieces on the file
        for(int i = 1; i < dy; i++){
            if(findPieceByCoordinate(
                    new int[]{p1.getCurrentCoordinate()[0], (p1.getCurrentCoordinate()[1] + (i*file_movement_dir))})
                    != null){
                return true;
            }
        }
        return false;
    }

    private boolean piecesOnRank(PieceV2 p1, int target_file) {
        // "scan" each file on this set rank for possible pieces
        int file_movement_dir = 1;
        if(p1.getCurrentCoordinate()[0] > target_file){
            file_movement_dir = -1;
        }

        // if a piece is found between our current location and our target, return that there are pieces on the file
        for(int i = 1; i < p1.delta(target_file, p1.getCurrentCoordinate()[0]); i++){
            if(findPieceByCoordinate(
                    new int[]{(p1.getCurrentCoordinate()[1] + (i*file_movement_dir)), p1.getCurrentCoordinate()[1]})
                    != null){
                return true;
            }
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

    public PieceV2[] getPiecesOnBoard() {
        return piecesOnBoard;
    }
}
