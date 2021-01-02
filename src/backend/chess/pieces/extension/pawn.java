package backend.chess.pieces.extension;

import backend.chess.env.ChessHelper;
import backend.chess.pieces.base.PieceHelper;
import backend.chess.pieces.base.piece;

import java.util.Arrays;

public class pawn extends piece {
    public pawn(int color, String notation) {
        super(color, notation, null);
    }

    @Override
    public boolean isMoveLegal(int[] to) {
        int[] from = getCurrentCoordinate();
        if(PieceHelper.isMoving(to, from)){
            if(getColor() == 1){
                int delta = PieceHelper.delta(to[1], from[1]);
                if(delta == -1 || (from[1] == 1 && delta == -2)){
                    // if moving only one and positive
                    return true;
                } else {
                    return false;
                }
            } else if (getColor() == 0){
                int delta = PieceHelper.delta(to[1], from[1]);
                if (delta == -1 || (from[1] == 6 && delta == -2)){
                    // if moving only one and positive
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isCaptureLegal(int[] to) {
        int[] from = getCurrentCoordinate();
        if(PieceHelper.isDiagonal(to, from)){
            // capture has to be diagonal
            if(PieceHelper.deltaAbs(to[0], from[0]) == 1){
                // can only capture one diagonal (we have checked for symmetry)
                if(getColor() == 1){
                    return PieceHelper.isForward(to[1], from[1]);
                } else if (getColor() == 0){
                    return PieceHelper.isBackward(to[1], from[1]);
                }
            } else {
                // if moving more than one then it is not a valid move
                return false;
            }
        }
        return false;
    }
}
