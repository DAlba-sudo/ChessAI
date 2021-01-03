package backend.chess.env;

import backend.chess.pieces.base.PieceHelper;
import backend.chess.pieces.base.piece;
import backend.chess.pieces.extension.*;

import java.util.Arrays;
import java.util.LinkedList;

public class ChessBoard {

    LinkedList<piece> pieces = new LinkedList<>();

    public ChessBoard(){
        createBoard();
    }

    private void createBoard(){
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                int[] coordinate = {c, r};
                String notation = ChessHelper.convertCoordinate(coordinate);
                piece piece_to_add = null;
                if(r == 0){
                    // we are at white pieces
                    switch (c){
                        case 0:
                            // rook
                            piece_to_add = new rook(1, notation);
                            break;
                        case 1:
                            // knight
                            piece_to_add = new knight(1, notation);
                            break;
                        case 2:
                            // bishop
                            piece_to_add = new bishop(1, notation);
                            break;
                        case 3:
                            // queen
                            piece_to_add = new queen(1, notation);
                            break;
                        case 4:
                            // king
                            piece_to_add = new king(1, notation);
                            break;
                        case 5:
                            // bishop
                            piece_to_add = new bishop(1, notation);
                            break;
                        case 6:
                            // knight
                            piece_to_add = new knight(1, notation);
                            break;
                        case 7:
                            // rook
                            piece_to_add = new rook(1, notation);
                            break;
                    }
                    if(piece_to_add != null){
                        pieces.add(piece_to_add);
                    }
                } else if (r == 7){
                    // we are at the black pieces
                    switch (c){
                        case 0:
                            // rook
                            piece_to_add = new rook(0, notation);
                            break;
                        case 1:
                            // knight
                            piece_to_add = new knight(0, notation);
                            break;
                        case 2:
                            // bishop
                            piece_to_add = new bishop(0, notation);
                            break;
                        case 3:
                            // queen
                            piece_to_add = new queen(0, notation);
                            break;
                        case 4:
                            // king
                            piece_to_add = new king(0, notation);
                            break;
                        case 5:
                            // bishop
                            piece_to_add = new bishop(0, notation);
                            break;
                        case 6:
                            // knight
                            piece_to_add = new knight(0, notation);
                            break;
                        case 7:
                            // rook
                            piece_to_add = new rook(0, notation);
                            break;
                    }
                    if(piece_to_add != null){
                        pieces.add(piece_to_add);
                    }
                } else if (r == 1){
                    // spawn pawns (white)
                    pieces.add(new pawn(1, notation));
                } else if (r == 6){
                    // spawn pawns (black)
                    pieces.add(new pawn(0, notation));
                } else {
                    break;
                }
            }
        }
    }

    public piece findPieceByCoordinate(int[] coordinate){
        for(piece p : pieces){
            if(Arrays.equals(p.getCurrentCoordinate(), coordinate)){
                return p;
            }
        }
        return null;
    }

    public void displayBoard(){
        String[][] board = getBoardInString();
        for(int r = 0; r < board.length; r++){
            System.out.println();
            for(int c = 0; c < board[r].length; c++){
                System.out.print(board[r][c]);
            }
        }
    }

    public String[][] getBoardInString(){
        String[][] board = new String[8][8];
        for(int r = 7; r >= 0; r--){
            for(int c = 0; c < 8; c++){
                int[] coordinate = {c, r};
                piece piece_on_square = findPieceByCoordinate(coordinate);
                if(piece_on_square != null){
                    int color = piece_on_square.getColor();
                    Character abbreviation = piece_on_square.getAbbreviation();
                    if(color == 1){
                        if(abbreviation != null){
                            board[7-r][c] = " " + abbreviation + " ";
                        } else {
                            board[7-r][c] = " P ";
                        }
                    } else if (color == 0){
                        if(abbreviation != null){
                            board[7-r][c] = " " + Character.toLowerCase(abbreviation) + " ";
                        } else {
                            board[7-r][c] = " p ";
                        }
                    }
                } else {
                    board[7-r][c] = " - ";
                }
            }
        }
        return board;
    }

    public boolean arePiecesInTheWay(String notation, piece p){
        int[] to = ChessHelper.getDestinationFromNotation(notation);
        int[] from = p.getCurrentCoordinate();
        int[][] squaresToCheck = PieceHelper.getMoves(to, from);
        boolean isCapturing = ChessHelper.isCapturing(notation);

        for(int[] square : squaresToCheck){
            if(isCapturing){
                if(!Arrays.equals(square, to) && findPieceByCoordinate(square) != null){
                    // checks each square except the one we are trying to capture on
                    return true;
                }
            } else {
                // if not capturing, check each square
                if(findPieceByCoordinate(square) != null){
                    return true;
                }
            }
        }
        return false;
    }

    // TODO: 1/2/2021 test functionality of this method (use case : select pieces given any notation)
    public piece getPieceFromNotation(String notation){
        // this method can be used to get a piece from an ambiguous or unambiguous notation
        int[] to = ChessHelper.getDestinationFromNotation(notation);
        boolean isCapturing = ChessHelper.isCapturing(notation);
        if(notation.length() > 2){
            // we are not dealing with a simple pawn move
            if((notation.length() == 3 && !isCapturing) || (notation.length() == 4 && !isCapturing)){
                // we are dealing with an unambiguous normal move
                Class<? extends piece> pieceType = ChessHelper.getPieceType(notation);
                for(piece p : pieces){
                    if(pieceType.isInstance(p) && (p.isMoveLegal(to))){
                        // if we have the correct pawn return it
                        return p;
                    }
                }
            } else if (notation.length() == 3 && isCapturing){
                // we have a pawn capture with specified file
                int pawnFile = ChessHelper.getNumFromFile(notation.charAt(0));
                for(piece p : pieces){
                    if(p instanceof pawn && p.getCurrentCoordinate()[0] == pawnFile && (p.isCaptureLegal(to))){
                        return p;
                    }
                }
                return null;
            } else {
                // we have a non-pawn ambiguous move
                Class<? extends piece> pieceType = ChessHelper.getPieceType(notation);
                StringBuilder relevant_from = new StringBuilder();
                String from_notation = "";

                boolean found_ambiguous_start = false;
                for(int i = 0; i < notation.length(); i++){
                    char current_character = notation.charAt(i);
                    if(current_character == '/'){
                        found_ambiguous_start = true;
                    }
                    if(found_ambiguous_start && relevant_from.length() == 0){
                        relevant_from.append(current_character);
                    } else if (found_ambiguous_start && relevant_from.length() > 1
                            && (!Character.isDigit(notation.charAt(i+1)))){
                        from_notation = relevant_from.toString();
                    }
                }
                int[] from = {};
                if(from_notation.length() == 1){
                    from = new int[]{ChessHelper.getNumFromFile(from_notation.charAt(0)), -1};
                } else if (from_notation.length() == 2){
                    from = new int[]{ChessHelper.getNumFromFile(from_notation.charAt(0)), Integer.parseInt(String.valueOf(from_notation.charAt(1)))};
                }
                // if we are capturing (take into account the 'x')
                for(piece p : pieces){
                    if(pieceType.isInstance(p)){
                        if(from[0] == p.getCurrentCoordinate()[0] && (from[1] == -1) || from[0] == p.getCurrentCoordinate()[0] && (from[1] == p.getCurrentCoordinate()[1])){
                            return p;
                        }
                    }
                }

            }
        } else if (notation.length() == 2) {
            // we are dealing with an unambiguous pawn move
            for(piece p : pieces){
                if(p instanceof pawn && Arrays.equals(p.getCurrentCoordinate(), ChessHelper.getDestinationFromNotation(notation))){
                    // if we have the correct pawn return it
                    return p;
                }
            }
            return null;
        }
        return null;
    }

    public LinkedList<piece> getPieceList() {
        return this.pieces;
    }
}
