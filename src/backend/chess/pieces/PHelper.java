package backend.chess.pieces;

public class PHelper {

    public static boolean isRookMovement(int[] to, int[] from){
        // checks if moving in same file and diff rank <-> or diff fil and same rank (up down)
        return (to[0] == from[0] && to[1] != from[1]) || (to[0] != from[0] && to[1] == from[1]);
    }

    public static boolean isBishopMovement(int[] to, int[] from){
        return isDiagonal(to, from);
    }

    public static boolean isDiagonal(int[] to, int[] from){
        return PHelper.deltaAbs(to[0], from[0]) == PHelper.deltaAbs(to[1], from[1]);
    }

    public static int deltaAbs(int to, int from){
        return Math.abs(to - from);
    }

    public static int delta(int to, int from){
        return to-from;
    }

}
