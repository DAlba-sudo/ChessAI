package sample;


import backend.chess.ChessBoard;
import backend.chess.ChessManager;
import backend.chess.agents.Player;
import backend.chess.pieces.revised.*;

public class test {

    public static void main(String[] args) {
        ChessBoard cb = new ChessBoard();
        cb.display();

        Player diego = new Player(cb, 1);
        Player ai = new Player(cb, 0);

        if(diego.movePiece("e4")){
            cb.display();
        } else {
            System.out.println();
            System.out.println("Not Possible move!");
        }

        if(ai.movePiece("e5")){
            cb.display();
        } else {
            System.out.println();
            System.out.println("Not Possible move!");
        }



    }

}
