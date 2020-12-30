package backend.chess.pieces.revised;

public class PawnV2 extends PieceV2{
    public PawnV2(String starting, int color) {
        super(starting, color, null);
    }

    public PawnV2(int x, int y, int color) {
        super(x, y, color, null);
    }

    @Override
    public boolean isMoveLegal(int x, int y) {
        boolean movementDirCorrect;
        if((isMovingForward(y) && isMoving(x, y)) && getColor() == 1){
            movementDirCorrect = true;
        } else movementDirCorrect = (!isMovingForward(y) && isMoving(x, y)) && getColor() == 0;

        int max_movement_forward = 1;
        if(getColor() == 1){
            if(movementDirCorrect && getCurrentCoordinate()[1] == 2){
                max_movement_forward = 2;
            }
        } else if(getColor() == 0) {
            if(movementDirCorrect && getCurrentCoordinate()[1] == 7){
                max_movement_forward = 2;
            }
        }
        return movementDirCorrect && delta(y, getCurrentCoordinate()[1]) <= max_movement_forward
                && !isMovingToSide(x);
    }

    @Override
    public boolean isCaptureLegal(int x, int y) {
        return isMoving(x, y) && ((getColor() == 1 && isMovingForward(y)) || getColor() == 0 && !isMovingForward(y))
                && isMovingDiagonal(x, y)
                && delta(x, getCurrentCoordinate()[0]) == 1;
    }


}
