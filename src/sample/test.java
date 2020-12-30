package sample;


import backend.chess.ChessBoard;
import backend.chess.ChessManager;
import backend.chess.pieces.revised.*;

public class test {

    public static void main(String[] args) {
        ChessBoard cb = new ChessBoard();
        for(int r = 0; r < cb.getBoard().length; r++){
            System.out.println();
            for(int c = 0; c < cb.getBoard()[r].length; c++){
                if(cb.getBoard()[r][c] != null){
                    Character notation = cb.getBoard()[r][c].getNotation();
                    if(notation != null){
                        if(cb.getBoard()[r][c].getColor() == 1){
                            System.out.print(" " + notation.toString().toUpperCase() + " ");
                        } else {
                            System.out.print(" "+notation.toString().toLowerCase() + " ");
                        }
                    } else {
                        if(cb.getBoard()[r][c].getColor() == 1){
                            System.out.print(" p ".toUpperCase());
                        } else {
                            System.out.print(" p ".toLowerCase());
                        }
                    }
                } else {
                    System.out.print(" - ");
                }
            }
        }
    }
}
