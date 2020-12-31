package sample;


import backend.chess.ChessBoard;
import backend.chess.ChessManager;
import backend.chess.agents.Player;
import backend.chess.pieces.revised.*;

public class test {

    private static ChessBoard cb;

    public static void main(String[] args) {
        cb = new ChessBoard();
        cb.display();
        System.out.println();
        System.out.println("---------------------------");


        Player diego = new Player(cb, 1);
        Player ai = new Player(cb, 0);

        movePieceShorthand(diego, "e4");
        movePieceShorthand(ai, "d5");
        movePieceShorthand(diego, "exd5");
        movePieceShorthand(ai, "h5");
        movePieceShorthand(diego, "g4");
        movePieceShorthand(ai, "hxg4");
        movePieceShorthand(diego, "Qxg4");
        movePieceShorthand(ai, "Bxg4");
        movePieceShorthand(diego, "d3");
        movePieceShorthand(ai, "Nf6");
        movePieceShorthand(diego, "Ba6");
    }

    public static void movePieceShorthand(Player p, String notation){
        if(p.movePiece(notation)){
            cb.display();
        } else {
            System.out.println();
            System.out.println("Not Possible move! > " + notation);
        }
        System.out.println();
        System.out.println("---------------------------");

    }

}
