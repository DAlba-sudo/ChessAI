package backend.chess.pieces;

public class Queen extends Piece {
    public Queen(int color, int[] pos) {
        super(color, pos, 'Q', false);
    }

    @Override
    public boolean moveRules(int[] to) {
        int[] from = getPos();
        boolean isBishopMovement = PHelper.isBishopMovement(to, from);
        boolean isRookMovement = PHelper.isRookMovement(to, from);
    return (isBishopMovement && !isRookMovement) || (isRookMovement && !isBishopMovement);
    }

    @Override
    public boolean captureRules(int[] to) {
        return moveRules(to);
    }
}
