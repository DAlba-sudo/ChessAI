package backend.chess.pieces.revised;

public class BishopV2 extends PieceV2{

    public BishopV2(String starting, int color) {
        super(starting, color);
    }

    @Override
    public boolean isMoveLegal(int x, int y) {
        return isMovingDiagonal(x, y) && isMoving(x, y);
    }

    @Override
    public boolean isCaptureLegal(int x, int y) {
        return isMoveLegal(x, y);
    }
}
