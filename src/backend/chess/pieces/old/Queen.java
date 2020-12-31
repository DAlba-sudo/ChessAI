package backend.chess.pieces.old;

import backend.chess.pieces.old.Piece;

import java.util.Arrays;

@Deprecated
public class Queen extends Piece {

    public Queen(int x, int y, int color) {
        super(x, y, 'Q', false, color);
    }

    public Queen(String notation, int color) {
        super(notation, false, color);
    }

    @Override
    public boolean canCapture(int[] coordinate) {
        return canMove(coordinate);
    }

    @Override
    public boolean canMove(int[] coordinateToMove) {
        boolean isBishop = Math.abs(coordinateToMove[0] - getCoordinate()[0]) ==
                Math.abs(coordinateToMove[1] - getCoordinate()[1]);
        boolean isRookMove = (coordinateToMove[0] == getCoordinate()[0] || coordinateToMove[1] == getCoordinate()[1]);
        return (isBishop || isRookMove) &&
        !Arrays.equals(coordinateToMove, getCoordinate()) && isValidPos(coordinateToMove);
    }
}
