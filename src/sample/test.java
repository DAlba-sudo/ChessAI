package sample;


import backend.chess.ChessManager;
import backend.chess.pieces.revised.*;

public class test {

    public static void main(String[] args) {
        KnightV2 pawn = new KnightV2("e4", 1);

        for(int r = 1; r <= 8; r++){
            System.out.println();
            for(int c = 1; c<=8; c++){
                String notation = ChessManager.numToFile(r) + Integer.toString(c);
                if(pawn.isPosLegal(notation)){
                    System.out.print(" y ");
                } else {
                    System.out.print(" n ");
                }
            }
        }
    }
}
