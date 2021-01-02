package backend.chess.pieces.extension;

import backend.chess.pieces.base.PieceHelper;
import backend.chess.pieces.base.piece;

public class queen extends piece {
    public queen(int color, String notation) {
        super(color, notation, 'Q');
    }

    @Override
    public boolean isMoveLegal(int[] to) {
        int[] from = getCurrentCoordinate();
        return PieceHelper.isMoving(to, from)
                && (((PieceHelper.isForward(to[1], from[1]) || PieceHelper.isBackward(to[1], from[1])
                || PieceHelper.isToLeft(to[0], from[0]) || PieceHelper.isToRight(to[0], from[0])) && !PieceHelper.isDiagonal(to, from))
                || PieceHelper.isDiagonal(to, from));
    }

    @Override
    public boolean isCaptureLegal(int[] to) {
        return isMoveLegal(to);
    }
}
