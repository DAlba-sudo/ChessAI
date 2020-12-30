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

        if(diego.movePiece("Nf3")){
            cb.display();
        } else {
            System.out.println();
            System.out.println("Not Possible move!");
        }

        if(ai.movePiece("Nf6")){
            cb.display();
        } else {
            System.out.println();
            System.out.println("Not Possible move!");
        }

        if(diego.movePiece("Ne5")){
            cb.display();
        } else {
            System.out.println();
            System.out.println("Not Possible move!");
        }

        if(ai.movePiece("Ne4")){
            cb.display();
        } else {
            System.out.println();
            System.out.println("Not Possible move!");
        }

        if(diego.movePiece("Qe2")){
            cb.display();
        } else {
            System.out.println();
            System.out.println("Not Possible move!");
        }

        if(ai.movePiece("Qe7")){
            cb.display();
        } else {
            System.out.println();
            System.out.println("Not Possible move!");
        }

        if(diego.movePiece("Qxe4")){
            cb.display();
        } else {
            System.out.println();
            System.out.println("Not Possible move!");
        }

        if(ai.movePiece("Nf6")){
            cb.display();
        } else {
            System.out.println();
            System.out.println("Not Possible move!");
        }

        if(diego.movePiece("Bb5")){
            cb.display();
        } else {
            System.out.println();
            System.out.println("Not Possible move!");
        }

        if(ai.movePiece("c6")){
            cb.display();
        } else {
            System.out.println();
            System.out.println("Not Possible move!");
        }

        if(diego.movePiece("Bxc6")){
            cb.display();
        } else {
            System.out.println();
            System.out.println("Not Possible move!");
        }

        if(ai.movePiece("bxc6")){
            cb.display();
        } else {
            System.out.println();
            System.out.println("Not Possible move!");
        }

    }

}
