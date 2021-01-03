package backend.chess.pieces;

public class Knight extends Piece{
    public Knight(int color, int[] pos) {
        super(color, pos, 'N', true);
    }

    @Override
    public boolean moveRules(int[] to) {
        int[] from = getPos();
        boolean move_legality = (PHelper.deltaAbs(to[0], from[0]) == 2 && PHelper.deltaAbs(to[1], from[1]) == 1) ||
                (PHelper.deltaAbs(to[1], from[1]) == 2 && PHelper.deltaAbs(to[0], from[0]) == 1);
        return move_legality;
    }

    @Override
    public boolean captureRules(int[] to) {
        return moveRules(to);
    }
}
