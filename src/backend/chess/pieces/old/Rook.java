package backend.chess.pieces.old;

import backend.chess.pieces.old.Piece;

import java.util.Arrays;

public class Rook extends Piece {
    public Rook(int x, int y, int color){
        super(x, y, 'R', false, color);
    }
    public Rook(int x, int y){
        super(x, y, 'R', false);
    }

    public Rook(String notation, int color){
        super(notation, false, color);
    }

    @Override
    public boolean canMove(int[] coordinateToMove) {
        return (coordinateToMove[0] == getCoordinate()[0] || coordinateToMove[1] == getCoordinate()[1]) &&
                !Arrays.equals(coordinateToMove, getCoordinate()) && isValidPos(coordinateToMove);
    }

    @Override
    public boolean canCapture(int[] coordinate) {
        return canMove(coordinate);
    }
}
