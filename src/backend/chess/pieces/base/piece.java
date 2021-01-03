package backend.chess.pieces.base;

import backend.chess.env.ChessHelper;

import java.util.Arrays;

public abstract class piece {

    private String current_pos;
    private int color;
    private int[] current_coordinate;
    private Character piece_abrev;
    private boolean isDead = false;

    public piece(int color, String notation, Character piece_abrev){
        this.current_pos = notation;
        this.current_coordinate = ChessHelper.getDestinationFromNotation(this.current_pos);
        setColor(color);
        setAbbreviation(piece_abrev);
    }

    public abstract boolean isMoveLegal(int[] to);
    public abstract boolean isCaptureLegal(int[] to);

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setAbbreviation(Character piece_abrev) {
        this.piece_abrev = piece_abrev;
    }

    public Character getAbbreviation() {
        return piece_abrev;
    }

    public int[] getCurrentCoordinate() {
        return current_coordinate;
    }

    public void setCoordinate(int[] current_coordinate) {
        this.current_coordinate = current_coordinate;
    }

    public void setDead() {
        isDead = true;
    }

    public boolean getDead(){
        return isDead;
    }

    @Override
    public String toString() {
        return "piece{" +
                "current_pos='" + current_pos + '\'' +
                ", color=" + color +
                ", current_coordinate=" + Arrays.toString(current_coordinate) +
                ", piece_abrev=" + piece_abrev +
                '}';
    }
}
