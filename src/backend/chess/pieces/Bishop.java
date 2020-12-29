package backend.chess.pieces;

import java.util.Arrays;

public class Bishop extends Piece{

    public Bishop(int x, int y) {
        super(x, y, 'B', false);
    }

    public Bishop(String notation) {
        super(notation, false);
    }

    @Override
    public boolean canMove(int[] coordinateToMove) {
        boolean isDiagonal = Math.abs(coordinateToMove[0] - getCoordinate()[0]) ==
                Math.abs(coordinateToMove[1] - getCoordinate()[1]);
        return isValidPos(coordinateToMove) && !Arrays.equals(getCoordinate(), coordinateToMove) &&
                isDiagonal;
    }

    @Override
    public boolean canCapture(int[] coordinate) {
        return canMove(coordinate);
    }


}
