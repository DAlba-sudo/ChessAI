package backend.chess.pieces.extension;

import backend.chess.pieces.base.PieceHelper;
import backend.chess.pieces.base.piece;

public class knight extends piece {
    public knight(int color, String notation) {
        super(color, notation, 'N');
    }

    @Override
    public boolean isMoveLegal(int[] to) {
        int[] from = getCurrentCoordinate();
        int xDelta = PieceHelper.deltaAbs(to[0], from[0]);
        int yDelta = PieceHelper.deltaAbs(to[1], from[1]);

        return (xDelta == 2 && yDelta == 1) || (xDelta == 1 && yDelta == 1) && PieceHelper.isMoving(to, from);
    }

    @Override
    public boolean isCaptureLegal(int[] to) {
        return isMoveLegal(to);
    }
}
