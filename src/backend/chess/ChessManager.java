package backend.chess;

import backend.chess.pieces.*;

import java.util.Stack;

public class ChessManager {
    public static final char[] files = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'
    };
    // TODO: TEST THE getCoordinateToMoveFrom() method for ambiguity cases
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
            } else if (current_char == 'x'){
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
                }
            }
        }
        return new int[]{-1, -1};
    }
}
