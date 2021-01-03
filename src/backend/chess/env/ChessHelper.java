package backend.chess.env;

import backend.chess.pieces.base.piece;
import backend.chess.pieces.extension.*;

public class ChessHelper {
    public static char[] file_abrev = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'
    };

    public static String getFileFromNum(int x){
        return Character.toString(file_abrev[x]);
    }

    public static int getNumFromFile(char file){
        for(int i = 0; i < file_abrev.length; i++){ if (file_abrev[i] == file){return i;}};
        return -1;
    }

    public static int[] getDestinationFromNotation(String notation){
        // will return the position of the notation given in the form of [0, 4] for a5

        if(notation.length() >= 2){
            return new int[]{getNumFromFile(notation.charAt(0)), Integer.parseInt(String.valueOf(notation.charAt(1)))-1};
        }
        return new int[]{-1, -1};
    }

    public static String convertCoordinate(int[] target_coordinate, int[] destination_coordinate,
                                           boolean isCapturing,
                                           boolean ambiguousDifFile,
                                           boolean ambiguousSameFile,
                                           piece p){
        Character abrev = p.getAbbreviation();
        StringBuilder sb = new StringBuilder();

        if(abrev != null){
            sb.append(abrev);
        }

        if(ambiguousDifFile){
            sb.append('/').append(getFileFromNum(destination_coordinate[0]));
        }

        if(ambiguousSameFile){
            sb.append('/').append(getFileFromNum(destination_coordinate[0])).append(destination_coordinate[1]);
        }

        if(isCapturing && abrev != null){
            sb.append('x');
        } else if (isCapturing && abrev == null){
            sb.append(getFileFromNum(destination_coordinate[0])).append('x');
        }

        sb.append(getFileFromNum(target_coordinate[0])).append(target_coordinate[1]);
        return sb.toString();
    }

    public static String convertCoordinate(int[] target_coordinate){
        return getFileFromNum(target_coordinate[0]) + (Integer.toString(target_coordinate[1]+1));
    }

    public static boolean isCapturing(String notation){
        for(int i = 0; i < notation.length(); i++){
            char current_char = notation.charAt(i);
            if(current_char == 'x'){
                return true;
            }
        }
        return false;
    }

    public static Class<? extends piece> getPieceType(String notation){
        Class<? extends piece> pieceType = pawn.class;
        switch (notation.charAt(0)){
            case 'R':
                pieceType = rook.class;
                break;
            case 'B':
                pieceType = bishop.class;
                break;
            case 'Q':
                pieceType = queen.class;
                break;
            case 'K':
                pieceType = king.class;
                break;
            case 'N':
                pieceType = knight.class;
                break;
        }
        return pieceType;
    }
}
