package backend.chess.pieces;

public class Bishop extends Piece {
    public Bishop(int color, int[] pos) {
        super(color, pos, 'B', false);
    }

    @Override
    public boolean moveRules(int[] to) {
        int[] from = getPos();
        return PHelper.isBishopMovement(to, from);
    }

    @Override
    public boolean captureRules(int[] to) {
        return moveRules(to);
    }
}
