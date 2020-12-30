package backend.chess.pieces.old;

import backend.chess.ChessManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static backend.chess.ChessManager.isWithin;

public abstract class Piece {

    private int[] coordinate = new int[2];
    private boolean hasMoved = false;
    private Character piece_label;
    private boolean canHop = false;
    private int color;

    public Piece(int x, int y, Character piece_label, boolean canHop, int color){
        this.coordinate[0] = x;
        this.coordinate[1] = y;
        this.piece_label = piece_label;
        this.canHop = canHop;
        this.color = color;
    }

    public Piece(int x, int y, Character piece_label, boolean canHop){
        this.coordinate[0] = x;
        this.coordinate[1] = y;
        this.piece_label = piece_label;
        this.canHop = canHop;
        this.color = color;
    }

    public Piece(String notation, boolean canHop, int color){
        this.canHop = canHop;
        this.color = color;
    }

    public abstract boolean canCapture(int[] coordinate);

    public abstract boolean canMove(int[] coordinateToMove);

    public boolean isValidPos(int[] dest){
        if(dest.length == 2){
            return isWithin(dest[0], 8, 1) && isWithin(dest[1], 8, 1);
        } else {
            return false;
        }
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int x, int y) {
        this.coordinate[0] = x;
        this.coordinate[1] = y;
    }

    public void setCoordinate(int[] coordinate){
        this.coordinate = coordinate;
    }

    public void setCoordinate(String notation){

    }

    public Character getPieceLabel() {
        return piece_label;
    }

    public boolean isMoved(){
        return this.hasMoved;
    }

    public void setMoved(boolean moved){
        this.hasMoved = moved;
    }

    public boolean isCanHop(){
        return this.canHop;
    }

    public LinkedList<int[]> getLegalMoves(boolean capture){
        LinkedList<int[]> legalMoves = new LinkedList<>();

        for(int r = 0; r <= 8; r++){
            for(int c = 0; c <= 8; c++){
                int[] dest = {r, c};
                boolean canMove = canMove(dest);
                boolean canCapture = canCapture(dest) && capture;
                if((canMove || canCapture) && (isValidPos(dest))) {
                    legalMoves.add(new int[]{r, c});
                }
            }
        }

        return legalMoves;
    }

    public int getColor() {
        return color;
    }
}
