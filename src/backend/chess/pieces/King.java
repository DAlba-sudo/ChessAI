package backend.chess.pieces;

public class King extends Piece {
    public King(int color, int[] pos) {
        super(color, pos, 'K', false);
    }

    @Override
    public boolean moveRules(int[] to) {
        int[] from = getPos();
        // checks to make sure it is atleast moving one square (and it can move backwards, but it is absolute
        // so that is still taken into account)
        return PHelper.deltaAbs(to[0], from[0]) <= 1 && PHelper.deltaAbs(to[1], from[1]) <= 1;
    }

    @Override
    public boolean captureRules(int[] to) {
        return moveRules(to);
    }
}
