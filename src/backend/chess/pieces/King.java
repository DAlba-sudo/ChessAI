package backend.chess.pieces;

import java.util.Arrays;

public class King extends Piece{

    public King(int x, int y) {
        super(x, y, 'K', false);
    }

    public King(String notation) {
        super(notation, false);
    }

    public boolean isInCheck(int[] coordinate){
        // TODO: Create Is In Check Condition
        return false;
    }

    @Override
    public boolean canCapture(int[] coordinate) {
        return canMove(coordinate);
    }

    @Override
    public boolean canMove(int[] coordinateToMove) {
        return isValidPos(coordinateToMove) && !Arrays.equals(getCoordinate(), coordinateToMove) &&
                (Math.abs(coordinateToMove[0] - getCoordinate()[0]) <= 1
                && Math.abs(coordinateToMove[1] - getCoordinate()[1]) <= 1) && !isInCheck(coordinateToMove);
    }
}
