package backend.chess.pieces.base;

import backend.chess.env.ChessHelper;

public abstract class piece {

    private String current_pos;
    private int color;
    private int[] current_coordinate;
    private Character piece_abrev;

    public piece(int color, String notation, Character piece_abrev){
        this.current_pos = notation;
        this.current_coordinate = ChessHelper.getDestinationFromNotation(this.current_pos);
        setColor(color);
        setAbbreviation(piece_abrev);
    }

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
}
