package backend.chess.pieces.base;

import java.util.Arrays;

public class PieceHelper {

    public static int deltaAbs(int to, int from){
        return Math.abs(to - from);
    }

    public static int delta(int to, int from){
        return to - from;
    }

    public static boolean isForward(int y2, int y1){
        return delta(y2, y1) > 0;
    }

    public static boolean isBackward(int y2, int y1){
        return delta(y2, y1) < 0;
    }

    public static boolean isMoving(int[] to, int[] from){
        return !Arrays.equals(to ,from);
    }

    public static boolean isWithinBounds(int[] to){
        return (to[0] <= 7 && to[1] <= 7) && (to[0] >= 0 && to[1] >= 0);
    }

    public static boolean isToRight(int x2, int x1){
        return delta(x2, x1) > 0;
    }

    public static boolean isToLeft(int x2, int x1){
        return delta(x2, x1) < 0;
    }

    public static boolean isDiagonal(int[] to, int[] from){
        return deltaAbs(to[0], from[0]) == deltaAbs(to[1], from[1]) && isMoving(to, from);
    }

    public static int[][] getMoves(int[] to, int[] from){
        boolean isMoving = isMoving(to, from);

        if(isMoving){
            boolean isDiagonal = isDiagonal(to, from);
            boolean isForward = isForward(to[1], from[1]);
            boolean isBackward = isBackward(to[1], from[1]);
            boolean isToRight = isToRight(to[0], from[0]);
            boolean isToLeft = isToLeft(to[0], from[0]);

            if(!isDiagonal){
                // if it is not diagonal (it is a straight, or to the sides)
                if(isForward || isBackward){
                    // if moving on the file
                    int squares_to_traverse = deltaAbs(to[1], from[1]);
                    int[][] squares = new int[squares_to_traverse][2];
                    if(isForward && !isBackward){
                        for(int i = 0; i < squares_to_traverse; i++){
                            squares[i] = new int[]{from[0], from[1]+i};
                        }
                        return squares;
                    } else if (!isForward && isBackward){
                        for(int i = 0; i < squares_to_traverse; i++){
                            squares[i] = new int[]{from[0], from[1]-i};
                        }
                        return squares;
                    }
                } else if (isToRight || isToLeft){
                    // if moving on the rank
                    int squares_to_traverse = deltaAbs(to[0], from[0]);
                    int[][] squares = new int[squares_to_traverse][2];
                }
                return new int[][]{};
            } else {
                // if it is diagonal
                return new int[][]{};
            }
        } else {
            return new int[][]{};
        }
    }
}
