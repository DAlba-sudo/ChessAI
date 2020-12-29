package backend.chess.pieces;

import java.util.Arrays;

public class Knight extends Piece{

    public Knight(int x, int y) {
        super(x, y, 'N', true);
    }

    public Knight(String notation) {
        super(notation, true);
    }

    @Override
    public boolean canCapture(int[] coordinate) {
        return canMove(coordinate);
    }

    @Override
    public boolean canMove(int[] coordinateToMove) {
        int dx = Math.abs(coordinateToMove[0] - getCoordinate()[0]);
        int dy = Math.abs(coordinateToMove[1] - getCoordinate()[1]);
        boolean isCorrectMovement = (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
        return isValidPos(coordinateToMove) && !Arrays.equals(getCoordinate(), coordinateToMove)
                && isCorrectMovement;
    }
}

