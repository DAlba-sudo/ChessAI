package backend.chess.pieces.old;

import java.util.Arrays;

public class Bishop extends Piece{

    public Bishop(int x, int y, int color) {
        super(x, y, 'B', false, color);
    }

    public Bishop(String notation, int color) {
        super(notation, false, color);
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
