package backend.chess.pieces.revised;

public class QueenV2 extends PieceV2{

    public QueenV2(String starting, int color) {
        super(starting, color);
    }

    @Override
    public boolean isMoveLegal(int x, int y) {
        return isMoving(x, y) && (
                ((getCurrentCoordinate()[0] == x || getCurrentCoordinate()[1] == y)
                        && !isMovingDiagonal(x, y)) || isMovingDiagonal(x, y));
    }

    @Override
    public boolean isCaptureLegal(int x, int y) {
        return isMoveLegal(x, y);
    }
}
