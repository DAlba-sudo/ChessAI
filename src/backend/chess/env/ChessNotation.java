package backend.chess.env;

import backend.chess.pieces.*;

public class ChessNotation {
    private static char[] files_abbrev = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'
    };

    public static int numFromFile(char file){
        for(int i = 0; i < files_abbrev.length; i++){
            if(files_abbrev[i] == file){
                return i;
            }
        }
        return -1;
    }

    public static boolean isCapture(String notation){
        for(int i = notation.length()-1; i >= 0; i--){
            char current_char = notation.charAt(i);
            if(current_char == 'x' && i < notation.length()-2){
                return true;
            } else if (i < notation.length()-2){
                return false;
            }
        }
        return false;
    }

    public static int[] findTo(String notation){
        // last two characters in the notation refer to the destination
        if(notation.length() >= 2){
            return new int[]{numFromFile(notation.charAt(notation.length()-2))
                    , Integer.parseInt(String.valueOf(notation.charAt(notation.length()-1)))-1};
        } else {
            return new int[]{-1, -1};
        }
    }

    public static Class<? extends Piece> getPieceType(String notation) {
        char first_char = notation.charAt(0);
        if(Character.isUpperCase(first_char)){
            switch (notation.charAt(0)){
                case 'R':
                    return Rook.class;
                case 'B':
                    return Bishop.class;
                case 'N':
                    return Knight.class;
                case 'K':
                    return King.class;
                case 'Q':
                    return Queen.class;
            }
        } else if ((notation.length() == 2 && !isCapture(notation)) || (notation.length() == 3 && isCapture(notation))){
            // special case for the pawns
            return Pawn.class;
        }
        return null;
    }

    // TODO: 1/2/2021 FIX THIS METHOD! Do proper testing to ensure it functions as you expect!
    public static int[] findFrom(String notation, Class<? extends Piece> pieceType) {
        // handling for ambiguous cases
        boolean isAmbiguous = isAmbiguous(notation);
        if(isAmbiguous && pieceType != Pawn.class){
            // if it is ambiguous and not a pawn
            boolean foundForwardSlash = false;
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < notation.length(); i++){
                char current_char = notation.charAt(i);
                if(current_char == '/'){
                    foundForwardSlash = true;
                }
                if(foundForwardSlash){
                    if(sb.length() == 0){
                        sb.append(current_char);
                    } else {
                        if(Character.isLowerCase(current_char) && !Character.isDigit(current_char)){
                            return findTo(sb.toString());
                        } else {
                            sb.append(current_char);
                        }
                    }
                }
            }
        } else if (isAmbiguous && pieceType == Pawn.class){
            // is ambiguous and piece type is pawn
            return new int[]{numFromFile(notation.charAt(0)), -1};
        }
        // random other case
        return new int[]{-1, -1};
    }

    private static boolean isAmbiguous(String notation) {
        for(int i = 0; i < notation.length(); i++){
            char current_char = notation.charAt(i);
            if(current_char == '/'){
                return true;
            }
        }
        return false;
    }
}
