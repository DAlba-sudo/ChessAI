package backend.chess.pieces;

import backend.chess.env.CM;

import java.util.Arrays;
import java.util.LinkedList;

public abstract class Piece {
    private int color;
    private int[] pos;
    private boolean canJump, isDead = false;
    private Character abbreviation;

    public Piece(int color, int[] pos, Character abbreviation, boolean canJump){
        setColor(color);
        setPos(pos);
        setAbbreviation(abbreviation);
        setCanJump(canJump);
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void setAbbreviation(Character abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Character getAbbreviation() {
        return abbreviation;
    }

    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }

    public boolean getCanJump(){
        return this.canJump;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    public int[] getPos() {
        return pos;
    }

    public boolean isMoveLegal(int[] to, boolean isCapturing){
        // checks to make sure position is within board, movement rules apply, and, if capturing, capture
        // rules apply (and also makes sure that the position is not the same)
        boolean legality = !isDead() && CM.isWithin(to) && !Arrays.equals(to, getPos())
                && ((isCapturing && captureRules(to)) || (!isCapturing && moveRules(to)));
        return legality;

    }

    public abstract boolean moveRules(int[] to);
    public abstract boolean captureRules(int[] to);

    public LinkedList<int[]> getMoves(boolean isCapturing) {
        LinkedList<int[]> moves = new LinkedList<>();
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                int[] pos = {c, r};
                if(isMoveLegal(pos, isCapturing)){
                    moves.add(pos);
                }
            }
        }
        return moves;
    }
}
