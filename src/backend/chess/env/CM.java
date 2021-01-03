package backend.chess.env;

import backend.chess.pieces.Bishop;
import backend.chess.pieces.PHelper;
import backend.chess.pieces.Piece;

import java.util.Arrays;
import java.util.LinkedList;

public class CM {
    public static boolean isWithin(int[] to){
        return (to[0] <= 7) && (to[0] >= 0) && (to[1] <= 7) && (to[1] >= 0);
    }
    
    public static LinkedList<int[]> getSquaresBetween(int[] to, int[] from){
        LinkedList<int[]> squares = new LinkedList<>();
        boolean isDiagonal = PHelper.isDiagonal(to, from);
        if(isDiagonal){
            // if it is diagonal, calculate blocks in between
            int[][] moves = getDiagonalMoves(to, from);
            squares.addAll(Arrays.asList(moves));
        } else {
            if(to[0] == from[0] && to[1] != from[1]){
                // same file, but different rank (same file)
                int[][] moves = getMovesInSameFile(to, from);
                squares.addAll(Arrays.asList(moves));
            } else if (to[0] != from[0] && to[1] == from[1]){
                // same rank, but different file (same rank)
                int[][] moves = getMovesInSameRank(to, from);
                squares.addAll(Arrays.asList(moves));
            } else {
                // some other movemet
                System.out.println("Unsupported movement");
            }
        }
        return squares;
    }

    private static int[][] getMovesInSameRank(int[] to, int[] from){
        int deltaX = PHelper.delta(to[0], from[0]);
        int[][] possible_moves;
        if(deltaX == 0){
            return new int[][]{};
        } else {
            possible_moves = new int[Math.abs(deltaX)][2];
        }

        int xMult = 1;
        if(deltaX < 0){
            xMult = -1;
        }

        for(int i = 0; i < Math.abs(deltaX); i++){
            int[] move = {from[0]+(xMult*(i+1)), from[1]};
            possible_moves[i] = move;
        }
        return possible_moves;
    }

    private static int[][] getMovesInSameFile(int[] to ,int[] from){
        int deltaY = PHelper.delta(to[1], from[1]);
        int[][] possible_moves;
        if(deltaY == 0){
            return new int[][]{};
        } else {
            possible_moves = new int[Math.abs(deltaY)][2];
        }

        int yMult = 1;
        if(deltaY < 0){
            yMult = -1;
        }

        for(int i = 0; i < Math.abs(deltaY); i++){
            int[] move = {from[0], from[1]+(yMult*(i+1))};
            possible_moves[i] = move;
        }
        return possible_moves;
    }

    private static int[][] getDiagonalMoves(int[] to, int[] from) {
        // we know that they are equal, because there is a check before this call
        int deltaX = PHelper.delta(to[0], from[0]);
        int deltaY = PHelper.delta(to[1], from[1]);
        int[][] possible_moves;
        if(Math.abs(deltaX) != Math.abs(deltaY) || deltaX == 0){
            System.out.println(deltaX);
            return new int[][]{};
        } else {
            possible_moves = new int[Math.abs(deltaX)][2];
        }
        
        int xMult = 1, yMult = 1;
        if(deltaX < 0){
            // we are moving to the left
            xMult = -1;
        }
        if(deltaY < 0){
            // we are moving to the down
            yMult = -1;
        }
        
        for(int i = 0; i < Math.abs(deltaX); i++){
            int[] pos = new int[]{from[0]+(xMult*(i+1)), from[1]+(yMult*(i+1))};
            possible_moves[i] = pos;
        }
        return possible_moves;
    }
}
