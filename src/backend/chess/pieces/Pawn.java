package backend.chess.pieces;

public class Pawn extends Piece{
    public Pawn(int color, int[] pos) {
        super(color, pos, null, false);
    }

    @Override
    public boolean moveRules(int[] to) {
        int[] from = getPos();
        int color = getColor();
        boolean isForward;
        if(color == 1){
            // if white
            isForward = to[1] > from[1];
        } else {
            // if black (there is a switch because black's 'forward' is in a different direction)
            isForward = to[1] < from[1];
        }
        // checks to make sure it is in right direction and straight
        return isForward && PHelper.deltaAbs(to[1], from[1]) == 1 && to[0] == from[0];
    }

    @Override
    public boolean captureRules(int[] to) {
        int[] from = getPos();
        int color = getColor();
        boolean isForward;
        if(color == 1){
            // if white
            isForward = to[1] > from[1];
        } else {
            // if black (there is a switch because black's 'forward' is in a different direction)
            isForward = to[1] < from[1];
        }
        // checking direction is right and in a diagonal of 1 square
        return isForward && PHelper.deltaAbs(to[0], from[0]) == 1 && PHelper.deltaAbs(to[1], from[1]) == 1;
    }
}
