package backend.chess;

import backend.chess.pieces.revised.*;

import java.util.Stack;

public class ChessManager {
    public static final char[] files = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'
    };
    public static boolean isWithin(int num, int max, int min){
        return num <= max && num >= min;
    }

    public static String numToFile(int x){
        return Character.toString(files[x-1]);
    }

    public static int fileToNum(char x){
        for(int i = 0; i < files.length; i++){
            if(x == files[i]){
                return i+1;
            }
        }
        return -1;
    }

    // specifies if we are capturing (from the given notation)
    public static boolean isCapturing(String notation){
        for(int i = 0; i < notation.length(); i++){
            char current_char = notation.charAt(i);
            if(current_char == 'x'){
                return true;
            }
        }
        return false;
    }

    // returns whether more than one piece can move to the specified location
    public static boolean isAmbiguous(String notation){
        for(int i = 0; i < notation.length(); i++){
            char current_char = notation.charAt(i);
            if(current_char == '/'){
                return true;
            } else if (current_char == 'x' && !Character.isUpperCase(notation.charAt(i-1))){
                return true;
            } else if (current_char == 'x' && Character.isUpperCase(notation.charAt(i-1))){
                return false;
            } else if (i == notation.length()-2){
                return false;
            }
        }
        return false;
    }

    public static int[] getCoordinateToMoveTo(String notation){
        // returns the coordinate of where the item is ending (keep in mind this is in reference to the board so
        // it starts with 1 and ends with 8)

        // returns the last 2 designations of the notation
        return new int[]{fileToNum(notation.charAt(notation.length()-2)),
                Integer.parseInt(Character.toString(notation.charAt(notation.length()-1)))};
    }

    public static int[] getCoordinateToMoveFrom(String notation){
        Stack<Character> characters = new Stack<>();

        if(isAmbiguous(notation)){
            for(int i = notation.length()-1; i >= 0; i--){
                char current_char = notation.charAt(i);
                characters.push(current_char);
                if(current_char == '/'){
                    characters.pop();
                    StringBuilder sb = new StringBuilder();
                    while(!characters.empty() && characters.peek() != 'x'){
                        char popped = characters.pop();

                        // this builds a string up to the right length
                        if(sb.length() == 0){
                            sb.append(popped);
                        } else if (sb.length() > 0 && Character.isAlphabetic(popped)){
                            break;
                        } else {
                            sb.append(popped);
                        }
                    }
                    // returns the coordinates as if we were moving there
                    if(sb.toString().length() == 2){
                        return getCoordinateToMoveTo(sb.toString());
                    } else {
                        return new int[]{fileToNum(sb.charAt(0)), -1};
                    }
                } else if (current_char == 'x' && (!Character.isUpperCase(notation.charAt(i+1)) || !Character.isUpperCase(notation.charAt(i+2)))){
                    // this is a pawn case where it is ambiguous so specify
                    StringBuilder sb = new StringBuilder();

                    int counter = i;
                    while(counter >= 0 && counter > i-2){
                        characters.push(notation.charAt(counter));
                        counter--;
                    }

                    while(!characters.empty()){
                        char popped = characters.pop();

                        // this builds a string up to the right length
                        if(sb.length() == 0){
                            sb.append(popped);
                        } else if (sb.length() > 0 && Character.isAlphabetic(popped)){
                            break;
                        } else {
                            sb.append(popped);
                        }

                        if(characters.peek() == 'x'){
                            break;
                        }
                    }
                    // returns the coordinates as if we were moving there
                    if(sb.toString().length() == 2){
                        return getCoordinateToMoveTo(sb.toString());
                    } else {
                        return new int[]{fileToNum(sb.charAt(0)), -1};
                    }
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static Class<? extends PieceV2> getPieceType(String notation) {
        if (notation.length() > 0) {
            if (Character.isUpperCase(notation.charAt(0))) {
                switch (notation.charAt(0)) {
                    case 'K':
                        return KingV2.class;
                    case 'Q':
                        return QueenV2.class;
                    case 'B':
                        return BishopV2.class;
                    case 'N':
                        return KnightV2.class;
                    case 'R':
                        return RookV2.class;
                    default:
                        return null;
                }
            } else {
                return PawnV2.class;
            }
        }
        return null;
    }

    public static String coordinateToNotation(int[] coordinate, PieceV2 p, boolean isCapturing) {
        if(p.getNotation() != null && !isCapturing){
            return p.getNotation().toString() + '/' + numToFile(p.getCurrentCoordinate()[0])
                    + Integer.toString(p.getCurrentCoordinate()[1])
                    + numToFile(coordinate[0]) + Integer.toString(coordinate[1]);
        } else if (p.getNotation() != null && isCapturing) {
            return p.getNotation().toString() + '/' + numToFile(p.getCurrentCoordinate()[0])
                    + Integer.toString(p.getCurrentCoordinate()[1]) + 'x'
                    + numToFile(coordinate[0]) + Integer.toString(coordinate[1]);
        } else {
            if(!isCapturing){
                return numToFile(p.getCurrentCoordinate()[0])
                        + Integer.toString(p.getCurrentCoordinate()[1])
                        + numToFile(coordinate[0]) + Integer.toString(coordinate[1]);
            } else {
                return numToFile(p.getCurrentCoordinate()[0])
                        + Integer.toString(p.getCurrentCoordinate()[1]) + 'x'
                        + numToFile(coordinate[0]) + Integer.toString(coordinate[1]);
            }
        }
    }
}
