package backend.chess;

import backend.chess.pieces.Piece;

public class ChessManager {
    public static final char[] files = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'
    };

    public static boolean isWithin(int num, int max, int min){
        return num <= max && num >= min;
    }

    private static String xToFile(int x){
        return Character.toString(files[x-1]);
    }

    public static String toNotation(Piece p, int[] coordinate, boolean captures, boolean ambigous){
        Character piece = p.getPieceLabel();
        String destination_file = xToFile(coordinate[0]) + Integer.toString(coordinate[1]);
        String current_file = xToFile(p.getCoordinate()[0]);

        StringBuilder sb = new StringBuilder();

        // append the piece notation
        if(piece != null){
            // if it actually has a name
            sb.append(piece);
        } else {
            sb.append(current_file);
        }

        // if it is ambigous specify the piece's position
        if(ambigous){
            sb.append('/').append(current_file).append(coordinate[1]);
        }

        if(captures){
            // captures
            sb.append('x');
        }

        sb.append(destination_file);
        return sb.toString();
    }
}
