package backend.chess.pieces.revised;

public class KingV2 extends PieceV2{

    public KingV2(String starting, int color) {
        super(starting, color);
    }

    @Override
    public boolean isMoveLegal(int x, int y) {
        return isMoving(x, y) && (delta(x, getCurrentCoordinate()[0]) == 1 || delta(y, getCurrentCoordinate()[1]) == 1);
    }

    @Override
    public boolean isCaptureLegal(int x, int y) {
        return isMoveLegal(x, y);
    }
}
