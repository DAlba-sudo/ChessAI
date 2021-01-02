package backend.chess.pieces.extension;

import backend.chess.pieces.base.PieceHelper;
import backend.chess.pieces.base.piece;

public class king extends piece {

    public king(int color, String notation) {
        super(color, notation, 'K');
    }

    @Override
    public boolean isMoveLegal(int[] to) {
        int[] from = getCurrentCoordinate();
        return PieceHelper.isMoving(to, from)
                && (PieceHelper.deltaAbs(to[0], from[0]) == 1 || PieceHelper.deltaAbs(to[1], from[1]) == 1);
    }

    @Override
    public boolean isCaptureLegal(int[] to) {
        return isMoveLegal(to);
    }
}
