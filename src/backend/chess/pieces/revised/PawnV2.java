package backend.chess.pieces.revised;

public class PawnV2 extends PieceV2{
    public PawnV2(String starting, int color) {
        super(starting, color);
    }

    @Override
    public boolean isMoveLegal(int x, int y) {
        boolean movementDirCorrect = isMovingForward(y) && isMoving(x, y);
        int max_movement_forward = 1;
        if(movementDirCorrect && getCurrentCoordinate()[1] == 2){
            max_movement_forward = 2;
        }
        return movementDirCorrect && delta(y, getCurrentCoordinate()[1]) <= max_movement_forward
                && !isMovingToSide(x);
    }

    @Override
    public boolean isCaptureLegal(int x, int y) {
        return isMoving(x, y) && isMovingForward(y) && isMovingDiagonal(x, y)
                && delta(x, getCurrentCoordinate()[0]) == 1;
    }


}
