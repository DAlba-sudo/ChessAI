package backend.chess.pieces.extension;

import backend.chess.pieces.base.PieceHelper;
import backend.chess.pieces.base.piece;

public class rook extends piece {
    public rook(int color, String notation) {
        super(color, notation, 'R');
    }

    @Override
    public boolean isMoveLegal(int[] to) {
        int[] from = getCurrentCoordinate();
        return PieceHelper.isMoving(to, from)
                && (PieceHelper.isForward(to[1], from[1]) || PieceHelper.isBackward(to[1], from[1])
                && (PieceHelper.isToRight(to[0], from[0]) || PieceHelper.isToLeft(to[0], from[0]))
                && !PieceHelper.isDiagonal(to, from));
    }

    @Override
    public boolean isCaptureLegal(int[] to) {
        return isMoveLegal(to);
    }
}
