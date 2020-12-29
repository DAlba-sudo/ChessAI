package backend.chess.pieces;

import java.util.Arrays;

public class Queen extends Piece{

    public Queen(int x, int y) {
        super(x, y, 'Q', false);
    }

    public Queen(String notation) {
        super(notation, false);
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
