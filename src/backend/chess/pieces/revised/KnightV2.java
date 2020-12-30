package backend.chess.pieces.revised;

public class KnightV2 extends PieceV2{

    public KnightV2(String starting, int color) {
        super(starting, color, 'N');
        setJump(true);
    }

    @Override
    public boolean isMoveLegal(int x, int y) {
        int dy = delta(y, getCurrentCoordinate()[1]);
        int dx = delta(x, getCurrentCoordinate()[0]);

        return (dy == 2 && dx == 1) || (dy == 1 && dx == 2) && isMoving(x, y);
    }

    public KnightV2(int x, int y, int color) {
        super(x, y, color, 'N');
    }

    @Override
    public boolean isCaptureLegal(int x, int y) {
        return isMoveLegal(x, y);
    }
}
