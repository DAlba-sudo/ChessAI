package backend.chess.pieces;

public class Rook extends Piece{
    public Rook(int color, int[] pos) {
        super(color, pos, 'R', false);
    }

    @Override
    public boolean moveRules(int[] to) {
        int[] from = getPos();
        // checks for either straight rank or file movement
        return PHelper.isRookMovement(to, from);
    }

    @Override
    public boolean captureRules(int[] to) {
        return moveRules(to);
    }
}
