package backend.chess.pieces;

import backend.chess.ChessManager;

import java.util.Arrays;

public class Pawn extends Piece{

    public Pawn(int x, int y){
        super(x, y, null, false);
    }

    public Pawn(String notation) {
        super(notation, false);
    }

    @Override
    public boolean canMove(int[] coordinateToMove) {
        boolean isForward = coordinateToMove[1] > getCoordinate()[1];
        boolean isNotToSide = coordinateToMove[0] == getCoordinate()[0];
        if(isValidPos(coordinateToMove)){
            if(isForward && isNotToSide && (!isMoved())){
                return ChessManager.isWithin(coordinateToMove[1], getCoordinate()[1]+2, getCoordinate()[1]);
            } else {
                return (coordinateToMove[1] == getCoordinate()[1] + 1) && (isForward && isNotToSide);
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canCapture(int[] coordinate) {
        boolean isForward = coordinate[1] == getCoordinate()[1]+1;
        return (isForward && coordinate[0] != getCoordinate()[0] && ChessManager.isWithin(coordinate[0],
                getCoordinate()[0]+1, getCoordinate()[0]-1)) && isValidPos(coordinate);
    }


}
